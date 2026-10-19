import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import java.util.Random;

public class CardPackTest {

    @Test
    @DisplayName("Testing valid, short, and negative pack files")
    public void testPackIsValid() throws IOException {
        int numPlayers = 4;
        Random rand = new Random(42); // fixed seed for reliability

        // sample packs:

        // valid pack: exactly 8n lines of non negative values
        File validPack = new File("testValidPack.txt");
        try (FileWriter writer = new FileWriter(validPack)) {
            for (int i = 0; i < 8 * numPlayers; i++) {
                writer.write((rand.nextInt(1, 11)) + "\n");
            }
        }

        // short pack: one line less than 8n
        File shortPack = new File("testShortPack.txt");
        try (FileWriter writer = new FileWriter(shortPack)) {
            for (int i = 0; i < 8 * numPlayers - 1; i++) {
                writer.write(rand.nextInt(1, 11) + "\n");
            }
        }

        // negative pack: 8n lines but one value is negative
        File negativePack = new File("testNegativePack.txt");
        try (FileWriter writer = new FileWriter(negativePack)) {
            for (int i = 0; i < 8 * numPlayers; i++) {
                if (i == 5) {
                    writer.write("-3\n");
                } else {
                    writer.write(rand.nextInt(1, 11) + "\n");
                }
            }
        }

        try {
            assertTrue(CardGame.isValidPack(validPack.getPath(), numPlayers));
            assertFalse(CardGame.isValidPack(shortPack.getPath(), numPlayers));
            assertFalse(CardGame.isValidPack(negativePack.getPath(), numPlayers));
        } finally {
            // delete .txt files at end of test
            validPack.delete();
            shortPack.delete();
            negativePack.delete();
        }
    }

    @Test
    @DisplayName("Testing a filepath that doesn't exist")
    public void testWrongFilepath() {
        assertFalse(CardGame.isValidPack("doesNotExist.txt", 4));
    }
}
