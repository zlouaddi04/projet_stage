package project.Controller;

import lombok.AllArgsConstructor;
import org.aspectj.weaver.ast.Literal;
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

    @GetMapping("/getbyname/{str}")
    public ResponseEntity<APIResponse<List<Pieces>>> GetBySubString(@PathVariable String str){
        return piecesService.GetPiecesContaining(str);
    }

    @GetMapping("/getbyRef/{str}")
    public ResponseEntity<APIResponse<List<Pieces>>> GetByRef(@PathVariable String str){
        return piecesService.GetByArticleRef(str);
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
