package nexadigital.finexa.FinexaController;

import nexadigital.finexa.FinexaDTO.UsuarioRespostaDTO;
import nexadigital.finexa.FinexaDTO.UsuarioCadastroDTO;
import nexadigital.finexa.FinexaService.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping("/usuarios")
    public UsuarioRespostaDTO salvar(@Valid @RequestBody UsuarioCadastroDTO usuario) {
        return usuarioService.salvar(usuario);
    }
}