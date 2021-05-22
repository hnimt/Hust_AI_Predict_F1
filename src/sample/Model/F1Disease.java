package sample.Model;

import sample.Database.Database;
import sample.Repository.DiseaseRepository;
import sample.Repository.F1Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class F1Disease {
    private int id;
    private F1 f1;
    private Disease disease;
    private float prediction;

    public F1Disease() {
    }

    public F1Disease(int id, F1 f1, Disease disease, float prediction) {
        this.id = id;
        this.f1 = f1;
        this.disease = disease;
        this.prediction = prediction;
    }

    @Override
    public String toString() {
        return "F1Disease{" +
                "id=" + id +
                ", f1=" + f1.getF1Name() +
                ", disease=" + disease.getDiseaseName() +
                ", prediction=" + prediction +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public F1 getF1() {
        return f1;
    }

    public void setF1(F1 f1) {
        this.f1 = f1;
    }

    public Disease getDisease() {
        return disease;
    }

    public void setDisease(Disease disease) {
        this.disease = disease;
    }

    public float getPrediction() {
        return prediction;
    }

    public void setPrediction(float prediction) {
        this.prediction = prediction;
    }

    // Get id f1
    public int getF1(F1 f1){
        String sql = "SELECT * FROM `F1` WHERE `F1Name`= ?";
        int index = 0;
        try {
            PreparedStatement ps = Database.getInstance().connect().prepareStatement(sql);
            ps.setString(1, f1.getF1Name());
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()){
                index = resultSet.getInt("F1ID");
            }
        } catch (SQLException e){
            e.printStackTrace();
        } finally {
            Database.getInstance().disconnect();
            return index;
        }
    }

    // Get all F1Disease
    public List<F1Disease> getAllF1Disease(){
        List<F1Disease> f1Diseases = new ArrayList<>();
        String sql = "SELECT * FROM `F1Disease` ORDER BY Prediction DESC";
        try {
            Statement sm = Database.getInstance().connect().createStatement();
            ResultSet resultSet = sm.executeQuery(sql);
            while (resultSet.next()){
                int id = resultSet.getInt("ID");
                int f1Id = resultSet.getInt("F1");
                int diseaseId = resultSet.getInt("Disease");
                float prediction = resultSet.getFloat("Prediction");
                F1Disease f1Disease = new F1Disease(id, F1Repository.findF1ById(f1Id), DiseaseRepository.findDisById(diseaseId), prediction);
                f1Diseases.add(f1Disease);
            }
        } catch (SQLException e){
            e.printStackTrace();
        } finally {
            Database.getInstance().disconnect();
            return f1Diseases;
        }
    }

    // Add F1 to DB
    public void addF1ToDB(F1 f1){
        String sql = "INSERT INTO `F1`(`F1Name`, `F1Description`, `F0Name`) VALUES (?,?,?)";
        try {
            PreparedStatement ps = Database.getInstance().connect().prepareStatement(sql);
            ps.setString(1, f1.getF1Name());
            ps.setString(2, f1.getF1Description());
            ps.setString(3, f1.getF0Name());
            ps.executeUpdate();
        } catch (SQLException e){
            e.printStackTrace();
        } finally {
            Database.getInstance().disconnect();
        }
    }

    // Add f1vs disease to db
    public void addF1DiseaseToDB(F1 f1, int diseaseId, float result){
        addF1ToDB(f1);
        int f1Id = getF1(f1);
        String sql = "INSERT INTO `F1Disease`(`F1`, `Disease`, `Prediction`) VALUES (?,?,?)";
        try {
            PreparedStatement ps = Database.getInstance().connect().prepareStatement(sql);
            ps.setInt(1, f1Id);
            ps.setInt(2, diseaseId);
            ps.setFloat(3, result);
            ps.executeUpdate();
        } catch (SQLException e){
            e.printStackTrace();
        } finally {
            Database.getInstance().disconnect();
        }
    }
}
