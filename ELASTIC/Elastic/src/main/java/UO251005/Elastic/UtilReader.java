package UO251005.Elastic;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

import org.apache.commons.compress.compressors.CompressorInputStream;
import org.apache.commons.compress.compressors.CompressorStreamFactory;

import io.netty.handler.codec.AsciiHeadersEncoder.NewlineType;
import twitter4j.TweetEntity;
import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.TwitterResponse;

public class UtilReader {

	public void parserito(String path) {
		try {
			BufferedInputStream stream = new BufferedInputStream(new FileInputStream(path));
			CompressorInputStream input = new CompressorStreamFactory().createCompressorInputStream(stream);
			BufferedReader reader = new BufferedReader(new InputStreamReader(input));
			String line ;
			while(reader.ready()){
				line = reader.readLine();
			}
		} catch (Exception e) {
			System.err.println("ERROR EN IO");
		}
		
		Twitter twitter = TwitterFactory.getSingleton();
		
	}

}
