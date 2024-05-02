package com.rightToVote;

import java.util.ArrayList;
import java.util.List;

public class Main {

	static int maxScientificValue = 0;
	static List<Instrument> selectedInstruments = new ArrayList<>();

	public static void main(String[] args) {
		List<Instrument> instruments = new ArrayList<>();
		instruments.add(new Instrument(3, 2, 10));
		instruments.add(new Instrument(4, 3, 15));
		instruments.add(new Instrument(2, 1, 8));
		instruments.add(new Instrument(5, 4, 20));

		int capacityWeight = 5;
		int capacityVolume = 7;

		generateSubsets(instruments, 0, new ArrayList<>(), capacityWeight, capacityVolume, 0);

		System.out.println("Selected instruments:");
		for (Instrument instrument : selectedInstruments) {
			System.out.println("- Instrument weight: " + instrument.weight + " kg, volume: " + instrument.volume
					+ " m^3, value: " + instrument.value);
		}
		System.out.println("Total scientific value achieved: " + maxScientificValue);
	}

	private static void generateSubsets(List<Instrument> instruments, int index, List<Instrument> currentSubset,
			int remainingWeight, int remainingVolume, int currentValue) {
		if (index == instruments.size()) {
			if (currentValue > maxScientificValue) {
				maxScientificValue = currentValue;
				selectedInstruments = new ArrayList<>(currentSubset);
			}
			return;
		}

		Instrument currentInstrument = instruments.get(index);
		if (currentInstrument.weight <= remainingWeight && currentInstrument.volume <= remainingVolume) {
			currentSubset.add(currentInstrument);
			generateSubsets(instruments, index + 1, currentSubset, remainingWeight - currentInstrument.weight,
					remainingVolume - currentInstrument.volume, currentValue + currentInstrument.value);
			currentSubset.remove(currentSubset.size() - 1);
		}

		generateSubsets(instruments, index + 1, currentSubset, remainingWeight, remainingVolume, currentValue);
	}
}
