import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class StemmingRuleReader

{
	 

	List<String> ReadRule(String ruleFilePath)
	{
		List<String> rawRuleList = Collections.emptyList();


		BufferedReader brd = null;
		String rLine = null;
		
		try
		{
			brd = new BufferedReader(new InputStreamReader(new FileInputStream(ruleFilePath), "UTF8"));
			rawRuleList = new ArrayList<String>();
			 

			for (;;)
			{
				if ((rLine = brd.readLine()) != null)
				{
				
					rawRuleList.add(rLine.split("-")[1]);
						
					
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

		return rawRuleList;

	}
}
