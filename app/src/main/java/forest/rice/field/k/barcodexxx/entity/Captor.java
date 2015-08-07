package forest.rice.field.k.barcodexxx.entity;

import java.util.UUID;

public class Captor {

    private String captorId;
    private String captorName;

    public Captor() {

    }

    public Captor(String captorName){
        this.captorId = UUID.randomUUID().toString();
        this.captorName = captorName;
    }

    public String getCaptorId() {
        return captorId;
    }

    public void setCaptorId(String captorId) {
        this.captorId = captorId;
    }

    public String getCaptorName() {
        return captorName;
    }

    public void setCaptorName(String captorName) {
        this.captorName = captorName;
    }
}
