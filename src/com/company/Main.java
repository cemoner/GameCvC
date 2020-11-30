package com.company;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        XoxCvC cvc = new XoxCvC(25);
        for(int i = 1;i<=1000;i++){
            cvc.game();
            Thread.sleep(250);
        }
        System.out.println(cvc.winC1);
        System.out.println(cvc.winC2);
    }
}
