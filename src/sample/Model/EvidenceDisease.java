package sample.Model;

import sample.Database.Database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EvidenceDisease {
    private int id;
    private Evidence evidence;
    private Disease disease;
    private float positive;
    private float neutral;
    private float negative;

    public EvidenceDisease() {
    }

    public EvidenceDisease(int id, Evidence evidence, Disease disease, float positive, float neutral, float negative) {
        this.id = id;
        this.evidence = evidence;
        this.disease = disease;
        this.positive = positive;
        this.neutral = neutral;
        this.negative = negative;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Evidence getEvidence() {
        return evidence;
    }

    public void setEvidence(Evidence evidence) {
        this.evidence = evidence;
    }

    public Disease getDisease() {
        return disease;
    }

    public void setDisease(Disease disease) {
        this.disease = disease;
    }

    public float getPositive() {
        return positive;
    }

    public void setPositive(float positive) {
        this.positive = positive;
    }

    public float getNeutral() {
        return neutral;
    }

    public void setNeutral(float neutral) {
        this.neutral = neutral;
    }

    public float getNegative() {
        return negative;
    }

    public void setNegative(float negative) {
        this.negative = negative;
    }

    public static EvidenceDisease getByEviAndDis(Evidence evidence, Disease disease){
        EvidenceDisease evidenceDisease = new EvidenceDisease();
        String sql = "SELECT * FROM EvidenceDisease WHERE Evidence = ? AND Disease = ?";
        try {
            PreparedStatement ps = Database.getInstance().connect().prepareStatement(sql);
            ps.setInt(1, evidence.getEvidenceId());
            ps.setInt(2, disease.getDiseaseId());
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()){
                int id = resultSet.getInt("ID");
                int evidenceId = resultSet.getInt("Evidence");
                int diseaseId = resultSet.getInt("Disease");
                float pos = resultSet.getFloat("Positive");
                float neu = resultSet.getFloat("Neutral");
                float neg = resultSet.getFloat("Negative");
                evidenceDisease = new EvidenceDisease(id, evidence, disease, pos, neu, neg);
            }

        } catch (SQLException e){
            e.printStackTrace();
        } finally {
            Database.getInstance().disconnect();
            return evidenceDisease;
        }
    }

    public static void updateEviDis(EvidenceDisease evidenceDisease){
        String sql = "UPDATE `EvidenceDisease` SET `Positive`=?,`Neutral`=?,`Negative`=? WHERE Evidence = ? AND Disease = ?";
        try {
            PreparedStatement ps = Database.getInstance().connect().prepareStatement(sql);
            ps.setFloat(1,evidenceDisease.getPositive());
            ps.setFloat(2,evidenceDisease.getNeutral());
            ps.setFloat(3,evidenceDisease.getNegative());
            ps.setInt(4,evidenceDisease.getEvidence().getEvidenceId());
            ps.setInt(5,evidenceDisease.getDisease().getDiseaseId());
            ps.executeUpdate();
        } catch (SQLException e){
            e.printStackTrace();
        } finally {
            Database.getInstance().disconnect();
        }
    }
}
