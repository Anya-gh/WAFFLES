package uk.ac.warwick.cs126.structures;

import uk.ac.warwick.cs126.models.Customer;
import uk.ac.warwick.cs126.models.Favourite;
import uk.ac.warwick.cs126.models.Restaurant;
import uk.ac.warwick.cs126.util.StringFormatter;
import uk.ac.warwick.cs126.util.ConvertToPlace;

import uk.ac.warwick.cs126.util.StringFormatter;

public class BinarySearchTree<K extends Comparable<K>, V> {
    private ListElement<Pair<K, V>> root;
    private int size;
    public BinarySearchTree() {
        size = 0;
    }

    /*public static void main(String[] args) {
        BinarySearchTree<Integer, String> BST = new BinarySearchTree<Integer,String>();
        MyArrayList<Pair<Integer, String>> myList = new MyArrayList<>();
        BST.add(10, "albert");
        BST.add(5, "lana");
        BST.add(12, "anya");
        BST.add(14, "iris");
        BST.add(11, "serena");
        System.out.println(BST.getSize());
        myList = BST.inOrder(BST.getRoot(), new MyArrayList<>());
        for (int i = 0; i < myList.size(); i++) {
            System.out.println(myList.get(i).getValue());
        }
        BST.remove(12);
        BST.remove(11);
        BST.remove(14);
        BST.remove(5);
        System.out.println(BST.getSize());
        myList = BST.inOrder(BST.getRoot(), new MyArrayList<>());
        for (int i = 0; i < myList.size(); i++) {
            System.out.println(myList.get(i).getValue());
        }
        BST.add(34, "dawn");
        BST.add(24, "lillie");
        BST.add(11, "serena");
        System.out.println(BST.getSize());
        myList = BST.inOrder(BST.getRoot(), new MyArrayList<>());
        for (int i = 0; i < myList.size(); i++) {
            System.out.println(myList.get(i).getValue());
        }
        BST.remove(11);
        BST.remove(10);
        BST.add(11, "serena");
        System.out.println(BST.getSize());
        myList = BST.inOrder(BST.getRoot(), new MyArrayList<>());
        for (int i = 0; i < myList.size(); i++) {
            System.out.println(myList.get(i).getValue());
        }
    }*/

    public ListElement<Pair<K, V>> getRoot() {
        return root;
    }

    public void setRoot(ListElement<Pair<K, V>> newRoot) {
        this.root = newRoot;
    }

    public int getSize() {
        return size;
    }

    public void incSize() {
        size++;
    }

    public void decSize() {
        size--;
    }

    public static <K extends Comparable<K>,V>BinarySearchTree<K, V> makeTree(MyArrayList<Pair<K, V>> treeNodes) {
        BinarySearchTree<K, V> newTree = new BinarySearchTree<>();
        for (int i = 0; i < treeNodes.size(); i++) {
            Pair<K, V> currentPair = treeNodes.get(i);
            newTree.add(currentPair.getKey(), currentPair.getValue());
        }
        return newTree;
    } 

    public boolean add(K key, V value) {
        boolean returnValue = true;
        ListElement<Pair<K, V>> temp = root;
        ListElement<Pair<K, V>> newNode = new ListElement<Pair<K,V>>(new Pair<K, V>(key, value));
        if (root == null) {
            root = newNode;
            size++;
            return true;
        }
        while (temp != null) {
            K tempKey = temp.getValue().getKey();
            if (key.compareTo(tempKey) < 0) {
                if (temp.getPrevious() == null) {
                    temp.setPrevious(newNode);
                    size++;
                    break;
                }
                else {
                    temp = temp.getPrevious();
                }
            }
            else if (key.compareTo(tempKey) > 0) {
                if (temp.getNext() == null) {
                    temp.setNext(newNode);
                    size++;
                    break;
                }
                else {
                    temp = temp.getNext();
                }
            }
            else {
                if ((value instanceof Customer) && (key instanceof String)) {
                    Customer addCustomer = (Customer) value;
                    Customer currentCustomer = (Customer) temp.getValue().getValue();
                    String firstName = addCustomer.getFirstName();
                    String currentFirstName = currentCustomer.getFirstName();
                    if (firstName.compareTo(currentFirstName) < 0) {
                        if (temp.getPrevious() == null) {
                            temp.setPrevious(newNode);
                            size++;
                            break;
                        }
                        else {
                            temp = temp.getPrevious();
                        }
                    }
                    else if (firstName.compareTo(currentFirstName) > 0) {
                        if (temp.getNext() == null) {
                            temp.setNext(newNode);
                            size++;
                            break;
                        }
                        else {
                            temp = temp.getNext();
                        }
                    }
                    else {
                        Long ID = addCustomer.getID();
                        Long currentID = currentCustomer.getID();
                        if (ID.compareTo(currentID) < 0) {
                            if (temp.getPrevious() == null) {
                                temp.setPrevious(newNode);
                                size++;
                                break;
                            }
                            else {
                                temp = temp.getPrevious();
                            }
                        }
                        else if (ID.compareTo(currentID) > 0) {
                            if (temp.getNext() == null) {
                                temp.setNext(newNode);
                                size++;
                                break;
                            }
                            else {
                                temp = temp.getNext();
                            }
                        }
                        else {
                            // the customer already exists
                            returnValue = false;
                            break;
                        }
                    }
                }
                else if ((value instanceof Restaurant) && (key instanceof String)) {
                    Restaurant addRestaurant = (Restaurant) value;
                    Restaurant currentRestaurant = (Restaurant) temp.getValue().getValue();
                    Long addRestaurantID = (Long) addRestaurant.getID();
                    Long currentRestaurantID = (Long) currentRestaurant.getID();
                    if (addRestaurantID.compareTo(currentRestaurantID) < 0) {
                        if (temp.getPrevious() == null) {
                            temp.setPrevious(newNode);
                            size++;
                            break;
                        }
                        else {
                            temp = temp.getPrevious();
                        }
                    }
                    else if (addRestaurantID.compareTo(currentRestaurantID) > 0) {
                        if (temp.getNext() == null) {
                            temp.setNext(newNode);
                            size++;
                            break;
                        }
                        else {
                            temp = temp.getNext();
                        }
                    }
                    else {
                        returnValue = false;
                        break;
                    }
                }
                else {
                    returnValue = false;
                    break;
                }
            }
        }
        return returnValue;
    }

    public static MyArrayList<Customer> inOrderSearch(ListElement<Pair<Long, Customer>> node, MyArrayList<Customer> arrayList, String searchTerm) {
        MyArrayList<Customer> leftArrayList = new MyArrayList<>();
        MyArrayList<Customer> currentNode = new MyArrayList<>();
        MyArrayList<Customer> rightArrayList = new MyArrayList<>();
        if (node != null) {
            String checkValue = (node.getValue().getValue().getFirstName() + " " + node.getValue().getValue().getLastName()).toLowerCase();
            String checkTerm = searchTerm.toLowerCase();
            leftArrayList = inOrderSearch(node.getPrevious(), arrayList, searchTerm);
            if ((checkValue.contains(checkTerm)) && (node.isActive())) {
                currentNode.add(node.getValue().getValue());
            }
            rightArrayList = inOrderSearch(node.getNext(), arrayList, searchTerm);
        }
        MyArrayList<Customer> returnArrayList = MyArrayList.concat(MyArrayList.concat(leftArrayList, currentNode), rightArrayList); 
        return returnArrayList;
    }

    public static void inOrderSearch(ListElement<Pair<Long, Customer>> node, BinarySearchTree<String, Customer> tree, String searchTerm) {
        if (node != null) {
            String checkValue = StringFormatter.convertAccentsFaster(node.getValue().getValue().getFirstName() + " " + node.getValue().getValue().getLastName()).toLowerCase();
            String checkTerm = searchTerm.toLowerCase();
            // left tree
            inOrderSearch(node.getPrevious(), tree, searchTerm);
            // current
            if ((checkValue.contains(checkTerm)) && (node.isActive())) {
                Customer customer = node.getValue().getValue();
                tree.add(customer.getLastName(), customer);
            }
            // right tree
            inOrderSearch(node.getNext(), tree, searchTerm);
        }
    }

    public static void inOrderSearchRestaurant(ListElement<Pair<Long, Restaurant>> node, BinarySearchTree<String, Restaurant> tree, String searchTerm, ConvertToPlace placeConverter) {
        if (node != null) {
            String placeName = placeConverter.convert(node.getValue().getValue().getLatitude(), node.getValue().getValue().getLongitude()).getName();
            String checkValue = StringFormatter.convertAccentsFaster(node.getValue().getValue().getName() + " " + node.getValue().getValue().getCuisine() + " " + placeName).toLowerCase();
            String checkTerm = searchTerm.toLowerCase();
            // left tree
            inOrderSearchRestaurant(node.getPrevious(), tree, searchTerm, placeConverter);
            // current
            if ((checkValue.contains(checkTerm)) && (node.isActive())) {
                Restaurant restaurant = node.getValue().getValue();
                tree.add(restaurant.getName(), restaurant);
            }
            // right tree
            inOrderSearchRestaurant(node.getNext(), tree, searchTerm, placeConverter);
        }
    }

    public static <K extends Comparable<K>> int inOrder(ListElement<Pair<K, Customer>> node, Customer[] array, int index) {
        if (node != null) {
            index = inOrder(node.getPrevious(), array, index);
            if (node.isActive()) {
                array[index] = node.getValue().getValue();
                index++;
            }
            index = inOrder(node.getNext(), array, index);
        }
        return index;
    }

    public static <K extends Comparable<K>> int inOrder(ListElement<Pair<K, Restaurant>> node, Restaurant[] array, int index) {
        if (node != null) {
            index = inOrder(node.getPrevious(), array, index);
            if (node.isActive()) {
                array[index] = node.getValue().getValue();
                index++;
            }
            index = inOrder(node.getNext(), array, index);
        }
        return index;
    }

    public static <K extends Comparable<K>> int inOrder(ListElement<Pair<K, Favourite>> node, Favourite[] array, int index) {
        if (node != null) {
            index = inOrder(node.getPrevious(), array, index);
            if (node.isActive()) {
                array[index] = node.getValue().getValue();
                index++;
            }
            index = inOrder(node.getNext(), array, index);
        }
        return index;
    }

    public V get(K key) {
        ListElement<Pair<K, V>> temp = root;
        while (temp != null) {
            K tempKey = temp.getValue().getKey();
            if (key.compareTo(tempKey) < 0) {
                temp = temp.getPrevious();
            }
            else if (key.compareTo(tempKey) > 0) {
                temp = temp.getNext();
            }
            else {
                return temp.getValue().getValue();
            }
        }
        return null;
    }

    public static <K extends Comparable<K>, V> MyArrayList<Pair<K, V>> inOrder(ListElement<Pair<K, V>> node, MyArrayList<Pair<K, V>> arrayList) {
        MyArrayList<Pair<K, V>> leftArrayList = new MyArrayList<>();
        MyArrayList<Pair<K, V>> currentNode = new MyArrayList<>();
        MyArrayList<Pair<K, V>> rightArrayList = new MyArrayList<>();
        if (node != null) {
            leftArrayList = inOrder(node.getPrevious(), arrayList);
            currentNode.add(node.getValue());
            rightArrayList = inOrder(node.getNext(), arrayList);
        }
        MyArrayList<Pair<K, V>> returnArrayList = MyArrayList.concat(MyArrayList.concat(leftArrayList, currentNode), rightArrayList); 
        return returnArrayList;
    }

    private MyArrayList<Pair<K, V>> inOrder(ListElement<Pair<K, V>> node, MyArrayList<Pair<K, V>> arrayList, K excludeKey) {
        MyArrayList<Pair<K, V>> leftArrayList = new MyArrayList<>();
        MyArrayList<Pair<K, V>> currentNode = new MyArrayList<>();
        MyArrayList<Pair<K, V>> rightArrayList = new MyArrayList<>();
        if (node != null) {
            leftArrayList = inOrder(node.getPrevious(), arrayList, excludeKey);
            if (node.getValue().getKey().equals(excludeKey) == false) {
                currentNode.add(node.getValue());
            }
            else {
                size--; 
            }
            rightArrayList = inOrder(node.getNext(), arrayList, excludeKey);
        }
        MyArrayList<Pair<K, V>> returnArrayList = MyArrayList.concat(MyArrayList.concat(leftArrayList, currentNode), rightArrayList); 
        return returnArrayList;
    }

    public void remove(K key) {
        /*if (this.get(key) != null) {
            root = makeTree(inOrder(this.getRoot(), new MyArrayList<>(), key)).getRoot();
        }*/
        ListElement<Pair<K, V>> temp = root;
        while (temp != null) {
            K tempKey = temp.getValue().getKey();
            if (key.compareTo(tempKey) < 0) {
                temp = temp.getPrevious();
            }
            else if (key.compareTo(tempKey) > 0) {
                temp = temp.getNext();
            }
            else {
                temp.setActive(false);
                size--;
                break;
            }
        }
    }
}