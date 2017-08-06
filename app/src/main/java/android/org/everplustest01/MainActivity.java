package android.org.everplustest01;

import android.support.annotation.CheckResult;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    CheckBox checkBoxServerA, checkBoxServerB;
    Button btnSendRequest;
    EditText editTextMessage;
    String URLServerA, URLServerB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setViews(); //뷰 요소들 셋
        setURLS();  //URL변수 셋
        setClickListeners();//클릭리스너 셋
    }

    //뷰 요소들 셋
    private void setViews(){
        checkBoxServerA = (CheckBox) findViewById(R.id.checkbox_option_server_A);
        checkBoxServerB = (CheckBox) findViewById(R.id.checkbox_option_server_B);
        btnSendRequest = (Button) findViewById(R.id.btn_send_request);
        editTextMessage = (EditText) findViewById(R.id.edittext_get_text);
    }
    //URL변수 셋
    private void setURLS(){
        // res/values/string.xml에서 가져온 값을 변수에 셋
        URLServerA = getApplicationContext().getResources().getString(R.string.url_server_A);
        URLServerB = getApplicationContext().getResources().getString(R.string.url_server_B);
    }
    //클릭리스너 셋
    private void setClickListeners(){
        btnSendRequest.setOnClickListener(this);
    }



    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_send_request:
                sendRequestMessqge();   //요청 메세지 전송(btnSendRequest클릭시)
                break;
        }
    }


    //요청 메세지 전송(btnSendRequest클릭시)
    private void sendRequestMessqge(){
        String body = editTextMessage.getText().toString(); //edittext로부터 받은 메세지
        if(!body.equals("")){
            //체크박스 체크 상태와 메세지에 따라 요청 배열을 생성
            ArrayList<HTTPOutboundRequest> requests = getRequestsByCheckStatus(body);
            for(HTTPOutboundRequest request: requests){
                //배열에 들어있는 request의 수 만큼 요청을 전송함
                HTTPOutboundRequestHandler handler
                        = new HTTPOutboundRequestHandler(request.getMessageBodyObjectString(), request.getUrl());
                handler.sendMessageForOneWay();
            }
        }else{
            //값 입력 안하면 경고 토스트 출력
            Toast.makeText(getApplicationContext(),
                    getApplicationContext().getResources().getString(R.string.edittext_get_text_hint),
                    Toast.LENGTH_SHORT).show();
        }
    }

    //체크박스 체크 상태와 메세지에 따라 요청 배열을 생성
    private ArrayList<HTTPOutboundRequest> getRequestsByCheckStatus(String message){
        ArrayList<HTTPOutboundRequest> requests = new ArrayList<>();
        if(checkBoxServerA.isChecked()){
            //서버 A 체크되었으면
            HTTPOutboundRequest request = new HTTPOutboundRequest(URLServerA);
            request.setKeyValue(Constants.OUTBOUND_REQ_KEY_SERVER_A_BODY, message);
            requests.add(request);
        }
        if(checkBoxServerB.isChecked()){
            //서버 B 체크되었으면
            HTTPOutboundRequest request = new HTTPOutboundRequest(URLServerB);
            request.setKeyValue(Constants.OUTBOUND_REQ_KEY_SERVER_B_CONTENT, message);
            requests.add(request);
        }
        return requests;
    }

}
