package space.harbour.java.hw5;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import org.junit.Test;

public class MyHashMapTest {

    MyHashMap<String, String> map = new MyHashMap<>();

    @Test
    public void size() {
        map.put("Key1", "Item1");
        map.put("Key2", "Item2");
        map.put("Key3", "Item3");
        map.put("Key4", "Item4");
        map.put("Key5", "Item5");
        assertEquals(5, map.size());
    }

    @Test
    public void isEmpty() {
        assertEquals(0, map.size());
    }

    @Test
    public void containsKey() {
        map.put("Key", "Item");
        assertTrue(map.containsKey("Key"));
    }

    @Test
    public void nonExistentContainsKey() {
        assertFalse(map.containsKey("Key"));
    }

    @Test(expected = NullPointerException.class)
    public void nullContainsKey() {
        assertFalse(map.containsKey(null));
    }

    @Test
    public void containsValue() {
        map.put("Key", "Item");
        assertTrue(map.containsValue("Item"));
    }

    @Test
    public void nonExistentContainsValue() {
        assertFalse(map.containsKey("Item"));
    }

    @Test(expected = NullPointerException.class)
    public void nullContainsValue() {
        assertFalse(map.containsKey(null));
    }


    @Test
    public void get() {
        map.put("Key", "Item");
        assertEquals("Item", map.get("Key"));
    }

    @Test
    public void nonExistentGet() {
        assertNull(map.get("Key"));
    }

    @Test
    public void put() {
        map.put("Key", "Item");
        assertEquals("Item", map.get("Key"));
    }

    @Test
    public void duplicatesPut() {
        map.put("a", "lalalala");
        map.put("a", "lalalala");
        assertEquals("lalalala", map.get("a"));
        assertEquals(1, map.size());
    }

    @Test(expected = NullPointerException.class)
    public void nullKeyPut() {
        map.put(null, "testnull");
    }

    @Test
    public void nullValuePut() {
        map.put("testnull", null);
        assertNull(map.get("testnull"));
    }

    @Test
    public void remove() {
        map.put("Key", "Item");
        map.clear();
        assertEquals(0, map.size());
    }

    @Test
    public void putAll() {
        MyHashMap<String, String> mapCopy = new MyHashMap<>();
        mapCopy.putAll(map);
        assertEquals(map, mapCopy);
    }

    @Test
    public void clear() {
        map.put("Key1", "Item1");
        map.put("Key2", "Item2");
        map.put("Key3", "Item3");
        map.put("Key4", "Item4");
        map.put("Key5", "Item5");
        map.clear();
        assertEquals(0, map.size());
    }

    @Test
    public void keySet() {
        map.put("Key1", "Item1");
        map.put("Key2", "Item2");
        map.put("Key3", "Item3");
        Set<String> set = new HashSet<>();
        set.add("Key1");
        set.add("Key2");
        set.add("Key3");
        assertEquals(set, map.keySet());
    }

    @Test
    public void values() {
        map.put("Key1", "Item1");
        map.put("Key2", "Item2");
        map.put("Key3", "Item3");
        Collection<String> collection = new HashSet<>();
        collection.add("Item1");
        collection.add("Item2");
        collection.add("Item3");
        assertEquals(collection, map.values());
    }

    @Test
    public void testCollisions() {
        for (int i = 0; i < 1_000; i++) {
            map.put("Key" + i, "Item" + i);
        }
        for (int i = 0; i < 1_000; i++) {
            assertTrue(map.containsKey("Key" + i));
        }
    }
}