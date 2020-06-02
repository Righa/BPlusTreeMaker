package com.example.bplustreemaker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import java.util.ArrayList;

public class TreeViewActivity extends AppCompatActivity {

    ArrayList<NodeItem> nodeItems;
    String[][] a = new String[1][1];
    int order;
    int m = order-1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tree_view);

        nodeItems = new ArrayList<>();
        Intent orderIntent = getIntent();
        order = orderIntent.getIntExtra("TREE_ORDER", 0);
        String tree = String.valueOf(order);

        nodeItems.add(new NodeItem(tree));
        nodeItems.add(new NodeItem("4,5,6"));
        nodeItems.add(new NodeItem("7,8,9"));

        RecyclerView view = findViewById(R.id.my_recycler);
        view.setHasFixedSize(true);
        RecyclerView.LayoutManager viewManager = new LinearLayoutManager(this);
        RecyclerView.Adapter viewAdapter = new NodeAdapter(nodeItems);

        view.setLayoutManager(viewManager);
        view.setAdapter(viewAdapter);
    }

   // public void makeTree(int order) {
   // }

    public void injectValue(String x, int level) {
        int value = 0;
        boolean inserted = false;
        //find place to insert
        for (int i = 0; i < a[level].length; i++) {
            //when the level in new the only value is null
            if ( a[level][i] == null ) {
                a[level][i] = x;
                inserted = true;
                break;
            }
            //not a divider
            else if (!"x".equals(a[level][i])) {
                if (Integer.parseInt(a[level][i]) > Integer.parseInt(x)) {
                    a[level] = insert(x, i, level);
                    inserted = true;
                    break;
                }
            }
            //node beyond divider is no go
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

            //i don't edit after the code works
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

            String[][] newArray = new String[a.length + 1][ 1 ];

            System.arraycopy(a, 0, newArray, 0, a.length);

            a = newArray;

            //push up index

            injectValue(a[level][nodeAt-(m/2)], level+1);

            //divide node

            a[level] = insert("x",nodeAt-(m/2),level);


        }
    }

    private String[] insert(String key, int index, int level) {

        String[] result = new String[a[level].length + 1];

        System.arraycopy(a[level], 0, result, 0, index);

        result[index] = key;

        if (a[level].length + 1 - index + 1 >= 0)
            System.arraycopy(a[level], index + 1 - 1, result, index + 1, a[level].length + 1 - index + 1);

        return result;
    }

}
