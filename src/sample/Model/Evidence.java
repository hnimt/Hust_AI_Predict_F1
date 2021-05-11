package sample.Model;

public class Evidence {
    private int evidenceId;
    private String evidenceName;
    private String evidenceDescription;

    public Evidence() {
    }

    public Evidence(int evidenceId, String evidenceName, String evidenceDescription) {
        this.evidenceId = evidenceId;
        this.evidenceName = evidenceName;
        this.evidenceDescription = evidenceDescription;
    }

    public int getEvidenceId() {
        return evidenceId;
    }

    public void setEvidenceId(int evidenceId) {
        this.evidenceId = evidenceId;
    }

    public String getEvidenceName() {
        return evidenceName;
    }

    public void setEvidenceName(String evidenceName) {
        this.evidenceName = evidenceName;
    }

    public String getEvidenceDescription() {
        return evidenceDescription;
    }

    public void setEvidenceDescription(String evidenceDescription) {
        this.evidenceDescription = evidenceDescription;
    }
}
