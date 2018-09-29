package ru.inbox.foreman.servlet;

import ru.inbox.foreman.PhoneBook;
import ru.inbox.foreman.coverter.ContactConverter;
import ru.inbox.foreman.coverter.ContactValidationConverter;
import ru.inbox.foreman.model.Contact;
import ru.inbox.foreman.service.ContactService;
import ru.inbox.foreman.service.ContactValidation;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.util.List;
import java.util.stream.Collectors;

public class DeleteListContactServlet extends HttpServlet {
    private ContactService phoneBookService = PhoneBook.phoneBookService;
    private ContactConverter contactConverter = PhoneBook.contactConverter;
    private ContactValidationConverter contactValidationConverter = PhoneBook.contactValidationConverter;

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        try (OutputStream responseStream = resp.getOutputStream()) {
            String contactJson = req.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
            List<Contact> contacts = contactConverter.convertListFormJson(contactJson);

            ContactValidation contactListRemoveValidation = phoneBookService.removeListContact(contacts);
            String contactListRemoveValidationJson = contactValidationConverter.convertToJson(contactListRemoveValidation);
            if (!contactListRemoveValidation.isValid()) {
                resp.setStatus(500);
            }

            responseStream.write(contactListRemoveValidationJson.getBytes(Charset.forName("UTF-8")));
        } catch (Exception e) {
            System.out.println("error DeleteContactServlet  POST: ");
            e.printStackTrace();
        }
    }


}
