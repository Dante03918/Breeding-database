package dante.wrappers;

import dante.model.DogModel;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
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
