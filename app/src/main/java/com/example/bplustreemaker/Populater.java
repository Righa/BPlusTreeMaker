package com.example.bplustreemaker;

public class Populater {
    //vars

    String[][] a = new String[1][1];
    private int order;
    private int m;


    //views

    public void setOrder(int order) {
        this.order = order;
        this.m = order - 1;
    }

    private String[] insert(String key, int index, int level) {

        String[] result = new String[a[level].length + 1];

        System.arraycopy(a[level], 0, result, 0, index);

        result[index] = key;

        for (int i = index + 1; i <= a[level].length; i++) {
            result[i] = a[level][i - 1];
        }

        return result;
    }

    public void injectValue(String x, int level) {

        int value = 0;

        boolean inserted = false;

        //find place to insert
        for (int i = 0; i < a[level].length; i++) {

            //when the level in new the only value is null
            if ( a[level][i] == null ) {

                System.out.println("replacing null value");
                a[level][i] = x;
                inserted = true;
                break;
            }

            else if (!"x".equals(a[level][i])) {

                if (Integer.parseInt(a[level][i]) > Integer.parseInt(x)) {
                    a[level] = insert(x, i, level);
                    inserted = true;
                    break;
                }
            }

            else if (Integer.parseInt(a[level][i+1]) > Integer.parseInt(x)) {
                a[level] = insert(x, i, level);
                inserted = true;
                break;
            }
        }

        //if it is greater than all
        if (!inserted) {
            a[level] = insert(x, a[level].length, level);
        }

        for (int i = 0; i < a[level].length; i++) {

            //reset value if one node is passed

            if ("x".equals(a[level][i])) {
                value = 0;
            }

            //split if value exceeds m-1

            else if(value == m){
                split(level,i);
                break;
            }

            //i don't edit after the codde works
            //i.e could have worked without nesting this statement

            else{
                value++;
            }
        }

    }

    public void split(int level, int nodeAt) {

        try {
            //push up index

            injectValue(a[level][nodeAt-(m/2)], level+1);

            //divide node

            a[level] = insert("x",nodeAt-(m/2),level);

        }

        catch (ArrayIndexOutOfBoundsException e) {

            //create new level

            String newArray[][] = new String[a.length + 1][ 1 ];

            System.arraycopy(a, 0, newArray, 0, a.length);

            a = newArray;

            //push up index

            injectValue(a[level][nodeAt-(m/2)], level+1);

            //divide node

            a[level] = insert("x",nodeAt-(m/2),level);


        }

    }

    public String[][] getNewArray() {
        return a;
    }
}
