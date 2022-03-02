package net.intelie.challenges;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;


public class EventStoreImpl implements EventStore {

    private final List<Event> eventList = Collections.synchronizedList(new ArrayList<>()); // synchronized for thread safe

    protected List<Event> getEvents() {
        return eventList;
    }

    @Override
    public void insert(Event event) {
        if (event == null || event.type() == null) {  //make sure event is not null
            return;
        }
        String type = event.type();
        long timestamp = event.timestamp();
        eventList.add(new Event(type, timestamp));
    }

    public void remove(Event event) {
        eventList.remove(event);

    }

    @Override
    public void removeAll(String type) {

        eventList.removeIf(event -> Objects.equals(event.type(), type));

    }

    @Override
    public EventIterator query(String type, long startTime, long endTime) {

        return new IteratorImp(type, startTime, endTime, this);


    }
}
