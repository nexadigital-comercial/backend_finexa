
package nexadigital.finexa.FinexaDTO;

import java.time.LocalDateTime;
import java.util.Map;

public record ErroRespostaDTO(
        LocalDateTime timestamp,
        int status,
        String code,
        String message,
        Map<String, String> fieldErrors
) {
}
