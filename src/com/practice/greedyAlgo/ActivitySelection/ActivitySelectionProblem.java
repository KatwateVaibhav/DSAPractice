package com.practice.greedyAlgo.ActivitySelection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class ActivitySelectionProblem {

	
	static void activitySelection(ArrayList<Activity> activityList) {
	
		//Implement Comparator interface and specify which variable('FinishTime') of user defined class should the 'compare' method work on.
		//This needs to be done because this will be required in Sort method  
		Comparator<Activity> finishTimeComparator = new Comparator<Activity>() {
			@Override
			public int compare(Activity o1, Activity o2) {
				return o1.getFinishTime() - o2.getFinishTime();
			}
		};
		
		//Sort all the objects stored in the Arraylist by 'FinishTime' variable
		Collections.sort(activityList, finishTimeComparator);
		Activity previousActivity = activityList.get(0);
		
		
		//Print first job
		System.out.println("\n\nRecommended Schedule:\n"+activityList.get(0));
		
		
		//For each job
		for (int i = 1; i < activityList.size() ; i++) {
			Activity activity = activityList.get(i);
			//if job's start time is greater than equal previous job's finish time then print job
			if (activity.getStartTime() >= previousActivity.getFinishTime()) {
				System.out.println(activity);
				previousActivity = activity;
			}
		}//end of loop
	}//end of method

	public static void main(String[] args) {
		//Create an ArrayList to store all activities
		ArrayList<Activity> activityList = new ArrayList<Activity>();
		
		//Insert activities in Arraylist
		activityList.add(new Activity("A1", 0, 6));
		activityList.add(new Activity("A2", 3, 4));
		activityList.add(new Activity("A3", 1, 2));
		activityList.add(new Activity("A4", 5, 8));
		activityList.add(new Activity("A5", 5, 7));
		activityList.add(new Activity("A6", 8, 9));
	
		//Print user entered data
		System.out.println("User provided Schedule:");
		for (int i = 0; i < activityList.size() ; i++) {
			Activity activity = activityList.get(i);
			System.out.println(activity.toString());
		}
		
		//Perform calculation on activities
		activitySelection(activityList);
	
	}//end of method
	
}
