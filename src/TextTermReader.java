import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class TextTermReader
{

	List<String> frontTermList = new ArrayList<String>();
	List<String> bodyTermList = new ArrayList<String>();
	List<String> backTermList = new ArrayList<String>();

	public void ReadTextTerm(String termFilePath)
	{ 
		BufferedReader brd = null;
		String rLine = null;

		try
		{
			brd = new BufferedReader(new InputStreamReader(new FileInputStream(
					termFilePath), "UTF8"));

			for (;;)
			{
				if ((rLine = brd.readLine()) != null)
				{

					rLine += "\n";
					String[] terms = rLine.split(" ");

					for (String term : terms)
					{

						frontTermList.add(this.extractFrontGrams(term));
						bodyTermList.add(this.extractBodyGrams(term));
						backTermList.add(this.extractBackGrams(term));

						System.out.println("front : "
								+ this.extractFrontGrams(term)+"len:"
										+ this.extractFrontGrams(term).length());
						System.out.println("body : "
								+ this.extractBodyGrams(term));
						System.out.println("back : "
								+ this.extractBackGrams(term) + "len:"
								+ this.extractBackGrams(term).length());
						System.out
								.println("=====================================");

					}
				}
				else
				{
					break;
				}

			}

			brd.close();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}

	}

	private String extractFrontGrams(String rawTerm)
	{

		char[] grams = rawTerm.toCharArray();

		StringBuilder frontGrams = new StringBuilder();
		
		for (char gram : grams)
		{
			if (isEnglish(gram))
			{
				break;
			}else
				frontGrams.append(gram);

		}

		return frontGrams.toString();

	}

	private String extractBodyGrams(String rawTerm)
	{

		char[] grams = rawTerm.toCharArray();

		StringBuilder bodyGrams = new StringBuilder();
		boolean isBodyStartPoint = false;

		for (char gram : grams)
		{
			if (isBodyStartPoint == false)
			{

				if (isEnglish(gram))
				{
					isBodyStartPoint = true;
					bodyGrams.append(gram);
				}

			}
			else
			{
				if (isNewLine(gram))
				{
					break;
				}
				else
				{
					bodyGrams.append(gram);

				}
			}

		}

		return bodyGrams.toString();

	}

	public String extractBackGrams(String rawTerm)
	{

		int i = 0;

		char[] grams = rawTerm.toCharArray();

		StringBuilder backGrams = new StringBuilder();
		
	 
		for (i = grams.length - 1; i > -1; i--)
		{

			if (!isNewLine(grams[i]))
			{
				break;
			}
			else
			{
				backGrams.append(grams[i]);
			}

		}
		 
		backGrams.append(" ");
	 
		return backGrams.reverse().toString();
	}

	private boolean isEnglish(char gram)
	{
		boolean isEng = false;

		if (('a' <= gram && gram <= 'z') || ('A' <= gram && gram <= 'Z'))
		{
			isEng = true;
		}
		else
		{
			isEng = false;
		}

		return isEng;
	}

	private boolean isNewLine(char gram)
	{
		boolean isNewLine = false;

		if (gram == ' ' || gram == '\t' || gram == '\n' || gram == '\r')
		{
			isNewLine = true;
		}
		else
		{
			isNewLine = false;
		}

		return isNewLine;
	}

}
