/**
 * Custom Binary Search Tree (BST) implementation for managing Patient records.
 * Uses Patient ID as the unique key.
 *
 * Provides:
 * - O(log n) average time complexity for Search, Insert, and Delete.
 * - In-Order Traversal to output patients in naturally sorted ascending order of ID.
 */
public class PatientBST {
    private PatientNode root;
    private int size;

    // Helper flag for deletion feedback
    private boolean lastDeleteSuccessful;

    /**
     * Constructs an empty Binary Search Tree.
     */
    public PatientBST() {
        this.root = null;
        this.size = 0;
    }

    /**
     * Checks if the BST is empty.
     *
     * @return true if root is null, false otherwise
     */
    public boolean isEmpty() {
        return root == null;
    }

    /**
     * Returns the total number of patients stored in the BST.
     *
     * @return total patient count
     */
    public int getSize() {
        return size;
    }

    // =========================================================================
    // 1. INSERTION
    // =========================================================================

    /**
     * Inserts a new patient record into the Binary Search Tree.
     * Duplicate Patient IDs are rejected with an informative message.
     *
     * BST Insertion Logic:
     * 1. If tree is empty, new node becomes the root.
     * 2. Compare incoming patientId with current node's patientId:
     *    - If incoming == current: Duplicate found; reject insertion.
     *    - If incoming < current: Traverse left.
     *    - If incoming > current: Traverse right.
     * 3. Attach new node at the correct leaf position.
     *
     * @param patient the Patient to insert
     * @return true if inserted successfully, false if duplicate ID
     */
    public boolean insert(Patient patient) {
        if (patient == null) {
            return false;
        }

        if (root == null) {
            root = new PatientNode(patient);
            size++;
            return true;
        }

        return insertHelper(root, patient);
    }

    private boolean insertHelper(PatientNode current, Patient patient) {
        int newId = patient.getPatientId();
        int currentId = current.getData().getPatientId();

        if (newId == currentId) {
            System.out.println("[Error] Patient ID " + newId + " already exists in the system. Duplicate rejected.");
            return false;
        } else if (newId < currentId) {
            if (current.getLeft() == null) {
                current.setLeft(new PatientNode(patient));
                size++;
                return true;
            } else {
                return insertHelper(current.getLeft(), patient);
            }
        } else {
            if (current.getRight() == null) {
                current.setRight(new PatientNode(patient));
                size++;
                return true;
            } else {
                return insertHelper(current.getRight(), patient);
            }
        }
    }

    // =========================================================================
    // 2. SEARCHING
    // =========================================================================

    /**
     * Searches for a patient in the BST using their Patient ID.
     *
     * BST Search Logic:
     * 1. Start at root.
     * 2. If current node is null, patient is not in tree (return null).
     * 3. If targetId == currentId, match found (return Patient).
     * 4. If targetId < currentId, search left subtree.
     * 5. If targetId > currentId, search right subtree.
     *
     * @param patientId the ID to search for
     * @return Patient object if found, or null if not found
     */
    public Patient search(int patientId) {
        return searchHelper(root, patientId);
    }

    private Patient searchHelper(PatientNode current, int patientId) {
        if (current == null) {
            return null; // Base case: not found
        }

        int currentId = current.getData().getPatientId();

        if (patientId == currentId) {
            return current.getData(); // Found
        } else if (patientId < currentId) {
            return searchHelper(current.getLeft(), patientId); // Search left
        } else {
            return searchHelper(current.getRight(), patientId); // Search right
        }
    }

    // =========================================================================
    // 3. DELETION
    // =========================================================================

    /**
     * Deletes a patient from the BST using their Patient ID.
     *
     * Handles all BST deletion scenarios:
     * - Node has 0 children (Leaf node): Simply remove it (replace with null).
     * - Node has 1 child: Replace the node with its non-null child.
     * - Node has 2 children:
     *     1. Find In-Order Successor (smallest node in right subtree).
     *     2. Copy successor's data into current node.
     *     3. Delete the successor node from the right subtree.
     * - Deleting root node is supported seamlessly.
     *
     * @param patientId the ID of the patient to delete
     * @return true if deleted, false if patient ID was not found
     */
    public boolean delete(int patientId) {
        lastDeleteSuccessful = false;
        root = deleteHelper(root, patientId);
        if (lastDeleteSuccessful) {
            size--;
        }
        return lastDeleteSuccessful;
    }

    private PatientNode deleteHelper(PatientNode current, int patientId) {
        if (current == null) {
            return null; // Target not found
        }

        int currentId = current.getData().getPatientId();

        if (patientId < currentId) {
            current.setLeft(deleteHelper(current.getLeft(), patientId));
        } else if (patientId > currentId) {
            current.setRight(deleteHelper(current.getRight(), patientId));
        } else {
            // Node to be deleted has been located!
            lastDeleteSuccessful = true;

            // Scenario A: Node has no left child (handles leaf and single right child)
            if (current.getLeft() == null) {
                return current.getRight();
            }
            // Scenario B: Node has no right child (handles single left child)
            else if (current.getRight() == null) {
                return current.getLeft();
            }

            // Scenario C: Node has TWO children
            // Step 1: Find the In-Order Successor (minimum value node in the right subtree)
            PatientNode successor = findMin(current.getRight());

            // Step 2: Copy successor's data into current node
            current.setData(successor.getData());

            // Step 3: Delete the successor from the right subtree
            current.setRight(deleteHelper(current.getRight(), successor.getData().getPatientId()));
        }

        return current;
    }

    /**
     * Finds the node with the minimum key in a given subtree (leftmost node).
     *
     * @param node root of the subtree
     * @return node with minimum value
     */
    private PatientNode findMin(PatientNode node) {
        while (node.getLeft() != null) {
            node = node.getLeft();
        }
        return node;
    }

    // =========================================================================
    // 4. IN-ORDER TRAVERSAL & DISPLAY
    // =========================================================================

    /**
     * Executes In-Order Traversal (Left -> Root -> Right).
     * Because of BST properties, this visits all patients in ascending order of Patient ID.
     */
    public void inOrderTraversal() {
        inOrderHelper(root);
    }

    private void inOrderHelper(PatientNode current) {
        if (current != null) {
            inOrderHelper(current.getLeft());
            System.out.printf("  | %-10d | %-20s | %-5d | %-13s | %-20s |\n",
                    current.getData().getPatientId(),
                    current.getData().getPatientName(),
                    current.getData().getAge(),
                    current.getData().getContactNumber(),
                    current.getData().getMedicalCondition());
            inOrderHelper(current.getRight());
        }
    }

    /**
     * Displays all registered patients in ascending order of Patient ID.
     */
    public void displayPatients() {
        if (root == null) {
            System.out.println("  No patient records currently found in the system (BST is empty).");
            return;
        }

        System.out.println("\n===========================================================================================");
        System.out.printf("  | %-10s | %-20s | %-5s | %-13s | %-20s |\n", "Patient ID", "Name", "Age", "Contact", "Condition");
        System.out.println("===========================================================================================");
        inOrderTraversal();
        System.out.println("===========================================================================================");
        System.out.println("  Total Registered Patients: " + size);
    }
}
