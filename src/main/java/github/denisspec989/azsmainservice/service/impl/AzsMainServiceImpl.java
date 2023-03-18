package github.denisspec989.azsmainservice.service.impl;

import github.denisspec989.azsmainservice.domain.Azs;
import github.denisspec989.azsmainservice.models.AzsDto;
import github.denisspec989.azsmainservice.models.PetrolStationDto;
import github.denisspec989.azsmainservice.repository.feign.FileServiceRepository;
import github.denisspec989.azsmainservice.repository.jpa.AzsRepository;
import github.denisspec989.azsmainservice.service.AzsMainService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class AzsMainServiceImpl implements AzsMainService {
    private final AzsRepository azsRepository;
    private final FileServiceRepository fileServiceRepository;

    List<Azs> fromPetrolStationDtoListToAzsEntityList(List<PetrolStationDto> petrolStationDtoList){
        List<Azs> azsList = new ArrayList<>();
        for(PetrolStationDto petrolStationDto:petrolStationDtoList ){
            Azs azs = new Azs();
            azs.setAzsCompanyId(petrolStationDto.getAzsId());
            azs.setLatitude(petrolStationDto.getLatitude());
            azs.setLongitude(petrolStationDto.getLongitude());
            azs.setTelephone(petrolStationDto.getTelephone()!=null? petrolStationDto.getTelephone().equals("")? null: petrolStationDto.getTelephone() : null);
            azs.setAzsName(petrolStationDto.getAzsName());
            azs.setAzsAddress(petrolStationDto.getAzsAddress());
            azsList.add(azs);
        }
        return azsList;
    }
    List<AzsDto>  fromAzsListToAzsDtoList(List<Azs> azsList){
        List<AzsDto> azsDtoList = new ArrayList<>();
        for(Azs azs:azsList){
            AzsDto azsDto = new AzsDto();
            azsDto.setAzsId_company(azs.getAzsCompanyId());
            azsDto.setLatitude(azs.getLatitude());
            azsDto.setLongitude(azs.getLongitude());
            azsDto.setTelephone(azs.getTelephone());
            azsDto.setAzsName(azs.getAzsName());
            azsDto.setAzsAddress(azs.getAzsAddress());
            azsDtoList.add(azsDto);
        }
        return azsDtoList;
    }
    AzsDto fromAzsToAzsDto(Azs azs){
        AzsDto azsDto = new AzsDto();
        azsDto.setAzsId_company(azs.getAzsCompanyId());
        azsDto.setLatitude(azs.getLatitude());
        azsDto.setLongitude(azs.getLongitude());
        azsDto.setTelephone(azs.getTelephone());
        azsDto.setAzsName(azs.getAzsName());
        azsDto.setAzsAddress(azs.getAzsAddress());
        return azsDto;
    }
    @Override
    @Scheduled(cron = "0 0 4 * * *")
    @Transactional
    public void scheduledGetNewAises() {
        System.out.println("start scheduling");
        ResponseEntity<List<PetrolStationDto>> responseJson;
        ResponseEntity<List<PetrolStationDto>> responseXML;
        do {
           responseJson = fileServiceRepository.getJsonData("Azs_with_prices_and_services");
           responseXML=fileServiceRepository.getXmlData("Azs_with_prices_and_services");
        } while (!(responseJson.getStatusCode().value()==200&&responseXML.getStatusCode().value()==200));
        List<Azs> savingList=fromPetrolStationDtoListToAzsEntityList(responseJson.getBody());
        List<Azs> xmlList=fromPetrolStationDtoListToAzsEntityList(responseXML.getBody());
        for(Azs azs:xmlList){
            if(savingList.contains(azs)){
                continue;
            }else {
                savingList.add(azs);
            }
        }
        azsRepository.saveAll(savingList);
    }

    @Override
    @Transactional
    public List<AzsDto> getFullListOfAzses() {
        return fromAzsListToAzsDtoList(azsRepository.findAll());
    }

    @Override
    @Transactional
    public AzsDto getAzsDetail(String azsId_company) {
        return fromAzsToAzsDto(azsRepository.findByAzsCompanyId(azsId_company).orElseThrow());
    }

}
