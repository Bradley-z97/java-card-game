import java.util.List;
import java.util.Collections;

public class CardPack {
    private int packSize;
    private List<Card> cards;

    public CardPack(int numPlayers) {
        this.packSize = 8 * numPlayers;
    }

    /**
     * Adds  cards to get deck (values 1-n)
     * and then shuffles them.
     * 
     * Not sure if we use cards 1-n or traditional
     * card values (maybe 1-10)?
     */
    public void initialisePack() {
        for (i = 0; i < packSize; i++) {
            Card c = new Card(i);
            this.cards.add(c);
        }
        Collections.shuffle(cards);
    }
} 