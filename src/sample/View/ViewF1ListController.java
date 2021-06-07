package sample.View;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import sample.Model.F1;
import sample.Model.F1Disease;

import java.util.ArrayList;
import java.util.List;

public class ViewF1ListController extends Stage{

    @FXML
    private TableView<F1DiseaseView> tblF1Disease;

    private TableColumn clId = new TableColumn("F1Id");
    private TableColumn clF1 = new TableColumn("F1");
    private TableColumn clDisease = new TableColumn("Disease");
    private TableColumn clPrediction = new TableColumn("Prediction");

    private F1Disease f1Disease;

    public void printTable(){
        try {
            f1Disease = new F1Disease();
            clId.setMinWidth(50);
            clF1.setMinWidth(330);
            clDisease.setMinWidth(100);
            clPrediction.setMinWidth(80);
            clId.setCellValueFactory(new PropertyValueFactory<>("id"));
            clF1.setCellValueFactory(new PropertyValueFactory<>("f1Name"));
            clDisease.setCellValueFactory(new PropertyValueFactory<>("diseaseName"));
            clPrediction.setCellValueFactory(new PropertyValueFactory<>("prediction"));
            List<F1Disease> f1Diseases = f1Disease.getAllF1Disease();
            List<F1DiseaseView> f1DiseaseViews = new ArrayList<>();
            for (F1Disease f1Disease1 : f1Diseases){
                int id = f1Disease1.getF1().getF1Id();
                String f1Name = f1Disease1.getF1().getF1Name();
                String disName = f1Disease1.getDisease().getDiseaseName();
                float prediction = f1Disease1.getPrediction();
                F1DiseaseView f1DiseaseView = new F1DiseaseView(id, f1Name, disName, prediction);
                f1DiseaseViews.add(f1DiseaseView);
            }

            ObservableList obserF1Disease = FXCollections.observableArrayList(f1DiseaseViews);

            tblF1Disease.setItems(obserF1Disease);
            tblF1Disease.getColumns().addAll(clId, clF1, clDisease, clPrediction);
        } catch (Exception e) {
            System.out.println("Cannot find id");
        }

    }

    private void closeStage(ActionEvent event) {
        Node source = (Node)  event.getSource();
        Stage stage  = (Stage) source.getScene().getWindow();
        stage.close();
    }
}
