import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;


public class SampleClient {
	
	public static void main(String args[]) throws Exception
	{
		String lastUrl;
		//String url ="https://www.google.com/?code=AQQZpafnJuadL_oBH9luu3MO0F57EubH1fVXOFJwfCxzCwpi8yO2Dxah9J2k9ct7Vy6fwbbYXWwTNjqS_p8ZuqcDs_tx_tuBRuayWBgIgrIed77JA6o&state=DCEEFWF45453sdffef424+";
		String url = "https://www.linkedin.com/uas/oauth2/authorization?response_type=code&client_id=xy6upwkl4yrd&scope=r_fullprofile%20r_emailaddress%20r_network%20&state=DCEEFWF45453sdffef424%20&redirect_uri=http://www.google.com";
		DefaultHttpClient httpclient = new DefaultHttpClient();
		  MyRedirectHandler handler = new MyRedirectHandler();
		  httpclient.setRedirectHandler(handler);

		  HttpGet get = new HttpGet(url);

		  HttpResponse response = httpclient.execute(get);

		  HttpEntity entity = response.getEntity();
		  lastUrl = url;
		  if(handler.lastRedirectedUri != null){
		      lastUrl = handler.lastRedirectedUri.toString();
		  }
		  System.out.println(lastUrl);
	}

}
