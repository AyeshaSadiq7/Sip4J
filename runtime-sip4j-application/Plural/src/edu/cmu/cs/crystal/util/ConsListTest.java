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

package edu.cmu.cs.crystal.util;

/**
 * @author ayeshasadiq
 * @since 11/03/2019
 *
 */


import static edu.cmu.cs.crystal.util.ConsList.cons;
import static edu.cmu.cs.crystal.util.ConsList.list;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Iterator;
import edu.cmu.cs.crystal.util.ConsList;
import edu.cmu.cs.crystal.util.Lambda2;


public class ConsListTest {
	
	ConsList<Integer> l = list(1,2,3,4,5);
	ConsList<Integer> front = ConsList.list(1,2,3,4,5);
	ConsList<Integer> back = ConsList.list(6,7,8,9,10);
	Object obj[] = new Object[10];
	public void testList() {
		
	//	ConsList<Integer> l = ConsList.empty();
		l.toArray();
		l.tl();
		l.toArray(obj);
		 l.isEmpty();
		 l.hd().intValue();
		 l = cons(2, l);
		 ConsList.concat(front, back);
		 ConsList<Integer> l2 = cons(2, l);
		 l2.tl().size();
		 l.removeElement(1).isEmpty();
		 l = l.removeElement(1);
		 Iterator<Integer> iter = l.iterator();
		  iter.hasNext();
		 iter.next().intValue();
		 l.contains(6);
		 l.contains(9);
	}
}

