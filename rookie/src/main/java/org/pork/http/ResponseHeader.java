package org.pork.http;

public class ResponseHeader {
    private String httpVersion;
    private String contentType;
    private String httpCode;
    private String TERMINATION_char;

    public String getHttpVersion() {
        return httpVersion;
    }

    public void setHttpVersion(String httpVersion) {
        this.httpVersion = httpVersion;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getHttpCode() {
        return httpCode;
    }

    public void setHttpCode(String httpCode) {
        this.httpCode = httpCode;
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append(String.format("HTTP/%s %s " + TERMINATION_char,httpVersion,httpCode));
        sb.append(String.format("Content-Type: %s " + TERMINATION_char,contentType));
        sb.append(TERMINATION_char);
        return sb.toString();
    }
}
