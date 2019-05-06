package design_parrent.pojo.p2_factory;

import lombok.Data;

public interface Site {
    Site create();
}


@Data
class GoodSite implements Site {
    private String value;

    public GoodSite(String value) {
        this.value = value;
    }


    @Override
    public Site create() {
        return new GoodSite("---Good-Site");
    }
}


@Data
class NormalSite implements Site {
    private String value;

    public NormalSite(String value) {
        this.value = value;
    }


    @Override
    public Site create() {
        return new GoodSite("---NormalSite--");
    }
}
