package com.example.import_export_excel.model.imports;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@Setter
@ToString
public class XLSXHeader {
    private final String fieldName;
    private final String xlsxColunmName;
    private final int colunmIndex;
}
