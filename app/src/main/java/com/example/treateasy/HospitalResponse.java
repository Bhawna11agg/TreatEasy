package com.example.treateasy;

public class HospitalResponse {
    private String Hospital_name;
    private int No_Of_Beds;
    private String Location;
    private String Pincode;
    private String State;

    public HospitalResponse(String hospital_name, int no_Of_Beds, String location, String pincode, String state) {
        Hospital_name = hospital_name;
        No_Of_Beds = no_Of_Beds;
        Location = location;
        Pincode = pincode;
        State = state;
    }

    public String getHospital_name() {
        return Hospital_name;
    }

    public int getNo_Of_Beds() {
        return No_Of_Beds;
    }

    public String getLocation() {
        return Location;
    }

    public String getPincode() {
        return Pincode;
    }

    public String getState() {
        return State;
    }

    public void setHospital_name(String hospital_name) {
        Hospital_name = hospital_name;
    }

    public void setNo_Of_Beds(int no_Of_Beds) {
        No_Of_Beds = no_Of_Beds;
    }

    public void setLocation(String location) {
        Location = location;
    }

    public void setPincode(String pincode) {
        Pincode = pincode;
    }

    public void setState(String state) {
        State = state;
    }
}
