package dante.model;

import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.List;

public class DogModel {

    private final StringProperty name;
    private final StringProperty sex;
    private final StringProperty breed;
    private final StringProperty coat;
    private final StringProperty rabiesVaccinations;
    private final StringProperty otherVaccinations;
    private final StringProperty litters;
    private final StringProperty surgicalProcedures;
    private final ListProperty heat;
    private final StringProperty birthday;

    public DogModel(){ this(null,null);}

    public DogModel(String name, String sex){

        this.name = new SimpleStringProperty(name);
        this.sex = new SimpleStringProperty(sex);

        this.breed = new SimpleStringProperty(null);
        this.coat = new SimpleStringProperty(null);
        this.rabiesVaccinations = new SimpleStringProperty(null);
        this.litters = new SimpleStringProperty(null);
        this.surgicalProcedures = new SimpleStringProperty(null);
        this.heat = new SimpleListProperty(null);
        this.birthday = new SimpleStringProperty(null);
        this.otherVaccinations = new SimpleStringProperty(null);
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

    public String getRabiesVaccinations() {
        return rabiesVaccinations.get();
    }

    public StringProperty rabiesVaccinationsProperty() {
        return rabiesVaccinations;
    }

    public void setRabiesVaccinations(String rabiesVaccinations) {
        this.rabiesVaccinations.set(rabiesVaccinations);
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

    public Object getHeat() { return heat.get(); }

    public ListProperty heatProperty() { return heat; }

   // public void setHeat(Object heat) { this.heat.set(heat); }

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
