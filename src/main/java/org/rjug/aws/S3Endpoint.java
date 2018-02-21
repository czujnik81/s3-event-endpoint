package org.rjug.aws;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import java.util.List;

public class S3Endpoint implements RequestHandler<Void, List<EventItem>> {
    AWSCredentialsProvider credentialsProvider;
    DynamoDBMapper mapper;

    public List<EventItem> handleRequest(Void aVoid, Context context) {
        return mapper.scan(EventItem.class, new DynamoDBScanExpression());
    }

    public S3Endpoint() {
        credentialsProvider = new AWSStaticCredentialsProvider(new BasicAWSCredentials(
            System.getenv("ACC"),
            System.getenv("SEC")
        ));

        mapper = new DynamoDBMapper(AmazonDynamoDBClientBuilder.standard()
        .withRegion(Regions.US_EAST_1)
        .withCredentials(credentialsProvider)
        .build());
    }
}
