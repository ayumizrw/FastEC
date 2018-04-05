package example.fastec.ayumi.coffce.net;

import java.util.Map;
import java.util.WeakHashMap;

import example.fastec.ayumi.coffce.net.callback.IError;
import example.fastec.ayumi.coffce.net.callback.IFailure;
import example.fastec.ayumi.coffce.net.callback.IRequest;
import example.fastec.ayumi.coffce.net.callback.ISuccess;
import okhttp3.RequestBody;

/**
 * 请求的具体实现类
 */
public class RestClient {

    private final String URL;
    private  static  final WeakHashMap<String,Object> PARAMS =RestCreator.getParams();
    private final IRequest REQUEST;
    private final ISuccess SUEECSS;
    private final IError ERROR;
    private final IFailure FAILURE;
    private final RequestBody BODY;

    public RestClient(String URL,
                      Map<String, Object> params,
                      IRequest REQUEST,
                      ISuccess SUEECSS,
                      IError ERROR,
                      IFailure FAILURE,
                      RequestBody BODY) {
        this.URL = URL;
       PARAMS.putAll(params);
        this.REQUEST = REQUEST;
        this.SUEECSS = SUEECSS;
        this.ERROR = ERROR;
        this.FAILURE = FAILURE;
        this.BODY = BODY;
    }

    public static RestClientBuilder builder(){
        return new RestClientBuilder();
    }
}
