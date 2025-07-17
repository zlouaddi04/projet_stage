package project.Controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.Service.PiecesService;
import project.Util.APIResponse;
import project.model.Pieces;

import java.util.List;

@RestController
@RequestMapping("/Pieces")
@AllArgsConstructor
public class PiecesController {
    final PiecesService piecesService;


    //GETMETHODS
    @GetMapping("/getall")
    public List<Pieces> GetPieces(){
        return piecesService.GetPieces();
    }



    //POSTMETHODS
    @PostMapping("/add")
    public ResponseEntity<APIResponse<Pieces>> AddPiece(@RequestBody Pieces p){
        return piecesService.AddPiece(p);
    }

    //PUTMETHODS
    @PutMapping("/UpdateStock/{Ref}/{stock}")
    public ResponseEntity<APIResponse<Pieces>> UpdateStock(
            @PathVariable String Ref,
            @PathVariable Long stock
    ){
        return piecesService.ModifierStock(Ref,stock) ;
    }

}
