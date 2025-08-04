package project.Service;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import project.Repository.PiecesRepository;
import project.Util.APIResponse;
import project.model.Pieces;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PiecesService {
    final PiecesRepository piecesRepository;

    public ResponseEntity<APIResponse<Pieces>> SaveEntity(Pieces P){
        Pieces savedPiece;
        try {
            savedPiece=piecesRepository.save(P);
        } catch (Exception e) {
            System.out.print(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new APIResponse<>("Echec operation"));
        }
        return ResponseEntity.status(HttpStatus.OK)
                .body(new APIResponse<>(savedPiece));

    }

    //GETMETHODS
    public List<Pieces> GetPieces(){
        return piecesRepository.findAll();
    }

    public ResponseEntity<APIResponse<List<Pieces>>> GetPiecesContaining(String str){
        List<Pieces> result=GetPieces().stream().filter(p->p.getDescription().toLowerCase().contains(str.toLowerCase())).toList();
        if(result.isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new APIResponse<>("No matching part"));
        return ResponseEntity.ok().body(new APIResponse<>(result));
    }


    public ResponseEntity<APIResponse<List<Pieces>>> GetByArticleRef(String str){
        List<Pieces> result=GetPieces().stream().filter(p->p.getArticle().toLowerCase().contains(str.toLowerCase())).toList();
        if(result.isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new APIResponse<>("No matching part"));
        return ResponseEntity.ok().body(new APIResponse<>(result));
    }


    //POSTMETHODS
    public ResponseEntity<APIResponse<Pieces>> AddPiece(Pieces p){
        if (p.getDescription()==null || p.getArticle()==null || p.getStock()==null||p.getEmplacement()==null)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new APIResponse<>("Veuillez remplir tous les champs"));

        if (p.getStock()<0)
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body(new APIResponse<>("Veuillez entrez une valeur de stock positive"));



        List<Pieces> Inventory=GetPieces();
        for(Pieces piece:Inventory){
            if (p.equals(piece)){
               return ResponseEntity.status(HttpStatus.CONFLICT)
                       .body(new APIResponse<>("une piece avec cette reference existe deja"));
            }
        }
       return SaveEntity(p);
    }

    //PUTMETHODS
    public ResponseEntity<APIResponse<Pieces>> ModifierStock(String REF,Long newstock){
        Optional<Pieces> Optionalp=piecesRepository.findByArticle(REF);
        if (Optionalp.isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new APIResponse<>("Piece introuvable"));
        Pieces p= Optionalp.get();
        p.setStock(newstock);
        return SaveEntity(p);
    }


    public ResponseEntity<APIResponse<Pieces>> ModifierEmplacement(String REF,String newEmplacement){
        Optional<Pieces> optionalPiece=piecesRepository.findByArticle(REF);
        if (optionalPiece.isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new APIResponse<>("Piece Introuvable"));
        Pieces p=optionalPiece.get();
        p.setEmplacement(newEmplacement);
        return SaveEntity(p);

    }

}
