package space.harbour.java.hw3;

import java.util.Map;

public class MyHashMapExample {


    public static void main(String[] args) {
        MyHashMap<String, String> map = new MyHashMap<String, String>();

        try {
            map.put("A", "1");
            map.put("B", "2");
            map.put("C", "3");

            System.out.println("Size of map is: " + map.size());
            System.out.println("Is the map empty: " + map.isEmpty());
            System.out.println("Does is contain key 'A'? : " + map.containsKey("A"));
            System.out.println("Does is contain value '1'? : " + map.containsValue("1"));
            System.out.println("Value of key 'A' is : " + map.get("A"));
            System.out.println("Remove key 'A': " + map.remove("A")
                    + " ---> Size now: " + map.size());

            // Create copy of map
            MyHashMap<String, String> mapCopy = new MyHashMap<>();
            mapCopy.putAll(map);

            System.out.println("New map is size: " + mapCopy.size());
            System.out.println("Clear out the first map");
            map.clear();
            System.out.println("Now it has size: " + map.size());

            System.out.println("The key set of the second map is: " + mapCopy.keySet());
            System.out.println("Collection of values is: " + mapCopy.values());
            System.out.println("Let's iterate in the second map:");
            mapCopy.put("New element key", "Hello World");
            for (Map.Entry<String, String> pair : mapCopy.entrySet()) {
                System.out.println(pair.getKey() + ": " + pair.getValue());
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
