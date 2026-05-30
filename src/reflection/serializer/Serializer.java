package reflection.serializer;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class Serializer {public String serialize(Object instance) {
    List<String> pairs = new ArrayList<>();
    Field[] fields = instance.getClass().getDeclaredFields();

    for (Field field : fields) {
        if (field.isAnnotationPresent(Stored.class)) {
            field.setAccessible(true);
            try {
                String name = field.getAnnotation(Stored.class).value();
                if (name.isEmpty()) name = field.getName();

                Object valueObj = field.get(instance);
                String value = (valueObj == null) ? "" : String.valueOf(valueObj);

                value = value.replace("%", "%25")
                        .replace(":", "%3a")
                        .replace("|", "%7c");

                pairs.add(name + ":" + value);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }
    return String.join("|", pairs);
}

    public <T> T deserialize(String inputString, Class<T> clazz) {
        try {
            T obj = clazz.getDeclaredConstructor().newInstance();
            String[] pairs = inputString.split("\\|");

            for (String pair : pairs) {
                String[] kv = pair.split(":", 2);
                String key = kv[0];
                String value = kv[1].replace("%7c", "|").replace("%3a", ":").replace("%25", "%");

                for (Field field : clazz.getDeclaredFields()) {
                    if (field.isAnnotationPresent(Stored.class)) {
                        String fieldName = field.getAnnotation(Stored.class).value();
                        if (fieldName.isEmpty()) fieldName = field.getName();

                        if (fieldName.equals(key)) {
                            field.setAccessible(true);
                            if (field.getType() == int.class) {
                                field.set(obj, Integer.parseInt(value));
                            } else {
                                field.set(obj, value);
                            }
                        }
                    }
                }
            }
            return obj;
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}