package filter;

import io.netty.handler.codec.http.FullHttpResponse;

/**
 * @author baiyumei
 * @title: HeaderHttpResponseFilter
 * @projectName java-journey
 * @description: TODO
 * @date 2021/11/209:10 下午
 */
public class HeaderHttpResponseFilter implements HttpResponseFilter{
    public void filter(FullHttpResponse fullHttpResponse) {
        //返回的过滤器作用：1，增加网关处理标记
        //2，报文转译，比如http请求转webservice请求，其他作用暂未想到
        fullHttpResponse.headers().set("gateway","0000");;
    }
}
