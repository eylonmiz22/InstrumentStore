//Eylon Mizrahi 206125411

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.InputMismatchException;
import javafx.animation.KeyFrame;
import javafx.animation.PathTransition;
import javafx.animation.Timeline;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

public class HeadWindow
{
	/**Fields *********************************************************************************************/
	private final int FIELDSWIDTH = 340;
	private int index;
	private AfekaInventory<MusicalInstrument> inventory;
	
	//Head textFields
	private TextField headTypeText, headBrandText, headPriceText;
	
	//Stage and pane
	private BorderPane headBPane;
	private Stage window;
	/******************************************************************************************************/
	
	public HeadWindow(AfekaInventory<MusicalInstrument> inventory)
	{
		HeadWindowBuilder(inventory);
		
		this.window = new Stage();
		this.window.setTitle("Afeka Instruments Music Store");
		this.headBPane = new BorderPane();
		
		//Panes
		setPanes(new BorderPane(), new GridPane(), new HBox(), new HBox(), new HBox(), new VBox(), new VBox());
		setTopHBoxChildren(new TextField(), new Button(), (HBox)this.headBPane.getTop());
		setCenterGridChildren((GridPane)this.headBPane.getCenter());
		setSideVBoxes((VBox)this.headBPane.getLeft(), (VBox)this.headBPane.getRight(),
					  new Button(), new Button());
		setBottomPaneTop(new Button(), new Button(), new Button(),
						 (HBox)((BorderPane)this.headBPane.getBottom()).getTop());
		
		Scene scene = new Scene(this.headBPane, 850, 300);
		this.window.setScene(scene);
	}
	
	private void HeadWindowBuilder(AfekaInventory<MusicalInstrument> inventory)
	{//TextFields, index and inventory setter.
		this.inventory = inventory;
		this.index = 0;
		
		//Text fields
		this.headTypeText = new TextField();
		this.headBrandText = new TextField();
		this.headPriceText = new TextField();
		
		this.headTypeText.setText(this.inventory.getInsArr().get(index).getClass().getCanonicalName());
		this.headBrandText.setText(this.inventory.getInsArr().get(index).getBrand());
		this.headPriceText.setText(this.inventory.getInsArr().get(index).getPrice().toString());
	}
	
	private HBox moveText()
	{//Creates the moving text in the bottom of the head page.
		Text text = new Text();
		LocalDate date = LocalDate.now();
		Timeline time = new Timeline( new KeyFrame(Duration.millis(1), e -> 
		{
			text.setText(date + " " + LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")) + " " +
					" Afeka Instruments Music Store $$$ ON SALE!!!" +
	 				" $$$ Guitars, Basses, Flutes, Saxophones and more!");
		}));
		time.setCycleCount(Timeline.INDEFINITE);
		time.play();
		text.setFill(Color.RED);
		text.setFont(Font.font("Courier", FontWeight.BOLD, 13));
		
		HBox hBox = new HBox(50);
		hBox.setPadding(new Insets(15, 15, 15, 15));
		hBox.getChildren().add(text);
		
		PathTransition animation = new PathTransition(Duration.seconds(10), new Line(900, 0, 0, 0), text);
		animation.setCycleCount(Timeline.INDEFINITE);
		animation.setAutoReverse(true);
		animation.play();
		text.setOnMouseEntered(e -> 
		{
			animation.pause();
			e.consume();
		});
		text.setOnMouseExited(e ->
		{
			animation.play();
		});
		return hBox;
	}
	
	public void setPanes(BorderPane bottomPane, GridPane centerGrid, HBox topHBox,
					 	  HBox bottomPaneTop, HBox bottomPaneBottom, VBox leftVBox, VBox rightVBox)
	{
		//BorderPanes
		this.headBPane.setPadding(new Insets(15, 15, 15, 15));
		bottomPane.setPadding(new Insets(15, 15, 15, 15));
	
		//Grid
		centerGrid.setPadding(new Insets(15, 15, 15, 15));
		centerGrid.setVgap(15);
		centerGrid.setHgap(15);
		centerGrid.setAlignment(Pos.CENTER);

		//HBoxes
		topHBox.setSpacing(15);
		bottomPaneTop.setSpacing(30);
		bottomPaneTop.setAlignment(Pos.CENTER);
		bottomPaneBottom = moveText();
		
		//VBoxes
		leftVBox.setSpacing(30);
		rightVBox.setSpacing(15);
		leftVBox.setAlignment(Pos.CENTER);
		rightVBox.setAlignment(Pos.CENTER);
		
		//Connecting the panes
		bottomPane.setTop(bottomPaneTop);
		bottomPane.setBottom(bottomPaneBottom);
		this.headBPane.setBottom(bottomPane);
		this.headBPane.setCenter(centerGrid);
		this.headBPane.setTop(topHBox);
		this.headBPane.setLeft(leftVBox);
		this.headBPane.setRight(rightVBox);
	}
	
	public void setTopHBoxChildren(TextField searchText, Button searchButton, HBox topHBox)
	{
		searchText.setPromptText("Search...");
		searchText.setPrefWidth(12);
		HBox.setHgrow(searchText, Priority.ALWAYS);
		topHBox.getChildren().add(searchText);
		searchButton = new Button("Go!");
		addGoButton(topHBox, searchText, searchButton);
	}
	
	public void addGoButton(HBox box, TextField searchText, Button goButton)
	{//Adds Go button into the program to search a text.
		box.getChildren().add(goButton);
		
		goButton.setOnAction(e -> 
		{
			goPress(searchText);
		});
		
		box.setOnKeyPressed(ke ->
		{
			if(ke.getCode() == KeyCode.ENTER)
			{
				goPress(searchText);
			}
		});
	}
	
	private void goPress(TextField searchText)
	{//Go button function for text searching
    	Alert alert = new Alert(AlertType.INFORMATION);
    	alert.setTitle("Information Dialog");
    	alert.setHeaderText("Search results:");
		String str = "";
		
		if(!searchText.getText().equals(""))
		{
			for(int i = 0; i < this.inventory.getInsArr().size(); i++)
			{
				if(this.inventory.getInsArr().get(i).toString().toLowerCase().contains(searchText.getText().toLowerCase()))
				{
					str += this.inventory.getInsArr().get(i) + "\n\n";
				}
			}
			if(!str.equals(""))
			{
            	alert.setContentText(str);
            	alert.showAndWait();
			}
			else
			{
            	alert.setContentText("No search results.");
            	alert.showAndWait();
			}
		}
	}
	
	public void setCenterGridChildren(GridPane centerGrid)
	{
		//Labels
		Label typeLabel = new Label("Type");
		GridPane.setConstraints(typeLabel, 0, 0);
		Label brandLabel = new Label("Brand");
		GridPane.setConstraints(brandLabel, 0, 1);
		Label priceLabel = new Label("Price");
		GridPane.setConstraints(priceLabel, 0, 2);
		
		//Fields
		setHeadField(this.headTypeText);
		GridPane.setConstraints(this.headTypeText, 1, 0);
		setHeadField(this.headBrandText);
		GridPane.setConstraints(this.headBrandText, 1, 1);
		setHeadField(this.headPriceText);
		GridPane.setConstraints(this.headPriceText, 1, 2);
		
		centerGrid.getChildren().addAll(typeLabel, brandLabel, priceLabel, this.headTypeText,
										this.headBrandText, this.headPriceText);
	}
	
	public void setHeadField(TextField field)
	{
		field.setPromptText("No Items");
		field.setPrefWidth(FIELDSWIDTH);
	}
	
	public void setSideVBoxes(VBox leftVBox, VBox rightVBox, Button leftArrowButton, Button rightArrowButton)
	{
		//Left arrow button
		leftArrowButton.setText("<");
		addLeftArrow(leftVBox, this.inventory.getInsArr(), leftArrowButton);
		this.headBPane.setLeft(leftVBox);
		
		//Right arrow button
		rightArrowButton.setText(">");
		addRightArrow(rightVBox, this.inventory.getInsArr(), rightArrowButton);
		this.headBPane.setRight(rightVBox);
	}
	
	public void addLeftArrow(VBox box, ArrayList<MusicalInstrument> allInstruments, Button leftArrowButton)			  
	{//adds the left arrow button into the head page. returns the updated index.
		box.getChildren().add(leftArrowButton);
		
		leftArrowButton.setOnAction(e1 ->
		{
			if(allInstruments.size() != 0)
			{
				if(index > 0)
				{
					index--;
					this.headTypeText.setText(allInstruments.get(index).getClass().getCanonicalName());
					this.headBrandText.setText(allInstruments.get(index).getBrand());
					String price = "" + allInstruments.get(index).getPrice();
					this.headPriceText.setText(price);
				}
				else
				{
					index = allInstruments.size() - 1;
					this.headTypeText.setText(allInstruments.get(index).getClass().getCanonicalName());
					this.headBrandText.setText(allInstruments.get(index).getBrand());
					String price = "" + allInstruments.get(index).getPrice();
					this.headPriceText.setText(price);
				}
			}
		});
	}
	
	public void addRightArrow(VBox box, ArrayList<MusicalInstrument> allInstruments, Button rightArrowButton)
	{//adds the right arrow button into the head page. returns the updated index.
		box.getChildren().add(rightArrowButton);
		
		rightArrowButton.setOnAction(e2 ->
		{
			if(allInstruments.size() != 0)
			{
				if(index < allInstruments.size() - 1)
				{
					index++;
					headTypeText.setText(allInstruments.get(index).getClass().getCanonicalName());
					headBrandText.setText(allInstruments.get(index).getBrand());
					String price = "" + allInstruments.get(index).getPrice();
					headPriceText.setText(price);
				}
				else
				{
					index = 0;
					headTypeText.setText(allInstruments.get(index).getClass().getCanonicalName());
					headBrandText.setText(allInstruments.get(index).getBrand());
					String price = "" + allInstruments.get(index).getPrice();
					headPriceText.setText(price);
				}
			}
		});
	}
	
	public void setBottomPaneTop(Button addButton, Button deleteButton, Button clearButton, HBox bottomPaneTop)
	{
		addButton.setText("Add");
		HBox hbox = new HBox();
		hbox.setAlignment(Pos.CENTER);
		addAddButton(bottomPaneTop, addButton);
		
		//Delete button
		deleteButton.setText("Delete");
		addDeleteButton(bottomPaneTop, deleteButton);
		
		//Clear button
		clearButton.setText("Clear");
		addClearButton(bottomPaneTop, clearButton);
	}
	
	public boolean isInsInArr()
	{//Checks by fields if there is such an instrument in the inventory.
		int i = 0;
		while(i < this.inventory.getInsArr().size())
		{
			if(this.inventory.getInsArr().get(i).getClass().getCanonicalName().equals(this.headTypeText.getText()) &&
			   this.inventory.getInsArr().get(i).getBrand().equals(this.headBrandText.getText()) &&
			   this.inventory.getInsArr().get(i).getPrice().doubleValue() == Double.parseDouble(this.headPriceText.getText()))
			{
				return true;
			}
			
			i++;
		}
		
		return false;
	}
	
	public void addDeleteButton(HBox box, Button deletelButton)
	{//Adds delete button into the program.
		box.getChildren().add(deletelButton);
		
		deletelButton.setOnAction(e -> 
		{
    		boolean removed = false;
    		
    		try 
    		{
        		if(removingTerms())
        	    {
        	    			if(!isInsInArr())
        	    			{
        	    				throw new InputMismatchException();
        	    			}
        	    			
        	        		removed = this.inventory.removeInstrument(this.inventory.getInsArr(),
        	        				  this.inventory.getInsArr().get(this.index));
        	    }
        		
        		if(removed)
        		{
        			if((this.index < this.inventory.getInsArr().size()) && (this.inventory.getInsArr().size() > 0))
        			{
        				nonEmptyInventorySetter();
        			}
        			else
        			{
        				emptyInventorySetter();
        			}
        		}
    		}
    		catch(Exception ex)
    		{
            	Alert alert = new Alert(AlertType.ERROR);
            	alert.setTitle("Invalid Input To Delete");
            	alert.setHeaderText(null);
            	alert.setContentText("please check again the fields, or if there is such an instrument in the inventory!");
            	alert.showAndWait();
    		}
		});
	}
	
	private boolean removingTerms()
	{//Checks if we can try to remove the current instrument from the inventory.
		if((this.index < this.inventory.getInsArr().size()) || (this.inventory.getInsArr().size() > 0) || 
     	   (this.headTypeText.getText().length() != 0) || (this.headBrandText.getText().length() != 0) || 
     	   (this.headPriceText.getText().length() != 0))
		{
			return true;
		}
		
		return false;
	}
	
	public void nonEmptyInventorySetter()
	{//Sets the page fields according to a non empty inventory.
		this.headTypeText.setText(this.inventory.getInsArr().get(this.index).getClass().getCanonicalName());
		this.headBrandText.setText(this.inventory.getInsArr().get(this.index).getBrand());
		String price = "" + this.inventory.getInsArr().get(this.index).getPrice();
		this.headPriceText.setText(price);
	}
	
	public void emptyInventorySetter()
	{//Sets the page fields according to an empty inventory.
		this.index = 0;
		this.headTypeText.setText("");
		this.headBrandText.setText("");
		this.headPriceText.setText("");
		this.headTypeText.setPromptText("No Items");
		this.headBrandText.setPromptText("No Items");
		this.headPriceText.setPromptText("No Items");
	}
	
	public void addClearButton(HBox box, Button cancelButton)
	{//adds cancel button into the program.
		box.getChildren().add(cancelButton);
		
    	cancelButton.setOnAction(e -> 
    	{
    		this.index = 0;
    		this.headTypeText.setText("");
    		this.headBrandText.setText("");
    		this.headPriceText.setText("");
    		this.headTypeText.setPromptText("No Items");
    		this.headBrandText.setPromptText("No Items");
    		this.headPriceText.setPromptText("No Items");
    		this.inventory.removeAll(this.inventory.getInsArr());
    	});
	}
	
	public void addAddButton(HBox box, Button addButton)
	{//Adds add button into the program.
		box.getChildren().add(addButton);
		AddInstrumentStage choosePage = new AddInstrumentStage(this.inventory, this.headTypeText, this.headBrandText,
															   this.headPriceText, this.index);
		addButton.setOnAction(e -> 
		{
			choosePage.getWindow().setScene(choosePage.getScene());
			choosePage.getWindow().show();
		});
		
		box.setOnKeyPressed(ke ->
		{
			if(ke.getCode() == KeyCode.A)
			{
				choosePage.getWindow().setScene(choosePage.getScene());
				choosePage.getWindow().show();
			}
		});
	}
	
	public Stage getWindow()
	{
		return this.window;
	}
	
    public int getIndex()
    {
    	return this.index;
    }
    
    public TextField getTypeText()
    {
  	  	return this.headTypeText;
    }
  
    public TextField getBrandText()
    {
    	return this.headBrandText;
    }
  
    public TextField getPriceText()
    {
    	return this.headPriceText;
    }
  
    public AfekaInventory<MusicalInstrument> getInventory()
    {
    	return this.inventory;
    }
  
    public void setIndex(int i)
    {
    	this.index = i;
    }
    
    public void setInventory(AfekaInventory<MusicalInstrument> inventory)
    {
    	this.inventory = inventory;
    }
  
    public void setTypeText(String str)
    {
    	this.headTypeText.setText(str);
    }
  
    public void setBrandText(String str)
    {
    	this.headBrandText.setText(str);
    }
  
    public void setPriceText(String str)
    {
    	this.headPriceText.setText(str);
    }
}
