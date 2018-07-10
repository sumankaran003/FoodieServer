package in.karan.suman.foodkaserver.Common;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;

import in.karan.suman.foodkaserver.Model.Request;
import in.karan.suman.foodkaserver.Model.User;
import in.karan.suman.foodkaserver.Remote.APIService;
import in.karan.suman.foodkaserver.Remote.FCMRetrofitClient;
import in.karan.suman.foodkaserver.Remote.IGeoCoordinates;
import in.karan.suman.foodkaserver.Remote.RetrofitClient;

/**
 * Created by Suman on 12-Dec-17.
 */

public class Common {
    public static  String PHONE_TEXT="userPhone";
    public static User currentUser;
    public static Request currentRequest;

    public static String topicNews="News";

    public static final String UPDATE = "Update";
    public static final String DELETE = "Delete";

    public static final int PICK_IMAGE_REQUEST = 71;

    private static final String BASE_URL="https://fcm.googleapis.com/";

    public static String convertCodeToStatus(String status) {

        if (status.equals("0")) {
            return "Placed";
        } else if (status.equals("1")) {
            return "On my way";
        } else {
            return "Shipped";
        }

    }

    public static final String baseUrl="https://maps.googleapis.com";

    public static IGeoCoordinates getGeoCodeService(){
        return RetrofitClient.getClient(baseUrl).create(IGeoCoordinates.class);
    }


    public static Bitmap scaleBitmap(Bitmap bitmap,int newWidth,int newHeight){

        Bitmap scaledBitmap=Bitmap.createBitmap(newWidth,newHeight,Bitmap.Config.ARGB_8888);

        float scaleX=newWidth/(float)bitmap.getWidth();
        float scaleY=newHeight/(float)bitmap.getHeight();
        float pivotX=0,pivotY=0;

        Matrix scaleMatrix = new Matrix();
        scaleMatrix.setScale(scaleX,scaleY,pivotX,pivotY);

        Canvas canvas=new Canvas(scaledBitmap);
        canvas.setMatrix(scaleMatrix);
        canvas.drawBitmap(bitmap,0,0,new Paint(Paint.FILTER_BITMAP_FLAG));

        return scaledBitmap;

    }

    public static APIService getFCMService()
    {
        return FCMRetrofitClient.getClient(BASE_URL).create(APIService.class);
    }



}
