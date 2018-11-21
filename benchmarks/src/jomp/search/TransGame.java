
/**************************************************************************
*                                                                         *
*             Java Grande Forum Benchmark Suite - Version 2.0             *
*                                                                         *
*                            produced by                                  *
*                                                                         *
*                  Java Grande Benchmarking Project                       *
*                                                                         *
*                                at                                       *
*                                                                         *
*                Edinburgh Parallel Computing Centre                      *
*                                                                         * 
*                email: epcc-javagrande@epcc.ed.ac.uk                     *
*                                                                         *
*                  Original version of this code by                       *
*                     John Tromp (tromp@cwi.nl)                           *
*                   (see copyright notice below)                          *
*                                                                         *
*      This version copyright (c) The University of Edinburgh, 1999.      *
*                         All rights reserved.                            *
*                                                                         *
**************************************************************************/


// Java Fhourstones 2.0 connect-4 solver
// (http://www.cwi.nl/~tromp/c4/fhour.html)
//
// implementation of the well-known game
// played on a vertical board of 7 columns by 6 rows,
// where 2 players take turns in dropping counters in a column.
// the first player to get four of his counters
// in a horizontal, vertical or diagonal row, wins the game.
// if neither player has won after 42 moves, then the game is drawn.
//
// This software is copyright (c) 1996 by
//      John Tromp
//      Lindenlaan 33
//      1701 GT Heerhugowaard
//      Netherlands
// E-mail: tromp@cwi.nl
//
// This notice must not be removed.
// This software must not be sold for profit.
// You may redistribute if your distributees have the
// same rights and restrictions.

package jomp.search; 

public class TransGame extends Game implements ConnectFourConstants {
	
  static final int NSAMELOCK = 0x20000;
  static final int STRIDERANGE = (TRANSIZE/PROBES-NSAMELOCK);
  static final int INTMODSTRIDERANGE = (int)((1L<<32) % STRIDERANGE);
  static final int ABSENT = -128;

  int ht[];			// hash locks
  byte he[];			// hash entries
  private int stride;
  private int htindex, lock;
  
  protected long posed, hits;		// counts transtore calls
  /*Method Name = TransGame
		  Vertex Name = ht, Post Permissions = unique, Pre-Permissions =none
		  Vertex Name = TRANSIZE, Post Permissions = pure, Pre-Permissions =pure
		  Vertex Name = he, Post Permissions = unique, Pre-Permissions =none*/
  public TransGame(){
    super();// superConstructorInvocation needs to be added as a method call
    ht = new int[TRANSIZE];
    he = new byte[TRANSIZE];    
  }
  /*Method Name = emptyTT
		  Vertex Name = TRANSIZE, Post Permissions = pure, Pre-Permissions =pure
		  Vertex Name = he, Post Permissions = share, Pre-Permissions =share
		  Vertex Name = posed, Post Permissions = share, Pre-Permissions =share
		  Vertex Name = hits, Post Permissions = share, Pre-Permissions =share*/
  void emptyTT()
  {
    int i, h, work;

    for (i=0; i<TRANSIZE; i++)
      if ((work = (h = he[i]) & 31) < 31)           // bytes are signed!!!
        he[i] = (byte)(h - (work<16 ? work : 4));     // work = work monus 4
    posed = hits = 0;
  }
 /* Method Name = hitRate
  * Vertex Name = posed, Post Permissions = pure, Pre-Permissions =pure
  * Vertex Name = hits, Post Permissions = pure, Pre-Permissions =pure*/
  double hitRate()
  {
    return posed != 0 ? (double)hits/(double)posed : 0.0;
  }
  
  /*Method Name = hash
		  Vertex Name = columns, Post Permissions = pure, Pre-Permissions =pure
		  Vertex Name = lock, Post Permissions = share, Pre-Permissions =share
		  Vertex Name = htindex, Post Permissions = share, Pre-Permissions =share
		  Vertex Name = TRANSIZE, Post Permissions = pure, Pre-Permissions =pure
		  Vertex Name = stride, Post Permissions = share, Pre-Permissions =share
		  Vertex Name = NSAMELOCK, Post Permissions = immutable, Pre-Permissions =immutable
		  Vertex Name = STRIDERANGE, Post Permissions = immutable, Pre-Permissions =immutable
		  Vertex Name = INTMODSTRIDERANGE, Post Permissions = immutable, Pre-Permissions =immutable*/
  void hash()
  {
    int t1,t2;
  
    long htemp;
  
    t1 = (columns[1] << 7 | columns[2]) << 7 | columns[3];
    t2 = (columns[7] << 7 | columns[6]) << 7 | columns[5];
    
    htemp = t1 > t2 ? (long)(t1 << 7 | columns[4]) << 21 | t2:
                      (long)(t2 << 7 | columns[4]) << 21 | t1;
    lock = (int)(htemp >> 17);
    htindex = (int)(htemp % TRANSIZE);
    stride = NSAMELOCK + lock % STRIDERANGE;
    if (lock < 0) {		// can't take unsigned mod in Java :(
      if ((stride += INTMODSTRIDERANGE) < NSAMELOCK)
        stride += STRIDERANGE;
    }
  }
  /*Method Name = transpose
		  Vertex Name = htindex, Post Permissions = share, Pre-Permissions =share
		  Vertex Name = PROBES, Post Permissions = immutable, Pre-Permissions =immutable
		  Vertex Name = ht, Post Permissions = pure, Pre-Permissions =pure
		  Vertex Name = lock, Post Permissions = share, Pre-Permissions =share
		  Vertex Name = he, Post Permissions = share, Pre-Permissions =share
		  Vertex Name = stride, Post Permissions = share, Pre-Permissions =share
		  Vertex Name = TRANSIZE, Post Permissions = pure, Pre-Permissions =pure
		  Vertex Name = ABSENT, Post Permissions = immutable, Pre-Permissions =immutable
		  Vertex Name = columns, Post Permissions = pure, Pre-Permissions =pure
		  Vertex Name = NSAMELOCK, Post Permissions = immutable, Pre-Permissions =immutable
		  Vertex Name = STRIDERANGE, Post Permissions = immutable, Pre-Permissions =immutable
		  Vertex Name = INTMODSTRIDERANGE, Post Permissions = immutable, Pre-Permissions =immutable*/
  int transpose()
  {
    hash();
    /*Method Name = hash
	  Vertex Name = columns, Post Permissions = pure, Pre-Permissions =pure
	  Vertex Name = lock, Post Permissions = share, Pre-Permissions =share
	  Vertex Name = htindex, Post Permissions = share, Pre-Permissions =share
	  Vertex Name = TRANSIZE, Post Permissions = pure, Pre-Permissions =pure
	  Vertex Name = stride, Post Permissions = share, Pre-Permissions =share
	  Vertex Name = NSAMELOCK, Post Permissions = immutable, Pre-Permissions =immutable
	  Vertex Name = STRIDERANGE, Post Permissions = immutable, Pre-Permissions =immutable
	  Vertex Name = INTMODSTRIDERANGE, Post Permissions = immutable, Pre-Permissions =immutable*/
    
    for (int x=htindex, i=0; i < PROBES; i++) {
      if (ht[x] == lock)
        return he[x];
      if ((x += stride) >= TRANSIZE)
        x -= TRANSIZE;
    }
    return ABSENT;
  }
  
  /*Method Name = result
		  Vertex Name = ABSENT, Post Permissions = immutable, Pre-Permissions =immutable
		  Vertex Name = htindex, Post Permissions = share, Pre-Permissions =share
		  Vertex Name = PROBES, Post Permissions = immutable, Pre-Permissions =immutable
		  Vertex Name = ht, Post Permissions = pure, Pre-Permissions =pure
		  Vertex Name = lock, Post Permissions = share, Pre-Permissions =share
		  Vertex Name = he, Post Permissions = share, Pre-Permissions =share
		  Vertex Name = stride, Post Permissions = share, Pre-Permissions =share
		  Vertex Name = TRANSIZE, Post Permissions = pure, Pre-Permissions =pure
		  Vertex Name = columns, Post Permissions = pure, Pre-Permissions =pure
		  Vertex Name = NSAMELOCK, Post Permissions = immutable, Pre-Permissions =immutable
		  Vertex Name = STRIDERANGE, Post Permissions = immutable, Pre-Permissions =immutable
		  Vertex Name = INTMODSTRIDERANGE, Post Permissions = immutable, Pre-Permissions =immutable*/
  String result()
  {
    int x;

    return (x = transpose()) == ABSENT ? "n/a" : result(x);
  }

  String result(int x)
  {
    return "" + "##-<=>+#".charAt(4+(x>>5)) + "(" + (x&31) + ")";
  }
  /*Method Name = transrestore
		  Vertex Name = posed, Post Permissions = pure, Pre-Permissions =pure
		  Vertex Name = htindex, Post Permissions = share, Pre-Permissions =share 
		  Vertex Name = PROBES, Post Permissions = immutable, Pre-Permissions =immutable
		  Vertex Name = ht, Post Permissions = share, Pre-Permissions =share
		  Vertex Name = lock, Post Permissions = share, Pre-Permissions =share
		  Vertex Name = hits, Post Permissions = pure, Pre-Permissions =pure
		  Vertex Name = he, Post Permissions = share, Pre-Permissions =share
		  Vertex Name = stride, Post Permissions = share, Pre-Permissions =share 
		  Vertex Name = TRANSIZE, Post Permissions = pure, Pre-Permissions =pure
		  Vertex Name = columns, Post Permissions = pure, Pre-Permissions =pure
		  Vertex Name = NSAMELOCK, Post Permissions = immutable, Pre-Permissions =immutable
		  Vertex Name = STRIDERANGE, Post Permissions = immutable, Pre-Permissions =immutable
		  Vertex Name = INTMODSTRIDERANGE, Post Permissions = immutable, Pre-Permissions =immutable*/
  
    void transrestore(int score, int work)
  {
    int i,x;
  
    if (work > 31)
      work = 31;
    posed++;
    hash();
    /*Method Name = hash
	  Vertex Name = columns, Post Permissions = pure, Pre-Permissions =pure
	  Vertex Name = lock, Post Permissions = share, Pre-Permissions =share
	  Vertex Name = htindex, Post Permissions = share, Pre-Permissions =share
	  Vertex Name = TRANSIZE, Post Permissions = pure, Pre-Permissions =pure
	  Vertex Name = stride, Post Permissions = share, Pre-Permissions =share
	  Vertex Name = NSAMELOCK, Post Permissions = immutable, Pre-Permissions =immutable
	  Vertex Name = STRIDERANGE, Post Permissions = immutable, Pre-Permissions =immutable
	  Vertex Name = INTMODSTRIDERANGE, Post Permissions = immutable, Pre-Permissions =immutable*/

    for (x=htindex, i=0; i < PROBES; i++) {
      if (ht[x] == lock) {
        hits++;
        he[x] = (byte)(score << 5 | work);
        return;
      }
      if ((x += stride) >= TRANSIZE)
        x -= TRANSIZE;
    }
    transput(score, work);
    /*Method Name = transput
	  Vertex Name = htindex, Post Permissions = pure, Pre-Permissions =pure
	  Vertex Name = PROBES, Post Permissions = immutable, Pre-Permissions =immutable
	  Vertex Name = he, Post Permissions = share, Pre-Permissions =share
	  Vertex Name = hits, Post Permissions = share, Pre-Permissions =share 
	  Vertex Name = ht, Post Permissions = share, Pre-Permissions =share
	  Vertex Name = lock, Post Permissions = pure, Pre-Permissions =pure
	  Vertex Name = stride, Post Permissions = pure, Pre-Permissions =pure
	  Vertex Name = TRANSIZE, Post Permissions = pure, Pre-Permissions =pure*/
  }
    /*Method Name = transtore
    		Vertex Name = posed, Post Permissions = share, Pre-Permissions =share 
    		Vertex Name = columns, Post Permissions = pure, Pre-Permissions =pure
    		Vertex Name = lock, Post Permissions = share, Pre-Permissions =share
    		Vertex Name = htindex, Post Permissions = share, Pre-Permissions =share
    		Vertex Name = TRANSIZE, Post Permissions = pure, Pre-Permissions =pure
    		Vertex Name = stride, Post Permissions = share, Pre-Permissions =share
    		Vertex Name = NSAMELOCK, Post Permissions = immutable, Pre-Permissions =immutable
    		Vertex Name = STRIDERANGE, Post Permissions = immutable, Pre-Permissions =immutable
    		Vertex Name = INTMODSTRIDERANGE, Post Permissions = immutable, Pre-Permissions =immutable
    		Vertex Name = PROBES, Post Permissions = immutable, Pre-Permissions =immutable
    		Vertex Name = he, Post Permissions = share, Pre-Permissions =share
    		Vertex Name = hits, Post Permissions = share, Pre-Permissions =share 
    		Vertex Name = ht, Post Permissions = pure, Pre-Permissions =pure*/ // its should be share
  void transtore(int score, int work)
  {
    if (work > 31)
      work = 31;
    posed++;
    hash();
    /*Method Name = hash
	  Vertex Name = columns, Post Permissions = pure, Pre-Permissions =pure
	  Vertex Name = lock, Post Permissions = share, Pre-Permissions =share
	  Vertex Name = htindex, Post Permissions = share, Pre-Permissions =share
	  Vertex Name = TRANSIZE, Post Permissions = pure, Pre-Permissions =pure
	  Vertex Name = stride, Post Permissions = share, Pre-Permissions =share
	  Vertex Name = NSAMELOCK, Post Permissions = immutable, Pre-Permissions =immutable
	  Vertex Name = STRIDERANGE, Post Permissions = immutable, Pre-Permissions =immutable
	  Vertex Name = INTMODSTRIDERANGE, Post Permissions = immutable, Pre-Permissions =immutable*/
    transput(score, work); 
    /*Method Name = transput
	  Vertex Name = htindex, Post Permissions = pure, Pre-Permissions =pure
	  Vertex Name = PROBES, Post Permissions = immutable, Pre-Permissions =immutable
	  Vertex Name = he, Post Permissions = share, Pre-Permissions =share
	  Vertex Name = hits, Post Permissions = share, Pre-Permissions =share 
	  Vertex Name = ht, Post Permissions = share, Pre-Permissions =share
	  Vertex Name = lock, Post Permissions = pure, Pre-Permissions =pure
	  Vertex Name = stride, Post Permissions = pure, Pre-Permissions =pure
	  Vertex Name = TRANSIZE, Post Permissions = pure, Pre-Permissions =pure*/
  }
  /*Method Name = transput
  Vertex Name = htindex, Post Permissions = pure, Pre-Permissions =pure
  Vertex Name = PROBES, Post Permissions = immutable, Pre-Permissions =immutable
  Vertex Name = he, Post Permissions = share, Pre-Permissions =share
  Vertex Name = hits, Post Permissions = share, Pre-Permissions =share 
  Vertex Name = ht, Post Permissions = share, Pre-Permissions =share
  Vertex Name = lock, Post Permissions = pure, Pre-Permissions =pure
  Vertex Name = stride, Post Permissions = pure, Pre-Permissions =pure
  Vertex Name = TRANSIZE, Post Permissions = pure, Pre-Permissions =pure*/
  void transput(int score, int work)
  {
    for (int x=htindex, i=0; i < PROBES; i++) {
      if (work > (he[x] & 31)) {
        hits++;
        ht[x] = lock;
        he[x] = (byte)(score << 5 | work);
        return;
      }
      if ((x += stride) >= TRANSIZE)
        x -= TRANSIZE;
    }
  }
  /*Method Name = htstat
		  Vertex Name = TRANSIZE, Post Permissions = pure, Pre-Permissions =pure
		  Vertex Name = he, Post Permissions = pure, Pre-Permissions =pure
		  Vertex Name = LOSE, Post Permissions = pure, Pre-Permissions =pure
		  Vertex Name = DRAWLOSE, Post Permissions = immutable, Pre-Permissions =immutable
		  Vertex Name = DRAW, Post Permissions = immutable, Pre-Permissions =immutable
		  Vertex Name = DRAWWIN, Post Permissions = immutable, Pre-Permissions =immutable
		  Vertex Name = WIN, Post Permissions = pure, Pre-Permissions =pure
		  Vertex Name = posed, Post Permissions = pure, Pre-Permissions =pure
		  Vertex Name = hits, Post Permissions = pure, Pre-Permissions =pure*/
  String htstat()      /* some statistics on hash table performance */
  {
    int total, i;
    StringBuffer buf = new StringBuffer();
    int works[];
    int typecnt[];                // bound type stats
  
    works = new int[32];
    typecnt = new int[8];
    for (i=0; i<32; i++)
      works[i] = 0;
    for (i=0; i<8; i++)
      typecnt[i] = 0;
    for (i=0; i<TRANSIZE; i++) {
      works[he[i] & 31]++;
      if ((he[i] & 31) != 0)
        typecnt[4 + (he[i] >> 5)]++;
    }
    for (total=i=0; i<8; i++)
      total += typecnt[i];
    if (total > 0)
      buf.append("store rate = " + hitRate() +
      "\n- "+typecnt[4+LOSE]/(double)total +
       " < "+typecnt[4+DRAWLOSE]/(double)total +
       " = "+typecnt[4+DRAW]/(double)total +
       " > " + typecnt[4+DRAWWIN]/(double)total +
       " + " + typecnt[4+WIN]/(double)total + "\n");
    for (i=0; i<32; i++) {
      buf.append(works[i]);
      buf.append((i&7)==7 ? '\n':'\t');
    }
    return buf.toString();
  }
}
