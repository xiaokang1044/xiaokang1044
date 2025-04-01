package customer.test_xb;

import java.io.ByteArrayInputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.json.JSONArray;
import org.json.JSONObject;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import java.text.DecimalFormat;

//application/octet-stream
public class testjson {
    public static void main(String[] args) throws Exception {

        DecimalFormat decimalFormat = new DecimalFormat("#,###.00");
        double number = 123456789.123456;
        String a = decimalFormat.format(number);
        System.out.println(a);
        // String jsonString = "["
        // + "{"
        // + "\"mail_destination\": \"bpmworkflowruntime_mail_ccs\","
        // + "\"mail_to\": \"xiaokang_bai@wistron.com\","
        // + "\"mail_content\": \"<head>\n<meta charset=\\\"UTF-8\\\">\n<meta
        // name=\\\"viewport\\\" content=\\\"width=device-width,
        // initial-scale=1.0\\\">\n<title>Email Content</title>\n<style>\n body {\\n
        // font-family: Arial, sans-serif;\\n line-height: 1.6;\\n color: #333;\\n
        // margin: 20px;\\n }\\n .email-container {\\n border: 1px solid #ccc;\\n
        // padding: 20px;\\n border-radius: 10px;\\n background-color: #f9f9f9;\\n }\\n
        // .email-header {\\n font-size: 1.2em;\\n font-weight: bold;\\n margin-bottom:
        // 10px;\\n }\\n .email-body {\\n margin-bottom: 20px;\\npadding-left: 60px; \\n
        // }\\n .email-footer {\\n font-size: 0.9em;\\n color: #777;\\n padding-top:
        // 10px;\\n margin-top: 20px;\\n }\\n .highlight {\\n color: #d9534f;\\n
        // font-weight: bold;\\n }\\n</style>\n</head>\n<body><div
        // class=\\\"email-header\\\">Dear XIAOKANG BAI:</div>\n<div
        // class=\\\"email-body\\\">\n XIAOKANG BAI changed the approver of Credit
        // Evalution Application (Doc. No:E202412200006 Applicant:PUNK WU)<br>\n from
        // XIAOKANG BAI to Smalk<br>\n with comments: test change <br>\n Please click <a
        // href=\\\"https://es-dev.launchpad.cfapps.jp20.hana.ondemand.com/site/ccs2#WorkflowTask-DisplayMyInbox\\\">
        // here </a> to open document for approval.<br>\n</div>\n<div
        // class=\\\"email-footer\\\">\n
        // --------------------------------------------------------------------------------------------<br>\n
        // Do NOT reply this mail as it is automatically sent by the system.<br>\n
        // --------------------------------------------------------------------------------------------<br>\n
        // CCS: Customer Credit Control System\\n</div></body>","
        // + "\"mail_subject\": \"[DEV]CCS Message: (Credit Evalution) E202412200006\","
        // + "\"mail_cc\": \"wits.smalkchen@wistron.com\","
        // + "\"mail_bcc\": \"\""
        // + "},"
        // + "{"
        // + "\"mail_destination\": \"bpmworkflowruntime_mail_ccs\","
        // + "\"mail_to\": \"xiaokang_bai@wistron.com\","
        // + "\"mail_content\": \"<head>\n<meta charset=\\\"UTF-8\\\">\n<meta
        // name=\\\"viewport\\\" content=\\\"width=device-width,
        // initial-scale=1.0\\\">\n<title>Email Content</title>\n<style>\n body {\\n
        // font-family: Arial, sans-serif;\\n line-height: 1.6;\\n color: #333;\\n
        // margin: 20px;\\n }\\n .email-container {\\n border: 1px solid #ccc;\\n
        // padding: 20px;\\n border-radius: 10px;\\n background-color: #f9f9f9;\\n }\\n
        // .email-header {\\n font-size: 1.2em;\\n font-weight: bold;\\n margin-bottom:
        // 10px;\\n }\\n .email-body {\\n margin-bottom: 20px;\\npadding-left: 60px; \\n
        // }\\n .email-footer {\\n font-size: 0.9em;\\n color: #777;\\n padding-top:
        // 10px;\\n margin-top: 20px;\\n }\\n .highlight {\\n color: #d9534f;\\n
        // font-weight: bold;\\n }\\n</style>\n</head>\n<body><div
        // class=\\\"email-header\\\">Dear XIAOKANG BAI:</div>\n<div
        // class=\\\"email-body\\\">\n XIAOKANG BAI changed the approver of Credit
        // Evalution Application (Doc. No:E202412200006 Applicant:PUNK WU)<br>\n from
        // XIAOKANG BAI to Smalk<br>\n with comments: test change <br>\n Please click <a
        // href=\\\"https://es-dev.launchpad.cfapps.jp20.hana.ondemand.com/site/ccs2#WorkflowTask-DisplayMyInbox\\\">
        // here </a> to open document for approval.<br>\n</div>\n<div
        // class=\\\"email-footer\\\">\n
        // --------------------------------------------------------------------------------------------<br>\n
        // Do NOT reply this mail as it is automatically sent by the system.<br>\n
        // --------------------------------------------------------------------------------------------<br>\n
        // CCS: Customer Credit Control System\\n</div></body>","
        // + "\"mail_subject\": \"[DEV]CCS Message: (Credit Evalution) E202412200006\","
        // + "\"mail_cc\": \"wits.smalkchen@wistron.com\","
        // + "\"mail_bcc\": \"\""
        // + "}"
        // + "]";

        // // 解析 JSON 字符串
        // JSONArray jsonArray = new JSONArray(jsonString);

        // // 遍历 JSON 数组
        // for (int i = 0; i < jsonArray.length(); i++) {
        // JSONObject jsonObject = jsonArray.getJSONObject(i);

        // // 获取 JSON 对象中的数据
        // String mailDestination = jsonObject.getString("mail_destination");
        // String mailTo = jsonObject.getString("mail_to");
        // String mailContent = jsonObject.getString("mail_content");
        // String mailSubject = jsonObject.getString("mail_subject");
        // String mailCc = jsonObject.getString("mail_cc");
        // String mailBcc = jsonObject.getString("mail_bcc");

        // // 输出解析结果
        // System.out.println("Mail Destination: " + mailDestination);
        // System.out.println("Mail To: " + mailTo);
        // System.out.println("Mail Content: " + mailContent);
        // System.out.println("Mail Subject: " + mailSubject);
        // System.out.println("Mail CC: " + mailCc);
        // System.out.println("Mail BCC: " + mailBcc);
        // System.out.println("--------------------------------------------------");
        // }

        // String a =
        // "<REQUEST><HEADER><SERVICEID>CCS2.0-QAS</SERVICEID><AUTHORIZATIONCODE>wOce5qD3</AUTHORIZATIONCODE><DATACREATEDTIME>20241226142040</DATACREATEDTIME></HEADER><FORMDATA
        // id=\"FORMDATA\"><SYSTEMID>CCS2.0
        // QAS</SYSTEMID><FORMTYPE>CreditRelease</FORMTYPE><FORMID>82f4c0a5-c351-11ef-ba63-eeee0a9d505b</FORMID><USERID>8706368</USERID><TITLE>CreditRelease:R202412260006</TITLE><SUBJECT>CCS2.0</SUBJECT><MESSAGEID/><CHKRESULT/><APPRSUMMARY/><SUMMARY><section
        // title=\"Applicant information\" type=\"List\"><Row><col
        // title=\"Status\">A</col><col title=\"Appl
        // Date\">2024-12-26T14:20:30Z</col><col title=\"No\">R202412260006</col><col
        // title=\"PIC\">10003259</col><col title=\"Applicant\">10003259</col><col
        // title=\"Pre-no\">R202412260004</col><col
        // title=\"Reviewer\"/></Row></section><section title=\"Body\"
        // titlecolor=\"orange\" type=\"GROUP\"><Row><col><title>Customer
        // Data</title><section type=\"LIST\"><Row><col title=\"Customer
        // Code\">0000000325</col><col title=\"Company\">L600-S600</col><col
        // title=\"Customer Nickname\">ALLIE-OTHER</col><col title=\"Production
        // Type\">PCB300</col><col title=\"Customer Name\"/><col title=\"Critical
        // Account\">N</col><col title=\"AR Insurance\"/><col
        // title=\"Contract\">N(null-null)</col><col
        // title=\"Guarantee\">null(null-null)</col></Row></section></col><col><title>Detail</title><section
        // type=\"LIST\"><Row><col title=\"Payment Term\">ZS45</col><col title=\"Payment
        // Descrtption\">ZS45</col><col title=\"New SO Payment term\">ZW45</col><col
        // title=\"Credit Limit\">10000.00</col><col title=\"Sales Order No\"/><col
        // title=\"Credit Release\">1000.00</col><col title=\"Supporting
        // Document\"/><col title=\"Explanation\">test1</col><col
        // title=\"Action\">test2</col></Row></section></col><col><title>Fin
        // List</title><section
        // type=\"LIST\"><Row/></section></col></Row></section></SUMMARY><ATTACHLIST/></FORMDATA></REQUEST>";
        // System.out.println(GetApplicantId(a));
        // GetApplicantId(a);

    }

    private static String GetApplicantId(String formContent) throws Exception {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(new ByteArrayInputStream(formContent.getBytes()));

            NodeList colList = doc.getElementsByTagName("col");
            for (int i = 0; i < colList.getLength(); i++) {
                Node col = colList.item(i);
                if (col.getNodeType() == Node.ELEMENT_NODE) {
                    Element colElement = (Element) col;
                    if ("Applicant".equals(colElement.getAttribute("title"))) {
                        return colElement.getTextContent();
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Can not get Applicant Id");
            throw new Exception("Can not get Applicant Id");
        }
        System.out.println("Can not get Applicant Id");
        return "";
    }

}
