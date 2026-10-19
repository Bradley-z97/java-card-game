public class GameState {
    private volatile boolean won;
    private volatile int winnerId = -1;

    public boolean hasWinner(Player player) {
        // Checks the player hand has 4 cards
        if (player.getHand().size() != 4) {
            return false;
        }

        // Checks that the card values are the same as the playerId
        for (Card card : player.getHand()) {
            if (!(card.getCardValue() == player.getPlayerId())) return false;
        }
        // Changes the boolean value of won to true and hasWinner to true
        winnerId = player.getPlayerId();
        won = true;
        return true;
    }

    public boolean isWon() {
        return won;
    }

    public int getWinnerId() {
        return winnerId;
    }
}