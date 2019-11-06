package main;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import main.model.DogModel;

import java.io.IOException;

public class Main extends Application {

   private Stage primaryStage;
   private BorderPane rootLayout;

   private ObservableList<DogModel> dogModelObservableList = FXCollections.observableArrayList();

   public Main(){
       dogModelObservableList.add(new DogModel("Amelka", "suka"));
       dogModelObservableList.add(new DogModel("Ignacy", "pies"));
       dogModelObservableList.add(new DogModel("Alicja", "suka"));
       dogModelObservableList.add(new DogModel("Zosia", "suka"));
       dogModelObservableList.add(new DogModel("Helenka", "suka"));
       dogModelObservableList.add(new DogModel("Lena", "suka"));
       dogModelObservableList.add(new DogModel("Czesio", "pies"));
       dogModelObservableList.add(new DogModel("Gniewko", "pies"));
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


    public static void main(String[] args) {
        launch(args);
    }
}
