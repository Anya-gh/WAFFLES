package uk.ac.warwick.cs126.structures;

public class PairLinkedList<K, V> {
    private ListElement<Pair<K, V>> head;

    public PairLinkedList(Pair<K, V> head) {
        this.head = new ListElement<>(head);
    }

    public Pair<K, V> getHead() {
        return head.getValue();
    }

    public static void main (String[] args) {
        PairLinkedList<Integer, String> MyLinkedList = new PairLinkedList<>(new Pair<Integer, String>(0, "anya"));
        MyLinkedList.add(new Pair<Integer, String>(1, "albert"));
        MyLinkedList.add(new Pair<Integer, String>(2, "anna"));
        System.out.println(MyLinkedList.get(2));
    }

    public void add(Pair<K, V> pair) {
        ListElement<Pair<K, V>> temp = head;
        ListElement<Pair<K, V>> newPair = new ListElement<>(pair);
        while (temp != null) {
            if (temp.getNext() == null) {
                temp.setNext(newPair);
                break;
            }
            else {
                temp = temp.getNext();
            }
        }
    }

    public V get(K key) {
        ListElement<Pair<K, V>> temp = head;
        if (temp != null) {
            V returnValue = temp.getValue().getValue();
            while (temp != null) {
                Pair<K, V> currentPair = temp.getValue();
                if (currentPair.getKey().equals(key)) {
                    returnValue = currentPair.getValue();
                    break;
                }
                temp = temp.getNext();
            }
            return returnValue;
        }
        else {
            return null;
        }
    }    
}