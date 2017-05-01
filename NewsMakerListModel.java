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
		newsMakerDefaultListModel = new DefaultListModel<NewsMakerModel>();
		
	}
	
	/**
	 * <P>
	 * This constructor sets the news maker list model.
	 * </P>
	 * @param newsMakerListModel The model to set the field to.
	 */
	public NewsMakerListModel(NewsMakerListModel newsMakerListModel) {
		this.newsMakerDefaultListModel = newsMakerListModel.getNewsMakers();
	}
	
	/**
	 * <P>
	 * This method returns whether or not the news maker list
	 * model is empty.
	 * </P>
	 * @return Whether or not the news maker list model is empty.
	 */
	public boolean isEmpty() {
		return newsMakerDefaultListModel.isEmpty();
		
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
	public NewsMakerModel get(NewsMakerModel newsMakerModel) //TODO didnt specify this variable name) 
	{
		for(int i = 0; i < newsMakerDefaultListModel.size(); i++) {
			if(newsMakerDefaultListModel.get(i).getName().equals(newsMakerModel.getName())) {
				return newsMakerDefaultListModel.get(i);
			}
		}
		System.err.println("Error. NewsMaker not found");
		return null;
		
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
	public NewsMakerModel get(int index) 
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
		//subjects as array list
		ArrayList<String> namesArrayList = new ArrayList<String>();
		//Special newsmaker
		//add all subjects to array list
		for(int i = 0; i < newsMakerDefaultListModel.size(); i++) {
			//if the news makers name is not none, then add to list
			if(!namesArrayList.contains(newsMakerDefaultListModel.get(i).getName())) {
				namesArrayList.add(newsMakerDefaultListModel.get(i).getName());
			}
		}
		
		//string[] subjects
		String[] names = new String[namesArrayList.size()];
		//add all subjects from array list to the string[] variable
		for(int i = 0; i < namesArrayList.size(); i++) {
			names[i] = namesArrayList.get(i);
		}
		
		return names;
	
	}
	/**
	 * <P>
	 * This method replaces the news maker specified.
	 * </P>	
	 * @param newsMakerModel The news maker to replace.
	 */
	public void replace(NewsMakerModel newsMakerModel) {
		for(int i = 0; i < newsMakerDefaultListModel.size();i++) {
			if(newsMakerDefaultListModel.get(i).equals(newsMakerModel)) {
				newsMakerDefaultListModel.remove(i);
				newsMakerDefaultListModel.add(i,newsMakerModel);
			}
		}
	}
	/**
	 * <P>
	 * This method removes the news maker specified.
	 * </P>
	 * @param newsMakerModel The newsmaker to remove.
	 */
	public void remove(NewsMakerModel newsMakerModel) {
		for(int i = 0; i < newsMakerDefaultListModel.size();i++) {
			if(newsMakerDefaultListModel.get(i).equals(newsMakerModel)) newsMakerDefaultListModel.remove(i);
		}
	}
	/**
	 * <P>
	 * This method removes the list of news makers.
	 * </P>
	 * @param newsMakers The news makers to remove.
	 */
	public void removeListOfNewsMakers(DefaultListModel<NewsMakerModel> newsMakers) {
		//for each news maker to remove
		for(int i = 0; i < newsMakers.size();i++) {
			//if the list contains that newsmaker
			if(this.contains(newsMakers.get(i))) {
				//loop through the list
				for(int j = 0; j < this.size(); j++) {
					//if the news maker in the list is equal to the one we want to remove
					if(this.get(j).equals(newsMakers.getElementAt(i))) {
						//loop through all the stories for him
						for(int x = 0; x < this.get(j).getNewsStoryListModel().size();x++) {
							//if the story in the list has the first newsmaker equal to the one we want to remove
							if(this.get(j).getNewsStoryListModel().getNewsStories().getElementAt(x).getNewsMaker1().getName().equals(newsMakers.get(i).getName())) {
								//change the name of that newsmaker in the story to none
								this.get(j).getNewsStoryListModel().get(x).setNewsMaker1(this.get(new NewsMakerModel()));
							}
							//if the story in the list has the second newsmaker equal to the one we want to remove
							else if(this.get(j).getNewsStoryListModel().getNewsStories().getElementAt(x).getNewsMaker2().getName().equals(newsMakers.get(i).getName())) {
								//change the name of that newsmaker in the story to none
								this.get(j).getNewsStoryListModel().get(x).setNewsMaker2(this.get(new NewsMakerModel()));
							}
						}
					}

				} //end for list
			}//end if contains
		}
		
		
		
		//for all the news makers to remove
		for(int i = 0; i < newsMakers.size();i++) {
			//if the list has that news maker
			if(this.newsMakerDefaultListModel.contains(newsMakers.get(i))) {
				//loop through the list
				for(int j = 0; j < this.newsMakerDefaultListModel.size(); j++) {
					//if the news maker in the list is equal to the one we want to remove
					if(this.newsMakerDefaultListModel.get(j).equals(newsMakers.getElementAt(i))) {
						//remove it
						this.newsMakerDefaultListModel.remove(j);
					}
					
				} //end for list
			}//end if contains
		}//end for news makers to remove
	}
	/**
	 * <P>
	 * This method removes all newsmakers.
	 * </P>
	 */
	public void removeAllNewsMakers() {
		for(int i = 0; i < newsMakerDefaultListModel.size();i++) {
			for(int x = 0; x < this.get(i).getNewsStoryListModel().size();x++) {
				//if the story in the list has the first newsmaker equal to the one we want to remove
				if(this.get(i).getNewsStoryListModel().getNewsStories().getElementAt(x).getNewsMaker1().getName().equals(newsMakerDefaultListModel.get(i).getName())) {
					//change the name of that newsmaker in the story to none
					this.get(i).getNewsStoryListModel().get(x).setNewsMaker1(this.get(new NewsMakerModel()));
				}
				//if the story in the list has the second newsmaker equal to the one we want to remove
				else if(this.get(i).getNewsStoryListModel().getNewsStories().getElementAt(x).getNewsMaker2().getName().equals(newsMakerDefaultListModel.get(i).getName())) {
					//change the name of that newsmaker in the story to none
					this.get(i).getNewsStoryListModel().get(x).setNewsMaker2(this.get(new NewsMakerModel()));

				} 
			} //end for each story
		} //end for each news maker
		newsMakerDefaultListModel.clear();
	}
	/**
	 * <P>
	 * This method sets the news makers from a news maker list model.
	 * </P>
	 * @param newsMakerListModel The news maker list model to set.
	 */
	public void setNewsMakersFromNewsMakerList(NewsMakerListModel newsMakerListModel) //TODO he didnt specify this variable name
	{		
		newsMakerDefaultListModel = newsMakerListModel.getNewsMakers();
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
		newsMakerDefaultListModel.addElement(newsMakerModel);
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
		ArrayList<NewsMakerModel> newsMakersAsArrayList = new ArrayList<NewsMakerModel>();
		
		for(int i = 0; i < newsMakerDefaultListModel.size(); i++) {
			newsMakersAsArrayList.add(newsMakerDefaultListModel.get(i));
		}
		Collections.sort(newsMakersAsArrayList);
			int index = Collections.binarySearch(newsMakersAsArrayList, new NewsMakerModel(newsMakerName));
			if (index >= 0) {
				// TODO Have it return a copy instead (Eventually)
				return this.newsMakerDefaultListModel.get(index);
			} else {
				return null;
			}
			
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
		//TODO make sure this works.
		for (int i = 0; i < newsMakerDefaultListModel.size(); i++) {
			if (newsMakerDefaultListModel.get(i).getName().contains(newsMakerName)) {
				return newsMakerDefaultListModel.getElementAt(i);
			}
		}
		System.err.println("NewsMaker not found");
		return null;
	}

	/**
	 * This method sorts the list using a stable sort. It also sets the
	 * <code>sorted</code> flag to <code>true</code>.
	 */
	public void sort() {
		//arraylist
		ArrayList<NewsMakerModel> model = new ArrayList<NewsMakerModel>();
		//put all values in arraylist
		for(int i = 0; i < newsMakerDefaultListModel.size(); i++) {
			model.add(newsMakerDefaultListModel.getElementAt(i));
		}
		//sort arraylist
		Collections.sort(model);
		//put back into default list model
		for(int i = 0; i < model.size(); i++) {
			newsMakerDefaultListModel.add(i, model.get(i));
		}
		
	}

	/**
	 * <P>
	 * This method handles when an action is performed.
	 * </P>
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		//TODO what do we do here??
		
	}
}
