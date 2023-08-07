package src.main.java.passwordmanager.EventListeners;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class EventManager {

    private HashMap<String,List<IEventListener>> listeners = new HashMap<String,List<IEventListener>>();

    public EventManager(String... operations){
        for(String operation : operations){
            this.listeners.put(operation, new ArrayList<IEventListener>());
        }

    }

    public void subscribe(String eventType, IEventListener listener){
        List<IEventListener> listenersList = listeners.get(eventType);
        listenersList.add(listener);
    }

    public void notify(String eventType, String message){
        List<IEventListener> listenersList = listeners.get(eventType);
        for(IEventListener listener : listenersList){
            listener.update(message);
        }
    }
    
}
