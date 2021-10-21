package com.example.import_export_excel.services;

import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import com.example.import_export_excel.database.entity.User;
import com.example.import_export_excel.domain.UserDomain;
import com.example.import_export_excel.model.ApiException;
import com.example.import_export_excel.model.ERROR;
import com.example.import_export_excel.model.MainResponse;
import com.example.import_export_excel.model.imports.XLSXParser;
import com.example.import_export_excel.model.request.UserRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class UserService {
    @Autowired
    private UserDomain userDomain;

    public MainResponse<User> createdUser(UserRequest userRequest) throws ApiException{
        validateRequest(userRequest);
        User user = new User(userRequest);
        userDomain.createdUser(user);
        MainResponse<User> response = new MainResponse<>();
        response.setData(user);
        return response;
    }

    public MainResponse<List<User>> createAll(MultipartFile file) throws ApiException{
        System.out.println("---------------start: " + System.currentTimeMillis());
        XLSXParser<User> xlsxParser = new XLSXParser<>();
        List<User> users = new ArrayList<>();
        try {
            InputStream inputStream = file.getInputStream();
            users = xlsxParser.parse(inputStream, User.class);
            Iterable<User> iUsers = users;
            userDomain.createdAllUser(iUsers);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        MainResponse<List<User>> listUsersResponse = new MainResponse<>();
        listUsersResponse.setData(users);
        System.out.println("---------------end: " + System.currentTimeMillis());
        return listUsersResponse;
    }

    public List<User> getAllForExport() throws ApiException{
        List<User> users = StreamSupport.stream(userDomain.getAllUser().spliterator(), false).collect(Collectors.toList());
        return users;
    }

    private void validateRequest(UserRequest userRequest) throws ApiException{
        if (StringUtils.isBlank(userRequest.getName())) {
            throw new ApiException(ERROR.INVALID_PARAM, "Tên không được bỏ trống");
        }

        if (StringUtils.isBlank(userRequest.getPhone())){
            throw new ApiException(ERROR.INVALID_PARAM, "Số điện thoại không được bỏ trống");
        }

        if (userRequest.getAge() <= 0){
            throw new ApiException(ERROR.INVALID_PARAM, "Tuổi không nhỏ hơn 0");
        }

        if (StringUtils.isBlank(userRequest.getAddress())){
            throw new ApiException(ERROR.INVALID_PARAM, "Địa chỉ không được bỏ trống");
        }

        if (StringUtils.isBlank(convertDateToString(userRequest.getBirth_day()))){
            throw new ApiException(ERROR.INVALID_PARAM, "Ngày sinh không được bỏ trống");
        }
    }

    private String convertDateToString(Date date) throws ApiException{
        try {
            String pattern = "MM/dd/yyyy HH:mm:ss";
            DateFormat df = new SimpleDateFormat(pattern);

            String dateAsString = df.format(date);
            return dateAsString;
        }
        catch(Exception e){
            throw new ApiException(ERROR.INVALID_PARAM, "Ngày sinh ngày sinh không đúng định dạng");
        }
    }
}
