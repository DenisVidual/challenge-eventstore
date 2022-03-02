package net.intelie.challenges;

public class Main {


    public static void main(String[] args) {

        EventStore eventStore = new EventStoreImpl();

        eventStore.insert(new Event("A", 1L));
        eventStore.insert(new Event("B", 2L));
        eventStore.insert(new Event("C", 3L));
        eventStore.insert(new Event("D", 4L));
        eventStore.insert(new Event("A", 5L));
        eventStore.insert(new Event("A", 6L));
        eventStore.insert(new Event("C", 7L));
        eventStore.insert(new Event("A", 8L));
        eventStore.insert(new Event("D", 9L));
        eventStore.insert(new Event("A", 10L));

        EventIterator iterator = eventStore.query("A", 1, 100);
        while (iterator.moveNext()) {
            System.out.println(iterator.current());
        }

        System.out.println("Remove type: C");

        eventStore.removeAll(("C"));

        System.out.println("Query Events type A from 1 to 10");

        iterator = eventStore.query("D", 1, 10);

        System.out.println(iterator.moveNext());
        System.out.println(iterator.current());
        iterator.remove();

        System.out.println("******************");

        while (iterator.moveNext()) {
            System.out.println(iterator.current());
        }


    }


}

