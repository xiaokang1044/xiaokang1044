package customer.test_xb.Service;

import java.io.*;
import java.util.zip.GZIPInputStream;

import okhttp3.*;

public class Testportal {
        public static void main(String[] args) throws IOException {
                ResponseBody file_body = get_file();
                File xktest = new File("/C:/Users/Z22080010/Desktop/KPI/VCF 測試pdf.pdf.gz");
                File xktest2 = new File("/C:/Users/Z22080010/Desktop/KPI/VCF 測試pdf.pdf");
                System.out.println(xktest.getAbsolutePath());
                System.out.println(xktest.getName());
                System.out.println(xktest.getPath());

                // FileInputStream
                // InputStream is2 = file_body.;
                InputStream is = file_body.byteStream();

                BufferedInputStream bis = new BufferedInputStream(is);
                try (FileOutputStream fos = new FileOutputStream(xktest2)) {
                        BufferedOutputStream bos = new BufferedOutputStream(fos);
                        // File nf = new File(file_name);
                        // tempFile.renameTo(nf);
                        byte[] buffer = new byte[1024];
                        int len;// 记录每次读取的字节个数
                        while ((len = bis.read(buffer)) != -1) {
                                bos.write(buffer, 0, len);
                        }
                        bos.close();
                        fos.close();
                        is.close();
                        bis.close();
                }
                GZIPInputStream in = null;
                in = new GZIPInputStream(new FileInputStream("/C:/Users/Z22080010/Desktop/KPI/VCF 測試pdf.pdf.gz"));
                FileOutputStream out = null;
                out = new FileOutputStream("/C:/Users/Z22080010/Desktop/KPI/VCF 測試pdf.pdf");
                byte[] buf = new byte[1024];
                int len;
                while ((len = in.read(buf)) > 0) {
                        out.write(buf, 0, len);
                }

                System.out.println("Closing the file and stream");
                in.close();
                out.close();

                InputStream input = new FileInputStream(xktest);

                byte[] byt = new byte[input.available()];

                input.read(byt);
                // xktest.createNewFile();
                OkHttpClient client = new OkHttpClient().newBuilder()
                                .build();
                MediaType mediaType = MediaType.parse("text/plain");
                RequestBody body = new MultipartBody.Builder().setType(MultipartBody.FORM)
                                .addFormDataPart("MessageId", "1561523")
                                .addFormDataPart("File", "VCF \u6E2C\u8A66pdf.pdf",
                                                RequestBody.create(MediaType.parse("application/octet-stream"),
                                                                xktest2))
                                .build();
                Request request = new Request.Builder()
                                .url("https://portalapp-qas.wistron.com/api/AP/AddApprovalFile")
                                .method("POST", body)
                                .build();
                Response response = client.newCall(request).execute();
                System.out.println(response.body().string());

                File xk = new File("xk.txt");
                // System.out.println);

        }

        public static ResponseBody get_file() throws IOException {
                OkHttpClient client = new OkHttpClient().newBuilder()
                                .build();
                MediaType mediaType = MediaType.parse("application/json");
                RequestBody body = null;
                Request request = new Request.Builder()
                                .url("https://api.openconnectors.jp20.ext.hana.ondemand.com/elements/api-v2/files?path=/General/V202306230001/bank/VCF \u6E2C\u8A66pdf.pdf")
                                .method("GET", body)
                                .addHeader("Authorization",
                                                "User QaJgzxZZ9CxZV9pD+GYdG2fBdLcjFDdOyRzfzGcAjoM=, Organization e127a73fd11950b4fb9056750c67171a, Element BMFVh5oUe5Z2KTWpbKv+1fVqKCmUqz2DwhjlSCric3U=")
                                .addHeader("Subsite", "/teams/FTPforBTP")
                                .addHeader("Content-Type", "application/json")
                                .build();
                Response response = client.newCall(request).execute();
                // System.out.println(response.body().string());
                return response.body();
        }

        // String apiAddApprovalFile =
        // "https://portalapp-qas.wistron.com/api/AP/AddApprovalFile";
        // RestTemplate restTemplate = new RestTemplate();

        // ResponseEntity<String> addApprovalFile(String fileName, byte[] fileByte,
        // String messageId) {
        // HttpHeaders headers = new HttpHeaders();
        // headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        // ContentDisposition contentDisposition =
        // ContentDisposition.builder("form-data")
        // .name("file").filename(fileName).build();
        // MultiValueMap<String, String> fileMap = new LinkedMultiValueMap<>();
        // fileMap.add(HttpHeaders.CONTENT_DISPOSITION, contentDisposition.toString());

        // HttpEntity<byte[]> fileEntity = new HttpEntity<>(fileByte, fileMap);
        // MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        // body.add("file", fileEntity);
        // body.add("MessageId", messageId);

        // HttpEntity<MultiValueMap<String, Object>> reqEntity = new HttpEntity<>(body,
        // headers);

        // return restTemplate.exchange(apiAddApprovalFile, HttpMethod.POST, reqEntity,
        // String.class);
        // }

}
