package dto;

public enum Coin {
    TWO_POUND(200, "£2"),
    ONE_POUND(100, "£1"),
    FIFTY(50, "50p"),
    TWENTY(20, "20p"),
    TEN(10, "10p"),
    FIVE(5, "5p"),
    TWO(2, "2p"),
    ONE(1, "1p");

    private final int value;
    private final String name;

    Coin(int value, String name) {
        this.value = value;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getValue() {
        return value;
    }
}
