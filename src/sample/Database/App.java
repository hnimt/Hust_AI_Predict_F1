package sample.Database;

import sample.Model.Evidence;
import sample.Repository.EvidenceRepository;

import java.util.List;

public class App {
    public static void main(String[] args) {
        System.out.println("Connect db with JDBC Driver");
        EvidenceRepository evidenceRepository = new EvidenceRepository();
        List<Evidence> evidenceList = evidenceRepository.getAllEvidences();
        evidenceList.forEach(System.out::println);
    }
}
