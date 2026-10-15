import java.io.FileWriter;
import java.io.File;
import java.io.IOException;
import java.lang.StringBuilder;
import java.util.List;
import java.util.ArrayList;
/**
 * The Player class constantly takes cards from the 'adding deck',
 * and discards to the 'removing' deck. This is passed to each thread
 * as an argument upon creation, as each player takes and discards 
 * to/from different decks.
 * 
 * A player's only job is to take/discard cards, thread safety is
 * handled inside the {@link Deck} class where the shared mutable state lies. 
 */
public class Player implements Runnable {
    private int playerId;
    private List<Card> hand = new ArrayList<>();
    private Deck addingDeck;
    private Deck removingDeck;
    private File textFile;

    public Player(int playerId, Deck addingDeck, Deck removingDeck) {
        this.playerId = playerId;
        this.addingDeck = addingDeck;
        this.removingDeck = removingDeck;
        this.textFile = new File("Player"+playerId+".txt");

        try {
            textFile.createNewFile();
        }
        catch (IOException e) {
            System.out.println("An error occurred.");
        }
    }

    /**
     * Method that makes the player take cards from the deck
     * to the left and discard to the deck on the right. 
     * 
     * The methods acting on {@link Deck} are synchronized so is thread safe.
     */
    @Override
    public void run() {
        boolean winner = false;
        try (FileWriter writer = new FileWriter(textFile, true)) {
            // Keeps playing until winner is found
            while (!winner) {
                // Build current hand string 
                StringBuilder currentHand = new StringBuilder();
                for (Card card : hand) {
                    currentHand.append(card.getCardValue());
                    currentHand.append(" ");
                }

                // write current hand to txt file
                writer.write("Player " + playerId +
                             " current hand is " + currentHand);

                // write card 'n' taken from deck 'x' to file
                Card cardTaken = addingDeck.takeFromDeck();
                this.addToHand(cardTaken);
                writer.write("Player " + playerId +
                             " draws a " + cardTaken.getCardValue() +
                             " from deck " + addingDeck.getDeckId() +
                             "\n");
            
                Card discardedCard = this.removeCard();
                // check if player has won
                if (discardedCard == null) {
                    winner = true;
                } else {
                    // discard a card from hand and write to txt file
                    removingDeck.insertCard(discardedCard);
                    writer.write("Player " + playerId +
                                 " discards a " + discardedCard.getCardValue() +
                                 " from deck " + removingDeck.getDeckId() +
                                 "\n");
                }
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
        }
    }

    public void addToHand(Card card) {
        hand.add(card);
    }

    /**
     * Finds and removes the first card that doesn't match the player's
     * preference (the same value as their player ID).
     * 
     * @return the discarded {@link Card}, or null if cards are all the
     * preferred value, meaning player has won. 
     */
    public Card removeCard() {
        List<Integer> valueList = new ArrayList<>();
        
        hand.forEach( (card) -> { valueList.add(card.getCardValue()); });

        for (int i = 0; i < hand.size(); i++) {
            if (valueList.get(i) == playerId) continue;
            return hand.remove(i);
        }

        return null; // if removeCard returns null, all cards == playerId, player wins
    }
}