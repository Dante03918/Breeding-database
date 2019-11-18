package dante;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.stage.FileChooser;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class RootLayoutController {

   private Main main;

    public void setRefference(Main main){
        this.main = main;
    }

//    @FXML
//    public void handleNew(){
//        dante.getDogModelObservableList().clear();
//    }

    @FXML
    public void handleOpen(){
        FileChooser fileChooser = new FileChooser();


        FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter("XML files (*.xml", "*.xml");
        fileChooser.getExtensionFilters().add(filter);

        File file = fileChooser.showOpenDialog(main.getPrimaryStage());

        if(file != null){
            main.loadDataFromFile(file);
            main.setFilePathToDogCollectionFile(file);
        }
        String contentForAlert = main.charsChain;

        if(!contentForAlert.isEmpty()){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Psy do szczepienia");
            alert.setHeaderText(null);
            alert.setContentText(contentForAlert);
            alert.showAndWait();
            }



    }

    @FXML
    public void handleSaveAs(){


        FileChooser fileChooser = new FileChooser();

        FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter("XML files *.xml", "*.xml");
        fileChooser.getExtensionFilters().add(filter);

        File file = fileChooser.showSaveDialog(main.getPrimaryStage());

        if(file != null){
            main.saveDataToFile(file);
        }
    }

    @FXML
    public void handleSave(){
        File dogList = main.getFilePathToDogCollectionFile();
        if(dogList != null){
            main.saveDataToFile(dogList);
        } else {
            handleSaveAs();
        }
    }
    @FXML
    public void handleExit(){
        System.exit(0);
    }
}
