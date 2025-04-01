package customer.test_xb.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.beans.Encoder;
import java.io.*;
import java.net.URLDecoder;

import okhttp3.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.ArrayList;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.checkerframework.checker.units.qual.s;
import org.springframework.web.bind.annotation.RequestBody;

import com.alibaba.fastjson.JSONObject;

import customer.test_xb.Service.group;

public class MatchingUserRoles {

        public static void main(String[] args) throws IOException {
                // 添加所有用户使用

                java.util.List<user> userlList = getuser_data();
                java.util.List<user> finlList = getuser_data_fin();
                System.out.println("1.0 user count :" + userlList.size());
                System.out.println("1.0 fin user count :" + finlList.size());
                for (user i : userlList) {
                post_user_to_IAS(i.getUserid(), i.getUser_name(), i.getUser_email());

                i.setIasId(getiasuserid(i.getUser_email()));
                put_user_to_IAS(i.getIasId(), i.getUser_name(), i.getUser_email());
                System.out.println(i.getIasId());
                }
                for (user i : finlList) {
                post_user_to_IAS(i.getUserid(), i.getUser_name(), i.getUser_email());

                // // 修改姓名 对于原先的数据

                i.setIasId(getiasuserid(i.getUser_email()));
                put_user_to_IAS(i.getIasId(), i.getUser_name(), i.getUser_email());
                }

                // 添加对应group 使用
                // java.util.List<String> groups = adduser_group();
                // for (String i : groups) {
                // post_group_to_IAS(i);
                // }

                // 向每个gourp 里面添加1.0 已经有这些权限的用户
                // 获取用户数据

                // 获取群组数据
                List<group> AllgroupsList = getAllgroupias();
                for (group i : AllgroupsList) {
                        System.out.println(i.getGroup_id());
                        System.out.println(i.getGroup_name());
                }
                // 一般 user 角色
                List<group> generalUserRoles = AllgroupsList.stream()
                                .filter(e -> e.getGroup_name().equals("CCS_CreditLimit")
                                                || e.getGroup_name().equals("CCS_CreditRelease")
                                                || e.getGroup_name().equals("CCS_BizFlow")
                                                || e.getGroup_name().equals("CCS_CreditNote"))
                                .collect(Collectors.toList());

                // FIN PIC(F0) 角色
                List<group> f0groupsList = AllgroupsList.stream()
                                .filter(e -> e.getGroup_name().equals("CCS_Supervisor")
                                                || e.getGroup_name().equals("CCS_CustomerMaster")
                                                || e.getGroup_name().equals("CCS_CreditLimit")
                                                || e.getGroup_name().equals("CCS_FINData")
                                                || e.getGroup_name().equals("CCS_CreditEvaluation")
                                                || e.getGroup_name().equals("CCS_CreditRelease")
                                                || e.getGroup_name().equals("CCS_BizFlow")
                                                || e.getGroup_name().equals("CCS_CreditNote")
                                                || e.getGroup_name().equals("CCS_Report"))
                                .collect(Collectors.toList());

                // FIN PIC 主管(F1) 角色
                List<group> f1groupsList = AllgroupsList.stream()
                                .filter(e -> e.getGroup_name().equals("CCS_Supervisor")
                                                || e.getGroup_name().equals("CCS_CustomerMaster")
                                                || e.getGroup_name().equals("CCS_CreditLimit")
                                                || e.getGroup_name().equals("CCS_FINData")
                                                || e.getGroup_name().equals("CCS_CreditEvaluation")
                                                || e.getGroup_name().equals("CCS_CreditRelease")
                                                || e.getGroup_name().equals("CCS_BizFlow")
                                                || e.getGroup_name().equals("CCS_CreditNote")
                                                || e.getGroup_name().equals("CCS_Report"))
                                .collect(Collectors.toList());

                // Controller(F2) 角色
                List<group> f2groupsList = AllgroupsList.stream()
                                .filter(e -> e.getGroup_name().equals("CCS_Supervisor")
                                                || e.getGroup_name().equals("CCS_CustomerMaster")
                                                || e.getGroup_name().equals("CCS_CreditLimit")
                                                || e.getGroup_name().equals("CCS_FINData")
                                                || e.getGroup_name().equals("CCS_CreditEvaluation")
                                                || e.getGroup_name().equals("CCS_CreditRelease")
                                                || e.getGroup_name().equals("CCS_BizFlow")
                                                || e.getGroup_name().equals("CCS_CreditNote")
                                                || e.getGroup_name().equals("CCS_Report"))
                                .collect(Collectors.toList());

                // CFO(F3) 角色
                List<group> f3groupsList = AllgroupsList.stream()
                                .filter(e -> e.getGroup_name().equals("CCS_Supervisor")
                                                || e.getGroup_name().equals("CCS_CustomerMaster")
                                                || e.getGroup_name().equals("CCS_CreditLimit")
                                                || e.getGroup_name().equals("CCS_FINData")
                                                || e.getGroup_name().equals("CCS_CreditEvaluation")
                                                || e.getGroup_name().equals("CCS_CreditRelease")
                                                || e.getGroup_name().equals("CCS_BizFlow")
                                                || e.getGroup_name().equals("CCS_CreditNote")
                                                || e.getGroup_name().equals("CCS_Report"))
                                .collect(Collectors.toList());

                // FIN-Top Manager (F4) 角色
                List<group> f4groupsList = AllgroupsList.stream()
                                .filter(e -> e.getGroup_name().equals("CCS_Supervisor")
                                                || e.getGroup_name().equals("CCS_CustomerMaster")
                                                || e.getGroup_name().equals("CCS_CreditLimit")
                                                || e.getGroup_name().equals("CCS_FINData")
                                                || e.getGroup_name().equals("CCS_CreditEvaluation")
                                                || e.getGroup_name().equals("CCS_CreditRelease")
                                                || e.getGroup_name().equals("CCS_BizFlow")
                                                || e.getGroup_name().equals("CCS_CreditNote")
                                                || e.getGroup_name().equals("CCS_Report"))
                                .collect(Collectors.toList());

                // 调用 ccsAssign 方法

                System.out.println("AllgroupsList size :" + AllgroupsList.size());
                // // setgrouplabel(AllgroupsList);
                // System.out.println(generalUserRoles.size());
                // System.out.println(f0groupsList.size());
                // System.out.println(f1groupsList.size());
                // System.out.println(f2groupsList.size());
                // System.out.println(f3groupsList.size());
                // System.out.println(f4groupsList.size());
                // // 循环patch 外层是group 内层是user 每完成一个group 发送一次请求
                // filteruser(AllgroupsList, userlList);
                // ccsAssign(generalUserRoles, userlList);
                ccsAssign(f0groupsList,
                                finlList.stream().filter(e -> e.getUser_level().equals("F0"))
                                                .collect(Collectors.toList()));
                ccsAssign(f1groupsList,
                                finlList.stream().filter(e -> e.getUser_level().equals("F1"))
                                                .collect(Collectors.toList()));
                ccsAssign(f2groupsList,
                                finlList.stream().filter(e -> e.getUser_level().equals("F2"))
                                                .collect(Collectors.toList()));
                ccsAssign(f3groupsList,
                                finlList.stream().filter(e -> e.getUser_level().equals("F3"))
                                                .collect(Collectors.toList()));
                ccsAssign(f4groupsList,
                                finlList.stream().filter(e -> e.getUser_level().equals("F4"))
                                                .collect(Collectors.toList()));

        }

        private static void ccsAssign(List<group> allgroupsList, List<user> userlList) throws IOException {
                for (group group1 : allgroupsList) {

                        for (user user1 : userlList) {
                                if (!(user1.getIasId() == null)) {
                                        patchgroup(group1.getGroup_id(), user1.getIasId());

                                } else {

                                }
                        }

                }
        }

        private static List<user> getuser_data_fin() throws FileNotFoundException, IOException {
                System.out.println("Hello, Java, BXK test for vcf 2.0 user！");
                {
                        java.util.List<user> userlList = new ArrayList<user>();
                        // 1. 创建 Workbook 对象
                        Workbook workbook = new XSSFWorkbook(new FileInputStream(new File(
                                        "C:\\Users\\Z22080010\\Desktop\\ccsuser.xlsx")));

                        // 2. 创建 Sheet 对象
                        Sheet sheet = workbook.getSheetAt(2);
                        // 3. 遍历每一行，获取单元格数据

                        // private String userid;
                        // private String user_name;
                        // private String user_email;
                        // private String user_power;
                        for (int i = 0; i < sheet.getPhysicalNumberOfRows(); i++) {
                                // 循环读取每一个格
                                Row row = sheet.getRow(i);

                                user userdata = new user();

                                Cell cell2 = row.getCell(3);
                                cell2.setCellType(Cell.CELL_TYPE_STRING);
                                String user_name = cell2.getStringCellValue();
                                userdata.setUser_name(user_name);

                                Cell cell1 = row.getCell(0);
                                cell1.setCellType(Cell.CELL_TYPE_STRING);
                                String userid = cell1.getStringCellValue();
                                userdata.setUserid(userid);

                                Cell cell3 = row.getCell(1);
                                cell3.setCellType(Cell.CELL_TYPE_STRING);
                                String user_email = cell3.getStringCellValue();
                                userdata.setUser_email(user_email);

                                Cell cell4 = row.getCell(2);
                                cell4.setCellType(Cell.CELL_TYPE_STRING);
                                String user_power = cell4.getStringCellValue();
                                userdata.setUser_level(user_power);
                                userlList.add(userdata);
                        }
                        // System.out.println(userlList);
                        return userlList;
                }

        }

        public static void post_user_to_IAS(String user_id, String user_name, String user_email) throws IOException {
                OkHttpClient client = new OkHttpClient().newBuilder()
                                .build();
                // String names = userName.split("/");
                okhttp3.MediaType mediaType = MediaType.parse("application/scim+json");
                String[] nameParts = user_name.split(" ");
                String givenName = "";
                String familyName = "";
                if (nameParts.length > 1) {
                        familyName = nameParts[nameParts.length - 1];
                        givenName = String.join(" ", Arrays.copyOf(nameParts, nameParts.length - 1));
                } else {
                        givenName = user_name;
                }

                okhttp3.RequestBody body = okhttp3.RequestBody.create(mediaType,
                                "{\r\n  \"name\":{\r\n" + //
                                                "        \"familyName\":\"" + familyName + "\",\r\n" + //
                                                "        \"givenName\":\"" + givenName + "\"\r\n" + //
                                                "    },  \"userName\":\"" + user_name
                                                + "\",\r\n    \"displayName\":\"" + user_id
                                                + "\",\r\n    \"emails\": [\r\n        {\r\n            \"primary\": true,\r\n            \"value\": \""
                                                + user_email
                                                + "\"\r\n        }\r\n    ],\r\n    \"schemas\": [\r\n        \"urn:ietf:params:scim:schemas:core:2.0:User\",\r\n        \"urn:ietf:params:scim:schemas:extension:sap:2.0:User\"\r\n    ],\r\n    \"active\": true,\r\n     \"urn:ietf:params:scim:schemas:extension:sap:2.0:User\": {\r\n        \"emails\": [\r\n            {\r\n                \"value\": \""
                                                + user_email
                                                + "\",\r\n                \"primary\": true\r\n            }\r\n        ],\r\n        \"mailVerified\": true,\r\n        \"status\": \"active\"\r\n    }\r\n}");
                // System.out.println("{\r\n \"userName\":\"" + user_name
                // + "\",\r\n \"displayName\":\"" + user_id
                // + "\",\r\n \"emails\": [\r\n {\r\n \"primary\": true,\r\n \"value\": \""
                // + user_email
                // + "\"\r\n }\r\n ],\r\n \"schemas\": [\r\n
                // \"urn:ietf:params:scim:schemas:core:2.0:User\",\r\n
                // \"urn:ietf:params:scim:schemas:extension:sap:2.0:User\"\r\n ],\r\n
                // \"active\": true,\r\n
                // \"urn:ietf:params:scim:schemas:extension:sap:2.0:User\": {\r\n \"emails\":
                // [\r\n {\r\n \"value\": \""
                // + user_email
                // + "\",\r\n \"primary\": true\r\n }\r\n ],\r\n \"mailVerified\": true,\r\n
                // \"status\": \"active\"\r\n }\r\n}");

                Request request = new Request.Builder()
                                .url("https://ae9ldgye1.accounts.ondemand.com/scim/Users")
                                // qas
                                // .url("https://a9nw1ogcx.accounts.ondemand.com/scim/Users")
                                .method("POST", body)
                                .addHeader("Content-Type", "application/scim+json")
                                // dev qas Basic
                                // N2U1MzM4YmItMWQzYi00MmZmLWE2MzQtZWExNTcwNDY3MjRkOmRINjNrcUlEWDlSakNSWXlsNm5xai9wcTJCSGVLRWNI
                                .addHeader("Authorization",
                                                "Basic N2U1MzM4YmItMWQzYi00MmZmLWE2MzQtZWExNTcwNDY3MjRkOmRINjNrcUlEWDlSakNSWXlsNm5xai9wcTJCSGVLRWNI")
                                // Basic
                                // ZWM3ZDY2MTUtMWU1Zi00MTI3LWJmZWMtNWFhMTZjMDY2YmY5OjhRbHg/RXRBc11TQm8/U3ZLXTVlMj9maUYuLkhILmJ3
                                // prd
                                // .addHeader("Authorization",
                                // "Basic
                                // ZWM3ZDY2MTUtMWU1Zi00MTI3LWJmZWMtNWFhMTZjMDY2YmY5OjhRbHg/RXRBc11TQm8/U3ZLXTVlMj9maUYuLkhILmJ3")
                                .build();
                okhttp3.Response response = client.newCall(request).execute();
                // System.out.println(response.body().string());
                // String path = "?$filter=(email_address_a eq \'xiaoka\'";
                // String a = URLDecoder.decode(path, "utf-8");
        }

        public static void put_user_to_IAS(String user_id, String user_name, String user_email) throws IOException {
                OkHttpClient client = new OkHttpClient().newBuilder()
                                .build();
                // String names = userName.split("/");
                okhttp3.MediaType mediaType = MediaType.parse("application/scim+json");
                String[] nameParts = user_name.split(" ");
                String givenName = "";
                String familyName = "";
                if (nameParts.length > 1) {
                        familyName = nameParts[nameParts.length - 1];
                        givenName = String.join(" ", Arrays.copyOf(nameParts, nameParts.length - 1));
                } else {
                        givenName = user_name;
                }

                okhttp3.RequestBody body = okhttp3.RequestBody.create(mediaType,
                                "{\r\n  \"name\":{\r\n" + //
                                                "        \"familyName\":\"" + familyName + "\",\r\n" + //
                                                "        \"givenName\":\"" + givenName + "\"\r\n" + //
                                                "    },  \"userName\":\"" + ""
                                                + "\",\r\n    \"displayName\":\"" + user_name
                                                + "\",\r\n    \"emails\": [\r\n        {\r\n            \"primary\": true,\r\n            \"value\": \""
                                                + user_email
                                                + "\"\r\n        }\r\n    ],\r\n    \"schemas\": [\r\n        \"urn:ietf:params:scim:schemas:core:2.0:User\",\r\n        \"urn:ietf:params:scim:schemas:extension:sap:2.0:User\"\r\n    ],\r\n    \"active\": true,\r\n     \"urn:ietf:params:scim:schemas:extension:sap:2.0:User\": {\r\n        \"emails\": [\r\n            {\r\n                \"value\": \""
                                                + user_email
                                                + "\",\r\n                \"primary\": true\r\n            }\r\n        ],\r\n        \"mailVerified\": true,\r\n        \"status\": \"active\"\r\n    }\r\n}");
                // System.out.println("{\r\n \"name\":{\r\n" + //
                // " \"familyName\":\"" + familyName + "\",\r\n" + //
                // " \"givenName\":\"" + givenName + "\"\r\n" + //
                // " }, \"userName\":\"" + ""
                // + "\",\r\n \"displayName\":\"" + user_name
                // + "\",\r\n \"emails\": [\r\n {\r\n \"primary\": true,\r\n \"value\": \""
                // + user_email
                // + "\"\r\n }\r\n ],\r\n \"schemas\": [\r\n
                // \"urn:ietf:params:scim:schemas:core:2.0:User\",\r\n
                // \"urn:ietf:params:scim:schemas:extension:sap:2.0:User\"\r\n ],\r\n
                // \"active\": true,\r\n
                // \"urn:ietf:params:scim:schemas:extension:sap:2.0:User\": {\r\n \"emails\":
                // [\r\n {\r\n \"value\": \""
                // + user_email
                // + "\",\r\n \"primary\": true\r\n }\r\n ],\r\n \"mailVerified\": true,\r\n
                // \"status\": \"active\"\r\n }\r\n}");

                // System.out.println(user_id);
                Request request = new Request.Builder()
                                .url("https://ae9ldgye1.accounts.ondemand.com/scim/Users/" + user_id)
                                // qas
                                // .url("https://a9nw1ogcx.accounts.ondemand.com/scim/Users")
                                .method("PUT", body)
                                .addHeader("Content-Type", "application/scim+json")
                                // dev qas Basic
                                // N2U1MzM4YmItMWQzYi00MmZmLWE2MzQtZWExNTcwNDY3MjRkOmRINjNrcUlEWDlSakNSWXlsNm5xai9wcTJCSGVLRWNI
                                .addHeader("Authorization",
                                                "Basic N2U1MzM4YmItMWQzYi00MmZmLWE2MzQtZWExNTcwNDY3MjRkOmRINjNrcUlEWDlSakNSWXlsNm5xai9wcTJCSGVLRWNI")
                                // Basic
                                // ZWM3ZDY2MTUtMWU1Zi00MTI3LWJmZWMtNWFhMTZjMDY2YmY5OjhRbHg/RXRBc11TQm8/U3ZLXTVlMj9maUYuLkhILmJ3
                                // prd
                                // .addHeader("Authorization",
                                // "Basic
                                // ZWM3ZDY2MTUtMWU1Zi00MTI3LWJmZWMtNWFhMTZjMDY2YmY5OjhRbHg/RXRBc11TQm8/U3ZLXTVlMj9maUYuLkhILmJ3")
                                .build();
                okhttp3.Response response = client.newCall(request).execute();
                System.out.println(response.body().string());
                // String path = "?$filter=(email_address_a eq \'xiaoka\'";
                // String a = URLDecoder.decode(path, "utf-8");
        }

        public static void post_group_to_IAS(String group_id) throws IOException {
                OkHttpClient client = new OkHttpClient().newBuilder()
                                .build();
                okhttp3.MediaType mediaType = MediaType.parse("application/scim+json");
                okhttp3.RequestBody body = okhttp3.RequestBody.create(mediaType,
                                "{\r\n  \"displayName\": \"Administrators2\",\r\n  \"members\": [\r\n    {\r\n      \"type\": \"User\",\r\n      \"value\": \""
                                                + group_id
                                                + "\"\r\n    }\r\n    \r\n    \r\n  ]\r\n  ,\r\n    \"schemas\": [\r\n    \"urn:ietf:params:scim:schemas:core:2.0:Group\"\r\n  ]\r\n}");
                Request request = new Request.Builder()
                                .url("https://a9nw1ogcx.accounts.ondemand.com/scim/Groups")
                                .method("POST", body)
                                .addHeader("Content-Type", "application/scim+json")
                                .addHeader("Authorization",
                                                "Basic ZWM3ZDY2MTUtMWU1Zi00MTI3LWJmZWMtNWFhMTZjMDY2YmY5OjhRbHg/RXRBc11TQm8/U3ZLXTVlMj9maUYuLkhILmJ3")
                                .addHeader("Cookie", "JSESSIONID=587C9F0538389D9DD00E409EBB2A0518")
                                .build();
                okhttp3.Response response = client.newCall(request).execute();
                // System.out.println(response.body().string());
        }

        public static void filteruser(List<group> AllgroupsList, List<user> userlList) throws IOException {

                for (group group1 : AllgroupsList) {
                        if (group1.getGroup_label() == null) {
                        } else {
                                for (user user1 : userlList) {
                                        if (!(user1.getUserid() == null)) {
                                                if (user1.getUser_power().contains(group1.getGroup_label())) {
                                                        patchgroup(group1.getGroup_id(), user1.getIasId());
                                                }
                                        } else {

                                        }
                                }

                        }

                }
        }

        public static void patchgroup(String groupid, String grouptouserid) throws IOException {

                String bodyString = "\r\n\r\n{\r\n  \"schemas\": [\r\n    \"urn:ietf:params:scim:api:messages:2.0:PatchOp\"\r\n  ],\r\n  \"Operations\": [\r\n    {\r\n      \"op\": \"add\",\r\n      \"path\": \"members\",\r\n      \"value\": [\r\n        {\r\n          \"value\":\""
                                + grouptouserid
                                + "\"\r\n          \r\n          \r\n        }\r\n      ]\r\n    }\r\n  ]\r\n}";
                OkHttpClient client = new OkHttpClient().newBuilder()
                                .build();
                // System.out.println("bodyString");
                // System.out.println(bodyString);
                MediaType mediaType = MediaType.parse("application/scim+json");
                okhttp3.RequestBody body = okhttp3.RequestBody.create(mediaType, bodyString);
                okhttp3.Request request = new Request.Builder()
                                // .url("https://a9nw1ogcx.accounts.ondemand.com/scim/Groups/" + groupid)
                                .url("https://ae9ldgye1.accounts.ondemand.com/scim/Groups/" + groupid)
                                .method("PATCH", body)
                                .addHeader("Authorization",
                                                "Basic N2U1MzM4YmItMWQzYi00MmZmLWE2MzQtZWExNTcwNDY3MjRkOmRINjNrcUlEWDlSakNSWXlsNm5xai9wcTJCSGVLRWNI")

                                // .addHeader("Authorization",
                                // "Basic
                                // ZWM3ZDY2MTUtMWU1Zi00MTI3LWJmZWMtNWFhMTZjMDY2YmY5OjhRbHg/RXRBc11TQm8/U3ZLXTVlMj9maUYuLkhILmJ3")
                                // .addHeader("Cookie", "JSESSIONID=88D492E6329736A8B033D2C332F7891B")
                                .build();
                okhttp3.Response response = client.newCall(request).execute();
                // System.out.println(response.body().string());
                // response.body().string();
                String entity = response.body().string();
                JSONObject iasgroups = new JSONObject();
                JSONObject jsResult = JSONObject.parseObject(entity); // Convert String to
                // JSON Object
                // System.out.println(jsResult);
                if (response.isSuccessful()) {
                        System.out.println("添加成功");
                } else
                        System.out.println("添加失败");
        }

        // 通过表格获取user数据
        public static java.util.List<user> getuser_data() throws FileNotFoundException, IOException {
                System.out.println("Hello, Java, BXK test for vcf 2.0 user！");
                {
                        java.util.List<user> userlList = new ArrayList<user>();
                        // 1. 创建 Workbook 对象
                        Workbook workbook = new XSSFWorkbook(new FileInputStream(new File(
                                        "C:\\Users\\Z22080010\\Desktop\\ccsuser.xlsx")));

                        // 2. 创建 Sheet 对象
                        Sheet sheet = workbook.getSheetAt(0);
                        // 3. 遍历每一行，获取单元格数据

                        // private String userid;
                        // private String user_name;
                        // private String user_email;
                        // private String user_power;
                        for (int i = 0; i < sheet.getPhysicalNumberOfRows(); i++) {
                                // 循环读取每一个格
                                Row row = sheet.getRow(i);

                                user userdata = new user();

                                Cell cell2 = row.getCell(2);
                                cell2.setCellType(Cell.CELL_TYPE_STRING);
                                String user_name = cell2.getStringCellValue();
                                userdata.setUser_name(user_name);

                                Cell cell1 = row.getCell(0);
                                cell1.setCellType(Cell.CELL_TYPE_STRING);
                                String userid = cell1.getStringCellValue();
                                userdata.setUserid(userid);

                                Cell cell3 = row.getCell(5);
                                cell3.setCellType(Cell.CELL_TYPE_STRING);
                                String user_email = cell3.getStringCellValue();
                                userdata.setUser_email(user_email);

                                // Cell cell4 = row.getCell(6);
                                // cell4.setCellType(Cell.CELL_TYPE_STRING);
                                // String user_power = cell4.getStringCellValue();
                                // userdata.setUser_power(user_power);
                                userlList.add(userdata);
                        }
                        // System.out.println(userlList);
                        return userlList;
                }

        }

        public static String getiasuserid(String email) throws IOException {

                OkHttpClient client = new OkHttpClient().newBuilder()
                                .build();
                MediaType mediaType = MediaType.parse("text/plain");
                okhttp3.RequestBody body = okhttp3.RequestBody.create(mediaType, "");
                okhttp3.Request request = new Request.Builder()
                                .url("https://ae9ldgye1.accounts.ondemand.com/scim/Users?filter=emails.value+eq+\""
                                                + email + "\"")
                                // .url("https://a9nw1ogcx.accounts.ondemand.com/scim/Users?filter=emails.value+eq+\""
                                // + email + "\"")
                                .method("GET", null)
                                .addHeader("Authorization",
                                                "Basic N2U1MzM4YmItMWQzYi00MmZmLWE2MzQtZWExNTcwNDY3MjRkOmRINjNrcUlEWDlSakNSWXlsNm5xai9wcTJCSGVLRWNI")

                                // .addHeader("Authorization",
                                // "Basic
                                // ZWM3ZDY2MTUtMWU1Zi00MTI3LWJmZWMtNWFhMTZjMDY2YmY5OjhRbHg/RXRBc11TQm8/U3ZLXTVlMj9maUYuLkhILmJ3")
                                // .addHeader("Cookie", "JSESSIONID=88D492E6329736A8B033D2C332F7891B")
                                .build();
                okhttp3.Response response = client.newCall(request).execute();
                // System.out.println(response.body().string());
                // response.body().string();
                String entity = response.body().string();
                JSONObject iasgroups = new JSONObject();
                JSONObject jsResult = JSONObject.parseObject(entity); // Convert String to
                // JSON Object

                com.alibaba.fastjson.JSONArray entityList = jsResult.getJSONArray("Resources");
                if (!(entityList == null)) {

                        int i = 0;

                        JSONObject groupObject = (JSONObject) entityList.get(i);
                        String userid = groupObject.getString("id");
                        System.out.println(userid);
                        return userid;
                } else
                        return null;

        }

        public static List<group> getAllgroupias() throws IOException {

                List<group> AllgroupsList = new ArrayList<group>();
                OkHttpClient client = new OkHttpClient().newBuilder()
                                .build();
                MediaType mediaType = MediaType.parse("text/plain");
                okhttp3.RequestBody body = okhttp3.RequestBody.create(mediaType, "a");
                okhttp3.Request request = new Request.Builder()
                                .url("https://ae9ldgye1.accounts.ondemand.com/scim/Groups")
                                .method("GET", null)
                                .addHeader("Authorization",
                                                "Basic N2U1MzM4YmItMWQzYi00MmZmLWE2MzQtZWExNTcwNDY3MjRkOmRINjNrcUlEWDlSakNSWXlsNm5xai9wcTJCSGVLRWNI")

                                // .addHeader("Authorization",
                                // "Basic
                                // ZWM3ZDY2MTUtMWU1Zi00MTI3LWJmZWMtNWFhMTZjMDY2YmY5OjhRbHg/RXRBc11TQm8/U3ZLXTVlMj9maUYuLkhILmJ3")
                                // .addHeader("Cookie", "JSESSIONID=88D492E6329736A8B033D2C332F7891B")
                                .build();
                okhttp3.Response response = client.newCall(request).execute();
                // System.out.println(response.body().string());
                // response.body().string();
                String entity = response.body().string();
                JSONObject iasgroups = new JSONObject();
                JSONObject jsResult = JSONObject.parseObject(entity); // Convert String to
                // JSON Object
                com.alibaba.fastjson.JSONArray entityList = jsResult.getJSONArray("Resources");
                int i = 0;
                for (i = 0; i < entityList.size(); i++) {
                        JSONObject groupObject = (JSONObject) entityList.get(i);
                        group a = new group();
                        a.setGroup_id(groupObject.getString("id"));
                        a.setGroup_name(groupObject.getString("displayName"));
                        if (a.getGroup_name().startsWith("CCS")) {
                                AllgroupsList.add(a);
                        }
                }
                System.out.println(AllgroupsList.size());
                return AllgroupsList;

        }

        // 通过本地的table 来抓取各个group对应的label

        public static java.util.List<String> setgrouplabel(List<group> AllgroupsList) {
                try {
                        java.util.List<String> userlList = new ArrayList<String>();
                        // 1. 创建 Workbook 对象
                        Workbook workbook = new XSSFWorkbook(new FileInputStream(new File(
                                        "C:\\Users\\Z22080010\\Desktop\\sapbtp \u5F00\u53D1\\BTP file\\\u6743\u9650\u95EE\u9898\\userdataconversion\\1.0to2.0app.xlsx")));

                        // 2. 创建 Sheet 对象
                        // index 表示是第n+1 个表
                        Sheet sheet = workbook.getSheetAt(0);
                        // 3. 遍历每一行，获取单元格数据
                        int x = 0;
                        for (int i = 1; i < sheet.getPhysicalNumberOfRows(); i++) {
                                // 循环读取每一个格
                                Row row = sheet.getRow(i);
                                String id = "";

                                Cell cell = row.getCell(2);
                                // 处理单元格中空单元格导致的空指针异常
                                if (cell == null) {
                                        cell = row.createCell(2);
                                }
                                id = cell.getStringCellValue();

                                for (int index2 = 0; index2 < AllgroupsList.size(); index2++) {
                                        // System.out.println(AllgroupsList.get(index2).group_name);
                                        // System.out.println(id);

                                        if (id.contentEquals((AllgroupsList.get(index2).getGroup_name()))) {
                                                Row row1 = sheet.getRow(i);
                                                Cell cell1 = row.getCell(1);
                                                String Group_label = cell1.getStringCellValue();
                                                AllgroupsList.get(index2).setGroup_label(Group_label);
                                                // System.out.println("name 相同");
                                                // System.out.println(id);
                                                // System.out.println(Group_label);
                                                x++;
                                        }

                                }

                        }
                        for (int i = 0; i < AllgroupsList.size(); i++) {
                                System.out.println(AllgroupsList.get(i).getGroup_id());
                                System.out.println(AllgroupsList.get(i).getGroup_name());
                                System.out.println(AllgroupsList.get(i).getGroup_label());

                        }
                        // System.out.println(userlList);
                        System.out.println("有" + x + "个group 实现对应");
                        return userlList;
                } catch (

                Exception e) {
                        e.printStackTrace();
                }
                return null;
        }
}
