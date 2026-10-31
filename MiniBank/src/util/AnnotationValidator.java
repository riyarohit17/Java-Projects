package util;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import model.annotation.Positive;
import model.annotation.MaxLength;

public class AnnotationValidator {

    public static String[] validate(Object obj) {
        List<String> errors = new ArrayList<>();

        Class<?> current = obj.getClass();

        while (current != null) {
            Field[] fields = current.getDeclaredFields();

            for (Field field : fields) {
                try {
                    field.setAccessible(true);
                    Object value = field.get(obj);

                    if (field.isAnnotationPresent(Positive.class)) {
                        Positive p = field.getAnnotation(Positive.class);

                        if (value instanceof Number &&
                                ((Number) value).longValue() <= 0) {
                            errors.add(field.getName() + ": " + p.message());
                        }
                    }

                    if (field.isAnnotationPresent(MaxLength.class)
                            && value instanceof String) {
                        MaxLength m = field.getAnnotation(MaxLength.class);
                        if (((String) value).length() > m.value()) {
                            errors.add(field.getName()
                                    + ": maximum length is " + m.value());
                        }
                    }
                } catch (IllegalAccessException e) {
                    errors.add("Could not validate " + field.getName());
                }
            }
            current = current.getSuperclass();
        }
        return errors.toArray(new String[0]);
    }
}
