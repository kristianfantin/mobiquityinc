package com.mobiquityinc.domain.service;

import com.mobiquityinc.domain.Challenge;
import com.mobiquityinc.domain.PackageChallenge;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import static com.mobiquityinc.messages.ApiMessages.PACKAGE_MAX_WEIGHT_EXCEEDED;

public class Solution {

    private static final int LIMIT_PACKAGE_WEIGHT = 100;
    private static final int LIMIT_ITEM_COST = 100;
    private final PackageChallenge packageChallenge;

    public Solution(PackageChallenge packageChallenge) {
        this.packageChallenge = packageChallenge;
    }

    public String execute() {
        List<List<Challenge>> workList = new LinkedList<>();

        double maxWeight = packageChallenge.getMaxWeight();
        if (maxWeight > LIMIT_PACKAGE_WEIGHT)
            return PACKAGE_MAX_WEIGHT_EXCEEDED.getMessage();

        packageChallenge.getChallenges()
                .stream()
                .filter(currentChallenge -> isItemValid(maxWeight, currentChallenge))
                .forEach(currentChallenge -> addChallenge(workList, maxWeight, currentChallenge));

        List<Challenge> results = getChallengesResults(workList);
        return results.isEmpty()?  "-" : getResult(results);
    }

    private boolean isItemValid(double maxWeight, Challenge currentChallenge) {
        return currentChallenge.getWeight() <= maxWeight && currentChallenge.getCurrency() <= LIMIT_ITEM_COST;
    }

    private void addChallenge(List<List<Challenge>> workList, double maxWeight, Challenge currentChallenge) {
        new WorkChallenge(workList, maxWeight, currentChallenge).execute();
    }

    private List<Challenge> getChallengesResults(List<List<Challenge>> workList) {
        double maxSum = 0;
        List<Challenge> results = new ArrayList<>();
        for (List<Challenge> challenges : workList) {
            double sum = challenges.stream().mapToDouble(Challenge::getCurrency).sum();
            if (sum > maxSum) {
                maxSum = sum;
                results = challenges;
            }
        }
        return results;
    }

    private String getResult(List<Challenge> results) {
        return String.join(", ", getCollect(results));
    }

    private List<String> getCollect(List<Challenge> results) {
        List<Integer> collect = results.stream().map(Challenge::getIndex).collect(Collectors.toList());
        new SortChallenge().sort(collect);
        return collect.stream().map(Object::toString).collect(Collectors.toList());
    }

}
