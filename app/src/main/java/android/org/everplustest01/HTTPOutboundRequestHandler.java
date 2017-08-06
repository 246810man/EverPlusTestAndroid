package android.org.everplustest01;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by nam on 2017-08-06.
 */

//HTTP요청을 처리할 PHP문서의 URL과 HTTPOutboundRequest객체를 받아서
//HTTP POST요청을 보내는 객체입니다.
//이 객체를 사용하기 위해서는 먼저 HTTPOutboundRequest객체를 선언해야 합니다

public class HTTPOutboundRequestHandler {
    public String uri, request;

    public HTTPOutboundRequestHandler(String message, String uri){
        this.uri = uri;
        this.request = message;
    }

    //응답 받지 않아도 되는 메세지 보내기
    public void sendMessageForOneWay(){
        HTTPRequest httpRequest = new HTTPRequest();
        httpRequest.execute();
    }


    //요청 전송 어싱크태스크(응답 수신 X)
    class HTTPRequest extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            BufferedReader bufferedReader = null;
            try {
                URL url = new URL(uri); //URL셋

                HttpURLConnection conn = (HttpURLConnection) url.openConnection();  //커넥션 셋
                conn.setRequestMethod("POST");  //POST방식 요청 셋
                conn.setDoOutput(true); //서버로 데이터를 전송할 수 있도록 설정(디폴트: false)
                OutputStream os = conn.getOutputStream();   //outputStream 선언
                //outputStream에 쿼리를 바이트로 인코딩하여 작성(인코딩은 UTF-8 방식)
                os.write(request.getBytes("UTF-8"));
                os.flush(); //outputstream 플러시
                os.close(); //outputstream 닫기
                Log.d("HTTPConnection", "전송 완료됨");
            }
            catch (MalformedURLException e) {
                e.printStackTrace();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

    }

}



