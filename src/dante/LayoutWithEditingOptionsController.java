package dante;

import com.sun.istack.internal.Nullable;
import dante.util.DateUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import dante.model.DogModel;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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
    private TextArea rabiesVaccinationsArea;
    @FXML
    private TextArea littersArea;
    @FXML
    private TextArea surgicalArea;
    @FXML
    private TextArea heatArea;
    @FXML
    private TextArea otherVaccinationArea;
    @FXML
    private DatePicker birthdayDatePicker;
    @FXML
    private DatePicker heatFromDatePicker;
    @FXML
    private DatePicker heatToDatePicker;
    @FXML
    private ListView heatsAsListView;
    @FXML
    private Button addHeatToListViewButton;

    private Stage editStage;
    private DogModel dogModel;
    private boolean clickedOk;

    DateUtil dateUtil = new DateUtil();

    List<String> heatsPeriodList = new ArrayList<>();

    ObservableList<String> listViewItems = FXCollections.observableArrayList();
    @FXML
    public  void initialize(){
        addHeatToListViewButton.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle (ActionEvent e){
                String firstPart = dateUtil.localDateToString(heatFromDatePicker.getValue());
                String secondPart = dateUtil.localDateToString(heatToDatePicker.getValue());
                heatsPeriodList.add(firstPart +" - "+secondPart);
                System.out.println(heatsPeriodList.get(0));

                listViewItems.clear();
                listViewItems.addAll(heatsPeriodList);

                heatsAsListView.setItems(listViewItems);
            }
        });
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
        //birthdayDatePicker.setValue(LocalDate.now());
        rabiesVaccinationsArea.setText(dogModel.getRabiesVaccinations());
        littersArea.setText(dogModel.getLitters());
        surgicalArea.setText(dogModel.getSurgicalProcedures());
        otherVaccinationArea.setText(dogModel.getOtherVaccinations());
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
        //dogModel.setBirthday(birthdayField.getText()
        dogModel.setBirthday(dateUtil.localDateToString(birthdayDatePicker.getValue()));
        if(dateUtil.dateValidation(rabiesVaccinationsArea.getText())){
            dogModel.setRabiesVaccinations(rabiesVaccinationsArea.getText());
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
        dogModel.setOtherVaccinations(otherVaccinationArea.getText());
        dogModel.setCoat(coatField.getText());
        dogModel.setLitters(littersArea.getText());
        dogModel.setSurgicalProcedures(surgicalArea.getText());


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
