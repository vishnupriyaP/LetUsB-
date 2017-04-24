import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

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
		//TODO delete this. This will be in nooz main file
		newsDataBaseModel  = new NewsDataBaseModel();
		selectionView = new SelectionView();
		setSelectionView(this.selectionView);
		
	}
	/**
	 * <P>
	 * This method sets the news data base model field.
	 * </P>
	 * @param newsDataBaseModel The news data base model to set.
	 */
	public void setNewsDataBaseModel(NewsDataBaseModel newsDataBaseModel) {
		this.newsDataBaseModel = newsDataBaseModel;
	}
	/**
	 * <P>
	 * This method sets the selection view.
	 * </P>
	 * @param selectionView The selection view to set.
	 */
	public void setSelectionView(SelectionView selectionView) {
		selectionView.registerNewsStoryMenuListener(new NewsStoryMenuListener());
		selectionView.registerNewsMakerMenuListener(new NewsMakerMenuListener());
		selectionView.registerFileMenuListener(new FileMenuListener());
		selectionView.registerDisplayMenuListener(new DisplayMenuListener());
		
		
	}
	/**
	 * <P>
	 * This method loads all the news data.
	 * </P>
	 */
	public void loadNewsData() {
		JFileChooser fc = new JFileChooser();
		fc.setDialogTitle("Open Binary Data File");
		int returnValue = fc.showOpenDialog(selectionView);
		if(returnValue == JFileChooser.APPROVE_OPTION) {
			System.out.println("Load Binary Data File");
			//TODO load data in
			fc.getSelectedFile();
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
			//TODO save data
			fc.getSelectedFile();
		}
	}
	/**
	 * <P>
	 * This method imports news stories.
	 * </P>
	 */
	private void importNoozStories() {
		
		//file chooser
		JFileChooser fc = new JFileChooser();
		fc.setDialogTitle("Open Text File(s)");

		//multiple files selection DISABLED. This causes problems with the for loop
		fc.setMultiSelectionEnabled(false);
		int returnValue = JFileChooser.APPROVE_OPTION;
		String newsStoryFilePath = "";
		String answer = "";
		
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
				//TODO get path will work??
				try {
					newsDataBaseModel.setNewsSourceMap(CodeFileProcessor.readCodeFile(fc.getSelectedFile().getPath()));
					System.out.println("SOURCES");
					for(String k : newsDataBaseModel.getNewsSourceMap().keySet()) System.out.println(k + " | " + newsDataBaseModel.getNewsSourceMap().get(k));
				} catch (IOException e) {
					e.printStackTrace();
				}

			}
			if(answer.equalsIgnoreCase("topic codes")) {

				try {
					newsDataBaseModel.setNewsTopicMap(CodeFileProcessor.readCodeFile(fc.getSelectedFile().getPath()));
					System.out.println("TOPICS");
					for(String k : newsDataBaseModel.getNewsTopicMap().keySet()) System.out.println(k + " | " + newsDataBaseModel.getNewsTopicMap().get(k));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
			if(answer.equalsIgnoreCase("subject codes")) {

				try {
					newsDataBaseModel.setNewsSubjectMap(CodeFileProcessor.readCodeFile(fc.getSelectedFile().getPath()));
					System.out.println("SUBJECTS");
					for(String k : newsDataBaseModel.getNewsSubjectMap().keySet()) System.out.println(k + " | " + newsDataBaseModel.getNewsSubjectMap().get(k));
				} catch (IOException e) {
					// TODO Auto-generated catch block
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
					//TODO get path will work??
					try {
						newsDataBaseModel.setNewsSourceMap(CodeFileProcessor.readCodeFile(fc.getSelectedFile().getPath()));
						System.out.println("SOURCES");
						for(String k : newsDataBaseModel.getNewsSourceMap().keySet()) System.out.println(k + " | " + newsDataBaseModel.getNewsSourceMap().get(k));
					} catch (IOException e) {
						e.printStackTrace();
					}

				}
				if(answer.equalsIgnoreCase("topic codes")) {

					try {
						newsDataBaseModel.setNewsTopicMap(CodeFileProcessor.readCodeFile(fc.getSelectedFile().getPath()));
						System.out.println("TOPICS");
						for(String k : newsDataBaseModel.getNewsTopicMap().keySet()) System.out.println(k + " | " + newsDataBaseModel.getNewsTopicMap().get(k));
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}
				if(answer.equalsIgnoreCase("subject codes")) {

					try {
						newsDataBaseModel.setNewsSubjectMap(CodeFileProcessor.readCodeFile(fc.getSelectedFile().getPath()));
						System.out.println("SUBJECTS");
						for(String k : newsDataBaseModel.getNewsSubjectMap().keySet()) System.out.println(k + " | " + newsDataBaseModel.getNewsSubjectMap().get(k));
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}
			}//end while incorrect input

		} // end while answer is approve
		try {
			 setNewsDataBaseModel(NoozFileProcessor.readNoozFile(newsStoryFilePath, 
					newsDataBaseModel.getNewsSourceMap(), newsDataBaseModel.getNewsTopicMap(), newsDataBaseModel.getNewsSubjectMap()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
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
		
	}
	/**
	 * <P>
	 * This method allows you to edit the news maker.
	 * </P>
	 * 
	 */
	private void editNewsMakers() {
		
	}
	/**
	 * <P>
	 * This method allows you to delete the news makers. 
	 * </P>
	 */
	private void deleteNewsMakers() {
		
	}
	/**
	 * <P>
	 * This method deletes the news maker list.
	 * </P>
	 */
	private void deleteNewsMakerList() {
		
	}
	/**
	 * <P>
	 * This method allows you to add a news story to the list.
	 * </P>
	 */
	private void addNewsStory() {
		
	}
	/**
	 * <P>
	 * This method allows you to edit the news stories.
	 * </P>
	 */
	private void editNewsStories() {
		
	}
	/**
	 * <P>
	 * This method allows you to sort the news stories.
	 * </P>
	 */
	private void sortNewsStories() {
		
	}
	/**
	 * <P>
	 * This method allows you to delete the news stories.
	 * </P>
	 */
	private void deleteNewsStories() {
		
	}
	/**
	 * <P>
	 * This method allows you to delete all news stories.
	 * </P>
	 */
	private void deleteAllNewsStories() {
		
	}
	/**
	 * <P>
	 * This method allows you to display pie charts.
	 * </P>
	 */
	private void displayPieCharts() {
		
	}
	/**
	 * <P>
	 * This method allows you to display text views.
	 * </P>
	 */
	private void displayTextViews() {
		
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
				loadNewsData();
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
	class NewsMakerMenuListener implements ActionListener {

		/**
		 * This method allows decides what actions should be taken when an event occurs.
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			String eventSourceText = ((JMenuItem) e.getSource()).getText();
			if(eventSourceText.equals("Add Newsmaker")) {
				System.out.println("Add newsmaker");
				
			}
			else if(eventSourceText.equals("Edit Newsmaker")) {
				System.out.println("edit newsmaker");
			}
			else if(eventSourceText.equals("Delete Newsmaker")) {
				System.out.println("delete newsmaker");
			}
			else if(eventSourceText.equals("Delete Newsmaker List")) {
				System.out.println("delete newsmaker list");
			}
			// TODO Auto-generated method stub
			
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
				System.out.println("Add News Story");
			}
			else if(eventSourceText.equals("Edit News Story")) {
				System.out.println("edit News Story");
			}
			else if(eventSourceText.equals("Sort News Stories")) {
				System.out.println("Sort News Stories");
			}
			else if(eventSourceText.equals("Delete News Story")) {
				System.out.println("Delete News Story");
			}
			else if(eventSourceText.equals("Delete All News Stories")) {
				System.out.println("Delete All News Stories");
			}
			// TODO Auto-generated method stub
			//create if statements to determine what component at the specified menu location has fired the event.
			
		}
		
	}
	/**
	 * <P>
	 * This class allows you to add a new display menu listener, and decide what actions
	 * should be taken on that object.
	 * </P>
	 *
	 */
	class DisplayMenuListener implements ActionListener {

		/**
		 * This method allows decides what actions should be taken when an event occurs.
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			String eventSourceText = ((JMenuItem) e.getSource()).getText();
			if(eventSourceText.equals("Text")) {
				System.out.println("Text");
			}
			else if(eventSourceText.equals("Pie Chart")) {
				System.out.println("Pie Chart");
			}
			// TODO Auto-generated method stub
			
		}
		
	}
	/**
	 * <P>
	 * This class allows you to add a new edit news maker name listener, and decide what actions
	 * should be taken on that object.
	 * </P>
	 *
	 */
	class EditNewsMakerNameListener implements ActionListener {

		/**
		 * This method allows decides what actions should be taken when an event occurs.
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}
	/**
	 * <P>
	 * This class allows you to add a new remove newsmaker from story listener, and decide what actions
	 * should be taken on that object.
	 * </P>
	 *
	 */
	class RemoveNewsMakerFromNewsStoriesListener implements ActionListener {

		/**
		 * This method allows decides what actions should be taken when an event occurs.
		 */
		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			
		}
		
	}
	/**
	 * <P>
	 * This class allows you to add a new add edit story listener, and decide what actions
	 * should be taken on that object.
	 * </P>
	 *
	 */
	class  AddEditNewsStoryListener implements ActionListener{

		/**
		 * This method allows decides what actions should be taken when an event occurs.
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}
	/**
	 * <P>
	 * This class allows you to add a new media type selection listener, and decide what actions
	 * should be taken on that object.
	 * </P>
	 *
	 */
	class MediaTypeSelectionListener implements ActionListener {

		/**
		 * This method allows decides what actions should be taken when an event occurs.
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	public static void main(String[] args) {
		new NewsController();
	}
	
	
}
