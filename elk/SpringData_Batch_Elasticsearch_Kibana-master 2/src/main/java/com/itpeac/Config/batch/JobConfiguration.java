
package com.itpeac.Config.batch;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JdbcPagingItemReader;
import org.springframework.batch.item.database.Order;
import org.springframework.batch.item.database.support.MySqlPagingQueryProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.itpeac.domain.Customer;

@Configuration
public class JobConfiguration {

	@Autowired
	public JobBuilderFactory jobBuilderFactory;

	@Autowired
	public StepBuilderFactory stepBuilderFactory;

	@Autowired
	public DataSource dataSource;


	// No bat dau no nghi ngo - ai cung phai lam viec ca 


	@Bean
	public JdbcPagingItemReader<Customer> pagingItemReader() {
		JdbcPagingItemReader<Customer> reader = new JdbcPagingItemReader<>();

		reader.setDataSource(this.dataSource);

		
		reader.setFetchSize(1);
		reader.setRowMapper(new CustomerRowMapper());

		MySqlPagingQueryProvider queryProvider = new MySqlPagingQueryProvider();
		queryProvider.setSelectClause("id, firstname, lastname");
		queryProvider.setFromClause("from customer");

		Map<String, Order> sortKeys = new HashMap<>(1);

		sortKeys.put("id", Order.ASCENDING);

		queryProvider.setSortKeys(sortKeys);

		reader.setQueryProvider(queryProvider);

		return reader;

		// tre trau - bo tay 

		// no o rastila - no con bo thang day day - 
	}



	// Step to run 
		// 
	@Bean
	public Step importCustomereStep(StepBuilderFactory stepBuilderFactory, ItemWriter<Customer> CustomerItemWriter) {
		return stepBuilderFactory.get("importCustomereStep").<Customer, Customer>chunk(100).reader(pagingItemReader())
				.writer(CustomerItemWriter).build();
	}

	// Con nho nay - ngay nao cung nhai dream theater 

	@Bean
	public Job job(JobBuilderFactory jobBuilderFactory, Step importCustomereStep) {
		return jobBuilderFactory.get("job").incrementer(new RunIdIncrementer()).start(importCustomereStep).build();
	}
}

