package com.example.table;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BookmarkGroup {
    @SerializedName("GROUP_ID")
    int group_id;
    @SerializedName("BOOKMARK_NAME")
    String bookmark_name;
    @SerializedName("SEQUENCE")
    int sequence;
    @SerializedName("CREATE_DATE")
    String create_date;
    @SerializedName("MODIFY_DATE")
    String modify_date;
}
