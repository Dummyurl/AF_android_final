package com.LeelaGroup.AgrawalFedration.Business_Pojo;

import com.LeelaGroup.AgrawalFedration.business.BusinessModuleClick;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by USer on 13-07-2017.
 */

public class BusinessGetSet {



    @SerializedName("mobile")
    String mobile;

    @SerializedName("message")
    String message;


    public String getCity_name() {
        return city_name;
    }

    public void setCity_name(String city_name) {
        this.city_name = city_name;
    }

    @SerializedName("cat_id")
    String cat_id;

    @SerializedName("city_name")
    String city_name;

    public String getCat_id() {
        return cat_id;
    }

    public void setCat_id(String cat_id) {
        this.cat_id = cat_id;
    }

    @SerializedName("logo")
    String logo;


    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    @SerializedName("reg_id")
    String reg_id;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getReg_id() {
        return reg_id;
    }

    public void setReg_id(String reg_id) {
        this.reg_id = reg_id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getTagLine() {
        return TagLine;
    }

    public void setTagLine(String tagLine) {
        TagLine = tagLine;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getMobile() {
        return Mobile;
    }

    public void setMobile(String mobile) {
        Mobile = mobile;
    }

    public String getContact_Number() {
        return Contact_Number;
    }

    public void setContact_Number(String contact_Number) {
        Contact_Number = contact_Number;
    }

    public String getAddress1() {
        return Address1;
    }

    public void setAddress1(String address1) {
        Address1 = address1;
    }

    public String getAddress2() {
        return Address2;
    }

    public void setAddress2(String address2) {
        Address2 = address2;
    }

    public String getCountry() {
        return Country;
    }

    public void setCountry(String country) {
        Country = country;
    }

    public String getSecondary_Country() {
        return Secondary_Country;
    }

    public void setSecondary_Country(String secondary_Country) {
        Secondary_Country = secondary_Country;
    }

    public String getState() {
        return State;
    }

    public void setState(String state) {
        State = state;
    }

    public String getSecondaryState() {
        return SecondaryState;
    }

    public void setSecondaryState(String secondaryState) {
        SecondaryState = secondaryState;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getSecondary_City() {
        return Secondary_City;
    }

    public void setSecondary_City(String secondary_City) {
        Secondary_City = secondary_City;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getLong_Description() {
        return Long_Description;
    }

    public void setLong_Description(String long_Description) {
        Long_Description = long_Description;
    }

    public String getYear() {
        return Year;
    }

    public void setYear(String year) {
        Year = year;
    }

    public String getWebsite() {
        return Website;
    }

    public void setWebsite(String website) {
        Website = website;
    }

    public String getCertification() {
        return Certification;
    }

    public void setCertification(String certification) {
        Certification = certification;
    }

    public String getLicenses() {
        return Licenses;
    }

    public void setLicenses(String licenses) {
        Licenses = licenses;
    }

    public String getPayment_Method() {
        return Payment_Method;
    }

    public void setPayment_Method(String payment_Method) {
        Payment_Method = payment_Method;
    }

    public String getLanguages() {
        return Languages;
    }

    public void setLanguages(String languages) {
        Languages = languages;
    }

    public String getCategory() {
        return Category;
    }

    public void setCategory(String category) {
        Category = category;
    }

    public String getSubcategory() {
        return Subcategory;
    }

    public void setSubcategory(String subcategory) {
        Subcategory = subcategory;
    }

    public String getLocale() {
        return Locale;
    }

    public void setLocale(String locale) {
        Locale = locale;
    }

    public String getService_Area() {
        return Service_Area;
    }

    public void setService_Area(String service_Area) {
        Service_Area = service_Area;
    }

    public String getWorking_days() {
        return working_days;
    }

    public void setWorking_days(String working_days) {
        this.working_days = working_days;
    }

    public String getHours_of_Operation() {
        return Hours_of_Operation;
    }

    public void setHours_of_Operation(String hours_of_Operation) {
        Hours_of_Operation = hours_of_Operation;
    }

    public String getSpecialities() {
        return Specialities;
    }

    public void setSpecialities(String specialities) {
        Specialities = specialities;
    }

    public String getBusinessType() {
        return BusinessType;
    }

    public void setBusinessType(String businessType) {
        BusinessType = businessType;
    }

    public String getAdvertiserType() {
        return AdvertiserType;
    }

    public void setAdvertiserType(String advertiserType) {
        AdvertiserType = advertiserType;
    }

    @SerializedName("Name")

    String Name;

    @SerializedName("TagLine")
    String TagLine;

    @SerializedName("Email")
    String Email;

    @SerializedName("Mobile")
    String Mobile;

    @SerializedName("Contact_Number")
    String Contact_Number;

    @SerializedName("Address1")
    String Address1;

    @SerializedName("Address2")
    String Address2;

    @SerializedName("Country")
    String Country;

    @SerializedName("Secondary_Country")
    String Secondary_Country;

    @SerializedName("State")
    String State;

    @SerializedName("SecondaryState")
    String SecondaryState;

    @SerializedName("city")
    String city;

    @SerializedName("Secondary_City")
    String Secondary_City;

    @SerializedName("Description")
    String Description;

    @SerializedName("Long_Description")
    String Long_Description;

    @SerializedName("Year")
    String Year;

    @SerializedName("Website")
    String Website;

    @SerializedName("Certification")
    String Certification;

    @SerializedName("Licenses")
    String Licenses;

    @SerializedName("Payment_Method")
    String Payment_Method;

    @SerializedName("Languages")
    String Languages;

    @SerializedName("Category")
    String Category;

    @SerializedName("Subcategory")
    String Subcategory;

    @SerializedName("Locale")
    String Locale;

    @SerializedName("Service_Area")
    String Service_Area;

    @SerializedName("working_days")
    String working_days;

    @SerializedName("Hours_of_Operation")
    String Hours_of_Operation;

    @SerializedName("Specialities")
    String Specialities;

    @SerializedName("BusinessType")
    String BusinessType;

    @SerializedName("AdvertiserType")
    String AdvertiserType;






}
