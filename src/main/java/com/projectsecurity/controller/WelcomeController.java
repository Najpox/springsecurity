package com.projectsecurity.controller;

import com.fasterxml.jackson.databind.ser.std.StdKeySerializers;
import com.projectsecurity.config.TestBeans;
import com.projectsecurity.model.Cards;
import com.projectsecurity.repository.AccountTransactionsRepository;
import org.apache.tomcat.util.buf.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@RestController
@RequestMapping("/apiw")
public class WelcomeController {

    private Logger log = LoggerFactory.getLogger(WelcomeController.class);

    @Autowired
    private testService testService;

    @GetMapping("/welcome")
    public String sayWelcome(){
        System.out.println("pringtinngL " + testService.getFirst());
        System.out.println("pringtinngL " + testService.getList());
        //bean sa vykona iba ak nad testbeans je configuration anotacia
        //System.out.println("pringtinng11x " + testService.getList().get(0).getTotalLimit());

        log.info("testtt loog");
        ApplicationContext apcntx = new AnnotationConfigApplicationContext(TestBeans.class);
        testService serviceBeans =  apcntx.getBean(testService.class);
        System.out.println("pringtinngssssssssssss " + serviceBeans);

        System.out.println("pringtinngsssssssllllll " + serviceBeans.getList());

        for(Cards card: serviceBeans.getList()) {
            System.out.println("cycle " + card.getTotalLimit());
        }




        return "hii man fff";
    }
}
