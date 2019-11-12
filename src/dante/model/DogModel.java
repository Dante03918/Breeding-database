package dante.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class DogModel {

    private final StringProperty name;
    private final StringProperty sex;
    private final StringProperty breed;
    private final StringProperty coat;
    private final StringProperty vaccinations;
    private final StringProperty litters;
    private final StringProperty surgicalProcedures;
    private final StringProperty heat;
    private final StringProperty birthday;

    public DogModel(){ this(null,null);}

    public DogModel(String name, String sex){

        this.name = new SimpleStringProperty(name);
        this.sex = new SimpleStringProperty(sex);

        this.breed = new SimpleStringProperty(null);
        this.coat = new SimpleStringProperty(null);
        this.vaccinations = new SimpleStringProperty(null);
        this.litters = new SimpleStringProperty(null);
        this.surgicalProcedures = new SimpleStringProperty(null);
        this.heat = new SimpleStringProperty(null);
        this.birthday = new SimpleStringProperty(null);
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

    public String getVaccinations() {
        return vaccinations.get();
    }

    public StringProperty vaccinationsProperty() {
        return vaccinations;
    }

    public void setVaccinations(String vaccinations) {
        this.vaccinations.set(vaccinations);
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

    public String getHeat() {
        return heat.get();
    }

    public StringProperty heatProperty() {
        return heat;
    }

    public void setHeat(String heat) {
        this.heat.set(heat);
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
}
