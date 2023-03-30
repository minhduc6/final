package com.example.springsocial.service;

import com.google.zxing.WriterException;


import javax.mail.MessagingException;
import java.io.IOException;

public interface ISendEmailService {
    String qrCode(String data,String toEmail,
                  String body,
                  String subject,
                  String attachment) throws WriterException, IOException, MessagingException;
}
