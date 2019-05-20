//Eylon Mizrahi 206125411

import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public class SaxoSelect extends AddInstrumentStage
{
	public SaxoSelect(AfekaInventory<MusicalInstrument> inventory, TextField headTypeText,
					  TextField headBrandText, TextField headPriceText, int index)
	{	
		super(inventory, headTypeText, headBrandText, headPriceText, index);
		
		//Setting the prompt texts.
		setPromptBrand("Ex: Jupiter");
		setPromptPrice("Ex: 300");
	}
	
	public HBox getSaxoPane()
	{
		getHeadBox().getChildren().clear();
		
		getLeftBox().getChildren().addAll(getBrandLabel(), getPriceLabel());
		
		getRightBox().getChildren().addAll(getBrandText(), getPriceText());
		
		getHeadBox().getChildren().addAll(getLeftBox(), getRightBox());
		
		return getHeadBox();
	}
}
