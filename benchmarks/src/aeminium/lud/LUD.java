package aeminium.lud;

public class LUD {
	public static final int DEFAULT_SIZE = 512;//2048
	public static final int DEFAULT_BLOCK_SIZE = 8;//16
	public static final double MAX_DIFF_THRESHOLD = 0.01;

	protected double[][] LU;
	protected int size;
	protected int BLOCK_SIZE;

	public LUD(Matrix A, int blocksize) {
		LU = A.getArrayCopy();
		size = A.getRowDimension();
		BLOCK_SIZE = blocksize;
		if (size != A.getColumnDimension()) {
			throw new RuntimeException("The matrix must be  a square matrix");
		}
	}
	/**
	 * blockLowerSolve - Perform forward substitution to solve for B' in LB' =
	 * B.
	 * 
	 * @param posB
	 *            The start position of matrix B in array LU where size of
	 *            matrix B is BLOCK_SIZE by BLOCK_SIZE
	 * @param posL
	 *            The start position of matrix L in array LU where size of
	 *            matrix L is BLOCK_SIZE by BLOCK_SIZE
	 **/
	protected void blockLowerSolve(MatrixPosition posB, MatrixPosition posL) {
		double a;
		int i, k, n;

		/* Perform forward substitution. */
		for (i = 1; i < BLOCK_SIZE; i++) {
			for (k = 0; k < i; k++) {
				a = LU[posL.row + i][posL.col + k];
				for (n = BLOCK_SIZE - 1; n >= 0; n--) {
					LU[posB.row + i][posB.col + n] -= a * LU[posB.row + k][posB.col + n];
				}
			}
		}
	}

	/**
	 * blockUpperSolve - Perform forward substitution to solve for B' in B'U =
	 * B.
	 * 
	 * @param posB
	 *            The start position of matrix B in array LU where size of
	 *            matrix B is BLOCK_SIZE by BLOCK_SIZE
	 * @param posU
	 *            The start position of matrix U in array LU where size of
	 *            matrix U is BLOCK_SIZE by BLOCK_SIZE
	 **/
	protected void blockUpperSolve(MatrixPosition posB, MatrixPosition posU) {
		double a;
		int i, k, n;

		/* Perform forward substitution. */
		for (i = 0; i < BLOCK_SIZE; i++) {
			for (k = 0; k < BLOCK_SIZE; k++) {
				LU[posB.row + i][posB.col + k] /= LU[posU.row + k][posU.col + k];
				a = LU[posB.row + i][posB.col + k];
				for (n = BLOCK_SIZE - 1; n >= (k + 1); n--) {
					LU[posB.row + i][posB.col + n] -= a * LU[posU.row + k][posU.col + n];
				}
			}
		}
	}

	protected void blockLU(MatrixPosition posB) {
		double a;
		int i, k, n;

		/* Factor block. */
		for (k = 0; k < BLOCK_SIZE; k++) {
			for (i = k + 1; i < BLOCK_SIZE; i++) {
				LU[posB.row + i][posB.col + k] /= LU[posB.row + k][posB.col + k];
				a = LU[posB.row + i][posB.col + k];
				for (n = BLOCK_SIZE - 1; n >= (k + 1); n--) {
					LU[posB.row + i][posB.col + n] -= a * LU[posB.row + k][posB.col + n];
				}
			}
		}
	}

	/**
	 * blockSchur - Compute Schur complement B' = B - AC.
	 * 
	 * @param posA
	 *            The start position of matrix A in array LU where size of
	 *            matrix A is BLOCK_SIZE by BLOCK_SIZE
	 * @param posB
	 *            The start position of matrix B in array LU where size of
	 *            matrix B is BLOCK_SIZE by BLOCK_SIZE
	 * @param posC
	 *            The start position of matrix C in array LU where size of
	 *            matrix C is BLOCK_SIZE by BLOCK_SIZE
	 **/
	protected void blockSchur(MatrixPosition posB, MatrixPosition posA, MatrixPosition posC) {
		int i, k, n;
		double a;

		/* Compute Schur complement. */
		for (i = 0; i < BLOCK_SIZE; i++) {
			for (k = 0; k < BLOCK_SIZE; k++) {
				a = LU[posA.row + i][posA.col + k];
				for (n = BLOCK_SIZE - 1; n >= 0; n--) {
					LU[posB.row + i][posB.col + n] -= a * LU[posC.row + k][posC.col + n];
				}
			}
		}
	}
}
