package br.com.sns_aws;

import java.time.LocalDate;

import br.com.sns_aws.services.SNSService;

public class App 
{
    public static void main( String[] args )
    {
        SNSService.sendMessage("Uma mensagem - " + LocalDate.now());
    }
}
