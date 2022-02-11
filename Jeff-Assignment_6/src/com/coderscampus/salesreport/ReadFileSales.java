package com.coderscampus.salesreport;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class ReadFileSales {
	
		public List<SalesData> loadData (String standardFileName) throws IOException {
			List<SalesData> fileData = new ArrayList<>();
			BufferedReader fileReader = null;
			try {
				fileReader = new BufferedReader(new FileReader(standardFileName));
				String line = fileReader.readLine();
				while ((line = fileReader.readLine()) != null) {
					String[] data = line.split(",");
					fileData.add(new SalesData(YearMonth.parse(data[0],DateTimeFormatter.ofPattern("MMM-yy")), Integer.parseInt(data[1])));
				}
				return fileData;
			} finally {
				if (fileReader != null)
					fileReader.close();
			}
		}

}
