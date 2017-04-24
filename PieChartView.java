import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;



/**
 * Project 4, CS 2334, Section 010, April 22, 2017
 * <P>
 * This class provides the view to see a pie chart on a newsmaker.
 * </P>
 * <P>
 * Note that the this class contains a pie chart and various methods used to construct the title,
 * wedges, and handle any actions performed by the user. This class also
 * contains a reference to the model, which will be used to pull and display data from. 
 * </P>
 * 
 * @author Dean Hougen, Jered Little, Vishnupriya Parasaram, Jessica Horner, and Zakary Koskovich 
 * @version 1.0
 * 
 */
//Jered Little created the stub code for this class.
public class PieChartView implements ActionListener {

	
	/** This variable contains the pie chart to display to the user. */
	private PieChart pieChart;
	/** This variable contains a reference to the model */
	private NewsMakerModel newsMakerModel;
	/** This variable keeps track of what media to display. */
	private String media;
	/** This variable keeps track of what content to display. */
	private String content;
	/** This variable keeps track of what measure to display. */
	private String measure;
	
	/**
	 * <P>
	 * This constructor sets all the variables, and constructs the title. (//TODO what will this do)
	 * </P>
	 * @param newsMakerModel The model to reference.
	 * @param media The media to display in the chart.
	 * @param content The content to display in the chart.
	 * @param measure The measure to display in the chart.
	 */
	public PieChartView(NewsMakerModel newsMakerModel, String media, String content, String measure) {
		
		//TODO constructor
	}
	
	/**
	 * <P>
	 * This method constructs the title for the pie chart.
	 * </P>
	 * @return The title of the pie chart.
	 */
	private String constructTitle() {
		return null;
		
	}
	
	/**
	 * <P>
	 * This method constructs the wedges for the pie chart.
	 * </P>
	 * @return The wedges for the pie chart.
	 */
	private List<Wedge> constructWedges() {
		return null;
		
	}
	
	/**
	 * <P>
	 * This method handles when an action is performed by updating the pie chart.
	 * </P>
	 * @param actionEvent The event that happened.
	 */
	public void actionPerformed(ActionEvent actionEvent) {
		
	}
	
}
