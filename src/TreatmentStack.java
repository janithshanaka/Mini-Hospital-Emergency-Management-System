/**
 * Custom Stack data structure implementing the LIFO (Last-In, First-Out) principle
 * for managing treatment history records.
 * Uses a top node pointer without any Java Collections.
 */
public class TreatmentStack {
    private TreatmentNode top;
    private int size;

    /**
     * Constructs an empty TreatmentStack.
     */
    public TreatmentStack() {
        this.top = null;
        this.size = 0;
    }

    /**
     * Checks if the treatment stack is empty.
     *
     * @return true if stack contains no records, false otherwise
     */
    public boolean isEmpty() {
        return top == null;
    }

    /**
     * Returns the number of treatment records in the stack.
     *
     * @return current stack size
     */
    public int getSize() {
        return size;
    }

    /**
     * Pushes a completed treatment record onto the top of the stack.
     *
     * Stack Push Logic:
     * 1. Create a new TreatmentNode containing the treatment record.
     * 2. Point new node's next to the current top: newNode.next = top.
     * 3. Update top to be the new node: top = newNode.
     * Time Complexity: O(1)
     *
     * @param record the TreatmentRecord to push
     */
    public void push(TreatmentRecord record) {
        if (record == null) {
            return;
        }

        TreatmentNode newNode = new TreatmentNode(record);
        newNode.setNext(top);
        top = newNode;
        size++;
    }

    /**
     * Pops and returns the most recently added treatment record from the top of the stack.
     *
     * Stack Pop Logic:
     * 1. Check if stack is empty. If so, return null.
     * 2. Retrieve the record from the top node.
     * 3. Advance top pointer: top = top.next.
     * Time Complexity: O(1)
     *
     * @return the popped TreatmentRecord, or null if stack is empty
     */
    public TreatmentRecord pop() {
        // Case 1: Empty stack
        if (isEmpty()) {
            return null;
        }

        // Case 2: Pop top element
        TreatmentRecord poppedRecord = top.getRecord();
        top = top.getNext();
        size--;
        return poppedRecord;
    }

    /**
     * Views the most recently added treatment record on top of the stack without removing it.
     * Time Complexity: O(1)
     *
     * @return the top TreatmentRecord, or null if stack is empty
     */
    public TreatmentRecord peek() {
        if (isEmpty()) {
            return null;
        }
        return top.getRecord();
    }

    /**
     * Displays all treatment records in the stack from top to bottom (LIFO: most recent to oldest).
     */
    public void displayHistory() {
        if (isEmpty()) {
            System.out.println("  The treatment history stack is currently empty.");
            return;
        }

        System.out.println("\n=========================================================================================================");
        System.out.println("                              TREATMENT HISTORY (LIFO - Top to Bottom)");
        System.out.println("=========================================================================================================");
        System.out.printf("  | %-6s | %-10s | %-20s | %-18s | %-20s | %-12s |\n",
                "Trt ID", "Pt ID", "Patient Name", "Doctor", "Treatment Done", "Date");
        System.out.println("---------------------------------------------------------------------------------------------------------");

        TreatmentNode current = top;
        int level = 1;
        while (current != null) {
            TreatmentRecord r = current.getRecord();
            String prefix = (level == 1) ? " [TOP]" : "";
            System.out.printf("  | %-6d | %-10d | %-20s | %-18s | %-20s | %-12s |%s\n",
                    r.getTreatmentId(),
                    r.getPatientId(),
                    r.getPatientName(),
                    r.getDoctorName(),
                    r.getTreatment(),
                    r.getTreatmentDate(),
                    prefix);
            current = current.getNext();
            level++;
        }

        System.out.println("=========================================================================================================");
        System.out.println("  Total Treatment Records on Stack: " + size);
    }
}
