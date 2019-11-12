package dante;

import dante.util.VaccDateUtil;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import dante.model.DogCollectionWrapper;
import dante.model.DogModel;

import javax.xml.bind.*;
import java.io.File;
import java.io.IOException;
import java.util.prefs.Preferences;

public class Main extends Application {

   private Stage primaryStage;
   private BorderPane rootLayout;

   private ObservableList<DogModel> dogModelObservableList = FXCollections.observableArrayList();

   public Main(){
//       dogModelObservableList.add(new DogModel("Amelka", "suka"));
//       dogModelObservableList.add(new DogModel("Ignacy", "pies"));
//       dogModelObservableList.add(new DogModel("Alicja", "suka"));
//       dogModelObservableList.add(new DogModel("Zosia", "suka"));
//       dogModelObservableList.add(new DogModel("Helenka", "suka"));
//       dogModelObservableList.add(new DogModel("Lena", "suka"));
//       dogModelObservableList.add(new DogModel("Czesio", "pies"));
//       dogModelObservableList.add(new DogModel("Gniewko", "pies"));
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

        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public Stage getPrimaryStage() {
        return primaryStage;
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

    public void loadDataFromFile(File file) {
        try {
            JAXBContext context = JAXBContext.newInstance(DogCollectionWrapper.class);

            Unmarshaller unmarshaller = context.createUnmarshaller();

            DogCollectionWrapper wrapper = (DogCollectionWrapper) unmarshaller.unmarshal(file);

            dogModelObservableList.clear();
            dogModelObservableList.addAll(wrapper.getDogs());

            VaccDateUtil vaccDateUtilList = new VaccDateUtil();
            vaccDateUtilList.setDogCollection(dogModelObservableList);

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


    public static void main(String[] args) {
        launch(args);
    }
}
