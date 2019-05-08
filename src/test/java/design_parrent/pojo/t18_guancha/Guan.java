package design_parrent.pojo.t18_guancha;

import lombok.Data;

import java.util.Observable;
import java.util.Observer;

@Data
public class Guan implements Observer {
    private Integer mystatus;

    @Override
    public void update(Observable o, Object arg) {
        System.out.println(o);
        this.mystatus = ((publish) o).getStatus();
    }
}