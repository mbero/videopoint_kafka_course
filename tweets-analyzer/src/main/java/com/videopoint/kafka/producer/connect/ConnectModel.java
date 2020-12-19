package com.videopoint.kafka.producer.connect;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

public class ConnectModel {
	private Schema schema;
	private Tweet payload;
	private ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
	
	public Schema getSchema() {
		return schema;
	}

	public void setSchema(Schema schema) {
		this.schema = schema;
	}

	public Tweet getPayload() {
		return payload;
	}

	public void setPayload(Tweet payload) {
		this.payload = payload;
	}
	
	@Override
	public String toString() {
		String json = null;
		try {
			json = ow.writeValueAsString(this);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return json;
	}


}
