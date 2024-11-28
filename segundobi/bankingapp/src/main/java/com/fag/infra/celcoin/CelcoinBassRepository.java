package com.fag.infra.celcoin;

import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import com.fag.domain.dto.BankslipDTO;
import com.fag.domain.repositories.IBassRepository;
import com.fag.infra.utils.JsonUtils;

public class CelcoinBassRepository implements IBassRepository{

        public static String genToken() throws Exception {
        HttpClient client = HttpClient.newHttpClient();
        URI uri = new URI("https://sandbox.openfinance.celcoin.dev/v5/token");
        String params = "client_id=" + URLEncoder.encode("41b44ab9a56440.teste.celcoinapi.v5", StandardCharsets.UTF_8)
                + "&grant_type=" + URLEncoder.encode("client_credentials", StandardCharsets.UTF_8)
                + "&client_secret=" + URLEncoder.encode(
                        "e9d15cde33024c1494de7480e69b7a18c09d7cd25a8446839b3be82a56a044a3", StandardCharsets.UTF_8);

        HttpRequest request = HttpRequest.newBuilder(uri)
                .header("Content-Type", "application/x-www-form-urlencoded")
                .POST(BodyPublishers.ofString(params))
                .build();

        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());

        return JsonUtils.getField(response.body(), "access_token");
    }

    @Override
    public String consultarBoleto(String linhaDigitavel) throws Exception {
        //descomente para fins de teste
        //linhaDigitavel = "23793381286008301352856000063307789840000150000";
        String token = genToken();
        String str = "{\r\n  \"barCode\": {\r\n    \"type\": 0,\r\n    \"digitable\": \"%s\"\r\n  }\r\n}".formatted(linhaDigitavel);

        System.out.println(str);

        URI uri = new URI("https://sandbox.openfinance.celcoin.dev/v5/transactions/billpayments/authorize");

        HttpRequest request = HttpRequest.newBuilder(uri)
                .header("Content-Type", "application/json")
                .header("authorization", "Bearer " + token)
                .POST(HttpRequest.BodyPublishers.ofString(str)) 
                .build();
        HttpClient client = HttpClient.newHttpClient();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println(response.toString());

        return response.body().toString();
    }

    public BankslipDTO retornaBankslipDTO(String barCode) throws Exception {
        String dadosDoBoleto = consultarBoleto(barCode);
        Map<String,Object> jsonMap = JsonUtils.srtToMap(dadosDoBoleto);
        BankslipDTO dto = new BankslipDTO();

        dto.setBarcode(barCode);
        dto.setTransactionId(jsonMap.get("transactionId").toString());
        dto.setValue(jsonMap.get("originalValue").toString());


        return dto;
    }


    @Override
    public String pagarBoleto(BankslipDTO dadosBoletoConsultado) throws Exception {
        
        
            String token = genToken();
            System.out.println("Pagando boleto " + dadosBoletoConsultado + " na celcoin");
            HttpClient client = HttpClient.newHttpClient();
            URI uri = new URI("https://sandbox.openfinance.celcoin.dev/v5/transactions/billpayments");
           String data =  "{\r\n  \"cpfCnpj\": \"24602516025\",\r\n  \"billData\": {\r\n    \"value\": "+ dadosBoletoConsultado.getValue()+",\r\n    \"originalValue\": "+dadosBoletoConsultado.getValue()+",\r\n    \"valueWithDiscount\": 0,\r\n    \"valueWithAdditional\": 0\r\n  },\r\n  \"barCode\": {\r\n    \"type\": 2,\r\n    \"digitable\": \""+ dadosBoletoConsultado.getBarcode() +"\",\r\n    \"barCode\": \"" + "" +"\"\r\n  },\r\n  \"dueDate\": \"2022-02-15T00:00:00.0000000-00:00\",\r\n  \"transactionIdAuthorize\": "+ dadosBoletoConsultado.getTransactionId() +",\r\n  \"externalNSU\": 1234,\r\n  \"externalTerminal\": \"t2\",\r\n  \"cpfcnpj\": \"51680002000100\"\r\n}";
           HttpRequest request = HttpRequest.newBuilder(uri)
           .header("Content-Type", "application/json")
           .header("Authorization", "Bearer " + token)
           .POST(BodyPublishers.ofString(data))
           .build();

           HttpResponse<String> response = client.send(request, BodyHandlers.ofString());


           return response.body();
    }

    @Override
    public String gerarQrCode(Double valorPix) throws Exception {
        //descomente Para fins de teste.
        //valorPix = 10.5;
        
        String token = genToken();
        HttpClient client = HttpClient.newHttpClient();
        URI uri = new URI("https://sandbox.openfinance.celcoin.dev/pix/v1/brcode/static");

        HttpRequest request = HttpRequest.newBuilder(uri)
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + token)
                .POST(BodyPublishers.ofString("{\n" + //
                        "  \"key\": \"testepix@celcoin.com.br\",\n" + //
                        "  \"amount\":"+valorPix+",\n" + //
                        "  \"merchant\": {\n" + //
                        "    \"postalCode\": \"01201005\",\n" + //
                        "    \"city\": \"Barueri\",\n" + //
                        "    \"merchantCategoryCode\": 0,\n" + //
                        "    \"name\": \"Celcoin\"\n" + //
                        "  }\n" + //
                        "}"))
                .build();

        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());

        return response.body();
    }

    
}
