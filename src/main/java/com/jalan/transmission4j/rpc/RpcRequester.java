package com.jalan.transmission4j.rpc;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.Base64;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.OkHttpClient.Builder;

public class RpcRequester {

    private OkHttpClient httpClient;
    private TransmissionConfig config;
    private String csrfToken;

    private ObjectMapper objectMapper;

    private final String CSRF_KEY = "X-Transmission-Session-Id";

    public RpcRequester(TransmissionConfig config) {
        this.config = config;
        this.objectMapper = new ObjectMapper();
        this.objectMapper.setSerializationInclusion(Include.NON_NULL);

        initHttpClient();
    }

    public void initHttpClient() {
        Builder httpClientBuilder = new OkHttpClient().newBuilder();

        if (this.config.getUsername() != null) {
            addAuthInterceptor(httpClientBuilder);
        }

        addCsrfTokenInterceptor(httpClientBuilder);

        //enable proxy for debug
        //httpClientBuilder.proxy(new Proxy(Proxy.Type.HTTP, new InetSocketAddress("127.0.0.1", 8080)));

        this.httpClient = httpClientBuilder.build();
    }

    public <P, R> RpcCommand<P, R> request(RpcCommand<P, R> command) throws IOException {
        RpcRequest<P> request = command.buildRequestPayload();
        RpcResponse<R> response = request(request, RpcResponse.class, 200);

        if (response == null) {
            throw new IOException("http request error... no response procesed");
        }

        Map args = (Map) response.getArguments();
        String argStrValue = objectMapper.writeValueAsString(args);
        response.setArguments((R) objectMapper.readValue(argStrValue, command.getResponseClazz()));

        command.setResponse(response);
        return command;
    }

    public <T, V> V request(T payload, Class<V> responseClazz, int expectedStatusCode) throws IOException {
        V result = null;
        byte retries = 4;

        do {
            try {
                Request req = new Request.Builder()
                        .url(config.getUrl())
                        .post(RequestBody.create(this.objectMapper.writeValueAsString(payload),
                                MediaType.get("application/json; charset=utf-8")))
                        .build();

                try (Response res = this.httpClient.newCall(req).execute()) {
                    if (res.code() == 409) { // CSRF token invalid
                        retries--;
                        continue;
                    }

                    String responseBody = res.body().string();

                    result = objectMapper.readValue(responseBody, responseClazz);
                    break;
                }
            } catch (JsonProcessingException jpe) {
                jpe.printStackTrace();
                retries--;
            }
        } while (retries != 0);

        return result;
    }

    public void request() throws IOException, RpcAuthException {
        Request req = new Request.Builder()
                .url(config.getUrl())
                .post(RequestBody.create("{}", MediaType.get("application/json; charset=utf-8")))
                .build();

        try (Response response = this.httpClient.newCall(req).execute()) {
            if (response.code() == 401) {
                throw new RpcAuthException();
            } else {
                System.out.println(response);
            }
        }
    }

    protected void addAuthInterceptor(Builder builder) {
        builder.addInterceptor(new Interceptor() {

            @Override
            public Response intercept(Chain chain) throws IOException {
                Request req = chain.request();
                String auth = config.getUsername() + ":" + config.getPassword();
                req = req.newBuilder()
                        .addHeader("Authorization", "Basic " + Base64.getEncoder()
                                .encodeToString(auth.getBytes()))
                        .build();
                return chain.proceed(req);
            }

        });
    }

    protected void addCsrfTokenInterceptor(Builder builder) {
        builder.addInterceptor(new Interceptor() {

            @Override
            public Response intercept(Chain chain) throws IOException {
                Request req = chain.request();
                Response res = null;

                if (csrfToken != null) {
                    req = req.newBuilder().addHeader(CSRF_KEY, csrfToken).build();
                }

                res = chain.proceed(req);

                if (res.headers().names().contains(CSRF_KEY)) {
                    csrfToken = res.headers().get(CSRF_KEY);
                }

                return res;
            }
        });
    }
}
