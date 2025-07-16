package project.Controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project.Service.PiecesService;
import project.model.Pieces;

import java.util.List;

@RestController
@RequestMapping("/Pieces")
@AllArgsConstructor
public class PiecesController {
    final PiecesService piecesService;

    @GetMapping("/getall")
    public List<Pieces> GetPieces(){
        return piecesService.GetPieces();
    }

}
