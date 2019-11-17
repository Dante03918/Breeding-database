package dante.util;

import dante.model.DogModel;

import java.util.ArrayList;
import java.util.List;

public class VaccDateUtil {

    private List<DogModel> dogCollection;
    public ArrayList listWithVaccDate = new ArrayList();


    public void setDogCollection(List<DogModel> dogCollection){
        this.dogCollection = dogCollection;
    }

    public List listWithDates(){
        for(DogModel dogModel:dogCollection){
            listWithVaccDate.add(dogModel.getVaccinations());
        }
        return listWithVaccDate;
    }
}
