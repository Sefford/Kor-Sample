package com.sefford.kor.samples.core.business.delegates;

import android.content.Context;
import android.database.Cursor;
import android.provider.ContactsContract;
import com.sefford.kor.interactors.interfaces.CacheDelegate;
import com.sefford.kor.samples.core.business.errors.GetPhoneContactsError;
import com.sefford.kor.samples.core.business.responses.GetPhoneContactsResponse;
import com.sefford.kor.samples.model.Contact;

import java.util.HashMap;
import java.util.Map;

/**
 * Request for contact info from the Phone
 *
 * @author Saul Diaz <sefford@gmail.com>
 */
public class GetPhoneContactsDelegate implements CacheDelegate<GetPhoneContactsResponse, GetPhoneContactsError> {

    final Context context;

    public GetPhoneContactsDelegate(Context context) {
        this.context = context;
    }

    private boolean isVisible(Cursor cursor) {
        return cursor.getInt(cursor.getColumnIndex(ContactsContract.Contacts.IN_VISIBLE_GROUP)) == 1;
    }

    Contact getContact(Map<Long, Contact> contactList, Cursor phones, long id) {
        Contact contact;
        if (contactList.containsKey(id)) {
            contact = contactList.get(id);
        } else {
            contact = new Contact();
            contact.setId(id);
            contact.setName(phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME)));
        }
        return contact;
    }

    @Override
    public GetPhoneContactsResponse retrieveFromCache() {
        // Get Contacts names and phones
        Map<Long, Contact> contactList = new HashMap<Long, Contact>();
        Cursor phones = context.getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                null, null, null, ContactsContract.Contacts.DISPLAY_NAME + " ASC");
        while (phones.moveToNext()) {
            long id = Long.valueOf(phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.CONTACT_ID)));
            String phone = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NORMALIZED_NUMBER));
            if (phone != null && isVisible(phones)) {
                final Contact contact = getContact(contactList, phones, id);
                contact.addPhone(phone);
                contactList.put(contact.getId(), contact);
            }
        }
        phones.close();

        // Get Contacts names and emails
        Cursor emails = context.getContentResolver().query(ContactsContract.CommonDataKinds.Email.CONTENT_URI,
                null, null, null, ContactsContract.Contacts.DISPLAY_NAME + " ASC");
        while (emails.moveToNext()) {
            long id = Long.valueOf(emails.getString(emails.getColumnIndex(ContactsContract.CommonDataKinds.Email.CONTACT_ID)));
            String mail = emails.getString(emails.getColumnIndex(ContactsContract.CommonDataKinds.Email.DATA));
            if (mail != null && isVisible(emails)) {
                final Contact contact = getContact(contactList, emails, id);
                contact.addEmail(mail);
                contactList.put(contact.getId(), contact);
            }
        }
        emails.close();

        final GetPhoneContactsResponse response = new GetPhoneContactsResponse();
        response.setSuccess(true);
        response.setFromNetwork(false);
        response.setContacts(contactList.values());
        return response;
    }

    @Override
    public boolean isCacheValid() {
        return true;
    }

    @Override
    public String getInteractorName() {
        return "GetContactRequest";
    }
}
