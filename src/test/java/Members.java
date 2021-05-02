import lombok.Getter;
import lombok.Setter;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Members implements Serializable{
   public Map<String, MemberData> getMap() {
      return map;
   }

   public void setMap(Map<String, MemberData> map) {
      this.map = map;
   }

   public Map<String,MemberData> map= new HashMap<>();
}
