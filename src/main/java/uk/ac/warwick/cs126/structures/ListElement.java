package uk.ac.warwick.cs126.structures;

public class ListElement<E> {
  private E value;
  private boolean active;
  private ListElement<E> previous;
  private ListElement<E> next;

  public ListElement(E _value) {
    this.value = _value;
    this.active = true;
  }

  public E getValue() {
    return this.value;
  }

  public void setValue(E _value) {
    this.value = _value;
  }

  public void setNext(ListElement<E> elem) {
    this.next = elem;
  }

  public void setPrevious(ListElement<E> elem) {
    this.previous = elem;
  }

  public ListElement<E> getNext() {
    return this.next;
  }

  public ListElement<E> getPrevious() {
    return this.previous;
  }

  public void setActive(boolean value) {
    this.active = value;
  }

  public boolean isActive() {
    return this.active;
  }
}
