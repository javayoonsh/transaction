

import java.util.ArrayList;

public class Util 
{	
	public static String implode(String seperater, ArrayList<String> list)
	{		
		String str = "";		

		for(int i=0;i<list.size();i++)
		{
			if(i == (list.size())-1 )
			{
				str+= list.get(i);
			}			
			else
			{
				str+= list.get(i)+seperater;
			}			
		}		
		
		System.out.println(str);
		
		return str;
	}
}
