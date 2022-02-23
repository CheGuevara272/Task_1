package com.parshin.postcard.entity;

import java.util.StringJoiner;

public class PostcardInPrivateCollection extends AbstractPostcard {
    private String collectorsName;
    private String collectionLocation;

    public PostcardInPrivateCollection() {
    }

    public String getCollectorsName() {
        return collectorsName;
    }

    public void setCollectorsName(String collectorsName) {
        this.collectorsName = collectorsName;
    }

    public String getCollectionLocation() {
        return collectionLocation;
    }

    public void setCollectionLocation(String collectionLocation) {
        this.collectionLocation = collectionLocation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        PostcardInPrivateCollection that = (PostcardInPrivateCollection) o;

        if (collectorsName != null ? !collectorsName.equals(that.collectorsName) : that.collectorsName != null)
            return false;
        return collectionLocation != null ? collectionLocation.equals(that.collectionLocation) : that.collectionLocation == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (collectorsName != null ? collectorsName.hashCode() : 0);
        result = 31 * result + (collectionLocation != null ? collectionLocation.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", PostcardInPrivateCollection.class.getSimpleName() + "[", "]")
                .add("collectorsName='" + collectorsName + "'")
                .add("collectionLocation='" + collectionLocation + "'")
                .toString();
    }
}
