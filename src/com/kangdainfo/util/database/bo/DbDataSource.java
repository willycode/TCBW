package com.kangdainfo.util.database.bo;

import java.io.Serializable;

/**
 * Table
 *
 * @author catty.liao
 * @version 1.0, Created on 2006/6/28
 */
public class DbDataSource implements Serializable {

    public DbDataSource() {
    }

    public DbDataSource(String driverClassName, String url, String username,
            String password, boolean commit, boolean only, int active,
            long wait, int idle, boolean isDefault) {
        this.driverClassName = driverClassName;
        this.url = url;
        this.username = username;
        this.password = password;
        this.defaultAutoCommit = commit;
        this.defaultReadOnly = only;
        this.maxActive = active;
        this.maxWait = wait;
        this.minIdle = idle;
        this.defaultDataSource = isDefault;
    }

    private String driverClassName;

    private String url;

    private String username;

    private String password;

    private int maxActive;

    private long maxWait;

    private int minIdle;

    private boolean defaultAutoCommit = false;

    private boolean defaultReadOnly = false;

    private boolean defaultDataSource = false;

    public boolean isDefaultAutoCommit() {
        return defaultAutoCommit;
    }

    public void setDefaultAutoCommit(boolean defaultAutoCommit) {
        this.defaultAutoCommit = defaultAutoCommit;
    }

    public boolean isDefaultReadOnly() {
        return defaultReadOnly;
    }

    public void setDefaultReadOnly(boolean defaultReadOnly) {
        this.defaultReadOnly = defaultReadOnly;
    }

    public String getDriverClassName() {
        return driverClassName;
    }

    public void setDriverClassName(String driverClassName) {
        this.driverClassName = driverClassName;
    }

    public int getMinIdle() {
        return minIdle;
    }

    public void setMinIdle(int minIdle) {
        this.minIdle = minIdle;
    }

    public int getMaxActive() {
        return maxActive;
    }

    public long getMaxWait() {
        return maxWait;
    }

    public void setMaxWait(long maxWait) {
        this.maxWait = maxWait;
    }

    public void setMaxActive(int maxActive) {
        this.maxActive = maxActive;
    }

    public void setMaxWait(int maxWait) {
        this.maxWait = maxWait;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean isDefaultDataSource() {
        return defaultDataSource;
    }

    public void setDefaultDataSource(boolean defaultDataSource) {
        this.defaultDataSource = defaultDataSource;
    }

}
