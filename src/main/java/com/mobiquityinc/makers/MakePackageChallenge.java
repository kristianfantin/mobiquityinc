package com.mobiquityinc.makers;

import com.google.common.base.CharMatcher;
import com.mobiquityinc.domain.Challenge;
import com.mobiquityinc.domain.PackageChallenge;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MakePackageChallenge {

    private MakePackageChallenge() { }

    private static final String REGEX_BETWEEN_PARENTHESES = "\\(([^)]+)\\)";

    public static PackageChallenge toPackageChallenge(String line) {
        Pattern p = Pattern.compile(REGEX_BETWEEN_PARENTHESES);
        PackageChallenge packageChallenge = new PackageChallenge();
        String[] values = line.split(":");
        packageChallenge.setMaxWeight(Double.parseDouble(values[0].trim()));
        Matcher m = p.matcher(values[1]);
        while (m.find()) {
            String current = m.group(1);
            String[] split = current.split(",");
            int index = Integer.parseInt(split[0].trim());
            double weight = Double.parseDouble(split[1].trim());
            double currency = Double.parseDouble(CharMatcher.inRange('0', '9').retainFrom(split[2].trim()));

            packageChallenge.setChallenges(getChallenge(index, weight, currency));
        }

        return packageChallenge;
    }

    private static Challenge getChallenge(int index, double weight, double currency) {
        return new Challenge(index, weight, currency);
    }
}
