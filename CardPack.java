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
     * This is wrong, needs to be cards in range of 2n.
     * so 1 to 2n, repeated 4 times. that way every player can have a chance
     * at winning (4 of the same card)
     * 
     */
    public void initialisePack() {
        for (i = 0; i < packSize; i++) {
            Card c = new Card(i);
            this.cards.add(c);
        }
        Collections.shuffle(cards);

    }
} 