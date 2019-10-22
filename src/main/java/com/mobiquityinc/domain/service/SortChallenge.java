package com.mobiquityinc.domain.service;

import com.mobiquityinc.domain.Challenge;

import java.util.Comparator;
import java.util.List;

public class SortChallenge {

    public void sortByWeight(List<Challenge> challenges) {
        challenges.sort(Comparator.comparing(Challenge::getWeight));
    }

}
