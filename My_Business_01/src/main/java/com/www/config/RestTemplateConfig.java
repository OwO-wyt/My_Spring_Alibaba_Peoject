package com.www.config;

import org.apache.http.impl.client.CloseableHttpClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.client.RestTemplate;


/**
 * 引入resttemplate组件
 */
@Configuration
public class RestTemplateConfig {



    @Bean(name = "restTemplate")
    public RestTemplate httpsRestTemplate(HttpComponentsClientHttpRequestFactory httpsFactory){
        RestTemplate restTemplate = new RestTemplate(httpsFactory);
        restTemplate.setErrorHandler(new ResponseErrorHandler() {
            @Override
            public boolean hasError(ClientHttpResponse clientHttpResponse) {
                return false;
            }

            @Override
            public void handleError(ClientHttpResponse clientHttpResponse) {
                //默认处理非200的返回，会抛异常
            }
        });
        restTemplate.getMessageConverters().add(new MyHttpMessageConverter());
        return restTemplate;
    }

    @Bean(name = "httpsFactory")
    public HttpComponentsClientHttpRequestFactory httpComponentsClientHttpRequestFactory() throws Exception{
        CloseableHttpClient httpClient = HttpClientUtils.acceptsUntrustedCertsHttpClient();
        HttpComponentsClientHttpRequestFactory httpsFactory = new HttpComponentsClientHttpRequestFactory(httpClient);
        httpsFactory.setReadTimeout(2000);
        httpsFactory.setConnectTimeout(2000);
        return httpsFactory;
    }



// -------------------- 以下是使用http请求时的配置-----------------------------

//
//    @Bean
//    public RestTemplate restTemplate(@Qualifier("clientHttpRequestFactory") ClientHttpRequestFactory factory) {
//        RestTemplate restTemplate = new RestTemplate(factory);
//        restTemplate.getMessageConverters().add(new MyHttpMessageConverter());
//        return restTemplate;
//    }

    /**
     * 请求连接池配置
     *
     * @return
     */
//    @Bean
//    public ClientHttpRequestFactory clientHttpRequestFactory(HttpClient httpClient) {
//        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
//        //httpClient创建器
//        factory.setHttpClient(httpClient);
//        //数据读取超时时间
//        factory.setReadTimeout(60000);
//        //连接超时时间
//        factory.setConnectTimeout(60000);
//        //连接池获取请求连接的超时时间
//        factory.setConnectionRequestTimeout(60000);
//        return factory;
//    }

    /**
     * httpClient
     *
     * @param poolingHttpClientConnectionManager
     * @return
     */
//    @Bean
//    public HttpClient httpClient(HttpClientConnectionManager poolingHttpClientConnectionManager) {
//        HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
//
//        //设置http连接管理器
//        httpClientBuilder.setConnectionManager(poolingHttpClientConnectionManager);
//
//        //设置重试次数
//        httpClientBuilder.setRetryHandler(new DefaultHttpRequestRetryHandler(3,true));
//
//        //设置默认请求头
//        List<Header> headers = new ArrayList<>();
//        headers.add(new BasicHeader("Connection","Keep-Alive"));
//        httpClientBuilder.setDefaultHeaders(headers);
//
//        return httpClientBuilder.build();
//    }


    /**
     * http连接管理器
     *
     * @return
     */
//    @Bean
//    public HttpClientConnectionManager poolingHttpClientConnectionManager() {
//        //注册http和https请求
//        Registry<ConnectionSocketFactory> registry = RegistryBuilder.<ConnectionSocketFactory>create()
//                .register("http", PlainConnectionSocketFactory.getSocketFactory())
//                .register("https", SSLConnectionSocketFactory.getSocketFactory())
//                .build();
//
//        PoolingHttpClientConnectionManager poolingHttpClientConnectionManager = new PoolingHttpClientConnectionManager(registry);
//
//        //最大连接数
//        poolingHttpClientConnectionManager.setMaxTotal(1000);
//        //同路由并发数（每个主机的并发）
//        poolingHttpClientConnectionManager.setDefaultMaxPerRoute(500);
//        return poolingHttpClientConnectionManager;
//    }


}
