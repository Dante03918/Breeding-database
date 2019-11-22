package dante;

import dante.util.DateUtil;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import dante.model.DogCollectionWrapper;
import dante.model.DogModel;

import javax.xml.bind.*;
import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.prefs.Preferences;

public class Main extends Application {

   private Stage primaryStage;
   private BorderPane rootLayout;

   public boolean saveFlag = false;

   private ObservableList<DogModel> dogModelObservableList = FXCollections.observableArrayList();

   private Set<String> stringContentForAlert = new HashSet<>();

   public String vaccinationsFieldContent = "";

   public Main(){
   }

   public ObservableList<DogModel> getDogModelObservableList(){
       return dogModelObservableList;
   }
    @Override
    public void start(Stage primaryStage) throws Exception{

        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Informacje hodowlane");

        initRootLayout();
        showDetailedInformationLayout();
    }
    public Stage getPrimaryStage(){
       return primaryStage;
    }
    public void initRootLayout(){
        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("rootLayout.fxml"));
            rootLayout = (BorderPane) loader.load();

            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);

            RootLayoutController controller = loader.getController();
            controller.setRefference(this);

            primaryStage.show();

        } catch (IOException e){
            e.printStackTrace();
        }
    }
    public void showDetailedInformationLayout(){
        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("layoutWithDetailedInformation.fxml"));
            AnchorPane anchorPane = (AnchorPane) loader.load();

            rootLayout.setCenter(anchorPane);

            LayoutWithDetailedInformationController controller = loader.getController();
            controller.setMain(this);

            autoLoadLastOpenedFile();

        } catch (IOException e){
            e.printStackTrace();
        }
    }
    public boolean showEditLayout(DogModel dogModel){
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("layoutWithEditingOptions.fxml"));
            AnchorPane pane = (AnchorPane) loader.load();


            Stage editStage = new Stage();
            editStage.setTitle("Edycja");
            editStage.initModality(Modality.WINDOW_MODAL);
            editStage.initOwner(primaryStage);
                Scene scene = new Scene(pane);
                editStage.setScene(scene);

            LayoutWithEditingOptionsController controller = loader.getController();
            controller.setDialogStage(editStage);
            controller.setDogModel(dogModel);

            editStage.showAndWait();
            return controller.isClickedOk();
        } catch (IOException e){
            e.printStackTrace();
            return false;
        }
    }

    public void autoLoadLastOpenedFile(){
        Preferences preferences = Preferences.userNodeForPackage(Main.class);
        File file = null;
        try{
        file = new File(preferences.get("filePath",null));
        } catch (Exception e){
            e.printStackTrace();
        }
        if(preferences.get("filePath",null) != null){
            try {
                loadDataFromFile(file);
                setFilePathToDogCollectionFile(file);
                if(!vaccinationsFieldContent.isEmpty()){
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Psy do szczepienia");
                    alert.setHeaderText(null);
                    alert.setContentText(vaccinationsFieldContent);
                    alert.showAndWait();
                }
            } catch (NullPointerException e){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Nie znaleziono pliku fxml");

                alert.showAndWait();
            }
        }
    }
    public void loadDataFromFile(File file) {
        try {
            JAXBContext context = JAXBContext.newInstance(DogCollectionWrapper.class);

            Unmarshaller unmarshaller = context.createUnmarshaller();

            DogCollectionWrapper wrapper = (DogCollectionWrapper) unmarshaller.unmarshal(file);

            dogModelObservableList.clear();
            dogModelObservableList.addAll(wrapper.getDogs());

            sendDogModelsFromObservableListToOtheClass();

            vaccinationsFieldContent = buildStringFromList();

        }catch (JAXBException e){
            e.printStackTrace();
        }
    }
    public void saveDataToFile(File file){
       try{
           JAXBContext context = JAXBContext.newInstance(DogCollectionWrapper.class);
           Marshaller marshaller = context.createMarshaller();
           marshaller.setProperty(marshaller.JAXB_FORMATTED_OUTPUT, true);

           DogCollectionWrapper wrapper = new DogCollectionWrapper();
           wrapper.setDogs(dogModelObservableList);

           marshaller.marshal(wrapper, file);
       } catch(JAXBException e){
           e.printStackTrace();
       }
    }

    public File getFilePathToDogCollectionFile(){
        Preferences preferences = Preferences.userNodeForPackage(Main.class);
        String filePath = preferences.get("filePath", null);

        if(filePath != null){
            return new File(filePath);
        } else {
            return null;
        }
   }

   public void setFilePathToDogCollectionFile(File file){
       Preferences preferences = Preferences.userNodeForPackage(Main.class);

       if(file != null){
           preferences.put("filePath", file.getPath());
       }
   }
   public void sendDogModelsFromObservableListToOtheClass(){

       DateUtil dateUtil = new DateUtil();

       for(DogModel model : dogModelObservableList){
          dateUtil.extractDateFromString(model.getName(), model.getVaccinations());
       }
       stringContentForAlert = dateUtil.stringContentForAlert;
   }
    public String buildStringFromList(){

       //stringContentForAlert = getStringContentForAlert();

        StringBuilder stringBuilder = new StringBuilder();
        String returnValue = "";

        if(stringContentForAlert.size() != 0) {
            for (String s : stringContentForAlert) {
                stringBuilder.append(s);
                stringBuilder.append("\n");
            }
            returnValue = stringBuilder.toString();
        } else{
            returnValue = "";
        }
        return returnValue;
    }


    public static void main(String[] args) {
        launch(args);
    }
}
