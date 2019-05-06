package design_parrent.pojo.p2_factory;

public interface Eniger {
    Eniger create();
}

class GoodEniger implements Eniger {

    private String value;


    public GoodEniger(String value) {
        this.value = value;
    }


    @Override
    public Eniger create() {
        return new GoodEniger("---GoodEniger-");
    }
}

class NormalEniger implements Eniger {

    private String value;


    public NormalEniger(String value) {
        this.value = value;
    }

    @Override
    public Eniger create() {
        return new NormalEniger("---NormalEniger-");
    }
}
