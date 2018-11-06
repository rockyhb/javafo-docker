import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.io.ByteArrayOutputStream;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import javafo.api.JaVaFoApi;

public class JaVaFoHTTPD {

    public static void main(String[] args) throws Exception {
        HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);
        server.createContext("/test", new MyHandler());
        server.setExecutor(null); // creates a default executor
        server.start();
    }

    static class MyHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange t) throws IOException {
	    ByteArrayOutputStream os = new ByteArrayOutputStream();
	    byte[] buffer = new byte[1024];
	    int length;
	    while ((length = t.getRequestBody().read(buffer)) != -1) {
		os.write(buffer, 0, length);
	    }
	    System.out.println(os.toString());
	    String res = JaVaFoApi.exec(1000,os.toString());
	    System.out.println(res);
            t.sendResponseHeaders(200, res.length());
            OutputStream os2 = t.getResponseBody();
            os2.write(res.getBytes());
            os2.close();
        }
    }

}
