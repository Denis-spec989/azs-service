package github.denisspec989.azsmainservice.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PriceModelDto {
    private Float fuelPrice;
    private String fuelName;
}
