package com.coderscampus.salesreport;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class ReportSalesApplication {

	public static void main(String[] args) throws IOException {
		String[] fileNames = { "model3", "modelS", "modelX" };
		ReadFileSales loadDataFile = new ReadFileSales();
		for (String fileName : fileNames) {
			List<SalesData> modelCarsData = loadDataFile.loadData(fileName + ".csv");
			System.out.println(fileName + " Yearly Sales Report\r\n" + "--------------------------- ");
			printReportToConsole(modelCarsData, fileName);
		}

	}

	private static void printReportToConsole(List<SalesData> modelSalesCar, String model) {

		Map<Integer, List<SalesData>> groupedByYear = modelSalesCar.stream()
				.collect(Collectors.groupingBy(sale -> sale.getDate().getYear()));

		String totalSalesByYear = groupedByYear.entrySet().stream()
				.map(sale -> sale.getKey() + " -> "
						+ sale.getValue().stream().collect(Collectors.summingInt(SalesData::getSales)))
				.collect(Collectors.joining(" \n"));

		System.out.println(totalSalesByYear + "\n");

		Optional<SalesData> bestSale = modelSalesCar.stream()
				.max((sale1, sale2) -> sale1.getSales().compareTo(sale2.getSales()));
		Optional<SalesData> worseSale = modelSalesCar.stream()
				.min((sale1, sale2) -> sale1.getSales().compareTo(sale2.getSales()));

		System.out.println("The best month for " + model + " was: " + bestSale.get().getDate());
		System.out.println("The worst month for " + model + " was: " + worseSale.get().getDate() + "\n");

	}

}
