import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.lang.reflect.Member;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;

public class MemberData implements Serializable {
    public String dataStoreDate;
    public Integer count;

    public String getDataStoreDate() {
        return dataStoreDate;
    }

    public void setDataStoreDate(String dataStoreDate) {
        this.dataStoreDate = dataStoreDate;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public MemberData(Integer count){
        this.count=count;
        this.dataStoreDate= new SimpleDateFormat("dd/MM/YYYY HH:mm").format(new Date());
    }

    @Override
    public String toString() {
        return dataStoreDate + " : "  + count;
    }
}
