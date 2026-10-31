import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import model.annotation.Id;

public class GenericRepository<T> {

    private final List<T> items = new ArrayList<>();

    public void save(T item) {
        items.add(item);
    }
    public List<T> findAll() {
        return new ArrayList<>(items);
    }
    public T findById(Object id) {
        for (T item : items) {
            Class<?> current = item.getClass();

            while (current != null) {
                for (Field field : current.getDeclaredFields()) {
                    if (field.isAnnotationPresent(Id.class)) {
                        try {
                            field.setAccessible(true);

                            Object value = field.get(item);

                            if (value != null && value.equals(id)) {
                                return item;
                            }
                        } catch (IllegalAccessException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
                current = current.getSuperclass();
            }
        }
        return null;
    }
    public int count() {
        return items.size();
    }
}
