package com.denghanbo.resume.eunm;

public enum PageType {

    BACK(1),
    PROJECT(2),
    SOCIRYACTIVE(3);



    private Integer type;

    PageType(Integer type) {
        this.type = type;
    }


    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
