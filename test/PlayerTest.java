import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.DisplayName;

public class PlayerTest {

    @Test
    @DisplayName("Testing removing a non preferred card")
    public void testDiscardCard() {
        GameState gameState = new GameState();

        Deck addingDeck = new Deck(1, gameState);
        Deck removingDeck = new Deck(2, gameState);

        Player player = new Player(2, addingDeck, removingDeck, gameState);

        player.addToHand(new Card(2));
        player.addToHand(new Card(5));
        player.addToHand(new Card(2));
        player.addToHand(new Card(7));

        Card discardedCard = player.removeCard();

        assertEquals(5, discardedCard.getCardValue());
    }

    @Test
    @DisplayName("Testing card conservation")
    public void cardConservationTest() {
        GameState gameState = new GameState();

        Deck addingDeck = new Deck(1, gameState);
        Deck removingDeck = new Deck(2, gameState);

        Player player = new Player(1, addingDeck, removingDeck, gameState);

        player.addToHand(new Card(1));
        player.addToHand(new Card(4));
        player.addToHand(new Card(1));
        player.addToHand(new Card(7));

        removingDeck.insertCard(new Card(2));
        removingDeck.insertCard(new Card(7));
        removingDeck.insertCard(new Card(8));
        removingDeck.insertCard(new Card(3));

        int initialCount = player.getHand().size() + addingDeck.size() + removingDeck.size();
        Card drawnCard = addingDeck.takeFromDeck();
        player.addToHand(drawnCard);

        Card discardCard = player.removeCard();
        removingDeck.insertCard(discardCard);

        int finalCount = player.getHand().size() + addingDeck.size() + removingDeck.size();

        assertEquals(initialCount, finalCount);
    }

}
