//Eylon Mizrahi 206125411

import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public class FluteSelect extends AddInstrumentStage
{
	/**Fields *********************************************************************************************/
	//Flute material label
	private Label materialLabel;
	
	//Flute material box
	private ComboBox<String> materialBox;
	
	//Flute type label
	private Label fluteTypeLabel;
	
	//Flute type box
	private ComboBox<String> typeBox;
	/******************************************************************************************************/
	
	public FluteSelect(AfekaInventory<MusicalInstrument> inventory, TextField headTypeText,
			  		   TextField headBrandText, TextField headPriceText, int index)
	{
		super(inventory, headTypeText, headBrandText, headPriceText, index);
		
		//Flute material label
		this.materialLabel = new Label("Material");
		
		//Flute type label
		this.fluteTypeLabel = new Label("Flute Type:");
		
		//ComboBoxes
		this.materialBox = new ComboBox<>();
		setFluteMaterialSelectTxt();
		this.typeBox = new ComboBox<>();
		setFluteTypeSelectTxt();
		
		//Setting the prompt texts.
		setPromptBrand("Ex: Levit");
		setPromptPrice("Ex: 300");
	}
	
	public void setFluteMaterialSelectTxt()
	{
		//Type ComboBox
		this.materialBox.setPromptText("Material");
		this.materialBox.getItems().addAll("Wood", "Metal", "Plastic");
	}
	
	public void setFluteTypeSelectTxt()
	{
		//Type ComboBox
		this.typeBox.setPromptText("Type");
		this.typeBox.getItems().addAll("Flute", "Recorder", "Bass");
	}
	
	public ComboBox<String> getMaterialBox()
	{
		return this.materialBox;
	}
	
	public ComboBox<String> getTypeBox()
	{
		return this.typeBox;
	}
	
	public void setMaterialBox(ComboBox<String> materialBox)
	{
		this.materialBox = materialBox;
	}
	
	public void setTypeBox(ComboBox<String> typeBox)
	{
		this.typeBox = typeBox;
	}
	
	public HBox getFlutePane()
	{
		getHeadBox().getChildren().clear();
		getLeftBox().getChildren().addAll(getBrandLabel(), getPriceLabel(),
										  this.materialLabel, this.fluteTypeLabel);
		
		getRightBox().getChildren().addAll(getBrandText(), getPriceText(), this.materialBox,
										   this.typeBox);
		
		getHeadBox().getChildren().addAll(getLeftBox(), getRightBox());
		return getHeadBox();
	}
}

