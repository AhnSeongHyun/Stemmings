import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class StemmingService
{

	public void stemming(Path path) throws IOException
	{

		TextTermReader termReader = new TextTermReader();
		StemmingRuleReader stemRuleReader = new StemmingRuleReader();

		List<String> rawTerms = termReader.ReadTextTerm(path.getTextFilePath());
		List<String> rawRules = stemRuleReader.ReadRule(path.getRuleFilePath());

		String filePath = path.getResultFilePath();
		List<String> stemmedTerms = new ArrayList<String>();

		int i = 0;
		String rawTerm = null;

		for (i = 0; i < rawTerms.size(); i++)
		{
			rawTerm = rawTerms.get(i);

			for (String rawRule : rawRules)
			{
				if (rawTerm.contains(rawRule))
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

		resultWriter.writeStmmedTerm(stemmedTerms, filePath);

	}

	private static String applyRuleToTerm(String term, String rule)
	{
		// TODO : NEED REFINE

		int ruleLen = rule.length();

		char[] termGram = term.toCharArray();

		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < ruleLen; i++)
		{
			sb.append(termGram[termGram.length - 1 - i]);

		}

		sb = sb.reverse();

		if (sb.toString().equals(rule))
		{

			System.out.println("term:" + term + "   rule : " + rule);

			sb.delete(0, sb.length());

			for (int i = 0; i < termGram.length - ruleLen; i++)
			{

				sb.append(termGram[i]);

			}

			System.out.println("stemmed : " + sb.toString());

		}

		return sb.toString();

	}
}
