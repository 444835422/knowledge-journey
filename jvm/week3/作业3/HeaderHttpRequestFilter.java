package filter;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.FullHttpRequest;
import outbound.ResponseHandler;

/**
 * @author baiyumei
 * @title: HeaderHttpRequestFilter
 * @projectName java-journey
 * @description: TODO
 * @date 2021/11/209:09 下午
 */
public class HeaderHttpRequestFilter implements HttpRequestFilter{

    ResponseHandler responseHandler = new ResponseHandler();
    public void filter(FullHttpRequest fullHttpRequest, ChannelHandlerContext ctx){
        String url = fullHttpRequest.uri();
        //按服务进行filter处理
        if(url.contains("/api")){
            apiServer(fullHttpRequest,ctx);
        }

    }
    public void apiServer(FullHttpRequest fullHttpRequest, ChannelHandlerContext ctx) {
        //检查服务格式是否正确
        String apiRequest = fullHttpRequest.headers().get("api-request");
        if("lilililili".equals(apiRequest)){
            //获取交易类别
            //交易流控
            //增加网关处理标记
            fullHttpRequest.headers().set("gateway","0000");
        }else{
         //不满足校验，网关不处理或拒绝交易
            try {
                responseHandler.handlerResponse(fullHttpRequest, ctx, "{error-code:1001}");
            }catch (Exception e){
                e.printStackTrace();
            }
        }

    }

}
