package com.example.musicplayer;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
public class AlbumSong extends AppCompatActivity {
    TextView tv4;
    ListView lv1;
    long[] lis;
    String nam;
    int alpic;
    String[] sn;
    ImageView im;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album_song);
        tv4=findViewById(R.id.sname);
        lv1=findViewById(R.id.lv1);
        im=findViewById(R.id.imageView2);
        Intent it2=getIntent();
        lis=it2.getLongArrayExtra("songlist");
        nam=it2.getStringExtra("Movie");
        alpic=it2.getIntExtra("Pic",0);
        sn=it2.getStringArrayExtra("n");
        tv4.setText(nam);
        im.setImageResource(alpic);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>
                (this, android.R.layout.simple_list_item_1, sn){
            @Override
            public View getView(int position, View convertView, ViewGroup parent){
                // Get the Item from ListView
                View view = super.getView(position, convertView, parent);
                // Initialize a TextView for ListView each Item
                TextView tv = (TextView) view.findViewById(android.R.id.text1);
                // Set the text color of TextView (ListView Item)
                tv.setTextColor(Color.WHITE);
                // Generate ListView Item using TextView
                return view;
            }
        };
        lv1.setAdapter(arrayAdapter);
        lv1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Intent intent=new Intent(getApplicationContext(),MusicPlayer.class);
                intent.putExtra("im",alpic);
                intent.putExtra("cs",lis[position]);
                intent.putExtra("sn",sn[position]);
                startActivity(intent);
            }
        });
    }
}