package pk;
import java.io.IOException;
import java.io.File;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayDeque;
import java.util.Deque;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.*;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
 
public class PA{
    static int[] job = new int[5];
    static int[] wkr = new int[5];
	static int m=0;
	static int n=0;
	static int n1=0;
	static int count=0;
	/*static final String[] id = new String[750];
	static final String[] name = new String[750];
	static final String[] typ1 = new String[750];
	//static final String[] typ2 = new String[750];*/
	static int tot = 0;
	static int hp = 0;
	static int atk = 0;
	static int def = 0;
	static int Spl_atk = 0;
	static int Spl_def = 0;
	static int spd = 0;
	static int[] id = new int[90];
	static double[] cst = new double[90];
	static boolean flag1 = true;
	
  public static class TokenizerMapper
       extends Mapper<LongWritable, Text, Text, Text>{
 
    public void map(LongWritable key, Text value, Context context
                    ) throws IOException, InterruptedException {
    	String[] st = value.toString().split(",");
    	context.write(new Text("summary"), new Text(st[0] + "_" + st[1]+ "_" + st[2]+ "_" + st[3]+ "_" + st[4]+ "_" + st[5]+ "_" + st[6]+ "_" + st[7]+ "_" + st[8]+ "_" + st[9]));
    	if(flag1)
    		m++;
    }
  }
 
  public static class IntSumReducer
       extends Reducer<Text,Text,Text,IntWritable> {
	  String compositeString = new String();;
		String[] compositeStringArray = new String[5];
    public void reduce(Text key, Iterable<Text> values,
                       Context context
                       ) throws IOException, InterruptedException {
    	for (Text val : values) {
    		compositeString = val.toString();
    		compositeStringArray = compositeString.split("_");
    		/*id[n]=compositeStringArray[0];
    		name[n]=compositeStringArray[1];
    		typ1[n]=compositeStringArray[2];
    		//typ2[n]=compositeStringArray[3];*/
    		tot+=Integer.parseInt(compositeStringArray[3]);
    		hp+=Integer.parseInt(compositeStringArray[4]);
    		atk+=Integer.parseInt(compositeStringArray[5]);
    		def+=Integer.parseInt(compositeStringArray[6]);
    		Spl_atk+=Integer.parseInt(compositeStringArray[7]);
    		Spl_def+=Integer.parseInt(compositeStringArray[8]);
    		spd+=Integer.parseInt(compositeStringArray[9]);
    		n++;
    		context.write(new Text(compositeString), new IntWritable(n));
    		//context.write(new Text(id[n-1]), new IntWritable(n));
    	}
    	if(n==m)
    	{
    		tot/=n;
    		hp/=n;
    		atk/=n;
    		def/=n;
    		Spl_atk/=n;
    		Spl_def/=n;
    		spd/=n;
    		context.write(new Text("\n\nAverage Total           :  "), new IntWritable(tot));
    		context.write(new Text("Average HP              :  "), new IntWritable(hp));
    		context.write(new Text("Average Attack          :  "), new IntWritable(atk));
    		context.write(new Text("Average Defence         :  "), new IntWritable(def));
    		context.write(new Text("Average Special Attack  :  "), new IntWritable(Spl_atk));
    		context.write(new Text("Average Special Defence :  "), new IntWritable(Spl_def));
    		context.write(new Text("Average Speed           :  "), new IntWritable(spd));
    		flag1 = false;
    	}
    }
  }
 
  public static class IntSumReducer1
  extends Reducer<Text,Text,Text,FloatWritable> {
 String compositeString = new String();;
	String[] compositeStringArray = new String[5];

public void reduce(Text key, Iterable<Text> values,
                  Context context
                  ) throws IOException, InterruptedException {
	double[] cost = new double[90] ;
	for (Text val : values) {
		compositeString = val.toString();
		compositeStringArray = compositeString.split("_");
		if(tot<=Integer.parseInt(compositeStringArray[3]) && hp<=Integer.parseInt(compositeStringArray[4]) && atk<=Integer.parseInt(compositeStringArray[5]) && def<=Integer.parseInt(compositeStringArray[6]) && Spl_atk<=Integer.parseInt(compositeStringArray[7]) && Spl_def<=Integer.parseInt(compositeStringArray[8]) && spd<=Integer.parseInt(compositeStringArray[9]))
		{
			id[count]=n1;
			cost[count] = (2*Integer.parseInt(compositeStringArray[4]) + Integer.parseInt(compositeStringArray[5]) + Integer.parseInt(compositeStringArray[6]) + 1.5 * (Integer.parseInt(compositeStringArray[7]) + Integer.parseInt(compositeStringArray[8])) + 0.75 * Integer.parseInt(compositeStringArray[9]))/10;
			cst[count]= (2.5*Integer.parseInt(compositeStringArray[3]) + 2*Integer.parseInt(compositeStringArray[4]) + 1.2*Integer.parseInt(compositeStringArray[5]) + 1.25*Integer.parseInt(compositeStringArray[6]) + 1.75*Integer.parseInt(compositeStringArray[7]) + 1.5*Integer.parseInt(compositeStringArray[8])+1.1*Integer.parseInt(compositeStringArray[9]))/30;
			context.write(new Text(Integer.toString(id[count])), new FloatWritable((float)cost[count]));
			count++;
		}
		n1++;
		//context.write(new Text(compositeString), new FloatWritable(n1));
		//context.write(new Text(id[n-1]), new IntWritable(n));
	}
	if(n1==m)
	{
		int tp1;
		double tp2;
		double tp3;
		for(int i=0;i<count-1;i++)
			for(int j=1+i;j<count;j++)
				if(cost[j]>cost[i])
				{
					tp1 = id[i];
					tp2 = cost[i];
					tp3 = cst[i];
					id[i] = id[j];
					cost[i] = cost[j];
					cst[i]=cst[j];
					id[j] = tp1;
					cost[j] = tp2;
					cst[j]=tp3;
				}
		context.write(new Text("\n\nTop Five Pokemon Cost"), new FloatWritable(0));
		for(int i=0;i<5;i++)
			context.write(new Text(Integer.toString(id[i])), new FloatWritable((float)cost[i]));
		context.write(new Text("\n\nAssigning Cost"), new FloatWritable(0));
		for(int i=0;i<5;i++)
			context.write(new Text(Integer.toString(id[i])), new FloatWritable((float)cst[i]));
		double[][] mat = new double[5][5];
		boolean flag=true;
		for(int i=0;i<5;i++)
		{
			for(int j=0;j<5;j++)
			{
				mat[i][j] = cost[i]*cst[j];
				if(flag==true)
					context.write(new Text("\n"), new FloatWritable((float)mat[i][j]));
				else
					context.write(new Text(" "), new FloatWritable((float)mat[i][j]));
				flag = false;
			}
			flag = true;
		}
		findMinimumCost(mat,5);
		for(int i=0;i<5;i++)
			context.write(new Text(Integer.toString(id[wkr[i]])), new FloatWritable((float)cost[i]));
		//String[][] list = new String[5][10];
		//List<String> arrayList = new ArrayList<>();
	}
	//System.out.println("\nHIII\n");
}
 
static int FindMin(double[][] inputArray,int n, boolean[] rowVisited, boolean[] colVisited, int target){
    double min=Integer.MAX_VALUE;
    
    int rowIndex=0,colIndex=0;
    //Check the present minimum job cost for target worker which will be at colIndex
    for(int i=0;i<n;i++){
        if(!colVisited[i] && inputArray[target][i]<min){
            min=inputArray[target][i];
            colIndex=i;
        }
    }
    min=Integer.MAX_VALUE;
    //Check whether the value at colIndex for the target worker is also minimum for that job
    for(int i=0;i<n;i++){
        if(!rowVisited[i] && inputArray[i][colIndex]<min){
            min=inputArray[i][colIndex];
            rowIndex=i;
        }
    }
    //If value is minimum then return the job number
    if(rowIndex==target)
        return  colIndex;
    else
        return -1;
    
}
 static void findMinimumCost(double[][] inputArray,int n){
     boolean rowVisited[]=new boolean[n]; //checks which job has been asigned yet
     boolean colVisited[]=new boolean[n]; //checks which worker has got the job yet
     int k=0;
     Deque<Integer> queue=new ArrayDeque<Integer>();
     //Initially add all workers in the queue
     for(int i=0;i<n;i++){
         queue.add(i);
     }
     while(!queue.isEmpty()){
         int target=queue.poll(); // Take each worker one by one and assign one job if it is costing minimum overall
         
         int min=FindMin(inputArray,n,rowVisited,colVisited,target);
         if(min==-1){
             //If that worker did not get the job, add the worker in the queue again
            queue.add(target);
         }
        else{
            //System.out.println("Assign Job "+ (min+1) + " to worker " + (target+1) + ".");
        	job[k]=min;
        	wkr[k++]=target;
            rowVisited[target]=true;
            colVisited[min]=true;
        }
     }
     int tmp1,tmp2;
     for(int i=0;i<k-1;i++)
    	 for(int j=i+1;j<k;j++)
    		 if(job[j]<job[j])
    		 {
    			 tmp1 = job[i];
    			 tmp2 = wkr[i];
    			 job[i]=job[j];
    			 wkr[i]=wkr[j];
    			 job[j]=tmp1;
    			 wkr[j]=tmp2;
    		 }
     }
}
 public static class IntSumReducer2
 extends Reducer<Text,Text,Text,FloatWritable> {
String compositeString = new String();;
	String[] compositeStringArray = new String[5];
public void reduce(Text key, Iterable<Text> values,
                 Context context
                 ) throws IOException, InterruptedException {
		File file = new File("abc.txt");
		file.createNewFile();
		BufferedWriter bw = new BufferedWriter(new FileWriter("abc.txt"));
		int ct_m=0;
		boolean f1,f2,f3,f4,f5;
		f1=f2=f3=f4=f5=true;
		String[] list = new String[5];
		for (Text val : values) {
			compositeString = val.toString();
			compositeStringArray = compositeString.split("_");
			if(ct_m==id[wkr[0]]&&f1)
			{
				list[0]=compositeString;
				context.write(new Text(list[0]), new FloatWritable(0));
				f1=false;
			}
			else if(ct_m==id[wkr[1]]&&f2)
			{
				list[1]=compositeString;
				context.write(new Text(list[1]), new FloatWritable(1));
				f2=false;
			}
			else if(ct_m==id[wkr[2]]&&f3)
			{
				list[2]=compositeString;
				context.write(new Text(list[2]), new FloatWritable(2));
				f3=false;
			}
			else if(ct_m==id[wkr[3]]&&f4)
			{
				list[3]=compositeString;
				context.write(new Text(list[3]), new FloatWritable(3));
				f4=false;
			}
			else if(ct_m==id[wkr[4]]&&f5)
			{
				list[4]=compositeString;	
				context.write(new Text(list[4]), new FloatWritable(4));
				f5=false;
			}
			ct_m++;
		}
		for(int i=0;i<5;i++)
			context.write(new Text(" "), new FloatWritable(wkr[i]));
		for(int i=0;i<5;i++)
		{
			bw.write(list[i]);
			bw.newLine();
		}
		bw.close();
	}
}
 
  public static void main(String[] args) throws Exception {
    Configuration conf = new Configuration();
    Job job = Job.getInstance(conf, "word count");
    job.setJarByClass(PA.class);
    job.setMapperClass(TokenizerMapper.class);
    //job.setCombinerClass(IntSumReducer.class);
    job.setReducerClass(IntSumReducer.class);
    job.setOutputKeyClass(Text.class);
    job.setOutputValueClass(IntWritable.class);
    job.setMapOutputKeyClass(Text.class);
	job.setMapOutputValueClass(Text.class);
    job.setInputFormatClass(TextInputFormat.class);
    FileInputFormat.addInputPath(job, new Path(args[0]));
    FileOutputFormat.setOutputPath(job, new Path(args[1]));
    job.waitForCompletion(true);
    
    Configuration conf1 = new Configuration();
    Job job1 = Job.getInstance(conf1, "word count");
    job1.setJarByClass(PA.class);
    job1.setMapperClass(TokenizerMapper.class);
    //job.setCombinerClass(IntSumReducer.class);
    job1.setReducerClass(IntSumReducer1.class);
    job1.setOutputKeyClass(Text.class);
    job1.setOutputValueClass(FloatWritable.class);
    job1.setMapOutputKeyClass(Text.class);
	job1.setMapOutputValueClass(Text.class);
    job1.setInputFormatClass(TextInputFormat.class);
    FileInputFormat.addInputPath(job1, new Path(args[0]));
    FileOutputFormat.setOutputPath(job1, new Path(args[2]));
    job1.waitForCompletion(true);
    
    Configuration conf2 = new Configuration();
    Job job2 = Job.getInstance(conf2, "word count");
    job2.setJarByClass(PA.class);
    job2.setMapperClass(TokenizerMapper.class);
    //job.setCombinerClass(IntSumReducer.class);
    job2.setReducerClass(IntSumReducer2.class);
    job2.setOutputKeyClass(Text.class);
    job2.setOutputValueClass(FloatWritable.class);
    job2.setMapOutputKeyClass(Text.class);
	job2.setMapOutputValueClass(Text.class);
    job2.setInputFormatClass(TextInputFormat.class);
    FileInputFormat.addInputPath(job2, new Path(args[0]));
    FileOutputFormat.setOutputPath(job2, new Path(args[3]));
    System.exit(job2.waitForCompletion(true) ? 0 : 1);
  }
}
