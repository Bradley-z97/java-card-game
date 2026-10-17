public class GameState {
    private volatile boolean won;
    private volatile int winnerId = -1;

    public boolean hasWinner(Player player) {
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