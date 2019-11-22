package dante;

import dante.util.DateUtil;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import dante.model.DogModel;

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

    DateUtil dateUtil = new DateUtil();

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

        boolean closeCondition = true;

        dogModel.setName(nameField.getText());
        dogModel.setSex(sexField.getText());
        dogModel.setBreed(breedField.getText());
        dogModel.setBirthday(birthdayField.getText());
        if(dateUtil.dateValidation(vaccinationsArea.getText())){
            dogModel.setVaccinations(vaccinationsArea.getText());
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Coś poszło nie tak");
            alert.setHeaderText(null);
            alert.setContentText("Któraś z wpisanych dat w polu 'Szczepienia' ma niepoprawny format. \n" +
                    "Prawidłowy format daty to 'dd.MM.yyyy'");
            alert.showAndWait();

            clickedOk = false;

            closeCondition = false;
        }

        dogModel.setCoat(coatField.getText());
        dogModel.setLitters(littersArea.getText());
        dogModel.setSurgicalProcedures(surgicalArea.getText());
        dogModel.setHeat(heatArea.getText());

        clickedOk = true;
        if(closeCondition) {
            editStage.close();
        }
    }
    @FXML
    public void cancelHandle(){
        editStage.close();
    }

}
