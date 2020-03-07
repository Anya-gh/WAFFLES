package uk.ac.warwick.cs126.structures;

import uk.ac.warwick.cs126.models.Favourite;
import java.util.Date;

public class PairLinkedList<K, V> {
    private ListElement<Pair<K, V>> head;

    public PairLinkedList(Pair<K, V> head) {
        this.head = new ListElement<>(head);
    }

    public ListElement<Pair<K, V>> getHead() {
        return head;
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
            if (temp.getValue().getKey() instanceof Long[]) {
                Favourite tempFavourite = (Favourite) temp.getValue().getValue();
                Long tempFavouriteID = tempFavourite.getID();
                Favourite newFavourite = (Favourite) newPair.getValue().getValue();
                Long newFavouriteID = newFavourite.getID();
                if (tempFavouriteID.equals(newFavouriteID)) {
                    temp.setActive(false);
                    break;
                }
            }
            if (temp.getNext() == null) {
                temp.setNext(newPair);
                break;
            }
            else {
                temp = temp.getNext();
            }
        }
    }

    public Pair<K, V> get(K key) {
        ListElement<Pair<K, V>> temp = head;
        Pair<K, V> nullPair = new Pair<K, V>(key, null);
        if (temp != null) {
            while (temp != null) {
                Pair<K, V> currentPair = temp.getValue();
                if (currentPair.getKey().equals(key)) {
                    return currentPair;
                }
                temp = temp.getNext();
            }
        }
        return nullPair;
    }    
}