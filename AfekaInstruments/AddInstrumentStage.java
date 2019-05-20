//Eylon Mizrahi 206125411

import java.util.InputMismatchException;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AddInstrumentStage
{
/**Fields *********************************************************************************************/
	//Stage scene and panes
	private Stage window;
	private Scene scene;
	private BorderPane bPane;
	private VBox leftBox, rightBox;
	private HBox topBox, bottomBox, headbox;
	
	//select type box
	private ComboBox<String> selectType;
	
	//Add button
	private Button addButton;
	
	//Labels
	private Label brandLabel;
	private Label priceLabel;
	
	//TextFields
	private TextField brandText;
	private TextField priceText;
/******************************************************************************************************/

	public AddInstrumentStage(AfekaInventory<MusicalInstrument> inventory, TextField headTypeText,
							  TextField headBrandText, TextField headPriceText, int index)
	{
		//Head Combo Box
		setSelectType(inventory, headTypeText, headBrandText, headPriceText, index);
		
		//Add button
		setAddButton(inventory, headTypeText, headBrandText, headPriceText, index);
			
		//Labels
		this.brandLabel = new Label("Brand:");
		this.priceLabel = new Label("Price:");
				
		//TextFields
		this.brandText = new TextField();
		this.priceText = new TextField();
	
		//Scene and panes
		this.topBox = new HBox();
		this.bottomBox = new HBox();
		setTopBox();
		setBottomBox();
		this.bPane = new BorderPane();
		this.bPane.setPadding(new Insets(40, 40, 40, 40));
		this.bPane.setTop(this.topBox);
		this.scene = new Scene(this.bPane, 450, 350);
		setWindow();
		setInsScene();
	}
	
	public Stage getWindow()
	{
		return this.window;
	}
	
	public Scene getScene()
	{
		return this.scene;
	}
	
	public Label getBrandLabel() 
	{
		return this.brandLabel;
	}
	
	public Label getPriceLabel() 
	{
		return this.priceLabel;
	}

	public Button getAddButton() 
	{
		return this.addButton;
	}
	
	public TextField getBrandText()
	{
		return this.brandText;
	}
	
	public TextField getPriceText()
	{
		return this.priceText;
	}
	
	public BorderPane getBPane()
	{
		return this.bPane;
	}
	
	public HBox getTopBox()
	{
		return this.topBox;
	}
	
	public HBox getHeadBox()
	{
		return this.headbox;
	}
	
	public VBox getLeftBox()
	{
		return this.leftBox;
	}
	
	public VBox getRightBox()
	{
		return this.rightBox;
	}
	
	public void setTopBox(HBox topBox)
	{
		this.topBox = topBox;
	}
	
	public void setHeadBox(HBox headBox)
	{
		this.headbox = headBox;
	}
	
	public void setLeftBox(VBox leftBox)
	{
		this.leftBox = leftBox;
	}
	
	public void setRightBox(VBox rightBox)
	{
		this.rightBox = rightBox;
	}
	
	public void setPromptBrand(String promptTxt)
	{
		this.brandText.setPromptText(promptTxt);
	}
	
	public void setPromptPrice(String promptTxt)
	{
		this.priceText.setPromptText(promptTxt);
	}
	
	public void setSelectTypeValue(String str)
	{
		this.selectType.setValue(str);
	}
	
	public void setInsScene()
	{
		this.leftBox = new VBox(27);
		this.rightBox = new VBox(15);
		this.headbox = new HBox(15);
		this.headbox.setPadding(new Insets(20, 20, 20, 20));
		this.headbox.setAlignment(Pos.CENTER);
		this.headbox.getChildren().addAll(this.leftBox,this.rightBox);
	}
	
	protected void alertCreator(String exMessage)
	{
    	Alert alert = new Alert(AlertType.ERROR);
    	alert.setTitle("Error");
    	alert.setHeaderText("Invalid Input");
    	alert.setContentText(exMessage);
    	alert.showAndWait();
	}
	
	public void headPageSetter(AfekaInventory<MusicalInstrument> inventory, TextField headTypeText,
			  				   TextField headBrandText, TextField headPriceText, int index)
	{
		index = inventory.getInsArr().size() - 1;
		headTypeText.setText(inventory.getInsArr().get(index).getClass().getCanonicalName());
		headBrandText.setText(this.brandText.getText());
		headPriceText.setText(this.priceText.getText());
	}
	public void setTopBox()
	{
		this.topBox = new HBox(15); 
		this.topBox.getChildren().add(this.selectType);
		this.topBox.setAlignment(Pos.CENTER);
	}
	
	public void setBottomBox()
	{
		this.bottomBox = new HBox(15); 
		this.bottomBox.setAlignment(Pos.CENTER);
	}
	
	public void setWindow()
	{
		this.window = new Stage();
		this.window.setTitle("Afeka instruments- Add new instrument");
		this.window.setScene(this.scene);
	}
	
	public void setAddButton(AfekaInventory<MusicalInstrument> inventory, TextField headTypeText,
			  				 TextField headBrandText, TextField headPriceText, int index)
	{//Builds the add button to add an instrument into the inventory.
		this.addButton = new Button("Add");
		getAddButton().setOnAction(e ->
		{
			try
			{
				if((this.priceText.getText().equals("")) || (this.brandText.getText().equals("")))
				{
					throw new IllegalArgumentException("Please fill in all the fields!");
				}
				
				switch (this.selectType.getValue())
				{
					case "Guitar": 
						addGuitar(inventory, headTypeText, headBrandText, headPriceText, index);
						break;
					case "Bass":
						addBass(inventory, headTypeText, headBrandText, headPriceText, index);
						break;
					case "Flute":
						addFlute(inventory, headTypeText, headBrandText, headPriceText, index);
						break;
					case "Saxophone":
						addSaxo(inventory, headTypeText, headBrandText, headPriceText, index);
						break;
				}
			}
			catch(IllegalArgumentException ex)
			{
				alertCreator(ex.getMessage());
			}
		});
	}
	
	public void addGuitar(AfekaInventory<MusicalInstrument> inventory, TextField headTypeText,
			  			  TextField headBrandText, TextField headPriceText, int index)
	{//Adds guitar to the inventory.
		try
		{
			((GuitarSelect)this).isNumOfStringsEmpty();
			
			if(((GuitarSelect)this).getGuitarTypeBox().getSelectionModel().isEmpty())
			{				
				throw new IllegalArgumentException("Please select a type!");
			}
			
			inventory.addInstrument(inventory.getInsArr(), new Guitar(getBrandText().getText(),
										 Double.parseDouble(getPriceText().getText()),
										 Integer.parseInt(((GuitarSelect)this).getNumOfStringsText().getText()),
										 ((GuitarSelect)this).getGuitarTypeBox().getValue()));
			
			headPageSetter(inventory, headTypeText, headBrandText, headPriceText, index);
		}
		catch(InputMismatchException | IllegalArgumentException ex)
		{
			alertCreator(ex.getMessage());
		}
	}
	
	public void addBass(AfekaInventory<MusicalInstrument> inventory, TextField headTypeText,
			  		    TextField headBrandText, TextField headPriceText, int index)
	{//Adds bass to the inventory.
		try
		{
			((BassSelect)this).isNumOfStringsEmpty();
			
			inventory.addInstrument(inventory.getInsArr(), new Bass(getBrandText().getText(),
									Double.parseDouble(getPriceText().getText()),
									Integer.parseInt(((BassSelect)this).getNumOfStringsText().getText()),
									((BassSelect)this).getFretlessBox().isSelected()));
			
			headPageSetter(inventory, headTypeText, headBrandText, headPriceText, index);
		}
		catch(InputMismatchException | IllegalArgumentException ex)
		{
			alertCreator(ex.getMessage());
		}
	}
	
	public void addFlute(AfekaInventory<MusicalInstrument> inventory, TextField headTypeText,
						 TextField headBrandText, TextField headPriceText, int index)
	{//Adds flute to the inventory.
		try
		{
			if(((FluteSelect)this).getTypeBox().getSelectionModel().isEmpty())
			{
				throw new IllegalArgumentException("Please select a type!");
			}
			else if(((FluteSelect)this).getMaterialBox().getSelectionModel().isEmpty())
			{
				throw new IllegalArgumentException("Please select a material!");
			}
			
			inventory.addInstrument(inventory.getInsArr(), new Flute(getBrandText().getText(),
										 Double.parseDouble(getPriceText().getText()),
										 ((FluteSelect)this).getMaterialBox().getValue(),
										 ((FluteSelect)this).getTypeBox().getValue()));
			
			headPageSetter(inventory, headTypeText, headBrandText, headPriceText, index);
		}
		catch(InputMismatchException | IllegalArgumentException ex)
		{
			alertCreator(ex.getMessage());
		}
	}
	
	public void addSaxo(AfekaInventory<MusicalInstrument> inventory, TextField headTypeText,
						TextField headBrandText, TextField headPriceText, int index)
	{//Adds saxophone to the inventory.
		try
		{
			inventory.addInstrument(inventory.getInsArr(), new Saxophone(getBrandText().getText(),
									Double.parseDouble(getPriceText().getText())));
			
			headPageSetter(inventory, headTypeText, headBrandText, headPriceText, index);
		}
		catch(InputMismatchException | IllegalArgumentException ex)
		{
			alertCreator(ex.getMessage());
		}
	}
	
	private void switchInsPage(AddInstrumentStage insPage, AfekaInventory<MusicalInstrument> inventory, TextField headTypeText,
			  				   TextField headBrandText, TextField headPriceText, int index)
	{//Switch the page by the comboBox of type selection.
		this.bottomBox.getChildren().clear();
		insPage.setSelectTypeValue(this.selectType.getValue());
		insPage.setAddButton(inventory, headTypeText, headBrandText, headPriceText, index);
		this.bottomBox.getChildren().add(insPage.getAddButton());
		
		if(insPage instanceof GuitarSelect)
		{
			this.bPane.setCenter(((GuitarSelect)insPage).getGuitarPane());
		}
		
		if(insPage instanceof BassSelect)
		{
			this.bPane.setCenter(((BassSelect)insPage).getBassPane());
		}
		
		if(insPage instanceof FluteSelect)
		{
			this.bPane.setCenter(((FluteSelect)insPage).getFlutePane());
		}
		
		if(insPage instanceof SaxoSelect)
		{
			this.bPane.setCenter(((SaxoSelect)insPage).getSaxoPane());
		}
		
		this.bPane.setBottom(this.bottomBox);
	}
	
	public void setSelectType(AfekaInventory<MusicalInstrument> inventory, TextField headTypeText,
							  TextField headBrandText, TextField headPriceText, int index)
	{//Builds comboBox for Selecting instruments type page
		
		this.selectType = new ComboBox<String>();
		this.selectType.getItems().addAll("Guitar", "Bass", "Flute", "Saxophone");
		this.selectType.setPromptText("Choose Instrument Type Here");
		this.selectType.setOnAction(e ->
		{
			switch (this.selectType.getValue())
			{
				case "Guitar":
					GuitarSelect gPage = new GuitarSelect(inventory, headTypeText, headBrandText, headPriceText, index);
					switchInsPage(gPage, inventory, headTypeText, headBrandText, headPriceText, index);
					break;
				case "Bass":
					BassSelect bPage = new BassSelect(inventory, headTypeText, headBrandText, headPriceText, index);
					switchInsPage(bPage, inventory, headTypeText, headBrandText, headPriceText, index);
					break;
				case "Flute":
					FluteSelect fPage = new FluteSelect(inventory, headTypeText, headBrandText, headPriceText, index);
					switchInsPage(fPage, inventory, headTypeText, headBrandText, headPriceText, index);
					break;
				case "Saxophone":	
					SaxoSelect sPage = new SaxoSelect(inventory, headTypeText, headBrandText, headPriceText, index);
					switchInsPage(sPage, inventory, headTypeText, headBrandText, headPriceText, index);
					break;
			}
		});
	}
}
