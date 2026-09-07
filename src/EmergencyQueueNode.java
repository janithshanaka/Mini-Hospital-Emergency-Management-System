/**
 * Node class representing an element in the Emergency Patient Queue.
 * Stores a Patient reference and a pointer to the next EmergencyQueueNode.
 */
public class EmergencyQueueNode {
    private Patient patient;
    private EmergencyQueueNode next;

    /**
     * Constructs a new EmergencyQueueNode for the specified patient.
     *
     * @param patient the Patient arriving at emergency
     */
    public EmergencyQueueNode(Patient patient) {
        this.patient = patient;
        this.next = null;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public EmergencyQueueNode getNext() {
        return next;
    }

    public void setNext(EmergencyQueueNode next) {
        this.next = next;
    }
}
