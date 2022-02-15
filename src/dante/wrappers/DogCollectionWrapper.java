package dante.wrappers;

import dante.model.DogModel;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

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
