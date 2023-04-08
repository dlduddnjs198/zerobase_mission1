package com.example;

import com.example.table.Wifi;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class PublicWifiData {
    private TbPublicWifiInfo TbPublicWifiInfo;

    @Getter
    @NoArgsConstructor
    public static class TbPublicWifiInfo {
        int list_total_count;
        Result RESULT;
        List<Wifi> row;

        @Getter
        @NoArgsConstructor
        public static class Result{
            String CODE;
            String MESSAGE;
        }
    }
}
