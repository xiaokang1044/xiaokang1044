package customer.test_xb.Service;

import java.util.ArrayList;
import java.beans.Encoder;
import java.io.*;
import java.net.URLDecoder;

import okhttp3.*;

import java.util.List;
import java.util.ArrayList;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.bind.annotation.RequestBody;

import com.alibaba.fastjson.JSONObject;

import customer.test_xb.Service.group;

public class DeleteIasuser {
    public static void main(String[] args) {
        List a = getuser_data();
        System.out.println(a.size());

    }

    public static List<user> getuser_data() {
        System.out.println("Hello, Java, BXK test for vcf 2.0 user！");
        try {
            java.util.List<user> userlList = new ArrayList<user>();
            // 1. 创建 Workbook 对象

            // 修改文件路径
            // qas
            Workbook workbook = new XSSFWorkbook(new FileInputStream(new File(
                    "C:\\Users\\Z22080010\\Desktop\\\u6743\u9650\u95EE\u9898\\userdata\\VCF_QAS_Employee_20230530.xlsx")));
            // prd
            // Workbook workbook = new XSSFWorkbook(new FileInputStream(new File(
            // "C:\\Users\\Z22080010\\Desktop\\\u6743\u9650\u95EE\u9898\\userdata\\VCF_QAS_Employee_20230530.xlsx")));
            // 2. 创建 Sheet 对象
            Sheet sheet = workbook.getSheetAt(0);
            // 3. 遍历每一行，获取单元格数据

            for (int i = 0; i < sheet.getPhysicalNumberOfRows(); i++) {
                // 循环读取每一个格
                Row row = sheet.getRow(i);
                String id = "";
                user userdata = new user();
                for (int index = 0; index < row.getPhysicalNumberOfCells(); index++) {
                    Cell cell = row.getCell(index);
                    // 处理单元格中空单元格导致的空指针异常
                    if (cell == null) {
                        cell = row.createCell(index);
                    }
                    id = cell.getStringCellValue();
                    // System.out.println(id);
                    if (index == 0) {
                        userdata.setUserid(id);
                    } else if (index == 1) {
                        userdata.setUser_name(id);
                    } else if (index == 2) {
                        userdata.setUser_email(id);
                    }
                }
                userlList.add(userdata);
            }
            System.out.println(userlList);
            return userlList;
        } catch (

        Exception e) {
            e.printStackTrace();
        }
        return null;

    }
}