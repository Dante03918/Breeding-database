package dante.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class DogModel {

    private  StringProperty name;
    private  StringProperty sex;
    private  StringProperty breed;
    private  StringProperty coat;
    private  List<LocalDate> rabiesVaccinations;
    private  List<LocalDate> viralVaccinations;
    private  StringProperty litters;
    private  StringProperty surgicalProcedures;
    private  StringProperty heats;
    private  LocalDate birthday;

    public DogModel(){

        this.name = new SimpleStringProperty(null);
        this.sex = new SimpleStringProperty(null);

        this.breed = new SimpleStringProperty(null);
        this.coat = new SimpleStringProperty(null);
        this.rabiesVaccinations = new ArrayList<>();
        this.litters = new SimpleStringProperty(null);
        this.surgicalProcedures = new SimpleStringProperty(null);
        this.heats = new SimpleStringProperty(null);
        this.viralVaccinations = new ArrayList<>();
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


    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public List<LocalDate> getViralVaccinations() {
        return viralVaccinations;
    }

    public void setViralVaccinations(List<LocalDate> viralVaccinations) {
        this.viralVaccinations = viralVaccinations;
    }
}
