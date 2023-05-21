package resources;

public enum OrderColor {
    NO_COLOR(new String[]{}),
    GRAY(new String[]{"GRAY"}),
    TWO_COLOR(new String[]{"BLACK", "GRAY"});

    private final String[] value;

    OrderColor(String[] value) {
        this.value = value;
    }

    public String[] getValue() {
        return value;
    }
}
