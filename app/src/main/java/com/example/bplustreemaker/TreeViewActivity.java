package com.example.bplustreemaker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.util.ArrayList;

public class TreeViewActivity extends AppCompatActivity {
    RecyclerView.LayoutManager viewManager;
    RecyclerView view;
    RecyclerView.Adapter viewAdapter;

    ArrayList<NodeItem> nodeItems;
    String[][] a;
    int order;
    Populater populater = new Populater();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tree_view);

        nodeItems = new ArrayList<>();
        Intent orderIntent = getIntent();
        order = orderIntent.getIntExtra("TREE_ORDER", 0);

        populater.setOrder(order);

        makeTree();
        //nodeItems.add(new NodeItem("Start inserting values..."));

        view = findViewById(R.id.my_recycler);
        //view.setHasFixedSize(true);
        viewManager = new LinearLayoutManager(this);
        viewAdapter = new NodeAdapter(nodeItems);

        view.setLayoutManager(viewManager);
        view.setAdapter(viewAdapter);
    }

   public void makeTree() {
        //clean initials
       nodeItems.clear();
       //re-populate nodes with new array
       a = populater.getNewArray();
       if (a[0][0] == null)
           return;

       for (int i = a.length-1; i >= 0; i = i - 1) {
           String allNodes = "|";
           for (int j = 0; j < a[i].length; j++) {
               if (a[i][j].equals("x")) {
                   if (i == 0)
                       allNodes = String.format("%s%s", allNodes, "| -> |");
                   else
                       allNodes = String.format("%s%s", allNodes, "|  |");
               }
               else
                   allNodes = String.format("%s%s,",allNodes, a[i][j]);
           }
           allNodes = String.format("%s%s",allNodes, "|");
           nodeItems.add(new NodeItem(allNodes));
       }

       viewAdapter.notifyDataSetChanged();
   }

    public void reCraftTree(View view) {
        EditText newValueField = findViewById(R.id.new_value_field);
        String newValue = newValueField.getText().toString();

        populater.injectValue(newValue, 0);

        //addNodeItem(newValue, 0);
        makeTree();
    }

}
