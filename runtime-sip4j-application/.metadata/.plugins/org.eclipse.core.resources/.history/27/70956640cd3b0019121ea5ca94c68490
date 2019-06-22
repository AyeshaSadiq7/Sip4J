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
package edu.cmu.cs.plural.linear;

import java.util.LinkedHashSet;

/**
 * Simple visitor to find errors according to the semantics of disjunctive
 * contexts and generate an appropriate error message where needed.  
 * Errors are discarded if a tuple with no errors is found
 * in a {@link ContextChoiceLE}.
 * @author Kevin Bierhoff
 * @since 4/24/2008
 */
abstract class ErrorReportingVisitor extends DisjunctiveVisitor<String> {
	
	/**
	 * Check an individual tuple with this method.
	 * @param tuple
	 * @return An error message or <code>null</code> if no errors were found.
	 */
	public abstract String checkTuple(TensorPluralTupleLE tuple);

	@Override
	public String context(LinearContextLE le) {
		return checkTuple(le.getTuple());
	}

	@Override
	public String all(ContextAllLE le) {
		LinkedHashSet<String> errors = new LinkedHashSet<String>();
		// succeed for empty (true) choice
		for(DisjunctiveLE e : le.getElements()) {
			String error = e.dispatch(this);
			// more complete error message: find *all* failing contexts,
			// not just the first one
			if(error != null)
				errors.add(error); 
		}
		if(errors.isEmpty())
			return null;
		String result = errorString(errors, " AND ");
		if(errors.size() > 1)
			return "{ " + result + " }";
		else
			return result;
	}

	public static String errorString(Iterable<String> errors, String separator) {
		StringBuffer result = new StringBuffer();
		boolean first = true;
		for(String s : errors) {
			if(first)
				first = false;
			else
				result.append(separator);
			result.append(s);
		}
		return result.toString();
	}

	@Override
	public String choice(ContextChoiceLE le) {
		if(le.getElements().isEmpty())
			// fail for empty (false) choice
			return "No available context--usually due to a previous failure or error during packing/unpacking";
		LinkedHashSet<String> errors = new LinkedHashSet<String>();
		for(DisjunctiveLE e : le.getElements()) {
			String error = e.dispatch(this);
			if(error == null)
				return null;
			errors.add(error);
		}
		assert !errors.isEmpty() : "Shouldn't be able to get here without actual errors";
		String result = errorString(errors, " OR ");
		if(errors.size() > 1)
			return "[ " + result + " ]";
		else
			return result;
	}
}