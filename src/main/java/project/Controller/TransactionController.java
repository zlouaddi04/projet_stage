package project.Controller;


import lombok.AllArgsConstructor;
import lombok.Getter;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.Service.TransactionService;
import project.Util.APIResponse;
import project.model.Transaction;

import java.util.List;
import java.util.concurrent.ThreadPoolExecutor;

@RestController
@RequestMapping("/Transaction")
@AllArgsConstructor
@CrossOrigin(origins = {"http://localhost:3000","http://127.0.0.1:3000"})
public class TransactionController {

    final TransactionService transactionService;



    //GETMETHODS
    @GetMapping("/getall")
    public List<Transaction> GetTransactions(){
        return transactionService.GetTransactions();
    }

    //POSTMETHODS
    @PostMapping("/add")
    public ResponseEntity<APIResponse<Transaction>> AddTransaction(@RequestBody Transaction T){
        return transactionService.AddTransaction(T);
    }

}