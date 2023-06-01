package com.parameta.controller;

import com.parameta.dto.EmpleadoDto;
import com.parameta.service.interf.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.xml.datatype.DatatypeConfigurationException;

@RestController
@RequestMapping("/api/v1/empleado")
@CrossOrigin(origins = "*")
public class EmpleadoController {

    @Autowired
    private PersonaService personaService;

    @PostMapping
    public ResponseEntity<?> validatePerson(@RequestBody @Valid EmpleadoDto empleadoDto) throws DatatypeConfigurationException {
        return personaService.validateRequestEmpleado(empleadoDto);
    }
}
