package com.example.networtest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpConnection;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.protocol.HTTP;
import org.json.JSONObject;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import android.os.Bundle;
import android.app.Activity;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends Activity {

	private ImageView mImageView;
	private NetworkImageView mNetworkImageView;
	
	private Button mButton;
	
	private RequestQueue mQueue = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		mImageView = (ImageView) findViewById(R.id.imageView_image);
		mNetworkImageView = (NetworkImageView) findViewById(R.id.networkImageView_image);
		
		mButton = (Button) findViewById(R.id.button_send);
		mButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
//				imageRequest();
				imageLoader();
				networkImageView();
			}
		});
		
		//创建请求队列
		mQueue = Volley.newRequestQueue(getApplicationContext());
		
//		stringRequest();
		jsonRequest();
		
//		new Thread(new Runnable() {
//			
//			@Override
//			public void run() {
////				useHttpClientGet("http://www.baidu.com");
////				useHttpClientPost("http://www.baidu.com");
//				useHttpUrlConnectionPost("http://www.baidu.com");
//			}
//		}).start();
		
	}

	//Volley
	//=====================================================
	
	/**
	 * StringRequest用法
	 */
	private void stringRequest(){
		StringRequest mStringRequest = new StringRequest(Request.Method.POST, "http://www.baidu.com", new Response.Listener<String>() {

			@Override
			public void onResponse(String response) {
				Log.e("1111111", response);
			}
		}, new Response.ErrorListener() {

			@Override
			public void onErrorResponse(VolleyError error) {

				Log.e("222222", error.getMessage(), error);
			}
		});
		
		//将请求添加在请求队列中
		mQueue.add(mStringRequest);
	}
	
	/**
	 * JsonRequest用法
	 */
	private void jsonRequest(){
		JsonObjectRequest mJsonObjectRequest = new JsonObjectRequest(Request.Method.POST,"http://api.1-blog.com/biz/bizserver/article/list.do",
                null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                    	
                    	Article mArticle = new Gson().fromJson(response.toString(), Article.class);
                    	List<Article.detail> mList = mArticle.getDetail();
                    	String title = mList.get(0).getTitle();
                    	
                        Log.d("wangshu", title);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("wangshu", error.getMessage(), error);
            }
        }
        );
        mQueue.add(mJsonObjectRequest);
	}
	
	/**
	 * ImageRequest加载图片
	 * ImageRequest已经是过时的方法了
	 */
	private void imageRequest(){
		ImageRequest imageRequest = new ImageRequest("http://img.my.csdn.net/uploads/201603/26/1458988468_5804.jpg", new Listener<Bitmap>() {

			@Override
			public void onResponse(Bitmap response) {
				mImageView.setImageBitmap(response);
			}
		}, 0, 0, Bitmap.Config.RGB_565, new ErrorListener() {

			@Override
			public void onErrorResponse(VolleyError error) {
				mImageView.setImageResource(R.drawable.ic_launcher);
			}
		});
		mQueue.add(imageRequest);
	}
	
	/**
	 * ImageLoader加载图片
	 * ImageLoader的内部使用ImageRequest来实现，它的构造器可以传入一个ImageCache缓存形参，实现了图片缓存的功能，同时还可以过滤重复链接，避免重复发送请求。
	 */
	private void imageLoader(){
		ImageLoader imageLoader = new ImageLoader(mQueue, new BitmapCache());
		ImageLoader.ImageListener listener = ImageLoader.getImageListener(mImageView, R.drawable.ic_launcher, R.drawable.ic_launcher);
		imageLoader.get("http://img.my.csdn.net/uploads/201603/26/1458988468_5804.jpg", listener);
	}
	
	/**
	 * NetworkImageView加载图片
	 * NetworkImageView是一个自定义控件，继承自ImageView，封装了请求网络加载图片的功能。
	 */
	private void networkImageView(){
		ImageLoader imageLoader = new ImageLoader(mQueue, new BitmapCache());
		mNetworkImageView.setDefaultImageResId(R.drawable.ic_launcher);
		mNetworkImageView.setErrorImageResId(R.drawable.ic_launcher);
		mNetworkImageView.setImageUrl("http://img.my.csdn.net/uploads/201603/26/1458988468_5804.jpg", imageLoader);
	}

	
	//HTTP
	//====================================================================
	private void useHttpUrlConnectionPost(String url){
		InputStream mInputStream = null;
		
		HttpURLConnection mHttpURLConnection = UrlConnManager.getHttpURLConnection(url);
		
		try {
			List<NameValuePair> postParams = new ArrayList<NameValuePair>();
			postParams.add(new BasicNameValuePair("username", "moon"));
			postParams.add(new BasicNameValuePair("password", "123"));
			UrlConnManager.postParams(mHttpURLConnection.getOutputStream(), postParams);
			mHttpURLConnection.connect();
			
			mInputStream = mHttpURLConnection.getInputStream();
			int code = mHttpURLConnection.getResponseCode();
			
			String respose = converStreamToString(mInputStream);
            Log.i("HttpUrlConnectionPOST", "请求状态码:" + code + "\n请求结果:\n" + respose);
            mInputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
	
	private HttpClient createHttpClient(){
		HttpParams mDefaultHttpParams = new BasicHttpParams();
		//设置连接超时
		HttpConnectionParams.setConnectionTimeout(mDefaultHttpParams, 15000);
		//设置请求超时
		HttpConnectionParams.setSoTimeout(mDefaultHttpParams, 15000);
		HttpConnectionParams.setTcpNoDelay(mDefaultHttpParams, true);
		HttpProtocolParams.setVersion(mDefaultHttpParams, HttpVersion.HTTP_1_1);
		HttpProtocolParams.setContentCharset(mDefaultHttpParams, HTTP.UTF_8);
		
		//持续握手
		HttpProtocolParams.setUseExpectContinue(mDefaultHttpParams, true);
		HttpClient mHttpClient = new DefaultHttpClient(mDefaultHttpParams);
		
		return mHttpClient;
	}
	
	//HttpClient的POST请求
	private void useHttpClientPost(String url){
		HttpPost mHttpPost = new HttpPost(url);
		mHttpPost.addHeader("Connection", "Keep-Alive");
		
		try {
			HttpClient mHttpClient = createHttpClient();
			List<NameValuePair> postParams = new ArrayList<NameValuePair>();
			//要传递的参数
			postParams.add(new BasicNameValuePair("username", "moon"));
			postParams.add(new BasicNameValuePair("password", "123"));
			
			mHttpPost.setEntity(new UrlEncodedFormEntity(postParams));
			
			HttpResponse mHttpResponse = mHttpClient.execute(mHttpPost);
			HttpEntity mHttpEntity = mHttpResponse.getEntity();
			int code = mHttpResponse.getStatusLine().getStatusCode();
			if (null != mHttpEntity) {
				InputStream mInputStream = mHttpEntity.getContent();
				String respose = converStreamToString(mInputStream);
				Log.i("HttpClientPOST", "请求状态码" + code + "\n请求结果:\n" + respose);
				mInputStream.close();
			}
			
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	//创建HttpGet和HttpClient，请求网络并得到HttpResponse，并对HttpResponse进行处理
	private void useHttpClientGet(String url){
		HttpGet mHttpGet = new HttpGet(url);
		mHttpGet.addHeader("Connection", "Keep-Alive");
		
		try {
			HttpClient mHttpClient = createHttpClient();
			HttpResponse mHttpResponse = mHttpClient.execute(mHttpGet);
			HttpEntity mHttpEntity = mHttpResponse.getEntity();
			int code = mHttpResponse.getStatusLine().getStatusCode();
			if (null != mHttpEntity) {
				InputStream mInputStream = mHttpEntity.getContent();
				String respose = converStreamToString(mInputStream);
				Log.i("HttpClient", "请求状态码" + code + "\n请求结果:\n" + respose);
				mInputStream.close();
			}
			
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//converStreamToString方法将请求结果转换成String类型
	private String converStreamToString(InputStream is) throws IOException{
		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		StringBuffer sb = new StringBuffer();
		String line = null;
		
		while ((line = reader.readLine()) != null) {
			sb.append(line + "\n");
		}
		String respose = sb.toString();
		return respose;
	}

}
