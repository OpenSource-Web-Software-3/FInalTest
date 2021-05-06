package crawler;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.io.BufferedReader;
import java.io.IOException;

public class API2 { // 한국산업인력공단_국가기술자격 종목 정보
	public static void main(String[] args) throws IOException {
		
		
		String serviceKey = "vpD6MRFjUQ4NBerxNq64yQPiJOyWBGYoR5J4cEm45Is8WrsxdWxTEwcqJh%2B6Lg7nYcV0vfiBzbkuyEzx%2Bx36lw%3D%3D";
//		String serviceKey = "vpD6MRFjUQ4NBerxNq64yQPiJOyWBGYoR5J4cEm45Is8WrsxdWxTEwcqJh+6Lg7nYcV0vfiBzbkuyEzx+x36lw==";
	    String serviceKeyDecoded = URLDecoder.decode(serviceKey, "UTF-8");
		System.out.println(" serviece Key Decorded  : " + serviceKeyDecoded);
		
		StringBuilder urlBuilder = new StringBuilder(
				"http://openapi.q-net.or.kr/api/service/rest/InquiryQualInfo/getList"); /* URL */
		urlBuilder.append("?" + URLEncoder.encode("ServiceKey", "UTF-8") + "=" + serviceKey); /* Service Key */
		urlBuilder.append("&" + URLEncoder.encode("serviceKey", "UTF-8") + "="
				+ URLEncoder.encode(serviceKeyDecoded, "UTF-8")); /* 발급받은 인증키받은 인증키 */
		urlBuilder.append("&" + URLEncoder.encode("seriesCd", "UTF-8") + "="
				+ URLEncoder.encode("01", "UTF-8")); /* (계열코드) 01:기술사, 02:기능장, 03:기사, 04:기능사 */
		URL url = new URL(urlBuilder.toString());
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		conn.setRequestProperty("Content-type", "application/json");
		System.out.println("Response code: " + conn.getResponseCode());
		BufferedReader rd;
		if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
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
