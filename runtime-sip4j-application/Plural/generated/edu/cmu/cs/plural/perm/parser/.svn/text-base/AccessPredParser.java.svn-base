// $ANTLR 3.0.1 D:\\workspace-unimod\\Plural\\permission-parser\\AccessPred.g 2009-07-17 14:42:48

package edu.cmu.cs.plural.perm.parser;

import edu.cmu.cs.plural.perm.parser.*;
import edu.cmu.cs.plural.fractions.Fraction;
import edu.cmu.cs.plural.concrete.PluralParseError;
import java.util.LinkedList;


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

public class AccessPredParser extends Parser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "TRUE", "FALSE", "ID", "IN", "IMPLIES", "DEQ", "NEQ", "NUMBER", "NULL", "ALT", "WITH", "TENS", "ONE", "ZERO", "LETTER", "DIGIT", "WHITESPACE", "'('", "')'", "','", "'#'", "'!'", "'.super'", "'.this'", "'fr'", "'fl'", "'dp'", "'df'", "'='", "'/'"
    };
    public static final int IMPLIES=8;
    public static final int LETTER=18;
    public static final int NULL=12;
    public static final int NUMBER=11;
    public static final int DEQ=9;
    public static final int WHITESPACE=20;
    public static final int ONE=16;
    public static final int ID=6;
    public static final int EOF=-1;
    public static final int TRUE=4;
    public static final int ZERO=17;
    public static final int NEQ=10;
    public static final int IN=7;
    public static final int ALT=13;
    public static final int TENS=15;
    public static final int DIGIT=19;
    public static final int FALSE=5;
    public static final int WITH=14;

        public AccessPredParser(TokenStream input) {
            super(input);
        }
        

    public String[] getTokenNames() { return tokenNames; }
    public String getGrammarFileName() { return "D:\\workspace-unimod\\Plural\\permission-parser\\AccessPred.g"; }

    
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



    // $ANTLR start start
    // D:\\workspace-unimod\\Plural\\permission-parser\\AccessPred.g:116:1: start returns [TopLevelPred result] : (p= perm EOF | TRUE EOF | FALSE EOF | EOF );
    public final TopLevelPred start() throws RecognitionException {
        TopLevelPred result = null;

        AccessPred p = null;


        try {
            // D:\\workspace-unimod\\Plural\\permission-parser\\AccessPred.g:117:2: (p= perm EOF | TRUE EOF | FALSE EOF | EOF )
            int alt1=4;
            switch ( input.LA(1) ) {
            case ID:
            case NULL:
            case 21:
            case 24:
                {
                alt1=1;
                }
                break;
            case TRUE:
                {
                int LA1_2 = input.LA(2);

                if ( (LA1_2==EOF) ) {
                    alt1=2;
                }
                else if ( ((LA1_2>=DEQ && LA1_2<=NEQ)) ) {
                    alt1=1;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("116:1: start returns [TopLevelPred result] : (p= perm EOF | TRUE EOF | FALSE EOF | EOF );", 1, 2, input);

                    throw nvae;
                }
                }
                break;
            case FALSE:
                {
                int LA1_3 = input.LA(2);

                if ( (LA1_3==EOF) ) {
                    alt1=3;
                }
                else if ( ((LA1_3>=DEQ && LA1_3<=NEQ)) ) {
                    alt1=1;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("116:1: start returns [TopLevelPred result] : (p= perm EOF | TRUE EOF | FALSE EOF | EOF );", 1, 3, input);

                    throw nvae;
                }
                }
                break;
            case EOF:
                {
                alt1=4;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("116:1: start returns [TopLevelPred result] : (p= perm EOF | TRUE EOF | FALSE EOF | EOF );", 1, 0, input);

                throw nvae;
            }

            switch (alt1) {
                case 1 :
                    // D:\\workspace-unimod\\Plural\\permission-parser\\AccessPred.g:117:4: p= perm EOF
                    {
                    pushFollow(FOLLOW_perm_in_start43);
                    p=perm();
                    _fsp--;

                    match(input,EOF,FOLLOW_EOF_in_start45); 
                     result = p; 

                    }
                    break;
                case 2 :
                    // D:\\workspace-unimod\\Plural\\permission-parser\\AccessPred.g:118:4: TRUE EOF
                    {
                    match(input,TRUE,FOLLOW_TRUE_in_start52); 
                    match(input,EOF,FOLLOW_EOF_in_start54); 
                     result = null; 

                    }
                    break;
                case 3 :
                    // D:\\workspace-unimod\\Plural\\permission-parser\\AccessPred.g:119:4: FALSE EOF
                    {
                    match(input,FALSE,FOLLOW_FALSE_in_start61); 
                    match(input,EOF,FOLLOW_EOF_in_start63); 
                     result = TopLevelPred.Impossible.getInstance(); 

                    }
                    break;
                case 4 :
                    // D:\\workspace-unimod\\Plural\\permission-parser\\AccessPred.g:120:4: EOF
                    {
                    match(input,EOF,FOLLOW_EOF_in_start70); 
                     result = EmptyPredicate.getInstance(); 

                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return result;
    }
    // $ANTLR end start


    // $ANTLR start access_pred
    // D:\\workspace-unimod\\Plural\\permission-parser\\AccessPred.g:123:1: access_pred returns [AccessPred result] : (i1= ID '(' i2= ref_expr ')' | i1= ID '(' i2= ref_expr ')' IN sl= state_list | i1= ID '(' i2= ref_expr ',' i3= ID ')' | i1= ID '(' i2= ref_expr ',' i3= ID ')' IN sl= state_list | i1= ID '(' i2= ref_expr ',' i3= ID ',' f= fractions ')' | i1= ID '(' i2= ref_expr ',' i3= ID ',' f= fractions ')' IN sl= state_list );
    public final AccessPred access_pred() throws RecognitionException {
        AccessPred result = null;

        Token i1=null;
        Token i3=null;
        RefExpr i2 = null;

        List<String> sl = null;

        TempFractionMap f = null;


        try {
            // D:\\workspace-unimod\\Plural\\permission-parser\\AccessPred.g:124:2: (i1= ID '(' i2= ref_expr ')' | i1= ID '(' i2= ref_expr ')' IN sl= state_list | i1= ID '(' i2= ref_expr ',' i3= ID ')' | i1= ID '(' i2= ref_expr ',' i3= ID ')' IN sl= state_list | i1= ID '(' i2= ref_expr ',' i3= ID ',' f= fractions ')' | i1= ID '(' i2= ref_expr ',' i3= ID ',' f= fractions ')' IN sl= state_list )
            int alt2=6;
            alt2 = dfa2.predict(input);
            switch (alt2) {
                case 1 :
                    // D:\\workspace-unimod\\Plural\\permission-parser\\AccessPred.g:124:4: i1= ID '(' i2= ref_expr ')'
                    {
                    i1=(Token)input.LT(1);
                    match(input,ID,FOLLOW_ID_in_access_pred89); 
                    match(input,21,FOLLOW_21_in_access_pred91); 
                    pushFollow(FOLLOW_ref_expr_in_access_pred95);
                    i2=ref_expr();
                    _fsp--;

                    match(input,22,FOLLOW_22_in_access_pred97); 
                     result = new TempPermission(i1.getText(),i2,null,null,null); 

                    }
                    break;
                case 2 :
                    // D:\\workspace-unimod\\Plural\\permission-parser\\AccessPred.g:126:4: i1= ID '(' i2= ref_expr ')' IN sl= state_list
                    {
                    i1=(Token)input.LT(1);
                    match(input,ID,FOLLOW_ID_in_access_pred110); 
                    match(input,21,FOLLOW_21_in_access_pred112); 
                    pushFollow(FOLLOW_ref_expr_in_access_pred116);
                    i2=ref_expr();
                    _fsp--;

                    match(input,22,FOLLOW_22_in_access_pred118); 
                    match(input,IN,FOLLOW_IN_in_access_pred120); 
                    pushFollow(FOLLOW_state_list_in_access_pred124);
                    sl=state_list();
                    _fsp--;

                     result = new TempPermission(i1.getText(),i2,null,null,sl); 

                    }
                    break;
                case 3 :
                    // D:\\workspace-unimod\\Plural\\permission-parser\\AccessPred.g:128:4: i1= ID '(' i2= ref_expr ',' i3= ID ')'
                    {
                    i1=(Token)input.LT(1);
                    match(input,ID,FOLLOW_ID_in_access_pred137); 
                    match(input,21,FOLLOW_21_in_access_pred139); 
                    pushFollow(FOLLOW_ref_expr_in_access_pred143);
                    i2=ref_expr();
                    _fsp--;

                    match(input,23,FOLLOW_23_in_access_pred145); 
                    i3=(Token)input.LT(1);
                    match(input,ID,FOLLOW_ID_in_access_pred149); 
                    match(input,22,FOLLOW_22_in_access_pred151); 
                     result = new TempPermission(i1.getText(),i2,i3.getText(),null,null); 

                    }
                    break;
                case 4 :
                    // D:\\workspace-unimod\\Plural\\permission-parser\\AccessPred.g:130:4: i1= ID '(' i2= ref_expr ',' i3= ID ')' IN sl= state_list
                    {
                    i1=(Token)input.LT(1);
                    match(input,ID,FOLLOW_ID_in_access_pred164); 
                    match(input,21,FOLLOW_21_in_access_pred166); 
                    pushFollow(FOLLOW_ref_expr_in_access_pred170);
                    i2=ref_expr();
                    _fsp--;

                    match(input,23,FOLLOW_23_in_access_pred172); 
                    i3=(Token)input.LT(1);
                    match(input,ID,FOLLOW_ID_in_access_pred176); 
                    match(input,22,FOLLOW_22_in_access_pred178); 
                    match(input,IN,FOLLOW_IN_in_access_pred180); 
                    pushFollow(FOLLOW_state_list_in_access_pred184);
                    sl=state_list();
                    _fsp--;

                     result = new TempPermission(i1.getText(),i2,i3.getText(),null,sl); 

                    }
                    break;
                case 5 :
                    // D:\\workspace-unimod\\Plural\\permission-parser\\AccessPred.g:132:4: i1= ID '(' i2= ref_expr ',' i3= ID ',' f= fractions ')'
                    {
                    i1=(Token)input.LT(1);
                    match(input,ID,FOLLOW_ID_in_access_pred196); 
                    match(input,21,FOLLOW_21_in_access_pred198); 
                    pushFollow(FOLLOW_ref_expr_in_access_pred202);
                    i2=ref_expr();
                    _fsp--;

                    match(input,23,FOLLOW_23_in_access_pred204); 
                    i3=(Token)input.LT(1);
                    match(input,ID,FOLLOW_ID_in_access_pred208); 
                    match(input,23,FOLLOW_23_in_access_pred210); 
                    pushFollow(FOLLOW_fractions_in_access_pred214);
                    f=fractions();
                    _fsp--;

                    match(input,22,FOLLOW_22_in_access_pred216); 
                     result = new TempPermission(i1.getText(),i2,i3.getText(),f.getMap(),null); 

                    }
                    break;
                case 6 :
                    // D:\\workspace-unimod\\Plural\\permission-parser\\AccessPred.g:134:4: i1= ID '(' i2= ref_expr ',' i3= ID ',' f= fractions ')' IN sl= state_list
                    {
                    i1=(Token)input.LT(1);
                    match(input,ID,FOLLOW_ID_in_access_pred228); 
                    match(input,21,FOLLOW_21_in_access_pred230); 
                    pushFollow(FOLLOW_ref_expr_in_access_pred234);
                    i2=ref_expr();
                    _fsp--;

                    match(input,23,FOLLOW_23_in_access_pred236); 
                    i3=(Token)input.LT(1);
                    match(input,ID,FOLLOW_ID_in_access_pred240); 
                    match(input,23,FOLLOW_23_in_access_pred242); 
                    pushFollow(FOLLOW_fractions_in_access_pred246);
                    f=fractions();
                    _fsp--;

                    match(input,22,FOLLOW_22_in_access_pred248); 
                    match(input,IN,FOLLOW_IN_in_access_pred250); 
                    pushFollow(FOLLOW_state_list_in_access_pred254);
                    sl=state_list();
                    _fsp--;

                     result = new TempPermission(i1.getText(),i2,i3.getText(),f.getMap(),sl); 

                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return result;
    }
    // $ANTLR end access_pred


    // $ANTLR start implies
    // D:\\workspace-unimod\\Plural\\permission-parser\\AccessPred.g:139:1: implies returns [AccessPred result] : ant= bin_expr IMPLIES cons= lowest ;
    public final AccessPred implies() throws RecognitionException {
        AccessPred result = null;

        BinaryExpr ant = null;

        AccessPred cons = null;


        try {
            // D:\\workspace-unimod\\Plural\\permission-parser\\AccessPred.g:140:2: (ant= bin_expr IMPLIES cons= lowest )
            // D:\\workspace-unimod\\Plural\\permission-parser\\AccessPred.g:140:4: ant= bin_expr IMPLIES cons= lowest
            {
            pushFollow(FOLLOW_bin_expr_in_implies278);
            ant=bin_expr();
            _fsp--;

            match(input,IMPLIES,FOLLOW_IMPLIES_in_implies280); 
            pushFollow(FOLLOW_lowest_in_implies284);
            cons=lowest();
            _fsp--;

             result = new PermissionImplication(ant,cons); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return result;
    }
    // $ANTLR end implies


    // $ANTLR start state_only
    // D:\\workspace-unimod\\Plural\\permission-parser\\AccessPred.g:143:1: state_only returns [AccessPred result] : ref= ref_expr IN state= ID ;
    public final AccessPred state_only() throws RecognitionException {
        AccessPred result = null;

        Token state=null;
        RefExpr ref = null;


        try {
            // D:\\workspace-unimod\\Plural\\permission-parser\\AccessPred.g:144:2: (ref= ref_expr IN state= ID )
            // D:\\workspace-unimod\\Plural\\permission-parser\\AccessPred.g:144:4: ref= ref_expr IN state= ID
            {
            pushFollow(FOLLOW_ref_expr_in_state_only303);
            ref=ref_expr();
            _fsp--;

            match(input,IN,FOLLOW_IN_in_state_only305); 
            state=(Token)input.LT(1);
            match(input,ID,FOLLOW_ID_in_state_only309); 
            result = new StateOnly(ref, state.getText());

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return result;
    }
    // $ANTLR end state_only


    // $ANTLR start bin_expr_ap
    // D:\\workspace-unimod\\Plural\\permission-parser\\AccessPred.g:148:1: bin_expr_ap returns [AccessPred result] : e= bin_expr ;
    public final AccessPred bin_expr_ap() throws RecognitionException {
        AccessPred result = null;

        BinaryExpr e = null;


        try {
            // D:\\workspace-unimod\\Plural\\permission-parser\\AccessPred.g:149:2: (e= bin_expr )
            // D:\\workspace-unimod\\Plural\\permission-parser\\AccessPred.g:149:4: e= bin_expr
            {
            pushFollow(FOLLOW_bin_expr_in_bin_expr_ap330);
            e=bin_expr();
            _fsp--;

            result = new BinaryExprAP(e);

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return result;
    }
    // $ANTLR end bin_expr_ap


    // $ANTLR start bin_expr
    // D:\\workspace-unimod\\Plural\\permission-parser\\AccessPred.g:152:1: bin_expr returns [BinaryExpr result] : (e1= primary_expr DEQ e2= primary_expr | e1= primary_expr NEQ e2= primary_expr );
    public final BinaryExpr bin_expr() throws RecognitionException {
        BinaryExpr result = null;

        PrimaryExpr e1 = null;

        PrimaryExpr e2 = null;


        try {
            // D:\\workspace-unimod\\Plural\\permission-parser\\AccessPred.g:153:2: (e1= primary_expr DEQ e2= primary_expr | e1= primary_expr NEQ e2= primary_expr )
            int alt3=2;
            switch ( input.LA(1) ) {
            case ID:
                {
                int LA3_1 = input.LA(2);

                if ( (LA3_1==DEQ) ) {
                    alt3=1;
                }
                else if ( (LA3_1==NEQ) ) {
                    alt3=2;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("152:1: bin_expr returns [BinaryExpr result] : (e1= primary_expr DEQ e2= primary_expr | e1= primary_expr NEQ e2= primary_expr );", 3, 1, input);

                    throw nvae;
                }
                }
                break;
            case 24:
                {
                int LA3_2 = input.LA(2);

                if ( (LA3_2==NUMBER) ) {
                    int LA3_8 = input.LA(3);

                    if ( (LA3_8==NEQ) ) {
                        alt3=2;
                    }
                    else if ( (LA3_8==DEQ) ) {
                        alt3=1;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("152:1: bin_expr returns [BinaryExpr result] : (e1= primary_expr DEQ e2= primary_expr | e1= primary_expr NEQ e2= primary_expr );", 3, 8, input);

                        throw nvae;
                    }
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("152:1: bin_expr returns [BinaryExpr result] : (e1= primary_expr DEQ e2= primary_expr | e1= primary_expr NEQ e2= primary_expr );", 3, 2, input);

                    throw nvae;
                }
                }
                break;
            case NULL:
                {
                int LA3_3 = input.LA(2);

                if ( (LA3_3==NEQ) ) {
                    alt3=2;
                }
                else if ( (LA3_3==DEQ) ) {
                    alt3=1;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("152:1: bin_expr returns [BinaryExpr result] : (e1= primary_expr DEQ e2= primary_expr | e1= primary_expr NEQ e2= primary_expr );", 3, 3, input);

                    throw nvae;
                }
                }
                break;
            case TRUE:
                {
                int LA3_4 = input.LA(2);

                if ( (LA3_4==NEQ) ) {
                    alt3=2;
                }
                else if ( (LA3_4==DEQ) ) {
                    alt3=1;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("152:1: bin_expr returns [BinaryExpr result] : (e1= primary_expr DEQ e2= primary_expr | e1= primary_expr NEQ e2= primary_expr );", 3, 4, input);

                    throw nvae;
                }
                }
                break;
            case FALSE:
                {
                int LA3_5 = input.LA(2);

                if ( (LA3_5==DEQ) ) {
                    alt3=1;
                }
                else if ( (LA3_5==NEQ) ) {
                    alt3=2;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("152:1: bin_expr returns [BinaryExpr result] : (e1= primary_expr DEQ e2= primary_expr | e1= primary_expr NEQ e2= primary_expr );", 3, 5, input);

                    throw nvae;
                }
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("152:1: bin_expr returns [BinaryExpr result] : (e1= primary_expr DEQ e2= primary_expr | e1= primary_expr NEQ e2= primary_expr );", 3, 0, input);

                throw nvae;
            }

            switch (alt3) {
                case 1 :
                    // D:\\workspace-unimod\\Plural\\permission-parser\\AccessPred.g:153:4: e1= primary_expr DEQ e2= primary_expr
                    {
                    pushFollow(FOLLOW_primary_expr_in_bin_expr349);
                    e1=primary_expr();
                    _fsp--;

                    match(input,DEQ,FOLLOW_DEQ_in_bin_expr351); 
                    pushFollow(FOLLOW_primary_expr_in_bin_expr355);
                    e2=primary_expr();
                    _fsp--;

                    result = new EqualsExpr(e1,e2);

                    }
                    break;
                case 2 :
                    // D:\\workspace-unimod\\Plural\\permission-parser\\AccessPred.g:154:4: e1= primary_expr NEQ e2= primary_expr
                    {
                    pushFollow(FOLLOW_primary_expr_in_bin_expr364);
                    e1=primary_expr();
                    _fsp--;

                    match(input,NEQ,FOLLOW_NEQ_in_bin_expr366); 
                    pushFollow(FOLLOW_primary_expr_in_bin_expr370);
                    e2=primary_expr();
                    _fsp--;

                    result = new NotEqualsExpr(e1,e2);

                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return result;
    }
    // $ANTLR end bin_expr


    // $ANTLR start primary_expr
    // D:\\workspace-unimod\\Plural\\permission-parser\\AccessPred.g:157:1: primary_expr returns [PrimaryExpr result] : (i= ID | '#' num= NUMBER | NULL | TRUE | FALSE );
    public final PrimaryExpr primary_expr() throws RecognitionException {
        PrimaryExpr result = null;

        Token i=null;
        Token num=null;

        try {
            // D:\\workspace-unimod\\Plural\\permission-parser\\AccessPred.g:158:2: (i= ID | '#' num= NUMBER | NULL | TRUE | FALSE )
            int alt4=5;
            switch ( input.LA(1) ) {
            case ID:
                {
                alt4=1;
                }
                break;
            case 24:
                {
                alt4=2;
                }
                break;
            case NULL:
                {
                alt4=3;
                }
                break;
            case TRUE:
                {
                alt4=4;
                }
                break;
            case FALSE:
                {
                alt4=5;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("157:1: primary_expr returns [PrimaryExpr result] : (i= ID | '#' num= NUMBER | NULL | TRUE | FALSE );", 4, 0, input);

                throw nvae;
            }

            switch (alt4) {
                case 1 :
                    // D:\\workspace-unimod\\Plural\\permission-parser\\AccessPred.g:158:4: i= ID
                    {
                    i=(Token)input.LT(1);
                    match(input,ID,FOLLOW_ID_in_primary_expr389); 
                    result = new Identifier(i.getText());

                    }
                    break;
                case 2 :
                    // D:\\workspace-unimod\\Plural\\permission-parser\\AccessPred.g:159:4: '#' num= NUMBER
                    {
                    match(input,24,FOLLOW_24_in_primary_expr397); 
                    num=(Token)input.LT(1);
                    match(input,NUMBER,FOLLOW_NUMBER_in_primary_expr401); 
                    result = new ParamReference(num.getText());

                    }
                    break;
                case 3 :
                    // D:\\workspace-unimod\\Plural\\permission-parser\\AccessPred.g:160:4: NULL
                    {
                    match(input,NULL,FOLLOW_NULL_in_primary_expr408); 
                    result = Null.getInstance();

                    }
                    break;
                case 4 :
                    // D:\\workspace-unimod\\Plural\\permission-parser\\AccessPred.g:161:4: TRUE
                    {
                    match(input,TRUE,FOLLOW_TRUE_in_primary_expr416); 
                    result = BoolLiteral.getTrueInstance();

                    }
                    break;
                case 5 :
                    // D:\\workspace-unimod\\Plural\\permission-parser\\AccessPred.g:162:4: FALSE
                    {
                    match(input,FALSE,FOLLOW_FALSE_in_primary_expr424); 
                    result = BoolLiteral.getFalseInstance();

                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return result;
    }
    // $ANTLR end primary_expr


    // $ANTLR start ref_expr
    // D:\\workspace-unimod\\Plural\\permission-parser\\AccessPred.g:165:1: ref_expr returns [RefExpr result] : (i= ID ( | '!' use= use_qualifier | '.super' | '.this' ( | '!' use2= use_qualifier ) ) | '#' num= NUMBER );
    public final RefExpr ref_expr() throws RecognitionException {
        RefExpr result = null;

        Token i=null;
        Token num=null;
        PermissionUse use = null;

        PermissionUse use2 = null;


        try {
            // D:\\workspace-unimod\\Plural\\permission-parser\\AccessPred.g:166:2: (i= ID ( | '!' use= use_qualifier | '.super' | '.this' ( | '!' use2= use_qualifier ) ) | '#' num= NUMBER )
            int alt7=2;
            int LA7_0 = input.LA(1);

            if ( (LA7_0==ID) ) {
                alt7=1;
            }
            else if ( (LA7_0==24) ) {
                alt7=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("165:1: ref_expr returns [RefExpr result] : (i= ID ( | '!' use= use_qualifier | '.super' | '.this' ( | '!' use2= use_qualifier ) ) | '#' num= NUMBER );", 7, 0, input);

                throw nvae;
            }
            switch (alt7) {
                case 1 :
                    // D:\\workspace-unimod\\Plural\\permission-parser\\AccessPred.g:166:5: i= ID ( | '!' use= use_qualifier | '.super' | '.this' ( | '!' use2= use_qualifier ) )
                    {
                    i=(Token)input.LT(1);
                    match(input,ID,FOLLOW_ID_in_ref_expr444); 
                    // D:\\workspace-unimod\\Plural\\permission-parser\\AccessPred.g:167:3: ( | '!' use= use_qualifier | '.super' | '.this' ( | '!' use2= use_qualifier ) )
                    int alt6=4;
                    switch ( input.LA(1) ) {
                    case IN:
                    case 22:
                    case 23:
                        {
                        alt6=1;
                        }
                        break;
                    case 25:
                        {
                        alt6=2;
                        }
                        break;
                    case 26:
                        {
                        alt6=3;
                        }
                        break;
                    case 27:
                        {
                        alt6=4;
                        }
                        break;
                    default:
                        NoViableAltException nvae =
                            new NoViableAltException("167:3: ( | '!' use= use_qualifier | '.super' | '.this' ( | '!' use2= use_qualifier ) )", 6, 0, input);

                        throw nvae;
                    }

                    switch (alt6) {
                        case 1 :
                            // D:\\workspace-unimod\\Plural\\permission-parser\\AccessPred.g:167:5: 
                            {
                            result = new Identifier(i.getText());

                            }
                            break;
                        case 2 :
                            // D:\\workspace-unimod\\Plural\\permission-parser\\AccessPred.g:168:6: '!' use= use_qualifier
                            {
                            match(input,25,FOLLOW_25_in_ref_expr458); 
                            pushFollow(FOLLOW_use_qualifier_in_ref_expr462);
                            use=use_qualifier();
                            _fsp--;

                            result = new Identifier(i.getText(), use);

                            }
                            break;
                        case 3 :
                            // D:\\workspace-unimod\\Plural\\permission-parser\\AccessPred.g:170:5: '.super'
                            {
                            match(input,26,FOLLOW_26_in_ref_expr473); 
                            result = Identifier.qualified(i.getText(), "super");

                            }
                            break;
                        case 4 :
                            // D:\\workspace-unimod\\Plural\\permission-parser\\AccessPred.g:172:5: '.this' ( | '!' use2= use_qualifier )
                            {
                            match(input,27,FOLLOW_27_in_ref_expr485); 
                            // D:\\workspace-unimod\\Plural\\permission-parser\\AccessPred.g:173:4: ( | '!' use2= use_qualifier )
                            int alt5=2;
                            int LA5_0 = input.LA(1);

                            if ( (LA5_0==IN||(LA5_0>=22 && LA5_0<=23)) ) {
                                alt5=1;
                            }
                            else if ( (LA5_0==25) ) {
                                alt5=2;
                            }
                            else {
                                NoViableAltException nvae =
                                    new NoViableAltException("173:4: ( | '!' use2= use_qualifier )", 5, 0, input);

                                throw nvae;
                            }
                            switch (alt5) {
                                case 1 :
                                    // D:\\workspace-unimod\\Plural\\permission-parser\\AccessPred.g:173:6: 
                                    {
                                    result = Identifier.qualified(i.getText(), "this");

                                    }
                                    break;
                                case 2 :
                                    // D:\\workspace-unimod\\Plural\\permission-parser\\AccessPred.g:174:6: '!' use2= use_qualifier
                                    {
                                    match(input,25,FOLLOW_25_in_ref_expr500); 
                                    pushFollow(FOLLOW_use_qualifier_in_ref_expr504);
                                    use2=use_qualifier();
                                    _fsp--;

                                    result = Identifier.qualifiedThis(i.getText(), use2);

                                    }
                                    break;

                            }


                            }
                            break;

                    }


                    }
                    break;
                case 2 :
                    // D:\\workspace-unimod\\Plural\\permission-parser\\AccessPred.g:178:4: '#' num= NUMBER
                    {
                    match(input,24,FOLLOW_24_in_ref_expr524); 
                    num=(Token)input.LT(1);
                    match(input,NUMBER,FOLLOW_NUMBER_in_ref_expr528); 
                    result = new ParamReference(num.getText());

                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return result;
    }
    // $ANTLR end ref_expr


    // $ANTLR start use_qualifier
    // D:\\workspace-unimod\\Plural\\permission-parser\\AccessPred.g:181:1: use_qualifier returns [PermissionUse result] : ( 'fr' | 'fl' | 'dp' | 'df' );
    public final PermissionUse use_qualifier() throws RecognitionException {
        PermissionUse result = null;

        try {
            // D:\\workspace-unimod\\Plural\\permission-parser\\AccessPred.g:182:2: ( 'fr' | 'fl' | 'dp' | 'df' )
            int alt8=4;
            switch ( input.LA(1) ) {
            case 28:
                {
                alt8=1;
                }
                break;
            case 29:
                {
                alt8=2;
                }
                break;
            case 30:
                {
                alt8=3;
                }
                break;
            case 31:
                {
                alt8=4;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("181:1: use_qualifier returns [PermissionUse result] : ( 'fr' | 'fl' | 'dp' | 'df' );", 8, 0, input);

                throw nvae;
            }

            switch (alt8) {
                case 1 :
                    // D:\\workspace-unimod\\Plural\\permission-parser\\AccessPred.g:182:4: 'fr'
                    {
                    match(input,28,FOLLOW_28_in_use_qualifier546); 
                    result = PermissionUse.FIELDS;

                    }
                    break;
                case 2 :
                    // D:\\workspace-unimod\\Plural\\permission-parser\\AccessPred.g:183:4: 'fl'
                    {
                    match(input,29,FOLLOW_29_in_use_qualifier553); 
                    result = PermissionUse.FIELDS;

                    }
                    break;
                case 3 :
                    // D:\\workspace-unimod\\Plural\\permission-parser\\AccessPred.g:184:4: 'dp'
                    {
                    match(input,30,FOLLOW_30_in_use_qualifier560); 
                    result = PermissionUse.DISPATCH;

                    }
                    break;
                case 4 :
                    // D:\\workspace-unimod\\Plural\\permission-parser\\AccessPred.g:185:4: 'df'
                    {
                    match(input,31,FOLLOW_31_in_use_qualifier567); 
                    result = PermissionUse.DISP_FIELDS;

                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return result;
    }
    // $ANTLR end use_qualifier


    // $ANTLR start perm
    // D:\\workspace-unimod\\Plural\\permission-parser\\AccessPred.g:189:1: perm returns [AccessPred result] : (left= wither ) ( ALT right= wither )* ;
    public final AccessPred perm() throws RecognitionException {
        AccessPred result = null;

        AccessPred left = null;

        AccessPred right = null;


        
        List<AccessPred> temp = new ArrayList<AccessPred>();

        try {
            // D:\\workspace-unimod\\Plural\\permission-parser\\AccessPred.g:196:2: ( (left= wither ) ( ALT right= wither )* )
            // D:\\workspace-unimod\\Plural\\permission-parser\\AccessPred.g:196:4: (left= wither ) ( ALT right= wither )*
            {
            // D:\\workspace-unimod\\Plural\\permission-parser\\AccessPred.g:196:4: (left= wither )
            // D:\\workspace-unimod\\Plural\\permission-parser\\AccessPred.g:196:5: left= wither
            {
            pushFollow(FOLLOW_wither_in_perm599);
            left=wither();
            _fsp--;

            temp.add(left); 

            }

            // D:\\workspace-unimod\\Plural\\permission-parser\\AccessPred.g:196:37: ( ALT right= wither )*
            loop9:
            do {
                int alt9=2;
                int LA9_0 = input.LA(1);

                if ( (LA9_0==ALT) ) {
                    alt9=1;
                }


                switch (alt9) {
            	case 1 :
            	    // D:\\workspace-unimod\\Plural\\permission-parser\\AccessPred.g:196:38: ALT right= wither
            	    {
            	    match(input,ALT,FOLLOW_ALT_in_perm605); 
            	    pushFollow(FOLLOW_wither_in_perm609);
            	    right=wither();
            	    _fsp--;

            	    temp.add(right);

            	    }
            	    break;

            	default :
            	    break loop9;
                }
            } while (true);


            }

            
            result = treeOfDisjList(temp);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return result;
    }
    // $ANTLR end perm


    // $ANTLR start wither
    // D:\\workspace-unimod\\Plural\\permission-parser\\AccessPred.g:199:1: wither returns [AccessPred result] : (left= conjs ) ( WITH right= conjs )* ;
    public final AccessPred wither() throws RecognitionException {
        AccessPred result = null;

        AccessPred left = null;

        AccessPred right = null;


        
        List<AccessPred> temp = new ArrayList<AccessPred>();

        try {
            // D:\\workspace-unimod\\Plural\\permission-parser\\AccessPred.g:206:2: ( (left= conjs ) ( WITH right= conjs )* )
            // D:\\workspace-unimod\\Plural\\permission-parser\\AccessPred.g:206:4: (left= conjs ) ( WITH right= conjs )*
            {
            // D:\\workspace-unimod\\Plural\\permission-parser\\AccessPred.g:206:4: (left= conjs )
            // D:\\workspace-unimod\\Plural\\permission-parser\\AccessPred.g:206:5: left= conjs
            {
            pushFollow(FOLLOW_conjs_in_wither641);
            left=conjs();
            _fsp--;

            temp.add(left); 

            }

            // D:\\workspace-unimod\\Plural\\permission-parser\\AccessPred.g:206:36: ( WITH right= conjs )*
            loop10:
            do {
                int alt10=2;
                int LA10_0 = input.LA(1);

                if ( (LA10_0==WITH) ) {
                    alt10=1;
                }


                switch (alt10) {
            	case 1 :
            	    // D:\\workspace-unimod\\Plural\\permission-parser\\AccessPred.g:206:37: WITH right= conjs
            	    {
            	    match(input,WITH,FOLLOW_WITH_in_wither647); 
            	    pushFollow(FOLLOW_conjs_in_wither651);
            	    right=conjs();
            	    _fsp--;

            	    temp.add(right);

            	    }
            	    break;

            	default :
            	    break loop10;
                }
            } while (true);


            }

            
            result = treeOfWithList(temp);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return result;
    }
    // $ANTLR end wither


    // $ANTLR start conjs
    // D:\\workspace-unimod\\Plural\\permission-parser\\AccessPred.g:209:1: conjs returns [AccessPred result] : (left= lowest ) ( TENS right= lowest )* ;
    public final AccessPred conjs() throws RecognitionException {
        AccessPred result = null;

        AccessPred left = null;

        AccessPred right = null;


        
        List<AccessPred> temp = new ArrayList<AccessPred>();

        try {
            // D:\\workspace-unimod\\Plural\\permission-parser\\AccessPred.g:216:3: ( (left= lowest ) ( TENS right= lowest )* )
            // D:\\workspace-unimod\\Plural\\permission-parser\\AccessPred.g:216:5: (left= lowest ) ( TENS right= lowest )*
            {
            // D:\\workspace-unimod\\Plural\\permission-parser\\AccessPred.g:216:5: (left= lowest )
            // D:\\workspace-unimod\\Plural\\permission-parser\\AccessPred.g:216:6: left= lowest
            {
            pushFollow(FOLLOW_lowest_in_conjs685);
            left=lowest();
            _fsp--;

             temp.add(left); 

            }

            // D:\\workspace-unimod\\Plural\\permission-parser\\AccessPred.g:216:39: ( TENS right= lowest )*
            loop11:
            do {
                int alt11=2;
                int LA11_0 = input.LA(1);

                if ( (LA11_0==TENS) ) {
                    alt11=1;
                }


                switch (alt11) {
            	case 1 :
            	    // D:\\workspace-unimod\\Plural\\permission-parser\\AccessPred.g:216:40: TENS right= lowest
            	    {
            	    match(input,TENS,FOLLOW_TENS_in_conjs691); 
            	    pushFollow(FOLLOW_lowest_in_conjs695);
            	    right=lowest();
            	    _fsp--;

            	    temp.add(right);

            	    }
            	    break;

            	default :
            	    break loop11;
                }
            } while (true);


            }

            
            result = treeOfConjList(temp);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return result;
    }
    // $ANTLR end conjs


    // $ANTLR start lowest
    // D:\\workspace-unimod\\Plural\\permission-parser\\AccessPred.g:219:1: lowest returns [AccessPred result] : (r= access_pred | e= bin_expr_ap | s= state_only | i= implies | '(' r= perm ')' );
    public final AccessPred lowest() throws RecognitionException {
        AccessPred result = null;

        AccessPred r = null;

        AccessPred e = null;

        AccessPred s = null;

        AccessPred i = null;


        try {
            // D:\\workspace-unimod\\Plural\\permission-parser\\AccessPred.g:220:2: (r= access_pred | e= bin_expr_ap | s= state_only | i= implies | '(' r= perm ')' )
            int alt12=5;
            switch ( input.LA(1) ) {
            case ID:
                {
                switch ( input.LA(2) ) {
                case 21:
                    {
                    alt12=1;
                    }
                    break;
                case NEQ:
                    {
                    switch ( input.LA(3) ) {
                    case ID:
                        {
                        int LA12_12 = input.LA(4);

                        if ( (LA12_12==IMPLIES) ) {
                            alt12=4;
                        }
                        else if ( (LA12_12==EOF||(LA12_12>=ALT && LA12_12<=TENS)||LA12_12==22) ) {
                            alt12=2;
                        }
                        else {
                            NoViableAltException nvae =
                                new NoViableAltException("219:1: lowest returns [AccessPred result] : (r= access_pred | e= bin_expr_ap | s= state_only | i= implies | '(' r= perm ')' );", 12, 12, input);

                            throw nvae;
                        }
                        }
                        break;
                    case 24:
                        {
                        int LA12_13 = input.LA(4);

                        if ( (LA12_13==NUMBER) ) {
                            int LA12_24 = input.LA(5);

                            if ( (LA12_24==EOF||(LA12_24>=ALT && LA12_24<=TENS)||LA12_24==22) ) {
                                alt12=2;
                            }
                            else if ( (LA12_24==IMPLIES) ) {
                                alt12=4;
                            }
                            else {
                                NoViableAltException nvae =
                                    new NoViableAltException("219:1: lowest returns [AccessPred result] : (r= access_pred | e= bin_expr_ap | s= state_only | i= implies | '(' r= perm ')' );", 12, 24, input);

                                throw nvae;
                            }
                        }
                        else {
                            NoViableAltException nvae =
                                new NoViableAltException("219:1: lowest returns [AccessPred result] : (r= access_pred | e= bin_expr_ap | s= state_only | i= implies | '(' r= perm ')' );", 12, 13, input);

                            throw nvae;
                        }
                        }
                        break;
                    case NULL:
                        {
                        int LA12_14 = input.LA(4);

                        if ( (LA12_14==EOF||(LA12_14>=ALT && LA12_14<=TENS)||LA12_14==22) ) {
                            alt12=2;
                        }
                        else if ( (LA12_14==IMPLIES) ) {
                            alt12=4;
                        }
                        else {
                            NoViableAltException nvae =
                                new NoViableAltException("219:1: lowest returns [AccessPred result] : (r= access_pred | e= bin_expr_ap | s= state_only | i= implies | '(' r= perm ')' );", 12, 14, input);

                            throw nvae;
                        }
                        }
                        break;
                    case TRUE:
                        {
                        int LA12_15 = input.LA(4);

                        if ( (LA12_15==EOF||(LA12_15>=ALT && LA12_15<=TENS)||LA12_15==22) ) {
                            alt12=2;
                        }
                        else if ( (LA12_15==IMPLIES) ) {
                            alt12=4;
                        }
                        else {
                            NoViableAltException nvae =
                                new NoViableAltException("219:1: lowest returns [AccessPred result] : (r= access_pred | e= bin_expr_ap | s= state_only | i= implies | '(' r= perm ')' );", 12, 15, input);

                            throw nvae;
                        }
                        }
                        break;
                    case FALSE:
                        {
                        int LA12_16 = input.LA(4);

                        if ( (LA12_16==EOF||(LA12_16>=ALT && LA12_16<=TENS)||LA12_16==22) ) {
                            alt12=2;
                        }
                        else if ( (LA12_16==IMPLIES) ) {
                            alt12=4;
                        }
                        else {
                            NoViableAltException nvae =
                                new NoViableAltException("219:1: lowest returns [AccessPred result] : (r= access_pred | e= bin_expr_ap | s= state_only | i= implies | '(' r= perm ')' );", 12, 16, input);

                            throw nvae;
                        }
                        }
                        break;
                    default:
                        NoViableAltException nvae =
                            new NoViableAltException("219:1: lowest returns [AccessPred result] : (r= access_pred | e= bin_expr_ap | s= state_only | i= implies | '(' r= perm ')' );", 12, 8, input);

                        throw nvae;
                    }

                    }
                    break;
                case DEQ:
                    {
                    switch ( input.LA(3) ) {
                    case ID:
                        {
                        int LA12_17 = input.LA(4);

                        if ( (LA12_17==EOF||(LA12_17>=ALT && LA12_17<=TENS)||LA12_17==22) ) {
                            alt12=2;
                        }
                        else if ( (LA12_17==IMPLIES) ) {
                            alt12=4;
                        }
                        else {
                            NoViableAltException nvae =
                                new NoViableAltException("219:1: lowest returns [AccessPred result] : (r= access_pred | e= bin_expr_ap | s= state_only | i= implies | '(' r= perm ')' );", 12, 17, input);

                            throw nvae;
                        }
                        }
                        break;
                    case 24:
                        {
                        int LA12_18 = input.LA(4);

                        if ( (LA12_18==NUMBER) ) {
                            int LA12_25 = input.LA(5);

                            if ( (LA12_25==IMPLIES) ) {
                                alt12=4;
                            }
                            else if ( (LA12_25==EOF||(LA12_25>=ALT && LA12_25<=TENS)||LA12_25==22) ) {
                                alt12=2;
                            }
                            else {
                                NoViableAltException nvae =
                                    new NoViableAltException("219:1: lowest returns [AccessPred result] : (r= access_pred | e= bin_expr_ap | s= state_only | i= implies | '(' r= perm ')' );", 12, 25, input);

                                throw nvae;
                            }
                        }
                        else {
                            NoViableAltException nvae =
                                new NoViableAltException("219:1: lowest returns [AccessPred result] : (r= access_pred | e= bin_expr_ap | s= state_only | i= implies | '(' r= perm ')' );", 12, 18, input);

                            throw nvae;
                        }
                        }
                        break;
                    case NULL:
                        {
                        int LA12_19 = input.LA(4);

                        if ( (LA12_19==IMPLIES) ) {
                            alt12=4;
                        }
                        else if ( (LA12_19==EOF||(LA12_19>=ALT && LA12_19<=TENS)||LA12_19==22) ) {
                            alt12=2;
                        }
                        else {
                            NoViableAltException nvae =
                                new NoViableAltException("219:1: lowest returns [AccessPred result] : (r= access_pred | e= bin_expr_ap | s= state_only | i= implies | '(' r= perm ')' );", 12, 19, input);

                            throw nvae;
                        }
                        }
                        break;
                    case TRUE:
                        {
                        int LA12_20 = input.LA(4);

                        if ( (LA12_20==IMPLIES) ) {
                            alt12=4;
                        }
                        else if ( (LA12_20==EOF||(LA12_20>=ALT && LA12_20<=TENS)||LA12_20==22) ) {
                            alt12=2;
                        }
                        else {
                            NoViableAltException nvae =
                                new NoViableAltException("219:1: lowest returns [AccessPred result] : (r= access_pred | e= bin_expr_ap | s= state_only | i= implies | '(' r= perm ')' );", 12, 20, input);

                            throw nvae;
                        }
                        }
                        break;
                    case FALSE:
                        {
                        int LA12_21 = input.LA(4);

                        if ( (LA12_21==IMPLIES) ) {
                            alt12=4;
                        }
                        else if ( (LA12_21==EOF||(LA12_21>=ALT && LA12_21<=TENS)||LA12_21==22) ) {
                            alt12=2;
                        }
                        else {
                            NoViableAltException nvae =
                                new NoViableAltException("219:1: lowest returns [AccessPred result] : (r= access_pred | e= bin_expr_ap | s= state_only | i= implies | '(' r= perm ')' );", 12, 21, input);

                            throw nvae;
                        }
                        }
                        break;
                    default:
                        NoViableAltException nvae =
                            new NoViableAltException("219:1: lowest returns [AccessPred result] : (r= access_pred | e= bin_expr_ap | s= state_only | i= implies | '(' r= perm ')' );", 12, 9, input);

                        throw nvae;
                    }

                    }
                    break;
                case IN:
                case 25:
                case 26:
                case 27:
                    {
                    alt12=3;
                    }
                    break;
                default:
                    NoViableAltException nvae =
                        new NoViableAltException("219:1: lowest returns [AccessPred result] : (r= access_pred | e= bin_expr_ap | s= state_only | i= implies | '(' r= perm ')' );", 12, 1, input);

                    throw nvae;
                }

                }
                break;
            case 24:
                {
                int LA12_2 = input.LA(2);

                if ( (LA12_2==NUMBER) ) {
                    switch ( input.LA(3) ) {
                    case NEQ:
                        {
                        switch ( input.LA(4) ) {
                        case ID:
                            {
                            int LA12_12 = input.LA(5);

                            if ( (LA12_12==IMPLIES) ) {
                                alt12=4;
                            }
                            else if ( (LA12_12==EOF||(LA12_12>=ALT && LA12_12<=TENS)||LA12_12==22) ) {
                                alt12=2;
                            }
                            else {
                                NoViableAltException nvae =
                                    new NoViableAltException("219:1: lowest returns [AccessPred result] : (r= access_pred | e= bin_expr_ap | s= state_only | i= implies | '(' r= perm ')' );", 12, 12, input);

                                throw nvae;
                            }
                            }
                            break;
                        case 24:
                            {
                            int LA12_13 = input.LA(5);

                            if ( (LA12_13==NUMBER) ) {
                                int LA12_24 = input.LA(6);

                                if ( (LA12_24==EOF||(LA12_24>=ALT && LA12_24<=TENS)||LA12_24==22) ) {
                                    alt12=2;
                                }
                                else if ( (LA12_24==IMPLIES) ) {
                                    alt12=4;
                                }
                                else {
                                    NoViableAltException nvae =
                                        new NoViableAltException("219:1: lowest returns [AccessPred result] : (r= access_pred | e= bin_expr_ap | s= state_only | i= implies | '(' r= perm ')' );", 12, 24, input);

                                    throw nvae;
                                }
                            }
                            else {
                                NoViableAltException nvae =
                                    new NoViableAltException("219:1: lowest returns [AccessPred result] : (r= access_pred | e= bin_expr_ap | s= state_only | i= implies | '(' r= perm ')' );", 12, 13, input);

                                throw nvae;
                            }
                            }
                            break;
                        case NULL:
                            {
                            int LA12_14 = input.LA(5);

                            if ( (LA12_14==EOF||(LA12_14>=ALT && LA12_14<=TENS)||LA12_14==22) ) {
                                alt12=2;
                            }
                            else if ( (LA12_14==IMPLIES) ) {
                                alt12=4;
                            }
                            else {
                                NoViableAltException nvae =
                                    new NoViableAltException("219:1: lowest returns [AccessPred result] : (r= access_pred | e= bin_expr_ap | s= state_only | i= implies | '(' r= perm ')' );", 12, 14, input);

                                throw nvae;
                            }
                            }
                            break;
                        case TRUE:
                            {
                            int LA12_15 = input.LA(5);

                            if ( (LA12_15==EOF||(LA12_15>=ALT && LA12_15<=TENS)||LA12_15==22) ) {
                                alt12=2;
                            }
                            else if ( (LA12_15==IMPLIES) ) {
                                alt12=4;
                            }
                            else {
                                NoViableAltException nvae =
                                    new NoViableAltException("219:1: lowest returns [AccessPred result] : (r= access_pred | e= bin_expr_ap | s= state_only | i= implies | '(' r= perm ')' );", 12, 15, input);

                                throw nvae;
                            }
                            }
                            break;
                        case FALSE:
                            {
                            int LA12_16 = input.LA(5);

                            if ( (LA12_16==EOF||(LA12_16>=ALT && LA12_16<=TENS)||LA12_16==22) ) {
                                alt12=2;
                            }
                            else if ( (LA12_16==IMPLIES) ) {
                                alt12=4;
                            }
                            else {
                                NoViableAltException nvae =
                                    new NoViableAltException("219:1: lowest returns [AccessPred result] : (r= access_pred | e= bin_expr_ap | s= state_only | i= implies | '(' r= perm ')' );", 12, 16, input);

                                throw nvae;
                            }
                            }
                            break;
                        default:
                            NoViableAltException nvae =
                                new NoViableAltException("219:1: lowest returns [AccessPred result] : (r= access_pred | e= bin_expr_ap | s= state_only | i= implies | '(' r= perm ')' );", 12, 8, input);

                            throw nvae;
                        }

                        }
                        break;
                    case DEQ:
                        {
                        switch ( input.LA(4) ) {
                        case ID:
                            {
                            int LA12_17 = input.LA(5);

                            if ( (LA12_17==EOF||(LA12_17>=ALT && LA12_17<=TENS)||LA12_17==22) ) {
                                alt12=2;
                            }
                            else if ( (LA12_17==IMPLIES) ) {
                                alt12=4;
                            }
                            else {
                                NoViableAltException nvae =
                                    new NoViableAltException("219:1: lowest returns [AccessPred result] : (r= access_pred | e= bin_expr_ap | s= state_only | i= implies | '(' r= perm ')' );", 12, 17, input);

                                throw nvae;
                            }
                            }
                            break;
                        case 24:
                            {
                            int LA12_18 = input.LA(5);

                            if ( (LA12_18==NUMBER) ) {
                                int LA12_25 = input.LA(6);

                                if ( (LA12_25==IMPLIES) ) {
                                    alt12=4;
                                }
                                else if ( (LA12_25==EOF||(LA12_25>=ALT && LA12_25<=TENS)||LA12_25==22) ) {
                                    alt12=2;
                                }
                                else {
                                    NoViableAltException nvae =
                                        new NoViableAltException("219:1: lowest returns [AccessPred result] : (r= access_pred | e= bin_expr_ap | s= state_only | i= implies | '(' r= perm ')' );", 12, 25, input);

                                    throw nvae;
                                }
                            }
                            else {
                                NoViableAltException nvae =
                                    new NoViableAltException("219:1: lowest returns [AccessPred result] : (r= access_pred | e= bin_expr_ap | s= state_only | i= implies | '(' r= perm ')' );", 12, 18, input);

                                throw nvae;
                            }
                            }
                            break;
                        case NULL:
                            {
                            int LA12_19 = input.LA(5);

                            if ( (LA12_19==IMPLIES) ) {
                                alt12=4;
                            }
                            else if ( (LA12_19==EOF||(LA12_19>=ALT && LA12_19<=TENS)||LA12_19==22) ) {
                                alt12=2;
                            }
                            else {
                                NoViableAltException nvae =
                                    new NoViableAltException("219:1: lowest returns [AccessPred result] : (r= access_pred | e= bin_expr_ap | s= state_only | i= implies | '(' r= perm ')' );", 12, 19, input);

                                throw nvae;
                            }
                            }
                            break;
                        case TRUE:
                            {
                            int LA12_20 = input.LA(5);

                            if ( (LA12_20==IMPLIES) ) {
                                alt12=4;
                            }
                            else if ( (LA12_20==EOF||(LA12_20>=ALT && LA12_20<=TENS)||LA12_20==22) ) {
                                alt12=2;
                            }
                            else {
                                NoViableAltException nvae =
                                    new NoViableAltException("219:1: lowest returns [AccessPred result] : (r= access_pred | e= bin_expr_ap | s= state_only | i= implies | '(' r= perm ')' );", 12, 20, input);

                                throw nvae;
                            }
                            }
                            break;
                        case FALSE:
                            {
                            int LA12_21 = input.LA(5);

                            if ( (LA12_21==IMPLIES) ) {
                                alt12=4;
                            }
                            else if ( (LA12_21==EOF||(LA12_21>=ALT && LA12_21<=TENS)||LA12_21==22) ) {
                                alt12=2;
                            }
                            else {
                                NoViableAltException nvae =
                                    new NoViableAltException("219:1: lowest returns [AccessPred result] : (r= access_pred | e= bin_expr_ap | s= state_only | i= implies | '(' r= perm ')' );", 12, 21, input);

                                throw nvae;
                            }
                            }
                            break;
                        default:
                            NoViableAltException nvae =
                                new NoViableAltException("219:1: lowest returns [AccessPred result] : (r= access_pred | e= bin_expr_ap | s= state_only | i= implies | '(' r= perm ')' );", 12, 9, input);

                            throw nvae;
                        }

                        }
                        break;
                    case IN:
                        {
                        alt12=3;
                        }
                        break;
                    default:
                        NoViableAltException nvae =
                            new NoViableAltException("219:1: lowest returns [AccessPred result] : (r= access_pred | e= bin_expr_ap | s= state_only | i= implies | '(' r= perm ')' );", 12, 11, input);

                        throw nvae;
                    }

                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("219:1: lowest returns [AccessPred result] : (r= access_pred | e= bin_expr_ap | s= state_only | i= implies | '(' r= perm ')' );", 12, 2, input);

                    throw nvae;
                }
                }
                break;
            case NULL:
                {
                int LA12_3 = input.LA(2);

                if ( (LA12_3==NEQ) ) {
                    switch ( input.LA(3) ) {
                    case ID:
                        {
                        int LA12_12 = input.LA(4);

                        if ( (LA12_12==IMPLIES) ) {
                            alt12=4;
                        }
                        else if ( (LA12_12==EOF||(LA12_12>=ALT && LA12_12<=TENS)||LA12_12==22) ) {
                            alt12=2;
                        }
                        else {
                            NoViableAltException nvae =
                                new NoViableAltException("219:1: lowest returns [AccessPred result] : (r= access_pred | e= bin_expr_ap | s= state_only | i= implies | '(' r= perm ')' );", 12, 12, input);

                            throw nvae;
                        }
                        }
                        break;
                    case 24:
                        {
                        int LA12_13 = input.LA(4);

                        if ( (LA12_13==NUMBER) ) {
                            int LA12_24 = input.LA(5);

                            if ( (LA12_24==EOF||(LA12_24>=ALT && LA12_24<=TENS)||LA12_24==22) ) {
                                alt12=2;
                            }
                            else if ( (LA12_24==IMPLIES) ) {
                                alt12=4;
                            }
                            else {
                                NoViableAltException nvae =
                                    new NoViableAltException("219:1: lowest returns [AccessPred result] : (r= access_pred | e= bin_expr_ap | s= state_only | i= implies | '(' r= perm ')' );", 12, 24, input);

                                throw nvae;
                            }
                        }
                        else {
                            NoViableAltException nvae =
                                new NoViableAltException("219:1: lowest returns [AccessPred result] : (r= access_pred | e= bin_expr_ap | s= state_only | i= implies | '(' r= perm ')' );", 12, 13, input);

                            throw nvae;
                        }
                        }
                        break;
                    case NULL:
                        {
                        int LA12_14 = input.LA(4);

                        if ( (LA12_14==EOF||(LA12_14>=ALT && LA12_14<=TENS)||LA12_14==22) ) {
                            alt12=2;
                        }
                        else if ( (LA12_14==IMPLIES) ) {
                            alt12=4;
                        }
                        else {
                            NoViableAltException nvae =
                                new NoViableAltException("219:1: lowest returns [AccessPred result] : (r= access_pred | e= bin_expr_ap | s= state_only | i= implies | '(' r= perm ')' );", 12, 14, input);

                            throw nvae;
                        }
                        }
                        break;
                    case TRUE:
                        {
                        int LA12_15 = input.LA(4);

                        if ( (LA12_15==EOF||(LA12_15>=ALT && LA12_15<=TENS)||LA12_15==22) ) {
                            alt12=2;
                        }
                        else if ( (LA12_15==IMPLIES) ) {
                            alt12=4;
                        }
                        else {
                            NoViableAltException nvae =
                                new NoViableAltException("219:1: lowest returns [AccessPred result] : (r= access_pred | e= bin_expr_ap | s= state_only | i= implies | '(' r= perm ')' );", 12, 15, input);

                            throw nvae;
                        }
                        }
                        break;
                    case FALSE:
                        {
                        int LA12_16 = input.LA(4);

                        if ( (LA12_16==EOF||(LA12_16>=ALT && LA12_16<=TENS)||LA12_16==22) ) {
                            alt12=2;
                        }
                        else if ( (LA12_16==IMPLIES) ) {
                            alt12=4;
                        }
                        else {
                            NoViableAltException nvae =
                                new NoViableAltException("219:1: lowest returns [AccessPred result] : (r= access_pred | e= bin_expr_ap | s= state_only | i= implies | '(' r= perm ')' );", 12, 16, input);

                            throw nvae;
                        }
                        }
                        break;
                    default:
                        NoViableAltException nvae =
                            new NoViableAltException("219:1: lowest returns [AccessPred result] : (r= access_pred | e= bin_expr_ap | s= state_only | i= implies | '(' r= perm ')' );", 12, 8, input);

                        throw nvae;
                    }

                }
                else if ( (LA12_3==DEQ) ) {
                    switch ( input.LA(3) ) {
                    case ID:
                        {
                        int LA12_17 = input.LA(4);

                        if ( (LA12_17==EOF||(LA12_17>=ALT && LA12_17<=TENS)||LA12_17==22) ) {
                            alt12=2;
                        }
                        else if ( (LA12_17==IMPLIES) ) {
                            alt12=4;
                        }
                        else {
                            NoViableAltException nvae =
                                new NoViableAltException("219:1: lowest returns [AccessPred result] : (r= access_pred | e= bin_expr_ap | s= state_only | i= implies | '(' r= perm ')' );", 12, 17, input);

                            throw nvae;
                        }
                        }
                        break;
                    case 24:
                        {
                        int LA12_18 = input.LA(4);

                        if ( (LA12_18==NUMBER) ) {
                            int LA12_25 = input.LA(5);

                            if ( (LA12_25==IMPLIES) ) {
                                alt12=4;
                            }
                            else if ( (LA12_25==EOF||(LA12_25>=ALT && LA12_25<=TENS)||LA12_25==22) ) {
                                alt12=2;
                            }
                            else {
                                NoViableAltException nvae =
                                    new NoViableAltException("219:1: lowest returns [AccessPred result] : (r= access_pred | e= bin_expr_ap | s= state_only | i= implies | '(' r= perm ')' );", 12, 25, input);

                                throw nvae;
                            }
                        }
                        else {
                            NoViableAltException nvae =
                                new NoViableAltException("219:1: lowest returns [AccessPred result] : (r= access_pred | e= bin_expr_ap | s= state_only | i= implies | '(' r= perm ')' );", 12, 18, input);

                            throw nvae;
                        }
                        }
                        break;
                    case NULL:
                        {
                        int LA12_19 = input.LA(4);

                        if ( (LA12_19==IMPLIES) ) {
                            alt12=4;
                        }
                        else if ( (LA12_19==EOF||(LA12_19>=ALT && LA12_19<=TENS)||LA12_19==22) ) {
                            alt12=2;
                        }
                        else {
                            NoViableAltException nvae =
                                new NoViableAltException("219:1: lowest returns [AccessPred result] : (r= access_pred | e= bin_expr_ap | s= state_only | i= implies | '(' r= perm ')' );", 12, 19, input);

                            throw nvae;
                        }
                        }
                        break;
                    case TRUE:
                        {
                        int LA12_20 = input.LA(4);

                        if ( (LA12_20==IMPLIES) ) {
                            alt12=4;
                        }
                        else if ( (LA12_20==EOF||(LA12_20>=ALT && LA12_20<=TENS)||LA12_20==22) ) {
                            alt12=2;
                        }
                        else {
                            NoViableAltException nvae =
                                new NoViableAltException("219:1: lowest returns [AccessPred result] : (r= access_pred | e= bin_expr_ap | s= state_only | i= implies | '(' r= perm ')' );", 12, 20, input);

                            throw nvae;
                        }
                        }
                        break;
                    case FALSE:
                        {
                        int LA12_21 = input.LA(4);

                        if ( (LA12_21==IMPLIES) ) {
                            alt12=4;
                        }
                        else if ( (LA12_21==EOF||(LA12_21>=ALT && LA12_21<=TENS)||LA12_21==22) ) {
                            alt12=2;
                        }
                        else {
                            NoViableAltException nvae =
                                new NoViableAltException("219:1: lowest returns [AccessPred result] : (r= access_pred | e= bin_expr_ap | s= state_only | i= implies | '(' r= perm ')' );", 12, 21, input);

                            throw nvae;
                        }
                        }
                        break;
                    default:
                        NoViableAltException nvae =
                            new NoViableAltException("219:1: lowest returns [AccessPred result] : (r= access_pred | e= bin_expr_ap | s= state_only | i= implies | '(' r= perm ')' );", 12, 9, input);

                        throw nvae;
                    }

                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("219:1: lowest returns [AccessPred result] : (r= access_pred | e= bin_expr_ap | s= state_only | i= implies | '(' r= perm ')' );", 12, 3, input);

                    throw nvae;
                }
                }
                break;
            case TRUE:
                {
                int LA12_4 = input.LA(2);

                if ( (LA12_4==NEQ) ) {
                    switch ( input.LA(3) ) {
                    case ID:
                        {
                        int LA12_12 = input.LA(4);

                        if ( (LA12_12==IMPLIES) ) {
                            alt12=4;
                        }
                        else if ( (LA12_12==EOF||(LA12_12>=ALT && LA12_12<=TENS)||LA12_12==22) ) {
                            alt12=2;
                        }
                        else {
                            NoViableAltException nvae =
                                new NoViableAltException("219:1: lowest returns [AccessPred result] : (r= access_pred | e= bin_expr_ap | s= state_only | i= implies | '(' r= perm ')' );", 12, 12, input);

                            throw nvae;
                        }
                        }
                        break;
                    case 24:
                        {
                        int LA12_13 = input.LA(4);

                        if ( (LA12_13==NUMBER) ) {
                            int LA12_24 = input.LA(5);

                            if ( (LA12_24==EOF||(LA12_24>=ALT && LA12_24<=TENS)||LA12_24==22) ) {
                                alt12=2;
                            }
                            else if ( (LA12_24==IMPLIES) ) {
                                alt12=4;
                            }
                            else {
                                NoViableAltException nvae =
                                    new NoViableAltException("219:1: lowest returns [AccessPred result] : (r= access_pred | e= bin_expr_ap | s= state_only | i= implies | '(' r= perm ')' );", 12, 24, input);

                                throw nvae;
                            }
                        }
                        else {
                            NoViableAltException nvae =
                                new NoViableAltException("219:1: lowest returns [AccessPred result] : (r= access_pred | e= bin_expr_ap | s= state_only | i= implies | '(' r= perm ')' );", 12, 13, input);

                            throw nvae;
                        }
                        }
                        break;
                    case NULL:
                        {
                        int LA12_14 = input.LA(4);

                        if ( (LA12_14==EOF||(LA12_14>=ALT && LA12_14<=TENS)||LA12_14==22) ) {
                            alt12=2;
                        }
                        else if ( (LA12_14==IMPLIES) ) {
                            alt12=4;
                        }
                        else {
                            NoViableAltException nvae =
                                new NoViableAltException("219:1: lowest returns [AccessPred result] : (r= access_pred | e= bin_expr_ap | s= state_only | i= implies | '(' r= perm ')' );", 12, 14, input);

                            throw nvae;
                        }
                        }
                        break;
                    case TRUE:
                        {
                        int LA12_15 = input.LA(4);

                        if ( (LA12_15==EOF||(LA12_15>=ALT && LA12_15<=TENS)||LA12_15==22) ) {
                            alt12=2;
                        }
                        else if ( (LA12_15==IMPLIES) ) {
                            alt12=4;
                        }
                        else {
                            NoViableAltException nvae =
                                new NoViableAltException("219:1: lowest returns [AccessPred result] : (r= access_pred | e= bin_expr_ap | s= state_only | i= implies | '(' r= perm ')' );", 12, 15, input);

                            throw nvae;
                        }
                        }
                        break;
                    case FALSE:
                        {
                        int LA12_16 = input.LA(4);

                        if ( (LA12_16==EOF||(LA12_16>=ALT && LA12_16<=TENS)||LA12_16==22) ) {
                            alt12=2;
                        }
                        else if ( (LA12_16==IMPLIES) ) {
                            alt12=4;
                        }
                        else {
                            NoViableAltException nvae =
                                new NoViableAltException("219:1: lowest returns [AccessPred result] : (r= access_pred | e= bin_expr_ap | s= state_only | i= implies | '(' r= perm ')' );", 12, 16, input);

                            throw nvae;
                        }
                        }
                        break;
                    default:
                        NoViableAltException nvae =
                            new NoViableAltException("219:1: lowest returns [AccessPred result] : (r= access_pred | e= bin_expr_ap | s= state_only | i= implies | '(' r= perm ')' );", 12, 8, input);

                        throw nvae;
                    }

                }
                else if ( (LA12_4==DEQ) ) {
                    switch ( input.LA(3) ) {
                    case ID:
                        {
                        int LA12_17 = input.LA(4);

                        if ( (LA12_17==EOF||(LA12_17>=ALT && LA12_17<=TENS)||LA12_17==22) ) {
                            alt12=2;
                        }
                        else if ( (LA12_17==IMPLIES) ) {
                            alt12=4;
                        }
                        else {
                            NoViableAltException nvae =
                                new NoViableAltException("219:1: lowest returns [AccessPred result] : (r= access_pred | e= bin_expr_ap | s= state_only | i= implies | '(' r= perm ')' );", 12, 17, input);

                            throw nvae;
                        }
                        }
                        break;
                    case 24:
                        {
                        int LA12_18 = input.LA(4);

                        if ( (LA12_18==NUMBER) ) {
                            int LA12_25 = input.LA(5);

                            if ( (LA12_25==IMPLIES) ) {
                                alt12=4;
                            }
                            else if ( (LA12_25==EOF||(LA12_25>=ALT && LA12_25<=TENS)||LA12_25==22) ) {
                                alt12=2;
                            }
                            else {
                                NoViableAltException nvae =
                                    new NoViableAltException("219:1: lowest returns [AccessPred result] : (r= access_pred | e= bin_expr_ap | s= state_only | i= implies | '(' r= perm ')' );", 12, 25, input);

                                throw nvae;
                            }
                        }
                        else {
                            NoViableAltException nvae =
                                new NoViableAltException("219:1: lowest returns [AccessPred result] : (r= access_pred | e= bin_expr_ap | s= state_only | i= implies | '(' r= perm ')' );", 12, 18, input);

                            throw nvae;
                        }
                        }
                        break;
                    case NULL:
                        {
                        int LA12_19 = input.LA(4);

                        if ( (LA12_19==IMPLIES) ) {
                            alt12=4;
                        }
                        else if ( (LA12_19==EOF||(LA12_19>=ALT && LA12_19<=TENS)||LA12_19==22) ) {
                            alt12=2;
                        }
                        else {
                            NoViableAltException nvae =
                                new NoViableAltException("219:1: lowest returns [AccessPred result] : (r= access_pred | e= bin_expr_ap | s= state_only | i= implies | '(' r= perm ')' );", 12, 19, input);

                            throw nvae;
                        }
                        }
                        break;
                    case TRUE:
                        {
                        int LA12_20 = input.LA(4);

                        if ( (LA12_20==IMPLIES) ) {
                            alt12=4;
                        }
                        else if ( (LA12_20==EOF||(LA12_20>=ALT && LA12_20<=TENS)||LA12_20==22) ) {
                            alt12=2;
                        }
                        else {
                            NoViableAltException nvae =
                                new NoViableAltException("219:1: lowest returns [AccessPred result] : (r= access_pred | e= bin_expr_ap | s= state_only | i= implies | '(' r= perm ')' );", 12, 20, input);

                            throw nvae;
                        }
                        }
                        break;
                    case FALSE:
                        {
                        int LA12_21 = input.LA(4);

                        if ( (LA12_21==IMPLIES) ) {
                            alt12=4;
                        }
                        else if ( (LA12_21==EOF||(LA12_21>=ALT && LA12_21<=TENS)||LA12_21==22) ) {
                            alt12=2;
                        }
                        else {
                            NoViableAltException nvae =
                                new NoViableAltException("219:1: lowest returns [AccessPred result] : (r= access_pred | e= bin_expr_ap | s= state_only | i= implies | '(' r= perm ')' );", 12, 21, input);

                            throw nvae;
                        }
                        }
                        break;
                    default:
                        NoViableAltException nvae =
                            new NoViableAltException("219:1: lowest returns [AccessPred result] : (r= access_pred | e= bin_expr_ap | s= state_only | i= implies | '(' r= perm ')' );", 12, 9, input);

                        throw nvae;
                    }

                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("219:1: lowest returns [AccessPred result] : (r= access_pred | e= bin_expr_ap | s= state_only | i= implies | '(' r= perm ')' );", 12, 4, input);

                    throw nvae;
                }
                }
                break;
            case FALSE:
                {
                int LA12_5 = input.LA(2);

                if ( (LA12_5==NEQ) ) {
                    switch ( input.LA(3) ) {
                    case ID:
                        {
                        int LA12_12 = input.LA(4);

                        if ( (LA12_12==IMPLIES) ) {
                            alt12=4;
                        }
                        else if ( (LA12_12==EOF||(LA12_12>=ALT && LA12_12<=TENS)||LA12_12==22) ) {
                            alt12=2;
                        }
                        else {
                            NoViableAltException nvae =
                                new NoViableAltException("219:1: lowest returns [AccessPred result] : (r= access_pred | e= bin_expr_ap | s= state_only | i= implies | '(' r= perm ')' );", 12, 12, input);

                            throw nvae;
                        }
                        }
                        break;
                    case 24:
                        {
                        int LA12_13 = input.LA(4);

                        if ( (LA12_13==NUMBER) ) {
                            int LA12_24 = input.LA(5);

                            if ( (LA12_24==EOF||(LA12_24>=ALT && LA12_24<=TENS)||LA12_24==22) ) {
                                alt12=2;
                            }
                            else if ( (LA12_24==IMPLIES) ) {
                                alt12=4;
                            }
                            else {
                                NoViableAltException nvae =
                                    new NoViableAltException("219:1: lowest returns [AccessPred result] : (r= access_pred | e= bin_expr_ap | s= state_only | i= implies | '(' r= perm ')' );", 12, 24, input);

                                throw nvae;
                            }
                        }
                        else {
                            NoViableAltException nvae =
                                new NoViableAltException("219:1: lowest returns [AccessPred result] : (r= access_pred | e= bin_expr_ap | s= state_only | i= implies | '(' r= perm ')' );", 12, 13, input);

                            throw nvae;
                        }
                        }
                        break;
                    case NULL:
                        {
                        int LA12_14 = input.LA(4);

                        if ( (LA12_14==EOF||(LA12_14>=ALT && LA12_14<=TENS)||LA12_14==22) ) {
                            alt12=2;
                        }
                        else if ( (LA12_14==IMPLIES) ) {
                            alt12=4;
                        }
                        else {
                            NoViableAltException nvae =
                                new NoViableAltException("219:1: lowest returns [AccessPred result] : (r= access_pred | e= bin_expr_ap | s= state_only | i= implies | '(' r= perm ')' );", 12, 14, input);

                            throw nvae;
                        }
                        }
                        break;
                    case TRUE:
                        {
                        int LA12_15 = input.LA(4);

                        if ( (LA12_15==EOF||(LA12_15>=ALT && LA12_15<=TENS)||LA12_15==22) ) {
                            alt12=2;
                        }
                        else if ( (LA12_15==IMPLIES) ) {
                            alt12=4;
                        }
                        else {
                            NoViableAltException nvae =
                                new NoViableAltException("219:1: lowest returns [AccessPred result] : (r= access_pred | e= bin_expr_ap | s= state_only | i= implies | '(' r= perm ')' );", 12, 15, input);

                            throw nvae;
                        }
                        }
                        break;
                    case FALSE:
                        {
                        int LA12_16 = input.LA(4);

                        if ( (LA12_16==EOF||(LA12_16>=ALT && LA12_16<=TENS)||LA12_16==22) ) {
                            alt12=2;
                        }
                        else if ( (LA12_16==IMPLIES) ) {
                            alt12=4;
                        }
                        else {
                            NoViableAltException nvae =
                                new NoViableAltException("219:1: lowest returns [AccessPred result] : (r= access_pred | e= bin_expr_ap | s= state_only | i= implies | '(' r= perm ')' );", 12, 16, input);

                            throw nvae;
                        }
                        }
                        break;
                    default:
                        NoViableAltException nvae =
                            new NoViableAltException("219:1: lowest returns [AccessPred result] : (r= access_pred | e= bin_expr_ap | s= state_only | i= implies | '(' r= perm ')' );", 12, 8, input);

                        throw nvae;
                    }

                }
                else if ( (LA12_5==DEQ) ) {
                    switch ( input.LA(3) ) {
                    case ID:
                        {
                        int LA12_17 = input.LA(4);

                        if ( (LA12_17==EOF||(LA12_17>=ALT && LA12_17<=TENS)||LA12_17==22) ) {
                            alt12=2;
                        }
                        else if ( (LA12_17==IMPLIES) ) {
                            alt12=4;
                        }
                        else {
                            NoViableAltException nvae =
                                new NoViableAltException("219:1: lowest returns [AccessPred result] : (r= access_pred | e= bin_expr_ap | s= state_only | i= implies | '(' r= perm ')' );", 12, 17, input);

                            throw nvae;
                        }
                        }
                        break;
                    case 24:
                        {
                        int LA12_18 = input.LA(4);

                        if ( (LA12_18==NUMBER) ) {
                            int LA12_25 = input.LA(5);

                            if ( (LA12_25==IMPLIES) ) {
                                alt12=4;
                            }
                            else if ( (LA12_25==EOF||(LA12_25>=ALT && LA12_25<=TENS)||LA12_25==22) ) {
                                alt12=2;
                            }
                            else {
                                NoViableAltException nvae =
                                    new NoViableAltException("219:1: lowest returns [AccessPred result] : (r= access_pred | e= bin_expr_ap | s= state_only | i= implies | '(' r= perm ')' );", 12, 25, input);

                                throw nvae;
                            }
                        }
                        else {
                            NoViableAltException nvae =
                                new NoViableAltException("219:1: lowest returns [AccessPred result] : (r= access_pred | e= bin_expr_ap | s= state_only | i= implies | '(' r= perm ')' );", 12, 18, input);

                            throw nvae;
                        }
                        }
                        break;
                    case NULL:
                        {
                        int LA12_19 = input.LA(4);

                        if ( (LA12_19==IMPLIES) ) {
                            alt12=4;
                        }
                        else if ( (LA12_19==EOF||(LA12_19>=ALT && LA12_19<=TENS)||LA12_19==22) ) {
                            alt12=2;
                        }
                        else {
                            NoViableAltException nvae =
                                new NoViableAltException("219:1: lowest returns [AccessPred result] : (r= access_pred | e= bin_expr_ap | s= state_only | i= implies | '(' r= perm ')' );", 12, 19, input);

                            throw nvae;
                        }
                        }
                        break;
                    case TRUE:
                        {
                        int LA12_20 = input.LA(4);

                        if ( (LA12_20==IMPLIES) ) {
                            alt12=4;
                        }
                        else if ( (LA12_20==EOF||(LA12_20>=ALT && LA12_20<=TENS)||LA12_20==22) ) {
                            alt12=2;
                        }
                        else {
                            NoViableAltException nvae =
                                new NoViableAltException("219:1: lowest returns [AccessPred result] : (r= access_pred | e= bin_expr_ap | s= state_only | i= implies | '(' r= perm ')' );", 12, 20, input);

                            throw nvae;
                        }
                        }
                        break;
                    case FALSE:
                        {
                        int LA12_21 = input.LA(4);

                        if ( (LA12_21==IMPLIES) ) {
                            alt12=4;
                        }
                        else if ( (LA12_21==EOF||(LA12_21>=ALT && LA12_21<=TENS)||LA12_21==22) ) {
                            alt12=2;
                        }
                        else {
                            NoViableAltException nvae =
                                new NoViableAltException("219:1: lowest returns [AccessPred result] : (r= access_pred | e= bin_expr_ap | s= state_only | i= implies | '(' r= perm ')' );", 12, 21, input);

                            throw nvae;
                        }
                        }
                        break;
                    default:
                        NoViableAltException nvae =
                            new NoViableAltException("219:1: lowest returns [AccessPred result] : (r= access_pred | e= bin_expr_ap | s= state_only | i= implies | '(' r= perm ')' );", 12, 9, input);

                        throw nvae;
                    }

                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("219:1: lowest returns [AccessPred result] : (r= access_pred | e= bin_expr_ap | s= state_only | i= implies | '(' r= perm ')' );", 12, 5, input);

                    throw nvae;
                }
                }
                break;
            case 21:
                {
                alt12=5;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("219:1: lowest returns [AccessPred result] : (r= access_pred | e= bin_expr_ap | s= state_only | i= implies | '(' r= perm ')' );", 12, 0, input);

                throw nvae;
            }

            switch (alt12) {
                case 1 :
                    // D:\\workspace-unimod\\Plural\\permission-parser\\AccessPred.g:220:4: r= access_pred
                    {
                    pushFollow(FOLLOW_access_pred_in_lowest721);
                    r=access_pred();
                    _fsp--;

                    result = r;

                    }
                    break;
                case 2 :
                    // D:\\workspace-unimod\\Plural\\permission-parser\\AccessPred.g:221:4: e= bin_expr_ap
                    {
                    pushFollow(FOLLOW_bin_expr_ap_in_lowest730);
                    e=bin_expr_ap();
                    _fsp--;

                    result = e;

                    }
                    break;
                case 3 :
                    // D:\\workspace-unimod\\Plural\\permission-parser\\AccessPred.g:222:10: s= state_only
                    {
                    pushFollow(FOLLOW_state_only_in_lowest745);
                    s=state_only();
                    _fsp--;

                    result = s;

                    }
                    break;
                case 4 :
                    // D:\\workspace-unimod\\Plural\\permission-parser\\AccessPred.g:223:10: i= implies
                    {
                    pushFollow(FOLLOW_implies_in_lowest760);
                    i=implies();
                    _fsp--;

                    result = i;

                    }
                    break;
                case 5 :
                    // D:\\workspace-unimod\\Plural\\permission-parser\\AccessPred.g:224:4: '(' r= perm ')'
                    {
                    match(input,21,FOLLOW_21_in_lowest767); 
                    pushFollow(FOLLOW_perm_in_lowest771);
                    r=perm();
                    _fsp--;

                    match(input,22,FOLLOW_22_in_lowest773); 
                    result = r;

                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return result;
    }
    // $ANTLR end lowest


    // $ANTLR start fractions
    // D:\\workspace-unimod\\Plural\\permission-parser\\AccessPred.g:228:1: fractions returns [TempFractionMap result] : n= ID '=' f= fract ( ',' m= ID '=' g= fract )* ;
    public final TempFractionMap fractions() throws RecognitionException {
        TempFractionMap result = null;

        Token n=null;
        Token m=null;
        Fraction f = null;

        Fraction g = null;


        
        result = new TempFractionMap();

        try {
            // D:\\workspace-unimod\\Plural\\permission-parser\\AccessPred.g:232:2: (n= ID '=' f= fract ( ',' m= ID '=' g= fract )* )
            // D:\\workspace-unimod\\Plural\\permission-parser\\AccessPred.g:232:4: n= ID '=' f= fract ( ',' m= ID '=' g= fract )*
            {
            n=(Token)input.LT(1);
            match(input,ID,FOLLOW_ID_in_fractions799); 
            match(input,32,FOLLOW_32_in_fractions801); 
            pushFollow(FOLLOW_fract_in_fractions805);
            f=fract();
            _fsp--;

            result.put(n.getText(), f);
            // D:\\workspace-unimod\\Plural\\permission-parser\\AccessPred.g:232:52: ( ',' m= ID '=' g= fract )*
            loop13:
            do {
                int alt13=2;
                int LA13_0 = input.LA(1);

                if ( (LA13_0==23) ) {
                    alt13=1;
                }


                switch (alt13) {
            	case 1 :
            	    // D:\\workspace-unimod\\Plural\\permission-parser\\AccessPred.g:232:53: ',' m= ID '=' g= fract
            	    {
            	    match(input,23,FOLLOW_23_in_fractions810); 
            	    m=(Token)input.LT(1);
            	    match(input,ID,FOLLOW_ID_in_fractions814); 
            	    match(input,32,FOLLOW_32_in_fractions816); 
            	    pushFollow(FOLLOW_fract_in_fractions820);
            	    g=fract();
            	    _fsp--;

            	    result.put(m.getText(), g);

            	    }
            	    break;

            	default :
            	    break loop13;
                }
            } while (true);


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return result;
    }
    // $ANTLR end fractions


    // $ANTLR start fract
    // D:\\workspace-unimod\\Plural\\permission-parser\\AccessPred.g:235:1: fract returns [Fraction result] : (p= NUMBER | p= NUMBER '/' q= NUMBER | i= ID );
    public final Fraction fract() throws RecognitionException {
        Fraction result = null;

        Token p=null;
        Token q=null;
        Token i=null;

        try {
            // D:\\workspace-unimod\\Plural\\permission-parser\\AccessPred.g:236:2: (p= NUMBER | p= NUMBER '/' q= NUMBER | i= ID )
            int alt14=3;
            int LA14_0 = input.LA(1);

            if ( (LA14_0==NUMBER) ) {
                int LA14_1 = input.LA(2);

                if ( (LA14_1==33) ) {
                    alt14=2;
                }
                else if ( ((LA14_1>=22 && LA14_1<=23)) ) {
                    alt14=1;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("235:1: fract returns [Fraction result] : (p= NUMBER | p= NUMBER '/' q= NUMBER | i= ID );", 14, 1, input);

                    throw nvae;
                }
            }
            else if ( (LA14_0==ID) ) {
                alt14=3;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("235:1: fract returns [Fraction result] : (p= NUMBER | p= NUMBER '/' q= NUMBER | i= ID );", 14, 0, input);

                throw nvae;
            }
            switch (alt14) {
                case 1 :
                    // D:\\workspace-unimod\\Plural\\permission-parser\\AccessPred.g:236:4: p= NUMBER
                    {
                    p=(Token)input.LT(1);
                    match(input,NUMBER,FOLLOW_NUMBER_in_fract842); 
                    result = Fraction.createExplicit(Integer.parseInt(p.getText()), 1);

                    }
                    break;
                case 2 :
                    // D:\\workspace-unimod\\Plural\\permission-parser\\AccessPred.g:237:4: p= NUMBER '/' q= NUMBER
                    {
                    p=(Token)input.LT(1);
                    match(input,NUMBER,FOLLOW_NUMBER_in_fract854); 
                    match(input,33,FOLLOW_33_in_fract856); 
                    q=(Token)input.LT(1);
                    match(input,NUMBER,FOLLOW_NUMBER_in_fract860); 
                    result = Fraction.createExplicit(Integer.parseInt(p.getText()), Integer.parseInt(q.getText()));

                    }
                    break;
                case 3 :
                    // D:\\workspace-unimod\\Plural\\permission-parser\\AccessPred.g:238:4: i= ID
                    {
                    i=(Token)input.LT(1);
                    match(input,ID,FOLLOW_ID_in_fract869); 
                    result = Fraction.createNamed(i.getText());

                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return result;
    }
    // $ANTLR end fract


    // $ANTLR start state_list
    // D:\\workspace-unimod\\Plural\\permission-parser\\AccessPred.g:241:1: state_list returns [List<String> result] : i= ID ( ',' j= ID )* ;
    public final List<String> state_list() throws RecognitionException {
        List<String> result = null;

        Token i=null;
        Token j=null;

        
        result = new LinkedList<String>();

        try {
            // D:\\workspace-unimod\\Plural\\permission-parser\\AccessPred.g:245:2: (i= ID ( ',' j= ID )* )
            // D:\\workspace-unimod\\Plural\\permission-parser\\AccessPred.g:245:4: i= ID ( ',' j= ID )*
            {
            i=(Token)input.LT(1);
            match(input,ID,FOLLOW_ID_in_state_list898); 
             result.add(i.getText()); 
            // D:\\workspace-unimod\\Plural\\permission-parser\\AccessPred.g:245:39: ( ',' j= ID )*
            loop15:
            do {
                int alt15=2;
                int LA15_0 = input.LA(1);

                if ( (LA15_0==23) ) {
                    alt15=1;
                }


                switch (alt15) {
            	case 1 :
            	    // D:\\workspace-unimod\\Plural\\permission-parser\\AccessPred.g:245:40: ',' j= ID
            	    {
            	    match(input,23,FOLLOW_23_in_state_list903); 
            	    j=(Token)input.LT(1);
            	    match(input,ID,FOLLOW_ID_in_state_list907); 
            	     result.add(j.getText()); 

            	    }
            	    break;

            	default :
            	    break loop15;
                }
            } while (true);


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return result;
    }
    // $ANTLR end state_list


    protected DFA2 dfa2 = new DFA2(this);
    static final String DFA2_eotS =
        "\53\uffff";
    static final String DFA2_eofS =
        "\5\uffff\1\14\22\uffff\1\33\10\uffff\1\45\11\uffff";
    static final String DFA2_minS =
        "\1\6\1\25\1\6\1\26\1\13\1\7\1\34\2\26\1\6\1\26\2\uffff\4\26\1\34"+
        "\5\26\1\6\1\7\1\40\2\uffff\1\6\2\26\1\13\1\6\1\7\1\26\1\40\2\uffff"+
        "\1\6\2\26\1\13\1\26";
    static final String DFA2_maxS =
        "\1\6\1\25\1\30\1\33\1\13\1\26\1\37\1\27\1\31\1\6\1\27\2\uffff\4"+
        "\27\1\37\5\27\1\6\1\26\1\40\2\uffff\1\13\1\41\1\27\1\13\1\6\1\26"+
        "\1\27\1\40\2\uffff\1\13\1\41\1\27\1\13\1\27";
    static final String DFA2_acceptS =
        "\13\uffff\1\2\1\1\15\uffff\1\4\1\3\10\uffff\1\6\1\5\5\uffff";
    static final String DFA2_specialS =
        "\53\uffff}>";
    static final String[] DFA2_transitionS = {
            "\1\1",
            "\1\2",
            "\1\3\21\uffff\1\4",
            "\1\5\1\11\1\uffff\1\6\1\7\1\10",
            "\1\12",
            "\1\13\5\uffff\3\14\6\uffff\1\14",
            "\1\15\1\16\1\17\1\20",
            "\1\5\1\11",
            "\1\5\1\11\1\uffff\1\21",
            "\1\22",
            "\1\5\1\11",
            "",
            "",
            "\1\5\1\11",
            "\1\5\1\11",
            "\1\5\1\11",
            "\1\5\1\11",
            "\1\23\1\24\1\25\1\26",
            "\1\30\1\27",
            "\1\5\1\11",
            "\1\5\1\11",
            "\1\5\1\11",
            "\1\5\1\11",
            "\1\31",
            "\1\32\5\uffff\3\33\6\uffff\1\33",
            "\1\34",
            "",
            "",
            "\1\36\4\uffff\1\35",
            "\1\41\1\40\11\uffff\1\37",
            "\1\41\1\40",
            "\1\42",
            "\1\43",
            "\1\44\5\uffff\3\45\6\uffff\1\45",
            "\1\41\1\40",
            "\1\46",
            "",
            "",
            "\1\50\4\uffff\1\47",
            "\1\41\1\40\11\uffff\1\51",
            "\1\41\1\40",
            "\1\52",
            "\1\41\1\40"
    };

    static final short[] DFA2_eot = DFA.unpackEncodedString(DFA2_eotS);
    static final short[] DFA2_eof = DFA.unpackEncodedString(DFA2_eofS);
    static final char[] DFA2_min = DFA.unpackEncodedStringToUnsignedChars(DFA2_minS);
    static final char[] DFA2_max = DFA.unpackEncodedStringToUnsignedChars(DFA2_maxS);
    static final short[] DFA2_accept = DFA.unpackEncodedString(DFA2_acceptS);
    static final short[] DFA2_special = DFA.unpackEncodedString(DFA2_specialS);
    static final short[][] DFA2_transition;

    static {
        int numStates = DFA2_transitionS.length;
        DFA2_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA2_transition[i] = DFA.unpackEncodedString(DFA2_transitionS[i]);
        }
    }

    class DFA2 extends DFA {

        public DFA2(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 2;
            this.eot = DFA2_eot;
            this.eof = DFA2_eof;
            this.min = DFA2_min;
            this.max = DFA2_max;
            this.accept = DFA2_accept;
            this.special = DFA2_special;
            this.transition = DFA2_transition;
        }
        public String getDescription() {
            return "123:1: access_pred returns [AccessPred result] : (i1= ID '(' i2= ref_expr ')' | i1= ID '(' i2= ref_expr ')' IN sl= state_list | i1= ID '(' i2= ref_expr ',' i3= ID ')' | i1= ID '(' i2= ref_expr ',' i3= ID ')' IN sl= state_list | i1= ID '(' i2= ref_expr ',' i3= ID ',' f= fractions ')' | i1= ID '(' i2= ref_expr ',' i3= ID ',' f= fractions ')' IN sl= state_list );";
        }
    }
 

    public static final BitSet FOLLOW_perm_in_start43 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_start45 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_TRUE_in_start52 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_start54 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_FALSE_in_start61 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_start63 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_EOF_in_start70 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_access_pred89 = new BitSet(new long[]{0x0000000000200000L});
    public static final BitSet FOLLOW_21_in_access_pred91 = new BitSet(new long[]{0x0000000001000040L});
    public static final BitSet FOLLOW_ref_expr_in_access_pred95 = new BitSet(new long[]{0x0000000000400000L});
    public static final BitSet FOLLOW_22_in_access_pred97 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_access_pred110 = new BitSet(new long[]{0x0000000000200000L});
    public static final BitSet FOLLOW_21_in_access_pred112 = new BitSet(new long[]{0x0000000001000040L});
    public static final BitSet FOLLOW_ref_expr_in_access_pred116 = new BitSet(new long[]{0x0000000000400000L});
    public static final BitSet FOLLOW_22_in_access_pred118 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_IN_in_access_pred120 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_state_list_in_access_pred124 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_access_pred137 = new BitSet(new long[]{0x0000000000200000L});
    public static final BitSet FOLLOW_21_in_access_pred139 = new BitSet(new long[]{0x0000000001000040L});
    public static final BitSet FOLLOW_ref_expr_in_access_pred143 = new BitSet(new long[]{0x0000000000800000L});
    public static final BitSet FOLLOW_23_in_access_pred145 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_ID_in_access_pred149 = new BitSet(new long[]{0x0000000000400000L});
    public static final BitSet FOLLOW_22_in_access_pred151 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_access_pred164 = new BitSet(new long[]{0x0000000000200000L});
    public static final BitSet FOLLOW_21_in_access_pred166 = new BitSet(new long[]{0x0000000001000040L});
    public static final BitSet FOLLOW_ref_expr_in_access_pred170 = new BitSet(new long[]{0x0000000000800000L});
    public static final BitSet FOLLOW_23_in_access_pred172 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_ID_in_access_pred176 = new BitSet(new long[]{0x0000000000400000L});
    public static final BitSet FOLLOW_22_in_access_pred178 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_IN_in_access_pred180 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_state_list_in_access_pred184 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_access_pred196 = new BitSet(new long[]{0x0000000000200000L});
    public static final BitSet FOLLOW_21_in_access_pred198 = new BitSet(new long[]{0x0000000001000040L});
    public static final BitSet FOLLOW_ref_expr_in_access_pred202 = new BitSet(new long[]{0x0000000000800000L});
    public static final BitSet FOLLOW_23_in_access_pred204 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_ID_in_access_pred208 = new BitSet(new long[]{0x0000000000800000L});
    public static final BitSet FOLLOW_23_in_access_pred210 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_fractions_in_access_pred214 = new BitSet(new long[]{0x0000000000400000L});
    public static final BitSet FOLLOW_22_in_access_pred216 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_access_pred228 = new BitSet(new long[]{0x0000000000200000L});
    public static final BitSet FOLLOW_21_in_access_pred230 = new BitSet(new long[]{0x0000000001000040L});
    public static final BitSet FOLLOW_ref_expr_in_access_pred234 = new BitSet(new long[]{0x0000000000800000L});
    public static final BitSet FOLLOW_23_in_access_pred236 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_ID_in_access_pred240 = new BitSet(new long[]{0x0000000000800000L});
    public static final BitSet FOLLOW_23_in_access_pred242 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_fractions_in_access_pred246 = new BitSet(new long[]{0x0000000000400000L});
    public static final BitSet FOLLOW_22_in_access_pred248 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_IN_in_access_pred250 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_state_list_in_access_pred254 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_bin_expr_in_implies278 = new BitSet(new long[]{0x0000000000000100L});
    public static final BitSet FOLLOW_IMPLIES_in_implies280 = new BitSet(new long[]{0x0000000001201070L});
    public static final BitSet FOLLOW_lowest_in_implies284 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ref_expr_in_state_only303 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_IN_in_state_only305 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_ID_in_state_only309 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_bin_expr_in_bin_expr_ap330 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_primary_expr_in_bin_expr349 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_DEQ_in_bin_expr351 = new BitSet(new long[]{0x0000000001001070L});
    public static final BitSet FOLLOW_primary_expr_in_bin_expr355 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_primary_expr_in_bin_expr364 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_NEQ_in_bin_expr366 = new BitSet(new long[]{0x0000000001001070L});
    public static final BitSet FOLLOW_primary_expr_in_bin_expr370 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_primary_expr389 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_24_in_primary_expr397 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_NUMBER_in_primary_expr401 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NULL_in_primary_expr408 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_TRUE_in_primary_expr416 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_FALSE_in_primary_expr424 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_ref_expr444 = new BitSet(new long[]{0x000000000E000002L});
    public static final BitSet FOLLOW_25_in_ref_expr458 = new BitSet(new long[]{0x00000000F0000000L});
    public static final BitSet FOLLOW_use_qualifier_in_ref_expr462 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_26_in_ref_expr473 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_27_in_ref_expr485 = new BitSet(new long[]{0x0000000002000002L});
    public static final BitSet FOLLOW_25_in_ref_expr500 = new BitSet(new long[]{0x00000000F0000000L});
    public static final BitSet FOLLOW_use_qualifier_in_ref_expr504 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_24_in_ref_expr524 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_NUMBER_in_ref_expr528 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_28_in_use_qualifier546 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_29_in_use_qualifier553 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_30_in_use_qualifier560 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_31_in_use_qualifier567 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_wither_in_perm599 = new BitSet(new long[]{0x0000000000002002L});
    public static final BitSet FOLLOW_ALT_in_perm605 = new BitSet(new long[]{0x0000000001201070L});
    public static final BitSet FOLLOW_wither_in_perm609 = new BitSet(new long[]{0x0000000000002002L});
    public static final BitSet FOLLOW_conjs_in_wither641 = new BitSet(new long[]{0x0000000000004002L});
    public static final BitSet FOLLOW_WITH_in_wither647 = new BitSet(new long[]{0x0000000001201070L});
    public static final BitSet FOLLOW_conjs_in_wither651 = new BitSet(new long[]{0x0000000000004002L});
    public static final BitSet FOLLOW_lowest_in_conjs685 = new BitSet(new long[]{0x0000000000008002L});
    public static final BitSet FOLLOW_TENS_in_conjs691 = new BitSet(new long[]{0x0000000001201070L});
    public static final BitSet FOLLOW_lowest_in_conjs695 = new BitSet(new long[]{0x0000000000008002L});
    public static final BitSet FOLLOW_access_pred_in_lowest721 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_bin_expr_ap_in_lowest730 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_state_only_in_lowest745 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_implies_in_lowest760 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_21_in_lowest767 = new BitSet(new long[]{0x0000000001201070L});
    public static final BitSet FOLLOW_perm_in_lowest771 = new BitSet(new long[]{0x0000000000400000L});
    public static final BitSet FOLLOW_22_in_lowest773 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_fractions799 = new BitSet(new long[]{0x0000000100000000L});
    public static final BitSet FOLLOW_32_in_fractions801 = new BitSet(new long[]{0x0000000000000840L});
    public static final BitSet FOLLOW_fract_in_fractions805 = new BitSet(new long[]{0x0000000000800002L});
    public static final BitSet FOLLOW_23_in_fractions810 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_ID_in_fractions814 = new BitSet(new long[]{0x0000000100000000L});
    public static final BitSet FOLLOW_32_in_fractions816 = new BitSet(new long[]{0x0000000000000840L});
    public static final BitSet FOLLOW_fract_in_fractions820 = new BitSet(new long[]{0x0000000000800002L});
    public static final BitSet FOLLOW_NUMBER_in_fract842 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NUMBER_in_fract854 = new BitSet(new long[]{0x0000000200000000L});
    public static final BitSet FOLLOW_33_in_fract856 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_NUMBER_in_fract860 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_fract869 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_state_list898 = new BitSet(new long[]{0x0000000000800002L});
    public static final BitSet FOLLOW_23_in_state_list903 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_ID_in_state_list907 = new BitSet(new long[]{0x0000000000800002L});

}