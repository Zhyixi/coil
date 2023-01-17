package com.walsin.coil.controller.login;

import com.google.gson.Gson;
import com.walsin.coil.common.result.Result;
import com.walsin.coil.common.utils.ResultUtil;
import com.walsin.coil.domain.model.login.CAS;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.http.ssl.SSLContexts;
import org.apache.poi.ss.formula.functions.T;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.net.ssl.SSLContext;
import javax.websocket.server.PathParam;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;


@Slf4j
@RestController
@CrossOrigin
@Api(tags = "Login")
@RequestMapping("/cas")
public class Login {



    @ApiOperation("CAS登入")
    @RequestMapping(value = "/login", method = RequestMethod.GET, produces = {"application/JSON"})
    public String casLogin(@PathParam("_username") String _username, @PathParam("_password") String _password, @PathParam("_env") String _env) {
        try {
            _password = URLDecoder.decode(_password,"UTF-8");
        } catch (UnsupportedEncodingException e) {

        }

        CAS cas = new CAS();
        cas.setUsername(_username);
        cas.setPassword(_password);
        cas.setAuth(false);

        CloseableHttpClient httpClient = null;
        String casURL = "https://cas.walsin.com:8889/cas/v1/tickets";
        if(_env.equals("ystst") || _env.equals("dev")) {
            casURL = "https://casdev.walsin.com/cas/v1/tickets/";
        }else {
            casURL = "https://cas.walsin.com:8889/cas/v1/tickets";
        }

        if(casURL.startsWith("https")) {
            // https URL : ignore SSL
            SSLContext sslContext;
            try {
                sslContext = SSLContexts.custom().loadTrustMaterial(null, (x509CertChain, authType) -> true)
                        .build();

                httpClient = HttpClients.custom().setSSLHostnameVerifier(NoopHostnameVerifier.INSTANCE)
                        .setSSLContext(sslContext).build();
            } catch (KeyManagementException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (NoSuchAlgorithmException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (KeyStoreException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        } else {
            // http URL : use default
            httpClient = HttpClients.createDefault();
        }

        //Set Proxy
//				HttpHost proxy = new HttpHost("proxy.walsin.inc", 8080, "http");

        //Config
//				RequestConfig config = RequestConfig.custom().setProxy(proxy).build();

        // Post Request
        ArrayList<NameValuePair> pairList = new ArrayList<NameValuePair>();
        pairList.add(new BasicNameValuePair("username", cas.getUsername()));
        pairList.add(new BasicNameValuePair("password", cas.getPassword()));
        pairList.add(new BasicNameValuePair("fromapp", "true"));

        HttpPost httpPost = new HttpPost(casURL);

        httpPost.setHeader("Content-type", "application/x-www-form-urlencoded");
        httpPost.setHeader("charset", "UTF-8");

        StringEntity entity;
        try {
            entity = new StringEntity(URLEncodedUtils.format(pairList, "UTF-8"));
            httpPost.setEntity(entity);
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        //Execute
        CloseableHttpResponse response;
        try {
            response = httpClient.execute(httpPost);
            //Get Entity Content
            String content = EntityUtils.toString(response.getEntity(), "utf-8");

            cas.setRes(content);
            cas.setStatusCode(response.getStatusLine().getStatusCode());

            if(response.getStatusLine().getStatusCode() == 201 || response.getStatusLine().getStatusCode() == 200) {
                Elements result = this.parseContentByDomSelect(content, "form");
                System.out.println("===> Result ");

                for (Element element : result) {
                    System.out.println(element.text());
                    System.out.println("ticket");
                    System.out.println(element.attr("action"));
                }


                cas.setStatusCode(response.getStatusLine().getStatusCode());
                cas.setPassword("***********");
                cas.setAuth(true);
                cas.setTicket(result.get(0).attr("action"));

            }

        } catch (ClientProtocolException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        String json = new Gson().toJson(cas);
        return json;
    }


    private Elements parseContentByDomSelect(String _content, String _domSelecter) {
        Document document = Jsoup.parse(_content);
        String title = document.title();
        log.info("===> title:" + title);
        Elements elements = document.select(_domSelecter);
        return elements;
    }



}
