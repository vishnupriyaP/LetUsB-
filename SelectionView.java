import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.border.TitledBorder;


/**
 * Project 4, CS 2334, Section 010, April 22, 2017
 * <P>
 * This class provides the view to add and/or edit a news story.
 * </P>
 * <P>
 * Note that the this class contains lists, panels, and menus for all the necessary components for a selection view.
 * This includes fields for the stories, newsmakers, load, save, etc. This class also
 * contains a reference to the model, which will be used to pull and display data from. 
 * </P>
 * 
 * @author Dean Hougen, Jered Little, Vishnupriya Parasaram, Jessica Horner, and Zakary Koskovich 
 * @version 1.0
 * 
 */


//Jered Little created the stub code & view display for this class.
public class SelectionView extends JFrame implements ActionListener{

	
	/** This variable holds the serial id of the selection view */
	private static final long serialVersionUID = 2960890098313533055L;
	/** This variable contains a reference to the model */
	private NewsDataBaseModel newsDataBaseModel;
	/** This variable contains the menu bar for all the menus. */
	private JMenuBar jmb;
	/** This variable contains the file menu. */
	private JMenu jmFile;
	/** This variable contains the load menu button. */
	private JMenuItem jmiLoad;
	/** This variable contains the save menu button. */
	private JMenuItem jmiSave;
	/** This variable contains the import menu button. */
	private JMenuItem jmiImport;
	/** This variable contains the export menu button. */
	private JMenuItem jmiExport;
	/** This variable contains the news maker menu. */
	private JMenu jmNewsMaker;
	/** This variable contains the add news maker menu button. */
	private JMenuItem jmiAddNewsMaker;
	/** This variable contains the edit news maker menu button. */
	private JMenuItem jmiEditNewsMaker;
	/** This variable contains the delete news maker menu button. */
	private JMenuItem jmiDeleteNewsMaker;
	/** This variable contains the delete news maker list menu button. */
	private JMenuItem jmiDeleteNewsMakerList;
	/** This variable contains the news story menu. */
	private JMenu jmNewsStory;
	/** This variable contains the add news story menu button. */
	private JMenuItem jmiAddNewsStory;
	/** This variable contains the edit news story button. */
	private JMenuItem jmiEditNewsStory;
	/** This variable contains the sort news story button. */
	private JMenuItem jmiSortNewsStories;
	/** This variable contains the delete news story button. */
	private JMenuItem jmiDeleteNewsStory;
	/** This variable contains the delete all news story button. */
	private JMenuItem jmiDeleteAllNewsStories;
	/** This variable contains the display menu. */
	private JMenu jmDisplay;
	/** This variable contains the pie chart menu button. */
	private JMenuItem jmiPieChart;
	/** This variable contains the text menu button. */
	private JMenuItem jmiText;
	/** This variable contains the list of news makers to display. */
	private JList<NewsMakerModel> jlNewsMakerList;
	/** This variable contains the scroll pane for the list of news makers. */
	private JScrollPane jspNewsMakerList;
	/** This variable contains the panel for the news maker list. */
	private JPanel jpNewsMakerList;
	/** This variable contains the list of news stories to display. */
	private JList<NewsStory> jlNewsStoryList;
	/** This variable contains the scroll pane for the list of news stories. */
	private JScrollPane jspNewsStoryList;
	/** This variable contains the panel for the news story list. */
	private JPanel jpNewsStoryList; 
	/** This variable contains a split pane for the two lists. */
	private JSplitPane splitPane;
	
	
	
	/**
	 * <P>
	 * This constructor initializes all the variables for the selection view makes it visible.
	 * </P>
	 */
	public SelectionView() {
		
		
		newsDataBaseModel = new NewsDataBaseModel();


			
		
		//Menu bar
		jmb = new JMenuBar();
		//file menu
		jmFile = new JMenu("File");
		jmiLoad = new JMenuItem("Load");
		jmiSave = new JMenuItem("Save");
		jmiSave.setEnabled(false);
		jmiImport = new JMenuItem("Import");
		jmiExport = new JMenuItem("Export");
		jmiExport.setEnabled(false);
		jmFile.add(jmiLoad);
		jmFile.add(jmiSave);
		jmFile.add(jmiImport);
		jmFile.add(jmiExport);
		jmb.add(jmFile);
		//newsmakers menu
		jmNewsMaker = new JMenu("Newsmakers");
		jmiAddNewsMaker = new JMenuItem("Add Newsmaker");
		jmiEditNewsMaker = new JMenuItem("Edit Newsmaker");
		jmiDeleteNewsMaker = new JMenuItem("Delete Newsmaker");
		jmiDeleteNewsMakerList = new JMenuItem("Delete Newsmaker List");
		jmNewsMaker.add(jmiAddNewsMaker);
		jmNewsMaker.add(jmiEditNewsMaker);
		jmNewsMaker.add(jmiDeleteNewsMaker);
		jmNewsMaker.add(jmiDeleteNewsMakerList);
		jmb.add(jmNewsMaker);
		//newsstories menu
		jmNewsStory = new JMenu("News Stories");
		jmiAddNewsStory = new JMenuItem("Add News Story");
		jmiEditNewsStory = new JMenuItem("Edit News Story");
		jmiSortNewsStories = new JMenuItem("Sort News Stories");
		jmiDeleteNewsStory = new JMenuItem("Delete News Story");
		jmiDeleteAllNewsStories= new JMenuItem("Delete All News Stories");
		jmNewsStory.add(jmiAddNewsStory);
		jmNewsStory.add(jmiEditNewsStory);
		jmNewsStory.add(jmiSortNewsStories);
		jmNewsStory.add(jmiDeleteNewsStory);
		jmNewsStory.add(jmiDeleteAllNewsStories);
		jmb.add(jmNewsStory);
		//Display menu
		jmDisplay = new JMenu("Display");
		jmiPieChart = new JMenuItem("Pie Chart");
		jmiText = new JMenuItem("Text");
		jmDisplay.add(jmiPieChart);
		jmDisplay.add(jmiText);
		jmb.add(jmDisplay);
		
		//newsmakers list
		//TODO change this variable after uncommenting field.
		jlNewsMakerList = new JList<NewsMakerModel>(newsDataBaseModel.getNewsMakers());
		jspNewsMakerList = new JScrollPane(jlNewsMakerList);
		jpNewsMakerList = new JPanel(new BorderLayout());
		jpNewsMakerList.setBorder((BorderFactory.createTitledBorder (BorderFactory.createEtchedBorder (),"News Makers",
        TitledBorder.LEFT,TitledBorder.TOP))); //title
		jpNewsMakerList.add(jspNewsMakerList);
		
		//newsstory list
		jlNewsStoryList = new JList<NewsStory>(newsDataBaseModel.getNewsStories());
		jspNewsStoryList = new JScrollPane(jlNewsStoryList);
		jpNewsStoryList = new JPanel(new BorderLayout());
		jpNewsStoryList.setBorder((BorderFactory.createTitledBorder (BorderFactory.createEtchedBorder (),"News Stories",
        TitledBorder.LEFT,TitledBorder.TOP)));
		jpNewsStoryList.add(jspNewsStoryList);
		
		//split pane for the two lists
		splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
                jpNewsMakerList, jpNewsStoryList);
		splitPane.setResizeWeight(0.3);
		

		//enable status
		jmiEditNewsMaker.setEnabled(false);
		jmiDeleteNewsMaker.setEnabled(false);
		jmiDeleteNewsMakerList.setEnabled(false);
		jmiSave.setEnabled(false);
		jmiExport.setEnabled(false);
		jmiEditNewsStory.setEnabled(false);
		jmiSortNewsStories.setEnabled(false);
		jmiDeleteNewsStory.setEnabled(false);
		jmiDeleteAllNewsStories.setEnabled(false);
		jmiText.setEnabled(false);
		jmiPieChart.setEnabled(false);
		
		//size and adding menu bar and split pane.
		this.setLayout(new BorderLayout());
		this.add(jmb, BorderLayout.NORTH);
		this.add(splitPane,BorderLayout.CENTER);
		this.setTitle("Selection View");
		this.setSize(700,500);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		
		
	}
	/**
	 * <P>
	 * This method registers the listener for the file menu.
	 * </P>
	 * @param fileMenuListener The listener to register to the file menu.
	 */
	public void registerFileMenuListener(ActionListener fileMenuListener) {
		jmiImport.addActionListener(fileMenuListener);
		jmiExport.addActionListener(fileMenuListener);
		jmiSave.addActionListener(fileMenuListener);
		jmiLoad.addActionListener(fileMenuListener);
	}
	/**
	 * <P>
	 * This method registers the listener for the news maker menu.
	 * </P>
	 * @param newsMakerMenuListener The listener to register to the news maker menu.
	 */
	public void registerNewsMakerMenuListener(ActionListener newsMakerMenuListener) {
		jmiAddNewsMaker.addActionListener(newsMakerMenuListener);
		jmiEditNewsMaker.addActionListener(newsMakerMenuListener);
		jmiDeleteNewsMaker.addActionListener(newsMakerMenuListener);
		jmiDeleteNewsMakerList.addActionListener(newsMakerMenuListener);
	}
	/**
	 * 
	 * This method registers the listener for the news story menu.
	 * 
	 * @param newsStoryMenuListener The listener to register to the news story menu.
	 */
	public void registerNewsStoryMenuListener(ActionListener newsStoryMenuListener) {
		jmiAddNewsStory.addActionListener(newsStoryMenuListener);
		jmiEditNewsStory.addActionListener(newsStoryMenuListener);
		jmiSortNewsStories.addActionListener(newsStoryMenuListener);
		jmiDeleteNewsStory.addActionListener(newsStoryMenuListener);
		jmiDeleteAllNewsStories.addActionListener(newsStoryMenuListener);
	}
	/**
	 * 
	 * This method registers the listener for the display menu.
	 * 
	 * @param displayMenuListener The listener to register to the display menu.
	 */
	public void registerDisplayMenuListener(ActionListener displayMenuListener) {
		jmiText.addActionListener(displayMenuListener);
		jmiPieChart.addActionListener(displayMenuListener);
	}
	/**
	 * <P>
	 * This method sets the model for the view to pull data from.
	 * </P>
	 * @param newsDataBaseModel The reference to the model.
	 */
	public void setNewsDataBaseModel(NewsDataBaseModel newsDataBaseModel) {
		this.newsDataBaseModel = newsDataBaseModel;
		this.newsDataBaseModel.addActionListener(this);
	}
	/**
	 * <P>
	 * This method handles when an action is performed.
	 * </P>
	 * @param actionEvent The event to process.
	 */
	public void actionPerformed(ActionEvent actionEvent) {
		
		//update the news maker model list
		if(newsDataBaseModel.getNewsMakerListModel().isEmpty() && newsDataBaseModel.getNewsStoryListModel().isEmpty()) {
			jmiSave.setEnabled(false);
			jmiExport.setEnabled(false);
			
		}
		else {
			jmiSave.setEnabled(true);
			jmiExport.setEnabled(true);
		}
		if(newsDataBaseModel.getNewsStoryListModel().isEmpty()) {
			jmiEditNewsStory.setEnabled(false);
			jmiSortNewsStories.setEnabled(false);
			jmiDeleteNewsStory.setEnabled(false);
			jmiDeleteAllNewsStories.setEnabled(false);
			jmiText.setEnabled(false);
			jmiPieChart.setEnabled(false);
		}
		else {
			jmiEditNewsStory.setEnabled(true);
			jmiSortNewsStories.setEnabled(true);
			jmiDeleteNewsStory.setEnabled(true);
			jmiDeleteAllNewsStories.setEnabled(true);
			jmiText.setEnabled(true);
			jmiPieChart.setEnabled(true);
		}
		if(newsDataBaseModel.getNewsMakerListModel().isEmpty()) {
			jmiEditNewsMaker.setEnabled(false);
			jmiDeleteNewsMaker.setEnabled(false);
			jmiDeleteNewsMakerList.setEnabled(false);
		}
		else {
			jmiEditNewsMaker.setEnabled(true);
			jmiDeleteNewsMaker.setEnabled(true);
			jmiDeleteNewsMakerList.setEnabled(true);
		}
		
		NewsMakerModel[] listData = new NewsMakerModel[newsDataBaseModel.getNewsMakerListModel().size()];
		for(int i = 0; i < listData.length; i++) {
			listData[i] = newsDataBaseModel.getNewsMakerListModel().get(i);
		}
		this.jlNewsMakerList.setListData(listData);
		
		//update the news story list
		NewsStory[] listData2 = new NewsStory[newsDataBaseModel.getNewsStoryListModel().size()];
		for(int i = 0; i < listData2.length; i++) {
			listData2[i] = newsDataBaseModel.getNewsStoryListModel().get(i);
		}
		this.jlNewsStoryList.setListData(listData2);
		
	}
	/**
	 * <P>
	 * This method returns the number of selected newsmakers.
	 * </P>
	 * @return The number of selected newsmakers. //TODO ???
	 */
	public int[] getSelectedNewsMakers() {
		return 	this.jlNewsMakerList.getSelectedIndices();
	}
	/**
	 * <P>
	 * This method returns the number of selected news stories.
	 * </P>
	 * @return The number of selected news stories. //TODO ???
	 */
	public int[] getSelectedNewsStories() {
		return 	this.jlNewsStoryList.getSelectedIndices();
	}


	
	
}
