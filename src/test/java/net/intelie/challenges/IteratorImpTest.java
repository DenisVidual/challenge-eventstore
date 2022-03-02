package net.intelie.challenges;

import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class IteratorImpTest {

    @Test
    public void moveNext() {

        List<Event> events = new ArrayList<>();
        EventStoreImpl store = new EventStoreImpl();
        events.add(new Event("A", 1));
        events.add(new Event("B", 2));
        events.add(new Event("C", 3));
        events.add(new Event("D", 4));
        events.add(new Event("A", 4));
        IteratorImp iteratorImp = new IteratorImp("A", 1, 4, store);
        iteratorImp.moveNext();
        assertEquals(0, iteratorImp.curPos);
        iteratorImp.moveNext();
        assertEquals(1, iteratorImp.curPos);


    }

    @Test
    public void current() {

        List<Event> events = new ArrayList<>();
        EventStoreImpl store = Mockito.mock(EventStoreImpl.class);
        Mockito.when(store.getEvents()).thenReturn(events);
        ArgumentCaptor<Event> captor = ArgumentCaptor.forClass(Event.class);
        events.add(new Event("A", 1));
        events.add(new Event("B", 2));
        events.add(new Event("C", 3));
        events.add(new Event("D", 4));
        events.add(new Event("A", 4));
        IteratorImp iteratorImp = new IteratorImp("A", 1, 4, store);

        iteratorImp.moveNext();
        iteratorImp.current();

        assertEquals(0, iteratorImp.curPos);


    }

    @Test
    public void remove() {

        List<Event> events = new ArrayList<>();
        EventStoreImpl store = Mockito.mock(EventStoreImpl.class);
        Mockito.when(store.getEvents()).thenReturn(events);
        ArgumentCaptor<Event> captor = ArgumentCaptor.forClass(Event.class);
        events.add(new Event("A", 1));
        events.add(new Event("B", 2));
        events.add(new Event("C", 3));
        events.add(new Event("D", 4));
        events.add(new Event("A", 5));
        IteratorImp iteratorImp = new IteratorImp("A", 1, 8, store);
        iteratorImp.moveNext();

        iteratorImp.remove();

        Mockito.verify(store).remove(captor.capture());
        assertEquals("A", captor.getValue().type());
        assertEquals(1, captor.getValue().timestamp());

    }

    @Test(expected = IllegalStateException.class)
    public void exception1() {

        List<Event> events = new ArrayList<>();
        EventStoreImpl store = Mockito.mock(EventStoreImpl.class);
        Mockito.when(store.getEvents()).thenReturn(events);
        ArgumentCaptor<Event> captor = ArgumentCaptor.forClass(Event.class);
        events.add(new Event("A", 1));
        IteratorImp iteratorImp = new IteratorImp("A", 1, 8, store);

        iteratorImp.remove();

    }

    @Test(expected = IllegalStateException.class)
    public void exception2() {

        List<Event> events = new ArrayList<>();
        EventStoreImpl store = Mockito.mock(EventStoreImpl.class);
        Mockito.when(store.getEvents()).thenReturn(events);
        ArgumentCaptor<Event> captor = ArgumentCaptor.forClass(Event.class);
        events.add(new Event("A", 1));
        IteratorImp iteratorImp = new IteratorImp("A", 1, 1, store);
        iteratorImp.moveNext();
        iteratorImp.moveNext();
        iteratorImp.current();

    }
}