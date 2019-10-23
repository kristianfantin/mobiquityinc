package com.mobiquityinc.makers;

import com.google.common.base.CharMatcher;
import com.mobiquityinc.domain.Challenge;
import com.mobiquityinc.domain.PackageChallenge;
import com.mobiquityinc.exception.APIException;
import com.mobiquityinc.messages.ErrorMessages;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.lang.Double.parseDouble;

public class MakePackageChallenge {

    private static final int INDEX_POSITION = 0;
    private static final int WEIGHT_POSITION = 1;
    private static final int CURRENCY_POSITION = 2;
    private static final int INDEX_MAX_WEIGHT = 0;
    private static final int INDEX_OF_CHALLENGE = 1;
    private static final int INDEX_WITHOUT_COMMA = 1;

    private MakePackageChallenge() { }

    private static final String REGEX_BETWEEN_PARENTHESES = "\\(([^)]+)\\)";

    public static PackageChallenge toPackageChallenge(String line) throws APIException {
        try {
            PackageChallenge packageChallenge = new PackageChallenge();
            Pattern p = Pattern.compile(REGEX_BETWEEN_PARENTHESES);
            String[] values = line.split(":");
            packageChallenge.setMaxWeight(parseDouble(values[INDEX_MAX_WEIGHT].trim()));
            Matcher m = p.matcher(values[INDEX_OF_CHALLENGE]);
            while (m.find()) {
                String current = m.group(INDEX_WITHOUT_COMMA);
                String[] split = current.split(",");
                int index = Integer.parseInt(split[INDEX_POSITION].trim());
                double weight = parseDouble(split[WEIGHT_POSITION].trim());
                double currency = parseDouble(CharMatcher.inRange('0', '9').retainFrom(split[CURRENCY_POSITION].trim()));

                packageChallenge.setChallenges(getChallenge(index, weight, currency));
            }

            return packageChallenge;
        }
        catch (ArrayIndexOutOfBoundsException e) {
            throw new APIException(ErrorMessages.INVALID_INPUT.getMessage(), e);
        }
    }

    private static Challenge getChallenge(int index, double weight, double currency) {
        return new Challenge(index, weight, currency);
    }
}
