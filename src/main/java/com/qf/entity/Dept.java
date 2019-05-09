package com.qf.entity;

import java.util.Date;

import lombok.Data;

@Data
public class Dept {
	
    private Integer id;

    private String dname;

    private String ddesc;

    private Integer dparentid;

    private Integer dindex;

    private Integer dstate;
    
    private Date createTime;
    
    private String parentname;
    
    private Integer subId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDname() {
        return dname;
    }

    public void setDname(String dname) {
        this.dname = dname == null ? null : dname.trim();
    }

    public String getDdesc() {
        return ddesc;
    }
    public void setDdesc(String ddesc) {
        this.ddesc = ddesc == null ? null : ddesc.trim();
    }

    public Integer getDparentid() {
        return dparentid;
    }

    public void setDparentid(Integer dparentid) {
        this.dparentid = dparentid;
    }

    public Integer getDindex() {
        return dindex;
    }

    public void setDindex(Integer dindex) {
        this.dindex = dindex;
    }

    public Integer getDstate() {
        return dstate;
    }

    public void setDstate(Integer dstate) {
        this.dstate = dstate;
    }
}