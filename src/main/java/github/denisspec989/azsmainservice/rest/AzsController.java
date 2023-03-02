package github.denisspec989.azsmainservice.rest;

import github.denisspec989.azsmainservice.models.AzsDto;
import github.denisspec989.azsmainservice.service.AzsMainService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/azses")
@RequiredArgsConstructor
public class AzsController {
    private final AzsMainService azsMainService;
    @GetMapping("/list")
    public List<AzsDto> getFullAzsList(){
        return azsMainService.getFullListOfAzses();
    }
    @GetMapping("/azs")
    public AzsDto getAzsDetail(@RequestParam("azsId")String azsId){
        return azsMainService.getAzsDetail(azsId);
    }
    @PostMapping("/load")
    public ResponseEntity manuallyLoadDataFromFileHandlerService(){
        azsMainService.scheduledGetNewAises();
        return new ResponseEntity(HttpStatus.OK);
    }
}
