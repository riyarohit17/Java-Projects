package Practical_13;

public class BoundedStats {

    public static <N extends Number> double average(N[] values) {

        if (values.length == 0) {
            return 0;
        }

        double sum = 0;

        for (N value : values) {
            sum += value.doubleValue();
        }

        return sum / values.length;
    }

    public static void main(String[] args) {

        Integer[] integers = {10, 20, 30, 40, 50};

        Double[] doubles = {10.5, 20.5, 30.5};

        Long[] longs = {100L, 200L, 300L};

        System.out.println("Integer Average: "
                + average(integers));

        System.out.println("Double Average: "
                + average(doubles));

        System.out.println("Long Average: "
                + average(longs));
    }
}
