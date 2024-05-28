package nl.bsoft.deeplink.controller;

import lombok.extern.slf4j.Slf4j;
import nl.bsoft.deeplink.generated.api.ToestandenApi;
import nl.bsoft.deeplink.generated.model.DeeplinkToestand;
import nl.bsoft.deeplink.model.Deeplink;
import nl.bsoft.deeplink.service.DeeplinkService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

@Slf4j
@Controller
public class DeeplinkController implements ToestandenApi {

    private final DeeplinkService deeplinkService;

    public DeeplinkController(DeeplinkService deeplinkService) {
        this.deeplinkService = deeplinkService;
    }

    @Override
    public ResponseEntity<DeeplinkToestand> _toestandenIdentificatieGet(String identificatie) {
        Deeplink deeplink = deeplinkService.findDeeplink(identificatie);
        DeeplinkToestand deeplinkToestand = new DeeplinkToestand();
        if (deeplink != null) {
            log.info("Retrieved toestand for identification: {}", deeplink);
            deeplinkToestand.setVersion(deeplink.getVersion());
            deeplinkToestand.setToestand(deeplink.getContent());
            return ResponseEntity.ok(deeplinkToestand);
        } else {
            return (ResponseEntity<DeeplinkToestand>) ResponseEntity.status(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<String> _vastleggen(DeeplinkToestand deeplinkToestand) {
        Deeplink deeplink = new Deeplink();
        deeplink.setContent(deeplinkToestand.getToestand());
        deeplink.setVersion(deeplinkToestand.getVersion());


        String identificatie = deeplinkService.addDeeplink(deeplink);
        log.info("Saved toestand with id: {}", identificatie);

        if (identificatie != null) {
            return ResponseEntity.ok(identificatie);
        } else {
            return (ResponseEntity<String>) ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE);
        }
    }
}
