package dante.model;

import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javax.xml.bind.annotation.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class DogModel {

    private  StringProperty name;
    private  StringProperty sex;
    private  StringProperty breed;
    private  StringProperty coat;
    private  List<LocalDate> rabiesVaccinations;
    private  StringProperty otherVaccinations;
    private  StringProperty litters;
    private  StringProperty surgicalProcedures;
    private  StringProperty heats;
    private  StringProperty birthday;

    public DogModel(){

        this.name = new SimpleStringProperty(null);
        this.sex = new SimpleStringProperty(null);

        this.breed = new SimpleStringProperty(null);
        this.coat = new SimpleStringProperty(null);
        this.rabiesVaccinations = new ArrayList<>();
        this.litters = new SimpleStringProperty(null);
        this.surgicalProcedures = new SimpleStringProperty(null);
        this.heats = new SimpleStringProperty(null);
        this.birthday = new SimpleStringProperty(null);
        this.otherVaccinations = new SimpleStringProperty(null);
    }

    public String getHeats() {
        return heats.get();
    }

    public StringProperty heatsProperty() {
        return heats;
    }

    public void setHeats(String heats) {
        this.heats.set(heats);
    }

    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getSex() {
        return sex.get();
    }

    public StringProperty sexProperty() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex.set(sex);
    }

    public String getBreed() {
        return breed.get();
    }

    public StringProperty breedProperty() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed.set(breed);
    }

    public String getCoat() {
        return coat.get();
    }

    public StringProperty coatProperty() {
        return coat;
    }

    public void setCoat(String coat) {
        this.coat.set(coat);
    }

    public List<LocalDate> getRabiesVaccinations() {
        return rabiesVaccinations;
    }

    public void setRabiesVaccinations(List<LocalDate> rabiesVaccinations) {
        this.rabiesVaccinations = rabiesVaccinations;
    }

    public String getLitters() {
        return litters.get();
    }

    public StringProperty littersProperty() {
        return litters;
    }

    public void setLitters(String litters) {
        this.litters.set(litters);
    }

    public String getSurgicalProcedures() {
        return surgicalProcedures.get();
    }

    public StringProperty surgicalProceduresProperty() {
        return surgicalProcedures;
    }

    public void setSurgicalProcedures(String surgicalProcedures) {
        this.surgicalProcedures.set(surgicalProcedures);
    }




    public void setBirthday(String birthday) {
        this.birthday.set(birthday);
    }

    public StringProperty getBirthdayProperty(){
        return birthday;
    }

    public String getBirthday(){
        return birthday.get();
    }

    public String getOtherVaccinations() {
        return otherVaccinations.get();
    }

    public StringProperty otherVaccinationsProperty() {
        return otherVaccinations;
    }

    public void setOtherVaccinations(String otherVaccinations) {
        this.otherVaccinations.set(otherVaccinations);
    }
}
