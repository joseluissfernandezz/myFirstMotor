package board;

import java.io.IOException;
import org.json.simple.parser.ParseException;
import enums.specialFeaturesEnum;
import main.main;
import readJson.Parameters;
import utils.FeaturesUtils;

public class jsonBoard {
	public int[][] createBoardFromJson(Parameters Parameters) throws IOException, ParseException {
		
        int rows = Parameters.getGameParameters().getNumberRows();
        int columns = Parameters.getGameParameters().getNumberColumns();
        int[][] board = new int[rows][columns];
        boardCreationClass boardCreationClass = new boardCreationClass();
		boardCreationClass.boardCreation(board, Parameters);
		
		for(String element:Parameters.getSpecialFeatures()) {
			
			if(specialFeaturesEnum.valueOf(element) == specialFeaturesEnum.RANDOM_WILD) {
				FeaturesUtils FeaturesUtils = new FeaturesUtils();
				FeaturesUtils.wildCreation(board);
			}
			
		}

        return board;
    }
}
