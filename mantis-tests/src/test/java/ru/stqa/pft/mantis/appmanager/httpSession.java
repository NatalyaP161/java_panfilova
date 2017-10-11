package ru.stqa.pft.mantis.appmanager;

import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.client.LaxRedirectStrategy;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class httpSession {
    private CloseableHttpClient httpClient;
    private ApplicationManager app;

    public httpSession(ApplicationManager app) {
        this.app = app;
        httpClient = HttpClients.custom().setRedirectStrategy(new LaxRedirectStrategy()).build();
    }

    public boolean login(String username) throws IOException {
        HttpPost post = new HttpPost(app.getProperty("web.baseUrl") + "/mantisbt-2.6.0/login_page.php");
        List<NameValuePair> params = new ArrayList<>();
        params.add(new BasicNameValuePair("username", username));
        params.add(new BasicNameValuePair("secure_session", "on"));
        params.add(new BasicNameValuePair("return", "index.php"));
        post.setEntity(new UrlEncodedFormEntity(params));
        CloseableHttpResponse response = httpClient.execute(post);
        String body = geTextFrom(response);
        return body.contains(String.format("<input id=\"username\" name=\"username\" type=\"text\" placeholder=\"Username\"\n" +
                "\t\t\t\t\t\t   size=\"32\" maxlength=\"191\" value=\"%s\"\n" +
                "\t\t\t\t\t\t   class=\"form-control autofocus\">", username));
    }

    public boolean loginPassword(String username, String password) throws IOException {
        HttpPost post = new HttpPost(app.getProperty("web.baseUrl") + "/mantisbt-2.6.0/login_password_page.php");
        List<NameValuePair> params = new ArrayList<>();
        params.add(new BasicNameValuePair("password", password));
        params.add(new BasicNameValuePair("secure_session", "on"));
        params.add(new BasicNameValuePair("return", "account_page.php"));
        post.setEntity(new UrlEncodedFormEntity(params));
        CloseableHttpResponse response = httpClient.execute(post);
        String body = geTextFrom(response);
        return body.contains(String.format("<span class=\"user-info\">%s</span>", username));
    }

    private String geTextFrom(CloseableHttpResponse response) throws IOException {
        try {
            return EntityUtils.toString(response.getEntity());
        } finally {
            response.close();
        }
    }

    public boolean isLoggedInAs(String username) throws IOException {
        HttpGet get = new HttpGet(app.getProperty("web.baseUrl") + "/account_page.php");
        CloseableHttpResponse response = httpClient.execute(get);
        String body = geTextFrom(response);
        return body.contains(String.format("<span class=\"user-info\">%s</span>", username));
    }
}
