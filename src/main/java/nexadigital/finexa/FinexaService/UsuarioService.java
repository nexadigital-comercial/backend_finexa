package nexadigital.finexa.FinexaService;

import nexadigital.finexa.FinexaEntity.UsuarioEntity;
import nexadigital.finexa.FinexaRepository.UsuarioRepository;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public UsuarioEntity salvar(UsuarioEntity usuario) {
        return usuarioRepository.save(usuario);
    }
}