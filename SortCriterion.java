/**
 * Project 4, CS 2334, Section 010, April 19, 2017
 * 
 * This enumerated type specifies the sort criterion for arranging new stories,
 * with the possibilities being source, topic, subject, length, or date/time.
 * 
 * @author Zak Koskovich, Jered Little, Vishnupriya Parasaram, and Jessica Horner
 * @version 1.0
 */
public enum SortCriterion {
	SOURCE,
	TOPIC,
	SUBJECT,
	LENGTH,
	DATE_TIME;
	
	/**
	 * <P>
	 * This method will convert the enumeration to a prettier version.
	 * </P>
	 */
	@Override
	public String toString() {
		switch(this)
		{
		case SOURCE:
			return "Source";
		case TOPIC:
			return "Topic";
		case SUBJECT:
			return "Subject";
		case LENGTH:
			return "Length";
		case DATE_TIME:
			return "Date/Time";
		default:
			throw new IllegalArgumentException();
		}
	}
}
