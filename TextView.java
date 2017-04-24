import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
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
		
		jfText = new JFrame();
		jfText.setLayout(new BorderLayout());
		jfText.setSize(500,500);
		
		jtaNewsStoryList = new JTextArea();
		jspNewsStoryList = new JScrollPane();
		jspNewsStoryList.add(jtaNewsStoryList);
		
		jtaSummaryLine = new JTextArea();
		
		jfText.add(jspNewsStoryList, BorderLayout.NORTH);
		jfText.add(jtaSummaryLine, BorderLayout.SOUTH);
		jfText.setVisible(true);
		
		
		
		constructNewsStoriesAndSummary();
		constructTitle();
		
	}
	
	/**
	 * <P>
	 * This method constructs the news stories and summary line for the view.
	 * </P>
	 */
	private void constructNewsStoriesAndSummary() {
		
	}
	/**
	 * <P>
	 * This method constructs the title for the news maker view.
	 * </P>
	 */
	private void constructTitle() {
		
	}
	/**
	 * <P>
	 * This method handles when an action is performed.
	 * </P>
	 * @param e The event to process.
	 */
	//TODO he didnt name the variable here
	private void actionPerformed(ActionEvent e) {
		
	}
	
	//TODO TEST DELETE
	public static void main(String[] args) {
		new TextView(new NewsMakerModel(),new ArrayList<NewsMedia>(), new ArrayList<SortCriterion>());
	}
}
