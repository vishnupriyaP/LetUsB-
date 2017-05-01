import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
/**
 * Project 4, CS 2334, Section 010, April 23, 2017
 * <P>
 * This class provides the view for the text display option from the selection view. This involves
 * showing a list of stories on a certain newsmaker, along with a summary line.
 * </P>
 * <P>
 * Note that the this class contains: fields for a list of stories, summary line, 
 * and panes/text areas for displaying this information.
 * </P>
 * 
 * @author Dean Hougen, Jered Little, Vishnupriya Parasaram, Jessica Horner, and Zakary Koskovich 
 * @version 1.0
 * 
 */
//Jered Little created the stub code for this class.
public class TextView implements ActionListener {

	/** This variable contains the frame for the view. */
	private JFrame jfText;
	/** This variable contains the reference to the model. */
	NewsMakerModel newsMakerModel;
	/** This variable contains the list of news media. */
	private List<NewsMedia> newsMedia;
	/** This variable contains the list of sort criteria. */
	private List<SortCriterion> sortCriteria;
	/** This variable contains the list of stories to display. */
	private String listOfStories;
	/** This variable is the summary line to display. */
	private String summaryLine;
	/** This variable is the text area for the news story list. */
	private JTextArea jtaNewsStoryList;
	/** This variable is the scroll pane for the news story list. */
	private JScrollPane jspNewsStoryList;
	/** This variable is the text area for the summary line. */
	private JTextArea jtaSummaryLine;
	
	/**
	 * <P>
	 * This method sets the model, the media type, and the sort criteria for the view.
	 * </P>
	 * @param newsMakerModel
	 * @param newsMedia
	 * @param sortCriteria
	 */
	public TextView(NewsMakerModel newsMakerModel,List<NewsMedia> newsMedia, List<SortCriterion> sortCriteria) {
		this.newsMakerModel = newsMakerModel;
		this.newsMedia = newsMedia;
		this.sortCriteria = sortCriteria;
		this.newsMakerModel.addActionListener(this);
		
		constructNewsStoriesAndSummary();
		
		jfText = new JFrame();
		jfText.setLayout(new BorderLayout());
		jfText.setSize(1000,1000);
		
		jtaNewsStoryList = new JTextArea(listOfStories);
		jtaNewsStoryList.setEditable(false);
		jspNewsStoryList = new JScrollPane(jtaNewsStoryList);
		
		
		jtaSummaryLine = new JTextArea(summaryLine);
		jtaSummaryLine.setEditable(false);
		
		jfText.add(jspNewsStoryList, BorderLayout.CENTER);
		jfText.add(jtaSummaryLine, BorderLayout.SOUTH);
		constructTitle();
		jfText.setVisible(true);
		
	
		
	}
	
	/**
	 * <P>
	 * This method constructs the news stories and summary line for the view.
	 * </P>
	 */
	private void constructNewsStoriesAndSummary() {
		/* The list of stories as a String */
		String listOfStories = "";

		/*
		 * Sets to keep track of the distinct news source names and topics found
		 * (for the summary line).
		 */
		Set<String> distinctNewsSourceNames = new TreeSet<String>();
		Set<String> distinctTopics = new TreeSet<String>();
		Set<String> distinctSubjects = new TreeSet<String>();

		/* The running total of the words in the stories. */
		int totalLength = 0;

		/*
		 * A local reference to the story list so that we don't have use the
		 * accessor method repeatedly (wasting time).
		 */
		NewsStoryListModel newsStoryList = newsMakerModel.getNewsStoryListModel();

		// Make our own copy of the data so that we can sort it.
		List<NewsStory> newsStories = new ArrayList<NewsStory>(newsStoryList.size());

		// If the user asked for newspaper stories, add those. (NOTE: indexOf returns a int value >= 0 if there exists that character in the string)
		if (newsMedia.contains(NewsMedia.NEWSPAPER)) {
			for (int i = 0; i < newsStoryList.size(); i++) {
				NewsStory newsStory = newsStoryList.get(i);
				if (newsStory instanceof NewspaperStory) {
					newsStories.add(newsStory);
				}
			}
		}
		// If the user asked for TV news stories, add those.
		if (newsMedia.contains(NewsMedia.TV)) {
			for (int i = 0; i < newsStoryList.size(); i++) {
				NewsStory newsStory = newsStoryList.get(i);
				if (newsStory instanceof TVNewsStory) {
					newsStories.add(newsStory);
				}
			}
		}
		// If they want online news stories.
		if(newsMedia.contains(NewsMedia.ONLINE)) {
			for (int i = 0; i < newsStoryList.size(); i++) {
				NewsStory newsStory = newsStoryList.get(i);
				if (newsStory instanceof OnlineNewsStory) {
					newsStories.add(newsStory);
				}
			}
		}
		// Sort the list based on the user's sort criteria
		// Start with tertiary sort criterion and work to primary
		for (int i = 4; i >= 0; i--) {
			if (sortCriteria.get(i).equals(SortCriterion.SUBJECT)) {
				Collections.sort(newsStories,SubjectComparator.SUBJECT_COMPARATOR);
			} 
			else if (sortCriteria.get(i).equals(SortCriterion.LENGTH)) {
				Collections.sort(newsStories, LengthComparator.LENGTH_COMPARATOR);
			} 
			else if(sortCriteria.get(i).equals(SortCriterion.SOURCE)){
				Collections.sort(newsStories, SourceComparator.SOURCE_COMPARATOR);
			}
			else if(sortCriteria.get(i).equals(SortCriterion.DATE_TIME)){
				Collections.sort(newsStories, DateComparator.DATE_COMPARATOR);
			}
			else {
				Collections.sort(newsStories);
			}
		}

		// Cycle through the stories one at a time
		for (NewsStory newsStory : newsStories) {

			// Add any new source name encountered to the set of names
			// Since sets exclude duplicates, we don't need to check
			distinctNewsSourceNames.add(newsStory.getSource());

			// Add any new topic encountered to the set of topics
			// Since sets exclude duplicates, we don't need to check
			distinctTopics.add(newsStory.getTopic());

			// Add any new subject encountered to the set of subjects
			// Since sets exclude duplicates, we don't need to check
			distinctSubjects.add(newsStory.getSubject());


			// Add to the running total for length
			// If the type is TV news, use seconds (from length)
			if (newsMedia.contains(NewsMedia.TV)) {
				totalLength += newsStory.getLength();
			}
			// If the type is newspaper, use words
			// If the type is mixed, use words as common unit
			else {
				totalLength += newsStory.getLengthInWords();
			}

			// Convert the story to the display format and add it to the end of
			// the list
			listOfStories += UserInterface.convertToOutputFormat(newsStory, newsMedia) + "\n";
		}

		// Construct the summary line
		// If the type is newspaper, use words
		if (newsMedia.contains(NewsMedia.NEWSPAPER) || newsMedia.contains(NewsMedia.ONLINE)) {
			summaryLine = "Number of Stories: " + newsStories.size() + "; Number of Sources: "
					+ distinctNewsSourceNames.size() + "; Number of Words: " + totalLength + "; Number of Topics: "
					+ distinctTopics.size() + "; Number of Subjects: " + distinctSubjects.size();
		}
		// If the type is TV news, use seconds (from length)
		else if (newsMedia.contains(NewsMedia.TV)) {
			summaryLine = "Number of Stories: " + newsStories.size() + "; Number of Sources: "
					+ distinctNewsSourceNames.size() + "; Seconds: " + totalLength + "; Number of Topics: "
					+ distinctTopics.size() + "; Number of Subjects: " + distinctSubjects.size();
		}
		// If the type is mixed, use words as common unit
		else {
			summaryLine = "Number of Stories: " + newsStories.size() + "; Number of Sources: "
					+ distinctNewsSourceNames.size() + "; Number of Word Equivalents: " + totalLength
					+ "; Number of Topics: " + distinctTopics.size() + "; Number of Subjects: " + distinctSubjects.size();
		}
		this.listOfStories = listOfStories;
	}
	/**
	 * <P>
	 * This method constructs the title for the news maker view.
	 * </P>
	 */
	private void constructTitle() {
		jfText.setTitle("News stories for " + newsMakerModel.getName());
	}
	/**
	 * <P>
	 * This method handles when an action is performed.
	 * </P>
	 * @param e The event to process.
	 */
	//TODO he didnt name the variable here
	public void actionPerformed(ActionEvent e) {
		System.out.println("Here");
		constructNewsStoriesAndSummary();
		constructTitle();
	}

}
