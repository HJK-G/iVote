package main;

public class Answer
{
	private char label;
	private String answer;
	private boolean isCorrect;

	public Answer(char i, String answer, boolean isCorrect)
	{
		this.label = i;
		this.answer = answer;
		this.isCorrect = isCorrect;
	}

	public char getLabel()
	{
		return label;
	}

	public String getAnswer()
	{
		return answer;
	}

	public boolean isCorrect()
	{
		return isCorrect;
	}

}
