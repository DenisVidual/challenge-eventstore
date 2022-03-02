package net.intelie.challenges;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors; //used for filtering

public class IteratorImp implements EventIterator {

    public EventStoreImpl eventStore;
    String type;
    long startTime;
    long endTime;
    int curPos = -1;
    private final List<Event> qList;

    public IteratorImp(String type, long startTime, long endTime, EventStoreImpl eventStore) {
        this.eventStore = eventStore;
        this.type = type;
        this.startTime = startTime;
        this.endTime = endTime;
        qList = eventStore.getEvents().stream().filter(event -> Objects.equals(event.type(), type) && event.timestamp() >= startTime && event.timestamp() < endTime).collect(Collectors.toList());
    }

    @Override
    public boolean moveNext() {
        curPos++;
        return curPos < qList.size();
    }

    @Override
    public Event current() {
        if (curPos < qList.size() && curPos != -1) {
            return qList.get(curPos);
        } else {
            throw new IllegalStateException();
        }
    }

    @Override
    public void remove() {
        eventStore.remove(current());
    }

    @Override
    public void close() throws Exception {

    }
}
