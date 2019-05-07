package com.selene.priyo.daftarmahasiswa;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;
//adapter, menjembatani
public class AdapterMahasiswa extends RecyclerView.Adapter<AdapterMahasiswa.MyViewHolder> {

    private List<Mahasiswa> mahasiswaList;

    //holder=menjembatani class mahasiswa
    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView txtNIM;
        public TextView txtNama;
        public ImageView imgJnsKel;

        public MyViewHolder(View view) {
            super(view);
            txtNIM = view.findViewById(R.id.textViewNIM);
            txtNama = view.findViewById(R.id.textViewNama);
            imgJnsKel = view.findViewById(R.id.imageViewMhs);
        }
    }

    public AdapterMahasiswa(List<Mahasiswa> mahasiswas) {
        this.mahasiswaList = mahasiswas;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        //dapat dari mana
        Mahasiswa m = mahasiswaList.get(position);
        holder.txtNIM.setText(m.getNim());
        holder.txtNama.setText(m.getNama());
        //kalo true male, false female, ini if
        int ResImg = (m.getJns_kel()) ? R.drawable.icons8_user_male : R.drawable.icons8_user_female;
        holder.imgJnsKel.setImageResource(ResImg);
    }

    @Override
    //mengecek apakah isi mahasiswalist sama atau tidak dengan item, kalo tidak sama errornya array of the bound
    public int getItemCount() {
        return mahasiswaList.size(); //berapa sizenya segitu countnya
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.mahasiswa_list,parent, false);
        return new MyViewHolder(v);
    }
}
