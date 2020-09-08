package com.example.votingsystem.module;

public class voteModule {
    int index,vote;
    String Candi,City;

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

    public voteModule(int index, int vote, String candi , String city) {
        this.index = index;
        this.vote = vote;
        Candi = candi;
        this.City=city;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getVote() {
        return vote;
    }

    public void setVote(int vote) {
        this.vote = vote;
    }

    public String getCandi() {
        return Candi;
    }

    public void setCandi(String candi) {
        Candi = candi;
    }
}
