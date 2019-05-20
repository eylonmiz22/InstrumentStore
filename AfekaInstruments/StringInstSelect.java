//Eylon Mizrahi 206125411

import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public abstract class StringInstSelect extends AddInstrumentStage
{
	/**Fields *********************************************************************************************/
	//Number of strings label
	private Label numOfStringsLabel;
	
	//Number of strings text field
	private TextField numOfStringsText;
	/******************************************************************************************************/
	
	public StringInstSelect(AfekaInventory<MusicalInstrument> inventory, TextField headTypeText,
			  TextField headBrandText, TextField headPriceText, int index)
	{
		super(inventory, headTypeText, headBrandText, headPriceText, index);
		
		this.numOfStringsLabel = new Label("Number of Strings:");
		this.numOfStringsText = new TextField();
	}
	
	public Label getNumOfStringsLabel()
	{
		this.numOfStringsText.setPrefWidth(150);
		return this.numOfStringsLabel;
	}
	
	public TextField getNumOfStringsText() 
	{
		return this.numOfStringsText;
	}	
	
	public void setPromptNOS(String promptTxt)
	{
		this.numOfStringsText.setPromptText(promptTxt);
	}
	
	protected void isNumOfStringsEmpty()
	{
		if(this.numOfStringsText.getText().equals(""))			
		{
			throw new IllegalArgumentException("Please fill in all the fields!");
		}
	}
}
