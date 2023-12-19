import java.util.ArrayList;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * The MyHashMap class from the book, modified to use quadratic probing
 * 
 * @author Steve Rubin
 *         12/6/23
 */

public class MyHashMap<K, V> implements MyMap<K, V> {
    private static int DEFAULT_INITIAL_CAPACITY = 4; // Define the default hash-table size. Must be a power of 2
    private static int MAXIMUM_CAPACITY = 1 << 30; // Define the maximum hash-table size. 1 << 30 is same as 2^30
    private int capacity; // Current hash-table capacity. Capacity is a power of 2
    private static float DEFAULT_MAX_LOAD_FACTOR = 0.5f; // Define default load factor
    private float loadFactorThreshold; // Specify a load factor used in the hash table
    private int size = 0; // The number of entries in the map
    private ArrayList<Entry<K, V>> table; // Hash table is an ArrayList

    /** Construct a map with the default capacity and load factor */
    public MyHashMap() {
        this(DEFAULT_INITIAL_CAPACITY, DEFAULT_MAX_LOAD_FACTOR);
    }

    /**
     * Construct a map with the specified initial capacity and
     * default load factor
     */
    public MyHashMap(int initialCapacity) {
        this(initialCapacity, DEFAULT_MAX_LOAD_FACTOR);
    }

    /**
     * Construct a map with the specified initial capacity
     * and load factor
     */
    public MyHashMap(int initialCapacity, float loadFactorThreshold) {
        if (initialCapacity > MAXIMUM_CAPACITY)
            this.capacity = MAXIMUM_CAPACITY;
        else
            this.capacity = trimToPowerOf2(initialCapacity);

        this.loadFactorThreshold = loadFactorThreshold;
        newTable();

    }

    /**
     * Create a new ArrayList for the hash table, fill with nulls
     * 
     */
    private void newTable() {
        ArrayList<Entry<K, V>> newTable = new ArrayList<>(capacity);
        for (int i = 0; i < capacity; i++) {
            newTable.add(null);
            
        }
        table = newTable;

    }

    @Override
    /**
     * Remove all of the entries from this map
     */
    public void clear() {
        size = 0;
        removeEntries();
    }

    /**
     * Return the capacity of the hash table
     * 
     * @return capacity
     */
    public int capacity() {
        return capacity;
    }

    @Override
    /**
     * Return true if the specified key is in the map
     * 
     * @param key the key whose presence in the map is to be tested
     * @return true if key is in the map
     */
    public boolean containsKey(K key) {
        if (get(key) != null)
            return true;
        else
            return false;
    }

    @Override
    /**
     * Return true if this map contains the value
     * 
     * @param value the value whose presence in this map is to be tested
     * @return true if this map contains the value
     */
    public boolean containsValue(V value) {

        for (int i = 0; i < capacity; i++) {
            if (table.get(i) != null && table.get(i).getValue() == value)
                return true;
        }
        return false;
    }

    @Override

    /**
     * Return a set of entries in the map
     * 
     * @return a set of entries in the map
     */
    public java.util.Set<Entry<K, V>> entrySet() {
        return IntStream.range(0, capacity)
                .filter(i -> table.get(i) != null)
                .mapToObj(table::get)
                .collect(Collectors.toSet());
    }

    @Override
    /**
     * Return the value that matches the specified key
     * 
     * @param key the key whose associated value is to be returned
     * @return the value to which the specified key is mapped, or null if this map
     *         contains no mapping for the key
     */
    public V get(K key) {
        int index = hash(key.hashCode());
        int offset = 0;

        while (table.get(index) != null) {
            if (table.get(index).getKey().equals(key)) {
                return table.get(index).getValue();
            }
            index = hash(index, ++offset);
        }

        return null;
    }

    @Override
    /**
     * Return true if this map contains no entries
     *
     * @return true if this map contains no entries
     */
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    /**
     * Return a set consisting of the keys in this map
     * 
     * @return a set consisting of the keys in this map
     */
    public java.util.Set<K> keySet() {
        return IntStream.range(0, capacity)
                .filter(i -> table.get(i) != null)
                .mapToObj(i -> table.get(i).getKey())
                .collect(Collectors.toSet());
    }

    @Override
    /**
     * Add an entry (key, value) into the map
     * 
     * @param key   the key to be added
     * @param value the value to be added
     * @return the previous value associated with key, or null if there was no
     *         mapping for key
     */
    public V put(K key, V value) {
        int index = hash(key.hashCode());
        int offset = 0;

        while (table.get(index) != null) {
            if (table.get(index).getKey().equals(key)) { // change value of key if key already exists
                Entry<K, V> entry = table.get(index);
                V oldValue = entry.getValue();
                entry.value = value;
                table.set(index, entry);
                return oldValue;
            }

            index = hash(index, ++offset); // overloaded hash method for quadratic probing
        }

        // check the loadfactor, rehash if needed
        if (size >= capacity * loadFactorThreshold) {
            if (capacity == MAXIMUM_CAPACITY)
                throw new RuntimeException("Exceeding maximum capacity");
            rehash();
        }

        table.set(index, new Entry<>(key, value)); // new entry
        size++;
        return null; // No previous value
    }

    @Override
    /**
     * Remove the entries for the specified key
     * 
     * @param key the key whose entry is to be removed from the map
     */
    public void remove(K key) {
        int index = hash(key.hashCode());
        int offset = 0;

        // Remove the first entry that matches the key
        while (table.get(index) != null) {
            if (table.get(index).getKey().equals(key)) {
                table.set(index, null);
                size--;
                break; // No sense in looking any futther
            }
            index = hash(index, ++offset); // Overloaded hash method for quadratic probing
        }
    }

    @Override
    /**
     * Return the number of entries in this map
     * 
     * @return the number of entries in this map
     */
    public int size() {
        return size;
    }


    @Override
    /**
     * Return a set consisting of values in this map
     * 
     * @return a set consisting of values in this map
     */
    public java.util.Set<V> values() {
        return IntStream.range(0, capacity)
                .filter(i -> table.get(i) != null)
                .mapToObj(i -> table.get(i).getValue())
                .collect(Collectors.toSet());
    }

    /**
     * Index of the hash code
     * 
     * @param hashCode
     * @return returns index after hashing
     */
    private int hash(int hashCode) {
        return (supplementalHash(hashCode) & 0x7FFFFFFF % capacity); // 0x7FFFFFFF in case hashCode is negative
    }

    /**
     * Index of the hash code (Quadratic probing version)
     * 
     * @param index  the index of the previous hashcode
     * @param offset the number of previous collisions
     * @return returns index to for
     */

    private int hash(int index, int offset) {
        return (index + offset * offset) % capacity;
    }

    /**
     * Ensure the hashing is evenly distributed
     * 
     * @param h the hashCode of the key
     * @return returns a supplemental hash value
     */
    private static int supplementalHash(int h) {
        h ^= (h >>> 20) ^ (h >>> 12);
        return h ^ (h >>> 7) ^ (h >>> 4);
    }

    /**
     * Return a power of 2 for initialCapacity
     *
     * @param initialCapacity
     * @return returns a power of 2
     */
    private int trimToPowerOf2(int initialCapacity) {
        int capacity = 1;
        while (capacity < initialCapacity) {
            capacity <<= 1;
        }

        return capacity;
    }

    /**
     * Remove all entries from map
     * 
     */
    private void removeEntries() {
        table.clear();
    }

    /**
     * Rehash the map
     * 
     */
    private void rehash() {
        ArrayList<Entry<K, V>> oldTable = table; // Store the old table
        capacity <<= 1; // Double capacity
        size = 0;

        newTable(); // Create a new table

        oldTable.stream()
                .filter(Objects::nonNull)
                .forEach(entry -> put(entry.getKey(), entry.getValue()));
    }

    @Override
    /**
     * Return a string repersentation for this map
     * 
     * @return a string repersentation for this map
     */
    public String toString() {
        return table.stream()
                .filter(Objects::nonNull)
                .map(Object::toString)
                .collect(Collectors.joining(",", "[", "]"));
    }

}