package design_parrent.pojo.p12_iterator;

public interface MyIter<T> {

    T next();

    T first();

    T last();

    Boolean isFirst();

    Boolean isLast();

    Boolean hasNext();

    void remove();


}
