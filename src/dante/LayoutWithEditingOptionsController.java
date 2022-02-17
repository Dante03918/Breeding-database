package dante;

//import dante.util.DateUtil;

import dante.util.StringUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import dante.model.DogModel;
import model.HeatModel;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class LayoutWithEditingOptionsController {

    @FXML
    private TextField nameField;
    @FXML
    private TextField breedField;
    @FXML
    private TextField coatField;
    @FXML
    private DatePicker birthdayDatepicker;
    @FXML
    private ListView rabiesListView;
    @FXML
    private TextArea littersArea;
    @FXML
    private TextArea surgicalArea;
    @FXML
    private ListView viralVaccListView;
    @FXML
    private DatePicker heatFromDatePicker;
    @FXML
    private DatePicker heatToDatePicker;
    @FXML
    private DatePicker rabiesVaccDatePicker;
    @FXML
    private DatePicker viralVaccDatePicker;
    @FXML
    private ListView heatsAsListView;
    @FXML
    private Button addHeatToListViewButton;
    @FXML
    private ChoiceBox genderChoiceBox;

    private Stage editStage;
    private DogModel dogModel;
    private boolean clickedOk;
    private List<LocalDate> rabiesVaccDates = new ArrayList<>();
    private List<LocalDate> viralVaccDates = new ArrayList<>();
    private List<HeatModel> heatsPeriods = new ArrayList<>();

    StringUtil stringUtil = new StringUtil();

    List<String> heatsPeriodList = new ArrayList<>();

    ObservableList<String> listViewItems = FXCollections.observableArrayList();

    @FXML
    public void initialize() {

        genderChoiceBox.setItems(FXCollections.observableList(List.of("PIES", "SUKA")));

        addHeatToListViewButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                HeatModel heatModel = new HeatModel(heatFromDatePicker.getValue(), heatToDatePicker.getValue());
                heatsPeriods.add(heatModel);
                List<String> heats = heatsPeriods.stream()
                        .map(c -> c.getHeatStart() + "/-/" + c.getHeatEnd())
                        .collect(Collectors.toList());

                heatsAsListView.setItems(FXCollections.observableList(heats));

            }
        });
    }

    public void setDialogStage(Stage editStage) {
        this.editStage = editStage;
    }

    public void setDogModel(DogModel dogModel) {

        List<String> heatDates;
        if (dogModel.getHeats() == null) {
            heatDates = new ArrayList<>();
            System.out.println("Przypisano nowy obiekt array list");
        } else {
            System.out.println("wykonano konwersje");
            heatDates = stringUtil.listFromCuttedString(dogModel.getHeats());
        }

        this.dogModel = dogModel;

        nameField.setText(dogModel.getName());
//        sexField.setText(dogModel.getSex());
        breedField.setText(dogModel.getBreed());
        coatField.setText(dogModel.getCoat());
//        birthdayField.setText(dogModel.getBirthday());
//        rabiesVaccinations.setItems(FXCollections.observableArrayList(dogModel.getRabiesVaccinations()));
        littersArea.setText(dogModel.getLitters());
        surgicalArea.setText(dogModel.getSurgicalProcedures());
//        otherVaccinationArea.setText(dogModel.getOtherVaccinations());
        heatsPeriodList = heatDates;
        listViewItems.addAll(heatDates);
//        heatsAsListView.setItems(listViewItems);

        for (String s : listViewItems) {
            System.out.println(s);
        }
    }

    public boolean isClickedOk() {
        return clickedOk;
    }

    @FXML
    public void okHandle() {

        boolean closeCondition = true;

        dogModel.setName(nameField.getText());
        dogModel.setSex(genderChoiceBox.getValue().toString());
        dogModel.setBreed(breedField.getText());
        dogModel.setBirthday(birthdayDatepicker.getValue());
//        if(dateUtil.dateValidation(rabiesVaccinationsArea.getText())){
            dogModel.setRabiesVaccinations(rabiesVaccDates);
//        } else {
//            Alert alert = new Alert(Alert.AlertType.WARNING);
//            alert.setTitle("Coś poszło nie tak");
//            alert.setHeaderText(null);
//            alert.setContentText("Któraś z wpisanych dat w polu 'Szczepienia' ma niepoprawny format. \n" +
//                    "Prawidłowy format daty to 'dd.MM.yyyy'");
//            alert.showAndWait();
//
//            clickedOk = false;
//
//            closeCondition = false;
//        }
        dogModel.setViralVaccinations(viralVaccDates);
        dogModel.setCoat(coatField.getText());
        dogModel.setLitters(littersArea.getText());
        dogModel.setSurgicalProcedures(surgicalArea.getText());
        dogModel.setHeats(stringUtil.concatListContent(heatsPeriodList));

        clickedOk = true;
        if (closeCondition) {
            editStage.close();
        }
    }

    @FXML
    public void cancelHandle() {
        editStage.close();
    }


    @FXML
    public void addRabiesVaccDateToListButtonHandler() {
        rabiesVaccDates.add(rabiesVaccDatePicker.getValue());
        rabiesListView.setItems(FXCollections.observableList(rabiesVaccDates));
    }

    @FXML
    public void addViralVaccDateToListView() {
        viralVaccDates.add(viralVaccDatePicker.getValue());
        viralVaccListView.setItems(FXCollections.observableList(viralVaccDates));
    }
}
