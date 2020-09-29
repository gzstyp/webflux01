package com.fwtai.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class Student {
    @Id
    private Long id;

    private String code;
    private String name;
    private String gender;
    private String address;

    private String remark;
    private boolean active;

}