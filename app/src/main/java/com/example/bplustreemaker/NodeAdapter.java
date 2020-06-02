package com.example.bplustreemaker;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class NodeAdapter extends RecyclerView.Adapter<NodeAdapter.NodeViewHolder> {

    private ArrayList<NodeItem> nodeItems;

    NodeAdapter(ArrayList<NodeItem> nodeItems) {
        this.nodeItems = nodeItems;
    }


    static class NodeViewHolder extends RecyclerView.ViewHolder {

        private TextView levelNodes;

        NodeViewHolder(@NonNull View itemView) {
            super(itemView);
            levelNodes = itemView.findViewById(R.id.level_nodes);
        }
    }


    @NonNull
    @Override
    public NodeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.node_items, parent, false);
        return new NodeViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull NodeViewHolder holder, int position) {
        NodeItem currentNode = nodeItems.get(position);

        holder.levelNodes.setText(currentNode.getLevelData());
    }

    @Override
    public int getItemCount() {
        return nodeItems.size();
    }
}
