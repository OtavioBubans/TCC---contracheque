
package br.com.crescer.contra.cheque.service;

import br.com.crescer.contra.cheque.entity.Colaborador;
import br.com.crescer.contra.cheque.repository.ColaboradorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author otavio.bubans
 */
@Service
public class ColaboradorService {
    
    @Autowired
    ColaboradorRepository colaboradorRepository; 
    
    public Colaborador findById(Long id){
        return colaboradorRepository.findOne(id);
    }
    
}
