package sample.Model;

public class F1 {
    private int F1Id;
    private String F1Name;
    private String F1Description;
    private String location;
    private String F1UrlImage;

    public F1() {
    }

    public F1(int f1Id, String f1Name, String f1Description, String location, String f1UrlImage) {
        F1Id = f1Id;
        F1Name = f1Name;
        F1Description = f1Description;
        this.location = location;
        F1UrlImage = f1UrlImage;
    }

    public F1(int f1Id, String f1Name, String f1Description, String location) {
        F1Id = f1Id;
        F1Name = f1Name;
        F1Description = f1Description;
        location = location;
    }

    public F1(String f1Name, String f1Description, String location) {
        F1Name = f1Name;
        F1Description = f1Description;
        location = location;
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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getF1UrlImage() {
        return F1UrlImage;
    }

    public void setF1UrlImage(String f1UrlImage) {
        F1UrlImage = f1UrlImage;
    }
}
