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
//NOTE: Jered Little created the stub code for this class. Jessica Horner modified the methods in this class.
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
		// TODO FIXME
	}
	
	/**
	 * <P>
	 * This method returns whether or not the news maker list
	 * model is empty.
	 * </P>
	 * @return Whether or not the news maker list model is empty.
	 */
	public boolean isEmpty() {
		boolean value = newsMakerDefaultListModel.isEmpty();
		return value;
		
	}
	
	/**
	 * <P>
	 * This method returns the size of the news maker list model.
	 * </P>
	 * @return The size of the news maker list model.
	 */
	public int size() {
		return newsMakerDefaultListModel.size();
		
	}
	/**
	 * <P>
	 * This method checks if the news maker is contained in the list.
	 * </P>
	 * @param newsMakerModel The news maker to look for.
	 * @return Whether or not the news maker is in the list.
	 */
	public boolean contains(NewsMakerModel newsMakerModel) {
		return newsMakerDefaultListModel.contains(newsMakerModel);
		
	}
	/**
	 * <P>
	 * This method gets the news maker based on the parameter.
	 * </P>
	 * @param newsMakerModel The news maker to get.
	 * @return The news maker from the list.
	 */
	public NewsMakerModel get(NewsMakerModel newsMakerModel) //TODO didn't specify this variable name) 
	{
		// TODO do we need to make sure that the list contains the news maker first?
		// if (contains(newsMakerModel) == true) {
		
		for(int i = 0; i < newsMakerDefaultListModel.size(); i++) {
			if(newsMakerDefaultListModel.getElementAt(i).getName().equals(newsMakerModel.getName())) {
				return newsMakerDefaultListModel.getElementAt(i);
			}
		}
			System.err.println("Search Result: Newsmaker not found");
			return null;
		// }
		// else {
			// System.err.print("That news maker does not exist.");
		// }
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
	public NewsMakerModel get(int index) //TODO he didn't specify this variable name
	{
		return newsMakerDefaultListModel.get(index);
		
	}
	/**
	 * <P>
	 * This method returns the names of the news makers as an array of strings.
	 * </P>
	 * @return The news makers names.
	 */
	public String[] getNewsMakerNames() {
		String[] newsMakers = new String[newsMakerDefaultListModel.getSize()];
		for (int i = 0; i < newsMakerDefaultListModel.getSize(); i++) {
			newsMakers[i]= newsMakerDefaultListModel.getElementAt(i).getName();
		}
		return newsMakers;
	}
	/**
	 * <P>
	 * This method replaces the news maker specified.
	 * </P>	
	 * @param newsMakerModel The news maker to replace.
	 */
	public void replace(NewsMakerModel newsMakerModel) {
		// TODO Double check that this method is the proper method.
		/* if I go to edit a news maker, two basic things to do in the view: edit name/news stories associated
		 * to remove news maker, select news story and click remove news maker
		 * Might want to change news maker, but there is an existing news maker with that name.
		 * Replace option should populate, change or cancel
		 * Binary search for the news maker if replacing
		*/
		// TODO figure out how to take the input from the user to replace the newsMakerModel 
		// newsMakerDefaultListModel.set(newsMakerDefaultListModel.indexOf(newsMakerModel), REPLACEME - text from user);
	}
	/**
	 * <P>
	 * This method removes the news maker specified.
	 * </P>
	 * @param newsMakerModel The newsmaker to remove.
	 */
	public void remove(NewsMakerModel newsMakerModel) {
		newsMakerDefaultListModel.removeElement(newsMakerModel);
	}
	/**
	 * <P>
	 * This method removes the list of news makers.
	 * </P>
	 * @param newsMakers The news makers to remove.
	 */
	public void removeListOfNewsMakers(DefaultListModel<NewsMakerModel> newsMakers) {
		newsMakers.removeAllElements();
	}
	/**
	 * <P>
	 * This method removes all newsmakers.
	 * </P>
	 */
	public void removeAllNewsMakers() {
		newsMakerDefaultListModel.removeAllElements();
	}
	/**
	 * <P>
	 * This method sets the news makers from a news maker list model.
	 * </P>
	 * @param newsMakerListModel The news maker list model to set.
	 */
	public void setNewsMakersFromNewsMakerList(NewsMakerListModel newsMakerListModel) //TODO he didn't specify this variable name
	{	
		newsMakerDefaultListModel.clear();
		for(int i = 0; i < newsMakerListModel.size(); i++) {
			newsMakerDefaultListModel.add(i, newsMakerListModel.get(i));
		}

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
	public void add(NewsMakerModel newsMakerModel) throws IllegalArgumentException { 
		if (newsMakerDefaultListModel.contains(newsMakerModel) != true) {
			newsMakerDefaultListModel.addElement(newsMakerModel);
		}
		else {
			System.err.print("That news maker already exists.");
		}
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
		ArrayList<NewsMakerModel> newsMakersTemp = new ArrayList<NewsMakerModel>();
		NewsMakerModel exactNewsMaker;
		int index;
		for (int i = 0; i < newsMakerDefaultListModel.size(); i++) {
			newsMakersTemp.add(newsMakerDefaultListModel.getElementAt(i));
		}
		
		//TODO ASK!! Do we need to include the option for a linear search?		
		
			// if(Sorted) {
				// binary search
		sort();
		index = Collections.binarySearch(newsMakersTemp, get(new NewsMakerModel(newsMakerName)));
		exactNewsMaker = newsMakersTemp.get(index);
			// }
			// else {
				//unsorted linear search

				//for (int i = 0; i < newsMakerDefaultListModel.getSize(); i++) {
					//NewsMakerModel newsMaker;
					//if (newsMaker.getName().equals(newsMakerName)) {
						//return newsMaker;
					//}
				//}
			// if (index >= 0) {
				// TODO Have it return a copy instead (Eventually)
				// return this.newsMakerDefaultListModel.get(index);
			// } else {
				// return null;
			// }
			
		System.err.print("Attempted to conduct binary search on unsorted list.");
		
		
		return exactNewsMaker;
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
		NewsMakerModel newsMaker = null;
		for (int i = 0; i < newsMakerDefaultListModel.size(); i++) {
			if (newsMakerDefaultListModel.get(i).contains(newsMakerName)) {
			return newsMaker = newsMakerDefaultListModel.get(i);
			}
			else newsMaker = null;
		}
		return newsMaker;
	}

	/**
	 * This method sorts the list using a stable sort. It also sets the
	 * <code>sorted</code> flag to <code>true</code>.
	 */
	public void sort() {
		ArrayList<NewsMakerModel> newsMakersTemp = new ArrayList<NewsMakerModel>();
		for (int i = 0; i < newsMakerDefaultListModel.size(); i++) {
			newsMakersTemp.add(newsMakerDefaultListModel.getElementAt(i));
		}
		Collections.sort(newsMakersTemp);
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
