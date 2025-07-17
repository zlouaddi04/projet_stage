package project.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.autoconfigure.web.WebProperties;

import java.util.Objects;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Pieces {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "BIGINT")
    private Long id;

    @Column(columnDefinition = "VARCHAR(10) default 'M107'")
    private String Usine;


    @Column(columnDefinition = "VARCHAR(10) default 'G200'")
    private String Magasin;

    @Column(columnDefinition ="VARCHAR(20)",nullable = false,unique = true )
    @JsonProperty("reference")
    private String article;

    @Column(columnDefinition = "VARCHAR(20)",nullable = false)
    @JsonProperty("emplacement")
    private String Emplacement;

    @Column(columnDefinition = "INT",nullable = false)
    @JsonProperty("stock")
    private Long Stock;

    @Column(columnDefinition = "TEXT",nullable = false)
    @JsonProperty("desc")
    private String Description;

    @Column(columnDefinition = "VARCHAR(20)")
    @JsonProperty("unite")
    private String Unite_Mesure;

    @PrePersist
    public void prePersist() {
        if (this.Magasin == null) this.Magasin = "G200";
        if (this.Usine == null) this.Usine = "M107";
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Pieces pieces = (Pieces) o;
        return Objects.equals(article, pieces.article);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(article);
    }
}
