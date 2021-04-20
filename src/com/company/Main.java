package com.company;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        XoxCvC cvc = new XoxCvC(3);
        for(int i = 1;i<=100;i++){
            cvc.game();
            Thread.sleep(50);
        }
        System.out.println(cvc.winC1);
        System.out.println(cvc.winC2);
    }
}
