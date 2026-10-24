
package nexadigital.finexa.FinexaConfig;

import nexadigital.finexa.FinexaDTO.ErroRespostaDTO;
import nexadigital.finexa.FinexaException.EmailJaCadastradoException;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErroRespostaDTO handleValidationErrors(
            MethodArgumentNotValidException ex) {

        Map<String, String> fieldErrors = new HashMap<>();

        ex.getBindingResult()
                .getFieldErrors()
                .forEach(error ->
                        fieldErrors.putIfAbsent(
                                error.getField(),
                                error.getDefaultMessage()
                        )
                );

        return new ErroRespostaDTO(
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST.value(),
                "VALIDATION_ERROR",
                "Existem campos inválidos.",
                fieldErrors
        );
    }

    @ExceptionHandler(EmailJaCadastradoException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErroRespostaDTO handleEmailJaCadastrado(
            EmailJaCadastradoException ex) {

        return new ErroRespostaDTO(
                LocalDateTime.now(),
                HttpStatus.CONFLICT.value(),
                "EMAIL_ALREADY_EXISTS",
                ex.getMessage(),
                null
        );
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErroRespostaDTO handleDataIntegrityViolation(
            DataIntegrityViolationException ex) {

        return new ErroRespostaDTO(
                LocalDateTime.now(),
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "DATABASE_ERROR",
                "Ocorreu um erro interno ao processar a solicitação.",
                null
        );
    }
}
