
package com.portfolio.mad.Repository;

import com.portfolio.mad.Entity.Acerca;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RAcerca extends JpaRepository<Acerca, Integer>{
    public Optional<Acerca> findByTitulacionA(String titulacionA);
    public boolean existsByTitulacionA(String titulacionA);
   
}
