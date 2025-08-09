package project.Service;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import project.Util.Email;

@Service
@AllArgsConstructor
public class EmailService {
    final JavaMailSender mailSender;



    public ResponseEntity<String> SendEmail(Email email){
        SimpleMailMessage msg=new SimpleMailMessage();
        msg.setFrom(email.getFromEmail());
        msg.setTo(email.getToEmail());
        msg.setSubject(email.getSubject());
        msg.setText(email.getBody());
        try{
            mailSender.send(msg);
            return ResponseEntity.status(HttpStatus.OK).body( "Mail Sent Successfully");
        }catch (MailException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something went wrong,"+e.getMessage());
        }
    }
}
