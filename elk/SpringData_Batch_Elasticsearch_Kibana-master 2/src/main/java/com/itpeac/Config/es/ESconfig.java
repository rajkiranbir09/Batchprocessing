package com.itpeac.Config.es;

import java.net.InetAddress;

import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

@Configuration
@EnableElasticsearchRepositories(basePackages = "RepositoryEs") // enable 
public class ESconfig {

	// ESConfig 

	@Bean
	public TransportClient client5() throws Exception {

		// Settings settings = ImmutableSettings.settingsBuilder()
        // .put("cluster.name", "elasticsearch_hien")
        // .put("client.transport.ignore_cluster_name", false)
        // .put("node.client", true)
        // .put("client.transport.sniff", true)
        // .build();




		TransportClient client = new PreBuiltTransportClient(Settings.EMPTY)
				.addTransportAddress(new TransportAddress(InetAddress.getByName("localhost"), 9300));


		// TransportClient client = new TransportClient(settings).addTransportAddress(new  InetSocketTransportAddress("localhost", 9300)); 

				// Set cluster name 


				



		return client;

	}

	@Bean
	public ElasticsearchOperations elasticsearchTemplate2() throws Exception {
		return new ElasticsearchTemplate(client5()); // elasticsearchTemplate - 

	}
	

}
