package nexadigital.finexa.FinexaController;

import nexadigital.finexa.FinexaEntity.UsuarioEntity;
import nexadigital.finexa.FinexaService.UsuarioService;
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
    public UsuarioEntity salvar(@RequestBody UsuarioEntity usuario) {
        return usuarioService.salvar(usuario);
    }
}