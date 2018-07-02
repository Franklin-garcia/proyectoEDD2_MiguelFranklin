

import java.io.Serializable;
import java.util.ArrayList;

public class AvailListCampos implements Serializable {

    private static final long SerialVersioUID = 615L;
    private ArrayList<EliminarCampos> Lista = new ArrayList();

    public AvailListCampos() {
    }

    public ArrayList<EliminarCampos> getList() {
        return Lista;
    }

    public void setList(ArrayList<EliminarCampos> Lista) {
        this.Lista = Lista;
    }

    public void addPosition(int PosicionInicial) {
        Lista.add(new EliminarCampos(PosicionInicial));
    }
    
    public void deletePosition(int pos) {
        try {
            Lista.remove(pos);
        } catch (Exception e) {
            System.out.println("Error al Eliminar de la AvailList Campos");
        }
    }

    public int getSize() {
        return Lista.size();
    }
}
