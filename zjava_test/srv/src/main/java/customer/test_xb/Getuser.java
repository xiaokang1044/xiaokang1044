package customer.test_xb;

import java.util.List;
import java.util.stream.Collectors;

import customer.test_xb.Service.dept;
import customer.test_xb.Service.group;
import customer.test_xb.Service.user;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import java.beans.Encoder;
import java.io.*;
import java.net.URLDecoder;

import okhttp3.*;

import org.apache.http.util.EntityUtils;
import org.apache.poi.ss.formula.functions.T;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.bind.annotation.RequestBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class Getuser {
    // prd
    public static String connectionString = "jdbc:sap://e3c7fcc5-c71b-466b-af55-95abb0570b0a.hana.prod-jp20.hanacloud.ondemand.com:443/";
    public static String user1 = "HANAUSER";
    public static String password = "HanaUser01";

    public static void main(String[] args) throws IOException {
        // get hr table data
        String dbString = "SELECT * FROM\"A6C9BEE90FF8405DBF4BC5B823472CCA\".\"ZAPI_EMPLOYEE\" ";
        List<user> dbusers = getResult(dbString);
        String dbString2 = "SELECT * FROM\"A6C9BEE90FF8405DBF4BC5B823472CCA\".\"ZAPI_DEPARTMENT\" ";
        List<dept> depts = getResult2(dbString2);

        // List<user> dbusers = null;
        // get ias data
        List<user> iasusers = getiasuserdata();
        List<user> retuser = new ArrayList<user>();

        for (user user1 : iasusers) {
            List<user> dbusers2 = dbusers.stream().filter(user -> user.getUser_email().equals(user1.getUser_email()))
                    .collect(Collectors.toList());
            if (dbusers2.size() == 0) {

            } else {
                dbusers2.get(0).setGroups(user1.getGroups());
                List<dept> depts2 = depts.stream().filter(a -> a.getDeptid().equals(dbusers2.get(0).getDept()))
                        .collect(Collectors.toList());
                if (depts2.size() != 0) {
                    dbusers2.get(0).setDeptname(depts2.get(0).getDeptname());

                }

                retuser.add(dbusers2.get(0));
            }

            // boolean flag = false;
            // for (user user2 : dbusers) {
            // flag = false;
            // if (user1.getUser_email().equals(user2.getUser_email())) {
            // // retuser.add(user2);
            // flag = true;
            // break;
            // }

            // }
            // if (flag == true) {
            // retuser.add(user1);
            // }
        }
        System.out.println(retuser.size());

        Workbook workbook = new XSSFWorkbook();

        // 设置表 设置名字
        Sheet sheet = workbook.createSheet();

        String path = "C:\\Users\\Z22080010\\Desktop\\test\\test2.xlsx";
        OutputStream out = null;

        workbook.setSheetName(0, "VCF DATA Consist Check"); // 之后改成相应的参数
        // 设置表头
        String[] headRowList = {
                "name", "Email", "Id", "Company", "Site", "Department Id", "Department Name",
                "Customer CompanyOrg", "Inquire All Customer Doc.", "Site Finances", "Ariba Status", "FIN(Modify)",
                "Inquire My Form", "User Setup", "Duty", "Customer Incoterm1", "Customer Form in Progress",
                "Vendor Code Form", "Customer Tax Code", "Vendor Tax Code", "Buyer Department", "Do SAP Block",
                "Vendor Messages", "Inquire All VendorDoc.", "Blocked Vendors", "Account Group Def.",
                "Customer Processing Error", "Country Key", "Modify Bank Reason", "Vendor Block", "Unblock",
                "Incoterm2", "Vendor Company Org", "DailyCheckService_destination", "Customer Block",
                "Modify Vendor", "Shipway", "Inquire Approved Doc.", "Batch Block Vendor",
                "Customer Country Key", "Payment Method", "Price", "Customer Code Form", "Division",
                "Customer Group", "FIN Default PIC", "Vendor Incoterm1", "Workflow", "Customer Payment Term",
                "Withholding Tax", "IAS", "Customer Modify", "Inquire Approved Customer Doc.", "Customer Messages",
                "Adjust Block Customer", "Customer Currency", "Sale District", "Site Admin", "Channel",
                "AcctGroup", "Vendor Processing Error", "Vendor Payment Term", "Buyer Employee Number", "Currency"
        };

        Row headRow = sheet.createRow(0);
        for (int i = 0; i < headRowList.length; i++) {
            Cell cell1 = headRow.createCell(i);
            cell1.setCellValue(headRowList[i]);
        }

        for (int j = 0; j < retuser.size(); j++) {
            int rowIndex = j + 1;
            String groups = "";
            Row dataRow = sheet.createRow(rowIndex);
            Cell cell0 = dataRow.createCell(0);
            cell0.setCellValue(retuser.get(j).getUser_name());
            Cell cell1 = dataRow.createCell(1);
            cell1.setCellValue(retuser.get(j).getUser_email());
            Cell cell2 = dataRow.createCell(2);
            cell2.setCellValue(retuser.get(j).getUserid());
            Cell cell3 = dataRow.createCell(3);
            cell3.setCellValue(retuser.get(j).getCompany());
            Cell cell4 = dataRow.createCell(4);
            cell4.setCellValue(retuser.get(j).getSite());
            Cell cell5 = dataRow.createCell(5);
            cell5.setCellValue(retuser.get(j).getDept());
            Cell cell6 = dataRow.createCell(6);
            cell6.setCellValue(retuser.get(j).getDeptname());
            // System.out.println(retuser.get(j).getGroups() != null);
            // if (retuser.get(j).getGroups() != null) {

            // for (int k = 0; k < retuser.get(j).getGroups().size(); k++) {
            // groups = groups + "/" + retuser.get(j).getGroups().get(k);
            // }
            // }
            // Cell cell5 = dataRow.createCell(5);
            // cell5.setCellValue(groups);
            if (retuser.get(j).getGroups() != null) {
                for (int k = 0; k < headRowList.length - 7; k++) {
                    Cell cell7 = dataRow.createCell(7 + k);
                    if (retuser.get(j).getGroups().contains(headRowList[k + 7])) {
                        cell7.setCellValue("V");
                    } else {
                        cell7.setCellValue("");
                    }
                }
            }

        }

        ByteArrayOutputStream fos = new ByteArrayOutputStream();
        // workbook.write(fos);
        out = new FileOutputStream(path);
        workbook.write(out);
        // return fos;
    }

    public static Connection setConect() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(connectionString, user1, password);
        } catch (SQLException e) {
            System.err.println("Connection Failed. User/Passwd Error? Message: " + e.getMessage());
            return connection;
        }
        return connection;
    }

    public static List<user> getResult(String sqlString) {
        Connection connection = setConect();
        ResultSet resultSet = null;
        long count = 0;
        List<user> users = new ArrayList<user>();

        if (connection != null) {
            try {
                System.out.println("Connection to HANA successful!");
                Statement stmt = connection.createStatement();
                resultSet = stmt
                        .executeQuery(sqlString);
                while (resultSet.next()) {
                    String matnr = resultSet.getString(1);
                    user selUser = new user();
                    // 工号
                    selUser.setUserid(resultSet.getString(8));
                    // 姓名
                    selUser.setUser_name(resultSet.getString(10).toLowerCase());
                    // 邮件
                    selUser.setDept(resultSet.getString(14));
                    selUser.setCompany(resultSet.getString(6).toLowerCase());
                    selUser.setSite(resultSet.getString(5).toLowerCase());
                    if (resultSet.getString(10) == null) {
                        String name = resultSet.getString(10);
                        if (name == null) {

                        } else {

                            String email = name.replace(' ', '_').toLowerCase() + "@wistron.com";

                            selUser.setUser_email(email);
                        }
                    } else {
                        selUser.setUser_email(resultSet.getString(17));

                    }

                    users.add(selUser);
                    count++;
                    // System.out.println(matnr);
                }
                System.err.println("数量" + count);
                return users;
            } catch (SQLException e) {
                System.err.println("Query failed!");
                System.err.println(e.toString());
            }
        }

        return users;
    }

    public static List<dept> getResult2(String sqlString) {
        Connection connection = setConect();
        ResultSet resultSet = null;
        long count = 0;
        List<dept> users = new ArrayList<dept>();

        if (connection != null) {
            try {
                System.out.println("Connection to HANA successful!");
                Statement stmt = connection.createStatement();
                resultSet = stmt
                        .executeQuery(sqlString);
                while (resultSet.next()) {
                    String matnr = resultSet.getString(1);
                    dept selUser = new dept();
                    // 工号
                    selUser.setDeptid(resultSet.getString(4));
                    // 姓名
                    selUser.setDeptname(resultSet.getString(5).toLowerCase());

                    users.add(selUser);
                    count++;
                    // System.out.println(matnr);
                }
                System.err.println("数量" + count);
                return users;
            } catch (SQLException e) {
                System.err.println("Query failed!");
                System.err.println(e.toString());
            }
        }

        return users;
    }

    public static List<user> getiasuserdata() throws IOException {
        List<user> users = new ArrayList<user>();
        int maxcount = 10;
        // 一次只能通过请求过去100 条
        for (int j = 0; j < 47; j++) {
            int count1 = j * 100;

            OkHttpClient client = new OkHttpClient().newBuilder()
                    .build();
            okhttp3.MediaType mediaType = MediaType.parse("application/scim+json");
            // String urls =
            // "https://a9nw1ogcx.accounts.ondemand.com/scim/Users?attributes=emails,name,dispalyName&startIndex="
            // + count1;
            String urls = "https://a9nw1ogcx.accounts.ondemand.com/scim/Users?startIndex="
                    // String urls =
                    // "https://ae9ldgye1.accounts.ondemand.com/scim/Users?startIndex="
                    + count1;
            Request request = new Request.Builder()
                    // qas

                    .url(urls)
                    .method("GET", null)
                    .addHeader("Content-Type", "application/scim+json")
                    .addHeader("attributes", "emails,name,dispalyName")
                    // DEV QAS
                    // .addHeader("Authorization",
                    // "Basic
                    // N2U1MzM4YmItMWQzYi00MmZmLWE2MzQtZWExNTcwNDY3MjRkOlJQazhHNlVkLTY/U09xSUB6PVFbQEJbWTJZczdBTQ==")
                    // PRD
                    // Basic
                    // ZWM3ZDY2MTUtMWU1Zi00MTI3LWJmZWMtNWFhMTZjMDY2YmY5OjhRbHg/RXRBc11TQm8/U3ZLXTVlMj9maUYuLkhILmJ3
                    // prd
                    .addHeader("Authorization",
                            "Basic ZWM3ZDY2MTUtMWU1Zi00MTI3LWJmZWMtNWFhMTZjMDY2YmY5OjhRbHg/RXRBc11TQm8/U3ZLXTVlMj9maUYuLkhILmJ3")
                    .build();
            okhttp3.Response response = client.newCall(request).execute();
            String responseBody = response.body().string(); // Convert response body to string
            // System.out.println(responseBody);
            JSONObject jsResult = JSONObject.parseObject(responseBody);
            maxcount = jsResult.getInteger("totalResults");
            maxcount = maxcount / 100;
            JSONArray jsResult1 = jsResult.getJSONArray("Resources");

            for (int i = 0; i < jsResult1.size(); i++) {
                user selUser = new user();
                JSONObject user1 = jsResult1.getJSONObject(i);
                selUser.setUserid(user1.getString("id"));
                // selUser.setUser_name(user1.getString("name"));

                JSONArray email1 = user1.getJSONArray("emails");
                // if (email1 == null) {
                // continue;
                // }
                JSONObject email2 = (JSONObject) email1.get(0);
                // selUser.setUser_email(email2.getString("value"));
                // selUser.setUserid(jsResult1.get(i).getString("id"));
                // selUser.setUser_name(jsResult1.getString("name"));
                selUser.setUser_email(email2.getString("value"));
                String email = email2.getString("value");
                String name1 = email.split("@")[0];
                String name = name1.replace('_', ' ');
                selUser.setUser_name(name);

                ArrayList<String> groups = new ArrayList<>();

                JSONArray groups1 = user1.getJSONArray("groups");

                if (groups1 != null) {

                    for (int m = 0; m < groups1.size(); m++) {
                        JSONObject groupsName1 = (JSONObject) groups1.get(m);
                        String groupsName = groupsName1.getString("display");
                        groups.add(groupsName);
                    }

                }
                selUser.setGroups(groups);
                // System.out.println(selUser.getGroups().size());
                users.add(selUser);
            }

        }
        return users;

    }

}
