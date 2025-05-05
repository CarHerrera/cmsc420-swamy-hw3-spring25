public class CuckooHash<K, V> {
    private static final int DEFAULT_CAPACITY = 16;
    private static final int MAX_REHASH_ATTEMPTS = 20;

    private Entry<K, V>[] table1;
    private Entry<K, V>[] table2;
    private int size;

    public CuckooHash() {
        this(DEFAULT_CAPACITY);
    }

    public CuckooHash(int capacity) {
        table1 = new Entry[capacity];
        table2 = new Entry[capacity];
        size = 0;
    }

    public void put(K key, V value) {
        Entry<K, V> newEntry = new Entry<>(key, value);
        for (int i = 0; i < MAX_REHASH_ATTEMPTS; i++) {
            newEntry = placeInTable(newEntry, table1, 1);
            if (newEntry == null) return;

            newEntry = placeInTable(newEntry, table2, 2);
            if (newEntry == null) return;

            rehash();
        }
    }

    public V get(K key) {
        int hash1 = hash1(key);
        if (table1[hash1] != null && table1[hash1].key.equals(key)) {
            return table1[hash1].value;
        }

        int hash2 = hash2(key);
        if (table2[hash2] != null && table2[hash2].key.equals(key)) {
            return table2[hash2].value;
        }

        return null;
    }

    public Entry<K,V> getRaw(K key) {
        int hash1 = hash1(key);
        if (table1[hash1] != null && table1[hash1].key.equals(key)) {
            return table1[hash1];
        }

        int hash2 = hash2(key);
        if (table2[hash2] != null && table2[hash2].key.equals(key)) {
            return table2[hash2];
        }

        return null;
    }

    public void remove(K key) {
    

        int hash1 = hash1(key);
        if (table1[hash1] != null && table1[hash1].key.equals(key)) {
            table1[hash1] = null;
            size--;
            return;
        }

        int hash2 = hash2(key);
        if (table2[hash2] != null && table2[hash2].key.equals(key)) {
            table2[hash2] = null;
            size--;
        }
    }
    
    public boolean containsKey(K key) {
        return get(key) != null;
    }

    public int size() {
        return size;
    }

    private Entry<K, V> placeInTable(Entry<K, V> entry, Entry<K, V>[] table, int tableNumber) {
        int hash = (tableNumber == 1) ? hash1(entry.key) : hash2(entry.key);

        if (table[hash] == null) {
            table[hash] = entry;
            size++;
            return null;
        } else {
            Entry<K, V> displaced = table[hash];
            table[hash] = entry;
            return displaced;
        }
    }

    private void rehash() {
        Entry<K, V>[] oldTable1 = table1;
        Entry<K, V>[] oldTable2 = table2;

        int newCapacity = table1.length * 2;
        table1 = new Entry[newCapacity];
        table2 = new Entry[newCapacity];
        size = 0;

        for (Entry<K, V> entry : oldTable1) {
            if (entry != null) put(entry.key, entry.value);
        }
        for (Entry<K, V> entry : oldTable2) {
            if (entry != null) put(entry.key, entry.value);
        }
    }

    private int hash1(K key) {
        return Math.abs(key.hashCode()) % table1.length;
    }

    private int hash2(K key) {
        return (Math.abs(key.hashCode() * 31) + 1) % table2.length;
    }

    public static void main(String[] args){
        CuckooHash<Task, Task> test = new CuckooHash<>();
        Task t1 = new Task("T1", 2, null);
        test.put(t1, t1);
        test.containsKey(t1);
        
    }
}   
class Entry<K, V> {
    K key;
    V value;

    Entry(K key, V value) {
        this.key = key;
        this.value = value;
    }
}