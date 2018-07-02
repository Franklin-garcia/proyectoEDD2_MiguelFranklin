

import java.io.Serializable;

public class EliminarCampos implements Serializable {

    private static final long SerialVersioUID = 637L;
    private int initial_position;
    private int final_position;

    public EliminarCampos(int initial_position) {
        this.initial_position = initial_position;
        this.final_position = initial_position + 78;
    }

    public int getInitial_position() {
        return initial_position;
    }

    public void setInitial_position(int initial_position) {
        this.initial_position = initial_position;
    }

    public int getFinal_position() {
        return final_position;
    }

    public void setFinal_position(int final_position) {
        this.final_position = final_position;
    }

}
