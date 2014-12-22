package com.sefford.kor.samples.core.business.responses;


import com.sefford.kor.samples.model.Contact;

import java.util.Collection;

/**
 * Created by sefford on 28/08/14.
 */
public class GetPhoneContactsResponse extends InteractorResponse {
    Collection<Contact> contacts;

    public Collection<Contact> getContacts() {
        return contacts;
    }

    public void setContacts(Collection<Contact> contacts) {
        this.contacts = contacts;
    }
}
