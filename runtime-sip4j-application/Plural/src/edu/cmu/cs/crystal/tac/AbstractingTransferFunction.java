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

import edu.cmu.cs.crystal.flow.AnalysisDirection;
import edu.cmu.cs.crystal.flow.LatticeElement;

/**
 * This class implements additional transfer functions that abstract or
 * group other transfer functions according to the instruction hierarchy.
 * This is convenient for analyses that want to treat many different
 * but related instructions in the same way.
 * 
 * @author Jonathan Aldrich
 *
 * @param <LE> LatticeElement subclass that represents the analysis knowledge.
 */
public abstract class AbstractingTransferFunction<LE extends LatticeElement<LE>> 
		implements ITACTransferFunction<LE> {

	private ITACAnalysisContext analysisContext;

	public AnalysisDirection getAnalysisDirection() {
		return AnalysisDirection.FORWARD_ANALYSIS;
	}

	public ITACAnalysisContext getAnalysisContext() {
		return analysisContext;
	}

	public void setAnalysisContext(ITACAnalysisContext analysisContext) {
		this.analysisContext = analysisContext;
	}
	
	public LE transfer(TACInstruction instr, LE value) {
		return value;
	}

	public LE transfer(AssignmentInstruction instr, LE value) {
		return transfer((TACInstruction) instr, value);
	}

	public LE transfer(InvocationInstruction instr, LE value) {
		return transfer((AssignmentInstruction) instr, value);
	}

	public LE transfer(OneOperandInstruction instr, LE value) {
		return transfer((AssignmentInstruction) instr, value);
	}

	public LE transfer(StoreInstruction instr, LE value) {
		return transfer((TACInstruction) instr, value);
	}


	public LE transferOver(ArrayInitInstruction instr, LE value) {
		return transfer((AssignmentInstruction)instr, value);
	}

	public LE transferOver2(BinaryOperation binop, LE value) {
		return transfer((AssignmentInstruction) binop, value);
	}

	public LE transferOver3(CastInstruction instr, LE value) {
		return transfer((OneOperandInstruction) instr, value);
	}

	public LE transferOver4(DotClassInstruction instr, LE value) {
		return transfer((AssignmentInstruction)instr, value);
	}

	public LE transferOver5(ConstructorCallInstruction instr, LE value) {
		return transfer((TACInstruction) instr, value);
	}

	public LE transferOver6(CopyInstruction instr, LE value) {
		return transfer((OneOperandInstruction) instr, value);
	}

	public LE transferOver6(EnhancedForConditionInstruction instr, LE value) {
		return transfer((TACInstruction) instr, value);
	}

	public LE transferOver7(InstanceofInstruction instr, LE value) {
		return transfer((OneOperandInstruction) instr, value);
	}

	public LE transferOver8(LoadLiteralInstruction instr, LE value) {
		return transfer((AssignmentInstruction)instr, value);
	}

	public LE transferOver9(LoadArrayInstruction instr, LE value) {
		return transfer((AssignmentInstruction)instr, value);
	}

	public LE transferOver10(LoadFieldInstruction instr, LE value) {
		return transfer((AssignmentInstruction)instr, value);
	}

	public LE transferOver11(MethodCallInstruction instr, LE value) {
		return transfer((InvocationInstruction) instr, value);
	}

	public LE transferOver12(NewArrayInstruction instr, LE value) {
		return transfer((AssignmentInstruction)instr, value);
	}

	public LE transferOver13(NewObjectInstruction instr, LE value) {
		return transfer((InvocationInstruction) instr, value);
	}

	public LE transferOver14(ReturnInstruction instr, LE value) {
		return transfer((TACInstruction) instr, value);
	}

	public LE transferOver15(StoreArrayInstruction instr, LE value) {
		return transfer((StoreInstruction) instr, value);
	}

	public LE transferOver16(StoreFieldInstruction instr, LE value) {
		return transfer((StoreInstruction) instr, value);
	}

	public LE transferOver17(SourceVariableDeclaration instr, LE value) {
		return transfer((TACInstruction) instr, value);
	}

	public LE transferOver18(SourceVariableRead instr, LE value) {
		return transfer((TACInstruction) instr, value);
	}

	public LE transferOver19(UnaryOperation unop, LE value) {
		return transfer((OneOperandInstruction) unop, value);
	}

}
