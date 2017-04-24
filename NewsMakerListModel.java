import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.DefaultListModel;

/**
 * Project 4, CS 2334, Section 010, April 23, 2017
 * <P>
 * A <code>NewsMakerListModel</code> is a list of <code>NewsMaker</code> objects.
 * Each <code>NewsMaker</code> in the list must have a unique name.
 * </P>
 * <P>
 * Note that this class now keeps track of whether its list has been sorted, to
 * ensure that a binary search will work, if used. Also, it has additional functionality as a model.
 * </P>
 * 
 * @author Dean Hougen, Jered Little, Vishnupriya Parasaram, Jessica Horner, and Zakary Koskovich 
 * @version 1.0
 *
 */
//NOTE: Jered Little created the stub code for this class.
class NewsMakerListModel implements Serializable, ActionListener {
	/**
	 * This is the first serializable version of NewsMakerList, so we select a
	 * serialVersionUID of 1L.
	 */
	private static final long serialVersionUID = 1L;

	/** The list of news makers. */
	private DefaultListModel<NewsMakerModel> newsMakerDefaultListModel; 

	/**
	 * The no-argument constructor initializes the list to be an empty
	 * list of <code>NewsMakerModel</code> objects.
	 */
	NewsMakerListModel() {

		
	}
	
	/**
	 * <P>
	 * This constructor sets the news maker list model.
	 * </P>
	 * @param newsMakerListModel The model to set the field to.
	 */
	public NewsMakerListModel(NewsMakerListModel newsMakerListModel) {
		
	}
	
	/**
	 * <P>
	 * This method returns whether or not the news maker list
	 * model is empty.
	 * </P>
	 * @return Whether or not the news maker list model is empty.
	 */
	public boolean isEmpty() {
		return false;
		
	}
	
	/**
	 * <P>
	 * This method returns the size of the news maker list model.
	 * </P>
	 * @return The size of the news maker list model.
	 */
	public int size() {
		return 0;
		
	}
	/**
	 * <P>
	 * This method checks if the news maker is contained in the list.
	 * </P>
	 * @param newsMakerModel The news maker to look for.
	 * @return Whether or not the news maker is in the list.
	 */
	public boolean contains(NewsMakerModel newsMakerModel) {
		return false;
		
	}
	/**
	 * <P>
	 * This method gets the news maker based on the parameter.
	 * </P>
	 * @param newsMakerModel The news maker to get.
	 * @return The news maker from the list.
	 */
	public NewsMakerModel get(NewsMakerModel newsMakerModel) //TODO didnt specify this variable name) 
	{
		return newsMakerModel;
		
	}
	/**
	 * <P>
	 * This method returns the list of news makers.
	 * </P>
	 * @return The list of news makers.
	 */
	public DefaultListModel<NewsMakerModel> getNewsMakers() {
		return newsMakerDefaultListModel;
		
	}
	/**
	 * <P>
	 * This method gets the news maker based on index.
	 * </P>
	 * @param index The index to look at.
	 * @return The news maker at that index.
	 */
	public NewsMakerModel get(int index) //TODO he didnt specify this variable name
	{
		return null;
		
	}
	/**
	 * <P>
	 * This method returns the names of the news makers as an array of strings.
	 * </P>
	 * @return The news makers names.
	 */
	public String[] getNewsMakerNames() {
		return null;
	
	}
	/**
	 * <P>
	 * This method replaces the news maker specified.
	 * </P>	
	 * @param newsMakerModel The news maker to replace.
	 */
	public void replace(NewsMakerModel newsMakerModel) {
		
	}
	/**
	 * <P>
	 * This method removes the news maker specified.
	 * </P>
	 * @param newsMakerModel The newsmaker to remove.
	 */
	public void remove(NewsMakerModel newsMakerModel) {
		
	}
	/**
	 * <P>
	 * This method removes the list of news makers.
	 * </P>
	 * @param newsMakers The news makers to remove.
	 */
	public void removeListOfNewsMakers(DefaultListModel<NewsMakerModel> newsMakers) {
		
	}
	/**
	 * <P>
	 * This method removes all newsmakers.
	 * </P>
	 */
	public void removeAllNewsMakers() {
		
	}
	/**
	 * <P>
	 * This method sets the news makers from a news maker list model.
	 * </P>
	 * @param newsMakerListModel The news maker list model to set.
	 */
	public void setNewsMakersFromNewsMakerList(NewsMakerListModel newsMakerListModel) //TODO he didnt specify this variable name
	{		

	}

	/**
	 * The mutator for adding news makers to the list.
	 * <P>
	 * By using our own class with its own <code>add</code> method, rather than
	 * directly using the <code>add</code> method of <code>ArrayList</code>, we
	 * can ensure that we don't add multiple <code>NewsMaker</code> objects with
	 * the same name to our list (thereby keeping the names unique).
	 * </P>
	 * 
	 * @param newsMakerModel
	 *            The news maker to add.
	 * @throws IllegalArgumentException
	 *             If the news maker to add is already in the list.
	 */
	public void add(NewsMakerModel newsMakerModel) {

	}


	/**
	 * This method should be able to use a binary search to find the news maker
	 * but relies on the list being sorted first. It therefore checks the
	 * <code>sorted</code> flag and prints an error to the standard error if it
	 * was called with an unsorted list. It will conduct a linear search if a
	 * binary search is not possible.
	 * 
	 * @param newsMakerName
	 *            The exact name for which to search.
	 * @return The news maker found or null if none found.
	 */
	public NewsMakerModel getExactMatch(String newsMakerName) {

		//TODO FIX
		/*
			int index = Collections.binarySearch(newsMakerDefaultListModel, new NewsMakerModel(newsMakerName));
			if (index >= 0) {
				// TODO Have it return a copy instead (Eventually)
				return this.newsMakerDefaultListModel.get(index);
			} else {
				return null;
			}
			*/
		System.err.println("Attempted to conduct binary search on unsorted list.");
		//TODO FIX
		/*
		for (NewsMakerModel newsMaker : newsMakerDefaultListModel) {
			if (newsMaker.getName().equals(newsMakerName)) {
				return newsMaker;
			}
		}
		*/
		return null;
	}

	/**
	 * This method searches for partial matches in the list, and returns the
	 * first news maker that contains the search string specified.
	 * 
	 * @param newsMakerName
	 *            The string on which to search.
	 * @return The news maker found or null if none found.
	 */
	public NewsMakerModel getPartialMatch(String newsMakerName) {
		//TODO FIX
		/*
		for (NewsMakerModel newsMaker : newsMakerDefaultListModel) {
			if (newsMaker.getName().contains(newsMakerName)) {
				return newsMaker;
			}
		}
		*/
		return null;
	}

	/**
	 * This method sorts the list using a stable sort. It also sets the
	 * <code>sorted</code> flag to <code>true</code>.
	 */
	public void sort() {
		//TODO fix
		//Collections.sort(newsMakerDefaultListModel);
	}

	/**
	 * <P>
	 * This method handles when an action is performed.
	 * </P>
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
