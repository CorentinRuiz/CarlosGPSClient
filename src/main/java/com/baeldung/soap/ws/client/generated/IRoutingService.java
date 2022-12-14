
package com.baeldung.soap.ws.client.generated;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.3.2
 * Generated source version: 2.2
 * 
 */
@WebService(name = "IRoutingService", targetNamespace = "http://tempuri.org/")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface IRoutingService {


    /**
     * 
     * @param origin
     * @param destination
     * @return
     *     returns com.baeldung.soap.ws.client.generated.BestItinerary
     */
    @WebMethod(operationName = "GetItinerary", action = "http://tempuri.org/IRoutingService/GetItinerary")
    @WebResult(name = "GetItineraryResult", targetNamespace = "http://tempuri.org/")
    @RequestWrapper(localName = "GetItinerary", targetNamespace = "http://tempuri.org/", className = "com.baeldung.soap.ws.client.generated.GetItinerary")
    @ResponseWrapper(localName = "GetItineraryResponse", targetNamespace = "http://tempuri.org/", className = "com.baeldung.soap.ws.client.generated.GetItineraryResponse")
    public BestItinerary getItinerary(
        @WebParam(name = "origin", targetNamespace = "http://tempuri.org/")
        String origin,
        @WebParam(name = "destination", targetNamespace = "http://tempuri.org/")
        String destination);

}
