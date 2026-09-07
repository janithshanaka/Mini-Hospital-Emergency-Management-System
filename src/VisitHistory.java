/**
 * Custom implementation of a Singly Linked List for managing patient visit records.
 * Built without using any built-in Java Collection Framework classes.
 */
public class VisitHistory {
    private VisitNode head;
    private int size;

    /**
     * Constructs an empty VisitHistory singly linked list.
     */
    public VisitHistory() {
        this.head = null;
        this.size = 0;
    }

    /**
     * Checks if the visit history list is empty.
     *
     * @return true if the list contains no visits, false otherwise
     */
    public boolean isEmpty() {
        return head == null;
    }

    /**
     * Returns the total number of visits recorded.
     *
     * @return count of visits
     */
    public int getSize() {
        return size;
    }

    /**
     * Adds a new visit to the end of the singly linked list.
     * Also verifies that the visit ID is unique within this patient's history.
     *
     * Linked List Insertion logic:
     * 1. If list is empty (head == null), make new node the head.
     * 2. Otherwise, traverse to the last node (where current.next == null) and append.
     *
     * @param visit the Visit record to add
     * @return true if added successfully, false if duplicate visit ID exists
     */
    public boolean addVisit(Visit visit) {
        if (visit == null) {
            return false;
        }

        // Check for duplicate visitId in this patient's history
        if (searchVisit(visit.getVisitId()) != null) {
            System.out.println("[Error] Visit with ID " + visit.getVisitId() + " already exists for this patient.");
            return false;
        }

        VisitNode newNode = new VisitNode(visit);

        // Case 1: Empty list (adding the first visit)
        if (head == null) {
            head = newNode;
        } else {
            // Case 2: Adding to a list with existing elements (traverse to tail)
            VisitNode current = head;
            while (current.getNext() != null) {
                current = current.getNext();
            }
            current.setNext(newNode);
        }
        size++;
        return true;
    }

    /**
     * Searches for a visit in the linked list using its Visit ID.
     *
     * Linked List Searching logic:
     * Traverse from head node along 'next' references until matching ID is found or end of list reached.
     * Time Complexity: O(n)
     *
     * @param visitId the unique ID of the visit
     * @return Visit object if found, or null if not found
     */
    public Visit searchVisit(int visitId) {
        VisitNode current = head;
        while (current != null) {
            if (current.getData().getVisitId() == visitId) {
                return current.getData();
            }
            current = current.getNext();
        }
        return null;
    }

    /**
     * Removes a visit from the singly linked list by Visit ID.
     *
     * Linked List Deletion logic:
     * 1. If empty list: return false.
     * 2. If node to delete is head: update head = head.next.
     * 3. If node is in middle or end: track 'previous' and 'current' nodes.
     *    Update previous.next = current.next, effectively unlinking current.
     *
     * @param visitId the ID of the visit to delete
     * @return true if found and removed, false otherwise
     */
    public boolean removeVisit(int visitId) {
        // Case 1: Empty list
        if (head == null) {
            return false;
        }

        // Case 2: Removing the first visit (head node)
        if (head.getData().getVisitId() == visitId) {
            head = head.getNext();
            size--;
            return true;
        }

        // Case 3: Removing a middle or last visit
        VisitNode previous = head;
        VisitNode current = head.getNext();

        while (current != null) {
            if (current.getData().getVisitId() == visitId) {
                // Unlink current node
                previous.setNext(current.getNext());
                size--;
                return true;
            }
            previous = current;
            current = current.getNext();
        }

        // Visit ID not found
        return false;
    }

    /**
     * Displays all visits in this patient's linked list sequentially.
     */
    public void displayVisits() {
        if (head == null) {
            System.out.println("  No visit records found for this patient.");
            return;
        }

        System.out.println("  +-------------------------------------------------------------------------");
        VisitNode current = head;
        int count = 1;
        while (current != null) {
            System.out.println("  Record #" + count + ":");
            current.getData().displayVisitDetails();
            current = current.getNext();
            count++;
        }
        System.out.println("  Total Visits: " + size);
    }
}
