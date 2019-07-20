package test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.apache.commons.collections.bag.HashBag;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.junit.Test;

import com.google.gson.Gson;

import additional_functions.SHA256;
import blockchain_main.BlockHeader;
import blockchain_main.Fitbit_data;

public class Dataset_test {

	public static final String path = "One_Year_of_FitBitChargeHR_Data.csv";

	@Test
	public void readDataset() throws FileNotFoundException {
		SparkSession spark = SparkSession.builder().appName("SparkSample").master("local[*]").getOrCreate();
		// Read file
		Dataset<Row> csv = spark.read().format("csv").option("header", true).load(path);
		csv.show();
	}

	@Test
	public void readDataset2() throws FileNotFoundException {
		File file = new File(path);
		file.exists();
		Scanner inputStream = new Scanner(file);
		while (inputStream.hasNext()) {
			String data = inputStream.next();
			String[] values = data.split(",");
			System.out.println(data);
		}

	}

	@Test
	public void testFitbitData() {
		Gson parser = new Gson();
		try {
			List<Fitbit_data> s = Fitbit_data.getInstancesAsList();
			for (Fitbit_data data : s) {
				System.out.println(parser.toJson(data));
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testFitbitDataHash() throws FileNotFoundException {
		List<Fitbit_data> data = Fitbit_data.getInstancesAsList();
		Gson parser = new Gson();
		String hash;
				
		for(Fitbit_data f :data) {
			System.out.println(parser.toJson(f));
			hash = SHA256.generateHash(parser.toJson(f));
			System.out.println(hash);
		}
		
	}
}
