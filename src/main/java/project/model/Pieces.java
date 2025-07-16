package project.model;

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

    @Column(columnDefinition = "VARCHAR(10) default 'M107'",nullable = false)
    private String Usine;


    @Column(columnDefinition = "VARCHAR(10) default 'G200'",nullable = false)
    private String Magasin;

    @Column(columnDefinition ="VARCHAR(20)",nullable = false,unique = true )
    private String Article;

    @Column(columnDefinition = "VARCHAR(20)",nullable = false)
    private String Emplacement;

    @Column(columnDefinition = "INT",nullable = false)
    private Long Stock;

    @Column(columnDefinition = "TEXT")
    private String Description;

    @Column(columnDefinition = "VARCHAR(20)")
    private String Unite_Mesure;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Pieces pieces = (Pieces) o;
        return Objects.equals(Article, pieces.Article);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(Article);
    }
}
