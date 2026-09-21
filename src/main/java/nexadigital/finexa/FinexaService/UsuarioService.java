package nexadigital.finexa.FinexaService;

import nexadigital.finexa.FinexaDTO.UsuarioCadastroDTO;
import nexadigital.finexa.FinexaDTO.UsuarioRespostaDTO;
import nexadigital.finexa.FinexaEntity.UsuarioEntity;
import nexadigital.finexa.FinexaRepository.UsuarioRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public UsuarioService(UsuarioRepository usuarioRepository,
                          PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UsuarioRespostaDTO salvar(UsuarioCadastroDTO usuarioDTO) {

        UsuarioEntity usuario = new UsuarioEntity();

        usuario.setNome(usuarioDTO.getNome());
        usuario.setEmail(usuarioDTO.getEmail());
        usuario.setSenha(passwordEncoder.encode(usuarioDTO.getSenha()));

        UsuarioEntity usuarioSalvo = usuarioRepository.save(usuario);

        UsuarioRespostaDTO resposta = new UsuarioRespostaDTO();

        resposta.setId(usuarioSalvo.getId());
        resposta.setNome(usuarioSalvo.getNome());
        resposta.setEmail(usuarioSalvo.getEmail());

        return resposta;
    }
}