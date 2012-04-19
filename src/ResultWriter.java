import java.io.BufferedWriter; 
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter; 
import java.io.Writer; 
import java.util.List;
 


public class ResultWriter
{

	public void writeStmmedTerm(List<String> stemmedTerms, String filePath)
			throws IOException
	{

		Writer bw = new BufferedWriter(new OutputStreamWriter(
				new FileOutputStream(filePath), "UTF8"));

		for (String term : stemmedTerms)
		{ 
			bw.write(term);
		}

		bw.close();

	}
}
