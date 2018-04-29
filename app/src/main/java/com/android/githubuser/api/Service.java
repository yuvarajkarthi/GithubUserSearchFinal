package com.android.githubuser.api;

import com.android.githubuser.model.ItemResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by delaroy on 3/22/17.
 */
public interface Service {
  //  @GET("/search/users?q=language:{lang}+location:{loc}")
//    Call<ItemResponse> getItems(@Query("lang") String lang, @Query("loc") String loc);
//    @GET("search/users?q=leo%20in:login")
    @GET("/search/users")
    Call<ItemResponse> getItems(@Query("q") String query);
}
