package org.serge.core.timer;

import org.serge.core.AsyncTask;
import org.serge.core.IThreadPool;
import org.serge.core.SimpleThreadPool;
import org.serge.core.WaitingQueueToBigException;

/**
 * 相对延时定时任务。period为下次执行相对上一次时间间隔(有漏掉的风险）
 * @author yangshj
 *
 * @param <Params>
 * @param <Result>
 */
public abstract class TimerTask<Params, Result> extends AsyncTask<Params, Result>{
	protected final static IThreadPool btp = new SimpleThreadPool(8*getAvailableProcessors(), 30*60000l);
	static{
		System.out.println("start timer task");
//		btp.setName("timer task");
		btp.start();
	}
	/**
	 * 最后一次运行时间
	 */
	long lastRunTime=0;
	/**
	 * 运行次数
	 */
	long runTimes=0;
	/**
	 * 定时器(小余1表示不循环）
	 * <p>单位 ms
	 * 
	 * @return
	 */ 
	protected long period(){
		return -1;
	}
	/**
	 * 加载延迟时间（定时延迟，或初始化执行延迟）
	 * 
	 * @return
	 */
	protected long delay(){
		return 0;
	}
	/**
	 * 任务id
	 * @return
	 */
	protected long id(){
		return this.hashCode();
	}
	void executeNow(){
		setParams(getParams());
		try {
			btp.putTask(this);
		} catch (WaitingQueueToBigException e) {
			e.printStackTrace();
		}
//		return this;
//		super.execute(getParams());
	}
	@Override
	public TimerTask<Params, Result> execute(Params... params) {
		setParams(params);
		return execute();//super.execute(params);
	}
	public TimerTask<Params, Result> execute() {
		BaseTimer.instance().add(this);
		return this;
	}
	@Override
	protected void onCancelled() {
		BaseTimer.instance().remote(this);
		super.onCancelled();
	}
}
