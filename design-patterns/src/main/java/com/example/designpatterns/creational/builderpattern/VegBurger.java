package com.example.designpatterns.creational.builderpattern;

/**
 * @author wanghaocun
 * @since 2022-05-04
 */
public class VegBurger extends Burger {
 
   @Override
   public float price() {
      return 25.0f;
   }
 
   @Override
   public String name() {
      return "Veg Burger";
   }
}
