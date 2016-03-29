package com.mfq.utils.datasource;

import org.apache.ibatis.session.SqlSessionFactory;

/**
 * Created by hui on 16/3/28.
 */
public class DynamicSqlSessionTemplate extends org.mybatis.spring.SqlSessionTemplate {
    public DynamicSqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
        super(sqlSessionFactory);
    }


}
