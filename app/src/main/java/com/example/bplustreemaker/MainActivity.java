package com.example.bplustreemaker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void startCreateTree(View view) {
        EditText orderField = findViewById(R.id.order_field);
        TextView errorField = findViewById(R.id.error_message_view);
        try {
            int treeOrder =  Integer.parseInt(orderField.getText().toString());
            if (treeOrder <= 2) {
                errorField.setText(R.string.error_message);
            }else {
                Intent treeViewer = new Intent(MainActivity.this, TreeViewActivity.class);
                treeViewer.putExtra("TREE_ORDER", treeOrder);
                startActivity(treeViewer);
            }
        }
        catch (NumberFormatException e) {
            errorField.setText(R.string.error_message);
        }

    }
}
