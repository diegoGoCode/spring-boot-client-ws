package com.parameta.client;

import com.parameta.soap.PostEmpleadoRequest;
import com.parameta.soap.PostEmpleadoResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.stereotype.Service;
import org.springframework.ws.client.core.WebServiceTemplate;

@Service
public class SoapClient {
    @Autowired
    private Jaxb2Marshaller jaxb2Marshaller;
    private WebServiceTemplate webServiceTemplate;

    public PostEmpleadoResponse cargar(PostEmpleadoRequest request){
        webServiceTemplate = new WebServiceTemplate(jaxb2Marshaller);
        PostEmpleadoResponse postEmpleadoRequest = (PostEmpleadoResponse) webServiceTemplate.marshalSendAndReceive("http://localhost:8080/ws", request);
        return postEmpleadoRequest;
    }
}
