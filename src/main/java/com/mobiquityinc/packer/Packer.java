package com.mobiquityinc.packer;

import com.mobiquityinc.domain.PackageChallenge;
import com.mobiquityinc.domain.service.FileService;
import com.mobiquityinc.domain.service.Solution;
import com.mobiquityinc.domain.service.SortChallenge;
import com.mobiquityinc.exception.APIException;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.stream.Collectors;

public class Packer {

  private Packer() { }

   public static String pack(String filePath) throws APIException, IOException {
    InputStream fileInputStream = new FileInputStream(filePath);
    List<PackageChallenge> packageChallengeList = new FileService(fileInputStream).load();
    orderList(packageChallengeList);

    return packageChallengeList
            .stream()
            .map(packageChallenge -> new Solution(packageChallenge).execute())
            .collect(Collectors.joining(System.lineSeparator()));
  }

  private static void orderList(List<PackageChallenge> packageChallengeList) {
    packageChallengeList.forEach(packageChallenge -> new SortChallenge().sortByWeight(packageChallenge.getChallenges()));
  }

}
