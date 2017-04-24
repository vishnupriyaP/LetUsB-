import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.swing.DefaultListModel;



/**
 * Project 4, CS 2334, Section 010, April 23, 2017
 * <P>
 * This class provides the functionality of removing, adding, modifying and changing various models and maps
 * for news makers and news stories. Also, it contains a news maker/story list model and an action listener list.
 * </P>
 * <P>
 * Note that the this class contains: fields for stories, newsmakers, 
 * action listeners, and maps for sources/topics/subjects.
 * </P>
 * 
 * @author Dean Hougen, Jered Little, Vishnupriya Parasaram, Jessica Horner, and Zakary Koskovich 
 * @version 1.0
 * 
 */
//NOTE: Jered Little created the stub code for this class.
public class NewsDataBaseModel implements Serializable {

	/**
	 * The serial id for the news data base model.
	 */
	private static final long serialVersionUID = -2277109613135567096L;
	/** The array list of action listeners for the data base. */
	private ArrayList<ActionListener> actionListenerList;
	/** The map of sources for the database. */
	private Map<String,String> newsSourceMap;
	/** The map of topics for the database. */
	private Map<String, String> newsTopicMap;
	/** The map of subjects for the database. */
	private Map<String,String> newsSubjectMap;
	/** A reference to the news maker model. */
	NewsMakerModel none; //why called none? idk
	/** A reference to the news maker list model. */
	private NewsMakerListModel newsMakerListModel;
	/** A reference to the news story list model. */
	private NewsStoryListModel newsStoryListModel;
	
	/**
	 * <P>
	 * This constructor for the data base contains no parameters, and just creates a new
	 * object of this type. Later, the variables can be assigned values.
	 * </P>
	 * 
	 */
	public NewsDataBaseModel() {
		newsSourceMap = new LinkedHashMap<String,String>();
		newsTopicMap = new LinkedHashMap<String,String>();
		newsSubjectMap = new LinkedHashMap<String,String>();
	}
	
	/**
	 * <P>
	 * This constructor initializes the newsmaker list model 
	 * and the news story list model.
	 * </P>
	 * @param newsMakerListModel - The news maker list model to initialize.
	 * @param newsStoryListModel - the news story list model to initialize.
	 * 
	 */
	public NewsDataBaseModel(NewsMakerListModel newsMakerListModel,
	 NewsStoryListModel newsStoryListModel) {
		newsSourceMap = new LinkedHashMap<String,String>();
		newsTopicMap = new LinkedHashMap<String,String>();
		newsSubjectMap = new LinkedHashMap<String,String>();
		this.newsMakerListModel = newsMakerListModel;
		this.newsStoryListModel = newsStoryListModel;
		
	}
	/**
	 * <P>
	 * This method returns the source map.
	 * </P>
	 * @return The source map.
	 */
	public Map<String,String> getNewsSourceMap() {
		return newsSourceMap;
		
	}
	/**
	 * <P>
	 * This method returns the news sources as an array of strings, instead of a map.
	 * </P>
	 * @return The news sources as an array of strings.
	 */
	public String[] getNewsSources() {
		return null;
		
	}
	/**
	 * <P>
	 * This methods sets the source map based on the parameter.
	 * </P>
	 * @param newsSourceMap The map to set to the news source map.
	 * 
	 */
	public void setNewsSourceMap(Map<String, String> newsSourceMap ) {
		this.newsSourceMap = newsSourceMap;
	}
	/**
	 * <P>
	 * This method returns the topic map.
	 * </P>
	 * @return The topic map.
	 */
	public Map<String,String> getNewsTopicMap() {
		return newsTopicMap;
		
	}
	/**
	 * <P>
	 * This method returns the news topics as an array of strings, instead of a map.
	 * </P>
	 * @return The news topics as an array of strings.
	 */
	public String[] getNewsTopics() {
		return null;
		
	}
	/**
	 * <P>
	 * This methods sets the topic map based on the parameter.
	 * </P>
	 * @param newsTopicMap The map to set to the news topic map.
	 * 
	 */
	public void setNewsTopicMap(Map<String, String> newsTopicMap) {
		this.newsTopicMap = newsTopicMap;
	}
	/**
	 * <P>
	 * This method returns the subject map.
	 * </P>
	 * @return The subject map.
	 */
	public Map<String,String> getNewsSubjectMap() {
		return newsSubjectMap;
		
	}
	/**
	 * <P>
	 * This method returns the news subjects as an array of strings, instead of a map.
	 * </P>
	 * @return The news subjects as an array of strings.
	 */
	public String[] getNewsSubjects() {
		return null;
		
	}
	/**
	 * <P>
	 * This methods sets the subject map based on the parameter.
	 * </P>
	 * @param newsSubjectMap The map to set to the news subject map.
	 * 
	 */
	public void setNewsSubjectMap(Map<String, String> newsSubjectMap) {
		this.newsSubjectMap = newsSubjectMap;
	}
	/**
	 * <P>
	 * This method checks if the news maker list is empty,
	 * and returns true if it is.
	 * </P>
	 * @return Whether or not the newsmaker list is empty.
	 */
	public boolean newsMakerListIsEmpty() {
		return false;
		
	}
	/**
	 * <P>
	 * This method checks if the database contains the news maker model parameter.
	 * </P>
	 * @param newsMakerModel The model to check for.
	 * @return Whether or not the model is in the database.
	 */
	public boolean containsNewsMakerModel(NewsMakerModel newsMakerModel) {
		return false;
		
	}
	/**
	 * <P>
	 * This method returns the news maker list model.
	 * </P>
	 * @return The news maker list model.
	 */
	public NewsMakerListModel getNewsMakerListModel() {
		return newsMakerListModel;
		
	}
	/**
	 * <P>
	 * This method gets the news maker names as an array of strings.
	 * </P>
	 * @return The news makers names in the database.
	 */
	public String[] getNewsMakerNames() {
		return null;
		
	}
	/**
	 * <P>
	 * This method returns the newsmakers as a defaultlistmodel.
	 * </P>
	 * @return The newsmakers as a defaultlistmodel.
	 */
	public DefaultListModel<NewsMakerModel> getNewsMakers() {
		return newsMakerListModel.getNewsMakers();
		
	}
	/**
	 * <P>
	 * This method sets the news maker list model.
	 * </P>
	 * @param newsMakerListModel The model to set.
	 */
	public void setNewsMakerListModel(NewsMakerListModel newsMakerListModel) {
		this.newsMakerListModel = newsMakerListModel;
	}
	/**
	 * <P>
	 * This method adds a newsmaker model.
	 * </P>
	 * @param newsMakerModel The news maker model to add.
	 */
	public void addNewsMakerModel(NewsMakerModel newsMakerModel) {
		this.newsMakerListModel.add(newsMakerModel);
	}
	/**
	 * <P>
	 * This method replaces a news maker model.
	 * </P>
	 * @param newsMakerModel The newsmaker model to replace.
	 */
	public void replaceNewsMakerModel(NewsMakerModel newsMakerModel) {
		this.newsMakerListModel.replace(newsMakerModel);
	}
	/**
	 * <P>
	 * This method removes the newsmakers from the default list model of newsmakers.
	 * </P>
	 * 
	 * @param newsMakers The news makers to remove.
	 */
	public void removeNewsMakers(DefaultListModel<NewsMakerModel> newsMakers) {
		for(int i = 0; i < newsMakers.size(); i++) {
			this.newsMakerListModel.remove(newsMakers.get(i));
		}
	}
	/**
	 * <P>
	 * This method removes all news makers from the database.
	 * </P>
	 */
	public void removeAllNewsMakers() {
		newsMakerListModel.removeAllNewsMakers();
	}
	/**
	 * 
	 * <P>
	 * This method sorts all the news makers.
	 * </P>
	 * 
	 */
	public void sortNewsMakerListModel() {
		//TODO
	}
	/**
	 * This method checks if the news story list is empty,
	 * and returns true if it is.
	 * </P>
	 * 
	 * @return Whether or not the news story list is empty.
	 */
	public boolean newsStoryListIsEmpty() {
		//TODO
		return false;
		
	}
	/**
	 * <P>
	 * This method checks if the database contains this 
	 * newstory parameter.
	 * </P>
	 * @param newsStory The news story to look for.
	 * @return Whether or not the data base contains that news story.
	 */
	public boolean containsNewsStory(NewsStory newsStory) {
		return false;
		
	}
	/**
	 * <P>
	 * This returns the news story list model.
	 * </P>
	 * @return The news story list model.
	 */
	public NewsStoryListModel getNewsStoryListModel() {
		return null;
		
	}
	/**
	 * <P>
	 * This method gets the news stories as a defaultlistmodel.
	 * </P>
	 * @return The news stories as a defaultlistmodel.
	 */
	public DefaultListModel<NewsStory> getNewsStories() {
		return null;
		
	}
	/**
	 * <P>
	 * This method sets the news story list model.
	 * </P>
	 * @param newsStoryListModel The model to set the field to.
	 */
	public void setNewsStoryListModel(NewsStoryListModel newsStoryListModel) {
		
	}
	/**
	 * <P>
	 * This method sets the news story list model from an array of news stories.
	 * </P>
	 * @param newsStoryModelArray The array of news stories.
	 */
	public void setNewsStoryListModelFromArray(NewsStory[] newsStoryModelArray) {
		
	}
	/**
	 * <P>
	 * This method adds a news story to the database.
	 * </P>
	 * @param newsStory The news story to add.
	 */
	public void addNewsStory(NewsStory newsStory) {
		
	}
	/**
	 * <P>
	 * This method removes the news stories from the default list model
	 * of news stories.
	 * </P>
	 * @param newsStories The news stories to remove.
	 */
	public void removeNewsStories(DefaultListModel<NewsStory> newsStories) {
		
	}
	/**
	 * <P>
	 * This method removes all news stories from the database.
	 * </P>
	 */
	public void removeAllNewsStories() {
		
	}
	/**
	 * <P>
	 * This method adds an action listener to the array of action
	 * listeners.
	 * </P>
	 * @param l The listener to add.
	 */
	public void addActionListener(ActionListener l) {
		
	}
	/**
	 * <P>
	 * This method removes an action listener from the list.
	 * </P>
	 * @param l The action listener to remove.
	 */
	public void removeActionListener(ActionListener l) {
		
	}
	/**
	 * <P>
	 * This method processes an action event.
	 * </P>
	 * @param e The event to process.
	 */
	private void processEvent(ActionEvent e) {
		
	}

	
	
	
	
	
	
	
}
