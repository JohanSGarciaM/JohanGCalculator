package com.example;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        ArrayList<Integer> lista = new ArrayList<Integer>();

        for(int i =1;i<=10;i++){
            lista.add(i);
        }

        System.out.println(lista.get(lista.size()-1));


    }
}