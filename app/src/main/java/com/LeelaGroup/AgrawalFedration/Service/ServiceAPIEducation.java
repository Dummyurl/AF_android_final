package com.LeelaGroup.AgrawalFedration.Service;

import com.LeelaGroup.AgrawalFedration.Education_Pojos.CETDetailPojo;
import com.LeelaGroup.AgrawalFedration.Education_Pojos.EducationDetailPojo;
import com.LeelaGroup.AgrawalFedration.Education_Pojos.EducationLoginPojo;
import com.LeelaGroup.AgrawalFedration.Education_Pojos.EducationNewsPojo;
import com.LeelaGroup.AgrawalFedration.Education_Pojos.ExamSubjectPojo;
import com.LeelaGroup.AgrawalFedration.Education_Pojos.FamilyDetailPojo;
import com.LeelaGroup.AgrawalFedration.Education_Pojos.FeatchPojo;
import com.LeelaGroup.AgrawalFedration.Education_Pojos.ImageFeatcPojo;
import com.LeelaGroup.AgrawalFedration.Education_Pojos.OtherDetailPojo;
import com.LeelaGroup.AgrawalFedration.Education_Pojos.PersonalDetailPojo;
import com.LeelaGroup.AgrawalFedration.Education_Pojos.RefferencePojo;
import com.LeelaGroup.AgrawalFedration.Education_Pojos.ServerResponse;
import com.LeelaGroup.AgrawalFedration.ForgotPasswordPojo;
import com.LeelaGroup.AgrawalFedration.ResetPasswordPojo;
import com.bumptech.glide.request.Request;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

/**
 * Created by USer on 08-07-2017.
 */

public interface ServiceAPIEducation {

    @FormUrlEncoded
    @POST(" Education/PersonalDetails.php")
    Call<PersonalDetailPojo> setStudentDetails(@Field("exam") String exam,
                                               @Field("sess") String sess,
                                               @Field("pd_fname") String pd_fname,
                                               @Field("pd_lname") String pd_lname,
                                               @Field("pd_dob") String pd_dob,
                                               @Field("pd_father_name") String pd_father_name,
                                               @Field("pd_mother_name") String pd_mother_name,
                                               @Field("pd_gender") String pd_gender,
                                               @Field("pd_mob1") String pd_mob1,
                                               @Field("pd_mob2") String pd_mob2,
                                               @Field("pd_email") String pd_email,
                                               @Field("pd_addr") String pd_addr,
                                               @Field("pd_pincode") String pd_pincode,
                                               @Field("pd_city") String pd_city,
                                               @Field("pd_state") String pd_state,
                                               @Field("pd_pwd") String pd_pwd
    );



    @FormUrlEncoded
    @POST(" Education/FamilyDetails.php")
    Call<FamilyDetailPojo> setFamilyDetails(@Field("exam") String exam,
                                            @Field("sess") String sess,
                                            @Field("fd_fathr_occup") String fd_fathr_occup,
                                            @Field("fd_fathr_telephone") String fd_fathr_telephone,
                                            @Field("fd_fathr_mob") String fd_fathr_mob,
                                            @Field("fd_fathr_desig") String fd_fathr_desig,
                                            @Field("fd_fathr_income") String fd_fathr_income,
                                            @Field("fd_fathr_pan") String fd_fathr_pan,
                                            @Field("fa_mothr_occup") String fa_mothr_occup,
                                            @Field("fa_mothr_telephone") String fa_mothr_telephone,
                                            @Field("fa_mothr_mob") String fa_mothr_mob,
                                            @Field("fa_mothr_religion") String fa_mothr_religion);

    @FormUrlEncoded
    @POST(" Education/EducationDetails.php")
    Call<EducationDetailPojo> setEducationDetails(@Field("exam") String exam,
                                                  @Field("sess") String sess,
                                                  @Field("ed_ssc_board") String ed_ssc_board,
                                                  @Field("ed_ssc_name") String ed_ssc_name,
                                                  @Field("ed_ssc_roll") String ed_ssc_roll,
                                                  @Field("ed_ssc_year") String ed_ssc_year,
                                                  @Field("ed_ssc_percent") String ed_ssc_percent,
                                                  @Field("ed_ssc_rank") String ed_ssc_rank,


                                                  @Field("ed_hsc_board") String ed_hsc_board,
                                                  @Field("ed_hsc_name") String ed_hsc_name,
                                                  @Field("ed_hsc_roll") String ed_hsc_roll,
                                                  @Field("ed_hsc_year") String ed_hsc_year,
                                                  @Field("ed_hsc_percent") String ed_hsc_percent,
                                                  @Field("ed_hsc_rank") String ed_hsc_rank,
                                                  @Field("ed_gd_university") String ed_gd_university,
                                                  @Field("ed_gd_inst") String ed_gd_inst,
                                                  @Field("ed_gd_degree_name") String ed_gd_degree_name,
                                                  @Field("ed_gd_year") String ed_gd_year,
                                                  @Field("ed_gd_percent") String ed_gd_percent,
                                                  @Field("ed_gd_rank") String ed_gd_rank,
                                                  @Field("ed_gd_appear_final") String ed_gd_appear_final,
                                                  @Field("ed_pg_university") String ed_pg_university,
                                                  @Field("ed_pg_inst") String ed_pg_inst,
                                                  @Field("ed_pg_degree_name") String ed_pg_degree_name,
                                                  @Field("ed_pg_year") String ed_pg_year,
                                                  @Field("ed_pg_percent") String ed_pg_percent,
                                                  @Field("ed_pg_rank") String ed_pg_rank,
                                                  @Field("ed_pg_menu") String ed_pg_menu);

    @FormUrlEncoded
    @POST(" Education/CetDetails.php")
    Call<CETDetailPojo> setCETDetails(@Field("exam") String exam,
                                      @Field("sess") String sess,
                                      @Field("cet_get_facility") String cet_get_facility,
                                      @Field("cd_cm_from") String cd_cm_from,
                                      @Field("cd_lang") String cd_lang,
                                      @Field("cd_schedule") String cd_schedule,
                                      @Field("cd_exam_center") String cd_exam_center);

    @FormUrlEncoded
    @POST("Education/OtherDetails.php")
    Call<OtherDetailPojo> setOtherDetails(@Field("exam") String exam,
                                          @Field("sess") String sess,
                                          @Field("other_activity") String other_activity,
                                          @Field("other_national_level") String other_national_level,
                                          @Field("other_year") String other_year,
                                          @Field("other_pos") String other_pos,
                                          @Field("other_achieve") String other_achieve,
                                          @Field("other_hobbies") String other_hobbies);

    @FormUrlEncoded
    @POST(" Education/AllDataInsert.php")
    Call<RefferencePojo> setRefferenceDetails(@Field("exam") String exam,
                                              @Field("sess") String sess,
                                              @Field("pd_fname") String pd_fname,
                                              @Field("pd_lname") String pd_lname,
                                              @Field("pd_dob") String pd_dob,
                                              @Field("pd_father_name") String pd_father_name,
                                              @Field("pd_mother_name") String pd_mother_name,
                                              @Field("pd_gender") String pd_gender,
                                              @Field("pd_mob1") String pd_mob1,
                                              @Field("pd_mob2") String pd_mob2,
                                              @Field("pd_email") String pd_email,
                                              @Field("pd_addr") String pd_addr,
                                              @Field("pd_pincode") String pd_pincode,
                                              @Field("pd_city") String pd_city,
                                              @Field("pd_state") String pd_state,
                                              @Field("pd_pwd") String pd_pwd,
                                              @Field("fd_fathr_occup") String fd_fathr_occup,
                                              @Field("fd_fathr_telephone") String fd_fathr_telephone,
                                              @Field("fd_fathr_mob") String fd_fathr_mob,
                                              @Field("fd_fathr_desig") String fd_fathr_desig,
                                              @Field("fd_fathr_income") String fd_fathr_income,
                                              @Field("fd_fathr_pan") String fd_fathr_pan,
                                              @Field("fa_mothr_occup") String fa_mothr_occup,
                                              @Field("fa_mothr_telephone") String fa_mothr_telephone,
                                              @Field("fa_mothr_mob") String fa_mothr_mob,
                                              @Field("fa_mothr_religion") String fa_mothr_religion,
                                              @Field("ed_ssc_board") String ed_ssc_board,
                                              @Field("ed_ssc_name") String ed_ssc_name,
                                              @Field("ed_ssc_roll") String ed_ssc_roll,
                                              @Field("ed_ssc_year") String ed_ssc_year,
                                              @Field("ed_ssc_percent") String ed_ssc_percent,
                                              @Field("ed_ssc_rank") String ed_ssc_rank,


                                              @Field("ed_hsc_board") String ed_hsc_board,
                                              @Field("ed_hsc_name") String ed_hsc_name,
                                              @Field("ed_hsc_roll") String ed_hsc_roll,
                                              @Field("ed_hsc_year") String ed_hsc_year,
                                              @Field("ed_hsc_percent") String ed_hsc_percent,
                                              @Field("ed_hsc_rank") String ed_hsc_rank,
                                              @Field("ed_gd_university") String ed_gd_university,
                                              @Field("ed_gd_inst") String ed_gd_inst,
                                              @Field("ed_gd_degree_name") String ed_gd_degree_name,
                                              @Field("ed_gd_year") String ed_gd_year,
                                              @Field("ed_gd_percent") String ed_gd_percent,
                                              @Field("ed_gd_rank") String ed_gd_rank,
                                              @Field("ed_gd_appear_final") String ed_gd_appear_final,
                                              @Field("ed_pg_university") String ed_pg_university,
                                              @Field("ed_pg_inst") String ed_pg_inst,
                                              @Field("ed_pg_degree_name") String ed_pg_degree_name,
                                              @Field("ed_pg_year") String ed_pg_year,
                                              @Field("ed_pg_percent") String ed_pg_percent,
                                              @Field("ed_pg_rank") String ed_pg_rank,
                                              @Field("ed_pg_menu") String ed_pg_menu,
                                              @Field("cet_get_facility") String cet_get_facility,
                                              @Field("cd_cm_from") String cd_cm_from,
                                              @Field("cd_lang") String cd_lang,
                                              @Field("cd_schedule") String cd_schedule,
                                              @Field("cd_exam_center") String cd_exam_center,
                                              @Field("es_select_exam") String es_select_exam,
                                              @Field("es_upsc_exam") String es_upsc_exam,
                                              @Field("es_upsc_prel_year") String es_upsc_prel_year,
                                              @Field("es_upsc_op_sub") String es_upsc_op_sub,
                                              @Field("es_upsc_coach_sub") String es_upsc_coach_sub,
                                              @Field("es_upsc_lang") String es_upsc_lang,
                                              @Field("es_state_exam") String es_state_exam,
                                              @Field("es_state_prel_year") String es_state_prel_year,
                                              @Field("es_state_prel_state") String es_state_prel_state,
                                              @Field("es_state_op_sub") String es_state_op_sub,
                                              @Field("es_state_coach_sub") String es_state_coach_sub,
                                              @Field("es_state_lang") String es_state_lang,
                                              @Field("es_past_attn_upsc") String es_past_attn_upsc,
                                              @Field("other_activity") String other_activity,
                                              @Field("other_national_level") String other_national_level,
                                              @Field("other_year") String other_year,
                                              @Field("other_pos") String other_pos,
                                              @Field("other_achieve") String other_achieve,
                                              @Field("other_hobbies") String other_hobbies,
                                              @Field("ref1_name") String ref1_name,
                                              @Field("ref1_addr") String ref1_addr,
                                              @Field("ref1_city") String ref1_city,
                                              @Field("ref1_mobile") String ref1_mobile,
                                              @Field("ref1_telephone") String ref1_telephone,
                                              @Field("ref1_email") String ref1_email,
                                              @Field("ref2_name") String ref2_name,
                                              @Field("ref2_addr") String ref2_addr,
                                              @Field("ref2_city") String ref2_city,
                                              @Field("ref2_mobile") String ref2_mobile,
                                              @Field("ref2_telephone") String ref2_telephone,
                                              @Field("ref2_email") String ref2_email);

    @FormUrlEncoded
    @POST("Education/ExamDetails.php")
    Call<ExamSubjectPojo> setExamDetails(@Field("exam") String exam,
                                         @Field("sess") String sess,
                                         @Field("es_select_exam") String es_select_exam,
                                         @Field("es_upsc_exam") String es_upsc_exam,
                                         @Field("es_upsc_prel_year") String es_upsc_prel_year,
                                         @Field("es_upsc_op_sub") String es_upsc_op_sub,
                                         @Field("es_upsc_coach_sub") String es_upsc_coach_sub,
                                         @Field("es_upsc_lang") String es_upsc_lang,
                                         @Field("es_state_exam") String es_state_exam,
                                         @Field("es_state_prel_year") String es_state_prel_year,
                                         @Field("es_state_prel_state") String es_state_prel_state,
                                         @Field("es_state_op_sub") String es_state_op_sub,
                                         @Field("es_state_coach_sub") String es_state_coach_sub,
                                         @Field("es_state_lang") String es_state_lang,
                                         @Field("es_past_attn_upsc") String es_past_attn_upsc);

    @Multipart
    @POST(" Education/upload_multiple_files.php")
    Call <ServerResponse> uploadMulFile(@Part("sess") RequestBody sess,@Part MultipartBody.Part file1, @Part MultipartBody.Part file2 , @Part MultipartBody.Part file3 , @Part MultipartBody.Part file4 , @Part MultipartBody.Part file5 , @Part MultipartBody.Part file6);

    @Multipart
    @POST(" Education/upload_profile_files.php")
    Call <ServerResponse> ProfilePicture(@Part("sess") RequestBody sess, @Part MultipartBody.Part file5);

    @Multipart
    @POST(" Education/upload_ssc_files.php")
    Call <ServerResponse> SSCPicture(@Part("sess") RequestBody sess, @Part MultipartBody.Part file1);

    @Multipart
    @POST(" Education/upload_hsc_files.php")
    Call <ServerResponse> HSCPicture(@Part("sess") RequestBody sess, @Part MultipartBody.Part file2);

    @Multipart
    @POST(" Education/upload_graduation_files.php")
    Call <ServerResponse> GradPicture(@Part("sess") RequestBody sess, @Part MultipartBody.Part file3);

    @Multipart
    @POST(" Education/upload_postgraduation_files.php")
    Call <ServerResponse> PostGradPicture(@Part("sess") RequestBody sess, @Part MultipartBody.Part file4);

    @Multipart
    @POST(" Education/upload_sign_files.php")
    Call <ServerResponse> SignPicture(@Part("sess") RequestBody sess, @Part MultipartBody.Part file6);

    @FormUrlEncoded
    @POST(" Education/featch_image.php")
    Call<ImageFeatcPojo> getImageDeTail(@Field("sess") String sess);

    @FormUrlEncoded
    @POST(" Education/Education_personal_detail_fetch.php")
    Call<PersonalDetailPojo> getPersonalDeTail(@Field("sess") String sess);

    @FormUrlEncoded
    @POST(" Education/Education_family_detail_fetch.php")
    Call<FamilyDetailPojo> getFamilyDeTail(@Field("sess") String sess);

    @FormUrlEncoded
    @POST(" Education/Education_education_detail_fetch.php")
    Call<EducationDetailPojo> getEducationDeTail(@Field("sess") String sess);

    @FormUrlEncoded
    @POST(" Education/Education_cet_detail_fetch.php")
    Call<CETDetailPojo> getCETDeTail(@Field("sess") String sess);

    @FormUrlEncoded
    @POST(" Education/Education_examsubject_detail_fetch.php")
    Call<ExamSubjectPojo> getExamSubjectDeTail(@Field("sess") String sess);

    @FormUrlEncoded
    @POST(" Education/Education_other_detail_fetch.php")
    Call<OtherDetailPojo> getOtherDeTail(@Field("sess") String sess);

    @FormUrlEncoded
    @POST(" Education/Education_refference_detail_fetch.php")
    Call<RefferencePojo> getRefferenceDeTail(@Field("sess") String sess);

    @GET(" Education/Fetch/Education_all_fetch.php")
    Call<List<FeatchPojo>> getFeatchDeTail();

    @FormUrlEncoded
    @POST(" Education/edu_log.php")
    Call<EducationLoginPojo> setLogin(@Field("pd_email") String pd_email,
                                      @Field("pd_pwd") String pd_pwd);


    @FormUrlEncoded
    @POST(" Education/EmailChecker.php")
    Call<PersonalDetailPojo> CheckEmail(@Field("pd_email") String pd_email);

    @GET("Education/getState.php")
    Call<List<PersonalDetailPojo>> fetchState();

    @FormUrlEncoded
    @POST("Education/getCityData.php")
    Call<List<PersonalDetailPojo>> getCityData(@Field("sname") String sname);

    @FormUrlEncoded
    @POST("Education/sendOtp.php")
    Call<ForgotPasswordPojo> getEduOtp(@Field("pd_email")String getEmail);

    @FormUrlEncoded
    @POST("Education/education_resetpass.php")
    Call<ResetPasswordPojo> resetPassword(@Field("pd_id")String userid,
                                          @Field("pd_pwd")String pass);

    @GET("articles?source=the-times-of-india&sortBy=top&apiKey=fe3da327f09c44f4bbedf589e936aff6")
    Call<EducationNewsPojo>getNews();
}