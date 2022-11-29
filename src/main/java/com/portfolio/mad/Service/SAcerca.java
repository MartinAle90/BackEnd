
package com.portfolio.mad.Service;

import com.portfolio.mad.Entity.Acerca;
import com.portfolio.mad.Repository.RAcerca;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class SAcerca {
    @Autowired
    RAcerca rAcerca;
    
    public List<Acerca> list(){
        return rAcerca.findAll();
    }
    
    public Optional<Acerca> getOne(int id){
        return rAcerca.findById(id);
    }
    
    public Optional<Acerca> getByTitulacionA(String titulacionA){
        return rAcerca.findByTitulacionA(titulacionA);
    }
    
    public void save(Acerca acerca){
        rAcerca.save(acerca);
    }
    
    public void delete(int id){
        rAcerca.deleteById(id);
    }
    
    public boolean existsById(int id){
        return rAcerca.existsById(id);
    }
    
    public boolean existsByTitulacionA(String titulacionA){
        return rAcerca.existsByTitulacionA(titulacionA);
    }
    
}
