package customer.zdep_jobs.services;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sap.cds.ql.Select;
import com.sap.cds.ql.cqn.CqnSelect;
import com.sap.cds.services.persistence.PersistenceService;

import cds.gen.zdep.Member;
import cds.gen.zdep.Member_;
import cds.gen.zdep.Plan;
import cds.gen.zdep.Plan_;

@Service
public class AlertService {
    @Autowired
    private PersistenceService db;

    @Autowired
    private MailService mailService;

    public List<Member> GetAllUser() {
        CqnSelect sel = Select.from(Member_.class);
        // Execute the query and return the results
        List<Member> result = db.run(sel).listOf(Member.class);
        return result;
    }

    public boolean checkPlan(String string) {
        String weekString = getWeek();
        CqnSelect sel = Select.from(Plan_.class).where(e -> e.weekId().eq(weekString).and(e.userId().eq(string) ));
        // Execute the query and return the results
        List<Plan> result = db.run(sel).listOf(Plan.class);
        if (weekString.length() > 0) {
            return true;
        } else {
            return false;
        }
    }

    public String getWeek() {
        LocalDate currentDate = LocalDate.now();
        LocalDate startOfWeek = currentDate.with(DayOfWeek.MONDAY);
        LocalDate endOfWeek = currentDate.with(DayOfWeek.SUNDAY);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        String startOfWeekStr = startOfWeek.format(formatter);
        String endOfWeekStr = endOfWeek.format(formatter);
        // 拼接结果字符串
        String result = startOfWeekStr + "-" + endOfWeekStr;
        return result;
    }

    public void checkAll() throws MessagingException {

        String htmlContent = "<!DOCTYPE html>\n" +
        "<html lang=\"zh-CN\">\n" +
        "<head>\n" +
        "    <meta charset=\"UTF-8\">\n" +
        "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
        "    <title>工时维护提醒</title>\n" +
        "    <style>\n" +
        "        body {\n" +
        "            font-family: Arial, sans-serif;\n" +
        "            line-height: 1.6;\n" +
        "        }\n" +
        "        .container {\n" +
        "            max-width: 600px;\n" +
        "            margin: 0 auto;\n" +
        "            padding: 20px;\n" +
        "            border: 1px solid #ddd;\n" +
        "            border-radius: 5px;\n" +
        "            background-color: #f9f9f9;\n" +
        "        }\n" +
        "        .header {\n" +
        "            text-align: center;\n" +
        "            padding-bottom: 20px;\n" +
        "        }\n" +
        "        .content {\n" +
        "            padding: 20px;\n" +
        "            background-color: #fff;\n" +
        "            border-radius: 5px;\n" +
        "            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);\n" +
        "        }\n" +
        "        .footer {\n" +
        "            text-align: center;\n" +
        "            padding-top: 20px;\n" +
        "            font-size: 0.9em;\n" +
        "            color: #777;\n" +
        "        }\n" +
        "    </style>\n" +
        "</head>\n" +
        "<body>\n" +
        "    <div class=\"container\">\n" +
        "        <div class=\"header\">\n" +
        "            <h2>工时维护提醒</h2>\n" +
        "        </div>\n" +
        "        <div class=\"content\">\n" +
        "            <p>尊敬的用户，</p>\n" +
        "            <p>请及时登录工时系统，维护您本周的工时记录。</p>\n" +
        "            <p><a href=\"https://es-dev.launchpad.cfapps.jp20.hana.ondemand.com/site/sapdashboard#plans2-display?sap-ui-app-id-hint=saas_approuter_zdep.plans1\" class=\"button\">点击这里进入工时系统</a></p>\n" +
        "            <p>如有任何问题，请联系系统管理员。</p>\n" +
        "            <p>谢谢！</p>\n" +
        "        </div>\n" +
        "        <div class=\"footer\">\n" +
        "            <p>此邮件为系统自动发送，请勿回复。</p>\n" +
        "        </div>\n" +
        "    </div>\n" +
        "</body>\n" +
        "</html>";
        List<Member> users = GetAllUser();
        for(Member a: users){
            if (!checkPlan(a.getEmployeeId())) {
                mailService.sendMailImpl("bpmworkflowruntime_mail", "xiaokang_bai@wistron.com", 
                htmlContent, "工时维护提醒", "xiaokang_bai@wistron.com","xiaokang_bai@wistron.com");
            }
        }
    }

}
