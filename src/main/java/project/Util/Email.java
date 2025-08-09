package project.Util;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.swing.*;

@NoArgsConstructor
@Getter
@Setter
public class Email {
    private String fromEmail;
    private String toEmail;
    private String subject;
    private String Body;

    @JsonCreator
    public Email(@JsonProperty("toEmail") String toEmail,
                @JsonProperty("subject") String subject,
                 @JsonProperty("Body") String Body) {
        this.fromEmail="zakaria.project.java@gmail.com";
        this.toEmail=toEmail;
        this.subject=subject;
        this.Body=Body;
    }

}
