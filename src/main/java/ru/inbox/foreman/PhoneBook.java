package ru.inbox.foreman;


import ru.inbox.foreman.coverter.ContactConverter;
import ru.inbox.foreman.coverter.ContactValidationConverter;
import ru.inbox.foreman.dao.ContactDao;
import ru.inbox.foreman.service.ContactService;

/**
 * Created by Anna on 14.06.2017.
 */
public class PhoneBook {

    public static ContactDao contactDao = new ContactDao();

    public static ContactService phoneBookService = new ContactService();

    public static ContactConverter contactConverter = new ContactConverter();

    public static ContactValidationConverter contactValidationConverter = new ContactValidationConverter();
}
