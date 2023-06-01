package com.parameta.service.interf;

import com.parameta.dto.EmpleadoDto;
import org.springframework.http.ResponseEntity;
import javax.xml.datatype.DatatypeConfigurationException;

public interface PersonaService {
    ResponseEntity<?> validateRequestEmpleado(EmpleadoDto empleadoDto) throws DatatypeConfigurationException;
}
