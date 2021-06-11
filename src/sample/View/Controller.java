package sample.View;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sample.Controller.CalculationController;
import sample.Model.Evidence;
import sample.Model.F1;
import sample.Model.F1Disease;
import sample.Model.F1Evidence;
import sample.Repository.EvidenceRepository;

import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;

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
    private MenuItem mnSave;
    @FXML
    private MenuItem mnNew;
    @FXML
    private MenuItem mnEdit;
    @FXML
    private MenuItem mnView;

    @FXML
    private Button btnChooseImage;
    @FXML
    private GridPane grid;
    @FXML
    private Button btnCalculate;

    private List<F1Evidence> f1EvidenceList;
    private EvidenceRepository evidenceRepository;
    private F1 f1;
    private F1Disease f1Disease;

    private CalculationController calcController;
    float result;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        f1Disease = new F1Disease();
        calcController = new CalculationController();
        f1 = new F1();
        f1EvidenceList = new ArrayList<>();
        evidenceRepository = new EvidenceRepository();
        List<Evidence> evidenceList = evidenceRepository.getAllEvidences();

        grid.setPadding(new Insets(10));
        grid.setVgap(20);
        grid.setHgap(20);
        grid.setAlignment(Pos.CENTER);

        // Menu save
        mnSave.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                f1Disease.addF1DiseaseToDB(f1, 1, result);
            }
        });

        // Menu new
        mnNew.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                txtName.clear();
                txtDescription.clear();
                txtF0Name.clear();
                txtResult.setText("");
                grid.getChildren().clear();
                f1EvidenceList.clear();
                printSubGrid(evidenceList);
            }
        });

        // Menu edit
        mnEdit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    showEviDisEdit();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        // Menu view
        mnView.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    showF1Disease();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        // Print sub grid
        printSubGrid(evidenceList);


        // Calculate
        btnCalculate.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                f1.setF1Name(txtName.getText());
                f1.setF1Description(txtDescription.getText());
                f1.setLocation(txtDescription.getText());

                List<F1Evidence> tmpF1EvidenceList = f1EvidenceList.stream()
                        .filter(f1Evidence -> f1Evidence.getPositive()!=0 || f1Evidence.getNeutral()!=0 || f1Evidence.getNegative()!=0)
                        .collect(Collectors.toList());

                if (!isSumProbValid(tmpF1EvidenceList)){
                    showErrSumProb();
                } else {
                    result = calcController.calculateResult(tmpF1EvidenceList);
                    printPredictResult(result);
                }
            }
        });
    }

    // Function handle subgrid
    public GridPane showSubGrid(F1 f1, Evidence evidence){
        F1Evidence f1Evidence = new F1Evidence(f1, evidence);
        GridPane subGrid = new GridPane();
        subGrid.getStyleClass().add("subgrid");
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
        comboBoxPositive.getStyleClass().add("select");
        comboBoxNeutral.getStyleClass().add("select");
        comboBoxNegative.getStyleClass().add("select");

        Label lblPosVal = new Label();
        lblPosVal.setPrefWidth(130);
        lblPosVal.setAlignment(Pos.CENTER);
        lblPosVal.setText(String.valueOf(changeStrToFl(comboBoxPositive.getValue().toString())));

        Label lblNeuVal = new Label();
        lblNeuVal.setPrefWidth(130);
        lblNeuVal.setAlignment(Pos.CENTER);
        lblNeuVal.setText(String.valueOf(changeStrToFl(comboBoxNeutral.getValue().toString())));

        Label lblNegVal = new Label();
        lblNegVal.setPrefWidth(130);
        lblNegVal.setAlignment(Pos.CENTER);
        lblNegVal.setText(String.valueOf(changeStrToFl(comboBoxNegative.getValue().toString())));

        subGrid.add(evidenceName, 0, 0, 3, 1);
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

    public void printSubGrid(List<Evidence> evidenceList){
        int columnIndex = 0;
        int rowIndex = 0;
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
    }


    // Change string gia tu to float
    public float changeStrToFl(String str){
        if (str.equalsIgnoreCase("None")) return 0;
        else if (str.equalsIgnoreCase("Very Low")) return 0.125F;
        else if (str.equalsIgnoreCase("Low")) return 0.25F;
        else if (str.equalsIgnoreCase("A little low")) return 0.375F;
        else if (str.equalsIgnoreCase("Medium")) return 0.5F;
        else if (str.equalsIgnoreCase("A little high")) return 0.625F;
        else if (str.equalsIgnoreCase("High")) return 0.75F;
        else if (str.equalsIgnoreCase("Very high")) return 0.875F;
        else if (str.equalsIgnoreCase("Perfect")) return 1F;
        return -1;
    }


    // Handle error
    public void showErrSumProb(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error sum probability");
        alert.setHeaderText("Error sum probability");
        alert.setContentText("Sum probability of a evidence is less or equal 1");
        alert.show();
    }

    public boolean isSumProbValid(List<F1Evidence> f1EvidenceRepositories){
        for (F1Evidence f1Evidence : f1EvidenceRepositories){
            if ((f1Evidence.getPositive()+ f1Evidence.getNeutral()+ f1Evidence.getNegative()) > 1) return false;
        }
        return true;
    }

    // Print predict result
    public void printPredictResult(float result){
        Float percentResult = result * 100;
        if (result < 0.25){
            txtResult.setText(String.format("Very low risk of Covid 19 infection with : %.2f%%.", percentResult));
            txtResult.setStyle("-fx-text-fill: #150e56;");
        } else if (result < 0.5){
            txtResult.setText(String.format("Low risk of Covid 19 infection with : %.2f%%.", percentResult));
            txtResult.setStyle("-fx-text-fill: #335d2d;");
        } else if (result < 0.75){
            txtResult.setText(String.format("High risk of Covid 19 infection with : %.2f%%.", percentResult));
            txtResult.setStyle("-fx-text-fill: #da723c;");
        } else{
            txtResult.setText(String.format("Very high risk of Covid 19 infection with : %.2f%%.", percentResult));
            txtResult.setStyle("-fx-text-fill: #6a097d;");
        }
    }

    // Show EvidenceDisease edit
    public void showEviDisEdit() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("EditEvidenceDisease.fxml"));
        Parent editEviDis = loader.load();
        EditEvidenceDiseaseController editController = loader.getController();
        editController.setData();
        editController.editEviDis();
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        Scene scene = new Scene(editEviDis);
        stage.setScene(scene);
        stage.showAndWait();
    }

    // Show EvidenceDisease edit
    public void showF1Disease() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("ViewF1List.fxml"));
        Parent viewF1List = loader.load();
        ViewF1ListController viewF1ListController = loader.getController();
        viewF1ListController.printTable();
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        Scene scene = new Scene(viewF1List);
        stage.setScene(scene);
        stage.showAndWait();
    }
}
