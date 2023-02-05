package com.aryan.cca.mp2.mapping;

import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;


@DynamoDbBean
public class SeedTracker {
	private String id;
	private String num;

	@DynamoDbPartitionKey
	public String getId() {
		return this.id;
	}
	public void setId(String id) {
		this.id = id;
	}

	public String getNum() {
		return this.num;
	}
	public void setNum(String num) {
		this.num = num;
	}
}
