package com.LeelaGroup.AgrawalFedration.Service;

import com.LeelaGroup.AgrawalFedration.ResetPasswordPojo;
import com.LeelaGroup.AgrawalFedration.ForgotPasswordPojo;
import com.LeelaGroup.AgrawalFedration.Medical_Pojos.Medical;
import com.LeelaGroup.AgrawalFedration.Medical_Pojos.Medical_Registration;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

/**
 * Created by USer on 06-07-2017.
 */

public interface MedicalServiceAPI {

    @FormUrlEncoded
    @POST("Medical/med_log.php")
    Call<Medical_Registration> getLogin(@Field("user_email") String user_email,
                                        @Field("user_pwd") String user_pwd);

    @Multipart
    @POST("Medical/insert_Service.php")
    Call<Medical> update_uploade_file(@Part MultipartBody.Part file,
                                      @Part("med_type") RequestBody med_type,
                                      @Part("med_name") RequestBody med_name,
                                      @Part("med_addr") RequestBody med_addr,
                                      @Part("med_pincode") RequestBody med_pincode,
                                      @Part("med_phone") RequestBody med_phone,
                                      @Part("med_country") RequestBody med_country,
                                      @Part("med_state") RequestBody med_state,
                                      @Part("med_ciy") RequestBody med_ciy,
                                      @Part("med_educ") RequestBody med_educ,
                                      @Part("med_open_time") RequestBody med_open_time,
                                      @Part("med_close_time") RequestBody med_close_time,
                                      @Part("med_about") RequestBody med_about,
                                      @Part("med_website") RequestBody med_website,

                                      @Part("med_cont_name") RequestBody med_cont_name,
                                      @Part("med_cont_phone") RequestBody med_cont_phone,
                                      @Part("med_cont_email") RequestBody med_cont_email,
                                      @Part("med_cont_desig") RequestBody med_cont_desig,
                                      @Part("med_cont_detail") RequestBody med_cont_detail ,
                                      @Part("user_id") RequestBody user_id);

    @FormUrlEncoded
    @POST("Medical/insert_contact_perosn.php")
    Call<Medical> ContactPerson(@Field("med_cont_name") String med_cont_name,
                                @Field("med_cont_phone") String med_cont_phone,
                                @Field("med_cont_email") String med_cont_email,
                                @Field("med_cont_desig") String med_cont_desig,
                                @Field("med_cont_detail") String med_cont_detail);

    @FormUrlEncoded
    @POST("Medical/registration.php")
    Call<Medical_Registration> medicalRegistion(@Field("user_fname") String user_fname,
                                                @Field("user_email") String user_email,
                                                @Field("user_phone") String user_phone,
                                                @Field("user_pwd") String user_pwd);
    @FormUrlEncoded
    @POST("Medical/medical_fetch.php")
    Call<List<Medical>> getImageMedical(@Field("user_id") String user_id);

    @FormUrlEncoded
    @POST("Medical/doctor_fetch.php")
    Call<List<Medical>> getImageDoctor(@Field("user_id") String user_id);

    @FormUrlEncoded
    @POST("Medical/hospital_fetch.php")
    Call<List<Medical>> getImageHospital(@Field("user_id") String user_id);

    @GET("Medical/getCity.php")
    Call<List<Medical>> getCity();

    @FormUrlEncoded
    @POST("Medical/fetch_city_by_name.php")
    Call<List<Medical>> getCityName(@Field("sname")  String sname);

    @FormUrlEncoded
    @POST("Medical/fetch_state_by_name.php")
    Call<List<Medical>> getStateName(@Field("cname")  String cname);


    @GET("Medical/fetch_country_by_name.php")
    Call<List<Medical>> getCountryName();


    @FormUrlEncoded
    @POST("Medical/top_fetch.php")
    Call<List<Medical>> postTopSearch(@Field("med_ciy")  String med_ciy,
                                      @Field("med_type") String med_type);

    @FormUrlEncoded
    @POST("Medical/sendOtp.php")
    Call<ForgotPasswordPojo> getOtp(@Field("user_email") String getEmail);

    @FormUrlEncoded
    @POST("Medical/resetPassword.php")
    Call<ResetPasswordPojo> resetPassword(@Field("user_id")String userid, @Field("user_pwd")String pass);


//    @FormUrlEncoded
//    @POST("Medical/sendOtp.php")
//    Call<ForgotPasswordPojo> getEduOtp(@Field("pd_email")String getEmail);
//
//    @FormUrlEncoded
//    @POST("Medical/Education/education_resetpass.php")
//    Call<ResetPasswordPojo> resetPassword(@Field("pd_id")String userid,
//                                          @Field("pd_pwd")String pass);
}
