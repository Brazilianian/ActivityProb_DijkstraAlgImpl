public enum SceneSpecification {
    YELLOW(1, "Yellow"), PINK(2, "Pink"), ORANGE(3, "Orange"), BLACK(4, "Black"), GREEN(5, "Green"), PURPLE(6, "Purple");

    private final int number;
    private final String name;

    SceneSpecification(int number, String name) {
        this.number = number;
        this.name = name;
    }

    public int getNumber() {
        return number;
    }

    public String getName() {
        return name;
    }
}
