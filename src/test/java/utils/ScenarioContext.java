package utils;

import java.util.HashMap;
import java.util.Map;

/**
 * ScenarioContext
 *
 * A shared key-value store passed between step definition classes
 * using Cucumber PicoContainer dependency injection.
 *
 * Example usage:
 *   context.set("petId", 12345L);
 *   long id = (long) context.get("petId");
 */
public class ScenarioContext {

    private final Map<String, Object> context = new HashMap<>();

    public void set(String key, Object value) {
        context.put(key, value);
    }

    public Object get(String key) {
        return context.get(key);
    }

    public boolean contains(String key) {
        return context.containsKey(key);
    }

    public void clear() {
        context.clear();
    }
}