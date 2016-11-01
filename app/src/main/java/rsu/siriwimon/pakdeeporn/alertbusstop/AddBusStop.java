package rsu.siriwimon.pakdeeporn.alertbusstop;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class AddBusStop extends FragmentActivity implements OnMapReadyCallback {

    // Explicit

    private GoogleMap mMap;
    private EditText editText;
    private Button button;
    private String nameBusStopString;
    private ImageView recordImageView,playImageView;
    private boolean aBoolean = true; // Non Record Sound
    private Uri uri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_addbusstop_layout);
        // Bind Widget
        editText = (EditText)findViewById(R.id.editText); //ผูกข้อมูล ediText กับ editText
        button = (Button) findViewById(R.id.button2); //ผูกข้อมูล button กับ button2
        recordImageView = (ImageView) findViewById(R.id.imageView3); //ผูกข้อมูลในการบันทึก
        playImageView = (ImageView) findViewById(R.id.imageView4); //แสดงเสียงที่บันทึก

        // Record Controller

        recordImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MediaStore.Audio.Media.RECORD_SOUND_ACTION); //สามารถเคลื่อนย้ายการทำงานแล้วสามารถทำงานได้
                startActivityForResult(intent,0); //ใ่เลข 0 เป็น resource
            } // OnClick เมื่อไหร่ก็ตามเมื่อมีการคลิกจะทำงานนอกปีกกา
        });




        ///Button Controller เมื่อมีการคลิกที่ Button เมื่อเป็น editext ไม่สามารถทำการประมวลผลได้ ต้องเปลี่ยนเป้น String
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Get Value From EdiText ทำการรับค่าจาก EdiText
                nameBusStopString = editText.getText().toString().trim(); //ต้องการพิมพ์ String โดยไม่ให้เกิดช่องว่าง

                // Check Space
                if (nameBusStopString.equals("")) {//ถ้า data ไม่มีการกรอกจะทำงานในปีกกา
                    // Have Space
                    MyAlert myAlert = new MyAlert(AddBusStop.this,
                            R.drawable.doremon48,
                            getResources().getString(R.string.title_have_space),
                            getResources().getString(R.string.message_have_space));
                    myAlert.myDialog();

                } // if

            }// OnClick เมื่อไหร่ที่ทำการคลิกปุ่มให้ทำคำสั่งนี้
        });


        //Play Controller
        playImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Check Record
                if (aBoolean) {
                    // Non Record
                    MyAlert myAlert = new MyAlert(AddBusStop.this, R.drawable.nobita48,
                            getResources().getString(R.string.title_record_sound),
                            getResources().getString(R.string.message_record_sound));
                    myAlert.myDialog();
                } else {
                    // Record OK
                    MediaPlayer mediaPlayer = MediaPlayer.create(AddBusStop.this,uri); //
                    mediaPlayer.start();
                }
            }
        });

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


    } // Main method

    @Override
    protected void onActivityResult(int requestCode,
                                    int resultCode,
                                    Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if ((requestCode == 0)&&(resultCode == RESULT_OK)){

            Log.d("1novV1","Result OK"); //การทำ debuging
            aBoolean = false; // Record Sound OK
            uri = data.getData();
        } //if


    } // onActivityResult

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    } // OnMapReady
} // Main Class
