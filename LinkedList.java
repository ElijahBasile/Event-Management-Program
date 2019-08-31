//LinkedList.java

import java.io.*;

//this handles the generic operations and data storage needed for a list of bigger data items stored in a linked list. Also makes seriliazation easier
public class LinkedList<T> implements Serializable {
    protected LLNode<T> head = null;
    protected int id = 0;

    //just checks for the existence of an item in a list
    public boolean search(T value) {
        LLNode<T> current = this.head;
        //list empty
        if (current == null) {
            System.out.println("List empty");
            return false;

        }
        //list has at least one item in it
        do {
            if (current.getValue() == value)
                return true;
            current = current.getAfter();
        } while (current != null);
        System.out.println("Item not found in list");
        return false;
    }
    
    //gets the node that matches the uid entered
    public LLNode<T> getNode(int uid) {
        LLNode<T> current = this.head;
        if (this.head == null)
            return null;
        while (current != null) {
            if (uid == current.getId())
                return current;
            current = current.getAfter();
        }
        return null;
    }
    
    //deletes chosen node in listing
    public void deleteNode(LLNode<T> toBeDeleted) {
        if (toBeDeleted == this.head) {
            this.head = toBeDeleted.getAfter();
        } else {
            LLNode<T> before = toBeDeleted.getBefore();
            LLNode<T> after = toBeDeleted.getAfter();
            before.setAfter(after);
            after.setBefore(before);
        }
    }

    //returns the head
    public LLNode<T> getHead() {
        return this.head;
    }

    //inserts a node at the top of the list
    public void insert(LLNode<T> inserting) {
        inserting.setAfter(this.head);
        if(this.head == null) {
            //empty
        } else {
            this.head.setBefore(inserting);
        }
        inserting.setId(this.id);
        this.head = inserting;
        this.id += 1;
    }
}//end class def









