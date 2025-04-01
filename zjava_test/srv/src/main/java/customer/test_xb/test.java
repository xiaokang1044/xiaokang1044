
package customer.test_xb;

import java.io.*;
import java.math.BigDecimal;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.ByteArrayBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.http.entity.ContentType;
import org.json.JSONObject;
import org.json.XML;

import com.sap.cloud.sdk.cloudplatform.connectivity.DestinationAccessor;
import com.sap.cloud.sdk.cloudplatform.connectivity.HttpClientAccessor;
import com.sap.cloud.sdk.cloudplatform.connectivity.HttpDestination;

import customer.test_xb.Service.dept;
import okhttp3.*;

public class test {
  // public static int sendToIas(String ias_id) throws ParseException,
  // URISyntaxException, IOException {
  // //List<Approvelist> emailLists = new ArrayList<Approvelist>();
  // //emailLists = getAllUserid();
  // //QAS
  // String group_id = "ef46be15-ceb5-405f-9388-172ce534499a";
  // HttpDestination vDestination =
  // DestinationAccessor.getDestination("BTP_IAS").asHttp();
  // HttpClient client = HttpClientAccessor.getHttpClient(vDestination);
  // String Path = "/scim/Groups/";
  // URI uri = new URIBuilder()
  // .setPath(Path)
  // .build();
  // String retuString = " ";
  // Iasuser entity2 = null;
  // String entity = "\r\n\r\n{\r\n \"schemas\": [\r\n
  // \"urn:ietf:params:scim:api:messages:2.0:PatchOp\"\r\n ],\r\n \"Operations\":
  // [\r\n {\r\n \"op\": \"add\",\r\n \"path\": \"members\",\r\n \"value\": [\r\n
  // {\r\n \"value\": \""
  // + ias_id + "\"\r\n }\r\n ]\r\n }\r\n ]\r\n}";
  // HttpPatch httpPatch = new HttpPatch(uri+WorkflowGroupId); //需要修改对应的group id
  // httpPatch.setHeader("Content-Type", "application/scim+json");
  // // httpPatch.setHeader("Accept", "application/json");
  // // httpPatch.setHeader("Authorization", "Basic
  // N2U1MzM4YmItMWQzYi00MmZmLWE2MzQtZWExNTcwNDY3MjRkOlJQazhHNlVkLTY/U09xSUB6PVFbQEJbWTJZczdBTQ==");
  // httpPatch.setEntity(new StringEntity(entity, "UTF-8"));
  // HttpResponse response = client.execute(httpPatch);
  // System.out.println(response.getStatusLine().getStatusCode());
  // return response.getStatusLine().getStatusCode();
  // }

  public static void main(String[] args) throws IOException, URISyntaxException, ParseException {
    // File file = GetCcsFile("C202412040006",
    // "/CreditLimit/C202412040006/營業稅與進出口報單申報實務1101022.pdf",
    // "測試.pdf",
    // 2);
    File file = new File("C:\\path\\to\\save\\directory\\測試.pdf");
    // File file = GetCcsFile("C202412040006",
    // "/CreditLimit/C202412040006/zsdq.XLSX",
    // "zsdq.XLSX",
    // 1);
    SendPortalFileRequest("1701271", file);
    // SendPortalFileRequest2();
    // JSONObject workflowBody = new JSONObject();
    // workflowBody.put("status", "COMPLETED");
    // JSONObject workflowString = new JSONObject();
    // workflowString.put("workflowInstanceId", "1234");
    // workflowString.put("workflowBody", workflowBody);
    // String jsonString1 = workflowString.toString();
    // System.out.println(jsonString1);

    // JSONObject workflowBody2 = new JSONObject();
    // workflowBody2.put("status", "COMPLETED");
    // workflowBody2.toString();
    // System.out.println(workflowBody2.toString());

    switch ('C') {
      case 'C':
      case 'S':
        System.out.println("C");
        break;
      default:
        System.out.println("D");
        break;
    }

  }

  public static File GetCcsFile(String sCno, String path, String file_name, int seq)
      throws URISyntaxException, ParseException, IOException {
    // String[] fileStrings = file_name.split("\.");
    // int length = fileStrings.length;
    // String file_name1 = "";
    // for (String i : fileStrings) {
    // file_name1 = file_name1 + i;
    // }
    // // 文件后缀
    // String file_name2 = fileStrings[length - 1];
    // String file_name3 = "file" + seq + "." + file_name2;

    String url_path = "";
    url_path = path;

    // HttpDestination vDestination =
    // DestinationAccessor.getDestination("OneDriveAPI").asHttp();
    // HttpClient client = HttpClientAccessor.getHttpClient(vDestination);

    CloseableHttpClient client = HttpClients.createDefault();
    URI uri = new URIBuilder("https://api.openconnectors.jp20.ext.hana.ondemand.com")
        .setPath("/elements/api-v2/files")
        .setParameter("path", url_path)
        .build();
    System.out.print(url_path);
    HttpGet httpGet = new HttpGet(uri);

    httpGet.setHeader("Authorization",
        "User OYI4ZmKM7TAGzmbdb/3pwt8rFOmXst/lrTXbYJZybn0=, Organization e127a73fd11950b4fb9056750c67171a, Element EoTizqY6SLbdaIkijo01Cc9cts9NMK1lfnGbiWg9RAE=");
    // httpGet.setHeader("Subsite", "/teams/FTPforBTP");
    httpGet.setHeader("Content-Type", "application/json");

    HttpResponse response = client.execute(httpGet);
    System.out.println(response.getStatusLine().getStatusCode());
    if (response.getStatusLine().getStatusCode() == 200) {
      System.out.println("file name");

      System.out.println(file_name);
      // 指定保存文件的目录
      File directory = new File("C:/path/to/save/directory/");
      if (!directory.exists()) {
        directory.mkdirs();
      }
      // String fileName = URLEncoder.encode(file_name,
      // StandardCharsets.UTF_8.toString());
      File tempFile = new File(directory, file_name);
      // System.out.println(response.getEntity().getContent().toString());
      // 获取输入流
      InputStream inputStream = response.getEntity().getContent();

      // 创建输出流
      FileOutputStream outputStream = new FileOutputStream(tempFile.getPath());

      // 读取输入流数据并写入输出流
      byte[] buffer = new byte[4096];
      int bytesRead;
      while ((bytesRead = inputStream.read(buffer)) != -1) {
        outputStream.write(buffer, 0, bytesRead);
      }

      // 关闭流
      outputStream.close();
      inputStream.close();
      System.out.println("File message:" + tempFile.getName() + tempFile.getPath() + "space" + tempFile.getParent());

      return tempFile;

    }
    System.out.println("Get data fail!");
    return null;

  }

  private static String SendPortalFileRequest(String messageId, File file) {
    // HttpDestination vDestination =
    // DestinationAccessor.getDestination("Portalapp").asHttp();
    // HttpClient client = HttpClientAccessor.getHttpClient(vDestination);
    CloseableHttpClient client = HttpClients.createDefault();
    // URI uri = new
    // URIBuilder("https://api.openconnectors.jp20.ext.hana.ondemand.com")
    try {
      // URI uri = new URIBuilder("https://portalapp-qas.wistron.com")
      // .setPath("/api/AP/AddApprovalFile")

      // .build();
      URI uri = new URIBuilder("http://10.47.144.62:8089")
          .setPath("/upload")
          .build();
      HttpPost httpPost = new HttpPost(uri);
      // httpPost.setHeader("Content-Type", "multipart/form-data");
      MultipartEntityBuilder entityBuilder = MultipartEntityBuilder.create();
      entityBuilder.setContentType(ContentType.MULTIPART_FORM_DATA);
      entityBuilder.setCharset(Charset.forName("UTF-8"));
      entityBuilder.addTextBody("MessageId", messageId, ContentType.TEXT_PLAIN);

      String fileName = new String(file.getName());

      byte[] utf8Bytes = fileName.getBytes("UTF-8");
      String utf8String = new String(utf8Bytes, "UTF-8");
      fileName = utf8String;
      // String fileName = URLEncoder.encode(file.getName(),
      // StandardCharsets.UTF_8.toString());
      // System.out.println("fileName:" + fileName);
      // entityBuilder.addBinaryBody("File", file,
      // ContentType.APPLICATION_OCTET_STREAM, fileName);

      System.out.println(file.getPath());
      entityBuilder.addBinaryBody("file", file);

      // Path path = file.toPath();
      // byte[] fileBytes = Files.readAllBytes(path);
      // System.out.println("file byte message :");
      // System.out.println(file.getName());
      // System.out.println(path.toString());
      // System.out.println(fileBytes.length);
      // ByteArrayBody byteArrayBody = new ByteArrayBody(fileBytes,
      // ContentType.DEFAULT_BINARY, fileName);
      // entityBuilder.addPart("File", byteArrayBody);

      HttpEntity requestEntity = entityBuilder.build();
      httpPost.setEntity(requestEntity);

      System.out.println("请求内容:" +
          httpPost.getRequestLine());
      HttpResponse response = client.execute(httpPost);

      if (response.getStatusLine().getStatusCode() == 200) {
        String entity = EntityUtils.toString(response.getEntity());
        JSONObject jsResult = new JSONObject(entity);
        System.out.println("send file to portal success");
        System.out.println(entity);
        // file.delete();
        System.out.println("fileName:" + fileName);
        System.out.println("filePath:" + file.getPath());
        // System.out.println("file byte message :");
        // System.out.println(file.getName());
        // System.out.println(path.toString());
        // System.out.println(fileBytes.length);
        return "send to portal success";
      } else {
        String returnMsg = response.getStatusLine().getStatusCode() + " "
            + response.getStatusLine().getReasonPhrase();
        System.out.println("send file to portal fail");
        System.out.println(returnMsg);
        // file.delete();
        return returnMsg;
      }
    } catch (Exception e) {
      e.printStackTrace();
      return "Exception occurred: " + e.getMessage();
    }

  }

  private static void SendPortalFileRequest2() throws IOException {
    // HttpDestination vDestination =
    // DestinationAccessor.getDestination("Portalapp").asHttp();
    // String destinationUrl = vDestination.getUri().toString();
    String destinationUrl = "https://portalapp-qas.wistron.com";
    OkHttpClient client = new OkHttpClient.Builder()
        .build();

    MediaType mediaType = MediaType.parse("text/plain");
    RequestBody body = new MultipartBody.Builder().setType(MultipartBody.FORM)
        .addFormDataPart("MessageId", "1700596")
        .addFormDataPart("File", "/C:/path/to/save/directory/營業稅與進出口報單申報實務1101022.pdf",
            RequestBody.create(MediaType.parse("application/octet-stream"),
                new File("/C:/path/to/save/directory/營業稅與進出口報單申報實務1101022.pdf")))
        .build();
    Request request = new Request.Builder()
        .url(destinationUrl + "/api/AP/AddApprovalFile")
        .method("POST", body)
        .build();
    Response response = client.newCall(request).execute();
    System.out.println(response.body().string());
  }
}
