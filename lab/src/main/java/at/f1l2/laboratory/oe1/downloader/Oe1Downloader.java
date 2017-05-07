package at.f1l2.laboratory.oe1.downloader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Oe1Downloader {

	public static void main(String[] args) {

		Oe1Downloader oe1Downloader = new Oe1Downloader();

		String webpage = oe1Downloader.requestPlayerSite();
		oe1Downloader.parsePrograms(webpage);

		//System.out.println(webpage);
		
	}

	private String requestPlayerSite() {

		try {
			URL url = new URL("https://audioapi.orf.at/oe1/api/json/current/broadcasts/");
			URLConnection yc = url.openConnection();
			BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream()));
			String inputLine;

			StringBuilder input = new StringBuilder();
			
			while ((inputLine = in.readLine()) != null) {
				input.append(inputLine);
			}
			in.close();
			
			return input.toString();
			
		} catch (IOException e) {
			System.out.println(e);
		}

		return null;
	}

	private List<Program> parsePrograms(String html) {
		
		ObjectMapper objectMapper = new ObjectMapper();
		Map<String, String> programsAsMap[];
		List<Program> programs = new ArrayList<>();
		
		try {
			programsAsMap = objectMapper.readValue(html, HashMap[].class);
			Arrays.asList(programsAsMap).stream().forEach(item -> System.out.println(item.toString()));
		
			
			
			for (Map<String, String> entry : programsAsMap) {
				
				Program program = new Program();
				program.
				
			}
			
		} catch (Exception e) {
			System.out.println(e);
		}

		return null;
	}

	private Byte[] downloadProgram(Program program) {

		return null;
	}

}
