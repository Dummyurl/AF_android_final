package com.LeelaGroup.AgrawalFedration.Education_Pojos;

/**
 * Created by USer on 25-09-2017.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Neeraj on 9/22/2017.
 */

public class EducationNewsPojo {

    @SerializedName("articles")
    @Expose
    private List<Article> articles = null;

    public List<Article> getArticles() {
        return articles;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }
}

