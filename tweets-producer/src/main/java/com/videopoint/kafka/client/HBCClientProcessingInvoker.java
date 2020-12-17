package com.videopoint.kafka.client;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import com.twitter.hbc.ClientBuilder;
import com.twitter.hbc.core.Client;
import com.twitter.hbc.core.Constants;
import com.twitter.hbc.core.endpoint.StatusesFilterEndpoint;
import com.twitter.hbc.core.processor.StringDelimitedProcessor;
import com.twitter.hbc.httpclient.auth.Authentication;
import com.videopoint.kafka.twitter.client.model.ClientWithMessagesQueue;

public class HBCClientProcessingInvoker {
	
	public static ClientWithMessagesQueue getClientWithMessagesQueue(Authentication authentication, String[] tagsToTrack) {
		
		BlockingQueue<String> queue = new LinkedBlockingQueue<String>(Integer.MAX_VALUE);
		StatusesFilterEndpoint endpoint = new StatusesFilterEndpoint();
		
		List<String> trackTerms = new ArrayList<String>();
		trackTerms.add("twitterapi");
		trackTerms.addAll(Arrays.asList(tagsToTrack));
		endpoint.trackTerms(trackTerms);
			
		Client client = new ClientBuilder()
				.hosts(Constants.STREAM_HOST)
				.endpoint(endpoint)
				.authentication(authentication)
				.processor(new StringDelimitedProcessor(queue))
				.build();
			
		return new ClientWithMessagesQueue(queue, client);
	}
}
