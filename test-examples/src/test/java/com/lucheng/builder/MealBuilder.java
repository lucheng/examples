package com.lucheng.builder;

public abstract class MealBuilder {
    Meal meal = new Meal();
    
    public abstract void buildFood();
    
    public abstract void buildDrink();
    
    public Meal getMeal(){
        return meal;
    }
}