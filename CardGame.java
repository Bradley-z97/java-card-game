import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class CardGame {
    public static void main(String[] args) {
        // TODO: main method implementation
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter the number of players: ");

        int numPlayers = scanner.nextInt();
        scanner.nextLine();

        while (true) {
            System.out.println("Please enter location of pack to load: ");
            String packLocation = scanner.nextLine();
            if (isValidPack(packLocation, numPlayers)) {
                break;
            } else {
                System.out.println("Invalid pack. Please try again");
            }
            }
        };

    public static boolean isValidPack(String filepath, int numPlayers) {
        int lineCount = 0;
        try {
            File file = new File(filepath);
            Scanner fileScanner = new Scanner(file);
            while(fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                lineCount++;
                
                try {
                    int value = Integer.parseInt(line);
                    if (value < 0) {
                        fileScanner.close();
                        return false;
                    }
                } catch (NumberFormatException e) {
                    return false;
                }
            }
            
        } catch (FileNotFoundException e) {
                return false;
            }
        return lineCount == 8 * numPlayers;
    }
}