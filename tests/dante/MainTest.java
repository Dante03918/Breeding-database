package dante;

import dante.model.DogCollectionWrapper;
import dante.model.DogModel;
import dante.util.StringUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.xml.bind.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    List<DogModel> dogModels = new ArrayList<>();

    @BeforeEach
    void setUp() {

        StringUtil stringUtil = new StringUtil();
       List<String> list = new ArrayList<>();


        list.add("12.03.2018");
        list.add("13.03.2018");
        list.add("14.03.2018");
        list.add("15.03.2018");

        DogModel dogModel = new DogModel();
        dogModel.setName("Zosia");
        dogModel.setBirthday("12.02.2012");
        dogModel.setOtherVaccinations("21.11.2019");
        dogModel.setBreed("Chin");
        dogModel.setCoat("Lemon");
        dogModel.setLitters("abc");
        dogModel.setHeats(stringUtil.concatListContent(list));
        dogModel.setRabiesVaccinations("17.01.2018");
        dogModel.setSex("suka");
        dogModel.setSurgicalProcedures("abc");

        dogModels.add(dogModel);
    }

    @Test
    void saveDataToFile() {
        try{
            JAXBContext context =  JAXBContext.newInstance(DogCollectionWrapper.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            DogCollectionWrapper wrapper = new DogCollectionWrapper();
            wrapper.setDogs(dogModels);

            marshaller.marshal(wrapper, new File("C:\\Users\\Dante\\Desktop\\testy.xml"));}
            catch(JAXBException e){
                e.printStackTrace();
            }



}
    @Test
    void loadDataFromFile(){
        File file = new File("C:\\Users\\Dante\\Desktop\\testy.xml");
        List list = new ArrayList();
        try {
            JAXBContext context = JAXBContext.newInstance(DogCollectionWrapper.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();


            DogCollectionWrapper wrapper =(DogCollectionWrapper) unmarshaller.unmarshal(file);

            list = wrapper.getDogs();

            System.out.println("Rozmiar: " + list.size());
        }catch(Exception e){
            e.printStackTrace();
        }
    }

}