package mybatis.test;

import dao.PermissionMapper;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.defaults.DefaultSqlSession;
import org.junit.Test;
import org.mybatis.spring.annotation.MapperScannerRegistrar;

public class T0_Ibatis {

    /**
     * 两种写sql的方式
     */
    @Test
    public void t1() {

//查询


//method1
        SqlSession sqlSession = null;
        sqlSession.selectOne("明明空间.sql语句ID", "参数");

//method2--- 面向对象的方式-最终调用的还是method1
        PermissionMapper mapper = sqlSession.getMapper(PermissionMapper.class);
        mapper.getPermissions(null);
//////////////////////////////////////////////////////////////////////////////////////////////////////

        //SqlSession 是个接口--默认的实现类：
        //mybatis/hibernate 自带一级缓存 与spring整合后失效
        DefaultSqlSession defaultSqlSession = null;
        //selectList的核心     MappedStatement ms = this.configuration.getMappedStatement(statement);

//扫描Mapper--spring 实现了扫描 扫描 basePackage[自己配置的包(注解或xml)]下的 .class[报名+扫描到的文件夹得到类名]
        MapperScannerRegistrar mapperScannerRegistrar = null;
        //doScan()方法

//通用sql无法确保查出一条数据----mybatis 的做法 调用selectList 先去查如果查处一条就正常-超过一条就报错
        //public <T> T selectOne(String statement, Object parameter) {
        //    List<T> list = this.selectList(statement, parameter);
        //    if (list.size() == 1) {
        //        return list.get(0);
        //    } else if (list.size() > 1) {
        //        throw new TooManyResultsException("Expected one result (or null) to be returned by selectOne(), but found: " + list.size());
        //    } else {
        //        return null;

//selectOne 最终调用的是selectList
        //public <E> List<E> selectList(String statement, Object parameter, RowBounds rowBounds) {
        //    List var5;
        //    try {
        //        MappedStatement ms = this.configuration.getMappedStatement(statement);
        //        var5 = this.executor.query(ms, this.wrapCollection(parameter), rowBounds, Executor.NO_RESULT_HANDLER);
        //    } catch (Exception var9) {
        //        throw ExceptionFactory.wrapException("Error querying database.  Cause: " + var9, var9);
        //    } finally {
        //        ErrorContext.instance().reset();
        //    }
        //
        //    return var5;
        //}


    }
}
