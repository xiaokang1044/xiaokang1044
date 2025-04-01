package customer.test_xb.Service;

import java.io.IOException;
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
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.bind.annotation.RequestBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class Deleteuser {
    // prd
    public static String prdconnectionString = "jdbc:sap://e3c7fcc5-c71b-466b-af55-95abb0570b0a.hana.prod-jp20.hanacloud.ondemand.com:443/";
    public static String prduser1 = "HANAUSER";
    public static String prdpassword = "HanaUser01";
    // dev
    public static String connectionString = "jdbc:sap://403e7b8e-875e-4fc3-b9c2-709a7dc8e659.hana.prod-jp20.hanacloud.ondemand.com:443/";
    public static String user1 = "HANAUSER";
    public static String password = "HanaUser01";

    public static void main(String[] args) throws IOException, SQLException {
        // get hr table data
        String dbString = "SELECT * FROM \"33889480C991422495380D50ECF12001\".\"ZAPI_EMPLOYEE\" WHERE SAL_LOCATION_A = 'WMI' AND COMPANY = '5A0' AND EMAIL_ADDRESS_A != 'none' and EMAIL_ADDRESS_A != 'n/a@wistron.com' and EMAIL_ADDRESS_A != ''";
        List<user> dbusers = getResult(dbString);

        // get ias data
        List<user> iasusers = getiasuserdata();

        // for (int i = 0; i < iasusers.size(); i++) {
        // // System.out.println(iasusers.get(i).getUserid());
        // // System.out.println(iasusers.get(i).getUser_name());
        // System.out.println(iasusers.get(i).getUser_email());
        // }

        List<user> delusers = new ArrayList<user>();
        for (user dbuser : dbusers) {
            for (user iaUser : iasusers) {
                // System.out.println(dbuser.getUser_name());
                // System.out.println(iaUser.getUser_name());
                if (iaUser.getUser_email().toLowerCase().equals(dbuser.getUser_email().toLowerCase())) {
                    delusers.add(iaUser);
                    continue;
                }
            }
        }

        System.out.println("db user count:" + dbusers.size());
        System.out.println("ias user count:" + iasusers.size());
        System.out.println("删除数量：" + delusers.size());

        // for (int i = 0; i < delusers.size(); i++) {
        // // System.out.println(delusers.get(i).getUserid());
        // // System.out.println(delusers.get(i).getUser_name());
        // System.out.println(delusers.get(i).getUser_email());
        // }
        List<user> sameusers = new ArrayList<user>();

        String dbString2 = "SELECT * FROM \"A6C9BEE90FF8405DBF4BC5B823472CCA\".\"ZAPI_EMPLOYEE\"";
        List<user> dbusers2 = getPrdResult(dbString2);
        // \"33889480C991422495380D50ECF12001\"
        for (user dbuser : dbusers2) {
            for (user iaUser : delusers) {
                // System.out.println(dbuser.getUser_name());
                // System.out.println(iaUser.getUser_name());
                if (iaUser.getUser_email().toLowerCase().equals(dbuser.getUser_email().toLowerCase())) {
                    sameusers.add(iaUser);
                    continue;
                }
            }
        }

        for (int i = 0; i < sameusers.size(); i++) {
            // System.out.println(delusers.get(i).getUserid());
            // System.out.println(delusers.get(i).getUser_name());
            System.out.println(sameusers.get(i).getUser_email());
        }
        System.out.println("转部门数量：" + sameusers.size());
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

    public static Connection setPrdConect() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(prdconnectionString, prduser1, prdpassword);
        } catch (SQLException e) {
            System.err.println("Connection Failed. User/Passwd Error? Message: " + e.getMessage());
            return connection;
        }
        return connection;
    }

    public static List<user> getPrdResult(String sqlString) {
        Connection connection = setPrdConect();
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
                    selUser.setUser_name(resultSet.getString(9).toLowerCase());
                    // 邮件
                    if (resultSet.getString(9) == null) {
                        String name = resultSet.getString(9);
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

    public static List<user> getResult(String sqlString) throws SQLException {
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
                    selUser.setUser_name(resultSet.getString(9).toLowerCase());
                    // 邮件
                    if (resultSet.getString(9) == null) {
                        String name = resultSet.getString(9);
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
        connection.close();
        return users;
    }

    public static List<user> getiasuserdata() throws IOException {
        List<user> users = new ArrayList<user>();
        // 一次只能通过请求过去100 条
        for (int j = 0; j < 45; j++) {
            int count1 = j * 100;

            OkHttpClient client = new OkHttpClient().newBuilder()
                    .build();
            okhttp3.MediaType mediaType = MediaType.parse("application/scim+json");
            String urls = "https://a9nw1ogcx.accounts.ondemand.com/scim/Users?attributes=emails,name,dispalyName&startIndex="
                    + count1;
            Request request = new Request.Builder()
                    // qas

                    .url(urls)
                    .method("GET", null)
                    .addHeader("Content-Type", "application/scim+json")
                    .addHeader("attributes", "emails,name,dispalyName")
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
            String responseBody = response.body().string(); // Convert response body to string
            System.out.println(responseBody);
            JSONObject jsResult = JSONObject.parseObject(responseBody);
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

                users.add(selUser);
            }

        }
        return users;

    }

}
