package project.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import project.model.Pieces;

import java.util.Optional;

@Repository
@Service
public interface PiecesRepository extends JpaRepository<Pieces,Long> {

    Optional<Pieces> findByArticle(String Article);

}
