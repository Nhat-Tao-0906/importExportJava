package com.example.import_export_excel.model.request;

import java.util.Date;

import lombok.Data;

@Data
public class UserRequest {
    private String name;

    private int age;

    private Date birth_day;

    private String phone;

    private String address;
}
