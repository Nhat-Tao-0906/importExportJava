package com.example.import_export_excel.aop;

import com.example.import_export_excel.model.ApiException;
import com.example.import_export_excel.model.MainResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
@ControllerAdvice
public class GlobalExceptionHandle extends ResponseEntityExceptionHandler {
    private static final Logger LOGGER = LogManager.getLogger(GlobalExceptionHandle.class);
    @ExceptionHandler(ApiException.class)
    @ResponseBody
    public ResponseEntity<MainResponse> handleCustomizedException(ApiException e) {
        LOGGER.info("Exception {}", e.getMessage());
        return new ResponseEntity<>(new MainResponse(e.getCode(), e.getMessage()), e.getHttpStatus());
    }
}
