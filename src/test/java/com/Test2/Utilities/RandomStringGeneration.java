package com.Test2.Utilities;

import org.apache.commons.lang3.RandomStringUtils;

public class RandomStringGeneration {

    public static String randomstring() {

        String generatedString=RandomStringUtils.randomAlphabetic(5);
        return (generatedString);

    }

    public static String number() {

        String generatednumber=RandomStringUtils.randomNumeric(4);

        return (generatednumber);


    }


}
