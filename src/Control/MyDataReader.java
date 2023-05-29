package Control;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class MyDataReader {
    public Object[][] readData(String filePath) {
        Path relativePath = Paths.get("src", "Model", filePath);
        File file = relativePath.toFile();

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            List<Object[]> dataList = new ArrayList<>();

            while ((line = reader.readLine()) != null) {
                String[] values = line.split(",");

                Object[] row = new Object[values.length];
                System.arraycopy(values, 0, row, 0, values.length);

                dataList.add(row);
            }

            Object[][] data = new Object[dataList.size()][];
            for (int i = 0; i < dataList.size(); i++) {
                data[i] = dataList.get(i);
            }

            Arrays.sort(data, Comparator.comparing(row -> Integer.parseInt((String) row[1]), Comparator.reverseOrder()));

            return data;
        } catch (IOException e) {
            e.printStackTrace();
            return new Object[0][0];
        }
    }
}
