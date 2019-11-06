package main;

import javafx.beans.property.ObjectProperty;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import main.model.DogModel;

import java.time.LocalDate;

public class LayoutWithDetailedInformationController {

    @FXML
    private TableView<DogModel> dogsCollection;
    @FXML
    private TableColumn<DogModel, String> nameColumn;

//    @FXML
//    private Label nameLabel;
    @FXML
    private Label  sexLabel;
    @FXML
    private Label  breedLabel;
    @FXML
    private Label  coatLabel;
    @FXML
    private CheckBox vaccinationsCheckBox;
    @FXML
    private CheckBox  littersCheckBox;
    @FXML
    private CheckBox  surgicalProceduresCheckBox;
    @FXML
    private CheckBox  heatCheckBox;
    @FXML
    private Label birthdayLabel;
    @FXML
    private TextArea textArea;

    private Main main;

    public LayoutWithDetailedInformationController(){}

    @FXML
    private void initialize(){
        nameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());

        showDogDetails(null);

        dogsCollection.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> showDogDetails(newValue));
    }

    public void setMain(Main main){
        this.main = main;


        dogsCollection.setItems(main.getDogModelObservableList());
    }

    public void showDogDetails(DogModel dogModel){
        if(dogModel != null){
            sexLabel.setText(dogModel.getSex());
            breedLabel.setText(dogModel.getBreed());
            coatLabel.setText(dogModel.getCoat());
            birthdayLabel.setText(dogModel.getBirthday());
            textArea.setText(setText(dogModel));
        } else {
            sexLabel.setText("");
            breedLabel.setText("");
            coatLabel.setText("");
            textArea.setText("");
        }
    }

    public String setText(DogModel dogModel){

         String variableValue = "";

        if(vaccinationsCheckBox.isSelected()){
            variableValue = dogModel.getVaccinations();
            System.out.println("Zaznaczono szczepienia");
        } else {

        }
        return variableValue;
    }
}
