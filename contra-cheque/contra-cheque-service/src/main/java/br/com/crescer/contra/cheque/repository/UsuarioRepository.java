
package br.com.crescer.contra.cheque.repository;

import br.com.crescer.contra.cheque.entity.Usuario;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author matheus.schmitz
 */
public interface UsuarioRepository extends CrudRepository<Usuario, Long> {

    Usuario findByEmail(String email);
}
