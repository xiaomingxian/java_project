package design_parrent.pojo.p11_zerenlian;

import lombok.Data;

@Data
public class Leave {

    private String name;

    private Integer days;

    private String reason;

    public Leave(String name, Integer days, String reason) {
        this.name = name;
        this.days = days;
        this.reason = reason;
    }
}
