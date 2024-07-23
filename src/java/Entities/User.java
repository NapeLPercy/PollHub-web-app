package Entities;

import java.util.ArrayList;

public class User {

    private int userId;
    private String name;
    private String surname;
    private String email;
    private Account account;
    ArrayList<Poll> polls;
    ArrayList<Vote> votes;

    public User() {
    }

    public User(String name, String surname, String email, ArrayList<Poll> polls, ArrayList<Vote> votes, Account account) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.polls = polls;
        this.votes = votes;
        this.account = account;
    }
    
        public User(String name, String surname, String email,Account account) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.account = account;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

        
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public ArrayList<Poll> getPolls() {
        return polls;
    }

    public void setPolls(ArrayList<Poll> polls) {
        this.polls = polls;
    }

    public ArrayList<Vote> getVotes() {
        return votes;
    }

    public void setVotes(ArrayList<Vote> votes) {
        this.votes = votes;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
    
}
