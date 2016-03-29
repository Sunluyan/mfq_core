package com.mfq.utils.datasource;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.SQLException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.core.Constants;
import org.springframework.jdbc.datasource.ConnectionProxy;

/**
 * Created by hui on 16/3/29.
 */
public class DynamicLazyConnectionDataSourceProxy extends DynamicDataSource {
    private static final Constants constants = new Constants(Connection.class);
    private static final Log logger = LogFactory.getLog(DynamicLazyConnectionDataSourceProxy.class);
    private Boolean defaultAutoCommit;
    private Integer defaultTransactionIsolation;

    public DynamicLazyConnectionDataSourceProxy() {
    }

    public DynamicLazyConnectionDataSourceProxy(DynamicDataSource targetDataSource) {
        this.setMaster(targetDataSource.getMaster());
        this.setSlaves(targetDataSource.getSlaves());
        this.afterPropertiesSet();
    }

    public void setDefaultAutoCommit(boolean defaultAutoCommit) {
        this.defaultAutoCommit = Boolean.valueOf(defaultAutoCommit);
    }

    public void setDefaultTransactionIsolation(int defaultTransactionIsolation) {
        this.defaultTransactionIsolation = Integer.valueOf(defaultTransactionIsolation);
    }

    public void setDefaultTransactionIsolationName(String constantName) {
        this.setDefaultTransactionIsolation(constants.asNumber(constantName).intValue());
    }

    public void afterPropertiesSet() {
        super.afterPropertiesSet();
        if(this.defaultAutoCommit == null || this.defaultTransactionIsolation == null) {
            try {
                Connection ex = this.determineTargetDataSource().getConnection();

                try {
                    this.checkDefaultConnectionProperties(ex);
                } finally {
                    ex.close();
                }
            } catch (SQLException var6) {
                logger.warn("Could not retrieve default auto-commit and transaction isolation settings", var6);
            }
        }

    }

    protected synchronized void checkDefaultConnectionProperties(Connection con) throws SQLException {
        if(this.defaultAutoCommit == null) {
            this.defaultAutoCommit = Boolean.valueOf(con.getAutoCommit());
        }

        if(this.defaultTransactionIsolation == null) {
            this.defaultTransactionIsolation = Integer.valueOf(con.getTransactionIsolation());
        }

    }

    protected Boolean defaultAutoCommit() {
        return this.defaultAutoCommit;
    }

    protected Integer defaultTransactionIsolation() {
        return this.defaultTransactionIsolation;
    }

    public Connection getConnection() throws SQLException {
        return (Connection)Proxy.newProxyInstance(ConnectionProxy.class.getClassLoader(), new Class[]{ConnectionProxy.class}, new DynamicLazyConnectionDataSourceProxy.LazyConnectionInvocationHandler());
    }

    public Connection getConnection(String username, String password) throws SQLException {
        return (Connection)Proxy.newProxyInstance(ConnectionProxy.class.getClassLoader(), new Class[]{ConnectionProxy.class}, new DynamicLazyConnectionDataSourceProxy.LazyConnectionInvocationHandler(username, password));
    }

    private class LazyConnectionInvocationHandler implements InvocationHandler {
        private String username;
        private String password;
        private Boolean readOnly;
        private Integer transactionIsolation;
        private Boolean autoCommit;
        private boolean closed;
        private Connection target;

        public LazyConnectionInvocationHandler() {
            this.readOnly = Boolean.FALSE;
            this.closed = false;
            this.autoCommit = DynamicLazyConnectionDataSourceProxy.this.defaultAutoCommit();
            this.transactionIsolation = DynamicLazyConnectionDataSourceProxy.this.defaultTransactionIsolation();
        }

        public LazyConnectionInvocationHandler(String username, String password) {
            this();
            this.username = username;
            this.password = password;
        }

        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            if(method.getName().equals("equals")) {
                return Boolean.valueOf(proxy == args[0]);
            } else if(method.getName().equals("hashCode")) {
                return Integer.valueOf(System.identityHashCode(proxy));
            } else {
                if(method.getName().equals("unwrap")) {
                    if(((Class)args[0]).isInstance(proxy)) {
                        return proxy;
                    }
                } else if(method.getName().equals("isWrapperFor")) {
                    if(((Class)args[0]).isInstance(proxy)) {
                        return Boolean.valueOf(true);
                    }
                } else if(method.getName().equals("getTargetConnection")) {
                    return this.getTargetConnection(method);
                }

                if(!this.hasTargetConnection()) {
                    if(method.getName().equals("toString")) {
                        return "Lazy Connection proxy for target DataSource [" + DynamicLazyConnectionDataSourceProxy.this.determineTargetDataSource() + "]";
                    }

                    if(method.getName().equals("isReadOnly")) {
                        return this.readOnly;
                    }

                    if(method.getName().equals("setReadOnly")) {
                        this.readOnly = (Boolean)args[0];
                        return null;
                    }

                    if(method.getName().equals("getTransactionIsolation")) {
                        if(this.transactionIsolation != null) {
                            return this.transactionIsolation;
                        }
                    } else {
                        if(method.getName().equals("setTransactionIsolation")) {
                            this.transactionIsolation = (Integer)args[0];
                            return null;
                        }

                        if(method.getName().equals("getAutoCommit")) {
                            if(this.autoCommit != null) {
                                return this.autoCommit;
                            }
                        } else {
                            if(method.getName().equals("setAutoCommit")) {
                                this.autoCommit = (Boolean)args[0];
                                return null;
                            }

                            if(method.getName().equals("commit")) {
                                return null;
                            }

                            if(method.getName().equals("rollback")) {
                                return null;
                            }

                            if(method.getName().equals("getWarnings")) {
                                return null;
                            }

                            if(method.getName().equals("clearWarnings")) {
                                return null;
                            }

                            if(method.getName().equals("close")) {
                                this.closed = true;
                                return null;
                            }

                            if(method.getName().equals("isClosed")) {
                                return Boolean.valueOf(this.closed);
                            }

                            if(this.closed) {
                                throw new SQLException("Illegal operation: connection is closed");
                            }
                        }
                    }
                }

                try {
                    return method.invoke(this.getTargetConnection(method), args);
                } catch (InvocationTargetException var5) {
                    throw var5.getTargetException();
                }
            }
        }

        private boolean hasTargetConnection() {
            return this.target != null;
        }

        private Connection getTargetConnection(Method operation) throws SQLException {
            if(this.target == null) {
                if(DynamicLazyConnectionDataSourceProxy.logger.isDebugEnabled()) {
                    DynamicLazyConnectionDataSourceProxy.logger.debug("Connecting to database for operation \'" + operation.getName() + "\'");
                }

                this.target = this.username != null?DynamicLazyConnectionDataSourceProxy.this.determineTargetDataSource().getConnection(this.username, this.password):DynamicLazyConnectionDataSourceProxy.this.determineTargetDataSource().getConnection();
                DynamicLazyConnectionDataSourceProxy.this.checkDefaultConnectionProperties(this.target);
                if(this.readOnly.booleanValue()) {
                    try {
                        this.target.setReadOnly(this.readOnly.booleanValue());
                    } catch (Exception var3) {
                        DynamicLazyConnectionDataSourceProxy.logger.debug("Could not set JDBC Connection read-only", var3);
                    }
                }

                if(this.transactionIsolation != null && !this.transactionIsolation.equals(DynamicLazyConnectionDataSourceProxy.this.defaultTransactionIsolation())) {
                    this.target.setTransactionIsolation(this.transactionIsolation.intValue());
                }

                if(this.autoCommit != null && this.autoCommit.booleanValue() != this.target.getAutoCommit()) {
                    this.target.setAutoCommit(this.autoCommit.booleanValue());
                }
            } else if(DynamicLazyConnectionDataSourceProxy.logger.isDebugEnabled()) {
                DynamicLazyConnectionDataSourceProxy.logger.debug("Using existing database connection for operation \'" + operation.getName() + "\'");
            }

            return this.target;
        }

    }
}

