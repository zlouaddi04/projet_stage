package project.Controller;


import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.Service.EmailService;
import project.Util.Email;

@RestController
@RequestMapping("/Email")
@AllArgsConstructor
@CrossOrigin(origins = {"http://localhost:3000","http://127.0.0.1:3000"})
public class EmailController {

    final EmailService emailService;

    //POSTMETHODS
    @PostMapping("/send")
    public ResponseEntity<String> sendEmail(@RequestBody Email email){
        return emailService.SendEmail(email);
    }
}
