package jvm.test;

public class T6_TaoYiFenXi {
    private T6_TaoYiFenXi taoYiFenXi;

    /**
     * 作为返回值可以被外部引用，发生了逃逸
     * @return
     */
    public T6_TaoYiFenXi getInstance() {
        return taoYiFenXi == null? new T6_TaoYiFenXi() : taoYiFenXi;

    }

    /**
     * 为成员赋值 发生了逃逸
     */
    public void setTaoYiFenXi() {
        this.taoYiFenXi = new T6_TaoYiFenXi ();
    }

    /**
     * 对象作用域仅在当前方法提中 没有发生逃逸
     */
    public void noTaoYi() {
        T6_TaoYiFenXi t = new T6_TaoYiFenXi ();
    }

    /**
     * 引用成员变量的值，发生了逃逸
     */
    public void  useTao(){
      T6_TaoYiFenXi t=  getInstance();
    }
}
