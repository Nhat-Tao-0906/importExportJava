package com.example.import_export_excel.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.example.import_export_excel.database.entity.User;
import com.example.import_export_excel.model.ApiException;
import com.example.import_export_excel.model.MainResponse;
import com.example.import_export_excel.model.export.UserExportExcel;
import com.example.import_export_excel.model.request.UserRequest;
import com.example.import_export_excel.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/v1/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping
    public MainResponse<User> created(@RequestBody UserRequest userRequest) throws ApiException{
        return userService.createdUser(userRequest);
    }

    @PostMapping("/created-all")
    public MainResponse<List<User>> createdAll(@RequestPart("file") MultipartFile file) throws ApiException{
        return userService.createAll(file);
    }

    @GetMapping("/export-user")
    public MainResponse<String> exportUser(HttpServletResponse response) throws IOException{
        response.setContentType("application/octet-stream");
        String headerKey = "Content-Disposition";
        String headerValue = "attachement; filename=users.xlsx";
        response.setHeader(headerKey, headerValue);

        List<User> users = userService.getAllForExport();
        UserExportExcel userExcelExport = new UserExportExcel(users);
        userExcelExport.export(response);
        return new MainResponse<>();
    }
}
