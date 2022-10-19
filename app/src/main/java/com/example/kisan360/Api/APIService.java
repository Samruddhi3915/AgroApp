package com.example.kisan360.Api;

import com.example.kisan360.Model.ContactList;
import com.example.kisan360.Model.DataModel;
import com.example.kisan360.Model.EnquiryList;
import com.example.kisan360.Model.ProductList;
import com.example.kisan360.Model.Result;
import com.example.kisan360.Model.SchemeList;
import com.example.kisan360.Model.TransportList;
import com.example.kisan360.Model.UserEnquiryList;
import com.example.kisan360.Model.UserModel;
import com.example.kisan360.Utility.Constants;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface APIService {
    @FormUrlEncoded
    @POST(Constants.FEEDBACK_API)
    Call<Result> FeedbackApi(
            @Field("description") String description,
            @Field("rating") String rating
    );


    @POST(Constants.TEACHER_ADD_NEW_Achivements)
    Call<ResponseBody> addNewAchivement(
            @Body RequestBody file);

    @FormUrlEncoded
    @POST(Constants.USER_GET_Prodcut)
    Call<ProductList> getAllProduct(
            @Field("category") String description,
            @Field("status") String status
    );

    @FormUrlEncoded
    @POST(Constants.ADMIN_GET_Prodcut)
    Call<ProductList> getAllProductAdmin(
            @Field("status") String status
    );


    @GET(Constants.USER_GET_SCHEME)
    Call<SchemeList> getAllScheme();


    @GET("getAllContacts.php")
    Call<ContactList> getAllContacts();

    @GET("getAllUserEnquiry.php")
    Call<UserEnquiryList> getAllUserEnquiry();

    @GET(Constants.USER_GET_ENQUIRY)
    Call<EnquiryList> getAllEnquiry();

    @GET("getAllTransport.php")
    Call<TransportList> getAllTransport();

    @FormUrlEncoded
    @POST(Constants.ADD_Donation)
    Call<DataModel> donationApi(
            @Field("name") String name,
            @Field("address") String address,
            @Field("phone") String phone,
            @Field("amount") String amount,
            @Field("description") String description
    );


    @FormUrlEncoded
    @POST(Constants.ADD_TRANSPORT)
    Call<DataModel> addTransportApi(
            @Field("name") String name,
            @Field("rate") String rate,
            @Field("vehicle_no") String vehicle_name,
            @Field("phone") String phone
    );

    @FormUrlEncoded
    @POST(Constants.ADD_ENQUIRY)
    Call<DataModel> buyApi(
            @Field("product_name") String product_name,
            @Field("product_price") String product_price,
            @Field("seller_name") String seller_name,
            @Field("name") String name,
            @Field("phone") String phone,
            @Field("address") String address,
            @Field("quantity") String quantity
            //@Field("bid_price") String bid_price
    );

    @FormUrlEncoded
    @POST("updateVaccineStatus.php")
    Call<Result> changevaccineStatus(
            @Field("pk_id") String pk_id
    );

    @FormUrlEncoded
    @POST("addUserEnquiry.php")
    Call<DataModel> addEnquiry(
            @Field("name") String name,
            @Field("phone") String phone,
            @Field("remark") String remark
    );


    @FormUrlEncoded
    @POST("addUser.php")
    Call<DataModel> addUser(
            @Field("name") String name,
            @Field("phone") String phone,
            @Field("email") String email,
            @Field("address") String address,
            @Field("role") String role
    );

    @FormUrlEncoded
    @POST("transportLogin.php")
    Call<UserModel> transportLogin(
            @Field("phone") String phone,
            @Field("role") String role
    );


}
