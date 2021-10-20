package com.example.import_export_excel.model;

public enum ERROR {
    SUCCESS(1, "SUCCESS"),
    LOGIN_FAIL(10, "Tên đăng nhập hoặc mật khẩu không đúng"),
    SYSTEM_ERROR(99, "Hệ thống đang nâng cấp tính năng này , xin vui lòng thử lại sau"),
    INVALID_REQUEST(100, "invalid request"),
    INVALID_PARAM(101, "invalid param"),
    INVALID_TOKEN(102, "Phiên làm việc đã hết hạn")
    ;
    private int code;
    private String message;

    ERROR(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
