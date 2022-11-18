package com.example.musicplayer;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
public class MainActivity extends AppCompatActivity {
    int languages[]={R.drawable.hindi,R.drawable.english,R.drawable.malyalam,R.drawable.telugu};

    String names[]={"Hindi","English","Malayalam","Telugu"};

    String albums[][]={{"Shershaah","Bhool Bhulaiya 2" ,"Hot hits Hindi "},{"Shape Of You","Faded","Rockabye"},{"Malayali Da","Mullu Maalarum","Hot Hits Malyalam"},{"DJ tillu","Hot Hits Telugu","Telugu Party Time"}};

    int albumpic[][]={{R.drawable.shershaah,R.drawable.bhoolbhulaiya,R.drawable.hothitshindi},{R.drawable.shapeofyou,R.drawable.faded,R.drawable.rockabye},{R.drawable.malayalida,R.drawable.mullummalarum,R.drawable.hothitsmalyalam},{R.drawable.djtillutelugu,R.drawable.hothitstelugu,R.drawable.telugupartytime}};

    long songs[][][]={{{R.raw.raataanlambiyan,R.raw.jaihindkisena},{R.raw.detaali, R.raw.bhoolbhulaiyaa2titletrack},{R.raw.nakhreynakhrey,R.raw.mannudaudajaye}},{{R.raw.shapeofyou},{R.raw.faded},{R.raw.rockabye}},{{R.raw.malayalida1,R.raw.malayalida2},{R.raw.mullumallarum1},{R.raw.arikevarathe,R.raw.themonstersong}},{{R.raw.tillu1,R.raw.tillu2},{R.raw.ohisha},{R.raw.touchmetouchme,R.raw.naninani}}};

    String snames[][][]={{{"Raataan Lambiyan","Jai Hind ki Sena "},{"De Taali","Bhool Bhulaiya 2 title track"},{"Nakhrey Nakhrey","Mann Uda Uda Jaye"}},{{"Shape of You"},{"Faded"},{"Rockabye"}},{{"Malayali Da 1","Malayali Da 2"},{"Mullu Malarum"},{"Arike Varathe ","The Monster Song"}},{{"Tillu Anna DJ Pedithe Song","Pataas Pilla"},{"Oh Isha"},{"Touchme Touchme","Nani Nani Song"}}};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        GridView g=findViewById(R.id.gv);
        g.setAdapter(new MyAdapter(this));
    }
    public class MyAdapter extends BaseAdapter {
        Context context;
        public MyAdapter(MainActivity mainActivity)
        {
            context=mainActivity;
        }
        @Override
        public int getCount() {
            return languages.length;
        }
        @Override
        public Object getItem(int position) {
            return null;
        }
        @Override
        public long getItemId(int position) {
            return 0;
        }
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view= LayoutInflater.from(context).inflate(R.layout.languages,parent,false);
            ImageView img = view.findViewById(R.id.iv);
            TextView tv = view.findViewById(R.id.tv3);
            img.setImageResource(languages[position]);
            tv.setText(String.valueOf(names[position]));
            view.setPadding(5,5,5,5);

            img.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(MainActivity.this, Albums.class);
                    i.putExtra("Names",albums[position]);
                    i.putExtra("pics",albumpic[position]);
                    long[][] s =songs[position];
                    Bundle mBundle = new Bundle();
                    mBundle.putSerializable("key_array_array", s);
                    i.putExtras(mBundle);
                    i.putExtra("song",s);
                    mBundle.putSerializable("name_array", snames[position]);
                    i.putExtras(mBundle);
                    i.putExtra("songname",snames[position]);
                    startActivity(i);
                }
            });
            return view;
        }
    }
}