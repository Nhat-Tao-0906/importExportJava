package com.example.import_export_excel.model.export;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import com.example.import_export_excel.database.entity.User;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class UserExportExcel {
    private XSSFWorkbook workbook;
    private XSSFSheet sheet;
    private List<User> users;

    public UserExportExcel(List<User> users){
        this.users = users;
        workbook = new XSSFWorkbook();
        sheet = workbook.createSheet("Users");
    }

    private void writeHeaderRow(){
        Row row = sheet.createRow(0);
        Cell cell = row.createCell(0);
        cell.setCellValue("ID");

        cell = row.createCell(1);
        cell.setCellValue("Name");

        cell = row.createCell(1);
        cell.setCellValue("Age");

        cell = row.createCell(2);
        cell.setCellValue("Birth Day");

        cell = row.createCell(3);
        cell.setCellValue("Phone");

        cell = row.createCell(4);
        cell.setCellValue("Address");
    }

    private void writeDataRow() {
        int ROW = 1;

        for (User user : users) {
            Row row = sheet.createRow(ROW++);

            Cell cell = row.createCell(0);
            cell.setCellValue(user.getId());

            cell = row.createCell(1);
            cell.setCellValue(user.getName());

            cell = row.createCell(1);
            cell.setCellValue(user.getAge());

            cell = row.createCell(2);
            cell.setCellValue(convertDateToString(user.getBirth_day()));

            cell = row.createCell(3);
            cell.setCellValue(user.getPhone());

            cell = row.createCell(4);
            cell.setCellValue(user.getAddress());
        }
    }

    public void export(HttpServletResponse response) throws IOException{
        writeHeaderRow();
        writeDataRow();

        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);

        workbook.close();
        outputStream.close();
    }

    private String convertDateToString(Date date){
        String patter = "dd/MM/yyyy";

        DateFormat df = new SimpleDateFormat(patter);
        String dateAsString = df.format(date);
        return dateAsString;
    }
}
