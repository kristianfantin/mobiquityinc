package com.mobiquityinc.template;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
class PackageChallengeTemplate {

    private double maxWeight;
    private List<ChallengeTemplate> challenges;

    PackageChallengeTemplate(double maxWeight) {
        this.maxWeight = maxWeight;
        if (challenges == null)
            challenges = new ArrayList<>();
    }

    List<ChallengeTemplate> getChallenges() {
        if (challenges == null)
            challenges = new ArrayList<>();

        return challenges;
    }

    void addChallengeTemplate(ChallengeTemplate challengeTemplate) {
        getChallenges().add(challengeTemplate);
    }
}
