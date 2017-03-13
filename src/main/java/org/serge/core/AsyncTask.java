package org.serge.core;

public abstract class AsyncTask<Params, Result> implements Runnable{
	protected final static IThreadPool btp = new SimpleThreadPool(getMaxThreadCount(), 10000);
	static{
//		System.out.println("start async task");
//		btp.setGroupName("async task");
		btp.setMaxWaitingQueue(10000000);
		btp.start();
	}
	protected static int getMaxThreadCount(){
		return 8*getAvailableProcessors();
//		return 50;
	}
	protected static int getAvailableProcessors(){
		System.out.println("available processors count:"+Runtime.getRuntime().availableProcessors());
		return Runtime.getRuntime().availableProcessors();
	}
	public static enum Status {
		/**
		 * 已完成
		 */
		FINISHED(), 
		/**
		 * 等待中
		 */
		PENDING(), 
		/**
		 * 执行中
		 */
		RUNNING();
	}

	Status status=Status.PENDING;
	private boolean cancel;
	private Params[] params;
	private Result result;
	private final long createDate = System.currentTimeMillis();

	public AsyncTask() {

	}
	public long getCreateDate() {
		return createDate;
	}
	public static int getTaskCount(){
		return btp.getTaskCount();
	}
	public static int getThreadCount(){
		return btp.getThreadCount();
	}
	/**
	 * 获取当前任务执行状态
	 * @return
	 */
	public final AsyncTask.Status getStatus() {
		return this.status;
	}

	protected abstract Result doInBackground(Params... params) throws Throwable;

	/**
	 * 任务执行之前（主线程执行方法）
	 */
	protected void onPreExecute() {

	}

	/**
	 * 执行结果处理
	 * @param result
	 */
	protected void onPostExecute(Result result) {

	}

	/**
	 * 取消任务
	 */
	protected void onCancelled() {
		btp.removeTask(this);
		this.cancel = true;
	}

	public final boolean isCancelled() {
		return this.cancel;
	}

	/**
	 * 取消任务
	 * @param mayInterruptIfRunning 允许中断正在执行任务
	 * @return
	 */
	public boolean cancel(boolean mayInterruptIfRunning) {
		if(mayInterruptIfRunning){
			onCancelled();
			return true;
		}else if(getStatus()==Status.PENDING){
			onCancelled();
			return true;
		}
		return false;
	}

//	/**
//	 * 获得执行结果，堵塞方法
//	 * @return
//	 * @throws java.lang.InterruptedException
//	 * @throws java.util.concurrent.ExecutionException
//	 */
//	public final Result get() throws java.lang.InterruptedException,
//			java.util.concurrent.ExecutionException {
//		return result;
//	}
//
//	/**
//	 * 获得执行结果，堵塞方法，等待指定时间
//	 * @param timeout
//	 * @param unit
//	 * @return
//	 * @throws java.lang.InterruptedException
//	 * @throws java.util.concurrent.ExecutionException
//	 * @throws java.util.concurrent.TimeoutException
//	 */
//	public final Result get(long timeout, java.util.concurrent.TimeUnit unit)
//			throws java.lang.InterruptedException,
//			java.util.concurrent.ExecutionException,
//			java.util.concurrent.TimeoutException {
//		return result;
//	}

	/**
	 * 执行任务
	 * @param params
	 * @return
	 */
	public AsyncTask<Params, Result> execute(Params... params) {
		setParams(params);
		try {
			btp.putTask(this);
		} catch (WaitingQueueToBigException e) {
			e.printStackTrace();
		}
		return this;
	}
	public Params[] getParams() {
		return params;
	}
	public void setParams(Params... params) {
		this.params = params;
	}
	final void setResult(Result result) {
		this.result = result;
	}
	final Result getResult() {
		return result;
	}

	/**
	 * 执行异常处理
	 * @param e
	 */
	protected void onException(Throwable e) {
		e.printStackTrace();
	}
	//=====================================
	@Override
	public final void run() {
		try{
			this.status = AsyncTask.Status.RUNNING;
			this.setResult(this.doInBackground(this.getParams()));
			this.onPostExecute(this.getResult());
		}catch(Throwable e){
			this.onException(e);
		}finally{
			this.status = AsyncTask.Status.FINISHED;
		}
	}
}

