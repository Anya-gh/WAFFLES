package uk.ac.warwick.cs126.structures;

public class Pair<K, V> {

  private K key;
  private V value;

  public Pair(K _key, V _value) {
      this.key = _key;
      this.value = _value;
  }

  // increment instances by 1
  public void setValue(V _value) {
    this.value = _value;
  }

  // set key for pair
  public void setKey(K _key) {
    this.key = _key;
  }

  // return instances
  public V getValue() {
    return this.value;
  }

  // return key
  public K getKey() {
    return this.key;
  }
}
