/**
 * Copyright (c) 2006, 2007, 2008 Marwan Abi-Antoun, Jonathan Aldrich, Nels E. Beckman,
 * Kevin Bierhoff, David Dickey, Ciera Jaspan, Thomas LaToza, Gabriel Zenarosa, and others.
 *
 * This file is part of Crystal.
 *
 * Crystal is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Crystal is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with Crystal.  If not, see <http://www.gnu.org/licenses/>.
 */
package edu.cmu.cs.crystal.tac;

import edu.cmu.cs.crystal.Crystal;
import edu.cmu.cs.crystal.flow.LatticeElement;
import edu.cmu.cs.crystal.tac.eclipse.CompilationUnitTACs;
/**
 * This class implements a standard flow analysis over 3-address code instructions
 * ({@link TACInstruction}).  Implement {@link ITACTransferFunction} to
 * define a specific analysis.  For branch-sensitivity, 
 * use {@link BranchSensitiveTACAnalysis}. 
 * 
 * @author Kevin Bierhoff
 *
 * @param <LE>	the LatticeElement subclass that represents the analysis knowledge
 *
 * @see BranchInsensitiveTACAnalysis
 * @deprecated Use TACFlowAnalysis instead
 */
public class BranchInsensitiveTACAnalysis<LE extends LatticeElement<LE>> 
		extends TACFlowAnalysis<LE> implements ITACAnalysisContext {

	/**
	 * Creates a standard flow analysis with the given transfer function.
	 * @param crystal
	 * @param tf
	 */
	public BranchInsensitiveTACAnalysis(Crystal crystal, ITACTransferFunction<LE> tf,
			CompilationUnitTACs compUnitTacs) {
		super(crystal, tf, compUnitTacs);
	}
}
