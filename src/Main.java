import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main
{

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException
	{
 
		
		Path path = new Path();
		
		if(args.length == 3)
		{
			path.setTextFilePath(args[0]);
			path.setRuleFilePath(args[1]);
			path.setResultFilePath(args[2]);
		}	
		else
		{
			
			path.setTextFilePath("./resource/test.txt");
			path.setRuleFilePath("./resource/rule.txt");
			path.setResultFilePath("./resource/result.txt"); 
		}
		
		
		
		StemmingService stem = new StemmingService();
		stem.stemming(path);
		
	}



}
