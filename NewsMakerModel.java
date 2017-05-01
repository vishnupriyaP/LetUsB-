import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * Project 4, CS 2334, Section 010, April 30, 2017
 * <P>
 * A <code>NewsMaker</code> is the subject of a <code>NewsStory</code>. A
 * <code>NewsMaker</code> may be a person or an organization. A
 * <code>NewsMaker</code> consists of a name and a collection of news
 * stories that feature that <code>NewsMaker</code>. There is a special
 * <code>NewsMaker</code> with the name "None" that is used for news
 * stories that don't have at least two named <code>NewsMakers</code>.
 * </P>
 * 
 * @author Dean Hougen
 * @version 3.0
 */
public class NewsMakerModel implements Comparable<NewsMakerModel>, Serializable, ActionListener {
	/**
	 * This is the first serializable version of NewsMaker, so we select a
	 * serialVersionUID of 1L.
	 */
	private static final long serialVersionUID = 1L;

	/** The name of the news maker. */
	private String name;

	/** The list of news stories in which the news maker is featured */
	private NewsStoryListModel newsStoryListModel = new NewsStoryListModel();

	/** The array list of action listeners. */
	private ArrayList<ActionListener> actionListenerList;
	
	/**
	 * The no-argument constructor for the class. Because we will often want to
	 * construct the special news maker "None," the no-arg constructor gives us
	 * this news maker.
	 */
	NewsMakerModel() {
		this.name = "None";
	}

	/**
	 * The general constructor for the class which takes the name of the news
	 * maker (generally the only thing we know about a news maker when the
	 * constructor is called) as an argument.
	 * 
	 * @param name
	 *            The name of the news maker.
	 */
	public NewsMakerModel(String name) {
		this.name = name;
	}

	/**
	 * The accessor for the name field.
	 * <P>
	 * Note that <code>String</code> objects are immutable, so it is fine to
	 * return the field itself.
	 * </P>
	 * 
	 * @return The name of the news maker.
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * The accessor for the list of news stories.
	 * <P>
	 * Note that <code>NewsStoryList</code> objects are mutable, so this
	 * really should return a copy of the list instead. However, we haven't
	 * studied that yet, so returning the list itself is acceptable for now.
	 * </P>
	 * 
	 * @return The list of stories featuring the news maker.
	 */
	public NewsStoryListModel getNewsStoryListModel() {
		// TODO Have it return a copy instead (Eventually)
		NewsStoryListModel model = new NewsStoryListModel();
		model = this.newsStoryListModel;
		return model;
	}

	/**
	 * The mutator that adds a news story to a news maker's list of
	 * stories.
	 * <P>
	 * Note that since this list should contain only stories in which the news
	 * maker is featured, we should have this method verify that the
	 * <code>NewsMaker</code> object is referenced in the
	 * <code>NewsStory</code> object before the story is added to the list.
	 * However, to keep the project relatively simple, this requirement was not
	 * made in the project description and this check doesn't need to be made
	 * yet.
	 * </P>
	 * 
	 * @param newsStory
	 *            The news story to add.
	 */
	public void addNewsStory(NewsStory newsStory) {
		// TODO Verify that story is about this NewsMaker (Eventually)
		this.newsStoryListModel.add(newsStory);
		processEvent(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "added news story to news maker"));
	}
	
	/**
	 * An overridden <code>equals</code> method.
	 * <P>
	 * A <code>NewsMaker</code> should be equal to another object if that object
	 * is also a <code>NewsMaker</code> object and they have the same name.
	 * (Since <code>equals</code> is a method in the <code>Object</code> class
	 * that we are overriding, the parameter needs to be an
	 * <code>Object</code>.)
	 * </P>
	 * 
	 * @param o
	 *            The Object to which to compare this.
	 */
	@Override
	public boolean equals(Object o) {
		if (o instanceof NewsMakerModel) {
			NewsMakerModel newsMaker = (NewsMakerModel) o;
			return this.name.equals(newsMaker.getName());
		}
		return false;
	}

	/**
	 * The required <code>compareTo</code> method for implementing
	 * <code>Comparable</code>. Looks at name only.
	 * 
	 * @param newsMaker
	 *            The other news maker to which to compare this.
	 */
	@Override
	//TODO he didnt change this in UML, but I was required to at least change
	//the data type.
	public int compareTo(NewsMakerModel newsMaker) {
		return this.name.compareTo(newsMaker.name);
	}
	/**
	 * <P>
	 * This method sets the name of a news maker.
	 * </P>
	 * @param name The name to set.
	 */
	public void setName(String name) {
		
		this.name = name;
		processEvent(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "set news maker name"));
	}
	/**
	 * <P>
	 * This method sets the news story list model.
	 * </P>
	 * @param newsStoryListModel The model to set to.
	 */
	public void setNewsStoryListModel(NewsStoryListModel newsStoryListModel) {
		this.newsStoryListModel = newsStoryListModel;
		processEvent(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "set news story list for news maker"));
	}
	/**
	 * <P>
	 * This method removes a news story from the news maker.
	 * </P>
	 * @param newsStory The news story to remove.
	 */
	public void removeNewsStory(NewsStory newsStory) {
		for(int i = 0; i < newsStoryListModel.size(); i++) {
			if(newsStoryListModel.get(i).equals(newsStory)) {
				newsStoryListModel.remove(newsStory);
			}
		}
		processEvent(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "removed news story from news maker"));
	}
	/**
	 * <P>
	 * This method overrides the to string method for a news maker.
	 * </P>
	 */
	@Override
	public String toString() {
		return this.getName();
		
	}
	/**
	 * <P>
	 * This method adds an action listener to the list.
	 * </P>
	 * @param l The listener to add.
	 */
	public void addActionListener(ActionListener l) {
		if (actionListenerList == null) {
			actionListenerList = new ArrayList<ActionListener>();
		}
		actionListenerList.add(l);
	}
	/**
	 * <P>
	 * This method removes an action listener from the list.
	 * @param l The listener to remove.
	 */
	public void removeActionListner(ActionListener l) {
		actionListenerList.remove(l);
	}
	/**
	 * <P>
	 * This method processes an action event when it occurs.
	 * </P>
	 * @param e The event to process.
	 */
	private void processEvent(ActionEvent e) {
		ArrayList<ActionListener> list;

		synchronized (this) {
			if (actionListenerList == null)
				return;
			list = (ArrayList<ActionListener>) actionListenerList.clone();
		}
		for (int i = 0; i < list.size(); i++) {
			System.out.println("Action performed... Informing Listeners...");
			ActionListener listener = list.get(i);
			listener.actionPerformed(e);
		}
	}

	/**
	 * <P>
	 * This method decides what to do when an action is performed.
	 * </P>
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		
	}
	
	
}
