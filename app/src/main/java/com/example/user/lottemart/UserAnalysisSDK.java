package com.example.user.lottemart;

import android.annotation.TargetApi;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by user on 2016-07-13.
 */
public class UserAnalysisSDK {
    private final int NUMOFACTDATA = 2;
    private final int NUMOFPURCHASEDDATA = 4;
    private final int NUMOFSEARCHDATA = 2;

    private JSONObject infoCarrier;
    private JSONArray[] activityInfo;
    private JSONArray[] purchasedInfo;
    private JSONArray[] searchInfo;

    private JSONArray activityName;
    private JSONArray activityTime;
    private JSONArray purchasedNum;
    private JSONArray purchasedName;
    private JSONArray purchasedCount;
    private JSONArray purchasedPrice;
    private JSONArray purchasedCategory;
    private JSONArray searchKeyword;
    private JSONArray searchCount;

    private Context callerContext;
    private String currentContext;
    private long startTime = 0;
    private long finishTime = 0;
    private int parameterTime = 10000;

    private URLConnection urlConnection = null;
    private ObjectOutputStream objectOutputStream;
    private ConnectionThread connectionThread;
    private TimerThread timerThread;

    private boolean sendingFlag = false;

    class ConnectionThread extends Thread{
        URL url;
        ConnectionThread(URL url) {
            this.url=url;
        }
        private void sender() throws IOException, NullPointerException{
            while(true) {
                if (sendingFlag == true){
                    String temp = infoCarrier.toString();
                    Log.d("Baeuk", temp);
                    urlConnection = url.openConnection(); // might occurs NullPointerException
                    urlConnection.setDoOutput(true);
                    urlConnection.setRequestProperty("Content-Type", "text/html; charset=EUC-KR");
                    objectOutputStream = new ObjectOutputStream(urlConnection.getOutputStream());

                    write(temp);
                    urlConnection.getInputStream();
                    sendingFlag = false;
                    freeMemory();
                }
            }
        }
        private void write(String json) throws IOException{
            objectOutputStream.writeObject(json);
        }
        @Override
        public void run(){
            try {
                sender();
            } catch (IOException e) {
                Log.d("Baeuk","Server not Opened"); // urlConnection.getOutputStream()
                sendingFlag = false;
                this.run();
            } catch (NullPointerException e){
                Toast.makeText(callerContext, "Server not Ready", Toast.LENGTH_SHORT).show();
            }
        }
    }
    class TimerThread extends Thread{
        long startTime;
        long currentTime;

        public TimerThread(){
            startTime = System.currentTimeMillis();
        }
        @Override
        public void run(){
            while(true){
                currentTime = System.currentTimeMillis();
                if(isTimeOver(parameterTime)) sendData();
            }
        }
        public boolean isTimeOver(int time){
            if((currentTime - startTime) > time){
                startTime = currentTime;
                return true;
            }
            else return false;
        }
    }
    public UserAnalysisSDK(URL url, Context callerContext, int timeOut){
        this.callerContext = callerContext;
        parameterTime = timeOut;
        memoryAllocate();
        connectionThread = new ConnectionThread(url);
        connectionThread.start();
        timerThread = new TimerThread();
        timerThread.start();
    }
    public JSONObject getJSONObject(){
        return infoCarrier;
    }
    private boolean memoryAllocate(){
        try {
            infoCarrier = new JSONObject();
            /*
            activityInfo = new JSONArray[NUMOFACTDATA];
            purchasedInfo = new JSONArray[NUMOFPURCHASEDDATA];
            searchInfo = new JSONArray[NUMOFSEARCHDATA];
            */
            activityName = new JSONArray();
            activityTime = new JSONArray();
            purchasedNum = new JSONArray();
            purchasedName = new JSONArray();
            purchasedCount = new JSONArray();
            purchasedPrice = new JSONArray();
            purchasedCategory = new JSONArray();
            searchKeyword = new JSONArray();
            searchCount = new JSONArray();

        /* ActivityInfo -> | 0 : 화면 번호 | 1 : 해당 화면에서의 체류 시간 | 2 : 해당 화면에서의 이탈 여부 | 3 : 화면별 누적조회 수 */
            //for (int i = 0; i < NUMOFACTDATA; i++) activityInfo[i] = new JSONArray();
        /* PurchasedInfo -> | 0 : 구매 상품명 | 1 : 해당 상품의 구매시각 */
            //for (int i = 0; i < NUMOFPURCHASEDDATA; i++) purchasedInfo[i] = new JSONArray();
        /* SearchingInfo -> | 0 : 검색 키워드 | 1 : 해당 키워드의 검색 상품 결과 수 */
            //for (int i = 0; i < NUMOFSEARCHDATA; i++) searchInfo[i] = new JSONArray();

        } catch(OutOfMemoryError e){
            return false;
        }
        return true;
    }
    @TargetApi(19)
    private void freeMemory(){
        int activityLength = activityName.length();
        int searchLength = searchKeyword.length();
        int purchasedLength = purchasedName.length();

        for(int i=0; i<activityLength; i++) {
            activityName.remove(0);
            activityTime.remove(0);
        }
        for(int i=0; i<searchLength; i++) {
            searchKeyword.remove(0);
            searchCount.remove(0);
        }
        for(int i=0; i<purchasedLength; i++){
            purchasedNum.remove(0);
            purchasedName.remove(0);
            purchasedCount.remove(0);
            purchasedPrice.remove(0);
            purchasedCategory.remove(0);
        }
    }
    public boolean sendData() {
        // ★ 기존 앱 초기 화면의 OnDestroy()에서 호출
        // ★ SDK의 isOverSize(int size) 가 참인 경우 호출
        sendingFlag = true;
        return true;
    }
    public boolean connectionDestroy(){
        // ★ 앱 종료시 호출
        if(quitThread()) return true;
        else return false;
    }
    private boolean quitThread(){
        try {
            objectOutputStream.close();
            connectionThread.interrupt();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        } catch (NullPointerException e){
            return false;
        }
        return true;
    }
    public String getExitActivity(){
        return currentContext;
    }
    public long timeCheckerStart(Context context){
        startTime = System.currentTimeMillis();
        currentContext = context.getClass().getSimpleName();
        return startTime;
    }
    public long timeCheckerStart(Context context, int itemNum){
        startTime = System.currentTimeMillis();
        currentContext = context.getClass().getSimpleName()+"_"+String.valueOf(itemNum);
        return startTime;
    }
    public long timeCheckerEnd(Context context){
        finishTime = System.currentTimeMillis();
        saveActivityInfo(context);
        return finishTime;
    }
    public long timeCheckerEnd(Context context, int itemNum){
        finishTime = System.currentTimeMillis();
        saveActivityInfo(context,itemNum);
        return finishTime;
    }
    public boolean saveActivityInfo(Context context){
        int stayingTime = (int) (finishTime - startTime) / 1000;
        if(stayingTime < 0 ) stayingTime = 0;
        activityName.put(context.getClass().getSimpleName());
        activityTime.put(stayingTime);
        try {
            infoCarrier.put("Activity_Name",activityName);
            infoCarrier.put("Activity_Time",activityTime);
        } catch (JSONException e) {
            Toast.makeText(callerContext,"Save ActivityInfo Failed.",Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
    public boolean saveActivityInfo(Context context, int itemNum){
        int stayingTime = (int) (finishTime - startTime) / 1000;
        if(stayingTime < 0) stayingTime = 0;
        activityName.put(context.getClass().getSimpleName()+"_"+itemNum);
        activityTime.put(stayingTime);
        try {
            infoCarrier.put("Activity_Name",activityName);
            infoCarrier.put("Activity_Time",activityTime);
        } catch (JSONException e) {
            Toast.makeText(callerContext,"Save ActivityInfo Failed.",Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
    public boolean saveLoginInfo(String userID, String gender, int age, String area, String job,
                                 String deviceName, int androidVersion, String loginTime){
        // ★ Login Button의 OnclickListener() 혹은 Login 직후의 Activity의 OnCreate()에 삽입
        // * APP 시작시 미리 로그인 되어있는 경우, 최초의 Activity의 OnCreate()에 삽입
        try {
            infoCarrier.put("userID",userID);
            infoCarrier.put("gender",gender);
            infoCarrier.put("age",age);
            infoCarrier.put("area",area);
            infoCarrier.put("job",job);
            infoCarrier.put("deviceName",deviceName);
            infoCarrier.put("androidVersion",androidVersion);
            infoCarrier.put("loginTime",loginTime);
        } catch (JSONException e) {
            Toast.makeText(callerContext,"Save LoginInfo Failed.",Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
    public boolean saveSearchInfo(String keyword, int numOfResultItems){
        // ★ 검색 결과 Activity에 삽입
        searchKeyword.put(keyword);
        searchCount.put(numOfResultItems);
        try {
            infoCarrier.put("Search_Keyword", searchKeyword);
            infoCarrier.put("Search_Count", searchCount);
        } catch (JSONException e) {
            Toast.makeText(callerContext,"Save SearchingInfo Failed.",Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
    public boolean savePurchasedInfo(int itemNum, String itemName, int count, int price, String category, String time){
        // ★ 구매 결과 Activity에 삽입
        purchasedNum.put(itemNum);
        purchasedName.put(itemName);
        purchasedCount.put(count);
        purchasedPrice.put(price);
        purchasedCategory.put(category);
        try {
            infoCarrier.put("Purchased_Time",time);
            infoCarrier.put("Purchased_Num",purchasedNum);
            infoCarrier.put("Purchased_Name",purchasedName);
            infoCarrier.put("Purchased_Count", purchasedCount);
            infoCarrier.put("Purchased_Price",purchasedPrice);
            infoCarrier.put("Purchased_Category",purchasedCategory);
        } catch (JSONException e) {
            Toast.makeText(callerContext,"Save ActivityInfo Failed.",Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
    public boolean saveExitActivityInfo(){
        try {
            infoCarrier.put("Exit_Activity",currentContext);
        } catch (JSONException e) {
            Toast.makeText(callerContext,"Save ExitActivityInfo Failed.",Toast.LENGTH_SHORT).show();
            return false;
        }
        sendData();
        return true;
    }
    public boolean isOverSize(int size) {
        Log.i("SizeOf",String.valueOf(sizeOf(infoCarrier)));
        int objectSize = sizeOf(this);
        if(objectSize <= -1) return false;  // 객체가 비어있으면 false 반환
        else if(objectSize == 0) return true;  // ObjectOutputStream을 만들 메모리도 없는 심각한 상황이므로 true 반환
        else if(objectSize >= size) return true; // 사용자가 지정한 size를 초과하면 true 반환
        else return false;
    }
    public String getDate(){
        SimpleDateFormat formatter =
                new SimpleDateFormat("yyyy.MM.dd HH:mm:ss", Locale.KOREA);
        Date currentTime = new Date();
        String dTime = formatter.format(currentTime);
        return dTime;
    }
    private int sizeOf(Object object)  {
        if (object == null)
            return -2;
        // Special output stream use to write the content
        // of an output stream to an internal byte array.
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        // Output stream that can write object
        ObjectOutputStream objectOutputStream = null;
        try {
            objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            // Write object and close the output stream
            objectOutputStream.writeObject(object);
            objectOutputStream.flush();
            objectOutputStream.close();
        } catch (IOException e) {
            return 0;
        }
        // Get the byte array
        byte[] byteArray = byteArrayOutputStream.toByteArray();

        // TODO can the toByteArray() method return a
        // null array ?
        return byteArray == null ? -1 : byteArray.length;
    }
}
