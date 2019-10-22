package com.mobiquityinc.template;

import com.mobiquityinc.domain.Challenge;
import com.mobiquityinc.domain.PackageChallenge;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MainTemplate {

    private List<PackageChallengeTemplate> challengeTemplates;

    public static ChallengeTemplate challenge(int index, double weight, double currency) {
        return new ChallengeTemplate().challenge(index, weight, currency);
    }

    public MainTemplate maxWeight(double maxWeight, ChallengeTemplate... challenges) {
        getChallengeTemplates().add(getPackageChallengeTemplate(maxWeight, challenges));
        return this;
    }

    private PackageChallengeTemplate getPackageChallengeTemplate(double maxWeight, ChallengeTemplate... challenges) {
        PackageChallengeTemplate template = new PackageChallengeTemplate(maxWeight);
        for (ChallengeTemplate challenge : challenges) {
            template.addChallengeTemplate(challenge);
        }
        return template;
    }

    private List<PackageChallengeTemplate> getChallengeTemplates() {
        if (challengeTemplates == null)
            challengeTemplates = new ArrayList<>();

        return challengeTemplates;
    }

    public void check(List<PackageChallenge> list) {
        int index = 0;
        for (PackageChallengeTemplate challengeTemplate : Collections.unmodifiableList(getChallengeTemplates())) {
            startCheck(list, challengeTemplate, index);
            index++;
        }
    }

    private void startCheck(List<PackageChallenge> packages, PackageChallengeTemplate challengeTemplate, int index) {
        PackageChallenge packageChallengeCheck = packages.get(index);
        assertEquals(challengeTemplate.getMaxWeight(), packageChallengeCheck.getMaxWeight(), "Max Weight: index " + index);
        checkChallenges(index, challengeTemplate, packageChallengeCheck);
    }

    private void checkChallenges(int index, PackageChallengeTemplate packageChallenge, PackageChallenge packageChallengeCheck) {
        int indexChallenge = 0;
        for (ChallengeTemplate challenge : Collections.unmodifiableList(packageChallenge.getChallenges())) {
            checkChallenge(packageChallengeCheck, indexChallenge, index, challenge);
            indexChallenge++;
        }
    }

    private void checkChallenge(PackageChallenge packageChallengeCheck, int indexChallenge, int index, ChallengeTemplate challenge) {
        Challenge challengeCheck = packageChallengeCheck.getChallenges().get(indexChallenge);

        assertEquals(challenge.getChallenge().getIndex(), challengeCheck.getIndex(), "Index (" + index + "," + indexChallenge + ")");
        assertEquals(challenge.getChallenge().getWeight(), challengeCheck.getWeight(), "Weight: Index (" + index + "," + indexChallenge + ")");
        assertEquals(challenge.getChallenge().getCurrency(), challengeCheck.getCurrency(), "Currency: Index (" + index + "," + indexChallenge + ")");
    }
}
