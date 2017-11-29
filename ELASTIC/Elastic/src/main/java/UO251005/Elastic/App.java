package UO251005.Elastic;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.elasticsearch.action.admin.indices.create.CreateIndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

/**
 * Hello world!
 *
 */
public class App 
{
	public static void main(String[] args)
    {
        //9300 api rest
		//9200 aplicacion web
        try {
			@SuppressWarnings({ "resource", "unused", "unchecked" })
			TransportClient client = new PreBuiltTransportClient(Settings.EMPTY)
					.addTransportAddress(new TransportAddress(InetAddress.getByName("localhost"),9300));
        
			
			//createIndex(client, "index");
			createDocument(client);
			client.close();
        } catch (UnknownHostException e) {
			System.err.println("ERROR EN LA CONEXION");
			e.printStackTrace();
		}
        
    }
	
	private static void createIndex(Client client, String indexName){
		client.admin().indices().create(new CreateIndexRequest(indexName)).actionGet();
	}
	
	private static void createDocument(Client client){
		String tweet = "{\"nombre\":\"pedro\""
				+ "\"apellido\":\"blanco\""
				+ "}";
		IndexResponse response = client.prepareIndex("lowercase","tabla").setSource(tweet,XContentType.JSON).get();
		response.toString();
	}
	
}
