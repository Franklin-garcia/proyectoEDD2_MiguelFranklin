

import java.io.Serializable;

public class EliminarRegistros implements Serializable {

    private static final long SerialVersioUID = 789L;
    private long position;
    private int llave;

    public EliminarRegistros(long position, int llave) {
        this.position = position;
        this.llave = llave;
    }

    public long getPosition() {
        return position;
    }

    public void setPosition(long position) {
        this.position = position;
    }

    public int getLlave() {
        return llave;
    }

    public void setLlave(int llave) {
        this.llave = llave;
    }

}
