/**
 * Node class representing an element in the Binary Search Tree (BST).
 * Stores a Patient object and references to left and right child nodes.
 */
public class PatientNode {
    private Patient data;
    private PatientNode left;
    private PatientNode right;

    /**
     * Constructs a new PatientNode with the given Patient data.
     * Left and right child references are initialized to null.
     *
     * @param data the Patient object to store
     */
    public PatientNode(Patient data) {
        this.data = data;
        this.left = null;
        this.right = null;
    }

    public Patient getData() {
        return data;
    }

    public void setData(Patient data) {
        this.data = data;
    }

    public PatientNode getLeft() {
        return left;
    }

    public void setLeft(PatientNode left) {
        this.left = left;
    }

    public PatientNode getRight() {
        return right;
    }

    public void setRight(PatientNode right) {
        this.right = right;
    }
}
