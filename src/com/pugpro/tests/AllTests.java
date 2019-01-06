package com.pugpro.tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ AttendingDAOTest.class, EventDAOTest.class, InstanceDAOTest.class, UserDAOTest.class })
public class AllTests {

}
