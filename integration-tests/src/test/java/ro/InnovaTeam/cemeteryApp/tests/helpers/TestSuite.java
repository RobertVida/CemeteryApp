package ro.InnovaTeam.cemeteryApp.tests.helpers;

import java.util.List;

/**
 * Created by robert on 11/24/2014.
 */
public class TestSuite {

    private List<TestCase> tests;

    public TestSuite() {
    }

    public List<TestCase> getTests() {
        return tests;
    }

    public void setTests(List<TestCase> tests) {
        this.tests = tests;
    }
}
