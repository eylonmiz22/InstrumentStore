//Eylon Mizrahi 206125411

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.control.CheckBox;

public class BassSelect extends StringInstSelect
{
	/**Fields *********************************************************************************************/
	//Fretless label
	private Label fretlessLabel;
	
	//Fretless box
	private CheckBox fretlessBox;
	/******************************************************************************************************/
	
	public BassSelect(AfekaInventory<MusicalInstrument> inventory, TextField headTypeText,
			  		  TextField headBrandText, TextField headPriceText, int index)
	{
		super(inventory, headTypeText, headBrandText, headPriceText, index);
		
		//Fretless label
		this.fretlessLabel = new Label("Fretless:");
		
		//Setting the prompt texts.
		setPromptBrand("Ex: Fender Jazz");
		setPromptPrice("Ex: 7500");
		setPromptNOS("Ex: 4");
		
		//Bass type box
		this.fretlessBox = new CheckBox();
	}
	
	public CheckBox getFretlessBox()
	{
		return this.fretlessBox;
	}
	
	public void setFretlessBox(CheckBox fretlessBox)
	{
		this.fretlessBox = fretlessBox;
	}
	
	public HBox getBassPane()
	{
		getHeadBox().getChildren().clear();
		getLeftBox().getChildren().addAll(getBrandLabel(), getPriceLabel(),
					 					  getNumOfStringsLabel(), this.fretlessLabel);
		
		getRightBox().getChildren().addAll(getBrandText(), getPriceText(), getNumOfStringsText(),
										   this.fretlessBox);
		
		getHeadBox().getChildren().addAll(getLeftBox(), getRightBox());
		
		return getHeadBox();
	}
}
