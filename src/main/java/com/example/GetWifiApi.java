package com.example;

import com.example.table.Wifi;
import com.google.gson.Gson;
import lombok.Getter;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

import java.util.List;

@Getter
public class GetWifiApi {

    private String apikey="6e6b4a796e646c6436366e45667472";
    private int startnum=1;
    private int endnum=1000;
    private String baseurl= "http://openapi.seoul.go.kr:8088/";
    private int list_total_count=2;

    public boolean getWifiInfo(){
        try{
            SqlService sqlService = SqlService.getInstance();
            if(!sqlService.tableNameCheck("wifi")){
                sqlService.sqlCreateWifi();
            }
            while(startnum<list_total_count){
                StringBuilder url=new StringBuilder(baseurl);
                url.append(apikey).append("/json/TbPublicWifiInfo/");
                url.append(startnum).append("/").append(endnum).append("/");
                // Okhttp 클라이언트 객체 생성
                OkHttpClient client = new OkHttpClient();

                // GET 요청 객체 생성
                Request.Builder builder = new Request.Builder().url(url.toString()).get();
                // json을 주고받는 경우 헤더에 추가
                builder.addHeader("Content-type","application/json");
                // request 객체 생성
                Request request = builder.build();

                // Okhttp 클라이언트로 GET 요청 객체 전송(동기 요청 방식 = 응답을 받을때까지 멈춤)
                Response response = client.newCall(request).execute();
                if(response.isSuccessful()){
                    // 응답 받아서 처리
                    ResponseBody body = response.body();
                    Gson gson = new Gson();
                    PublicWifiData publicWifiData = gson.fromJson(body.string(), PublicWifiData.class);
                    List<Wifi> wifiList = publicWifiData.getTbPublicWifiInfo().row;
                    list_total_count=publicWifiData.getTbPublicWifiInfo().getList_total_count();
                    for (Wifi wifi : wifiList) {
                        sqlService.sqlUpsertWifi(wifi);
                    }
                }
                else{
                    System.err.println("Error occurred in GetWifiApi.java");
                    return false;
                }
                System.out.println("startnum="+startnum+" and endnum="+endnum+" is complete");
                startnum+=1000;
                endnum+=1000;
                if(endnum>list_total_count){
                    endnum=list_total_count;
                }
            }
            return true;
        } catch (Exception e) {
            System.err.println("Error occurred in GetWifiApi.java");
            e.printStackTrace();
        }

        return false;
    }
}
