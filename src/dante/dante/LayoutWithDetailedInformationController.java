package dante;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import dante.model.DogModel;

public class LayoutWithDetailedInformationController {

    @FXML
    private TableView<DogModel> dogsCollection;
    @FXML
    private TableColumn<DogModel, String> nameColumn;

//    @FXML
//    private ChoiceBox genderChoiceBox;
    @FXML
    private Label breedLabel;
    @FXML
    private Label coatLabel;
    @FXML
    private RadioButton rabiesVaccinationsButton;
    @FXML
    private RadioButton littersButton;
    @FXML
    private RadioButton surgicalProceduresButton;
    @FXML
    private RadioButton heatButton;
    @FXML
    private RadioButton otherVaccinations;
    @FXML
    private Label birthdayLabel;
    @FXML
    private TextArea textArea;


    private String vaccinationsString;
    private String littersString;
    private String surgicalProceduresString;
    private String heatString;
    private String sex;
    private String otherVaccinationsString;

    private DogModel modelRefference;
    private dante.Main main;
    private DogModel selectedItem;


    @FXML
    private void initialize() {
        nameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());

        dogsCollection.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<DogModel>() {
            @Override
            public void changed(ObservableValue<? extends DogModel> observable, DogModel oldValue, DogModel newValue) {
                if(newValue != null){
                    showDogDetails(newValue);
                }
            }
        });
        rabiesVaccinationsButton.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                selectedItem = dogsCollection.getSelectionModel().getSelectedItem();
                if(selectedItemValidate(selectedItem)) {

                    if (newValue) {

                    textArea.setText(vaccinationsString);
                }}
            }
        });
        heatButton.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                selectedItem = dogsCollection.getSelectionModel().getSelectedItem();
                if(selectedItemValidate(selectedItem)) {

                    if (newValue) {
                    if(sex != null && sex.equals("pies")){
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Wskazówka");
                        alert.setHeaderText(null);
                        alert.setContentText("Pies jest tak skonstruowany, że nie posiada cieczki ;)");
                        alert.showAndWait();
                    }else {
                       textArea.setText(modelRefference.getHeats());
                    }
                }}
            }
        });
        littersButton.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                selectedItem = dogsCollection.getSelectionModel().getSelectedItem();
                if(selectedItemValidate(selectedItem)) {

                    if (newValue) {
                        textArea.setText(littersString);
                        System.out.println("Mioty");
                }
            }}
        });
        surgicalProceduresButton.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                selectedItem = dogsCollection.getSelectionModel().getSelectedItem();
                if(selectedItemValidate(selectedItem)) {

                    if (newValue) {
                    textArea.setText(surgicalProceduresString);
                    System.out.println("Zabiegi");                }
            }}
        });
        otherVaccinations.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                selectedItem = dogsCollection.getSelectionModel().getSelectedItem();
                if(selectedItemValidate(selectedItem)) {

                    textArea.setText(otherVaccinationsString);
            }}
        });
    }
    public void setMain(dante.Main main) {
        this.main = main;

        dogsCollection.setItems(main.getDogModelObservableList());
    }

    public void showDogDetails(DogModel dogModel) {
        if (dogModel != null) {

            rabiesVaccinationsButton.setSelected(false);
            littersButton.setSelected(false);
            surgicalProceduresButton.setSelected(false);
            heatButton.setSelected(false);

            textArea.setText("");

//            sexLabel.setText(dogModel.getSex());
            breedLabel.setText(dogModel.getBreed());
            coatLabel.setText(dogModel.getCoat());
//            birthdayLabel.setText(dogModel.getBirthday());

//            vaccinationsString = dogModel.getRabiesVaccinations();
            littersString = dogModel.getLitters();
            surgicalProceduresString = dogModel.getSurgicalProcedures();
            sex = dogModel.getSex().toString();
//            otherVaccinationsString = dogModel.getViralVaccinations();

            modelRefference = dogModel;
        } else {
//            sexLabel.setText("");
            breedLabel.setText("");
            coatLabel.setText("");
            textArea.setText("");
        }

    }

    @FXML
    public void newButtonHandle(){
        DogModel dogModel = new DogModel();
        boolean clickedOk = main.showEditLayout(dogModel);

//        genderChoiceBox.setItems(FXCollections.observableList(List.of("PIES", "SUKA")));
        if(clickedOk){
            main.getDogModelObservableList().add(dogModel);
        }
    }
    @FXML
    public void editButtonHandle(){
         selectedItem = dogsCollection.getSelectionModel().getSelectedItem();
    if(selectedItemValidate(selectedItem)) {
        boolean clickedOk = main.showEditLayout(selectedItem);
        if (clickedOk) {
            showDogDetails(selectedItem);
        }
    }
    }
    @FXML
    public void deleteButtonHandle(){
        int selectedItem = dogsCollection.getSelectionModel().getSelectedIndex();
        dogsCollection.getItems().remove(selectedItem);
    }
    public boolean selectedItemValidate(DogModel selectedItem){
        boolean condition;
        if(selectedItem != null){
            condition = true;
        } else {
            condition = false;
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Ostrzeżenie");
            alert.setHeaderText("Żaden obiekt z listy nie został wybrany");
            alert.setContentText("Wybierz psa na liście z lewej strony okna");

            alert.showAndWait();
        }
        return condition;
    }
}
