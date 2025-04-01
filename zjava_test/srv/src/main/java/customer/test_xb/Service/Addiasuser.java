package customer.test_xb.Service;

import java.util.ArrayList;
import java.beans.Encoder;
import java.io.*;
import java.net.URLDecoder;

import okhttp3.*;

import io.vavr.collection.List;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.bind.annotation.RequestBody;

public class Addiasuser {
    public static void main(String[] args) throws IOException {
        // 添加所有用户使用

        java.util.List<user> userlList = getuser_data();
        System.out.println("1.0 user count :" + userlList.size());
        // for (user i : userlList) {
        // post_user_to_IAS(i.getUserid(), i.getUser_name(), i.getUser_email());
        // }

        // // 添加对应group 使用
        // java.util.List<String> groups = adduser_group();
        // for (String i : groups) {
        // post_group_to_IAS(i);
        // }
    }
    // 获取删除的user的数据

    // 添加所有的group
    public static java.util.List<String> adduser_group() {
        try {
            java.util.List<String> userlList = new ArrayList<String>();
            // 1. 创建 Workbook 对象
            Workbook workbook = new XSSFWorkbook(new FileInputStream(new File(
                    "C:\\Users\\Z22080010\\Desktop\\sapbtp \u5F00\u53D1\\BTP file\\\u6743\u9650\u95EE\u9898\\userdata\\VCF_QAS_Employee_20230530.xlsx")));

            // 2. 创建 Sheet 对象
            Sheet sheet = workbook.getSheetAt(2);
            // 3. 遍历每一行，获取单元格数据

            for (int i = 0; i < sheet.getPhysicalNumberOfRows(); i++) {
                // 循环读取每一个格
                Row row = sheet.getRow(i);
                String id = "";
                for (int index = 0; index < row.getPhysicalNumberOfCells(); index++) {
                    Cell cell = row.getCell(index);
                    // 处理单元格中空单元格导致的空指针异常
                    if (cell == null) {
                        cell = row.createCell(index);
                    }
                    id = cell.getStringCellValue();
                    // System.out.println(id);

                }
                userlList.add(id);
            }
            System.out.println(userlList);
            return userlList;
        } catch (

        Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // 添加group 请求
    public static void post_group_to_IAS(String group_id) throws IOException {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        okhttp3.MediaType mediaType = MediaType.parse("application/scim+json");
        okhttp3.RequestBody body = okhttp3.RequestBody.create(mediaType,
                "{\r\n  \"displayName\": \"Administrators2\",\r\n  \"members\": [\r\n    {\r\n      \"type\": \"User\",\r\n      \"value\": \""
                        + group_id
                        + "\"\r\n    }\r\n    \r\n    \r\n  ]\r\n  ,\r\n    \"schemas\": [\r\n    \"urn:ietf:params:scim:schemas:core:2.0:Group\"\r\n  ]\r\n}");
        Request request = new Request.Builder()
                .url("https://ae9ldgye1.accounts.ondemand.com/scim/Groups")
                .method("POST", body)
                .addHeader("Content-Type", "application/scim+json")
                .addHeader("Authorization",
                        "Basic N2U1MzM4YmItMWQzYi00MmZmLWE2MzQtZWExNTcwNDY3MjRkOlJQazhHNlVkLTY/U09xSUB6PVFbQEJbWTJZczdBTQ==")
                .addHeader("Cookie", "JSESSIONID=587C9F0538389D9DD00E409EBB2A0518")
                .build();
        okhttp3.Response response = client.newCall(request).execute();
        System.out.println(response.body().string());
    }

    // 通过表格获取user数据
    public static java.util.List<user> getuser_data() {
        System.out.println("Hello, Java, BXK test for ccs 2.0 user！");
        try {
            java.util.List<user> userlList = new ArrayList<user>();
            // 1. 创建 Workbook 对象

            // 修改文件路径
            // qas
            Workbook workbook = new XSSFWorkbook(new FileInputStream(new File(
                    "C:\\Users\\Z22080010\\Desktop\\ccsuser.xlsx")));
            // prd
            // Workbook workbook = new XSSFWorkbook(new FileInputStream(new File(
            // "C:\\Users\\Z22080010\\Desktop\\\u6743\u9650\u95EE\u9898\\userdata\\VCF_QAS_Employee_20230530.xlsx")));
            // 2. 创建 Sheet 对象
            Sheet sheet = workbook.getSheetAt(0);
            // 3. 遍历每一行，获取单元格数据

            for (int i = 0; i < sheet.getPhysicalNumberOfRows(); i++) {
                // 循环读取每一个格
                Row row = sheet.getRow(i);
                String id = "";
                user userdata = new user();
                for (int index = 0; index < row.getPhysicalNumberOfCells(); index++) {
                    Cell cell = row.getCell(index);
                    // 处理单元格中空单元格导致的空指针异常
                    if (cell == null) {
                        cell = row.createCell(index);
                    }
                    switch (cell.getCellType()) {
                        case Cell.CELL_TYPE_STRING:
                            id = cell.getStringCellValue();
                            break;
                        case Cell.CELL_TYPE_NUMERIC:
                            id = String.valueOf(cell.getNumericCellValue());
                            break;
                        default:
                            id = "";
                    }
                    // System.out.println(id);
                    if (index == 0) {
                        userdata.setUserid(id);
                    } else if (index == 2) {
                        userdata.setUser_name(id);
                    } else if (index == 5) {
                        userdata.setUser_email(id);
                    }
                }
                userlList.add(userdata);
            }
            return userlList;
        } catch (

        Exception e) {
            e.printStackTrace();
        }
        return null;

    }

    public static void post_user_to_IAS(String user_id, String user_name, String user_email) throws IOException {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        okhttp3.MediaType mediaType = MediaType.parse("application/scim+json");
        okhttp3.RequestBody body = okhttp3.RequestBody.create(mediaType,
                "{\r\n    \"userName\":\"" + user_name
                        + "\",\r\n    \"displayName\":\"" + user_id
                        + "\",\r\n    \"emails\": [\r\n        {\r\n            \"primary\": true,\r\n            \"value\": \""
                        + user_email
                        + "\"\r\n        }\r\n    ],\r\n    \"schemas\": [\r\n        \"urn:ietf:params:scim:schemas:core:2.0:User\"\r\n    ],\r\n\r\n    \"active\": true,\r\n     \"urn:ietf:params:scim:schemas:extension:sap:2.0:User\": {\r\n        \"emails\": [\r\n            {\r\n                \"value\": \"xixin_zhang@wistron.com\",\r\n                \"primary\": true\r\n            }\r\n        ],\r\n        \"mailVerified\": true,\r\n        \"status\": \"active\"\r\n    }\r\n}");
        Request request = new Request.Builder()
                // qas
                .url("https://a9nw1ogcx.accounts.ondemand.com/scim/Users")
                .method("POST", body)
                .addHeader("Content-Type", "application/scim+json")
                // .addHeader("Authorization",
                // "Basic
                // N2U1MzM4YmItMWQzYi00MmZmLWE2MzQtZWExNTcwNDY3MjRkOlJQazhHNlVkLTY/U09xSUB6PVFbQEJbWTJZczdBTQ==")
                // Basic
                // ZWM3ZDY2MTUtMWU1Zi00MTI3LWJmZWMtNWFhMTZjMDY2YmY5OjhRbHg/RXRBc11TQm8/U3ZLXTVlMj9maUYuLkhILmJ3
                // prd
                .addHeader("Authorization",
                        "Basic ZWM3ZDY2MTUtMWU1Zi00MTI3LWJmZWMtNWFhMTZjMDY2YmY5OjhRbHg/RXRBc11TQm8/U3ZLXTVlMj9maUYuLkhILmJ3")
                .build();
        okhttp3.Response response = client.newCall(request).execute();
        System.out.println(response.body().string());
        // String path = "?$filter=(email_address_a eq \'xiaoka\'";
        // String a = URLDecoder.decode(path, "utf-8");
    }

    // 通过table 抓取所有user 的数据，然后通过patch 请求来修改group
}