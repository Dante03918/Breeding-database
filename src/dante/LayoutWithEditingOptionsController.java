package main;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import main.model.DogModel;

public class LayoutWithEditingOptionsController {

    @FXML
    private TextField nameField;
    @FXML
    private TextField sexField;
    @FXML
    private TextField breedField;
    @FXML
    private TextField coatField;
    @FXML
    private TextField birthdayField;
    @FXML
    private TextArea vaccinationsArea;
    @FXML
    private TextArea littersArea;
    @FXML
    private TextArea surgicalArea;
    @FXML
    private TextArea heatArea;

    private Stage editStage;
    private DogModel dogModel;
    private boolean clickedOk;

    @FXML
    public  void initialize(){
    }

    public void setDialogStage(Stage editStage){
        this.editStage = editStage;
    }

    public void setDogModel(DogModel dogModel){
        this.dogModel = dogModel;

        nameField.setText(dogModel.getName());
        sexField.setText(dogModel.getSex());
        breedField.setText(dogModel.getBreed());
        coatField.setText(dogModel.getCoat());
        birthdayField.setText(dogModel.getBirthday());
        vaccinationsArea.setText(dogModel.getVaccinations());
        littersArea.setText(dogModel.getLitters());
        surgicalArea.setText(dogModel.getSurgicalProcedures());
        heatArea.setText(dogModel.getHeat());
    }

    public boolean isClickedOk(){
        return clickedOk;
    }

    @FXML
    public void okHandle(){
        dogModel.setName(nameField.getText());
        dogModel.setSex(sexField.getText());
        dogModel.setBreed(breedField.getText());
        dogModel.setCoat(coatField.getText());
        dogModel.setBirthday(birthdayField.getText());
        dogModel.setVaccinations(vaccinationsArea.getText());
        dogModel.setLitters(littersArea.getText());
        dogModel.setSurgicalProcedures(surgicalArea.getText());
        dogModel.setHeat(heatArea.getText());

        clickedOk = true;
        editStage.close();
    }
    @FXML
    public void cancelHandle(){
        editStage.close();
    }

}
