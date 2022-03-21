package com.projectsecurity.controller;


import com.projectsecurity.model.Cards;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class testService2 {

    private List<Cards> listsCards = new ArrayList<Cards>();
/*
  public void listsCards() {
      Cards card = new Cards();
      card.setTotalLimit(5700);
      card.setCardType("devbetneaa");
      listsCards.add(card);

      card = new Cards();
      card.setTotalLimit(8700);
      card.setCardType("devbetneaa22");
      listsCards.add(card);

      card = new Cards();
      card.setTotalLimit(9700);
      card.setCardType("devbetneaa33");
      listsCards.add(card);
  }*/

    public String getFirst(){
        return "first";
    }
    public String getSecond(){
        return "second";
    }

    public List<Cards> getList(){
        return this.listsCards;
    }
}
