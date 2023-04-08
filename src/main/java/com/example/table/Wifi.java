package com.example.table;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Wifi {
    double distance_value=0.0;
    @SerializedName("X_SWIFI_MGR_NO")
    String mgr_no;
    @SerializedName("X_SWIFI_WRDOFC")
    String wrdofc;
    @SerializedName("X_SWIFI_MAIN_NM")
    String main_name;
    @SerializedName("X_SWIFI_ADRES1")
    String address1;
    @SerializedName("X_SWIFI_ADRES2")
    String address2;
    @SerializedName("X_SWIFI_INSTL_FLOOR")
    String install_floor;
    @SerializedName("X_SWIFI_INSTL_TY")
    String install_type;
    @SerializedName("X_SWIFI_INSTL_MBY")
    String install_mby;
    @SerializedName("X_SWIFI_SVC_SE")
    String  service_se;
    @SerializedName("X_SWIFI_CMCWR")
    String cmcwr;
    @SerializedName("X_SWIFI_CNSTC_YEAR")
    String cnstc_year;
    @SerializedName("X_SWIFI_INOUT_DOOR")
    String inout_door;
    @SerializedName("X_SWIFI_REMARS3")
    String remars3;
    @SerializedName("LAT")
    double latitude;
    @SerializedName("LNT")
    double longitude;
    @SerializedName("WORK_DTTM")
    String work_date;
}
