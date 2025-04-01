package customer.test_xb.Service;

import java.io.*;
import okhttp3.*;

public class Main {
  public static void main(String[] args) {
    OkHttpClient client = new OkHttpClient().newBuilder()
        .build();
    MediaType mediaType = MediaType.parse("text/plain");
    RequestBody body = RequestBody.create(mediaType, "text");
    Request request = new Request.Builder()
        .url("https://saps4qasweb.wistron.com:44300/sap/opu/odata/sap/ZCOMTRANS/ZCOMCollection")
        // .method("GET", body)
        .get()
        .addHeader("Authorization", "Basic QVBCWElBT0tBTkdCOlhpYW9rYW5nOTgxMTA3Li4uLg==")
        // .addHeader("Cookie",
        // "SAP_SESSIONID_QAS_888=CPDVuiJgaKMeKvBKSEXs-JUFPec1GxHvvRb5N2yb36A%3d;
        // sap-usercontext=sap-client=888")
        .build();
    Response response;
    try {
      response = client.newCall(request).execute();
      System.out.println(response.body().string());
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

  }
}
