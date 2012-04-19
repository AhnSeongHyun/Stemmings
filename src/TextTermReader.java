import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TextTermReader
{

	public List<String> ReadTextTerm(String termFilePath)
	{

		List<String> rawTermList = Collections.emptyList();

		BufferedReader brd = null;
		String rLine = null;

		try
		{
			brd = new BufferedReader(new InputStreamReader(new FileInputStream(
					termFilePath), "UTF8"));
			rawTermList = new ArrayList<String>();

			for (;;)
			{
				if ((rLine = brd.readLine()) != null)
				{

					rLine += "\n";
					String[] terms = rLine.split(" ");

					for (String term : terms)
					{
						rawTermList.add(term);
					}
				}
				else
					break;
			}

			brd.close();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}

		return rawTermList;

	}
}
