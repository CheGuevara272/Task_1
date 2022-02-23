package com.parshin.postcard.entity;

import java.util.StringJoiner;

public class PostcardInMuseum extends AbstractPostcard {
    private String museumName;
    private String museumLocation;

    public PostcardInMuseum() {
        super();
    }

    public String getMuseumName() {
        return museumName;
    }

    public void setMuseumName(String museumName) {
        this.museumName = museumName;
    }

    public String getMuseumLocation() {
        return museumLocation;
    }

    public void setMuseumLocation(String museumLocation) {
        this.museumLocation = museumLocation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        PostcardInMuseum that = (PostcardInMuseum) o;

        if (museumName != null ? !museumName.equals(that.museumName) : that.museumName != null) return false;
        return museumLocation != null ? museumLocation.equals(that.museumLocation) : that.museumLocation == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (museumName != null ? museumName.hashCode() : 0);
        result = 31 * result + (museumLocation != null ? museumLocation.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", PostcardInMuseum.class.getSimpleName() + "[", "]")
                .add("museumName='" + museumName + "'")
                .add("museumLocation='" + museumLocation + "'")
                .toString();
    }
}
