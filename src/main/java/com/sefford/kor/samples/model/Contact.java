package com.sefford.kor.samples.model;

import android.content.ContentUris;
import android.net.Uri;
import android.provider.ContactsContract;
import com.sefford.brender.interfaces.Renderable;
import com.sefford.kor.samples.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Contact class for sending info over the
 *
 * @author Saul Diaz <saul@feverup.com>
 */
public class Contact implements Comparable<Contact>, Renderable {


    long id;
    String image;
    String name;
    List<String> phones = new ArrayList<String>();
    List<String> emails = new ArrayList<String>();

    public Long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getPhones() {
        return phones;
    }

    public void setPhones(List<String> phones) {
        this.phones = phones;
    }

    public void addPhone(String phone) {
        this.phones.add(phone);
    }

    public List<String> getEmails() {
        return emails;
    }

    public void setEmails(List<String> emails) {
        this.emails = emails;
    }

    public void addEmail(String email) {
        this.emails.add(email);
    }

    public String getImage() {
        Uri contactUri = ContentUris.withAppendedId(ContactsContract.Contacts.CONTENT_URI, getId());
        Uri displayPhotoUri = Uri.withAppendedPath(contactUri, "photo");
        return displayPhotoUri.toString();
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Contact contact = (Contact) o;

        if (id != contact.id) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }

    @Override
    public int compareTo(Contact another) {
        return name.compareTo(another.name);
    }

    @Override
    public int getRenderableId() {
        return R.layout.item_contact;
    }
}
