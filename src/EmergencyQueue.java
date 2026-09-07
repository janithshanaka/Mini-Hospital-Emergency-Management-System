/**
 * Custom Queue data structure implementing the FIFO (First-In, First-Out) principle
 * for managing incoming emergency patients.
 * Uses front and rear node references without any Java Collections.
 */
public class EmergencyQueue {
    private EmergencyQueueNode front;
    private EmergencyQueueNode rear;
    private int size;

    /**
     * Constructs an empty EmergencyQueue.
     */
    public EmergencyQueue() {
        this.front = null;
        this.rear = null;
        this.size = 0;
    }

    /**
     * Checks whether the emergency queue is empty.
     *
     * @return true if queue has no patients, false otherwise
     */
    public boolean isEmpty() {
        return front == null;
    }

    /**
     * Returns the number of patients waiting in the queue.
     *
     * @return current queue size
     */
    public int getSize() {
        return size;
    }

    /**
     * Adds a patient to the rear of the emergency queue (Enqueue operation).
     *
     * Queue Enqueue Logic:
     * 1. Create a new EmergencyQueueNode.
     * 2. If queue is empty (rear == null):
     *    Both front and rear point to the new node.
     * 3. Otherwise:
     *    rear.next points to the new node, and rear is updated to new node.
     * Time Complexity: O(1)
     *
     * @param patient the Patient to add to the queue
     */
    public void enqueue(Patient patient) {
        if (patient == null) {
            return;
        }

        EmergencyQueueNode newNode = new EmergencyQueueNode(patient);

        // Case 1: Queue is empty
        if (rear == null) {
            front = newNode;
            rear = newNode;
        } else {
            // Case 2: Adding to non-empty queue
            rear.setNext(newNode);
            rear = newNode;
        }
        size++;
    }

    /**
     * Removes and returns the patient from the front of the queue (Dequeue operation).
     *
     * Queue Dequeue Logic:
     * 1. Check if queue is empty. If so, return null.
     * 2. Retrieve patient data from front node.
     * 3. Advance front reference: front = front.next.
     * 4. If front becomes null (queue emptied), also reset rear = null.
     * Time Complexity: O(1)
     *
     * @return the dequeued Patient, or null if queue is empty
     */
    public Patient dequeue() {
        // Case 1: Empty queue
        if (isEmpty()) {
            return null;
        }

        // Case 2: Queue has at least one patient
        Patient dequeuedPatient = front.getPatient();
        front = front.getNext();

        // If front becomes null, the queue has become empty
        if (front == null) {
            rear = null;
        }

        size--;
        return dequeuedPatient;
    }

    /**
     * Views the patient at the front of the queue without removing them (Peek operation).
     * Time Complexity: O(1)
     *
     * @return the front Patient, or null if empty
     */
    public Patient peek() {
        if (isEmpty()) {
            return null;
        }
        return front.getPatient();
    }

    /**
     * Displays all patients currently waiting in the emergency queue in arrival order.
     */
    public void displayWaitingPatients() {
        if (isEmpty()) {
            System.out.println("  The emergency queue is currently empty. No patients waiting.");
            return;
        }

        System.out.println("\n==========================================================================================");
        System.out.println("                         EMERGENCY PATIENT QUEUE (FIFO)");
        System.out.println("==========================================================================================");
        System.out.printf("  | %-4s | %-10s | %-20s | %-5s | %-25s |\n", "Pos", "ID", "Patient Name", "Age", "Medical Condition");
        System.out.println("------------------------------------------------------------------------------------------");

        EmergencyQueueNode current = front;
        int position = 1;

        while (current != null) {
            Patient p = current.getPatient();
            System.out.printf("  | #%-3d | %-10d | %-20s | %-5d | %-25s |\n",
                    position,
                    p.getPatientId(),
                    p.getPatientName(),
                    p.getAge(),
                    p.getMedicalCondition());
            current = current.getNext();
            position++;
        }

        System.out.println("==========================================================================================");
        System.out.println("  Total Patients Waiting: " + size);
    }
}
