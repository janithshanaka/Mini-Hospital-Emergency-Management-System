/**
 * Comprehensive Automated Unit & Integration Tests for all four custom data structures:
 * 1. PatientBST (Binary Search Tree)
 * 2. EmergencyQueue (FIFO Queue)
 * 3. TreatmentStack (LIFO Stack)
 * 4. VisitHistory (Singly Linked List)
 */
public class DataStructuresTest {

    public static void main(String[] args) {
        System.out.println("==================================================");
        System.out.println("   RUNNING AUTOMATED DATA STRUCTURE VERIFICATION   ");
        System.out.println("==================================================");

        testPatientBST();
        testEmergencyQueue();
        testTreatmentStack();
        testVisitHistory();

        System.out.println("\n==================================================");
        System.out.println("   >>> ALL 4 DATA STRUCTURE TESTS PASSED! <<<     ");
        System.out.println("==================================================");
    }

    private static void assertEquals(Object expected, Object actual, String testName) {
        if ((expected == null && actual == null) || (expected != null && expected.equals(actual))) {
            System.out.println("  [PASS] " + testName);
        } else {
            System.err.println("  [FAIL] " + testName + " | Expected: " + expected + ", Got: " + actual);
            System.exit(1);
        }
    }

    private static void assertTrue(boolean condition, String testName) {
        if (condition) {
            System.out.println("  [PASS] " + testName);
        } else {
            System.err.println("  [FAIL] " + testName + " | Expected condition to be TRUE.");
            System.exit(1);
        }
    }

    // =========================================================================
    // BST TESTS
    // =========================================================================
    private static void testPatientBST() {
        System.out.println("\n--- Testing 1: PatientBST (Binary Search Tree) ---");
        PatientBST bst = new PatientBST();

        // Test Empty Tree
        assertTrue(bst.isEmpty(), "BST initially empty");
        assertEquals(null, bst.search(1001), "Search in empty BST returns null");
        assertTrue(!bst.delete(1001), "Delete from empty BST returns false");

        // Test Insertion
        Patient p1003 = new Patient(1003, "John Silva", 45, "0712345678", "Chest Pain");
        Patient p1001 = new Patient(1001, "Nimal Perera", 32, "0771234567", "Fever");
        Patient p1005 = new Patient(1005, "Sarah Fernando", 28, "0769876543", "Accident");
        Patient p1002 = new Patient(1002, "Kamal Perera", 56, "0755555555", "Diabetes");
        Patient p1004 = new Patient(1004, "Anne Silva", 39, "0788888888", "Headache");

        assertTrue(bst.insert(p1003), "Insert root (1003)");
        assertTrue(bst.insert(p1001), "Insert left child (1001)");
        assertTrue(bst.insert(p1005), "Insert right child (1005)");
        assertTrue(bst.insert(p1002), "Insert (1002)");
        assertTrue(bst.insert(p1004), "Insert (1004)");
        assertEquals(5, bst.getSize(), "BST size is 5");

        // Test Duplicate Rejection
        Patient dup = new Patient(1001, "Duplicate", 20, "000", "Cold");
        assertTrue(!bst.insert(dup), "Duplicate patient ID 1001 correctly rejected");
        assertEquals(5, bst.getSize(), "BST size remains 5 after duplicate attempt");

        // Test Search
        Patient found = bst.search(1005);
        assertTrue(found != null && found.getPatientName().equals("Sarah Fernando"), "Search existing patient (1005)");
        assertEquals(null, bst.search(9999), "Search non-existing patient (9999)");

        // Test Deletion of Leaf Node (1004 is left child of 1005, has no children)
        assertTrue(bst.delete(1004), "Delete leaf node (1004)");
        assertEquals(null, bst.search(1004), "1004 no longer found after delete");
        assertEquals(4, bst.getSize(), "BST size is now 4");

        // Test Deletion of Node with 1 Child (1005 now has no left child, only right is null, or 1001 has right child 1002)
        // 1001 has no left child, right child is 1002
        assertTrue(bst.delete(1001), "Delete node with 1 child (1001)");
        assertEquals(null, bst.search(1001), "1001 no longer found after delete");
        assertTrue(bst.search(1002) != null, "1002 still reachable after parent 1001 deleted");
        assertEquals(3, bst.getSize(), "BST size is now 3");

        // Test Deletion of Node with 2 Children (Root 1003 has left child 1002 and right child 1005)
        assertTrue(bst.delete(1003), "Delete root node with 2 children (1003)");
        assertEquals(null, bst.search(1003), "Root 1003 no longer found after delete");
        assertTrue(bst.search(1002) != null, "1002 still reachable");
        assertTrue(bst.search(1005) != null, "1005 still reachable");
        assertEquals(2, bst.getSize(), "BST size is now 2");
    }

    // =========================================================================
    // QUEUE TESTS
    // =========================================================================
    private static void testEmergencyQueue() {
        System.out.println("\n--- Testing 2: EmergencyQueue (FIFO Queue) ---");
        EmergencyQueue queue = new EmergencyQueue();

        assertTrue(queue.isEmpty(), "Queue initially empty");
        assertEquals(null, queue.dequeue(), "Dequeue on empty queue returns null");
        assertEquals(null, queue.peek(), "Peek on empty queue returns null");

        Patient p1 = new Patient(1, "Patient A", 30, "111", "Injury");
        Patient p2 = new Patient(2, "Patient B", 40, "222", "Asthma");
        Patient p3 = new Patient(3, "Patient C", 50, "333", "Cardiac");

        queue.enqueue(p1);
        assertEquals(1, queue.getSize(), "Queue size 1 after 1st enqueue");
        assertEquals("Patient A", queue.peek().getPatientName(), "Peek returns Patient A");

        queue.enqueue(p2);
        queue.enqueue(p3);
        assertEquals(3, queue.getSize(), "Queue size 3 after 3 enqueues");

        // Test FIFO Order: A -> B -> C
        assertEquals("Patient A", queue.dequeue().getPatientName(), "1st Dequeue is Patient A");
        assertEquals(2, queue.getSize(), "Queue size is 2");
        assertEquals("Patient B", queue.dequeue().getPatientName(), "2nd Dequeue is Patient B");
        assertEquals("Patient C", queue.dequeue().getPatientName(), "3rd Dequeue is Patient C");

        assertTrue(queue.isEmpty(), "Queue is empty after dequeuing all patients");
        assertEquals(null, queue.dequeue(), "Dequeue after emptied returns null");
    }

    // =========================================================================
    // STACK TESTS
    // =========================================================================
    private static void testTreatmentStack() {
        System.out.println("\n--- Testing 3: TreatmentStack (LIFO Stack) ---");
        TreatmentStack stack = new TreatmentStack();

        assertTrue(stack.isEmpty(), "Stack initially empty");
        assertEquals(null, stack.pop(), "Pop on empty stack returns null");
        assertEquals(null, stack.peek(), "Peek on empty stack returns null");

        TreatmentRecord t1 = new TreatmentRecord(1, 101, "Alice", "Dr. A", "Oxygen", "2026-09-01");
        TreatmentRecord t2 = new TreatmentRecord(2, 102, "Bob", "Dr. B", "Antibiotics", "2026-09-02");
        TreatmentRecord t3 = new TreatmentRecord(3, 103, "Charlie", "Dr. C", "Surgery", "2026-09-03");

        stack.push(t1);
        stack.push(t2);
        stack.push(t3);

        assertEquals(3, stack.getSize(), "Stack size is 3");
        assertEquals(3, stack.peek().getTreatmentId(), "Peek returns top element (t3)");

        // Test LIFO Order: t3 -> t2 -> t1
        assertEquals(3, stack.pop().getTreatmentId(), "1st Pop is t3 (Last-In)");
        assertEquals(2, stack.pop().getTreatmentId(), "2nd Pop is t2");
        assertEquals(1, stack.pop().getTreatmentId(), "3rd Pop is t1 (First-In)");

        assertTrue(stack.isEmpty(), "Stack empty after popping all elements");
        assertEquals(null, stack.pop(), "Pop on emptied stack returns null");
    }

    // =========================================================================
    // SINGLY LINKED LIST TESTS
    // =========================================================================
    private static void testVisitHistory() {
        System.out.println("\n--- Testing 4: VisitHistory (Singly Linked List) ---");
        VisitHistory list = new VisitHistory();

        assertTrue(list.isEmpty(), "Linked list initially empty");
        assertEquals(null, list.searchVisit(10), "Search in empty list returns null");
        assertTrue(!list.removeVisit(10), "Remove from empty list returns false");

        Visit v1 = new Visit(101, "2026-01-01", "Dr. X", "Cold", "Medication A");
        Visit v2 = new Visit(102, "2026-02-01", "Dr. Y", "Flu", "Medication B");
        Visit v3 = new Visit(103, "2026-03-01", "Dr. Z", "Checkup", "Advice");

        assertTrue(list.addVisit(v1), "Add 1st visit (head)");
        assertTrue(list.addVisit(v2), "Add 2nd visit (middle)");
        assertTrue(list.addVisit(v3), "Add 3rd visit (tail)");
        assertEquals(3, list.getSize(), "List size is 3");

        // Duplicate rejection
        Visit dup = new Visit(101, "2026-04-01", "Dr. W", "Other", "Other");
        assertTrue(!list.addVisit(dup), "Duplicate visit ID 101 rejected");
        assertEquals(3, list.getSize(), "List size remains 3");

        // Search test
        assertEquals("Cold", list.searchVisit(101).getDiagnosis(), "Search visit 101");
        assertEquals("Checkup", list.searchVisit(103).getDiagnosis(), "Search visit 103");
        assertEquals(null, list.searchVisit(999), "Search non-existing visit 999 returns null");

        // Remove Middle node (v2: 102)
        assertTrue(list.removeVisit(102), "Remove middle visit (102)");
        assertEquals(null, list.searchVisit(102), "Visit 102 no longer found");
        assertEquals(2, list.getSize(), "List size is 2");

        // Remove Head node (v1: 101)
        assertTrue(list.removeVisit(101), "Remove head visit (101)");
        assertEquals(null, list.searchVisit(101), "Visit 101 no longer found");
        assertEquals(1, list.getSize(), "List size is 1");

        // Remove Tail node (v3: 103)
        assertTrue(list.removeVisit(103), "Remove remaining single node (103)");
        assertEquals(null, list.searchVisit(103), "Visit 103 no longer found");
        assertTrue(list.isEmpty(), "List is now empty");
        assertEquals(0, list.getSize(), "List size is 0");
    }
}
