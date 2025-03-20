package customer.zdep_jobs.controllers;

import javax.mail.MessagingException;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import customer.zdep_jobs.services.MailService;

@RestController
@RequestMapping("/rest")
public class MailController {

    @Autowired
    private MailService mailService;

    @PostMapping("/sendMail")
    public String senMail(@RequestBody String body) throws MessagingException {
        JSONObject js_result = new JSONObject(body);
        String mail_destination = js_result.getString("mail_destination");
        String mail_to = js_result.getString("mail_to");
        String mail_content = js_result.getString("mail_content");
        String mail_subject = js_result.getString("mail_subject");
        String mail_cc = js_result.getString("mail_cc");
        String mail_bcc = js_result.getString("mail_bcc");
        mailService.sendMailImpl(mail_destination, mail_to, mail_content, mail_subject, mail_cc, mail_bcc);

        return "test";
    }

}
