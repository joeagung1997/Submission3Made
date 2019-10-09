package com.example.submission3made.film;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;


import com.bumptech.glide.Glide;
import com.example.submission3made.R;
import com.example.submission3made.film.model.DataFilmBean;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class FilmAdapter extends RecyclerView.Adapter<FilmAdapter.MyViewHolder> {

    private ArrayList<DataFilmBean> listFilm;

    private Context context;

    public FilmAdapter(Context context, ArrayList<DataFilmBean> list) {
        this.context = context;
        this.listFilm = list;

    }



    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_film,viewGroup,false);
        return  new MyViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {

        holder.txtName.setText(listFilm.get(position).getTitle());
//        holder.imgPhoto.setImageResource(Integer.parseInt(listFilm.get(position).getPosterPath()));
        holder.txtDesc.setText(listFilm.get(position).getOverview());

        Picasso.get().load("https://image.tmdb.org/t/p/w500/"+listFilm.get(position).getPosterPath()).into(holder.imgPhoto);


//        Glide.with(context).load(listFilm.get(position).getPosterPath()).into(holder.imgPhoto);
//        Picasso.get().load(listFilm.get(position).getPosterPath()).into(holder.imgPhoto);
//        holder.txtTanggal.setText(listFilm.get(position).getJadwal());
//        holder.txtRating.setText(listFilm.get(position).getRating());


//        holder.btnLike.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(context,"Like",Toast.LENGTH_LONG).show();
//            }
//        });
//
//        holder.btnShare.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(context,"Share",Toast.LENGTH_LONG).show();
//            }
//        });

        holder.idConstraint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context,DetilFilm.class);
                i.putExtra("film",listFilm.get(position));
                context.startActivity(i);
            }
        });

    }

    @Override
    public int getItemCount() {
        return listFilm.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView txtName, txtDesc, txtTanggal, txtPlace,txtRating, txtLanguage;
        private ImageView imgPhoto;
        private ConstraintLayout idConstraint;
        private Button btnShare,btnLike;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);


            txtName = itemView.findViewById(R.id.txt_name);
            txtDesc = itemView.findViewById(R.id.txt_description);
            imgPhoto = itemView.findViewById(R.id.img_photo);
            idConstraint = itemView.findViewById(R.id.id_constraint);
            btnShare = itemView.findViewById(R.id.button_bagikan);
            btnLike = itemView.findViewById(R.id.button_suka);
            txtTanggal = itemView.findViewById(R.id.txt_tanggal);
            txtRating = itemView.findViewById(R.id.rating);



        }
    }
}