package com.mobiquityinc.template;

import com.mobiquityinc.domain.Challenge;

public class ChallengeTemplate {

    private Challenge challenge;

    ChallengeTemplate challenge (int index, double weight, double currency) {
        challenge = new Challenge(index, weight, currency);
        return this;
    }

    Challenge getChallenge() {
        return challenge;
    }
}
