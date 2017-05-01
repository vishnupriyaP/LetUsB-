
public enum Month {
	JANUARY,
	FEBRUARY,
	MARCH,
	APRIL,
	MAY,
	JUNE,
	JULY,
	AUGUST,
	SEPTEMBER,
	OCTOBER,
	NOVEMBER,
	DECEMBER;
	
	/**
	 * <P>
	 * This method will convert the enumeration to a prettier version.
	 * </P>
	 */
	@Override
	public String toString() {
		switch (this) {
		case JANUARY:
			return "January";
		case FEBRUARY:
			return "February";
		case MARCH:
			return "March";
		case APRIL:
			return "April";
		case MAY:
			return "May";
		case JUNE:
			return "June";
		case JULY:
			return "July";
		case AUGUST:
			return "August";
		case SEPTEMBER:
			return "September";
		case OCTOBER:
			return "October";
		case NOVEMBER:
			return "November";
		case DECEMBER:
			return "December";
		default:
			throw new IllegalArgumentException();
		}
		
	}
	/**
	 * <P>
	 * This method will convert from an enumeration to an integer value.
	 * </P>
	 */
	public int toInt() {
		switch (this) {
		case JANUARY:
			return 1;
		case FEBRUARY:
			return 2;
		case MARCH:
			return 3;
		case APRIL:
			return 4;
		case MAY:
			return 5;
		case JUNE:
			return 6;
		case JULY:
			return 7;
		case AUGUST:
			return 8;
		case SEPTEMBER:
			return 9;
		case OCTOBER:
			return 10;
		case NOVEMBER:
			return 11;
		case DECEMBER:
			return 12;
		default:
			throw new IllegalArgumentException();
		}
		
	}
	/**
	 * <P>
	 * This method will convert an integer to a month enumeration.
	 * </P>
	 * @param x The integer to convert to a date.
	 */
	//TODO he didnt specify variable name
	public static Month fromInt(int x) {
		switch (x) {
		case 1:
			return Month.JANUARY;
		case 2:
			return Month.FEBRUARY;
		case 3:
			return Month.MARCH;
		case 4:
			return Month.APRIL;
		case 5:
			return Month.MAY;
		case 6:
			return Month.JUNE;
		case 7:
			return Month.JULY;
		case 8:
			return Month.AUGUST;
		case 9:
			return Month.SEPTEMBER;
		case 10:
			return Month.OCTOBER;
		case 11:
			return Month.NOVEMBER;
		case 12:
			return Month.DECEMBER;
		default:
			throw new IllegalArgumentException();
		}
		
	}

	
}
