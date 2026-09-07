/**
 * Represents a single clinical visit record for a patient.
 */
public class Visit {
    private int visitId;
    private String visitDate;
    private String doctorName;
    private String diagnosis;
    private String treatment;

    public Visit(int visitId, String visitDate, String doctorName, String diagnosis, String treatment) {
        this.visitId = visitId;
        this.visitDate = visitDate;
        this.doctorName = doctorName;
        this.diagnosis = diagnosis;
        this.treatment = treatment;
    }

    public int getVisitId() {
        return visitId;
    }

    public void setVisitId(int visitId) {
        this.visitId = visitId;
    }

    public String getVisitDate() {
        return visitDate;
    }

    public void setVisitDate(String visitDate) {
        this.visitDate = visitDate;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public String getTreatment() {
        return treatment;
    }

    public void setTreatment(String treatment) {
        this.treatment = treatment;
    }

    public void displayVisitDetails() {
        System.out.printf("  | Visit ID   : %-6d | Date   : %-12s | Doctor: %-16s\n", visitId, visitDate, doctorName);
        System.out.printf("  | Diagnosis  : %-25s | Treatment: %-20s\n", diagnosis, treatment);
        System.out.println("  +-------------------------------------------------------------------------");
    }

    @Override
    public String toString() {
        return "Visit ID: " + visitId + ", Date: " + visitDate + ", Doctor: " + doctorName +
               ", Diagnosis: " + diagnosis + ", Treatment: " + treatment;
    }
}
