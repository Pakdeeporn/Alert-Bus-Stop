package rsu.siriwimon.pakdeeporn.alertbusstop;

import android.content.Intent;
import android.database.Cursor;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.FragmentActivity;
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

    //Explicit
    private GoogleMap mMap;
    private EditText editText;
    private Button button;
    private String nameBusStopString, pathAudioString;
    private ImageView recordImageView, playImageView;
    private boolean aBoolean = true;    // Non Record Sound
    private Uri uri;
    private double latStartADouble = 13.964535;
    private double lngStartADouble = 100.585110;
    private double latBusStopADouble,lngBusStopADouble;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_addbusstop_layout);

        //Bind Widget
        editText = (EditText) findViewById(R.id.editText);
        button = (Button) findViewById(R.id.button2);
        recordImageView = (ImageView) findViewById(R.id.imageView3);
        playImageView = (ImageView) findViewById(R.id.imageView4);


        //Record Controller
        recordImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MediaStore.Audio.Media.RECORD_SOUND_ACTION);
                startActivityForResult(intent, 0);

            }   // onClick
        });


        ///Button Controller
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Get Value From EditText
                nameBusStopString = editText.getText().toString().trim();

                //Check Space
                if (nameBusStopString.equals("")) {
                    //Have Space
                    MyAlert myAlert = new MyAlert(AddBusStop.this,
                            R.drawable.doremon48,
                            getResources().getString(R.string.title_have_space),
                            getResources().getString(R.string.message_have_space));
                    myAlert.myDialog();

                } else if (aBoolean) {
                    //Non Record Audio
                    MyAlert myAlert = new MyAlert(AddBusStop.this,R.drawable.kon48,
                            getResources().getString(R.string.title_record_sound),
                            getResources().getString(R.string.message_record_sound));
                    myAlert.myDialog();


                }

                // if

            }   // onClick
        });

        //Play Controller
        playImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Check Record
                if (aBoolean) {
                    //Non Record
                    MyAlert myAlert = new MyAlert(AddBusStop.this, R.drawable.nobita48,
                            getResources().getString(R.string.title_record_sound),
                            getResources().getString(R.string.message_record_sound));
                    myAlert.myDialog();
                } else {
                    // Record OK
                    MediaPlayer mediaPlayer = MediaPlayer.create(AddBusStop.this, uri);

                    mediaPlayer.start();

                }

            }   // onClick
        });


        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

    }   // Main Method


    @Override
    protected void onActivityResult(int requestCode,
                                    int resultCode,
                                    Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if ((requestCode == 0) && (resultCode == RESULT_OK)) {

            Log.d("1novV1", "Result OK");
            aBoolean = false; // Record Sound OK
            uri = data.getData();

            //Find Path of Audio
            String[] strings = {MediaStore.Audio.Media.DATA};
            Cursor cursor = getContentResolver().query(uri, strings, null, null, null);
            if (cursor != null) {
                cursor.moveToFirst();
                int index = cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DATA);
                pathAudioString = cursor.getString(index);
            } else {
                pathAudioString = uri.getPath();
            }

            Log.d("1novV1", "pathAudioString ==> " + pathAudioString);


        } //if


    }   // onActivityResult

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

       //  Create Map
        LatLng centerLatLng = new LatLng(latStartADouble,lngStartADouble);
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(centerLatLng,16)); //ตำแหน่งซูมที่ 16

        //Get Event from Click Maps
        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {

                mMap.clear(); //เครียร์ maps

                mMap.addMarker(new MarkerOptions()
                .position(latLng));

                latBusStopADouble = latLng.latitude;
                lngBusStopADouble = latLng.longitude;

                Log.d("1novV1","Lat ==> " + latBusStopADouble);
                Log.d("1novV1","Lng ==> " + lngBusStopADouble);





            } // OnMapsClick
        });



    }   // onMapReady

}   // Main Class