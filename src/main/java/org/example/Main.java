package org.example;

import com.baeldung.soap.ws.client.generated.BestItinerary;
import com.baeldung.soap.ws.client.generated.IService1;
import com.baeldung.soap.ws.client.generated.Service1;

public class Main  {



    public static void main(String[] args) {

        InstructionsConsumer consumer = new InstructionsConsumer();
        consumer.run();

        Service1 service1 = new Service1();
        IService1 proxyService1 = service1.getBasicHttpBindingIService1();
        BestItinerary bestItinerary = proxyService1.getItinerary("1 Chem. des Courses, 31100 Toulouse", "11 Quai Saint-Pierre, 31000 Toulouse");

    }


}
