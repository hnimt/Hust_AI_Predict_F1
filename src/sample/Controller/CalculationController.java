package sample.Controller;

import sample.Database.Database;
import sample.Model.Evidence;
import sample.Model.F1;
import sample.Model.F1Evidence;
import sample.Repository.F1Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CalculationController {
    private F1Repository f1Repository;

    public CalculationController() {
    }

    public CalculationController(F1Repository f1Repository) {
        this.f1Repository = f1Repository;
    }

    public float getProbabilityByF1AndEvidence(String type, int f1ID, int evidenceId){
        float result = -1;
        String sql = "SELECT " +
                type +
                " FROM `F1Evidence` WHERE `F1ID` = ? AND `Evidence` = ?";
        try {
            PreparedStatement ps = Database.getInstance().connect().prepareStatement(sql);
            ps.setInt(1, f1ID);
            ps.setInt(2, evidenceId);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()){
                result = resultSet.getFloat(type);
            }
        } catch (SQLException e){
            e.printStackTrace();
        } finally {
            Database.getInstance().disconnect();
            return result;
        }
    }

    public float getProbabilityByEvidenceAndDisease(String type, int evidenceId, int diseaseId){
        float result = -1;
        String sql = "SELECT " +
                type +
                " FROM `EvidenceDisease` WHERE `Evidence` = ? AND `Disease` = ?";
        try {
            PreparedStatement ps = Database.getInstance().connect().prepareStatement(sql);
            ps.setInt(1, evidenceId);
            ps.setInt(2, diseaseId);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()){
                result = resultSet.getFloat(type);
            }
        } catch (SQLException e){
            e.printStackTrace();
        } finally {
            Database.getInstance().disconnect();
            return result;
        }
    }

    public float findMin(float f1EvidenceProbability, float evidenceDiseaseProbability){
        if (f1EvidenceProbability > evidenceDiseaseProbability) {
            if (evidenceDiseaseProbability <= 0) return f1EvidenceProbability;
            else return evidenceDiseaseProbability;
        } else {
            return f1EvidenceProbability;
        }
    }

    public float findMax(float f1EvidenceProbability, float evidenceDiseaseProbability){
        if (f1EvidenceProbability > evidenceDiseaseProbability) {
            return f1EvidenceProbability;
        } else {
            return evidenceDiseaseProbability;
        }
    }

    public float findMaxList(List<Float> floatList){
        return floatList.stream().max(Float::compare).get();
    }

    public float findMinList(List<Float> floatList){
        return floatList.stream().min(Float::compare).get();
    }

    public float caclProbability(float pos, float neu, float neg){
        float tmpPi = 1 - (pos + neu + neg);
        return pos - neg*tmpPi;
    }

    public float calculateResult(List<F1Evidence> f1Evidences){
        List<Float> listPos = new ArrayList<>();
        List<Float> listNeu = new ArrayList<>();
        List<Float> listNeg = new ArrayList<>();
        for (F1Evidence f1Evidence : f1Evidences){
            Evidence evidence = f1Evidence.getEvidence();
            F1 f1 = f1Evidence.getF1();
            float f1EviPos = f1Evidence.getPositive();
            float eviDisPos = getProbabilityByEvidenceAndDisease("Positive", evidence.getEvidenceId(), 1);
            listPos.add(findMin(f1EviPos, f1EviPos));
            float f1EviNeu = f1Evidence.getNeutral();
            float eviDisNeu = getProbabilityByEvidenceAndDisease("Neutral", evidence.getEvidenceId(), 1);
            listNeu.add(findMin(f1EviNeu, eviDisNeu));
            float f1EviNeg = f1Evidence.getNegative();
            float EviDisNeg = getProbabilityByEvidenceAndDisease("Negative", evidence.getEvidenceId(), 1);
            listNeg.add(findMax(f1EviNeg, EviDisNeg));
        }
        float finalPos = findMaxList(listPos);
        float finalNeu = findMinList(listNeu);
        float finalNeg = findMinList(listNeg);
        return caclProbability(finalPos, finalNeu, finalNeg);
    }

}
