/**
 * 
 */
package com.lesson.batch;

import org.springframework.stereotype.Component;

/**
 * @author zhailiang
 *
 */
@Component
public class BookTaskImpl implements BookTask {

	/* (non-Javadoc)
	 * @see com.lesson.batch.BookTask#doTask()
	 */
	@Override
	public void doTask() {
		System.out.println("task 开始运行");
	}

}
