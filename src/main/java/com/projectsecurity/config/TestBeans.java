package com.projectsecurity.config;

import com.projectsecurity.controller.BalanceController;
import com.projectsecurity.controller.testService;
import com.projectsecurity.model.AccountTransactions;
import com.projectsecurity.model.Cards;
import com.projectsecurity.repository.AccountTransactionsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.util.List;


public class TestBeans {


   // @Primary
    @Bean(name = {"bean1", "bean2"})
    @ConditionalOnProperty(value = "bean.test.mode", havingValue = "b1", matchIfMissing = false)
    public testService getTestService(){
        testService testServicex = new testService();
        Cards card = new Cards();
        card.setTotalLimit(99700);
        card.setCardType("devbetneaa5555");
        testServicex.getList().add(card);

        return testServicex;

    }


    @Bean(name = {"bean3", "bean4"})
    @ConditionalOnProperty(value = "bean.test.mode", havingValue = "b2", matchIfMissing = true)
    public testService getTestService2(){
        testService testServicex = new testService();
        Cards card = new Cards();
        card.setTotalLimit(114700);
        card.setCardType("devbetneaa55655");
        testServicex.getList().add(card);

        return testServicex;

    }



}
