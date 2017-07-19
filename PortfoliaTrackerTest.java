package com.g5.pf.test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class PortfoliaTrackerTest {

	public static void main(String[] args) {
		String[] units = null;
		List<String> lines = null;
		Map<Integer, Integer> lineData = null;

		String fileName = args[0];

		try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {

			String line;
			lines = new ArrayList<String>();
			lineData = new ConcurrentHashMap<Integer, Integer>();
			int i = 0;
			int result = 0;
			while ((line = br.readLine()) != null) {
				lines.add(line);

				units = line.split(",");
				for (String unit : units) {
					String[] keyValue = unit.split("-");
					result = result + Integer.parseInt(keyValue[1].trim());
				}

				lineData.put(i, result);
				result = 0;
				i++;
			}

			for (int j = 0; j < lineData.size();) {
				Set<Integer> keys = lineData.keySet();
				int max = 0;
				int newKey = 0;

				for (int key : keys) {
					if (max < lineData.get(key)) {
						max = lineData.get(key);
						newKey = key;
					}
				}
				System.out.println(lines.get(newKey));
				lineData.remove(newKey);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
