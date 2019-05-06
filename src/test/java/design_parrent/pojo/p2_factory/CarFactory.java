package design_parrent.pojo.p2_factory;

public interface CarFactory {
    Eniger createE();

    Tier createT();

    Site createS();

}


class GoodCar implements CarFactory {


    @Override
    public Eniger createE() {
        return null;
    }

    @Override
    public Tier createT() {
        return null;
    }

    @Override
    public Site createS() {
        return null;
    }
}

class NormalCar implements CarFactory {


    @Override
    public Eniger createE() {
        return null;
    }

    @Override
    public Tier createT() {
        return null;
    }

    @Override
    public Site createS() {
        return null;
    }
}