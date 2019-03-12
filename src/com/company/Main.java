package com.company;

public class Main {

    public static void main(String[] args) {
        MyLinkedList<Integer> list = new MyLinkedList<>();
        System.out.println("init size " + list.size());
        for (int i = 0; i < 10; i++) {
            list.add(i);
        }
        System.out.println("add 10 elements - size " +list.size());
        list.remove(5);

        /*
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
        */

        System.out.println("remove 5th element - size " + list.size());
    }

}
