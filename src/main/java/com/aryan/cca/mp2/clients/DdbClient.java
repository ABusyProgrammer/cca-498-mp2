package com.aryan.cca.mp2.clients;

import java.security.InvalidParameterException;

import com.aryan.cca.mp2.mapping.SeedTracker;

import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.Key;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;
import software.amazon.awssdk.enhanced.dynamodb.model.GetItemEnhancedRequest;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.ResourceInUseException;
import software.amazon.awssdk.services.dynamodb.waiters.DynamoDbWaiter;

/*
 * curl -X POST http://mp2-546641303.us-east-2.elb.amazonaws.com/ -H 'Content-Type: application/json' -d '{"num": 100}'
 * curl -X GET http://mp2-546641303.us-east-2.elb.amazonaws.com/
 */
public class DdbClient {	
	private DynamoDbEnhancedClient ddbClient;
	private DynamoDbTable<SeedTracker> seedTrackerTable;

	private static final String DEFAULT_NUM = "0";


	public DdbClient() {
		ddbClient = DynamoDbEnhancedClient.builder()
				.dynamoDbClient(DynamoDbClient.builder()
					.region(Region.US_EAST_2)
					.credentialsProvider(
						StaticCredentialsProvider.create(
							AwsBasicCredentials.create(
								"", 
								""
							)))
					.build())
				.build();	
				
		seedTrackerTable = ddbClient.table("SeedTable", TableSchema.fromClass(SeedTracker.class));

		this.createTable();
	}

	public void createTable() {
		try {
			seedTrackerTable.createTable(builder -> builder
			.provisionedThroughput(throughput -> throughput
				.readCapacityUnits(10L)
				.writeCapacityUnits(10L)
				.build())
			);

			try (DynamoDbWaiter waiter = DynamoDbWaiter.create()) { // DynamoDbWaiter is Autocloseable
				waiter.waitUntilTableExists(builder -> builder
						.tableName("SeedTable")
						.build())
					.matched()
					.response()
					.orElseThrow(() -> new RuntimeException("Customer table was not created."));
			}
		} catch (ResourceInUseException e) {
			System.out.println("The table has already been created -> " + e.getMessage());
		}

		this.putNumber(DEFAULT_NUM);
	}

	public String getNumber() {
		return seedTrackerTable.getItem(
			(GetItemEnhancedRequest.Builder requestBuilder) -> 
				requestBuilder.key(Key.builder().partitionValue("seedNumber").build()))
			.getNum();
	}
	
	public void putNumber(String num) {
		if (!isNum(num)) {
			throw new InvalidParameterException("The input passed in must be numerical");
		}

		SeedTracker seed = new SeedTracker();
		seed.setId("seedNumber");
		seed.setNum(num);

		seedTrackerTable.putItem(seed);
	}

	public Boolean isNum(String num) {
		if (num == null) {
			return Boolean.FALSE;
		}

		try {
			Integer.parseInt(num);
		} catch (NumberFormatException nfe) {
			return Boolean.FALSE;
		}

		return Boolean.TRUE;
	}
}
