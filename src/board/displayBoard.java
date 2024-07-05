package board;

public class displayBoard {
    public void displayBoard(int[][] board) {
    	
        System.out.println("Current Board:");
        
        for (int[] row : board) {  	
            for (int cell : row) {
            	
                System.out.print(cell + " ");
                
            }        
            System.out.println();  
        }  
        System.out.println();
    }
}
