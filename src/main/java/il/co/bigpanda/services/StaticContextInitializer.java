package il.co.bigpanda.services;

import il.co.bigpanda.models.OperationsVO;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

@Component
public class StaticContextInitializer implements InitializingBean{

    public static List< OperationsVO> jsonRead;

    @Override
    public void afterPropertiesSet() throws Exception{
        jsonRead = new ArrayList();
        ProcessBuilder builder = new ProcessBuilder( "E:/test/generator.exe" );
        final Process process = builder.start();
        final Thread ioThread = new Thread(){
            @Override
            public void run(){
                try{
                    try ( BufferedReader reader = new BufferedReader(
                            new InputStreamReader( process.getInputStream() ) ) ){
                        String line = null;
                        while( ( line = reader.readLine() ) != null ){
                            try{
                                OperationsVO opertion = new OperationsVO();
                                JSONObject jsonObject = new JSONObject( line );
                                opertion.setJsonObject( jsonObject );
                                jsonRead.add( opertion );
                            } catch ( JSONException ex ) {
                                System.out.println( ex.getLocalizedMessage() );
                            }
                        }
                    }
                } catch ( final IOException e ) {
                    e.printStackTrace();
                }
            }
        };
        ioThread.start();
    }
}
