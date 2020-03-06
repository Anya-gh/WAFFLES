package uk.ac.warwick.cs126.structures;

public class LinkedList<E> {
  private ListElement<E> head;

  public LinkedList(E val) {
    head = new ListElement<E>(val);
  }

  public ListElement<E> getHead() {
    return this.head;
  }

  public ListElement<E> get(E val) {
    ListElement<E> returnValue = null;
    ListElement<E> temp = head;
    while (temp.getNext() != null) {
      if (temp.getValue() == val) {
        returnValue = temp;
        break;
      }
      temp = temp.getNext();
    }
    return returnValue;
  }

  public void add(E val) {
    ListElement<E> newElement = new ListElement<E>(val);
    ListElement<E> temp = head;
    while (temp.getNext() != null) {
      temp = temp.getNext();
    }
    temp.setNext(newElement);
  }

  public String toString() {
    ListElement<E> temp = head;
    String toString = "";
    while (temp != null) {
      toString += temp.getValue();
      temp = temp.getNext();
    }
    return toString;
  }

}
