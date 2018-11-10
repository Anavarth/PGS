import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.*;

public class GS {
	static String[][] readfile(String name,String spl)
	{
		File file = new File(name);
		String[][] st = new String[5][10];
		int i=0;
		BufferedReader br=null;
		  try
		  { 
			  br = new BufferedReader(new FileReader(file)); 
			  while ((st[i] = br.readLine().split(spl)) != null)
			  {
				  for(int j=0;j<st[i].length;j++)
					  System.out.print(st[i][j]+"  ");
				  System.out.println();
				  i++;
				  if(i==5)
					  break;
			  }
		  }catch(IOException ex){
			  System.err.println("An IOException was caught!");
			  ex.printStackTrace();
		  }
		  finally
		  {
			  try
			  {
				  br.close();
			  }catch(IOException ex){
				  System.err.println("An IOException was caught!");
				  ex.printStackTrace();
			  }
		  }
		  return(st);
	}
	static void print(String[] S1,String[] S2)
	{
		if(Integer.parseInt(S1[4])<=0)
			S1[4]=Integer.toString(0);
		if(Integer.parseInt(S2[4])<=0)
			S2[4]=Integer.toString(0);
		System.out.println(S1[1]+"\t"+S1[4]+"\t\t"+S2[1]+"\t"+S2[4]);
	}
	static void siml(String[][] S1,String[][] S2)
	{
		System.out.println("\n\n\nPlayer 1\t\t\t\tPlayer2");
		System.out.println("Pokemon"+"\t\t\t"+"HP"+"\t\t"+"Pokemon"+"\t\t"+"HP");
		int p1_pk_count=0,p2_pk_count=0,p1_ct,p2_ct;
		while(true)
		{
			p1_ct=p2_ct=0;
			for(int i = 0;;i++ )
			{
				if(i%2==0)
				{
					if(p1_ct%4==0&&p1_ct%6==0)
					{
						if(Integer.parseInt(S2[p2_pk_count][8])-Integer.parseInt(S1[p1_pk_count][7])<0)
						{
							S2[p2_pk_count][4]=Integer.toString(Integer.parseInt(S2[p2_pk_count][4])+Integer.parseInt(S2[p2_pk_count][8])-Integer.parseInt(S1[p1_pk_count][7]));
						}
					}
					else if(p1_ct%4==0)
					{
						if(Integer.parseInt(S2[p2_pk_count][6])-Integer.parseInt(S1[p1_pk_count][7])<0)
						{
							S2[p2_pk_count][4]=Integer.toString(Integer.parseInt(S2[p2_pk_count][4])+Integer.parseInt(S2[p2_pk_count][6])-Integer.parseInt(S1[p1_pk_count][7]));
						}
						
					}
					else if(p1_ct%6==0)
					{
						if(Integer.parseInt(S2[p2_pk_count][8])-Integer.parseInt(S1[p1_pk_count][5])<0)
						{
							S2[p2_pk_count][4]=Integer.toString(Integer.parseInt(S2[p2_pk_count][4])+Integer.parseInt(S2[p2_pk_count][8])-Integer.parseInt(S1[p1_pk_count][5]));
						}
					}
					else
					{
						if(Integer.parseInt(S2[p2_pk_count][6])-Integer.parseInt(S1[p1_pk_count][5])<0)
						{
							S2[p2_pk_count][4]=Integer.toString(Integer.parseInt(S2[p2_pk_count][4])+Integer.parseInt(S2[p2_pk_count][6])-Integer.parseInt(S1[p1_pk_count][5]));
						}
					}
					p1_ct++;
				}
				else
				{
					if(p2_ct%4==0&&p2_ct%6==0)
					{
						if(Integer.parseInt(S1[p1_pk_count][8])-Integer.parseInt(S2[p2_pk_count][7])<0)
						{
							S1[p1_pk_count][4]=Integer.toString(Integer.parseInt(S1[p1_pk_count][4])+Integer.parseInt(S1[p1_pk_count][8])-Integer.parseInt(S2[p2_pk_count][7]));
						}
					}
					else if(p2_ct%4==0)
					{
						if(Integer.parseInt(S1[p1_pk_count][6])-Integer.parseInt(S2[p2_pk_count][7])<0)
						{
							S1[p1_pk_count][4]=Integer.toString(Integer.parseInt(S1[p1_pk_count][4])+Integer.parseInt(S1[p1_pk_count][6])-Integer.parseInt(S2[p2_pk_count][7]));
						}
						
					}
					else if(p2_ct%6==0)
					{
						if(Integer.parseInt(S1[p1_pk_count][8])-Integer.parseInt(S2[p2_pk_count][5])<0)
						{
							S1[p1_pk_count][4]=Integer.toString(Integer.parseInt(S1[p1_pk_count][4])+Integer.parseInt(S1[p1_pk_count][8])-Integer.parseInt(S2[p2_pk_count][5]));
						}
					}
					else
					{
						if(Integer.parseInt(S1[p1_pk_count][6])-Integer.parseInt(S2[p2_pk_count][5])<0)
						{
							S1[p1_pk_count][4]=Integer.toString(Integer.parseInt(S1[p1_pk_count][4])+Integer.parseInt(S1[p1_pk_count][6])-Integer.parseInt(S2[p2_pk_count][5]));
						}
					}
					p2_ct++;
				}
				print(S1[p1_pk_count],S2[p2_pk_count]);
				if(Integer.parseInt(S1[p1_pk_count][4])<=0)
				{
					p1_pk_count++;
					System.out.println();
					break;
				}
				if(Integer.parseInt(S2[p2_pk_count][4])<=0)
				{
					p2_pk_count++;
					System.out.println();
					break;
				}
			}
			if(p1_pk_count==5||p2_pk_count==5)
				break;
		}
		if(p1_pk_count==5)
			System.out.println("\n\nPlayer 2 is Victory");
		else
			System.out.println("\n\nPlayer 1 is Victory");
	}
	public static void main(String args[])
	{
		String[][] F=readfile("/home/hadoop/abc.txt","_");
		System.out.println("\n\n");
		String[][] F1=readfile("/home/hadoop/abc1.txt",",");
		siml(F,F1);
	}
}
