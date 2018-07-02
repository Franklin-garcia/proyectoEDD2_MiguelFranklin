


import java.io.Serializable;
import java.util.ArrayList;

public class Node<E> implements Serializable{
    private static final long SerialVersioUID = 601L;
    private ArrayList<E> keys;
    private ArrayList<Object> Punteros;
    private Node<E> Siguiente;
    private Node<E> Anterior;
    boolean isLeaf;
    int nodeSize;
    private long position;

    public long getPosition() {
        return position;
    }

    public void setPosition(long position) {
        this.position = position;
    }
    public Node(int nodeSize, boolean isLeaf, long position) {
        super();
        keys = new ArrayList<E>();
        Punteros = new ArrayList<Object>();
        this.nodeSize = nodeSize;
        this.isLeaf = isLeaf;
        this.position=position;
    }

    public Node() {
    }
    
    public ArrayList<E> getKeys() {
        return keys;
    }

    public void setKeys(ArrayList<E> keys) {
        this.keys = keys;
    }

    public ArrayList<Object> getPointers() {
        return Punteros;
    }

    public void setPointers(ArrayList<Object> punteros) {
        this.Punteros = punteros;
    }

    public Node<E> getNext() {
        return Siguiente;
    }

    public void setNext(Node<E> siguiente) {
        this.Siguiente = siguiente; 
    }

    public Node<E> getPrev() {
        return Anterior;
    }

    public void setPrev(Node<E> anterior) {
        this.Anterior = anterior;
    }

    public boolean isLeaf() {
        return isLeaf;
    }

    public void setLeaf(boolean isLeaf) {
        this.isLeaf = isLeaf;
    }

    public int getNodeSize() {
        return nodeSize;
    }

    public void setNodeSize(int nodeSize) {
        this.nodeSize = nodeSize;
    }
}
