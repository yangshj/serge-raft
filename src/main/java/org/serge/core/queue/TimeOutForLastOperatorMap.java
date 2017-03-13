package org.serge.core.queue;

import java.util.Iterator;
import java.util.Map;

import org.serge.core.timer.TimerTask;

/**
 * 按最后超时时间超时
 * @author yangshj
 */
public class TimeOutForLastOperatorMap <K,T> extends AbstractMyMap<K, T> implements ITimeOutMap<K, T>{

	@Override
	protected TimerTask<Void, Void> createTimeOutTask(final long timeOut, final Map<K, AbstractMyMap<K, T>.Cache<T>> map) {
		return  new TimerTask<Void, Void>(){
			@Override
			protected long period() {
				return timeOut/3;
			}
			@Override
			protected Void doInBackground(Void... params) throws Throwable {
				Iterator<java.util.Map.Entry<K, TimeOutForCreateDateMap<K, T>.Cache<T>>> iterator = map.entrySet().iterator();
				while(iterator.hasNext()){
					java.util.Map.Entry<K, TimeOutForCreateDateMap<K, T>.Cache<T>> entry = iterator.next();
					if(System.currentTimeMillis()-entry.getValue().lastTime>=timeOut){
						handlerTimeOut(entry);
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
