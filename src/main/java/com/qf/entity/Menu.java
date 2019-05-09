package com.qf.entity;

import lombok.Data;

@Data
public class Menu {
    private Integer id;

    private String menuName;

    private String menuUrl;

    private String menuPercore;

    private Integer menuType;

    private Integer menuParentid;

    private String menuDesc;
    
    private String mparentname;
    
    private String subId;

}