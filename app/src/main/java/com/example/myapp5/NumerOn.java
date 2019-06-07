package com.example.myapp5;

public class NumerOn{
    private int digitsNumber=3;
    private int trialNumber=0;
    private int[] answer;
    private int[] input;

    public NumerOn() {
        answer=new int[digitsNumber];
        input=new int[digitsNumber];
    }
    public NumerOn(Integer digitNumber) {
        this.digitsNumber=digitNumber;
        answer=new int[digitsNumber];
        input=new int[digitsNumber];
    }
}