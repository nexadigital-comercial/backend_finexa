package nexadigital.finexa.FinexaController;

import org.springframework.security.core.AuthenticationException;
import nexadigital.finexa.FinexaDTO.UsuarioRespostaDTO;
import nexadigital.finexa.FinexaService.UsuarioService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import nexadigital.finexa.FinexaDTO.UsuarioLoginDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UsuarioService usuarioService;
    private final AuthenticationManager authenticationManager;
    private final SecurityContextRepository securityContextRepository;

    public AuthController(
            AuthenticationManager authenticationManager,
            SecurityContextRepository securityContextRepository,
            UsuarioService usuarioService) {

        this.authenticationManager = authenticationManager;
        this.securityContextRepository = securityContextRepository;
        this.usuarioService = usuarioService;
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(
            @Valid @RequestBody UsuarioLoginDTO usuarioDTO,
            HttpServletRequest request,
            HttpServletResponse response) {


        Authentication authentication;

        try {
            authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            usuarioDTO.getEmail(),
                            usuarioDTO.getSenha()
                    )
            );
        } catch (AuthenticationException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of(
                            "erro", "E-mail ou senha inválidos."
                    ));
        }


        SecurityContext context =
                SecurityContextHolder.createEmptyContext();

        context.setAuthentication(authentication);
        SecurityContextHolder.setContext(context);

        securityContextRepository.saveContext(
                context, request, response);

        return ResponseEntity.status(HttpStatus.OK)
                .body(Map.of(
                        "mensagem",
                        "Autenticação realizada com sucesso."
                ));
    }



    @PostMapping("/logout")
    public ResponseEntity<Map<String, String>> logout(
            HttpServletRequest request,
            HttpServletResponse response) {

        SecurityContextHolder.clearContext();

        jakarta.servlet.http.HttpSession session =
                request.getSession(false);

        if (session != null) {
            session.invalidate();
        }

        return ResponseEntity.ok(
                Map.of("mensagem", "Logout realizado com sucesso.")
        );
    }



    @GetMapping("/me")
    public ResponseEntity<UsuarioRespostaDTO> usuarioAutenticado() {

        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        UsuarioRespostaDTO usuario =
                usuarioService.buscarPorEmail(authentication.getName());

        return ResponseEntity.ok(usuario);
    }
}
