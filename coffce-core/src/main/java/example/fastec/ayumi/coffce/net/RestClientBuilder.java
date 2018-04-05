package example.fastec.ayumi.coffce.net;

import java.util.Map;
import java.util.WeakHashMap;

import example.fastec.ayumi.coffce.net.callback.IError;
import example.fastec.ayumi.coffce.net.callback.IFailure;
import example.fastec.ayumi.coffce.net.callback.IRequest;
import example.fastec.ayumi.coffce.net.callback.ISuccess;
import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 *
 */
public class RestClientBuilder {
    private  String mUrl;
    private  static final WeakHashMap<String,Object> PARAMS = RestCreator.getParams();
    private  IRequest mIRequest;
    private  ISuccess mISuccess;
    private  IError mIError;
    private  IFailure mIFailure;
    private  RequestBody mBody;

    RestClientBuilder(){

    }

    public final RestClientBuilder url(String url){
        this.mUrl =url;
        return this;
    }

    public final RestClientBuilder params(WeakHashMap<String,Object> params){
        PARAMS.putAll(params);
        return this;
    }

    /**
     * 重载
     * @param key
     * @param value
     * @return
     */
    public final RestClientBuilder params(String key,Object value){

        PARAMS.put(key,value);
        return this;
    }

    /**
     * 原始数据
     * @param raw
     * @return
     */
    public final RestClientBuilder raw(String raw){
        this.mBody = RequestBody.create(MediaType.parse("application/json;charset=UTF-8"),raw);
        return this;
    }

    public final RestClientBuilder onrequest(IRequest iRequest){
        this.mIRequest =iRequest;
        return this;
    }
    public final RestClientBuilder success(ISuccess isuccess){
        this.mISuccess =isuccess;
        return this;
    }
    public final RestClientBuilder failure(IFailure ifailure){
        this.mIFailure =ifailure;
        return this;
    }
    public final RestClientBuilder error(IError iError){
        this.mIError =iError;
        return this;
    }



    public final RestClient build(){
        return new RestClient(mUrl,PARAMS,mIRequest,mISuccess,mIError,mIFailure,mBody);
    }

}