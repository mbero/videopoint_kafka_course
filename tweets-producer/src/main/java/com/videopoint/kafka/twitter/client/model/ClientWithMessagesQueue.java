package com.videopoint.kafka.twitter.client.model;

import java.util.concurrent.BlockingQueue;

import com.twitter.hbc.core.Client;

public class ClientWithMessagesQueue {

	private BlockingQueue<String> messagesQueue;
	private Client tweeterHBCClient;

	public BlockingQueue<String> getMessagesQueue() {
		return messagesQueue;
	}

	public void setMessagesQueue(BlockingQueue<String> messagesQueue) {
		this.messagesQueue = messagesQueue;
	}

	public Client getTweeterHBCClient() {
		return tweeterHBCClient;
	}

	public void setTweeterHBCClient(Client tweeterHBCClient) {
		this.tweeterHBCClient = tweeterHBCClient;
	}

	public ClientWithMessagesQueue(BlockingQueue<String> messagesQueue, Client tweeterHBCClient) {
		super();
		this.messagesQueue = messagesQueue;
		this.tweeterHBCClient = tweeterHBCClient;
	}

}
