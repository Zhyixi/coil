package com.walsin.coil.common.utils;

import tech.tablesaw.api.ColumnType;
import tech.tablesaw.api.Row;
import tech.tablesaw.api.Table;

import java.util.List;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class TableUtil {
    public static String ConvertToJson (Table table) {
        List<String> columnNames = table.columnNames();
        columnNames.stream().map((s) -> {
            return s;
        });

        Spliterator<Row> spliterator = Spliterators.spliteratorUnknownSize(table.iterator(), 0);
        Stream<Row> rowStream = StreamSupport.stream(spliterator, false);

        String rowsJsonArray = rowStream.map((row) -> {
            String jsonKeyValues = columnNames.stream().map((columnName) -> {
                String propertyVal = "";
                Object columnValue = row.getObject(columnName);
                ColumnType colType = table.column(columnName).type();
                if (colType == ColumnType.STRING || colType == ColumnType.LOCAL_DATE) {
                    propertyVal = "\"" + columnName + "\"" + ":" + "\"" + columnValue + "\"";
                } else {
                    propertyVal = "\"" + columnName + "\"" + ":" + columnValue;
                }
                return propertyVal;
            }).collect(Collectors.joining(","));
            return "{" + jsonKeyValues + "}";
        }).collect(Collectors.joining(","));

        String finalString = "[" + rowsJsonArray + "]";
//        System.out.println(finalString);

        return finalString;
    }

}
