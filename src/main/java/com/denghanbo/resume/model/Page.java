package com.denghanbo.resume.model;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Data
@Entity
public class Page {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    public Integer id;
    public String title;
    public String descript;
    public String content;

    public Integer type;

    //在主界面显示的缩略图
    public String imag;

}