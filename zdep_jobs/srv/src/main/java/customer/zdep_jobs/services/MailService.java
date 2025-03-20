package customer.zdep_jobs.services;
import java.util.List;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.stereotype.Service;

import com.sap.cloud.sdk.cloudplatform.connectivity.Destination;
import com.sap.cloud.sdk.cloudplatform.connectivity.DestinationAccessor;


@Service
public class MailService {

    public void sendMailImpl(String mail_destination, String mail_to, String mail_content, String mail_subject,
            String mail_cc, String mail_bcc) 
            throws MessagingException {

        Destination vDestination = DestinationAccessor.getDestination(mail_destination);
        String mail_password = vDestination.get("mail.password").get().toString();
        String mail_from = vDestination.get("mail.smtp.from").get().toString();

        String host = vDestination.get("mail.smtp.host").get().toString();
        String port = vDestination.get("mail.smtp.port").get().toString();
        String auth = vDestination.get("mail.smtp.auth").get().toString();
        // String checkserveridentity =
        // vDestination.get("mail.smtp.ssl.checkserveridentity").get().toString();
        // String trust = vDestination.get("mail.smtp.ssl.trust").get().toString();
        String enable = vDestination.get("mail.smtp.starttls.enable").get().toString();
        // String required =
        // vDestination.get("mail.smtp.starttls.required").get().toString();
        String protocol = vDestination.get("mail.transport.protocol").get().toString();

        Properties prop = new Properties();
        // prop.setProperty("spring.mail.host", "smtp.office365.com"); // 设置邮件服务器
        prop.setProperty("mail.host", host);
        // prop.setProperty("spring.mail.protocol", "smtp"); // 邮件发送协议
        prop.setProperty("mail.transport.protocol", protocol);
        // prop.setProperty("spring.mail.properites.mail.smtp.auth", "true"); //
        // 需要验证用户名和密码
        prop.setProperty("mail.smtp.auth", auth);
        // prop.setProperty("spring.mail.properites.mail.smtp.starttls.enable", "true");
        prop.setProperty("mail.smtp.starttls.enable", enable);
        // prop.setProperty("spring.mail.port", "587");
        prop.setProperty("mail.smtp.port", port);
        prop.setProperty("mail.imap.partialfetch", "false");

        // 创建定义整个邮件程序所需的环境信息的 Session 对象
        // Session session = Session.getInstance(prop);
        Session session = Session.getDefaultInstance(prop, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                // 发件人邮箱用户名，授权码
                return new PasswordAuthentication(mail_from, mail_password);
            }
        });

        // 开启 Session 的 debug 模式，这样就可以查看程序发送 Email 的运行状态
        session.setDebug(true);

        // 通过 session 得到 transport 对象
        Transport ts = session.getTransport();

        // 创建邮件，写邮件
        MimeMessage message = new MimeMessage(session);

        // System.out.println(mail_to+"1#"+mail_content+"2#"+ mail_subject + "3#" +
        // mail_cc + "4#" + mail_bcc + "5#"+ mail_from + "6#" + mail_password);
        // try {
            message.setFrom(new InternetAddress(mail_from)); // Mail from

            InternetAddress[] sendTo = InternetAddress.parse(mail_to);
            message.setRecipients(MimeMessage.RecipientType.TO, sendTo); // Mail To

            if (null != mail_cc || mail_cc.equals("")) {
                InternetAddress[] carbonCopy = InternetAddress.parse(mail_cc);
                message.setRecipients(MimeMessage.RecipientType.CC, carbonCopy); // CC
            }

            if (null != mail_bcc || mail_bcc.equals("")) {
                InternetAddress[] carbonCopy2 = InternetAddress.parse(mail_bcc);
                message.setRecipients(MimeMessage.RecipientType.BCC, carbonCopy2); // BCC
            }

            message.setContent(mail_content, "text/html; charset=UTF-8"); // content
            message.setSubject(mail_subject); // subject 主题
            ts.connect(mail_from, mail_password); // content 内容
            ts.send(message);
            ts.close();
        //     return "Send Mail Successfully";
        // } catch (MessagingException e) {
        //     e.printStackTrace();
        //     return "Send Mail Failed";
        // }
    }

    public String sendMailImpl2(String mail_to, String mail_content, String mail_subject, String mail_cc)
            throws MessagingException {

        String mail_from = "";
        String mail_password = "";

        String smtp = "smtp.office365.com";
        String port = "587";
        Properties prop = new Properties();
        // prop.setProperty("spring.mail.host", "smtp.office365.com"); // 设置邮件服务器
        prop.setProperty("mail.host", smtp);
        // prop.setProperty("spring.mail.protocol", "smtp"); // 邮件发送协议
        prop.setProperty("mail.transport.protocol", "smtp");
        // prop.setProperty("spring.mail.properites.mail.smtp.auth", "true"); //
        // 需要验证用户名和密码
        prop.setProperty("mail.smtp.auth", "true");
        // prop.setProperty("spring.mail.properites.mail.smtp.starttls.enable", "true");
        prop.setProperty("mail.smtp.starttls.enable", "true");
        // prop.setProperty("spring.mail.port", "587");
        prop.setProperty("mail.smtp.port", port);
        prop.setProperty("mail.imap.partialfetch", "false");

        // 创建定义整个邮件程序所需的环境信息的 Session 对象
        // Session session = Session.getInstance(prop);
        Session session = Session.getDefaultInstance(prop, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                // 发件人邮箱用户名，授权码
                return new PasswordAuthentication(mail_from, mail_password);
            }
        });

        // 开启 Session 的 debug 模式，这样就可以查看程序发送 Email 的运行状态
        session.setDebug(true);

        // 通过 session 得到 transport 对象
        Transport ts = session.getTransport();

        // 创建邮件，写邮件
        MimeMessage message = new MimeMessage(session);
        try {
            message.setFrom(new InternetAddress(mail_from));
            InternetAddress[] sendTo = InternetAddress.parse(mail_to);
            message.setRecipients(MimeMessage.RecipientType.TO, sendTo);
            if (null != mail_cc || mail_cc.equals("")) {
                InternetAddress[] carbonCopy = InternetAddress.parse(mail_cc);
                message.setRecipients(MimeMessage.RecipientType.CC, carbonCopy);
            }
            message.setText(mail_content);
            message.setSubject(mail_subject);
            ts.connect(mail_from, mail_password);
            ts.send(message);
            ts.close();
            return "Send Mail Successfully";
        } catch (MessagingException e) {
            e.printStackTrace();
            return "Send Mail Failed";
        }
    }

    public void sendMailImpl3(String mail_destination, String mail_to, String mail_content, String mail_subject) throws MessagingException {

        Destination vDestination = DestinationAccessor.getDestination(mail_destination);
        String mail_password = vDestination.get("mail.password").get().toString();
        String mail_from = vDestination.get("mail.smtp.from").get().toString();
        String host = vDestination.get("mail.smtp.host").get().toString();
        String port = vDestination.get("mail.smtp.port").get().toString();
        String auth = vDestination.get("mail.smtp.auth").get().toString();
        String enable = vDestination.get("mail.smtp.starttls.enable").get().toString();
        String protocol = vDestination.get("mail.transport.protocol").get().toString();

        Properties prop = new Properties();
        prop.setProperty("mail.host", host);
        prop.setProperty("mail.transport.protocol", protocol);
        // 需要验证用户名和密码
        prop.setProperty("mail.smtp.auth", auth);
        prop.setProperty("mail.smtp.starttls.enable", enable);
        prop.setProperty("mail.smtp.port", port);
        prop.setProperty("mail.imap.partialfetch", "false");

        // 创建定义整个邮件程序所需的环境信息的 Session 对象
        // Session session = Session.getInstance(prop);
        Session session = Session.getDefaultInstance(prop, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                // 发件人邮箱用户名，授权码
                return new PasswordAuthentication(mail_from, mail_password);
            }
        });

        // 开启 Session 的 debug 模式，这样就可以查看程序发送 Email 的运行状态
        session.setDebug(true);

        // 通过 session 得到 transport 对象
        Transport ts = session.getTransport();

        // 创建邮件，写邮件
        MimeMessage message = new MimeMessage(session);

            message.setFrom(new InternetAddress(mail_from)); // Mail from

            InternetAddress[] sendTo = InternetAddress.parse(mail_to);
            message.setRecipients(MimeMessage.RecipientType.TO, sendTo); // Mail To
        
            message.setText(mail_content, "UTF-8","html"); // content
            message.setSubject(mail_subject); // subject 主题
            ts.connect(mail_from, mail_password); // content 内容
            ts.send(message);
            ts.close();
        
    }
}



    