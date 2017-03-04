ckage helloworld;

import com.basho.riak.client.api.RiakClient;
import com.basho.riak.client.api.cap.Quorum;
import com.basho.riak.client.api.commands.datatypes.UpdateDatatype;
import com.basho.riak.client.api.commands.kv.StoreValue;
import com.basho.riak.client.api.commands.kv.StoreValue.Builder;
import com.basho.riak.client.api.commands.kv.StoreValue.Option;
import com.basho.riak.client.core.query.Location;
import com.basho.riak.client.core.query.Namespace;
import com.basho.riak.client.core.query.RiakObject;
import com.basho.riak.client.core.util.BinaryValue;
import java.net.UnknownHostException;
import java.util.concurrent.ExecutionException;

/**
 *
 * @author basho
 */
public class HelloWorld {
    

    /**
     * @param args the command line arguments
     * @throws java.net.UnknownHostException
     * @throws java.util.concurrent.ExecutionException
     * @throws java.lang.InterruptedException
     */
    public static void main(String[] args) throws UnknownHostException, ExecutionException, InterruptedException {
        System.out.println("Hello World!");
        System.out.println("Working on Riak Demo");
        other();      
        riak();
    }
    
     public static void other() throws UnknownHostException {
        System.out.println("other");        
    }
    
    public static void riak() throws UnknownHostException, ExecutionException, InterruptedException{
        System.out.println("Inside riak class");
        
        RiakClient client = RiakClient.newClient("192.168.0.15");
        
        Namespace ns = new Namespace("default", "java_code");
        Location location = new Location(ns, "java_key");
        RiakObject riakObject = new RiakObject();
        riakObject.setValue(BinaryValue.create("java_value"));
        StoreValue store;
        store = new StoreValue.Builder(riakObject)
              .withLocation(location)
              .withOption(Option.W, new Quorum(3)).build();
        client.execute(store);
    
    }
