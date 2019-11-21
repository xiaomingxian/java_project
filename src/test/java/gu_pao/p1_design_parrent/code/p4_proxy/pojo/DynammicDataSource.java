package gu_pao.p1_design_parrent.code.p4_proxy.pojo;

public class DynammicDataSource {

    private static String DEFAULT_DATASOURCE = "默认数据源";

    private static final ThreadLocal<String> local = new ThreadLocal<>();


    public static void set(String dataSource) {
        local.set(dataSource);
    }

    public static void resetDataSource() {
        local.set(DEFAULT_DATASOURCE);
    }

    public static String get() {
        return local.get();
    }
}
