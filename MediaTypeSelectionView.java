

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


/**
 * Project 4, CS 2334, Section 010, April 22, 2017
 * <P>
 * This class provides the view to add and/or edit a news story.
 * </P>
 * <P>
 * Note that the this class contains check boxes, panels, and buttons for all the necessary components for a media type view.
 * This includes fields for the stories, yes/no buttons, and panels. Note that this class DOES NOT contain a reference
 * to the model.
 * </P>
 * 
 * @author Dean Hougen, Jered Little, Vishnupriya Parasaram, Jessica Horner, and Zakary Koskovich 
 * @version 1.0
 * 
 */

//Jered Little created the stub code & view display for this class.
public class MediaTypeSelectionView extends JPanel{

	
	/**
	 * The serialId for the media type selection view.
	 */
	private static final long serialVersionUID = -8727419379400336434L;
	/** This variable contains the check box for the news paper media type. */
	JCheckBox jcbNewspaper;
	/** This variable contains the check box for the TV news story media type. */
	JCheckBox jcbTVNews;
	/** This variable contains the check box for the online news story media type. */
	JCheckBox jcbOnline;
	/** This variable is the label that contains the graphing question statement. */
	private JLabel jlblMediaType;
	/** This variable is just a blank button. */
	private JButton jbBlank;
	/** This variable is the cancel button. */
	private JButton jbCancel; 
	/** This variable is the okay button. */
	private JButton jbOkay;
	/** This variable is the panel for the okay, cancel, and blank buttons. */
	private JPanel jpCompletionButtons;
	/** This variable is the panel for the media type buttons. */
	private JPanel jpMediaType;
	
	
	/**
	 * <P>
	 * This constructor initializes all the fields for a media type selection view,
	 * and puts them in their appropriate places in the panel.
	 * </P>
	 */
	public MediaTypeSelectionView() {
		//TODO
		this.setLayout(new BoxLayout(this,BoxLayout.PAGE_AXIS));
		
		jcbNewspaper = new JCheckBox("Newspaper Story");
		jcbTVNews = new JCheckBox("TV News Story");
		jcbOnline = new JCheckBox("Online News Story");
		jlblMediaType = new JLabel("Graph news stories from which media type(s)?");
		jbBlank = new JButton();
		jbOkay = new JButton("Okay");
		jbCancel = new JButton("Cancel");
		//not visible TODO why do we need this?
		jbBlank.setVisible(false);
		
		jpCompletionButtons = new JPanel();
		jpCompletionButtons.setLayout(new BoxLayout(jpCompletionButtons,BoxLayout.LINE_AXIS));
		jpCompletionButtons.add(jbBlank);
		jpCompletionButtons.add(Box.createHorizontalGlue());
		jpCompletionButtons.add(jbCancel);
		jpCompletionButtons.add(jbOkay);
		
		jpMediaType = new JPanel();
		jpMediaType.setLayout(new BoxLayout(jpMediaType,BoxLayout.PAGE_AXIS));
		jpMediaType.add(jlblMediaType);
		jpMediaType.add(jcbNewspaper);
		jpMediaType.add(jcbTVNews);
		jpMediaType.add(jcbOnline);
		
		this.add(jpMediaType);
		this.add(jpCompletionButtons);
		
		
		
	}

	//TODO TEST DELETE
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.add(new MediaTypeSelectionView());
		frame.setVisible(true);
		frame.setSize(360, 155);
	}
	
	
}
