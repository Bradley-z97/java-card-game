public class Card {

    private final Integer number; // immutable value

    public Card(Integer number) {
        this.number = number;
    }

    //Return the integer value of the card
    public Integer getCardValue() {
        return number;
    }
}