//import android.util.Log;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;

//import org.json.JSONException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

// 날짜 쓰려고
//import java.text.ParseException;
import javax.swing.*;
import java.util.Calendar;
import java.text.SimpleDateFormat;
// 시간 쓰려고
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.StringTokenizer;

public class ApiExplorer {

    private JPanel weatherPanel;
    static String temp = "";
    public static String accessOpenAPI() throws IOException {


        String pangyo_x = "62";
        String pangyo_y = "123";

        // strToday : 오늘 날짜 - yyyyMMdd
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        Calendar c1 = Calendar.getInstance();
        String strToday = sdf.format(c1.getTime());
        System.out.println("Today : " + strToday);

        // strTime : 현재시간 - HHmm
        LocalTime now = LocalTime.now();
        //System.out.println("Now = " + now);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        String strNow = now.format(formatter);
        //System.out.println(strNow);  // 06:20:57

        StringTokenizer st = new StringTokenizer(strNow,":");
        String HH = st.nextToken();
        String mm = st.nextToken();
        String strTime = HH + mm;
        System.out.println("Time : " + strTime);


        // 1. URL을 만들기 위한 StringBuilder.
        StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/getUltraSrtNcst"); /*URL*/
        urlBuilder.append("?" + URLEncoder.encode("serviceKey","UTF-8") + "=%2B4U91yilK%2BaT6zHwqWrVyIAqmX%2BKK03unMbiqOFAr1IZhrBncQtODS6DH%2FVsg7M2hWYAa1DTKdojc7RadID4FQ%3D%3D"); /*Service Key*/
        urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /*페이지번호*/
        urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode("2000", "UTF-8")); /*한 페이지 결과 수*/
        urlBuilder.append("&" + URLEncoder.encode("dataType","UTF-8") + "=" + URLEncoder.encode("JSON", "UTF-8")); /*요청자료형식(XML/JSON) Default: XML*/
        urlBuilder.append("&" + URLEncoder.encode("base_date","UTF-8") + "=" + URLEncoder.encode(strToday, "UTF-8")); /*‘21년 6월 28일 발표*/
        urlBuilder.append("&" + URLEncoder.encode("base_time","UTF-8") + "=" + URLEncoder.encode(strTime, "UTF-8")); /*06시 발표(정시단위) */
        urlBuilder.append("&" + URLEncoder.encode("nx","UTF-8") + "=" + URLEncoder.encode(pangyo_x, "UTF-8")); /*예보지점의 X 좌표값*/
        urlBuilder.append("&" + URLEncoder.encode("ny","UTF-8") + "=" + URLEncoder.encode(pangyo_y, "UTF-8")); /*예보지점의 Y 좌표값*/
        // 3. URL 객체 생성.
        URL url = new URL(urlBuilder.toString());
        // 4. 요청하고자 하는 URL과 통신하기 위한 Connection 객체 생성.
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        // 5. 통신을 위한 메소드 SET.
        conn.setRequestMethod("GET");
        // 6. 통신을 위한 Content-type SET.
        conn.setRequestProperty("Content-type", "application/json");
        // 7. 통신 응답 코드 확인.
        System.out.println("Response code: " + conn.getResponseCode());
        // 8. 전달받은 데이터를 BufferedReader 객체로 저장.
        BufferedReader rd;
        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        } else {
            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
        }
        // 9. 저장된 데이터를 라인별로 읽어 StringBuilder 객체로 저장.
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
            sb.append(line);
        }
        // 10. 객체 해제.
        rd.close();
        conn.disconnect();

        String result = sb.toString();

        // 11. 전달받은 데이터 확인.
        System.out.println("Result : " + result);

        /*
         * PTY  강수형태         코드값
         * REH  습도              %
         * RN1  1시간 강수량     (1 mm)
         * T1H  기온              ℃
         * UUU  동서바람성분       m/s
         * VEC  풍향              deg
         * VVV  남북바람성분       m/s
         * WSD  풍속              m/s
         */


        // Parsing
        // 인증키 (개인이 받아와야함)
        String key = "%2B4U91yilK%2BaT6zHwqWrVyIAqmX%2BKK03unMbiqOFAr1IZhrBncQtODS6DH%2FVsg7M2hWYAa1DTKdojc7RadID4FQ%3D%3D";

        // 파싱한 데이터를 저장할 변수
        String fianl = "";
        try {

            BufferedReader bf;

            bf = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"));

            result = bf.readLine();

            // response 키를 가지고 데이터를 파싱
            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObject = (JSONObject)jsonParser.parse(result);
            JSONObject response = (JSONObject)jsonObject.get("response");

            // response 로 부터 body 찾기
            JSONObject body = (JSONObject)response.get("body");

            JSONObject items = (JSONObject)body.get("items");

            JSONArray item = (JSONArray)items.get("item");

            String category = "";
            String obsrValue = "";



            System.out.println(item.size());



            for(int i=0;i<item.size();i++){
                JSONObject items_key = (JSONObject)item.get(i);

                category = "";
                obsrValue = "";
                category += items_key.get("category");
                obsrValue += items_key.get("obsrValue");

                //System.out.println("category("+i+") : " + category);
                //System.out.println("obsrValue("+i+") : " + obsrValue);

                if(category.equals("T1H")){
                    temp = obsrValue;
                }
            }

            System.out.println("기온은 : " + temp + "℃ 입니다.");


        }catch(Exception e) {
            e.printStackTrace();
        }

        System.out.println(temp);
        return temp;
    }
}