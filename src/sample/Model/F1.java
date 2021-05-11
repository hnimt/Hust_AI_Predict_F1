package sample.Model;

public class F1 {
    private int F1Id;
    private String F1Name;
    private String F1Description;
    private String F0Name;
    private String F1UrlImage;

    public F1() {
    }

    public F1(int f1Id, String f1Name, String f1Description, String f0Name, String f1UrlImage) {
        F1Id = f1Id;
        F1Name = f1Name;
        F1Description = f1Description;
        F0Name = f0Name;
        F1UrlImage = f1UrlImage;
    }

    public F1(String f1Name, String f1Description, String f0Name) {
        F1Name = f1Name;
        F1Description = f1Description;
        F0Name = f0Name;
    }

    public int getF1Id() {
        return F1Id;
    }

    public void setF1Id(int f1Id) {
        F1Id = f1Id;
    }

    public String getF1Name() {
        return F1Name;
    }

    public void setF1Name(String f1Name) {
        F1Name = f1Name;
    }

    public String getF1Description() {
        return F1Description;
    }

    public void setF1Description(String f1Description) {
        F1Description = f1Description;
    }

    public String getF0Name() {
        return F0Name;
    }

    public void setF0Name(String f0Name) {
        F0Name = f0Name;
    }

    public String getF1UrlImage() {
        return F1UrlImage;
    }

    public void setF1UrlImage(String f1UrlImage) {
        F1UrlImage = f1UrlImage;
    }
}
