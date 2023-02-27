package github.denisspec989.azsmainservice.service.impl;

import github.denisspec989.azsmainservice.domain.Azs;
import github.denisspec989.azsmainservice.models.PetrolStationDto;
import github.denisspec989.azsmainservice.repository.feign.FileServiceRepository;
import github.denisspec989.azsmainservice.repository.jpa.AzsRepository;
import github.denisspec989.azsmainservice.service.AzsMainService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AzsMainServiceImpl implements AzsMainService {
    private final AzsRepository azsRepository;
    private final FileServiceRepository fileServiceRepository;

    List<Azs> fromPetrolStationDtoListToAzsEntityList(List<PetrolStationDto> petrolStationDtoList){
        List<Azs> azsList = new ArrayList<>();
        for(PetrolStationDto petrolStationDto:petrolStationDtoList ){
            Azs azs = new Azs();
            azs.setAzsId_company(petrolStationDto.getAzsId());
            azs.setLatitude(petrolStationDto.getLatitude());
            azs.setLongitude(petrolStationDto.getLongitude());
            azs.setTelephone(petrolStationDto.getTelephone());
            azs.setAzsName(petrolStationDto.getAzsName());
            azs.setAzsAddress(petrolStationDto.getAzsAddress());
            azsList.add(azs);
        }
        return azsList;
    }
    @Override
    @Scheduled(cron = "0 0 4 * * *")
    @Transactional
    public void scheduledGetNewAises() {
        System.out.println("start scheduling");
        azsRepository.saveAll(fromPetrolStationDtoListToAzsEntityList(fileServiceRepository.getJsonData("Azs_with_prices_and_services")));
        System.out.println("end saving");
    }
}
