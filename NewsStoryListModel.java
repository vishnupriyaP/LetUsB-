import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListModel;

/**
 * Project 4, CS 2334, Section 010, April 23, 2017
 * <P>
 * A <code>NewsStoryListModel</code> is a list of <code>NewsStory</code> objects.
 * </P>
 * 
  * @author Dean Hougen, Jered Little, Vishnupriya Parasaram, Jessica Horner, and Zakary Koskovich 
 * @version 1.0
 *
 */
class NewsStoryListModel implements Serializable {
	/**
	 * This is the first serializable version of NewsStoryList, so we select a
	 * serialVersionUID of 1L.
	 */
	private static final long serialVersionUID = 1L;

	/** The list of newspaper stories. */
	private DefaultListModel<NewsStory> newsStories;

	/**
	 * <P>
	 * This constructor creates a news story list model.
	 * </P>
	 */
	public NewsStoryListModel() {
		
	}
	/**
	 * <P>
	 * This constructor sets the news story list model.
	 * </P>
	 * @param newsStoryListModel The news story list model to set.
	 */
	//TODO he didnt specify the parameter name.
	public NewsStoryListModel(NewsStoryListModel newsStoryListModel) {
		
	}
	/**
	 * <P>
	 * This method checks if the list is empty.
	 * </P>
	 * @return Whether or not the list is empty.
	 */
	public boolean isEmpty() {
		return newsStories.isEmpty();
		
	}
	/**
	 * <P>
	 * This method returns the size of the list.
	 * </P>
	 * @return The size of the list.
	 */
	public int size() {
		return newsStories.size();
		
	}
	/**
	 * <P>
	 * This method checks if the list contains a news story.
	 * </P>
	 * @param newsStory The news story to check for.
	 * @return Whether or not the list contains the news story.
	 */
	public boolean contains(NewsStory newsStory) {
		return newsStories.contains(newsStory);
		
	}
	/**
	 * This method returns the news story at the specified index.
	 * @param index The index to look at.
	 * @return The news story at that index.
	 */
	public NewsStory get(int index) {
		return newsStories.getElementAt(index);
		
	}
	/**
	 * <P>
	 * This method returns the list of news stories.
	 * </P>
	 * @return The list of news stories.
	 */
	public DefaultListModel<NewsStory> getNewsStories() {
		return newsStories;
		
	}
	/**
	 * <P>
	 * This method adds a news story to the list.
	 * </P>
	 * @param newsStory The news story to add.
	 */
	public void add(NewsStory newsStory) {
		newsStories.addElement(newsStory);
	}
	/**
	 * <P>
	 * This method removes a news story from the list.
	 * </P>
	 * @param newsStory The news story to remove.
	 */
	public void remove(NewsStory newsStory) {
		newsStories.removeElement(newsStory);
	}
	/**
	 * This method removes a list of news stories.
	 * @param newsStories The stories to remove.
	 */
	public void removeListOfNewsStories(DefaultListModel<NewsStory> newsStories) {
		newsStories.removeAllElements(); 
	}
	/**
	 * This method sets the news story list.
	 * @param newsStoryListModel The model to set to.
	 */
	public void setNewsStories(NewsStoryListModel newsStoryListModel) {
		newsStories.clear();
		for(int i = 0; i<newsStoryListModel.size(); i++) {
			newsStories.add(i,newsStoryListModel.get(i));
		}
	}
	/**
	 * This method sets the news story list model from an array.
	 * @param newsStoryModelArray The array of news stories.
	 */
	public void setNewsStoriesFromArray(NewsStory[] newsStoryModelArray) {
		newsStories.clear();
		for(int i = 0; i < newsStoryModelArray.length; i++) {
			newsStories.add(i,newsStoryModelArray[i]);
		}
	}
}
