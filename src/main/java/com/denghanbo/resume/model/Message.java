package com.denghanbo.resume.model;

import lombok.Data;
import org.springframework.web.bind.annotation.RequestParam;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
public class Message {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    Integer id;
    String name;
    String email;
    String subject;
    String message;


}
