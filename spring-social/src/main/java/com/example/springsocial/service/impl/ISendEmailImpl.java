package com.example.springsocial.service.impl;

import com.example.springsocial.service.ISendEmailService;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.itextpdf.html2pdf.HtmlConverter;
import java.io.File;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.nio.file.FileSystems;
import java.nio.file.Path;

@Service
public class ISendEmailImpl implements ISendEmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Override
    public String qrCode(String data, String toEmail,
                         String body,
                         String subject,
                         String attachment) throws WriterException, IOException, MessagingException {

            QRCodeWriter qrCodeWriter = new QRCodeWriter();
            BitMatrix matrix = qrCodeWriter.encode(data, BarcodeFormat.QR_CODE, 200, 200);
            // Write to file image
            String outputFile = "qr-codes/image.png";
            Path path = FileSystems.getDefault().getPath(outputFile);
            MatrixToImageWriter.writeToPath(matrix, "PNG", path);
            HtmlConverter.convertToPdf(new File("./pdf-input.html"),new File("demo-html.pdf"));
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
            mimeMessageHelper.setTo(toEmail);
            mimeMessageHelper.setText(data);
            mimeMessageHelper.setSubject(subject);
            FileSystemResource fileSystemResource =
                    new FileSystemResource(new File("demo-html.pdf"));
            mimeMessageHelper.addAttachment(fileSystemResource.getFilename(),
                    fileSystemResource);
            javaMailSender.send(mimeMessage);

        return data;
    }


}
