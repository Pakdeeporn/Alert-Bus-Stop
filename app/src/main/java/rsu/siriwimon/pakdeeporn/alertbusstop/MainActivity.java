package rsu.siriwimon.pakdeeporn.alertbusstop;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {
    //Explicit
    private ListView listView;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Bind Widget การผูกตัวแปลให้ทำงานร่วมกัน
        listView = (ListView)findViewById(R.id.livBusstop);
        button = (Button)findViewById(R.id.button);

        //Button Controller
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mySoundEffect(R.raw.add_bus);

            } //Onclick
        });



    } // Main Method

    private void mySoundEffect(int intSound) {
        MediaPlayer mediaPlayer = MediaPlayer.create(getBaseContext(),intSound);
        mediaPlayer.start(); //ทำการร้องเพลงง

        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                mediaPlayer.release(); //คืนหน่วยความจำ

            }
        });


    } // mySoundEffect
} // Main Class
