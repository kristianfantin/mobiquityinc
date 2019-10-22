package com.mobiquityinc.domain.service;

import com.mobiquityinc.domain.PackageChallenge;
import com.mobiquityinc.exception.APIException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import static com.mobiquityinc.makers.MakePackageChallenge.toPackageChallenge;
import static com.mobiquityinc.messages.ErrorMessages.IO_ERROR;

public class FileService {

    private InputStream inputStream;

    public FileService(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public List<PackageChallenge> load() throws APIException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            List<PackageChallenge> result = new ArrayList<>();
            String readLine;
            while ((readLine = reader.readLine()) != null) {
                if (!readLine.isEmpty())
                    result.add(toPackageChallenge(readLine));
            }
            return result;
        } catch (IOException e) {
            throw new APIException(IO_ERROR.getMessage(), e);
        }
    }
}
