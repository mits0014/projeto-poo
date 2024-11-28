package com.fag.service;

import java.time.LocalDateTime;
import java.util.UUID;

import com.fag.domain.dto.BankslipDTO;
import com.fag.domain.dto.LoginDTO;
import com.fag.domain.dto.UserAccountDTO;
import com.fag.domain.repositories.IBassRepository;
import com.fag.domain.repositories.IUserInterface;
import com.fag.domain.repositories.IUserRepository;
import com.fag.infra.celcoin.CelcoinBassRepository;

public class BankingService {

     private IUserInterface gui;

     private Integer account = 1;

     private IUserRepository userDb;

     private IBassRepository iBassRepository;



     public BankingService(IUserInterface gui, IUserRepository userDb, IBassRepository ibassRepository){
        this.gui = gui;
        this.userDb = userDb;
        this.iBassRepository = ibassRepository;
     }

    public Integer showMenu(){
        return gui.showInitialScreenMenu();
    }

    public LoginDTO getLoginDTO(){
        return gui.getloginData();
    }

   
    public UserAccountDTO getRegisterUserDTO(){
        UserAccountDTO userAccountDTO = gui.getRegisterUser();
        String uuid = UUID.randomUUID().toString();

        userAccountDTO.setAccountNumber(account.toString());
        userAccountDTO.setCreatedAt(LocalDateTime.now());  

        userAccountDTO.setId(uuid);
        userAccountDTO.setAccountNumber(account.toString());
        userAccountDTO.setCreatedAt(LocalDateTime.now());

        account++;

        return userAccountDTO;
    }

    public void showExitMessage(){
        gui.showExitMessage();
    }


    public UserAccountDTO createUserAccountDTO(UserAccountDTO user){
        return userDb.createUser(user);
    }

    public void login(UserAccountDTO user) throws Exception{
        CelcoinBassRepository bassRepo = new CelcoinBassRepository();
        while (true) {
        Integer option = gui.showHomeMenu(user.getName());

        switch (option) {
            case 1:
                System.out.println("COnsulte o boleto");
                String barcode = gui.getBarcode();

                
                String response = bassRepo.consultarBoleto(barcode);

                gui.showBankslipData(response);
                break;
            case 2:

                System.out.println("pagar boleto");
                BankslipDTO data = gui.getPaymentBankslipInfo();
                String respotaBoletoPago = bassRepo.pagarBoleto(data);

                gui.showBankslipData(respotaBoletoPago);
                break;
            case 3:
                Double amount = gui.getPixData();

                String pixResponse = bassRepo.gerarQrCode(amount);

                gui.showPixData(pixResponse);

                break;
            case 4:
                gui.showExitMessage();
                return;
        }
    }

    }

    public UserAccountDTO findUser(LoginDTO loginDTO){
            UserAccountDTO user = userDb.findUserBy(loginDTO.getDocument());

            if (user == null) {
                gui.showErrorMsg("Usuario não encontrado");
                return null;
            }

            if (!user.getPassword().equals(loginDTO.getPassword())) {
                gui.showErrorMsg("Senha errada");
                return null;
            }
            return user;

    }

  /*   public void execute(UserAccountDTO dto) {
       Integer option = gui.showHomeMenu(dto.getName());

        System.out.println(option);
    }*/

   

}
