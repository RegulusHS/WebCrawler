package HttpClient.URLConnection;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class demo03 {

    public static void main(String[] args) throws Exception {
        //Post表单需要提交的参数
        String wen = "EH612315646CS";
        String action = "ajax";
        //初始化URL
        URL url = new URL("http://www.kd185.com/ems.php");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();  //使用URLConnection的子类HttpURLConnection
        //允许Input、Output,不使用Cache
        conn.setDoInput(true);
        conn.setDoOutput(true);
        conn.setUseCaches(false);
        conn.setRequestMethod("POST");
        StringBuffer params = new StringBuffer();
        // 表单参数拼接
        params.append("wen").append("=").append(wen).append("&")
                .append("action").append("=").append(action);
        byte[] bytes = params.toString().getBytes();
        conn.getOutputStream().write(bytes);    //在连接中添加参数
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));  //定义BufferedReader输入流来读取URL的响应,这里设置编码
        String line;
        String html = "";
        while((line = bufferedReader.readLine()) != null) {
            html += line;
        }
        System.out.println(html);
    }
}
