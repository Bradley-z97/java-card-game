import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.DisplayName;

public class PlayerTest {

    @Test
    @DisplayName("Testing removing a non preferred card")
    public void testDiscardCard() {

        GameState gameState = new GameState();

        // Create an addingDeck and removingDeck for the player
        Deck addingDeck = new Deck(1, gameState);
        Deck removingDeck = new Deck(2, gameState);

        Player player = new Player(2, addingDeck, removingDeck, gameState);

        // Add 4 cards to the players hand
        player.addToHand(new Card(2));
        player.addToHand(new Card(5));
        player.addToHand(new Card(2));
        player.addToHand(new Card(7));

        // Removing a card from hand
        Card discardedCard = player.removeCard();

        // Check the card removed is a non preferred card
        assertEquals(5, discardedCard.getCardValue());
    }

    @Test
    @DisplayName("Testing card conservation")
    public void cardConservationTest() {

        GameState gameState = new GameState();

        // Create an addingDeck and removingDeck for the player
        Deck addingDeck = new Deck(1, gameState);
        Deck removingDeck = new Deck(2, gameState);

        Player player = new Player(1, addingDeck, removingDeck, gameState);

        // Add cards to the player hands
        player.addToHand(new Card(1));
        player.addToHand(new Card(4));
        player.addToHand(new Card(1));
        player.addToHand(new Card(7));

        // Add cards to the removing deck
        removingDeck.insertCard(new Card(2));
        removingDeck.insertCard(new Card(7));
        removingDeck.insertCard(new Card(8));
        removingDeck.insertCard(new Card(3));

        // Have an initial count for how many cards there are amongst the players hand, addingDeck
        // and removingDeck
        int initialCount = player.getHand().size() + addingDeck.size() + removingDeck.size();

        // Take a card from the removingDeck
        Card drawnCard = removingDeck.takeFromDeck();
        player.addToHand(drawnCard);

        // Discarding a card from the players hand to the addingDeck
        Card discardCard = player.removeCard();
        addingDeck.insertCard(discardCard);

        // Have final count for how many cards there are amongst the players hands, addingDeck
        // and removingDeck
        int finalCount = player.getHand().size() + addingDeck.size() + removingDeck.size();

        // Check the intialCount and finalCount are the same and that the players hand has
        // four cards
        assertEquals(initialCount, finalCount);
        assertEquals(4, player.getHand().size());
    }

}
