package com.example.import_export_excel.database.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.example.import_export_excel.model.imports.XLSXField;
import com.example.import_export_excel.model.request.UserRequest;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "user")
public class User extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @XLSXField(colunm = "Name")
    @Column(name = "name")
    private String name;

    @XLSXField(colunm = "Age")
    @Column(name = "age")
    private int age;

    @XLSXField(colunm = "Birth Day")
    @Column(name = "birth_day")
    private Date birth_day;

    @XLSXField(colunm = "Phone")
    @Column(name = "phone")
    private String phone;

    @XLSXField(colunm = "Address")
    @Column(name = "address")
    private String address;

    public User(UserRequest userRequest){
        this.name = userRequest.getName();
        this.age = userRequest.getAge();
        this.birth_day = userRequest.getBirth_day();
        this.phone = userRequest.getPhone();
        this.address = userRequest.getAddress();
    }
}
