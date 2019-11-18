package dante;

import dante.util.DateUtil;
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
//    private Label nameLabel;
    @FXML
    private Label sexLabel;
    @FXML
    private Label breedLabel;
    @FXML
    private Label coatLabel;
    @FXML
    private RadioButton vaccinationsButton;
    @FXML
    private RadioButton littersButton;
    @FXML
    private RadioButton surgicalProceduresButton;
    @FXML
    private RadioButton heatButton;
    @FXML
    private Label birthdayLabel;
    @FXML
    private TextArea textArea;

    private Main main;

    public LayoutWithDetailedInformationController() {
    }

    @FXML
    private void initialize() {
        nameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());

        dogsCollection.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> showDogDetails(newValue));
    }

    public void setMain(Main main) {
        this.main = main;

        dogsCollection.setItems(main.getDogModelObservableList());
    }

    public void showDogDetails(DogModel dogModel) {
        if (dogModel != null) {
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

        DogModel selectedItem = dogsCollection.getSelectionModel().getSelectedItem();

        if (vaccinationsButton.isSelected()) {

            if (!selectedItem.getVaccinations().isEmpty()) {

                textArea.setText(selectedItem.getVaccinations());

            } else {

                textArea.setText("Lista szczepień jest pusta.");
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning!");
                alert.setHeaderText(null);
                alert.setContentText("Nie wybrano psa z listy po lewej, lub lista szczepień jest pusta");
                alert.show();
            }
        } else if (heatButton.isSelected()) {

            if (selectedItem.getSex().equals("pies")) {

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Wskazówka");
                alert.setHeaderText(null);
                alert.setContentText("Pies jest tak skonstruowany, że nie posiada cieczki ;) \n Wybierz innego psa, lub inne informacje");
                alert.showAndWait();

            } else {

                textArea.setText(selectedItem.getHeat());
            }
        } else if(littersButton.isSelected()){

            if(selectedItem.getLitters().isEmpty()){
                textArea.setText("Nie wprowadzono żadnych miotów");

            } else{
                textArea.setText(selectedItem.getLitters());
            }
        } else if(surgicalProceduresButton.isSelected()){

            if(selectedItem.getSurgicalProcedures().isEmpty()){

                textArea.setText("Lista zabiegów jest pusta.");

            } else{

                textArea.setText(selectedItem.getSurgicalProcedures());
            }
        }
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
        DogModel selectedItem = dogsCollection.getSelectionModel().getSelectedItem();
    if(selectedItem != null) {
        boolean clickedOk = main.showEditLayout(selectedItem);
        if (clickedOk) {
            showDogDetails(selectedItem);
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
