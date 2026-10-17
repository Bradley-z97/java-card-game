public class GameState {
    private volatile boolean won = false;
    private int winnerId = -1;

    public boolean hasWinner(Player player) {
        if (player.getHand().size() != 4) {
            return false;
        }
        for (Card card : player.getHand()) {
            if (!(card.getCardValue() == player.getPlayerId())) return false;
        }
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