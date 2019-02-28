package com.fast.family.datasource;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.xa.DruidXADataSource;
import com.atomikos.jdbc.AtomikosDataSourceBean;
import com.fast.family.datasource.druid.DruidDataSourceProperties;
import com.fast.family.datasource.exception.DynamicDataSourceException;
import lombok.extern.slf4j.Slf4j;

import javax.sql.DataSource;
import javax.sql.XADataSource;
import java.sql.SQLException;

/**
 * @author 张顺
 * @version 1.0
 */
@Slf4j
public class DynamicDataSourceFatcory {

    public static DataSource createDateSource(DynamicDataSourceProperties properties, DataSource dataSource) {
        if (properties.getDruid() != null && dataSource instanceof DruidXADataSource) {
            return createAtomikosDataSouce(properties);
        } else if (properties.getDruid() != null && dataSource instanceof DruidDataSource) {
            return createDruidDataSource(properties.getDruid());
        }
        throw new DynamicDataSourceException("数据源创建失败");
    }

    private static DataSource createAtomikosDataSouce(DynamicDataSourceProperties properties) {
        AtomikosDataSourceBean dataSourceBean = new AtomikosDataSourceBean();
        dataSourceBean.setBorrowConnectionTimeout(properties.getAtomikos().getBorrowConnectionTimeout());
        dataSourceBean.setDefaultIsolationLevel(properties.getAtomikos().getDefaultIsolationLevel());
        try {
            dataSourceBean.setLoginTimeout(properties.getAtomikos().getLoginTimeout());
        } catch (SQLException e) {
            log.warn("init atomikos datasource param loginTimeout", e);
        }
        dataSourceBean.setUniqueResourceName(properties.getAtomikos().getUniqueResourceName());
        dataSourceBean.setMaintenanceInterval(properties.getAtomikos().getMaintenanceInterval());
        dataSourceBean.setMaxIdleTime(properties.getAtomikos().getMaxIdleTime());
        dataSourceBean.setMaxLifetime(properties.getAtomikos().getMaxLifetime());
        dataSourceBean.setMaxPoolSize(properties.getAtomikos().getMaxPoolSize());
        dataSourceBean.setMinPoolSize(properties.getAtomikos().getMinPoolSize());
        dataSourceBean.setReapTimeout(properties.getAtomikos().getReapTimeout());
        dataSourceBean.setTestQuery(properties.getAtomikos().getTestQuery());
        dataSourceBean.setXaDataSource(createXADruidDataSource(properties.getDruid()));
        dataSourceBean.setXaProperties(properties.getAtomikos().getXaProperties());
        dataSourceBean.setXaDataSourceClassName("com.alibaba.druid.pool.xa.DruidXADataSource");
        return dataSourceBean;
    }


    private static XADataSource createXADruidDataSource(DruidDataSourceProperties properties) {
        DruidXADataSource druidXADataSource = new DruidXADataSource();
        druidXADataSource.setUrl(properties.getUrl());
        druidXADataSource.setUsername(properties.getUsername());
        druidXADataSource.setPassword(properties.getPassword());
        druidXADataSource.setDriverClassName(properties.getDriverClassName());
        druidXADataSource.setInitialSize(properties.getInitialSize());
        druidXADataSource.setMaxActive(properties.getMaxActive());
        druidXADataSource.setMinIdle(properties.getMinIdle());
        druidXADataSource.setMaxWait(properties.getMaxWait());
        druidXADataSource.setTimeBetweenEvictionRunsMillis(properties.getTimeBetweenEvictionRunsMillis());
        druidXADataSource.setMinEvictableIdleTimeMillis(properties.getMinEvictableIdleTimeMillis());
        druidXADataSource.setMaxEvictableIdleTimeMillis(properties.getMaxEvictableIdleTimeMillis());
        druidXADataSource.setValidationQuery(properties.getValidationQuery());
        druidXADataSource.setValidationQueryTimeout(properties.getValidationQueryTimeout());
        druidXADataSource.setTestOnBorrow(properties.isTestOnBorrow());
        druidXADataSource.setTestOnReturn(properties.isTestOnReturn());
        druidXADataSource.setTestWhileIdle(properties.isTestWhileIdle());
        druidXADataSource.setPoolPreparedStatements(properties.isPoolPreparedStatements());
        druidXADataSource.setMaxOpenPreparedStatements(properties.getMaxOpenPreparedStatements());
        druidXADataSource.setSharePreparedStatements(properties.isSharePreparedStatements());
        druidXADataSource.setConnectProperties(properties.getConnectionProperties());
        try {
            druidXADataSource.setFilters(properties.getFilters());
            druidXADataSource.init();
        } catch (SQLException e) {
            throw new DynamicDataSourceException("创建数据源失败", e);
        }
        return druidXADataSource;
    }

    private static DataSource createDruidDataSource(DruidDataSourceProperties properties) {
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setUrl(properties.getUrl());
        druidDataSource.setUsername(properties.getUsername());
        druidDataSource.setPassword(properties.getPassword());
        druidDataSource.setDriverClassName(properties.getDriverClassName());
        druidDataSource.setInitialSize(properties.getInitialSize());
        druidDataSource.setMaxActive(properties.getMaxActive());
        druidDataSource.setMinIdle(properties.getMinIdle());
        druidDataSource.setMaxWait(properties.getMaxWait());
        druidDataSource.setTimeBetweenEvictionRunsMillis(properties.getTimeBetweenEvictionRunsMillis());
        druidDataSource.setMinEvictableIdleTimeMillis(properties.getMinEvictableIdleTimeMillis());
        druidDataSource.setMaxEvictableIdleTimeMillis(properties.getMaxEvictableIdleTimeMillis());
        druidDataSource.setValidationQuery(properties.getValidationQuery());
        druidDataSource.setValidationQueryTimeout(properties.getValidationQueryTimeout());
        druidDataSource.setTestOnBorrow(properties.isTestOnBorrow());
        druidDataSource.setTestOnReturn(properties.isTestOnReturn());
        druidDataSource.setTestWhileIdle(properties.isTestWhileIdle());
        druidDataSource.setPoolPreparedStatements(properties.isPoolPreparedStatements());
        druidDataSource.setMaxOpenPreparedStatements(properties.getMaxOpenPreparedStatements());
        druidDataSource.setSharePreparedStatements(properties.isSharePreparedStatements());
        druidDataSource.setConnectProperties(properties.getConnectionProperties());
        try {
            druidDataSource.setFilters(properties.getFilters());
            druidDataSource.init();
        } catch (SQLException e) {
            throw new DynamicDataSourceException("创建数据源失败", e);
        }
        return druidDataSource;
    }

}
