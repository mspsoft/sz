package com.sz.testcases;

import org.testng.annotations.Test;

import com.sz.base.TestBase;
import com.sz.utilities.businessLibrary;

public class AddNewExperiment extends TestBase{
  @Test
  public void f() {
	  	String stepNumber="step1";
	  	String journalCode="SRL-2002";
	  	String experimentName="Experiment1";
	  	String status="Open"; 
	  	String ReactionType="Acidic Reaction";
	  	String ReactionName="Acidic hydrochloric reaction";
		businessLibrary.launchApplication();
		businessLibrary.loginToSynzeal(config.getProperty("scientist_username"), config.getProperty("scientist_password"));
		businessLibrary.editScheme("SZ-C081D01-A", "In Progress");
		businessLibrary.addExperimentButtonClick();
		businessLibrary.addExperiment(stepNumber,journalCode,experimentName,status,ReactionType,ReactionName);

  }
}
