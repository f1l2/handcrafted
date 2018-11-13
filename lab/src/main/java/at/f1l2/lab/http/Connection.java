package at.f1l2.lab.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

public class Connection {

	public static void main(String[] args)
			throws InterruptedException, IOException, NoSuchAlgorithmException, KeyManagementException {

		System.out.println("here");
		TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager() {
			public X509Certificate[] getAcceptedIssuers() {
				return null;
			}

			public void checkClientTrusted(X509Certificate[] certs, String authType) {
			}

			public void checkServerTrusted(X509Certificate[] certs, String authType) {
			}
		} };
		// Install the all-trusting trust manager
		SSLContext sc = SSLContext.getInstance("SSL");
		// Create empty HostnameVerifier
		HostnameVerifier hv = new HostnameVerifier() {
			public boolean verify(String arg0, SSLSession arg1) {
				return true;
			}
		};

		sc.init(null, trustAllCerts, new java.security.SecureRandom());
		HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
		HttpsURLConnection.setDefaultHostnameVerifier(hv);

		URL url = new URL("https://www.google.com");
		URLConnection connection = url.openConnection();

		connection.getInputStream();

		BufferedReader rd = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		String line;

		StringBuilder result = new StringBuilder();

		while ((line = rd.readLine()) != null) {
			result.append(line);
		}
		rd.close();
		System.out.println(result.toString());

	}

}
