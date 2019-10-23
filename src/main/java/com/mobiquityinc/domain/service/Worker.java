package com.mobiquityinc.domain.service;

import com.mobiquityinc.domain.Challenge;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.IntStream;

class Worker {

    private List<List<Challenge>> workList;
    private double maxWeight;
    private Challenge currentChallenge;

    Worker(List<List<Challenge>> workList, double maxWeight, Challenge currentChallenge) {
        this.workList = workList;
        this.maxWeight = maxWeight;
        this.currentChallenge = currentChallenge;
    }

    void execute() {
        IntStream.range(0, workList.size()).forEach(index -> addToList(workList, maxWeight, currentChallenge, index));
        workList.add(setNewChallenge(currentChallenge));
    }

    private List<Challenge> setNewChallenge(Challenge challenge) {
        List<Challenge> result = new ArrayList<>();
        result.add(challenge);
        return result;
    }

    private void addToList(List<List<Challenge>> workList, double maxWeight, Challenge currentChallenge, int index) {
        workList.add(setNewChallenge(workList, maxWeight, currentChallenge, index));
    }

    private List<Challenge> setNewChallenge(List<List<Challenge>> workList, double maxWeight, Challenge currentChallenge, int index) {
        List<Challenge> challenges = new ArrayList<>();
        setNewItems(workList, maxWeight, currentChallenge, index, challenges);
        challenges.add(currentChallenge);
        return challenges;
    }

    private void setNewItems(List<List<Challenge>> workList, double maxWeight, Challenge currentChallenge, int index, List<Challenge> challenges) {
        AtomicReference<Double> sum = new AtomicReference<>((double) 0);
        workList.get(index)
                .stream()
                .filter(runningChallenge -> (runningChallenge.getWeight() + currentChallenge.getWeight() + sum.get()) <= maxWeight)
                .forEach(runningChallenge -> {
                    challenges.add(runningChallenge);
                    sum.updateAndGet(v -> v + runningChallenge.getWeight());
                });
    }

}
