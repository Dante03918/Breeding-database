package dante;

import dante.util.VaccDateUtil;
import javafx.fxml.FXML;
import javafx.stage.FileChooser;

import java.io.File;
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
}
