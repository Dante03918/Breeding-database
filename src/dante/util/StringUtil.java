package dante.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class StringUtil {

    public String alertBuilder(Set<String> overRabiesVacc, Set<String> monthRabiesVacc, Set<String> overOtherVacc, Set<String> monthOtherVacc){
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
    public List<String> listFromCuttedString(String stringChain){

        List<String> cutted = new ArrayList<>();

            Pattern pattern = Pattern.compile("(\\d{2}\\.\\d{2}\\.\\d{4}.-.\\d{2}\\.\\d{2}\\.\\d{4})");
            Matcher matcher = pattern.matcher(stringChain);

            while (matcher.find()) {
                cutted.add(matcher.group(1));
        }

        return cutted;
    }
    public String concatListContent(List<String> list){

       return String.join("\n", list);

    }
}
