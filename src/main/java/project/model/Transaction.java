package project.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.autoconfigure.web.WebProperties;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Objects;

@Entity
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Transaction {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @JsonProperty("user")
    @Column(nullable = false)
    private String user;

    @JsonProperty("reference")
    @Column(nullable = false)
    private String CodePiece;

    @JsonProperty("quantity")
    @Column(nullable = false)
    private Long Quantity;

    @Column(nullable = false)
    private LocalDateTime confirmtaionDate;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Transaction that = (Transaction) o;
        return Objects.equals(Id, that.Id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(Id);
    }
}
