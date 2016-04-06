package ie.gmit.sw.fuzzy;

import net.sourceforge.jFuzzyLogic.FIS;
import net.sourceforge.jFuzzyLogic.FunctionBlock;

import ie.gmit.sw.ai.*;

public class Fighting {
	
	int weaponLevel = GameRunner.weaponValue;
	int enemyLevel = GameRunner.enemyValue;
	
	public Fighting(){
		
		FIS fis = FIS.load("fcl/fight.fcl", true); //	Load and parse the FCL
		
		//Display linguistic variables and terms
		FunctionBlock functionBlock = fis.getFunctionBlock("Project");
		
		fis.setVariable("weapon", weaponLevel); //Apply value to a variable
		fis.setVariable("opponent", enemyLevel);
		fis.evaluate(); //Execute the fuzzy interface engine
		
		System.out.println(fis.getVariable("result").getValue()); //Output end result
		
	}

}
