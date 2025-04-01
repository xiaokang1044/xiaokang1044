package customer.test_xb.Service;

public class SendToUser {
    // send mail to user by outlook

    public SendToUser() {
        System.out.println("SendToUser constructor");
    }

    public void sendMail(String mailAddress, String mailContent) {
        System.out.println("Send mail to " + mailAddress + " with content: " + mailContent);
    }

    // send file by mail
    public void sendFile(String mailAddress, String filePath) {
        System.out.println("Send file to " + mailAddress + " with file path: " + filePath);
    }

}