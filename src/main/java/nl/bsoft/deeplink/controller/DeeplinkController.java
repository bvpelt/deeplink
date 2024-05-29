package nl.bsoft.deeplink.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.extern.slf4j.Slf4j;
import nl.bsoft.deeplink.exception.NotFoundException;
import nl.bsoft.deeplink.generated.api.ToestandenApi;
import nl.bsoft.deeplink.generated.model.DeeplinkToestand;
import nl.bsoft.deeplink.generated.model.Problem;
import nl.bsoft.deeplink.model.Deeplink;
import nl.bsoft.deeplink.service.DeeplinkService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class DeeplinkController { //implements ToestandenApi {

    private final DeeplinkService deeplinkService;

    public DeeplinkController(DeeplinkService deeplinkService) {
        this.deeplinkService = deeplinkService;
    }

    /**
     * GET /toestanden/{identificatie}
     *
     * @param identificatie  (required)
     * @return Resultaat van informatieproduct bevraging (status code 200)
     *         or Not found (status code 404)
     *         or Unexpected problem (status code 200)
     */
    @Operation(
            operationId = "toestandenIdentificatieGet",
            tags = { "Deeplink" },
            responses = {
                    @ApiResponse(responseCode = "200", description = "Resultaat van informatieproduct bevraging", content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = DeeplinkToestand.class)),
                            @Content(mediaType = "application/problem+json", schema = @Schema(implementation = DeeplinkToestand.class))
                    }),
                    @ApiResponse(responseCode = "404", description = "Not found", content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = Problem.class)),
                            @Content(mediaType = "application/problem+json", schema = @Schema(implementation = Problem.class))
                    }),
                    @ApiResponse(responseCode = "default", description = "Unexpected problem", content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = Problem.class)),
                            @Content(mediaType = "application/problem+json", schema = @Schema(implementation = Problem.class))
                    })
            },
            security = {
                    @SecurityRequirement(name = "api_key")
            }
    )
    @RequestMapping(
            method = RequestMethod.GET,
            value = "/toestanden/{identificatie}",
            produces = { "application/json", "application/problem+json" }
    )
    public ResponseEntity<?> _toestandenIdentificatieGet(String identificatie) {
        try {
            Deeplink deeplink = deeplinkService.findDeeplink(identificatie);
            DeeplinkToestand deeplinkToestand = null;
            if (deeplink != null) {
                deeplinkToestand = new DeeplinkToestand();
                log.info("Retrieved toestand for identification: {}", deeplink);
                deeplinkToestand.setVersion(deeplink.getVersion());
                deeplinkToestand.setToestand(deeplink.getContent());
                return ResponseEntity.ok(deeplinkToestand);
            } else {
                Problem problem = new Problem();
                problem.setDetail("Not Found");
                problem.setStatus(404);
                problem.setTitle("Get identificatie");
                return new ResponseEntity<DeeplinkToestand>(deeplinkToestand, HttpStatus.NOT_FOUND);
            }
        } catch (NotFoundException e) {
            Problem problem = new Problem();
            problem.setTitle("Not found");
            problem.setDetail(e.getMessage());
            problem.setStatus(404);
            problem.setDetail("The requested toestand with identificatie " + identificatie + " was not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(problem);
        } catch (Exception e) {
            Problem problem = new Problem();
            problem.setTitle("Internal Server Error");
            problem.setDetail(e.getMessage());
            problem.setStatus(500);
            problem.setDetail("An unexpected error occurred: " + e.toString());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(problem);
        }
    }

    /**
     * POST /toestanden : Vastleggen van DSO LV viewer toestand
     * Vastleggen van de toestand van een DSO LV Viewer in de vorm van een deeplink key en de DSO LV toestand
     *
     * @param deeplinkToestand DeeplinkToestand De toestand van de DSO-LV Viewer applicatie  (required)
     * @return Resultaat van informatieproduct bevraging (status code 200)
     *         or Bad request (status code 400)
     *         or Not found (status code 404)
     *         or Not acceptable (status code 406)
     *         or Precondition failed (status code 412)
     *         or Default error sample response (status code 200)
     */
    @Operation(
            operationId = "vastleggen",
            summary = "Vastleggen van DSO LV viewer toestand",
            description = "Vastleggen van de toestand van een DSO LV Viewer in de vorm van een deeplink key en de DSO LV toestand",
            tags = { "Deeplink" },
            responses = {
                    @ApiResponse(responseCode = "200", description = "Resultaat van informatieproduct bevraging", content = {
                            @Content(mediaType = "application/text", schema = @Schema(implementation = String.class)),
                            @Content(mediaType = "application/problem+json", schema = @Schema(implementation = String.class))
                    }),
                    @ApiResponse(responseCode = "400", description = "Bad request", content = {
                            @Content(mediaType = "application/text", schema = @Schema(implementation = Problem.class)),
                            @Content(mediaType = "application/problem+json", schema = @Schema(implementation = Problem.class))
                    }),
                    @ApiResponse(responseCode = "404", description = "Not found", content = {
                            @Content(mediaType = "application/text", schema = @Schema(implementation = Problem.class)),
                            @Content(mediaType = "application/problem+json", schema = @Schema(implementation = Problem.class))
                    }),
                    @ApiResponse(responseCode = "406", description = "Not acceptable", content = {
                            @Content(mediaType = "application/text", schema = @Schema(implementation = Problem.class)),
                            @Content(mediaType = "application/problem+json", schema = @Schema(implementation = Problem.class))
                    }),
                    @ApiResponse(responseCode = "412", description = "Precondition failed", content = {
                            @Content(mediaType = "application/text", schema = @Schema(implementation = Problem.class)),
                            @Content(mediaType = "application/problem+json", schema = @Schema(implementation = Problem.class))
                    }),
                    @ApiResponse(responseCode = "default", description = "Default error sample response", content = {
                            @Content(mediaType = "application/text", schema = @Schema(implementation = Problem.class)),
                            @Content(mediaType = "application/problem+json", schema = @Schema(implementation = Problem.class))
                    })
            },
            security = {
                    @SecurityRequirement(name = "api_key")
            }
    )
    @RequestMapping(
            method = RequestMethod.POST,
            value = "/toestanden",
            produces = { "application/text", "application/problem+json" },
            consumes = { "application/json" }
    )

    public ResponseEntity<?> _vastleggen(DeeplinkToestand deeplinkToestand) {
        Deeplink deeplink = new Deeplink();
        deeplink.setContent(deeplinkToestand.getToestand());
        deeplink.setVersion(deeplinkToestand.getVersion());


        String identificatie = deeplinkService.addDeeplink(deeplink);

        if (identificatie != null) {
            return ResponseEntity.ok(identificatie);
        } else {
            return (ResponseEntity<String>) ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE);
        }
    }
}
