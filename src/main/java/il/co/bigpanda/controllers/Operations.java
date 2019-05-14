package il.co.bigpanda.controllers;

import il.co.bigpanda.models.OperationsVO;
import il.co.bigpanda.models.Result;
import il.co.bigpanda.services.StaticContextInitializer;
import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class Operations{

    @GetMapping("/result/{dataSearch}")
    public ResponseEntity<Result> result(
            @Valid @PathVariable("dataSearch") String dataSearch )
            throws InterruptedException{

        List<JSONObject> temp = new ArrayList();
        for( OperationsVO item : StaticContextInitializer.jsonRead ){
            temp.add( item.getJsonObject() );
        }

        int countEvent_type = 0;
        int count = 0;
        for( JSONObject item : temp ){
            if( item.has( "event_type" ) ){
                countEvent_type++;
                if( item.has( "data" ) ){

                    if( item.getString( "data" ).equals( dataSearch ) ){
                        count++;
                    }
                }
            }
        }
        Result result = new Result();
        result.setDataSearch( dataSearch );
        result.setMessage( "A count of events by event type: " + countEvent_type, "A count of words ("+ dataSearch +") encountered in the data field of the events: " + count );

        return new ResponseEntity<>( result, HttpStatus.OK );
    }
}
