package uk.ac.warwick.cs126.structures;

public class CharacterHashMap {
  public PairLinkedList<Character, String>[] hashArray;
  int capacity;

  public CharacterHashMap() {
      capacity = 2000;
      hashArray = new PairLinkedList[capacity];
  }

  public static void main(String[] args) {
      CharacterHashMap MyHashMap = new CharacterHashMap();
      MyHashMap.add('ℳ', "M");
      System.out.println(MyHashMap.get('ℳ'));
  }

  public int hashCode(char character) {
      int code = ((int) character) % (capacity);
      return code;
  }

  public void add(char key, String value) {
      int hashCode = this.hashCode(key);
      System.out.println(hashCode);
      Pair<Character, String> newPair = new Pair<Character, String>(key, value); 
      if (hashArray[hashCode] == null) {
          PairLinkedList<Character, String> newLinkedList = new PairLinkedList<Character, String>(newPair);
          hashArray[hashCode] = newLinkedList;
      }
      else {
          hashArray[hashCode].add(newPair);
      }
  }

  public String get(char key) {
      int hashCode = this.hashCode(key);
      PairLinkedList<Character, String> temp = hashArray[hashCode];
      return temp.get(key);
  }
}
