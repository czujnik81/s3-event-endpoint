package org.rjug.aws;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

import lombok.Data;

@Data
@DynamoDBTable(tableName = "S3Events")
public class EventItem {

    @DynamoDBHashKey
    private String id;

    @DynamoDBAttribute(attributeName = "ts")
    private String timestamp;

    @DynamoDBAttribute(attributeName = "eventName")
    private String event;

    @DynamoDBAttribute(attributeName = "fineName")
    private String fileName;
}
