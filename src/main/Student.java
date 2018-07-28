package main;

import java.util.ArrayList;

public class Student
{
	private String id;
	private ArrayList<Answer> selectedAnswers;

	public Student(String id, ArrayList<Answer> selectedAnswers)
	{
		this.id = id;
		this.selectedAnswers = selectedAnswers;
	}

	public String getID()
	{
		return id;
	}

	public ArrayList<Answer> getSelectedAnswers()
	{
		return selectedAnswers;
	}
}
