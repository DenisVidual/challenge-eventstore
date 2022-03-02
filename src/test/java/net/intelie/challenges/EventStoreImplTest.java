package net.intelie.challenges;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class EventStoreImplTest {

    @Test
    public void insert() {

        EventStoreImpl store = new EventStoreImpl();

        store.insert(new Event("A", 1L));

        List<Event> events = store.getEvents();
        assertEquals("List size", 1, events.size());
        assertEquals("A", events.get(0).type());
        assertEquals(1L, events.get(0).timestamp());

    }

    @Test
    public void remove() {
        EventStoreImpl store = new EventStoreImpl();
        Event event = new Event("A", 1);
        store.getEvents().add(event);

        store.remove(event);

        List<Event> events = store.getEvents();
        assertEquals("List size", 0, events.size());


    }

    @Test
    public void removeAll() {

        EventStoreImpl store = new EventStoreImpl();


        store.insert(new Event("A", 1L));
        store.insert(new Event("B", 2L));
        store.insert(new Event("C", 3L));
        store.insert(new Event("D", 4L));
        store.insert(new Event("A", 5L));
        store.insert(new Event("A", 6L));

        store.removeAll("A");

        assertEquals(3, store.getEvents().size());


    }
}