package github.denisspec989.azsmainservice.service;

import github.denisspec989.azsmainservice.models.AzsDto;

import java.util.List;

public interface AzsMainService {
    void scheduledGetNewAises();
    List<AzsDto> getFullListOfAzses();
    AzsDto getAzsDetail(String azsId_company);
}
