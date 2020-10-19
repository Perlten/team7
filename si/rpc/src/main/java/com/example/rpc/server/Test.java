package com.example.rpc.server;

public class Test {


    public static void main(String[] args) {

        int[][] matrix = new int[8][8];

        //counts rows, every second row i want to start with white
        // Every other row with black :)

        //every second one has to swap color
        int brickCounter = 0;

        for (int i = 0; i < matrix.length; i++) {

            if(i % 2 == 0){
                brickCounter = 0;
            }else{
                brickCounter = 1;
            }

            for (int j = 0; j < matrix.length; j++) {
                if(brickCounter % 2 == 0){
                    matrix[i][j] = 0;
                    brickCounter ++;
                }else{
                    matrix[i][j] = 1;
                    brickCounter++;
                }
            }

        }




        for (int i = 0; i < matrix.length; i++) { //this equals to the row in our matrix.
            for (int j = 0; j < matrix[i].length; j++) { //this equals to the column in each row.
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println(); //change line on console as row comes to end in the matrix.
        }

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
