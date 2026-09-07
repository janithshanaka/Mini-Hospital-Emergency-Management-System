import java.util.Scanner;

/**
 * Main application class for the Mini Hospital Emergency Management System.
 * Demonstrates manual implementation and utilization of four core data structures:
 * 1. Binary Search Tree (BST)   - Patient Records
 * 2. Queue (FIFO)               - Emergency Patient Queue
 * 3. Stack (LIFO)               - Treatment History
 * 4. Singly Linked List         - Patient Visit History
 *
 * Designed and built without using any Java Collection Framework classes.
 */
public class Main {

    // Core Data Structure Instances
    private static final PatientBST patientBST = new PatientBST();
    private static final EmergencyQueue emergencyQueue = new EmergencyQueue();
    private static final TreatmentStack treatmentStack = new TreatmentStack();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            printMainMenu();
            int choice = InputHelper.readInt(scanner, "Enter your choice (1-7): ");

            switch (choice) {
                case 1:
                    handlePatientManagementMenu(scanner);
                    break;
                case 2:
                    handleEmergencyQueueMenu(scanner);
                    break;
                case 3:
                    handleTreatmentManagementMenu(scanner);
                    break;
                case 4:
                    handleVisitHistoryMenu(scanner);
                    break;
                case 5:
                    displayAllPatients();
                    InputHelper.pressEnterToContinue(scanner);
                    break;
                case 6:
                    loadSampleData();
                    InputHelper.pressEnterToContinue(scanner);
                    break;
                case 7:
                    running = false;
                    System.out.println("\n==================================================");
                    System.out.println("  Thank you for using the Hospital Management System.");
                    System.out.println("  System successfully shut down. Goodbye!");
                    System.out.println("==================================================");
                    break;
                default:
                    System.out.println("[Error] Invalid choice! Please select an option between 1 and 7.");
                    InputHelper.pressEnterToContinue(scanner);
                    break;
            }
        }

        scanner.close();
    }

    // =========================================================================
    // MAIN MENU DISPLAY
    // =========================================================================

    private static void printMainMenu() {
        System.out.println("\n==================================================");
        System.out.println("    MINI HOSPITAL EMERGENCY MANAGEMENT SYSTEM     ");
        System.out.println("==================================================");
        System.out.println("  1. Patient Management (Binary Search Tree)");
        System.out.println("  2. Emergency Queue Management (FIFO Queue)");
        System.out.println("  3. Treatment Management (LIFO Stack)");
        System.out.println("  4. Patient Visit History (Singly Linked List)");
        System.out.println("  5. Display All Patients (In-Order BST Traversal)");
        System.out.println("  6. Load Sample Data");
        System.out.println("  7. Exit");
        System.out.println("==================================================");
    }

    // =========================================================================
    // 1. PATIENT MANAGEMENT SUBMENU (BST)
    // =========================================================================

    private static void handlePatientManagementMenu(Scanner scanner) {
        boolean inSubMenu = true;

        while (inSubMenu) {
            System.out.println("\n--------------------------------------------------");
            System.out.println("                PATIENT MANAGEMENT                ");
            System.out.println("--------------------------------------------------");
            System.out.println("  1. Register New Patient");
            System.out.println("  2. Search Patient");
            System.out.println("  3. Delete Patient");
            System.out.println("  4. Display All Patients");
            System.out.println("  5. Back to Main Menu");
            System.out.println("--------------------------------------------------");

            int choice = InputHelper.readInt(scanner, "Enter your choice (1-5): ");

            switch (choice) {
                case 1:
                    registerNewPatient(scanner);
                    break;
                case 2:
                    searchPatient(scanner);
                    break;
                case 3:
                    deletePatient(scanner);
                    break;
                case 4:
                    displayAllPatients();
                    break;
                case 5:
                    inSubMenu = false;
                    break;
                default:
                    System.out.println("[Error] Invalid option. Please select 1 to 5.");
                    break;
            }

            if (inSubMenu && choice >= 1 && choice <= 4) {
                InputHelper.pressEnterToContinue(scanner);
            }
        }
    }

    private static void registerNewPatient(Scanner scanner) {
        System.out.println("\n>>> Register New Patient");
        int patientId = InputHelper.readPositiveInt(scanner, "Enter Patient ID (e.g. 1001): ");

        // Check duplicate beforehand for clear immediate feedback
        if (patientBST.search(patientId) != null) {
            System.out.println("[Error] Patient with ID " + patientId + " already exists in the BST! Duplicate registration rejected.");
            return;
        }

        String name = InputHelper.readNonEmptyString(scanner, "Enter Patient Name: ");
        int age = InputHelper.readPositiveInt(scanner, "Enter Age: ");
        String contact = InputHelper.readNonEmptyString(scanner, "Enter Contact Number: ");
        String condition = InputHelper.readNonEmptyString(scanner, "Enter Medical Condition: ");

        Patient newPatient = new Patient(patientId, name, age, contact, condition);
        boolean inserted = patientBST.insert(newPatient);

        if (inserted) {
            System.out.println("\n[Success] Patient '" + name + "' (ID: " + patientId + ") registered into BST successfully!");
        }
    }

    private static void searchPatient(Scanner scanner) {
        System.out.println("\n>>> Search Patient by ID");
        int patientId = InputHelper.readPositiveInt(scanner, "Enter Patient ID to search: ");

        Patient found = patientBST.search(patientId);
        if (found != null) {
            System.out.println("\n[Match Found in BST]");
            found.displayPatientDetails();
        } else {
            System.out.println("\n[Not Found] Patient with ID " + patientId + " does not exist in the system.");
        }
    }

    private static void deletePatient(Scanner scanner) {
        System.out.println("\n>>> Delete Patient Record from BST");
        int patientId = InputHelper.readPositiveInt(scanner, "Enter Patient ID to delete: ");

        Patient target = patientBST.search(patientId);
        if (target == null) {
            System.out.println("\n[Not Found] Cannot delete. Patient with ID " + patientId + " does not exist.");
            return;
        }

        boolean deleted = patientBST.delete(patientId);
        if (deleted) {
            System.out.println("\n[Success] Patient '" + target.getPatientName() + "' (ID: " + patientId + ") has been deleted from the BST.");
        } else {
            System.out.println("\n[Error] Failed to delete patient with ID " + patientId + ".");
        }
    }

    private static void displayAllPatients() {
        patientBST.displayPatients();
    }

    // =========================================================================
    // 2. EMERGENCY QUEUE MANAGEMENT SUBMENU (FIFO QUEUE)
    // =========================================================================

    private static void handleEmergencyQueueMenu(Scanner scanner) {
        boolean inSubMenu = true;

        while (inSubMenu) {
            System.out.println("\n--------------------------------------------------");
            System.out.println("             EMERGENCY PATIENT QUEUE              ");
            System.out.println("--------------------------------------------------");
            System.out.println("  1. Add Patient to Emergency Queue (Enqueue)");
            System.out.println("  2. Call Next Patient for Treatment (Dequeue)");
            System.out.println("  3. View Next Patient (Peek)");
            System.out.println("  4. Display Waiting Patients");
            System.out.println("  5. Back to Main Menu");
            System.out.println("--------------------------------------------------");

            int choice = InputHelper.readInt(scanner, "Enter your choice (1-5): ");

            switch (choice) {
                case 1:
                    addPatientToQueue(scanner);
                    break;
                case 2:
                    callNextPatient(scanner);
                    break;
                case 3:
                    viewNextPatient();
                    break;
                case 4:
                    emergencyQueue.displayWaitingPatients();
                    break;
                case 5:
                    inSubMenu = false;
                    break;
                default:
                    System.out.println("[Error] Invalid option. Please select 1 to 5.");
                    break;
            }

            if (inSubMenu && choice >= 1 && choice <= 4) {
                InputHelper.pressEnterToContinue(scanner);
            }
        }
    }

    private static void addPatientToQueue(Scanner scanner) {
        System.out.println("\n>>> Add Patient to Emergency Queue");
        int patientId = InputHelper.readPositiveInt(scanner, "Enter Patient ID: ");

        // Search in BST first
        Patient patient = patientBST.search(patientId);
        if (patient == null) {
            System.out.println("\n[Error] Patient not found. Please register the patient first.");
            return;
        }

        emergencyQueue.enqueue(patient);
        System.out.println("\n[Success] Patient '" + patient.getPatientName() + "' (ID: " + patientId +
                ") added to the rear of the Emergency Queue.");
        System.out.println("Current Queue Size: " + emergencyQueue.getSize());
    }

    private static void callNextPatient(Scanner scanner) {
        System.out.println("\n>>> Call Next Patient for Treatment");

        if (emergencyQueue.isEmpty()) {
            System.out.println("[Notice] The emergency queue is empty. No waiting patients to call.");
            return;
        }

        Patient treatedPatient = emergencyQueue.dequeue();
        System.out.println("\n*** Now treating patient: " + treatedPatient.getPatientName() +
                " (ID: " + treatedPatient.getPatientId() + ") ***");
        System.out.println("Condition: " + treatedPatient.getMedicalCondition());
        System.out.println("Remaining in queue: " + emergencyQueue.getSize());

        // Streamlined option to complete treatment immediately
        System.out.print("\nWould you like to complete and record this treatment now? (1: Yes / 2: Later): ");
        int opt = InputHelper.readInt(scanner, "");
        if (opt == 1) {
            recordTreatmentForPatient(scanner, treatedPatient);
        }
    }

    private static void viewNextPatient() {
        System.out.println("\n>>> View Next Patient in Queue (Peek)");

        Patient next = emergencyQueue.peek();
        if (next == null) {
            System.out.println("[Notice] The emergency queue is empty.");
        } else {
            System.out.println("\n[Next in Line for Emergency Treatment]");
            next.displayPatientDetails();
        }
    }

    // =========================================================================
    // 3. TREATMENT MANAGEMENT SUBMENU (LIFO STACK)
    // =========================================================================

    private static void handleTreatmentManagementMenu(Scanner scanner) {
        boolean inSubMenu = true;

        while (inSubMenu) {
            System.out.println("\n--------------------------------------------------");
            System.out.println("               TREATMENT MANAGEMENT               ");
            System.out.println("--------------------------------------------------");
            System.out.println("  1. Complete Patient Treatment (Push to Stack)");
            System.out.println("  2. View Last Completed Treatment (Peek)");
            System.out.println("  3. Remove Last Treatment Record (Pop)");
            System.out.println("  4. Display Treatment History (Top to Bottom)");
            System.out.println("  5. Back to Main Menu");
            System.out.println("--------------------------------------------------");

            int choice = InputHelper.readInt(scanner, "Enter your choice (1-5): ");

            switch (choice) {
                case 1:
                    completePatientTreatment(scanner);
                    break;
                case 2:
                    viewLastTreatment();
                    break;
                case 3:
                    removeLastTreatment();
                    break;
                case 4:
                    treatmentStack.displayHistory();
                    break;
                case 5:
                    inSubMenu = false;
                    break;
                default:
                    System.out.println("[Error] Invalid option. Please select 1 to 5.");
                    break;
            }

            if (inSubMenu && choice >= 1 && choice <= 4) {
                InputHelper.pressEnterToContinue(scanner);
            }
        }
    }

    private static void completePatientTreatment(Scanner scanner) {
        System.out.println("\n>>> Complete Patient Treatment");
        int patientId = InputHelper.readPositiveInt(scanner, "Enter Patient ID: ");

        Patient patient = patientBST.search(patientId);
        if (patient == null) {
            System.out.println("\n[Error] Patient not found in records. Please register patient first.");
            return;
        }

        recordTreatmentForPatient(scanner, patient);
    }

    private static void recordTreatmentForPatient(Scanner scanner, Patient patient) {
        System.out.println("Recording treatment for: " + patient.getPatientName() + " (ID: " + patient.getPatientId() + ")");
        int treatmentId = InputHelper.readPositiveInt(scanner, "Enter Treatment ID (e.g. 501): ");
        String doctor = InputHelper.readNonEmptyString(scanner, "Enter Doctor Name: ");
        String treatment = InputHelper.readNonEmptyString(scanner, "Enter Treatment Description: ");
        String date = InputHelper.readNonEmptyString(scanner, "Enter Treatment Date (e.g. 2026-09-07): ");

        TreatmentRecord record = new TreatmentRecord(treatmentId, patient.getPatientId(), patient.getPatientName(), doctor, treatment, date);
        treatmentStack.push(record);

        System.out.println("\n[Success] Treatment completed and record added to history.");
        System.out.println("Record pushed onto Treatment Stack. (Stack Size: " + treatmentStack.getSize() + ")");

        // Also add to patient's Singly Linked List visit history
        System.out.print("Add this session as a permanent visit in patient's visit history? (1: Yes / 2: No): ");
        int opt = InputHelper.readInt(scanner, "");
        if (opt == 1) {
            Visit newVisit = new Visit(treatmentId, date, doctor, patient.getMedicalCondition(), treatment);
            boolean added = patient.getVisitHistory().addVisit(newVisit);
            if (added) {
                System.out.println("[Success] Visit also added to patient's Singly Linked List history.");
            }
        }
    }

    private static void viewLastTreatment() {
        System.out.println("\n>>> View Last Completed Treatment (Peek)");
        TreatmentRecord topRecord = treatmentStack.peek();

        if (topRecord == null) {
            System.out.println("[Notice] Treatment stack is empty. No treatments recorded yet.");
        } else {
            System.out.println("\n[Most Recent Completed Treatment (Top of Stack)]");
            topRecord.displayRecordDetails();
        }
    }

    private static void removeLastTreatment() {
        System.out.println("\n>>> Remove Last Treatment Record (Pop)");

        if (treatmentStack.isEmpty()) {
            System.out.println("[Notice] Cannot pop. Treatment stack is currently empty.");
            return;
        }

        TreatmentRecord popped = treatmentStack.pop();
        System.out.println("\n[Success] Removed most recent treatment record from top of stack (LIFO):");
        popped.displayRecordDetails();
        System.out.println("Remaining Treatment Records on Stack: " + treatmentStack.getSize());
    }

    // =========================================================================
    // 4. PATIENT VISIT HISTORY SUBMENU (SINGLY LINKED LIST)
    // =========================================================================

    private static void handleVisitHistoryMenu(Scanner scanner) {
        boolean inSubMenu = true;

        while (inSubMenu) {
            System.out.println("\n--------------------------------------------------");
            System.out.println("              PATIENT VISIT HISTORY               ");
            System.out.println("--------------------------------------------------");
            System.out.println("  1. Add Visit");
            System.out.println("  2. Remove Visit");
            System.out.println("  3. Search Visit");
            System.out.println("  4. Display Visit History");
            System.out.println("  5. Back to Main Menu");
            System.out.println("--------------------------------------------------");

            int choice = InputHelper.readInt(scanner, "Enter your choice (1-5): ");

            if (choice >= 1 && choice <= 4) {
                int patientId = InputHelper.readPositiveInt(scanner, "\nEnter Patient ID: ");
                Patient patient = patientBST.search(patientId);

                if (patient == null) {
                    System.out.println("\n[Error] Patient not found.");
                } else {
                    System.out.println("Selected Patient: " + patient.getPatientName() + " (ID: " + patient.getPatientId() + ")");
                    switch (choice) {
                        case 1:
                            addVisitToPatient(scanner, patient);
                            break;
                        case 2:
                            removeVisitFromPatient(scanner, patient);
                            break;
                        case 3:
                            searchVisitInPatient(scanner, patient);
                            break;
                        case 4:
                            displayPatientVisitHistory(patient);
                            break;
                    }
                }
                InputHelper.pressEnterToContinue(scanner);
            } else if (choice == 5) {
                inSubMenu = false;
            } else {
                System.out.println("[Error] Invalid option. Please select 1 to 5.");
            }
        }
    }

    private static void addVisitToPatient(Scanner scanner, Patient patient) {
        System.out.println("\n>>> Add Clinical Visit for " + patient.getPatientName());
        int visitId = InputHelper.readPositiveInt(scanner, "Visit ID (e.g. 1): ");

        if (patient.getVisitHistory().searchVisit(visitId) != null) {
            System.out.println("[Error] Visit ID " + visitId + " already exists for this patient!");
            return;
        }

        String visitDate = InputHelper.readNonEmptyString(scanner, "Visit Date (e.g. 2026-09-07): ");
        String doctor = InputHelper.readNonEmptyString(scanner, "Doctor Name: ");
        String diagnosis = InputHelper.readNonEmptyString(scanner, "Diagnosis: ");
        String treatment = InputHelper.readNonEmptyString(scanner, "Treatment: ");

        Visit visit = new Visit(visitId, visitDate, doctor, diagnosis, treatment);
        boolean added = patient.getVisitHistory().addVisit(visit);

        if (added) {
            System.out.println("\n[Success] Visit record #" + visitId + " added to " +
                    patient.getPatientName() + "'s Singly Linked List history.");
        }
    }

    private static void removeVisitFromPatient(Scanner scanner, Patient patient) {
        System.out.println("\n>>> Remove Visit Record from " + patient.getPatientName());
        int visitId = InputHelper.readPositiveInt(scanner, "Enter Visit ID to remove: ");

        boolean removed = patient.getVisitHistory().removeVisit(visitId);
        if (removed) {
            System.out.println("\n[Success] Visit ID " + visitId + " successfully removed from linked list.");
        } else {
            System.out.println("\n[Not Found] Visit with ID " + visitId + " does not exist in this patient's history.");
        }
    }

    private static void searchVisitInPatient(Scanner scanner, Patient patient) {
        System.out.println("\n>>> Search Visit Record for " + patient.getPatientName());
        int visitId = InputHelper.readPositiveInt(scanner, "Enter Visit ID to search: ");

        Visit found = patient.getVisitHistory().searchVisit(visitId);
        if (found != null) {
            System.out.println("\n[Visit Record Found]");
            found.displayVisitDetails();
        } else {
            System.out.println("\n[Not Found] Visit with ID " + visitId + " was not found for this patient.");
        }
    }

    private static void displayPatientVisitHistory(Patient patient) {
        System.out.println("\n=========================================================================");
        System.out.println("  VISIT HISTORY FOR: " + patient.getPatientName() + " (ID: " + patient.getPatientId() + ")");
        System.out.println("=========================================================================");
        patient.getVisitHistory().displayVisits();
    }

    // =========================================================================
    // 6. LOAD SAMPLE DATA
    // =========================================================================

    private static void loadSampleData() {
        System.out.println("\n>>> Loading University Assignment Sample Data...");

        // Sample Patients as specified in Section 13:
        // 1003 - John Silva - 45 - 0712345678 - Chest Pain
        // 1001 - Nimal Perera - 32 - 0771234567 - Fever
        // 1005 - Sarah Fernando - 28 - 0769876543 - Accident
        // 1002 - Kamal Perera - 56 - 0755555555 - Diabetes
        // 1004 - Anne Silva - 39 - 0788888888 - Headache
        Patient p1 = new Patient(1003, "John Silva", 45, "0712345678", "Chest Pain");
        Patient p2 = new Patient(1001, "Nimal Perera", 32, "0771234567", "Fever");
        Patient p3 = new Patient(1005, "Sarah Fernando", 28, "0769876543", "Accident");
        Patient p4 = new Patient(1002, "Kamal Perera", 56, "0755555555", "Diabetes");
        Patient p5 = new Patient(1004, "Anne Silva", 39, "0788888888", "Headache");

        // Insert into BST (inserted in non-sorted order to demonstrate BST sorting via in-order traversal)
        patientBST.insert(p1);
        patientBST.insert(p2);
        patientBST.insert(p3);
        patientBST.insert(p4);
        patientBST.insert(p5);

        // Add sample visits for patients (demonstrating independent Singly Linked Lists)
        p1.getVisitHistory().addVisit(new Visit(1, "2026-01-15", "Dr. Wickramasinghe", "Hypertension", "Prescribed ACE Inhibitor"));
        p1.getVisitHistory().addVisit(new Visit(2, "2026-05-20", "Dr. Wickramasinghe", "Angina checkup", "ECG Normal, rest recommended"));

        p2.getVisitHistory().addVisit(new Visit(1, "2026-03-10", "Dr. Gunawardena", "Seasonal Viral Flu", "Paracetamol & Hydration"));

        p4.getVisitHistory().addVisit(new Visit(1, "2025-11-04", "Dr. Fernando", "Type 2 Diabetes", "Metformin 500mg"));
        p4.getVisitHistory().addVisit(new Visit(2, "2026-04-12", "Dr. Fernando", "Routine HbA1c", "Dietary adjustments"));
        p4.getVisitHistory().addVisit(new Visit(3, "2026-08-01", "Dr. Fernando", "Diabetic Foot Exam", "Normal, annual review"));

        // Populate Emergency Queue (FIFO demonstration)
        // Patient A enters first (Nimal Perera)
        // Patient B enters second (Sarah Fernando)
        // Patient C enters third (John Silva)
        emergencyQueue.enqueue(p2);
        emergencyQueue.enqueue(p3);
        emergencyQueue.enqueue(p1);

        // Populate Treatment Stack (LIFO demonstration)
        treatmentStack.push(new TreatmentRecord(501, 1004, "Anne Silva", "Dr. Alwis", "Migraine relief injection", "2026-09-05"));
        treatmentStack.push(new TreatmentRecord(502, 1002, "Kamal Perera", "Dr. Fernando", "Insulin stabilization", "2026-09-06"));
        treatmentStack.push(new TreatmentRecord(503, 1005, "Sarah Fernando", "Dr. Jayasuriya", "Fracture splint applied", "2026-09-07"));

        System.out.println("[Success] Sample data successfully loaded!");
        System.out.println(" - 5 Patients inserted into BST (1003, 1001, 1005, 1002, 1004).");
        System.out.println(" - Clinical visits added to patients' individual Singly Linked Lists.");
        System.out.println(" - 3 Patients enqueued in Emergency Queue (FIFO: 1001 -> 1005 -> 1003).");
        System.out.println(" - 3 Treatment records pushed onto Treatment Stack (LIFO: 501 -> 502 -> [TOP: 503]).");
    }
}
