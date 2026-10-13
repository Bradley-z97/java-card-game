import java.util.Queue;
import java.util.ArrayDequeue;

public class Deck {
    private Queue<Card> deckCards;

    // methods need to be protected from race conditions etc
    // Done synchronized for now, but maybe something else ??
    public Deck() {
        this.deckCards = new ArrayDequeue<>();
    }
    
    public synchronized Card takeFromDeck() {
        return deckCards.poll(); // Take card from the top of the deck
    }

    public synchronized void insertCard(Card card) {
        deckCards.add(card); // Add card to the bottom of the deck
    }
}