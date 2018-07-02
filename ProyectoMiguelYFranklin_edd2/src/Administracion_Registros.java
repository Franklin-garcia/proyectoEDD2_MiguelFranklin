/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.BufferedWriter;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.RandomAccessFile;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author miguel
 */
public class Administracion_Registros implements Serializable {

    private static final long SerialVersioUID = 6900L;
    private AvailListCampos AvailListCampos = new AvailListCampos();
    private ArrayList<Campos> ListCampos = new ArrayList();
    private String dirPath;
    private boolean DireccionExistente;
    B_Plus_Tree<Integer> Arbol = new B_Plus_Tree<Integer>(2000, 2000, 1500, 0);
    private int CantidadRegistros = 0;
    private AvailListRegistros AvailListRegistros = new AvailListRegistros();

    public Administracion_Registros() {
        DireccionExistente = false;

    }

    public ArrayList<Campos> getListCampos() {
        return ListCampos;
    }

    public void setListCampos(ArrayList<Campos> ListCampos) {
        this.ListCampos = ListCampos;
    }

    public Administracion_Registros(String path) {
        String dirPatch = path;
        DireccionExistente = true;
    }

    public boolean dirExist() {
        File bd = new File(dirPath + "/bd.txt");
        File availCampos = new File(dirPath + "/AvailListCampos.alc");
        if (!bd.exists()) {
            return false;
        }
        if (!availCampos.exists()) {
            return false;
        }
        return true;
    }

    public String getDirPath() {
        return dirPath;
    }

    public void setDirPath(String dirPath) {
        this.dirPath = dirPath;
    }

    public AvailListRegistros getAvailListRegsitros() {
        return AvailListRegistros;
    }

    public void setAvlr(AvailListRegistros availListRegistros) {
        this.AvailListRegistros = availListRegistros;
    }

    public AvailListCampos getAvlc() {
        return AvailListCampos;
    }

    public void saveAvailListCampos() {
        //Guardo el AvailList de Campos
        FileOutputStream fw = null;
        ObjectOutputStream bw = null;
        try {
            fw = new FileOutputStream(dirPath + "/AvailList.alc");
            bw = new ObjectOutputStream(fw);
            bw.writeObject(AvailListCampos);
            bw.flush();
            bw.close();
            fw.close();
        } catch (Exception e) {

        } finally {
            try {
                bw.close();
                fw.close();
            } catch (Exception e) {
            }
        }//Termino de Guardar Avail List Campos!
    }

    public void setAvlc(AvailListCampos avlc) {
        this.AvailListCampos = avlc;
    }

    public String getDirPatch() {
        return dirPath;
    }

    public void setDirPatch(String dirPatch) {
        this.dirPath = dirPatch;
        DireccionExistente = true;
    }

    public boolean isDirExist() {
        return DireccionExistente;
    }

    public void setDirExist(boolean dirExist) {
        this.DireccionExistente = dirExist;
    }

    public ArrayList<Campos> getCampos() {
        return ListCampos;
    }

    public void setCampos(Campos campos) {
        this.ListCampos.add(campos);
    }

    int posicionArchivoCampos(int pos) {
        if (pos == 0) {
            return 0;
        } else {
            return (pos * 73) + 3;
        }

    }

    public void deleteRegistro(long posicion, String pathTxt) throws FileNotFoundException, IOException {
        RandomAccessFile fileAF = new RandomAccessFile(pathTxt, "rw");
        fileAF.seek(posicion);
        String x = "X";
        System.out.println("Posicion en el deelete " + posicion);
        fileAF.write(x.getBytes());
        AvailListRegistros.addPosition(posicion, 0);

    }

    public void deleteRecordTREE(int llave) {
        Arbol.delete(llave);
    }

    long PosicionRegistro(long pos) {
        if (pos == 1) {
            System.out.println("retorna base");
            return 201;
        } else {
            int suma = 0;
            int n=0;
            for (Campos u : ListCampos) {
                suma += u.getLongitud()+1;
                
            }
            suma+=-2+ListCampos.size();
            System.out.println(201 + (suma * (pos-1))+"antes de retornar");
            return 201 + (suma * (pos-1));

        }
    }

    public void addRegistro(String llave, long initial, ArrayList<Campos> ListCapmpos, String registro) throws FileNotFoundException, IOException {
        String retVal = "+|";
        int llaveInt = Integer.parseInt(llave);
        /*
        ACA SE GUARDA EL REGISTRO
        int pos;
        int array = 0;
        for (Campos u : ListCampos) {
            retVal += registro.get(array);
            System.out.println("");

            for (int i = 0; i < u.getLongitud()- llaveInt; i++) {
                retVal += " ";
            }
            retVal += "|";
            array++;
        }
         */
        CantidadRegistros++;
        System.out.println(CantidadRegistros);
        if (initial != -1) {
            Arbol.AddNode(llaveInt, initial, initial);
        } else {
            System.out.println("En el arbol parametros:  "+"llave: "+llaveInt+"   Posicion Registro:  "+PosicionRegistro(CantidadRegistros));
            Arbol.AddNode(llaveInt, PosicionRegistro(CantidadRegistros), PosicionRegistro(CantidadRegistros));
        }

        System.out.println(Arbol.print());

    }

    public B_Plus_Tree getBtree() {
        return Arbol;
    }


    public void setBtree(B_Plus_Tree tree) {
        this.Arbol = tree;
    }

    public long getCant_registros() {
        return CantidadRegistros;
    }

    public void setCant_registros(int cant_registros) {
        this.CantidadRegistros = cant_registros;
    }

    public ArrayList<String> ListRecord(long pos,String File) throws FileNotFoundException, IOException {
        ArrayList<String> array = new ArrayList();
        RandomAccessFile fileAF = new RandomAccessFile(new File(File), "r");
        fileAF.seek(pos);
        String add = fileAF.readLine();
        int last = 0;
        for (int i = 0; i < add.length(); i++) {
            if (add.charAt(i) == '|') {
                array.add(add.substring(last, i));
                last = i + 1;
            }
        }

        fileAF.close();
        return array;
    }

    public int getCantidadRegistros() {
        return CantidadRegistros;
    }

    public void setCantidadRegistros(int CantidadRegistros) {
        this.CantidadRegistros = CantidadRegistros;
    }

    public void readTree() {
        try {

            FileInputStream entrada = new FileInputStream(new File(dirPath + "/btree.lol"));
            ObjectInputStream objeto = new ObjectInputStream(entrada);
            try {
                Arbol = (B_Plus_Tree) objeto.readObject();
                AvailListCampos = (AvailListCampos) objeto.readObject();
                AvailListRegistros = (AvailListRegistros) objeto.readObject();
                CantidadRegistros = (int) objeto.readObject();
            } catch (EOFException e) {
            }
            objeto.close();
            entrada.close();
        } catch (Exception e) {

        }
    }

    public void writeBTree(String file) {
        FileOutputStream fw = null;
        ObjectOutputStream bw = null;
        try {
            fw = new FileOutputStream("./" + file + "/btree.lol");
            bw = new ObjectOutputStream(fw);
            bw.writeObject(Arbol);
            bw.writeObject(AvailListCampos);
            bw.writeObject(AvailListRegistros);
            bw.writeObject(getCant_registros());

            bw.flush();
        } catch (Exception e) {

        } finally {
            try {
                bw.close();
                fw.close();
            } catch (Exception e) {
            }
        }
    }

    public void readBtree() {

    }

    public ArrayList<String> foundRecord(long position, String x) throws FileNotFoundException, IOException {
        ArrayList<String> array = new ArrayList();
        try {
            RandomAccessFile fileAF = new RandomAccessFile(new File(x), "r");
            fileAF.seek(position + 2);
            String add = fileAF.readLine();
            int last = 0;
            for (int i = 0; i < add.length(); i++) {
                if (add.charAt(i) == '|') {
                    array.add(add.substring(last, i));
                    last = i + 1;
                }
            }
            System.out.println(array.toString());

            fileAF.close();

        } catch (Exception e) {

        }
        return array;
    }
}
