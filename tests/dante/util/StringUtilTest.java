//package dante.util;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.regex.Matcher;
//import java.util.regex.Pattern;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//class StringUtilTest {
//
//    @BeforeEach
//    void setUp() {
//    }
//
//    @Test
//    void listFromCuttedString() {
//        List<String> cutted = new ArrayList<>();
//
//        String stringChain = "12.02.2018 - 13.05.2019***13.02.2008 - 15.01.2019";
//
//        Pattern pattern = Pattern.compile("(\\d{2}\\.\\d{2}\\.\\d{4}.-.\\d{2}\\.\\d{2}\\.\\d{4})");
//        Matcher matcher = pattern.matcher(stringChain);
//
//        while(matcher.find()){
//            cutted.add(matcher.group(1));
//            System.out.println(matcher.group(1));
//        }
//        for(String s : cutted){
//            System.out.println(s);
//        }
//
//    }
//}