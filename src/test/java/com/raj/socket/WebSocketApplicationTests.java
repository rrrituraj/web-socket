package com.raj.socket;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

//@SpringBootTest
class WebSocketApplicationTests {

    @Autowired
    JavaMailSender javaMailSender;

    @Test
    void contextLoads() {
    }

    @Test
    void scrapper() throws IOException {
        final String URL = "https://www.amazon.in/gp/product/B00F2GQ0SI?pf_rd_p=649eac15-05ce-45c0-86ac-3e413b8ba3d4&pf_rd_r=DW8KMNBP998BACSG4F8W";
        final String userAgent = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.88 Safari/537.36";

        Document document = Jsoup.connect(URL).userAgent(userAgent).get();

        String titlle = document.getElementById("productTitle").text();
        String price = document.getElementById("priceblock_dealprice").text().substring(2);
        Float converted_price = Float.valueOf(price);

        if (converted_price > 700) {
            //sendEmail(URL);
        }

        System.out.println(titlle + "   " + converted_price);
    }

    private void sendEmail(String url) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo("guptamaharaj@gmail.com");
        mailMessage.setSubject("Price Fell down");
        mailMessage.setText("Check the amazon link " + url);
        mailMessage.setFrom("ennaswamiayyer@gmail.com");
        javaMailSender.send(mailMessage);
    }

}
