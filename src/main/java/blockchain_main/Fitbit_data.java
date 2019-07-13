package blockchain_main;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

/**
 * @author aki
 *
 */
public class Fitbit_data {
	// variable names are matching with CSV file
	public static final String path = "One_Year_of_FitBitChargeHR_Data.csv";
	public String Date, Calories, Steps, Distance, floors, Minutes_sitting, Minutes_of_slow_activity,
			Minutes_of_moderate_activity, Minutes_of_intense_activity, Calories_Activity;

	public Fitbit_data() {

	}

	public Fitbit_data(String row) {
		String[] values = row.split(",");
		this.Date = values[0];
		this.Calories = values[1];
		this.Steps = values[2];
		this.Distance = values[3];
		this.floors = values[4];
		this.Minutes_sitting = values[5];
		this.Minutes_of_slow_activity = values[6];
		this.Minutes_of_moderate_activity = values[7];
		this.Minutes_of_intense_activity = values[8];
		this.Calories_Activity = values[9];
	}

	/**
	 * @return List of Fitbit Instances(data per day)
	 * @throws FileNotFoundException Data has to be comma separated 10 Strings
	 *                               Reference to
	 *                               One_Year_of_FitbitChargeHR_Data.csv
	 */
	public static List<Fitbit_data> getInstancesAsList() throws FileNotFoundException {
		List<Fitbit_data> instances = new ArrayList<Fitbit_data>();

		File file = new File(path);
		file.exists();
		Scanner inputStream = new Scanner(file);
		while (inputStream.hasNext()) {
			String data = inputStream.next();
			instances.add(new Fitbit_data(data));
		}
		return instances;
	}

	public void setDate(String date) {
		this.Date = date;
	}

	public String getDate() {
		return Date;
	}

	public void setCalories(String calories) {
		this.Calories = calories;
	}

	public String getCalories() {
		return Calories;
	}

	public void setSteps(String steps) {
		this.Steps = steps;
	}

	public String getSteps() {
		return Steps;
	}

	public void setDistance(String distance) {
		this.Distance = distance;
	}

	public String getDistance() {
		return Distance;
	}

	public void setFloors(String floors) {
		this.floors = floors;
	}

	public String getFloors() {
		return floors;
	}

	public void setMinutesSitting(String minutes_sitting) {
		this.Minutes_sitting = minutes_sitting;
	}

	public String getMinutesSitting() {
		return Minutes_sitting;
	}

	public void setMinutesofSlowActivity(String minutes_slow_activity) {
		this.Minutes_of_slow_activity = minutes_slow_activity;
	}

	public String getMinutesofSlowActivity() {
		return Minutes_of_intense_activity;
	}

	public void setMinutesofModerateActivity(String minutes_moderate_activity) {
		this.Minutes_of_moderate_activity = minutes_moderate_activity;
	}

	public String getMinutesofModerateActivity() {
		return Minutes_of_moderate_activity;
	}

	public void setMinutesofIntenseActivity(String minutes_intense_activity) {
		this.Minutes_of_intense_activity = minutes_intense_activity;
	}

	public String getinutesofIntenseActivity() {
		return Minutes_of_intense_activity;
	}

	public void setCaloriesActivity(String caloriesactivity) {
		this.Calories_Activity = caloriesactivity;
	}

	public String getCaloriesActivity() {
		return Calories_Activity;
	}

	/**
	 * @return Spark sql Dataset
	 * @throws FileNotFoundException this method can be used when handling dataset
	 *                               with SQL command
	 */
//	protected Dataset<Row> loadDataset() throws FileNotFoundException {
//		SparkSession spark = SparkSession.builder().appName("SparkSample").master("local[*]").getOrCreate();
//		// load csv into dataset(dataframe)
//		Dataset<Row> csv = spark.read().format("csv").option("header", true).load(path);
//		return csv;
//	}
}
