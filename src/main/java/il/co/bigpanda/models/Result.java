package il.co.bigpanda.models;

import java.io.Serializable;
import javax.validation.constraints.NotEmpty;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import org.springframework.hateoas.ResourceSupport;

@XmlRootElement(name = "result")
@XmlAccessorType(XmlAccessType.FIELD)
public class Result extends ResourceSupport implements Serializable{

    public Result(){
        super();
    }

    @NotEmpty(message = "dataSearch must not be empty")
    private String dataSearch;

    private String[] message;

    public String getDataSearch(){
        return dataSearch;
    }

    public void setDataSearch( String dataSearch ){
        this.dataSearch = dataSearch;
    }

    public String[] getMessage(){
        return message;
    }

    public void setMessage( String... message ){
        this.message = message;
    }

}
