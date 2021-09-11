package com.example.holamundo.web;

import com.example.holamundo.domain.Persona;
import com.example.holamundo.service.PersonaServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.ArrayList;

@Controller
public class Controlador {

    @Autowired
    private PersonaServiceImpl personaService;

    @GetMapping("/")
    public String inicio(Model model){
        ArrayList<Persona> personas= (ArrayList<Persona>) personaService.listarPersonas();
        model.addAttribute("personas",personas);
        Double saldoTotal=0D;
        for(Persona p: personas){
            saldoTotal+= p.getSaldo();
        }
        model.addAttribute("saldoTotal",saldoTotal);
        model.addAttribute("totalClientes",personas.size());
        return "index";
    }

    @GetMapping("/agregar")
    public String agregar(Persona persona){
        //spring busca un objeto de tipo Persona en la fabrica de spring
        // si no lo encuentra entonces va a crear un nuevo objeto y lo va a
        // inyectar de manera automatica en el metodo
        return "modificar";
    }

    @PostMapping("/guardar")
    public String guardar(@Valid Persona persona, Errors errores){
        if(errores.hasErrors()){
            return "modificar";
        }
        personaService.guardar(persona);
        return "redirect:/";
    }

    // spring buscar el objeto si ya existe lo va a
    // inyectar y ademas el parametro que estamos recibiendo lo relaciona con el objeto de tipo persona
    // recibimos la variable modelo para compartirla con la siguiente peticion
    //idPersona debe ser igual al de la clase persona
    @GetMapping("/editar/{idPersona}")
    public String editar(Persona persona, Model model){
        persona = personaService.encontrarPersona(persona);
        model.addAttribute("persona", persona);
        return "modificar";
    }

    @GetMapping("/eliminar")
    public String eliminar(Persona persona){
        personaService.eliminar(persona);
        return "redirect:/";
    }
}
