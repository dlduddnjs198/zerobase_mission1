package com.example.table;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class History {
    @SerializedName("HISTORY_ID")
    int history_id;
    @SerializedName("LOOK_LAT")
    double latitude;
    @SerializedName("LOOK_LNT")
    double longitude;
    @SerializedName("LOOK_DATE")
    String date;
}
