package project.Service;

import lombok.AllArgsConstructor;
import org.springframework.dao.TransientDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import project.Repository.PiecesRepository;
import project.Repository.TransactionRepository;
import project.Repository.UserRepository;
import project.Util.APIResponse;
import project.model.Pieces;
import project.model.Transaction;
import project.model.User;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor

public class TransactionService {
    final TransactionRepository transactionRepository;
    final UserRepository userRepository;
    final PiecesRepository piecesRepository;
    final PiecesService piecesService;

    public ResponseEntity<APIResponse<Transaction>> SaveEntity(Transaction T){
        Transaction savedTransaction;
        try {
            savedTransaction=transactionRepository.save(T);
        } catch (Exception e) {
            System.out.print(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new APIResponse<>("Echec operation"));
        }
        return ResponseEntity.status(HttpStatus.OK)
                .body(new APIResponse<>(savedTransaction));

    }


    //GETMETHODS
    public List<Transaction> GetTransactions(){
        return transactionRepository.findAll();
    }

    //POSTMETHODS
    public ResponseEntity<APIResponse<Transaction>> AddTransaction(Transaction T){
        if (T.getCodePiece()==null||T.getUser()==null||T.getQuantity()==null)
            return ResponseEntity.badRequest().body(new APIResponse<>("please fill all fields"));

        if (userRepository.findByUsername(T.getUser()).isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new APIResponse<>("User not found"));

        if (piecesRepository.findByArticle(T.getCodePiece()).isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new APIResponse<>("Item not found"));

        Pieces piece=piecesRepository.findByArticle(T.getCodePiece()).get();

        if (T.getQuantity()>piece.getStock())
            return ResponseEntity.badRequest().body(new APIResponse<>("Quantity exceeds current stock"));

        piecesService.ModifierStock(T.getCodePiece(),piece.getStock()-T.getQuantity());
        T.setConfirmtaionDate(LocalDateTime.now());
        return SaveEntity(T);
    }
}

