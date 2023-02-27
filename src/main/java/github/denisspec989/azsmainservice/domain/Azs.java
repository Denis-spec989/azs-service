package github.denisspec989.azsmainservice.domain;

import lombok.*;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
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
}
