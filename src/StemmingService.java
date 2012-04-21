import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class StemmingService
{

	public void stemming(Path path) throws IOException
	{

		TextTermReader termReader = new TextTermReader();
		StemmingRuleReader stemRuleReader = new StemmingRuleReader();

		termReader.ReadTextTerm(path.getTextFilePath());
		List<String> rawRules = stemRuleReader.ReadRule(path.getRuleFilePath());

		String filePath = path.getResultFilePath();
		List<String> stemmedTerms = new ArrayList<String>();

		int i = 0;
		String rawTerm = null;

		for (i = 0; i < termReader.bodyTermList.size(); i++)
		{
			rawTerm = termReader.bodyTermList.get(i);

			for (String rawRule : rawRules)
			{
				if (containRule(rawTerm, rawRule))
				{
					stemmedTerms.add(applyRuleToTerm(rawTerm, rawRule));
					break;

				}
				else
				{
					stemmedTerms.add(rawTerm);
					break;
				}
			}
		}

		ResultWriter resultWriter = new ResultWriter();

		resultWriter.writeStmmedTerm(termReader, stemmedTerms, filePath);

	}

	private  String applyRuleToTerm(String term, String rule)
	{
 

		String appliedTerm = null;

		int ruleLen = rule.length();

		char[] termGram = term.toCharArray();

		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < ruleLen; i++)
		{
			sb.append(termGram[termGram.length - 1 - i]);

		}

		sb = sb.reverse();

		if (isEqualRule(sb.toString(), rule))
		{
 

			sb.delete(0, sb.length());

			for (int i = 0; i < termGram.length - ruleLen; i++)
			{

				sb.append(termGram[i]);

			}

		
			appliedTerm = sb.toString();
		}
		else
		{

			appliedTerm = term;
		}

		return appliedTerm;

	}
	
	private boolean containRule(String inputTerm, String rule)
	{
		boolean isContain = false;
		
		 
		if (inputTerm.contains(rule.toLowerCase()))
		{
			isContain = true;

		}
		else if (inputTerm.contains(rule.toUpperCase()))
		{
			isContain = true;
		}
		else
		{
			isContain = false;
		}
		return isContain;
	}
	
	
	private boolean isEqualRule(String inputGrams, String rule)
	{
		boolean isEqual = false;
		
		 
		if (inputGrams.equals(rule.toLowerCase()))
		{
			isEqual = true;

		}
		else if (inputGrams.equals(rule.toUpperCase()))
		{
			isEqual = true;
		}
		else
		{
			isEqual = false;
		}
		return isEqual;
	}
}
