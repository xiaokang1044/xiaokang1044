package customer.test_xb.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.beans.Encoder;
import java.io.*;
import java.net.URLDecoder;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

// import okhttp3.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.ArrayList;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.checkerframework.checker.units.qual.s;
import org.springframework.web.bind.annotation.RequestBody;

import com.alibaba.fastjson.JSONObject;

import customer.test_xb.Service.group;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;

public class test123 {

    public static String connectionString = "jdbc:sap://e3c7fcc5-c71b-466b-af55-95abb0570b0a.hana.prod-jp20.hanacloud.ondemand.com:443/";
    public static String user1 = "HANAUSER";
    public static String password = "HanaUser01";

    public static void main(String[] args) throws IOException {
        // 添加所有用户使用
        String dbString = "SELECT * FROM\"A6C9BEE90FF8405DBF4BC5B823472CCA\".\"ZAPI_EMPLOYEE\" ";
        List<user> dbusers = getResult(dbString);
        java.util.List<user> userlList = getuser_data();
        System.out.println(userlList.size());
        System.out.println(dbusers.size());
        List<user> filteredUsers = dbusers.stream()
                .filter(dbUser -> userlList.stream()
                        .anyMatch(user -> user.getUserid().equals(dbUser.getUserid())))
                .collect(Collectors.toList());

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("C:\\Users\\Z22080010\\Desktop\\output.txt"))) {
            for (user user : filteredUsers) {
                writer.write(user.getUserid());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        filteredUsers.forEach(user -> System.out.print(user.getUserid() + " "));
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
                .url("https://ae9ldgye1.accounts.ondemand.com/scim/Users?filter=emails.value+eq+\"" + email + "\"")
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

}
