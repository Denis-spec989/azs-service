package github.denisspec989.azsmainservice.repository.jpa;

import github.denisspec989.azsmainservice.domain.Azs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;
@Repository
public interface AzsRepository extends JpaRepository<Azs, UUID> {
    Optional<Azs> findByAzsCompanyId(String azsCompanyId);
}
