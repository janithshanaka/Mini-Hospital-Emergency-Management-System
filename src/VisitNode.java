/**
 * Node class representing an element in the Singly Linked List of patient visits.
 * Contains Visit data and a pointer (reference) to the next VisitNode.
 */
public class VisitNode {
    private Visit data;
    private VisitNode next;

    /**
     * Constructs a new VisitNode with the given Visit data.
     * The next pointer is initialized to null.
     *
     * @param data the Visit object to store
     */
    public VisitNode(Visit data) {
        this.data = data;
        this.next = null;
    }

    public Visit getData() {
        return data;
    }

    public void setData(Visit data) {
        this.data = data;
    }

    public VisitNode getNext() {
        return next;
    }

    public void setNext(VisitNode next) {
        this.next = next;
    }
}
