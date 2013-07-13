package nlp;

import java.io.IOException;

import java.io.UnsupportedEncodingException;

import java.net.URL;

import java.net.URLConnection;

import java.net.URLEncoder;

import java.security.InvalidKeyException;

import java.security.NoSuchAlgorithmException;

import java.text.SimpleDateFormat;

import java.util.Date;

import java.util.Scanner;

import java.util.TimeZone;

import org.apache.commons.codec.binary.Base64;


public class AskZiggyNLUEndpoint{

	public String getZiggyResponse(String query)

	{
		String URL = createAskZiggyApiUrl(query, "91d2431a", "a748d763b19a2fc67d362d8270b376b2", null);
		Scanner scanner = null;
		try {

			URL AskZiggyRequest = new URL(URL);
			URLConnection connection = AskZiggyRequest.openConnection();  
			connection.setDoOutput(true); 
			scanner = new Scanner(AskZiggyRequest.openStream());

		} catch (IOException e) {
			e.printStackTrace();
		}

		String response = scanner.useDelimiter("\\Z").next(); 
		scanner.close(); 
		return response;
	}
	
	public static String createAskZiggyApiUrl(String naturalLanguageText, String apiKey, String secretKey, String endUserId){
		String endpoint = "https://www.ask-ziggy.net/NLP/api/parse";
		String apiversion = "1";
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss'Z'");
			sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
			Date date = new Date();
			String timeStamp = sdf.format(date);
			String base_string = "input="+URLEncoder.encode(naturalLanguageText, "utf-8")+
					"&timestamp="+URLEncoder.encode(timeStamp, "utf-8")+
					"&apikey="+apiKey+
					"&userid="+endUserId +
					"&apiversion="+apiversion+
					"&secretkey="+secretKey;
			javax.crypto.SecretKey key = new javax.crypto.spec.SecretKeySpec(secretKey.getBytes("UTF8"), "HmacSHA256");
			javax.crypto.Mac signer = javax.crypto.Mac.getInstance(key.getAlgorithm());
			signer.init(key);
			byte[] hashedMessageBytes = signer.doFinal(base_string.replace(" ","").replace("+","").replace("%20","").getBytes("UTF8"));
			String signatureString = new String(Base64.encodeBase64(hashedMessageBytes));
			String url = endpoint + "?input="+URLEncoder.encode(naturalLanguageText, "utf-8")+"&timestamp="+URLEncoder.encode(timeStamp, "utf-8")+"&apikey="+URLEncoder.encode(apiKey, "utf-8")+"&userid="+endUserId+"&apiversion="+URLEncoder.encode(apiversion, "utf-8")+"&signature="+URLEncoder.encode(signatureString, "utf-8");
			return url;
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();

		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();

		} catch (InvalidKeyException e) {
			e.printStackTrace();
		}
		return null;
	}

}