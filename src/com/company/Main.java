package com.company;

import com.company.List.MyLinkedList;

public class Main {

    public static void main(String[] args) {

        MyLinkedList<Integer> list = new MyLinkedList<>();
        System.out.println("init size " + list.size());

        for (int i = 1; i < 11; i++) {
            list.add(0, i);
        }

        System.out.println(list.size());

        /*
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
        */

        System.out.println(list);

    }

}
