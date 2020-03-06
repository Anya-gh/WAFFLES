package uk.ac.warwick.cs126.structures;

import uk.ac.warwick.cs126.models.Customer;

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
            }
        }
        return returnValue;
    }

    /*public static BinarySearchTree<String, Customer> add(Customer customer, BinarySearchTree<String, Customer> customerBST) {
        ListElement<Pair<String, Customer>> temp = customerBST.getRoot();
        ListElement<Pair<String, Customer>> newNode = new ListElement<Pair<String, Customer>>(new Pair<String, Customer>(customer.getLastName(), customer));
        if (temp == null) {
            System.out.println("tried to set root");
            customerBST.setRoot(newNode);
            customerBST.incSize();
            return customerBST;
        }
        while (temp != null) {
            String tempKey = temp.getValue().getKey();
            String lastName = customer.getLastName();
            System.out.println("beginning! " + customer.getFirstName());
            if (lastName.compareTo(tempKey) < 0) {
                if (temp.getPrevious() == null) {
                    temp.setPrevious(newNode);
                    customerBST.incSize();
                    break;
                }
                else {
                    temp = temp.getPrevious();
                }
            }
            else if (lastName.compareTo(tempKey) > 0) {
                if (temp.getNext() == null) {
                    temp.setNext(newNode);
                    customerBST.decSize();
                    break;
                }
                else {
                    temp = temp.getNext();
                }
            }
            else {
                System.out.println("moving to first name");
                tempKey = temp.getValue().getValue().getFirstName();
                String firstName = customer.getFirstName();
                if (firstName.compareTo(tempKey) < 0) {
                    if (temp.getPrevious() == null) {
                        temp.setPrevious(newNode);
                        customerBST.incSize();
                        break;
                    }
                    else {
                        temp = temp.getPrevious();
                    }
                }
                else if (firstName.compareTo(tempKey) > 0) {
                    if (temp.getNext() == null) {
                        temp.setNext(newNode);
                        customerBST.decSize();
                        break;
                    }
                    else {
                        temp = temp.getNext();
                    }
                }
                else {
                    System.out.println("moving to ID");
                    Long tempID = temp.getValue().getValue().getID();
                    Long ID = customer.getID();
                    if (ID.compareTo(tempID) < 0) {
                        if (temp.getPrevious() == null) {
                            temp.setPrevious(newNode);
                            customerBST.incSize();
                            break;
                        }
                        else {
                            temp = temp.getPrevious();
                        }
                    }
                    else if (ID.compareTo(tempID) > 0) {
                        if (temp.getNext() == null) {
                            temp.setNext(newNode);
                            customerBST.decSize();
                            break;
                        }
                        else {
                            temp = temp.getNext();
                        }
                    }
                }
            }
        }
        if (temp != null) {
            if (temp.getNext() != null) {
                System.out.println("added: " + temp.getNext().getValue().getValue().getFirstName());
            }
            else {
                System.out.println("added: " + temp.getPrevious().getValue().getValue().getFirstName());
            }
        }
        return customerBST;
    }*/

    /*public static void inOrderSearch(ListElement<Pair<Long, Customer>> node, BinarySearchTree<Long, Customer> customerTree, BinarySearchTree<String, Customer> newTree, String term) {
        if (node != null) {
            inOrderSearch(node.getPrevious(), customerTree, newTree, term);
            String checkValue = (node.getValue().getValue().getFirstName() + " " + node.getValue().getValue().getLastName()).toLowerCase();
            String checkTerm = term.toLowerCase();
            if (checkValue.contains(checkTerm)) {
                System.out.println("yay " + checkValue + " " + term);
                add(node.getValue().getValue(), newTree);
            }
            inOrderSearch(node.getNext(), customerTree, newTree, term);
        }
    }*/

    public static MyArrayList<Customer> inOrderSearch(ListElement<Pair<Long, Customer>> node, MyArrayList<Customer> arrayList, String searchTerm) {
        MyArrayList<Customer> leftArrayList = new MyArrayList<>();
        MyArrayList<Customer> currentNode = new MyArrayList<>();
        MyArrayList<Customer> rightArrayList = new MyArrayList<>();
        if (node != null) {
            String checkValue = (node.getValue().getValue().getFirstName() + " " + node.getValue().getValue().getLastName()).toLowerCase();
            String checkTerm = searchTerm.toLowerCase();
            leftArrayList = inOrderSearch(node.getPrevious(), arrayList, searchTerm);
            if (checkValue.contains(checkTerm)) {
                currentNode.add(node.getValue().getValue());
            }
            rightArrayList = inOrderSearch(node.getNext(), arrayList, searchTerm);
        }
        MyArrayList<Customer> returnArrayList = MyArrayList.concat(MyArrayList.concat(leftArrayList, currentNode), rightArrayList); 
        return returnArrayList;
    }

    public static void inOrderSearch(ListElement<Pair<Long, Customer>> node, BinarySearchTree<String, Customer> tree, String searchTerm) {
        if (node != null) {
            String checkValue = (node.getValue().getValue().getFirstName() + " " + node.getValue().getValue().getLastName()).toLowerCase();
            String checkTerm = searchTerm.toLowerCase();
            // left tree
            inOrderSearch(node.getPrevious(), tree, searchTerm);
            // current
            if (checkValue.contains(checkTerm)) {
                Customer customer = node.getValue().getValue();
                tree.add(customer.getLastName(), customer);
            }
            // right tree
            inOrderSearch(node.getNext(), tree, searchTerm);
        }
    }

    /*public static <K extends Comparable<K>> void inOrder((ListElement<Pair<K, Customer>> node, Customer[] customerArray, int count) {
        if (node != null) {

        }
    }*/
    

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
        if (this.get(key) != null) {
            root = makeTree(inOrder(this.getRoot(), new MyArrayList<>(), key)).getRoot();
        }
    }
}