package com.fag;

import com.fag.domain.dto.BankslipDTO;
import com.fag.infra.celcoin.CelcoinBassRepository;
import com.fag.infra.console.ConsoleUI;
import com.fag.infra.pg.PostgresConnection;
import com.fag.service.BankingService;

public class mainteste {
    public static void main(String[] args) throws Exception {
        CelcoinBassRepository celcoin = new CelcoinBassRepository();


        System.out.println(celcoin.genToken());
    }   
}
