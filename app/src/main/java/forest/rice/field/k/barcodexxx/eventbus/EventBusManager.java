package forest.rice.field.k.barcodexxx.eventbus;

import com.google.common.eventbus.EventBus;

public class EventBusManager {

    private static EventBus eventBus;

   public enum Event {
        POKEMON_ADD,
    }

    public static EventBus getEventBus() {
        if (eventBus == null) {
            eventBus = new EventBus();
        }
        return eventBus;
    }
}
