package com.parshin.postcard.entity;

import java.time.Year;
import java.util.StringJoiner;

public class AbstractPostcard {
    private String postcardId;
    private String author;
    private String theme;
    private Year year;
    private String type;
    private String country;
    private String valuable;


    public AbstractPostcard() {
    }

    public String getPostcardId() {
        return postcardId;
    }

    public void setPostcardId(String postcardId) {
        this.postcardId = postcardId;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public Year getYear() {
        return year;
    }

    public void setYear(Year year) {
        this.year = year;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getValuable() {
        return valuable;
    }

    public void setValuable(String valuable) {
        this.valuable = valuable;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AbstractPostcard postcard = (AbstractPostcard) o;

        if (postcardId != null ? !postcardId.equals(postcard.postcardId) : postcard.postcardId != null) return false;
        if (author != null ? !author.equals(postcard.author) : postcard.author != null) return false;
        if (theme != null ? !theme.equals(postcard.theme) : postcard.theme != null) return false;
        if (year != null ? !year.equals(postcard.year) : postcard.year != null) return false;
        if (type != null ? !type.equals(postcard.type) : postcard.type != null) return false;
        if (country != null ? !country.equals(postcard.country) : postcard.country != null) return false;
        return valuable != null ? valuable.equals(postcard.valuable) : postcard.valuable == null;
    }

    @Override
    public int hashCode() {
        int result = postcardId != null ? postcardId.hashCode() : 0;
        result = 31 * result + (author != null ? author.hashCode() : 0);
        result = 31 * result + (theme != null ? theme.hashCode() : 0);
        result = 31 * result + (year != null ? year.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (country != null ? country.hashCode() : 0);
        result = 31 * result + (valuable != null ? valuable.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", AbstractPostcard.class.getSimpleName() + "[", "]")
                .add("postcardId='" + postcardId + "'")
                .add("author='" + author + "'")
                .add("theme='" + theme + "'")
                .add("year=" + year)
                .add("type='" + type + "'")
                .add("country='" + country + "'")
                .add("valuable='" + valuable + "'")
                .toString();
    }
}
