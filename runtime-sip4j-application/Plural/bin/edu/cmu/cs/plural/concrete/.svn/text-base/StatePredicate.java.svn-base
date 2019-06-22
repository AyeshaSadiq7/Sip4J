/**
 * Copyright (C) 2007, 2008 Carnegie Mellon University and others.
 *
 * This file is part of Plural.
 *
 * Plural is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License version 2 as
 * published by the Free Software Foundation.
 *
 * Plural is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Plural; if not, see <http://www.gnu.org/licenses>.
 *
 * Linking Plural statically or dynamically with other modules is
 * making a combined work based on Plural. Thus, the terms and
 * conditions of the GNU General Public License cover the whole
 * combination.
 *
 * In addition, as a special exception, the copyright holders of Plural
 * give you permission to combine Plural with free software programs or
 * libraries that are released under the GNU LGPL and with code
 * included in the standard release of Eclipse under the Eclipse Public
 * License (or modified versions of such code, with unchanged license).
 * You may copy and distribute such a system following the terms of the
 * GNU GPL for Plural and the licenses of the other code concerned.
 *
 * Note that people who make modified versions of Plural are not
 * obligated to grant this special exception for their modified
 * versions; it is their choice whether to do so. The GNU General
 * Public License gives permission to release a modified version
 * without this exception; this exception also makes it possible to
 * release a modified version which carries forward this exception.
 */
package edu.cmu.cs.plural.concrete;

import edu.cmu.cs.crystal.analysis.alias.Aliasing;
import edu.cmu.cs.plural.fractions.FractionalPermissions;
import edu.cmu.cs.plural.track.PluralTupleLatticeElement;

/**
 * This predicate is <b>not</b> meant to ever be fed directly into the {@link DynamicStateLogic};
 * instead it can be used to test and add state information in a given tuple lattice.
 * @author Kevin Bierhoff
 *
 */
public class StatePredicate implements VariablePredicate {
	
	private Aliasing var;
	private String stateInfo;
	private boolean inFrame;
	
	public static StatePredicate createStatePred(Aliasing var, String stateInfo) {
		return new StatePredicate(var, stateInfo, false);
	}

	private StatePredicate(Aliasing var, String stateInfo, boolean inFrame) {
		this.var = var;
		this.stateInfo = stateInfo;
		this.inFrame = inFrame;
	}
	
	public String getStateInfo() {
		return stateInfo;
	}
	
	public boolean isInFrame() {
		return inFrame;
	}

	@Override
	public VariablePredicate createIdenticalPred(Aliasing other) {
		return new StatePredicate(other, stateInfo, inFrame);
	}

	@Override
	public VariablePredicate createOppositePred(Aliasing other) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean denotesBooleanFalsehood() {
		return false;
	}

	@Override
	public boolean denotesBooleanTruth() {
		return false;
	}

	@Override
	public boolean denotesNonNullVariable() {
		return false;
	}

	@Override
	public boolean denotesNullVariable() {
		return false;
	}

	@Override
	public Aliasing getVariable() {
		return var;
	}

	@Override
	public boolean isUnsatisfiable(PluralTupleLatticeElement value) {
		FractionalPermissions ps = value.get(var);
		edu.cmu.cs.plural.states.StateSpace space = ps.getStateSpace();
		for(String s : ps.getStateInfo(inFrame)) {
			if(!space.areOrthogonal(s, stateInfo)) {
				if(space.firstBiggerThanSecond(stateInfo, s))
					continue;
				if(space.firstBiggerThanSecond(s, stateInfo))
					continue;
				return true;
			}
		}
		return false;
	}

	@Override
	public PluralTupleLatticeElement putIntoLattice(
			PluralTupleLatticeElement value) {
		FractionalPermissions ps = value.get(var);
		ps = ps.learnTemporaryStateInfo(stateInfo, inFrame);
		value.put(var, ps);
		return value;
	}

	@Override
	public boolean isSatisfied(PluralTupleLatticeElement value) {
		return value.get(var).isInState(stateInfo, inFrame);
	}
	
	@Override
	public String toString() {
		if(inFrame)
			return var + "!fr IN " + stateInfo;
		else
			return var + " IN " + stateInfo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (inFrame ? 1231 : 1237);
		result = prime * result
				+ ((stateInfo == null) ? 0 : stateInfo.hashCode());
		result = prime * result + ((var == null) ? 0 : var.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final StatePredicate other = (StatePredicate) obj;
		if (inFrame != other.inFrame)
			return false;
		if (stateInfo == null) {
			if (other.stateInfo != null)
				return false;
		} else if (!stateInfo.equals(other.stateInfo))
			return false;
		if (var == null) {
			if (other.var != null)
				return false;
		} else if (!var.equals(other.var))
			return false;
		return true;
	}

	@Override
	public boolean isAlwaysTrue() {
		return false;
	}

}
