/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.Serializable;
import java.util.ArrayList;

public class AvailListRegistros implements Serializable {

    private static final long SerialVersioUID = 616L;
    ArrayList<EliminarRegistros> list = new ArrayList();


    public void addPosition(long position, int llave) {
        list.add(new EliminarRegistros(position, llave));
    }

    public AvailListRegistros() {
    }

    public int size() {
        return list.size();
    }

    public EliminarRegistros pop() {
        if (size() > 0) {
            EliminarRegistros temp = list.get(0);
            list.remove(0);
            return temp;
        } else {
            return null;
        }
    }
}
