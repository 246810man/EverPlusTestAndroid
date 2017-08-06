package android.org.everplustest01;

import org.json.JSONException;
import org.json.JSONObject;


/**
 * Created by nam on 2017-08-06.
 */

public class HTTPOutboundRequest {
    //요청 서버의 url과 요청 메세지 바디를 정의한 클래스

    String url;
    JSONObject messageBodyObject;

    HTTPOutboundRequest(String url){
        this.url = url;
        this.messageBodyObject = new JSONObject();
    }

    public void setKeyValue (String key, String value){
        try {
            messageBodyObject.put(key, value);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    public String getMessageBodyObjectString(){
        return messageBodyObject.toString();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public JSONObject getMessageBodyObject() {
        return messageBodyObject;
    }

    public void setMessageBodyObject(JSONObject messageBodyObject) {
        this.messageBodyObject = messageBodyObject;
    }
}
