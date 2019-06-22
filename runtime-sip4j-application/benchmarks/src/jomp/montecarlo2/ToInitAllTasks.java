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
*      Original version of this code by Hon Yau (hwyau@epcc.ed.ac.uk)     *
*                                                                         *
*      This version copyright (c) The University of Edinburgh, 1999.      *
*                         All rights reserved.                            *
*                                                                         *
**************************************************************************/


package jomp.montecarlo2;
/**
  * Class for defining the initialisation data for all tasks.
  *
  * @author H W Yau
  * @version $Revision: 1.10 $ $Date: 1999/02/16 18:52:53 $
  

  */
public class ToInitAllTasks implements java.io.Serializable {
  private String header;
  private String name;
  private int startDate;
  private int endDate;
  private double dTime;
  private int returnDefinition;
  private double expectedReturnRate;
  private double volatility;
  private int nTimeSteps;
  private double pathStartValue;

  /**
    * Constructor, for initialisation data which are common to all
    * computation tasks.
    *
    * @param header Simple header string.
    * @param name The name of the security which this Monte Carlo path
    *             should represent.
    * @param startDate The date when the path starts, in 'YYYYMMDD' format.
    * @param endDate The date when the path ends, in 'YYYYMMDD' format.
    * @param dTime The interval in the data between successive data points
    *              in the generated path.
    * @param returnDefinition How the statistic variables were defined,
    *                         according to the definitions in
    *                         <code>ReturnPath</code>'s two class variables
    *                         <code>COMPOUNDED</code> and
    *                         <code>NONCOMPOUNDED</code>.
    * @param expectedReturnRate The measured expected return rate for which
    *       to generate.
    * @param volatility The measured volatility for which to generate.
    * @param nTimeSteps The number of time steps for which to generate.
    * @param pathStartValue The stock price value to use at the start of each
    *        Monte Carlo simulation path.
   
 @Perm(requires="pure(#1) 
 * pure(#2) * pure(#3) * pure(#4) * pure(#5) * pure(#6) 
 * pure(#7) * immutable(#8) * none(name) * none(startDate) * none(endDate) * none(dTime) * none(returnDefinition) 
 * none(expectedReturnRate) * none(volatility) * none(nTimeSteps) * none(pathStartValue) * none(header) in ALIVE", 
 ensures= "
 pure(nTimeStepsMC) * immutable(pathStartValue) * 
 unique(name) * unique(startDate) * unique(endDate) * unique(dTime) * unique(returnDefinition) * unique(expectedReturnRate) * 
 unique(volatility) * unique(nTimeSteps) * unique(dTime) * unique(header)
 unique(pathStartValue) * pure(#1) * pure(#2) * pure(#3) * pure(#4) * pure(#5) * pure(#6) * pure(#7) * immutable(#8)  in ALIVE")

    */
  public ToInitAllTasks(String header, String name, int startDate, int endDate, 
  double dTime, int returnDefinition, double expectedReturnRate, double volatility, 
  double pathStartValue) {
    this.header             = header;
    this.name               = name;
    this.startDate          = startDate;
    this.endDate            = endDate;
    this.dTime              = dTime;
    this.returnDefinition   = returnDefinition;
    this.expectedReturnRate = expectedReturnRate;
    this.volatility         = volatility;
    this.nTimeSteps         = nTimeSteps;
    this.pathStartValue     = pathStartValue;
  }
  /**
    * Another constructor, slightly easier to use by having slightly
    * fewer arguments.  Makes use of the "ReturnPath" object to
    * accomplish this.
    *
    * @param obj Object used to define the instance variables which
    *            should be carried over to this object.
    * @param nTimeSteps The number of time steps which the Monte
    *                   Carlo generator should make.
    * @param pathStartValue The stock price value to use at the start of each
    *        Monte Carlo simulation path.
    * @exception DemoException thrown if there is a problem accessing the
    *                          instance variables from the target objetct.
    */
  public ToInitAllTasks(ReturnPath obj, int nTimeSteps, double pathStartValue)  // parser generates permission for this constructor it should generate for both
  throws DemoException {
    //
    // Instance variables defined in the PathId object.
    this.name      = obj.getname();
    this.startDate = obj.getstartDate();
    this.endDate   = obj.getendDate();
    this.dTime     = obj.getdTime();
    //
    // Instance variables defined in ReturnPath object.
    this.returnDefinition   = obj.getreturnDefinition();
    this.expectedReturnRate = obj.getexpectedReturnRate();
    this.volatility         = obj.getvolatility();
    this.nTimeSteps         = nTimeSteps;
    this.pathStartValue     = pathStartValue;
  }
  //------------------------------------------------------------------------
  // Accessor methods for class ToInitAllTasks.
  // Generated by 'makeJavaAccessor.pl' script.  HWY.  20th January 1999.
  //------------------------------------------------------------------------
  /**
    * Accessor method for private instance variable <code>header</code>.
    *
    * @return Value of instance variable <code>header</code>.
  	
  	@Perm(requires="pure(header) in ALIVE", 
 	ensures= "pure(header) in ALIVE")
    */
  public String getheader() {
    return(this.header);
  }
  /**
    * Set method for private instance variable <code>header</code>.
    *
    * @param header the value to set for the instance variable <code>header</code>.
   @Perm(requires="share(header) in ALIVE", 
 ensures= "share(header) in ALIVE")
    */
  public void setheader(String header) {
    this.header = header;
  }
  /**
    * Accessor method for private instance variable <code>name</code>.
    *
    * @return Value of instance variable <code>name</code>.
  @Perm(requires="share(name) in ALIVE", 
 ensures= "share(name) in ALIVE")
    */
  public String getname() {
    return(this.name);
  }
  /**
    * Set method for private instance variable <code>name</code>.
    *
    * @param name the value to set for the instance variable <code>name</code>.
  @Perm(requires="share(name) in ALIVE", 
 ensures= "share(name) in ALIVE")
    */
  public void setname(String name) {
    this.name = name;
  }
  /**
    * Accessor method for private instance variable <code>startDate</code>.
    *
    * @return Value of instance variable <code>startDate</code>.
   @Perm(requires="pure(startDate) in ALIVE", 
 ensures= "pure(startDate) in ALIVE")
    */
  public int getstartDate() {
    return(this.startDate);
  }
  /**
    * Set method for private instance variable <code>startDate</code>.
    *
    * @param startDate the value to set for the instance variable <code>startDate</code>.
    @Perm(requires="share(startDate) in ALIVE", 
     ensures= "share(startDate) in ALIVE")
    */
  public void setstartDate(int startDate) {
    this.startDate = startDate;
  }
  /**
    * Accessor method for private instance variable <code>endDate</code>.
    *
    * @return Value of instance variable <code>endDate</code>.
  @Perm(requires="pure(endDate) in ALIVE", 
 ensures= "pure(endDate) in ALIVE")
    */
  public int getendDate() {
    return(this.endDate);
  }
  /**
    * Set method for private instance variable <code>endDate</code>.
    *
    * @param endDate the value to set for the instance variable <code>endDate</code>.
   @Perm(requires="share(endDate) in ALIVE", 
 	ensures= "share(endDate) in ALIVE")
    */
  public void setendDate(int endDate) {
    this.endDate = endDate;
  }
  /**
    * Accessor method for private instance variable <code>dTime</code>.
    *
    * @return Value of instance variable <code>dTime</code>.
  @Perm(requires="pure(dTime) in ALIVE", 
 ensures= "pure(dTime) in ALIVE")
    */
  public double getdTime() {
    return(this.dTime);
  }
  /**
    * Set method for private instance variable <code>dTime</code>.
    *
    * @param dTime the value to set for the instance variable <code>dTime</code>.
  
@Perm(requires="share(dTime) in ALIVE", 
 ensures= "share(dTime) in ALIVE")
    */
  public void setDTime(double dTime) {
    this.dTime = dTime;
  }
  /**
    * Accessor method for private instance variable <code>returnDefinition</code>.
    *
    * @return Value of instance variable <code>returnDefinition</code>.
	@Perm(requires="pure(returnDefinition) in ALIVE", 
	 ensures= "pure(returnDefinition) in ALIVE")  
 */
  public int getreturnDefinition() {
    return(this.returnDefinition);
  }
  /**
    * Set method for private instance variable <code>returnDefinition</code>.
    *
    * @param returnDefinition the value to set for the instance variable <code>returnDefinition</code>.
   @Perm(requires="share(returnDefinition) in ALIVE", 
 ensures= "share(returnDefinition) in ALIVE")
    */
  public void setReturnDefinition(int returnDefinition) {
    this.returnDefinition = returnDefinition;
  }
  /**
    * Accessor method for private instance variable <code>expectedReturnRate</code>.
    *
    * @return Value of instance variable <code>expectedReturnRate</code>.
  @Perm(requires="pure(expectedReturnRate) in ALIVE", 
 ensures= "pure(expectedReturnRate) in ALIVE")
    */
  public double getexpectedReturnRate() {
    return(this.expectedReturnRate);
  }
  /**
    * Set method for private instance variable <code>expectedReturnRate</code>.
    *
    * @param expectedReturnRate the value to set for the instance variable <code>expectedReturnRate</code>.
	  @Perm(requires="share(expectedReturnRate) in ALIVE", 
	 ensures= "share(expectedReturnRate) in ALIVE")

    */
  public void setExpectedReturnRate(double expectedReturnRate) {
    this.expectedReturnRate = expectedReturnRate;
  }
  /**
    * Accessor method for private instance variable <code>volatility</code>.
    *
    * @return Value of instance variable <code>volatility</code>.
    
    @Perm(requires="pure(volatility) in ALIVE", 
 	ensures= "pure(volatility) in ALIVE")

    */
  public double getvolatility() {
    return(this.volatility);
  }
  /**
    * Set method for private instance variable <code>volatility</code>.
    *
    * @param volatility the value to set for the instance variable <code>volatility</code>.
    @Perm(requires="share(volatility) in ALIVE", 
 ensures= "share(volatility) in ALIVE")
    */
  public void setVolatility(double volatility) {
    this.volatility = volatility;
  }
  /**
    * Accessor method for private instance variable <code>nTimeSteps</code>.
    *
    * @return Value of instance variable <code>nTimeSteps</code>.
   @Perm(requires="pure(nTimeSteps) in ALIVE", 
 	ensures= "pure(nTimeSteps) in ALIVE")
    */
  public int getnTimeSteps() {
    return(this.nTimeSteps);
  }
  /**
    * Set method for private instance variable <code>nTimeSteps</code>.
    *
    * @param nTimeSteps the value to set for the instance variable <code>nTimeSteps</code>.
   @Perm(requires="share(nTimeSteps) in ALIVE", 
 ensures= "share(nTimeSteps) in ALIVE")
    */
  public void setnTimeSteps(int nTimeSteps) {
    this.nTimeSteps = nTimeSteps;
  }
  /**
    * Accessor method for private instance variable <code>pathStartValue</code>.
    *
    * @return Value of instance variable <code>pathStartValue</code>.
  @Perm(requires="pure(pathStartValue) in ALIVE", 
 ensures= "pure(pathStartValue) in ALIVE")
    */
  public double getpathStartValue() {
    return(this.pathStartValue);
  }
  /**
    * Set method for private instance variable <code>pathStartValue</code>.
    *
    * @param pathStartValue the value to set for the instance variable <code>pathStartValue</code>.
	 @Perm(requires="share(pathStartValue) in ALIVE", 
	 ensures= "share(pathStartValue) in ALIVE")

    */
  public void setpathStartValue(double pathStartValue) {
    this.pathStartValue = pathStartValue;
  }
  //------------------------------------------------------------------------
}
