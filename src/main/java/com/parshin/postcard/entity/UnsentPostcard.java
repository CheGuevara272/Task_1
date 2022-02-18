package com.parshin.postcard.entity;

import java.util.StringJoiner;

public class UnsentPostcard extends AbstractPostcard {
    private String sent;
    private String currentOwner;

    public UnsentPostcard() {}

    public String getSent() {
        return sent;
    }

    public void setSent(String sent) {
        this.sent = sent;
    }

    public String getCurrentOwner() {
        return currentOwner;
    }

    public void setCurrentOwner(String currentOwner) {
        this.currentOwner = currentOwner;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        UnsentPostcard that = (UnsentPostcard) o;

        if (sent != null ? !sent.equals(that.sent) : that.sent != null) return false;
        return currentOwner != null ? currentOwner.equals(that.currentOwner) : that.currentOwner == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (sent != null ? sent.hashCode() : 0);
        result = 31 * result + (currentOwner != null ? currentOwner.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", UnsentPostcard.class.getSimpleName() + "[", "]")
                .add("sent='" + sent + "'")
                .add("currentOwner='" + currentOwner + "'")
                .toString();
    }
}
