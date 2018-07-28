package main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class SimulationDriver
{
	private static Question question;
	private static Scanner input;
	private static Student[] students;
	private static HashMap<Character, Integer> answerStat;

	public static void main(String[] args)
	{
		input = new Scanner(System.in);
		setQuestion();

		answerStat = new HashMap<>();
		getStudentAnswers();

		for (int i = 0; i < students.length; i++)
		{
			boolean correct = true;
			for (Answer chosen : students[i].getSelectedAnswers())
				if (!chosen.isCorrect())
					correct = false;

			if (correct)
				System.out.println(students[i].getID() + " is correct");
		}

		for (char label : answerStat.keySet())
			System.out.println(label + ": " + answerStat.get(label));
	}

	private static void setQuestion()
	{
		System.out.println("What is the question: ");
		String questionText = input.nextLine();
		System.out.print("Num answers: ");
		int numAnswers = input.nextInt();
		Answer[] answers = new Answer[numAnswers];
		boolean multipleChoice = false;
		boolean hasCorrect = false;

		for (int i = 0; i < numAnswers; i++)
		{
			System.out.print("What one character label do you want for this answer: ");
			char label = input.next().charAt(0);
			input.nextLine();
			System.out.println("Answer " + label + " text:");
			String ansText = input.nextLine();
			System.out.println("Is this a correct answer (true/false): ");
			boolean correct = input.nextBoolean();

			if (hasCorrect && correct)
				multipleChoice = true;
			if (correct)
				hasCorrect = true;
			answers[i] = new Answer(label, ansText, correct);
		}

		question = new Question(questionText, answers, multipleChoice);

	}

	private static void getStudentAnswers()
	{
		System.out.print("How many students do you have: ");
		int numStudents = input.nextInt();
		students = new Student[numStudents];

		for (int i = 0; i < numStudents; i++)
		{
			System.out.println("Hand to next student");
			System.out.print("What's your ID: ");
			String id = input.next();

			System.out.println(question.getQuestion());
			for (Answer answer : question.getAnswers())
				System.out.println(answer.getLabel() + ": " + answer.getAnswer());

			HashMap<Character, Answer> chosen = new HashMap<>();
			boolean doneAnswering = true;
			do
			{
				System.out.println("Which choice do you want?");
				char chosenLabel = input.next().charAt(0);
				for (int j = 0; j < question.getAnswers().length; j++)
					if (question.getAnswers()[j].getLabel() == chosenLabel)
					{
						if (answerStat.containsKey(chosenLabel))
							answerStat.put(chosenLabel, answerStat.get(chosenLabel) + 1);
						else
							answerStat.put(chosenLabel, 1);

						chosen.put(chosenLabel, question.getAnswers()[j]);
						break;
					}

				if (question.isMultipleChoice())
				{
					System.out.print("Do you want to add more choices (true/false): ");
					doneAnswering = !input.nextBoolean();
				}

			}
			while (!doneAnswering);

			ArrayList<Answer> selectedAnswers = new ArrayList<>();
			for (char label : chosen.keySet())
				selectedAnswers.add(chosen.get(label));

			students[i] = new Student(id, selectedAnswers);
		}
	}
}
