//LLNode.java

import java.io.*;

//LLNode represents the generic node in the generic linked list object
public class LLNode<T> implements Serializable {
    private T value; 
    private LLNode<T> before;
    private LLNode<T> after;
    private int id;

    public LLNode(T value) {
        this.value = value;
    }

    public void setId(int _id) {
        this.id = _id;
    }

    public void setAfter(LLNode<T> _after ) {
        this.after = _after;
    }

    public void setBefore(LLNode<T> _before) {
        this.before = _before;
    }

    public LLNode<T> getAfter() {
        return this.after;
    }

    public LLNode<T> getBefore() {
        return this.before;
    }

    public T getValue() {
        return this.value;
    }

    public int getId() {
        return this.id;
    }
}
