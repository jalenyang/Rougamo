package com.pork.rest;

import org.apache.http.nio.client.HttpAsyncClient;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.AsyncClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsAsyncClientHttpRequestFactory;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;
import org.springframework.web.client.AsyncRestTemplate;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;

public class AsyncApplication {
    public static void main(String[] args) throws NoSuchAlgorithmException, KeyStoreException, KeyManagementException,
            IOException, CertificateException {
        String url = "https://clab1607lbwas.netact.nsn-rdnet.net:9997/adapt-mgr/api/cso/v1/adaptation";

//        Map<String, String> param = new HashMap<>();
//        param.put("deploymentType", "deploy");

        MultiValueMap<String, String> postParameters = new LinkedMultiValueMap<String, String>();
        postParameters.add("deploymentType", "deploy");

        HttpHeaders headers = new HttpHeaders();

        HttpEntity<MultiValueMap<String, String>> requestEntity  = new HttpEntity<MultiValueMap<String, String>>(postParameters, headers);


        HttpAsyncClient client = AsyncHttpClientUtils.acceptsUntrustedCertsHttpClient();
//        HttpAsyncClient client = AsyncHttpClientUtils.selfSignedHttpClient("D:\\MWSClientStore.jks", "password");
//        HttpAsyncClient client = AsyncHttpClientUtils.selfSignedHttpClient("D:\\lbwas-client-cert.jks", "elm3kh2ifjstmjh3qudd15ibik");

        AsyncClientHttpRequestFactory asyncFactory = new HttpComponentsAsyncClientHttpRequestFactory(client);

        AsyncRestTemplate asyncRestTemplate = new AsyncRestTemplate(asyncFactory);
        ListenableFuture<ResponseEntity<String>> forUri = asyncRestTemplate.postForEntity(url, requestEntity,String.class);
        forUri.addCallback(new ListenableFutureCallback<Object>() {
            @Override
            public void onSuccess(Object o) {
                System.out.println("successfully=======");
            }

            @Override
            public void onFailure(Throwable throwable) {
                System.out.println("Failed=====" + throwable.getMessage());
            }
        });
        System.out.println("successfully55555=======");
    }
}
