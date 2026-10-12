import java.util.concurrent.atomic.AtomicInteger;

public class Card {

    private final AtomicInteger number; // immutable value

    public Card(AtomicInteger number) {
        this.number = number;
    }
}