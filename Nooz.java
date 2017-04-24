import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Map;
import java.util.TreeMap;

/**
 * Project 4, CS 2334, Section 010, April 23, 2017
 * <P>
 * Nooz is the driver class for Nooz, a simple newspaper story data system.
 * Because user interaction with the date is focused on news makers, the primary
 * data structure used by the driver is a <code>NewsMakerList</code>, which is
 * used within <code>main</code>.
 * </P>
 *
 * @author Dean Hougen, Jered Little, Vishnupriya Parasaram, Jessica Horner, and Zakary Koskovich 
 * @version 4.0
 */
//Jered Little modified the stub code for this class.
class Nooz {
	/** The model. */
	private static NewsDataBaseModel newsDataBaseModel;
	/** The view. */
	private static SelectionView selectionView;
	/** The controller. */
	private static NewsController newsController;
	
	/**
	 * <P>
	 * This method starts the program, by setting the model, view, and controller.
	 * @param args The program arguments.
	 */
	public static void main(String[] args) {
	
		//TODO set the model, view, and controller
	}
}
