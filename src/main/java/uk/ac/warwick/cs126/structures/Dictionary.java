package uk.ac.warwick.cs126.structures;

public class Dictionary<K> {
  private MyArrayList<Pair<K, Integer>> dictionary;
  private int size;

  public Dictionary() {
    dictionary = new MyArrayList<>();
    size = dictionary.size();
  }

  public Pair<K, Integer> getEntry(K key) {
    Pair<K, Integer> returnValue = null;
    for (int i = 0; i < this.size; i++)
    {
      Pair<K, Integer> currentPair = dictionary.get(i);
      if (currentPair.getKey() == key)
      {
        returnValue = currentPair;
      }
    }
    return returnValue;
  }

  public Integer getInstances(K key) {
    Integer returnValue = -1;
    Pair<K, Integer> pair = this.getEntry(key);
    int index = this.dictionary.indexOf(pair);
    if (index != -1) {
      returnValue = pair.getValue();
    }
    return (int) returnValue;
  }

  private void updatePair(K key) {
    Pair<K, Integer> pair = this.getEntry(key);
    int index = this.dictionary.indexOf(pair);
    if (index == -1) {
      throw new ArrayIndexOutOfBoundsException("key not found: " + key);
    }
    pair.setValue(pair.getValue() + 1);
    this.dictionary.set(index, pair);
  }

  public void addEntry(K key) {
    Pair<K, Integer> pair = this.getEntry(key);
    if (pair == null)
    {
      Pair<K, Integer> newPair = new Pair<K, Integer>(key, 1);
      dictionary.add(newPair);
      this.size += 1;
    }
    else {
      this.updatePair(key);
    }
  }
}
