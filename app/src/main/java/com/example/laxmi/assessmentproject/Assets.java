package com.example.laxmi.assessmentproject;


public class Assets {
    private String assetMake;
    private String allocatedTo;
    private String allocatedTill;
    private int assetId;
    private String yearOfMaking;

//    public Assets(int assetId,String assetMake, String yearOfMaking,String allocatedTo, String allocatedTill ) {
//        this.assetMake = assetMake;
//        this.allocatedTo = allocatedTo;
//        this.allocatedTill = allocatedTill;
//        this.assetId = assetId;
//        this.yearOfMaking = yearOfMaking;
//    }

    public Assets(String assetMake, String yearOfMaking,String allocatedTo, String allocatedTill) {
        this.assetMake = assetMake;
        this.yearOfMaking = yearOfMaking;
        this.allocatedTo = allocatedTo;
        this.allocatedTill = allocatedTill;

    }



    public Assets() {

    }

    public String getAssetMake() {
        return assetMake;
    }

    public void setAssetMake(String assetMake) {

        this.assetMake = assetMake;
    }

    public String getAllocatedTo() {

        return allocatedTo;
    }

    public void setAllocatedTo(String allocatedTo) {

        this.allocatedTo = allocatedTo;
    }

    public String getAllocatedTill() {

        return allocatedTill;
    }

    public void setAllocatedTill(String allocatedTill) {

        this.allocatedTill = allocatedTill;
    }

    public int getAssetId() {

        return assetId;
    }

    public void setAssetId(int assetId) {

        this.assetId = assetId;
    }

    public String getYearOfMaking() {

        return yearOfMaking;
    }

    public void setYearOfMaking(String yearOfMaking) {

        this.yearOfMaking = yearOfMaking;
    }
}
