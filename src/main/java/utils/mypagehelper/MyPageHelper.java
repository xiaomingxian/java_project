package utils.mypagehelper;

/**
 * 功能:综合实体类条件查询和分页查询
 */
public class MyPageHelper<T> {
    private Object object;
    private MyPage mypage;
    private String describe;


    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    public MyPage getMypage() {
        return mypage;
    }

    public void setMypage(MyPage mypage) {
        this.mypage = mypage;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }
}
