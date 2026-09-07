/**
 * Represents a completed emergency treatment session record.
 */
public class TreatmentRecord {
    private int treatmentId;
    private int patientId;
    private String patientName;
    private String doctorName;
    private String treatment;
    private String treatmentDate;

    public TreatmentRecord(int treatmentId, int patientId, String patientName, String doctorName, String treatment, String treatmentDate) {
        this.treatmentId = treatmentId;
        this.patientId = patientId;
        this.patientName = patientName;
        this.doctorName = doctorName;
        this.treatment = treatment;
        this.treatmentDate = treatmentDate;
    }

    public int getTreatmentId() {
        return treatmentId;
    }

    public void setTreatmentId(int treatmentId) {
        this.treatmentId = treatmentId;
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

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getTreatment() {
        return treatment;
    }

    public void setTreatment(String treatment) {
        this.treatment = treatment;
    }

    public String getTreatmentDate() {
        return treatmentDate;
    }

    public void setTreatmentDate(String treatmentDate) {
        this.treatmentDate = treatmentDate;
    }

    public void displayRecordDetails() {
        System.out.println("==================================================");
        System.out.printf("  Treatment ID   : %d\n", treatmentId);
        System.out.printf("  Patient ID     : %d\n", patientId);
        System.out.printf("  Patient Name   : %s\n", patientName);
        System.out.printf("  Attending Dr.  : %s\n", doctorName);
        System.out.printf("  Treatment Done : %s\n", treatment);
        System.out.printf("  Date & Time    : %s\n", treatmentDate);
        System.out.println("==================================================");
    }

    @Override
    public String toString() {
        return String.format("[Treatment #%d] Patient: %s (ID: %d) | Doctor: %s | Treatment: %s | Date: %s",
                treatmentId, patientName, patientId, doctorName, treatment, treatmentDate);
    }
}
