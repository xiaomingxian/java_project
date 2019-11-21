package gu_pao.p1_design_parrent.code.p4_proxy.pojo;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;


/**
 * Spring中的dataSource接口 仅仅是demo
 */
public class SpringDynamicDataSource extends AbstractRoutingDataSource {


    private DynammicDataSource dynammicDataSource;

    public SpringDynamicDataSource(DynammicDataSource dynammicDataSource) {
        this.dynammicDataSource = dynammicDataSource;
    }


    @Override
    protected Object determineCurrentLookupKey() {
        return this.dynammicDataSource.get();
    }
}
