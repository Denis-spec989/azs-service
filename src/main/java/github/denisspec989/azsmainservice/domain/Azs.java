package github.denisspec989.azsmainservice.domain;

import lombok.*;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "azses")
@Getter
@Setter
@DynamicUpdate
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Azs {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private UUID azs_id;
    private String      azsId_company;
    private Double      latitude;
    private Double      longitude;
    private String      telephone;
    private String      azsName;
    private String      azsAddress;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Azs)) return false;
        Azs azs = (Azs) o;
        azs.setTelephone(azs.getTelephone()!=null?azs.getTelephone().equals("")?null:azs.getTelephone():null);
        this.telephone=getTelephone()!=null?getTelephone().equals("")?null:getTelephone():null;
        return Objects.equals(getAzsId_company(), azs.getAzsId_company()) && Objects.equals(getLatitude(), azs.getLatitude()) && Objects.equals(getLongitude(), azs.getLongitude()) && Objects.equals(getTelephone(), azs.getTelephone()) && Objects.equals(getAzsName(), azs.getAzsName()) && Objects.equals(getAzsAddress(), azs.getAzsAddress());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getAzsId_company(), getLatitude(), getLongitude(), getTelephone(), getAzsName(), getAzsAddress());
    }
}
