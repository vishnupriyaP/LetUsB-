import java.awt.event.ActionEvent;
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
	private DefaultListModel<NewsStory> newsStories = new DefaultListModel<NewsStory>();

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
		this.newsStories = newsStoryListModel.getNewsStories();
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
		return newsStories.get(index);
		
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
		for(int i = 0; i < newsStories.size(); i++) {
			if(newsStories.get(i).equals(newsStory)) {
				newsStories.remove(i);
			}
		}
	}
	/**
	 * This method removes a list of news stories.
	 * @param newsStories The stories to remove.
	 */
	public void removeListOfNewsStories(DefaultListModel<NewsStory> newsStories) {
		
		//for all the news stories to remove
		for(int i = 0; i < newsStories.size();i++) {
			//if the list has that story
			if(this.newsStories.contains(newsStories.get(i))) {
				//loop through the list
				for(int j = 0; j < this.newsStories.size(); j++) {
					//if the story in the list is equal to the one we want to remove
					if(this.newsStories.get(j).equals(newsStories.getElementAt(i))) {
						//remove it
						this.newsStories.remove(j);
					}
					
				} //end for list
			}//end if contains
		}//end for stories to remove
		
	}
	/**
	 * This method sets the news story list.
	 * @param newsStoryListModel The model to set to.
	 */
	public void setNewsStories(NewsStoryListModel newsStoryListModel) {
		this.newsStories = newsStoryListModel.getNewsStories();
	}
	/**
	 * This method sets the news story list model from an array.
	 * @param newsStoryModelArray The array of news stories.
	 */
	public void setNewsStoriesFromArray(NewsStory[] newsStoryModelArray) {
		for(int i = 0; i < newsStoryModelArray.length;i++) {
			newsStories.addElement(newsStoryModelArray[i]);
		}
	}

	
	
	
	
	
}
