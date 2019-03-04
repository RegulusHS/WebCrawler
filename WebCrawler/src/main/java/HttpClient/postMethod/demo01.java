package HttpClient.postMethod;

import org.apache.http.Consts;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;

public class demo01 {

    public static void main(String[] args) {
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
        nvps.add(new BasicNameValuePair("param1", "value1"));
        nvps.add(new BasicNameValuePair("param2", "value2"));
        UrlEncodedFormEntity entity = new UrlEncodedFormEntity(nvps, Consts.UTF_8);
        HttpPost httpPost = new HttpPost("http://localhost/handler.do");
        httpPost.setEntity(entity);
    }
}
