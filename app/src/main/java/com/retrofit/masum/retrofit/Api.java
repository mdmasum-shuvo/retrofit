package com.retrofit.masum.retrofit;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Masum on 2/18/2018.
 */

public interface Api {

    //String BASE_URL = "https://api.coinmarketcap.com/v1/";

    @GET("ticker")
    Call<List<Product>> getHeroes(@Query("start") int from, @Query("limit") int limit);
}
