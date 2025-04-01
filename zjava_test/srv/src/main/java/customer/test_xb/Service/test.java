package customer.test_xb.Service;

import java.io.*;
import okhttp3.*;

public class test {
        public static void main(String[] args) {
                // OkHttpClient client = new OkHttpClient().newBuilder().build();
                // MediaType mediaType = MediaType.parse("text/plain");
                // RequestBody body = RequestBody.create(mediaType, "123 ");
                // Request request = new Request.Builder()
                // .url("http://saperpq.wistron.com:8000/sap/opu/odata/sap/ZBASEDATA_SRV/VendorMaster?$top=100")
                // .method("GET", body)
                // .put(body)
                // .addHeader("Authorization", "Basic QVBCR1NEQzpHU0RDMjAyM1FBUw==")
                // .addHeader("Cookie",
                // "SAP_SESSIONID_QAS_888=jqr-0dY_NGoqRoKL7Zw_rkEQGGCE8xHusAoAF6R3EBA%3d;
                // sap-usercontext=sap-client=888")
                // .build();
                // try (Response response = client.newCall(request).execute()) {
                // try {
                // System.out.println(response.body().string());
                // } catch (IOException e) {
                // TODO Auto-generated catch block
                // e.printStackTrace();
                // }
                // } catch (IOException e) {
                // TODO Auto-generated catch block
                // e.printStackTrace();
                // }
                int i = 1;

        }

}

// URI uriApp = new URIBuilder()
// .setScheme("https")
// //
// .setHost("scpgatw-wistronprework-sdev-zcomapi-table-srv.cfapps.eu10.hana.ondemand.com")
// .setHost("portalapp-qas.wistron.com")
// .setPort(443)
// // .setPath("/odata/v4/TableService/HrEmployee")
// .setPath("/api/AP/AddApprovalFile")
// // .setParameter("$filter", "ENO\teq\t'"+ENO+"'")
// // .setParameter("$filter", "deptid\teq\t'"+deptCode+"'")
// // .setParameter("path", "/General/V202304100001/bank/business_rule.txt")
// .build();
// HttpPost httpPostApp = new HttpPost(uriApp);
// // httpPostApp.setHeader("Subsite", "/teams/FTPforBTP");
// // httpPostApp.setHeader("Authorization", "User
// QaJgzxZZ9CxZV9pD+GYdG2fBdLcjFDdOyRzfzGcAjoM=, Organization
// e127a73fd11950b4fb9056750c67171a, Element
// BMFVh5oUe5Z2KTWpbKv+1fVqKCmUqz2DwhjlSCric3U=");
// // httpPostApp.setHeader("Content-Type", "multipart/form-data");

// MultipartEntityBuilder entityBuilder = MultipartEntityBuilder.create();
// // entityBuilder.setCharset(java.nio.charset.StandardCharsets.UTF_8);
// entityBuilder.setContentType(ContentType.MULTIPART_FORM_DATA);

// entityBuilder.addTextBody("MessageId", "1559018", ContentType.TEXT_PLAIN);
// // 添加文件参数
// File file = new File("/C:/Users/Z19083399/Desktop/testyx.txt");
// entityBuilder.addBinaryBody("File", file,
// ContentType.APPLICATION_OCTET_STREAM, file.getName());

// // 构建请求体
// HttpEntity requestEntity = entityBuilder.build();

// // 设置 HttpPost 的请求体
// httpPostApp.setEntity(requestEntity);

// HttpEntity httpEntity = MultipartEntityBuilder.create()
// // FORM
// .addPart("name",
// new StringBody("<Spring Cloud>",
// ContentType.create("application/x-www-form-urlencoded",
// StandardCharsets.UTF_8)))
// // JSON
// .addPart("info",
// new StringBody("{\"site\": \"https://www.springcloud.io\"}",
// ContentType.APPLICATION_JSON))
// // FILE
// .addBinaryBody("logo", new File("C:\\Users\\KevinBlandy\\Desktop\\logo.png"),
// ContentType.IMAGE_PNG,
// "logo.png")
// .build();

// String[] infor_flag = "M,P,F".split(",");
// int i = 0;
// for (i = 0; i < infor_flag.length; i++) {
// System.out.println(infor_flag[i]);
// }
// System.out.println(useArraysBinarySearch(infor_flag, "F"));

// String[] block_description = new String[4];

// block_description[0] = "";
// block_description[1] = "此Customer code不再使用:
// 不允許開立SO/DN/PGI/Billing/不允許立應收帳款(Direct Posting)";
// block_description[2] = "不允許開立SO/DN PGI/Billing ; 允許立應收帳款by FI Direct
// Posting.";
// block_description[3] = "不允許開立New normal SO (仍開放開立 Debit(ZYD)
// /Credit(ZYC));允許Old SO/Open DN PGI/Billing及立應收帳款by FI Direct Posting.";
// String a = block_description[3];
// System.out.println(a);

// public static boolean useArraysBinarySearch(String[] arr, String targetValue)
// {
// int a = Arrays.binarySearch(arr, targetValue);
// if (a >= 0) {
// return true;
// } else {
// return false;
// }
// }

// import com.google.gson.Gson;
// import com.google.gson.reflect.TypeToken;
// import com.sun.net.httpserver.HttpExchange;
// import com.sun.net.httpserver.HttpHandler;
// import com.sun.net.httpserver.HttpServer;
// import org.apache.commons.lang.StringUtils;

// import java.io.*;
// import java.net.InetSocketAddress;
// import java.net.URI;
// import java.sql.*;
// import java.util.Map;

// /**
// * @ClassName: DataConversion
// * @Description:
// * @Author: Z19083399
// * @Date: 2023/7/3 17:12
// */
// public class DataConversion {

// public static void main(String[] args) throws IOException {
// HttpServer httpServer = HttpServer.create(new InetSocketAddress(8001), 0);
// httpServer.createContext("/test", new DataConversion.TestHandler());
// httpServer.start();
// System.out.println("8001端口已成功启动");
// }

// static class TestHandler implements HttpHandler {

// @Override
// public void handle(HttpExchange httpExchange) throws IOException {
// //获取请求路径
// URI requestURI = httpExchange.getRequestURI();
// System.out.println("请求路径为："+requestURI);
// //获取请求方法
// String requestMethod = httpExchange.getRequestMethod();
// System.out.println("请求方法为："+requestMethod);
// //获取请求体
// InputStream requestBody = httpExchange.getRequestBody();
// InputStreamReader inputStreamReader = new InputStreamReader(requestBody);
// BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
// StringBuffer stringBuffer = new StringBuffer();
// String s = "";
// while ((s = bufferedReader.readLine()) != null) {
// stringBuffer.append(s);
// }

// System.out.println(stringBuffer);

// //此处引入谷歌Gson框架将String转为Map方便获取参数
// // Gson gson = new Gson();
// // Map map = gson.fromJson(stringBuffer.toString(), new TypeToken<Map<String,
// Object>>() {
// // }.getType());
// // String oracleTableSql = String.valueOf(stringBuffer);
// String oracleTableSql =
// StringUtils.substringBefore(String.valueOf(stringBuffer), "@");
// String hanaTable = StringUtils.substringAfter(String.valueOf(stringBuffer),
// "@");
// OutputStream responseBody = httpExchange.getResponseBody();

// DataConversion dataConversion = new DataConversion();
// //返回
// String rtn = dataTransfer(oracleTableSql,hanaTable);
// httpExchange.sendResponseHeaders(200, 0);

// responseBody.write(rtn.getBytes());
// responseBody.close();
// }
// }

// public static String dataTransfer(String source, String target) {
// String oracleUrl = "jdbc:oracle:thin:@10.37.34.56:1525:VCFPRD";
// String oracleUser = "VCF20";
// String oraclePassword = "VCF20";

// // DEV
// // String hanaUrl =
// "jdbc:sap://403e7b8e-875e-4fc3-b9c2-709a7dc8e659.hana.prod-jp20.hanacloud.ondemand.com:443/";
// // PRD
// String hanaUrl =
// "jdbc:sap://e3c7fcc5-c71b-466b-af55-95abb0570b0a.hana.prod-jp20.hanacloud.ondemand.com:443/";
// String hanaUser = "HANAUSER";
// String hanaPassword = "HanaUser01";

// String oracleTable = "source_table";
// // String hanaTable =
// "AA30E8073A39431EA93DA91E1CBB4799.ZVCF_COM_APPROVAL_LIST";
// String hanaTable = target;

// // Import Oracle JDBC driver
// try {
// Class.forName("oracle.jdbc.driver.OracleDriver");
// } catch (ClassNotFoundException e) {
// System.out.println("Oracle JDBC driver is missing.");
// e.printStackTrace();
// return e.getMessage();
// }

// long processTime = 0;
// long sqlProcessTime = 0;

// // Import SAP HANA JDBC driver
// try {
// Class.forName("com.sap.db.jdbc.Driver");
// } catch (ClassNotFoundException e) {
// System.out.println("SAP HANA JDBC driver is missing.");
// e.printStackTrace();
// return e.getMessage();
// }

// // Connection objects
// Connection oracleConn = null;
// Connection hanaConn = null;

// try {
// // Create connections
// oracleConn = DriverManager.getConnection(oracleUrl, oracleUser,
// oraclePassword);
// hanaConn = DriverManager.getConnection(hanaUrl, hanaUser, hanaPassword);

// String query = source;
// Statement statement = oracleConn.createStatement();
// ResultSet resultSet = statement.executeQuery(query);

// // Get the metadata of the Oracle table
// ResultSetMetaData metaData = resultSet.getMetaData();
// int columnCount = metaData.getColumnCount();

// // Construct the SQL insert statement for the HANA table
// StringBuilder insertStatement = new StringBuilder();
// StringBuilder culumnStatement = new StringBuilder();
// StringBuilder placeholderStatement = new StringBuilder();

// for (int i = 1; i <= columnCount; i++) {
// // Get the column name and add it to the insert statement
// String columnName = metaData.getColumnName(i);
// culumnStatement.append(columnName);
// placeholderStatement.append("?");

// if (i < columnCount) {
// culumnStatement.append(",");
// placeholderStatement.append(",");
// }
// }

// insertStatement.append("INSERT INTO " + hanaTable + " (");
// insertStatement.append(culumnStatement);
// insertStatement.append(") VALUES (");
// insertStatement.append(placeholderStatement);
// insertStatement.append(")");

// PreparedStatement hanaStatement =
// hanaConn.prepareStatement(insertStatement.toString());

// long stimesql = System.currentTimeMillis();

// while (resultSet.next()) {
// for (int i = 1; i <= columnCount; i++) {
// // Get the column value and add it to the insert statement
// // String columnValue = resultSet.getString(i);
// //
// // if (columnValue != null) {
// // hanaStatement.setString(i,columnValue);
// //// insertStatement.append("'" + columnValue.toString() + "'");
// // } else {
// // hanaStatement.setString(i,"NULL");
// //// insertStatement.append("NULL");
// // }
// hanaStatement.setString(i,resultSet.getString(i));

// // if (i < columnCount) {
// // insertStatement.append(",");
// // }
// }

// // insertStatement.append(")");

// // // Execute the insert statement on the HANA table
// // hanaStatement.addBatch(insertStatement.toString());
// hanaStatement.addBatch();
// // System.out.println(insertStatement.toString());

// // Clear the insert statement for the next iteration
// // insertStatement.setLength(0);
// }

// long etimesql = System.currentTimeMillis();

// long stime = System.currentTimeMillis();
// hanaStatement.executeBatch();
// long etime = System.currentTimeMillis();
// processTime = etime-stime;
// sqlProcessTime = etimesql-stimesql;

// System.out.println("Data transfer complete.");
// System.out.println("Successful, SQL处理时间：" + sqlProcessTime/1000 + " 秒,
// DB执行时间：" + processTime/1000 + " 秒");

// } catch (SQLException e) {
// System.out.println("Error occurred while transferring data.");
// e.printStackTrace();
// return e.getMessage();
// } finally {
// // Close connections
// if (oracleConn != null) {
// try {
// oracleConn.close();
// } catch (SQLException e) {
// e.printStackTrace();
// return e.getMessage();
// }
// }

// if (hanaConn != null) {
// try {
// hanaConn.close();
// } catch (SQLException e) {
// e.printStackTrace();
// return e.getMessage();
// }
// }
// }
// return "Successful, SQL处理时间：" + sqlProcessTime/1000 + " 秒, DB执行时间：" +
// processTime/1000 + " 秒";
// }

// //
// // public static String dataTransfer(String source, String target) {
// // String oracleUrl = "jdbc:oracle:thin:@10.37.34.56:1525:VCFPRD";
// // String oracleUser = "VCF20";
// // String oraclePassword = "VCF20";
// //
// // String hanaUrl =
// "jdbc:sap://403e7b8e-875e-4fc3-b9c2-709a7dc8e659.hana.prod-jp20.hanacloud.ondemand.com:443/";
// // String hanaUser = "HANAUSER";
// // String hanaPassword = "HanaUser01";
// //
// // String oracleTable = "source_table";
// //// String hanaTable =
// "AA30E8073A39431EA93DA91E1CBB4799.ZVCF_COM_APPROVAL_LIST";
// // String hanaTable = target;
// //
// // // Import Oracle JDBC driver
// // try {
// // Class.forName("oracle.jdbc.driver.OracleDriver");
// // } catch (ClassNotFoundException e) {
// // System.out.println("Oracle JDBC driver is missing.");
// // e.printStackTrace();
// // return e.getMessage();
// // }
// //
// // long processTime = 0;
// // long sqlProcessTime = 0;
// //
// // // Import SAP HANA JDBC driver
// // try {
// // Class.forName("com.sap.db.jdbc.Driver");
// // } catch (ClassNotFoundException e) {
// // System.out.println("SAP HANA JDBC driver is missing.");
// // e.printStackTrace();
// // return e.getMessage();
// // }
// //
// // // Connection objects
// // Connection oracleConn = null;
// // Connection hanaConn = null;
// //
// // try {
// // // Create connections
// // oracleConn = DriverManager.getConnection(oracleUrl, oracleUser,
// oraclePassword);
// // hanaConn = DriverManager.getConnection(hanaUrl, hanaUser, hanaPassword);
// //
// // // Query the Oracle table
// //// String query = "SELECT DNO AS CNO,\n" +
// //// "APSEQ AS SEQ,\n" +
// //// "DCLASSCODE AS FORM_TYPE,\n" +
// //// "'V200703020056' AS ORIGIN_CNO,\n" +
// //// "'YXTEST' AS NAME,\n" +
// //// "OAPNO AS ORIGIN_APPROVAL_BY,\n" +
// //// "APLEVELNO AS ROLE,\n" +
// //// "PIC AS EMPLOYEE_ID,\n" +
// //// "'YOUNGTTT' AS EMAIL,\n" +
// //// "PICDEPTCODE AS DEPT_NO,\n" +
// //// "APLEVELNO AS PIC_LEVEL,\n" +
// //// "APSTATUS AS STATUS,\n" +
// //// "REPLACE(APCOMMENT,'''','''''') AS \"COMMENT\",\n" +
// //// "'YYTESTYY' AS WORKFLOW_KEY,\n" +
// //// "'2023-07-01 11:50:00' AS \"DATE\"\n" +
// //// "FROM (SELECT A.* FROM VCF.APTABLEH A \n" +
// //// "WHERE DNO != 'V201606150121' AND APSTATUS = 'C' AND APSEQ IS NOT NULL
// AND NOT EXISTS(SELECT 1 FROM VCF.APTABLEH WHERE DNO = A.DNO AND APSEQ =
// A.APSEQ AND APDATE > A.APDATE))";
// //
// //// String query = "SELECT DNO AS CNO,\n" +
// //// "APSEQ AS SEQ,\n" +
// //// "DCLASSCODE AS FORM_TYPE,\n" +
// //// "'V200703020056' AS ORIGIN_CNO,\n" +
// //// "'YXTEST' AS NAME,\n" +
// //// "OAPNO AS ORIGIN_APPROVAL_BY,\n" +
// //// "APLEVELNO AS ROLE,\n" +
// //// "PIC AS EMPLOYEE_ID,\n" +
// //// "'YOUNGTTT' AS EMAIL,\n" +
// //// "PICDEPTCODE AS DEPT_NO,\n" +
// //// "APLEVELNO AS PIC_LEVEL,\n" +
// //// "APSTATUS AS STATUS,\n" +
// //// "APCOMMENT AS \"COMMENT\",\n" +
// //// "'WORKFLOW1' AS WORKFLOW_KEY,\n" +
// //// "'2023-07-01 11:50:00' AS \"DATE\"\n" +
// //// "FROM VCF.APTABLEH\n" +
// //// "WHERE DNO IN ('V200703020056','V200703020047')";
// // String query = source;
// // Statement statement = oracleConn.createStatement();
// // ResultSet resultSet = statement.executeQuery(query);
// //
// // // Get the metadata of the Oracle table
// // ResultSetMetaData metaData = resultSet.getMetaData();
// // int columnCount = metaData.getColumnCount();
// //
// // // Construct the SQL insert statement for the HANA table
// // StringBuilder insertStatement = new StringBuilder();
// // StringBuilder culumnStatement = new StringBuilder();
// //
// // for (int i = 1; i <= columnCount; i++) {
// // // Get the column name and add it to the insert statement
// // String columnName = metaData.getColumnName(i);
// // culumnStatement.append(columnName);
// //
// // if (i < columnCount) {
// // culumnStatement.append(",");
// // }
// // }
// //
// //
// // Statement hanaStatement = hanaConn.createStatement();
// //
// // long stimesql = System.currentTimeMillis();
// //
// // while (resultSet.next()) {
// // insertStatement.append("INSERT INTO " + hanaTable + " (");
// // insertStatement.append(culumnStatement);
// // insertStatement.append(") VALUES (");
// // for (int i = 1; i <= columnCount; i++) {
// // // Get the column value and add it to the insert statement
// // Object columnValue = resultSet.getObject(i);
// //
// // if (columnValue != null) {
// // insertStatement.append("'" + columnValue.toString() + "'");
// // } else {
// // insertStatement.append("NULL");
// // }
// //
// // if (i < columnCount) {
// // insertStatement.append(",");
// // }
// // }
// //
// // insertStatement.append(")");
// //
// // // Execute the insert statement on the HANA table
// // hanaStatement.addBatch(insertStatement.toString());
// //// System.out.println(insertStatement.toString());
// //
// // // Clear the insert statement for the next iteration
// // insertStatement.setLength(0);
// // }
// //
// // long etimesql = System.currentTimeMillis();
// //
// // long stime = System.currentTimeMillis();
// // hanaStatement.executeBatch();
// // long etime = System.currentTimeMillis();
// // processTime = etime-stime;
// // sqlProcessTime = etimesql-stimesql;
// //
// // System.out.println("Data transfer complete.");
// //
// // } catch (SQLException e) {
// // System.out.println("Error occurred while transferring data.");
// // e.printStackTrace();
// // return e.getMessage();
// // } finally {
// // // Close connections
// // if (oracleConn != null) {
// // try {
// // oracleConn.close();
// // } catch (SQLException e) {
// // e.printStackTrace();
// // return e.getMessage();
// // }
// // }
// //
// // if (hanaConn != null) {
// // try {
// // hanaConn.close();
// // } catch (SQLException e) {
// // e.printStackTrace();
// // return e.getMessage();
// // }
// // }
// // }
// // return "Successful, SQL处理时间：" + sqlProcessTime/1000 + " 秒, DB执行时间：" +
// processTime/1000 + " 秒";
// // }
// }