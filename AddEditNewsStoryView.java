import java.awt.GridLayout;
import java.text.NumberFormat;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.NumberFormatter;

/**
 * Project 4, CS 2334, Section 010, April 19, 2017
 * <P>
 * This class provides the view to add and/or edit a news story.
 * </P>
 * <P>
 * Note that the this class contains labels, panels, and JComboBoxes for all the necessary components.
 * This includes fields for the story source, type, subject, newsmakers, length, etc. This class also
 * contains a reference to the model, which will be used to pull and display data from. 
 * </P>
 * 
 * @author Dean Hougen, Jered Little, Vishnupriya Parasaram, Jessica Horner, and Zakary Koskovich 
 * @version 1.0
 * 
 */

//NOTE: Jered Little put together the stub code & basic view display for this class.
public class AddEditNewsStoryView extends JPanel{
	
	/** This field is the serial id for the add/edit news story view */
	private static final long serialVersionUID = -2026934989310105918L;
	/** This variable contains a reference to the database model. */
	private NewsDataBaseModel newsDataBaseModel;
	/** This variable contains a reference to the news story model. */
	private NewsStory newsStory;
	/** This field contains the label for the news story type. */
	private JLabel jlbNewsStoryType;
	/** This field contains the JComboBox for the news story type. */
	JComboBox<NewsMedia> jcbNewsStoryType;
	/** This field contains a JPanel for the news story type. */
	private JPanel jpNewsStoryType;
	/** This field contains the label for the news story source. */
	private JLabel jlbNewsStorySource;
	/** This field contains the JComboBox for the news story source. */
	JComboBox<String> jcbNewsStorySource;
	/** This field contains the JPanel for the news story source. */
	private JPanel jpNewsStorySource;
	/** This field contains the JLabel for the news story topic. */
	private JLabel jlbNewsStoryTopic;
	/** This field contains the JComboBox for the news story topic. */
	JComboBox<String> jcbNewsStoryTopic;
	/** This field contains the JPanel for the news story topic. */
	private JPanel jpNewsStoryTopic;
	/** This field contains a label for the news story subject. */
	private JLabel jlbNewsStorySubject;
	/** This field contains the JComboBox for the news story subject. */
	JComboBox<String> jcbNewsStorySubject;
	/** This field contains the JPanel for the news story subject. */
	private JPanel jpNewsStorySubject;
	/** This field contains a label for the first news maker in the news story. */
	private JLabel jlbNewsStoryNewsMaker1;
	/** This field contains a JComboBox for the first news maker in the news story. */
	JComboBox<String> jcbNewsStoryNewsMaker1;
	/** This field contains a JPanel for the first news maker in the news story. */
	private JPanel jpNewsStoryNewsMaker1;
	/** This field contains a label for the second news maker in the news story. */
	private JLabel jlbNewsStoryNewsMaker2;
	/** This field contains a JComboBox for the second news maker in the news story. */
	JComboBox<String> jcbNewsStoryNewsMaker2;
	/** This field contains a JPanel for the second news maker in the news story. */
	private JPanel jpNewsStoryNewsMaker2;
	/** This field contains the panel for the news story length. */
	private JLabel jlbNewsStoryLength;
	/** This field contains the number formatter for the integer field input. */
	private NumberFormat integerFieldFormatter;
	/** This field contains text field for the news story length. */
	JFormattedTextField jftfNewsStoryLength;
	/** This field contains the panel for the news story length. */
	private JPanel jplNewsStoryLength;
	/** This field contains the label for the news story year. */
	private JLabel jlbNewsStoryYear;
	/** This field contains an array for the years //TODO what */
	private Integer[] years;
	/** This field contains the JComboBox to enter the year for the story. */
	JComboBox<Integer> jcbNewsStoryYear;
	/** This field contains the panel for the news story year. */
	private JPanel jplNewsStoryYear;
	/** This field contains the label for the news story month. */
	private JLabel jlbNewsStoryMonth;
	/** This field contains the JComboBox for the news story month. */
	JComboBox<Month> jcbNewsStoryMonth;
	/** This field contains the JPanel for the news story month. */
	private JPanel jplNewsStoryMonth;
	/** This field contains a label for the news story day. */
	private JLabel jlbNewsStoryDay;
	/** This field contains a label for the days //TODO what. */
	private Integer[] days;
	/** This field contains the JComboBox for the news story day. */
	JComboBox<Integer> jcbNewsStoryDay;
	/** This field contains the panel for the news story day. */
	private JPanel jplNewsStoryDay;
	/** This field contains a label for the news story part of day. */
	private JLabel jlbNewsStoryPartOfDay;
	/** This field contains the JComboBox for the news story part of day. */
	JComboBox<PartOfDay> jcbNewsStoryPartOfDay;
	/** This field contains the panel for the news story part of day. */
	private JPanel jplNewsStoryPartOfDay;
	/** This field contains the panel for when the story was published //TODO what. */
	private JPanel jplNewsStoryWhen;
	/** This field contains the button to add/edit the story. */
	JButton jbtAddEditNewsStory;
	/** This field contains the panel to add/edit a story. */
	private JPanel jplAddEditNewsStory;
	
	/**
	 * 
	 * <P>
	 * This constructor initializes all the buttons, panels, and puts them
	 * in their proper places using the GridLayout class.
	 * </P>
	 * 
	 * @param newsDataBaseModel - The model for the news data base.
	 * @param newsStoryModel - the model for the news story.
	 */
	public AddEditNewsStoryView(NewsDataBaseModel newsDataBaseModel, NewsStory newsStory
			) {
		//TODO constructor. add code.
		this.newsDataBaseModel = newsDataBaseModel;
			this.setLayout(new GridLayout(9, 1,0,10));

			/* Story Type */
			jlbNewsStoryType = new JLabel("Type:");
			jcbNewsStoryType = new JComboBox<NewsMedia>();
			jcbNewsStoryType.addItem(NewsMedia.NEWSPAPER);
			jcbNewsStoryType.addItem(NewsMedia.ONLINE);
			jcbNewsStoryType.addItem(NewsMedia.TV);
			
			jpNewsStoryType = new JPanel(new GridLayout(1,2));
			jpNewsStoryType.add(jlbNewsStoryType);
			jpNewsStoryType.add(jcbNewsStoryType);

			/* Story Source */
			jlbNewsStorySource = new JLabel("Source:");
			//TODO change the JComboBox
			jcbNewsStorySource = new JComboBox<String>();
			System.out.println(newsDataBaseModel.getNewsSources().length);
			for(int i = 0; i < newsDataBaseModel.getNewsSources().length; i++) {
				jcbNewsStorySource.addItem(newsDataBaseModel.getNewsSources()[i]);
			}
			jpNewsStorySource = new JPanel(new GridLayout(1,2));
			jpNewsStorySource.add(jlbNewsStorySource);
			jpNewsStorySource.add(jcbNewsStorySource);


			/* Story Topic */
			jlbNewsStoryTopic = new JLabel("Topic:");
			jcbNewsStoryTopic = new JComboBox<String>();
			for(int i = 0; i < newsDataBaseModel.getNewsTopics().length; i++) {
				jcbNewsStoryTopic.addItem(newsDataBaseModel.getNewsTopics()[i]);
			}
			jpNewsStoryTopic = new JPanel(new GridLayout(1,2));
			jpNewsStoryTopic.add(jlbNewsStoryTopic);
			jpNewsStoryTopic.add(jcbNewsStoryTopic);

			/* Story Subject */
			jlbNewsStorySubject = new JLabel("Subject:");
			jcbNewsStorySubject = new JComboBox<String>();
			for(int i = 0; i < newsDataBaseModel.getNewsSubjects().length; i++) {
				jcbNewsStorySubject.addItem(newsDataBaseModel.getNewsSubjects()[i]);
			}
			jpNewsStorySubject = new JPanel(new GridLayout(1,2));
			jpNewsStorySubject.add(jlbNewsStorySubject);
			jpNewsStorySubject.add(jcbNewsStorySubject);

			/* Story NewsMaker 1 */
			jlbNewsStoryNewsMaker1 = new JLabel("News Maker 1:");
			jcbNewsStoryNewsMaker1 = new JComboBox<String>();
			jcbNewsStoryNewsMaker1.setEditable(true);
			String[] names1 = newsDataBaseModel.getNewsMakerNames();
			for(int i = 0; i < names1.length; i++) {
				jcbNewsStoryNewsMaker1.addItem(names1[i]);
			}
			jpNewsStoryNewsMaker1 = new JPanel(new GridLayout(1,2));
			jpNewsStoryNewsMaker1.add(jlbNewsStoryNewsMaker1);
			jpNewsStoryNewsMaker1.add(jcbNewsStoryNewsMaker1);

			/* Story NewsMaker 2 */
			jlbNewsStoryNewsMaker2 = new JLabel("News Maker 2:");
			//TODO change the JComboBox
			jcbNewsStoryNewsMaker2 = new JComboBox<String>();
			jcbNewsStoryNewsMaker2.setEditable(true);
			String[] names2 = newsDataBaseModel.getNewsMakerNames();
			for(int i = 0; i < names2.length; i++) {
				jcbNewsStoryNewsMaker2.addItem(names2[i]);
			}
			jpNewsStoryNewsMaker2 = new JPanel(new GridLayout(1,2));
			jpNewsStoryNewsMaker2.add(jlbNewsStoryNewsMaker2);
			jpNewsStoryNewsMaker2.add(jcbNewsStoryNewsMaker2);


			/* Story Length */
			jlbNewsStoryLength = new JLabel("Length:");
			//min and max size
			NumberFormatter nf = new NumberFormatter();
			nf.setMinimum(new Integer(0));
			nf.setMaximum(new Integer(1000000000));
			jftfNewsStoryLength = new JFormattedTextField(nf);
			jplNewsStoryLength = new JPanel(new GridLayout(1,2));
			jplNewsStoryLength.add(jlbNewsStoryLength);
			jplNewsStoryLength.add(jftfNewsStoryLength);


			/* Year */
			jlbNewsStoryYear = new JLabel("Year:");
			jcbNewsStoryYear = new JComboBox<Integer>();
			for(int i = 1; i <= 2017; i++) {
				jcbNewsStoryYear.addItem(i);
			}
			jplNewsStoryYear = new JPanel(new GridLayout(1,2));
			jplNewsStoryYear.add(jlbNewsStoryYear);
			jplNewsStoryYear.add(jcbNewsStoryYear);

			/* Month */
			jlbNewsStoryMonth = new JLabel("Month:");
			jcbNewsStoryMonth = new JComboBox<Month>();
			jcbNewsStoryMonth.addItem(Month.JANUARY);
			jcbNewsStoryMonth.addItem(Month.FEBRUARY);
			jcbNewsStoryMonth.addItem(Month.MARCH);
			jcbNewsStoryMonth.addItem(Month.APRIL);
			jcbNewsStoryMonth.addItem(Month.MAY);
			jcbNewsStoryMonth.addItem(Month.JUNE);
			jcbNewsStoryMonth.addItem(Month.JULY);
			jcbNewsStoryMonth.addItem(Month.AUGUST);
			jcbNewsStoryMonth.addItem(Month.SEPTEMBER);
			jcbNewsStoryMonth.addItem(Month.OCTOBER);
			jcbNewsStoryMonth.addItem(Month.NOVEMBER);
			jcbNewsStoryMonth.addItem(Month.DECEMBER);
			jplNewsStoryMonth = new JPanel(new GridLayout(1,2));
			jplNewsStoryMonth.add(jlbNewsStoryMonth);
			jplNewsStoryMonth.add(jcbNewsStoryMonth);

			/* Day */
			jlbNewsStoryDay = new JLabel("Day:");
			jcbNewsStoryDay = new JComboBox<Integer>();
			for(int i = 1; i <= 31; i++) {
				jcbNewsStoryDay.addItem(i);
			}
			jplNewsStoryDay = new JPanel(new GridLayout(1,2));
			jplNewsStoryDay.add(jlbNewsStoryDay);
			jplNewsStoryDay.add(jcbNewsStoryDay);

			/* Part Of Day */
			jlbNewsStoryPartOfDay = new JLabel("Part of Day:");
			jcbNewsStoryPartOfDay = new JComboBox<PartOfDay>();
			jcbNewsStoryPartOfDay.addItem(PartOfDay.MORNING);
			jcbNewsStoryPartOfDay.addItem(PartOfDay.AFTERNOON);
			jcbNewsStoryPartOfDay.addItem(PartOfDay.EVENING);
			jcbNewsStoryPartOfDay.addItem(PartOfDay.LATE_NIGHT);
			jplNewsStoryPartOfDay = new JPanel(new GridLayout(1,2));
			jplNewsStoryPartOfDay.add(jlbNewsStoryPartOfDay);
			jplNewsStoryPartOfDay.add(jcbNewsStoryPartOfDay);

			/* Panel for WHEN */
			jplNewsStoryWhen = new JPanel(new GridLayout(1,4,50,0));
			jplNewsStoryWhen.add(jplNewsStoryYear);
			jplNewsStoryWhen.add(jplNewsStoryMonth);
			jplNewsStoryWhen.add(jplNewsStoryDay);
			jplNewsStoryWhen.add(jplNewsStoryPartOfDay);

			/* Add Story */
			jbtAddEditNewsStory = new JButton();
			jplAddEditNewsStory = new JPanel();
			jplAddEditNewsStory.add(jbtAddEditNewsStory);


			this.add(jpNewsStoryType);
			this.add(jpNewsStorySource);
			this.add(jpNewsStoryTopic);
			this.add(jpNewsStorySubject);
			this.add(jpNewsStoryNewsMaker1);
			this.add(jpNewsStoryNewsMaker2);
			this.add(jplNewsStoryLength);
			this.add(jplNewsStoryWhen);
			this.add(jplAddEditNewsStory);
			this.setVisible(true);

			//if they chose edit news story, we need to populate the fields
			if(newsStory != null) {
				//part of day
				if(newsStory instanceof OnlineNewsStory) jcbNewsStoryPartOfDay.setSelectedItem(((OnlineNewsStory) newsStory).getPartOfDay());
				if(newsStory instanceof TVNewsStory) jcbNewsStoryPartOfDay.setSelectedItem(((TVNewsStory) newsStory).getPartOfDay());
				//basic info
				jcbNewsStorySource.setSelectedItem(newsStory.getSource());
				jcbNewsStoryTopic.setSelectedItem(newsStory.getTopic());
				jcbNewsStorySubject.setSelectedItem(newsStory.getSubject());
				jftfNewsStoryLength.setText(Integer.toString(newsStory.getLength()));
				jcbNewsStoryNewsMaker1.setSelectedItem(newsStory.getNewsMaker1().getName());
				jcbNewsStoryNewsMaker2.setSelectedItem(newsStory.getNewsMaker2().getName());
				//date
				jcbNewsStoryYear.setSelectedItem(newsStory.getDate().getYear());
				jcbNewsStoryMonth.setSelectedItem(newsStory.getDate().getMonthValue());
				jcbNewsStoryDay.setSelectedItem(newsStory.getDate().getDayOfMonth());
			}
			

	}



}