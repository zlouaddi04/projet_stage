package project.Service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import project.Repository.PiecesRepository;
import project.model.Pieces;

import java.util.List;

@Service
@AllArgsConstructor
public class PiecesService {
    final PiecesRepository piecesRepository;

    //GETMETHODS
    public List<Pieces> GetPieces(){
        return piecesRepository.findAll();
    }

}
