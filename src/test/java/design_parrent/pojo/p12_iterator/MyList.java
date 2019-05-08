package design_parrent.pojo.p12_iterator;

import java.util.ArrayList;
import java.util.List;

public class MyList {

    List list = new ArrayList();

    public void add(Object o) {
        list.add(o);
    }

    public MyIter getIter() {
        return new Itr();
    }

    //list 中也是用的内部类----可以直接使用成员变量
    private class Itr implements MyIter<Object> {
        private int cursor;//游标

        @Override
        public Object next() {
            Object o = list.get(cursor);
            cursor++;
            return o;
        }

        @Override
        public Object first() {
            if (list.size() > 0)
                return list.get(0);
            else
                return null;
        }

        @Override
        public Object last() {
            if (list.size() > 0)
                return list.get(list.size() - 1);
            else
                return null;
        }

        @Override
        public Boolean isFirst() {
            if (cursor == 0) {
                return true;
            } else
                return false;
        }

        @Override
        public Boolean isLast() {
            if (cursor == list.size() - 1)
                return true;
            else
                return false;
        }

        @Override
        public Boolean hasNext() {
            if (cursor < list.size())
                return true;
            else
                return false;
        }

        @Override
        public void remove() {
            Object remove = list.remove(cursor);
        }


    }

}
