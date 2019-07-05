// $ANTLR 3.2 Sep 23, 2009 12:02:23 /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g 2019-06-25 21:17:13

package uma.SMC;
  


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

public class PluralLexer extends Lexer {
    public static final int PUNCTUATION=31;
    public static final int CASES=32;
    public static final int PARAM=24;
    public static final int EQUALOPERATOR=20;
    public static final int EVERYTHING=56;
    public static final int IMMUTABLE=12;
    public static final int PUBLIC_BEHAVIOR=9;
    public static final int JMLEND=52;
    public static final int AND=28;
    public static final int ID=61;
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
    public static final int INV=42;
    public static final int OPERATOR=43;
    public static final int INVARIANT=59;
    public static final int OLD=60;
    public static final int RESULT=23;
    public static final int SEMICOLON=44;
    public static final int INT=58;
    public static final int VALUE=37;
    public static final int JMLSTART=51;
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
    public static final int PERM=18;
    public static final int GREATERTHANEQUAL=48;

    // delegates
    // delegators

    public PluralLexer() {;} 
    public PluralLexer(CharStream input) {
        this(input, new RecognizerSharedState());
    }
    public PluralLexer(CharStream input, RecognizerSharedState state) {
        super(input,state);

    }
    public String getGrammarFileName() { return "/Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g"; }

    // $ANTLR start "AT_FULL"
    public final void mAT_FULL() throws RecognitionException {
        try {
            int _type = AT_FULL;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:17:8: ( '@Full' )
            // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:17:20: '@Full'
            {
            match("@Full"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "AT_FULL"

    // $ANTLR start "AT_PURE"
    public final void mAT_PURE() throws RecognitionException {
        try {
            int _type = AT_PURE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:18:8: ( '@Pure' )
            // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:18:20: '@Pure'
            {
            match("@Pure"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "AT_PURE"

    // $ANTLR start "AT_IMMUTABLE"
    public final void mAT_IMMUTABLE() throws RecognitionException {
        try {
            int _type = AT_IMMUTABLE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:19:13: ( '@Immutable' )
            // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:19:20: '@Immutable'
            {
            match("@Immutable"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "AT_IMMUTABLE"

    // $ANTLR start "AT_SHARE"
    public final void mAT_SHARE() throws RecognitionException {
        try {
            int _type = AT_SHARE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:20:9: ( '@Share' )
            // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:20:20: '@Share'
            {
            match("@Share"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "AT_SHARE"

    // $ANTLR start "AT_UNIQUE"
    public final void mAT_UNIQUE() throws RecognitionException {
        try {
            int _type = AT_UNIQUE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:21:10: ( '@Unique' )
            // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:21:20: '@Unique'
            {
            match("@Unique"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "AT_UNIQUE"

    // $ANTLR start "PUBLIC_BEHAVIOR"
    public final void mPUBLIC_BEHAVIOR() throws RecognitionException {
        try {
            int _type = PUBLIC_BEHAVIOR;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:22:16: ( 'public_behavior' )
            // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:22:20: 'public_behavior'
            {
            match("public_behavior"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "PUBLIC_BEHAVIOR"

    // $ANTLR start "FULL"
    public final void mFULL() throws RecognitionException {
        try {
            int _type = FULL;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:25:5: ( ( 'F' | 'f' ) 'ull' )
            // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:25:17: ( 'F' | 'f' ) 'ull'
            {
            if ( input.LA(1)=='F'||input.LA(1)=='f' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}

            match("ull"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "FULL"

    // $ANTLR start "PURE"
    public final void mPURE() throws RecognitionException {
        try {
            int _type = PURE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:26:5: ( ( 'P' | 'p' ) 'ure' )
            // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:26:17: ( 'P' | 'p' ) 'ure'
            {
            if ( input.LA(1)=='P'||input.LA(1)=='p' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}

            match("ure"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "PURE"

    // $ANTLR start "IMMUTABLE"
    public final void mIMMUTABLE() throws RecognitionException {
        try {
            int _type = IMMUTABLE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:27:10: ( ( 'I' | 'i' ) 'mmutable' )
            // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:27:17: ( 'I' | 'i' ) 'mmutable'
            {
            if ( input.LA(1)=='I'||input.LA(1)=='i' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}

            match("mmutable"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "IMMUTABLE"

    // $ANTLR start "SHARE"
    public final void mSHARE() throws RecognitionException {
        try {
            int _type = SHARE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:28:6: ( ( 'S' | 's' ) 'hare' )
            // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:28:17: ( 'S' | 's' ) 'hare'
            {
            if ( input.LA(1)=='S'||input.LA(1)=='s' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}

            match("hare"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "SHARE"

    // $ANTLR start "UNIQUE"
    public final void mUNIQUE() throws RecognitionException {
        try {
            int _type = UNIQUE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:29:7: ( ( 'U' | 'u' ) 'nique' )
            // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:29:17: ( 'U' | 'u' ) 'nique'
            {
            if ( input.LA(1)=='U'||input.LA(1)=='u' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}

            match("nique"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "UNIQUE"

    // $ANTLR start "NONE"
    public final void mNONE() throws RecognitionException {
        try {
            int _type = NONE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:30:5: ( ( 'N' | 'n' ) 'one' )
            // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:30:15: ( 'N' | 'n' ) 'one'
            {
            if ( input.LA(1)=='N'||input.LA(1)=='n' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}

            match("one"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "NONE"

    // $ANTLR start "LSBRACKET"
    public final void mLSBRACKET() throws RecognitionException {
        try {
            int _type = LSBRACKET;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:34:10: ( '(' )
            // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:34:17: '('
            {
            match('('); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "LSBRACKET"

    // $ANTLR start "RSBRACKET"
    public final void mRSBRACKET() throws RecognitionException {
        try {
            int _type = RSBRACKET;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:35:10: ( ')' )
            // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:35:17: ')'
            {
            match(')'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RSBRACKET"

    // $ANTLR start "PERM"
    public final void mPERM() throws RecognitionException {
        try {
            int _type = PERM;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:36:5: ( '@' ( 'P' | 'p' ) 'erm' )
            // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:36:17: '@' ( 'P' | 'p' ) 'erm'
            {
            match('@'); 
            if ( input.LA(1)=='P'||input.LA(1)=='p' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}

            match("erm"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "PERM"

    // $ANTLR start "EQUAL"
    public final void mEQUAL() throws RecognitionException {
        try {
            int _type = EQUAL;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:37:6: ( '=' )
            // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:37:18: '='
            {
            match('='); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "EQUAL"

    // $ANTLR start "EQUALOPERATOR"
    public final void mEQUALOPERATOR() throws RecognitionException {
        try {
            int _type = EQUALOPERATOR;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:38:14: ( '==' )
            // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:38:26: '=='
            {
            match("=="); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "EQUALOPERATOR"

    // $ANTLR start "IN"
    public final void mIN() throws RecognitionException {
        try {
            int _type = IN;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:39:4: ( 'in' )
            // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:39:18: 'in'
            {
            match("in"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "IN"

    // $ANTLR start "THIS"
    public final void mTHIS() throws RecognitionException {
        try {
            int _type = THIS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:40:5: ( 'this' | 'this!fr' )
            int alt1=2;
            int LA1_0 = input.LA(1);

            if ( (LA1_0=='t') ) {
                int LA1_1 = input.LA(2);

                if ( (LA1_1=='h') ) {
                    int LA1_2 = input.LA(3);

                    if ( (LA1_2=='i') ) {
                        int LA1_3 = input.LA(4);

                        if ( (LA1_3=='s') ) {
                            int LA1_4 = input.LA(5);

                            if ( (LA1_4=='!') ) {
                                alt1=2;
                            }
                            else {
                                alt1=1;}
                        }
                        else {
                            NoViableAltException nvae =
                                new NoViableAltException("", 1, 3, input);

                            throw nvae;
                        }
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 1, 2, input);

                        throw nvae;
                    }
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 1, 1, input);

                    throw nvae;
                }
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 1, 0, input);

                throw nvae;
            }
            switch (alt1) {
                case 1 :
                    // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:40:18: 'this'
                    {
                    match("this"); 


                    }
                    break;
                case 2 :
                    // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:40:25: 'this!fr'
                    {
                    match("this!fr"); 


                    }
                    break;

            }
            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "THIS"

    // $ANTLR start "RESULT"
    public final void mRESULT() throws RecognitionException {
        try {
            int _type = RESULT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:41:7: ( ( 'R' | 'r' ) 'esult' )
            // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:41:19: ( 'R' | 'r' ) 'esult'
            {
            if ( input.LA(1)=='R'||input.LA(1)=='r' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}

            match("esult"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RESULT"

    // $ANTLR start "PARAM"
    public final void mPARAM() throws RecognitionException {
        try {
            int _type = PARAM;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:42:6: ( '#' ( '0' .. '9' )* )
            // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:42:19: '#' ( '0' .. '9' )*
            {
            match('#'); 
            // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:42:22: ( '0' .. '9' )*
            loop2:
            do {
                int alt2=2;
                int LA2_0 = input.LA(1);

                if ( ((LA2_0>='0' && LA2_0<='9')) ) {
                    alt2=1;
                }


                switch (alt2) {
            	case 1 :
            	    // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:42:23: '0' .. '9'
            	    {
            	    matchRange('0','9'); 

            	    }
            	    break;

            	default :
            	    break loop2;
                }
            } while (true);


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "PARAM"

    // $ANTLR start "REQUIRES"
    public final void mREQUIRES() throws RecognitionException {
        try {
            int _type = REQUIRES;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:44:9: ( 'requires' )
            // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:44:18: 'requires'
            {
            match("requires"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "REQUIRES"

    // $ANTLR start "ENSURES"
    public final void mENSURES() throws RecognitionException {
        try {
            int _type = ENSURES;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:45:8: ( 'ensures' )
            // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:45:18: 'ensures'
            {
            match("ensures"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "ENSURES"

    // $ANTLR start "QUOTE"
    public final void mQUOTE() throws RecognitionException {
        try {
            int _type = QUOTE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:46:6: ( '\"' )
            // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:46:18: '\"'
            {
            match('\"'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "QUOTE"

    // $ANTLR start "AND"
    public final void mAND() throws RecognitionException {
        try {
            int _type = AND;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:47:4: ( '*' )
            // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:47:19: '*'
            {
            match('*'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "AND"

    // $ANTLR start "USE"
    public final void mUSE() throws RecognitionException {
        try {
            int _type = USE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:48:4: ( ( 'U' | 'u' ) 'se' )
            // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:48:19: ( 'U' | 'u' ) 'se'
            {
            if ( input.LA(1)=='U'||input.LA(1)=='u' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}

            match("se"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "USE"

    // $ANTLR start "USEFIELDS"
    public final void mUSEFIELDS() throws RecognitionException {
        try {
            int _type = USEFIELDS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:49:10: ( ( 'U' | 'u' ) 'se.FIELDS' )
            // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:49:19: ( 'U' | 'u' ) 'se.FIELDS'
            {
            if ( input.LA(1)=='U'||input.LA(1)=='u' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}

            match("se.FIELDS"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "USEFIELDS"

    // $ANTLR start "PUNCTUATION"
    public final void mPUNCTUATION() throws RecognitionException {
        try {
            int _type = PUNCTUATION;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:51:13: ( ',' )
            // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:51:17: ','
            {
            match(','); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "PUNCTUATION"

    // $ANTLR start "CASES"
    public final void mCASES() throws RecognitionException {
        try {
            int _type = CASES;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:52:6: ( '@' ( 'C' | 'c' ) 'ases' )
            // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:52:17: '@' ( 'C' | 'c' ) 'ases'
            {
            match('@'); 
            if ( input.LA(1)=='C'||input.LA(1)=='c' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}

            match("ases"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "CASES"

    // $ANTLR start "LCBRACKET"
    public final void mLCBRACKET() throws RecognitionException {
        try {
            int _type = LCBRACKET;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:53:10: ( '{' )
            // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:53:17: '{'
            {
            match('{'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "LCBRACKET"

    // $ANTLR start "RCBRACKET"
    public final void mRCBRACKET() throws RecognitionException {
        try {
            int _type = RCBRACKET;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:54:10: ( '}' )
            // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:54:17: '}'
            {
            match('}'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RCBRACKET"

    // $ANTLR start "CLASS_STATES"
    public final void mCLASS_STATES() throws RecognitionException {
        try {
            int _type = CLASS_STATES;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:55:13: ( '@' ( 'C' | 'c' ) 'lass' ( 'S' | 's' ) 'tates' )
            // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:55:17: '@' ( 'C' | 'c' ) 'lass' ( 'S' | 's' ) 'tates'
            {
            match('@'); 
            if ( input.LA(1)=='C'||input.LA(1)=='c' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}

            match("lass"); 

            if ( input.LA(1)=='S'||input.LA(1)=='s' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}

            match("tates"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "CLASS_STATES"

    // $ANTLR start "REFINE"
    public final void mREFINE() throws RecognitionException {
        try {
            int _type = REFINE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:56:7: ( '@' ( 'R' | 'r' ) 'efine' )
            // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:56:17: '@' ( 'R' | 'r' ) 'efine'
            {
            match('@'); 
            if ( input.LA(1)=='R'||input.LA(1)=='r' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}

            match("efine"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "REFINE"

    // $ANTLR start "VALUE"
    public final void mVALUE() throws RecognitionException {
        try {
            int _type = VALUE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:57:6: ( ( 'V' | 'v' ) 'alue' )
            // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:57:17: ( 'V' | 'v' ) 'alue'
            {
            if ( input.LA(1)=='V'||input.LA(1)=='v' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}

            match("alue"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "VALUE"

    // $ANTLR start "STATE"
    public final void mSTATE() throws RecognitionException {
        try {
            int _type = STATE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:58:6: ( '@' ( 'S' | 's' ) 'tate' )
            // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:58:17: '@' ( 'S' | 's' ) 'tate'
            {
            match('@'); 
            if ( input.LA(1)=='S'||input.LA(1)=='s' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}

            match("tate"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "STATE"

    // $ANTLR start "STATES"
    public final void mSTATES() throws RecognitionException {
        try {
            int _type = STATES;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:59:7: ( '@' ( 'S' | 's' ) 'tates' )
            // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:59:17: '@' ( 'S' | 's' ) 'tates'
            {
            match('@'); 
            if ( input.LA(1)=='S'||input.LA(1)=='s' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}

            match("tates"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "STATES"

    // $ANTLR start "DIM"
    public final void mDIM() throws RecognitionException {
        try {
            int _type = DIM;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:60:4: ( ( 'D' | 'd' ) 'im' )
            // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:60:17: ( 'D' | 'd' ) 'im'
            {
            if ( input.LA(1)=='D'||input.LA(1)=='d' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}

            match("im"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "DIM"

    // $ANTLR start "NAME"
    public final void mNAME() throws RecognitionException {
        try {
            int _type = NAME;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:61:5: ( ( 'N' | 'n' ) 'ame' )
            // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:61:17: ( 'N' | 'n' ) 'ame'
            {
            if ( input.LA(1)=='N'||input.LA(1)=='n' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}

            match("ame"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "NAME"

    // $ANTLR start "INV"
    public final void mINV() throws RecognitionException {
        try {
            int _type = INV;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:62:4: ( 'inv' )
            // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:62:17: 'inv'
            {
            match("inv"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "INV"

    // $ANTLR start "OPERATOR"
    public final void mOPERATOR() throws RecognitionException {
        try {
            int _type = OPERATOR;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:63:9: ( '==' | '!=' )
            int alt3=2;
            int LA3_0 = input.LA(1);

            if ( (LA3_0=='=') ) {
                alt3=1;
            }
            else if ( (LA3_0=='!') ) {
                alt3=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 3, 0, input);

                throw nvae;
            }
            switch (alt3) {
                case 1 :
                    // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:63:17: '=='
                    {
                    match("=="); 


                    }
                    break;
                case 2 :
                    // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:63:22: '!='
                    {
                    match("!="); 


                    }
                    break;

            }
            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "OPERATOR"

    // $ANTLR start "SEMICOLON"
    public final void mSEMICOLON() throws RecognitionException {
        try {
            int _type = SEMICOLON;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:64:10: ( ';' )
            // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:64:17: ';'
            {
            match(';'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "SEMICOLON"

    // $ANTLR start "LESS"
    public final void mLESS() throws RecognitionException {
        try {
            int _type = LESS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:65:5: ( '<' )
            // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:65:18: '<'
            {
            match('<'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "LESS"

    // $ANTLR start "LESSTHANEQUAL"
    public final void mLESSTHANEQUAL() throws RecognitionException {
        try {
            int _type = LESSTHANEQUAL;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:66:14: ( '<=' )
            // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:66:18: '<='
            {
            match("<="); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "LESSTHANEQUAL"

    // $ANTLR start "GREATER"
    public final void mGREATER() throws RecognitionException {
        try {
            int _type = GREATER;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:67:8: ( '>' )
            // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:67:18: '>'
            {
            match('>'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "GREATER"

    // $ANTLR start "GREATERTHANEQUAL"
    public final void mGREATERTHANEQUAL() throws RecognitionException {
        try {
            int _type = GREATERTHANEQUAL;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:68:17: ( '>=' )
            // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:68:19: '>='
            {
            match(">="); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "GREATERTHANEQUAL"

    // $ANTLR start "ANDD"
    public final void mANDD() throws RecognitionException {
        try {
            int _type = ANDD;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:69:5: ( '&&' )
            // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:69:21: '&&'
            {
            match("&&"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "ANDD"

    // $ANTLR start "OR"
    public final void mOR() throws RecognitionException {
        try {
            int _type = OR;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:70:3: ( '||' )
            // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:70:20: '||'
            {
            match("||"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "OR"

    // $ANTLR start "JMLSTART"
    public final void mJMLSTART() throws RecognitionException {
        try {
            int _type = JMLSTART;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:71:9: ( '/*@' )
            // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:71:20: '/*@'
            {
            match("/*@"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "JMLSTART"

    // $ANTLR start "JMLEND"
    public final void mJMLEND() throws RecognitionException {
        try {
            int _type = JMLEND;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:72:7: ( '*/' )
            // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:72:20: '*/'
            {
            match("*/"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "JMLEND"

    // $ANTLR start "PLUSMINUSOPERATOR"
    public final void mPLUSMINUSOPERATOR() throws RecognitionException {
        try {
            int _type = PLUSMINUSOPERATOR;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:73:18: ( ( '+' | '-' ) )
            // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:73:21: ( '+' | '-' )
            {
            if ( input.LA(1)=='+'||input.LA(1)=='-' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "PLUSMINUSOPERATOR"

    // $ANTLR start "ASSIGNABLE"
    public final void mASSIGNABLE() throws RecognitionException {
        try {
            int _type = ASSIGNABLE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:74:11: ( 'assignable' )
            // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:74:18: 'assignable'
            {
            match("assignable"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "ASSIGNABLE"

    // $ANTLR start "NOTHING"
    public final void mNOTHING() throws RecognitionException {
        try {
            int _type = NOTHING;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:75:8: ( '\\\\nothing' )
            // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:75:18: '\\\\nothing'
            {
            match("\\nothing"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "NOTHING"

    // $ANTLR start "EVERYTHING"
    public final void mEVERYTHING() throws RecognitionException {
        try {
            int _type = EVERYTHING;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:76:11: ( '\\\\everything' )
            // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:76:18: '\\\\everything'
            {
            match("\\everything"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "EVERYTHING"

    // $ANTLR start "GHOST"
    public final void mGHOST() throws RecognitionException {
        try {
            int _type = GHOST;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:77:6: ( 'ghost' )
            // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:77:17: 'ghost'
            {
            match("ghost"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "GHOST"

    // $ANTLR start "INT"
    public final void mINT() throws RecognitionException {
        try {
            int _type = INT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:78:4: ( 'int' )
            // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:78:17: 'int'
            {
            match("int"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "INT"

    // $ANTLR start "INVARIANT"
    public final void mINVARIANT() throws RecognitionException {
        try {
            int _type = INVARIANT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:79:10: ( 'invariant' )
            // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:79:17: 'invariant'
            {
            match("invariant"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "INVARIANT"

    // $ANTLR start "OLD"
    public final void mOLD() throws RecognitionException {
        try {
            int _type = OLD;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:80:4: ( '\\\\old' )
            // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:80:17: '\\\\old'
            {
            match("\\old"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "OLD"

    // $ANTLR start "ID"
    public final void mID() throws RecognitionException {
        try {
            int _type = ID;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:81:3: ( ( 'a' .. 'z' | 'A' .. 'Z' ) ( 'a' .. 'z' | 'A' .. 'Z' | '0' .. '9' | '_' | '.' )* )
            // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:81:17: ( 'a' .. 'z' | 'A' .. 'Z' ) ( 'a' .. 'z' | 'A' .. 'Z' | '0' .. '9' | '_' | '.' )*
            {
            if ( (input.LA(1)>='A' && input.LA(1)<='Z')||(input.LA(1)>='a' && input.LA(1)<='z') ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}

            // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:81:36: ( 'a' .. 'z' | 'A' .. 'Z' | '0' .. '9' | '_' | '.' )*
            loop4:
            do {
                int alt4=2;
                int LA4_0 = input.LA(1);

                if ( (LA4_0=='.'||(LA4_0>='0' && LA4_0<='9')||(LA4_0>='A' && LA4_0<='Z')||LA4_0=='_'||(LA4_0>='a' && LA4_0<='z')) ) {
                    alt4=1;
                }


                switch (alt4) {
            	case 1 :
            	    // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:
            	    {
            	    if ( input.LA(1)=='.'||(input.LA(1)>='0' && input.LA(1)<='9')||(input.LA(1)>='A' && input.LA(1)<='Z')||input.LA(1)=='_'||(input.LA(1)>='a' && input.LA(1)<='z') ) {
            	        input.consume();

            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;}


            	    }
            	    break;

            	default :
            	    break loop4;
                }
            } while (true);


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "ID"

    // $ANTLR start "NUMBERS"
    public final void mNUMBERS() throws RecognitionException {
        try {
            int _type = NUMBERS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:82:8: ( ( '0' .. '9' )* )
            // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:82:17: ( '0' .. '9' )*
            {
            // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:82:17: ( '0' .. '9' )*
            loop5:
            do {
                int alt5=2;
                int LA5_0 = input.LA(1);

                if ( ((LA5_0>='0' && LA5_0<='9')) ) {
                    alt5=1;
                }


                switch (alt5) {
            	case 1 :
            	    // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:82:18: '0' .. '9'
            	    {
            	    matchRange('0','9'); 

            	    }
            	    break;

            	default :
            	    break loop5;
                }
            } while (true);


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "NUMBERS"

    // $ANTLR start "WS"
    public final void mWS() throws RecognitionException {
        try {
            int _type = WS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:83:3: ( ( ' ' | '\\t' | '\\n' | '\\r' )+ )
            // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:83:18: ( ' ' | '\\t' | '\\n' | '\\r' )+
            {
            // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:83:18: ( ' ' | '\\t' | '\\n' | '\\r' )+
            int cnt6=0;
            loop6:
            do {
                int alt6=2;
                int LA6_0 = input.LA(1);

                if ( ((LA6_0>='\t' && LA6_0<='\n')||LA6_0=='\r'||LA6_0==' ') ) {
                    alt6=1;
                }


                switch (alt6) {
            	case 1 :
            	    // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:
            	    {
            	    if ( (input.LA(1)>='\t' && input.LA(1)<='\n')||input.LA(1)=='\r'||input.LA(1)==' ' ) {
            	        input.consume();

            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;}


            	    }
            	    break;

            	default :
            	    if ( cnt6 >= 1 ) break loop6;
                        EarlyExitException eee =
                            new EarlyExitException(6, input);
                        throw eee;
                }
                cnt6++;
            } while (true);

            _channel=HIDDEN;

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "WS"

    public void mTokens() throws RecognitionException {
        // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:1:8: ( AT_FULL | AT_PURE | AT_IMMUTABLE | AT_SHARE | AT_UNIQUE | PUBLIC_BEHAVIOR | FULL | PURE | IMMUTABLE | SHARE | UNIQUE | NONE | LSBRACKET | RSBRACKET | PERM | EQUAL | EQUALOPERATOR | IN | THIS | RESULT | PARAM | REQUIRES | ENSURES | QUOTE | AND | USE | USEFIELDS | PUNCTUATION | CASES | LCBRACKET | RCBRACKET | CLASS_STATES | REFINE | VALUE | STATE | STATES | DIM | NAME | INV | OPERATOR | SEMICOLON | LESS | LESSTHANEQUAL | GREATER | GREATERTHANEQUAL | ANDD | OR | JMLSTART | JMLEND | PLUSMINUSOPERATOR | ASSIGNABLE | NOTHING | EVERYTHING | GHOST | INT | INVARIANT | OLD | ID | NUMBERS | WS )
        int alt7=60;
        alt7 = dfa7.predict(input);
        switch (alt7) {
            case 1 :
                // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:1:10: AT_FULL
                {
                mAT_FULL(); 

                }
                break;
            case 2 :
                // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:1:18: AT_PURE
                {
                mAT_PURE(); 

                }
                break;
            case 3 :
                // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:1:26: AT_IMMUTABLE
                {
                mAT_IMMUTABLE(); 

                }
                break;
            case 4 :
                // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:1:39: AT_SHARE
                {
                mAT_SHARE(); 

                }
                break;
            case 5 :
                // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:1:48: AT_UNIQUE
                {
                mAT_UNIQUE(); 

                }
                break;
            case 6 :
                // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:1:58: PUBLIC_BEHAVIOR
                {
                mPUBLIC_BEHAVIOR(); 

                }
                break;
            case 7 :
                // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:1:74: FULL
                {
                mFULL(); 

                }
                break;
            case 8 :
                // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:1:79: PURE
                {
                mPURE(); 

                }
                break;
            case 9 :
                // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:1:84: IMMUTABLE
                {
                mIMMUTABLE(); 

                }
                break;
            case 10 :
                // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:1:94: SHARE
                {
                mSHARE(); 

                }
                break;
            case 11 :
                // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:1:100: UNIQUE
                {
                mUNIQUE(); 

                }
                break;
            case 12 :
                // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:1:107: NONE
                {
                mNONE(); 

                }
                break;
            case 13 :
                // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:1:112: LSBRACKET
                {
                mLSBRACKET(); 

                }
                break;
            case 14 :
                // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:1:122: RSBRACKET
                {
                mRSBRACKET(); 

                }
                break;
            case 15 :
                // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:1:132: PERM
                {
                mPERM(); 

                }
                break;
            case 16 :
                // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:1:137: EQUAL
                {
                mEQUAL(); 

                }
                break;
            case 17 :
                // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:1:143: EQUALOPERATOR
                {
                mEQUALOPERATOR(); 

                }
                break;
            case 18 :
                // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:1:157: IN
                {
                mIN(); 

                }
                break;
            case 19 :
                // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:1:160: THIS
                {
                mTHIS(); 

                }
                break;
            case 20 :
                // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:1:165: RESULT
                {
                mRESULT(); 

                }
                break;
            case 21 :
                // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:1:172: PARAM
                {
                mPARAM(); 

                }
                break;
            case 22 :
                // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:1:178: REQUIRES
                {
                mREQUIRES(); 

                }
                break;
            case 23 :
                // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:1:187: ENSURES
                {
                mENSURES(); 

                }
                break;
            case 24 :
                // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:1:195: QUOTE
                {
                mQUOTE(); 

                }
                break;
            case 25 :
                // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:1:201: AND
                {
                mAND(); 

                }
                break;
            case 26 :
                // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:1:205: USE
                {
                mUSE(); 

                }
                break;
            case 27 :
                // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:1:209: USEFIELDS
                {
                mUSEFIELDS(); 

                }
                break;
            case 28 :
                // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:1:219: PUNCTUATION
                {
                mPUNCTUATION(); 

                }
                break;
            case 29 :
                // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:1:231: CASES
                {
                mCASES(); 

                }
                break;
            case 30 :
                // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:1:237: LCBRACKET
                {
                mLCBRACKET(); 

                }
                break;
            case 31 :
                // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:1:247: RCBRACKET
                {
                mRCBRACKET(); 

                }
                break;
            case 32 :
                // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:1:257: CLASS_STATES
                {
                mCLASS_STATES(); 

                }
                break;
            case 33 :
                // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:1:270: REFINE
                {
                mREFINE(); 

                }
                break;
            case 34 :
                // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:1:277: VALUE
                {
                mVALUE(); 

                }
                break;
            case 35 :
                // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:1:283: STATE
                {
                mSTATE(); 

                }
                break;
            case 36 :
                // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:1:289: STATES
                {
                mSTATES(); 

                }
                break;
            case 37 :
                // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:1:296: DIM
                {
                mDIM(); 

                }
                break;
            case 38 :
                // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:1:300: NAME
                {
                mNAME(); 

                }
                break;
            case 39 :
                // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:1:305: INV
                {
                mINV(); 

                }
                break;
            case 40 :
                // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:1:309: OPERATOR
                {
                mOPERATOR(); 

                }
                break;
            case 41 :
                // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:1:318: SEMICOLON
                {
                mSEMICOLON(); 

                }
                break;
            case 42 :
                // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:1:328: LESS
                {
                mLESS(); 

                }
                break;
            case 43 :
                // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:1:333: LESSTHANEQUAL
                {
                mLESSTHANEQUAL(); 

                }
                break;
            case 44 :
                // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:1:347: GREATER
                {
                mGREATER(); 

                }
                break;
            case 45 :
                // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:1:355: GREATERTHANEQUAL
                {
                mGREATERTHANEQUAL(); 

                }
                break;
            case 46 :
                // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:1:372: ANDD
                {
                mANDD(); 

                }
                break;
            case 47 :
                // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:1:377: OR
                {
                mOR(); 

                }
                break;
            case 48 :
                // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:1:380: JMLSTART
                {
                mJMLSTART(); 

                }
                break;
            case 49 :
                // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:1:389: JMLEND
                {
                mJMLEND(); 

                }
                break;
            case 50 :
                // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:1:396: PLUSMINUSOPERATOR
                {
                mPLUSMINUSOPERATOR(); 

                }
                break;
            case 51 :
                // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:1:414: ASSIGNABLE
                {
                mASSIGNABLE(); 

                }
                break;
            case 52 :
                // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:1:425: NOTHING
                {
                mNOTHING(); 

                }
                break;
            case 53 :
                // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:1:433: EVERYTHING
                {
                mEVERYTHING(); 

                }
                break;
            case 54 :
                // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:1:444: GHOST
                {
                mGHOST(); 

                }
                break;
            case 55 :
                // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:1:450: INT
                {
                mINT(); 

                }
                break;
            case 56 :
                // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:1:454: INVARIANT
                {
                mINVARIANT(); 

                }
                break;
            case 57 :
                // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:1:464: OLD
                {
                mOLD(); 

                }
                break;
            case 58 :
                // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:1:468: ID
                {
                mID(); 

                }
                break;
            case 59 :
                // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:1:471: NUMBERS
                {
                mNUMBERS(); 

                }
                break;
            case 60 :
                // /Users/ayeshasadiq/Documents/sip4j/permission-specs/pulse/uma/Plural/grammar/Plural.g:1:479: WS
                {
                mWS(); 

                }
                break;

        }

    }


    protected DFA7 dfa7 = new DFA7(this);
    static final String DFA7_eotS =
        "\1\45\1\uffff\7\44\2\uffff\1\73\3\44\1\uffff\2\44\1\uffff\1\101"+
        "\3\uffff\2\44\2\uffff\1\105\1\107\4\uffff\1\44\1\uffff\1\44\14\uffff"+
        "\4\44\1\130\5\44\2\uffff\4\44\2\uffff\2\44\4\uffff\1\44\3\uffff"+
        "\1\44\5\uffff\4\44\1\155\1\156\1\uffff\2\44\1\162\2\44\1\uffff\5"+
        "\44\1\172\2\44\1\uffff\1\44\1\177\1\u0080\2\44\2\uffff\3\44\1\uffff"+
        "\1\u0086\1\u0087\1\u0088\4\44\1\uffff\2\44\1\uffff\1\44\2\uffff"+
        "\2\44\1\u0093\2\44\3\uffff\3\44\1\u0099\1\44\1\u009b\1\u009d\3\44"+
        "\1\uffff\1\u00a1\1\44\1\u00a3\2\44\1\uffff\1\44\3\uffff\3\44\1\uffff"+
        "\1\44\1\uffff\1\44\1\u00ac\5\44\1\u00b2\1\uffff\2\44\1\u00b5\1\u00b6"+
        "\1\44\1\uffff\2\44\2\uffff\1\u00ba\1\u00bb\1\44\2\uffff\3\44\1\u00c0"+
        "\1\uffff";
    static final String DFA7_eofS =
        "\u00c1\uffff";
    static final String DFA7_minS =
        "\1\11\1\103\3\165\1\155\1\150\1\156\1\141\2\uffff\1\75\1\155\1\150"+
        "\1\145\1\uffff\1\145\1\156\1\uffff\1\57\3\uffff\1\141\1\151\2\uffff"+
        "\2\75\4\uffff\1\163\1\145\1\150\4\uffff\1\145\1\uffff\1\150\2\uffff"+
        "\1\141\1\uffff\1\164\1\142\1\154\1\162\1\155\1\56\1\141\1\151\1"+
        "\145\1\156\1\155\2\uffff\1\151\1\161\2\163\2\uffff\1\154\1\155\4"+
        "\uffff\1\163\3\uffff\1\157\2\uffff\1\141\2\uffff\1\154\1\145\1\154"+
        "\1\165\2\56\1\uffff\1\162\1\161\1\56\2\145\1\uffff\1\163\4\165\1"+
        "\56\1\151\1\163\1\164\1\151\2\56\1\164\1\162\2\uffff\1\145\1\165"+
        "\1\106\1\uffff\3\56\1\154\1\151\1\162\1\145\1\uffff\1\147\1\164"+
        "\1\145\1\143\2\uffff\1\141\1\151\1\56\1\145\1\111\3\uffff\1\164"+
        "\1\162\1\145\1\56\1\156\1\56\1\163\1\137\1\142\1\141\1\uffff\1\56"+
        "\1\105\1\56\1\145\1\163\1\uffff\1\141\3\uffff\1\142\1\154\1\156"+
        "\1\uffff\1\114\1\uffff\1\163\1\56\1\142\2\145\1\164\1\104\1\56\1"+
        "\uffff\1\154\1\150\2\56\1\123\1\uffff\1\145\1\141\2\uffff\2\56\1"+
        "\166\2\uffff\1\151\1\157\1\162\1\56\1\uffff";
    static final String DFA7_maxS =
        "\1\175\1\163\3\165\1\156\1\150\1\163\1\157\2\uffff\1\75\1\155\1"+
        "\150\1\145\1\uffff\1\145\1\156\1\uffff\1\57\3\uffff\1\141\1\151"+
        "\2\uffff\2\75\4\uffff\1\163\1\157\1\150\4\uffff\1\165\1\uffff\1"+
        "\164\2\uffff\1\154\1\uffff\1\164\1\162\1\154\1\162\1\155\1\172\1"+
        "\141\1\151\1\145\1\156\1\155\2\uffff\1\151\3\163\2\uffff\1\154\1"+
        "\155\4\uffff\1\163\3\uffff\1\157\2\uffff\1\141\2\uffff\1\154\1\145"+
        "\1\154\1\165\2\172\1\uffff\1\162\1\161\1\172\2\145\1\uffff\1\163"+
        "\4\165\1\172\1\151\1\163\1\164\1\151\2\172\1\164\1\162\2\uffff\1"+
        "\145\1\165\1\106\1\uffff\3\172\1\154\1\151\1\162\1\145\1\uffff\1"+
        "\147\1\164\1\145\1\143\2\uffff\1\141\1\151\1\172\1\145\1\111\3\uffff"+
        "\1\164\1\162\1\145\1\172\1\156\1\172\1\163\1\137\1\142\1\141\1\uffff"+
        "\1\172\1\105\1\172\1\145\1\163\1\uffff\1\141\3\uffff\1\142\1\154"+
        "\1\156\1\uffff\1\114\1\uffff\1\163\1\172\1\142\2\145\1\164\1\104"+
        "\1\172\1\uffff\1\154\1\150\2\172\1\123\1\uffff\1\145\1\141\2\uffff"+
        "\2\172\1\166\2\uffff\1\151\1\157\1\162\1\172\1\uffff";
    static final String DFA7_acceptS =
        "\11\uffff\1\15\1\16\4\uffff\1\25\2\uffff\1\30\1\uffff\1\34\1\36"+
        "\1\37\2\uffff\1\50\1\51\2\uffff\1\56\1\57\1\60\1\62\3\uffff\1\72"+
        "\1\73\1\74\1\1\1\uffff\1\3\1\uffff\1\5\1\17\1\uffff\1\41\13\uffff"+
        "\1\21\1\20\4\uffff\1\61\1\31\2\uffff\1\53\1\52\1\55\1\54\1\uffff"+
        "\1\64\1\65\1\71\1\uffff\1\2\1\4\1\uffff\1\35\1\40\6\uffff\1\22\5"+
        "\uffff\1\21\16\uffff\1\47\1\67\3\uffff\1\32\7\uffff\1\45\4\uffff"+
        "\1\10\1\7\5\uffff\1\14\1\46\1\23\12\uffff\1\12\5\uffff\1\42\1\uffff"+
        "\1\66\1\44\1\43\3\uffff\1\13\1\uffff\1\24\10\uffff\1\27\5\uffff"+
        "\1\26\2\uffff\1\11\1\70\3\uffff\1\33\1\63\4\uffff\1\6";
    static final String DFA7_specialS =
        "\u00c1\uffff}>";
    static final String[] DFA7_transitionS = {
            "\2\46\2\uffff\1\46\22\uffff\1\46\1\31\1\22\1\17\2\uffff\1\35"+
            "\1\uffff\1\11\1\12\1\23\1\40\1\24\1\40\1\uffff\1\37\13\uffff"+
            "\1\32\1\33\1\13\1\34\1\uffff\1\1\3\44\1\30\1\44\1\3\2\44\1\14"+
            "\4\44\1\10\1\44\1\4\1\44\1\20\1\6\1\44\1\7\1\27\4\44\1\uffff"+
            "\1\42\4\uffff\1\41\2\44\1\30\1\21\1\3\1\43\1\44\1\5\4\44\1\10"+
            "\1\44\1\2\1\44\1\16\1\6\1\15\1\7\1\27\4\44\1\25\1\36\1\26",
            "\1\55\2\uffff\1\47\2\uffff\1\51\6\uffff\1\50\1\uffff\1\56\1"+
            "\52\1\uffff\1\53\15\uffff\1\55\14\uffff\1\54\1\uffff\1\56\1"+
            "\57",
            "\1\60",
            "\1\61",
            "\1\62",
            "\1\63\1\64",
            "\1\65",
            "\1\66\4\uffff\1\67",
            "\1\71\15\uffff\1\70",
            "",
            "",
            "\1\72",
            "\1\63",
            "\1\74",
            "\1\75",
            "",
            "\1\76",
            "\1\77",
            "",
            "\1\100",
            "",
            "",
            "",
            "\1\102",
            "\1\103",
            "",
            "",
            "\1\104",
            "\1\106",
            "",
            "",
            "",
            "",
            "\1\110",
            "\1\112\10\uffff\1\111\1\113",
            "\1\114",
            "",
            "",
            "",
            "",
            "\1\54\17\uffff\1\115",
            "",
            "\1\116\13\uffff\1\117",
            "",
            "",
            "\1\120\12\uffff\1\121",
            "",
            "\1\117",
            "\1\122\17\uffff\1\123",
            "\1\124",
            "\1\123",
            "\1\125",
            "\1\44\1\uffff\12\44\7\uffff\32\44\4\uffff\1\44\1\uffff\23\44"+
            "\1\127\1\44\1\126\4\44",
            "\1\131",
            "\1\132",
            "\1\133",
            "\1\134",
            "\1\135",
            "",
            "",
            "\1\137",
            "\1\141\1\uffff\1\140",
            "\1\140",
            "\1\142",
            "",
            "",
            "\1\143",
            "\1\144",
            "",
            "",
            "",
            "",
            "\1\145",
            "",
            "",
            "",
            "\1\146",
            "",
            "",
            "\1\147",
            "",
            "",
            "\1\150",
            "\1\151",
            "\1\152",
            "\1\153",
            "\1\44\1\uffff\12\44\7\uffff\32\44\4\uffff\1\44\1\uffff\1\154"+
            "\31\44",
            "\1\44\1\uffff\12\44\7\uffff\32\44\4\uffff\1\44\1\uffff\32\44",
            "",
            "\1\157",
            "\1\160",
            "\1\161\1\uffff\12\44\7\uffff\32\44\4\uffff\1\44\1\uffff\32"+
            "\44",
            "\1\163",
            "\1\164",
            "",
            "\1\165",
            "\1\166",
            "\1\167",
            "\1\170",
            "\1\171",
            "\1\44\1\uffff\12\44\7\uffff\32\44\4\uffff\1\44\1\uffff\32\44",
            "\1\173",
            "\1\174",
            "\1\175",
            "\1\176",
            "\1\44\1\uffff\12\44\7\uffff\32\44\4\uffff\1\44\1\uffff\32\44",
            "\1\44\1\uffff\12\44\7\uffff\32\44\4\uffff\1\44\1\uffff\32\44",
            "\1\u0081",
            "\1\u0082",
            "",
            "",
            "\1\u0083",
            "\1\u0084",
            "\1\u0085",
            "",
            "\1\44\1\uffff\12\44\7\uffff\32\44\4\uffff\1\44\1\uffff\32\44",
            "\1\44\1\uffff\12\44\7\uffff\32\44\4\uffff\1\44\1\uffff\32\44",
            "\1\44\1\uffff\12\44\7\uffff\32\44\4\uffff\1\44\1\uffff\32\44",
            "\1\u0089",
            "\1\u008a",
            "\1\u008b",
            "\1\u008c",
            "",
            "\1\u008d",
            "\1\u008e",
            "\1\u008f",
            "\1\u0090",
            "",
            "",
            "\1\u0091",
            "\1\u0092",
            "\1\44\1\uffff\12\44\7\uffff\32\44\4\uffff\1\44\1\uffff\32\44",
            "\1\u0094",
            "\1\u0095",
            "",
            "",
            "",
            "\1\u0096",
            "\1\u0097",
            "\1\u0098",
            "\1\44\1\uffff\12\44\7\uffff\32\44\4\uffff\1\44\1\uffff\32\44",
            "\1\u009a",
            "\1\44\1\uffff\12\44\7\uffff\32\44\4\uffff\1\44\1\uffff\32\44",
            "\1\u009c",
            "\1\u009e",
            "\1\u009f",
            "\1\u00a0",
            "",
            "\1\44\1\uffff\12\44\7\uffff\32\44\4\uffff\1\44\1\uffff\32\44",
            "\1\u00a2",
            "\1\44\1\uffff\12\44\7\uffff\32\44\4\uffff\1\44\1\uffff\32\44",
            "\1\u00a4",
            "\1\u00a5",
            "",
            "\1\u00a6",
            "",
            "",
            "",
            "\1\u00a7",
            "\1\u00a8",
            "\1\u00a9",
            "",
            "\1\u00aa",
            "",
            "\1\u00ab",
            "\1\44\1\uffff\12\44\7\uffff\32\44\4\uffff\1\44\1\uffff\32\44",
            "\1\u00ad",
            "\1\u00ae",
            "\1\u00af",
            "\1\u00b0",
            "\1\u00b1",
            "\1\44\1\uffff\12\44\7\uffff\32\44\4\uffff\1\44\1\uffff\32\44",
            "",
            "\1\u00b3",
            "\1\u00b4",
            "\1\44\1\uffff\12\44\7\uffff\32\44\4\uffff\1\44\1\uffff\32\44",
            "\1\44\1\uffff\12\44\7\uffff\32\44\4\uffff\1\44\1\uffff\32\44",
            "\1\u00b7",
            "",
            "\1\u00b8",
            "\1\u00b9",
            "",
            "",
            "\1\44\1\uffff\12\44\7\uffff\32\44\4\uffff\1\44\1\uffff\32\44",
            "\1\44\1\uffff\12\44\7\uffff\32\44\4\uffff\1\44\1\uffff\32\44",
            "\1\u00bc",
            "",
            "",
            "\1\u00bd",
            "\1\u00be",
            "\1\u00bf",
            "\1\44\1\uffff\12\44\7\uffff\32\44\4\uffff\1\44\1\uffff\32\44",
            ""
    };

    static final short[] DFA7_eot = DFA.unpackEncodedString(DFA7_eotS);
    static final short[] DFA7_eof = DFA.unpackEncodedString(DFA7_eofS);
    static final char[] DFA7_min = DFA.unpackEncodedStringToUnsignedChars(DFA7_minS);
    static final char[] DFA7_max = DFA.unpackEncodedStringToUnsignedChars(DFA7_maxS);
    static final short[] DFA7_accept = DFA.unpackEncodedString(DFA7_acceptS);
    static final short[] DFA7_special = DFA.unpackEncodedString(DFA7_specialS);
    static final short[][] DFA7_transition;

    static {
        int numStates = DFA7_transitionS.length;
        DFA7_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA7_transition[i] = DFA.unpackEncodedString(DFA7_transitionS[i]);
        }
    }

    class DFA7 extends DFA {

        public DFA7(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 7;
            this.eot = DFA7_eot;
            this.eof = DFA7_eof;
            this.min = DFA7_min;
            this.max = DFA7_max;
            this.accept = DFA7_accept;
            this.special = DFA7_special;
            this.transition = DFA7_transition;
        }
        public String getDescription() {
            return "1:1: Tokens : ( AT_FULL | AT_PURE | AT_IMMUTABLE | AT_SHARE | AT_UNIQUE | PUBLIC_BEHAVIOR | FULL | PURE | IMMUTABLE | SHARE | UNIQUE | NONE | LSBRACKET | RSBRACKET | PERM | EQUAL | EQUALOPERATOR | IN | THIS | RESULT | PARAM | REQUIRES | ENSURES | QUOTE | AND | USE | USEFIELDS | PUNCTUATION | CASES | LCBRACKET | RCBRACKET | CLASS_STATES | REFINE | VALUE | STATE | STATES | DIM | NAME | INV | OPERATOR | SEMICOLON | LESS | LESSTHANEQUAL | GREATER | GREATERTHANEQUAL | ANDD | OR | JMLSTART | JMLEND | PLUSMINUSOPERATOR | ASSIGNABLE | NOTHING | EVERYTHING | GHOST | INT | INVARIANT | OLD | ID | NUMBERS | WS );";
        }
    }
 

}