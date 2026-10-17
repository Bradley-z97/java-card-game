import java.util.Queue;
import java.util.ArrayDeque;
import java.lang.StringBuilder;
import java.io.File;
import java.io.IOException;
import java.io.FileWriter;

public class Deck {
    private final int deckId; // Given upon creation, sequential. ie: 1,2,3,4...n
    private Queue<Card> deckCards;
    private File deckOutputText;
    private final GameState gameState;

    // methods need to be protected from race conditions etc
    // Done synchronized for now, but maybe something else ??
    public Deck(int deckId, GameState gameState) {
        this.deckCards = new ArrayDeque<>();
        this.deckId = deckId;
        this.deckOutputText = new File("deck" + deckId + "_output.txt");
        this.gameState = gameState;

        try{
            deckOutputText.createNewFile();
        } catch (IOException e) {
            System.out.println("An error occurred.");
        }
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

    public void writeToTextFile() {
        try (FileWriter writer = new FileWriter(deckOutputText, false)) {
            if (gameState.isWon()) {
                writer.write("deck" + deckId + " contents: " + getDeckContents());
            } 
        } catch (IOException e) {
            System.out.println("An error occurred.");
        }
    }
}