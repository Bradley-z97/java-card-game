import java.util.List;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Queue;
import java.util.ArrayDeque;

public class CardPack {
    private Queue<Card> pack;
    private int numPlayers;
    private String filepath;

    public CardPack(int numPlayers, String filepath) {
        this.numPlayers = numPlayers;
        this.filepath = filepath;
        this.pack = new ArrayDeque<>();
    }

    public void initialisePack(String filepath) {
        File file = new File(filepath);

        try(Scanner scanner = new Scanner(file);) {
            while (scanner.hasNextInt()) {
            Integer num = scanner.nextInt();
            
            Card card = new Card(num);
            pack.add(card);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
        }
    }

    public Card takeCard() {
        return pack.poll();
    }
}