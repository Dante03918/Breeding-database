package dante.model;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "dogs")
@XmlAccessorType(XmlAccessType.FIELD)
public class DogCollectionWrapper {

    @XmlElement(name = "dog")
    private List<DogModel> dogs;


    public List<DogModel> getDogs(){
        return dogs;
    }
    public void setDogs(List<DogModel> dogs){
        this.dogs = dogs;
    }


}
