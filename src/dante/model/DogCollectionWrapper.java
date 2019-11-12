package dante.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "dogs")
public class DogCollectionWrapper {

    private List<DogModel> dogs;

@XmlElement(name = "dog")
    public List<DogModel> getDogs(){
        return dogs;
    }
    public void setDogs(List<DogModel> dogs){
    this.dogs = dogs;
    }
}
