package com.mfq.utils.datasource;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import javax.sql.DataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.datasource.LazyConnectionDataSourceProxy;

/**
 * Created by hui on 16/3/29.
 */
public class DynamicLazyConnectionDataSourceProxy extends LazyConnectionDataSourceProxy{

    private static final Logger log = LoggerFactory
            .getLogger(DynamicDataSource.class);

    private AtomicInteger counter = new AtomicInteger();

    /**
     * master库 dataSource
     */
    private DataSource master;

    /**
     * slaves
     */
    private List<DataSource> slaves;

    public DynamicLazyConnectionDataSourceProxy() {
    }

    public DynamicLazyConnectionDataSourceProxy(DataSource targetDataSource) {

        this.afterPropertiesSet();
    }


    /**
     * 根据标识
     * 获取数据源
     *
     */
    protected DataSource determineTargetDataSource() {
        DataSource returnDataSource = null;
        if(DataSourceHolder.isMaster()){
            returnDataSource = master;
        }else if(DataSourceHolder.isSlave()){
            int count = counter.incrementAndGet();
            if(count>1000000){
                counter.set(0);
            }
            int n = slaves.size();
            int index = count%n;
            returnDataSource = slaves.get(index);
        }else{
            returnDataSource = master;
        }
        if(returnDataSource instanceof org.apache.tomcat.jdbc.pool.DataSource){
            org.apache.tomcat.jdbc.pool.DataSource source = (org.apache.tomcat.jdbc.pool.DataSource)returnDataSource;
            String jdbcUrl = source.getUrl();
            log.info("jdbcUrl:" + jdbcUrl);
        }
        return returnDataSource;
    }


    @Override
    public Connection getConnection() throws SQLException {
        return determineTargetDataSource().getConnection();
    }

    @Override
    public Connection getConnection(String username, String password) throws SQLException {
        return determineTargetDataSource().getConnection(username,password);
    }



    public DataSource getMaster() {
        return master;
    }

    public void setMaster(DataSource master) {
        this.master = master;
    }

    public List<DataSource> getSlaves() {
        return slaves;
    }

    public void setSlaves(List<DataSource> slaves) {
        this.slaves = slaves;
    }

}

