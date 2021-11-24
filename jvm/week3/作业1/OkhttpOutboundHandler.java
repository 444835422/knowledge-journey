package outbound.okhttp;


import filter.HeaderHttpResponseFilter;
import filter.HttpRequestFilter;
import filter.HttpResponseFilter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.FullHttpRequest;
import okhttp3.*;
import outbound.ResponseHandler;
import router.HttpEndpointRouter;
import router.RandomHttpEndpointRouter;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * @author baiyumei
 * @title: OkhttpOutboundHandler
 * @projectName java-journey
 * @description: TODO
 * @date 2021/11/2111:37 上午
 */
public class OkhttpOutboundHandler {
    private List<String> backendUrls;

    private OkHttpClient client ;

    public static final MediaType JSON
            = MediaType.get("application/json; charset=utf-8");
    ResponseHandler responseHandler = new ResponseHandler();
    HttpResponseFilter filter = new HeaderHttpResponseFilter();
    HttpEndpointRouter router = new RandomHttpEndpointRouter();

    public OkhttpOutboundHandler(List<String> backendUrls){
        this.backendUrls = backendUrls.stream().map(this::formatURL).collect(Collectors.toList());

        int cores = Runtime.getRuntime().availableProcessors()/2;
        long keepAliveTime=5;
        int queueSize=1024;
        RejectedExecutionHandler handler = new ThreadPoolExecutor.CallerRunsPolicy();

        client = new OkHttpClient(
                new OkHttpClient.Builder().connectionPool(new ConnectionPool(cores,keepAliveTime,TimeUnit.MINUTES)));

    }
    private String formatURL(String backend){
        return backend.endsWith("/")?backend.substring(0,backend.length()-1):backend;
    }

    public void handler(final FullHttpRequest fullHttpRequest, final ChannelHandlerContext ctx, HttpRequestFilter filter) throws IOException {

        String backendUrl = router.route(this.backendUrls);
        String url = backendUrl+fullHttpRequest.uri();
        //拦截器处理请求信息
        filter.filter(fullHttpRequest,ctx);

        Response response = run(url, "");
        System.out.println(response.headers().toString());
        try {
            responseHandler.handlerResponse(fullHttpRequest,ctx,response.body().string());
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    Response run(String url,String body) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .build();

        return client.newCall(request).execute();
    }

}
