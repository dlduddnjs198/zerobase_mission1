package com.example.table;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Bookmark {
    @SerializedName("BOOKMARK_ID")
    int bookmark_id;
    @SerializedName("BOOKMARK_NAME")
    String bookmark_name;
    @SerializedName("X_SWIFI_MAIN_NM")
    String wifi_name;
    @SerializedName("CREATE_DATE")
    String create_date;
    @SerializedName("X_SWIFI_MGR_NO")
    String wifi_key;
    @SerializedName("GROUP_ID")
    int group_key;
}
