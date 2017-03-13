package org.serge.core.queue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import org.serge.core.timer.TimerTask;

/**
 * 按创建时间超时
 * @author yangshj
 *
 * @param <K>
 * @param <T>
 */
public class TimeOutForCreateDateMap <K,T> extends AbstractMyMap<K, T> implements ITimeOutMap<K, T>{

	@Override
	protected TimerTask<Void, Void> createTimeOutTask(final long timeOut, final Map<K, AbstractMyMap<K, T>.Cache<T>> map) {
		return  new TimerTask<Void, Void>(){
//			@Override
//			protected long period() {
//				return timeOut/3;
//			}
			@Override
			protected Void doInBackground(Void... params) throws Throwable {
				while(!Thread.interrupted()){
			        final List<java.util.Map.Entry<K, TimeOutForCreateDateMap<K, T>.Cache<T>>> listData = new ArrayList<java.util.Map.Entry<K, TimeOutForCreateDateMap<K, T>.Cache<T>>>(map.entrySet());  
			        Collections.sort(listData, new Comparator<java.util.Map.Entry<K, TimeOutForCreateDateMap<K, T>.Cache<T>>>() {
						@Override
						public int compare(
								java.util.Map.Entry<K, AbstractMyMap<K, T>.Cache<T>> o1,
								java.util.Map.Entry<K, AbstractMyMap<K, T>.Cache<T>> o2) {
							Long l1 = Long.valueOf(o1.getValue().createTime);
							Long l2 = Long.valueOf(o2.getValue().createTime);
							return l1.compareTo(l2);
						}
					});
			        
			        for(int i=0; i<listData.size(); i++){
			        	long time = System.currentTimeMillis()-listData.get(i).getValue().createTime;
			        	if(time>=timeOut){
			        		final int index = i;
			        		new Thread(new Runnable() {
								@Override
								public void run() {
									handlerTimeOut(listData.get(index));
								}
							}).start();
			        		handlerTimeOut(listData.get(index));
			        	} else {
			        		long waitTime = timeOut-time;
			        		Thread.sleep(waitTime);
			        		break;
			        	}
			        }
				}
				return null;
			}
			@Override
			protected void onException(Throwable e) {
				e.printStackTrace();
			}
		};
	}

}
