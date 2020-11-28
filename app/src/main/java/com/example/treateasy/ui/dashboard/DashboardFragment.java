package com.example.treateasy.ui.dashboard;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.treateasy.HospitalResponse;
import com.example.treateasy.HospitalRetrofitClient;
import com.example.treateasy.R;
import com.example.treateasy.hospitalInterface;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;

import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
public class DashboardFragment extends Fragment {
    int flag=1;
    Location currentLocation;
    FusedLocationProviderClient fusedLocationProviderClient;
    private static final int REQUEST_CODE = 101;
    private SupportMapFragment mapFragment;
    private DashboardViewModel dashboardViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
       fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(getActivity());
        Log.d("9", "ji");
        fetchLocation();
        return inflater.inflate(R.layout.fragment_home, container, false);
//       // final TextView textView = root.findViewById(R.id.text_dashboard);
//        try{
//            HospitalRetrofitClient Client = new HospitalRetrofitClient();
//            hospitalInterface apiService = Client.getClient().create(hospitalInterface.class);
//            Call<List<HospitalResponse>> call = apiService.getHospitalDetails();
//            call.enqueue(new Callback<List<HospitalResponse>>() {
//                @Override
//                public void onResponse(Call<List<HospitalResponse>> call, Response<List<HospitalResponse>> response) {
//                    Log.d("9", response.body().get(0).getHospital_name());
//                }
//
//                @Override
//                public void onFailure(Call<List<HospitalResponse>> call, Throwable t) {
//                    Log.d("Error", t.getMessage());
//                }
//            });
//        }catch (Exception e){
//            Log.d("Error", e.getMessage());
//        }
////        dashboardViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
////            @Override
////            public void onChanged(@Nullable String s) {
////                textView.setText(s);
////            }
////        });
//        return root;
    }

    private void fetchLocation() {
        Log.d("9", "ji");
        if (ActivityCompat.checkSelfPermission(
                getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            Log.d("9", "ji");
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_CODE);
            //Log.d("9", "ji");
            return  ;
        }
        Log.d("9", "ji");
        Task<Location> task = fusedLocationProviderClient.getLastLocation();
        task.addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if (location != null) {
                    currentLocation = location;
                    FragmentManager fm = getChildFragmentManager();
                    mapFragment = (SupportMapFragment) fm.findFragmentById(R.id.map);
                    if (mapFragment == null) {
                        mapFragment = SupportMapFragment.newInstance();
                        fm.beginTransaction().replace(R.id.map, mapFragment).commit();

                    } else {
                        mapFragment.getMapAsync(new OnMapReadyCallback() {

                            @Override
                            public void onMapReady(GoogleMap googleMap) {
                                LatLng latLng = new LatLng(currentLocation.getLatitude(), currentLocation.getLongitude());
                                MarkerOptions markerOptions = new MarkerOptions().position(latLng).title("I am here!");
                                googleMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));
                                googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 5));
                                googleMap.addMarker(markerOptions);
                                googleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
                                flag=0;

                            }
                        });

                    }
                }
            };
        });
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        Log.d("9", requestCode+"");
        Log.d("9", "ji");
        switch (requestCode) {
            case REQUEST_CODE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    fetchLocation();
                }
                break;
        }
    }
}