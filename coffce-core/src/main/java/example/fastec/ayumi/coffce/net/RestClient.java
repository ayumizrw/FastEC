package example.fastec.ayumi.coffce.net;

import android.content.Context;

import java.io.File;
import java.util.Map;
import java.util.WeakHashMap;

import example.fastec.ayumi.coffce.net.callback.IError;
import example.fastec.ayumi.coffce.net.callback.IFailure;
import example.fastec.ayumi.coffce.net.callback.IRequest;
import example.fastec.ayumi.coffce.net.callback.ISuccess;
import example.fastec.ayumi.coffce.net.callback.RequestCallBacks;
import example.fastec.ayumi.coffce.ui.LatteLoader;
import example.fastec.ayumi.coffce.ui.LoaderStyle;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.Body;
import retrofit2.http.Multipart;

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
    private final File FILE;
    private final LoaderStyle LOADER_STYLE;
    private final Context CONTEXT;

    public RestClient(String URL,
                      Map<String, Object> params,
                      IRequest REQUEST,
                      ISuccess SUEECSS,
                      IError ERROR,
                      IFailure FAILURE,
                      RequestBody BODY,
                      File file,
                      Context context,
                      LoaderStyle loaderStyle) {
        this.URL = URL;
       PARAMS.putAll(params);
        this.REQUEST = REQUEST;
        this.SUEECSS = SUEECSS;
        this.ERROR = ERROR;
        this.FAILURE = FAILURE;
        this.BODY = BODY;
        this.FILE = file;
        this.CONTEXT =context;
        this.LOADER_STYLE =loaderStyle;
    }

    public static RestClientBuilder builder(){
        return new RestClientBuilder();
    }

    private void request(HttpMetod httpMetod){
        final RestService service = RestCreator.getRestService();
        Call<String>  call =null;

        if(REQUEST != null){
            REQUEST.onRequestStart();
        }
        if(LOADER_STYLE !=null){
            LatteLoader.showloading(CONTEXT,LOADER_STYLE);
        }
        switch (httpMetod){
            case GET:
                call =service.get(URL,PARAMS);
                break;
            case POST:
                call =service.post(URL,PARAMS);
                break;
            case POST_RAW:
                call = service.postRaw(URL,BODY);
                break;
            case PUT:
                call =service.put(URL,PARAMS);
                break;
            case PUT_RAW:
                call = service.putRaw(URL,BODY);
                break;
            case DELETE:
                call =service.delete(URL,PARAMS);
                break;
            case UPLODE:
                RequestBody requestBody = RequestBody.create(MediaType.parse(MultipartBody.FORM.toString()),FILE);
                final MultipartBody.Part body =MultipartBody.Part.createFormData("file",FILE.getName(),requestBody);
                call =RestCreator.getRestService().uploade(URL,body);
                break;
                default:
                    break;
        }

        if(call !=null){
            call.enqueue(getRequestCallBack());
        }
    }

    private Callback<String> getRequestCallBack(){
        return new RequestCallBacks(REQUEST,SUEECSS,ERROR,FAILURE, LOADER_STYLE);
    }
    /**
     * 具体使用方法
     */

    public final void get(){
        request(HttpMetod.GET);
    }
    public final void post(){
        if(BODY == null){
            request(HttpMetod.POST);
        }else {
            if(!PARAMS.isEmpty()){
                throw new RuntimeException("params must be null");
            }
            request(HttpMetod.POST_RAW);
        }
       // request(HttpMetod.POST);
    }
    public final void put(){
        if(BODY == null){
            request(HttpMetod.PUT);
        }else {
            if(!PARAMS.isEmpty()){
                throw new RuntimeException("params must be null");
            }
            request(HttpMetod.PUT_RAW);
        }
    }
    public final void delete(){
        request(HttpMetod.DELETE);
    }
}
