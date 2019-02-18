package com.example.minesh.cardview;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.StudentViewHolder> implements Filterable {
    private Context mCtx;
    private List<StudentClass> studentList;
    private List<StudentClass> tempList;
    private OnItemClickListener onItemClickListener;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        onItemClickListener = listener;
    }

    public ListAdapter(Context mCtx, List<StudentClass> facultyList) {
        this.mCtx = mCtx;
        this.studentList = facultyList;
        tempList = facultyList;
    }

    @NonNull
    @Override
    public StudentViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.student_list_layout, null);
        return new StudentViewHolder(view, onItemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull StudentViewHolder facultyViewHolder, int i) {
        StudentClass fac = studentList.get(i);
        facultyViewHolder.textName.setText("");
        facultyViewHolder.textID.setText(fac.gid()+"  "+fac.gName());
    }

    @Override
    public int getItemCount() {
        return studentList.size();
    }

    class StudentViewHolder extends RecyclerView.ViewHolder {

        TextView textName, textID;

        public StudentViewHolder(@NonNull View itemView, final OnItemClickListener listener) {
            super(itemView);

            textName = itemView.findViewById(R.id.textViewName);
            textID = itemView.findViewById(R.id.textViewID);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onItemClick(position);
                        }
                    }
                }
            });
        }
    }

    public List<StudentClass> currentList() {
        return studentList;
    }

    public Filter getFilter() {

        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {

                String charString = charSequence.toString();

                if (charString.isEmpty()) {

                    studentList = tempList;
                } else {

                    ArrayList<StudentClass> filteredList = new ArrayList<>();

                    for (StudentClass fac : tempList) {

                        if (fac.gName().toLowerCase().contains(charString)) {

                            filteredList.add(fac);
                        }
                    }

                    studentList = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = studentList;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                studentList = (ArrayList<StudentClass>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }
}