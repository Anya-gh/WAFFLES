package uk.ac.warwick.cs126.structures;

public class HashMap<K, V> {
  public PairLinkedList<K, V>[] hashArray;
  int capacity;

  public HashMap() {
      capacity = 2000;
      hashArray = new PairLinkedList[capacity];
  }

  public int getIndex(K key) {
      int code = (key.hashCode() % (capacity));
      return Math.abs(code);
  }

  public void add(K key, V value) {
      int hashCode = this.getIndex(key);
      Pair<K, V> newPair = new Pair<K, V>(key, value); 
      if (hashArray[hashCode] == null) {
          PairLinkedList<K, V> newLinkedList = new PairLinkedList<K, V>(newPair);
          hashArray[hashCode] = newLinkedList;
      }
      else {
          hashArray[hashCode].add(newPair);
      }
  }

  public V get(K key) {
      int hashCode = this.getIndex(key);
      PairLinkedList<K, V> temp = hashArray[hashCode];
      if (temp != null) {
        return temp.get(key);
      }
      else {
        return null;
      }
  }
}
