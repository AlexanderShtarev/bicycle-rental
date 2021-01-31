package com.epam.jwd.domain;

import com.epam.jwd.context.annotation.Column;
import com.epam.jwd.context.annotation.Length;

import java.util.Objects;

public class Image extends Entity implements Identified<Long> {

    @Column(name = "title")
    @Length(min = 1, max = 128)
    String title;

    @Column(name = "image_link")
    String imageLink;


    public Image() {
    }

    public Image(Image.Builder builder) {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Image image = (Image) o;
        return id.equals(image.id) &&
                title.equals(image.title) &&
                imageLink.equals(image.imageLink);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, imageLink);
    }

    @Override
    public String toString() {
        return "Image{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", imageLink='" + imageLink + '\'' +
                '}';
    }

    public static Image.Builder builder() {
        return new Image.Builder();
    }

    public static class Builder {

        Long id;
        String imageLink;
        String title;

        private Builder() {
        }

        public Image.Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Image.Builder title(String title) {
            this.title = title;
            return this;
        }

        public Image.Builder imageLink(String imageLink) {
            this.imageLink = imageLink;
            return this;
        }

        public Image build() {
            Image image = new Image(this);
            image.id = id;
            image.imageLink = imageLink;
            image.title = title;
            return image;
        }

    }

}
