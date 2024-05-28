package nl.bsoft.deeplink.repository;

import nl.bsoft.deeplink.database.DeeplinkDto;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DeeplinkDtoRepository extends PagingAndSortingRepository<DeeplinkDto, Long>,
        CrudRepository<DeeplinkDto, Long>,
        JpaSpecificationExecutor<DeeplinkDto> {

    Optional<DeeplinkDto> findByIdentificatie(final String identificatie);

    Optional<DeeplinkDto> findByMd5hash(final String md5hash);
}
