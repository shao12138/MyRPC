package org.nudt;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class HTTPTransportClient implements TransportClient {
    private String url;

    public void connect(Peer peer) {
        this.url = "http://" + peer.getHost() + ":" + peer.getPort();

    }

    public InputStream write(InputStream data) {
        try {
            HttpURLConnection httpConn = (HttpURLConnection) new URL(url).openConnection();
            httpConn.setDoInput(true);
            httpConn.setDoOutput(true);
            httpConn.setUseCaches(false);
            httpConn.setRequestMethod("POST");
            httpConn.connect();

            IOUtils.copy(data, httpConn.getOutputStream());

            int resultCode = httpConn.getResponseCode();
            if (resultCode == HttpURLConnection.HTTP_OK) {
                return httpConn.getInputStream();
            } else {
                return httpConn.getErrorStream();
            }
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    public void close() {

    }
}
