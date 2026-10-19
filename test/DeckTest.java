import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.DisplayName;

public class DeckTest {

    @Test
    @DisplayName("Tesing FIFO deck behaviour")
    public void testFIFO() {
        GameState gameState = new GameState();
        Deck deck = new Deck(1, gameState);

        deck.insertCard(new Card(4));
        deck.insertCard(new Card(7));
        deck.insertCard(new Card(3));
        deck.insertCard(new Card(2));

        assertEquals(4, deck.takeFromDeck().getCardValue());
        assertEquals(7, deck.takeFromDeck().getCardValue());
        assertEquals(3, deck.takeFromDeck().getCardValue());
        assertEquals(2, deck.takeFromDeck().getCardValue());

    }
    
}
