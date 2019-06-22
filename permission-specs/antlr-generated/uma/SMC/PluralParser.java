// $ANTLR 3.2 Sep 23, 2009 12:02:23 /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g 2019-04-08 15:29:33

package uma.SMC;


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

public class PluralParser extends Parser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "AT_FULL", "AT_PURE", "AT_IMMUTABLE", "AT_SHARE", "AT_UNIQUE", "PUBLIC_BEHAVIOR", "FULL", "PURE", "IMMUTABLE", "SHARE", "UNIQUE", "NONE", "LSBRACKET", "RSBRACKET", "PERM", "EQUAL", "EQUALOPERATOR", "IN", "THIS", "RESULT", "PARAM", "REQUIRES", "ENSURES", "QUOTE", "AND", "USE", "USEFIELDS", "PUNCTUATION", "CASES", "LCBRACKET", "RCBRACKET", "CLASS_STATES", "REFINE", "VALUE", "STATE", "STATES", "DIM", "NAME", "INV", "OPERATOR", "SEMICOLON", "LESS", "LESSTHANEQUAL", "GREATER", "GREATERTHANEQUAL", "ANDD", "OR", "JMLSTART", "JMLEND", "PLUSMINUSOPERATOR", "ASSIGNABLE", "NOTHING", "EVERYTHING", "GHOST", "INT", "INVARIANT", "OLD", "ID", "NUMBERS", "WS"
    };
    public static final int PUNCTUATION=31;
    public static final int CASES=32;
    public static final int EVERYTHING=56;
    public static final int EQUALOPERATOR=20;
    public static final int PARAM=24;
    public static final int IMMUTABLE=12;
    public static final int JMLEND=52;
    public static final int PUBLIC_BEHAVIOR=9;
    public static final int ID=61;
    public static final int AND=28;
    public static final int EOF=-1;
    public static final int USEFIELDS=30;
    public static final int STATES=39;
    public static final int ENSURES=26;
    public static final int QUOTE=27;
    public static final int PURE=11;
    public static final int AT_UNIQUE=8;
    public static final int NAME=41;
    public static final int GREATER=47;
    public static final int FULL=10;
    public static final int IN=21;
    public static final int RSBRACKET=17;
    public static final int EQUAL=19;
    public static final int LCBRACKET=33;
    public static final int LESS=45;
    public static final int THIS=22;
    public static final int REFINE=36;
    public static final int NOTHING=55;
    public static final int SHARE=13;
    public static final int LESSTHANEQUAL=46;
    public static final int AT_IMMUTABLE=6;
    public static final int RCBRACKET=34;
    public static final int CLASS_STATES=35;
    public static final int AT_PURE=5;
    public static final int NUMBERS=62;
    public static final int ASSIGNABLE=54;
    public static final int AT_FULL=4;
    public static final int UNIQUE=14;
    public static final int STATE=38;
    public static final int GHOST=57;
    public static final int OPERATOR=43;
    public static final int INV=42;
    public static final int INVARIANT=59;
    public static final int OLD=60;
    public static final int RESULT=23;
    public static final int INT=58;
    public static final int SEMICOLON=44;
    public static final int JMLSTART=51;
    public static final int VALUE=37;
    public static final int PLUSMINUSOPERATOR=53;
    public static final int LSBRACKET=16;
    public static final int WS=63;
    public static final int REQUIRES=25;
    public static final int NONE=15;
    public static final int OR=50;
    public static final int DIM=40;
    public static final int AT_SHARE=7;
    public static final int USE=29;
    public static final int ANDD=49;
    public static final int GREATERTHANEQUAL=48;
    public static final int PERM=18;

    // delegates
    // delegators


        public PluralParser(TokenStream input) {
            this(input, new RecognizerSharedState());
        }
        public PluralParser(TokenStream input, RecognizerSharedState state) {
            super(input, state);
             
        }
        

    public String[] getTokenNames() { return PluralParser.tokenNames; }
    public String getGrammarFileName() { return "/Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g"; }



    // $ANTLR start "jmlSpecifications"
    // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:88:1: jmlSpecifications : ( jmlClassSpecifications | jmlMethodSpecification );
    public final void jmlSpecifications() throws RecognitionException {
        try {
            // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:88:18: ( jmlClassSpecifications | jmlMethodSpecification )
            int alt1=2;
            int LA1_0 = input.LA(1);

            if ( (LA1_0==GHOST||LA1_0==INVARIANT) ) {
                alt1=1;
            }
            else if ( (LA1_0==JMLSTART) ) {
                alt1=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 1, 0, input);

                throw nvae;
            }
            switch (alt1) {
                case 1 :
                    // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:88:20: jmlClassSpecifications
                    {
                    pushFollow(FOLLOW_jmlClassSpecifications_in_jmlSpecifications1071);
                    jmlClassSpecifications();

                    state._fsp--;


                    }
                    break;
                case 2 :
                    // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:88:43: jmlMethodSpecification
                    {
                    pushFollow(FOLLOW_jmlMethodSpecification_in_jmlSpecifications1073);
                    jmlMethodSpecification();

                    state._fsp--;


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
        return ;
    }
    // $ANTLR end "jmlSpecifications"


    // $ANTLR start "jmlClassSpecifications"
    // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:91:1: jmlClassSpecifications : ( jmlGhostDeclaration | jmlGhostInv );
    public final void jmlClassSpecifications() throws RecognitionException {
        try {
            // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:91:23: ( jmlGhostDeclaration | jmlGhostInv )
            int alt2=2;
            int LA2_0 = input.LA(1);

            if ( (LA2_0==GHOST) ) {
                alt2=1;
            }
            else if ( (LA2_0==INVARIANT) ) {
                alt2=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 2, 0, input);

                throw nvae;
            }
            switch (alt2) {
                case 1 :
                    // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:91:25: jmlGhostDeclaration
                    {
                    pushFollow(FOLLOW_jmlGhostDeclaration_in_jmlClassSpecifications1081);
                    jmlGhostDeclaration();

                    state._fsp--;


                    }
                    break;
                case 2 :
                    // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:91:45: jmlGhostInv
                    {
                    pushFollow(FOLLOW_jmlGhostInv_in_jmlClassSpecifications1083);
                    jmlGhostInv();

                    state._fsp--;


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
        return ;
    }
    // $ANTLR end "jmlClassSpecifications"


    // $ANTLR start "jmlGhostDeclaration"
    // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:93:1: jmlGhostDeclaration : GHOST INT dim= ID SEMICOLON ;
    public final void jmlGhostDeclaration() throws RecognitionException {
        Token dim=null;

        try {
            // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:93:20: ( GHOST INT dim= ID SEMICOLON )
            // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:93:22: GHOST INT dim= ID SEMICOLON
            {
            match(input,GHOST,FOLLOW_GHOST_in_jmlGhostDeclaration1090); 
            match(input,INT,FOLLOW_INT_in_jmlGhostDeclaration1092); 
            dim=(Token)match(input,ID,FOLLOW_ID_in_jmlGhostDeclaration1096); 
            match(input,SEMICOLON,FOLLOW_SEMICOLON_in_jmlGhostDeclaration1098); 

                E_JmlSpecification.setDimensionName((dim!=null?dim.getText():null));


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "jmlGhostDeclaration"


    // $ANTLR start "jmlGhostInv"
    // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:97:1: jmlGhostInv : INVARIANT low= NUMBERS LESSTHANEQUAL ID ANDD ID LESSTHANEQUAL high= NUMBERS SEMICOLON ;
    public final void jmlGhostInv() throws RecognitionException {
        Token low=null;
        Token high=null;

        try {
            // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:97:12: ( INVARIANT low= NUMBERS LESSTHANEQUAL ID ANDD ID LESSTHANEQUAL high= NUMBERS SEMICOLON )
            // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:97:14: INVARIANT low= NUMBERS LESSTHANEQUAL ID ANDD ID LESSTHANEQUAL high= NUMBERS SEMICOLON
            {
            match(input,INVARIANT,FOLLOW_INVARIANT_in_jmlGhostInv1106); 
            low=(Token)match(input,NUMBERS,FOLLOW_NUMBERS_in_jmlGhostInv1111); 
            match(input,LESSTHANEQUAL,FOLLOW_LESSTHANEQUAL_in_jmlGhostInv1113); 
            match(input,ID,FOLLOW_ID_in_jmlGhostInv1115); 
            match(input,ANDD,FOLLOW_ANDD_in_jmlGhostInv1117); 
            match(input,ID,FOLLOW_ID_in_jmlGhostInv1119); 
            match(input,LESSTHANEQUAL,FOLLOW_LESSTHANEQUAL_in_jmlGhostInv1121); 
            high=(Token)match(input,NUMBERS,FOLLOW_NUMBERS_in_jmlGhostInv1125); 
            match(input,SEMICOLON,FOLLOW_SEMICOLON_in_jmlGhostInv1127); 

                int nlow=Integer.parseInt((low!=null?low.getText():null));
                int nhigh=Integer.parseInt((high!=null?high.getText():null));
              E_JmlSpecification.setDimensionValues(nlow, nhigh);


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "jmlGhostInv"


    // $ANTLR start "jmlMethodSpecification"
    // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:104:1: jmlMethodSpecification : JMLSTART PUBLIC_BEHAVIOR ( REQUIRES jmlRequires SEMICOLON )? ( jmlAssign )? ( jmlEnsures )? JMLEND ;
    public final void jmlMethodSpecification() throws RecognitionException {
        try {
            // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:104:23: ( JMLSTART PUBLIC_BEHAVIOR ( REQUIRES jmlRequires SEMICOLON )? ( jmlAssign )? ( jmlEnsures )? JMLEND )
            // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:104:25: JMLSTART PUBLIC_BEHAVIOR ( REQUIRES jmlRequires SEMICOLON )? ( jmlAssign )? ( jmlEnsures )? JMLEND
            {
            match(input,JMLSTART,FOLLOW_JMLSTART_in_jmlMethodSpecification1136); 
            match(input,PUBLIC_BEHAVIOR,FOLLOW_PUBLIC_BEHAVIOR_in_jmlMethodSpecification1138); 
            // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:104:51: ( REQUIRES jmlRequires SEMICOLON )?
            int alt3=2;
            int LA3_0 = input.LA(1);

            if ( (LA3_0==REQUIRES) ) {
                alt3=1;
            }
            switch (alt3) {
                case 1 :
                    // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:104:52: REQUIRES jmlRequires SEMICOLON
                    {
                    match(input,REQUIRES,FOLLOW_REQUIRES_in_jmlMethodSpecification1142); 
                    pushFollow(FOLLOW_jmlRequires_in_jmlMethodSpecification1144);
                    jmlRequires();

                    state._fsp--;

                    match(input,SEMICOLON,FOLLOW_SEMICOLON_in_jmlMethodSpecification1147); 

                    }
                    break;

            }

            // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:104:86: ( jmlAssign )?
            int alt4=2;
            int LA4_0 = input.LA(1);

            if ( (LA4_0==ASSIGNABLE) ) {
                alt4=1;
            }
            switch (alt4) {
                case 1 :
                    // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:104:87: jmlAssign
                    {
                    pushFollow(FOLLOW_jmlAssign_in_jmlMethodSpecification1152);
                    jmlAssign();

                    state._fsp--;


                    }
                    break;

            }

            // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:104:99: ( jmlEnsures )?
            int alt5=2;
            int LA5_0 = input.LA(1);

            if ( (LA5_0==ENSURES) ) {
                alt5=1;
            }
            switch (alt5) {
                case 1 :
                    // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:104:100: jmlEnsures
                    {
                    pushFollow(FOLLOW_jmlEnsures_in_jmlMethodSpecification1157);
                    jmlEnsures();

                    state._fsp--;


                    }
                    break;

            }

            match(input,JMLEND,FOLLOW_JMLEND_in_jmlMethodSpecification1161); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "jmlMethodSpecification"


    // $ANTLR start "jmlRequires"
    // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:106:1: jmlRequires : ( jmlReq | jmlOrReq | jmlLessThanEqualReq );
    public final void jmlRequires() throws RecognitionException {
        try {
            // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:106:12: ( jmlReq | jmlOrReq | jmlLessThanEqualReq )
            int alt6=3;
            int LA6_0 = input.LA(1);

            if ( (LA6_0==ID) ) {
                int LA6_1 = input.LA(2);

                if ( (LA6_1==EQUALOPERATOR) ) {
                    int LA6_2 = input.LA(3);

                    if ( (LA6_2==NUMBERS) ) {
                        int LA6_4 = input.LA(4);

                        if ( (LA6_4==OR) ) {
                            alt6=2;
                        }
                        else if ( (LA6_4==SEMICOLON) ) {
                            alt6=1;
                        }
                        else {
                            NoViableAltException nvae =
                                new NoViableAltException("", 6, 4, input);

                            throw nvae;
                        }
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 6, 2, input);

                        throw nvae;
                    }
                }
                else if ( (LA6_1==LESSTHANEQUAL) ) {
                    alt6=3;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 6, 1, input);

                    throw nvae;
                }
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 6, 0, input);

                throw nvae;
            }
            switch (alt6) {
                case 1 :
                    // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:106:13: jmlReq
                    {
                    pushFollow(FOLLOW_jmlReq_in_jmlRequires1167);
                    jmlReq();

                    state._fsp--;


                    }
                    break;
                case 2 :
                    // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:106:20: jmlOrReq
                    {
                    pushFollow(FOLLOW_jmlOrReq_in_jmlRequires1169);
                    jmlOrReq();

                    state._fsp--;


                    }
                    break;
                case 3 :
                    // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:106:29: jmlLessThanEqualReq
                    {
                    pushFollow(FOLLOW_jmlLessThanEqualReq_in_jmlRequires1171);
                    jmlLessThanEqualReq();

                    state._fsp--;


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
        return ;
    }
    // $ANTLR end "jmlRequires"


    // $ANTLR start "jmlOrReq"
    // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:108:1: jmlOrReq : jmlReq ( OR jmlReq )+ ;
    public final void jmlOrReq() throws RecognitionException {
        try {
            // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:108:9: ( jmlReq ( OR jmlReq )+ )
            // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:108:11: jmlReq ( OR jmlReq )+
            {
            pushFollow(FOLLOW_jmlReq_in_jmlOrReq1178);
            jmlReq();

            state._fsp--;

            // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:108:18: ( OR jmlReq )+
            int cnt7=0;
            loop7:
            do {
                int alt7=2;
                int LA7_0 = input.LA(1);

                if ( (LA7_0==OR) ) {
                    alt7=1;
                }


                switch (alt7) {
            	case 1 :
            	    // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:108:19: OR jmlReq
            	    {
            	    match(input,OR,FOLLOW_OR_in_jmlOrReq1181); 
            	    pushFollow(FOLLOW_jmlReq_in_jmlOrReq1183);
            	    jmlReq();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    if ( cnt7 >= 1 ) break loop7;
                        EarlyExitException eee =
                            new EarlyExitException(7, input);
                        throw eee;
                }
                cnt7++;
            } while (true);


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "jmlOrReq"


    // $ANTLR start "jmlLessThanEqualReq"
    // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:110:1: jmlLessThanEqualReq : ID LESSTHANEQUAL tstate= NUMBERS ;
    public final void jmlLessThanEqualReq() throws RecognitionException {
        Token tstate=null;

        try {
            // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:110:20: ( ID LESSTHANEQUAL tstate= NUMBERS )
            // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:110:22: ID LESSTHANEQUAL tstate= NUMBERS
            {
            match(input,ID,FOLLOW_ID_in_jmlLessThanEqualReq1192); 
            match(input,LESSTHANEQUAL,FOLLOW_LESSTHANEQUAL_in_jmlLessThanEqualReq1194); 
            tstate=(Token)match(input,NUMBERS,FOLLOW_NUMBERS_in_jmlLessThanEqualReq1198); 

            	      int n=Integer.parseInt((tstate!=null?tstate.getText():null));
            	      int x=1;
            	      while(x<=n){
            	       E_JmlSpecification.addRequires(""+x);
            	       x++;
            	    }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "jmlLessThanEqualReq"


    // $ANTLR start "jmlReq"
    // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:119:1: jmlReq : ID EQUALOPERATOR strState= NUMBERS ;
    public final void jmlReq() throws RecognitionException {
        Token strState=null;

        try {
            // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:119:7: ( ID EQUALOPERATOR strState= NUMBERS )
            // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:119:9: ID EQUALOPERATOR strState= NUMBERS
            {
            match(input,ID,FOLLOW_ID_in_jmlReq1207); 
            match(input,EQUALOPERATOR,FOLLOW_EQUALOPERATOR_in_jmlReq1209); 
            strState=(Token)match(input,NUMBERS,FOLLOW_NUMBERS_in_jmlReq1213); 

              E_JmlSpecification.addRequires((strState!=null?strState.getText():null));



            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "jmlReq"


    // $ANTLR start "jmlEnsures"
    // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:124:1: jmlEnsures : ( jmlEns | jmlOldEns );
    public final void jmlEnsures() throws RecognitionException {
        try {
            // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:124:11: ( jmlEns | jmlOldEns )
            int alt8=2;
            int LA8_0 = input.LA(1);

            if ( (LA8_0==ENSURES) ) {
                int LA8_1 = input.LA(2);

                if ( (LA8_1==ID) ) {
                    int LA8_2 = input.LA(3);

                    if ( (LA8_2==EQUALOPERATOR) ) {
                        int LA8_3 = input.LA(4);

                        if ( (LA8_3==NUMBERS) ) {
                            alt8=1;
                        }
                        else if ( (LA8_3==OLD) ) {
                            alt8=2;
                        }
                        else {
                            NoViableAltException nvae =
                                new NoViableAltException("", 8, 3, input);

                            throw nvae;
                        }
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 8, 2, input);

                        throw nvae;
                    }
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 8, 1, input);

                    throw nvae;
                }
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 8, 0, input);

                throw nvae;
            }
            switch (alt8) {
                case 1 :
                    // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:124:12: jmlEns
                    {
                    pushFollow(FOLLOW_jmlEns_in_jmlEnsures1221);
                    jmlEns();

                    state._fsp--;


                    }
                    break;
                case 2 :
                    // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:124:19: jmlOldEns
                    {
                    pushFollow(FOLLOW_jmlOldEns_in_jmlEnsures1223);
                    jmlOldEns();

                    state._fsp--;


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
        return ;
    }
    // $ANTLR end "jmlEnsures"


    // $ANTLR start "jmlOldEns"
    // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:126:1: jmlOldEns : ENSURES ID EQUALOPERATOR OLD LSBRACKET ID RSBRACKET (ope= PLUSMINUSOPERATOR num= NUMBERS )? SEMICOLON ;
    public final void jmlOldEns() throws RecognitionException {
        Token ope=null;
        Token num=null;

        try {
            // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:126:10: ( ENSURES ID EQUALOPERATOR OLD LSBRACKET ID RSBRACKET (ope= PLUSMINUSOPERATOR num= NUMBERS )? SEMICOLON )
            // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:126:11: ENSURES ID EQUALOPERATOR OLD LSBRACKET ID RSBRACKET (ope= PLUSMINUSOPERATOR num= NUMBERS )? SEMICOLON
            {
            match(input,ENSURES,FOLLOW_ENSURES_in_jmlOldEns1229); 
            match(input,ID,FOLLOW_ID_in_jmlOldEns1231); 
            match(input,EQUALOPERATOR,FOLLOW_EQUALOPERATOR_in_jmlOldEns1233); 
            match(input,OLD,FOLLOW_OLD_in_jmlOldEns1235); 
            match(input,LSBRACKET,FOLLOW_LSBRACKET_in_jmlOldEns1237); 
            match(input,ID,FOLLOW_ID_in_jmlOldEns1239); 
            match(input,RSBRACKET,FOLLOW_RSBRACKET_in_jmlOldEns1241); 
            // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:126:63: (ope= PLUSMINUSOPERATOR num= NUMBERS )?
            int alt9=2;
            int LA9_0 = input.LA(1);

            if ( (LA9_0==PLUSMINUSOPERATOR) ) {
                alt9=1;
            }
            switch (alt9) {
                case 1 :
                    // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:126:64: ope= PLUSMINUSOPERATOR num= NUMBERS
                    {
                    ope=(Token)match(input,PLUSMINUSOPERATOR,FOLLOW_PLUSMINUSOPERATOR_in_jmlOldEns1246); 
                    num=(Token)match(input,NUMBERS,FOLLOW_NUMBERS_in_jmlOldEns1250); 

                    }
                    break;

            }

            match(input,SEMICOLON,FOLLOW_SEMICOLON_in_jmlOldEns1254); 


              if ((ope!=null?ope.getText():null)!=null&& (num!=null?num.getText():null)!=null)
                E_JmlSpecification.setEnsures("old"+(ope!=null?ope.getText():null)+(num!=null?num.getText():null));
             else
                E_JmlSpecification.setEnsures("old");
             


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "jmlOldEns"


    // $ANTLR start "jmlEns"
    // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:136:1: jmlEns : ENSURES ID EQUALOPERATOR strState= NUMBERS SEMICOLON ;
    public final void jmlEns() throws RecognitionException {
        Token strState=null;

        try {
            // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:136:7: ( ENSURES ID EQUALOPERATOR strState= NUMBERS SEMICOLON )
            // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:136:9: ENSURES ID EQUALOPERATOR strState= NUMBERS SEMICOLON
            {
            match(input,ENSURES,FOLLOW_ENSURES_in_jmlEns1263); 
            match(input,ID,FOLLOW_ID_in_jmlEns1265); 
            match(input,EQUALOPERATOR,FOLLOW_EQUALOPERATOR_in_jmlEns1267); 
            strState=(Token)match(input,NUMBERS,FOLLOW_NUMBERS_in_jmlEns1271); 
            match(input,SEMICOLON,FOLLOW_SEMICOLON_in_jmlEns1273); 

              E_JmlSpecification.setEnsures((strState!=null?strState.getText():null));



            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "jmlEns"


    // $ANTLR start "jmlAssign"
    // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:141:1: jmlAssign : ASSIGNABLE (strPerm= '\\\\everything' | strPerm= '\\\\nothing' | strPerm= ID ) SEMICOLON ;
    public final void jmlAssign() throws RecognitionException {
        Token strPerm=null;

        try {
            // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:141:10: ( ASSIGNABLE (strPerm= '\\\\everything' | strPerm= '\\\\nothing' | strPerm= ID ) SEMICOLON )
            // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:141:11: ASSIGNABLE (strPerm= '\\\\everything' | strPerm= '\\\\nothing' | strPerm= ID ) SEMICOLON
            {
            match(input,ASSIGNABLE,FOLLOW_ASSIGNABLE_in_jmlAssign1280); 
            // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:141:22: (strPerm= '\\\\everything' | strPerm= '\\\\nothing' | strPerm= ID )
            int alt10=3;
            switch ( input.LA(1) ) {
            case EVERYTHING:
                {
                alt10=1;
                }
                break;
            case NOTHING:
                {
                alt10=2;
                }
                break;
            case ID:
                {
                alt10=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 10, 0, input);

                throw nvae;
            }

            switch (alt10) {
                case 1 :
                    // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:141:23: strPerm= '\\\\everything'
                    {
                    strPerm=(Token)match(input,EVERYTHING,FOLLOW_EVERYTHING_in_jmlAssign1285); 

                    }
                    break;
                case 2 :
                    // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:141:46: strPerm= '\\\\nothing'
                    {
                    strPerm=(Token)match(input,NOTHING,FOLLOW_NOTHING_in_jmlAssign1289); 

                    }
                    break;
                case 3 :
                    // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:141:66: strPerm= ID
                    {
                    strPerm=(Token)match(input,ID,FOLLOW_ID_in_jmlAssign1293); 

                    }
                    break;

            }

            match(input,SEMICOLON,FOLLOW_SEMICOLON_in_jmlAssign1296); 

                
                String perm="Pure";
                String str=(strPerm!=null?strPerm.getText():null);
                if (str.compareTo("\\nothing")==0)
                      perm="Pure";
                 else if (str.length()>0)
                        perm="Full";       
                E_JmlSpecification.setPerm(perm);


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "jmlAssign"


    // $ANTLR start "specifications"
    // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:157:1: specifications : ( perm | cases | classstates | refine );
    public final void specifications() throws RecognitionException {
        try {
            // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:157:15: ( perm | cases | classstates | refine )
            int alt11=4;
            switch ( input.LA(1) ) {
            case AT_FULL:
            case AT_PURE:
            case AT_IMMUTABLE:
            case AT_SHARE:
            case AT_UNIQUE:
            case PERM:
                {
                alt11=1;
                }
                break;
            case CASES:
                {
                alt11=2;
                }
                break;
            case CLASS_STATES:
                {
                alt11=3;
                }
                break;
            case REFINE:
                {
                alt11=4;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 11, 0, input);

                throw nvae;
            }

            switch (alt11) {
                case 1 :
                    // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:157:17: perm
                    {
                    pushFollow(FOLLOW_perm_in_specifications1309);
                    perm();

                    state._fsp--;


                    }
                    break;
                case 2 :
                    // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:157:22: cases
                    {
                    pushFollow(FOLLOW_cases_in_specifications1311);
                    cases();

                    state._fsp--;


                    }
                    break;
                case 3 :
                    // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:157:28: classstates
                    {
                    pushFollow(FOLLOW_classstates_in_specifications1313);
                    classstates();

                    state._fsp--;


                    }
                    break;
                case 4 :
                    // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:157:40: refine
                    {
                    pushFollow(FOLLOW_refine_in_specifications1315);
                    refine();

                    state._fsp--;


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
        return ;
    }
    // $ANTLR end "specifications"


    // $ANTLR start "refine"
    // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:160:1: refine : REFINE LSBRACKET LCBRACKET ( states ) ( PUNCTUATION states )* RCBRACKET RSBRACKET ;
    public final void refine() throws RecognitionException {
        try {
            // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:160:7: ( REFINE LSBRACKET LCBRACKET ( states ) ( PUNCTUATION states )* RCBRACKET RSBRACKET )
            // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:160:8: REFINE LSBRACKET LCBRACKET ( states ) ( PUNCTUATION states )* RCBRACKET RSBRACKET
            {
            match(input,REFINE,FOLLOW_REFINE_in_refine1322); 
            match(input,LSBRACKET,FOLLOW_LSBRACKET_in_refine1324); 
            match(input,LCBRACKET,FOLLOW_LCBRACKET_in_refine1326); 
            // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:160:35: ( states )
            // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:160:36: states
            {
            pushFollow(FOLLOW_states_in_refine1329);
            states();

            state._fsp--;


            }

            // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:160:44: ( PUNCTUATION states )*
            loop12:
            do {
                int alt12=2;
                int LA12_0 = input.LA(1);

                if ( (LA12_0==PUNCTUATION) ) {
                    alt12=1;
                }


                switch (alt12) {
            	case 1 :
            	    // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:160:45: PUNCTUATION states
            	    {
            	    match(input,PUNCTUATION,FOLLOW_PUNCTUATION_in_refine1333); 
            	    pushFollow(FOLLOW_states_in_refine1335);
            	    states();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop12;
                }
            } while (true);

            match(input,RCBRACKET,FOLLOW_RCBRACKET_in_refine1340); 
            match(input,RSBRACKET,FOLLOW_RSBRACKET_in_refine1342); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "refine"


    // $ANTLR start "states"
    // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:163:1: states : STATES LSBRACKET dimension PUNCTUATION ( value )* RSBRACKET ;
    public final void states() throws RecognitionException {
        try {
            // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:163:7: ( STATES LSBRACKET dimension PUNCTUATION ( value )* RSBRACKET )
            // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:163:9: STATES LSBRACKET dimension PUNCTUATION ( value )* RSBRACKET
            {
            match(input,STATES,FOLLOW_STATES_in_states1351); 
            match(input,LSBRACKET,FOLLOW_LSBRACKET_in_states1353); 
            pushFollow(FOLLOW_dimension_in_states1355);
            dimension();

            state._fsp--;

            match(input,PUNCTUATION,FOLLOW_PUNCTUATION_in_states1357); 
            // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:163:48: ( value )*
            loop13:
            do {
                int alt13=2;
                int LA13_0 = input.LA(1);

                if ( (LA13_0==VALUE) ) {
                    alt13=1;
                }


                switch (alt13) {
            	case 1 :
            	    // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:163:49: value
            	    {
            	    pushFollow(FOLLOW_value_in_states1360);
            	    value();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop13;
                }
            } while (true);

            match(input,RSBRACKET,FOLLOW_RSBRACKET_in_states1364); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "states"


    // $ANTLR start "dimension"
    // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:167:1: dimension : DIM EQUAL QUOTE dim= ID QUOTE ;
    public final void dimension() throws RecognitionException {
        Token dim=null;

        try {
            // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:167:10: ( DIM EQUAL QUOTE dim= ID QUOTE )
            // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:167:12: DIM EQUAL QUOTE dim= ID QUOTE
            {
            match(input,DIM,FOLLOW_DIM_in_dimension1373); 
            match(input,EQUAL,FOLLOW_EQUAL_in_dimension1375); 
            match(input,QUOTE,FOLLOW_QUOTE_in_dimension1377); 
            dim=(Token)match(input,ID,FOLLOW_ID_in_dimension1381); 
            match(input,QUOTE,FOLLOW_QUOTE_in_dimension1383); 

                                                      EVMDD_SMC_Generator.addDimension((dim!=null?dim.getText():null));
                                                    

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "dimension"


    // $ANTLR start "value"
    // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:172:1: value : VALUE EQUAL LCBRACKET item ( PUNCTUATION item )* RCBRACKET ;
    public final void value() throws RecognitionException {
        try {
            // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:172:6: ( VALUE EQUAL LCBRACKET item ( PUNCTUATION item )* RCBRACKET )
            // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:172:8: VALUE EQUAL LCBRACKET item ( PUNCTUATION item )* RCBRACKET
            {
            match(input,VALUE,FOLLOW_VALUE_in_value1393); 
            match(input,EQUAL,FOLLOW_EQUAL_in_value1395); 
            match(input,LCBRACKET,FOLLOW_LCBRACKET_in_value1397); 
            pushFollow(FOLLOW_item_in_value1399);
            item();

            state._fsp--;

            // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:172:35: ( PUNCTUATION item )*
            loop14:
            do {
                int alt14=2;
                int LA14_0 = input.LA(1);

                if ( (LA14_0==PUNCTUATION) ) {
                    alt14=1;
                }


                switch (alt14) {
            	case 1 :
            	    // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:172:36: PUNCTUATION item
            	    {
            	    match(input,PUNCTUATION,FOLLOW_PUNCTUATION_in_value1402); 
            	    pushFollow(FOLLOW_item_in_value1404);
            	    item();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop14;
                }
            } while (true);

            match(input,RCBRACKET,FOLLOW_RCBRACKET_in_value1408); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "value"


    // $ANTLR start "item"
    // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:174:1: item : QUOTE state_name= ID QUOTE ;
    public final void item() throws RecognitionException {
        Token state_name=null;

        try {
            // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:174:5: ( QUOTE state_name= ID QUOTE )
            // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:174:7: QUOTE state_name= ID QUOTE
            {
            match(input,QUOTE,FOLLOW_QUOTE_in_item1415); 
            state_name=(Token)match(input,ID,FOLLOW_ID_in_item1419); 
            match(input,QUOTE,FOLLOW_QUOTE_in_item1421); 

                                               EVMDD_SMC_Generator.addDimensionValue((state_name!=null?state_name.getText():null));
                                               EVMDD_SMC_Generator.addState((state_name!=null?state_name.getText():null));
                                             

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "item"


    // $ANTLR start "classstates"
    // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:181:1: classstates : start_classstates state ( PUNCTUATION state )* end_classstates ;
    public final void classstates() throws RecognitionException {
        try {
            // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:181:12: ( start_classstates state ( PUNCTUATION state )* end_classstates )
            // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:181:14: start_classstates state ( PUNCTUATION state )* end_classstates
            {
            pushFollow(FOLLOW_start_classstates_in_classstates1432);
            start_classstates();

            state._fsp--;

            pushFollow(FOLLOW_state_in_classstates1434);
            state();

            state._fsp--;

            // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:181:38: ( PUNCTUATION state )*
            loop15:
            do {
                int alt15=2;
                int LA15_0 = input.LA(1);

                if ( (LA15_0==PUNCTUATION) ) {
                    alt15=1;
                }


                switch (alt15) {
            	case 1 :
            	    // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:181:39: PUNCTUATION state
            	    {
            	    match(input,PUNCTUATION,FOLLOW_PUNCTUATION_in_classstates1437); 
            	    pushFollow(FOLLOW_state_in_classstates1439);
            	    state();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop15;
                }
            } while (true);

            pushFollow(FOLLOW_end_classstates_in_classstates1443);
            end_classstates();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "classstates"


    // $ANTLR start "start_classstates"
    // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:184:1: start_classstates : CLASS_STATES LSBRACKET LCBRACKET ;
    public final void start_classstates() throws RecognitionException {
        try {
            // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:184:18: ( CLASS_STATES LSBRACKET LCBRACKET )
            // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:184:19: CLASS_STATES LSBRACKET LCBRACKET
            {
            match(input,CLASS_STATES,FOLLOW_CLASS_STATES_in_start_classstates1450); 
            match(input,LSBRACKET,FOLLOW_LSBRACKET_in_start_classstates1452); 
            match(input,LCBRACKET,FOLLOW_LCBRACKET_in_start_classstates1454); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "start_classstates"


    // $ANTLR start "end_classstates"
    // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:187:1: end_classstates : RCBRACKET RSBRACKET ;
    public final void end_classstates() throws RecognitionException {
        try {
            // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:187:16: ( RCBRACKET RSBRACKET )
            // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:187:17: RCBRACKET RSBRACKET
            {
            match(input,RCBRACKET,FOLLOW_RCBRACKET_in_end_classstates1461); 
            match(input,RSBRACKET,FOLLOW_RSBRACKET_in_end_classstates1463); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "end_classstates"


    // $ANTLR start "state"
    // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:190:1: state : STATE LSBRACKET NAME EQUAL QUOTE state_name= ID QUOTE ( PUNCTUATION invariant )* RSBRACKET ;
    public final void state() throws RecognitionException {
        Token state_name=null;

        try {
            // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:190:6: ( STATE LSBRACKET NAME EQUAL QUOTE state_name= ID QUOTE ( PUNCTUATION invariant )* RSBRACKET )
            // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:190:8: STATE LSBRACKET NAME EQUAL QUOTE state_name= ID QUOTE ( PUNCTUATION invariant )* RSBRACKET
            {
            match(input,STATE,FOLLOW_STATE_in_state1471); 
            match(input,LSBRACKET,FOLLOW_LSBRACKET_in_state1473); 
            match(input,NAME,FOLLOW_NAME_in_state1475); 
            match(input,EQUAL,FOLLOW_EQUAL_in_state1477); 
            match(input,QUOTE,FOLLOW_QUOTE_in_state1479); 
            state_name=(Token)match(input,ID,FOLLOW_ID_in_state1483); 
            match(input,QUOTE,FOLLOW_QUOTE_in_state1485); 
             EVMDD_SMC_Generator.addState((state_name!=null?state_name.getText():null));
            // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:190:112: ( PUNCTUATION invariant )*
            loop16:
            do {
                int alt16=2;
                int LA16_0 = input.LA(1);

                if ( (LA16_0==PUNCTUATION) ) {
                    alt16=1;
                }


                switch (alt16) {
            	case 1 :
            	    // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:190:113: PUNCTUATION invariant
            	    {
            	    match(input,PUNCTUATION,FOLLOW_PUNCTUATION_in_state1490); 
            	    pushFollow(FOLLOW_invariant_in_state1492);
            	    invariant();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop16;
                }
            } while (true);

            match(input,RSBRACKET,FOLLOW_RSBRACKET_in_state1496); 
            // add states into class structure
                                                                                                           
                                                                                                            

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "state"


    // $ANTLR start "invariant"
    // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:195:1: invariant : INV EQUAL QUOTE ( condition ( AND condition )* )? QUOTE ;
    public final void invariant() throws RecognitionException {
        try {
            // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:195:10: ( INV EQUAL QUOTE ( condition ( AND condition )* )? QUOTE )
            // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:195:11: INV EQUAL QUOTE ( condition ( AND condition )* )? QUOTE
            {
            match(input,INV,FOLLOW_INV_in_invariant1591); 
            match(input,EQUAL,FOLLOW_EQUAL_in_invariant1593); 
            match(input,QUOTE,FOLLOW_QUOTE_in_invariant1595); 
            // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:195:27: ( condition ( AND condition )* )?
            int alt18=2;
            int LA18_0 = input.LA(1);

            if ( ((LA18_0>=FULL && LA18_0<=NONE)||LA18_0==ID) ) {
                alt18=1;
            }
            switch (alt18) {
                case 1 :
                    // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:195:28: condition ( AND condition )*
                    {
                    pushFollow(FOLLOW_condition_in_invariant1598);
                    condition();

                    state._fsp--;

                    // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:195:38: ( AND condition )*
                    loop17:
                    do {
                        int alt17=2;
                        int LA17_0 = input.LA(1);

                        if ( (LA17_0==AND) ) {
                            alt17=1;
                        }


                        switch (alt17) {
                    	case 1 :
                    	    // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:195:39: AND condition
                    	    {
                    	    match(input,AND,FOLLOW_AND_in_invariant1601); 
                    	    pushFollow(FOLLOW_condition_in_invariant1603);
                    	    condition();

                    	    state._fsp--;


                    	    }
                    	    break;

                    	default :
                    	    break loop17;
                        }
                    } while (true);


                    }
                    break;

            }

            match(input,QUOTE,FOLLOW_QUOTE_in_invariant1609); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "invariant"


    // $ANTLR start "condition"
    // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:198:1: condition : (var= ID op= OPERATOR val= ID | ap= accesspermission LSBRACKET var= ( THIS | ID ) RSBRACKET ( IN st= ID )? );
    public final void condition() throws RecognitionException {
        Token var=null;
        Token op=null;
        Token val=null;
        Token st=null;
        PluralParser.accesspermission_return ap = null;


        try {
            // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:198:11: (var= ID op= OPERATOR val= ID | ap= accesspermission LSBRACKET var= ( THIS | ID ) RSBRACKET ( IN st= ID )? )
            int alt20=2;
            int LA20_0 = input.LA(1);

            if ( (LA20_0==ID) ) {
                alt20=1;
            }
            else if ( ((LA20_0>=FULL && LA20_0<=NONE)) ) {
                alt20=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 20, 0, input);

                throw nvae;
            }
            switch (alt20) {
                case 1 :
                    // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:198:13: var= ID op= OPERATOR val= ID
                    {
                    var=(Token)match(input,ID,FOLLOW_ID_in_condition1620); 
                    op=(Token)match(input,OPERATOR,FOLLOW_OPERATOR_in_condition1624); 
                    val=(Token)match(input,ID,FOLLOW_ID_in_condition1628); 

                                                            String variable=(var!=null?var.getText():null);
                                                            String opertor=(op!=null?op.getText():null);
                                                            String value=(val!=null?val.getText():null);
                                                            EVMDD_SMC_Generator.addBoolStateInvariant(variable,opertor,value);
                                                          

                    }
                    break;
                case 2 :
                    // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:204:13: ap= accesspermission LSBRACKET var= ( THIS | ID ) RSBRACKET ( IN st= ID )?
                    {
                    pushFollow(FOLLOW_accesspermission_in_condition1646);
                    ap=accesspermission();

                    state._fsp--;

                    match(input,LSBRACKET,FOLLOW_LSBRACKET_in_condition1648); 
                    var=(Token)input.LT(1);
                    if ( input.LA(1)==THIS||input.LA(1)==ID ) {
                        input.consume();
                        state.errorRecovery=false;
                    }
                    else {
                        MismatchedSetException mse = new MismatchedSetException(null,input);
                        throw mse;
                    }

                    match(input,RSBRACKET,FOLLOW_RSBRACKET_in_condition1658); 
                    // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:204:67: ( IN st= ID )?
                    int alt19=2;
                    int LA19_0 = input.LA(1);

                    if ( (LA19_0==IN) ) {
                        alt19=1;
                    }
                    switch (alt19) {
                        case 1 :
                            // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:204:68: IN st= ID
                            {
                            match(input,IN,FOLLOW_IN_in_condition1661); 
                            st=(Token)match(input,ID,FOLLOW_ID_in_condition1665); 

                            }
                            break;

                    }


                                                                                          String accessPermission=(ap!=null?input.toString(ap.start,ap.stop):null);
                                                                                          String variable=(var!=null?var.getText():null);
                                                                                          String state=(st!=null?st.getText():null);
                                                                                          if (state==null)
                                                                                                state="alive";
                                                                                          EVMDD_SMC_Generator.addStateInvariant(accessPermission,variable,state);
                                                                                        

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
        return ;
    }
    // $ANTLR end "condition"


    // $ANTLR start "cases"
    // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:214:1: cases : CASES LSBRACKET LCBRACKET perm ( other perm )* RCBRACKET RSBRACKET ;
    public final void cases() throws RecognitionException {
        try {
            // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:214:6: ( CASES LSBRACKET LCBRACKET perm ( other perm )* RCBRACKET RSBRACKET )
            // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:214:7: CASES LSBRACKET LCBRACKET perm ( other perm )* RCBRACKET RSBRACKET
            {
            match(input,CASES,FOLLOW_CASES_in_cases1676); 
            match(input,LSBRACKET,FOLLOW_LSBRACKET_in_cases1678); 
            match(input,LCBRACKET,FOLLOW_LCBRACKET_in_cases1680); 
            pushFollow(FOLLOW_perm_in_cases1682);
            perm();

            state._fsp--;

            // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:214:38: ( other perm )*
            loop21:
            do {
                int alt21=2;
                int LA21_0 = input.LA(1);

                if ( (LA21_0==PUNCTUATION) ) {
                    alt21=1;
                }


                switch (alt21) {
            	case 1 :
            	    // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:214:39: other perm
            	    {
            	    pushFollow(FOLLOW_other_in_cases1685);
            	    other();

            	    state._fsp--;

            	    pushFollow(FOLLOW_perm_in_cases1687);
            	    perm();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop21;
                }
            } while (true);

            match(input,RCBRACKET,FOLLOW_RCBRACKET_in_cases1691); 
            match(input,RSBRACKET,FOLLOW_RSBRACKET_in_cases1693); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "cases"


    // $ANTLR start "other"
    // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:217:1: other : PUNCTUATION ;
    public final void other() throws RecognitionException {
        try {
            // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:217:6: ( PUNCTUATION )
            // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:217:8: PUNCTUATION
            {
            match(input,PUNCTUATION,FOLLOW_PUNCTUATION_in_other1701); 
            EVMDD_SMC_Generator.addCase();

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "other"


    // $ANTLR start "perm"
    // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:220:1: perm : ( PERM LSBRACKET requires_ensures_clause RSBRACKET | attype );
    public final void perm() throws RecognitionException {
        try {
            // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:220:5: ( PERM LSBRACKET requires_ensures_clause RSBRACKET | attype )
            int alt22=2;
            int LA22_0 = input.LA(1);

            if ( (LA22_0==PERM) ) {
                alt22=1;
            }
            else if ( ((LA22_0>=AT_FULL && LA22_0<=AT_UNIQUE)) ) {
                alt22=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 22, 0, input);

                throw nvae;
            }
            switch (alt22) {
                case 1 :
                    // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:220:7: PERM LSBRACKET requires_ensures_clause RSBRACKET
                    {
                    match(input,PERM,FOLLOW_PERM_in_perm1712); 
                    match(input,LSBRACKET,FOLLOW_LSBRACKET_in_perm1714); 
                    pushFollow(FOLLOW_requires_ensures_clause_in_perm1716);
                    requires_ensures_clause();

                    state._fsp--;

                    match(input,RSBRACKET,FOLLOW_RSBRACKET_in_perm1718); 

                    }
                    break;
                case 2 :
                    // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:221:7: attype
                    {
                    pushFollow(FOLLOW_attype_in_perm1727);
                    attype();

                    state._fsp--;


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
        return ;
    }
    // $ANTLR end "perm"


    // $ANTLR start "attype"
    // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:223:1: attype : ap= at_ap_permission ( LSBRACKET ( REQUIRES EQUAL QUOTE restate= typestate QUOTE )? ( PUNCTUATION )? ( ENSURES EQUAL QUOTE enstate= typestate QUOTE )? ( PUNCTUATION usevalue )? )? ;
    public final void attype() throws RecognitionException {
        PluralParser.at_ap_permission_return ap = null;

        PluralParser.typestate_return restate = null;

        PluralParser.typestate_return enstate = null;


        try {
            // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:223:7: (ap= at_ap_permission ( LSBRACKET ( REQUIRES EQUAL QUOTE restate= typestate QUOTE )? ( PUNCTUATION )? ( ENSURES EQUAL QUOTE enstate= typestate QUOTE )? ( PUNCTUATION usevalue )? )? )
            // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:223:8: ap= at_ap_permission ( LSBRACKET ( REQUIRES EQUAL QUOTE restate= typestate QUOTE )? ( PUNCTUATION )? ( ENSURES EQUAL QUOTE enstate= typestate QUOTE )? ( PUNCTUATION usevalue )? )?
            {
            pushFollow(FOLLOW_at_ap_permission_in_attype1735);
            ap=at_ap_permission();

            state._fsp--;

            // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:223:28: ( LSBRACKET ( REQUIRES EQUAL QUOTE restate= typestate QUOTE )? ( PUNCTUATION )? ( ENSURES EQUAL QUOTE enstate= typestate QUOTE )? ( PUNCTUATION usevalue )? )?
            int alt27=2;
            int LA27_0 = input.LA(1);

            if ( (LA27_0==LSBRACKET) ) {
                alt27=1;
            }
            switch (alt27) {
                case 1 :
                    // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:223:29: LSBRACKET ( REQUIRES EQUAL QUOTE restate= typestate QUOTE )? ( PUNCTUATION )? ( ENSURES EQUAL QUOTE enstate= typestate QUOTE )? ( PUNCTUATION usevalue )?
                    {
                    match(input,LSBRACKET,FOLLOW_LSBRACKET_in_attype1738); 
                    // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:223:39: ( REQUIRES EQUAL QUOTE restate= typestate QUOTE )?
                    int alt23=2;
                    int LA23_0 = input.LA(1);

                    if ( (LA23_0==REQUIRES) ) {
                        alt23=1;
                    }
                    switch (alt23) {
                        case 1 :
                            // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:223:40: REQUIRES EQUAL QUOTE restate= typestate QUOTE
                            {
                            match(input,REQUIRES,FOLLOW_REQUIRES_in_attype1741); 
                            match(input,EQUAL,FOLLOW_EQUAL_in_attype1743); 
                            match(input,QUOTE,FOLLOW_QUOTE_in_attype1745); 
                            pushFollow(FOLLOW_typestate_in_attype1749);
                            restate=typestate();

                            state._fsp--;

                            match(input,QUOTE,FOLLOW_QUOTE_in_attype1751); 

                            }
                            break;

                    }

                    // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:223:87: ( PUNCTUATION )?
                    int alt24=2;
                    int LA24_0 = input.LA(1);

                    if ( (LA24_0==PUNCTUATION) ) {
                        int LA24_1 = input.LA(2);

                        if ( (LA24_1==EOF||LA24_1==ENSURES||LA24_1==PUNCTUATION||LA24_1==RCBRACKET) ) {
                            alt24=1;
                        }
                    }
                    switch (alt24) {
                        case 1 :
                            // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:223:88: PUNCTUATION
                            {
                            match(input,PUNCTUATION,FOLLOW_PUNCTUATION_in_attype1756); 

                            }
                            break;

                    }

                    // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:223:102: ( ENSURES EQUAL QUOTE enstate= typestate QUOTE )?
                    int alt25=2;
                    int LA25_0 = input.LA(1);

                    if ( (LA25_0==ENSURES) ) {
                        alt25=1;
                    }
                    switch (alt25) {
                        case 1 :
                            // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:223:103: ENSURES EQUAL QUOTE enstate= typestate QUOTE
                            {
                            match(input,ENSURES,FOLLOW_ENSURES_in_attype1761); 
                            match(input,EQUAL,FOLLOW_EQUAL_in_attype1763); 
                            match(input,QUOTE,FOLLOW_QUOTE_in_attype1765); 
                            pushFollow(FOLLOW_typestate_in_attype1769);
                            enstate=typestate();

                            state._fsp--;

                            match(input,QUOTE,FOLLOW_QUOTE_in_attype1771); 

                            }
                            break;

                    }

                    // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:223:149: ( PUNCTUATION usevalue )?
                    int alt26=2;
                    int LA26_0 = input.LA(1);

                    if ( (LA26_0==PUNCTUATION) ) {
                        int LA26_1 = input.LA(2);

                        if ( (LA26_1==USE||LA26_1==VALUE) ) {
                            alt26=1;
                        }
                    }
                    switch (alt26) {
                        case 1 :
                            // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:223:150: PUNCTUATION usevalue
                            {
                            match(input,PUNCTUATION,FOLLOW_PUNCTUATION_in_attype1776); 
                            pushFollow(FOLLOW_usevalue_in_attype1778);
                            usevalue();

                            state._fsp--;


                            }
                            break;

                    }


                    }
                    break;

            }


                                                                    String str=(ap!=null?input.toString(ap.start,ap.stop):null);
                                                                    str=str.substring(1); //this remove @
                                                                    
                                                                    String re_state=(restate!=null?input.toString(restate.start,restate.stop):null);
                                                                    if (re_state==null)
                                                                        re_state="alive";
                                                                   
                                                                    String en_state=(enstate!=null?input.toString(enstate.start,enstate.stop):null);
                                                                    if (en_state==null)
                                                                        en_state="alive";
                                                                    
                                                                    EVMDD_SMC_Generator.addRequiresAP_TS(str.toString(),re_state);
                                                                    EVMDD_SMC_Generator.addEnsuresAP_TS(str.toString(),en_state);
                  

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "attype"


    // $ANTLR start "usevalue"
    // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:240:1: usevalue : ( USE EQUAL USEFIELDS | VALUE EQUAL QUOTE ID QUOTE );
    public final void usevalue() throws RecognitionException {
        try {
            // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:240:9: ( USE EQUAL USEFIELDS | VALUE EQUAL QUOTE ID QUOTE )
            int alt28=2;
            int LA28_0 = input.LA(1);

            if ( (LA28_0==USE) ) {
                alt28=1;
            }
            else if ( (LA28_0==VALUE) ) {
                alt28=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 28, 0, input);

                throw nvae;
            }
            switch (alt28) {
                case 1 :
                    // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:240:11: USE EQUAL USEFIELDS
                    {
                    match(input,USE,FOLLOW_USE_in_usevalue1792); 
                    match(input,EQUAL,FOLLOW_EQUAL_in_usevalue1794); 
                    match(input,USEFIELDS,FOLLOW_USEFIELDS_in_usevalue1796); 

                    }
                    break;
                case 2 :
                    // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:241:11: VALUE EQUAL QUOTE ID QUOTE
                    {
                    match(input,VALUE,FOLLOW_VALUE_in_usevalue1809); 
                    match(input,EQUAL,FOLLOW_EQUAL_in_usevalue1811); 
                    match(input,QUOTE,FOLLOW_QUOTE_in_usevalue1813); 
                    match(input,ID,FOLLOW_ID_in_usevalue1815); 
                    match(input,QUOTE,FOLLOW_QUOTE_in_usevalue1817); 

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
        return ;
    }
    // $ANTLR end "usevalue"


    // $ANTLR start "requires_ensures_clause"
    // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:245:1: requires_ensures_clause : ( requires_clause )? ( PUNCTUATION )? ( ensures_clause )? ;
    public final void requires_ensures_clause() throws RecognitionException {
        try {
            // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:245:24: ( ( requires_clause )? ( PUNCTUATION )? ( ensures_clause )? )
            // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:245:26: ( requires_clause )? ( PUNCTUATION )? ( ensures_clause )?
            {
            // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:245:26: ( requires_clause )?
            int alt29=2;
            int LA29_0 = input.LA(1);

            if ( (LA29_0==REQUIRES) ) {
                alt29=1;
            }
            switch (alt29) {
                case 1 :
                    // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:245:27: requires_clause
                    {
                    pushFollow(FOLLOW_requires_clause_in_requires_ensures_clause1831);
                    requires_clause();

                    state._fsp--;


                    }
                    break;

            }

            // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:245:45: ( PUNCTUATION )?
            int alt30=2;
            int LA30_0 = input.LA(1);

            if ( (LA30_0==PUNCTUATION) ) {
                alt30=1;
            }
            switch (alt30) {
                case 1 :
                    // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:245:46: PUNCTUATION
                    {
                    match(input,PUNCTUATION,FOLLOW_PUNCTUATION_in_requires_ensures_clause1836); 

                    }
                    break;

            }

            // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:245:60: ( ensures_clause )?
            int alt31=2;
            int LA31_0 = input.LA(1);

            if ( (LA31_0==ENSURES) ) {
                alt31=1;
            }
            switch (alt31) {
                case 1 :
                    // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:245:61: ensures_clause
                    {
                    pushFollow(FOLLOW_ensures_clause_in_requires_ensures_clause1841);
                    ensures_clause();

                    state._fsp--;


                    }
                    break;

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "requires_ensures_clause"


    // $ANTLR start "requires_clause"
    // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:248:1: requires_clause : REQUIRES EQUAL QUOTE re_accesspermission_typestates ( AND re_accesspermission_typestates )* QUOTE ;
    public final void requires_clause() throws RecognitionException {
        try {
            // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:248:17: ( REQUIRES EQUAL QUOTE re_accesspermission_typestates ( AND re_accesspermission_typestates )* QUOTE )
            // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:248:19: REQUIRES EQUAL QUOTE re_accesspermission_typestates ( AND re_accesspermission_typestates )* QUOTE
            {
            match(input,REQUIRES,FOLLOW_REQUIRES_in_requires_clause1852); 
            match(input,EQUAL,FOLLOW_EQUAL_in_requires_clause1854); 
            match(input,QUOTE,FOLLOW_QUOTE_in_requires_clause1856); 
            pushFollow(FOLLOW_re_accesspermission_typestates_in_requires_clause1858);
            re_accesspermission_typestates();

            state._fsp--;

            // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:248:71: ( AND re_accesspermission_typestates )*
            loop32:
            do {
                int alt32=2;
                int LA32_0 = input.LA(1);

                if ( (LA32_0==AND) ) {
                    alt32=1;
                }


                switch (alt32) {
            	case 1 :
            	    // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:248:72: AND re_accesspermission_typestates
            	    {
            	    match(input,AND,FOLLOW_AND_in_requires_clause1861); 
            	    pushFollow(FOLLOW_re_accesspermission_typestates_in_requires_clause1863);
            	    re_accesspermission_typestates();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop32;
                }
            } while (true);

            match(input,QUOTE,FOLLOW_QUOTE_in_requires_clause1867); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "requires_clause"


    // $ANTLR start "ensures_clause"
    // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:251:1: ensures_clause : ENSURES EQUAL QUOTE en_accesspermission_typestates ( AND en_accesspermission_typestates )* QUOTE ;
    public final void ensures_clause() throws RecognitionException {
        try {
            // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:251:15: ( ENSURES EQUAL QUOTE en_accesspermission_typestates ( AND en_accesspermission_typestates )* QUOTE )
            // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:251:17: ENSURES EQUAL QUOTE en_accesspermission_typestates ( AND en_accesspermission_typestates )* QUOTE
            {
            match(input,ENSURES,FOLLOW_ENSURES_in_ensures_clause1875); 
            match(input,EQUAL,FOLLOW_EQUAL_in_ensures_clause1877); 
            match(input,QUOTE,FOLLOW_QUOTE_in_ensures_clause1879); 
            pushFollow(FOLLOW_en_accesspermission_typestates_in_ensures_clause1881);
            en_accesspermission_typestates();

            state._fsp--;

            // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:251:68: ( AND en_accesspermission_typestates )*
            loop33:
            do {
                int alt33=2;
                int LA33_0 = input.LA(1);

                if ( (LA33_0==AND) ) {
                    alt33=1;
                }


                switch (alt33) {
            	case 1 :
            	    // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:251:69: AND en_accesspermission_typestates
            	    {
            	    match(input,AND,FOLLOW_AND_in_ensures_clause1884); 
            	    pushFollow(FOLLOW_en_accesspermission_typestates_in_ensures_clause1886);
            	    en_accesspermission_typestates();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop33;
                }
            } while (true);

            match(input,QUOTE,FOLLOW_QUOTE_in_ensures_clause1890); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "ensures_clause"


    // $ANTLR start "re_accesspermission_typestates"
    // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:254:1: re_accesspermission_typestates : (ap= accesspermission LSBRACKET THIS RSBRACKET ( IN st= typestate )? | ap= accesspermission LSBRACKET para= PARAM RSBRACKET ( IN st= typestate )? | para= PARAM OPERATOR ID );
    public final void re_accesspermission_typestates() throws RecognitionException {
        Token para=null;
        PluralParser.accesspermission_return ap = null;

        PluralParser.typestate_return st = null;


        try {
            // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:254:32: (ap= accesspermission LSBRACKET THIS RSBRACKET ( IN st= typestate )? | ap= accesspermission LSBRACKET para= PARAM RSBRACKET ( IN st= typestate )? | para= PARAM OPERATOR ID )
            int alt36=3;
            int LA36_0 = input.LA(1);

            if ( ((LA36_0>=FULL && LA36_0<=NONE)) ) {
                int LA36_1 = input.LA(2);

                if ( (LA36_1==LSBRACKET) ) {
                    int LA36_3 = input.LA(3);

                    if ( (LA36_3==PARAM) ) {
                        alt36=2;
                    }
                    else if ( (LA36_3==THIS) ) {
                        alt36=1;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 36, 3, input);

                        throw nvae;
                    }
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 36, 1, input);

                    throw nvae;
                }
            }
            else if ( (LA36_0==PARAM) ) {
                alt36=3;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 36, 0, input);

                throw nvae;
            }
            switch (alt36) {
                case 1 :
                    // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:254:34: ap= accesspermission LSBRACKET THIS RSBRACKET ( IN st= typestate )?
                    {
                    pushFollow(FOLLOW_accesspermission_in_re_accesspermission_typestates1901);
                    ap=accesspermission();

                    state._fsp--;

                    match(input,LSBRACKET,FOLLOW_LSBRACKET_in_re_accesspermission_typestates1903); 
                    match(input,THIS,FOLLOW_THIS_in_re_accesspermission_typestates1905); 
                    match(input,RSBRACKET,FOLLOW_RSBRACKET_in_re_accesspermission_typestates1907); 
                    // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:254:79: ( IN st= typestate )?
                    int alt34=2;
                    int LA34_0 = input.LA(1);

                    if ( (LA34_0==IN) ) {
                        alt34=1;
                    }
                    switch (alt34) {
                        case 1 :
                            // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:254:80: IN st= typestate
                            {
                            match(input,IN,FOLLOW_IN_in_re_accesspermission_typestates1910); 
                            pushFollow(FOLLOW_typestate_in_re_accesspermission_typestates1914);
                            st=typestate();

                            state._fsp--;


                            }
                            break;

                    }


                                                                                                            String en_state=(st!=null?input.toString(st.start,st.stop):null);
                                                                                                            if (en_state==null)
                                                                                                                en_state="alive";
                                                                                                            EVMDD_SMC_Generator.addRequiresAP_TS((ap!=null?input.toString(ap.start,ap.stop):null),en_state);
                                                                                                

                    }
                    break;
                case 2 :
                    // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:260:33: ap= accesspermission LSBRACKET para= PARAM RSBRACKET ( IN st= typestate )?
                    {
                    pushFollow(FOLLOW_accesspermission_in_re_accesspermission_typestates1954);
                    ap=accesspermission();

                    state._fsp--;

                    match(input,LSBRACKET,FOLLOW_LSBRACKET_in_re_accesspermission_typestates1956); 
                    para=(Token)match(input,PARAM,FOLLOW_PARAM_in_re_accesspermission_typestates1960); 
                    match(input,RSBRACKET,FOLLOW_RSBRACKET_in_re_accesspermission_typestates1962); 
                    // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:260:84: ( IN st= typestate )?
                    int alt35=2;
                    int LA35_0 = input.LA(1);

                    if ( (LA35_0==IN) ) {
                        alt35=1;
                    }
                    switch (alt35) {
                        case 1 :
                            // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:260:85: IN st= typestate
                            {
                            match(input,IN,FOLLOW_IN_in_re_accesspermission_typestates1965); 
                            pushFollow(FOLLOW_typestate_in_re_accesspermission_typestates1969);
                            st=typestate();

                            state._fsp--;


                            }
                            break;

                    }


                                                                                                String re_state=(st!=null?input.toString(st.start,st.stop):null);
                                                                                                if (re_state==null)
                                                                                                          re_state="alive";
                                                                                                String param_number=(para!=null?para.getText():null);
                                                                                                param_number=param_number.substring(1); //this remove #
                                                                                                EVMDD_SMC_Generator.addRequiresParam_AP_TS((ap!=null?input.toString(ap.start,ap.stop):null),re_state,param_number);
                                                                                                

                    }
                    break;
                case 3 :
                    // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:268:34: para= PARAM OPERATOR ID
                    {
                    para=(Token)match(input,PARAM,FOLLOW_PARAM_in_re_accesspermission_typestates2011); 
                    match(input,OPERATOR,FOLLOW_OPERATOR_in_re_accesspermission_typestates2013); 
                    match(input,ID,FOLLOW_ID_in_re_accesspermission_typestates2015); 

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
        return ;
    }
    // $ANTLR end "re_accesspermission_typestates"


    // $ANTLR start "en_accesspermission_typestates"
    // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:271:1: en_accesspermission_typestates : (ap= accesspermission LSBRACKET THIS RSBRACKET ( IN st= typestate )? | ap= accesspermission LSBRACKET RESULT RSBRACKET ( IN st= typestate )? | ap= accesspermission LSBRACKET para= PARAM RSBRACKET ( IN st= typestate )? | para= PARAM OPERATOR ID );
    public final void en_accesspermission_typestates() throws RecognitionException {
        Token para=null;
        PluralParser.accesspermission_return ap = null;

        PluralParser.typestate_return st = null;


        try {
            // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:271:32: (ap= accesspermission LSBRACKET THIS RSBRACKET ( IN st= typestate )? | ap= accesspermission LSBRACKET RESULT RSBRACKET ( IN st= typestate )? | ap= accesspermission LSBRACKET para= PARAM RSBRACKET ( IN st= typestate )? | para= PARAM OPERATOR ID )
            int alt40=4;
            int LA40_0 = input.LA(1);

            if ( ((LA40_0>=FULL && LA40_0<=NONE)) ) {
                int LA40_1 = input.LA(2);

                if ( (LA40_1==LSBRACKET) ) {
                    switch ( input.LA(3) ) {
                    case THIS:
                        {
                        alt40=1;
                        }
                        break;
                    case RESULT:
                        {
                        alt40=2;
                        }
                        break;
                    case PARAM:
                        {
                        alt40=3;
                        }
                        break;
                    default:
                        NoViableAltException nvae =
                            new NoViableAltException("", 40, 3, input);

                        throw nvae;
                    }

                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 40, 1, input);

                    throw nvae;
                }
            }
            else if ( (LA40_0==PARAM) ) {
                alt40=4;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 40, 0, input);

                throw nvae;
            }
            switch (alt40) {
                case 1 :
                    // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:271:34: ap= accesspermission LSBRACKET THIS RSBRACKET ( IN st= typestate )?
                    {
                    pushFollow(FOLLOW_accesspermission_in_en_accesspermission_typestates2146);
                    ap=accesspermission();

                    state._fsp--;

                    match(input,LSBRACKET,FOLLOW_LSBRACKET_in_en_accesspermission_typestates2148); 
                    match(input,THIS,FOLLOW_THIS_in_en_accesspermission_typestates2150); 
                    match(input,RSBRACKET,FOLLOW_RSBRACKET_in_en_accesspermission_typestates2152); 
                    // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:271:79: ( IN st= typestate )?
                    int alt37=2;
                    int LA37_0 = input.LA(1);

                    if ( (LA37_0==IN) ) {
                        alt37=1;
                    }
                    switch (alt37) {
                        case 1 :
                            // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:271:80: IN st= typestate
                            {
                            match(input,IN,FOLLOW_IN_in_en_accesspermission_typestates2155); 
                            pushFollow(FOLLOW_typestate_in_en_accesspermission_typestates2159);
                            st=typestate();

                            state._fsp--;


                            }
                            break;

                    }

                     
                                                                                                 String en_state=(st!=null?input.toString(st.start,st.stop):null);
                                                                                                 if (en_state==null)
                                                                                                     en_state="alive";
                                                                                                EVMDD_SMC_Generator.addEnsuresAP_TS((ap!=null?input.toString(ap.start,ap.stop):null),en_state);
                                                                                                

                    }
                    break;
                case 2 :
                    // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:277:33: ap= accesspermission LSBRACKET RESULT RSBRACKET ( IN st= typestate )?
                    {
                    pushFollow(FOLLOW_accesspermission_in_en_accesspermission_typestates2199);
                    ap=accesspermission();

                    state._fsp--;

                    match(input,LSBRACKET,FOLLOW_LSBRACKET_in_en_accesspermission_typestates2201); 
                    match(input,RESULT,FOLLOW_RESULT_in_en_accesspermission_typestates2203); 
                    match(input,RSBRACKET,FOLLOW_RSBRACKET_in_en_accesspermission_typestates2205); 
                    // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:277:80: ( IN st= typestate )?
                    int alt38=2;
                    int LA38_0 = input.LA(1);

                    if ( (LA38_0==IN) ) {
                        alt38=1;
                    }
                    switch (alt38) {
                        case 1 :
                            // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:277:81: IN st= typestate
                            {
                            match(input,IN,FOLLOW_IN_in_en_accesspermission_typestates2208); 
                            pushFollow(FOLLOW_typestate_in_en_accesspermission_typestates2212);
                            st=typestate();

                            state._fsp--;


                            }
                            break;

                    }

                     
                                                                                                 String en_state=(st!=null?input.toString(st.start,st.stop):null);
                                                                                                 if (en_state==null)
                                                                                                     en_state="alive";
                                                                                                EVMDD_SMC_Generator.addEnsuresResult_AP_TS((ap!=null?input.toString(ap.start,ap.stop):null),en_state);
                                                                                                

                    }
                    break;
                case 3 :
                    // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:283:33: ap= accesspermission LSBRACKET para= PARAM RSBRACKET ( IN st= typestate )?
                    {
                    pushFollow(FOLLOW_accesspermission_in_en_accesspermission_typestates2252);
                    ap=accesspermission();

                    state._fsp--;

                    match(input,LSBRACKET,FOLLOW_LSBRACKET_in_en_accesspermission_typestates2254); 
                    para=(Token)match(input,PARAM,FOLLOW_PARAM_in_en_accesspermission_typestates2258); 
                    match(input,RSBRACKET,FOLLOW_RSBRACKET_in_en_accesspermission_typestates2260); 
                    // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:283:84: ( IN st= typestate )?
                    int alt39=2;
                    int LA39_0 = input.LA(1);

                    if ( (LA39_0==IN) ) {
                        alt39=1;
                    }
                    switch (alt39) {
                        case 1 :
                            // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:283:85: IN st= typestate
                            {
                            match(input,IN,FOLLOW_IN_in_en_accesspermission_typestates2263); 
                            pushFollow(FOLLOW_typestate_in_en_accesspermission_typestates2267);
                            st=typestate();

                            state._fsp--;


                            }
                            break;

                    }


                                                                                                String en_state=(st!=null?input.toString(st.start,st.stop):null);
                                                                                                if (en_state==null)
                                                                                                      en_state="alive";
                                                                                                String param_number=(para!=null?para.getText():null);
                                                                                                param_number=param_number.substring(1); //this remove #
                                                                                                EVMDD_SMC_Generator.addEnsuresParam_AP_TS((ap!=null?input.toString(ap.start,ap.stop):null),en_state,param_number);
                                                                                                

                    }
                    break;
                case 4 :
                    // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:291:34: para= PARAM OPERATOR ID
                    {
                    para=(Token)match(input,PARAM,FOLLOW_PARAM_in_en_accesspermission_typestates2309); 
                    match(input,OPERATOR,FOLLOW_OPERATOR_in_en_accesspermission_typestates2311); 
                    match(input,ID,FOLLOW_ID_in_en_accesspermission_typestates2313); 

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
        return ;
    }
    // $ANTLR end "en_accesspermission_typestates"

    public static class typestate_return extends ParserRuleReturnScope {
    };

    // $ANTLR start "typestate"
    // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:294:1: typestate : ID ;
    public final PluralParser.typestate_return typestate() throws RecognitionException {
        PluralParser.typestate_return retval = new PluralParser.typestate_return();
        retval.start = input.LT(1);

        try {
            // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:294:10: ( ID )
            // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:294:12: ID
            {
            match(input,ID,FOLLOW_ID_in_typestate2373); 

            }

            retval.stop = input.LT(-1);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "typestate"

    public static class at_ap_permission_return extends ParserRuleReturnScope {
    };

    // $ANTLR start "at_ap_permission"
    // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:297:1: at_ap_permission : ( AT_UNIQUE | AT_FULL | AT_SHARE | AT_PURE | AT_IMMUTABLE );
    public final PluralParser.at_ap_permission_return at_ap_permission() throws RecognitionException {
        PluralParser.at_ap_permission_return retval = new PluralParser.at_ap_permission_return();
        retval.start = input.LT(1);

        try {
            // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:297:19: ( AT_UNIQUE | AT_FULL | AT_SHARE | AT_PURE | AT_IMMUTABLE )
            // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:
            {
            if ( (input.LA(1)>=AT_FULL && input.LA(1)<=AT_UNIQUE) ) {
                input.consume();
                state.errorRecovery=false;
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                throw mse;
            }


            }

            retval.stop = input.LT(-1);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "at_ap_permission"

    public static class accesspermission_return extends ParserRuleReturnScope {
    };

    // $ANTLR start "accesspermission"
    // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:305:1: accesspermission : ( UNIQUE | FULL | SHARE | PURE | IMMUTABLE | NONE );
    public final PluralParser.accesspermission_return accesspermission() throws RecognitionException {
        PluralParser.accesspermission_return retval = new PluralParser.accesspermission_return();
        retval.start = input.LT(1);

        try {
            // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:305:17: ( UNIQUE | FULL | SHARE | PURE | IMMUTABLE | NONE )
            // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:
            {
            if ( (input.LA(1)>=FULL && input.LA(1)<=NONE) ) {
                input.consume();
                state.errorRecovery=false;
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                throw mse;
            }


            }

            retval.stop = input.LT(-1);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "accesspermission"

    // Delegated rules


 

    public static final BitSet FOLLOW_jmlClassSpecifications_in_jmlSpecifications1071 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_jmlMethodSpecification_in_jmlSpecifications1073 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_jmlGhostDeclaration_in_jmlClassSpecifications1081 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_jmlGhostInv_in_jmlClassSpecifications1083 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_GHOST_in_jmlGhostDeclaration1090 = new BitSet(new long[]{0x0400000000000000L});
    public static final BitSet FOLLOW_INT_in_jmlGhostDeclaration1092 = new BitSet(new long[]{0x2000000000000000L});
    public static final BitSet FOLLOW_ID_in_jmlGhostDeclaration1096 = new BitSet(new long[]{0x0000100000000000L});
    public static final BitSet FOLLOW_SEMICOLON_in_jmlGhostDeclaration1098 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_INVARIANT_in_jmlGhostInv1106 = new BitSet(new long[]{0x4000000000000000L});
    public static final BitSet FOLLOW_NUMBERS_in_jmlGhostInv1111 = new BitSet(new long[]{0x0000400000000000L});
    public static final BitSet FOLLOW_LESSTHANEQUAL_in_jmlGhostInv1113 = new BitSet(new long[]{0x2000000000000000L});
    public static final BitSet FOLLOW_ID_in_jmlGhostInv1115 = new BitSet(new long[]{0x0002000000000000L});
    public static final BitSet FOLLOW_ANDD_in_jmlGhostInv1117 = new BitSet(new long[]{0x2000000000000000L});
    public static final BitSet FOLLOW_ID_in_jmlGhostInv1119 = new BitSet(new long[]{0x0000400000000000L});
    public static final BitSet FOLLOW_LESSTHANEQUAL_in_jmlGhostInv1121 = new BitSet(new long[]{0x4000000000000000L});
    public static final BitSet FOLLOW_NUMBERS_in_jmlGhostInv1125 = new BitSet(new long[]{0x0000100000000000L});
    public static final BitSet FOLLOW_SEMICOLON_in_jmlGhostInv1127 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_JMLSTART_in_jmlMethodSpecification1136 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_PUBLIC_BEHAVIOR_in_jmlMethodSpecification1138 = new BitSet(new long[]{0x0050000006000000L});
    public static final BitSet FOLLOW_REQUIRES_in_jmlMethodSpecification1142 = new BitSet(new long[]{0x2000000000000000L});
    public static final BitSet FOLLOW_jmlRequires_in_jmlMethodSpecification1144 = new BitSet(new long[]{0x0000100000000000L});
    public static final BitSet FOLLOW_SEMICOLON_in_jmlMethodSpecification1147 = new BitSet(new long[]{0x0050000004000000L});
    public static final BitSet FOLLOW_jmlAssign_in_jmlMethodSpecification1152 = new BitSet(new long[]{0x0010000004000000L});
    public static final BitSet FOLLOW_jmlEnsures_in_jmlMethodSpecification1157 = new BitSet(new long[]{0x0010000000000000L});
    public static final BitSet FOLLOW_JMLEND_in_jmlMethodSpecification1161 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_jmlReq_in_jmlRequires1167 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_jmlOrReq_in_jmlRequires1169 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_jmlLessThanEqualReq_in_jmlRequires1171 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_jmlReq_in_jmlOrReq1178 = new BitSet(new long[]{0x0004000000000000L});
    public static final BitSet FOLLOW_OR_in_jmlOrReq1181 = new BitSet(new long[]{0x2000000000000000L});
    public static final BitSet FOLLOW_jmlReq_in_jmlOrReq1183 = new BitSet(new long[]{0x0004000000000002L});
    public static final BitSet FOLLOW_ID_in_jmlLessThanEqualReq1192 = new BitSet(new long[]{0x0000400000000000L});
    public static final BitSet FOLLOW_LESSTHANEQUAL_in_jmlLessThanEqualReq1194 = new BitSet(new long[]{0x4000000000000000L});
    public static final BitSet FOLLOW_NUMBERS_in_jmlLessThanEqualReq1198 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_jmlReq1207 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_EQUALOPERATOR_in_jmlReq1209 = new BitSet(new long[]{0x4000000000000000L});
    public static final BitSet FOLLOW_NUMBERS_in_jmlReq1213 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_jmlEns_in_jmlEnsures1221 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_jmlOldEns_in_jmlEnsures1223 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ENSURES_in_jmlOldEns1229 = new BitSet(new long[]{0x2000000000000000L});
    public static final BitSet FOLLOW_ID_in_jmlOldEns1231 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_EQUALOPERATOR_in_jmlOldEns1233 = new BitSet(new long[]{0x1000000000000000L});
    public static final BitSet FOLLOW_OLD_in_jmlOldEns1235 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_LSBRACKET_in_jmlOldEns1237 = new BitSet(new long[]{0x2000000000000000L});
    public static final BitSet FOLLOW_ID_in_jmlOldEns1239 = new BitSet(new long[]{0x0000000000020000L});
    public static final BitSet FOLLOW_RSBRACKET_in_jmlOldEns1241 = new BitSet(new long[]{0x0020100000000000L});
    public static final BitSet FOLLOW_PLUSMINUSOPERATOR_in_jmlOldEns1246 = new BitSet(new long[]{0x4000000000000000L});
    public static final BitSet FOLLOW_NUMBERS_in_jmlOldEns1250 = new BitSet(new long[]{0x0000100000000000L});
    public static final BitSet FOLLOW_SEMICOLON_in_jmlOldEns1254 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ENSURES_in_jmlEns1263 = new BitSet(new long[]{0x2000000000000000L});
    public static final BitSet FOLLOW_ID_in_jmlEns1265 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_EQUALOPERATOR_in_jmlEns1267 = new BitSet(new long[]{0x4000000000000000L});
    public static final BitSet FOLLOW_NUMBERS_in_jmlEns1271 = new BitSet(new long[]{0x0000100000000000L});
    public static final BitSet FOLLOW_SEMICOLON_in_jmlEns1273 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ASSIGNABLE_in_jmlAssign1280 = new BitSet(new long[]{0x2180000000000000L});
    public static final BitSet FOLLOW_EVERYTHING_in_jmlAssign1285 = new BitSet(new long[]{0x0000100000000000L});
    public static final BitSet FOLLOW_NOTHING_in_jmlAssign1289 = new BitSet(new long[]{0x0000100000000000L});
    public static final BitSet FOLLOW_ID_in_jmlAssign1293 = new BitSet(new long[]{0x0000100000000000L});
    public static final BitSet FOLLOW_SEMICOLON_in_jmlAssign1296 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_perm_in_specifications1309 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_cases_in_specifications1311 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_classstates_in_specifications1313 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_refine_in_specifications1315 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_REFINE_in_refine1322 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_LSBRACKET_in_refine1324 = new BitSet(new long[]{0x0000000200000000L});
    public static final BitSet FOLLOW_LCBRACKET_in_refine1326 = new BitSet(new long[]{0x0000008000000000L});
    public static final BitSet FOLLOW_states_in_refine1329 = new BitSet(new long[]{0x0000000480000000L});
    public static final BitSet FOLLOW_PUNCTUATION_in_refine1333 = new BitSet(new long[]{0x0000008000000000L});
    public static final BitSet FOLLOW_states_in_refine1335 = new BitSet(new long[]{0x0000000480000000L});
    public static final BitSet FOLLOW_RCBRACKET_in_refine1340 = new BitSet(new long[]{0x0000000000020000L});
    public static final BitSet FOLLOW_RSBRACKET_in_refine1342 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_STATES_in_states1351 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_LSBRACKET_in_states1353 = new BitSet(new long[]{0x0000010000000000L});
    public static final BitSet FOLLOW_dimension_in_states1355 = new BitSet(new long[]{0x0000000080000000L});
    public static final BitSet FOLLOW_PUNCTUATION_in_states1357 = new BitSet(new long[]{0x0000002000020000L});
    public static final BitSet FOLLOW_value_in_states1360 = new BitSet(new long[]{0x0000002000020000L});
    public static final BitSet FOLLOW_RSBRACKET_in_states1364 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DIM_in_dimension1373 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_EQUAL_in_dimension1375 = new BitSet(new long[]{0x0000000008000000L});
    public static final BitSet FOLLOW_QUOTE_in_dimension1377 = new BitSet(new long[]{0x2000000000000000L});
    public static final BitSet FOLLOW_ID_in_dimension1381 = new BitSet(new long[]{0x0000000008000000L});
    public static final BitSet FOLLOW_QUOTE_in_dimension1383 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_VALUE_in_value1393 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_EQUAL_in_value1395 = new BitSet(new long[]{0x0000000200000000L});
    public static final BitSet FOLLOW_LCBRACKET_in_value1397 = new BitSet(new long[]{0x0000000008000000L});
    public static final BitSet FOLLOW_item_in_value1399 = new BitSet(new long[]{0x0000000480000000L});
    public static final BitSet FOLLOW_PUNCTUATION_in_value1402 = new BitSet(new long[]{0x0000000008000000L});
    public static final BitSet FOLLOW_item_in_value1404 = new BitSet(new long[]{0x0000000480000000L});
    public static final BitSet FOLLOW_RCBRACKET_in_value1408 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_QUOTE_in_item1415 = new BitSet(new long[]{0x2000000000000000L});
    public static final BitSet FOLLOW_ID_in_item1419 = new BitSet(new long[]{0x0000000008000000L});
    public static final BitSet FOLLOW_QUOTE_in_item1421 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_start_classstates_in_classstates1432 = new BitSet(new long[]{0x0000004000000000L});
    public static final BitSet FOLLOW_state_in_classstates1434 = new BitSet(new long[]{0x0000000480000000L});
    public static final BitSet FOLLOW_PUNCTUATION_in_classstates1437 = new BitSet(new long[]{0x0000004000000000L});
    public static final BitSet FOLLOW_state_in_classstates1439 = new BitSet(new long[]{0x0000000480000000L});
    public static final BitSet FOLLOW_end_classstates_in_classstates1443 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CLASS_STATES_in_start_classstates1450 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_LSBRACKET_in_start_classstates1452 = new BitSet(new long[]{0x0000000200000000L});
    public static final BitSet FOLLOW_LCBRACKET_in_start_classstates1454 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RCBRACKET_in_end_classstates1461 = new BitSet(new long[]{0x0000000000020000L});
    public static final BitSet FOLLOW_RSBRACKET_in_end_classstates1463 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_STATE_in_state1471 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_LSBRACKET_in_state1473 = new BitSet(new long[]{0x0000020000000000L});
    public static final BitSet FOLLOW_NAME_in_state1475 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_EQUAL_in_state1477 = new BitSet(new long[]{0x0000000008000000L});
    public static final BitSet FOLLOW_QUOTE_in_state1479 = new BitSet(new long[]{0x2000000000000000L});
    public static final BitSet FOLLOW_ID_in_state1483 = new BitSet(new long[]{0x0000000008000000L});
    public static final BitSet FOLLOW_QUOTE_in_state1485 = new BitSet(new long[]{0x0000000080020000L});
    public static final BitSet FOLLOW_PUNCTUATION_in_state1490 = new BitSet(new long[]{0x0000040000000000L});
    public static final BitSet FOLLOW_invariant_in_state1492 = new BitSet(new long[]{0x0000000080020000L});
    public static final BitSet FOLLOW_RSBRACKET_in_state1496 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_INV_in_invariant1591 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_EQUAL_in_invariant1593 = new BitSet(new long[]{0x0000000008000000L});
    public static final BitSet FOLLOW_QUOTE_in_invariant1595 = new BitSet(new long[]{0x200000000800FC00L});
    public static final BitSet FOLLOW_condition_in_invariant1598 = new BitSet(new long[]{0x0000000018000000L});
    public static final BitSet FOLLOW_AND_in_invariant1601 = new BitSet(new long[]{0x200000000000FC00L});
    public static final BitSet FOLLOW_condition_in_invariant1603 = new BitSet(new long[]{0x0000000018000000L});
    public static final BitSet FOLLOW_QUOTE_in_invariant1609 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_condition1620 = new BitSet(new long[]{0x0000080000000000L});
    public static final BitSet FOLLOW_OPERATOR_in_condition1624 = new BitSet(new long[]{0x2000000000000000L});
    public static final BitSet FOLLOW_ID_in_condition1628 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_accesspermission_in_condition1646 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_LSBRACKET_in_condition1648 = new BitSet(new long[]{0x2000000000400000L});
    public static final BitSet FOLLOW_set_in_condition1652 = new BitSet(new long[]{0x0000000000020000L});
    public static final BitSet FOLLOW_RSBRACKET_in_condition1658 = new BitSet(new long[]{0x0000000000200002L});
    public static final BitSet FOLLOW_IN_in_condition1661 = new BitSet(new long[]{0x2000000000000000L});
    public static final BitSet FOLLOW_ID_in_condition1665 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CASES_in_cases1676 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_LSBRACKET_in_cases1678 = new BitSet(new long[]{0x0000000200000000L});
    public static final BitSet FOLLOW_LCBRACKET_in_cases1680 = new BitSet(new long[]{0x00000000000401F0L});
    public static final BitSet FOLLOW_perm_in_cases1682 = new BitSet(new long[]{0x0000000480000000L});
    public static final BitSet FOLLOW_other_in_cases1685 = new BitSet(new long[]{0x00000000000401F0L});
    public static final BitSet FOLLOW_perm_in_cases1687 = new BitSet(new long[]{0x0000000480000000L});
    public static final BitSet FOLLOW_RCBRACKET_in_cases1691 = new BitSet(new long[]{0x0000000000020000L});
    public static final BitSet FOLLOW_RSBRACKET_in_cases1693 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PUNCTUATION_in_other1701 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PERM_in_perm1712 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_LSBRACKET_in_perm1714 = new BitSet(new long[]{0x0000000086020000L});
    public static final BitSet FOLLOW_requires_ensures_clause_in_perm1716 = new BitSet(new long[]{0x0000000000020000L});
    public static final BitSet FOLLOW_RSBRACKET_in_perm1718 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_attype_in_perm1727 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_at_ap_permission_in_attype1735 = new BitSet(new long[]{0x0000000000010002L});
    public static final BitSet FOLLOW_LSBRACKET_in_attype1738 = new BitSet(new long[]{0x0000000086000002L});
    public static final BitSet FOLLOW_REQUIRES_in_attype1741 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_EQUAL_in_attype1743 = new BitSet(new long[]{0x0000000008000000L});
    public static final BitSet FOLLOW_QUOTE_in_attype1745 = new BitSet(new long[]{0x2000000000000000L});
    public static final BitSet FOLLOW_typestate_in_attype1749 = new BitSet(new long[]{0x0000000008000000L});
    public static final BitSet FOLLOW_QUOTE_in_attype1751 = new BitSet(new long[]{0x0000000084000002L});
    public static final BitSet FOLLOW_PUNCTUATION_in_attype1756 = new BitSet(new long[]{0x0000000084000002L});
    public static final BitSet FOLLOW_ENSURES_in_attype1761 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_EQUAL_in_attype1763 = new BitSet(new long[]{0x0000000008000000L});
    public static final BitSet FOLLOW_QUOTE_in_attype1765 = new BitSet(new long[]{0x2000000000000000L});
    public static final BitSet FOLLOW_typestate_in_attype1769 = new BitSet(new long[]{0x0000000008000000L});
    public static final BitSet FOLLOW_QUOTE_in_attype1771 = new BitSet(new long[]{0x0000000080000002L});
    public static final BitSet FOLLOW_PUNCTUATION_in_attype1776 = new BitSet(new long[]{0x0000002020000000L});
    public static final BitSet FOLLOW_usevalue_in_attype1778 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_USE_in_usevalue1792 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_EQUAL_in_usevalue1794 = new BitSet(new long[]{0x0000000040000000L});
    public static final BitSet FOLLOW_USEFIELDS_in_usevalue1796 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_VALUE_in_usevalue1809 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_EQUAL_in_usevalue1811 = new BitSet(new long[]{0x0000000008000000L});
    public static final BitSet FOLLOW_QUOTE_in_usevalue1813 = new BitSet(new long[]{0x2000000000000000L});
    public static final BitSet FOLLOW_ID_in_usevalue1815 = new BitSet(new long[]{0x0000000008000000L});
    public static final BitSet FOLLOW_QUOTE_in_usevalue1817 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_requires_clause_in_requires_ensures_clause1831 = new BitSet(new long[]{0x0000000084000002L});
    public static final BitSet FOLLOW_PUNCTUATION_in_requires_ensures_clause1836 = new BitSet(new long[]{0x0000000004000002L});
    public static final BitSet FOLLOW_ensures_clause_in_requires_ensures_clause1841 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_REQUIRES_in_requires_clause1852 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_EQUAL_in_requires_clause1854 = new BitSet(new long[]{0x0000000008000000L});
    public static final BitSet FOLLOW_QUOTE_in_requires_clause1856 = new BitSet(new long[]{0x200000000100FC00L});
    public static final BitSet FOLLOW_re_accesspermission_typestates_in_requires_clause1858 = new BitSet(new long[]{0x0000000018000000L});
    public static final BitSet FOLLOW_AND_in_requires_clause1861 = new BitSet(new long[]{0x200000000100FC00L});
    public static final BitSet FOLLOW_re_accesspermission_typestates_in_requires_clause1863 = new BitSet(new long[]{0x0000000018000000L});
    public static final BitSet FOLLOW_QUOTE_in_requires_clause1867 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ENSURES_in_ensures_clause1875 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_EQUAL_in_ensures_clause1877 = new BitSet(new long[]{0x0000000008000000L});
    public static final BitSet FOLLOW_QUOTE_in_ensures_clause1879 = new BitSet(new long[]{0x200000000100FC00L});
    public static final BitSet FOLLOW_en_accesspermission_typestates_in_ensures_clause1881 = new BitSet(new long[]{0x0000000018000000L});
    public static final BitSet FOLLOW_AND_in_ensures_clause1884 = new BitSet(new long[]{0x200000000100FC00L});
    public static final BitSet FOLLOW_en_accesspermission_typestates_in_ensures_clause1886 = new BitSet(new long[]{0x0000000018000000L});
    public static final BitSet FOLLOW_QUOTE_in_ensures_clause1890 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_accesspermission_in_re_accesspermission_typestates1901 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_LSBRACKET_in_re_accesspermission_typestates1903 = new BitSet(new long[]{0x0000000000400000L});
    public static final BitSet FOLLOW_THIS_in_re_accesspermission_typestates1905 = new BitSet(new long[]{0x0000000000020000L});
    public static final BitSet FOLLOW_RSBRACKET_in_re_accesspermission_typestates1907 = new BitSet(new long[]{0x0000000000200002L});
    public static final BitSet FOLLOW_IN_in_re_accesspermission_typestates1910 = new BitSet(new long[]{0x2000000000000000L});
    public static final BitSet FOLLOW_typestate_in_re_accesspermission_typestates1914 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_accesspermission_in_re_accesspermission_typestates1954 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_LSBRACKET_in_re_accesspermission_typestates1956 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_PARAM_in_re_accesspermission_typestates1960 = new BitSet(new long[]{0x0000000000020000L});
    public static final BitSet FOLLOW_RSBRACKET_in_re_accesspermission_typestates1962 = new BitSet(new long[]{0x0000000000200002L});
    public static final BitSet FOLLOW_IN_in_re_accesspermission_typestates1965 = new BitSet(new long[]{0x2000000000000000L});
    public static final BitSet FOLLOW_typestate_in_re_accesspermission_typestates1969 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PARAM_in_re_accesspermission_typestates2011 = new BitSet(new long[]{0x0000080000000000L});
    public static final BitSet FOLLOW_OPERATOR_in_re_accesspermission_typestates2013 = new BitSet(new long[]{0x2000000000000000L});
    public static final BitSet FOLLOW_ID_in_re_accesspermission_typestates2015 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_accesspermission_in_en_accesspermission_typestates2146 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_LSBRACKET_in_en_accesspermission_typestates2148 = new BitSet(new long[]{0x0000000000400000L});
    public static final BitSet FOLLOW_THIS_in_en_accesspermission_typestates2150 = new BitSet(new long[]{0x0000000000020000L});
    public static final BitSet FOLLOW_RSBRACKET_in_en_accesspermission_typestates2152 = new BitSet(new long[]{0x0000000000200002L});
    public static final BitSet FOLLOW_IN_in_en_accesspermission_typestates2155 = new BitSet(new long[]{0x2000000000000000L});
    public static final BitSet FOLLOW_typestate_in_en_accesspermission_typestates2159 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_accesspermission_in_en_accesspermission_typestates2199 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_LSBRACKET_in_en_accesspermission_typestates2201 = new BitSet(new long[]{0x0000000000800000L});
    public static final BitSet FOLLOW_RESULT_in_en_accesspermission_typestates2203 = new BitSet(new long[]{0x0000000000020000L});
    public static final BitSet FOLLOW_RSBRACKET_in_en_accesspermission_typestates2205 = new BitSet(new long[]{0x0000000000200002L});
    public static final BitSet FOLLOW_IN_in_en_accesspermission_typestates2208 = new BitSet(new long[]{0x2000000000000000L});
    public static final BitSet FOLLOW_typestate_in_en_accesspermission_typestates2212 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_accesspermission_in_en_accesspermission_typestates2252 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_LSBRACKET_in_en_accesspermission_typestates2254 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_PARAM_in_en_accesspermission_typestates2258 = new BitSet(new long[]{0x0000000000020000L});
    public static final BitSet FOLLOW_RSBRACKET_in_en_accesspermission_typestates2260 = new BitSet(new long[]{0x0000000000200002L});
    public static final BitSet FOLLOW_IN_in_en_accesspermission_typestates2263 = new BitSet(new long[]{0x2000000000000000L});
    public static final BitSet FOLLOW_typestate_in_en_accesspermission_typestates2267 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PARAM_in_en_accesspermission_typestates2309 = new BitSet(new long[]{0x0000080000000000L});
    public static final BitSet FOLLOW_OPERATOR_in_en_accesspermission_typestates2311 = new BitSet(new long[]{0x2000000000000000L});
    public static final BitSet FOLLOW_ID_in_en_accesspermission_typestates2313 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_typestate2373 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_at_ap_permission0 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_accesspermission0 = new BitSet(new long[]{0x0000000000000002L});

}