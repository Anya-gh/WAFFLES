package uk.ac.warwick.cs126.structures;

import uk.ac.warwick.cs126.models.Favourite;
import uk.ac.warwick.cs126.util.ArrayCompare;
import java.util.Date;

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
        return temp.get(key).getValue();
      }
      else {
        return null;
      }
  }

  public void remove(K key, V value) {
    int hashCode = this.getIndex(key);
    PairLinkedList<K, V> list = hashArray[hashCode];
    if (list != null) {
      ListElement<Pair<K, V>> temp = list.getHead();
      while (temp != null) {
        K tempKey = temp.getValue().getKey();
        V tempValue = temp.getValue().getValue();
        if ((tempKey.equals(key)) && (tempValue.equals(value))) {
          temp.setActive(false);
          break;
        }
        temp = temp.getNext();
      }
    }
  }

  public Favourite getOldestFavourite(K key, Long[] ids) {
    ListElement<Pair<K, V>> linkedListHead = hashArray[this.getIndex(key)].getHead();
    if ((linkedListHead.getValue().getKey() instanceof Long) && (linkedListHead.getValue().getValue() instanceof Pair)) {
      ListElement<Pair<Long, Pair<Long[], Favourite>>> temp = (ListElement) linkedListHead;
      Pair<Long[], Favourite> oldestFavouritePair = temp.getValue().getValue(); 
      Favourite oldestFavourite = oldestFavouritePair.getValue();
      while (temp != null) {
        if (temp.isActive()) {
          Long[] array = (Long[]) temp.getValue().getValue().getKey();
          Favourite favourite = temp.getValue().getValue().getValue();
          Pair<Long[], Favourite> currentPair = new Pair<>(array, favourite);
          if (ArrayCompare.equals(currentPair.getKey(), ids)) {
            if (oldestFavourite.getDateFavourited().compareTo(currentPair.getValue().getDateFavourited()) > 0) {
              oldestFavourite = currentPair.getValue();
            }
          }
        }
        temp = temp.getNext();
      }
      return oldestFavourite;
    }
    return null;
  }
}
