package algorithm;

public class NumOfSubmarines {

	public  Integer countSubmarines(int[][] mat)
	{
		//Integer sum=countSubmarines2(mat);
		boolean isGameBoardValid = true;

		for (int row = 0; row < mat.length; row++)
			for (int col = 0; col < mat[1].length; col++)
				isGameBoardValid = isGameBoardValid  && checkCellValidation(mat, row, col);

		if (!isGameBoardValid) {
			System.out.println("The game board is not valid. Try another board please.");
		}
		else {
			return countSubmarines2(mat);
		}
		return 0;
	}
	
	public  Integer countSubmarines2(int[][] mat) {
		Integer numOfSubmarines = 0;
		boolean lookForMorePiecesOfSub, foundSubmarine = false;
		
        for (int row = 0; row < mat.length; row++)
			for (int col = 0; col < mat[1].length; col++) {
				
				// Only if the current cell is a start of a submarine
				if (mat[row][col] == 1) {
					
					// If so, we would want to look for the rest of the submarine.
					lookForMorePiecesOfSub = true;
					
					// Now, as a result of our iteration start, we may reveal submarines on our right or below us.
					// Check if there is submarine below me
					for (int i = row + 1; i < mat.length && (lookForMorePiecesOfSub); i++) {
						// If current cell is another piece of submarine
						if (mat[i][col] == 1) {
							// After we revealed the submarine, we need to 'remove' other pieces of submarines
							// around our cell- because a valid submarine is away at least one cell from each other
							setZeroAroundCell(mat, i, col);
							foundSubmarine = true;
						} else {
							// We got an empty cell which means end of submarine pieces
							lookForMorePiecesOfSub = false;
						}
					}
					if (foundSubmarine) {
						setZeroAroundCell(mat, row, col);
						numOfSubmarines++;
					}
					else {
						lookForMorePiecesOfSub = true;
						// Check if there is submarine on my right side
						for (int j = col + 1; j < mat[1].length && (lookForMorePiecesOfSub); j++) {
							// If current cell is another piece of submarine
							if (mat[row][j] == 1) {
								// After we revealed the submarine, we need to 'remove' other pieces of submarines
								// around our cell- because a valid submarine is away at least one cell from each other
								setZeroAroundCell(mat, row, j);
								foundSubmarine = true;
							} else {
								// We got an empty cell which means end of submarine pieces
								lookForMorePiecesOfSub = false;
							}
						}
						if (foundSubmarine) {
							setZeroAroundCell(mat, row, col);
							numOfSubmarines++;
						}
					}
				}
				
				// Now we can get ready for next round and look for another submarine
				foundSubmarine = false;
			}
        return numOfSubmarines;
	}
	
	public  void setZeroAroundCell(int[][] mat, int row, int col) {
		
		// Iterate over the around of specific cell, and set all cells around that cell- to zero value(0).
		for (int i = row - 1; i <= row + 1; i++) {
			for (int j = col - 1; j <= col + 1; j++) {
				try {
					mat[i][j] = 0;
				} catch (ArrayIndexOutOfBoundsException e) {
					// DO NOTHING- JUST MOVE TO THE NEXT CELL(PLEASE OF COURSE :))
				}
			}
		}
	}
	
	public  boolean checkCellValidation(int[][] mat, int row, int col) {
		if (!(mat[row][col] == 1))
			return true;
		
		// Check if there is option to down-right slant
		if ((row + 1) < mat.length && (col + 1) < mat[1].length && mat[row + 1][col + 1] == 1)
			// If there is an option, lets check if the only valid situation is happen- in which there is cross-slants
			if (!(mat[row][col + 1] == 1 && mat[row + 1][col] == 1))
				return false;
		
		// Check if there is option to up-left slant
		if ((row - 1) >= 0 && (col - 1) >= 0 && mat[row - 1][col - 1] == 1)
			// If there is an option, lets check if the only valid situation is happen- in which there is cross-slants
			if (!(mat[row][col - 1] == 1 && mat[row - 1][col] == 1))
				return false;
		
		// Check if there is option to up-right slant
		if ((row - 1) >= 0 && (col + 1) < mat[1].length && mat[row - 1][col + 1] == 1)
			// If there is an option, lets check if the only valid situation is happen- in which there is cross-slants
			if (!(mat[row][col +1] == 1 && mat[row - 1][col] == 1))
				return false;
		
		// Check if there is option to down-left slant
		if ((row + 1) < mat.length && (col - 1) >= 0 && mat[row + 1][col - 1] == 1){
			// If there is an option, lets check if the only valid situation is happen- in which there is cross-slants
			if (mat[row][col - 1] == 1 && mat[row + 1][col] == 1) {
				return true;
			}
			return false;
		}
		return true;
	}
	
	public void print(int[][] mat) {
		
		//Iterate over the game board and prints cell's values
		for (int row = 0; row < mat.length; row++) {
			for (int col = 0; col < mat[1].length; col++) {
				System.out.print((mat[row][col]) + "   ");
			}
			System.out.println();
		}	
	}

}
