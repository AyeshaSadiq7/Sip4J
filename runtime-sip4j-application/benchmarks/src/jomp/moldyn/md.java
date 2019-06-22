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
*                         Dieter Heermann                                 * 
*                       converted to Java by                              *
*                Lorna Smith  (l.smith@epcc.ed.ac.uk)                     *
*                   (see copyright notice below)                          *
*                                                                         *
*      This version copyright (c) The University of Edinburgh, 1999.      *
*                         All rights reserved.                            *
*                                                                         *
**************************************************************************/



package jomp.moldyn;

import java.text.NumberFormat;

import jomp.moldyn.md;



public class md { // composes random class

	  public static final int ITERS = 100;
	  public static final double LENGTH = 50e-10;
	  public static final double m = 4.0026;
	  public static final double mu = 1.66056e-27;
	  public static final double kb = 1.38066e-23;
	  public static final double TSIM = 50;
	  public static final double deltat = 5e-16;
	  public static particle one[] = null;
	  public static double epot = 0.0;
	  public static double vir = 0.0;
	  public static double count = 0.0;
	  int size;
	  int datasizes[] = {8,13};

	  public static int interactions = 0;

	  int i,j,k,lg,mdsize,move,mm;

	  double l,rcoff,rcoffs,side,sideh,hsq,hsq2,vel; 
	  double a,r,sum,tscale,sc,ekin,ek,ts,sp;    
	  double den = 0.83134;
	  double tref = 0.722;
	  double h = 0.064;
	  double vaver,vaverh,rand;
	  double etot,temp,pres,rp;
	  double u1,u2,v1,v2,s;

	  int ijk,npartm,PARTSIZE,iseed,tint;
	  int irep = 10;
	  int istop = 19;
	  int iprint = 10;
	  int movemx = 50;

	  random randnum;
	  NumberFormat nbf;
	  NumberFormat nbf2;
	  NumberFormat nbf3;
	  
	  public void initialise() {

	    nbf = NumberFormat.getInstance(); // nbf = rw, null
	    nbf.setMaximumFractionDigits(4);// nbf= rw, null
	    nbf.setMinimumFractionDigits(4);// nbf= rw, null
	    nbf.setGroupingUsed(false); // nbf = rw,null

	    nbf2 = NumberFormat.getInstance();// nbf = rw, null
	    nbf2.setMaximumFractionDigits(1);// nbf = rw, null
	    nbf2.setMinimumFractionDigits(1);// nbf = rw, null

	    nbf2.setMaximumFractionDigits(4);// nbf = rw, null
	    nbf2.setMinimumFractionDigits(1);// nbf = rw, null

	    nbf3 = NumberFormat.getInstance();// nbf = rw, null
	    nbf3.setMaximumFractionDigits(6);// nbf = rw, null
	    nbf3.setMinimumFractionDigits(6);// nbf = rw, null
	 
	/* Parameter determination */

	    mm = datasizes[size];// mm = rw,null datasizes = r,null, size = r,rw
	    PARTSIZE = mm*mm*mm*4;// partsize = rw,null
	    mdsize = PARTSIZE;// mdsize = rw,r, PARTSIZE= r,null
	    one = new particle [mdsize];// one = rw, null,
	    l = LENGTH; // l = rw, null, Length = r, null
	   
	    side = Math.pow((mdsize/den),0.3333333);// side = rw, r, den = r, r
	    rcoff = mm/4.0;// rcoff= rw, r

	    a = side/mm;// a= rw, null;
	    sideh = side*0.5;// rw ,null
	    hsq = h*h;// hsq = rw,r, h = r, r
	    hsq2 = hsq*0.5;//hsq2 = rw,
	    npartm = mdsize - 1;//npartm = rw,null,  
	    rcoffs = rcoff * rcoff;// rcoffs = rw,null, rcoff= r , r
	    tscale = 16.0 / (1.0 * mdsize - 1.0);//tscale = rw, r
	    vaver = 1.13 * Math.sqrt(tref / 24.0);// vaver = rw, null, tref= r,r, 
	    vaverh = vaver * h; // rw, r

	/* Particle Generation */

	    ijk = 0;// ijk = rw, null
	    for (lg=0; lg<=1; lg++) {//lg = rw,null
	     for (i=0; i<mm; i++) {// i = rw, null
	      for (j=0; j<mm; j++) {// j = rw, null
	       for (k=0; k<mm; k++) {// k = rw,null
	       one[ijk] = new particle((i*a+lg*a*0.5),(j*a+lg*a*0.5),(k*a),0.0,0.0,0.0,0.0,0.0,0.0);
	     // one = rw, null, k = r,null
	         ijk = ijk + 1;
	       }
	      }
	     }
	    }
	    for (lg=1; lg<=2; lg++) {
	     for (i=0; i<mm; i++) {
	      for (j=0; j<mm; j++) {
	       for (k=0; k<mm; k++) {
	       one[ijk] = new particle((i*a+(2-lg)*a*0.5),(j*a+(lg-1)*a*0.5), (k*a+a*0.5),0.0,0.0,0.0,0.0,0.0,0.0);	
	        ijk = ijk + 1;
	       }
	      }
	     }
	    }

	/* Initialise velocities */

	    iseed = 0;// iseed rw, null
	    v1 = 0.0;// v1 = rw,null
	    v2 = 0.0;//v2 = rw,null

	    randnum = new random(iseed,v1,v2);// randum = rw, null
	   
	    System.out.println("testing Qualified name = "+randnum.v1);
	    
	    for (i=0; i < mdsize; i+=2) {
	     r  = randnum.seed(); // r = rw,null
	     one[i].xvelocity = r*randnum.v1;// xvelocity = rw, rw
	     one[i+1].xvelocity  = r*randnum.v2;
	    }

	    for (i=0; i<mdsize; i+=2) {
	     r  = randnum.seed();
	     one[i].yvelocity = r*randnum.v1; // yvelocity = rw, rw
	     one[i+1].yvelocity  = r*randnum.v2;
	    }

	    for (i=0; i<mdsize; i+=2) {
	     r  = randnum.seed();
	     one[i].zvelocity = r*randnum.v1; //zvelocity = rw, rw
	     one[i+1].zvelocity  = r*randnum.v2;
	    }

	/* velocity scaling */

	    ekin = 0.0; // ekin = rw, rw
	    sp = 0.0;// sp = rw, null

	    for(i = 0; i<mdsize;i++) {
	     sp = sp + one[i].xvelocity;
	    }
	    sp = sp / mdsize;

	    for(i=0;i<mdsize;i++) {
	     one[i].xvelocity = one[i].xvelocity - sp;
	     ekin = ekin + one[i].xvelocity*one[i].xvelocity;
	    }

	    sp = 0.0;
	    for(i=0;i<mdsize;i++) {
	     sp = sp + one[i].yvelocity;
	    }
	    sp = sp / mdsize;

	    for(i=0;i < mdsize;i++) {
	     one[i].yvelocity = one[i].yvelocity - sp;
	     ekin = ekin + one[i].yvelocity*one[i].yvelocity;
	    }

	    sp = 0.0;
	    for(i=0;i<mdsize;i++) {
	     sp = sp + one[i].zvelocity;
	    }
	    sp = sp / mdsize;

	    for(i=0;i<mdsize;i++) {
	     one[i].zvelocity = one[i].zvelocity - sp;
	     ekin = ekin + one[i].zvelocity*one[i].zvelocity;
	    }

	    ts = tscale * ekin; // ts = rw,null, 
	    sc = h * Math.sqrt(tref/ts);// sc = rw,rw

	    for(i=0;i<mdsize;i++) {

	    one[i].xvelocity = one[i].xvelocity * sc;     
	    one[i].yvelocity = one[i].yvelocity * sc;     
	    one[i].zvelocity = one[i].zvelocity * sc;
	    runiters();//full(this)

	    }

	/* MD simulation */

	  }

	  public void runiters(){

	   move = 0;// rw,null
	   for (move=0;move<movemx;move++) {//movemx r,r

	    for (i=0;i<mdsize;i++) {// mdsize = r, rw
	     one[i].domove(side);    // full(this), one = rw, rw, side = r rw    /* move the particles and update velocities */
	    }

	    epot = 0.0;// rw,rw
	    vir = 0.0;// rw,rw

	    for (i=0;i<mdsize;i++) {
	     one[i].force(side,rcoff,mdsize,i); // full(this), side=r,rw rcoff = r, rw, i = r,r /* compute forces */
	    }

	    sum = 0.0; // sum = rw, null

	    for (i=0;i<mdsize;i++) {
	     sum = sum + one[i].mkekin(hsq2);//ful(this) sum = rw,null, hsq2+= r, rw    /*scale forces, update velocities */
	    }

	    ekin = sum/hsq;// //ekin = rw,rw, hsq=r,rw   

	    vel = 0.0;// vel =rw, null
	    count = 0.0;// count = rw, null

	    for (i=0;i<mdsize;i++) {
	     vel = vel + one[i].velavg(vaverh,h); //full(this), safe approx h = r, r, veverh = r, rw /* average velocity */
	    }

	    vel = vel / h;

	/* tmeperature scale if required */

	    if((move < istop) && (((move+1) % irep) == 0)) {// mov = r,null, irep = r,null istop = r,null
	     sc = Math.sqrt(tref / (tscale*ekin)); // tref = r,r, sc = rw,rw, tscale = r, rw
	     for (i=0;i < mdsize;i++) {
	      one[i].dscal(sc,1);//full(this)
	     }
	     ekin = tref / tscale;
	    }

	/* sum to get full potential energy and virial */

	    if(((move+1) % iprint) == 0) { // iprint = r,null
	     ek = 24.0*ekin; //ek= rw, r,
	     epot = 4.0*epot;
	     etot = ek + epot;// rw,null
	     temp = tscale * ekin;// temp = rw,null
	     pres = den * 16.0 * (ekin - vir) / mdsize;// pres= rw,null, den = r, r
	     vel = vel / mdsize; 
	     rp = (count / mdsize) * 100.0;// rp = rw, null, count = r,null
	    }

	   }



	  }
	 
	}
	class particle {

	  public double xcoord, ycoord, zcoord;
	  public double xvelocity,yvelocity,zvelocity;
	  public double xforce,yforce,zforce;

	  public particle(double xco, double yco, double zco, double xvel,
	                  double yvel,double zvel,double xfor, 
	                  double yfor, double zfor) {

	   this.xcoord = xco; //xcoord = rw,rw
	   this.ycoord = yco; //ycoord = rw,rw
	   this.zcoord = zco;//zcoord = rw,rw
	   this.xvelocity = xvel;//xvelocity = rw,rw
	   this.yvelocity = yvel;//yvelocity = rw,rw
	   this.zvelocity = zvel;//zvelocity = rw,rw
	   this.xforce = xfor;//xforce = rw,rw
	   this.yforce = yfor;//yforce = rw,rw
	   this.zforce = zfor;//zforce = rw,rw

	  }

	  public void domove(double side) { // side = r,rw, and all other rw,rw

	    xcoord = xcoord + xvelocity + xforce;
	    ycoord = ycoord + yvelocity + yforce;
	    zcoord = zcoord + zvelocity + zforce;

	    if(xcoord < 0) { xcoord = xcoord + side; } 
	    if(xcoord > side) { xcoord = xcoord - side; }
	    if(ycoord < 0) { ycoord = ycoord + side; }
	    if(ycoord > side) { ycoord = ycoord - side; }
	    if(zcoord < 0) { zcoord = zcoord + side; }
	    if(zcoord > side) { zcoord = zcoord - side; }

	    xvelocity = xvelocity + xforce;
	    yvelocity = yvelocity + yforce;
	    zvelocity = zvelocity + zforce;

	    xforce = 0.0;
	    yforce = 0.0;
	    zforce = 0.0;

	  }

	  public void force(double side, double rcoff,int mdsize,int x) {

	    double sideh;
	    double rcoffs;

	    double xx,yy,zz,xi,yi,zi,fxi,fyi,fzi;
	    double rd,rrd,rrd2,rrd3,rrd4,rrd6,rrd7,r148;
	    double forcex,forcey,forcez;

	    int i;

	    sideh = 0.5*side; 
	    rcoffs = rcoff*rcoff;

	     xi = xcoord;
	     yi = ycoord;
	     zi = zcoord;
	     fxi = 0.0;
	     fyi = 0.0;
	     fzi = 0.0;

	       for (i=x+1;i<mdsize;i++) {  
	        xx = xi - md.one[i].xcoord;
	        yy = yi - md.one[i].ycoord;
	        zz = zi - md.one[i].zcoord;

	        if(xx < (-sideh)) { xx = xx + side; }
	        if(xx > (sideh))  { xx = xx - side; }
	        if(yy < (-sideh)) { yy = yy + side; }
	        if(yy > (sideh))  { yy = yy - side; }
	        if(zz < (-sideh)) { zz = zz + side; }
	        if(zz > (sideh))  { zz = zz - side; }

	        rd = xx*xx + yy*yy + zz*zz;

	        if(rd <= rcoffs) {
	           rrd = 1.0/rd;
	           rrd2 = rrd*rrd;
	           rrd3 = rrd2*rrd;
	           rrd4 = rrd2*rrd2;
	           rrd6 = rrd2*rrd4;
	           rrd7 = rrd6*rrd;
	           md.epot = md.epot + (rrd6 - rrd3);
	           r148 = rrd7 - 0.5*rrd4;
	           md.vir = md.vir - rd*r148;
	           forcex = xx * r148;
	           fxi = fxi + forcex;
	           md.one[i].xforce = md.one[i].xforce - forcex;
	           forcey = yy * r148;
	           fyi = fyi + forcey;
	           md.one[i].yforce = md.one[i].yforce - forcey;
	           forcez = zz * r148;
	           fzi = fzi + forcez;
	           md.one[i].zforce = md.one[i].zforce - forcez; 
	           md.interactions++;
	        }

	       } 

	     xforce = xforce + fxi;
	     yforce = yforce + fyi;
	     zforce = zforce + fzi;

	  }

	  public double mkekin(double hsq2) {

	    double sumt = 0.0; 

	    xforce = xforce * hsq2;
	    yforce = yforce * hsq2;
	    zforce = zforce * hsq2;
	    
	    xvelocity = xvelocity + xforce; 
	    yvelocity = yvelocity + yforce; 
	    zvelocity = zvelocity + zforce; 

	    sumt = (xvelocity*xvelocity)+(yvelocity*yvelocity)+(zvelocity*zvelocity);
	    return sumt;
	  }

	  public double velavg(double vaverh,double h) {
	 
	    double velt;
	    double sq;

	    sq = Math.sqrt(xvelocity*xvelocity + yvelocity*yvelocity +
	                 zvelocity*zvelocity);

	    if(sq > vaverh) { md.count = md.count + 1.0; }
	    
	    velt = sq;
	    return velt;
	  }

	  public void dscal(double sc,int incx) {

	    xvelocity = xvelocity * sc;
	    yvelocity = yvelocity * sc;   
	    zvelocity = zvelocity * sc;   



	  }

	}

	class random {

	  public int iseed;
	  public double v1,v2;

	  public random(int iseed,double v1,double v2) {// iseed = rw,rw, v1= rw,rw, v2= rw,rw
		  this.iseed = iseed;
		  this.v1 = v1;
		  this.v2 = v2;
	  }

	  public double update() {// iseed = rw,rw

	  double rand;
	  double scale= 4.656612875e-10;

	  int is1,is2,iss2;
	  int imult=16807;
	  int imod = 2147483647;

	  if (iseed<=0) { iseed = 1; }

	  is2 = iseed % 32768;
	  is1 = (iseed-is2)/32768;
	  iss2 = is2 * imult;
	  is2 = iss2 % 32768;
	  is1 = (is1*imult+(iss2-is2)/32768) % (65536);

	  iseed = (is1*32768+is2) % imod;

	  rand = scale * iseed;

	  return rand;

	  }

	  public double seed() {// v1= rw, rw, v2 = rw,rw

	   double s,u1,u2,r;
	     do {
	       
	      u1 = update();
	       
	       u2 = update();

	       v1 = 2.0 * u1 - 1.0;
	       v2 = 2.0 * u2 - 1.0;
	       s = v1*v1 + v2*v2;

	     } while (s >= 1.0);

	     r = Math.sqrt(-2.0*Math.log(s)/s);

	     return r;
	}
	}

/* 
Class Name = md
Class Name = JGFMolDynBenchSizeA
Method Name = main
Vertex Name = ITERS, Post Permissions = unique, Pre-Permissions =none
Vertex Name = LENGTH, Post Permissions = unique, Pre-Permissions =none
Vertex Name = m, Post Permissions = unique, Pre-Permissions =none
Vertex Name = mu, Post Permissions = unique, Pre-Permissions =none
Vertex Name = kb, Post Permissions = unique, Pre-Permissions =none
Vertex Name = TSIM, Post Permissions = unique, Pre-Permissions =none
Vertex Name = deltat, Post Permissions = unique, Pre-Permissions =none
Vertex Name = one, Post Permissions = unique, Pre-Permissions =none
Vertex Name = epot, Post Permissions = unique, Pre-Permissions =none
Vertex Name = vir, Post Permissions = unique, Pre-Permissions =none
Vertex Name = count, Post Permissions = unique, Pre-Permissions =none
Vertex Name = datasizes, Post Permissions = unique, Pre-Permissions =none
Vertex Name = interactions, Post Permissions = unique, Pre-Permissions =none
Vertex Name = den, Post Permissions = unique, Pre-Permissions =none
Vertex Name = tref, Post Permissions = unique, Pre-Permissions =none
Vertex Name = h, Post Permissions = unique, Pre-Permissions =none
Vertex Name = irep, Post Permissions = unique, Pre-Permissions =none
Vertex Name = istop, Post Permissions = unique, Pre-Permissions =none
Vertex Name = iprint, Post Permissions = unique, Pre-Permissions =none
Vertex Name = movemx, Post Permissions = unique, Pre-Permissions =none
Vertex Name = timers, Post Permissions = unique, Pre-Permissions =none
Vertex Name = name, Post Permissions = unique, Pre-Permissions =none
Vertex Name = opname, Post Permissions = unique, Pre-Permissions =none
Vertex Name = size, Post Permissions = unique, Pre-Permissions =none
Vertex Name = time, Post Permissions = unique, Pre-Permissions =none
Vertex Name = calls, Post Permissions = unique, Pre-Permissions =none
Vertex Name = opcount, Post Permissions = unique, Pre-Permissions =none
Vertex Name = on, Post Permissions = unique, Pre-Permissions =none
Vertex Name = start_time, Post Permissions = unique, Pre-Permissions =none
Vertex Name = nbf, Post Permissions = unique, Pre-Permissions =none
Vertex Name = nbf2, Post Permissions = unique, Pre-Permissions =none
Vertex Name = nbf3, Post Permissions = unique, Pre-Permissions =none
Vertex Name = mm, Post Permissions = unique, Pre-Permissions =none
Vertex Name = PARTSIZE, Post Permissions = unique, Pre-Permissions =none
Vertex Name = mdsize, Post Permissions = unique, Pre-Permissions =none
Vertex Name = l, Post Permissions = unique, Pre-Permissions =none
Vertex Name = side, Post Permissions = unique, Pre-Permissions =none
Vertex Name = rcoff, Post Permissions = unique, Pre-Permissions =none
Vertex Name = a, Post Permissions = unique, Pre-Permissions =none
Vertex Name = sideh, Post Permissions = unique, Pre-Permissions =none
Vertex Name = hsq, Post Permissions = unique, Pre-Permissions =none
Vertex Name = hsq2, Post Permissions = unique, Pre-Permissions =none
Vertex Name = npartm, Post Permissions = unique, Pre-Permissions =none
Vertex Name = rcoffs, Post Permissions = unique, Pre-Permissions =none
Vertex Name = tscale, Post Permissions = unique, Pre-Permissions =none
Vertex Name = vaver, Post Permissions = unique, Pre-Permissions =none
Vertex Name = vaverh, Post Permissions = unique, Pre-Permissions =none
Vertex Name = ijk, Post Permissions = unique, Pre-Permissions =none
Vertex Name = lg, Post Permissions = unique, Pre-Permissions =none
Vertex Name = i, Post Permissions = unique, Pre-Permissions =none
Vertex Name = j, Post Permissions = unique, Pre-Permissions =none
Vertex Name = k, Post Permissions = unique, Pre-Permissions =none
Vertex Name = iseed, Post Permissions = unique, Pre-Permissions =none
Vertex Name = v1, Post Permissions = unique, Pre-Permissions =none
Vertex Name = v2, Post Permissions = unique, Pre-Permissions =none
Vertex Name = randnum, Post Permissions = unique, Pre-Permissions =none
Vertex Name = r, Post Permissions = unique, Pre-Permissions =none
Vertex Name = xvelocity, Post Permissions = unique, Pre-Permissions =none
Vertex Name = yvelocity, Post Permissions = unique, Pre-Permissions =none
Vertex Name = zvelocity, Post Permissions = unique, Pre-Permissions =none
Vertex Name = ekin, Post Permissions = unique, Pre-Permissions =none
Vertex Name = sp, Post Permissions = unique, Pre-Permissions =none
Vertex Name = ts, Post Permissions = unique, Pre-Permissions =none
Vertex Name = sc, Post Permissions = unique, Pre-Permissions =none
Vertex Name = xcoord, Post Permissions = unique, Pre-Permissions =none
Vertex Name = ycoord, Post Permissions = unique, Pre-Permissions =none
Vertex Name = zcoord, Post Permissions = unique, Pre-Permissions =none
Vertex Name = xforce, Post Permissions = unique, Pre-Permissions =none
Vertex Name = yforce, Post Permissions = unique, Pre-Permissions =none
Vertex Name = zforce, Post Permissions = unique, Pre-Permissions =none
Vertex Name = move, Post Permissions = unique, Pre-Permissions =none
Vertex Name = sum, Post Permissions = unique, Pre-Permissions =none
Vertex Name = vel, Post Permissions = unique, Pre-Permissions =none
Vertex Name = ek, Post Permissions = unique, Pre-Permissions =none
Vertex Name = etot, Post Permissions = unique, Pre-Permissions =none
Vertex Name = temp, Post Permissions = unique, Pre-Permissions =none
Vertex Name = pres, Post Permissions = unique, Pre-Permissions =none
Vertex Name = rp, Post Permissions = unique, Pre-Permissions =none
Method Name = initialise
Vertex Name = nbf, Post Permissions = share, Pre-Permissions =share
Vertex Name = nbf2, Post Permissions = share, Pre-Permissions =share
Vertex Name = nbf3, Post Permissions = share, Pre-Permissions =share
Vertex Name = mm, Post Permissions = share, Pre-Permissions =share
Vertex Name = datasizes, Post Permissions = immutable, Pre-Permissions =immutable
Vertex Name = size, Post Permissions = pure, Pre-Permissions =pure
Vertex Name = PARTSIZE, Post Permissions = share, Pre-Permissions =share
Vertex Name = mdsize, Post Permissions = share, Pre-Permissions =share
Vertex Name = one, Post Permissions = unique, Pre-Permissions =none
Vertex Name = l, Post Permissions = share, Pre-Permissions =share
Vertex Name = LENGTH, Post Permissions = pure, Pre-Permissions =pure
Vertex Name = side, Post Permissions = share, Pre-Permissions =share
Vertex Name = den, Post Permissions = pure, Pre-Permissions =pure
Vertex Name = rcoff, Post Permissions = share, Pre-Permissions =share
Vertex Name = a, Post Permissions = share, Pre-Permissions =share
Vertex Name = sideh, Post Permissions = share, Pre-Permissions =share
Vertex Name = hsq, Post Permissions = share, Pre-Permissions =share
Vertex Name = h, Post Permissions = pure, Pre-Permissions =pure
Vertex Name = hsq2, Post Permissions = share, Pre-Permissions =share
Vertex Name = npartm, Post Permissions = share, Pre-Permissions =share
Vertex Name = rcoffs, Post Permissions = share, Pre-Permissions =share
Vertex Name = tscale, Post Permissions = share, Pre-Permissions =share
Vertex Name = vaver, Post Permissions = share, Pre-Permissions =share
Vertex Name = tref, Post Permissions = pure, Pre-Permissions =pure
Vertex Name = vaverh, Post Permissions = share, Pre-Permissions =share
Vertex Name = ijk, Post Permissions = unique, Pre-Permissions =none
Vertex Name = lg, Post Permissions = share, Pre-Permissions =share
Vertex Name = i, Post Permissions = share, Pre-Permissions =share
Vertex Name = j, Post Permissions = share, Pre-Permissions =share
Vertex Name = k, Post Permissions = share, Pre-Permissions =share
Vertex Name = iseed, Post Permissions = share, Pre-Permissions =share
Vertex Name = v1, Post Permissions = share, Pre-Permissions =share
Vertex Name = v2, Post Permissions = share, Pre-Permissions =share
Vertex Name = randnum, Post Permissions = unique, Pre-Permissions =none
Vertex Name = r, Post Permissions = share, Pre-Permissions =share
Vertex Name = xvelocity, Post Permissions = share, Pre-Permissions =share
Vertex Name = yvelocity, Post Permissions = share, Pre-Permissions =share
Vertex Name = zvelocity, Post Permissions = share, Pre-Permissions =share
Vertex Name = ekin, Post Permissions = share, Pre-Permissions =share
Vertex Name = sp, Post Permissions = share, Pre-Permissions =share
Vertex Name = ts, Post Permissions = share, Pre-Permissions =share
Vertex Name = sc, Post Permissions = share, Pre-Permissions =share
Vertex Name = xcoord, Post Permissions = share, Pre-Permissions =share
Vertex Name = ycoord, Post Permissions = share, Pre-Permissions =share
Vertex Name = zcoord, Post Permissions = share, Pre-Permissions =share
Vertex Name = xforce, Post Permissions = share, Pre-Permissions =share
Vertex Name = yforce, Post Permissions = share, Pre-Permissions =share
Vertex Name = zforce, Post Permissions = share, Pre-Permissions =share
Method Name = particle
Vertex Name = xcoord, Post Permissions = share, Pre-Permissions =share
Vertex Name = ycoord, Post Permissions = share, Pre-Permissions =share
Vertex Name = zcoord, Post Permissions = share, Pre-Permissions =share
Vertex Name = xvelocity, Post Permissions = share, Pre-Permissions =share
Vertex Name = yvelocity, Post Permissions = share, Pre-Permissions =share
Vertex Name = zvelocity, Post Permissions = share, Pre-Permissions =share
Vertex Name = xforce, Post Permissions = share, Pre-Permissions =share
Vertex Name = yforce, Post Permissions = share, Pre-Permissions =share
Vertex Name = zforce, Post Permissions = share, Pre-Permissions =share
Method Name = random
Vertex Name = iseed, Post Permissions = share, Pre-Permissions =share
Vertex Name = v1, Post Permissions = share, Pre-Permissions =share
Vertex Name = v2, Post Permissions = share, Pre-Permissions =share
Method Name = seed
Vertex Name = v1, Post Permissions = share, Pre-Permissions =share
Vertex Name = v2, Post Permissions = share, Pre-Permissions =share
Vertex Name = iseed, Post Permissions = share, Pre-Permissions =share
Method Name = update
Vertex Name = iseed, Post Permissions = share, Pre-Permissions =share
Method Name = runiters
Vertex Name = move, Post Permissions = share, Pre-Permissions =share
Vertex Name = movemx, Post Permissions = pure, Pre-Permissions =pure
Vertex Name = i, Post Permissions = share, Pre-Permissions =share
Vertex Name = mdsize, Post Permissions = pure, Pre-Permissions =pure
Vertex Name = one, Post Permissions = share, Pre-Permissions =share
Vertex Name = side, Post Permissions = pure, Pre-Permissions =pure
Vertex Name = epot, Post Permissions = share, Pre-Permissions =share
Vertex Name = vir, Post Permissions = share, Pre-Permissions =share
Vertex Name = rcoff, Post Permissions = pure, Pre-Permissions =pure
Vertex Name = sum, Post Permissions = share, Pre-Permissions =share
Vertex Name = hsq2, Post Permissions = pure, Pre-Permissions =pure
Vertex Name = ekin, Post Permissions = share, Pre-Permissions =share
Vertex Name = hsq, Post Permissions = pure, Pre-Permissions =pure
Vertex Name = vel, Post Permissions = share, Pre-Permissions =share
Vertex Name = count, Post Permissions = share, Pre-Permissions =share
Vertex Name = vaverh, Post Permissions = pure, Pre-Permissions =pure
Vertex Name = h, Post Permissions = pure, Pre-Permissions =pure
Vertex Name = istop, Post Permissions = pure, Pre-Permissions =pure
Vertex Name = irep, Post Permissions = pure, Pre-Permissions =pure
Vertex Name = sc, Post Permissions = share, Pre-Permissions =share
Vertex Name = tref, Post Permissions = pure, Pre-Permissions =pure
Vertex Name = tscale, Post Permissions = share, Pre-Permissions =share
Vertex Name = iprint, Post Permissions = pure, Pre-Permissions =pure
Vertex Name = ek, Post Permissions = share, Pre-Permissions =share
Vertex Name = etot, Post Permissions = share, Pre-Permissions =share
Vertex Name = temp, Post Permissions = share, Pre-Permissions =share
Vertex Name = pres, Post Permissions = share, Pre-Permissions =share
Vertex Name = den, Post Permissions = pure, Pre-Permissions =pure
Vertex Name = rp, Post Permissions = share, Pre-Permissions =share
Vertex Name = xcoord, Post Permissions = share, Pre-Permissions =share
Vertex Name = xvelocity, Post Permissions = share, Pre-Permissions =share
Vertex Name = xforce, Post Permissions = share, Pre-Permissions =share
Vertex Name = ycoord, Post Permissions = share, Pre-Permissions =share
Vertex Name = yvelocity, Post Permissions = share, Pre-Permissions =share
Vertex Name = yforce, Post Permissions = share, Pre-Permissions =share
Vertex Name = zcoord, Post Permissions = share, Pre-Permissions =share
Vertex Name = zvelocity, Post Permissions = share, Pre-Permissions =share
Vertex Name = zforce, Post Permissions = share, Pre-Permissions =share
Vertex Name = interactions, Post Permissions = share, Pre-Permissions =share
Method Name = domove
Vertex Name = side, Post Permissions = pure, Pre-Permissions =pure
Vertex Name = xcoord, Post Permissions = share, Pre-Permissions =share
Vertex Name = xvelocity, Post Permissions = share, Pre-Permissions =share
Vertex Name = xforce, Post Permissions = share, Pre-Permissions =share
Vertex Name = ycoord, Post Permissions = share, Pre-Permissions =share
Vertex Name = yvelocity, Post Permissions = share, Pre-Permissions =share
Vertex Name = yforce, Post Permissions = share, Pre-Permissions =share
Vertex Name = zcoord, Post Permissions = share, Pre-Permissions =share
Vertex Name = zvelocity, Post Permissions = share, Pre-Permissions =share
Vertex Name = zforce, Post Permissions = share, Pre-Permissions =share
Method Name = force
Vertex Name = side, Post Permissions = pure, Pre-Permissions =pure
Vertex Name = rcoff, Post Permissions = pure, Pre-Permissions =pure
Vertex Name = mdsize, Post Permissions = pure, Pre-Permissions =pure
Vertex Name = i, Post Permissions = pure, Pre-Permissions =pure
Vertex Name = xcoord, Post Permissions = pure, Pre-Permissions =pure
Vertex Name = ycoord, Post Permissions = pure, Pre-Permissions =pure
Vertex Name = zcoord, Post Permissions = pure, Pre-Permissions =pure
Vertex Name = one, Post Permissions = pure, Pre-Permissions =pure
Vertex Name = epot, Post Permissions = share, Pre-Permissions =share
Vertex Name = vir, Post Permissions = share, Pre-Permissions =share
Vertex Name = xforce, Post Permissions = share, Pre-Permissions =share
Vertex Name = yforce, Post Permissions = share, Pre-Permissions =share
Vertex Name = zforce, Post Permissions = share, Pre-Permissions =share
Vertex Name = interactions, Post Permissions = share, Pre-Permissions =share
Method Name = mkekin
Vertex Name = hsq2, Post Permissions = pure, Pre-Permissions =pure
Vertex Name = xforce, Post Permissions = share, Pre-Permissions =share
Vertex Name = yforce, Post Permissions = share, Pre-Permissions =share
Vertex Name = zforce, Post Permissions = share, Pre-Permissions =share
Vertex Name = xvelocity, Post Permissions = share, Pre-Permissions =share
Vertex Name = yvelocity, Post Permissions = share, Pre-Permissions =share
Vertex Name = zvelocity, Post Permissions = share, Pre-Permissions =share
Method Name = velavg
Vertex Name = vaverh, Post Permissions = pure, Pre-Permissions =pure
Vertex Name = h, Post Permissions = pure, Pre-Permissions =pure
Vertex Name = xvelocity, Post Permissions = pure, Pre-Permissions =pure
Vertex Name = yvelocity, Post Permissions = pure, Pre-Permissions =pure
Vertex Name = zvelocity, Post Permissions = pure, Pre-Permissions =pure
Vertex Name = count, Post Permissions = share, Pre-Permissions =share
Method Name = dscal
Vertex Name = sc, Post Permissions = pure, Pre-Permissions =pure
Vertex Name = xvelocity, Post Permissions = share, Pre-Permissions =share
Vertex Name = yvelocity, Post Permissions = share, Pre-Permissions =share
Vertex Name = zvelocity, Post Permissions = share, Pre-Permissions =share
Class Name = particle
Class Name = JGFMolDynBenchSizeA
Method Name = main
Vertex Name = ITERS, Post Permissions = unique, Pre-Permissions =none
Vertex Name = LENGTH, Post Permissions = unique, Pre-Permissions =none
Vertex Name = m, Post Permissions = unique, Pre-Permissions =none
Vertex Name = mu, Post Permissions = unique, Pre-Permissions =none
Vertex Name = kb, Post Permissions = unique, Pre-Permissions =none
Vertex Name = TSIM, Post Permissions = unique, Pre-Permissions =none
Vertex Name = deltat, Post Permissions = unique, Pre-Permissions =none
Vertex Name = one, Post Permissions = unique, Pre-Permissions =none
Vertex Name = epot, Post Permissions = unique, Pre-Permissions =none
Vertex Name = vir, Post Permissions = unique, Pre-Permissions =none
Vertex Name = count, Post Permissions = unique, Pre-Permissions =none
Vertex Name = datasizes, Post Permissions = unique, Pre-Permissions =none
Vertex Name = interactions, Post Permissions = unique, Pre-Permissions =none
Vertex Name = den, Post Permissions = unique, Pre-Permissions =none
Vertex Name = tref, Post Permissions = unique, Pre-Permissions =none
Vertex Name = h, Post Permissions = unique, Pre-Permissions =none
Vertex Name = irep, Post Permissions = unique, Pre-Permissions =none
Vertex Name = istop, Post Permissions = unique, Pre-Permissions =none
Vertex Name = iprint, Post Permissions = unique, Pre-Permissions =none
Vertex Name = movemx, Post Permissions = unique, Pre-Permissions =none
Vertex Name = timers, Post Permissions = unique, Pre-Permissions =none
Vertex Name = name, Post Permissions = unique, Pre-Permissions =none
Vertex Name = opname, Post Permissions = unique, Pre-Permissions =none
Vertex Name = size, Post Permissions = unique, Pre-Permissions =none
Vertex Name = time, Post Permissions = unique, Pre-Permissions =none
Vertex Name = calls, Post Permissions = unique, Pre-Permissions =none
Vertex Name = opcount, Post Permissions = unique, Pre-Permissions =none
Vertex Name = on, Post Permissions = unique, Pre-Permissions =none
Vertex Name = start_time, Post Permissions = unique, Pre-Permissions =none
Vertex Name = nbf, Post Permissions = unique, Pre-Permissions =none
Vertex Name = nbf2, Post Permissions = unique, Pre-Permissions =none
Vertex Name = nbf3, Post Permissions = unique, Pre-Permissions =none
Vertex Name = mm, Post Permissions = unique, Pre-Permissions =none
Vertex Name = PARTSIZE, Post Permissions = unique, Pre-Permissions =none
Vertex Name = mdsize, Post Permissions = unique, Pre-Permissions =none
Vertex Name = l, Post Permissions = unique, Pre-Permissions =none
Vertex Name = side, Post Permissions = unique, Pre-Permissions =none
Vertex Name = rcoff, Post Permissions = unique, Pre-Permissions =none
Vertex Name = a, Post Permissions = unique, Pre-Permissions =none
Vertex Name = sideh, Post Permissions = unique, Pre-Permissions =none
Vertex Name = hsq, Post Permissions = unique, Pre-Permissions =none
Vertex Name = hsq2, Post Permissions = unique, Pre-Permissions =none
Vertex Name = npartm, Post Permissions = unique, Pre-Permissions =none
Vertex Name = rcoffs, Post Permissions = unique, Pre-Permissions =none
Vertex Name = tscale, Post Permissions = unique, Pre-Permissions =none
Vertex Name = vaver, Post Permissions = unique, Pre-Permissions =none
Vertex Name = vaverh, Post Permissions = unique, Pre-Permissions =none
Vertex Name = ijk, Post Permissions = unique, Pre-Permissions =none
Vertex Name = lg, Post Permissions = unique, Pre-Permissions =none
Vertex Name = i, Post Permissions = unique, Pre-Permissions =none
Vertex Name = j, Post Permissions = unique, Pre-Permissions =none
Vertex Name = k, Post Permissions = unique, Pre-Permissions =none
Vertex Name = iseed, Post Permissions = unique, Pre-Permissions =none
Vertex Name = v1, Post Permissions = unique, Pre-Permissions =none
Vertex Name = v2, Post Permissions = unique, Pre-Permissions =none
Vertex Name = randnum, Post Permissions = unique, Pre-Permissions =none
Vertex Name = r, Post Permissions = unique, Pre-Permissions =none
Vertex Name = xvelocity, Post Permissions = unique, Pre-Permissions =none
Vertex Name = yvelocity, Post Permissions = unique, Pre-Permissions =none
Vertex Name = zvelocity, Post Permissions = unique, Pre-Permissions =none
Vertex Name = ekin, Post Permissions = unique, Pre-Permissions =none
Vertex Name = sp, Post Permissions = unique, Pre-Permissions =none
Vertex Name = ts, Post Permissions = unique, Pre-Permissions =none
Vertex Name = sc, Post Permissions = unique, Pre-Permissions =none
Vertex Name = xcoord, Post Permissions = unique, Pre-Permissions =none
Vertex Name = ycoord, Post Permissions = unique, Pre-Permissions =none
Vertex Name = zcoord, Post Permissions = unique, Pre-Permissions =none
Vertex Name = xforce, Post Permissions = unique, Pre-Permissions =none
Vertex Name = yforce, Post Permissions = unique, Pre-Permissions =none
Vertex Name = zforce, Post Permissions = unique, Pre-Permissions =none
Vertex Name = move, Post Permissions = unique, Pre-Permissions =none
Vertex Name = sum, Post Permissions = unique, Pre-Permissions =none
Vertex Name = vel, Post Permissions = unique, Pre-Permissions =none
Vertex Name = ek, Post Permissions = unique, Pre-Permissions =none
Vertex Name = etot, Post Permissions = unique, Pre-Permissions =none
Vertex Name = temp, Post Permissions = unique, Pre-Permissions =none
Vertex Name = pres, Post Permissions = unique, Pre-Permissions =none
Vertex Name = rp, Post Permissions = unique, Pre-Permissions =none
Method Name = particle
Vertex Name = xcoord, Post Permissions = share, Pre-Permissions =share
Vertex Name = ycoord, Post Permissions = share, Pre-Permissions =share
Vertex Name = zcoord, Post Permissions = share, Pre-Permissions =share
Vertex Name = xvelocity, Post Permissions = share, Pre-Permissions =share
Vertex Name = yvelocity, Post Permissions = share, Pre-Permissions =share
Vertex Name = zvelocity, Post Permissions = share, Pre-Permissions =share
Vertex Name = xforce, Post Permissions = share, Pre-Permissions =share
Vertex Name = yforce, Post Permissions = share, Pre-Permissions =share
Vertex Name = zforce, Post Permissions = share, Pre-Permissions =share
Method Name = domove
Vertex Name = side, Post Permissions = pure, Pre-Permissions =pure
Vertex Name = xcoord, Post Permissions = share, Pre-Permissions =share
Vertex Name = xvelocity, Post Permissions = share, Pre-Permissions =share
Vertex Name = xforce, Post Permissions = share, Pre-Permissions =share
Vertex Name = ycoord, Post Permissions = share, Pre-Permissions =share
Vertex Name = yvelocity, Post Permissions = share, Pre-Permissions =share
Vertex Name = yforce, Post Permissions = share, Pre-Permissions =share
Vertex Name = zcoord, Post Permissions = share, Pre-Permissions =share
Vertex Name = zvelocity, Post Permissions = share, Pre-Permissions =share
Vertex Name = zforce, Post Permissions = share, Pre-Permissions =share
Method Name = force
Vertex Name = side, Post Permissions = pure, Pre-Permissions =pure
Vertex Name = rcoff, Post Permissions = pure, Pre-Permissions =pure
Vertex Name = mdsize, Post Permissions = pure, Pre-Permissions =pure
Vertex Name = i, Post Permissions = pure, Pre-Permissions =pure
Vertex Name = xcoord, Post Permissions = pure, Pre-Permissions =pure
Vertex Name = ycoord, Post Permissions = pure, Pre-Permissions =pure
Vertex Name = zcoord, Post Permissions = pure, Pre-Permissions =pure
Vertex Name = one, Post Permissions = pure, Pre-Permissions =pure
Vertex Name = epot, Post Permissions = share, Pre-Permissions =share
Vertex Name = vir, Post Permissions = share, Pre-Permissions =share
Vertex Name = xforce, Post Permissions = share, Pre-Permissions =share
Vertex Name = yforce, Post Permissions = share, Pre-Permissions =share
Vertex Name = zforce, Post Permissions = share, Pre-Permissions =share
Vertex Name = interactions, Post Permissions = share, Pre-Permissions =share
Method Name = mkekin
Vertex Name = hsq2, Post Permissions = pure, Pre-Permissions =pure
Vertex Name = xforce, Post Permissions = share, Pre-Permissions =share
Vertex Name = yforce, Post Permissions = share, Pre-Permissions =share
Vertex Name = zforce, Post Permissions = share, Pre-Permissions =share
Vertex Name = xvelocity, Post Permissions = share, Pre-Permissions =share
Vertex Name = yvelocity, Post Permissions = share, Pre-Permissions =share
Vertex Name = zvelocity, Post Permissions = share, Pre-Permissions =share
Method Name = velavg
Vertex Name = vaverh, Post Permissions = pure, Pre-Permissions =pure
Vertex Name = h, Post Permissions = pure, Pre-Permissions =pure
Vertex Name = xvelocity, Post Permissions = pure, Pre-Permissions =pure
Vertex Name = yvelocity, Post Permissions = pure, Pre-Permissions =pure
Vertex Name = zvelocity, Post Permissions = pure, Pre-Permissions =pure
Vertex Name = count, Post Permissions = share, Pre-Permissions =share
Method Name = dscal
Vertex Name = sc, Post Permissions = pure, Pre-Permissions =pure
Vertex Name = xvelocity, Post Permissions = share, Pre-Permissions =share
Vertex Name = yvelocity, Post Permissions = share, Pre-Permissions =share
Vertex Name = zvelocity, Post Permissions = share, Pre-Permissions =share
Class Name = random
Class Name = JGFMolDynBenchSizeA
Method Name = main
Vertex Name = ITERS, Post Permissions = unique, Pre-Permissions =none
Vertex Name = LENGTH, Post Permissions = unique, Pre-Permissions =none
Vertex Name = m, Post Permissions = unique, Pre-Permissions =none
Vertex Name = mu, Post Permissions = unique, Pre-Permissions =none
Vertex Name = kb, Post Permissions = unique, Pre-Permissions =none
Vertex Name = TSIM, Post Permissions = unique, Pre-Permissions =none
Vertex Name = deltat, Post Permissions = unique, Pre-Permissions =none
Vertex Name = one, Post Permissions = unique, Pre-Permissions =none
Vertex Name = epot, Post Permissions = unique, Pre-Permissions =none
Vertex Name = vir, Post Permissions = unique, Pre-Permissions =none
Vertex Name = count, Post Permissions = unique, Pre-Permissions =none
Vertex Name = datasizes, Post Permissions = unique, Pre-Permissions =none
Vertex Name = interactions, Post Permissions = unique, Pre-Permissions =none
Vertex Name = den, Post Permissions = unique, Pre-Permissions =none
Vertex Name = tref, Post Permissions = unique, Pre-Permissions =none
Vertex Name = h, Post Permissions = unique, Pre-Permissions =none
Vertex Name = irep, Post Permissions = unique, Pre-Permissions =none
Vertex Name = istop, Post Permissions = unique, Pre-Permissions =none
Vertex Name = iprint, Post Permissions = unique, Pre-Permissions =none
Vertex Name = movemx, Post Permissions = unique, Pre-Permissions =none
Vertex Name = timers, Post Permissions = unique, Pre-Permissions =none
Vertex Name = name, Post Permissions = unique, Pre-Permissions =none
Vertex Name = opname, Post Permissions = unique, Pre-Permissions =none
Vertex Name = size, Post Permissions = unique, Pre-Permissions =none
Vertex Name = time, Post Permissions = unique, Pre-Permissions =none
Vertex Name = calls, Post Permissions = unique, Pre-Permissions =none
Vertex Name = opcount, Post Permissions = unique, Pre-Permissions =none
Vertex Name = on, Post Permissions = unique, Pre-Permissions =none
Vertex Name = start_time, Post Permissions = unique, Pre-Permissions =none
Vertex Name = nbf, Post Permissions = unique, Pre-Permissions =none
Vertex Name = nbf2, Post Permissions = unique, Pre-Permissions =none
Vertex Name = nbf3, Post Permissions = unique, Pre-Permissions =none
Vertex Name = mm, Post Permissions = unique, Pre-Permissions =none
Vertex Name = PARTSIZE, Post Permissions = unique, Pre-Permissions =none
Vertex Name = mdsize, Post Permissions = unique, Pre-Permissions =none
Vertex Name = l, Post Permissions = unique, Pre-Permissions =none
Vertex Name = side, Post Permissions = unique, Pre-Permissions =none
Vertex Name = rcoff, Post Permissions = unique, Pre-Permissions =none
Vertex Name = a, Post Permissions = unique, Pre-Permissions =none
Vertex Name = sideh, Post Permissions = unique, Pre-Permissions =none
Vertex Name = hsq, Post Permissions = unique, Pre-Permissions =none
Vertex Name = hsq2, Post Permissions = unique, Pre-Permissions =none
Vertex Name = npartm, Post Permissions = unique, Pre-Permissions =none
Vertex Name = rcoffs, Post Permissions = unique, Pre-Permissions =none
Vertex Name = tscale, Post Permissions = unique, Pre-Permissions =none
Vertex Name = vaver, Post Permissions = unique, Pre-Permissions =none
Vertex Name = vaverh, Post Permissions = unique, Pre-Permissions =none
Vertex Name = ijk, Post Permissions = unique, Pre-Permissions =none
Vertex Name = lg, Post Permissions = unique, Pre-Permissions =none
Vertex Name = i, Post Permissions = unique, Pre-Permissions =none
Vertex Name = j, Post Permissions = unique, Pre-Permissions =none
Vertex Name = k, Post Permissions = unique, Pre-Permissions =none
Vertex Name = iseed, Post Permissions = unique, Pre-Permissions =none
Vertex Name = v1, Post Permissions = unique, Pre-Permissions =none
Vertex Name = v2, Post Permissions = unique, Pre-Permissions =none
Vertex Name = randnum, Post Permissions = unique, Pre-Permissions =none
Vertex Name = r, Post Permissions = unique, Pre-Permissions =none
Vertex Name = xvelocity, Post Permissions = unique, Pre-Permissions =none
Vertex Name = yvelocity, Post Permissions = unique, Pre-Permissions =none
Vertex Name = zvelocity, Post Permissions = unique, Pre-Permissions =none
Vertex Name = ekin, Post Permissions = unique, Pre-Permissions =none
Vertex Name = sp, Post Permissions = unique, Pre-Permissions =none
Vertex Name = ts, Post Permissions = unique, Pre-Permissions =none
Vertex Name = sc, Post Permissions = unique, Pre-Permissions =none
Vertex Name = xcoord, Post Permissions = unique, Pre-Permissions =none
Vertex Name = ycoord, Post Permissions = unique, Pre-Permissions =none
Vertex Name = zcoord, Post Permissions = unique, Pre-Permissions =none
Vertex Name = xforce, Post Permissions = unique, Pre-Permissions =none
Vertex Name = yforce, Post Permissions = unique, Pre-Permissions =none
Vertex Name = zforce, Post Permissions = unique, Pre-Permissions =none
Vertex Name = move, Post Permissions = unique, Pre-Permissions =none
Vertex Name = sum, Post Permissions = unique, Pre-Permissions =none
Vertex Name = vel, Post Permissions = unique, Pre-Permissions =none
Vertex Name = ek, Post Permissions = unique, Pre-Permissions =none
Vertex Name = etot, Post Permissions = unique, Pre-Permissions =none
Vertex Name = temp, Post Permissions = unique, Pre-Permissions =none
Vertex Name = pres, Post Permissions = unique, Pre-Permissions =none
Vertex Name = rp, Post Permissions = unique, Pre-Permissions =none
Method Name = random
Vertex Name = iseed, Post Permissions = share, Pre-Permissions =share
Vertex Name = v1, Post Permissions = share, Pre-Permissions =share
Vertex Name = v2, Post Permissions = share, Pre-Permissions =share
Method Name = update
Vertex Name = iseed, Post Permissions = share, Pre-Permissions =share
Method Name = seed
Vertex Name = v1, Post Permissions = share, Pre-Permissions =share
Vertex Name = v2, Post Permissions = share, Pre-Permissions =share
Vertex Name = iseed, Post Permissions = share, Pre-Permissions =share
Class Name = JGFMolDynBench
Class Name = JGFMolDynBenchSizeA
Method Name = main
Vertex Name = ITERS, Post Permissions = unique, Pre-Permissions =none
Vertex Name = LENGTH, Post Permissions = unique, Pre-Permissions =none
Vertex Name = m, Post Permissions = unique, Pre-Permissions =none
Vertex Name = mu, Post Permissions = unique, Pre-Permissions =none
Vertex Name = kb, Post Permissions = unique, Pre-Permissions =none
Vertex Name = TSIM, Post Permissions = unique, Pre-Permissions =none
Vertex Name = deltat, Post Permissions = unique, Pre-Permissions =none
Vertex Name = one, Post Permissions = unique, Pre-Permissions =none
Vertex Name = epot, Post Permissions = unique, Pre-Permissions =none
Vertex Name = vir, Post Permissions = unique, Pre-Permissions =none
Vertex Name = count, Post Permissions = unique, Pre-Permissions =none
Vertex Name = datasizes, Post Permissions = unique, Pre-Permissions =none
Vertex Name = interactions, Post Permissions = unique, Pre-Permissions =none
Vertex Name = den, Post Permissions = unique, Pre-Permissions =none
Vertex Name = tref, Post Permissions = unique, Pre-Permissions =none
Vertex Name = h, Post Permissions = unique, Pre-Permissions =none
Vertex Name = irep, Post Permissions = unique, Pre-Permissions =none
Vertex Name = istop, Post Permissions = unique, Pre-Permissions =none
Vertex Name = iprint, Post Permissions = unique, Pre-Permissions =none
Vertex Name = movemx, Post Permissions = unique, Pre-Permissions =none
Vertex Name = timers, Post Permissions = unique, Pre-Permissions =none
Vertex Name = name, Post Permissions = unique, Pre-Permissions =none
Vertex Name = opname, Post Permissions = unique, Pre-Permissions =none
Vertex Name = size, Post Permissions = unique, Pre-Permissions =none
Vertex Name = time, Post Permissions = unique, Pre-Permissions =none
Vertex Name = calls, Post Permissions = unique, Pre-Permissions =none
Vertex Name = opcount, Post Permissions = unique, Pre-Permissions =none
Vertex Name = on, Post Permissions = unique, Pre-Permissions =none
Vertex Name = start_time, Post Permissions = unique, Pre-Permissions =none
Vertex Name = nbf, Post Permissions = unique, Pre-Permissions =none
Vertex Name = nbf2, Post Permissions = unique, Pre-Permissions =none
Vertex Name = nbf3, Post Permissions = unique, Pre-Permissions =none
Vertex Name = mm, Post Permissions = unique, Pre-Permissions =none
Vertex Name = PARTSIZE, Post Permissions = unique, Pre-Permissions =none
Vertex Name = mdsize, Post Permissions = unique, Pre-Permissions =none
Vertex Name = l, Post Permissions = unique, Pre-Permissions =none
Vertex Name = side, Post Permissions = unique, Pre-Permissions =none
Vertex Name = rcoff, Post Permissions = unique, Pre-Permissions =none
Vertex Name = a, Post Permissions = unique, Pre-Permissions =none
Vertex Name = sideh, Post Permissions = unique, Pre-Permissions =none
Vertex Name = hsq, Post Permissions = unique, Pre-Permissions =none
Vertex Name = hsq2, Post Permissions = unique, Pre-Permissions =none
Vertex Name = npartm, Post Permissions = unique, Pre-Permissions =none
Vertex Name = rcoffs, Post Permissions = unique, Pre-Permissions =none
Vertex Name = tscale, Post Permissions = unique, Pre-Permissions =none
Vertex Name = vaver, Post Permissions = unique, Pre-Permissions =none
Vertex Name = vaverh, Post Permissions = unique, Pre-Permissions =none
Vertex Name = ijk, Post Permissions = unique, Pre-Permissions =none
Vertex Name = lg, Post Permissions = unique, Pre-Permissions =none
Vertex Name = i, Post Permissions = unique, Pre-Permissions =none
Vertex Name = j, Post Permissions = unique, Pre-Permissions =none
Vertex Name = k, Post Permissions = unique, Pre-Permissions =none
Vertex Name = iseed, Post Permissions = unique, Pre-Permissions =none
Vertex Name = v1, Post Permissions = unique, Pre-Permissions =none
Vertex Name = v2, Post Permissions = unique, Pre-Permissions =none
Vertex Name = randnum, Post Permissions = unique, Pre-Permissions =none
Vertex Name = r, Post Permissions = unique, Pre-Permissions =none
Vertex Name = xvelocity, Post Permissions = unique, Pre-Permissions =none
Vertex Name = yvelocity, Post Permissions = unique, Pre-Permissions =none
Vertex Name = zvelocity, Post Permissions = unique, Pre-Permissions =none
Vertex Name = ekin, Post Permissions = unique, Pre-Permissions =none
Vertex Name = sp, Post Permissions = unique, Pre-Permissions =none
Vertex Name = ts, Post Permissions = unique, Pre-Permissions =none
Vertex Name = sc, Post Permissions = unique, Pre-Permissions =none
Vertex Name = xcoord, Post Permissions = unique, Pre-Permissions =none
Vertex Name = ycoord, Post Permissions = unique, Pre-Permissions =none
Vertex Name = zcoord, Post Permissions = unique, Pre-Permissions =none
Vertex Name = xforce, Post Permissions = unique, Pre-Permissions =none
Vertex Name = yforce, Post Permissions = unique, Pre-Permissions =none
Vertex Name = zforce, Post Permissions = unique, Pre-Permissions =none
Vertex Name = move, Post Permissions = unique, Pre-Permissions =none
Vertex Name = sum, Post Permissions = unique, Pre-Permissions =none
Vertex Name = vel, Post Permissions = unique, Pre-Permissions =none
Vertex Name = ek, Post Permissions = unique, Pre-Permissions =none
Vertex Name = etot, Post Permissions = unique, Pre-Permissions =none
Vertex Name = temp, Post Permissions = unique, Pre-Permissions =none
Vertex Name = pres, Post Permissions = unique, Pre-Permissions =none
Vertex Name = rp, Post Permissions = unique, Pre-Permissions =none
Method Name = JGFrun
Vertex Name = interactions, Post Permissions = share, Pre-Permissions =share
Vertex Name = timers, Post Permissions = share, Pre-Permissions =share
Vertex Name = name, Post Permissions = share, Pre-Permissions =share
Vertex Name = opname, Post Permissions = share, Pre-Permissions =share
Vertex Name = size, Post Permissions = share, Pre-Permissions =share
Vertex Name = time, Post Permissions = share, Pre-Permissions =share
Vertex Name = calls, Post Permissions = share, Pre-Permissions =share
Vertex Name = opcount, Post Permissions = share, Pre-Permissions =share
Vertex Name = on, Post Permissions = share, Pre-Permissions =share
Vertex Name = start_time, Post Permissions = share, Pre-Permissions =share
Vertex Name = nbf, Post Permissions = share, Pre-Permissions =share
Vertex Name = nbf2, Post Permissions = share, Pre-Permissions =share
Vertex Name = nbf3, Post Permissions = share, Pre-Permissions =share
Vertex Name = mm, Post Permissions = share, Pre-Permissions =share
Vertex Name = datasizes, Post Permissions = immutable, Pre-Permissions =immutable
Vertex Name = PARTSIZE, Post Permissions = share, Pre-Permissions =share
Vertex Name = mdsize, Post Permissions = share, Pre-Permissions =share
Vertex Name = one, Post Permissions = unique, Pre-Permissions =none
Vertex Name = l, Post Permissions = share, Pre-Permissions =share
Vertex Name = LENGTH, Post Permissions = pure, Pre-Permissions =pure
Vertex Name = side, Post Permissions = share, Pre-Permissions =share
Vertex Name = den, Post Permissions = pure, Pre-Permissions =pure
Vertex Name = rcoff, Post Permissions = share, Pre-Permissions =share
Vertex Name = a, Post Permissions = share, Pre-Permissions =share
Vertex Name = sideh, Post Permissions = share, Pre-Permissions =share
Vertex Name = hsq, Post Permissions = share, Pre-Permissions =share
Vertex Name = h, Post Permissions = pure, Pre-Permissions =pure
Vertex Name = hsq2, Post Permissions = share, Pre-Permissions =share
Vertex Name = npartm, Post Permissions = share, Pre-Permissions =share
Vertex Name = rcoffs, Post Permissions = share, Pre-Permissions =share
Vertex Name = tscale, Post Permissions = share, Pre-Permissions =share
Vertex Name = vaver, Post Permissions = share, Pre-Permissions =share
Vertex Name = tref, Post Permissions = pure, Pre-Permissions =pure
Vertex Name = vaverh, Post Permissions = share, Pre-Permissions =share
Vertex Name = ijk, Post Permissions = unique, Pre-Permissions =none
Vertex Name = lg, Post Permissions = share, Pre-Permissions =share
Vertex Name = i, Post Permissions = share, Pre-Permissions =share
Vertex Name = j, Post Permissions = share, Pre-Permissions =share
Vertex Name = k, Post Permissions = share, Pre-Permissions =share
Vertex Name = iseed, Post Permissions = share, Pre-Permissions =share
Vertex Name = v1, Post Permissions = share, Pre-Permissions =share
Vertex Name = v2, Post Permissions = share, Pre-Permissions =share
Vertex Name = randnum, Post Permissions = unique, Pre-Permissions =none
Vertex Name = r, Post Permissions = share, Pre-Permissions =share
Vertex Name = xvelocity, Post Permissions = share, Pre-Permissions =share
Vertex Name = yvelocity, Post Permissions = share, Pre-Permissions =share
Vertex Name = zvelocity, Post Permissions = share, Pre-Permissions =share
Vertex Name = ekin, Post Permissions = share, Pre-Permissions =share
Vertex Name = sp, Post Permissions = share, Pre-Permissions =share
Vertex Name = ts, Post Permissions = share, Pre-Permissions =share
Vertex Name = sc, Post Permissions = share, Pre-Permissions =share
Vertex Name = xcoord, Post Permissions = share, Pre-Permissions =share
Vertex Name = ycoord, Post Permissions = share, Pre-Permissions =share
Vertex Name = zcoord, Post Permissions = share, Pre-Permissions =share
Vertex Name = xforce, Post Permissions = share, Pre-Permissions =share
Vertex Name = yforce, Post Permissions = share, Pre-Permissions =share
Vertex Name = zforce, Post Permissions = share, Pre-Permissions =share
Vertex Name = move, Post Permissions = share, Pre-Permissions =share
Vertex Name = movemx, Post Permissions = pure, Pre-Permissions =pure
Vertex Name = epot, Post Permissions = share, Pre-Permissions =share
Vertex Name = vir, Post Permissions = share, Pre-Permissions =share
Vertex Name = sum, Post Permissions = share, Pre-Permissions =share
Vertex Name = vel, Post Permissions = share, Pre-Permissions =share
Vertex Name = count, Post Permissions = share, Pre-Permissions =share
Vertex Name = istop, Post Permissions = pure, Pre-Permissions =pure
Vertex Name = irep, Post Permissions = pure, Pre-Permissions =pure
Vertex Name = iprint, Post Permissions = pure, Pre-Permissions =pure
Vertex Name = ek, Post Permissions = share, Pre-Permissions =share
Vertex Name = etot, Post Permissions = share, Pre-Permissions =share
Vertex Name = temp, Post Permissions = share, Pre-Permissions =share
Vertex Name = pres, Post Permissions = share, Pre-Permissions =share
Vertex Name = rp, Post Permissions = share, Pre-Permissions =share
Method Name = addTimer
Vertex Name = timers, Post Permissions = share, Pre-Permissions =share
Vertex Name = name, Post Permissions = share, Pre-Permissions =share
Vertex Name = opname, Post Permissions = share, Pre-Permissions =share
Vertex Name = size, Post Permissions = share, Pre-Permissions =share
Vertex Name = time, Post Permissions = share, Pre-Permissions =share
Vertex Name = calls, Post Permissions = share, Pre-Permissions =share
Vertex Name = opcount, Post Permissions = share, Pre-Permissions =share
Vertex Name = on, Post Permissions = share, Pre-Permissions =share
Method Name = JGFTimer
Vertex Name = name, Post Permissions = share, Pre-Permissions =share
Vertex Name = opname, Post Permissions = share, Pre-Permissions =share
Vertex Name = size, Post Permissions = share, Pre-Permissions =share
Vertex Name = time, Post Permissions = share, Pre-Permissions =share
Vertex Name = calls, Post Permissions = share, Pre-Permissions =share
Vertex Name = opcount, Post Permissions = share, Pre-Permissions =share
Vertex Name = on, Post Permissions = share, Pre-Permissions =share
Method Name = reset
Vertex Name = time, Post Permissions = share, Pre-Permissions =share
Vertex Name = calls, Post Permissions = share, Pre-Permissions =share
Vertex Name = opcount, Post Permissions = share, Pre-Permissions =share
Vertex Name = on, Post Permissions = share, Pre-Permissions =share
Method Name = JGFsetsize
Vertex Name = size, Post Permissions = share, Pre-Permissions =share
Method Name = startTimer
Vertex Name = timers, Post Permissions = share, Pre-Permissions =share
Vertex Name = on, Post Permissions = share, Pre-Permissions =share
Vertex Name = name, Post Permissions = pure, Pre-Permissions =pure
Vertex Name = start_time, Post Permissions = share, Pre-Permissions =share
Method Name = start
Vertex Name = on, Post Permissions = share, Pre-Permissions =share
Vertex Name = name, Post Permissions = pure, Pre-Permissions =pure
Vertex Name = start_time, Post Permissions = share, Pre-Permissions =share
Method Name = JGFinitialise
Vertex Name = nbf, Post Permissions = share, Pre-Permissions =share
Vertex Name = nbf2, Post Permissions = share, Pre-Permissions =share
Vertex Name = nbf3, Post Permissions = share, Pre-Permissions =share
Vertex Name = mm, Post Permissions = share, Pre-Permissions =share
Vertex Name = datasizes, Post Permissions = immutable, Pre-Permissions =immutable
Vertex Name = size, Post Permissions = pure, Pre-Permissions =pure
Vertex Name = PARTSIZE, Post Permissions = share, Pre-Permissions =share
Vertex Name = mdsize, Post Permissions = share, Pre-Permissions =share
Vertex Name = one, Post Permissions = unique, Pre-Permissions =none
Vertex Name = l, Post Permissions = share, Pre-Permissions =share
Vertex Name = LENGTH, Post Permissions = pure, Pre-Permissions =pure
Vertex Name = side, Post Permissions = share, Pre-Permissions =share
Vertex Name = den, Post Permissions = pure, Pre-Permissions =pure
Vertex Name = rcoff, Post Permissions = share, Pre-Permissions =share
Vertex Name = a, Post Permissions = share, Pre-Permissions =share
Vertex Name = sideh, Post Permissions = share, Pre-Permissions =share
Vertex Name = hsq, Post Permissions = share, Pre-Permissions =share
Vertex Name = h, Post Permissions = pure, Pre-Permissions =pure
Vertex Name = hsq2, Post Permissions = share, Pre-Permissions =share
Vertex Name = npartm, Post Permissions = share, Pre-Permissions =share
Vertex Name = rcoffs, Post Permissions = share, Pre-Permissions =share
Vertex Name = tscale, Post Permissions = share, Pre-Permissions =share
Vertex Name = vaver, Post Permissions = share, Pre-Permissions =share
Vertex Name = tref, Post Permissions = pure, Pre-Permissions =pure
Vertex Name = vaverh, Post Permissions = share, Pre-Permissions =share
Vertex Name = ijk, Post Permissions = unique, Pre-Permissions =none
Vertex Name = lg, Post Permissions = share, Pre-Permissions =share
Vertex Name = i, Post Permissions = share, Pre-Permissions =share
Vertex Name = j, Post Permissions = share, Pre-Permissions =share
Vertex Name = k, Post Permissions = share, Pre-Permissions =share
Vertex Name = iseed, Post Permissions = share, Pre-Permissions =share
Vertex Name = v1, Post Permissions = share, Pre-Permissions =share
Vertex Name = v2, Post Permissions = share, Pre-Permissions =share
Vertex Name = randnum, Post Permissions = unique, Pre-Permissions =none
Vertex Name = r, Post Permissions = share, Pre-Permissions =share
Vertex Name = xvelocity, Post Permissions = share, Pre-Permissions =share
Vertex Name = yvelocity, Post Permissions = share, Pre-Permissions =share
Vertex Name = zvelocity, Post Permissions = share, Pre-Permissions =share
Vertex Name = ekin, Post Permissions = share, Pre-Permissions =share
Vertex Name = sp, Post Permissions = share, Pre-Permissions =share
Vertex Name = ts, Post Permissions = share, Pre-Permissions =share
Vertex Name = sc, Post Permissions = share, Pre-Permissions =share
Vertex Name = xcoord, Post Permissions = share, Pre-Permissions =share
Vertex Name = ycoord, Post Permissions = share, Pre-Permissions =share
Vertex Name = zcoord, Post Permissions = share, Pre-Permissions =share
Vertex Name = xforce, Post Permissions = share, Pre-Permissions =share
Vertex Name = yforce, Post Permissions = share, Pre-Permissions =share
Vertex Name = zforce, Post Permissions = share, Pre-Permissions =share
Method Name = JGFapplication
Vertex Name = timers, Post Permissions = share, Pre-Permissions =share
Vertex Name = on, Post Permissions = share, Pre-Permissions =share
Vertex Name = name, Post Permissions = pure, Pre-Permissions =pure
Vertex Name = start_time, Post Permissions = share, Pre-Permissions =share
Vertex Name = move, Post Permissions = share, Pre-Permissions =share
Vertex Name = movemx, Post Permissions = pure, Pre-Permissions =pure
Vertex Name = i, Post Permissions = share, Pre-Permissions =share
Vertex Name = mdsize, Post Permissions = pure, Pre-Permissions =pure
Vertex Name = one, Post Permissions = share, Pre-Permissions =share
Vertex Name = side, Post Permissions = pure, Pre-Permissions =pure
Vertex Name = epot, Post Permissions = share, Pre-Permissions =share
Vertex Name = vir, Post Permissions = share, Pre-Permissions =share
Vertex Name = rcoff, Post Permissions = pure, Pre-Permissions =pure
Vertex Name = sum, Post Permissions = share, Pre-Permissions =share
Vertex Name = hsq2, Post Permissions = pure, Pre-Permissions =pure
Vertex Name = ekin, Post Permissions = share, Pre-Permissions =share
Vertex Name = hsq, Post Permissions = pure, Pre-Permissions =pure
Vertex Name = vel, Post Permissions = share, Pre-Permissions =share
Vertex Name = count, Post Permissions = share, Pre-Permissions =share
Vertex Name = vaverh, Post Permissions = pure, Pre-Permissions =pure
Vertex Name = h, Post Permissions = pure, Pre-Permissions =pure
Vertex Name = istop, Post Permissions = pure, Pre-Permissions =pure
Vertex Name = irep, Post Permissions = pure, Pre-Permissions =pure
Vertex Name = sc, Post Permissions = share, Pre-Permissions =share
Vertex Name = tref, Post Permissions = pure, Pre-Permissions =pure
Vertex Name = tscale, Post Permissions = share, Pre-Permissions =share
Vertex Name = iprint, Post Permissions = pure, Pre-Permissions =pure
Vertex Name = ek, Post Permissions = share, Pre-Permissions =share
Vertex Name = etot, Post Permissions = share, Pre-Permissions =share
Vertex Name = temp, Post Permissions = share, Pre-Permissions =share
Vertex Name = pres, Post Permissions = share, Pre-Permissions =share
Vertex Name = den, Post Permissions = pure, Pre-Permissions =pure
Vertex Name = rp, Post Permissions = share, Pre-Permissions =share
Vertex Name = xcoord, Post Permissions = share, Pre-Permissions =share
Vertex Name = xvelocity, Post Permissions = share, Pre-Permissions =share
Vertex Name = xforce, Post Permissions = share, Pre-Permissions =share
Vertex Name = ycoord, Post Permissions = share, Pre-Permissions =share
Vertex Name = yvelocity, Post Permissions = share, Pre-Permissions =share
Vertex Name = yforce, Post Permissions = share, Pre-Permissions =share
Vertex Name = zcoord, Post Permissions = share, Pre-Permissions =share
Vertex Name = zvelocity, Post Permissions = share, Pre-Permissions =share
Vertex Name = zforce, Post Permissions = share, Pre-Permissions =share
Vertex Name = interactions, Post Permissions = share, Pre-Permissions =share
Vertex Name = time, Post Permissions = share, Pre-Permissions =share
Vertex Name = calls, Post Permissions = share, Pre-Permissions =share
Method Name = stopTimer
Vertex Name = timers, Post Permissions = share, Pre-Permissions =share
Vertex Name = time, Post Permissions = share, Pre-Permissions =share
Vertex Name = start_time, Post Permissions = pure, Pre-Permissions =pure
Vertex Name = on, Post Permissions = share, Pre-Permissions =share
Vertex Name = name, Post Permissions = pure, Pre-Permissions =pure
Vertex Name = calls, Post Permissions = share, Pre-Permissions =share
Method Name = stop
Vertex Name = time, Post Permissions = share, Pre-Permissions =share
Vertex Name = start_time, Post Permissions = pure, Pre-Permissions =pure
Vertex Name = on, Post Permissions = share, Pre-Permissions =share
Vertex Name = name, Post Permissions = pure, Pre-Permissions =pure
Vertex Name = calls, Post Permissions = share, Pre-Permissions =share
Method Name = JGFvalidate
Vertex Name = ek, Post Permissions = pure, Pre-Permissions =pure
Vertex Name = size, Post Permissions = pure, Pre-Permissions =pure
Method Name = JGFtidyup
Vertex Name = one, Post Permissions = unique, Pre-Permissions =none
Method Name = addOpsToTimer
Vertex Name = timers, Post Permissions = share, Pre-Permissions =share
Vertex Name = opcount, Post Permissions = share, Pre-Permissions =share
Method Name = addops
Vertex Name = opcount, Post Permissions = share, Pre-Permissions =share
Method Name = printTimer
Vertex Name = timers, Post Permissions = share, Pre-Permissions =share
Vertex Name = opname, Post Permissions = share, Pre-Permissions =share
Vertex Name = name, Post Permissions = pure, Pre-Permissions =pure
Vertex Name = time, Post Permissions = pure, Pre-Permissions =pure
Vertex Name = size, Post Permissions = pure, Pre-Permissions =pure
Vertex Name = opcount, Post Permissions = pure, Pre-Permissions =pure
Method Name = print
Vertex Name = opname, Post Permissions = share, Pre-Permissions =share
Vertex Name = name, Post Permissions = pure, Pre-Permissions =pure
Vertex Name = time, Post Permissions = pure, Pre-Permissions =pure
Vertex Name = size, Post Permissions = pure, Pre-Permissions =pure
Vertex Name = opcount, Post Permissions = pure, Pre-Permissions =pure
Method Name = perf
Vertex Name = opcount, Post Permissions = pure, Pre-Permissions =pure
Vertex Name = time, Post Permissions = pure, Pre-Permissions =pure
Class Name = JGFMolDynBenchSizeA
Class Name = JGFMolDynBenchSizeA
Method Name = main
Vertex Name = ITERS, Post Permissions = unique, Pre-Permissions =none
Vertex Name = LENGTH, Post Permissions = unique, Pre-Permissions =none
Vertex Name = m, Post Permissions = unique, Pre-Permissions =none
Vertex Name = mu, Post Permissions = unique, Pre-Permissions =none
Vertex Name = kb, Post Permissions = unique, Pre-Permissions =none
Vertex Name = TSIM, Post Permissions = unique, Pre-Permissions =none
Vertex Name = deltat, Post Permissions = unique, Pre-Permissions =none
Vertex Name = one, Post Permissions = unique, Pre-Permissions =none
Vertex Name = epot, Post Permissions = unique, Pre-Permissions =none
Vertex Name = vir, Post Permissions = unique, Pre-Permissions =none
Vertex Name = count, Post Permissions = unique, Pre-Permissions =none
Vertex Name = datasizes, Post Permissions = unique, Pre-Permissions =none
Vertex Name = interactions, Post Permissions = unique, Pre-Permissions =none
Vertex Name = den, Post Permissions = unique, Pre-Permissions =none
Vertex Name = tref, Post Permissions = unique, Pre-Permissions =none
Vertex Name = h, Post Permissions = unique, Pre-Permissions =none
Vertex Name = irep, Post Permissions = unique, Pre-Permissions =none
Vertex Name = istop, Post Permissions = unique, Pre-Permissions =none
Vertex Name = iprint, Post Permissions = unique, Pre-Permissions =none
Vertex Name = movemx, Post Permissions = unique, Pre-Permissions =none
Vertex Name = timers, Post Permissions = unique, Pre-Permissions =none
Vertex Name = name, Post Permissions = unique, Pre-Permissions =none
Vertex Name = opname, Post Permissions = unique, Pre-Permissions =none
Vertex Name = size, Post Permissions = unique, Pre-Permissions =none
Vertex Name = time, Post Permissions = unique, Pre-Permissions =none
Vertex Name = calls, Post Permissions = unique, Pre-Permissions =none
Vertex Name = opcount, Post Permissions = unique, Pre-Permissions =none
Vertex Name = on, Post Permissions = unique, Pre-Permissions =none
Vertex Name = start_time, Post Permissions = unique, Pre-Permissions =none
Vertex Name = nbf, Post Permissions = unique, Pre-Permissions =none
Vertex Name = nbf2, Post Permissions = unique, Pre-Permissions =none
Vertex Name = nbf3, Post Permissions = unique, Pre-Permissions =none
Vertex Name = mm, Post Permissions = unique, Pre-Permissions =none
Vertex Name = PARTSIZE, Post Permissions = unique, Pre-Permissions =none
Vertex Name = mdsize, Post Permissions = unique, Pre-Permissions =none
Vertex Name = l, Post Permissions = unique, Pre-Permissions =none
Vertex Name = side, Post Permissions = unique, Pre-Permissions =none
Vertex Name = rcoff, Post Permissions = unique, Pre-Permissions =none
Vertex Name = a, Post Permissions = unique, Pre-Permissions =none
Vertex Name = sideh, Post Permissions = unique, Pre-Permissions =none
Vertex Name = hsq, Post Permissions = unique, Pre-Permissions =none
Vertex Name = hsq2, Post Permissions = unique, Pre-Permissions =none
Vertex Name = npartm, Post Permissions = unique, Pre-Permissions =none
Vertex Name = rcoffs, Post Permissions = unique, Pre-Permissions =none
Vertex Name = tscale, Post Permissions = unique, Pre-Permissions =none
Vertex Name = vaver, Post Permissions = unique, Pre-Permissions =none
Vertex Name = vaverh, Post Permissions = unique, Pre-Permissions =none
Vertex Name = ijk, Post Permissions = unique, Pre-Permissions =none
Vertex Name = lg, Post Permissions = unique, Pre-Permissions =none
Vertex Name = i, Post Permissions = unique, Pre-Permissions =none
Vertex Name = j, Post Permissions = unique, Pre-Permissions =none
Vertex Name = k, Post Permissions = unique, Pre-Permissions =none
Vertex Name = iseed, Post Permissions = unique, Pre-Permissions =none
Vertex Name = v1, Post Permissions = unique, Pre-Permissions =none
Vertex Name = v2, Post Permissions = unique, Pre-Permissions =none
Vertex Name = randnum, Post Permissions = unique, Pre-Permissions =none
Vertex Name = r, Post Permissions = unique, Pre-Permissions =none
Vertex Name = xvelocity, Post Permissions = unique, Pre-Permissions =none
Vertex Name = yvelocity, Post Permissions = unique, Pre-Permissions =none
Vertex Name = zvelocity, Post Permissions = unique, Pre-Permissions =none
Vertex Name = ekin, Post Permissions = unique, Pre-Permissions =none
Vertex Name = sp, Post Permissions = unique, Pre-Permissions =none
Vertex Name = ts, Post Permissions = unique, Pre-Permissions =none
Vertex Name = sc, Post Permissions = unique, Pre-Permissions =none
Vertex Name = xcoord, Post Permissions = unique, Pre-Permissions =none
Vertex Name = ycoord, Post Permissions = unique, Pre-Permissions =none
Vertex Name = zcoord, Post Permissions = unique, Pre-Permissions =none
Vertex Name = xforce, Post Permissions = unique, Pre-Permissions =none
Vertex Name = yforce, Post Permissions = unique, Pre-Permissions =none
Vertex Name = zforce, Post Permissions = unique, Pre-Permissions =none
Vertex Name = move, Post Permissions = unique, Pre-Permissions =none
Vertex Name = sum, Post Permissions = unique, Pre-Permissions =none
Vertex Name = vel, Post Permissions = unique, Pre-Permissions =none
Vertex Name = ek, Post Permissions = unique, Pre-Permissions =none
Vertex Name = etot, Post Permissions = unique, Pre-Permissions =none
Vertex Name = temp, Post Permissions = unique, Pre-Permissions =none
Vertex Name = pres, Post Permissions = unique, Pre-Permissions =none
Vertex Name = rp, Post Permissions = unique, Pre-Permissions =none
Method Name = printHeader
/////////////////////////////////////////////////////////////////////////////
 * 
 * Class Name = md
Class Name = JGFMolDynBenchSizeA
Method Name = initialise
Vertex Name = mold.nbf, Post Permissions = share, Pre-Permissions =share
Vertex Name = mold.nbf2, Post Permissions = share, Pre-Permissions =share
Vertex Name = mold.nbf3, Post Permissions = share, Pre-Permissions =share
Vertex Name = mold.mm, Post Permissions = share, Pre-Permissions =share
Vertex Name = mold.datasizes, Post Permissions = immutable, Pre-Permissions =immutable
Vertex Name = mold.size, Post Permissions = pure, Pre-Permissions =pure
Vertex Name = mold.PARTSIZE, Post Permissions = share, Pre-Permissions =share
Vertex Name = mold.mdsize, Post Permissions = share, Pre-Permissions =share
Vertex Name = mold.one, Post Permissions = unique, Pre-Permissions =none
Vertex Name = mold.l, Post Permissions = share, Pre-Permissions =share
Vertex Name = mold.LENGTH, Post Permissions = immutable, Pre-Permissions =immutable
Vertex Name = mold.side, Post Permissions = share, Pre-Permissions =share
Vertex Name = mold.den, Post Permissions = immutable, Pre-Permissions =immutable
Vertex Name = mold.rcoff, Post Permissions = share, Pre-Permissions =share
Vertex Name = mold.a, Post Permissions = share, Pre-Permissions =share
Vertex Name = mold.sideh, Post Permissions = share, Pre-Permissions =share
Vertex Name = mold.hsq, Post Permissions = share, Pre-Permissions =share
Vertex Name = mold.h, Post Permissions = immutable, Pre-Permissions =immutable
Vertex Name = mold.hsq2, Post Permissions = share, Pre-Permissions =share
Vertex Name = mold.npartm, Post Permissions = share, Pre-Permissions =share
Vertex Name = mold.rcoffs, Post Permissions = share, Pre-Permissions =share
Vertex Name = mold.tscale, Post Permissions = share, Pre-Permissions =share
Vertex Name = mold.vaver, Post Permissions = share, Pre-Permissions =share
Vertex Name = mold.tref, Post Permissions = immutable, Pre-Permissions =immutable
Vertex Name = mold.vaverh, Post Permissions = share, Pre-Permissions =share
Vertex Name = mold.ijk, Post Permissions = unique, Pre-Permissions =none
Vertex Name = mold.lg, Post Permissions = share, Pre-Permissions =share
Vertex Name = mold.i, Post Permissions = share, Pre-Permissions =share
Vertex Name = mold.j, Post Permissions = share, Pre-Permissions =share
Vertex Name = mold.k, Post Permissions = share, Pre-Permissions =share
Vertex Name = mold.one.one, Post Permissions = unique, Pre-Permissions =none
Vertex Name = mold.iseed, Post Permissions = share, Pre-Permissions =share
Vertex Name = mold.v1, Post Permissions = share, Pre-Permissions =share
Vertex Name = mold.v2, Post Permissions = share, Pre-Permissions =share
Vertex Name = mold.randnum, Post Permissions = unique, Pre-Permissions =none
Vertex Name = mold.randnum.randnum, Post Permissions = unique, Pre-Permissions =none
Vertex Name = mold.r, Post Permissions = share, Pre-Permissions =share
Vertex Name = particle.xvelocity, Post Permissions = share, Pre-Permissions =share
Vertex Name = mold.xvelocity, Post Permissions = unique, Pre-Permissions =none
Vertex Name = particle.yvelocity, Post Permissions = share, Pre-Permissions =share
Vertex Name = mold.yvelocity, Post Permissions = unique, Pre-Permissions =none
Vertex Name = particle.zvelocity, Post Permissions = share, Pre-Permissions =share
Vertex Name = mold.zvelocity, Post Permissions = unique, Pre-Permissions =none
Vertex Name = mold.ekin, Post Permissions = share, Pre-Permissions =share
Vertex Name = mold.sp, Post Permissions = share, Pre-Permissions =share
Vertex Name = mold.ts, Post Permissions = share, Pre-Permissions =share
Vertex Name = mold.sc, Post Permissions = share, Pre-Permissions =share
Vertex Name = mold.one.xcoord, Post Permissions = unique, Pre-Permissions =none
Vertex Name = mold.one.ycoord, Post Permissions = unique, Pre-Permissions =none
Vertex Name = mold.one.zcoord, Post Permissions = unique, Pre-Permissions =none
Vertex Name = mold.one.xvelocity, Post Permissions = unique, Pre-Permissions =none
Vertex Name = mold.one.yvelocity, Post Permissions = unique, Pre-Permissions =none
Vertex Name = mold.one.zvelocity, Post Permissions = unique, Pre-Permissions =none
Vertex Name = mold.one.xforce, Post Permissions = unique, Pre-Permissions =none
Vertex Name = mold.one.yforce, Post Permissions = unique, Pre-Permissions =none
Vertex Name = mold.one.zforce, Post Permissions = unique, Pre-Permissions =none
Vertex Name = mold.xcoord, Post Permissions = unique, Pre-Permissions =none
Vertex Name = mold.ycoord, Post Permissions = unique, Pre-Permissions =none
Vertex Name = mold.zcoord, Post Permissions = unique, Pre-Permissions =none
Vertex Name = mold.xforce, Post Permissions = unique, Pre-Permissions =none
Vertex Name = mold.yforce, Post Permissions = unique, Pre-Permissions =none
Vertex Name = mold.zforce, Post Permissions = unique, Pre-Permissions =none
Vertex Name = mold.randnum.iseed, Post Permissions = unique, Pre-Permissions =none
Vertex Name = mold.randnum.v1, Post Permissions = unique, Pre-Permissions =none
Vertex Name = mold.randnum.v2, Post Permissions = unique, Pre-Permissions =none
Vertex Name = randnum.v1, Post Permissions = share, Pre-Permissions =share
Vertex Name = randnum.v2, Post Permissions = share, Pre-Permissions =share
Vertex Name = randnum.iseed, Post Permissions = share, Pre-Permissions =share
Method Name = particle
Vertex Name = mold.one.xcoord, Post Permissions = unique, Pre-Permissions =none
Vertex Name = mold.one.ycoord, Post Permissions = unique, Pre-Permissions =none
Vertex Name = mold.one.zcoord, Post Permissions = unique, Pre-Permissions =none
Vertex Name = mold.one.xvelocity, Post Permissions = unique, Pre-Permissions =none
Vertex Name = mold.one.yvelocity, Post Permissions = unique, Pre-Permissions =none
Vertex Name = mold.one.zvelocity, Post Permissions = unique, Pre-Permissions =none
Vertex Name = mold.one.xforce, Post Permissions = unique, Pre-Permissions =none
Vertex Name = mold.one.yforce, Post Permissions = unique, Pre-Permissions =none
Vertex Name = mold.one.zforce, Post Permissions = unique, Pre-Permissions =none
Vertex Name = mold.xcoord, Post Permissions = unique, Pre-Permissions =none
Vertex Name = mold.ycoord, Post Permissions = unique, Pre-Permissions =none
Vertex Name = mold.zcoord, Post Permissions = unique, Pre-Permissions =none
Vertex Name = mold.xvelocity, Post Permissions = unique, Pre-Permissions =none
Vertex Name = mold.yvelocity, Post Permissions = unique, Pre-Permissions =none
Vertex Name = mold.zvelocity, Post Permissions = unique, Pre-Permissions =none
Vertex Name = mold.xforce, Post Permissions = unique, Pre-Permissions =none
Vertex Name = mold.yforce, Post Permissions = unique, Pre-Permissions =none
Vertex Name = mold.zforce, Post Permissions = unique, Pre-Permissions =none
Method Name = random
Vertex Name = mold.iseed, Post Permissions = pure, Pre-Permissions =pure
Vertex Name = mold.v1, Post Permissions = pure, Pre-Permissions =pure
Vertex Name = mold.v2, Post Permissions = pure, Pre-Permissions =pure
Vertex Name = mold.randnum.iseed, Post Permissions = unique, Pre-Permissions =none
Vertex Name = mold.randnum.v1, Post Permissions = unique, Pre-Permissions =none
Vertex Name = mold.randnum.v2, Post Permissions = unique, Pre-Permissions =none
Method Name = seed
Vertex Name = randnum.v1, Post Permissions = share, Pre-Permissions =share
Vertex Name = randnum.v2, Post Permissions = share, Pre-Permissions =share
Vertex Name = randnum.iseed, Post Permissions = share, Pre-Permissions =share
Method Name = update
Vertex Name = randnum.iseed, Post Permissions = share, Pre-Permissions =share
Method Name = runiters
Vertex Name = mold.move, Post Permissions = share, Pre-Permissions =share
Vertex Name = mold.movemx, Post Permissions = immutable, Pre-Permissions =immutable
Vertex Name = mold.i, Post Permissions = share, Pre-Permissions =share
Vertex Name = mold.mdsize, Post Permissions = pure, Pre-Permissions =pure
Vertex Name = mold.one, Post Permissions = pure, Pre-Permissions =pure
Vertex Name = mold.side, Post Permissions = pure, Pre-Permissions =pure
Vertex Name = mold.epot, Post Permissions = share, Pre-Permissions =share
Vertex Name = mold.vir, Post Permissions = share, Pre-Permissions =share
Vertex Name = mold.rcoff, Post Permissions = pure, Pre-Permissions =pure
Vertex Name = mold.sum, Post Permissions = share, Pre-Permissions =share
Vertex Name = mold.hsq2, Post Permissions = pure, Pre-Permissions =pure
Vertex Name = mold.ekin, Post Permissions = share, Pre-Permissions =share
Vertex Name = mold.hsq, Post Permissions = pure, Pre-Permissions =pure
Vertex Name = mold.vel, Post Permissions = share, Pre-Permissions =share
Vertex Name = mold.count, Post Permissions = share, Pre-Permissions =share
Vertex Name = mold.vaverh, Post Permissions = pure, Pre-Permissions =pure
Vertex Name = mold.h, Post Permissions = immutable, Pre-Permissions =immutable
Vertex Name = mold.istop, Post Permissions = immutable, Pre-Permissions =immutable
Vertex Name = mold.irep, Post Permissions = immutable, Pre-Permissions =immutable
Vertex Name = mold.sc, Post Permissions = share, Pre-Permissions =share
Vertex Name = mold.tref, Post Permissions = immutable, Pre-Permissions =immutable
Vertex Name = mold.tscale, Post Permissions = pure, Pre-Permissions =pure
Vertex Name = mold.iprint, Post Permissions = immutable, Pre-Permissions =immutable
Vertex Name = mold.ek, Post Permissions = share, Pre-Permissions =share
Vertex Name = mold.etot, Post Permissions = share, Pre-Permissions =share
Vertex Name = mold.temp, Post Permissions = share, Pre-Permissions =share
Vertex Name = mold.pres, Post Permissions = share, Pre-Permissions =share
Vertex Name = mold.den, Post Permissions = immutable, Pre-Permissions =immutable
Vertex Name = mold.rp, Post Permissions = share, Pre-Permissions =share
Vertex Name = one.xcoord, Post Permissions = share, Pre-Permissions =share
Vertex Name = one.xvelocity, Post Permissions = share, Pre-Permissions =share
Vertex Name = one.xforce, Post Permissions = share, Pre-Permissions =share
Vertex Name = one.ycoord, Post Permissions = share, Pre-Permissions =share
Vertex Name = one.yvelocity, Post Permissions = share, Pre-Permissions =share
Vertex Name = one.yforce, Post Permissions = share, Pre-Permissions =share
Vertex Name = one.zcoord, Post Permissions = share, Pre-Permissions =share
Vertex Name = one.zvelocity, Post Permissions = share, Pre-Permissions =share
Vertex Name = one.zforce, Post Permissions = share, Pre-Permissions =share
Vertex Name = particle.xcoord, Post Permissions = pure, Pre-Permissions =pure
Vertex Name = one.one, Post Permissions = pure, Pre-Permissions =pure
Vertex Name = particle.ycoord, Post Permissions = pure, Pre-Permissions =pure
Vertex Name = particle.zcoord, Post Permissions = pure, Pre-Permissions =pure
Vertex Name = one.epot, Post Permissions = share, Pre-Permissions =share
Vertex Name = one.vir, Post Permissions = share, Pre-Permissions =share
Vertex Name = particle.xforce, Post Permissions = share, Pre-Permissions =share
Vertex Name = particle.yforce, Post Permissions = share, Pre-Permissions =share
Vertex Name = particle.zforce, Post Permissions = share, Pre-Permissions =share
Vertex Name = one.interactions, Post Permissions = share, Pre-Permissions =share
Vertex Name = one.count, Post Permissions = share, Pre-Permissions =share
Method Name = domove
Vertex Name = mold.side, Post Permissions = pure, Pre-Permissions =pure
Vertex Name = one.xcoord, Post Permissions = share, Pre-Permissions =share
Vertex Name = one.xvelocity, Post Permissions = share, Pre-Permissions =share
Vertex Name = one.xforce, Post Permissions = share, Pre-Permissions =share
Vertex Name = one.ycoord, Post Permissions = share, Pre-Permissions =share
Vertex Name = one.yvelocity, Post Permissions = share, Pre-Permissions =share
Vertex Name = one.yforce, Post Permissions = share, Pre-Permissions =share
Vertex Name = one.zcoord, Post Permissions = share, Pre-Permissions =share
Vertex Name = one.zvelocity, Post Permissions = share, Pre-Permissions =share
Vertex Name = one.zforce, Post Permissions = share, Pre-Permissions =share
Method Name = force
Vertex Name = mold.side, Post Permissions = pure, Pre-Permissions =pure
Vertex Name = mold.rcoff, Post Permissions = pure, Pre-Permissions =pure
Vertex Name = mold.mdsize, Post Permissions = pure, Pre-Permissions =pure
Vertex Name = mold.i, Post Permissions = pure, Pre-Permissions =pure
Vertex Name = one.xcoord, Post Permissions = pure, Pre-Permissions =pure
Vertex Name = one.ycoord, Post Permissions = pure, Pre-Permissions =pure
Vertex Name = one.zcoord, Post Permissions = pure, Pre-Permissions =pure
Vertex Name = particle.xcoord, Post Permissions = pure, Pre-Permissions =pure
Vertex Name = one.one, Post Permissions = pure, Pre-Permissions =pure
Vertex Name = particle.ycoord, Post Permissions = pure, Pre-Permissions =pure
Vertex Name = particle.zcoord, Post Permissions = pure, Pre-Permissions =pure
Vertex Name = one.epot, Post Permissions = share, Pre-Permissions =share
Vertex Name = one.vir, Post Permissions = share, Pre-Permissions =share
Vertex Name = particle.xforce, Post Permissions = share, Pre-Permissions =share
Vertex Name = one.xforce, Post Permissions = share, Pre-Permissions =share
Vertex Name = particle.yforce, Post Permissions = share, Pre-Permissions =share
Vertex Name = one.yforce, Post Permissions = share, Pre-Permissions =share
Vertex Name = particle.zforce, Post Permissions = share, Pre-Permissions =share
Vertex Name = one.zforce, Post Permissions = share, Pre-Permissions =share
Vertex Name = one.interactions, Post Permissions = share, Pre-Permissions =share
Method Name = mkekin
Vertex Name = mold.hsq2, Post Permissions = pure, Pre-Permissions =pure
Vertex Name = one.xforce, Post Permissions = share, Pre-Permissions =share
Vertex Name = one.yforce, Post Permissions = share, Pre-Permissions =share
Vertex Name = one.zforce, Post Permissions = share, Pre-Permissions =share
Vertex Name = one.xvelocity, Post Permissions = share, Pre-Permissions =share
Vertex Name = one.yvelocity, Post Permissions = share, Pre-Permissions =share
Vertex Name = one.zvelocity, Post Permissions = share, Pre-Permissions =share
Method Name = velavg
Vertex Name = mold.vaverh, Post Permissions = pure, Pre-Permissions =pure
Vertex Name = mold.h, Post Permissions = immutable, Pre-Permissions =immutable
Vertex Name = one.xvelocity, Post Permissions = pure, Pre-Permissions =pure
Vertex Name = one.yvelocity, Post Permissions = pure, Pre-Permissions =pure
Vertex Name = one.zvelocity, Post Permissions = pure, Pre-Permissions =pure
Vertex Name = one.count, Post Permissions = share, Pre-Permissions =share
Method Name = dscal
Vertex Name = mold.sc, Post Permissions = pure, Pre-Permissions =pure
Vertex Name = one.xvelocity, Post Permissions = share, Pre-Permissions =share
Vertex Name = one.yvelocity, Post Permissions = share, Pre-Permissions =share
Vertex Name = one.zvelocity, Post Permissions = share, Pre-Permissions =share
Class Name = particle
Class Name = JGFMolDynBenchSizeA
Method Name = main
Vertex Name = md.ITERS, Post Permissions = unique, Pre-Permissions =none
Vertex Name = md.LENGTH, Post Permissions = unique, Pre-Permissions =none
Vertex Name = md.m, Post Permissions = unique, Pre-Permissions =none
Vertex Name = md.mu, Post Permissions = unique, Pre-Permissions =none
Vertex Name = md.kb, Post Permissions = unique, Pre-Permissions =none
Vertex Name = md.TSIM, Post Permissions = unique, Pre-Permissions =none
Vertex Name = md.deltat, Post Permissions = unique, Pre-Permissions =none
Vertex Name = md.one, Post Permissions = unique, Pre-Permissions =none
Vertex Name = md.epot, Post Permissions = unique, Pre-Permissions =none
Vertex Name = md.vir, Post Permissions = unique, Pre-Permissions =none
Vertex Name = md.count, Post Permissions = unique, Pre-Permissions =none
Vertex Name = md.datasizes, Post Permissions = unique, Pre-Permissions =none
Vertex Name = md.interactions, Post Permissions = unique, Pre-Permissions =none
Vertex Name = md.den, Post Permissions = unique, Pre-Permissions =none
Vertex Name = md.tref, Post Permissions = unique, Pre-Permissions =none
Vertex Name = md.h, Post Permissions = unique, Pre-Permissions =none
Vertex Name = md.irep, Post Permissions = unique, Pre-Permissions =none
Vertex Name = md.istop, Post Permissions = unique, Pre-Permissions =none
Vertex Name = md.iprint, Post Permissions = unique, Pre-Permissions =none
Vertex Name = md.movemx, Post Permissions = unique, Pre-Permissions =none
Vertex Name = mold.interactions, Post Permissions = none, Pre-Permissions =none
Vertex Name = JGFInstrumentor.timers, Post Permissions = unique, Pre-Permissions =none
Vertex Name = JGFTimer.name, Post Permissions = unique, Pre-Permissions =none
Vertex Name = JGFTimer.opname, Post Permissions = unique, Pre-Permissions =none
Vertex Name = JGFTimer.size, Post Permissions = unique, Pre-Permissions =none
Vertex Name = JGFTimer.time, Post Permissions = unique, Pre-Permissions =none
Vertex Name = JGFTimer.calls, Post Permissions = unique, Pre-Permissions =none
Vertex Name = JGFTimer.opcount, Post Permissions = unique, Pre-Permissions =none
Vertex Name = JGFTimer.on, Post Permissions = unique, Pre-Permissions =none
Vertex Name = mold.size, Post Permissions = unique, Pre-Permissions =none
Vertex Name = JGFTimer.start_time, Post Permissions = unique, Pre-Permissions =none
Vertex Name = mold.nbf, Post Permissions = unique, Pre-Permissions =none
Vertex Name = mold.nbf2, Post Permissions = unique, Pre-Permissions =none
Vertex Name = mold.nbf3, Post Permissions = unique, Pre-Permissions =none
Vertex Name = mold.mm, Post Permissions = unique, Pre-Permissions =none
Vertex Name = mold.datasizes, Post Permissions = none, Pre-Permissions =none
Vertex Name = mold.PARTSIZE, Post Permissions = unique, Pre-Permissions =none
Vertex Name = mold.mdsize, Post Permissions = unique, Pre-Permissions =none
Vertex Name = mold.one, Post Permissions = unique, Pre-Permissions =none
Vertex Name = mold.l, Post Permissions = unique, Pre-Permissions =none
Vertex Name = mold.LENGTH, Post Permissions = none, Pre-Permissions =none
Vertex Name = mold.side, Post Permissions = unique, Pre-Permissions =none
Vertex Name = mold.den, Post Permissions = none, Pre-Permissions =none
Vertex Name = mold.rcoff, Post Permissions = unique, Pre-Permissions =none
Vertex Name = mold.a, Post Permissions = unique, Pre-Permissions =none
Vertex Name = mold.sideh, Post Permissions = unique, Pre-Permissions =none
Vertex Name = mold.hsq, Post Permissions = unique, Pre-Permissions =none
Vertex Name = mold.h, Post Permissions = none, Pre-Permissions =none
Vertex Name = mold.hsq2, Post Permissions = unique, Pre-Permissions =none
Vertex Name = mold.npartm, Post Permissions = unique, Pre-Permissions =none
Vertex Name = mold.rcoffs, Post Permissions = unique, Pre-Permissions =none
Vertex Name = mold.tscale, Post Permissions = unique, Pre-Permissions =none
Vertex Name = mold.vaver, Post Permissions = unique, Pre-Permissions =none
Vertex Name = mold.tref, Post Permissions = none, Pre-Permissions =none
Vertex Name = mold.vaverh, Post Permissions = unique, Pre-Permissions =none
Vertex Name = mold.ijk, Post Permissions = unique, Pre-Permissions =none
Vertex Name = mold.lg, Post Permissions = unique, Pre-Permissions =none
Vertex Name = mold.i, Post Permissions = unique, Pre-Permissions =none
Vertex Name = mold.j, Post Permissions = unique, Pre-Permissions =none
Vertex Name = mold.k, Post Permissions = unique, Pre-Permissions =none
Vertex Name = mold.one.one, Post Permissions = unique, Pre-Permissions =none
Vertex Name = mold.iseed, Post Permissions = unique, Pre-Permissions =none
Vertex Name = mold.v1, Post Permissions = unique, Pre-Permissions =none
Vertex Name = mold.v2, Post Permissions = unique, Pre-Permissions =none
Vertex Name = mold.randnum, Post Permissions = unique, Pre-Permissions =none
Vertex Name = mold.randnum.randnum, Post Permissions = unique, Pre-Permissions =none
Vertex Name = mold.r, Post Permissions = unique, Pre-Permissions =none
Vertex Name = particle.xvelocity, Post Permissions = unique, Pre-Permissions =none
Vertex Name = mold.xvelocity, Post Permissions = unique, Pre-Permissions =none
Vertex Name = particle.yvelocity, Post Permissions = unique, Pre-Permissions =none
Vertex Name = mold.yvelocity, Post Permissions = unique, Pre-Permissions =none
Vertex Name = particle.zvelocity, Post Permissions = unique, Pre-Permissions =none
Vertex Name = mold.zvelocity, Post Permissions = unique, Pre-Permissions =none
Vertex Name = mold.ekin, Post Permissions = unique, Pre-Permissions =none
Vertex Name = mold.sp, Post Permissions = unique, Pre-Permissions =none
Vertex Name = mold.ts, Post Permissions = unique, Pre-Permissions =none
Vertex Name = mold.sc, Post Permissions = unique, Pre-Permissions =none
Vertex Name = mold.one.xcoord, Post Permissions = unique, Pre-Permissions =none
Vertex Name = mold.one.ycoord, Post Permissions = unique, Pre-Permissions =none
Vertex Name = mold.one.zcoord, Post Permissions = unique, Pre-Permissions =none
Vertex Name = mold.one.xvelocity, Post Permissions = unique, Pre-Permissions =none
Vertex Name = mold.one.yvelocity, Post Permissions = unique, Pre-Permissions =none
Vertex Name = mold.one.zvelocity, Post Permissions = unique, Pre-Permissions =none
Vertex Name = mold.one.xforce, Post Permissions = unique, Pre-Permissions =none
Vertex Name = mold.one.yforce, Post Permissions = unique, Pre-Permissions =none
Vertex Name = mold.one.zforce, Post Permissions = unique, Pre-Permissions =none
Vertex Name = mold.xcoord, Post Permissions = unique, Pre-Permissions =none
Vertex Name = mold.ycoord, Post Permissions = unique, Pre-Permissions =none
Vertex Name = mold.zcoord, Post Permissions = unique, Pre-Permissions =none
Vertex Name = mold.xforce, Post Permissions = unique, Pre-Permissions =none
Vertex Name = mold.yforce, Post Permissions = unique, Pre-Permissions =none
Vertex Name = mold.zforce, Post Permissions = unique, Pre-Permissions =none
Vertex Name = mold.randnum.iseed, Post Permissions = unique, Pre-Permissions =none
Vertex Name = mold.randnum.v1, Post Permissions = unique, Pre-Permissions =none
Vertex Name = mold.randnum.v2, Post Permissions = unique, Pre-Permissions =none
Vertex Name = randnum.v1, Post Permissions = unique, Pre-Permissions =none
Vertex Name = randnum.v2, Post Permissions = unique, Pre-Permissions =none
Vertex Name = randnum.iseed, Post Permissions = unique, Pre-Permissions =none
Vertex Name = mold.move, Post Permissions = unique, Pre-Permissions =none
Vertex Name = mold.movemx, Post Permissions = none, Pre-Permissions =none
Vertex Name = mold.epot, Post Permissions = unique, Pre-Permissions =none
Vertex Name = mold.vir, Post Permissions = unique, Pre-Permissions =none
Vertex Name = mold.sum, Post Permissions = unique, Pre-Permissions =none
Vertex Name = mold.vel, Post Permissions = unique, Pre-Permissions =none
Vertex Name = mold.count, Post Permissions = unique, Pre-Permissions =none
Vertex Name = mold.istop, Post Permissions = none, Pre-Permissions =none
Vertex Name = mold.irep, Post Permissions = none, Pre-Permissions =none
Vertex Name = mold.iprint, Post Permissions = none, Pre-Permissions =none
Vertex Name = mold.ek, Post Permissions = unique, Pre-Permissions =none
Vertex Name = mold.etot, Post Permissions = unique, Pre-Permissions =none
Vertex Name = mold.temp, Post Permissions = unique, Pre-Permissions =none
Vertex Name = mold.pres, Post Permissions = unique, Pre-Permissions =none
Vertex Name = mold.rp, Post Permissions = unique, Pre-Permissions =none
Vertex Name = one.xcoord, Post Permissions = unique, Pre-Permissions =none
Vertex Name = one.xvelocity, Post Permissions = unique, Pre-Permissions =none
Vertex Name = one.xforce, Post Permissions = unique, Pre-Permissions =none
Vertex Name = one.ycoord, Post Permissions = unique, Pre-Permissions =none
Vertex Name = one.yvelocity, Post Permissions = unique, Pre-Permissions =none
Vertex Name = one.yforce, Post Permissions = unique, Pre-Permissions =none
Vertex Name = one.zcoord, Post Permissions = unique, Pre-Permissions =none
Vertex Name = one.zvelocity, Post Permissions = unique, Pre-Permissions =none
Vertex Name = one.zforce, Post Permissions = unique, Pre-Permissions =none
Vertex Name = particle.xcoord, Post Permissions = none, Pre-Permissions =none
Vertex Name = one.one, Post Permissions = none, Pre-Permissions =none
Vertex Name = particle.ycoord, Post Permissions = none, Pre-Permissions =none
Vertex Name = particle.zcoord, Post Permissions = none, Pre-Permissions =none
Vertex Name = one.epot, Post Permissions = unique, Pre-Permissions =none
Vertex Name = one.vir, Post Permissions = unique, Pre-Permissions =none
Vertex Name = particle.xforce, Post Permissions = unique, Pre-Permissions =none
Vertex Name = particle.yforce, Post Permissions = unique, Pre-Permissions =none
Vertex Name = particle.zforce, Post Permissions = unique, Pre-Permissions =none
Vertex Name = one.interactions, Post Permissions = unique, Pre-Permissions =none
Vertex Name = one.count, Post Permissions = unique, Pre-Permissions =none
////////////////////////////////////////////////////////////////////////////////
 * 
 * Class Name = JGFMolDynBenchSizeA
Method Name = main
Ref-Var= moldyn.md.ITERS, Pre-Permissions=none, Post Permissions=unique
Ref-Var= moldyn.md.LENGTH, Pre-Permissions=none, Post Permissions=unique
Ref-Var= moldyn.md.m, Pre-Permissions=none, Post Permissions=unique
Ref-Var= moldyn.md.mu, Pre-Permissions=none, Post Permissions=unique
Ref-Var= moldyn.md.kb, Pre-Permissions=none, Post Permissions=unique
Ref-Var= moldyn.md.TSIM, Pre-Permissions=none, Post Permissions=unique
Ref-Var= moldyn.md.deltat, Pre-Permissions=none, Post Permissions=unique
Ref-Var= moldyn.md.one, Pre-Permissions=none, Post Permissions=unique
Ref-Var= moldyn.md.epot, Pre-Permissions=none, Post Permissions=unique
Ref-Var= moldyn.md.vir, Pre-Permissions=none, Post Permissions=unique
Ref-Var= moldyn.md.count, Pre-Permissions=none, Post Permissions=unique
Ref-Var= moldyn.md.datasizes, Pre-Permissions=none, Post Permissions=unique
Ref-Var= moldyn.md.interactions, Pre-Permissions=none, Post Permissions=unique
Ref-Var= moldyn.md.den, Pre-Permissions=none, Post Permissions=unique
Ref-Var= moldyn.md.tref, Pre-Permissions=none, Post Permissions=unique
Ref-Var= moldyn.md.h, Pre-Permissions=none, Post Permissions=unique
Ref-Var= moldyn.md.irep, Pre-Permissions=none, Post Permissions=unique
Ref-Var= moldyn.md.istop, Pre-Permissions=none, Post Permissions=unique
Ref-Var= moldyn.md.iprint, Pre-Permissions=none, Post Permissions=unique
Ref-Var= moldyn.md.movemx, Pre-Permissions=none, Post Permissions=unique
Ref-Var= mold.size, Pre-Permissions=none, Post Permissions=unique
Ref-Var= mold.nbf, Pre-Permissions=none, Post Permissions=unique
Ref-Var= mold.nbf2, Pre-Permissions=none, Post Permissions=unique
Ref-Var= mold.nbf3, Pre-Permissions=none, Post Permissions=unique
Ref-Var= mold.mm, Pre-Permissions=none, Post Permissions=unique
Ref-Var= mold.datasizes, Pre-Permissions=none, Post Permissions=none
Ref-Var= mold.PARTSIZE, Pre-Permissions=none, Post Permissions=unique
Ref-Var= mold.mdsize, Pre-Permissions=none, Post Permissions=unique
Ref-Var= mold.one, Pre-Permissions=none, Post Permissions=unique
Ref-Var= mold.l, Pre-Permissions=none, Post Permissions=unique
Ref-Var= mold.LENGTH, Pre-Permissions=none, Post Permissions=none
Ref-Var= mold.side, Pre-Permissions=none, Post Permissions=unique
Ref-Var= mold.den, Pre-Permissions=none, Post Permissions=none
Ref-Var= mold.rcoff, Pre-Permissions=none, Post Permissions=unique
Ref-Var= mold.a, Pre-Permissions=none, Post Permissions=unique
Ref-Var= mold.sideh, Pre-Permissions=none, Post Permissions=unique
Ref-Var= mold.hsq, Pre-Permissions=none, Post Permissions=unique
Ref-Var= mold.h, Pre-Permissions=none, Post Permissions=none
Ref-Var= mold.hsq2, Pre-Permissions=none, Post Permissions=unique
Ref-Var= mold.npartm, Pre-Permissions=none, Post Permissions=unique
Ref-Var= mold.rcoffs, Pre-Permissions=none, Post Permissions=unique
Ref-Var= mold.tscale, Pre-Permissions=none, Post Permissions=unique
Ref-Var= mold.vaver, Pre-Permissions=none, Post Permissions=unique
Ref-Var= mold.tref, Pre-Permissions=none, Post Permissions=none
Ref-Var= mold.vaverh, Pre-Permissions=none, Post Permissions=unique
Ref-Var= mold.ijk, Pre-Permissions=none, Post Permissions=unique
Ref-Var= mold.lg, Pre-Permissions=none, Post Permissions=unique
Ref-Var= mold.i, Pre-Permissions=none, Post Permissions=unique
Ref-Var= mold.j, Pre-Permissions=none, Post Permissions=unique
Ref-Var= mold.k, Pre-Permissions=none, Post Permissions=unique
Ref-Var= mold.one.one, Pre-Permissions=none, Post Permissions=unique
Ref-Var= mold.iseed, Pre-Permissions=none, Post Permissions=unique
Ref-Var= mold.v1, Pre-Permissions=none, Post Permissions=unique
Ref-Var= mold.v2, Pre-Permissions=none, Post Permissions=unique
Ref-Var= mold.randnum, Pre-Permissions=none, Post Permissions=unique
Ref-Var= mold.randnum.randnum, Pre-Permissions=none, Post Permissions=unique
Ref-Var= mold.v1, Pre-Permissions=none, Post Permissions=none
Ref-Var= mold.r, Pre-Permissions=none, Post Permissions=unique
Ref-Var= moldyn.particle.xvelocity, Pre-Permissions=none, Post Permissions=unique
Ref-Var= mold.xvelocity, Pre-Permissions=none, Post Permissions=none
Ref-Var= mold.v2, Pre-Permissions=none, Post Permissions=none
Ref-Var= moldyn.particle.yvelocity, Pre-Permissions=none, Post Permissions=unique
Ref-Var= mold.yvelocity, Pre-Permissions=none, Post Permissions=none
Ref-Var= moldyn.particle.zvelocity, Pre-Permissions=none, Post Permissions=unique
Ref-Var= mold.zvelocity, Pre-Permissions=none, Post Permissions=none
Ref-Var= mold.ekin, Pre-Permissions=none, Post Permissions=unique
Ref-Var= mold.sp, Pre-Permissions=none, Post Permissions=unique
Ref-Var= mold.ts, Pre-Permissions=none, Post Permissions=unique
Ref-Var= mold.sc, Pre-Permissions=none, Post Permissions=unique
Ref-Var= randnum.v1, Pre-Permissions=none, Post Permissions=unique
Ref-Var= randnum.v2, Pre-Permissions=none, Post Permissions=unique
Ref-Var= randnum.iseed, Pre-Permissions=none, Post Permissions=unique
Ref-Var= mold.move, Pre-Permissions=none, Post Permissions=unique
Ref-Var= mold.movemx, Pre-Permissions=none, Post Permissions=none
Ref-Var= mold.epot, Pre-Permissions=none, Post Permissions=unique
Ref-Var= mold.vir, Pre-Permissions=none, Post Permissions=unique
Ref-Var= mold.sum, Pre-Permissions=none, Post Permissions=unique
Ref-Var= mold.vel, Pre-Permissions=none, Post Permissions=unique
Ref-Var= mold.count, Pre-Permissions=none, Post Permissions=unique
Ref-Var= mold.istop, Pre-Permissions=none, Post Permissions=none
Ref-Var= mold.irep, Pre-Permissions=none, Post Permissions=none
Ref-Var= mold.iprint, Pre-Permissions=none, Post Permissions=none
Ref-Var= mold.ek, Pre-Permissions=none, Post Permissions=unique
Ref-Var= mold.etot, Pre-Permissions=none, Post Permissions=unique
Ref-Var= mold.temp, Pre-Permissions=none, Post Permissions=unique
Ref-Var= mold.pres, Pre-Permissions=none, Post Permissions=unique
Ref-Var= mold.rp, Pre-Permissions=none, Post Permissions=unique
Ref-Var= one.xcoord, Pre-Permissions=none, Post Permissions=unique
Ref-Var= one.xvelocity, Pre-Permissions=none, Post Permissions=unique
Ref-Var= one.xforce, Pre-Permissions=none, Post Permissions=unique
Ref-Var= one.ycoord, Pre-Permissions=none, Post Permissions=unique
Ref-Var= one.yvelocity, Pre-Permissions=none, Post Permissions=unique
Ref-Var= one.yforce, Pre-Permissions=none, Post Permissions=unique
Ref-Var= one.zcoord, Pre-Permissions=none, Post Permissions=unique
Ref-Var= one.zvelocity, Pre-Permissions=none, Post Permissions=unique
Ref-Var= one.zforce, Pre-Permissions=none, Post Permissions=unique
Ref-Var= moldyn.particle.xcoord, Pre-Permissions=none, Post Permissions=none
Ref-Var= one.one, Pre-Permissions=none, Post Permissions=none
Ref-Var= moldyn.particle.ycoord, Pre-Permissions=none, Post Permissions=none
Ref-Var= moldyn.particle.zcoord, Pre-Permissions=none, Post Permissions=none
Ref-Var= one.epot, Pre-Permissions=none, Post Permissions=none
Ref-Var= one.vir, Pre-Permissions=none, Post Permissions=none
Ref-Var= moldyn.particle.xforce, Pre-Permissions=none, Post Permissions=unique
Ref-Var= moldyn.particle.yforce, Pre-Permissions=none, Post Permissions=unique
Ref-Var= moldyn.particle.zforce, Pre-Permissions=none, Post Permissions=unique
Ref-Var= one.interactions, Pre-Permissions=none, Post Permissions=unique
Ref-Var= one.count, Pre-Permissions=none, Post Permissions=none
Method Name = initialise
Ref-Var= mold.nbf, Pre-Permissions=share, Post Permissions=share
Ref-Var= mold.nbf2, Pre-Permissions=share, Post Permissions=share
Ref-Var= mold.nbf3, Pre-Permissions=share, Post Permissions=share
Ref-Var= mold.mm, Pre-Permissions=share, Post Permissions=share
Ref-Var= mold.datasizes, Pre-Permissions=pure, Post Permissions=pure
Ref-Var= mold.size, Pre-Permissions=pure, Post Permissions=pure
Ref-Var= mold.PARTSIZE, Pre-Permissions=share, Post Permissions=share
Ref-Var= mold.mdsize, Pre-Permissions=share, Post Permissions=share
Ref-Var= mold.one, Pre-Permissions=none, Post Permissions=unique
Ref-Var= mold.l, Pre-Permissions=share, Post Permissions=share
Ref-Var= mold.LENGTH, Pre-Permissions=pure, Post Permissions=pure
Ref-Var= mold.side, Pre-Permissions=share, Post Permissions=share
Ref-Var= mold.den, Pre-Permissions=pure, Post Permissions=pure
Ref-Var= mold.rcoff, Pre-Permissions=share, Post Permissions=share
Ref-Var= mold.a, Pre-Permissions=share, Post Permissions=share
Ref-Var= mold.sideh, Pre-Permissions=share, Post Permissions=share
Ref-Var= mold.hsq, Pre-Permissions=share, Post Permissions=share
Ref-Var= mold.h, Pre-Permissions=pure, Post Permissions=pure
Ref-Var= mold.hsq2, Pre-Permissions=share, Post Permissions=share
Ref-Var= mold.npartm, Pre-Permissions=share, Post Permissions=share
Ref-Var= mold.rcoffs, Pre-Permissions=share, Post Permissions=share
Ref-Var= mold.tscale, Pre-Permissions=share, Post Permissions=share
Ref-Var= mold.vaver, Pre-Permissions=share, Post Permissions=share
Ref-Var= mold.tref, Pre-Permissions=pure, Post Permissions=pure
Ref-Var= mold.vaverh, Pre-Permissions=share, Post Permissions=share
Ref-Var= mold.ijk, Pre-Permissions=none, Post Permissions=unique
Ref-Var= mold.lg, Pre-Permissions=share, Post Permissions=share
Ref-Var= mold.i, Pre-Permissions=share, Post Permissions=share
Ref-Var= mold.j, Pre-Permissions=share, Post Permissions=share
Ref-Var= mold.k, Pre-Permissions=share, Post Permissions=share
Ref-Var= mold.one.one, Pre-Permissions=none, Post Permissions=unique
Ref-Var= mold.iseed, Pre-Permissions=share, Post Permissions=share
Ref-Var= mold.v1, Pre-Permissions=share, Post Permissions=share
Ref-Var= mold.v2, Pre-Permissions=share, Post Permissions=share
Ref-Var= mold.randnum, Pre-Permissions=none, Post Permissions=unique
Ref-Var= mold.randnum.randnum, Pre-Permissions=none, Post Permissions=unique
Ref-Var= mold.v1, Pre-Permissions=pure, Post Permissions=pure
Ref-Var= mold.r, Pre-Permissions=share, Post Permissions=share
Ref-Var= moldyn.particle.xvelocity, Pre-Permissions=share, Post Permissions=share
Ref-Var= mold.xvelocity, Pre-Permissions=pure, Post Permissions=pure
Ref-Var= mold.v2, Pre-Permissions=pure, Post Permissions=pure
Ref-Var= moldyn.particle.yvelocity, Pre-Permissions=share, Post Permissions=share
Ref-Var= mold.yvelocity, Pre-Permissions=pure, Post Permissions=pure
Ref-Var= moldyn.particle.zvelocity, Pre-Permissions=share, Post Permissions=share
Ref-Var= mold.zvelocity, Pre-Permissions=pure, Post Permissions=pure
Ref-Var= mold.ekin, Pre-Permissions=share, Post Permissions=share
Ref-Var= mold.sp, Pre-Permissions=share, Post Permissions=share
Ref-Var= mold.ts, Pre-Permissions=share, Post Permissions=share
Ref-Var= mold.sc, Pre-Permissions=share, Post Permissions=share
Ref-Var= randnum.v1, Pre-Permissions=share, Post Permissions=share
Ref-Var= randnum.v2, Pre-Permissions=share, Post Permissions=share
Ref-Var= randnum.iseed, Pre-Permissions=share, Post Permissions=share
Method Name = particle
Ref-Var= mold.one.xcoord, Pre-Permissions=none, Post Permissions=unique
Ref-Var= mold.one.ycoord, Pre-Permissions=none, Post Permissions=unique
Ref-Var= mold.one.zcoord, Pre-Permissions=none, Post Permissions=unique
Ref-Var= mold.one.xvelocity, Pre-Permissions=none, Post Permissions=unique
Ref-Var= mold.one.yvelocity, Pre-Permissions=none, Post Permissions=unique
Ref-Var= mold.one.zvelocity, Pre-Permissions=none, Post Permissions=unique
Ref-Var= mold.one.xforce, Pre-Permissions=none, Post Permissions=unique
Ref-Var= mold.one.yforce, Pre-Permissions=none, Post Permissions=unique
Ref-Var= mold.one.zforce, Pre-Permissions=none, Post Permissions=unique
Method Name = random
Ref-Var= mold.iseed, Pre-Permissions=pure, Post Permissions=pure
Ref-Var= mold.v1, Pre-Permissions=pure, Post Permissions=pure
Ref-Var= mold.v2, Pre-Permissions=pure, Post Permissions=pure
Ref-Var= mold.randnum.iseed, Pre-Permissions=none, Post Permissions=unique
Ref-Var= mold.randnum.v1, Pre-Permissions=none, Post Permissions=unique
Ref-Var= mold.randnum.v2, Pre-Permissions=none, Post Permissions=unique
Method Name = seed
Ref-Var= randnum.v1, Pre-Permissions=share, Post Permissions=share
Ref-Var= randnum.v2, Pre-Permissions=share, Post Permissions=share
Ref-Var= randnum.iseed, Pre-Permissions=share, Post Permissions=share
Method Name = update
Ref-Var= randnum.iseed, Pre-Permissions=share, Post Permissions=share
Method Name = runiters
Ref-Var= mold.move, Pre-Permissions=share, Post Permissions=share
Ref-Var= mold.movemx, Pre-Permissions=pure, Post Permissions=pure
Ref-Var= mold.i, Pre-Permissions=share, Post Permissions=share
Ref-Var= mold.mdsize, Pre-Permissions=pure, Post Permissions=pure
Ref-Var= mold.one, Pre-Permissions=pure, Post Permissions=pure
Ref-Var= mold.side, Pre-Permissions=pure, Post Permissions=pure
Ref-Var= mold.epot, Pre-Permissions=share, Post Permissions=share
Ref-Var= mold.vir, Pre-Permissions=share, Post Permissions=share
Ref-Var= mold.rcoff, Pre-Permissions=pure, Post Permissions=pure
Ref-Var= mold.sum, Pre-Permissions=share, Post Permissions=share
Ref-Var= mold.hsq2, Pre-Permissions=pure, Post Permissions=pure
Ref-Var= mold.ekin, Pre-Permissions=share, Post Permissions=share
Ref-Var= mold.hsq, Pre-Permissions=pure, Post Permissions=pure
Ref-Var= mold.vel, Pre-Permissions=share, Post Permissions=share
Ref-Var= mold.count, Pre-Permissions=share, Post Permissions=share
Ref-Var= mold.vaverh, Pre-Permissions=pure, Post Permissions=pure
Ref-Var= mold.h, Pre-Permissions=pure, Post Permissions=pure
Ref-Var= mold.istop, Pre-Permissions=pure, Post Permissions=pure
Ref-Var= mold.irep, Pre-Permissions=pure, Post Permissions=pure
Ref-Var= mold.sc, Pre-Permissions=share, Post Permissions=share
Ref-Var= mold.tref, Pre-Permissions=pure, Post Permissions=pure
Ref-Var= mold.tscale, Pre-Permissions=pure, Post Permissions=pure
Ref-Var= mold.iprint, Pre-Permissions=pure, Post Permissions=pure
Ref-Var= mold.ek, Pre-Permissions=share, Post Permissions=share
Ref-Var= mold.etot, Pre-Permissions=share, Post Permissions=share
Ref-Var= mold.temp, Pre-Permissions=share, Post Permissions=share
Ref-Var= mold.pres, Pre-Permissions=share, Post Permissions=share
Ref-Var= mold.den, Pre-Permissions=pure, Post Permissions=pure
Ref-Var= mold.rp, Pre-Permissions=share, Post Permissions=share
Ref-Var= one.xcoord, Pre-Permissions=share, Post Permissions=share
Ref-Var= one.xvelocity, Pre-Permissions=share, Post Permissions=share
Ref-Var= one.xforce, Pre-Permissions=share, Post Permissions=share
Ref-Var= one.ycoord, Pre-Permissions=share, Post Permissions=share
Ref-Var= one.yvelocity, Pre-Permissions=share, Post Permissions=share
Ref-Var= one.yforce, Pre-Permissions=share, Post Permissions=share
Ref-Var= one.zcoord, Pre-Permissions=share, Post Permissions=share
Ref-Var= one.zvelocity, Pre-Permissions=share, Post Permissions=share
Ref-Var= one.zforce, Pre-Permissions=share, Post Permissions=share
Ref-Var= moldyn.particle.xcoord, Pre-Permissions=pure, Post Permissions=pure
Ref-Var= one.one, Pre-Permissions=pure, Post Permissions=pure
Ref-Var= moldyn.particle.ycoord, Pre-Permissions=pure, Post Permissions=pure
Ref-Var= moldyn.particle.zcoord, Pre-Permissions=pure, Post Permissions=pure
Ref-Var= one.epot, Pre-Permissions=pure, Post Permissions=pure
Ref-Var= one.vir, Pre-Permissions=pure, Post Permissions=pure
Ref-Var= moldyn.particle.xforce, Pre-Permissions=share, Post Permissions=share
Ref-Var= moldyn.particle.yforce, Pre-Permissions=share, Post Permissions=share
Ref-Var= moldyn.particle.zforce, Pre-Permissions=share, Post Permissions=share
Ref-Var= one.interactions, Pre-Permissions=share, Post Permissions=share
Ref-Var= one.count, Pre-Permissions=pure, Post Permissions=pure
Method Name = domove
Ref-Var= mold.side, Pre-Permissions=pure, Post Permissions=pure
Ref-Var= one.xcoord, Pre-Permissions=share, Post Permissions=share
Ref-Var= one.xvelocity, Pre-Permissions=share, Post Permissions=share
Ref-Var= one.xforce, Pre-Permissions=share, Post Permissions=share
Ref-Var= one.ycoord, Pre-Permissions=share, Post Permissions=share
Ref-Var= one.yvelocity, Pre-Permissions=share, Post Permissions=share
Ref-Var= one.yforce, Pre-Permissions=share, Post Permissions=share
Ref-Var= one.zcoord, Pre-Permissions=share, Post Permissions=share
Ref-Var= one.zvelocity, Pre-Permissions=share, Post Permissions=share
Ref-Var= one.zforce, Pre-Permissions=share, Post Permissions=share
Method Name = force
Ref-Var= mold.side, Pre-Permissions=pure, Post Permissions=pure
Ref-Var= mold.rcoff, Pre-Permissions=pure, Post Permissions=pure
Ref-Var= mold.mdsize, Pre-Permissions=pure, Post Permissions=pure
Ref-Var= mold.i, Pre-Permissions=pure, Post Permissions=pure
Ref-Var= one.xcoord, Pre-Permissions=pure, Post Permissions=pure
Ref-Var= one.ycoord, Pre-Permissions=pure, Post Permissions=pure
Ref-Var= one.zcoord, Pre-Permissions=pure, Post Permissions=pure
Ref-Var= moldyn.particle.xcoord, Pre-Permissions=pure, Post Permissions=pure
Ref-Var= one.one, Pre-Permissions=pure, Post Permissions=pure
Ref-Var= moldyn.particle.ycoord, Pre-Permissions=pure, Post Permissions=pure
Ref-Var= moldyn.particle.zcoord, Pre-Permissions=pure, Post Permissions=pure
Ref-Var= one.epot, Pre-Permissions=pure, Post Permissions=pure
Ref-Var= one.vir, Pre-Permissions=pure, Post Permissions=pure
Ref-Var= moldyn.particle.xforce, Pre-Permissions=share, Post Permissions=share
Ref-Var= one.xforce, Pre-Permissions=share, Post Permissions=share
Ref-Var= moldyn.particle.yforce, Pre-Permissions=share, Post Permissions=share
Ref-Var= one.yforce, Pre-Permissions=share, Post Permissions=share
Ref-Var= moldyn.particle.zforce, Pre-Permissions=share, Post Permissions=share
Ref-Var= one.zforce, Pre-Permissions=share, Post Permissions=share
Ref-Var= one.interactions, Pre-Permissions=share, Post Permissions=share
Method Name = mkekin
Ref-Var= mold.hsq2, Pre-Permissions=pure, Post Permissions=pure
Ref-Var= one.xforce, Pre-Permissions=share, Post Permissions=share
Ref-Var= one.yforce, Pre-Permissions=share, Post Permissions=share
Ref-Var= one.zforce, Pre-Permissions=share, Post Permissions=share
Ref-Var= one.xvelocity, Pre-Permissions=share, Post Permissions=share
Ref-Var= one.yvelocity, Pre-Permissions=share, Post Permissions=share
Ref-Var= one.zvelocity, Pre-Permissions=share, Post Permissions=share
Method Name = velavg
Ref-Var= mold.vaverh, Pre-Permissions=pure, Post Permissions=pure
Ref-Var= mold.h, Pre-Permissions=pure, Post Permissions=pure
Ref-Var= one.xvelocity, Pre-Permissions=pure, Post Permissions=pure
Ref-Var= one.yvelocity, Pre-Permissions=pure, Post Permissions=pure
Ref-Var= one.zvelocity, Pre-Permissions=pure, Post Permissions=pure
Ref-Var= one.count, Pre-Permissions=pure, Post Permissions=pure
Method Name = dscal
Ref-Var= mold.sc, Pre-Permissions=pure, Post Permissions=pure
Ref-Var= one.xvelocity, Pre-Permissions=share, Post Permissions=share
Ref-Var= one.yvelocity, Pre-Permissions=share, Post Permissions=share
Ref-Var= one.zvelocity, Pre-Permissions=share, Post Permissions=share

//////////////////////////////////////////////////////


//////////////////////////////////////////////////////

//////////////////////////////////////////////////////
Class Name = particle
Method Name = particle
Ref-Var= mold.one.xcoord, Pre-Permissions=none, Post Permissions=unique
Ref-Var= mold.one.ycoord, Pre-Permissions=none, Post Permissions=unique
Ref-Var= mold.one.zcoord, Pre-Permissions=none, Post Permissions=unique
Ref-Var= mold.one.xvelocity, Pre-Permissions=none, Post Permissions=unique
Ref-Var= mold.one.yvelocity, Pre-Permissions=none, Post Permissions=unique
Ref-Var= mold.one.zvelocity, Pre-Permissions=none, Post Permissions=unique
Ref-Var= mold.one.xforce, Pre-Permissions=none, Post Permissions=unique
Ref-Var= mold.one.yforce, Pre-Permissions=none, Post Permissions=unique
Ref-Var= mold.one.zforce, Pre-Permissions=none, Post Permissions=unique
Method Name = domove
Ref-Var= mold.side, Pre-Permissions=pure, Post Permissions=pure
Ref-Var= one.xcoord, Pre-Permissions=share, Post Permissions=share
Ref-Var= one.xvelocity, Pre-Permissions=share, Post Permissions=share
Ref-Var= one.xforce, Pre-Permissions=share, Post Permissions=share
Ref-Var= one.ycoord, Pre-Permissions=share, Post Permissions=share
Ref-Var= one.yvelocity, Pre-Permissions=share, Post Permissions=share
Ref-Var= one.yforce, Pre-Permissions=share, Post Permissions=share
Ref-Var= one.zcoord, Pre-Permissions=share, Post Permissions=share
Ref-Var= one.zvelocity, Pre-Permissions=share, Post Permissions=share
Ref-Var= one.zforce, Pre-Permissions=share, Post Permissions=share
Method Name = force
Ref-Var= mold.side, Pre-Permissions=pure, Post Permissions=pure
Ref-Var= mold.rcoff, Pre-Permissions=pure, Post Permissions=pure
Ref-Var= mold.mdsize, Pre-Permissions=pure, Post Permissions=pure
Ref-Var= mold.i, Pre-Permissions=pure, Post Permissions=pure
Ref-Var= one.xcoord, Pre-Permissions=pure, Post Permissions=pure
Ref-Var= one.ycoord, Pre-Permissions=pure, Post Permissions=pure
Ref-Var= one.zcoord, Pre-Permissions=pure, Post Permissions=pure
Ref-Var= moldyn.particle.xcoord, Pre-Permissions=pure, Post Permissions=pure
Ref-Var= one.one, Pre-Permissions=pure, Post Permissions=pure
Ref-Var= moldyn.particle.ycoord, Pre-Permissions=pure, Post Permissions=pure
Ref-Var= moldyn.particle.zcoord, Pre-Permissions=pure, Post Permissions=pure
Ref-Var= one.epot, Pre-Permissions=pure, Post Permissions=pure
Ref-Var= one.vir, Pre-Permissions=pure, Post Permissions=pure
Ref-Var= moldyn.particle.xforce, Pre-Permissions=share, Post Permissions=share
Ref-Var= one.xforce, Pre-Permissions=share, Post Permissions=share
Ref-Var= moldyn.particle.yforce, Pre-Permissions=share, Post Permissions=share
Ref-Var= one.yforce, Pre-Permissions=share, Post Permissions=share
Ref-Var= moldyn.particle.zforce, Pre-Permissions=share, Post Permissions=share
Ref-Var= one.zforce, Pre-Permissions=share, Post Permissions=share
Ref-Var= one.interactions, Pre-Permissions=share, Post Permissions=share
Method Name = mkekin
Ref-Var= mold.hsq2, Pre-Permissions=pure, Post Permissions=pure
Ref-Var= one.xforce, Pre-Permissions=share, Post Permissions=share
Ref-Var= one.yforce, Pre-Permissions=share, Post Permissions=share
Ref-Var= one.zforce, Pre-Permissions=share, Post Permissions=share
Ref-Var= one.xvelocity, Pre-Permissions=share, Post Permissions=share
Ref-Var= one.yvelocity, Pre-Permissions=share, Post Permissions=share
Ref-Var= one.zvelocity, Pre-Permissions=share, Post Permissions=share
Method Name = velavg
Ref-Var= mold.vaverh, Pre-Permissions=pure, Post Permissions=pure
Ref-Var= mold.h, Pre-Permissions=pure, Post Permissions=pure
Ref-Var= one.xvelocity, Pre-Permissions=pure, Post Permissions=pure
Ref-Var= one.yvelocity, Pre-Permissions=pure, Post Permissions=pure
Ref-Var= one.zvelocity, Pre-Permissions=pure, Post Permissions=pure
Ref-Var= one.count, Pre-Permissions=pure, Post Permissions=pure
Method Name = dscal
Ref-Var= mold.sc, Pre-Permissions=pure, Post Permissions=pure
Ref-Var= one.xvelocity, Pre-Permissions=share, Post Permissions=share
Ref-Var= one.yvelocity, Pre-Permissions=share, Post Permissions=share
Ref-Var= one.zvelocity, Pre-Permissions=share, Post Permissions=share

//////////////////////////////////////////////////////


//////////////////////////////////////////////////////

//////////////////////////////////////////////////////
Class Name = random
Method Name = random
Ref-Var= mold.iseed, Pre-Permissions=pure, Post Permissions=pure
Ref-Var= mold.v1, Pre-Permissions=pure, Post Permissions=pure
Ref-Var= mold.v2, Pre-Permissions=pure, Post Permissions=pure
Ref-Var= mold.randnum.iseed, Pre-Permissions=none, Post Permissions=unique
Ref-Var= mold.randnum.v1, Pre-Permissions=none, Post Permissions=unique
Ref-Var= mold.randnum.v2, Pre-Permissions=none, Post Permissions=unique
Method Name = update
Ref-Var= randnum.iseed, Pre-Permissions=share, Post Permissions=share
Method Name = seed
Ref-Var= randnum.v1, Pre-Permissions=share, Post Permissions=share
Ref-Var= randnum.v2, Pre-Permissions=share, Post Permissions=share
Ref-Var= randnum.iseed, Pre-Permissions=share, Post Permissions=share
 */
	
