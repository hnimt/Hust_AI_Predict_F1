package sample.View;

public class F1DiseaseView {
    private int id;
    private String f1Name;
    private String diseaseName;
    private float prediction;

    public F1DiseaseView(int id, String f1Name, String diseaseName, float prediction) {
        this.id = id;
        this.f1Name = f1Name;
        this.diseaseName = diseaseName;
        this.prediction = prediction;
    }

    public F1DiseaseView() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getF1Name() {
        return f1Name;
    }

    public void setF1Name(String f1Name) {
        this.f1Name = f1Name;
    }

    public String getDiseaseName() {
        return diseaseName;
    }

    public void setDiseaseName(String diseaseName) {
        this.diseaseName = diseaseName;
    }

    public float getPrediction() {
        return prediction;
    }

    public void setPrediction(float prediction) {
        this.prediction = prediction;
    }
}
