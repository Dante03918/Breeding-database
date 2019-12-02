package dante.util;

import java.util.ArrayList;
import java.util.Set;

public class StringUtil {

    public String builder(Set<String> overRabiesVacc, Set<String> monthRabiesVacc, Set<String> overOtherVacc, Set<String> monthOtherVacc){
        StringBuilder stringBuilder = new StringBuilder();

        if(!overRabiesVacc.isEmpty()) {
            stringBuilder.append("Przeterminowane szczepienia na wściekliznę \n");
            for(String s : overRabiesVacc){
                stringBuilder.append(s);
                stringBuilder.append("\n");
            }
        } if(!monthRabiesVacc.isEmpty()){
            stringBuilder.append("Szczepienia na wściekliznę zbliżającą się datą ważności \n");
            for(String s : monthRabiesVacc){
                stringBuilder.append(s);
                stringBuilder.append("\n");
            }
        } if(!overOtherVacc.isEmpty()){
            stringBuilder.append("Przeterminowane szczepienia na wirusówki \n");
            for(String s : overOtherVacc){
                stringBuilder.append(s);
                stringBuilder.append("\n");
            }
        } if(!monthOtherVacc.isEmpty()){
            stringBuilder.append("Szczepienia na wirusówki ze zbliżającą się datą ważności \n");
            for(String s : monthOtherVacc){
                stringBuilder.append(s);
                stringBuilder.append("\n");
            }
        }
        return stringBuilder.toString();
}
}
