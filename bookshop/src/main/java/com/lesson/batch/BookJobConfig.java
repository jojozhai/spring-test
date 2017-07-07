/**
 * 
 */
package com.lesson.batch;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.step.tasklet.MethodInvokingTaskletAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author zhailiang
 *
 */
@Configuration
@EnableBatchProcessing
public class BookJobConfig {
	
	@Autowired
	private JobBuilderFactory jobs;
	
	@Autowired
	private StepBuilderFactory steps;
	
	@Autowired
	private BookTask bookTask;
	
	@Bean
	public Job job(Step step1) {
		return jobs.get("bookJob").start(step1).build();
	}
	
	@Bean
	public Step step1() {
		MethodInvokingTaskletAdapter adapter = new MethodInvokingTaskletAdapter();
		adapter.setTargetObject(bookTask);
		adapter.setTargetMethod("doTask");
		return steps.get("bookJobStep1").tasklet(adapter).build();
	}

}
