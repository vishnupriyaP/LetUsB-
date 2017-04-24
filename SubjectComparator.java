import java.util.Comparator;

/**
 * Project 4, CS 2334, Section 010, April 23, 2017
 * <P>
 * The SubjectComparator compares the subjects of two news stories.
 * </P>
 * <P>
 * Note that the constructor has nothing to do, so it is omitted.
 * </P>
 * 
 * @author Dean Hougen, Jered Little, Vishnupriya Parasaram, Jessica Horner, and Zakary Koskovich 
 * @version 1.0
 * 
 */
//Jered Little created the stub code for this class.
public class SubjectComparator implements Comparator<NewsStory> {

	/** This method is used to call the compare method below. */
	public static SubjectComparator SUBJECT_COMPARATOR;
	
	/**
	 * <P>
	 * This method compares the subjects of two news stories.
	 * </P>
	 * @param newsStory1 The first story to compare.
	 * @param newsStory2 The second story to compare.
	 * @return An integer value representing whether or not the first story
	 * is greater than, less than, or equal to the second stories subject.
	 */
	@Override
	public int compare(NewsStory newsStory1, NewsStory newsStory2) {
		return 0;
		
	}


	
}
