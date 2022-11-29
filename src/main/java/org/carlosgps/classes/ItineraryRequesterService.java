package org.carlosgps.classes;

import com.baeldung.soap.ws.client.generated.BestItinerary;
import com.baeldung.soap.ws.client.generated.IService1;
import com.baeldung.soap.ws.client.generated.Service1;

import java.util.List;

public class ItineraryRequesterService {

    Service1 service1;

    IService1 proxyService1;

    BestItinerary bestItinerary;

    public ItineraryRequesterService() {
        service1 = new Service1();
        proxyService1 = service1.getBasicHttpBindingIService1();
    }

    public GPSPoint getOriginPoint(){
        List<Double> originAPIFormat = bestItinerary.getPoints().getValue().getArrayOfdouble().get(0).getDouble();
        return new GPSPoint(originAPIFormat.get(originAPIFormat.size()-1), originAPIFormat.get(0));
    }

    public GPSPoint getDestinationPoint(){
        List<Double> destAPIFormat = bestItinerary.getPoints().getValue().getArrayOfdouble().get(bestItinerary.getPoints().getValue().getArrayOfdouble().size() - 1).getDouble();
        return new GPSPoint(destAPIFormat.get(destAPIFormat.size()-1),destAPIFormat.get(0));
    }

    public GPSPoint getFirstStationPoint(){
        if(bestItinerary.getPoints().getValue().getArrayOfdouble().size() == 4){
            List<Double> stationPointAPIFormat = bestItinerary.getPoints().getValue().getArrayOfdouble().get(1).getDouble();
            return new GPSPoint(stationPointAPIFormat.get(stationPointAPIFormat.size()-1),stationPointAPIFormat.get(0));
        }

        return null;
    }

    public GPSPoint getLastStationPoint(){
        if(bestItinerary.getPoints().getValue().getArrayOfdouble().size() == 4){
            List<Double> stationPointAPIFormat = bestItinerary.getPoints().getValue().getArrayOfdouble().get(2).getDouble();
            return new GPSPoint(stationPointAPIFormat.get(stationPointAPIFormat.size()-1),stationPointAPIFormat.get(0));
        }
        return null;
    }

    public Double getTotalDistance(){
        return Math.round(bestItinerary.getDistance() * 100.0) / 100.0;
    }

    public void setBestItinerary(String origin, String destination) {
       bestItinerary = proxyService1.getItinerary(origin,destination);
    }
}
