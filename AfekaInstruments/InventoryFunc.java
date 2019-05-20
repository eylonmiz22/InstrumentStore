//Eylon Mizrahi 206125411

import java.util.ArrayList;

public interface InventoryFunc<T>
{
	public void addAllStringInstruments(ArrayList<T> insArr1, ArrayList<T> insArr2);
	public void addAllWindInstruments(ArrayList<T> insArr1, ArrayList<T> insArr2);
	public void SortByBrandAndPrice(ArrayList<T> insArr);
	public int binnarySearchByBrandAndPrice(ArrayList<T> insArr, String brand, Number price);
	public void addInstrument(ArrayList<T> insArr, T ins);
	public boolean removeInstrument(ArrayList<T> insArr, T ins);
	public boolean removeAll(ArrayList<T> insArr);
}
