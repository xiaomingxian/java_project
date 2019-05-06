package design_parrent.pojo.p2_factory;

public interface Tier {

    Tier create();

}


class GoodTier implements Tier {
    private String value;

    public GoodTier(String value) {
        this.value = value;
    }

    @Override
    public Tier create() {
        return new GoodTier("-----goodValue---");
    }
}

class NormalTier implements Tier {
    private String value;

    public NormalTier(String value) {
        this.value = value;
    }

    @Override
    public Tier create() {
        return new NormalTier("-----goodValue---");
    }
}