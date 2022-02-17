package dante;


import enums.Gender;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
    private ListView<LocalDate> rabiesListView;
    @FXML
    private TextArea littersArea;
    @FXML
    private TextArea surgicalArea;
    @FXML
    private ListView<LocalDate> viralVaccListView;
    @FXML
    private DatePicker heatFromDatePicker;
    @FXML
    private DatePicker heatToDatePicker;
    @FXML
    private DatePicker rabiesVaccDatePicker;
    @FXML
    private DatePicker viralVaccDatePicker;
    @FXML
    private ListView<String> heatsAsListView;
    @FXML
    private Button addHeatToListViewButton;
    @FXML
    private ChoiceBox genderChoiceBox;

    private Stage editStage;
    private DogModel dogModel;
    private boolean clickedOk;
    private final List<LocalDate> rabiesVaccDates = new ArrayList<>();
    private final List<LocalDate> viralVaccDates = new ArrayList<>();
    private final List<HeatModel> heatsPeriods = new ArrayList<>();

    List<String> heatsPeriodList = new ArrayList<>();

    ObservableList<String> listViewItems = FXCollections.observableArrayList();

    @FXML
    public void initialize() {

        genderChoiceBox.setItems(FXCollections.observableList(List.of("PIES", "SUKA")));

        addHeatToListViewButton.setOnAction(e -> {

            HeatModel heatModel = new HeatModel(heatFromDatePicker.getValue(), heatToDatePicker.getValue());
            heatsPeriods.add(heatModel);

            List<String> heats = heatsPeriods.stream()
                    .map(c -> c.getHeatStart() + "/-/" + c.getHeatEnd())
                    .collect(Collectors.toList());
            heatsAsListView.setItems(FXCollections.observableList(heats));

        });
    }

    public void setDialogStage(Stage editStage) {
        this.editStage = editStage;
    }

    public void setDogModel(DogModel dogModel) {

        List<String> heatDates = new ArrayList<>();
        if (dogModel.getHeats() == null) {
        } else {
            List<String> heats = heatsPeriods.stream()
                    .map(c -> c.getHeatStart() + "/-/" + c.getHeatEnd())
                    .collect(Collectors.toList());
            heatsAsListView.setItems(FXCollections.observableList(heats));
        }

        this.dogModel = dogModel;

        nameField.setText(dogModel.getName());
        genderChoiceBox.setItems(FXCollections.observableList(List.of(Gender.values())));
        breedField.setText(dogModel.getBreed());
        coatField.setText(dogModel.getCoat());
        birthdayDatepicker.setValue(dogModel.getBirthday());
        littersArea.setText(dogModel.getLitters());
        surgicalArea.setText(dogModel.getSurgicalProcedures());
        heatsPeriodList = heatDates;
        listViewItems.addAll(heatDates);

        for (String s : listViewItems) {
            System.out.println(s);
        }
    }

    public boolean isClickedOk() {
        return clickedOk;
    }

    @FXML
    public void okHandle() {

        dogModel.setName(nameField.getText());
        dogModel.setSex((Enum) genderChoiceBox.getValue());
        dogModel.setBreed(breedField.getText());
        dogModel.setBirthday(birthdayDatepicker.getValue());
        dogModel.setRabiesVaccinations(rabiesVaccDates);
        dogModel.setViralVaccinations(viralVaccDates);
        dogModel.setCoat(coatField.getText());
        dogModel.setLitters(littersArea.getText());
        dogModel.setSurgicalProcedures(surgicalArea.getText());
        dogModel.setHeats(heatsPeriods);

        System.out.println(dogModel.toString());

        clickedOk = true;

        editStage.close();

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
