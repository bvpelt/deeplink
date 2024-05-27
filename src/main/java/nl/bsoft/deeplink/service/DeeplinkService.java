package nl.bsoft.deeplink.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import nl.bsoft.deeplink.database.DeeplinkDto;
import nl.bsoft.deeplink.model.Deeplink;
import nl.bsoft.deeplink.repository.DeeplinkDtoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
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

            UUID identificatie = UUID.randomUUID();
            deeplinkDto.setIdentificatie(identificatie.toString());
            deeplinkDto.setContent(stringToJsonNode(deeplink.getContent()));
            log.info("Save deeplink: {}", deeplinkDto);
            deeplinkDtoRepository.save(deeplinkDto);
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
                deeplink.setContent(jsonNodeToString(optionalDeeplinkDto.get().getContent()));
            }
        } catch (Exception e) {
            log.error("Error converting string to json: {} error {}", deeplink.getContent(), e);
        }
        return deeplink;
    }

    private String jsonNodeToString(JsonNode jsonNode) throws IOException {
        return objectMapper.writeValueAsString(jsonNode);
    }

    private JsonNode stringToJsonNode(String jsonString) throws IOException {
        return objectMapper.readTree(jsonString);
    }
}
