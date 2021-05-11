package sample.View;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import sample.Controller.CalculationController;
import sample.Model.Evidence;
import sample.Model.F1;
import sample.Model.F1Evidence;
import sample.Repository.EvidenceRepository;

import java.net.URL;
import java.util.*;

public class Controller implements Initializable {

    @FXML
    private TextField txtName;
    @FXML
    private TextField txtDescription;
    @FXML
    private TextField txtF0Name;
    @FXML
    private Label txtResult;

    @FXML
    private Button btnChooseImage;
    @FXML
    private GridPane grid;
    @FXML
    private Button btnCalculate;

    private List<F1Evidence> f1EvidenceList;
    private EvidenceRepository evidenceRepository;
    private F1 f1;

    private CalculationController calcController;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        calcController = new CalculationController();
        f1 = new F1();
        f1EvidenceList = new ArrayList<>();
        grid.setPadding(new Insets(10));
        grid.setVgap(20);
        grid.setHgap(20);
        grid.setAlignment(Pos.CENTER);

        int columnIndex = 0;
        int rowIndex = 0;
        evidenceRepository = new EvidenceRepository();
        List<Evidence> evidenceList = evidenceRepository.getAllEvidences();

        // Print grid
        for (Evidence evidence : evidenceList){
            if (columnIndex > 2){
                columnIndex=0;
                rowIndex++;
                GridPane subGrid = showSubGrid(f1, evidence);
                grid.add(subGrid, columnIndex, rowIndex);
                columnIndex++;
            } else {
                GridPane subGrid = showSubGrid(f1, evidence);
                grid.add(subGrid, columnIndex, rowIndex);
                columnIndex++;
            }
        }

        // Calculate
        btnCalculate.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                f1.setF1Name(txtName.getText());
                f1.setF1Description(txtDescription.getText());
                f1.setF0Name(txtDescription.getText());

                if (!isSumProbValid(f1EvidenceList)){
                    showErrSumProb();
                } else {
                    float result = calcController.calculateResult(f1EvidenceList);
                    txtResult.setText(printPredictResult(result));
                }
            }
        });
    }

    // Print subgrid
    public GridPane showSubGrid(F1 f1, Evidence evidence){
        F1Evidence f1Evidence = new F1Evidence(f1, evidence);
        GridPane subGrid = new GridPane();
        subGrid.setVgap(10);
        subGrid.setHgap(10);
        subGrid.setAlignment(Pos.CENTER);
        Text evidenceName = new Text(evidence.getEvidenceName());
        Label lblPositive = new Label("Positive");
        Label lblNeutral = new Label("Neutral");
        Label lblNegative = new Label("Negative");

        ObservableList<String> giaTu = FXCollections.observableArrayList(
                "None", "Very Low", "Low", "A little low", "Medium",
                "A little high", "High", "Very high", "Perfect"
        );
        ComboBox comboBoxPositive = new ComboBox(giaTu);
        comboBoxPositive.setValue("None");
        ComboBox comboBoxNeutral = new ComboBox(giaTu);
        comboBoxNeutral.setValue("None");
        ComboBox comboBoxNegative = new ComboBox(giaTu);
        comboBoxNegative.setValue("None");

        Label lblPosVal = new Label();
        lblPosVal.setPrefWidth(105);
        lblPosVal.setAlignment(Pos.CENTER);
        lblPosVal.setText(String.valueOf(changeStrToFl(comboBoxPositive.getValue().toString())));
        Label lblNeuVal = new Label();
        lblNeuVal.setPrefWidth(105);
        lblNeuVal.setAlignment(Pos.CENTER);
        lblNeuVal.setText(String.valueOf(changeStrToFl(comboBoxNeutral.getValue().toString())));
        Label lblNegVal = new Label();
        lblNegVal.setPrefWidth(105);
        lblNegVal.setAlignment(Pos.CENTER);
        lblNegVal.setText(String.valueOf(changeStrToFl(comboBoxNegative.getValue().toString())));

        subGrid.add(evidenceName, 0, 0);
        subGrid.add(lblPositive, 0, 1);
        subGrid.add(lblNeutral, 1, 1);
        subGrid.add(lblNegative, 2, 1);
        subGrid.add(comboBoxPositive, 0, 2);
        subGrid.add(comboBoxNeutral, 1, 2);
        subGrid.add(comboBoxNegative, 2, 2);
        subGrid.add(lblPosVal, 0, 3);
        subGrid.add(lblNeuVal, 1, 3);
        subGrid.add(lblNegVal, 2, 3);

        // Handle combobox
        comboBoxPositive.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                float positive = changeStrToFl(comboBoxPositive.getValue().toString());
                f1Evidence.setPositive(positive);
                lblPosVal.setText(String.valueOf(positive));
            }
        });
        comboBoxNeutral.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                float neutral = changeStrToFl(comboBoxNeutral.getValue().toString());
                f1Evidence.setNeutral(neutral);
                lblNeuVal.setText(String.valueOf(neutral));
            }
        });
        comboBoxNegative.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                float negative = changeStrToFl(comboBoxNegative.getValue().toString());
                f1Evidence.setNegative(negative);
                lblNegVal.setText(String.valueOf(negative));
            }
        });

        f1EvidenceList.add(f1Evidence);

        return subGrid;
    }

    public float changeStrToFl(String str){
        if (str.equalsIgnoreCase("None")) return 0;
        else if (str.equalsIgnoreCase("Very Low")) return 0.125F;
        else if (str.equalsIgnoreCase("Low")) return 0.25F;
        else if (str.equalsIgnoreCase("A little low")) return 0.375F;
        else if (str.equalsIgnoreCase("Medium")) return 0.5F;
        else if (str.equalsIgnoreCase("A little high")) return 0.625F;
        else if (str.equalsIgnoreCase("High")) return 0.625F;
        else if (str.equalsIgnoreCase("Very high")) return 0.875F;
        else if (str.equalsIgnoreCase("Perfect")) return 1F;
        return -1;
    }

    public void showErrSumProb(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error sum probability");
        alert.setHeaderText("Error sum probability");
        alert.setContentText("Sum probability of a evidence is less or equal 1");
        alert.show();
    }

    public boolean isSumProbValid(List<F1Evidence> f1Evidences){
        for (F1Evidence f1Evidence : f1Evidences){
            if ((f1Evidence.getPositive()+f1Evidence.getNeutral()+f1Evidence.getNegative()) > 1) return false;
        }
        return true;
    }

    public String printPredictResult(float result){
        Float percentResult = result * 100;
        if (result < 0.25) return String.format("Very low risk of Covid 19 infection with : %.2f%%.", percentResult);
        if (result < 0.5) return String.format("Low risk of Covid 19 infection with : %.2f%%.", percentResult);
        if (result < 0.75) return String.format("High risk of Covid 19 infection with : %.2f%%.", percentResult);
        else return String.format("Very high risk of Covid 19 infection with : %.2f%%.", percentResult);
    }
}
