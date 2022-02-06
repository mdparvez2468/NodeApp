package com.example.nodeappmvvm;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class NodeAdapter extends RecyclerView.Adapter<NodeAdapter.ViewHolder> {
    List<Note> noteList;
    private OnItemClickListener listener;
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.note_sample,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Note note = noteList.get(position);

        holder.t1.setText(note.getTitle());
        holder.t2.setText(note.getDescription());
        holder.t3.setText(note.getPriority()+"");

    }
    public void setNotes(List<Note> notes){
        this.noteList = notes;
        notifyDataSetChanged();
    }
    public Note getNodeAT(int position){
        return noteList.get(position);
    }

    @Override
    public int getItemCount() {
        return noteList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView t1,t2,t3;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            t1 = itemView.findViewById(R.id.text_title);
            t2 = itemView.findViewById(R.id.text_description);
            t3 = itemView.findViewById(R.id.text_priority);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener!=null && getAdapterPosition() != RecyclerView.NO_POSITION){
                        listener.inItemClick(noteList.get(getAdapterPosition()));
                    }

                }
            });
        }
    }
    public interface OnItemClickListener{
        void inItemClick(Note note);
    }
    public void setOnItemClickListener(OnItemClickListener listener){
        this.listener = listener;
    }
}
