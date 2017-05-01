import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * Project 4, CS 2334, Section 010, April 23, 2017
 * <P>
 * This class provides the controller for the MVC part of project 4. This involves
 * having methods on saving, loading, importing, editing, deleting, etc. As well as having
 * inner classes for listener objects, that will decide what actions need to be performed.
 * </P>
 * <P>
 * Note that the this class contains: fields for the model, view, and other dialog/list fields.
 * </P>
 * 
 * @author Dean Hougen, Jered Little, Vishnupriya Parasaram, Jessica Horner, and Zakary Koskovich 
 * @version 1.0
 * 
 */
//Jered Little created the stub code for this class.
public class NewsController {

	
	private NewsDataBaseModel newsDataBaseModel;
	private SelectionView selectionView;
	private EditNewsMakerView editNewsMakerView;
	private JDialog viewDialog;
	private AddEditNewsStoryView addEditNewsStoryView;
	private NewsStory editedNewsStory;
	private MediaTypeSelectionView mediaTypeSelectionView;
	private List<NewsMedia> selectedMediaTypes;
	
	/**
	 * <P>
	 *TODO
	 * This is the constructor for the news controller. I have no idea what it does.
	 * </P>
	 */
	public NewsController() {

	}
	/**
	 * <P>
	 * This method sets the news data base model field.
	 * </P>
	 * @param newsDataBaseModel The news data base model to set.
	 */
	public void setNewsDataBaseModel(NewsDataBaseModel newsDataBaseModel) {
		if(newsDataBaseModel.getNewsMakerListModel().isEmpty() && newsDataBaseModel.getNewsStoryListModel().isEmpty()) {
			this.newsDataBaseModel = newsDataBaseModel;
			return;
		}
		this.newsDataBaseModel.setNewsMakerListModel(newsDataBaseModel.getNewsMakerListModel());
		this.newsDataBaseModel.setNewsStoryListModel(newsDataBaseModel.getNewsStoryListModel());
		selectionView.setNewsDataBaseModel(newsDataBaseModel);
		System.out.println("Success! NewsDataBaseModel has been set! Good work!");
	}
	/**
	 * <P>
	 * This method sets the selection view.
	 * </P>
	 * @param selectionView The selection view to set.
	 */
	public void setSelectionView(SelectionView selectionView) {
		this.selectionView = selectionView;
		selectionView.registerNewsStoryMenuListener(new NewsStoryMenuListener());
		selectionView.registerNewsMakerMenuListener(new NewsMakerMenuListener());
		selectionView.registerFileMenuListener(new FileMenuListener());
		selectionView.registerDisplayMenuListener(new DisplayMenuListener());
		
		
	}
	/**
	 * <P>
	 * This method loads all the news data in BINARY FORMAT.
	 * </P>
	 * @throws IOException 
	 * @throws ClassNotFoundException 
	 */
	public void loadNewsData() throws ClassNotFoundException, IOException {
		JFileChooser fc = new JFileChooser();
		fc.setDialogTitle("Open Binary Data File");
		int returnValue = fc.showOpenDialog(selectionView);
		if(returnValue == JFileChooser.APPROVE_OPTION) {
			System.out.println("Load Binary Data File");
			//TODO load data in
			String outputFileName = fc.getSelectedFile().getPath();
			FileInputStream fileInputStream = new FileInputStream(outputFileName);
			ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
			newsDataBaseModel = (NewsDataBaseModel) objectInputStream.readObject();
			objectInputStream.close();
			
		}
	}
	/**
	 * <P>
	 * This method saves all the news data.
	 * </P> 
	 */
	private void saveNewsData() {
		//No data is in the model, so we cant save.
		if(newsDataBaseModel.newsMakerListIsEmpty() && newsDataBaseModel.newsStoryListIsEmpty()) {
			return;
		}
		JFileChooser fc = new JFileChooser();
		fc.setDialogTitle("Save Binary Data File");
		int returnValue = fc.showSaveDialog(selectionView);
		if(returnValue == JFileChooser.APPROVE_OPTION) {
			System.out.println("Save Binary File");
		}
	}
	/**
	 * <P>
	 * This method imports news stories.
	 * </P>
	 */
	private void importNoozStories() {
		Map<String,String> sourceMap = new LinkedHashMap<String,String>();
		Map<String,String> topicMap = new LinkedHashMap<String,String>();
		Map<String,String> subjectMap = new LinkedHashMap<String,String>();
		//file chooser
		JFileChooser fc = new JFileChooser();
		fc.setDialogTitle("Open Text File(s)");

		//multiple files selection DISABLED. This causes problems with the for loop
		fc.setMultiSelectionEnabled(false);
		int returnValue = JFileChooser.APPROVE_OPTION;
		String newsStoryFilePath = "";
		String answer = "";
		String path = "";
		while(returnValue == JFileChooser.APPROVE_OPTION) {
			//input dialog question
			returnValue = fc.showOpenDialog(selectionView);
			
			if(returnValue == JFileChooser.CANCEL_OPTION) {
				break;
			}
			//ask for what type of file
			answer = JOptionPane.showInputDialog(fc,"Does this file contain source codes, topic codes, subject codes, or news stories?",
					fc.getSelectedFile().getName(),JOptionPane.WARNING_MESSAGE);
			
			//create the maps based on user answer
			if(answer.equalsIgnoreCase("source codes")) {
				try {
					path = fc.getSelectedFile().getPath();
					newsDataBaseModel.setNewsSourceMap(CodeFileProcessor.readCodeFile(path));
				} catch (IOException e) {
					e.printStackTrace();
				}

			}
			if(answer.equalsIgnoreCase("topic codes")) {

				try {
					path = fc.getSelectedFile().getPath();
					newsDataBaseModel.setNewsTopicMap(CodeFileProcessor.readCodeFile(path));
				} catch (IOException e) {
					e.printStackTrace();
				}

			}
			if(answer.equalsIgnoreCase("subject codes")) {

				try {
					path = fc.getSelectedFile().getPath();
					newsDataBaseModel.setNewsSubjectMap(CodeFileProcessor.readCodeFile(path));
				} catch (IOException e) {
					e.printStackTrace();
				}

			}
			if(answer.equalsIgnoreCase("news stories")) {
					newsStoryFilePath = fc.getSelectedFile().getPath();
			}
			while(!answer.equals("topic codes") && !answer.equalsIgnoreCase("subject codes") && !answer.equalsIgnoreCase("news stories") &&
					!answer.equalsIgnoreCase("source codes")) {

				answer = JOptionPane.showInputDialog(selectionView,"Not a valid answer. "
						+ "Does this file contain source codes, topic codes, subject codes, or news stories?",fc.getSelectedFile().getName(),
							JOptionPane.WARNING_MESSAGE);
				if(answer.equalsIgnoreCase("source codes")) {

					try {
						newsDataBaseModel.setNewsSourceMap(CodeFileProcessor.readCodeFile(fc.getSelectedFile().getPath()));
					} catch (IOException e) {
						e.printStackTrace();
					}

				}
				if(answer.equalsIgnoreCase("topic codes")) {

					try {
						newsDataBaseModel.setNewsTopicMap(CodeFileProcessor.readCodeFile(fc.getSelectedFile().getPath()));
					} catch (IOException e) {

						e.printStackTrace();
					}

				}
				if(answer.equalsIgnoreCase("subject codes")) {

					try {
						newsDataBaseModel.setNewsSubjectMap(CodeFileProcessor.readCodeFile(fc.getSelectedFile().getPath()));
					} catch (IOException e) {
						e.printStackTrace();
					}

				}
			}//end while incorrect input

		} // end while answer is approve
		try {
			
			sourceMap = newsDataBaseModel.getNewsSourceMap();
			topicMap = newsDataBaseModel.getNewsTopicMap();
			subjectMap = newsDataBaseModel.getNewsSubjectMap();
			NewsDataBaseModel tempModel = NoozFileProcessor.readNoozFile(newsStoryFilePath, 
					newsDataBaseModel.getNewsSourceMap(), newsDataBaseModel.getNewsTopicMap(), newsDataBaseModel.getNewsSubjectMap());
			 newsDataBaseModel.setNewsSourceMap(sourceMap);
			 newsDataBaseModel.setNewsSubjectMap(subjectMap);
			 newsDataBaseModel.setNewsTopicMap(topicMap);
			 //call this method to set the model again and inform the view.
			 setNewsDataBaseModel(tempModel);
			 
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * <P>
	 * This method exports news stories.
	 * </P>
	 */
	private void exportNewsStories() {
		//No data is in the model, so we cant save.
		if(newsDataBaseModel == null) {
			return;
		}
		JFileChooser fc = new JFileChooser();
		fc.setDialogTitle("Save Text File");
		int returnValue = fc.showSaveDialog(selectionView);
		if(returnValue == JFileChooser.APPROVE_OPTION) {
			System.out.println("Save Text File");
			//TODO save data
			fc.getSelectedFile();
		}
	}
	/**
	 * <P>
	 * This method allow you to add a news maker to the list.
	 * </P>
	 */
	private void addNewsMaker() {
		String input = "";
		
		input = JOptionPane.showInputDialog(selectionView,"Enter News Maker Name: ",
			"Add News Maker",JOptionPane.INFORMATION_MESSAGE);
		

		//if user didnt click cancel
		if(!(input == null)) {
			//if the data base has the newsmaker already
			if(newsDataBaseModel.containsNewsMakerModel(new NewsMakerModel(input))) {
				//ask to replace
				int replaceNewsMaker = JOptionPane.showConfirmDialog(selectionView,"Replace News Maker: " + input,
						"Replace News Maker",JOptionPane.YES_NO_CANCEL_OPTION);
				//if they pressed yes
				if(replaceNewsMaker == JOptionPane.YES_OPTION) {
					//replace
					//TODO replace
					System.out.println("Testing: We will now REPLACE the newsmakermodel.... Uncomment line below this when the newsdatabasemodel is finished.");
					//newsDataBaseModel.replaceNewsMakerModel(new NewsMakerModel(input));
				}
			}
			//otherwise, if the database doesnt contain the news maker
			else {
				//add him
				newsDataBaseModel.addNewsMakerModel(new NewsMakerModel(input));
			}	
		} // end if
		
		
	}
	/**
	 * <P>
	 * This method allows you to edit the news maker.
	 * </P>
	 * 
	 */
	private void editNewsMakers() {
		//create the edit news maker view based on the first news maker selected TODO, when you get the selection view loaded with data, use this line and
		for(int i = 0; i < selectionView.getSelectedNewsMakers().length; i++) {
			editNewsMakerView = new EditNewsMakerView(newsDataBaseModel.getNewsMakerListModel().get(selectionView.getSelectedNewsMakers()[i]),newsDataBaseModel);
			this.newsDataBaseModel.addActionListener(editNewsMakerView);
			viewDialog = new JDialog();
			viewDialog.setTitle("Edit News Maker");
			viewDialog.add(editNewsMakerView);
			viewDialog.setSize(500, 300);
			viewDialog.setVisible(true);
		}
		editNewsMakerView.jtfName.addActionListener(new EditNewsMakerNameListener());
		//TODO Test delete
		editNewsMakerView.jbtRemoveFromStory.addActionListener(new RemoveNewsMakerFromNewsStoriesListener());
		
		
	}
	/**
	 * <P>
	 * This method allows you to delete the news makers. 
	 * </P>
	 */
	private void deleteNewsMakers() {
		
		//indices of the selected newsmakers
		int[] selectedNewsMakersIndex = selectionView.getSelectedNewsMakers();
		DefaultListModel<NewsMakerModel> modelsToRemove = new DefaultListModel<NewsMakerModel>();
		
		//loop through how many are selected
		for(int i = 0; i < selectedNewsMakersIndex.length; i++) {
			//ask to delete each one
			int deleteNewsMaker = JOptionPane.showConfirmDialog(selectionView,"Delete News Maker: " + newsDataBaseModel.getNewsMakerListModel().get(selectedNewsMakersIndex[i]),
					"Delete News Maker",JOptionPane.YES_NO_CANCEL_OPTION);
			//if they want to delete
			if(deleteNewsMaker == JOptionPane.YES_OPTION) {
				//get the news makers, remove the one specified.
				modelsToRemove.addElement((newsDataBaseModel.getNewsMakerListModel().get(selectedNewsMakersIndex[i])));
			}
			else {
				continue;
			}
		}
		newsDataBaseModel.removeNewsMakers(modelsToRemove);
	}
	/**
	 * <P>
	 * This method deletes the news maker list.
	 * </P>
	 */
	private void deleteNewsMakerList() {
		
		int deleteNewsMaker = JOptionPane.showConfirmDialog(selectionView,"Delete ALL News Makers?",
				"Delete News Makers",JOptionPane.YES_NO_CANCEL_OPTION);
		//if they want to delete
		if(deleteNewsMaker == JOptionPane.YES_OPTION) {
			//get the news makers, remove the one specified.
			newsDataBaseModel.removeAllNewsMakers();
		}
		
		
	}
	/**
	 * <P>
	 * This method allows you to add a news story to the list.
	 * </P>
	 */
	private void addNewsStory() {
		addEditNewsStoryView = new AddEditNewsStoryView(newsDataBaseModel,null);
		addEditNewsStoryView.jbtAddEditNewsStory.addActionListener(new AddEditNewsStoryListener());
		addEditNewsStoryView.jbtAddEditNewsStory.setText("Add News Story");
		viewDialog = new JDialog();
		viewDialog.setTitle("Add News Story");
		viewDialog.add(addEditNewsStoryView);
		viewDialog.setSize(700, 400);
		viewDialog.setVisible(true);
		
	}
	/**
	 * <P>
	 * This method allows you to edit the news stories.
	 * </P>
	 */
	private void editNewsStories() {
			int[] selectedNewsStoriesIndex = selectionView.getSelectedNewsStories();
			DefaultListModel<NewsStory> storiesToRemove = new DefaultListModel<NewsStory>();
				
				//loop through how many are selected
				for(int i = 0; i < selectedNewsStoriesIndex.length; i++) {
					System.out.println("Here");
					//new window for each addeditnewsmakerview
					addEditNewsStoryView = new AddEditNewsStoryView(newsDataBaseModel,newsDataBaseModel.getNewsStoryListModel().get(selectedNewsStoriesIndex[i]));
					addEditNewsStoryView.jbtAddEditNewsStory.addActionListener(new AddEditNewsStoryListener());
					addEditNewsStoryView.jbtAddEditNewsStory.setText("Edit News Story");
					viewDialog = new JDialog();
					viewDialog.setTitle("Edit News Story");
					viewDialog.add(addEditNewsStoryView);
					viewDialog.setSize(700, 400);
					viewDialog.setVisible(true);
					//we have to remove the stories, since we are going to edit them and add them back.
					storiesToRemove.addElement(newsDataBaseModel.getNewsStoryListModel().get(selectedNewsStoriesIndex[i]));
					//remove story from individual newsmakers (will be added when they click edit news story
					newsDataBaseModel.getNewsMakerListModel().get(storiesToRemove.get(i).getNewsMaker1()).removeNewsStory(storiesToRemove.get(i));
					newsDataBaseModel.getNewsMakerListModel().get(storiesToRemove.get(i).getNewsMaker2()).removeNewsStory(storiesToRemove.get(i));
				}
				//we have to remove the stories, since we are going to edit them and add them back.
				newsDataBaseModel.removeNewsStories(storiesToRemove);
				
	}
	/**
	 * <P>
	 * Method to sort the news stories. Uses a JOptionPane to query the user about the preferred sort order. 
	 * If the user cancels, it returns without sorting. Otherwise, it copies the DefaultListModel of stories to an array, 
	 * sorts the array based on the specified sort criterion, 
	 * then sets the news story list model of the news database to the sorted array.
	 * </P>
	 */
	private void sortNewsStories() {
		//cant sort an empty list
		if(newsDataBaseModel.getNewsStories().isEmpty()) return;
		String[] order = {"primary","secondary","tertiary","quaternary"};
		//the sort order (lowest index, highest priority)
		ArrayList<SortCriterion> sortOrder = new ArrayList<SortCriterion>();
		//loop to get all sort criterion
		for(int i = 0; i < 4; i++) {
			String answer = JOptionPane.showInputDialog("What is your " +order[i]+ " sort criterion? (source, topic, subject, date, or length?)");
			//if they cancel without entering anything
			if(answer.equals("source")) {
				sortOrder.add(SortCriterion.SOURCE);
			}
			if(answer.equals("topic")) {
				sortOrder.add(SortCriterion.TOPIC);
			}
			if(answer.equals("subject")) {
				sortOrder.add(SortCriterion.SUBJECT);
			}
			if(answer.equals("length")) {
				sortOrder.add(SortCriterion.LENGTH);
			}
			if(answer.equals("date")) {
				sortOrder.add(SortCriterion.DATE_TIME);
			}
			if(sortOrder.get(i) == null) {
				return;
			}
			//while the answer is not valid
			while(!sortOrder.get(i).equals(SortCriterion.SOURCE) && !sortOrder.get(i).equals(SortCriterion.TOPIC) && !sortOrder.get(i).equals(SortCriterion.SUBJECT) && !sortOrder.get(i).equals(SortCriterion.DATE_TIME) && 
					!sortOrder.get(i).equals(SortCriterion.LENGTH)) {
				sortOrder.remove(i);
				answer = JOptionPane.showInputDialog("Try Again. What is your " +order[i]+ " sort criterion? (source, topic, subject, date, or length?)");
				if(answer.equals("source")) {
					sortOrder.add(SortCriterion.SOURCE);
				}
				if(answer.equals("topic")) {
					sortOrder.add(SortCriterion.TOPIC);
				}
				if(answer.equals("subject")) {
					sortOrder.add(SortCriterion.SUBJECT);
				}
				if(answer.equals("length")) {
					sortOrder.add(SortCriterion.LENGTH);
				}
				if(answer.equals("date")) {
					sortOrder.add(SortCriterion.DATE_TIME);
				}
			}
		}
		//add the last sort criteria
		if(!sortOrder.contains("source")) {
			sortOrder.add(SortCriterion.SOURCE);
		}
		else if(!sortOrder.contains("topic")) {
			sortOrder.add(SortCriterion.TOPIC);
		}
		else if(!sortOrder.contains("length")) {
			sortOrder.add(SortCriterion.LENGTH);
		}
		else if(!sortOrder.contains("date")) {
			sortOrder.add(SortCriterion.DATE_TIME);
		}
		else if(!sortOrder.contains("subject")) {
			sortOrder.add(SortCriterion.SUBJECT);
		}
		//create an arraylist to sort on
		ArrayList<NewsStory> listToSort = new ArrayList<NewsStory>();
		//add all data from news stories in data base to the array list
		for(int i = 0; i < newsDataBaseModel.getNewsStories().size(); i++) {
			listToSort.add(newsDataBaseModel.getNewsStories().getElementAt(i));
		}
		//clear the stories so we can add the sorted ones later
		//TODO do we clear?
		//sort based on criteria list
		for(int i = 5; i > 0;i--) {
			if(sortOrder.get(i).equals(SortCriterion.SOURCE)) {
				Collections.sort(listToSort, SourceComparator.SOURCE_COMPARATOR);
			}
			else if(sortOrder.get(i).equals(SortCriterion.LENGTH)) {
				Collections.sort(listToSort,LengthComparator.LENGTH_COMPARATOR);
			}
			else if(sortOrder.get(i).equals(SortCriterion.DATE_TIME)) {
				Collections.sort(listToSort,DateComparator.DATE_COMPARATOR);
			}
			else if(sortOrder.get(i).equals(SortCriterion.SUBJECT)) {
				Collections.sort(listToSort,SubjectComparator.SUBJECT_COMPARATOR);
			}
			else if(sortOrder.get(i).equals(SortCriterion.TOPIC)) {
				Collections.sort(listToSort);
			}
		}
		//add each story to the data base now (sorted)
		NewsStoryListModel tempModel = new NewsStoryListModel();
		for(int i = 0; i < listToSort.size(); i++) {
			tempModel.add(listToSort.get(i));
		}
		newsDataBaseModel.setNewsStoryListModel(tempModel);
	
	}
	
	/**
	 * <P>
	 * This method allows you to delete the news stories.
	 * </P>
	 */
	private void deleteNewsStories() {
		
		int[] selectedNewsStoriesIndex = selectionView.getSelectedNewsStories();
		DefaultListModel<NewsStory> storiesToRemove = new DefaultListModel<NewsStory>();
		
		for(int i = 0; i < selectedNewsStoriesIndex.length; i++) {
			
			int deleteStory = JOptionPane.showConfirmDialog(selectionView,"Delete News Story: " + newsDataBaseModel.getNewsStoryListModel().get(selectedNewsStoriesIndex[i]),
					"Delete News Story",JOptionPane.YES_NO_CANCEL_OPTION);
			//if they want to delete
			if(deleteStory == JOptionPane.YES_OPTION) {
				//get the news makers, remove the one specified.
				storiesToRemove.addElement(newsDataBaseModel.getNewsStoryListModel().get(selectedNewsStoriesIndex[i]));
			}
			else {
				continue;
			}
			
		}
		
		newsDataBaseModel.removeNewsStories(storiesToRemove);
		
	}
	/**
	 * <P>
	 * This method allows you to delete all news stories.
	 * </P>
	 */
	private void deleteAllNewsStories() {
		int deleteNewsStories = JOptionPane.showConfirmDialog(selectionView,"Delete ALL News Stories?",
				"Delete News Stories",JOptionPane.YES_NO_CANCEL_OPTION);
		//if they want to delete
		if(deleteNewsStories == JOptionPane.YES_OPTION) {
			//get the news makers, remove the one specified.
			newsDataBaseModel.removeAllNewsStories();
		}
	}
	/**
	 * This method is called to display pie charts when requested by the user.
	 * It gets the list of all selected news makers from the
	 * <code>SectionView</code> and displays one pie chart per news maker. For
	 * each pie chart it needs to determine the media type(s) to display, the
	 * news content type to display, and the news metric to use for display.
	 */
	private void displayPieCharts() {
		// Get the indices of the news makers selected in the selection view.
		int[] indices = selectionView.getSelectedNewsMakers();
		
		// If there are no selected news makers, alert the user and return.
		if (0 == indices.length) {
			JOptionPane.showMessageDialog(selectionView, "No newsmaker selected.", "Invalid Selection",
					JOptionPane.WARNING_MESSAGE);
		} else {
			// If there are selected news makers, go through the process for each.
			NewsMakerListModel newsMakerListModel = this.newsDataBaseModel.getNewsMakerListModel();
			for (int index : indices) {
				NewsMakerModel newsMakerModel = newsMakerListModel.get(index);
				String newsMakerName = newsMakerModel.getName();

				// Get media types using MediaTypeSelectionView.
				this.selectedMediaTypes = null;
				this.mediaTypeSelectionView = new MediaTypeSelectionView();
				MediaTypeSelectionListener mediaTypeSelectionListener = new MediaTypeSelectionListener();
				this.mediaTypeSelectionView.jbOkay.addActionListener(mediaTypeSelectionListener);
				this.mediaTypeSelectionView.jbCancel.addActionListener(mediaTypeSelectionListener);

				this.viewDialog = new JDialog(selectionView, newsMakerName, true);
				this.viewDialog.add(mediaTypeSelectionView);
				this.viewDialog.setResizable(false);
				this.viewDialog.pack();
				this.viewDialog.setVisible(true);

				// If no media types were selected, go on to next news maker.
				if (null == this.selectedMediaTypes) {
					continue;
				}

				// Get content type using JOptionPane.
				NewsContent selectedNewsContent = null;
				selectedNewsContent = (NewsContent) JOptionPane.showInputDialog(selectionView,
						"Graph news stories based on which content?", newsMakerName, JOptionPane.PLAIN_MESSAGE, null,
						NewsContent.values(), NewsContent.TOPIC);
				if (null == selectedNewsContent) {
					continue;
				}

				// Get metric type using JOPtionPane.
				NewsMetric selectedNewsMetric = null;
				selectedNewsMetric = (NewsMetric) JOptionPane.showInputDialog(selectionView,
						"Graph news stories based on which metric?", newsMakerName, JOptionPane.PLAIN_MESSAGE, null,
						NewsMetric.values(), NewsMetric.LENGTH);
				if (null == selectedNewsMetric) {
					continue;
				}

				// Create the pie chart.
				//TODO I had to change this??
				PieChartView pieChartView = new PieChartView(newsMakerModel, selectedMediaTypes.toString(), selectedNewsContent.toString(),
						selectedNewsMetric.toString());
				
				//Make sure the pie chart listens for model changes so that it can update itself.
				newsMakerModel.addActionListener(pieChartView);
			}
		}
	}	
	/**
	 * <P>
	 * This method allows you to display text views.
	 * </P>
	 */
	private void displayTextViews() {
		// Get the indices of the news makers selected in the selection view.
		int[] indices = selectionView.getSelectedNewsMakers();
		
		// If there are no selected news makers, alert the user and return.
		if (0 == indices.length) {
			JOptionPane.showMessageDialog(selectionView, "No newsmaker selected.", "Invalid Selection",
					JOptionPane.WARNING_MESSAGE);
		} else {
			// If there are selected news makers, go through the process for each.
			NewsMakerListModel newsMakerListModel = this.newsDataBaseModel.getNewsMakerListModel();
			for (int index : indices) {
				NewsMakerModel newsMakerModel = newsMakerListModel.get(index);
				String newsMakerName = newsMakerModel.getName();

				// Get media types using MediaTypeSelectionView.
				this.selectedMediaTypes = null;
				this.mediaTypeSelectionView = new MediaTypeSelectionView();
				MediaTypeSelectionListener mediaTypeSelectionListener = new MediaTypeSelectionListener();
				this.mediaTypeSelectionView.jbOkay.addActionListener(mediaTypeSelectionListener);
				this.mediaTypeSelectionView.jbCancel.addActionListener(mediaTypeSelectionListener);
				
				

				this.viewDialog = new JDialog(selectionView, newsMakerName, true);
				this.viewDialog.add(mediaTypeSelectionView);
				this.viewDialog.setResizable(false);
				this.viewDialog.pack();
				this.viewDialog.setVisible(true);
				
				/***************************************SORT ORDER *************************/
				String[] order = {"primary","secondary","tertiary","quaternary"};
				//the sort order (lowest index, highest priority)
				ArrayList<SortCriterion> sortOrder = new ArrayList<SortCriterion>();
				//loop to get all sort criterion
				for(int i = 0; i < 4; i++) {
					String answer = JOptionPane.showInputDialog("What is your " +order[i]+ " sort criterion? (source, topic, subject, date, or length?)");
					//if they cancel without entering anything
					if(answer == null) return;
					if(answer.equals("source")) {
						sortOrder.add(SortCriterion.SOURCE);
					}
					if(answer.equals("topic")) {
						sortOrder.add(SortCriterion.TOPIC);
					}
					if(answer.equals("subject")) {
						sortOrder.add(SortCriterion.SUBJECT);
					}
					if(answer.equals("length")) {
						sortOrder.add(SortCriterion.LENGTH);
					}
					if(answer.equals("date")) {
						sortOrder.add(SortCriterion.DATE_TIME);
					}
					if(sortOrder.get(i) == null) {
						return;
					}
					//while the answer is not valid
					while(!sortOrder.get(i).equals(SortCriterion.SOURCE) && !sortOrder.get(i).equals(SortCriterion.TOPIC) && !sortOrder.get(i).equals(SortCriterion.SUBJECT) && !sortOrder.get(i).equals(SortCriterion.DATE_TIME) && 
							!sortOrder.get(i).equals(SortCriterion.LENGTH)) {
						sortOrder.remove(i);
						answer = JOptionPane.showInputDialog("Try Again. What is your " +order[i]+ " sort criterion? (source, topic, subject, date, or length?)");
						if(answer.equals("source")) {
							sortOrder.add(SortCriterion.SOURCE);
						}
						if(answer.equals("topic")) {
							sortOrder.add(SortCriterion.TOPIC);
						}
						if(answer.equals("subject")) {
							sortOrder.add(SortCriterion.SUBJECT);
						}
						if(answer.equals("length")) {
							sortOrder.add(SortCriterion.LENGTH);
						}
						if(answer.equals("date")) {
							sortOrder.add(SortCriterion.DATE_TIME);
						}
					}
				}
				//add the last sort criteria
				if(!sortOrder.contains("source")) {
					sortOrder.add(SortCriterion.SOURCE);
				}
				else if(!sortOrder.contains("topic")) {
					sortOrder.add(SortCriterion.TOPIC);
				}
				else if(!sortOrder.contains("length")) {
					sortOrder.add(SortCriterion.LENGTH);
				}
				else if(!sortOrder.contains("date")) {
					sortOrder.add(SortCriterion.DATE_TIME);
				}
				else if(!sortOrder.contains("subject")) {
					sortOrder.add(SortCriterion.SUBJECT);
				}
				/**********************************************************************************/
				new TextView(newsMakerModel,selectedMediaTypes,sortOrder);
				
				// If no media types were selected, go on to next news maker.
				if (null == this.selectedMediaTypes) {
					continue;
				}
			} //for each selected newsmaker
		}
		
	}
	
	
	/**
	 * <P>
	 * This class allows you to add a new file menu listener, and decide what actions
	 * should be taken on that object.
	 * </P>
	 *
	 */
	class FileMenuListener implements ActionListener {

		/**
		 * This method allows decides what actions should be taken when an event occurs.
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			String eventSourceText = ((JMenuItem) e.getSource()).getText();
			if(eventSourceText.equals("Import")) {
				importNoozStories();
			}
			else if(eventSourceText.equals("Export")) {
				exportNewsStories();
			}
			else if(eventSourceText.equals("Save")) {
					saveNewsData();

			}
			else if(eventSourceText.equals("Load")) {
				
				try {
					loadNewsData();
				} catch (ClassNotFoundException e1) {
					e1.printStackTrace();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				
			}
			
		}
		
	}
	
	/**
	 * <P>
	 * This class allows you to add a new news maker menu listener, and decide what actions
	 * should be taken on that object.
	 * </P>
	 *
	 */
	public class NewsMakerMenuListener implements ActionListener {

		/**
		 * This method allows decides what actions should be taken when an event occurs.
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			String eventSourceText = ((JMenuItem) e.getSource()).getText();
			if(eventSourceText.equals("Add Newsmaker")) {
				addNewsMaker();
				
			}
			else if(eventSourceText.equals("Edit Newsmaker")) {
				editNewsMakers();
			}
			else if(eventSourceText.equals("Delete Newsmaker")) {
				deleteNewsMakers();
			}
			else if(eventSourceText.equals("Delete Newsmaker List")) {
				deleteNewsMakerList();
			}
		}
		
	}
	/**
	 * <P>
	 * This class allows you to add a new news story menu listener, and decide what actions
	 * should be taken on that object.
	 * </P>
	 *
	 */
	public class NewsStoryMenuListener implements ActionListener {

		/**
		 * This method allows decides what actions should be taken when an event occurs.
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			String eventSourceText = ((JMenuItem) e.getSource()).getText();
			if(eventSourceText.equals("Add News Story")) {
				addNewsStory();
			}
			else if(eventSourceText.equals("Edit News Story")) {
				editNewsStories();
			}
			else if(eventSourceText.equals("Sort News Stories")) {
				sortNewsStories();
			}
			else if(eventSourceText.equals("Delete News Story")) {
				deleteNewsStories();
			}
			else if(eventSourceText.equals("Delete All News Stories")) {
				deleteAllNewsStories();
			}
			
		}
		
	}
	/**
	 * <P>
	 * This class allows you to add a new display menu listener, and decide what actions
	 * should be taken on that object.
	 * </P>
	 *
	 */
	public class DisplayMenuListener implements ActionListener {

		/**
		 * This method allows decides what actions should be taken when an event occurs.
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			String eventSourceText = ((JMenuItem) e.getSource()).getText();
			if(eventSourceText.equals("Text")) {
				System.out.println("Text");
				displayTextViews();
			}
			else if(eventSourceText.equals("Pie Chart")) {
				System.out.println("Pie Chart");
				displayPieCharts();
			}
			
		}
		
	}
	/**
	 * <P>
	 * This class allows you to add a new edit news maker name listener, and decide what actions
	 * should be taken on that object.
	 * </P>
	 *
	 */
	public class EditNewsMakerNameListener implements ActionListener {

		/**
		 * This method allows decides what actions should be taken when an event occurs.
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			//change name of news maker in data base
			newsDataBaseModel.getNewsMakerListModel().get(editNewsMakerView.newsMakerModel).setName(editNewsMakerView.jtfName.getText());
			//for each story in the data base
			for(int i = 0; i < newsDataBaseModel.getNewsStoryListModel().size(); i++) {
				if(newsDataBaseModel.getNewsStoryListModel().get(i).getNewsMaker1().equals(editNewsMakerView.newsMakerModel.getName())) {
					//change name of all news stories containing that newsmaker
					newsDataBaseModel.getNewsStoryListModel().get(i).getNewsMaker1().setName(editNewsMakerView.jtfName.getText());
				}
				if(newsDataBaseModel.getNewsStoryListModel().get(i).getNewsMaker2().equals(editNewsMakerView.newsMakerModel.getName())) {
					newsDataBaseModel.getNewsStoryListModel().get(i).getNewsMaker2().setName(editNewsMakerView.jtfName.getText());
				}
				
			}
		}
		
	}
	/**
	 * <P>
	 * This class allows you to add a new remove newsmaker from story listener, and decide what actions
	 * should be taken on that object.
	 * </P>
	 *
	 */
	public class RemoveNewsMakerFromNewsStoriesListener implements ActionListener {

		/**
		 * This method allows decides what actions should be taken when an event occurs.
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			//the indices of the selected stories
			int[] selectedStoriesIndices = editNewsMakerView.getSelectedNewsStoryIndices();
			ArrayList<NewsStory> storiesToRemove = new ArrayList<NewsStory>();
			//for all the stories selected
			for(int i = 0; i < selectedStoriesIndices.length; i++) {
				
				//the story to remove
				storiesToRemove.add(editNewsMakerView.newsMakerModel.getNewsStoryListModel().get(selectedStoriesIndices[i]));
		
				//for each story in the database
				for(int j = 0; j < newsDataBaseModel.getNewsStoryListModel().size(); j++) {
					//the data base story in that is selected
					NewsStory dataBaseStory = newsDataBaseModel.getNewsStoryListModel().getNewsStories().getElementAt(j);
					//if that story equals the one to remove
					if(dataBaseStory.equals(storiesToRemove.get(i))) {
						//if the first newsmaker in that story is the one we want to remove
						if(dataBaseStory.getNewsMaker1().equals(storiesToRemove.get(i).getNewsMaker1())) {
							//set the newsmaker to none
							editNewsMakerView.newsMakerModel.getNewsStoryListModel().get(selectedStoriesIndices[i]).setNewsMaker1(new NewsMakerModel("None"));
						}
						if(dataBaseStory.getNewsMaker2().equals(storiesToRemove.get(i).getNewsMaker2())) {
							editNewsMakerView.newsMakerModel.getNewsStoryListModel().get(selectedStoriesIndices[i]).setNewsMaker2(new NewsMakerModel("None"));
						} 
						
					} //end if data base story is equal
				} //end for each story in the data base
				 
			} //end for each selected indice
			
			//remove the story from the news maker model
			for(int i = 0; i < storiesToRemove.size(); i++) {
				newsDataBaseModel.getNewsMakerListModel().get(editNewsMakerView.newsMakerModel).getNewsStoryListModel().remove(storiesToRemove.get(i));
			}
			
		}
		
	}
	/**
	 * <P>
	 * This class allows you to add a new add edit story listener, and decide what actions
	 * should be taken on that object.
	 * </P>
	 *
	 */
	public class  AddEditNewsStoryListener implements ActionListener{

		/**
		 * This method allows decides what actions should be taken when an event occurs.
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			
			//get all the data from the fields
				int month = ((Month)addEditNewsStoryView.jcbNewsStoryMonth.getSelectedItem()).toInt();
				LocalDate date = LocalDate.of(Integer.parseInt(addEditNewsStoryView.jcbNewsStoryYear.getSelectedItem().toString()), 
						month, Integer.parseInt(addEditNewsStoryView.jcbNewsStoryDay.getSelectedItem().toString()));
				String sourceName = addEditNewsStoryView.jcbNewsStorySource.getSelectedItem().toString();
				int length = Integer.parseInt(addEditNewsStoryView.jftfNewsStoryLength.getText().replaceAll(",", ""));
				String topic = addEditNewsStoryView.jcbNewsStoryTopic.getSelectedItem().toString();
				String subject = addEditNewsStoryView.jcbNewsStorySubject.getSelectedItem().toString();
				String partOfDayString = addEditNewsStoryView.jcbNewsStoryPartOfDay.getSelectedItem().toString();
				PartOfDay partOfDay;
				if(partOfDayString.equals("Morning")) {
					partOfDay = PartOfDay.MORNING;
				}
				else if(partOfDayString.equals("Afternoon")) {
					partOfDay = PartOfDay.AFTERNOON;
				}
				else if(partOfDayString.equals("Evening")) {
					partOfDay = PartOfDay.EVENING;
				}
				else {
					partOfDay = PartOfDay.LATE_NIGHT;
				}
				NewsMakerModel newsMaker1;
				NewsMakerModel newsMaker2;
				if(addEditNewsStoryView.jcbNewsStoryNewsMaker1.getSelectedItem().toString() != null) {
					newsMaker1 = new NewsMakerModel(addEditNewsStoryView.jcbNewsStoryNewsMaker1.getSelectedItem().toString());
				}
				else {
					newsMaker1 = new NewsMakerModel();
				}
				if(addEditNewsStoryView.jcbNewsStoryNewsMaker2.getSelectedItem().toString() != null) {
					newsMaker2 = new NewsMakerModel(addEditNewsStoryView.jcbNewsStoryNewsMaker2.getSelectedItem().toString());
				}
				else {
					newsMaker2 = new NewsMakerModel();
				}
				
				

				//if it is an add news story view
				if(((JButton)e.getSource()).getText().equals("Add News Story")) {
					
					//if they want a news paper story
					if(addEditNewsStoryView.jcbNewsStoryType.getSelectedItem().toString().equals("Newspaper")) {
						System.out.println("Newspaper story added.");
						newsDataBaseModel.addNewsStory((new NewspaperStory(date, sourceName,length,topic, subject,
								newsMaker1, newsMaker2)));
						if(!newsDataBaseModel.containsNewsMakerModel(newsMaker1)) {
							newsDataBaseModel.addNewsMakerModel(newsMaker1);
						}
						if(!newsDataBaseModel.containsNewsMakerModel(newsMaker2)) {
							newsDataBaseModel.addNewsMakerModel(newsMaker2);
						}
						//add story to individual newsmakers
						newsDataBaseModel.getNewsMakerListModel().get(newsMaker1).addNewsStory(new NewspaperStory(date,sourceName,length,topic,subject,
								newsMaker1,newsMaker2));
						newsDataBaseModel.getNewsMakerListModel().get(newsMaker2).addNewsStory(new NewspaperStory(date,sourceName,length,topic,subject,
								newsMaker1,newsMaker2));
					}
					//if they want an online news story
					else if(addEditNewsStoryView.jcbNewsStoryType.getSelectedItem().toString().equals("Online Story")) {
						System.out.println("Online News story added.");
						//add to big list
						newsDataBaseModel.addNewsStory((new OnlineNewsStory(date, sourceName,length,topic, subject,partOfDay,
								newsMaker1, newsMaker2)));

						//if the model doesnt have these newsmakers, add them
						if(!newsDataBaseModel.containsNewsMakerModel(newsMaker1)) {
							newsDataBaseModel.addNewsMakerModel(newsMaker1);
						}
						if(!newsDataBaseModel.containsNewsMakerModel(newsMaker2)) {
							newsDataBaseModel.addNewsMakerModel(newsMaker2);
						}
						//add story to individual newsmakers
						newsDataBaseModel.getNewsMakerListModel().get(newsMaker1).addNewsStory((new OnlineNewsStory(date, sourceName,length,topic, subject,partOfDay,
								newsMaker1, newsMaker2)));
						newsDataBaseModel.getNewsMakerListModel().get(newsMaker2).addNewsStory((new OnlineNewsStory(date, sourceName,length,topic, subject,partOfDay,
								newsMaker1, newsMaker2)));
					}
					else {
						//if they want a tv news story
						System.out.println("TV News story added.");
						newsDataBaseModel.addNewsStory((new TVNewsStory(date, sourceName,length,topic, subject,partOfDay,
								newsMaker1, newsMaker2)));
						if(!newsDataBaseModel.containsNewsMakerModel(newsMaker1)) {
							newsDataBaseModel.addNewsMakerModel(newsMaker1);
						}
						if(!newsDataBaseModel.containsNewsMakerModel(newsMaker2)) {
							newsDataBaseModel.addNewsMakerModel(newsMaker2);
						}
						//add story to individual newsmakers
						newsDataBaseModel.getNewsMakerListModel().get(newsMaker1).addNewsStory((new TVNewsStory(date, sourceName,length,topic, subject,partOfDay,
								newsMaker1, newsMaker2)));
						newsDataBaseModel.getNewsMakerListModel().get(newsMaker2).addNewsStory((new TVNewsStory(date, sourceName,length,topic, subject,partOfDay,
								newsMaker1, newsMaker2)));
					}
					
				}
				//otherwise, if the view is an edit news story view
				else {
					
					//if they want a news paper story now
					if(addEditNewsStoryView.jcbNewsStoryType.getSelectedItem().toString().equals("Newspaper")) {
						System.out.println("Newspaper story added.");
						newsDataBaseModel.addNewsStory((new NewspaperStory(date, sourceName,length,topic, subject,
								newsMaker1, newsMaker2)));
						
						if(!newsDataBaseModel.containsNewsMakerModel(newsMaker1)) {
							newsDataBaseModel.addNewsMakerModel(newsMaker1);
						}
						if(!newsDataBaseModel.containsNewsMakerModel(newsMaker2)) {
							newsDataBaseModel.addNewsMakerModel(newsMaker2);
						}
						
						//add story to individual newsmakers
						newsDataBaseModel.getNewsMakerListModel().get(newsMaker1).addNewsStory((new TVNewsStory(date, sourceName,length,topic, subject,partOfDay,
								newsMaker1, newsMaker2)));
						newsDataBaseModel.getNewsMakerListModel().get(newsMaker2).addNewsStory((new TVNewsStory(date, sourceName,length,topic, subject,partOfDay,
								newsMaker1, newsMaker2)));
						
					}
					//if they want an online news story
					else if(addEditNewsStoryView.jcbNewsStoryType.getSelectedItem().toString().equals("Online Story")) {
						System.out.println("Online News story added.");

						newsDataBaseModel.addNewsStory((new OnlineNewsStory(date, sourceName,length,topic, subject,partOfDay,
								newsMaker1, newsMaker2)));
						
						if(!newsDataBaseModel.containsNewsMakerModel(newsMaker1)) {
							newsDataBaseModel.addNewsMakerModel(newsMaker1);
						}
						if(!newsDataBaseModel.containsNewsMakerModel(newsMaker2)) {
							newsDataBaseModel.addNewsMakerModel(newsMaker2);
						}
						
						//add story to individual newsmakers
						newsDataBaseModel.getNewsMakerListModel().get(newsMaker1).addNewsStory((new TVNewsStory(date, sourceName,length,topic, subject,partOfDay,
								newsMaker1, newsMaker2)));
						newsDataBaseModel.getNewsMakerListModel().get(newsMaker2).addNewsStory((new TVNewsStory(date, sourceName,length,topic, subject,partOfDay,
								newsMaker1, newsMaker2)));
					}
					//if they want a tv news story
					else {
						System.out.println("TV News story added.");
						newsDataBaseModel.addNewsStory((new TVNewsStory(date, sourceName,length,topic, subject,partOfDay,
								newsMaker1, newsMaker2)));
						
						if(!newsDataBaseModel.containsNewsMakerModel(newsMaker1)) {
							newsDataBaseModel.addNewsMakerModel(newsMaker1);
						}
						if(!newsDataBaseModel.containsNewsMakerModel(newsMaker2)) {
							newsDataBaseModel.addNewsMakerModel(newsMaker2);
						}
						
						//add story to individual newsmakers
						newsDataBaseModel.getNewsMakerListModel().get(newsMaker1).addNewsStory((new TVNewsStory(date, sourceName,length,topic, subject,partOfDay,
								newsMaker1, newsMaker2)));
						newsDataBaseModel.getNewsMakerListModel().get(newsMaker2).addNewsStory((new TVNewsStory(date, sourceName,length,topic, subject,partOfDay,
								newsMaker1, newsMaker2)));
					}
					
					
				}
				viewDialog.dispose();
		}

	}
	/**
	 * <code>MediaTypeSelectionListener</code> is an inner class (within
	 * <code>NewsController</code>) to listen for actions from a
	 * <code>MediaTypeSelectionView</code>. It has one method that does all the
	 * work -- the overridden <code>actionPerformed</code> method -- which is
	 * called when there is a relevant <code>actionEvent</code>.
	 * 
	 * @author Dean Hougen
	 * @version 1.0
	 *
	 */
	public class MediaTypeSelectionListener implements ActionListener {

		/**
		 * The overridden <code>actionPerformed</code> method that does all of
		 * the work. If the user indicates their selections are "OK" (the
		 * <code>actionCommand</code> is "OK"), it queries each
		 * <code>JCheckBox</code> of the <code>MediaTypeSelectionView</code> to
		 * see if it was selected. If so, it adds the corresponding
		 * <code>NewsMedia</code> type to the <code>selectedMediaTypes</code>
		 * list. If no check boxes were selected, it warns the user of this
		 * fact. Once it has extracted the relevant information from the
		 * <code>MediaTypeSelectionView</code>, it disposes of that view, which
		 * closes the modal window and allows the user to interact with other
		 * windows in <code>Nooz</code>.
		 */
		@Override
		public void actionPerformed(ActionEvent actionEvent) {
			selectedMediaTypes = new LinkedList<NewsMedia>();
			if ("OK".equals(actionEvent.getActionCommand())) {
				if (mediaTypeSelectionView.jcbNewspaper.isSelected()) {
					selectedMediaTypes.add(NewsMedia.NEWSPAPER);
				}
				if (mediaTypeSelectionView.jcbTVNews.isSelected()) {
					selectedMediaTypes.add(NewsMedia.TV);
				}
				if (mediaTypeSelectionView.jcbOnline.isSelected()) {
					selectedMediaTypes.add(NewsMedia.ONLINE);
				}
				if (null == selectedMediaTypes) {
					JOptionPane.showMessageDialog(selectionView, "No media type selected.", "Invalid Selection",
							JOptionPane.WARNING_MESSAGE);
				}
			}
			viewDialog.dispose();
		}
	}
	
	
}
