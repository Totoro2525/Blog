package com.totoro.web;

import org.apache.commons.io.IOUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

@RestController
public class ProxyImageController {
    @GetMapping("/proxy-image")
    public void proxyImage(@RequestParam("url") String url, HttpServletResponse response) throws IOException {
        HttpURLConnection conn = null;
        InputStream is = null;
        try {
            URL imageUrl = new URL(url);
            // 动态设置Referer为图片域名
            String referer = imageUrl.getProtocol() + "://" + imageUrl.getHost() + "/";
            conn = (HttpURLConnection) imageUrl.openConnection();
            conn.setRequestProperty("Referer", referer);
            conn.setRequestProperty("User-Agent", "Mozilla/5.0");
            conn.connect();
            String contentType = conn.getContentType();
            if (contentType != null) {
                response.setContentType(contentType);
            }
            is = conn.getInputStream();
            IOUtils.copy(is, response.getOutputStream());
        } finally {
            if (is != null) is.close();
            if (conn != null) conn.disconnect();
        }
    }
} 