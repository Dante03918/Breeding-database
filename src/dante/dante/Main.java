package dante;

import controllers.LayoutWithDetailedInformationController;
import controllers.LayoutWithEditingOptionsController;
import controllers.RootLayoutController;
import dante.util.StringUtil;
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
import dante.wrappers.DogCollectionWrapper;
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

   public ObservableList<DogModel> dogModelObservableList = FXCollections.observableArrayList();

    private final Set<String> overdueRabiesVaccinationsSet = new HashSet<>();
    private final Set<String> monthBeforeRabiesVaccinationExpireDateSet = new HashSet<>();
    private final Set<String> overdueOtherVaccinationsSet = new HashSet<>();
    private final Set<String> monthBeforeOtherVaccinationExpireDateSet = new HashSet<>();

    public String contentForAlert = "";
   public String overdueVaccinationsFieldContent = "";

   public Main(){
   }

   public ObservableList<DogModel> getDogModelObservableList(){
       return dogModelObservableList;
   }
    @Override
    public void start(Stage primaryStage){

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
            rootLayout = loader.load();

            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);

            RootLayoutController controller = loader.getController();
            controller.setReference(this);

            primaryStage.show();

        } catch (IOException e){
            e.printStackTrace();
        }
    }
    public void showDetailedInformationLayout(){
        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("layoutWithDetailedInformation.fxml"));
            AnchorPane anchorPane = loader.load();

            rootLayout.setCenter(anchorPane);

            LayoutWithDetailedInformationController controller = loader.getController();
            controller.setMain(this);

//            autoLoadLastOpenedFile();

        } catch (IOException e){
            e.printStackTrace();
        }
    }
    public boolean showEditLayout(DogModel dogModel){
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("layoutWithEditingOptions.fxml"));
            AnchorPane pane = loader.load();


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

//    public void autoLoadLastOpenedFile(){
//        Preferences preferences = Preferences.userNodeForPackage(Main.class);
//        File file = null;
//        try{
//        file = new File(preferences.get("filePath",null));
//        } catch (Exception e){
//            e.printStackTrace();
//        }
//        if(preferences.get("filePath",null) != null){
//            try {
//                loadDataFromFile(file);
//                setFilePathToDogCollectionFile(file);
//
//            } catch (NullPointerException e){
//                Alert alert = new Alert(Alert.AlertType.ERROR);
//                alert.setTitle("Error");
//                alert.setHeaderText(null);
//                alert.setContentText("Nie znaleziono pliku fxml");
//
//                alert.showAndWait();
//            }
//        }
//    }
    public void loadDataFromFile(File file) {
        try {
            JAXBContext context = JAXBContext.newInstance(DogCollectionWrapper.class);

            Unmarshaller unmarshaller = context.createUnmarshaller();

            DogCollectionWrapper wrapper = (DogCollectionWrapper) unmarshaller.unmarshal(file);

            dogModelObservableList.clear();
            dogModelObservableList.addAll(wrapper.getDogs());

            retrieveRabiesVaccinationDates();
            retrieveOtherVaccinationDates();

            StringUtil stringUtil = new StringUtil();

            contentForAlert = stringUtil.alertBuilder(overdueRabiesVaccinationsSet, monthBeforeRabiesVaccinationExpireDateSet, overdueOtherVaccinationsSet,
                    monthBeforeOtherVaccinationExpireDateSet);

            if(!contentForAlert.isEmpty()){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Informacje");
                alert.setHeaderText(null);
                alert.setContentText(contentForAlert);

                alert.showAndWait();
            }

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
   public void retrieveRabiesVaccinationDates(){

//       dateUtil.setMonths(12);

//       for(DogModel model : dogModelObservableList){
//          dateUtil.extractDateFromString(model.getName(), model.getRabiesVaccinations());
          //dateUtil.extractDateFromString(model.getName(), model.getOtherVaccinations());
//       }
//       overdueRabiesVaccinationsSet = dateUtil.overdueVaccinationList;
//       monthBeforeRabiesVaccinationExpireDateSet = dateUtil.monthBeforeVaccinationExpireDateList;
   }
    public void retrieveOtherVaccinationDates(){

//        dateUtil.setMonths(24);


//        for(DogModel model : dogModelObservableList){
            //dateUtil.extractDateFromString(model.getName(), model.getRabiesVaccinations());
//            dateUtil.extractDateFromString(model.getName(), model.getOtherVaccinations());
//        }
//        overdueOtherVaccinationsSet = dateUtil.overdueVaccinationList;
//        monthBeforeOtherVaccinationExpireDateSet = dateUtil.monthBeforeVaccinationExpireDateList;
    }
//    public String buildStringFromList(Set<String> collection){
//
//       //overdueVaccinations = getStringContentForAlert();
//
//        StringBuilder stringBuilder = new StringBuilder();
//        String returnValue = "";
//
//        if(collection.size() != 0) {
//            for (String s : collection) {
//                stringBuilder.append(s);
//                stringBuilder.append("\n");
//            }
//            returnValue = stringBuilder.toString();
//        } else{
//            returnValue = "";
//        }
//        return returnValue;
//    }


    public static void main(String[] args) {
        launch(args);
    }
}
