package uk.ac.warwick.cs126.structures;

public class PairLinkedList<K, V> {
    private ListElement<Pair<K, V>> head;

    public PairLinkedList(Pair<K, V> head) {
        this.head = new ListElement<>(head);
    }

    public static void main (String[] args) {
        PairLinkedList<Integer, String> MyLinkedList = new PairLinkedList<>(new Pair<Integer, String>(0, "anya"));
        MyLinkedList.add(new Pair<Integer, String>(1, "albert"));
        System.out.println(MyLinkedList.get(1));
    }

    public void add(Pair<K, V> pair) {
        ListElement<Pair<K, V>> temp = head;
        ListElement<Pair<K, V>> newPair = new ListElement<>(pair);
        if (temp.getNext() == null) {
            temp.setNext(newPair);
        }
        else {
            temp = temp.getNext();
        }
    }

    public V get(K key) {
        ListElement<Pair<K, V>> temp = head;
        V returnValue = temp.getValue().getValue();
        while (temp != null) {
            Pair<K, V> currentPair = temp.getValue();
            if (currentPair.getKey() == key) {
                returnValue = currentPair.getValue();
                break;
            }
            temp = temp.getNext();
        }
        return returnValue;
    }    
}