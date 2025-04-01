package customer.test_xb.Service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class testjdbc {
    public static void main(String[] args) {
        // 数据库URL，用户名和密码
        String url = "jdbc:sap://403e7b8e-875e-4fc3-b9c2-709a7dc8e659.hana.prod-jp20.hanacloud.ondemand.com:443/";
        String user = "HANAUSER";
        String password = "HanaUser01";

        try {
            // 1. 加载数据库驱动
            Class.forName("com.sap.db.jdbc.Driver");

            // 2. 创建数据库连接
            Connection conn = DriverManager.getConnection(url, user, password);

            // 3. 创建Statement对象执行SQL查询
            Statement stmt = conn.createStatement();
            String sql = "SELECT * FROM C5D0753E116C42DEAEFD877442F4BBE2.ZVCF_C_M_GENERAL"; // 确保yourtable是你的HANA数据库中的一个表
            ResultSet rs = stmt.executeQuery(sql);

            // 4. 获取ResultSet结果集处理查询结果
            while (rs.next()) {
                // 根据你的表结构获取和输出数据
                String id = rs.getString("customer_code"); // 假设有一个名为"id"的列
                String name = rs.getString("customer_name1"); // 假设有一个名为"name"的列
                System.out.println("ID: " + id + ", Name: " + name);
            }

            // 5. 关闭连接和资源
            rs.close();
            stmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}