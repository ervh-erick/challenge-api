package com.erick.challenge.api.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Util {

	public static String formatDate(LocalDate date) {
		DateTimeFormatter formatters = DateTimeFormatter.ofPattern("dd/MM/uuuu");
	    return date.format(formatters);
	}
}
