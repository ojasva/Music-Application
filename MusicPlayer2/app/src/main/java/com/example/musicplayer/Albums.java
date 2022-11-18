package com.example.musicplayer;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
public class Albums extends AppCompatActivity {
    String alb[];
    int albpic[];
    long[][] songli;
    String[][] songn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_albums);
        Intent it = getIntent();
        alb=it.getStringArrayExtra("Names");
        albpic=it.getIntArrayExtra("pics");
        Object[] objectArray = (Object[]) getIntent().getExtras().getSerializable("key_array_array");
        Object[] object=(Object[]) getIntent().getExtras().getSerializable("songname");
        if(objectArray!=null && object!=null){
            songli = new long[objectArray.length][];
            songn=new String[object.length][];
            for(int i=0;i<objectArray.length;i++){
                songli[i]=(long[]) objectArray[i];
                songn[i]=(String[])object[i];
            }
        }
        ListView lv=findViewById(R.id.lv);
        MyAdapter adapt=new MyAdapter(this);
        lv.setAdapter(adapt);
    }
    private class MyAdapter extends BaseAdapter {
        Context c;
        public MyAdapter(Albums mainActivity)
        {
            c=mainActivity;
        }
        @Override
        public int getCount() {
            return alb.length;
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
            View view= LayoutInflater.from(c).inflate(R.layout.songlist,parent,false);
            TextView t1=view.findViewById(R.id.tv2);
            ImageView I1=view.findViewById(R.id.imageView);
            Button b1=view.findViewById(R.id.b1);
            t1.setText(alb[position]);
            I1.setImageResource(albpic[position]);
            b1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent it1=new Intent(Albums.this,AlbumSong.class);
                    it1.putExtra("songlist",songli[position]);
                    it1.putExtra("Movie",alb[position]);
                    it1.putExtra("Pic",albpic[position]);
                    it1.putExtra("n",songn[position]);
                    startActivity(it1);
                }
            });
            return view;
        }
    }
}