package Practical_13;

class Box<T> {
    private T value;

    public void set(T value) {
        this.value = value;
    }

    public T get() {
        return value;
    }
}

class Pair<A, B> {
    private A first;
    private B second;

    public Pair(A first, B second) {
        this.first = first;
        this.second = second;
    }

    public A getFirst() {
        return first;
    }

    public B getSecond() {
        return second;
    }
}

public class GenericContainers {

    public static <T> T firstElement(T[] array) {
        if (array.length == 0) {
            return null;
        }
        return array[0];
    }

    public static void main(String[] args) {

        Box<Integer> intBox = new Box<>();
        intBox.set(100);
        System.out.println("Integer Box: " + intBox.get());

        Box<String> strBox = new Box<>();
        strBox.set("Hello Java");
        System.out.println("String Box: " + strBox.get());

        Pair<String, Integer> pair1 =
                new Pair<>("Riya", 20);

        Pair<Integer, Double> pair2 =
                new Pair<>(101, 95.5);

        System.out.println("\nPair 1: "
                + pair1.getFirst() + ", " + pair1.getSecond());

        System.out.println("Pair 2: "
                + pair2.getFirst() + ", " + pair2.getSecond());

        Integer[] numbers = {10, 20, 30};
        String[] names = {"Riya", "Zeel", "Karan"};

        System.out.println("\nFirst Integer: "
                + firstElement(numbers));

        System.out.println("First String: "
                + firstElement(names));
    }
}
