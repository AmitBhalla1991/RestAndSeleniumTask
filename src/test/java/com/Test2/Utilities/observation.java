package com.Test2.Utilities;

import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.ITestContext;

public class observation implements ITestListener {

    @Override
    public void onStart(ITestContext result) {
        System.out.println("The name of the testcase Started is :" + result.getName()+" Test Case Started.");

    }

    @Override
    public void onTestStart(ITestResult result) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onTestSuccess(ITestResult result) {
        System.out.println("The name of the testcase Success is :" + result.getName());

    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onTestFailure(ITestResult result) {
        System.out.println("The name of the testcase Failed is :" + result.getName());

    }

    @Override
    public void onTestSkipped(ITestResult result) {
        System.out.println("The name of the testcase Skipped is :" + result.getName());

    }


    @Override
    public void onFinish(ITestContext result) {
        System.out.println("The name of the testcase Finish is :" + result.getName());

    }

}
