package org.serge.core.queue;

import java.util.Map;

/**
 * 超时清除Map
 * @author yangshj
 *
 * @param <K>
 * @param <V>
 */
public interface ITimeOutMap<K,V> extends Map<K, V>, ITimeOut,IFixedMaxSize{
	/**
	 * 设置最大长度限制
	 * <p>设置最大长度限制后，长度超限时，随机剔除原有队列数据，插入新数据
	 * @param maxSize maxSize>0有效，小于等于0表示不限制
	 */
	public void setMaxSize(int maxSize);
	/**
	 * 设置自定义超时处理器
	 * @param handler
	 */
	public void setCustomTimeOutHandler(ITimeOutHandler<K,V> handler);
	//===================================
	/**
	 * 超时删除回调器
	 * @author MODI
	 *
	 * @param <T>
	 */
	public interface ITimeOutHandler<K,V> {
		/**
		 * 超时对象处理(扩展处理，执行此方法时，对象已移出）
		 * @param key
		 * @param value
		 */
		void timeOut(K key, V value);
	}

}
