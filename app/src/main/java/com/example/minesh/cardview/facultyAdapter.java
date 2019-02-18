package com.example.minesh.cardview;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class facultyAdapter extends RecyclerView.Adapter<facultyAdapter.FacultyViewHolder> {

    private Context mCtx;
    private List<faculty> facultyList;
    private OnItemClickListener onItemClickListener;

    public interface OnItemClickListener{
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        onItemClickListener=listener;
    }

    public facultyAdapter(Context mCtx, List<faculty> facultyList) {
        this.mCtx = mCtx;
        this.facultyList = facultyList;
    }

    @NonNull
    @Override
    public FacultyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater=LayoutInflater.from(mCtx);
                View view=inflater.inflate(R.layout.list_layout,null);
        return new FacultyViewHolder(view,onItemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull FacultyViewHolder facultyViewHolder, int i) {
            faculty fac= facultyList.get(i);
            facultyViewHolder.textName.setText(fac.gName());
            facultyViewHolder.textShortInfo.setText(fac.gShortInfo());
            facultyViewHolder.textEmail.setText(fac.gEmali());
            facultyViewHolder.imageView.setImageDrawable(mCtx.getResources().getDrawable(fac.gImage()));
    }

    @Override
    public int getItemCount() {
        return facultyList.size();
    }

    class FacultyViewHolder extends RecyclerView.ViewHolder{

        ImageView imageView;
        TextView textName,textShortInfo,textEmail;

        public FacultyViewHolder(@NonNull View itemView, final OnItemClickListener listener) {
            super(itemView);

            imageView=itemView.findViewById(R.id.imageView);
            textName=itemView.findViewById(R.id.textViewTitle);
            textShortInfo=itemView.findViewById(R.id.textViewPrice);
            textEmail= itemView.findViewById(R.id.textViewShortDesc);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(listener!=null){
                        int position=getAdapterPosition();
                        if(position!=RecyclerView.NO_POSITION){
                            listener.onItemClick(position);
                        }
                    }
                }
            });
        }
    }
}
