package uk.ac.warwick.cs126.structures;

public class LinkedListElement {
    public ListElement value;
    public ListElement next;
    
    public LinkedListElement(Object value1, String value2) {
        value = new ListElement(value1);
        next = new ListElement(value2);
    }

    public static void main(String[] args) {
        LinkedListElement myElem = new LinkedListElement("anya", "albert");
    }
}