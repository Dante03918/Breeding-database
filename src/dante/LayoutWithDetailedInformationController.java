package main;

import javafx.beans.property.ObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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

       // showDogDetails(null);

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
        } else {
            sexLabel.setText("");
            breedLabel.setText("");
            coatLabel.setText("");
            textArea.setText("");
        }

        vaccinationsCheckBox.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {

                littersCheckBox.setSelected(false);
                heatCheckBox.setSelected(false);
                surgicalProceduresCheckBox.setSelected(false);

                textArea.setText(setText(dogModel, "vaccinations"));
            }
        });

        littersCheckBox.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                vaccinationsCheckBox.setSelected(false);
                heatCheckBox.setSelected(false);
                surgicalProceduresCheckBox.setSelected(false);

                textArea.setText(setText(dogModel, "litters"));
            }
        });

        surgicalProceduresCheckBox.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                littersCheckBox.setSelected(false);
                heatCheckBox.setSelected(false);
                vaccinationsCheckBox.setSelected(false);

                textArea.setText(setText(dogModel, "surgical"));
            }
        });

        heatCheckBox.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                littersCheckBox.setSelected(false);
                surgicalProceduresCheckBox.setSelected(false);
                vaccinationsCheckBox.setSelected(false);

                textArea.setText(setText(dogModel, "heat"));

            }
        });
    }


    public String setText(DogModel dogModel, String checkBoxLabel){

         String variableValue = "";

        if(checkBoxLabel.equals("vaccinations")){
          variableValue = dogModel.getVaccinations();
        }
        if(checkBoxLabel.equals("litters")){
            variableValue = dogModel.getLitters();
        }
        if (checkBoxLabel.equals("surgical")){
            variableValue = dogModel.getSurgicalProcedures();
        }
        if(checkBoxLabel.equals("heat")){
            variableValue = dogModel.getHeat();
        }
        return variableValue;
    }

    @FXML
    public void newButtonHandle(){
        DogModel dogModel = new DogModel();
        boolean clickedOk = main.showEditLayout(dogModel);
        if(clickedOk){
            main.getDogModelObservableList().add(dogModel);
        }
    }
    @FXML
    public void editButtonHandle(){
    DogModel model = dogsCollection.getSelectionModel().getSelectedItem();
    if(model != null) {
        boolean clickedOk = main.showEditLayout(model);
        if (clickedOk) {
            showDogDetails(model);
        }
    } else {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Ostrzeżenie");
        alert.setHeaderText("Żaden obiekt z listy nie został wybrany");
        alert.setContentText("Wybierz psa na liście z lewej strony okna");

        alert.showAndWait();
    }
    }
    @FXML
    public void deleteButtonHandle(){
        int selectedItem = dogsCollection.getSelectionModel().getSelectedIndex();
        dogsCollection.getItems().remove(selectedItem);
    }
}
