package com.parshin.postcard.entity;

import java.util.Objects;
import java.util.StringJoiner;

public class SentPostcard extends AbstractPostcard {
    private String sent;
    private PostalInformation postalInformation;


    public SentPostcard() {
        super();
    }

    public String getSent() {
        return sent;
    }

    public void setSent(String sent) {
        this.sent = sent;
    }

    public PostalInformation getPostalInformation() {
        return postalInformation;
    }

    public void setPostalInformation(PostalInformation postalInformation) {
        this.postalInformation = postalInformation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        SentPostcard that = (SentPostcard) o;

        if (sent != null ? !sent.equals(that.sent) : that.sent != null) return false;
        return postalInformation != null ? postalInformation.equals(that.postalInformation) : that.postalInformation == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (sent != null ? sent.hashCode() : 0);
        result = 31 * result + (postalInformation != null ? postalInformation.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", SentPostcard.class.getSimpleName() + "[", "]")
                .add("sent='" + sent + "'")
                .add("postalInformation=" + postalInformation)
                .toString();
    }
}
