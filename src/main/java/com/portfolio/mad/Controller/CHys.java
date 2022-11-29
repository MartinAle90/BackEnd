/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.portfolio.mad.Controller;

import com.portfolio.mad.Dto.dtoHys;
import com.portfolio.mad.Entity.Hys;
import com.portfolio.mad.Security.Controller.Mensaje;
import com.portfolio.mad.Service.SHys;
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
@RequestMapping("/skill")
@CrossOrigin(origins ={"https://frontendmad90.web.app/", "http://localhost:4200/"})


public class CHys {
    @Autowired
    SHys sHys;
    
    @GetMapping("/lista")
    public ResponseEntity<List<Hys>> list(){
        List<Hys> list = sHys.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }
    
    @GetMapping("/detail/{id}")
    public ResponseEntity<Hys> getById(@PathVariable("id") int id){
        if(!sHys.existsById(id))
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        Hys hys = sHys.getOne(id).get();
        return new ResponseEntity(hys, HttpStatus.OK);
    }
    
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id) {
        if (!sHys.existsById(id)) {
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        }
        sHys.delete(id);
        return new ResponseEntity(new Mensaje("skill eliminada"), HttpStatus.OK);
    }

    
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody dtoHys dtohys){      
        if(StringUtils.isBlank(dtohys.getNombre()))
            return new ResponseEntity(new Mensaje("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        if(sHys.existsByNombre(dtohys.getNombre()))
            return new ResponseEntity(new Mensaje("Esa skill ya existe"), HttpStatus.BAD_REQUEST);
        
        Hys hys = new Hys(dtohys.getNombre(), dtohys.getPorcentaje());
        sHys.save(hys);
        
        return new ResponseEntity(new Mensaje("Skill agregada"), HttpStatus.OK);
    }
    
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody dtoHys dtohys){
        //Validamos si existe el ID
        if(!sHys.existsById(id))
            return new ResponseEntity(new Mensaje("El ID no existe"), HttpStatus.BAD_REQUEST);
        //Compara nombre de las skills
        if(sHys.existsByNombre(dtohys.getNombre()) && sHys.getByNombre(dtohys.getNombre()).get().getId() != id)
            return new ResponseEntity(new Mensaje("Esa Skill ya existe"), HttpStatus.BAD_REQUEST);
        //No puede estar vacio
        if(StringUtils.isBlank(dtohys.getNombre()))
            return new ResponseEntity(new Mensaje("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        
        Hys hys = sHys.getOne(id).get();
        hys.setNombre(dtohys.getNombre());
        hys.setPorcentaje((dtohys.getPorcentaje()));
        
        sHys.save(hys);
        return new ResponseEntity(new Mensaje("Skill actualizada"), HttpStatus.OK);
             
    }
}
