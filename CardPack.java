import java.util.List;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

public class CardPack {
    private List<Card> pack;
    private int numPlayers;
    private String filepath;

    public CardPack(int numPlayers, String filepath) {
        this.numPlayers = numPlayers;
        this.filepath = filepath;
        this.pack = new ArrayList<>();
    }

    public void initialisePack(String filepath) {
        File file = new File(filepath);

        try(Scanner scanner = new Scanner(file);) {
            while (scanner.hasNextInt()) {
            AtomicInteger num = new AtomicInteger(scanner.nextInt());
            
            Card card = new Card(num);
            pack.add(card);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
        }    
    }
}