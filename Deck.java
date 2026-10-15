import java.util.Queue;
import java.util.ArrayDequeue;
import java.lang.StringBuilder;

public class Deck {
    private final int deckId; // Given upon creation, sequential. ie: 1,2,3,4...n
    private Queue<Card> deckCards;

    // methods need to be protected from race conditions etc
    // Done synchronized for now, but maybe something else ??
    public Deck(int deckId) {
        this.deckCards = new ArrayDequeue<>();
        this.deckId = deckId;
    }
    
    public synchronized Card takeFromDeck() {
        return deckCards.poll(); // Take card from the top of the deck
    }

    public synchronized void insertCard(Card card) {
        deckCards.add(card); // Add card to the bottom of the deck
    }

    public int getDeckId() {
        return deckId;
    }

    public synchronized String getDeckContents() {
        StringBuilder sb = new StringBuilder();
        for (Card card : deckCards) {
            sb.append(card.getCardValue());
            sb.append(" ");
        }
        return sb.toString();
    }
}