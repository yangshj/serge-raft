package org.serge.core.queue;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.serge.core.timer.TimerTask;

/**
 * 按创建时间超时
 * @author yangshj
 *
 * @param <T>
 */
abstract class AbstractMyMap<K,T> implements ITimeOutMap<K, T>{
	private final Map<K, Cache<T>> map = new ConcurrentHashMap<K, AbstractMyMap<K,T>.Cache<T>>();
	private long timeOut;
	private volatile int index;
	private int maxSize;
	@Override
	public int size() {
		synchronized (map) {
			return map.size();
		}
	}

	@Override
	public boolean isEmpty() {
		synchronized (map) {
			return map.isEmpty();
		}
	}

	@Override
	public boolean containsKey(Object key) {
		synchronized (map) {
			return map.containsKey(key);
		}
	}

	@Override
	public boolean containsValue(Object value) {
		synchronized (map) {
			return map.containsValue(value);
		}
	}

	@Override
	public T get(Object key) {
		synchronized (map) {
			AbstractMyMap<K, T>.Cache<T> cache = map.get(key);
			if(null != cache){
				cache.lastTime = System.currentTimeMillis();
				return cache.v;
			}
			return null;
		}
	}

	@Override
	public T put(K key, T value) {
		while (!addIndex()) {
			removeRandom();
		}
		synchronized (map) {
			Cache<T> put = map.put(key, new Cache<T>(value));
			if(null != put){
				return put.v;
			}
			return null;
		}
	}
	
	private void removeRandom(){
		Iterator<K> iterator = map.keySet().iterator();
		if(iterator.hasNext()){
			K k = iterator.next();
//			map.remove(k);
			remove(k);
		}
	}

	@Override
	public T remove(Object key) {
		T t;
		synchronized (map) {
			Cache<T> cache = map.remove(key);
			if(null != cache){
				t = cache.v;
			}else{
				t = null;
			}
		}
		if(null != t){
			subIndex();
		}
		return t;
	}

	@Override
	public void putAll(Map<? extends K, ? extends T> m) {
		for(java.util.Map.Entry<? extends K, ? extends T> e : m.entrySet()){
			put(e.getKey(), e.getValue());
		}
	}

	@Override
	public void clear() {
		synchronized (map) {
			map.clear();
		}
		synchronized (this) {
			index = 0;
		}
	}

	@Override
	public Set<K> keySet() {
		return map.keySet();
	}

	@Override
	public Collection<T> values() {
		Collection<Cache<T>> values = map.values();
		List<T> list = new ArrayList<T>();
		for(Cache<T> c: values){
			list.add(c.v);
		}
		return list;
	}

	@Override
	public Set<java.util.Map.Entry<K, T>> entrySet() {
		Map<K, T> map1 = new HashMap<K, T>();
		for(java.util.Map.Entry<K, Cache<T>> e : map.entrySet()){
			map1.put(e.getKey(), e.getValue().v);
		}
		return map1.entrySet();
	}

	@Override
	public void setTimeOut(long timeOut) {
		this.timeOut = timeOut;
		//
		updateTimeOutTask();
	}
	private boolean addIndex(){
		synchronized (this) {
			if(maxSize>0 && index>=maxSize){
				return false;
			}
			index++;
			return true;
		}
	}
	private boolean subIndex(){
		synchronized (this) {
			if(index>0){
				index--;
				return true;
			}
			return false;
		}
	}
	//
	/**
	 * 设置最大长度限制
	 * <p>设置最大长度限制后，长度超限时，随机剔除原有队列数据，插入新数据
	 * @param maxSize maxSize>0有效，小于等于0表示不限制
	 */
	public void setMaxSize(int maxSize){
		this.maxSize = maxSize;
	}
	//
	private TimerTask<Void, Void> timerTask;
	private ITimeOutHandler<K,T> handler;
//	protected ITimeOutHandler<T> getTimeOutHandler() {
//		return handler;
//	}
	protected void handlerTimeOut(java.util.Map.Entry<K, TimeOutForCreateDateMap<K, T>.Cache<T>> entry){
		try{
			if(null != remove(entry.getKey()) && null != handler){
				handler.timeOut(entry.getKey(), entry.getValue().v);
			}
		}catch(Throwable e){
			e.printStackTrace();
		}
	}
	/**
	 * 创建定时处理任务
	 * @param timeOut
	 * @return
	 */
	protected abstract TimerTask<Void, Void> createTimeOutTask(long timeOut, final Map<K, Cache<T>> map);
	private void updateTimeOutTask() {
		if(null != timerTask){
			timerTask.cancel(true);
		}
		timerTask = createTimeOutTask(timeOut, map);
		timerTask.execute();
	}
	
	@Override
	public void setCustomTimeOutHandler(ITimeOutHandler<K,T> handler) {
		this.handler = handler;
	}

	protected class Cache<V>{
		protected final V v;
		protected final long createTime = System.currentTimeMillis();
		protected long lastTime = createTime;
		public Cache(V v) {
			this.v = v;
		}
		@SuppressWarnings("rawtypes")
		@Override
		public boolean equals(Object obj) {
			if(obj instanceof Cache){
				return v.equals(((Cache) obj).v);
			}
			return v.equals(obj);
		}
		@Override
		public int hashCode() {
			return v.hashCode();
		}
	}
}
