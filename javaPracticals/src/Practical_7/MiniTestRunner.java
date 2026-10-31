package Practical_7;

import java.lang.annotation.*;
import java.lang.reflect.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface Run {}

class MyTests {

    @Run
    public void testAddition() {
        System.out.println("Addition test executed");
    }

    @Run
    public void testSubtraction() {
        System.out.println("Subtraction test executed");
    }

    public void normalMethod() {
        System.out.println("Normal method executed");
    }

    @Run
    public void testMultiplication() {
        System.out.println("Multiplication test executed");
    }
}

public class MiniTestRunner {
    public static void main(String[] args) {

        MyTests obj = new MyTests();
        int count = 0;
        Method[] methods = MyTests.class.getDeclaredMethods();

        for (Method method : methods) {
            if (method.isAnnotationPresent(Run.class)
                    && method.getParameterCount() == 0) {
                try {
                    method.invoke(obj);
                    count++;
                } catch (Exception e) {
                    System.out.println("Test failed: "
                            + method.getName());
                }
            }
        }
        System.out.println("Total tests executed: " + count);
    }
}
