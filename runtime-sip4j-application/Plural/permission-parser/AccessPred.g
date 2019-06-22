/*
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
grammar AccessPred;

@header {
package edu.cmu.cs.plural.perm.parser;

import edu.cmu.cs.plural.perm.parser.*;
import edu.cmu.cs.plural.fractions.Fraction;
import edu.cmu.cs.plural.concrete.PluralParseError;
import java.util.LinkedList;
}

@lexer::header {
package edu.cmu.cs.plural.perm.parser;
}

@members {
    /**
     * Main method for parser testing.
     */
    public static void main(String[] args) throws Exception {
        AccessPredLexer lex = new AccessPredLexer(new ANTLRFileStream(args[0]));
       	CommonTokenStream tokens = new CommonTokenStream(lex);

        AccessPredParser parser = new AccessPredParser(tokens);

        try {
            parser.start();
        } catch (RecognitionException e)  {
            e.printStackTrace();
        }
    }
    @Override
    public void displayRecognitionError(String[] tokenNames,
                                        RecognitionException e) {
        throw new PluralParseError(e);
    }
    
    private static AccessPred treeOfConjList(List<AccessPred> list) {
      if( list.size() == 1 ) {
      	return list.get(0);
      } 
      else if( list.size() == 0 ) {
      	throw new IllegalStateException();
      }
      else {
      	return new Conjunction( list.remove(0), treeOfConjList( list ) );
      }
    }
    
    private static AccessPred treeOfDisjList(List<AccessPred> list) {
      if( list.size() == 1 ) {
      	return list.get(0);
      } 
      else if( list.size() == 0 ) {
      	throw new IllegalStateException();
      }
      else {
      	return new Disjunction( list.remove(0), treeOfConjList( list ) );
      }    
    }
    
    private static AccessPred treeOfWithList(List<AccessPred> list) {
      if( list.size() == 1 ) {
      	return list.get(0);
      } 
      else if( list.size() == 0 ) {
      	throw new IllegalStateException();
      }
      else {
      	return new Withing( list.remove(0), treeOfConjList( list ) );
      }
    }
}

/*------------------------------------------------------------------
 * PARSER RULES!
 *------------------------------------------------------------------*/

start returns [TopLevelPred result]
	:	p=perm EOF { $result = p; }
	|	TRUE EOF { $result = null; }
	|	FALSE EOF { $result = TopLevelPred.Impossible.getInstance(); }
	|	EOF { $result = EmptyPredicate.getInstance(); }
	;

access_pred returns [AccessPred result]
	:	i1=ID '(' i2=ref_expr ')' 
		{ $result = new TempPermission($i1.text,i2,null,null,null); } 
	|	i1=ID '(' i2=ref_expr ')' IN sl=state_list 
		{ $result = new TempPermission($i1.text,i2,null,null,sl); } 
	|	i1=ID '(' i2=ref_expr ',' i3=ID ')' 
		{ $result = new TempPermission($i1.text,i2,$i3.text,null,null); } 
	|	i1=ID '(' i2=ref_expr ',' i3=ID ')' IN sl=state_list 
		{ $result = new TempPermission($i1.text,i2,$i3.text,null,sl); }
	|	i1=ID '(' i2=ref_expr ',' i3=ID ',' f=fractions ')' 
		{ $result = new TempPermission($i1.text,i2,$i3.text,f.getMap(),null); }
	|	i1=ID '(' i2=ref_expr ',' i3=ID ',' f=fractions ')' IN sl=state_list 
		{ $result = new TempPermission($i1.text,i2,$i3.text,f.getMap(),sl); }
	;

/* we have the ability to make concrete field values imply other things */
implies returns [AccessPred result]
	: ant=bin_expr IMPLIES cons=lowest { $result = new PermissionImplication(ant,cons); }
	;

state_only returns [AccessPred result]
	: ref=ref_expr IN state=ID {$result = new StateOnly(ref, state.getText());}
	;

/* This expression type is for Java-like expressions, e.g., socket == null */
bin_expr_ap returns [AccessPred result]
	: e=bin_expr {$result = new BinaryExprAP(e);}
	;

bin_expr returns [BinaryExpr result]
	: e1=primary_expr DEQ e2=primary_expr {$result = new EqualsExpr(e1,e2);}
	| e1=primary_expr NEQ e2=primary_expr {$result = new NotEqualsExpr(e1,e2);}
	;

primary_expr returns [PrimaryExpr result]
	: i=ID 	{$result = new Identifier(i.getText());}
	| '#' num=NUMBER {$result = new ParamReference(num.getText());}
	| NULL 	{$result = Null.getInstance();}
	| TRUE 	{$result = BoolLiteral.getTrueInstance();}
	| FALSE	{$result = BoolLiteral.getFalseInstance();}
	;

ref_expr returns [RefExpr result]
	: 	i=ID 
		(	{$result = new Identifier(i.getText());}
		| 	'!' use=use_qualifier
			{$result = new Identifier(i.getText(), use);}
		|	'.super' 
			{$result = Identifier.qualified(i.getText(), "super");}
		|	'.this' 
			(	{$result = Identifier.qualified(i.getText(), "this");}
			|	'!' use2=use_qualifier
				{$result = Identifier.qualifiedThis(i.getText(), use2);}
			)
		)
	| '#' num=NUMBER {$result = new ParamReference(num.getText());}
	;
	
use_qualifier returns [PermissionUse result]
	: 'fr' {$result = PermissionUse.FIELDS;}
	| 'fl' {$result = PermissionUse.FIELDS;}
	| 'dp' {$result = PermissionUse.DISPATCH;}
	| 'df' {$result = PermissionUse.DISP_FIELDS;}
	;

/* From low to high precedence */
perm returns [AccessPred result]
@init {
List<AccessPred> temp = new ArrayList<AccessPred>();
}
@after {
$result = treeOfDisjList(temp);
}
	:	(left=wither {temp.add(left); }) (ALT right=wither {temp.add(right);})*
	;

wither returns [AccessPred result]
@init {
List<AccessPred> temp = new ArrayList<AccessPred>();
}
@after {
$result = treeOfWithList(temp);
}
	:	(left=conjs {temp.add(left); })	(WITH right=conjs {temp.add(right);})*
	;
	
conjs returns [AccessPred result]
@init {
List<AccessPred> temp = new ArrayList<AccessPred>();
}
@after {
$result = treeOfConjList(temp);
}
 	:	(left=lowest { temp.add(left); }) (TENS right=lowest {temp.add(right);})*
 	; 
		
lowest returns [AccessPred result]	
	:	r=access_pred	{$result = r;}
	|	e=bin_expr_ap	{$result = e;}
	|       s=state_only	{$result = s;}
	|       i=implies	{$result = i;}
	|	'(' r=perm ')'	{$result = r;}
	;

/* permission parts */
fractions returns [TempFractionMap result]
@init {
$result = new TempFractionMap();
}
	:	n=ID '=' f=fract {$result.put(n.getText(), f);} (',' m=ID '=' g=fract {$result.put(m.getText(), g);} )*
	;

fract returns [Fraction result]
	:	p=NUMBER				{$result = Fraction.createExplicit(Integer.parseInt($p.text), 1);}
	|	p=NUMBER '/' q=NUMBER	{$result = Fraction.createExplicit(Integer.parseInt($p.text), Integer.parseInt($q.text));}
	|	i=ID					{$result = Fraction.createNamed($i.text);}
	;
	
state_list returns [List<String> result]
@init {
$result = new LinkedList<String>();
}
	:	i=ID { $result.add(i.getText()); } (',' j=ID { $result.add(j.getText()); } )*
	;
 
/*------------------------------------------------------------------
* LEXER RULES!
*------------------------------------------------------------------*/
	ALT	:	'alt' | '+';
	TENS	:	'tens' | '*';
	WITH	:	'with' | '&';
	DEQ	:	'==';
	NEQ	:	'!=';
	IN 	:	'in';
	NULL	:	'null';
	IMPLIES	:	'=>' | 'implies';
	TRUE	:	'true';
	FALSE	:	'false';
	ONE		:	'one';
	ZERO	:	'zero';

ID 	:	(LETTER|'_')(LETTER|DIGIT|'_')*;

fragment LETTER: ('a'..'z' | 'A'..'Z');

NUMBER	: (DIGIT)+ ;

WHITESPACE : ( '\t' | ' ' | '\r' | '\n'| '\u000C' )+ 	{ $channel = HIDDEN; } ;

fragment DIGIT	: '0'..'9' ;
