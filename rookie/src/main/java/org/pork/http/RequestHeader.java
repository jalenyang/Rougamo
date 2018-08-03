package org.pork.http;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class RequestHeader {

    private static final Logger logger = LogManager.getLogger(RequestHeader.class);

    private String requestType;
    private String protocol;
    private String httpVersion;
    private String host;
    private String connection;
    private String contentLength;
    private String origin;
    private String contentType;
    private String cacheControl;
    private String upgradeInsecureRequests;
    private String userAgent;
    private String accept;
    private String acceptEncoding;
    private String acceptLanguage;

    public RequestHeader(String httpHeader) {
        parserHeaders(httpHeader);
    }

    private void parserHeaders(String httpHeader) {
        String[] headers = httpHeader.split("\r\n");
        String type_protocol_version = headers[0];
        String[] arry = type_protocol_version.split("/");
        if (type_protocol_version.split("/").length > 2) {
            this.requestType = type_protocol_version.split("/")[0].trim();
            this.protocol = type_protocol_version.split("/")[1].trim();
            this.httpVersion = type_protocol_version.split("/")[2].trim();
        }

        for (String header : headers) {
            if (header.startsWith("Host:")) {
                this.host = header.substring(header.indexOf("Host:")).trim();
            } else if (header.startsWith("Connection:")) {
                this.connection = header.substring(header.indexOf("Connection:")).trim();
            } else if (header.startsWith("Content-Length:")) {
                this.contentLength = header.substring(header.indexOf("Content-Length:")).trim();
            } else if (header.startsWith("Cache-Control:")) {
                this.cacheControl = header.substring(header.indexOf("Cache-Control:")).trim();
            } else if (header.startsWith("Origin:")) {
                this.origin = header.substring(header.indexOf("Origin:")).trim();
            } else if (header.startsWith("User-Agent:")) {
                this.userAgent = header.substring(header.indexOf("User-Agent:")).trim();
            } else if (header.startsWith("Content-Type:")) {
                this.contentType = header.substring(header.indexOf("Content-Type:")).trim();
            } else if (header.startsWith("Accept:")) {
                this.accept = header.substring(header.indexOf("Accept:")).trim();
            } else if (header.startsWith("Accept-Encoding:")) {
                this.acceptEncoding = header.substring(header.indexOf("Accept-Encoding:")).trim();
            } else if (header.startsWith("Accept-Language:")) {
                this.acceptLanguage = header.substring(header.indexOf("Accept-Language:")).trim();
            }
        }
    }


    public String getRequestType() {
        return requestType;
    }

    public void setRequestType(String requestType) {
        this.requestType = requestType;
    }

    public String getHttpVersion() {
        return httpVersion;
    }

    public void setHttpVersion(String httpVersion) {
        this.httpVersion = httpVersion;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getConnection() {
        return connection;
    }

    public void setConnection(String connection) {
        this.connection = connection;
    }

    public String getCacheControl() {
        return cacheControl;
    }

    public void setCacheControl(String cacheControl) {
        this.cacheControl = cacheControl;
    }

    public String getUpgradeInsecureRequests() {
        return upgradeInsecureRequests;
    }

    public void setUpgradeInsecureRequests(String upgradeInsecureRequests) {
        this.upgradeInsecureRequests = upgradeInsecureRequests;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    public String getAccept() {
        return accept;
    }

    public void setAccept(String accept) {
        this.accept = accept;
    }

    public String getAcceptEncoding() {
        return acceptEncoding;
    }

    public void setAcceptEncoding(String acceptEncoding) {
        this.acceptEncoding = acceptEncoding;
    }

    public String getAcceptLanguage() {
        return acceptLanguage;
    }

    public void setAcceptLanguage(String acceptLanguage) {
        this.acceptLanguage = acceptLanguage;
    }

}
