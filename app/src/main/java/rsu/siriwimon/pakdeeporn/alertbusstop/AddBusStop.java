package rsu.siriwimon.pakdeeporn.alertbusstop;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_addbusstop_layout);
        // Bind Widget
        editText = (EditText)findViewById(R.id.editText); //ผูกข้อมูล ediText กับ editText
        button = (Button) findViewById(R.id.button2); //ผูกข้อมูล button กับ button2


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


        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


    } // Main method

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    } // OnMapReady
} // Main Class
