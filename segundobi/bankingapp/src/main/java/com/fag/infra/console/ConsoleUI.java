package com.fag.infra.console;

import java.util.Scanner;

import com.fag.domain.dto.BankslipDTO;
import com.fag.domain.dto.LoginDTO;
import com.fag.domain.dto.UserAccountDTO;
import com.fag.domain.repositories.IUserInterface;
import com.fag.infra.celcoin.CelcoinBassRepository;

public class ConsoleUI implements IUserInterface {

    private Scanner inpuScanner = new Scanner(System.in);

    @Override
    public Integer showInitialScreenMenu() {
        System.out.println("Bem vindo ao BankFag!");
        System.out.println("[1] Realizar login");
        System.out.println("[2] Criar conta");
        System.out.println("[3] Sair");

        return inpuScanner.nextInt();
    }

    @Override
    public Integer showHomeMenu(String userName) {
    
        
        System.out.println("Olá " + userName + "! O que deseja fazer hoje?\n");
        System.out.println("[1] Consultar boleto\n");
        System.out.println("[2] Realizar pagamento boleto\n");
        System.out.println("[3] Gerar QR Code PIX\n");
        System.out.println("[4] SAIR");

        return inpuScanner.nextInt();
    }

   
    @Override
    public LoginDTO getloginData() {
        LoginDTO data = new LoginDTO();
        inpuScanner.nextLine();


        System.out.println("Informe seu documento: ");
        String document = inpuScanner.nextLine();
        
        System.out.println("Informe sua senha: ");
        String password = inpuScanner.nextLine();

        data.setDocument(document);
        data.setPassword(password);

        return data;
    }

    @Override
    public UserAccountDTO getRegisterUser() {
       UserAccountDTO userData = new UserAccountDTO();
       inpuScanner.nextLine();


       System.out.println("Informe seu documento: ");
       String document = inpuScanner.nextLine();

       System.out.println("Informe seu nome: ");
       String name = inpuScanner.nextLine();
       
       System.out.println("Informe seu email: ");
       String email = inpuScanner.nextLine();
       System.out.println("Informe sua senha: ");
       String password = inpuScanner.nextLine();

       userData.setDocument(document);
       userData.setPassword(password);
       userData.setName(name);
       userData.setEmail(email);
       
       return userData;
    }

    @Override
    public void showErrorMsg(String msg) {
        System.out.println("ERRO: " + msg);
    }

    @Override
    public void showExitMessage() {

        System.out.println("Obrigado por estar utilizando nossas aplicações");
       
    }

    @Override
    public String getBarcode() {
        System.out.println("Insira o código de barras:");
        inpuScanner.nextLine();
        String barcode = inpuScanner.nextLine();

        return barcode;
    }

    @Override
    public BankslipDTO getPaymentBankslipInfo() throws Exception {
        
        System.out.println("Insira o código de barras:");
        inpuScanner.nextLine();
        String barcode = inpuScanner.nextLine();
        CelcoinBassRepository getdata = new CelcoinBassRepository();

        BankslipDTO bankslipDTO = getdata.retornaBankslipDTO(barcode);

        System.out.println(bankslipDTO.getBarcode());
        System.out.println(bankslipDTO.getTransactionId());

        return bankslipDTO;
        
        /*System.out.println("Insira o identificador de pagamento:");
        String id = inpuScanner.nextLine();

        bankslipDTO.setBarcode(barcode);
        bankslipDTO.setTransactionId(id);

        return bankslipDTO;*/
    }

    @Override
    public void showBankslipData(String data) {
        System.out.println("Dados do boleto: " + data);
    }

    @Override
    public Double getPixData() {
        System.out.println("Insira valor do PIX:");
        Double amount = inpuScanner.nextDouble();

        return amount;
    }

    @Override
    public void showPixData(String data) {
    System.out.println("Dados do PIX: " + data);
    }
   
}
