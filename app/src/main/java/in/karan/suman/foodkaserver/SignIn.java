package in.karan.suman.foodkaserver;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.rengwuxian.materialedittext.MaterialEditText;

import in.karan.suman.foodkaserver.Common.Common;
import in.karan.suman.foodkaserver.Model.User;

public class SignIn extends AppCompatActivity {

    MaterialEditText edtPhone,edtPassword;
    Button btnSignIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        edtPassword=findViewById(R.id.edtPassword);
        edtPhone=findViewById(R.id.edtPhone);
        btnSignIn=findViewById(R.id.btnSignIn);

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference table_user=database.getReference("User");

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final ProgressDialog mDialog=new ProgressDialog(SignIn.this);
                mDialog.setMessage("Please wait...");
                mDialog.show();


                table_user.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        //Check if user exist
                        if (dataSnapshot.child(edtPhone.getText().toString()).exists()){

                            //Get User Info
                            mDialog.dismiss();

                            User user = dataSnapshot.child(edtPhone.getText().toString()).getValue(User.class);
                            user.setPhone(edtPhone.getText().toString());

                            if(Boolean.parseBoolean(user.getIsStaff()))//if is staff =true
                            {

                                if (user.getPassword().equals(edtPassword.getText().toString())) {

                               Intent intent = new Intent(SignIn.this,Home.class);
                                Common.currentUser=user;
                                startActivity(intent);
                                finish();
                                }
                                else {

                                    Toast.makeText(SignIn.this, "Worng Password !!!", Toast.LENGTH_SHORT);
                                }
                            }
                            else {

                                Toast.makeText(SignIn.this, "Login with staff account", Toast.LENGTH_SHORT);
                            }



                        }else
                        {
                            mDialog.dismiss();
                            Toast.makeText(SignIn.this, "User doesn't exist in database", Toast.LENGTH_SHORT);
                        }

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }
        });





    }
}
