package com.selene.priyo.daftarmahasiswa;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Mahasiswa> mahasiswaList = new ArrayList<>();
    RecyclerView recyclerView;
    AdapterMahasiswa adapterMahasiswa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Add data", Snackbar.LENGTH_LONG)
                        .setAction("ADD", AddData).show();
            }
        });

        recyclerView = findViewById(R.id.recycleViewData);

        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llm);

        LoadData();

        adapterMahasiswa = new AdapterMahasiswa(mahasiswaList);
        recyclerView.setAdapter(adapterMahasiswa);

        recyclerView.addOnItemTouchListener(new RecyclerItemListener(getApplicationContext(), recyclerView,
                new RecyclerItemListener.RecyclerTouchListener() {
                    public void onClickItem(View v, int position) { //position=menandakan yang mana mw diakses
                        Toast.makeText(getApplicationContext(), mahasiswaList.get(position).getAlamat(),Toast.LENGTH_SHORT).show();
                    }

                    public void onLongClickItem(View v, int position) {
                        RemoveData(position);
                    }
                }));

    }

    void LoadData() {
        mahasiswaList.add(new Mahasiswa("180010101","Adi","Denpasar",true));
        mahasiswaList.add(new Mahasiswa("180010102","Budi","Badung",false));
        mahasiswaList.add(new Mahasiswa("180010103","Candra","Gianyar",true));
        mahasiswaList.add(new Mahasiswa("180010104","Desi","Tabanan",false));
        mahasiswaList.add(new Mahasiswa("180010105","Edi","Klungkung",true));
        mahasiswaList.add(new Mahasiswa("180010106","Fahmi","Negara",true));
    }

    View.OnClickListener AddData = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            View customLayoutView = View.inflate(MainActivity.this, R.layout.layout_add_data, null);

            final EditText edNim = customLayoutView.findViewById(R.id.inputNIM);
            final EditText edNama = customLayoutView.findViewById(R.id.inputNama);
            final EditText edAlamat = customLayoutView.findViewById(R.id.inputAlamat);
            final RadioGroup rgJnsKel = customLayoutView.findViewById(R.id.radioJnsKel);

            //alert sebagai tombol untuk menambah data
            final AlertDialog.Builder builder;
            builder = new AlertDialog.Builder(MainActivity.this);
            builder.setCancelable(true);
            builder.setTitle(R.string.strTitleAlert);
            builder.setView(customLayoutView);
            builder.setPositiveButton(R.string.btnKlikstr, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    String nim = edNim.getText().toString();
                    String nama = edNama.getText().toString();
                    String alamat = edAlamat.getText().toString();
                    Boolean jnskel = ValueRadioButton(rgJnsKel);

                    mahasiswaList.add(new Mahasiswa(nim, nama, alamat, jnskel));
                    adapterMahasiswa.notifyDataSetChanged();
                    //notifydatasetchanged=jika list ditambah item baru. dia akan mengecek yang ditampilkan dengan yg di memori berbeda, jadi dia akan meload ulang dengan data yang baru

                    Toast.makeText(MainActivity.this,
                            R.string.btnKlikstr,Toast.LENGTH_LONG).show();
                    dialogInterface.dismiss();
                }
            });
            AlertDialog alertDialog = builder.create();
            alertDialog.show();

        }
    };

    //cari posisi yang harus di delete, kalo udah diremove akan notifydatasetchanged
    void RemoveData(int position) {
        mahasiswaList.remove(position);
        adapterMahasiswa.notifyDataSetChanged();
    }

    Boolean ValueRadioButton(RadioGroup rg) {
        int radioButtonID = rg.getCheckedRadioButtonId();
        View radioButton = rg.findViewById(radioButtonID);
        int idx = rg.indexOfChild(radioButton);
        RadioButton r = (RadioButton) rg.getChildAt(idx);
        String selectedtext = r.getText().toString();
        return (selectedtext.equals(getResources().getString(R.string.rb1)));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
