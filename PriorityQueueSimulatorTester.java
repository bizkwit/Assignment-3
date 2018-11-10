import java.io.*;
import java.util.Random;

public class PriorityQueueSimulatorTester {

	public static void main(String[] args) {
		//OUTPUT
		BufferedWriter writer; //file writer
		try {
			writer=new BufferedWriter(new FileWriter("SimulatorPerformanceResults.txt"));
			
			//N
			int maxNumberOfJobs=1000;	//N input for simulator
			
			//RANDOM GEN
			Random random = new Random();	//random generator
			int completed=0;
			
			//CPU DATA
			int systemTime=0;	//current system time
			Job[] jobsInputArray= new Job[maxNumberOfJobs];	//array holding jobs
			int i,j;	//loop variables
			long avgWait=0;	//average waiting time
			long totalPriorityChanges=0; //priority changes
			
			long start=0;
			long end=0;
			long actualSysTime=0; //actual system time needed to execute all jobs
			
			String nameSet="JOB_";//job name
			int jobLength=0;//currentJobLength
			int jobPriority=0;//finalPriority
			

			///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
			//											ARRAY HEAP IMPLEMENTATION								     		 //
			///////////////////////////////////////////////////////////////////////////////////////////////////////////////////			
			
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
			
			//JOB INSERTION
			start=System.nanoTime();
			System.out.println("Commencing Queue Loading, Array Heap Implementation");
			ArrayHeap<Integer, Job> heapQueue = new ArrayHeap<Integer, Job>(maxNumberOfJobs);
			for(j=0;j<maxNumberOfJobs;j++) {
				Job current=jobsInputArray[j];
				int currentKey=jobsInputArray[j].getJobPriority();
				System.out.println("Adding Job "+current.toString());
				heapQueue.insert(currentKey, current);
				systemTime++;
			}
			System.out.println("Queue Loading Complete..\n");
			
			//JOB PROCESSING
			System.out.println("Commenicing Queue Processing..");
			boolean starveCheck=false;
			while(heapQueue.isEmpty()!=true){
				Job processing=(Job)heapQueue.getMinVal();
				System.out.println("Processing "+processing.toString());
	
				systemTime+=1;
				int currentJob=processing.getCurrentJobLength();
				currentJob-=1;
				processing.setCurrentJobLength(currentJob);
				
				//STARVATION CHECK
				if((completed%30==0)&&(starveCheck==true)) {
					heapQueue.modLast();
					systemTime++;
					totalPriorityChanges++;
				}
				
				//REINSERTION
				if(processing.getCurrentJobLength()>0) {
					heapQueue.insert(processing.getJobPriority(), processing);
					systemTime++;
					starveCheck=false;
				}
				
				//JOB COMPLETE
				else {
					processing.setEndTime(systemTime);
					processing.setWaitTime(systemTime-processing.getEntryTime()-processing.getJobLength());
					avgWait=avgWait+processing.getEndTime();
					completed++;
					starveCheck=true;
					System.out.println("Job Complete...\n"
										+processing.toString()+"\nEnd time: "
										+processing.getEndTime()+"\nWait time :"
										+processing.getWaitTime()+"\n");
				}
				
			}
			end=System.nanoTime();
			
			//AVG WAITING TIME AND ACTUAL TIME
			avgWait=avgWait/maxNumberOfJobs;
			actualSysTime=end-start;
			
			//TERMINATION MESSAGE
			System.out.println("CPU is free, Queue is empty..."
								+ "\nTotal jobs to execute: "+maxNumberOfJobs+" jobs"
								+ "\nCurrent system time (cycles): "+systemTime
								+"\nTotal number of jobs executed: "+completed+" jobs"
								+"\nAverage process waiting time: "+avgWait+" cycles"
								+"\nTotal number of priority changes: "+totalPriorityChanges
								+"\nActual system time needed to execute all jobs (nano) : "+actualSysTime);
			
			writer.append("Array Heap Implementation with N: "+maxNumberOfJobs+"\n");
			writer.newLine();
			writer.append("CPU is free, Queue is empty...");
			writer.newLine();
			writer.append("Total jobs to execute: "+maxNumberOfJobs+" jobs");
			writer.newLine();
			writer.append("Total jobs to execute: "+maxNumberOfJobs+" jobs");
			writer.newLine();
			writer.append("Current system time (cycles): "+systemTime);
			writer.newLine();
			writer.append("Total number of jobs executed: "+completed+" jobs");
			writer.newLine();
			writer.append("Average process waiting time: "+avgWait+" cycles");
			writer.newLine();
			writer.append("Total number of priority changes: "+totalPriorityChanges);
			writer.newLine();
			writer.append("Actual system time needed to execute all jobs (nano) : "+actualSysTime);
			writer.newLine();
			writer.newLine();
			
			System.out.println("\nCPU is free...\nJobs Completed: "+completed);
			
			///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
			//											UNSORTED LIST IMPLEMENTATION										 //
			///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
			
			//RESET
			completed=0;
			systemTime=0;
			avgWait=0;
			totalPriorityChanges=0;
			jobLength=0;
			jobPriority=0;
			
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
			
			//JOB INSERTION
			start=System.nanoTime();
			System.out.println("Commencing Queue Loading, Unsorted List Implementation");
			UnsortedListPriorityQueue<Integer, Job> list=new UnsortedListPriorityQueue<Integer, Job>();
			for(j=0;j<maxNumberOfJobs;j++) {
				System.out.println("Adding :"+jobsInputArray[j].toString());
				list.insert(jobsInputArray[j].getJobPriority(),jobsInputArray[j]);
				systemTime++;
			}
			System.out.println("Queue Loading Complete..\n");
						
			//JOB PROCESSING
			System.out.println("Commencing Queue Processing..");
			starveCheck=false;
			while(list.isEmpty()!=true){
				
				//DEALS WITH STARVED JOBS ONCE FIRST JOB HAS BEEN COMPLETED
				if((completed%30==0)&&(starveCheck==true)) {
					list.getLastPriority().getData().setFinalPriority(1);
					totalPriorityChanges++;
					systemTime++;
				}
				
				//NORMAL JOB PROCESSING
				System.out.println("New CPU Iteration:");
				Job current=new Job(list.min().getData());
				System.out.println("Current Job: "+current.toString()+"\n");
				int length=current.getCurrentJobLength()-1;
				int priority=current.getFinalPriority();
				current.setCurrentJobLength(length);
				System.out.println("Removing: "+list.removeMin().toString()+"\n");
				
				//COMPLETED JOB
				if((current.getCurrentJobLength()==0)) {
					current.setEndTime(systemTime);
					current.setWaitTime(systemTime-current.getEntryTime()-current.getJobLength());
					avgWait=current.getEndTime();
					completed++;
					starveCheck=true;
					System.out.println("Job Complete...\n"
										+current.toString()+"\nEnd time: "
										+current.getEndTime()+"\nWait time :"
										+current.getWaitTime()+"\n");
				}
				
				//INSERTS JOB BACK TO QUEUE IF THERES JOB LEFT TO EXECUTE
				if(current.getCurrentJobLength()!=0) {
					System.out.println("Inserting :"+list.insert(priority, current).toString()+"\n");
					systemTime++;
					starveCheck=false;
				}
				
				//PRINTS QUEUE STATUS
				/*if(list.toString().equals("null queue")) {
					break;
				}*/
			}
			end=System.nanoTime();
			
			//AVG WAITING TIME AND ACTUAL TIME
			avgWait=avgWait/maxNumberOfJobs;
			actualSysTime=end-start;
			
			//TERMINATION MESSAGE
			System.out.println("CPU is free, Queue is empty..."
								+ "\nTotal jobs to execute: "+maxNumberOfJobs+" jobs"
								+ "\nCurrent system time (cycles): "+systemTime
								+"\nTotal number of jobs executed: "+completed+" jobs"
								+"\nAverage process waiting time: "+avgWait+" cycles"
								+"\nTotal number of priority changes: "+totalPriorityChanges
								+"\nActual system time needed to execute all jobs (nano) : "+actualSysTime);
			
			writer.append("Unsorted Priority List Implementation with N: "+maxNumberOfJobs+"\n");
			writer.newLine();
			writer.append("CPU is free, Queue is empty...");
			writer.newLine();
			writer.append("Total jobs to execute: "+maxNumberOfJobs+" jobs");
			writer.newLine();
			writer.append("Total jobs to execute: "+maxNumberOfJobs+" jobs");
			writer.newLine();
			writer.append("Current system time (cycles): "+systemTime);
			writer.newLine();
			writer.append("Total number of jobs executed: "+completed+" jobs");
			writer.newLine();
			writer.append("Average process waiting time: "+avgWait+" cycles");
			writer.newLine();
			writer.append("Total number of priority changes: "+totalPriorityChanges);
			writer.newLine();
			writer.append("Actual system time needed to execute all jobs (nano) : "+actualSysTime);
			writer.close();
			
		}
		catch (IOException e1){
			e1.printStackTrace();
		}
	}
}

