/**
 * Copyright (C) 2007-2009 Carnegie Mellon University and others.
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
package edu.cmu.cs.plural.fractions;

import java.util.HashMap;
import java.util.Map;

/**
 * Encapsulates mappings between named fractions, basically for alpha-converting them.
 * @author Kevin Bierhoff
 * @since Apr 11, 2009
 */
public class NamedFractionMapping {
	
	/** 
	 * Bidirectional named fraction map.
	 * If named fractions f1 and f2 are mapped to each other then they must 
	 * both be keys in this map, with the other as their value.  
	 */
	private Map<NamedFraction, NamedFraction> mapping = new HashMap<NamedFraction, NamedFraction>();

	/**
	 * Attempts to map the two given named fractions to each other;
	 * has no effect if the desired mapping is not possible or already in place.
	 * Mapping can fail, namely if at least one of the given named fractions already
	 * participates in another mapping.
	 * @param f1
	 * @param f2
	 * @return <code>true</code> if the given named fractions could be or
	 * were already mapped;
	 * <code>false</code> otherwise.
	 */
	public boolean map(NamedFraction f1, NamedFraction f2) {
		if(f1 == null || f2 == null)
			return false;
		if(f1.equals(f2))
			// no mapping needed
			return true;
		
		// let's see what the current mapping says
		NamedFraction m1 = mapping.get(f1);
		NamedFraction m2 = mapping.get(f2);
		if(m1 == null && m2 == null) {
			// neither f1 nor f2 mapped yet
			// add the mapping
			mapping.put(f1, f2);
			mapping.put(f2, f1);
			// success
			return true;
		}
		
		if(m1 != null && m2 != null) {
			// both already mapped
			// must be mapped to each other
			return m1.equals(f2) && m2.equals(f1);
		}
		
		// *either* f1 or f2 already mapped
		// fail because can't map to each other
		return false;
	}
}
