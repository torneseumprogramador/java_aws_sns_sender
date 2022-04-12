package br.com.sns_aws.services;

import software.amazon.awssdk.auth.credentials.AwsCredentials;
import software.amazon.awssdk.auth.credentials.AwsCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.sns.SnsClient;
import software.amazon.awssdk.services.sns.model.PublishRequest;
import software.amazon.awssdk.services.sns.model.PublishResponse;

public class SNSService {
    private static String topicArn = "arn:aws:sns:us-east-1:763818760783:poc-sns";

    public static void sendMessage(String message){
        AwsCredentialsProvider credentialsProvider = new AwsCredentialsProvider() {
            @Override
            public AwsCredentials resolveCredentials() {
                return new AwsCredentials() {
                    @Override
                    public String accessKeyId() {
                        return System.getenv("AWS_ACCESS_KEY");
                    }
        
                    @Override
                    public String secretAccessKey() {
                        return System.getenv("AWS_SECRET_KEY");
                    }
                };
            }
        };

        SnsClient snsClient = SnsClient.builder()
                .region(Region.US_EAST_1)
                .credentialsProvider(credentialsProvider)
                .build();


        PublishRequest request = PublishRequest.builder()
                .messageStructure("Um envio de SNS")
                .message(message)
                .topicArn(topicArn)
                .build();

        PublishResponse result = snsClient.publish(request);
        
        System.out.println("Message sent, Status was " + result.sdkHttpResponse().statusCode());
        
        snsClient.close();
    }
}