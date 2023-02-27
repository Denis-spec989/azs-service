package github.denisspec989.azsmainservice.rest;

import github.denisspec989.azsmainservice.models.PetrolStationDto;
import github.denisspec989.azsmainservice.repository.feign.FileServiceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/azs")
@RequiredArgsConstructor
public class AzsController {
    private final FileServiceRepository fileServiceRepository;
    @PostMapping(value = "/json")
    List<PetrolStationDto> getJsonData(@RequestParam("fileName") String fileName){
        return fileServiceRepository.getJsonData(fileName);
    }
}
