package nl.bsoft.deeplink.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import nl.bsoft.deeplink.database.DeeplinkDto;
import nl.bsoft.deeplink.model.Deeplink;
import nl.bsoft.deeplink.repository.DeeplinkDtoRepository;
import nl.bsoft.deeplink.util.MD5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
public class DeeplinkService {
    private final DeeplinkDtoRepository deeplinkDtoRepository;
    @Autowired
    private ObjectMapper objectMapper;
    public DeeplinkService(DeeplinkDtoRepository deeplinkDtoRepository) {
        this.deeplinkDtoRepository = deeplinkDtoRepository;
    }

    public String addDeeplink(final Deeplink deeplink) {
        DeeplinkDto deeplinkDto = null;

        try {
            deeplinkDto = new DeeplinkDto(deeplink);

            String md5hash = MD5Hash.getMD5Hex(deeplink.getContent());
            LocalDateTime now = LocalDateTime.now(ZoneId.systemDefault());
            Optional<DeeplinkDto> optionalDeeplinkDto = deeplinkDtoRepository.findByMd5hash(md5hash);
            if (optionalDeeplinkDto.isPresent()) {
                deeplinkDto = optionalDeeplinkDto.get();
                deeplinkDto.setCreated(now);
                deeplinkDtoRepository.save(deeplinkDto);
                log.info("Deeplink already saved with id: {}", deeplinkDto.getIdentificatie());
            } else {
                UUID identificatie = UUID.randomUUID();
                deeplinkDto.setIdentificatie(identificatie.toString());

                deeplinkDto.setCreated(now);
                deeplinkDto.setMd5hash(md5hash);
                deeplinkDto.setContent(deeplink.getContent());
                log.info("Save deeplink: {}", deeplinkDto);
                deeplinkDtoRepository.save(deeplinkDto);
            }
        } catch (Exception e) {
            log.error("Error converting string to json: {} error {}", deeplink.getContent(), e);
            return null;
        }
        if (deeplinkDto != null) {
            return deeplinkDto.getIdentificatie();
        } else {
            return null;
        }
    }

    public Deeplink findDeeplink(final String identificatie) {
        Deeplink deeplink = null;

        try {
            Optional<DeeplinkDto> optionalDeeplinkDto = deeplinkDtoRepository.findByIdentificatie(identificatie);
            if (optionalDeeplinkDto.isPresent()) {
                log.info("Found deeplink: {}", optionalDeeplinkDto.get());
                deeplink = new Deeplink(optionalDeeplinkDto.get());
                deeplink.setContent(optionalDeeplinkDto.get().getContent());
            }
        } catch (Exception e) {
            log.error("Error converting string to json: {} error {}", deeplink.getContent(), e);
        }
        return deeplink;
    }

}
