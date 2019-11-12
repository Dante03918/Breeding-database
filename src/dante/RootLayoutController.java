package main;

import javafx.fxml.FXML;
import javafx.stage.FileChooser;

import java.io.File;

public class RootLayoutController {

   private Main main ;

    public void setMain(Main main){
        this.main = main;
    }

//    @FXML
//    public void handleNew(){
//        main.getDogModelObservableList().clear();
//    }

    @FXML
    public void handleOpen(){
        FileChooser fileChooser = new FileChooser();

        FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter("XML files (*.xml", "*.xml");
        fileChooser.getExtensionFilters().add(filter);

        File file = fileChooser.showOpenDialog(main.getPrimaryStage());

        if(file != null){
            main.loadDataFromFile(file);
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
}
