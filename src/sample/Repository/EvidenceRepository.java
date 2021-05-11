package sample.Repository;

import sample.Database.Database;
import sample.Model.Evidence;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class EvidenceRepository {
    private List<Evidence> evidenceList;

    public EvidenceRepository() {
        this.evidenceList = new ArrayList<>();
    }

    public EvidenceRepository(List<Evidence> evidenceList) {
        this.evidenceList = evidenceList;
    }

    public List<Evidence> getEvidenceList() {
        return evidenceList;
    }

    public void setEvidenceList(List<Evidence> evidenceList) {
        this.evidenceList = evidenceList;
    }

    public List<Evidence> getAllEvidences(){
        String sql = "SELECT * FROM Evidence";
        evidenceList.clear();
        try {
            Statement sm = Database.getInstance().connect().createStatement();
            ResultSet resultSet = sm.executeQuery(sql);
            while (resultSet.next()){
                int evidenceId = resultSet.getInt("EvidenceID");
                String evidenceName = resultSet.getString("EvidenceName");
                String evidenceDescription = resultSet.getString("EvidenceDescription");
                Evidence evidence = new Evidence(evidenceId, evidenceName, evidenceDescription);
                evidenceList.add(evidence);
            }
        } catch (SQLException e){
            e.printStackTrace();
        } finally {
            Database.getInstance().disconnect();
            return evidenceList;
        }
    }

    public Evidence findEviByName(String name){
        for (Evidence evidence : evidenceList){
            if (evidence.getEvidenceName().equalsIgnoreCase(name)) return evidence;
        }
        return null;
    }
}
