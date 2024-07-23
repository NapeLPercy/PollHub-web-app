package Entities;
public class Option {
    
    private int id;
    private String optionText;
    private int voteCount;

    public Option(){}
    
    public Option(String optionText, int voteCount) {
        this.optionText = optionText;
        this.voteCount = voteCount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    

    public String getOptionText() {
        return optionText;
    }

    public void setOptionText(String optionText) {
        this.optionText = optionText;
    }

    public int getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(int voteCount) {
        this.voteCount = voteCount;
    }
    
    
}
