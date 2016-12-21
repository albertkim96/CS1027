import java.util.*;
import java.io.*;

public class Main {


public static void main(String[] args) 
{
// Local variables
StringTokenizer tokenizer;
String  widgetName;
String  widgetPartName;
Double  widgetPartPrice=0.0;
Integer widgetPartQuantity=0;
Boolean widgetFeasibility=true;
Part part;
Part targetPart;
final int NOT_FOUND=-1;
Integer partsInventoryQuantity;

// widgetIndex and widgetQuantity is a hack  - assumes 
// no more than 10 parts per widget
// The idea is to save all the part indices and quantities 
// for a widget - if it proves feasible to build it, 
// then print out all the part information
// and update the part inventory array
int[] widgetIndex = new int[10];
int[] widgetQuantity = new int[10];

// Create widgetReader Object
InStringFile widgetReader=new InStringFile("widgets.txt");
// Create PartsInventory Object
PartsInventory PartsInventory=new PartsInventory("parts.txt",3);

System.out.format("%n%n%40s%n---------------------------------------------%n",
		  "Wonky Widget/Parts Processing");
Integer partCt=0;
Integer widgetCt=0;
Integer widgetCt2=0;
Integer numTokens;
Double widgetCost=0.0;
Double totalWidgetCost=0.0;
Integer numWidgetParts=0;

System.out.format("%nPartsInventory before Widget Construction\n");
System.out.format("---------------------------------------------%n");
System.out.println(PartsInventory.toString());
System.out.format("%d parts in database%n",PartsInventory.getNumberOfParts());
System.out.format("%d parts added to PartsInventory database%n%n",
		   PartsInventory.getNumberOfParts());

System.out.format("%nProcessing Widget Data File:%n");
System.out.format("----------------------------%n");

do {
   String widgetLine = widgetReader.read();
   tokenizer=new StringTokenizer(widgetLine);
   numTokens=tokenizer.countTokens();

   if(numTokens==1) 
      {
      numWidgetParts=0;
      widgetCt++;
      widgetName=tokenizer.nextToken();
      System.out.format("%nWidget Number: %3d Widget Name: %-10s%n",widgetCt,widgetName);
      System.out.format("---------------------------------------%n");
      }
   else 
      if(numTokens==2) 
        {
	widgetPartName=tokenizer.nextToken();
	widgetPartQuantity=Integer.parseInt(tokenizer.nextToken());
	System.out.format("%-10s %5d%n",widgetPartName,widgetPartQuantity);

	// Need both the array index of the part and the part object
	int partIndex=PartsInventory.searchPart(widgetPartName);
	Part partObject=PartsInventory.getPartObject(partIndex);
        partsInventoryQuantity=partObject.getPartQuantity();

        widgetIndex[numWidgetParts]=partIndex;
        widgetQuantity[numWidgetParts]=widgetPartQuantity;
        numWidgetParts++;
	if(widgetPartQuantity > partsInventoryQuantity) 
          {
	  System.out.format("Warning: %s: Required %5d Available: %5d%n",
	                     widgetPartName,widgetPartQuantity,partsInventoryQuantity);
          widgetFeasibility=false;
          }
        }
      else // a blank line specifies no more parts for this widget
	if(numTokens==0) 
          {
	  if(widgetFeasibility)
	    {
            widgetCost=0.0;
            for(int i=0;i<numWidgetParts;i++) 
              {
              Part partObject=PartsInventory.getPartObject(widgetIndex[i]);
              widgetPartName=partObject.getPartName();
              widgetPartPrice=partObject.getPartPrice();
	      widgetCost+=widgetPartPrice*widgetPartQuantity;
              System.out.format("Part %s requires quantity %d, leaving %d available\n",
                widgetPartName,widgetQuantity[i],partObject.getPartQuantity()-widgetQuantity[i]);
              // Update part quantity
              partObject.setPartQuantity(partObject.getPartQuantity()-widgetQuantity[i]);
              // If there no parts left (quantity is 0) remove that part
              // from the PartsInventory database
              if(partObject.getPartQuantity()==0) PartsInventory.removePart(partObject);
              }
	    System.out.format("%nThis widget was successfully constructed%n");
	    System.out.format("widget cost: %7.2f%n%n",widgetCost);
	    widgetCt2++;
            numWidgetParts=0;
	    totalWidgetCost+=widgetCost;
	    }
	  else
	    {
	    System.out.format("%nThis widget cannot be constructed - not enough parts!!!%n");
	    System.out.format("%nPartsInventory not updated as widget not built\n");
	    }

	System.out.format("%nPartsInventory Updated After Widget Construction%n");
	System.out.format("------------------------------------------------%n");
	System.out.println(PartsInventory.toString());
	System.out.format("%d parts in database%n",PartsInventory.getNumberOfParts());

	// prepare for next widget if there is one
	widgetCost=0.0;
	widgetFeasibility=true;
        numWidgetParts=0;
	}
			
   } while (!widgetReader.endOfFile());

widgetReader.close();
System.out.format("%nWidget Status%n");
System.out.format("----------------------------------------------------%n");
System.out.format("%3d widgets processed%n",widgetCt);
System.out.format("%3d widgets constructed, %3d widgets not constructed%n",
						   widgetCt2,widgetCt-widgetCt2);
System.out.format("Total costs for all constructed widgets: %7.2f%n",totalWidgetCost);
}

}
