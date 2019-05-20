//Eylon Mizrahi 206125411

import java.util.ArrayList;

public class AfekaInventory<T> implements InventoryFunc<T> 
{
	private ArrayList<T> insArr;
	private double price;
	private boolean isSorted;
	
	public AfekaInventory(ArrayList<T> insArr, double price, boolean isSorted)
	{
		this.insArr = insArr;
		this.price = price;
		this.isSorted = isSorted;
	}
	
	public AfekaInventory(ArrayList<T> insArr)
	{
		this.insArr = insArr;
		setInsPrice(insArr);
		isSortedIns(insArr);
	}
	
	public ArrayList<T> getInsArr()
	{
		return this.insArr;
	}
	
	public double getPrice()
	{
		return this.price;
	}
	
	public boolean getIsSorted()
	{
		return this.isSorted;
	}
	
	public void setInsArr(ArrayList<T> insArr)
	{
		this.insArr = insArr;
		
	}
	
	public void setPrice(double price)
	{
		this.price = price;
	}
	
	public void setInsPrice(ArrayList<T> insArr)
	{
		if(isMusicalInsArr(insArr))
		{
			this.price = 0;
			for(int i = 0; i < insArr.size(); i++)
			{
				this.price += ((MusicalInstrument)insArr.get(i)).getPrice().doubleValue();
			}
		}
	}
	
	public void setIsSorted(boolean isSorted)
	{
		this.isSorted = isSorted;
	}
	
	public boolean isSortedIns(ArrayList<T> insArr)
	{
		if(isMusicalInsArr(insArr))
		{
			boolean helper = true;
	       	for(int i = 0; i < insArr.size() - 1; i++)
	       	{
	       		if(((MusicalInstrument)insArr.get(i)).compareTo((MusicalInstrument)insArr.get(i + 1)) > 0)
	       		{
	       			helper = false;
	       			break;
	           	}
	        }
	       	return helper;
		}
		return false;
	}
	
	public void addAllStringInstruments(ArrayList<T> insArr1, ArrayList<T> insArr2)
	{
		for(int i = 0; i < insArr1.size(); i++)
		{
			if(insArr1.get(i) instanceof StringInstrument)
			{
				insArr2.add(insArr1.get(i));
				this.price += ((MusicalInstrument)insArr1.get(i)).getPrice().doubleValue();
			}
		}
		isSortedIns(insArr2);
	}
	
	public void addAllWindInstruments(ArrayList<T> insArr1, ArrayList<T> insArr2)
	{
		for(int i = 0; i < insArr1.size(); i++)
		{
			if(insArr1.get(i) instanceof WindInstrument)
			{
				insArr2.add(insArr1.get(i));
				this.price += ((MusicalInstrument)insArr1.get(i)).getPrice().doubleValue();
			}
		}
		isSortedIns(insArr2);
	}
	
	public void SortByBrandAndPrice(ArrayList<T> insArr)
	{
		if((isMusicalInsArr(insArr)) && (insArr.size() > 0))
		{
			int i = 0, j = i + 1;
	    	T helper = insArr.get(j);
	        for(i = 0; i < insArr.size(); i++)
	       	{
	       		for(j = 1; j < insArr.size() - i; j++)
	       		{
	       			if((insArr.get(i) == null) || (insArr.get(j) == null))
	       			{
	       				break;
	       			}
	       			if(((MusicalInstrument)insArr.get(j - 1)).compareTo((MusicalInstrument)insArr.get(j)) > 0)
        			{	
	       				helper = insArr.get(j - 1);
	        			insArr.set(j - 1, insArr.get(j));
	        			insArr.set(j, helper);	        	
	       			}
	       		}
	       	}
		}
		if(this.insArr == insArr)
		{
			this.isSorted = true;
		}
	}
	
	public int binnarySearchByBrandAndPrice(ArrayList<T> insArr, String brand, Number price)
	{
		if((isMusicalInsArr(insArr)) && (isSortedIns(insArr)))
		{
		    int low = 0, high = insArr.size() - 1, mid;
		    while (low <= high)
		    {
		        mid = (low + high) / 2;
		        if(((MusicalInstrument)insArr.get(mid)).compareTo(brand, price) > 0)
		            high = mid - 1;
		        else if(((MusicalInstrument)insArr.get(mid)).compareTo(brand, price) < 0)
		            low = mid + 1;
		        else 
		            return mid;
		    }
		}
		return -1;
	}
	
	public void addInstrument(ArrayList<T> insArr, T ins)
	{
		insArr.add(ins);
		isSortedIns(this.insArr);
		if(ins instanceof MusicalInstrument)
		{
			this.price += ((MusicalInstrument)ins).getPrice().doubleValue();
		}
	}
	
	public boolean removeInstrument(ArrayList<T> insArr, T ins)
	{
		System.out.println(ins);
		boolean removed = insArr.remove(ins);
		if((this.insArr == insArr) && removed)
		{
			isSortedIns(this.insArr);
			if(ins instanceof MusicalInstrument)
			{
				this.price -= ((MusicalInstrument)ins).getPrice().doubleValue();
			}
		}
		return removed;
	}
	
	public boolean removeAll(ArrayList<T> insArr)
	{
		if(insArr.size() == 0)
		{
			return false;
		}
		boolean removed = insArr.removeAll(insArr);
		if((this.insArr == insArr) && removed)
		{
			setPrice(0);
			setIsSorted(false);
		}
		return removed;
	}
	
	public boolean isMusicalInsArr(ArrayList<T> insArr)
	{//Checks if a typically ArrayList is an instruments ArrayList. 
		for(int i = 0; i < insArr.size(); i++)
		{
			if(!(insArr.get(i) instanceof MusicalInstrument))
			{
				return false;
			}
		}
		return true;
	}
	
	public double rationalSum(T num1, T num2)
	{//return a double sum of 2 unknown type numbers.
		try
		{
			return (double)num1 + (double)num2;	
		}
		catch(Exception ex)
		{
			System.out.println("The input operands are invalid");
			return -1;
		}
	}
	
    public ArrayList<T> cloneArr()
    {//A clone method for copying an array.
    	ArrayList<T> insArr = new ArrayList<T>();
    	for(int i = 0; i < this.insArr.size(); i++)
    	{
    		insArr.add(this.insArr.get(i));
    	}
    	return insArr;
    }
    
	public String toString()
	{
		String str = "", strIns = "";
		str += "\n-------------------------------------------------------------------------\n" +
			   "AFEKA MUSICAL INSTRUMENTS INVENTORY\n" +
			   "-------------------------------------------------------------------------\n";
			   for(int i = 0; i < this.insArr.size(); i++)
			   {
				   strIns += insArr.get(i).toString() + "\n";
			   }
			   if(strIns.equals(""))
			   {
				   strIns = "There Is No Instruments To Show\n";
			   }
		str += strIns + "\nTotal Price: " + this.price + "    Sorted: " + this.isSorted;
		return str;
	}
}
