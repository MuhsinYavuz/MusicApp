package com.muhsinyavuz.musicapplication.model

import com.google.gson.annotations.SerializedName

data class Response(
    @SerializedName("feed")
    val feed: Feed
)

data class Feed(
    @SerializedName("author")
    val author: Author,
    @SerializedName("copyright")
    val copyright: String,
    @SerializedName("country")
    val country: String,
    @SerializedName("icon")
    val icon: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("links")
    val links: List<Link>,
    @SerializedName("results")
    val results: ArrayList<Result>,
    @SerializedName("title")
    val title: String,
    @SerializedName("updated")
    val updated: String
)

data class Author(
    @SerializedName("name")
    val name: String,
    @SerializedName("url")
    val url: String
)

data class Link(
    @SerializedName("self")
    val self: String
)

data class Result(
    @SerializedName("artistId")
    val artistId: String,
    @SerializedName("artistName")
    val artistName: String,
    @SerializedName("artistUrl")
    val artistUrl: String,
    @SerializedName("artworkUrl100")
    val artworkUrl100: String,
    @SerializedName("contentAdvisoryRating")
    val contentAdvisoryRating: String,
    @SerializedName("genres")
    val genres: List<Genre>?=null,
    @SerializedName("id")
    val id: String,
    @SerializedName("kind")
    val kind: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("releaseDate")
    val releaseDate: String,
    @SerializedName("url")
    val url: String
)

data class Genre(
    @SerializedName("genreId")
    val genreId: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("url")
    val url: String
)
