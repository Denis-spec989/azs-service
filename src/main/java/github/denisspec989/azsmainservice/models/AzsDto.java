package github.denisspec989.azsmainservice.models;

import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AzsDto {
    private String      azsId_company;
    private Double      latitude;
    private Double      longitude;
    private String      telephone;
    private String      azsName;
    private String      azsAddress;
}
