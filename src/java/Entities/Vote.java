package Entities;

public class Vote {
    
    private String chosenOption;

    public Vote(){}
    
    public Vote(String chosenOption) {
        this.chosenOption = chosenOption;
    }

    public String getChosenOption() {
        return chosenOption;
    }

    public void setChosenOption(String chosenOption) {
        this.chosenOption = chosenOption;
    }
    
}
