import java.util.List;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.Queue;

public class CardPack {
    private Queue<Card> pack;
    private int numPlayers;
    private String filepath;

    public CardPack(int numPlayers, String filepath) {
        this.numPlayers = numPlayers;
        this.filepath = filepath;
        this.pack = new Queue<>();
    }

    public void initialisePack(String filepath) {
        File file = new File(filepath);

        try(Scanner scanner = new Scanner(file);) {
            while (scanner.hasNextInt()) {
            Integer num = new Integer(scanner.nextInt());
            
            Card card = new Card(num);
            pack.add(card);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
        }
    }
}