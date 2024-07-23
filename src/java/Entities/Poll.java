package Entities;

import java.util.ArrayList;
import java.util.Date;

public class Poll {

    private int pollId;
    private String user_id;
    private String question;
    private String status;
    private String topic;
    private Date date;
    private ArrayList<Vote> pollVotes;
    private ArrayList<Option> pollOptions;

    public Poll() {
    }

    public Poll(String question, String status, String topic, Date date, ArrayList<Vote> pollVotes, ArrayList<Option> pollOptions) {
        this.question = question;
        this.status = status;
        this.topic = topic;
        this.date = date;
        this.pollVotes = pollVotes;
        this.pollOptions = pollOptions;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public int getPollId() {
        return pollId;
    }

    public void setPollId(int pollId) {
        this.pollId = pollId;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public ArrayList<Vote> getPollVotes() {
        return pollVotes;
    }

    public void setPollVotes(ArrayList<Vote> pollVotes) {
        this.pollVotes = pollVotes;
    }

    public ArrayList<Option> getPollOptions() {
        return pollOptions;
    }

    public void setPollOptions(ArrayList<Option> pollOptions) {
        this.pollOptions = pollOptions;
    }

}
