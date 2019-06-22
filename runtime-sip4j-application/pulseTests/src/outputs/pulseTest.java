package outputs;
import edu.cmu.cs.plural.annot.*;

@ClassStates({@State(name = "alive")})
class JGFMonteCarloBenchSizeA {
@Perm(ensures="unique(this) in alive")
JGFMonteCarloBenchSizeA() {   }

@Perm(requires="unique(this) in alive",
ensures="unique(this) in alive")
  void main(String argv[]) {
 
} 

}ENDOFCLASS

@ClassStates({@State(name = "alive")})

class JGFMonteCarloBench {
@Perm(ensures="unique(this) in alive")
JGFMonteCarloBench() {   }

@Perm(requires="unique(this) in alive",
ensures="unique(this) in alive")
 public void JGFrun(int size) {
 
} 
@Perm(requires="share(this) in alive",
ensures="share(this) in alive")
 public void JGFsetsize(int size) {
 
} 
@Perm(requires="unique(this) in alive",
ensures="unique(this) in alive")
 public void JGFinitialise() {
 
} 
@Perm(requires="unique(this) in alive",
ensures="unique(this) in alive")
 public void JGFapplication() {
 
} 
@Perm(requires="pure(this) in alive",
ensures="pure(this) in alive")
 public void JGFvalidate() {
 
} 
@Perm(requires="unique(this) in alive",
ensures="unique(this) in alive")
 public void JGFtidyup() {
 
} 

}ENDOFCLASS

@ClassStates({@State(name = "alive")})

class CallAppDemo {
@Perm(ensures="unique(this) in alive")
CallAppDemo() {   }

@Perm(requires="unique(this) in alive",
ensures="unique(this) in alive")
 public void initialise() {
 
} 
@Perm(requires="share(this) in alive",
ensures="share(this) in alive")
 public void presults() {
 
} 
@Perm(requires="unique(this) in alive",
ensures="unique(this) in alive")
 public void runiters() {
 
} 

}ENDOFCLASS

@ClassStates({@State(name = "alive")})

class AppDemo {
@Perm(ensures="unique(this) in alive")
AppDemo() {   }

@Perm(requires="unique(this) in alive",
ensures="unique(this) in alive")
 public void initSerial() {
 
} 
@Perm(requires="unique(this) in alive",
ensures="unique(this) in alive")
 private void initTasks(int nRunsMC) {
 
} 
@Perm(requires="share(this) in alive",
ensures="share(this) in alive")
 public void processSerial() {
 
} 
@Perm(requires="share(this) in alive",
ensures="share(this) in alive")
 private void processResults() {
 
} 
@Perm(requires="unique(this) in alive",
ensures="unique(this) in alive")
 public void runSerial() {
 
} 
@Perm(requires="pure(this) in alive",
ensures="pure(this) in alive")
 public String getdataDirname() {
 return null;
 
} 
@Perm(requires="full(this) in alive",
ensures="full(this) in alive")
 public void setdataDirname(String dataDirname) {
 
} 
@Perm(requires="pure(this) in alive",
ensures="pure(this) in alive")
 public String getdataFilename() {
 return null;
 
} 
@Perm(requires="full(this) in alive",
ensures="full(this) in alive")
 public void setdataFilename(String dataFilename) {
 
} 
@Perm(requires="pure(this) in alive",
ensures="pure(this) in alive")
 public int getnTimeStepsMC() {
 return 0;
 
} 
@Perm(requires="full(this) in alive",
ensures="full(this) in alive")
 public void setnTimeStepsMC(int nTimeStepsMC) {
 
} 
@Perm(requires="pure(this) in alive",
ensures="pure(this) in alive")
 public int getnRunsMC() {
 return 0;
 
} 
@Perm(requires="full(this) in alive",
ensures="full(this) in alive")
 public void setnRunsMC(int nRunsMC) {
 
} 
@Perm(requires="pure(this) in alive",
ensures="pure(this) in alive")
 public Vector gettasks() {
 return null;
 
} 
@Perm(requires="share(this) in alive",
ensures="share(this) in alive")
 public void settasks(Vector tasks) {
 
} 
@Perm(requires="pure(this) in alive",
ensures="pure(this) in alive")
 public Vector getresults() {
 return null;
 
} 
@Perm(requires="share(this) in alive",
ensures="share(this) in alive")
 public void setresults(Vector results) {
 
} 

}ENDOFCLASS

@ClassStates({@State(name = "alive")})

class Universal {
@Perm(ensures="unique(this) in alive")
Universal() {   }

@Perm(requires="full(this) in alive",
ensures="full(this) in alive")
 public void setprompt(String prompt) {
 
} 
@Perm(requires="full(this) in alive",
ensures="full(this) in alive")
 public void setDEBUG(boolean DEBUG) {
 
} 
@Perm(requires="pure(this) in alive",
ensures="pure(this) in alive")
 public void dbgPrintln(String s) {
 
} 
@Perm(requires="pure(this) in alive",
ensures="pure(this) in alive")
 public void errPrintln(String s) {
 
} 
@Perm(requires="pure(this) in alive",
ensures="pure(this) in alive")
 public boolean getDEBUG() {
 return 0;
 
} 
@Perm(requires="pure(this) in alive",
ensures="pure(this) in alive")
 public boolean getUNIVERSALDEBUG() {
 return 0;
 
} 
@Perm(requires="full(this) in alive",
ensures="full(this) in alive")
 public void setUNIVERSALDEBUG(boolean UNIVERSAL_DEBUG) {
 
} 
@Perm(requires="pure(this) in alive",
ensures="pure(this) in alive")
 public String getprompt() {
 return null;
 
} 
@Perm(requires="pure(this) in alive",
ensures="pure(this) in alive")
 public void dbgPrint(String s) {
 
} 
@Perm(requires="pure(this) in alive",
ensures="pure(this) in alive")
 public void errPrint(String s) {
 
} 

}ENDOFCLASS

@ClassStates({@State(name = "alive")})

class PathId {
@Perm(ensures="unique(this) in alive")
PathId() {   }

@Perm(requires="pure(this) in alive",
ensures="pure(this) in alive")
 public void dbgDumpFields() {
 
} 
@Perm(requires="share(this) in alive",
ensures="share(this) in alive")
 public void copyInstanceVariables(PathId obj) {
 
} 
@Perm(requires="pure(this) in alive",
ensures="pure(this) in alive")
 public double getdTime() {
 return 0;
 
} 
@Perm(requires="pure(this) in alive",
ensures="pure(this) in alive")
 public String getname() {
 return null;
 
} 
@Perm(requires="pure(this) in alive",
ensures="pure(this) in alive")
 public int getstartDate() {
 return 0;
 
} 
@Perm(requires="pure(this) in alive",
ensures="pure(this) in alive")
 public int getendDate() {
 return 0;
 
} 
@Perm(requires="share(this) in alive",
ensures="share(this) in alive")
 public void setname(String name) {
 
} 
@Perm(requires="share(this) in alive",
ensures="share(this) in alive")
 public void setstartDate(int startDate) {
 
} 
@Perm(requires="share(this) in alive",
ensures="share(this) in alive")
 public void setendDate(int endDate) {
 
} 
@Perm(requires="share(this) in alive",
ensures="share(this) in alive")
 public void setdTime(double dTime) {
 
} 

}ENDOFCLASS

@ClassStates({@State(name = "alive")})

class RatePath {
@Perm(ensures="unique(this) in alive")
RatePath() {   }

@Perm(requires="share(this) in alive",
ensures="share(this) in alive")
 public ReturnPath getReturnCompounded() {
 return null;
 
} 
@Perm(requires="share(this) in alive",
ensures="share(this) in alive")
 public ReturnPath getReturnNonCompounded() {
 return null;
 
} 
@Perm(requires="unique(this) in alive",
ensures="unique(this) in alive")
 private void readRatesFile(String dirName, String filename) {
 
} 
@Perm(requires="pure(this) in alive",
ensures="pure(this) in alive")
 public double getEndPathValue() {
 return 0;
 
} 
@Perm(requires="pure(this) in alive",
ensures="pure(this) in alive")
 public double getPathValue(int index) {
 return 0;
 
} 
@Perm(requires="share(this) in alive",
ensures="share(this) in alive")
 public void incpathValue(double[] operandPath) {
 
} 
@Perm(requires="pure(this) in alive",
ensures="pure(this) in alive")
 public double[] getpathValue() {
 return null;
 
} 
@Perm(requires="share(this) in alive",
ensures="share(this) in alive")
 public void setpathValue(double[] pathValue) {
 
} 
@Perm(requires="pure(this) in alive",
ensures="pure(this) in alive")
 public int[] getpathDate() {
 return null;
 
} 
@Perm(requires="share(this) in alive",
ensures="share(this) in alive")
 public void setpathDate(int[] pathDate) {
 
} 

}ENDOFCLASS

@ClassStates({@State(name = "alive")})

class ReturnPath {
@Perm(ensures="unique(this) in alive")
ReturnPath() {   }

@Perm(requires="share(this) in alive",
ensures="share(this) in alive")
 public void estimatePath() {
 
} 
@Perm(requires="share(this) in alive",
ensures="share(this) in alive")
 public void computeMean() {
 
} 
@Perm(requires="share(this) in alive",
ensures="share(this) in alive")
 public void computeVariance() {
 
} 
@Perm(requires="share(this) in alive",
ensures="share(this) in alive")
 public void computeExpectedReturnRate() {
 
} 
@Perm(requires="share(this) in alive",
ensures="share(this) in alive")
 public void computeVolatility() {
 
} 
@Perm(requires="pure(this) in alive",
ensures="pure(this) in alive")
 public void dbgDumpFields() {
 
} 
@Perm(requires="pure(this) in alive",
ensures="pure(this) in alive")
 public double getexpectedReturnRate() {
 return 0;
 
} 
@Perm(requires="pure(this) in alive",
ensures="pure(this) in alive")
 public double getvolatility() {
 return 0;
 
} 
@Perm(requires="pure(this) in alive",
ensures="pure(this) in alive")
 public int getreturnDefinition() {
 return 0;
 
} 
@Perm(requires="pure(this) in alive",
ensures="pure(this) in alive")
 public double getvolatility2() {
 return 0;
 
} 
@Perm(requires="pure(this) in alive",
ensures="pure(this) in alive")
 public double[] getpathValue() {
 return null;
 
} 
@Perm(requires="full(this) in alive",
ensures="full(this) in alive")
 public void setpathValue(double[] pathValue) {
 
} 
@Perm(requires="pure(this) in alive",
ensures="pure(this) in alive")
 public int getnPathValue() {
 return 0;
 
} 
@Perm(requires="full(this) in alive",
ensures="full(this) in alive")
 public void setnPathValue(int nPathValue) {
 
} 
@Perm(requires="full(this) in alive",
ensures="full(this) in alive")
 public void setreturnDefinition(int returnDefinition) {
 
} 
@Perm(requires="share(this) in alive",
ensures="share(this) in alive")
 public void setexpectedReturnRate(double expectedReturnRate) {
 
} 
@Perm(requires="share(this) in alive",
ensures="share(this) in alive")
 public void setvolatility(double volatility) {
 
} 
@Perm(requires="share(this) in alive",
ensures="share(this) in alive")
 public void setvolatility2(double volatility2) {
 
} 
@Perm(requires="pure(this) in alive",
ensures="pure(this) in alive")
 public double getmean() {
 return 0;
 
} 
@Perm(requires="share(this) in alive",
ensures="share(this) in alive")
 public void setmean(double mean) {
 
} 
@Perm(requires="pure(this) in alive",
ensures="pure(this) in alive")
 public double getvariance() {
 return 0;
 
} 
@Perm(requires="share(this) in alive",
ensures="share(this) in alive")
 public void setvariance(double variance) {
 
} 

}ENDOFCLASS

@ClassStates({@State(name = "alive")})

class MonteCarloPath {
@Perm(ensures="unique(this) in alive")
MonteCarloPath() {   }

@Perm(requires="share(this) in alive",
ensures="share(this) in alive")
 private void copyInstanceVariables(ReturnPath obj) {
 
} 
@Perm(requires="share(this) in alive",
ensures="share(this) in alive")
 public void setreturnDefinition(int returnDefinition) {
 
} 
@Perm(requires="share(this) in alive",
ensures="share(this) in alive")
 public void setexpectedReturnRate(double expectedReturnRate) {
 
} 
@Perm(requires="share(this) in alive",
ensures="share(this) in alive")
 public void setvolatility(double volatility) {
 
} 
@Perm(requires="share(this) in alive",
ensures="share(this) in alive")
 public void setnTimeSteps(int nTimeSteps) {
 
} 
@Perm(requires="share(this) in alive",
ensures="share(this) in alive")
 public void setpathStartValue(double pathStartValue) {
 
} 
@Perm(requires="share(this) in alive",
ensures="share(this) in alive")
 public void setpathValue(double[] pathValue) {
 
} 
@Perm(requires="share(this) in alive",
ensures="share(this) in alive")
 public void setfluctuations(double[] fluctuations) {
 
} 
@Perm(requires="share(this) in alive",
ensures="share(this) in alive")
 public void computeFluctuationsGaussian(long randomSeed) {
 
} 
@Perm(requires="share(this) in alive",
ensures="share(this) in alive")
 public void computePathValue(double startValue) {
 
} 
@Perm(requires="pure(this) in alive",
ensures="pure(this) in alive")
 public double[] getpathValue() {
 return null;
 
} 
@Perm(requires="pure(this) in alive",
ensures="pure(this) in alive")
 public int getnTimeSteps() {
 return 0;
 
} 
@Perm(requires="pure(this) in alive",
ensures="pure(this) in alive")
 public double[] getfluctuations() {
 return null;
 
} 
@Perm(requires="pure(this) in alive",
ensures="pure(this) in alive")
 public int getreturnDefinition() {
 return 0;
 
} 
@Perm(requires="pure(this) in alive",
ensures="pure(this) in alive")
 public double getexpectedReturnRate() {
 return 0;
 
} 
@Perm(requires="pure(this) in alive",
ensures="pure(this) in alive")
 public double getvolatility() {
 return 0;
 
} 
@Perm(requires="pure(this) in alive",
ensures="pure(this) in alive")
 public double getpathStartValue() {
 return 0;
 
} 
@Perm(requires="pure(this) in alive",
ensures="pure(this) in alive")
 public void writeFile(String dirName, String filename) {
 
} 

 public RatePath getRatePath() {
 return null;
 
} 
@Perm(requires="share(this) in alive",
ensures="share(this) in alive")
 public void computeFluctuationsGaussianOverload() {
 
} 

}ENDOFCLASS

@ClassStates({@State(name = "alive")})

class ToInitAllTasks {
@Perm(ensures="unique(this) in alive")
ToInitAllTasks() {   }

@Perm(requires="pure(this) in alive",
ensures="pure(this) in alive")
 public String getname() {
 return null;
 
} 
@Perm(requires="pure(this) in alive",
ensures="pure(this) in alive")
 public int getstartDate() {
 return 0;
 
} 
@Perm(requires="pure(this) in alive",
ensures="pure(this) in alive")
 public int getendDate() {
 return 0;
 
} 
@Perm(requires="pure(this) in alive",
ensures="pure(this) in alive")
 public double getdTime() {
 return 0;
 
} 
@Perm(requires="pure(this) in alive",
ensures="pure(this) in alive")
 public int getreturnDefinition() {
 return 0;
 
} 
@Perm(requires="pure(this) in alive",
ensures="pure(this) in alive")
 public double getexpectedReturnRate() {
 return 0;
 
} 
@Perm(requires="pure(this) in alive",
ensures="pure(this) in alive")
 public double getvolatility() {
 return 0;
 
} 
@Perm(requires="pure(this) in alive",
ensures="pure(this) in alive")
 public int getnTimeSteps() {
 return 0;
 
} 
@Perm(requires="pure(this) in alive",
ensures="pure(this) in alive")
 public double getpathStartValue() {
 return 0;
 
} 
@Perm(requires="pure(this) in alive",
ensures="pure(this) in alive")
 public String getheader() {
 return null;
 
} 
@Perm(requires="full(this) in alive",
ensures="full(this) in alive")
 public void setheader(String header) {
 
} 
@Perm(requires="full(this) in alive",
ensures="full(this) in alive")
 public void setname(String name) {
 
} 
@Perm(requires="full(this) in alive",
ensures="full(this) in alive")
 public void setstartDate(int startDate) {
 
} 
@Perm(requires="full(this) in alive",
ensures="full(this) in alive")
 public void setendDate(int endDate) {
 
} 
@Perm(requires="full(this) in alive",
ensures="full(this) in alive")
 public void setDTime(double dTime) {
 
} 
@Perm(requires="full(this) in alive",
ensures="full(this) in alive")
 public void setReturnDefinition(int returnDefinition) {
 
} 
@Perm(requires="full(this) in alive",
ensures="full(this) in alive")
 public void setExpectedReturnRate(double expectedReturnRate) {
 
} 
@Perm(requires="full(this) in alive",
ensures="full(this) in alive")
 public void setVolatility(double volatility) {
 
} 
@Perm(requires="full(this) in alive",
ensures="full(this) in alive")
 public void setnTimeSteps(int nTimeSteps) {
 
} 
@Perm(requires="full(this) in alive",
ensures="full(this) in alive")
 public void setpathStartValue(double pathStartValue) {
 
} 

}ENDOFCLASS

@ClassStates({@State(name = "alive")})

class ToResult {
@Perm(ensures="unique(this) in alive")
ToResult() {   }

@Perm(requires="pure(this) in alive",
ensures="pure(this) in alive")
 public double getexpectedReturnRate() {
 return 0;
 
} 
@Perm(requires="pure(this) in alive",
ensures="pure(this) in alive")
 public double getvolatility() {
 return 0;
 
} 
@Perm(requires="pure(this) in alive",
ensures="pure(this) in alive")
 public String toString() {
 return null;
 
} 
@Perm(requires="pure(this) in alive",
ensures="pure(this) in alive")
 public String getheader() {
 return null;
 
} 
@Perm(requires="full(this) in alive",
ensures="full(this) in alive")
 public void setheader(String header) {
 
} 
@Perm(requires="full(this) in alive",
ensures="full(this) in alive")
 public void setexpectedReturnRate(double expectedReturnRate) {
 
} 
@Perm(requires="full(this) in alive",
ensures="full(this) in alive")
 public void setvolatility(double volatility) {
 
} 
@Perm(requires="pure(this) in alive",
ensures="pure(this) in alive")
 public double getVolatility2() {
 return 0;
 
} 
@Perm(requires="full(this) in alive",
ensures="full(this) in alive")
 public void setvolatility2(double volatility2) {
 
} 
@Perm(requires="pure(this) in alive",
ensures="pure(this) in alive")
 public double getfinalStockPrice() {
 return 0;
 
} 
@Perm(requires="full(this) in alive",
ensures="full(this) in alive")
 public void setfinalStockPrice(double finalStockPrice) {
 
} 
@Perm(requires="pure(this) in alive",
ensures="pure(this) in alive")
 public double[] getpathValue() {
 return null;
 
} 
@Perm(requires="full(this) in alive",
ensures="full(this) in alive")
 public void setpathValue(double[] pathValue) {
 
} 

}ENDOFCLASS

@ClassStates({@State(name = "alive")})

class PriceStock {
@Perm(ensures="unique(this) in alive")
PriceStock() {   }

@Perm(requires="share(this) in alive",
ensures="share(this) in alive")
 public void setInitAllTasks(Object obj) {
 
} 
@Perm(requires="share(this) in alive",
ensures="share(this) in alive")
 public void setTask(Object obj) {
 
} 
@Perm(requires="share(this) in alive",
ensures="share(this) in alive")
 public void run() {
 
} 
@Perm(requires="pure(this) in alive",
ensures="pure(this) in alive")
 public Object getResult() {
 return null;
 
} 

}ENDOFCLASS

@ClassStates({@State(name = "alive")})

class ToTask {
@Perm(ensures="unique(this) in alive")
ToTask() {   }

@Perm(requires="pure(this) in alive",
ensures="pure(this) in alive")
 public String getheader() {
 return null;
 
} 
@Perm(requires="pure(this) in alive",
ensures="pure(this) in alive")
 public long getrandomSeed() {
 return 0;
 
} 
@Perm(requires="full(this) in alive",
ensures="full(this) in alive")
 public void setheader(String header) {
 
} 
@Perm(requires="full(this) in alive",
ensures="full(this) in alive")
 public void setrandomSeed(long randomSeed) {
 
} 

}ENDOFCLASS

@ClassStates({@State(name = "alive")})

class DemoException {
@Perm(ensures="unique(this) in alive")
DemoException() {   }


}ENDOFCLASS

@ClassStates({@State(name = "alive")})

class JGFInstrumentor {
@Perm(ensures="unique(this) in alive")
JGFInstrumentor() {   }

@Perm(requires="share(this) in alive",
ensures="share(this) in alive")
  void addTimer(String name) {
 
} 
@Perm(requires="share(this) in alive",
ensures="share(this) in alive")
  void startTimer(String name) {
 
} 
@Perm(requires="share(this) in alive",
ensures="share(this) in alive")
  void stopTimer(String name) {
 
} 
@Perm(requires="share(this) in alive",
ensures="share(this) in alive")
  void addOpsToTimer(String name, double count) {
 
} 
@Perm(requires="share(this) in alive",
ensures="share(this) in alive")
  double readTimer(String name) {
 return 0;
 
} 
@Perm(requires="share(this) in alive",
ensures="share(this) in alive")
  void resetTimer(String name) {
 
} 
@Perm(requires="share(this) in alive",
ensures="share(this) in alive")
  void printTimer(String name) {
 
} 
@Perm(requires="share(this) in alive",
ensures="share(this) in alive")
  void printperfTimer(String name) {
 
} 
@Perm(requires="share(this) in alive",
ensures="share(this) in alive")
  void storeData(String name, Object obj) {
 
} 
@Perm(requires="share(this) in alive",
ensures="share(this) in alive")
  void retrieveData(String name, Object obj) {
 
} 

  void printHeader(int section, int size) {
 
} 
@Perm(requires="unique(this) in alive",
ensures="unique(this) in alive")
  void main(String argv[]) {
 
} 

}ENDOFCLASS

@ClassStates({@State(name = "alive")})

class JGFTimer {
@Perm(ensures="unique(this) in alive")
JGFTimer() {   }

@Perm(requires="share(this) in alive",
ensures="share(this) in alive")
 public void start() {
 
} 
@Perm(requires="share(this) in alive",
ensures="share(this) in alive")
 public void stop() {
 
} 
@Perm(requires="share(this) in alive",
ensures="share(this) in alive")
 public void addops(double count) {
 
} 
@Perm(requires="share(this) in alive",
ensures="share(this) in alive")
 public void reset() {
 
} 
@Perm(requires="share(this) in alive",
ensures="share(this) in alive")
 public void print() {
 
} 
@Perm(requires="pure(this) in alive",
ensures="pure(this) in alive")
 public double perf() {
 return 0;
 
} 
@Perm(requires="pure(this) in alive",
ensures="pure(this) in alive")
 public void printperf() {
 
} 
@Perm(requires="pure(this) in alive",
ensures="pure(this) in alive")
 public void longprint() {
 
} 

}ENDOFCLASS

@ClassStates({@State(name = "alive")})

class test {
@Perm(ensures="unique(this) in alive")
test() {   }

@Perm(requires="unique(this) in alive",
ensures="unique(this) in alive")
 public void createObject() {
 
} 
@Perm(requires="pure(this) in alive",
ensures="pure(this) in alive")
 public void readA() {
 
} 
@Perm(requires="unique(this) in alive",
ensures="unique(this) in alive")
  void main(String[] arg) {
 
} 

}ENDOFCLASS

@ClassStates({@State(name = "alive")})

class Utilities {
@Perm(ensures="unique(this) in alive")
Utilities() {   }

@Perm(requires="unique(this) in alive",
ensures="unique(this) in alive")
  String which(String executable, String pathEnv) {
 return null;
 
} 
@Perm(requires="immutable(this) in alive",
ensures="immutable(this) in alive")
  String[] splitString(String splitChar, String arg) {
 return null;
 
} 

  String joinString(String joinChar, String stringArray[]) {
 return null;
 
} 

  String joinStringOverloaded(String joinChar, String stringArray[], int index) {
 return null;
 
} 

}ENDOFCLASS

