package sample.Model;

public class Disease {
    private int diseaseId;
    private String diseaseName;
    private String diseaseDescription;
    private String diseaseUrlImage;

    public Disease() {
    }

    public Disease(int diseaseId, String diseaseName, String diseaseDescription, String diseaseUrlImage) {
        this.diseaseId = diseaseId;
        this.diseaseName = diseaseName;
        this.diseaseDescription = diseaseDescription;
        this.diseaseUrlImage = diseaseUrlImage;
    }

    public int getDiseaseId() {
        return diseaseId;
    }

    public void setDiseaseId(int diseaseId) {
        this.diseaseId = diseaseId;
    }

    public String getDiseaseName() {
        return diseaseName;
    }

    public void setDiseaseName(String diseaseName) {
        this.diseaseName = diseaseName;
    }

    public String getDiseaseDescription() {
        return diseaseDescription;
    }

    public void setDiseaseDescription(String diseaseDescription) {
        this.diseaseDescription = diseaseDescription;
    }

    public String getDiseaseUrlImage() {
        return diseaseUrlImage;
    }

    public void setDiseaseUrlImage(String diseaseUrlImage) {
        this.diseaseUrlImage = diseaseUrlImage;
    }
}
