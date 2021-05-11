package sample.Repository;

import sample.Database.Database;
import sample.Model.Disease;
import sample.Model.Evidence;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DiseaseRepository {
    List<Disease> diseaseList;

    public DiseaseRepository() {
        this.diseaseList = new ArrayList<>();
    }

    public DiseaseRepository(List<Disease> diseaseList) {
        this.diseaseList = diseaseList;
    }

    public List<Disease> getDiseaseList() {
        return diseaseList;
    }

    public void setDiseaseList(List<Disease> diseaseList) {
        this.diseaseList = diseaseList;
    }

    public List<Disease> getAllDiseases(){
        String sql = "SELECT * FROM Disease";
        diseaseList.clear();
        try {
            Statement sm = Database.getInstance().connect().createStatement();
            ResultSet resultSet = sm.executeQuery(sql);
            while (resultSet.next()){
                int diseaseId = resultSet.getInt("DiseaseID");
                String diseaseName = resultSet.getString("DiseaseName");
                String diseaseDescription = resultSet.getString("DiseaseDescription");
                Disease disease = new Disease(diseaseId, diseaseName, diseaseDescription);
                diseaseList.add(disease);
            }
        } catch (SQLException e){
            e.printStackTrace();
        } finally {
            Database.getInstance().disconnect();
            return diseaseList;
        }
    }

    public Disease findDisByName(String name){
        for (Disease disease : diseaseList){
            if (disease.getDiseaseName().equalsIgnoreCase(name)) return disease;
        }
        return null;
    }
}
