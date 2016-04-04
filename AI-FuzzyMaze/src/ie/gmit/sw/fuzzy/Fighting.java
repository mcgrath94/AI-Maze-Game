package ie.gmit.sw.fuzzy;

import net.sourceforge.jFuzzyLogic.FIS;
import net.sourceforge.jFuzzyLogic.FunctionBlock;

public class Fighting {
	
	public Fighting(){
		
		FIS fis = FIS.load("fcl/fight.fcl", true); //	Load and parse the FCL
		
		//Display linguistic variables and terms
		FunctionBlock functionBlock = fis.getFunctionBlock("Project");
		
		int weaponValue = 0;
		int enemyValue = 0;
		fis.setVariable("weapon", weaponValue); //Apply value to a variable
		fis.setVariable("opponent", enemyValue);
		fis.evaluate(); //Execute the fuzzy interface engine
		
		System.out.println(fis.getVariable("win").getValue()); //Output end result
		
	}

}
