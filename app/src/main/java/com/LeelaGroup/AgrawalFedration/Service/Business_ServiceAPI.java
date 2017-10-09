package com.LeelaGroup.AgrawalFedration.Service;

import com.LeelaGroup.AgrawalFedration.Business_Pojo.ProfileImageFetchPojo;
import com.LeelaGroup.AgrawalFedration.Business_Pojo.ProfileImagePojo;
import com.LeelaGroup.AgrawalFedration.ForgotPasswordPojo;
import com.LeelaGroup.AgrawalFedration.Business_Pojo.BusinessCardPojo;
import com.LeelaGroup.AgrawalFedration.Business_Pojo.BusinessGetCategoryDataPOJO;
import com.LeelaGroup.AgrawalFedration.Business_Pojo.BusinessGetSet;
import com.LeelaGroup.AgrawalFedration.Business_Pojo.BusinessImage;
import com.LeelaGroup.AgrawalFedration.Business_Pojo.BusinessLoginResponsePOJO;
import com.LeelaGroup.AgrawalFedration.Business_Pojo.Business_Registration_Pojo;
import com.LeelaGroup.AgrawalFedration.Business_Pojo.Business_ViewFull_Add_POJO;
import com.LeelaGroup.AgrawalFedration.Business_Pojo.C_S_C_Pojo;
import com.LeelaGroup.AgrawalFedration.Business_Pojo.FranchiseeRegPojo;
import com.LeelaGroup.AgrawalFedration.Business_Pojo.My_Add_Card_Pojo;
import com.LeelaGroup.AgrawalFedration.NotificationPojo;
import com.LeelaGroup.AgrawalFedration.ResetPasswordPojo;
import com.LeelaGroup.AgrawalFedration.notification.NotifyPojo;

import java.util.ArrayList;
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
 * Created by USer on 13-07-2017.
 */

public interface Business_ServiceAPI {

    @GET("Business/BusinessModuleImages.php")
    Call<List<BusinessImage>> getBusinessImage();

    @Multipart
    @POST("Business/advertiser_registration.php")
    Call<BusinessGetSet> AdvertiseRegistrationBusiness(@Part MultipartBody.Part file,
                                                       @Part("reg_id") RequestBody reg_id,
                                                       @Part("Name") RequestBody Name,
                                                       @Part("TagLine") RequestBody TagLine,
                                                       @Part("Email") RequestBody Email,
                                                       @Part("Mobile") RequestBody Mobile,
                                                       @Part("Contact_Number") RequestBody Contact_Number,
                                                       @Part("Address1") RequestBody Address1,
                                                       @Part("Address2") RequestBody Address2,
                                                       @Part("Country") RequestBody Country,
                                                       @Part("Secondary_Country") RequestBody Secondary_Country,
                                                       @Part("State") RequestBody State,
                                                       @Part("SecondaryState") RequestBody SecondaryState,
                                                       @Part("city") RequestBody city,
                                                       @Part("Secondary_City")RequestBody Secondary_City	,
                                                       @Part("Description") RequestBody Description,
                                                       @Part("Long_Description") RequestBody Long_Description,
                                                       @Part("Year") RequestBody Year,
                                                       @Part("Website") RequestBody Website,
                                                       @Part("Certification") RequestBody Certification,
                                                       @Part("Licenses") RequestBody Licenses,
                                                       @Part("Payment_Method") RequestBody Payment_Method,
                                                       @Part("Languages") RequestBody Languages,
                                                       @Part("Category") RequestBody Category,
                                                       @Part("Subcategory") RequestBody Subcategory,
                                                       @Part("Locale") RequestBody Locale,
                                                       @Part("Service_Area") RequestBody Service_Area,
                                                       @Part("working_days") RequestBody working_days,
                                                       @Part("Hours_of_Operation") RequestBody Hours_of_Operation,
                                                       @Part("Specialities") RequestBody Specialities,
                                                       @Part("BusinessType") RequestBody BusinessType,
                                                       @Part("AdvertiserType") RequestBody AdvertiserType

    );

    @FormUrlEncoded
    @POST("Business/franchisee_registration.php")
    Call<FranchiseeRegPojo> AddBusinessFranchise(@Field("Fran_fname") String Fran_fname,
                                                @Field("Fran_lname") String Fran_lname,
                                                @Field("Fran_email") String Fran_email,
                                                @Field("Fran_mobile") String Fran_mobile,
                                                @Field("Fran_address") String Fran_address,
                                                @Field("Fran_chapter") String Fran_chapter,
                                                @Field("Fran_country") String Fran_country,
                                                @Field("Fran_state") String Fran_state,
                                                @Field("Fran_city") String Fran_city,
                                                @Field("Fran_gender") String Fran_gender

    );

    @FormUrlEncoded
    @POST("Business/moduleclick_card_display.php")
    Call<List<BusinessCardPojo>> getCard(@Field("cat_id") String cat_id);

    @FormUrlEncoded
    @POST("Business/Business_signup.php")
    Call<Business_Registration_Pojo> business_reg(@Field("user_fname") String user_fname,
                                                        @Field("user_email") String user_email,
                                                        @Field("user_phone") String user_phone,
                                                        @Field("user_pwd") String user_pwd);

    @FormUrlEncoded
    @POST("Business/BusinessLogin.php")
    Call<BusinessLoginResponsePOJO> LoginUser(@Field("user_email") String user_email,
                                              @Field("user_pwd") String user_pwd);

    @FormUrlEncoded
    @POST("Business/User_Adds.php")
    Call<List<My_Add_Card_Pojo>> getMyAdd(@Field("reg_id") String reg_id);

    @FormUrlEncoded
    @POST("Business/OnView_or_OnEdit_Click.php")
    Call<Business_ViewFull_Add_POJO> getFullAdd(@Field("ar_id") String ar_id);

    @GET("Business/getCategoryData.php")
    Call<List<BusinessGetCategoryDataPOJO>> getCtegory();

    @FormUrlEncoded
    @POST("Business/DeleteMyCard.php")
    Call<My_Add_Card_Pojo> deleteCard(@Field("ar_id") String ar_id);

    @FormUrlEncoded
    @POST("Business/city.php")
    Call<List<C_S_C_Pojo>> getCityName(@Field("sname") String sname);

    @FormUrlEncoded
    @POST("Business/state.php")
    Call<List<C_S_C_Pojo>> getStateName(@Field("cname") String cname);

    @GET("Business/country.php")
    Call<List<C_S_C_Pojo>> getCountryName();

    @FormUrlEncoded
    @POST("Business/forgotpassword.php")
    Call<ForgotPasswordPojo> getOtp(@Field("user_email") String getEmail);

    @FormUrlEncoded
    @POST("Business/resetpassword.php")
    Call<ResetPasswordPojo> resetPassword(@Field("user_id")String userid, @Field("user_pwd")String pass);

    @Multipart
    @POST("inserOrUpdateProPic.php")
    Call<ProfileImagePojo> setProfileImage(@Part MultipartBody.Part fileToUpload, @Part("userid")RequestBody id);

    @FormUrlEncoded
    @POST("fetchProPic.php")
    Call<ProfileImageFetchPojo> getProfilePic(@Field("userid") String uid);

    @FormUrlEncoded
    @POST("pushNotification/fcm_insert.php")
    Call<NotificationPojo> updateToken(@Field("user_id") String user_id,@Field("fcm_token") String fcm_token);

    @FormUrlEncoded
    @POST("pushNotification/getNotification.php")
    Call<ArrayList<NotifyPojo>> getNotification(@Field("user_id") String user_id);
}
