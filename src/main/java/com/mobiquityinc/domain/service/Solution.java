package com.mobiquityinc.domain.service;

import com.mobiquityinc.domain.Challenge;
import com.mobiquityinc.domain.PackageChallenge;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Solution {

    private final PackageChallenge packageChallenge;

    public Solution(PackageChallenge packageChallenge) {
        this.packageChallenge = packageChallenge;
    }

    public String execute() {
        List<List<Challenge>> queue = new LinkedList<>();

        double maxWeight = packageChallenge.getMaxWeight();
        for (Challenge currentChallenge : packageChallenge.getChallenges()) {
            if (currentChallenge.getWeight() <= maxWeight) {
                int size = queue.size();
                for (int i = 0; i < size; i++) {
                    queue.get(i)
                            .stream()
                            .filter(runningChallenge -> (runningChallenge.getWeight() + currentChallenge.getWeight()) <= maxWeight)
                            .map(runningChallenge -> getChallenges(currentChallenge, runningChallenge))
                            .forEach(queue::add);
                }
                queue.add(getChallenges(currentChallenge));
            }
            else
                break;
        }

        double maxSum = 0;
        List<Challenge> results = new ArrayList<>();
        for (List<Challenge> challenges : queue) {
            double sum = challenges.stream().mapToDouble(Challenge::getCurrency).sum();
            if (sum > maxSum) {
                maxSum = sum;
                results = challenges;
            }
        }

        List<String> solved = new ArrayList<>();
        for (Challenge result : results) {
            solved.add(String.valueOf(result.getIndex()));
        }

        return String.join(",", solved);
    }

    private List<Challenge> getChallenges(Challenge currentChallenge, Challenge runningChallenge) {
        List<Challenge> result = getChallenges(currentChallenge);
        result.add(runningChallenge);
        return result;
    }

    private List<Challenge> getChallenges(Challenge currentChallenge) {
        List<Challenge> result = new ArrayList<>();
        result.add(currentChallenge);
        return result;
    }
}
