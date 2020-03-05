package uk.ac.warwick.cs126.structures;

public class BinarySearchTree<K extends Comparable<K>, V> {
    private ListElement<Pair<K, V>> root;
    private MyArrayList<Pair<K, V>> nodes;
    private int size;
    public BinarySearchTree() {
        nodes = new MyArrayList<>();
    }

    /*public static void main(String[] args) {
        BinarySearchTree<Integer, String> BST = new BinarySearchTree<Integer,String>();
        BST.add(10, "albert");
        BST.add(5, "lana");
        BST.add(12, "anya");
        BST.add(14, "iris");
        BST.add(11, "serena");
        System.out.println(BST.getSize());
        BST.remove(12);
        BST.remove(11);
        BST.remove(14);
        BST.remove(5);
        System.out.println(BST.getSize());
        BST.add(34, "dawn");
        BST.add(24, "lillie");
        BST.add(11, "serena");
        System.out.println(BST.getSize());
        BST.remove(11);
        BST.remove(10);
        BST.inOrder(BST.getRoot());
        BST.add(11, "serena");
        BST.inOrder(BST.getRoot());
        System.out.println(BST.getSize());
    }*/

    public ListElement<Pair<K, V>> getRoot() {
        return root;
    }

    public MyArrayList<Pair<K, V>> getNodes() {
        return nodes;
    }

    private void setNodes(MyArrayList<Pair<K, V>> newArrayList) {
        nodes = newArrayList;
    }

    public int getSize() {
        return size;
    }
    
    public int size() {
        this.setNodes(new MyArrayList<>());
        this.inOrder(this.getRoot());
        int returnValue = this.getNodes().size();
        this.setNodes(new MyArrayList<>());
        return returnValue;
    }

    public BinarySearchTree<K, V> makeTree(MyArrayList<Pair<K, V>> treeNodes) {
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
                returnValue = false;
                break;
            }
        }
        return returnValue;
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

    public void inOrder(ListElement<Pair<K, V>> node) {
        if (node != null) {
            inOrder(node.getNext());
            System.out.println(node.getValue().getValue());
            nodes.add(node.getValue());
            inOrder(node.getPrevious());
        }
        if (node == root) {
            //this.setNodes(new MyArrayList<>());
        }
    }

    private void inOrder(ListElement<Pair<K, V>> node, K excludeKey) {
        if (node != null) {
            inOrder(node.getNext(), excludeKey);
            if (node.getValue().getKey().equals(excludeKey) == false) {
                nodes.add(node.getValue());
            }
            else {
                size--;
            }
            inOrder(node.getPrevious(), excludeKey);
        }
    }

    public void remove(K key) {
        this.setNodes(new MyArrayList<>());
        if (this.get(key) != null) {
            this.inOrder(root, key);
            root = this.makeTree(this.getNodes()).getRoot();
        }
        this.setNodes(new MyArrayList<>());
    }
}