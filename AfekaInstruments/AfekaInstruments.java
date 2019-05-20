//Eylon Mizrahi 206125411

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import javafx.application.Application;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextInputDialog;
import javafx.stage.Stage;

public class AfekaInstruments extends Application
{	
    public static void main(String[] args) throws Exception 
    {
    	launch(args);
    }
    
	@Override
	public void start(Stage primaryStage) throws Exception 
	{
		File file = getInstrumentsFileFromDialog();
		
		if(file != null)
		{
			AfekaInventory<MusicalInstrument> inventory =
			new AfekaInventory<MusicalInstrument>(new ArrayList<MusicalInstrument>(), 0, false);
			loadInstrumentsFromFile(file, inventory.getInsArr());
			
		    if(inventory.getInsArr().size() == 0)
		    {
		        	Alert alert = new Alert(AlertType.INFORMATION);
		        	alert.setTitle("Information Dialog");
		        	alert.setHeaderText(null);
		        	alert.setContentText("There are no instruments in the store currently");
		        	alert.showAndWait();
		            return;
		    }
		    
		    HeadWindow headPage = new HeadWindow(inventory);	    
		    headPage.getWindow().show();
		}
	}
	
    public File getInstrumentsFileFromDialog() 
    {
        boolean stopLoop = true;
		TextInputDialog fileInputDialog = new TextInputDialog();
		fileInputDialog.setTitle("Confirmation");
		fileInputDialog.setHeaderText("Load Instruments From File");
		fileInputDialog.setContentText("Please enter file name:");
    	Alert alert = new Alert(AlertType.ERROR);
    	alert.setTitle("Error");
    	alert.setHeaderText("File Error!");
    	alert.setContentText("Cannot read from file, please try again");
		Optional<String> result;
        File file;
        
        do 
        {
        	result = fileInputDialog.showAndWait();
        	if(result.isPresent() == false)
        	{
        		System.exit(0);
        	}
            file = new File(result.get());
            stopLoop = file.exists() && file.canRead();
            if(!stopLoop)
            {
            	alert.showAndWait();
            }
        }
        while(!stopLoop);

        return file;
    }
    
    public void loadInstrumentsFromFile(File file, ArrayList<MusicalInstrument> allInstruments)
    {
        Scanner scanner = null;
        
        try 
        {
            scanner = new Scanner(file);
            addAllInstruments(allInstruments ,loadGuitars(scanner));
            addAllInstruments(allInstruments ,loadBassGuitars(scanner));
            addAllInstruments(allInstruments ,loadFlutes(scanner));
            addAllInstruments(allInstruments ,loadSaxophones(scanner));
        }
        catch (InputMismatchException | IllegalArgumentException ex)
        {
        	Alert alert = new Alert(AlertType.ERROR);
        	alert.setTitle("Error");
        	alert.setHeaderText("Exception Error!");
        	alert.setContentText(ex.getMessage());
            System.exit(1);
        }
        catch (FileNotFoundException ex)
        {
        	Alert alert = new Alert(AlertType.ERROR);
        	alert.setTitle("Error Dialog");
        	alert.setHeaderText(null);
        	alert.setContentText("File Error! File was not found");
        	alert.showAndWait();
            System.exit(2);
        }
        finally
        {
            scanner.close();
        }
        System.out.println("\nInstruments loaded from file successfully!\n");
    }

    public ArrayList<MusicalInstrument> loadGuitars(Scanner scanner)
    {
        int numOfInstruments = scanner.nextInt();
        ArrayList<MusicalInstrument> guitars = new ArrayList<MusicalInstrument>(numOfInstruments);
        
        for(int i = 0; i < numOfInstruments ; i++)
        {
            guitars.add(new Guitar(scanner));
        }
        
        return guitars;
    }

    public ArrayList<MusicalInstrument> loadBassGuitars(Scanner scanner)
    {
        int numOfInstruments = scanner.nextInt();
        ArrayList<MusicalInstrument> bassGuitars = new ArrayList<MusicalInstrument>(numOfInstruments);
        
        for(int i = 0; i < numOfInstruments; i++)
        {
            bassGuitars.add(new Bass(scanner));
        }
        
        return bassGuitars;
    }

    public ArrayList<MusicalInstrument> loadFlutes(Scanner scanner)
    {
        int numOfInstruments = scanner.nextInt();
        ArrayList<MusicalInstrument> flutes = new ArrayList<MusicalInstrument>(numOfInstruments);
        
        for(int i = 0; i < numOfInstruments; i++)
        {
            flutes.add(new Flute(scanner));
        }
        
        return flutes;
    }

    public ArrayList<MusicalInstrument> loadSaxophones(Scanner scanner)
    {
        int numOfInstruments = scanner.nextInt();
        ArrayList<MusicalInstrument> saxophones = new ArrayList<MusicalInstrument>(numOfInstruments);
        
        for(int i = 0; i < numOfInstruments; i++)
        {
            saxophones.add(new Saxophone(scanner));
        }
        
        return saxophones;
    }

    public void addAllInstruments(ArrayList<MusicalInstrument> instruments,
    							  ArrayList<MusicalInstrument> moreInstruments)
    {
        for(int i = 0 ; i < moreInstruments.size() ; i++)
        {
            instruments.add(moreInstruments.get(i));
        }
    }

    public void printInstruments(ArrayList<MusicalInstrument> instruments)
    {
        for(int i = 0 ; i < instruments.size(); i++)
        {
            System.out.println(instruments.get(i));
        }
    }
}
