grammar Plural;

options {
  language = Java;
}
@header {
package uma.SMC;
}

@lexer::header {
package uma.SMC;
  
}

/* LEXER RULES*/
AT_FULL:           '@Full';
AT_PURE:           '@Pure';
AT_IMMUTABLE:      '@Immutable';
AT_SHARE:          '@Share';
AT_UNIQUE:         '@Unique';
PUBLIC_BEHAVIOR:   'public_behavior';


FULL:           ('F'|'f')'ull';
PURE:           ('P'|'p')'ure';
IMMUTABLE:      ('I'|'i')'mmutable';
SHARE:          ('S'|'s')'hare';
UNIQUE:         ('U'|'u')'nique';
NONE:         ('N'|'n')'one';



LSBRACKET:      '(';
RSBRACKET:      ')';
PERM:           '@'('P'|'p')'erm';
EQUAL:           '=';
EQUALOPERATOR:           '==';
IN :             'in';
THIS:            'this'|'this!fr';
RESULT:           ('R'|'r')'esult';
PARAM:            '#'('0'..'9')*;

REQUIRES:        'requires';
ENSURES:         'ensures';
QUOTE:           '"';
AND:              '*';
USE:              ('U'|'u')'se';
USEFIELDS:        ('U'|'u')'se.FIELDS';

PUNCTUATION :   ',';
CASES:          '@'('C'|'c')'ases';
LCBRACKET:      '{';
RCBRACKET:      '}';
CLASS_STATES:   '@'('C'|'c')'lass'('S'|'s')'tates';
REFINE:         '@'('R'|'r')'efine';
VALUE:          ('V'|'v')'alue';
STATE:          '@'('S'|'s')'tate';
STATES:         '@'('S'|'s')'tates';
DIM:            ('D'|'d')'im';
NAME:           ('N'|'n')'ame';
INV:            'inv';
OPERATOR:       '=='|'!=';
SEMICOLON:      ';';
LESS:            '<';
LESSTHANEQUAL:   '<=';
GREATER:         '>';
GREATERTHANEQUAL: '>=';
ANDD:               '&&';
OR:                '||';
JMLSTART:          '/*@';
JMLEND:            '*/';
PLUSMINUSOPERATOR:  ('+'|'-');
ASSIGNABLE:      'assignable';
NOTHING:         '\\nothing';
EVERYTHING:      '\\everything';
GHOST:          'ghost';
INT:            'int';
INVARIANT:      'invariant';
OLD:            '\\old';
ID:             ('a'..'z'|'A'..'Z')('a'..'z'|'A'..'Z'|'0'..'9'|'_'|'.')*;
NUMBERS:        ('0'..'9')*;
WS:              (' '|'\t'|'\n'|'\r')+        {$channel=HIDDEN;};


////////////////////// START OF JML GRAMMAR////////////////////

jmlSpecifications: jmlClassSpecifications|jmlMethodSpecification
;

jmlClassSpecifications: jmlGhostDeclaration|jmlGhostInv
;
jmlGhostDeclaration: GHOST INT dim=ID SEMICOLON{
    E_JmlSpecification.setDimensionName($dim.text);
}
;
jmlGhostInv: INVARIANT  low=NUMBERS LESSTHANEQUAL ID ANDD ID LESSTHANEQUAL high=NUMBERS SEMICOLON{
    int nlow=Integer.parseInt($low.text);
    int nhigh=Integer.parseInt($high.text);
  E_JmlSpecification.setDimensionValues(nlow, nhigh);
}
;

jmlMethodSpecification: JMLSTART PUBLIC_BEHAVIOR  (REQUIRES jmlRequires  SEMICOLON)? (jmlAssign)? (jmlEnsures)? JMLEND
;
jmlRequires:jmlReq|jmlOrReq|jmlLessThanEqualReq
;
jmlOrReq: jmlReq (OR jmlReq)+
;
jmlLessThanEqualReq: ID LESSTHANEQUAL tstate=NUMBERS {
	      int n=Integer.parseInt($tstate.text);
	      int x=1;
	      while(x<=n){
	       E_JmlSpecification.addRequires(""+x);
	       x++;
	    }
}
;
jmlReq: ID EQUALOPERATOR strState=NUMBERS {
  E_JmlSpecification.addRequires($strState.text);

}
;
jmlEnsures:jmlEns|jmlOldEns
;
jmlOldEns:ENSURES ID EQUALOPERATOR OLD LSBRACKET ID RSBRACKET (ope=PLUSMINUSOPERATOR num=NUMBERS)? SEMICOLON{

  if ($ope.text!=null&& $num.text!=null)
    E_JmlSpecification.setEnsures("old"+$ope.text+$num.text);
 else
    E_JmlSpecification.setEnsures("old");
 
}
;

jmlEns: ENSURES ID EQUALOPERATOR strState=NUMBERS SEMICOLON{
  E_JmlSpecification.setEnsures($strState.text);

}
;
jmlAssign:ASSIGNABLE (strPerm='\\everything'|strPerm='\\nothing'|strPerm=ID) SEMICOLON{
    
    String perm="Pure";
    String str=$strPerm.text;
    if (str.compareTo("\\nothing")==0)
          perm="Pure";
     else if (str.length()>0)
            perm="Full";       
    E_JmlSpecification.setPerm(perm);
}
;

////////////////////////////////////////////END OF JML GRAMMER ////////////////////

///////////////////START OF PLURAL SPECIFICATIONS GRAMMER////////////////////////////////////////

specifications: perm|cases|classstates|refine
;

refine:REFINE LSBRACKET LCBRACKET (states) (PUNCTUATION states)*  RCBRACKET RSBRACKET // note the difference b/w state and states
;

states: STATES LSBRACKET dimension PUNCTUATION (value)* RSBRACKET
;


dimension: DIM EQUAL QUOTE dim=ID QUOTE {
                                          EVMDD_SMC_Generator.addDimension($dim.text);
                                        }
;

value: VALUE EQUAL LCBRACKET item (PUNCTUATION item)* RCBRACKET
;
item: QUOTE state_name=ID QUOTE {
                                   EVMDD_SMC_Generator.addDimensionValue($state_name.text);
                                   EVMDD_SMC_Generator.addState($state_name.text);
                                 }
;


classstates: start_classstates state (PUNCTUATION state)* end_classstates
;

start_classstates:CLASS_STATES LSBRACKET LCBRACKET
;

end_classstates:RCBRACKET RSBRACKET
;

state: STATE LSBRACKET NAME EQUAL QUOTE state_name=ID QUOTE { EVMDD_SMC_Generator.addState($state_name.text);} (PUNCTUATION invariant)* RSBRACKET {// add states into class structure
                                                                                               
                                                                                                }
;
                                                                                      
invariant:INV EQUAL QUOTE (condition (AND condition)*)? QUOTE
;

condition : var=ID op=OPERATOR val=ID {
                                        String variable=$var.text;
                                        String opertor=$op.text;
                                        String value=$val.text;
                                        EVMDD_SMC_Generator.addBoolStateInvariant(variable,opertor,value);
                                      }
          | ap=accesspermission LSBRACKET var=(THIS|ID) RSBRACKET (IN st=ID)? {
                                                                      String accessPermission=$ap.text;
                                                                      String variable=$var.text;
                                                                      String state=$st.text;
                                                                      if (state==null)
                                                                            state="alive";
                                                                      EVMDD_SMC_Generator.addStateInvariant(accessPermission,variable,state);
                                                                    }
;

cases:CASES LSBRACKET LCBRACKET perm (other perm)* RCBRACKET RSBRACKET
;

other: PUNCTUATION  {EVMDD_SMC_Generator.addCase();}
;

perm: PERM LSBRACKET requires_ensures_clause RSBRACKET 
    | attype
;
attype:ap=at_ap_permission (LSBRACKET (REQUIRES EQUAL QUOTE restate=typestate QUOTE)? (PUNCTUATION)? (ENSURES EQUAL QUOTE enstate=typestate QUOTE)? (PUNCTUATION usevalue)?)? {
                                                        String str=$ap.text;
                                                        str=str.substring(1); //this remove @
                                                        
                                                        String re_state=$restate.text;
                                                        if (re_state==null)
                                                            re_state="alive";
                                                       
                                                        String en_state=$enstate.text;
                                                        if (en_state==null)
                                                            en_state="alive";
                                                        
                                                        EVMDD_SMC_Generator.addRequiresAP_TS(str.toString(),re_state);
                                                        EVMDD_SMC_Generator.addEnsuresAP_TS(str.toString(),en_state);
      }
;

usevalue: USE EQUAL USEFIELDS 
         |VALUE EQUAL QUOTE ID QUOTE
  
;
  
requires_ensures_clause: (requires_clause)? (PUNCTUATION)? (ensures_clause)?
;

requires_clause : REQUIRES EQUAL QUOTE re_accesspermission_typestates (AND re_accesspermission_typestates)* QUOTE
;

ensures_clause: ENSURES EQUAL QUOTE en_accesspermission_typestates (AND en_accesspermission_typestates)* QUOTE
;

re_accesspermission_typestates : ap=accesspermission LSBRACKET THIS RSBRACKET (IN st=typestate)? {
                                                                                        String en_state=$st.text;
                                                                                        if (en_state==null)
                                                                                            en_state="alive";
                                                                                        EVMDD_SMC_Generator.addRequiresAP_TS($ap.text,en_state);
                                                                            }
                               |ap=accesspermission LSBRACKET para=PARAM RSBRACKET (IN st=typestate)? {
                                                                            String re_state=$st.text;
                                                                            if (re_state==null)
                                                                                      re_state="alive";
                                                                            String param_number=$para.text;
                                                                            param_number=param_number.substring(1); //this remove #
                                                                            EVMDD_SMC_Generator.addRequiresParam_AP_TS($ap.text,re_state,param_number);
                                                                            } 
                               | para=PARAM OPERATOR ID     /*#0!=null*/                                      
                                                                            
;
en_accesspermission_typestates : ap=accesspermission LSBRACKET THIS RSBRACKET (IN st=typestate)? { 
                                                                             String en_state=$st.text;
                                                                             if (en_state==null)
                                                                                 en_state="alive";
                                                                            EVMDD_SMC_Generator.addEnsuresAP_TS($ap.text,en_state);
                                                                            }
                               |ap=accesspermission LSBRACKET RESULT RSBRACKET (IN st=typestate)? { 
                                                                             String en_state=$st.text;
                                                                             if (en_state==null)
                                                                                 en_state="alive";
                                                                            EVMDD_SMC_Generator.addEnsuresResult_AP_TS($ap.text,en_state);
                                                                            }
                               |ap=accesspermission LSBRACKET para=PARAM RSBRACKET (IN st=typestate)? {
                                                                            String en_state=$st.text;
                                                                            if (en_state==null)
                                                                                  en_state="alive";
                                                                            String param_number=$para.text;
                                                                            param_number=param_number.substring(1); //this remove #
                                                                            EVMDD_SMC_Generator.addEnsuresParam_AP_TS($ap.text,en_state,param_number);
                                                                            } 
                               | para=PARAM OPERATOR ID     /*#0!=null*/                                              
;

typestate: ID
;

at_ap_permission  :
                AT_UNIQUE      
                |AT_FULL        
                |AT_SHARE       
                |AT_PURE       
                |AT_IMMUTABLE   
;
              
accesspermission: UNIQUE         
                |FULL            
                |SHARE          
                |PURE            
                |IMMUTABLE
                |NONE      
;

