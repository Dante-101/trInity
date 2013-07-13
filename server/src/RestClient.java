import static spark.Spark.*;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import spark.Request;
import spark.Response;
import spark.Route;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import spark.utils.IOUtils;


/**
 * @author Nick Freeman <nfreeman@linkedin.com>
 */
public class RestClient
{

  public static String getResponse(String url) throws Exception {

    DefaultHttpClient client = new DefaultHttpClient();
    HttpGet request = new HttpGet(url);
    HttpResponse response = client.execute(request);

    // Get the response
    InputStream is = response.getEntity().getContent();

    String content;
    try {
      content = IOUtils.toString(is);
    } catch (Exception e) {
      System.out.println(e);
      return null;
    }

    return content;

  }



}
