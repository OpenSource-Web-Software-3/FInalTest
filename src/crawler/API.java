package crawler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;

public class API { //한국산업인력공단_국가자격 종목 코드 정보
	public static void main(String[] args) throws IOException {
		
		String serviceKey = "vpD6MRFjUQ4NBerxNq64yQPiJOyWBGYoR5J4cEm45Is8WrsxdWxTEwcqJh%2B6Lg7nYcV0vfiBzbkuyEzx%2Bx36lw%3D%3D";
//		String serviceKey = "vpD6MRFjUQ4NBerxNq64yQPiJOyWBGYoR5J4cEm45Is8WrsxdWxTEwcqJh+6Lg7nYcV0vfiBzbkuyEzx+x36lw==";
	    String serviceKeyDecoded = URLDecoder.decode(serviceKey, "UTF-8");
		System.out.println(" serviece Key Decorded  : " + serviceKeyDecoded);
		
		StringBuilder urlBuilder = new StringBuilder("http://openapi.q-net.or.kr/api/service/rest/InquiryJmcdInfoSVC/getDisqList"); /*URL*/
        urlBuilder.append("?" + URLEncoder.encode("ServiceKey","UTF-8") + "=" + URLEncoder.encode(serviceKeyDecoded, "UTF-8")); /*Service Key*/
//        urlBuilder.append("&" + URLEncoder.encode("ServiceKey","UTF-8") + "=" + ); /*발급받은 인증키*/
        urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode("10", "UTF-8")); /*한 페이지 결과 수*/
        urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /*페이지 번호*/
        URL url = new URL(urlBuilder.toString());
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");
        System.out.println("Response code: " + conn.getResponseCode());
        BufferedReader rd;
        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        } else {
            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
        }
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
            sb.append(line);
        }
        rd.close();
        conn.disconnect();
        System.out.println(sb.toString());
	}
}
