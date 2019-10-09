package com.example.submission3made.tvshow;


import android.app.ProgressDialog;
import android.content.res.TypedArray;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.submission3made.R;
import com.example.submission3made.tvshow.model.DataTvShowBean;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class TvShowFragment extends Fragment {


    private RecyclerView recyclerView;
    private ArrayList<TvShowBean> tvShowBean = new ArrayList<>();
    TvShowAdapter adapter;


    private String[] dataNameTv;
    private String[] dataDescriptionTv;
    private TypedArray dataPhotoTv;
    private String[] dataJadwalTayang;

    ProgressDialog progressDialog;







    public TvShowFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tv_show, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Loading..");
        recyclerView = view.findViewById(R.id.rv_listTv);



        MainViewModelTvShow model = ViewModelProviders.of(this).get(MainViewModelTvShow.class);

        model.getTvShow();

        model.getTvDataShowModel().observe(this, new Observer<ArrayList<DataTvShowBean>>() {
            @Override
            public void onChanged(ArrayList<DataTvShowBean> dataTvShowBeans) {
                generateDataList(dataTvShowBeans);

            }
        });

        model.getLoading().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if (aBoolean) {
                    progressDialog.show();
                } else {
                    progressDialog.dismiss();
                }
            }
        });

        model.getMessage().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                Toast.makeText(getActivity(),s,Toast.LENGTH_LONG).show();

            }
        });
//
//        dataDescriptionTv = getResources().getStringArray(R.array.descTv);
//        dataJadwalTayang = getResources().getStringArray(R.array.jamTayang);
//        dataNameTv = getResources().getStringArray(R.array.judulTv);
//
//        adapter  = new TvShowAdapter(getActivity(),tvShowBean);
//
//
//        recyclerView = view.findViewById(R.id.rv_listTv);
//        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
//        recyclerView.setHasFixedSize(true);
//        recyclerView.setAdapter(adapter);

//        prepare();
//        addItem();
//

    }

    private void generateDataList(ArrayList<DataTvShowBean> dataTvShowBeans) {
        adapter = new TvShowAdapter(getActivity(),dataTvShowBeans);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

    }


//    private void addItem() {
//        tvShowBean = new ArrayList<>();
//        for (int i = 0; i < dataNameTv.length; i++) {
//            TvShowBean tvShow = new TvShowBean();
//            tvShow.setImageTv(dataPhotoTv.getResourceId(i, -1));
//            tvShow.setTitleTv(dataNameTv[i]);
//            tvShow.setDesc(dataDescriptionTv[i]);
//            tvShow.setJadwalTv(dataJadwalTayang[i]);
//            tvShowBean.add(tvShow);
//        }
//        adapter.setListTv(tvShowBean);
//    }
//
//    private void prepare() {
//        dataNameTv = getResources().getStringArray(R.array.judulTv);
//        dataDescriptionTv = getResources().getStringArray(R.array.descTv);
//        dataPhotoTv = getResources().obtainTypedArray(R.array.tvPhoto);
//    }

}
