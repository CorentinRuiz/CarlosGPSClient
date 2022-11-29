package org.carlosgps;

import org.carlosgps.gui.GuiApp;
import org.carlosgps.classes.InstructionsConsumer;

public class Main  {



    public static void main(String[] args) {
        InstructionsConsumer consumer = new InstructionsConsumer();
        consumer.run();

        GuiApp.main();
    }


}
