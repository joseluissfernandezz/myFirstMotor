package parametersLoader;

import java.util.ArrayList;
import java.io.File;
import readJson.Parameters;

public class loadParameters {
	
	ArrayList<Parameters> Parameters = new ArrayList<>();
	
	public loadParameters() {
		
		File resources = new File("resources/");
		
		for(File file : resources.listFiles()) {
			
			if(file.getName().startsWith("gameParameters")&&(file.getName().endsWith(".json"))) {
				Parameters.add(new Parameters(file));
			}
			
		} 		
	}
	
	public ArrayList<Parameters> getParameters() {
		return Parameters;
	}
	public void setParameters(ArrayList<Parameters> parameters) {
		Parameters = parameters;
	}

}
