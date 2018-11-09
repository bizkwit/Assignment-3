
public class Job{

	private String jobName; // name of that process/job
	private int jobLength; // needed CPU cycles for this job to terminate
	private int currentJobLength; // remaining length of the job at any given time
	private int jobPriority; // initial priority of this job
	private int finalPriority; // final priority of the job at termination time
	private long entryTime; // time this job entered the priority queue
	private long endTime; // when this job finally terminated
	private long waitTime; // total amount of wait time a process had to incure from the time it entered the queue until it terminates
	
	/**
	 * <h1> default constructor </h1>
	 * initiates the object and assign 0 to all
	 */
	public Job()
	{
		jobName = "";
		jobLength = 0;
		currentJobLength = 0;
		jobPriority = 0;
		finalPriority = 0;
		entryTime = 0;
		endTime = 0;
		waitTime = 0;
	}
	
	/**
	 * <h1> custom constructor </h1>
	 * assigns the given values to their places
	 * @param n job name	
	 * @param jL job length
	 * @param cJL current job length
	 * @param jP job priority
	 * @param fP final priority
	 * @param entryT entry time
	 * @param endT end time
	 * @param wT wait time
	 */
	public Job(String n, int jL, int cJL, int jP, int fP, long entryT, long endT, long wT)
	{
		jobName = n;
		jobLength = jL;
		currentJobLength = cJL;
		jobPriority = jP;
		finalPriority = fP;
		entryTime = entryT;
		endTime = endT;
		waitTime = wT;
	}
	
	/**
	 * <h1> gets job name </h1>
	 * @return jobName
	 */
	public String getJobName()
	{
		return jobName;
	}
	
	/**
	 * <h1>gets job length</h1>
	 * @return job length
	 */
	public int getJobLength()
	{
		return jobLength;
	}
	
	/**
	 * <h1> gets current job length </h1>
	 * @return current job length
	 */
	public int getCurrentJobLength()
	{
		return currentJobLength;
	}
	
	/**
	 * <h1> gets job priority </h1>
	 * @return job priority
	 */
	public int getJobPriority()
	{
		return jobPriority;
	}
	
	/**
	 * <h1> gets final job priority </h1>
	 * @return final priority
	 */
	public int getFinalPriority()
	{
		return finalPriority;
	}
	
	/**
	 * <h1> gets entry time </h1>
	 * @return entry time
	 */
	public long getEntryTime()
	{
		return entryTime;
	}
	
	/**
	 * <h1> gets end time </h1>
	 * @return end time
	 */
	public long getEndTime()
	{
		return endTime;
	}
	
	/**
	 * <h1> gets wait time </h1>
	 * @return wait time
	 */
	public long getWaitTime()
	{
		return waitTime;
	}
	
	/**
	 * <h1> sets job name </h1>
	 */
	public void setJobName(String n)
	{
		jobName = n;
	}
	
	/**
	 * <h1>sets job length</h1>
	 */
	public void setJobLength(int jL)
	{
		jobLength = jL;
	}
	
	/**
	 * <h1> sets current job length </h1>
	 */
	public void setCurrentJobLength(int cJL)
	{
		currentJobLength = cJL;
	}
	
	/**
	 * <h1> sets job priority </h1>
	 */
	public void setJobPriority(int jP)
	{
		jobPriority = jP;
	}
	
	/**
	 * <h1> sets final job priority </h1>
	 */
	public void setFinalPriority(int fP)
	{
		finalPriority = fP;
	}
	
	/**
	 * <h1> sets entry time </h1>
	 */
	public void setEntryTime(long entryT)
	{
		entryTime = entryT;
	}
	
	/**
	 * <h1> gets end time</h1>
	 */
	public void setEndTime(long endT)
	{
		endTime = endT;
	}
	
	/**
	 * <h1> sets wait time </h1>
	 */
	public void setWaitTime(long wT)
	{
		waitTime = wT;
	}
	
	public String toString()
	{
		return jobName+". Job length: "+jobLength+" cycles; Current remaing length: "
				+currentJobLength+" cycles; Initial priority: "+jobPriority;
	}
}
