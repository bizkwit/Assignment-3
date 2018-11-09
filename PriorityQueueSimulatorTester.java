import java.io.*;
import java.util.Random;

public class PriorityQueueSimulatorTester {

	public static void main(String[] args) {
		BufferedWriter writer; //file writer
		Random random = new Random();	//random generator
		int completed=0;
		
		int maxNumberOfJobs=10;	//N input for simulator
		
		int systemTime=0;	//current system time
		Job[] jobsInputArray= new Job[maxNumberOfJobs];	//array holding jobs
		int i,j;	//loop variables
		
		long avgWait;	//average waiting time
		long totalPriorityChanges; //priority changes
		long actualSysTime; //actual system time needed to execute all jobs
		
		String nameSet="JOB_";//job name
		int jobLength=0;//currentJobLength
		int jobPriority=0;//finalPriority
		
		//JOB CREATION
		for(i=0;i<maxNumberOfJobs;i++) {
			jobLength=random.nextInt(70)+1;
			jobPriority=random.nextInt(40)+1;
			jobsInputArray[i]= new Job(	nameSet+(i+1)//name
										, jobLength	//job length
										, jobLength	//current length
										, jobPriority	//job priority
										, jobPriority	//final priority
										, 0	//entry time
										, 0	//end time
										, 0);	//wait time
		}
		
		//ARRAY HEAP IMPLEMENTATION
		/*
		//JOB INSERTION
		System.out.println("Commencing Queue Loading, Array Heap Implementation");
		ArrayHeap<Integer, Job> heapQueue = new ArrayHeap<Integer, Job>(maxNumberOfJobs);
		for(j=0;j<maxNumberOfJobs;j++) {
			Job current=jobsInputArray[j];
			int currentKey=jobsInputArray[j].getJobPriority();
			System.out.println("Adding Job "+current.toString());
			heapQueue.insert(currentKey, current);
			systemTime+=1;
		}
		System.out.println("Queue Loading Complete..\n");
		
		//PRINT QUEUE
		System.out.println("Queue Contents:");
		while(heapQueue.isEmpty()!=true) {
			System.out.println(heapQueue.getMinVal().toString());
		}
		System.out.println("\n");
		
		//JOB PROCESSING
		System.out.println("Commenicing Queue Processing..");
		while(heapQueue.isEmpty()!=true){
			Job processing=(Job)heapQueue.getMinVal();
			System.out.println("Processing "+processing.toString());

			systemTime+=1;
			int currentJob=processing.getCurrentJobLength();
			currentJob-=1;
			processing.setCurrentJobLength(currentJob);
			
			//Executions remaining
			if(processing.getCurrentJobLength()>0) {
				heapQueue.insert(processing.getJobPriority(), processing);
			}
			
			//Job Complete
			else {
				processing.setEndTime(systemTime);	//sets end time
				jobsInputArray[(int)processing.getEntryTime()]=processing;	//returns completed to array
				System.out.println("Job Complete..\n");
				completed++;
			}
			
			if(completed%30==0) {
				
				
			}
			
		}
		System.out.println("\nCPU is free...\nJobs Completed: "+completed);
		*/
		
		//UNSORTED LIST IMPLEMENTATION
		//JOB INSERTION
		System.out.println("Commencing Queue Loading, Unsorted List Implementation");
		UnsortedListPriorityQueue<Integer, Job> list=new UnsortedListPriorityQueue<Integer, Job>();
		for(j=0;j<maxNumberOfJobs;j++) {
			System.out.println("Adding :"+jobsInputArray[j].toString());
			list.insert(jobsInputArray[j].getJobPriority(),jobsInputArray[j]);
			systemTime+=1;
		}
		System.out.println("Queue Loading Complete..\n");
		
		/*//PRINT QUEUE
		System.out.println("Queue Contents:");
		while(list.isEmpty()!=true) {
			System.out.println(list.removeMin().toString());
		}
		System.out.println("\n");
		*/
		
		
		//JOB PROCESSING
		System.out.println("Commencing Queue Processing..");
		j=0;
		while(list.isEmpty()!=true){
			Job current=list.removeMin().getData();
			System.out.println("Current Job: "+current.toString()+"\n");
			
			int length=current.getCurrentJobLength()-1;
			int priority=current.getFinalPriority();
			current.setCurrentJobLength(length);
			list.insert(priority, current);
			
			if(length<=0) {
				list.removeMin();
				System.out.println("\n***Job Complete***\n");

			}
			System.out.println(list.toString()+"\n");
		}
		System.out.println("\nCPU is free...\nJobs Completed: "+completed);
		
	}

}

