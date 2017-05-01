
public enum NewsMetric {

	LENGTH,
	COUNT;

	/**
	 * <P>
	 * This method will convert the enumeration to a prettier version.
	 * </P>
	 */
	@Override
	public String toString() {
		switch(this)
		{
		case LENGTH:
			return "Length";
		case COUNT:
			return "Count";
		default:
			throw new IllegalArgumentException();
		}
		
	}
	
}
