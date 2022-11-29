
package com.portfolio.mad.Controller;

import com.portfolio.mad.Dto.dtoAcerca;
import com.portfolio.mad.Entity.Acerca;
import com.portfolio.mad.Security.Controller.Mensaje;
import com.portfolio.mad.Service.SAcerca;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/acerca")
@CrossOrigin(origins ={"https://frontendmad90.web.app/", "http://localhost:4200/"})

public class CAcerca {
    @Autowired
    SAcerca sAcerca;
    
    @GetMapping("/lista")
    public ResponseEntity<List<Acerca>> list(){
        List<Acerca> list = sAcerca.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }
    @GetMapping("/detail/{id}")
    public ResponseEntity<Acerca> getById(@PathVariable("id")int id){
        if(!sAcerca.existsById(id)){
            return new ResponseEntity(new Mensaje("No Existe el ID"), HttpStatus.BAD_REQUEST);
        }
        
        Acerca acerca = sAcerca.getOne(id).get();
        return new ResponseEntity(acerca, HttpStatus.OK);
    }
    
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id){
        if(!sAcerca.existsById(id)){
            return new ResponseEntity(new Mensaje("No Existe el ID"), HttpStatus.NOT_FOUND);
        }
        sAcerca.delete(id);
        return new ResponseEntity(new Mensaje("Acerca Eliminada"), HttpStatus.OK);
    }
    
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody dtoAcerca dtoacerca){
        if(StringUtils.isBlank(dtoacerca.getTitulacionA())){
            return new ResponseEntity(new Mensaje("El Titulo es Obligatorio"), HttpStatus.BAD_REQUEST);
        }
        if(sAcerca.existsByTitulacionA(dtoacerca.getTitulacionA())){
            return new ResponseEntity(new Mensaje("El Titulo Seleccionado Ya Existe"), HttpStatus.BAD_REQUEST);
        }
        
        Acerca acerca = new Acerca(
                dtoacerca.getTitulacionA(), dtoacerca.getDescripcionA()
            );
        sAcerca.save(acerca);
        return new ResponseEntity(new Mensaje("Acerca Creada Correctamente"), HttpStatus.OK);
                
    }
    
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody dtoAcerca dtoacerca){
        if(!sAcerca.existsById(id)){
            return new ResponseEntity(new Mensaje("No Existe el ID"), HttpStatus.NOT_FOUND);
        }
        if(sAcerca.existsByTitulacionA(dtoacerca.getTitulacionA()) && sAcerca.getByTitulacionA(dtoacerca.getTitulacionA()).get().getId() != id){
            return new ResponseEntity(new Mensaje("El Titulo Seleccionado Ya Existe"), HttpStatus.BAD_REQUEST);
        }
        if(StringUtils.isBlank(dtoacerca.getTitulacionA())){
            return new ResponseEntity(new Mensaje("El Campo No Puede Estar Vacio"), HttpStatus.BAD_REQUEST);
        }
        
        Acerca acerca = sAcerca.getOne(id).get();
        
        acerca.setTitulacionA(dtoacerca.getTitulacionA());
        acerca.setDescripcionA(dtoacerca.getDescripcionA());
        
        sAcerca.save(acerca);
        
        return new ResponseEntity(new Mensaje("El Acerca de Fue Actualizado"), HttpStatus.OK);
    }
}

