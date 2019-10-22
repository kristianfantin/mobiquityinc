package com.mobiquityinc.domain;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class PackageChallenge {

    private double maxWeight;
    private List<Challenge> challenges = new ArrayList<>();

    public void setChallenges(Challenge challenge) {
        getChallenges().add(challenge);
    }
}
