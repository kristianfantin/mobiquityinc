package com.mobiquityinc.domain.service;

import com.mobiquityinc.domain.Challenge;
import com.mobiquityinc.domain.PackageChallenge;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static com.mobiquityinc.messages.ApiMessages.PACKAGE_MAX_WEIGHT_EXCEEDED;
import static com.mobiquityinc.messages.ApiMessages.THERE_MIGHT_BE_UP_15_ITEM_TO_CHOOSE;

public class Solution {

    private static final int LIMIT_PACKAGE_WEIGHT = 100;
    private static final int LIMIT_ITEM_COST = 100;
    private static final int SIZE_LIMIT_ITEMS = 15;
    private final PackageChallenge packageChallenge;

    public Solution(PackageChallenge packageChallenge) {
        this.packageChallenge = packageChallenge;
    }

    public String execute() {
        double maxWeight = packageChallenge.getMaxWeight();
        String validateAdditionalConstraints = validateAdditionalConstraints(maxWeight);
        return validateAdditionalConstraints.isEmpty() ? getExecuteResult(maxWeight) : validateAdditionalConstraints;
    }

    private String getExecuteResult(double maxWeight) {
        List<List<Challenge>> workList = new LinkedList<>();
        packageChallenge.getChallenges()
                .stream()
                .filter(currentChallenge -> isItemValid(maxWeight, currentChallenge))
                .forEach(currentChallenge -> addChallenge(workList, maxWeight, currentChallenge));

        List<Challenge> results = getChallengesResults(workList);
        return results.isEmpty()?  "-" : getResult(results);
    }

    private String validateAdditionalConstraints(double maxWeight) {
        if (maxWeight > LIMIT_PACKAGE_WEIGHT)
            return PACKAGE_MAX_WEIGHT_EXCEEDED.getMessage();

        if (packageChallenge.getChallenges().size() > SIZE_LIMIT_ITEMS)
            return THERE_MIGHT_BE_UP_15_ITEM_TO_CHOOSE.getMessage();

        return "";
    }

    private boolean isItemValid(double maxWeight, Challenge currentChallenge) {
        return currentChallenge.getWeight() <= maxWeight && currentChallenge.getCurrency() <= LIMIT_ITEM_COST;
    }

    private void addChallenge(List<List<Challenge>> workList, double maxWeight, Challenge currentChallenge) {
        new Worker(workList, maxWeight, currentChallenge).execute();
    }

    private List<Challenge> getChallengesResults(List<List<Challenge>> workList) {
        List<Double> valuesSum = workList
                .stream()
                .map(x -> x.stream().mapToDouble(Challenge::getCurrency).sum())
                .collect(Collectors.toList());
        Optional<Integer> index = IntStream.range(0, valuesSum.size()).boxed().max(Comparator.comparingDouble(valuesSum::get));
        return index.isPresent() ? workList.get(index.get()) : new ArrayList<>();
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
