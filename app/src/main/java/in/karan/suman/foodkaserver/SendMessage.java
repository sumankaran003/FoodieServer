package in.karan.suman.foodkaserver;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.rengwuxian.materialedittext.MaterialEditText;

import java.util.HashMap;
import java.util.Map;

import in.karan.suman.foodkaserver.Common.Common;
import in.karan.suman.foodkaserver.Model.DataMessage;
import in.karan.suman.foodkaserver.Model.MyResponse;
import in.karan.suman.foodkaserver.Model.Offer;
import in.karan.suman.foodkaserver.Remote.APIService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SendMessage extends AppCompatActivity {

    MaterialEditText edtMessage,edtTitle;
    Button btnSend;
    FirebaseDatabase database;
    DatabaseReference offerTable;

    APIService mService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_message);

        mService= Common.getFCMService();

        database=FirebaseDatabase.getInstance();
        offerTable=database.getReference("Offer");

        edtMessage=findViewById(R.id.edtMessage);
        edtTitle=findViewById(R.id.edtTitle);
        btnSend=findViewById(R.id.btnSend);

      btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                Map<String,String> dataSend=new HashMap<>();
                dataSend.put("title",edtTitle.getText().toString());
                dataSend.put("message",edtMessage.getText().toString());

                 DataMessage dataMessage = new DataMessage(new StringBuilder("/topics/").append(Common.topicNews).toString(), dataSend);

                mService.sendNotification(dataMessage)
                        .enqueue(new Callback<MyResponse>() {

                            @Override
                            public void onResponse(Call<MyResponse> call, Response<MyResponse> response) {
                                if(response.isSuccessful())
                                    Toast.makeText(SendMessage.this,"Message Sent",Toast.LENGTH_SHORT).show();
                                updateOffer(edtTitle.getText().toString(),edtMessage.getText().toString());
                            }

                            @Override
                            public void onFailure(Call<MyResponse> call, Throwable t) {


                                Toast.makeText(SendMessage.this,""+t.getMessage(),Toast.LENGTH_SHORT).show();

                            }
                        });


            }
        });



    }



    private void updateOffer(String head,String detail)
    {

        Offer offer=new Offer(
                head,detail
        );

        String order_number=String.valueOf(System.currentTimeMillis());

        offerTable.child(order_number)
                .setValue(offer);

    }
}
