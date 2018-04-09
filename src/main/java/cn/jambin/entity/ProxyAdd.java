package cn.jambin.entity;

import java.util.Date;

public class ProxyAdd {
    private String host;

    private Integer port;

    private Byte flag;

    private Date time;

    private Integer total;

    private Integer errTotal;

    private String errorInfo;

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host == null ? null : host.trim();
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public Byte getFlag() {
        return flag;
    }

    public void setFlag(Byte flag) {
        this.flag = flag;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getErrTotal() {
        return errTotal;
    }

    public void setErrTotal(Integer errTotal) {
        this.errTotal = errTotal;
    }

    public String getErrorInfo() {
        return errorInfo;
    }

    public void setErrorInfo(String errorInfo) {
        this.errorInfo = errorInfo == null ? null : errorInfo.trim();
    }
}