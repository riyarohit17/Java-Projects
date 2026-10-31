package Practical_7;
import java.lang.annotation.*;
import java.lang.reflect.*;
import java.util.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@interface NotBlank {}

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@interface MaxLength {
    int value();
}

class SignupForm {

    @NotBlank
    @MaxLength(10)
    String username;

    @NotBlank
    @MaxLength(30)
    String email;

    @NotBlank
    @MaxLength(12)
    String password;

    SignupForm(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }
}

public class FormValidator {

    public static List<String> validate(Object obj) {
        List<String> errors = new ArrayList<>();

        for (Field field : obj.getClass().getDeclaredFields()) {
            try {
                field.setAccessible(true);
                Object value = field.get(obj);

                if (field.isAnnotationPresent(NotBlank.class)) {
                    if (value == null ||
                            value.toString().trim().isEmpty()) {
                        errors.add(field.getName() + " cannot be blank");
                    }
                }

                if (field.isAnnotationPresent(MaxLength.class)) {
                    int max = field.getAnnotation(
                            MaxLength.class).value();

                    if (value != null &&
                            value.toString().length() > max) {
                        errors.add(field.getName() +
                                " exceeds maximum length " + max);
                    }
                }

            } catch (IllegalAccessException e) {
                errors.add("Cannot access " + field.getName());
            }
        }

        return errors;
    }

    public static void main(String[] args) {

        SignupForm form = new SignupForm(
                "",
                "verylongemailaddress@example.com",
                "123456"
        );

        List<String> errors = validate(form);

        if (errors.isEmpty()) {
            System.out.println("Form is valid!");
        } else {
            System.out.println("Validation Errors:");
            for (String error : errors) {
                System.out.println(error);
            }
        }
    }
}
