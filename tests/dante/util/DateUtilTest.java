package dante.util;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class DateUtilTest {

    DateUtil dateUtil = new DateUtil();

    @Test
    void shouldReturnFalseCondition() {
        String incorrectFormatDate = "12/12/2001";
        assertFalse(dateUtil.dateValidation(incorrectFormatDate));
    }

    @Test
    void shouldReturnTrueCondition() {
        String correctFormatDate = "10.09.2012";
        assertTrue(dateUtil.dateValidation(correctFormatDate));
    }

    @Test
    void stringDateToLocalDateParser() {
        ArrayList<String> stringDates = new ArrayList<>();
        stringDates.add("12.03.2002");
        stringDates.add("24.01.2015");
        assertTrue(dateUtil.stringDateToLocalDateParser(stringDates).size() == stringDates.size());
    }
}