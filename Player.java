public class Player implements Runnable {
    private int playerId;
    private List<Card> hand = new ArrayList<>();
    private Deck addingDeck;
    private Deck removingDeck;

    public Player(int playerId, Deck addingDeck, Deck removingDeck) {
        this.playerId = playerId;
        this.addingDeck = addingDeck;
        this.removingDeck = removingDeck;
    }

    @Override
    public void run() {
        boolean winner = false;

        while (!winner) {

            this.addToHand(addingDeck.takeFromDeck());
            
            Card discardedCard = this.removeCard();

            if (discardedCard == null) {
                winner = true;
            } else removingDeck.insertCard(discardedCard);
        }
    }

    public void addToHand(Card card) {
        hand.add(card);
    }

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