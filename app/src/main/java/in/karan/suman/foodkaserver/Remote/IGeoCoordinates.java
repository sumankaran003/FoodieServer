package in.karan.suman.foodkaserver.Remote;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Suman on 17-Dec-17.
 */

public interface IGeoCoordinates {

    @GET("maps/api/geocode/json")
    Call<String> getGeoCode(@Query("address") String address);
    @GET("maps/api/geocode/json")
    Call<String> getDirections(@Query("origin") String origin,@Query("destination") String destination);



}
