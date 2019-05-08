package design_parrent.pojo.t18_guancha;

import lombok.Data;

import java.util.Observable;

@Data
public class publish extends Observable {

    private Integer status;

    public void setStatus(Integer status) {
        this.status = status;
        setChanged();
        //notifyObservers(this.status);

        notifyObservers();
    }

}