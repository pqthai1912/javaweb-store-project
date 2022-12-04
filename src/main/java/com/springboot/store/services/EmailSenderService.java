package com.springboot.store.services;

import javax.mail.MessagingException;

public interface EmailSenderService {
	void sendMailWithAttachment(String toEmail,
            String body,
            String subject,
            String attachment) throws MessagingException;
}
