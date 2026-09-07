/**
 * Node class representing an element in the Treatment History Stack.
 * Stores a TreatmentRecord and a pointer to the next TreatmentNode below it.
 */
public class TreatmentNode {
    private TreatmentRecord record;
    private TreatmentNode next;

    /**
     * Constructs a new TreatmentNode with the given treatment record.
     *
     * @param record the TreatmentRecord to store
     */
    public TreatmentNode(TreatmentRecord record) {
        this.record = record;
        this.next = null;
    }

    public TreatmentRecord getRecord() {
        return record;
    }

    public void setRecord(TreatmentRecord record) {
        this.record = record;
    }

    public TreatmentNode getNext() {
        return next;
    }

    public void setNext(TreatmentNode next) {
        this.next = next;
    }
}
