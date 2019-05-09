package design_parrent.pojo.t19_beiwanglu;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * 责任人类
 */
@Data
public class SaveFactory {

    private Save save;

    private Integer index;

    private List<Save> list = new ArrayList<>();

    public void Saves(Save save) {
        list.add(save);
        index = list.size() - 1;
    }

    public Save back() {
        if (index < 0) {
            System.out.println("已经撤回完毕");
            return null;
        }

        Save save = list.get(index);
        index--;
        return save;
    }


}
