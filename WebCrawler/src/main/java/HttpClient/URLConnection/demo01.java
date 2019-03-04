package HttpClient.URLConnection;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;

public class demo01 {

    public static void main(String[] args) {
        try {
            URL url = new URL("http://www.w3school.com.cn/b.asp");
            URLConnection conn = url.openConnection();
            InputStream in = conn.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(in, "gbk"));
            String line;
            String html = "";   //这里的内容是html
            while ((line = reader.readLine()) != null) {
                html += line;
            }
            System.out.println(html);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
