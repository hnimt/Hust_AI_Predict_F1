package sample.View;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.Model.Disease;
import sample.Model.Evidence;
import sample.Model.EvidenceDisease;
import sample.Repository.DiseaseRepository;
import sample.Repository.EvidenceRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

public class EditEvidenceDiseaseController extends Stage {

    @FXML
    private ComboBox cbEvidence;
    @FXML
    private ComboBox cbDisease;
    @FXML
    private TextField txtPositive;
    @FXML
    private TextField txtNeutral;
    @FXML
    private TextField txtNegative;
    @FXML
    private Button btnOk;
    @FXML
    private Button btnCancel;
    private float positive;
    private float neutral;
    private float negative;
    private ObservableList<String> evidences;
    EvidenceRepository evidenceRepository;
    private ObservableList<String> diseases;
    DiseaseRepository diseaseRepository;

    private EvidenceDisease evidenceDisease;

    private Evidence selectedEvidence;
    private Disease selectedDisease;

    public void setData() {
        evidenceDisease = new EvidenceDisease();
        evidenceRepository = new EvidenceRepository();
        List<String> evidencesName = new ArrayList<>();
        evidenceRepository.getAllEvidences().forEach(evidence -> evidencesName.add(evidence.getEvidenceName()));
        evidences = FXCollections.observableArrayList(evidencesName);
        cbEvidence.setItems(evidences);

        diseaseRepository = new DiseaseRepository();
        List<String> diseasesName = new ArrayList<>();
        diseaseRepository.getAllDiseases().forEach(disease -> diseasesName.add(disease.getDiseaseName()));
        diseases = FXCollections.observableArrayList(diseasesName);
        cbDisease.setItems(diseases);

        cbEvidence.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String strEvidence = cbEvidence.getValue().toString();
                selectedEvidence = evidenceRepository.findEviByName(strEvidence);
                if (selectedDisease!=null){
                    evidenceDisease = EvidenceDisease.getByEviAndDis(selectedEvidence, selectedDisease);
                    txtPositive.setText(String.valueOf(evidenceDisease.getPositive()));
                    txtNeutral.setText(String.valueOf(evidenceDisease.getNeutral()));
                    txtNegative.setText(String.valueOf(evidenceDisease.getNegative()));
                }
            }
        });

        cbDisease.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String strDis = cbDisease.getValue().toString();
                selectedDisease = diseaseRepository.findDisByName(strDis);
                if (selectedEvidence!=null){
                    evidenceDisease = EvidenceDisease.getByEviAndDis(selectedEvidence, selectedDisease);
                    txtPositive.setText(String.valueOf(evidenceDisease.getPositive()));
                    txtNeutral.setText(String.valueOf(evidenceDisease.getNeutral()));
                    txtNegative.setText(String.valueOf(evidenceDisease.getNegative()));
                }
            }
        });


    }

    public void editEviDis(){
        btnOk.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                evidenceDisease.setPositive(Float.valueOf(txtPositive.getText()));
                evidenceDisease.setNeutral(Float.valueOf(txtNeutral.getText()));
                evidenceDisease.setNegative(Float.valueOf(txtNegative.getText()));
                EvidenceDisease.updateEviDis(evidenceDisease);
            }
        });

        btnCancel.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                closeStage(event);
            }
        });
    }

    private void closeStage(ActionEvent event) {
        Node source = (Node)  event.getSource();
        Stage stage  = (Stage) source.getScene().getWindow();
        stage.close();
    }

}
