//Eylon Mizrahi 206125411

import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public class GuitarSelect extends StringInstSelect
{
	/**Fields *********************************************************************************************/
	//Guitar type label
	private Label guitarType;
	
	//Guitar type box
	private ComboBox<String> guitarTypeBox;
	/******************************************************************************************************/
	
	public GuitarSelect(AfekaInventory<MusicalInstrument> inventory, TextField headTypeText,
			  			TextField headBrandText, TextField headPriceText, int index)
	{
		super(inventory, headTypeText, headBrandText, headPriceText, index);
		
		//Guitar type label
		this.guitarType = new Label("Guitar Type:");
		
		//Setting the prompt texts
		setPromptBrand("Ex: Gibson");
		setPromptPrice("Ex: 7500");
		setPromptNOS("Ex: 6");
		
		//Guitar type box
		this.setGuitarTypeBox(new ComboBox<String>());
		setGuitarTypeSelectTxt();
		
		//setAddButton();
	}
	
	public void setGuitarTypeSelectTxt()
	{
		//Type ComboBox
		this.getGuitarTypeBox().setPromptText("Type");
		this.getGuitarTypeBox().getItems().addAll("Classic", "Acoustic", "Electric");
	}
	
	public void setGuitarTypeBox(ComboBox<String> guitarTypeBox) 
	{
		this.guitarTypeBox = guitarTypeBox;
	}
	
	public ComboBox<String> getGuitarTypeBox() 
	{
		return guitarTypeBox;
	}
	
	public HBox getGuitarPane()
	{	
		getHeadBox().getChildren().clear();
		
		getLeftBox().getChildren().addAll(getBrandLabel(), getPriceLabel(), getNumOfStringsLabel(), this.guitarType);
		
		getRightBox().getChildren().addAll(getBrandText(), getPriceText(), getNumOfStringsText(), this.getGuitarTypeBox());
		
		getHeadBox().getChildren().addAll(getLeftBox(), getRightBox());
		return getHeadBox();
	}
}

