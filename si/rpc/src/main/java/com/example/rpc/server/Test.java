package com.example.rpc.server;

public class Test {


    public static void main(String[] args) {

        Teacher t = new Teacher("Mathias", 25);

        System.out.println(t.name);

    }


    private static class Teacher{

        String name;
        int age;

        //Constructor
        public Teacher(String tmpName, int tmpAge){
            name = tmpName;
            age = tmpAge;
        }

    }


}
