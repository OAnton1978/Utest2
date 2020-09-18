package anton;

import org.junit.jupiter.api.Assertions;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;


public class Test {
    @org.junit.jupiter.api.Test
    public void testMainListValidResult() {
        // given:
        int sizeList = 2; //размер коллекции
        String[] columnMapping = {"id", "firstName", "lastName", "country", "age"};
        String fileName = "data.csv";
        long idTest = 1;
        String firstNameTest = "John";
        String lastNameTest = "Smith";
        String countryTest = "USA";
        int ageTest = 25;
        // when:
        List<Employee> list = Main.parseCSV(columnMapping, fileName);
        // then:
        Assertions.assertTrue(sizeList == list.size());
        int qqq = list.get(0).getAge();
        Assertions.assertTrue(idTest == list.get(0).getId());
        Assertions.assertEquals(firstNameTest, list.get(0).getFirstName());
        Assertions.assertEquals(lastNameTest, list.get(0).getLastName());
        Assertions.assertEquals(countryTest, list.get(0).getCountry());
        Assertions.assertTrue(ageTest == list.get(0).getAge());
    }

    @org.junit.jupiter.api.Test
    public void testMainJsonValidResult() {
        // given:
        String[] columnMapping = {"id", "firstName", "lastName", "country", "age"};
        String fileName = "data.csv";
        List<Employee> list = Main.parseCSV(columnMapping, fileName);
        String jsonSample = "[{\"id\":1,\"firstName\":\"John\",\"lastName\":\"Smith\",\"country\":\"USA\",\"age\":25},{\"id\":2,\"firstName\":\"Inav\",\"lastName\":\"Petrov\",\"country\":\"RU\",\"age\":23}]";
        // when:
        String json = Main.listToJson(list);
        // then:
        Assertions.assertEquals(jsonSample, json);
    }

    @org.junit.jupiter.api.Test
    public void testMainJsonWriteResult() {
        // given:
        String jsonSample = "[{\"id\":1,\"firstName\":\"John\",\"lastName\":\"Smith\",\"country\":\"USA\",\"age\":25},{\"id\":2,\"firstName\":\"Inav\",\"lastName\":\"Petrov\",\"country\":\"RU\",\"age\":23}]";
        // when:
        Main.writeString(jsonSample);
        // then:
        StringBuffer jsonResult = new StringBuffer("");
        try (FileInputStream fis = new FileInputStream("data.json")) {
            jsonResult = new StringBuffer("");
            int i = 0;
            int c;
            while ((c = fis.read()) != -1) {
                jsonResult.insert(i, (char) c);
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        String jsonResultString = jsonResult.reverse().toString();
        Assertions.assertEquals(jsonResultString, jsonSample);
    }
}
