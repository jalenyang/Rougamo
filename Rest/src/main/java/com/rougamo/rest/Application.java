package com.rougamo.rest;


import org.apache.http.client.HttpClient;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.util.HashMap;
import java.util.Map;

public class Application {
    public static void main(String[] args) throws NoSuchAlgorithmException, KeyStoreException, KeyManagementException, IOException, CertificateException {
        String url = "https://clab1607lbwas.netact.nsn-rdnet.net:9997/adapt-mgr/api/cso/v1/adaptation";

        Map<String, String> param = new HashMap<>();
        param.put("deploymentType","deploy");

        HttpClient client = HttpClientUtils.acceptsUntrustedCertsHttpClient();
        ClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory(client);
        RestTemplate template = new RestTemplate(factory);
        template.postForLocation(url, null, param);
    }

}
