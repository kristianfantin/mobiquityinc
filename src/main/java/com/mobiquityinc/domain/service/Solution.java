package com.mobiquityinc.domain.service;

import com.mobiquityinc.domain.Challenge;
import com.mobiquityinc.domain.PackageChallenge;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Solution {

    private final PackageChallenge packageChallenge;

    public Solution(PackageChallenge packageChallenge) {
        this.packageChallenge = packageChallenge;
    }

    public String execute() {
        List<List<Challenge>> workList = new LinkedList<>();

        double maxWeight = packageChallenge.getMaxWeight();
        packageChallenge.getChallenges()
                .stream()
                .filter(currentChallenge -> currentChallenge.getWeight() <= maxWeight)
                .forEach(currentChallenge -> addChallenge(workList, maxWeight, currentChallenge));

        List<Challenge> results = getChallengesResults(workList);
        return results.isEmpty()?  "-" : getResult(results);
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

    private void addChallenge(List<List<Challenge>> workList, double maxWeight, Challenge currentChallenge) {
        IntStream.range(0, workList.size()).forEach(index -> addToList(workList, maxWeight, currentChallenge, index));
        workList.add(getChallenges(currentChallenge));
    }

    private void addToList(List<List<Challenge>> workList, double maxWeight, Challenge currentChallenge, int index) {
        workList.get(index)
                .stream()
                .filter(runningChallenge -> (runningChallenge.getWeight() + currentChallenge.getWeight()) <= maxWeight)
                .map(runningChallenge -> getChallenges(currentChallenge, runningChallenge))
                .forEach(workList::add);
    }

    private String getResult(List<Challenge> results) {
        return String.join(", ", getCollect(results));
    }

    private List<String> getCollect(List<Challenge> results) {
        List<Integer> collect = results.stream().map(Challenge::getIndex).collect(Collectors.toList());
        new SortChallenge().sort(collect);
        return collect.stream().map(Object::toString).collect(Collectors.toList());
    }

    private List<Challenge> getChallenges(Challenge currentChallenge, Challenge runningChallenge) {
        List<Challenge> result = getChallenges(currentChallenge);
        result.add(runningChallenge);
        return result;
    }

    private List<Challenge> getChallenges(Challenge challenge) {
        List<Challenge> result = new ArrayList<>();
        result.add(challenge);
        return result;
    }
}
