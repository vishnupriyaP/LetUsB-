import java.util.List;

public enum NewsMedia {

	
	NEWSPAPER,
	ONLINE,
	TV;
	public static List<NewsMedia> VALUES_LIST;
	
	
	/**
	 * <P>
	 * This method will convert the enumeration to a prettier version.
	 * </P>
	 */
	@Override
	public String toString() {
		switch (this) {
		case NEWSPAPER:
			return "Newspaper";
		case ONLINE:
			return "Online Story";
		case TV:
			return "TV Story";
		default:
			throw new IllegalArgumentException();
		}
		
	}
	
	
	/**
	 * <P>
	 * This method returns the enumeration values as a list.
	 * </P>
	 * @return The enumeration values as a list.
	 */
	public List<NewsMedia> valuesAsList() {
		return null;
		
	}
	
}
