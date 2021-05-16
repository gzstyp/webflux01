package com.fwtai.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class Student {
    @Id
    private Long id;//当主键且仅当为自增长时id为空时是添加,不为空时是编辑

    private String code;
    private String name;
    private String gender;
    private String address;
}