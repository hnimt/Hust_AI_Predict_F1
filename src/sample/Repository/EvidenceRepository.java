package sample.Repository;

import sample.Database.Database;
import sample.Model.Evidence;
import sample.Model.F1;

import java.sql.PreparedStatement;
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

    public static Evidence findEviById(int id){
        String sql = "SELECT * FROM `Evidence` WHERE EvidenceID = ?";
        Evidence evidence = null;
        try {
            PreparedStatement ps = Database.getInstance().connect().prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()){
                int eviId = resultSet.getInt("EvidenceID");
                String eviName = resultSet.getString("EvidenceName");
                String eviDes = resultSet.getString("EvidenceDescription");
                evidence = new Evidence(eviId, eviName, eviDes);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Database.getInstance().disconnect();
            return evidence;
        }
    }
}
