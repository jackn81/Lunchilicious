package edu.scranton.nesbittj3.lunchilicious;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface KeplerPlaceHolderAPI {
    @GET ("lunchilicious/menuitems")
    Call<List<MenuItem>> updateMenu();

    @POST ("lunchilicious/addmenuitem")
    Call<MenuItem> addToMenu(@Body MenuItem menuItem);
}
