/**
 * Test class for MyHashMap, modified from textbook.
 * 
 * @author Steve Rubin
 *         12/6/23
 */
public class TestMyHashMap {
        public static void main(String[] args) {
                // Create a map
                MyHashMap<String, Integer> map = new MyHashMap<>();
                System.out.printf("Size of Map is %d and Capacity is %d\n", map.size(), map.capacity());
                map.put("Smith", 30);
                map.put("Anderson", 31);
                map.put("Lewis", 29);
                map.put("Cook", 29);
                map.put("Smith", 65);
                map.put("Bob", 69);
                map.put("Rob", 70);
                map.put("Tom", 71);
                map.put("Tim", 72);
                if (!map.isEmpty())
                        System.out.println("Entries in map: " + map);
                System.out.println("entrySet()");
                map.entrySet().forEach(System.out::println);
                System.out.println("values()");
                map.values().forEach(System.out::println);

                System.out.println("keySet()");
                map.keySet().forEach(System.out::println);

                System.out.println("Entries in map: " + map);

                System.out.println("The age for " + "Lewis is " +
                                map.get("Lewis"));

                System.out.printf("Size of Map is %d and Capacity is %d\n", map.size(), map.capacity());

                System.out.println("Is Smith in the map? " +
                                map.containsKey("Smith"));

                map.remove("Smith");
                System.out.printf("Size of Map is %d and Capacity is %d\n", map.size(), map.capacity());

                System.out.println("Is Smith in the map? " +
                                map.containsKey("Smith"));
;
                System.out.println("Is age 33 in the map? " +
                                map.containsValue(33));
                System.out.println("Is age 31 in the map? " +
                                map.containsValue(31));
                map.remove("Smith");
                System.out.println("Entries in map: " + map);

                map.clear();
                System.out.println("Entries in map: " + map);
                System.out.printf("Size of Map is %d and Capacity is %d\n", map.size(), map.capacity());

        }
}