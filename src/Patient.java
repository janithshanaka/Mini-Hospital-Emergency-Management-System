/**
 * Represents a patient registered in the hospital management system.
 * Each patient contains basic demographic data and encapsulates an independent
 * Singly Linked List (VisitHistory) to store clinical visits.
 */
public class Patient {
    private int patientId;
    private String patientName;
    private int age;
    private String contactNumber;
    private String medicalCondition;
    private VisitHistory visitHistory;

    /**
     * Constructs a new Patient instance with an empty visit history.
     *
     * @param patientId        the unique patient identifier (used as BST key)
     * @param patientName      the patient's full name
     * @param age              the patient's age in years
     * @param contactNumber    the patient's telephone/mobile number
     * @param medicalCondition the primary symptom or diagnosis
     */
    public Patient(int patientId, String patientName, int age, String contactNumber, String medicalCondition) {
        this.patientId = patientId;
        this.patientName = patientName;
        this.age = age;
        this.contactNumber = contactNumber;
        this.medicalCondition = medicalCondition;
        this.visitHistory = new VisitHistory();
    }

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getMedicalCondition() {
        return medicalCondition;
    }

    public void setMedicalCondition(String medicalCondition) {
        this.medicalCondition = medicalCondition;
    }

    public VisitHistory getVisitHistory() {
        return visitHistory;
    }

    public void setVisitHistory(VisitHistory visitHistory) {
        this.visitHistory = visitHistory;
    }

    /**
     * Displays complete details of the patient in a formatted box.
     */
    public void displayPatientDetails() {
        System.out.println("==================================================");
        System.out.printf("  Patient ID        : %d\n", patientId);
        System.out.printf("  Patient Name      : %s\n", patientName);
        System.out.printf("  Age               : %d\n", age);
        System.out.printf("  Contact Number    : %s\n", contactNumber);
        System.out.printf("  Medical Condition : %s\n", medicalCondition);
        System.out.printf("  Total Visits      : %d\n", visitHistory.getSize());
        System.out.println("==================================================");
    }

    @Override
    public String toString() {
        return String.format("[ID: %d] %s | Age: %d | Contact: %s | Condition: %s",
                patientId, patientName, age, contactNumber, medicalCondition);
    }
}
