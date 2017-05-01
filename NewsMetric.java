/**
 * Project 4, CS 2334, Section 010, April 19, 2017
 * 
 * This enumerated type specifies the type of measurement used to describe the
 * duration of the news story, with the possibilities being length or count.
 * 
 * @author Zak Koskovich, Jered Little, Vishnupriya Parasaram, and Jessica Horner
 * @version 1.0
 * 
 */
public enum NewsMetric {

	LENGTH,
	COUNT;

	/**
	 * <P>
	 * This method will convert the enumeration to a String.
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
