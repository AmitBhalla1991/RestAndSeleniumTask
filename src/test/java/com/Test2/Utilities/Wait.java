package com.Test2.Utilities;

import com.Test2.TestBase.Base;
import groovyjarjarpicocli.CommandLine;

import java.util.concurrent.TimeUnit;

public class Wait extends Base {

    public static void waitInSeconds(int period) {

        driver.manage().timeouts().implicitlyWait(period, TimeUnit.SECONDS);

    }

    public static void fixedWait(int value) {
        try {
            Thread.sleep(value);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
