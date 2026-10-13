public class Card {

    private final Integer number; // immutable value

    public Card(Integer number) {
        this.number = number;
    }

    public Integer getCardValue() {
        return number;
    }
}