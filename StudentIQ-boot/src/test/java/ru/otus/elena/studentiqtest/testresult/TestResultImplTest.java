
package ru.otus.elena.studentiqtest.testresult;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestResultImplTest {
    
    @Autowired
    TestResult testResult;
    
    public TestResultImplTest() {
    }

    @Test
    public void testPrintResult() throws NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
      
        Method methodGetMessage=TestResultImpl.class.getDeclaredMethod("getMessage", String.class, int.class);
        methodGetMessage.setAccessible(true);
        String result=(String) methodGetMessage.invoke(testResult, "vasya", 0);
        String expected="vasya, I wonder, how have you been managed to get our university??? Your result = 0";
        assertEquals(result,expected);

    }
    
}
