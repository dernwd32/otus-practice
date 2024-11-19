public interface Flying {

    public default void fly() {
        System.out.println("Я лечу");
    };
}