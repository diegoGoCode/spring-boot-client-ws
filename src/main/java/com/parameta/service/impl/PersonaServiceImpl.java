package com.parameta.service.impl;

import com.parameta.dto.EmpleadoDto;
import com.parameta.dto.Response;
import com.parameta.client.SoapClient;
import com.parameta.service.interf.PersonaService;
import com.parameta.soap.Empleado;
import com.parameta.soap.PostEmpleadoRequest;
import com.parameta.utils.EmpleadoUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.util.GregorianCalendar;

@Service
public class PersonaServiceImpl implements PersonaService {

    @Autowired
    private SoapClient soapClient;

    @Override
    public ResponseEntity<?> validateRequestEmpleado(EmpleadoDto empleadoDto) throws DatatypeConfigurationException {
        soapClient.cargar(buildRequestSaveEmpleado(empleadoDto));

        return ResponseEntity.status(HttpStatus.OK).body(
                Response.builder()
                    .tiempoVinculacion(EmpleadoUtils.calculateTime(empleadoDto.getFechaVinculacion()))
                    .edadEmpleado(EmpleadoUtils.calculateTime(empleadoDto.getFechaNacimiento()))
                .build());
    }

    private PostEmpleadoRequest buildRequestSaveEmpleado(EmpleadoDto empleadoDto) throws DatatypeConfigurationException {
        PostEmpleadoRequest request = new PostEmpleadoRequest();
        Empleado empleado = new Empleado();

        empleado.setId(empleadoDto.getId());
        empleado.setNombres(empleadoDto.getNombres());
        empleado.setApellidos(empleadoDto.getApellidos());
        empleado.setTipoDocumento(empleadoDto.getTipoDocumento());
        empleado.setNumeroDocumento(empleadoDto.getNumeroDocumento());

        DatatypeFactory datatypeFactoryNacimiento = DatatypeFactory.newInstance();
        GregorianCalendar gregorianCalendarNacimiento = new GregorianCalendar();
        gregorianCalendarNacimiento.setTime(empleadoDto.getFechaNacimiento());
        XMLGregorianCalendar xmlGregorianCalendarNacimiento = datatypeFactoryNacimiento.newXMLGregorianCalendar(gregorianCalendarNacimiento);

        empleado.setFechaNacimiento(xmlGregorianCalendarNacimiento);

        DatatypeFactory datatypeFactoryVinculacion = DatatypeFactory.newInstance();
        GregorianCalendar gregorianCalendarVinculacion = new GregorianCalendar();
        gregorianCalendarVinculacion.setTime(empleadoDto.getFechaNacimiento());
        XMLGregorianCalendar xmlGregorianCalendarVinculacion = datatypeFactoryVinculacion.newXMLGregorianCalendar(gregorianCalendarVinculacion);

        empleado.setFechaVinculacion(xmlGregorianCalendarVinculacion);
        empleado.setCargo(empleadoDto.getCargo());
        empleado.setSalario(empleadoDto.getSalario());

        request.setEmpleado(empleado);

        return request;
    }
}
