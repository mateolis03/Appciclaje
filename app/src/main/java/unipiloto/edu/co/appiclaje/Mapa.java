package unipiloto.edu.co.appiclaje;


import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.location.LocationRequest;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Mapa extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private String nickname;
    private String tipo;
    private List<String> listaAddress = new ArrayList<>();
    private List<String> listaId = new ArrayList<>();
    private List<LatLng> path = new ArrayList<>();
    private GpsTracker gpsTracker;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        Intent intent = getIntent();
        nickname = intent.getStringExtra("nickname");
        tipo = intent.getStringExtra("tipo");
        try {
            if (ContextCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, 101);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
    public LatLng getLocation(){
        gpsTracker = new GpsTracker(Mapa.this);
        if(gpsTracker.canGetLocation()){
            double latitude = gpsTracker.getLatitude();
            double longitude = gpsTracker.getLongitude();
            LatLng latLng = new LatLng(latitude,longitude);
            return  latLng;
        }else{
            gpsTracker.showSettingsAlert();
        }
        return null;
    }



    public LatLng latLogwithAddress(String strAddress) {
        Geocoder coder = new Geocoder(this);
        List<Address> address;

        try {
            address = coder.getFromLocationName(strAddress, 100);
            if (address != null) {
                try {
                    Address location = address.get(0);
                    LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
                    mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
                    mMap.animateCamera(CameraUpdateFactory.zoomTo(15));
                    return latLng;
                } catch (IndexOutOfBoundsException er) {
                    Toast.makeText(this, "Location isn't available", Toast.LENGTH_SHORT).show();
                }
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        Query query = null;
        if (tipo.equalsIgnoreCase("Natural")) {
            query = FirebaseDatabase.getInstance().getReference("solicitudes").orderByChild("nickname").equalTo(nickname);
        } else {
            query = FirebaseDatabase.getInstance().getReference("solicitudes");
        }

        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                        if (!dataSnapshot.getKey().isEmpty()) {
                            String id = dataSnapshot.getKey();
                            String direccion = dataSnapshot.child("address").getValue().toString();
                            String estado = dataSnapshot.child("estado").getValue().toString();
                            listaAddress.add(direccion);
                            listaId.add(id + "-" + estado);
                        }
                    }
                }
                mMap = googleMap;
                //mMap.setMinZoomPreference(15f);
                // mMap.setMaxZoomPreference(20f);
                LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
                Location location = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                LatLng gps = getLocation();
                path.add(gps);
                mMap.addMarker(new MarkerOptions().position(gps).title("Tú"));
                for (int i = 0; i < listaAddress.size(); i++) {
                    LatLng addresses = latLogwithAddress(listaAddress.get(i));
                    path.add(addresses);
                    String id = listaId.get(i);
                    mMap.addMarker(new MarkerOptions()
                            .position(addresses)
                            .title(id));
                    mMap.moveCamera(CameraUpdateFactory.newLatLng(addresses));
                }
                if (path.size() > 0) {
                    PolylineOptions opts = new PolylineOptions().addAll(path).color(Color.BLUE).width(5);
                    mMap.addPolyline(opts);
                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }
}
