package il.co.bigpanda.models;

import org.json.JSONObject;

public class OperationsVO{

    public OperationsVO(){
    }

    private JSONObject jsonObject;

    public JSONObject getJsonObject(){
        return jsonObject;
    }

    public void setJsonObject( JSONObject jsonObject ){
        this.jsonObject = jsonObject;
    }
}
