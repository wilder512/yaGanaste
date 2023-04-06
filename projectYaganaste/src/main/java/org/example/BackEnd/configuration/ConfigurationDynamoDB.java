package org.example.BackEnd.configuration;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBAsyncClientBuilder;
import org.socialsignin.spring.data.dynamodb.repository.config.EnableDynamoDBRepositories;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableDynamoDBRepositories(basePackages = "org.example.BackEnd.Repository")
/*
this tag @EnableDynamoDBRepositories we can to specify package for trcae all repositories
 */
public class ConfigurationDynamoDB {
    @Value("${cloud.aws.dynamodb.endpoint}")
    private String endPoint;
    @Value("${cloud.aws.dynamodb.region}")
    private String region;
    @Value("${cloud.aws.credentials.access-key}")
    private String accesKey;
    @Value("${cloud.aws.credentials.secret-key}")
    private String secretKey;

    @Bean
    public AmazonDynamoDB amazonDynamoDB()
    {
        AmazonDynamoDB amazonDynamoDB = AmazonDynamoDBAsyncClientBuilder
                .standard()
                .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(endPoint,region))
                .withCredentials(awsCredentialsProvider())
                .build();
        return  amazonDynamoDB;
    }
    @Bean
    public AWSCredentialsProvider awsCredentialsProvider(){
        return  new AWSStaticCredentialsProvider( new BasicAWSCredentials(accesKey,secretKey));
    }

}
