package org.serge.core.queue;

public interface IFixedMaxSize {
	/**
	 * 设置最大长度限制
	 * @param maxSize maxSize>0有效，小于等于0表示不限制
	 */
	public void setMaxSize(int maxSize);
}

