package com.LeelaGroup.AgrawalFedration.Service;

import com.LeelaGroup.AgrawalFedration.ForgotPasswordPojo;
import com.LeelaGroup.AgrawalFedration.ResetPasswordPojo;
import com.LeelaGroup.AgrawalFedration.matrimony.models.AboutUsPojo;
import com.LeelaGroup.AgrawalFedration.matrimony.models.AllDetails;
import com.LeelaGroup.AgrawalFedration.matrimony.models.BasicDetailAndContactInfo;
import com.LeelaGroup.AgrawalFedration.matrimony.models.CountryStateCity;
import com.LeelaGroup.AgrawalFedration.matrimony.models.EducationAndOccupationDetails;
import com.LeelaGroup.AgrawalFedration.matrimony.models.EventsDetails;
import com.LeelaGroup.AgrawalFedration.matrimony.models.FetchFilterDetail;
import com.LeelaGroup.AgrawalFedration.matrimony.models.GetRegName;
import com.LeelaGroup.AgrawalFedration.matrimony.models.ImageUploadPojo;
import com.LeelaGroup.AgrawalFedration.matrimony.models.LoginModel;
import com.LeelaGroup.AgrawalFedration.matrimony.models.PartnrPrefdetails;
import com.LeelaGroup.AgrawalFedration.matrimony.models.PhysicalAndOtherDetails;
import com.LeelaGroup.AgrawalFedration.matrimony.models.ProfileIdPojo;
import com.LeelaGroup.AgrawalFedration.matrimony.models.ProfileModel;
import com.LeelaGroup.AgrawalFedration.matrimony.models.RegistrationDetails;
import com.LeelaGroup.AgrawalFedration.matrimony.models.SocialAndFamilyDetails;
import com.LeelaGroup.AgrawalFedration.matrimony.models.SussessStoriesPojo;

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
 * Created by USer on 10-07-2017.
 */

public interface ServiceMatrimony {

    @Multipart
    @POST("Matrimony/mat_basic_cont_reg.php")
    Call<BasicDetailAndContactInfo> setBasicDetail(
            @Part("mat_sess") RequestBody mat_sess,
            @Part MultipartBody.Part mreg_prof_pic,
            @Part("mreg_am") RequestBody mreg_am,
            @Part("mreg_fname") RequestBody mreg_fname,
            @Part("mreg_mname") RequestBody mreg_mname,
            @Part("mreg_lname") RequestBody mreg_lname,
            @Part("mreg_birth_place") RequestBody mreg_birth_place,
            @Part("mreg_birth_time") RequestBody mreg_birth_time,
            @Part("mreg_native_place") RequestBody mreg_native_place,
            @Part("mreg_dob") RequestBody mreg_dob,
            @Part("mreg_age") RequestBody mreg_age,
            @Part("mreg_marital_status") RequestBody mreg_marital_status,
            @Part("mreg_gender") RequestBody mreg_gender,
            @Part("mreg_no_child") RequestBody mreg_no_child,
            @Part("mreg_child_leave_status") RequestBody mreg_child_leave_status,
            @Part("mreg_mother_tongue") RequestBody mreg_mother_tongue,
            @Part("mreg_about_me") RequestBody mreg_about_me

    );

    @FormUrlEncoded
    @POST("Matrimony/mat_cont_update.php")
    Call<BasicDetailAndContactInfo> setContactDetails(@Field("mreg_landline") String mreg_landline,
                                                      @Field("mreg_phone") String mreg_phone,
                                                      @Field("mreg_email") String mreg_email,
                                                      @Field("mreg_addr") String mreg_addr,
                                                      @Field("mreg_country") String mreg_country,
                                                      @Field("mreg_state") String mreg_state,
                                                      @Field("mreg_city") String mreg_city,
                                                      @Field("mreg_pincode") String mreg_pincode,
                                                      @Field("mreg_resid_status") String mreg_resid_status


    );

    @FormUrlEncoded
    @POST("Matrimony/mat_rel_insert.php")
    Call<BasicDetailAndContactInfo> setReligionDetails(@Field("mat_reg_religion") String mat_reg_religion,
                                                       @Field("mat_reg_caste") String mat_reg_caste,
                                                       @Field("mat_reg_subcaste") String mat_reg_subcaste

    );




    @FormUrlEncoded
    @POST("Matrimony/mat_phy_other_reg.php")
    Call<PhysicalAndOtherDetails> setPhysicalDetails(@Field("mreg_phy_ht") String height,
                                                     @Field("mreg_phy_wt") String weight,
                                                     @Field("mreg_bdy_type") String bdyType,
                                                     @Field("mreg_blood_grp") String bldGroup,
                                                     @Field("mreg_complexion") String cmlxn,
                                                     @Field("mreg_handicapped") String handicaped,
                                                     @Field("mreg_smoke") String smoke,
                                                     @Field("mreg_drink") String drink,
                                                     @Field("mreg_diet") String diet,
                                                     @Field("fk_id") String fk_id

    );


    @FormUrlEncoded
    @POST("Matrimony/mat_other_update.php")
    Call<PhysicalAndOtherDetails> setOtherDetails(@Field("mreg_come_frm") String mreg_come_frm,
                                                  @Field("mreg_prof_create_for") String mreg_prof_create_for,
                                                  @Field("mreg_hobby") String mreg_hobby,
                                                  @Field("mreg_interest") String mreg_interest,
                                                  @Field("mreg_pwd") String mreg_pwd
    );


    @FormUrlEncoded
    @POST("Matrimony/mat_qual_occup_reg.php")
    Call<EducationAndOccupationDetails> setEducationDetails(@Field("mat_reg_edu") String mat_reg_edu,
                                                            @Field("mat_reg_pg") String mat_reg_pg,
                                                            @Field("mat_reg_docto") String mat_reg_docto,
                                                            @Field("mat_reg_certifi") String mat_reg_certifi,
                                                            @Field("fk_id") String fk_id
    );

    @FormUrlEncoded
    @POST("Matrimony/mat_occu_update.php")
    Call<EducationAndOccupationDetails> setOccupationDetails(@Field("mat_reg_occup") String mat_reg_occup,
                                                             @Field("mat_reg_industry") String mat_reg_industry,
                                                             @Field("mat_reg_empl") String mat_reg_empl,
                                                             @Field("mat_reg_ipa") String mat_reg_ipa
    );


    @Multipart
    @POST("Matrimony/mat_social_update.php")
    Call <SocialAndFamilyDetails> setSocialDetails(@Part MultipartBody.Part file1,
                                                   @Part MultipartBody.Part file2,
                                                   @Part("mat_reg_manglik") RequestBody mat_reg_manglik,
                                                   @Part("mat_reg_horoscope_match") RequestBody mat_reg_horoscope_match,
                                                   @Part("mat_reg_gothra_self") RequestBody mat_reg_gothra_self,
                                                   @Part("mat_reg_gothra_mama") RequestBody mat_reg_gothra_mama,
                                                   @Part("fk_id") RequestBody fk_id

    );

    @FormUrlEncoded
    @POST("Matrimony/mat_family_update.php")
    Call<SocialAndFamilyDetails> setFamilyDetails(@Field("mat_reg_father_name") String mat_reg_father_name,
                                                  @Field("mat_reg_mother_name") String mat_reg_mother_name,
                                                  @Field("mat_reg_father_occup") String mat_reg_father_occup,
                                                  @Field("mat_reg_mother_occup") String mat_reg_mother_occup,
                                                  @Field("mat_reg_no_brother") String mat_reg_no_brother,
                                                  @Field("mat_reg_no_sister") String mat_reg_no_sister,
                                                  @Field("mat_mar_bro") String mat_mar_bro,
                                                  @Field("mat_mar_sis") String mat_mar_sis,
                                                  @Field("mat_reg_status") String mat_reg_status,
                                                  @Field("mreg_fam_type") String mreg_fam_type,
                                                  @Field("mreg_fam_lpa") String mreg_fam_lpa
    );


    @FormUrlEncoded
    @POST("Matrimony/mat_groom_reg.php")
    Call<PartnrPrefdetails> setPartPrefDetails(@Field("mreg_looking_for") String mreg_looking_for,
                                               @Field("mreg_reg_between") String mreg_reg_between,
                                               @Field("mreg_country_leaving") String mreg_country_leaving,
                                               @Field("mreg_state_leaving") String mreg_state_leaving,
                                               @Field("mreg_city_leaving") String mreg_city_leaving,
                                               @Field("mreg_residential_status") String mreg_residential_status,
                                               @Field("mreg_ht") String mreg_ht,
                                               @Field("mreg_wt") String mreg_wt,
                                               @Field("mreg_groom_complexion") String mreg_groom_complexion,
                                               @Field("mreg_educ") String mreg_educ,
                                               @Field("mreg_religion") String mreg_religion,
                                               @Field("mreg_caste") String mreg_caste,
                                               @Field("fk_id") String fk_id

    );

    @FormUrlEncoded
    @POST("Matrimony/matromonial_registration.php")
    Call<RegistrationDetails> setRegDetaild(@Field("mat_fname") String mat_fname,
                                            @Field("mat_mname") String mat_mname,
                                            @Field("mat_lname") String mat_lname,
                                            @Field("mat_email") String mat_email,
                                            @Field("mat_phone") String mat_phone,
                                            @Field("mat_gender") String mat_gender,
                                            @Field("mat_pwd") String mat_pwd
    );

    @FormUrlEncoded
    @POST("Matrimony/matri_login.php")
    Call<LoginModel> setLoginDetails(@Field("mat_email") String mat_email,
                                     @Field("mat_pwd") String mat_pwd
    );


    @FormUrlEncoded
    @POST("Matrimony/matrimony_fetch/educationFetchMatrimony.php")
    Call<EducationAndOccupationDetails> educationall(@Field("fk_id") String fk_id
    );

    @FormUrlEncoded
    @POST("Matrimony/matrimony_fetch/soc_rel_fetch.php")
    Call<SocialAndFamilyDetails> familysocialAttri(@Field("fk_id") String fk_id
    );

    @FormUrlEncoded
    @POST("Matrimony/matrimony_fetch/other_phy.php")
    Call<PhysicalAndOtherDetails> phyOtherDetail(@Field("fk_id") String fk_id
    );

    @FormUrlEncoded
    @POST("Matrimony/matrimony_fetch/partnerFetch.php")
    Call<PartnrPrefdetails> PartnerSearch(@Field("fk_id") String fk_id
    );

    @FormUrlEncoded
    @POST("Matrimony/matrimony_fetch/Basic_cont_register.php")
    Call<BasicDetailAndContactInfo> BasicContact(@Field("mat_sess") String mat_sess
    );

    @FormUrlEncoded
    @POST("Matrimony/getProfilepic.php")
    Call<LoginModel> getProfilePic(@Field("mat_id") String mat_id);

    @FormUrlEncoded
    @POST("Matrimony/get_filterd_records.php")
    Call<List<FetchFilterDetail>> getPerCard(@Field("mreg_am") String mreg_am,
                                             @Field("mat_reg_caste") String mat_reg_caste,
                                             @Field("mreg_city") String mreg_city

    );
    @FormUrlEncoded
    @POST("Matrimony/fetchBasicAndContactDetails.php")
    Call<BasicDetailAndContactInfo> getBasicDetailsAndContact(@Field("mat_id") String mat_id);

    @FormUrlEncoded
    @POST("Matrimony/fetchFamilyAndSocialDetails.php")
    Call<SocialAndFamilyDetails> getfFamilyAndSocialDetails(@Field("mat_id") String mat_id);

    @FormUrlEncoded
    @POST("Matrimony/fetchEduAndOccuDetails.php")
    Call<EducationAndOccupationDetails> getEduAndOccuDetails(@Field("mat_id") String mat_id);

    @FormUrlEncoded
    @POST("Matrimony/fetchPhyAndOthrDetl.php")
    Call<PhysicalAndOtherDetails> getPhysAndOtherDetls(@Field("mat_id") String mat_id);


    @FormUrlEncoded
    @POST("Matrimony/fetchPartnrPrefDetails.php")
    Call<PartnrPrefdetails> gerPartnrPrefDetails(@Field("mat_id") String mat_id);

    @FormUrlEncoded
    @POST("Matrimony/fetchAllDetails.php")
    Call<AllDetails> getAllDetails(@Field("mat_id") String mat_id);

    @FormUrlEncoded
    @POST("Matrimony/fetchAllDetailsOfProfile.php")
    Call<AllDetails> getAllProfileDetails(@Field("pid") String pid);

    @GET("Matrimony/fetchEventsDetails.php")
    Call<List<EventsDetails>> getEventDetails();

    @GET("Matrimony/fetchSuccessStories.php")
    Call<List<SussessStoriesPojo>> getSuccessStories();

    @POST("Matrimony/getAbouUsDetails.php")
    Call<AboutUsPojo> getAboutDetails();

    @GET("Matrimony/countries.php")
    Call<List<CountryStateCity>> getCountries();

    @GET("Matrimony/states.php")
    Call<List<CountryStateCity>> getStates();

    @GET("Matrimony/city.php")
    Call<List<CountryStateCity>> getCity();

    @Multipart
    @POST("Matrimony/imageUpload.php")
    Call<ImageUploadPojo> uploadImage(@Part MultipartBody.Part fileToUpload);

    @FormUrlEncoded
    @POST("Matrimony/insertAll.php")
    Call<AllDetails> insertAll(@Field("mat_id") String mat_id,
                               @Field("mreg_prof_pic") String imageName,
                               @Field("mreg_am") String mreg_am,
                               @Field("mreg_fname") String mreg_fname,
                               @Field("mreg_mname") String mreg_mname,
                               @Field("mreg_lname") String mreg_lname,
                               @Field("mreg_birth_place") String mreg_birth_place,
                               @Field("mreg_birth_time") String mreg_birth_time,
                               @Field("mreg_native_place") String mreg_native_place,
                               @Field("mreg_dob") String mreg_dob,
                               @Field("mreg_age") String mreg_age,
                               @Field("mreg_marital_status") String mreg_marital_status,
                               @Field("mreg_gender") String mreg_gender,
                               @Field("mreg_no_child") String mreg_no_child,
                               @Field("mreg_child_leave_status") String mreg_child_leave_status,
                               @Field("mreg_mother_tongue") String mreg_mother_tongue,
                               @Field("mreg_about_me") String mreg_about_me,
                               @Field("mat_reg_religion") String mat_reg_religion,
                               @Field("mat_reg_caste") String mat_reg_caste,
                               @Field("mat_reg_subcaste") String mat_reg_subcaste,
                               @Field("mreg_landline") String mreg_landline,
                               @Field("mreg_phone") String mreg_phone,
                               @Field("mreg_email") String mreg_email,
                               @Field("mreg_addr") String mreg_addr,
                               @Field("mreg_country") String mreg_country,
                               @Field("mreg_state") String mreg_state,
                               @Field("mreg_city") String mreg_city,
                               @Field("mreg_pincode") String mreg_pincode,
                               @Field("mreg_resid_status") String mreg_resid_status,
                               @Field("mat_reg_manglik") String mat_reg_manglik,
                               @Field("mat_reg_horoscope_match") String mat_reg_horoscope_match,
                               @Field("mat_reg_gothra_self") String mat_reg_gothra_self,
                               @Field("mat_reg_edu") String mat_reg_edu,
                               @Field("mat_reg_occup") String mat_reg_occup,
                               @Field("mat_reg_industry") String mat_reg_industry,
                               @Field("mat_reg_empl") String mat_reg_empl,
                               @Field("mat_reg_ipa") String mat_reg_ipa,
                               @Field("mat_reg_father_name") String mat_reg_father_name,
                               @Field("mat_reg_mother_name") String mat_reg_mother_name,
                               @Field("mat_reg_father_occup") String mat_reg_father_occup,
                               @Field("mat_reg_mother_occup") String mat_reg_mother_occup,
                               @Field("mat_reg_no_brother") String mat_reg_no_brother,
                               @Field("mat_reg_no_sister") String mat_reg_no_sister,
                               @Field("mat_mar_bro") String mat_mar_bro,
                               @Field("mat_mar_sis") String mat_mar_sis,
                               @Field("mat_reg_status") String mat_reg_status,
                               @Field("mreg_fam_type") String mreg_fam_type,
                               @Field("mreg_fam_lpa") String mreg_fam_lpa,
                               @Field("mreg_phy_ht") String mreg_phy_ht,
                               @Field("mreg_phy_wt") String mreg_phy_wt,
                               @Field("mreg_bdy_type") String mreg_bdy_type,
                               @Field("mreg_blood_grp") String mreg_blood_grp,
                               @Field("mreg_complexion") String mreg_complexion,
                               @Field("mreg_handicapped") String mreg_handicapped,
                               @Field("mreg_smoke") String mreg_smoke,
                               @Field("mreg_drink") String mreg_drink,
                               @Field("mreg_diet") String mreg_diet,
                               @Field("mreg_come_frm") String mreg_come_frm,
                               @Field("mreg_prof_create_for") String mreg_prof_create_for,
                               @Field("mreg_hobby") String mreg_hobby,
                               @Field("mreg_interest") String mreg_interest,
                               @Field("mreg_pwd") String mreg_pwd,
                               @Field("mreg_looking_for") String mreg_looking_for,
                               @Field("mreg_reg_maxage") String mreg_reg_maxage,
                               @Field("mreg_reg_minage") String mreg_reg_minage,
                               @Field("mreg_country_leaving") String mreg_country_leaving,
                               @Field("mreg_state_leaving") String mreg_state_leaving,
                               @Field("mreg_city_leaving") String mreg_city_leaving,
                               @Field("mreg_residential_status") String mreg_residential_status,
                               @Field("mreg_min_ht") String mreg_min_ht,
                               @Field("mreg_max_ht") String mreg_max_ht,
                               @Field("mreg_groom_complexion") String mreg_groom_complexion,
                               @Field("mreg_educ") String mreg_educ,
                               @Field("mreg_religion") String mreg_religion,
                               @Field("mreg_caste") String mreg_caste);

    @FormUrlEncoded
    @POST("Matrimony/getRegisterName.php")
    Call<GetRegName> getRegName(@Field("mat_id") String mat_id);

    @FormUrlEncoded
    @POST("Matrimony/sendOtp.php")
    Call<ForgotPasswordPojo> getOtp(@Field("mat_email") String getEmail);

    @FormUrlEncoded
    @POST("Matrimony/resetPassword.php")
    Call<ResetPasswordPojo> resetPassword(@Field("mat_id") String userid, @Field("mat_pwd") String pass);

    @FormUrlEncoded
    @POST("Matrimony/fetchProfileList.php")
    Call<List<ProfileModel>> getProfileList(@Field("mat_id") String userid);

    @FormUrlEncoded
    @POST("Matrimony/isProfileIdExist.php")
    Call<ProfileIdPojo> isPRofileIDExist(@Field("mat_id") String mat_id);
}




