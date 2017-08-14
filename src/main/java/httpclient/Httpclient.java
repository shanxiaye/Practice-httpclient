package httpclient;

import java.io.IOException;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;

public class Httpclient {

	public static void main(String[] args) {
		// 1.创建一个浏览器
		HttpClient client = new HttpClient();
		// 2.在地址栏输入请求地址
//		GetMethod method = new GetMethod("http://f.apiplus.net/cqssc-10.json");
		PostMethod method = new PostMethod("http://localhost:8080/httpclient-web/HelloServlet");
		try {
			// 中文乱码处理
			method.setRequestHeader("Content-Type","application/x-www-form-urlencoded;charset=UTF-8");
			// 设置请求参数（可选）
			NameValuePair[] parametersBody = {
					new NameValuePair("name", "方理")
			};
			method.setRequestBody(parametersBody);
			
			
			// 3.发起请求
			int statusCode = client.executeMethod(method);
			if (statusCode != HttpStatus.SC_OK) {
				System.err.println("Method failed: " + method.getStatusLine());
		  	}
			
			// 4.获取响应
			byte[] response = method.getResponseBody();
			// 5.打印
			System.out.println(new String(response));
		} catch (HttpException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			// 6.释放资源
			method.releaseConnection();
		}}

}
