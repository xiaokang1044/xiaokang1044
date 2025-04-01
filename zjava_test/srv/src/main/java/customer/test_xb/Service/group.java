package customer.test_xb.Service;

import java.util.List;
import java.util.ArrayList;
import java.beans.Encoder;
import java.io.*;
import java.net.URLDecoder;

import okhttp3.*;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.bind.annotation.RequestBody;

import com.alibaba.fastjson.JSONObject;

public class group {
    // public static void main(String[] args) throws IOException {
    // List<group> AllgroupsList = getAllgroupias();
    // setgrouplabel(AllgroupsList);
    // // 获取userdata 对于相应的数据 向不同的group 发送不同请求 通过group id 来向group 添加user 信息
    // // getiasuserid("xiaokang_bai@wistron.com");
    // }

    private String group_id;
    private String group_name;
    private String group_label;

    public String getGroup_id() {
        return group_id;
    }

    public void setGroup_id(String group_id) {
        this.group_id = group_id;
    }

    public String getGroup_name() {
        return group_name;
    }

    public void setGroup_name(String group_name) {
        this.group_name = group_name;
    }

    public String getGroup_label() {
        return group_label;
    }

    public void setGroup_label(String group_label) {
        this.group_label = group_label;
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
                        "Basic N2U1MzM4YmItMWQzYi00MmZmLWE2MzQtZWExNTcwNDY3MjRkOlJQazhHNlVkLTY/U09xSUB6PVFbQEJbWTJZczdBTQ==")
                .addHeader("Cookie", "JSESSIONID=88D492E6329736A8B033D2C332F7891B")
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
            AllgroupsList.add(a);
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
                    "C:\\Users\\Z22080010\\Desktop\\\u6743\u9650\u95EE\u9898\\userdataconversion\\1.0to2.0app.xlsx")));

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
