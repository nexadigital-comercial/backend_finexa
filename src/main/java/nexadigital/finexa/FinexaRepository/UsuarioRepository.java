package nexadigital.finexa.FinexaRepository;

import nexadigital.finexa.FinexaEntity.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Long> {
}