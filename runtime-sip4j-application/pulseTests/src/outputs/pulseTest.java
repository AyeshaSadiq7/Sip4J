package outputs;
import edu.cmu.cs.plural.annot.*;

@ClassStates({@State(name = "alive")})
class SampleAction {
@Perm(ensures="unique(this) in alive")
SampleAction() {   }

@Perm(requires="share(this) in alive",
ensures="share(this) in alive")
 public void run(IAction action) {
 
} 

 public void selectionChanged(IAction action, ISelection selection) {
 
} 

 public void dispose() {
 
} 
@Perm(requires="share(this) in alive",
ensures="share(this) in alive")
 public void init(IWorkbenchWindow window) {
 
} 

}ENDOFCLASS

@ClassStates({@State(name = "alive")})

class JMLAnnotatedJavaClass {
@Perm(ensures="unique(this) in alive")
JMLAnnotatedJavaClass() {   }

@Perm(requires="unique(this) in alive",
ensures="unique(this) in alive")
 public String translateJMLAnnotationsToPlural(String JProgram) {
 return null;
 
} 
@Perm(requires="unique(this) in alive",
ensures="unique(this) in alive")
 private String translateClassSpecifications(String JProgram) {
 return null;
 
} 
@Perm(requires="unique(this) in alive",
ensures="unique(this) in alive")
 private void parseAndStoreJMLAnnotation(String JMLAnnotation) {
 
} 
@Perm(requires="unique(this) in alive",
ensures="unique(this) in alive")
 private String translateMethodSpecification(String JProgram) {
 return null;
 
} 

 public String getInputStream(ICompilationUnit unit) {
 return null;
 
} 

 public String readFileAsString(String filePath) {
 return null;
 
} 

}ENDOFCLASS

@ClassStates({@State(name = "alive")})

class PluralParser {
@Perm(ensures="unique(this) in alive")
PluralParser() {   }

@Perm(requires="unique(this) in alive",
ensures="unique(this) in alive")
  void jmlSpecifications() {
 
} 
@Perm(requires="unique(this) in alive",
ensures="unique(this) in alive")
  void jmlClassSpecifications() {
 
} 
@Perm(requires="unique(this) in alive",
ensures="unique(this) in alive")
  void jmlGhostDeclaration() {
 
} 
@Perm(requires="unique(this) in alive",
ensures="unique(this) in alive")
  void jmlGhostInv() {
 
} 
@Perm(requires="unique(this) in alive",
ensures="unique(this) in alive")
  void jmlMethodSpecification() {
 
} 
@Perm(requires="unique(this) in alive",
ensures="unique(this) in alive")
  void jmlRequires() {
 
} 
@Perm(requires="unique(this) in alive",
ensures="unique(this) in alive")
  void jmlReq() {
 
} 
@Perm(requires="unique(this) in alive",
ensures="unique(this) in alive")
  void jmlOrReq() {
 
} 
@Perm(requires="unique(this) in alive",
ensures="unique(this) in alive")
  void jmlLessThanEqualReq() {
 
} 
@Perm(requires="unique(this) in alive",
ensures="unique(this) in alive")
  void jmlAssign() {
 
} 
@Perm(requires="unique(this) in alive",
ensures="unique(this) in alive")
  void jmlEnsures() {
 
} 
@Perm(requires="unique(this) in alive",
ensures="unique(this) in alive")
  void jmlEns() {
 
} 
@Perm(requires="unique(this) in alive",
ensures="unique(this) in alive")
  void jmlOldEns() {
 
} 
@Perm(requires="unique(this) in alive",
ensures="unique(this) in alive")
  void specifications() {
 
} 
@Perm(requires="unique(this) in alive",
ensures="unique(this) in alive")
  void perm() {
 
} 
@Perm(requires="unique(this) in alive",
ensures="unique(this) in alive")
  void requiresensuresClause() {
 
} 
@Perm(requires="unique(this) in alive",
ensures="unique(this) in alive")
  void requiresClause() {
 
} 
@Perm(requires="unique(this) in alive",
ensures="unique(this) in alive")
  void reaccesspermissionTypestates() {
 
} 
@Perm(requires="immutable(this) in alive",
ensures="immutable(this) in alive")
  AccesspermissionReturn accesspermission() {
 return null;
 
} 
@Perm(requires="unique(this) in alive",
ensures="unique(this) in alive")
  TypestateReturn typestate() {
 return null;
 
} 
@Perm(requires="unique(this) in alive",
ensures="unique(this) in alive")
  void ensuresclause() {
 
} 
@Perm(requires="unique(this) in alive",
ensures="unique(this) in alive")
  void enaccesspermissiontypestates() {
 
} 
@Perm(requires="unique(this) in alive",
ensures="unique(this) in alive")
  void attype() {
 
} 
@Perm(requires="immutable(this) in alive",
ensures="immutable(this) in alive")
  AtApPermissionReturn atappermission() {
 return null;
 
} 
@Perm(requires="unique(this) in alive",
ensures="unique(this) in alive")
  void usevalue() {
 
} 
@Perm(requires="unique(this) in alive",
ensures="unique(this) in alive")
  void cases() {
 
} 
@Perm(requires="unique(this) in alive",
ensures="unique(this) in alive")
  void other() {
 
} 
@Perm(requires="unique(this) in alive",
ensures="unique(this) in alive")
  void classstates() {
 
} 
@Perm(requires="unique(this) in alive",
ensures="unique(this) in alive")
  void startClassstates() {
 
} 
@Perm(requires="unique(this) in alive",
ensures="unique(this) in alive")
  void state() {
 
} 
@Perm(requires="unique(this) in alive",
ensures="unique(this) in alive")
  void invariant() {
 
} 
@Perm(requires="unique(this) in alive",
ensures="unique(this) in alive")
  void condition() {
 
} 
@Perm(requires="unique(this) in alive",
ensures="unique(this) in alive")
  void endclassstates() {
 
} 
@Perm(requires="unique(this) in alive",
ensures="unique(this) in alive")
  void refine() {
 
} 
@Perm(requires="unique(this) in alive",
ensures="unique(this) in alive")
  void states() {
 
} 
@Perm(requires="unique(this) in alive",
ensures="unique(this) in alive")
  void dimension() {
 
} 
@Perm(requires="unique(this) in alive",
ensures="unique(this) in alive")
  void value() {
 
} 
@Perm(requires="unique(this) in alive",
ensures="unique(this) in alive")
  void item() {
 
} 
@Perm(ensures="none(this) in alive")
 public String[] getTokenNames() {
 return null;
 
} 

 public String getGrammarFileName() {
 return null;
 
} 

}ENDOFCLASS

@ClassStates({@State(name = "alive")})

class EJmlSpecification {
@Perm(ensures="unique(this) in alive")
EJmlSpecification() {   }

@Perm(requires="share(this) in alive",
ensures="share(this) in alive")
  void setDimensionName(String str) {
 
} 
@Perm(requires="share(this) in alive",
ensures="share(this) in alive")
  void setDimensionValues(int low, int high) {
 
} 
@Perm(requires="share(this) in alive",
ensures="share(this) in alive")
  void addRequires(String str) {
 
} 
@Perm(requires="share(this) in alive",
ensures="share(this) in alive")
  void setPerm(String str) {
 
} 
@Perm(requires="share(this) in alive",
ensures="share(this) in alive")
  void setEnsures(String str) {
 
} 
@Perm(requires="share(this) in alive",
ensures="share(this) in alive")
  String JmlClassSpec2PluralClassSpec() {
 return null;
 
} 
@Perm(requires="unique(this) in alive",
ensures="unique(this) in alive")
  void reset() {
 
} 
@Perm(requires="share(this) in alive",
ensures="share(this) in alive")
  String JmlMethodSpec2PluralMethodSpec() {
 return null;
 
} 
@Perm(requires="share(this) in alive",
ensures="share(this) in alive")
  String moreRequires() {
 return null;
 
} 
@Perm(requires="pure(this) in alive",
ensures="pure(this) in alive")
  String getPerm() {
 return null;
 
} 
@Perm(requires="share(this) in alive",
ensures="share(this) in alive")
  String determineEnsures(String req) {
 return null;
 
} 
@Perm(requires="share(this) in alive",
ensures="share(this) in alive")
  String oneRequires() {
 return null;
 
} 
@Perm(requires="share(this) in alive",
ensures="share(this) in alive")
  String noRequires() {
 return null;
 
} 

}ENDOFCLASS

@ClassStates({@State(name = "alive")})

class EGhost {
@Perm(ensures="unique(this) in alive")
EGhost() {   }

@Perm(requires="share(this) in alive",
ensures="share(this) in alive")
 public void setDimensionName(String str) {
 
} 
@Perm(requires="share(this) in alive",
ensures="share(this) in alive")
 public void setDimensionValues(int low, int high) {
 
} 
@Perm(requires="pure(this) in alive",
ensures="pure(this) in alive")
 public String getDimensionName() {
 return null;
 
} 
@Perm(requires="pure(this) in alive",
ensures="pure(this) in alive")
 public int getLowValueofInv() {
 return 0;
 
} 
@Perm(requires="pure(this) in alive",
ensures="pure(this) in alive")
 public int getHighValueofInv() {
 return 0;
 
} 

}ENDOFCLASS

@ClassStates({@State(name = "alive")})

class Time {
@Perm(ensures="unique(this) in alive")
Time() {   }

@Perm(requires="share(this) in alive",
ensures="share(this) in alive")
 public String toString() {
 return null;
 
} 

}ENDOFCLASS

@ClassStates({@State(name = "alive")})

class FileReader {
@Perm(ensures="unique(this) in alive")
FileReader() {   }


  String readFile(String pathname) {
 return null;
 
} 

}ENDOFCLASS

@ClassStates({@State(name = "alive")})

class UserSelectedClassesAnalysis {
@Perm(ensures="unique(this) in alive")
UserSelectedClassesAnalysis() {   }


 private CompilationUnit getCompilationUnit(String prog) {
 return null;
 
} 
@Perm(requires="unique(this) in alive",
ensures="unique(this) in alive")
  void analyzeFromCommandLine(LinkedList<String> inputFiles, String strType, String strK) {
 
} 
@Perm(requires="unique(this) in alive",
ensures="unique(this) in alive")
  void callModelCheckerThroughCommandLine() {
 
} 
@Perm(requires="unique(this) in alive",
ensures="unique(this) in alive")
  void printMetrics() {
 
} 
@Perm(requires="share(this) in alive",
ensures="share(this) in alive")
  void printMethodMetrics() {
 
} 
@Perm(requires="share(this) in alive",
ensures="share(this) in alive")
  Time getTime() {
 return null;
 
} 
@Perm(requires="unique(this) in alive",
ensures="unique(this) in alive")
  void CreatePdfSummary_CommandLine() {
 
} 

  void makePdfCommandLine() {
 
} 
@Perm(requires="unique(this) in alive",
ensures="unique(this) in alive")
 public void analyzeFromPlugin(List<ICompilationUnit> compilationUnitList, int test) {
 
} 

 public String getInputStream(ICompilationUnit unit) {
 return null;
 
} 
@Perm(requires="unique(this) in alive",
ensures="unique(this) in alive")
  void callModelCheckerThroughPlugin() {
 
} 
@Perm(requires="unique(this) in alive",
ensures="unique(this) in alive")
  void createPdfSummaryPlugin() {
 
} 

  void makePdfPlugin() {
 
} 

}ENDOFCLASS

@ClassStates({@State(name = "alive")})

class EVMDDSMCGenerator {
@Perm(ensures="unique(this) in alive")
EVMDDSMCGenerator() {   }

@Perm(requires="unique(this) in alive",
ensures="unique(this) in alive")
  void reset() {
 
} 
@Perm(requires="unique(this) in alive",
ensures="unique(this) in alive")
  String modifyConstructorSpecifications(String prog) {
 return null;
 
} 
@Perm(requires="share(this) in alive",
ensures="share(this) in alive")
  EPackage getPkgObject() {
 return null;
 
} 
@Perm(requires="share(this) in alive",
ensures="share(this) in alive")
  void addRequiresAPTS(String ap, String ts) {
 
} 
@Perm(requires="share(this) in alive",
ensures="share(this) in alive")
  void addRequiresParamAPTS(String ap, String ts, String argumentNumber) {
 
} 
@Perm(requires="share(this) in alive",
ensures="share(this) in alive")
  void addEnsuresAPTS(String ap, String ts) {
 
} 
@Perm(requires="unique(this) in alive",
ensures="unique(this) in alive")
  void addEnsuresResultAPTS(String ap, String ts) {
 
} 
@Perm(requires="share(this) in alive",
ensures="share(this) in alive")
  void addEnsuresParamAPTS(String ap, String ts, String argumentNumber) {
 
} 
@Perm(requires="unique(this) in alive",
ensures="unique(this) in alive")
  void addCase() {
 
} 
@Perm(requires="share(this) in alive",
ensures="share(this) in alive")
  void addState(String stateName) {
 
} 
@Perm(requires="share(this) in alive",
ensures="share(this) in alive")
  void addBoolStateInvariant(String variable, String operator, String value) {
 
} 
@Perm(requires="share(this) in alive",
ensures="share(this) in alive")
  void addStateInvariant(String accessPermission, String variable, String state) {
 
} 
@Perm(requires="share(this) in alive",
ensures="share(this) in alive")
  void addDimension(String name) {
 
} 
@Perm(requires="share(this) in alive",
ensures="share(this) in alive")
  void addDimensionValue(String value) {
 
} 

  void addPkgObject(EPackage _pkg) {
 
} 

}ENDOFCLASS

@ClassStates({@State(name = "alive")})

class EPackage {
@Perm(ensures="unique(this) in alive")
EPackage() {   }

@Perm(requires="pure(this) in alive",
ensures="pure(this) in alive")
 public LinkedList<EClass> getClasses() {
 return null;
 
} 
@Perm(requires="unique(this) in alive",
ensures="unique(this) in alive")
 public int getTotalStates() {
 return 0;
 
} 
@Perm(requires="pure(this) in alive",
ensures="pure(this) in alive")
 public int getTotalReachableStates() {
 return 0;
 
} 
@Perm(requires="pure(this) in alive",
ensures="pure(this) in alive")
 public String getSinkStates() {
 return null;
 
} 
@Perm(requires="share(this) in alive",
ensures="share(this) in alive")
 public void setSinkStates(String sinkStates) {
 
} 
@Perm(requires="share(this) in alive",
ensures="share(this) in alive")
 public void setName(String str) {
 
} 
@Perm(requires="unique(this) in alive",
ensures="unique(this) in alive")
 public String getName() {
 return null;
 
} 

}ENDOFCLASS

@ClassStates({@State(name = "alive")})

class EClass {
@Perm(ensures="unique(this) in alive")
EClass() {   }

@Perm(requires="pure(this) in alive",
ensures="pure(this) in alive")
 public LinkedList<EMethod> getMethods() {
 return null;
 
} 
@Perm(requires="unique(this) in alive",
ensures="unique(this) in alive")
 public String getName() {
 return null;
 
} 
@Perm(requires="pure(this) in alive",
ensures="pure(this) in alive")
 public String getSuperClassName() {
 return null;
 
} 
@Perm(requires="pure(this) in alive",
ensures="pure(this) in alive")
 public LinkedList<EField> getFields() {
 return null;
 
} 
@Perm(requires="share(this) in alive",
ensures="share(this) in alive")
 public boolean hasMoreThanOneDimension() {
 return 0;
 
} 
@Perm(requires="pure(this) in alive",
ensures="pure(this) in alive")
 public LinkedList<EDim> getDimensions() {
 return null;
 
} 
@Perm(requires="pure(this) in alive",
ensures="pure(this) in alive")
 public LinkedList<EState> getStates() {
 return null;
 
} 
@Perm(requires="pure(this) in alive",
ensures="pure(this) in alive")
 public int getIndex() {
 return 0;
 
} 
@Perm(requires="share(this) in alive",
ensures="share(this) in alive")
 public EMethod getConstructor() {
 return null;
 
} 
@Perm(requires="pure(this) in alive",
ensures="pure(this) in alive")
 public int findStateIndex(String st) {
 return 0;
 
} 
@Perm(requires="immutable(this) in alive",
ensures="immutable(this) in alive")
 public ArrayList<String> getVariablesofBooleanInvariants() {
 return null;
 
} 
@Perm(requires="immutable(this) in alive",
ensures="immutable(this) in alive")
 public LinkedList<String> getTransitions() {
 return null;
 
} 
@Perm(requires="pure(this) in alive",
ensures="pure(this) in alive")
 public LinkedList<EState> getReachableStates() {
 return null;
 
} 
@Perm(requires="unique(this) in alive",
ensures="unique(this) in alive")
 public int getTotalStates() {
 return 0;
 
} 
@Perm(requires="pure(this) in alive",
ensures="pure(this) in alive")
 public int getTotalReachableStates() {
 return 0;
 
} 
@Perm(requires="immutable(this) in alive",
ensures="immutable(this) in alive")
 public void addClassStatesSpecifications(String annotation) {
 
} 
@Perm(requires="share(this) in alive",
ensures="share(this) in alive")
 public void setName(String str) {
 
} 
@Perm(requires="share(this) in alive",
ensures="share(this) in alive")
 public void setSuperClassName(String str) {
 
} 
@Perm(requires="share(this) in alive",
ensures="share(this) in alive")
 public void addField(EField field) {
 
} 
@Perm(requires="share(this) in alive",
ensures="share(this) in alive")
 public void addMethod(EMethod method) {
 
} 
@Perm(requires="share(this) in alive",
ensures="share(this) in alive")
 public void addState(EState state) {
 
} 
@Perm(requires="share(this) in alive",
ensures="share(this) in alive")
 public void addDimension(EDim dim) {
 
} 
@Perm(requires="share(this) in alive",
ensures="share(this) in alive")
 public void setIndex(int classIndex) {
 
} 
@Perm(requires="share(this) in alive",
ensures="share(this) in alive")
 public void createObject() {
 
} 
@Perm(requires="pure(this) in alive",
ensures="pure(this) in alive")
 public int getLastObjectIndex() {
 return 0;
 
} 

}ENDOFCLASS

@ClassStates({@State(name = "alive")})

class EMethod {
@Perm(ensures="unique(this) in alive")
EMethod() {   }

@Perm(requires="unique(this) in alive",
ensures="unique(this) in alive")
 public String getName() {
 return null;
 
} 
@Perm(requires="immutable(this) in alive",
ensures="immutable(this) in alive")
 public LinkedList<ESpecification> getRequiresAPTS() {
 return null;
 
} 
@Perm(requires="immutable(this) in alive",
ensures="immutable(this) in alive")
 public LinkedList<ESpecification> getEnsuresAPTS() {
 return null;
 
} 
@Perm(requires="unique(this) in alive",
ensures="unique(this) in alive")
 public String getIdentifier() {
 return null;
 
} 
@Perm(requires="pure(this) in alive",
ensures="pure(this) in alive")
 public LinkedList<EParameter> getParameters() {
 return null;
 
} 
@Perm(requires="pure(this) in alive",
ensures="pure(this) in alive")
 public int getIndex() {
 return 0;
 
} 
@Perm(requires="pure(this) in alive",
ensures="pure(this) in alive")
 public boolean getRequiresClauseSatisfiability() {
 return 0;
 
} 
@Perm(requires="share(this) in alive",
ensures="share(this) in alive")
 public Boolean isConcurrentMethod() {
 return null;
 
} 
@Perm(requires="share(this) in alive",
ensures="share(this) in alive")
 public void setRequiresClauseSatisfiability(Boolean flag) {
 
} 
@Perm(requires="share(this) in alive",
ensures="share(this) in alive")
 public void setConcurrentMethod(String toMethod) {
 
} 
@Perm(requires="share(this) in alive",
ensures="share(this) in alive")
 public void addSpecifications(String annotation) {
 
} 
@Perm(requires="share(this) in alive",
ensures="share(this) in alive")
 public void setName(String str) {
 
} 
@Perm(requires="share(this) in alive",
ensures="share(this) in alive")
 public void setReturnType(String str) {
 
} 
@Perm(requires="share(this) in alive",
ensures="share(this) in alive")
 public void setIdentifier(String str) {
 
} 
@Perm(requires="share(this) in alive",
ensures="share(this) in alive")
 public void addParameter(EParameter parameter) {
 
} 
@Perm(requires="unique(this) in alive",
ensures="unique(this) in alive")
 public String getReturnType() {
 return null;
 
} 
@Perm(requires="share(this) in alive",
ensures="share(this) in alive")
 public void setCaseNumber(int x) {
 
} 
@Perm(requires="pure(this) in alive",
ensures="pure(this) in alive")
 public int getCaseNumber() {
 return 0;
 
} 
@Perm(requires="share(this) in alive",
ensures="share(this) in alive")
 public void setIndex(int methodIndex) {
 
} 
@Perm(requires="share(this) in alive",
ensures="share(this) in alive")
 public void setJMLPermission(String Permission) {
 
} 
@Perm(requires="unique(this) in alive",
ensures="unique(this) in alive")
 public String getJMLPermission() {
 return null;
 
} 

}ENDOFCLASS

@ClassStates({@State(name = "alive")})

class ESpecification {
@Perm(ensures="unique(this) in alive")
ESpecification() {   }

@Perm(requires="share(this) in alive",
ensures="share(this) in alive")
 public void setAP(String ap) {
 
} 
@Perm(requires="pure(this) in alive",
ensures="pure(this) in alive")
 public EClass getParentClass() {
 return null;
 
} 
@Perm(requires="pure(this) in alive",
ensures="pure(this) in alive")
 public String getFieldName() {
 return null;
 
} 
@Perm(requires="unique(this) in alive",
ensures="unique(this) in alive")
 public String getTS() {
 return null;
 
} 
@Perm(requires="unique(this) in alive",
ensures="unique(this) in alive")
 public String getAP() {
 return null;
 
} 
@Perm(requires="share(this) in alive",
ensures="share(this) in alive")
 public void setAPTS(String ap, String ts) {
 
} 

 public Object clone() {
 return null;
 
} 

}ENDOFCLASS

@ClassStates({@State(name = "alive")})

class EGeneratedPluralSpecification {
@Perm(ensures="unique(this) in alive")
EGeneratedPluralSpecification() {   }

@Perm(requires="unique(this) in alive",
ensures="unique(this) in alive")
  void createFromCommandLine(String prog, String className) {
 
} 
@Perm(requires="unique(this) in alive",
ensures="unique(this) in alive")
  void createFromPlugin(String prog, String className) {
 
} 

}ENDOFCLASS

@ClassStates({@State(name = "alive")})

class ESMCModel {
@Perm(ensures="unique(this) in alive")
ESMCModel() {   }

@Perm(requires="share(this) in alive",
ensures="share(this) in alive")
  void setK(int k) {
 
} 
@Perm(requires="unique(this) in alive",
ensures="unique(this) in alive")
  void generateSMCmodelCommandLine(int testType) {
 
} 
@Perm(requires="unique(this) in alive",
ensures="unique(this) in alive")
  void Transitions() {
 
} 
@Perm(requires="pure(this) in alive",
ensures="pure(this) in alive")
  String comment(String str) {
 return null;
 
} 
@Perm(requires="unique(this) in alive",
ensures="unique(this) in alive")
  void declarationsAndinitilizations() {
 
} 
@Perm(requires="unique(this) in alive",
ensures="unique(this) in alive")
  void initialize(LinkedList<EClass> _listClasses) {
 
} 
@Perm(requires="unique(this) in alive",
ensures="unique(this) in alive")
  void modelAlias(String className, Integer objectIndex, Integer refIndex) {
 
} 
@Perm(requires="unique(this) in alive",
ensures="unique(this) in alive")
  boolean isClassExist(String className) {
 return 0;
 
} 
@Perm(requires="unique(this) in alive",
ensures="unique(this) in alive")
  void createInstanceInModel(EClass _class, String name, int objectIndex, int J) {
 
} 
@Perm(requires="unique(this) in alive",
ensures="unique(this) in alive")
  void modelPrimePCandMethod(EClass _class, EMethod _method, Integer objectIndex, Integer refIndex) {
 
} 
@Perm(requires="unique(this) in alive",
ensures="unique(this) in alive")
  void startMethod(EClass _class, EMethod _method, Integer objectIndex, Integer refIndex) {
 
} 
@Perm(requires="unique(this) in alive",
ensures="unique(this) in alive")
  void modelPCConstructor(EClass _class, Integer objectIndex, Integer refIndex, EClass _currentClass) {
 
} 
@Perm(requires="unique(this) in alive",
ensures="unique(this) in alive")
  void modelAPs(EClass _class, Integer objectIndex, Integer refIndex) {
 
} 
@Perm(requires="unique(this) in alive",
ensures="unique(this) in alive")
  EClass getClass(String className) {
 return null;
 
} 
@Perm(requires="unique(this) in alive",
ensures="unique(this) in alive")
  int getObjectIndex(EClass _class, String variable) {
 return 0;
 
} 
@Perm(requires="unique(this) in alive",
ensures="unique(this) in alive")
  void modelPCMethod(EClass _class, Integer objectIndex, Integer refIndex) {
 
} 
@Perm(requires="unique(this) in alive",
ensures="unique(this) in alive")
  EClass getFieldClass(EClass _class, String fieldName) {
 return null;
 
} 
@Perm(requires="pure(this) in alive",
ensures="pure(this) in alive")
  int getDimensionIndex(EClass _class, String ts) {
 return 0;
 
} 
@Perm(requires="unique(this) in alive",
ensures="unique(this) in alive")
  void startAPTS(EClass _class, EMethod _method, String ap, String stateName, Integer objectIndex, Integer refIndex) {
 
} 
@Perm(requires="unique(this) in alive",
ensures="unique(this) in alive")
  void startAPTSPARAM(EMethod _method, Integer J) {
 
} 

  void error(String state, String method) {
 
} 
@Perm(requires="unique(this) in alive",
ensures="unique(this) in alive")
  void startPrimeTSPARAM(EMethod method, Integer refIndex) {
 
} 
@Perm(requires="unique(this) in alive",
ensures="unique(this) in alive")
  void starPrimeAP(String ap, EClass _class, Integer objectIndex, Integer refIndex, String stateName) {
 
} 
@Perm(requires="unique(this) in alive",
ensures="unique(this) in alive")
  void modelPrimeConstructor(EClass _class, Integer objectIndex, Integer refIndex) {
 
} 
@Perm(requires="unique(this) in alive",
ensures="unique(this) in alive")
  void modelInheritance(EClass _class, Integer objectIndex, Integer refIndex) {
 
} 
@Perm(requires="unique(this) in alive",
ensures="unique(this) in alive")
  void modelPrimeAPStateInvariants(EClass _class, Integer refIndex, String stateName) {
 
} 
@Perm(requires="unique(this) in alive",
ensures="unique(this) in alive")
  int getClassIndex(String name) {
 return 0;
 
} 
@Perm(requires="share(this) in alive",
ensures="share(this) in alive")
  void modelPrimeAP(String ap, String className, Integer objectIndex, Integer refIndex) {
 
} 
@Perm(requires="pure(this) in alive",
ensures="pure(this) in alive")
  int getAPId(String ap) {
 return 0;
 
} 
@Perm(requires="unique(this) in alive",
ensures="unique(this) in alive")
  void modelEndPCMethod(EClass _class, EMethod _method, Integer objectIndex, Integer refIndex) {
 
} 
@Perm(requires="unique(this) in alive",
ensures="unique(this) in alive")
  void endMethod(EClass _class, EMethod _method, Integer objectIndex, Integer refIndex) {
 
} 
@Perm(requires="unique(this) in alive",
ensures="unique(this) in alive")
  void modelEndPCConstructor(EClass _class, EMethod _method, Integer objectIndex, Integer refIndex, EClass _currentClass) {
 
} 
@Perm(requires="unique(this) in alive",
ensures="unique(this) in alive")
  void modelPrimePCConstructor(EClass _class, Integer objectIndex, Integer refIndex, EClass _currentClass) {
 
} 
@Perm(requires="unique(this) in alive",
ensures="unique(this) in alive")
  void modelPrimePC(EClass _class, Integer objectIndex, Integer refIndex) {
 
} 
@Perm(requires="unique(this) in alive",
ensures="unique(this) in alive")
  void endPrimeAPTS(EClass _class, String methodName, String ap, String stateName, Integer objectIndex, Integer refIndex) {
 
} 
@Perm(requires="unique(this) in alive",
ensures="unique(this) in alive")
  void modelendConstructor(EClass _class, EMethod _method, Integer refIndex) {
 
} 
@Perm(requires="unique(this) in alive",
ensures="unique(this) in alive")
  void updateBoolStateInvariants(EClass _class, String methodName, String stateName, Integer objectIndex) {
 
} 
@Perm(requires="unique(this) in alive",
ensures="unique(this) in alive")
  void updateStateInvariants(EClass _class, String methodName, String stateName, Integer refIndex) {
 
} 
@Perm(requires="unique(this) in alive",
ensures="unique(this) in alive")
  void updateState(String methodName, String state, EClass _class, Integer objectIndex) {
 
} 
@Perm(requires="unique(this) in alive",
ensures="unique(this) in alive")
  void modelState(EClass _class, Integer objectIndex, EMethod _method, String stateName) {
 
} 
@Perm(requires="unique(this) in alive",
ensures="unique(this) in alive")
  void modelStateInvariants(EClass _class, int refIndex, EMethod _method, String stateName) {
 
} 
@Perm(requires="unique(this) in alive",
ensures="unique(this) in alive")
  void modelBoolStateInvariants(EClass _class, Integer objectIndex, String stateName) {
 
} 
@Perm(requires="unique(this) in alive",
ensures="unique(this) in alive")
  void methodsReachability(EClass _class, Integer objectIndex, Integer refIndex) {
 
} 
@Perm(requires="unique(this) in alive",
ensures="unique(this) in alive")
  void modelAP(EClass _class, Integer objectIndex, Integer refIndex, String ap) {
 
} 
@Perm(requires="share(this) in alive",
ensures="share(this) in alive")
  void updateTokens(String ap, String className, Integer objectIndex, Integer refIndex) {
 
} 
@Perm(requires="unique(this) in alive",
ensures="unique(this) in alive")
  void endPrimeAPTSPARAM(EMethod method, Integer refIndex) {
 
} 
@Perm(requires="share(this) in alive",
ensures="share(this) in alive")
  void initilizeVariables(String className, int objectIndex, EClass _class, int modifier) {
 
} 
@Perm(requires="share(this) in alive",
ensures="share(this) in alive")
  void initilizeKVariables(String className, int objectIndex, int K) {
 
} 
@Perm(requires="share(this) in alive",
ensures="share(this) in alive")
  void defineVariables(String className, int objectIndex, EClass _class, int modifier) {
 
} 
@Perm(requires="share(this) in alive",
ensures="share(this) in alive")
  void defineKVariables(String className, int objectIndex, EClass _class, int K) {
 
} 
@Perm(requires="pure(this) in alive",
ensures="pure(this) in alive")
  boolean isPrivateAndIndexEqualToZero(int refIndex, EField _field) {
 return 0;
 
} 
@Perm(requires="share(this) in alive",
ensures="share(this) in alive")
  void generateSMCmodelPlugin(EPackage _pkg, int testType) {
 
} 
@Perm(requires="unique(this) in alive",
ensures="unique(this) in alive")
  void createAlias() {
 
} 
@Perm(requires="unique(this) in alive",
ensures="unique(this) in alive")
  void addIndexes() {
 
} 
@Perm(requires="unique(this) in alive",
ensures="unique(this) in alive")
  void createDimensionsObject(EClass _class) {
 
} 
@Perm(requires="unique(this) in alive",
ensures="unique(this) in alive")
  void createDimensionAsField(EClass _class, EDim _dim, int count) {
 
} 
@Perm(requires="unique(this) in alive",
ensures="unique(this) in alive")
  void createParentObject(EClass _class) {
 
} 
@Perm(requires="unique(this) in alive",
ensures="unique(this) in alive")
  void createParentAsField(EClass _class, EClass _currentClass) {
 
} 
@Perm(requires="unique(this) in alive",
ensures="unique(this) in alive")
  void addInvariantStateIndex(EClass _class) {
 
} 
@Perm(requires="unique(this) in alive",
ensures="unique(this) in alive")
  void setInvariantVariableType(EClass _class, EInvariant inv) {
 
} 
@Perm(requires="unique(this) in alive",
ensures="unique(this) in alive")
  void Spec() {
 
} 
@Perm(requires="unique(this) in alive",
ensures="unique(this) in alive")
  void statesAdjancyMatrix(EClass _class, Integer objectIndex) {
 
} 
@Perm(requires="unique(this) in alive",
ensures="unique(this) in alive")
  void concurrentMethods(EClass _class, Integer objectIndex, Integer refIndex) {
 
} 
@Perm(requires="share(this) in alive",
ensures="share(this) in alive")
  void sinkStates() {
 
} 

}ENDOFCLASS

@ClassStates({@State(name = "alive")})

class EField {
@Perm(ensures="unique(this) in alive")
EField() {   }

@Perm(requires="unique(this) in alive",
ensures="unique(this) in alive")
 public String getName() {
 return null;
 
} 
@Perm(requires="pure(this) in alive",
ensures="pure(this) in alive")
 public int getObjectIndex() {
 return 0;
 
} 
@Perm(requires="unique(this) in alive",
ensures="unique(this) in alive")
 public String getType() {
 return null;
 
} 
@Perm(requires="pure(this) in alive",
ensures="pure(this) in alive")
 public int getClassIndex() {
 return 0;
 
} 
@Perm(requires="pure(this) in alive",
ensures="pure(this) in alive")
 public int getModifier() {
 return 0;
 
} 
@Perm(requires="share(this) in alive",
ensures="share(this) in alive")
 public void setName(String str) {
 
} 
@Perm(requires="share(this) in alive",
ensures="share(this) in alive")
 public void setType(String str) {
 
} 
@Perm(requires="share(this) in alive",
ensures="share(this) in alive")
 public void setModifier(int mod) {
 
} 
@Perm(requires="share(this) in alive",
ensures="share(this) in alive")
 public void setClassIndex(int classIndex) {
 
} 
@Perm(requires="share(this) in alive",
ensures="share(this) in alive")
 public void setObjectIndex(int objectIndex) {
 
} 

}ENDOFCLASS

@ClassStates({@State(name = "alive")})

class EDim {
@Perm(ensures="unique(this) in alive")
EDim() {   }

@Perm(requires="pure(this) in alive",
ensures="pure(this) in alive")
 public ArrayList<String> getValues() {
 return null;
 
} 
@Perm(requires="share(this) in alive",
ensures="share(this) in alive")
 public void addValue(String str) {
 
} 
@Perm(requires="full(this) in alive",
ensures="full(this) in alive")
 public void setName(String str) {
 
} 
@Perm(requires="pure(this) in alive",
ensures="pure(this) in alive")
 public String getName(String str) {
 return null;
 
} 

}ENDOFCLASS

@ClassStates({@State(name = "alive")})

class EParameter {
@Perm(ensures="unique(this) in alive")
EParameter() {   }

@Perm(requires="immutable(this) in alive",
ensures="immutable(this) in alive")
 public LinkedList<ESpecification> getRequiresAPTS() {
 return null;
 
} 
@Perm(requires="pure(this) in alive",
ensures="pure(this) in alive")
 public String getType() {
 return null;
 
} 
@Perm(requires="immutable(this) in alive",
ensures="immutable(this) in alive")
 public LinkedList<ESpecification> getEnsuresAPTS() {
 return null;
 
} 
@Perm(requires="unique(this) in alive",
ensures="unique(this) in alive")
 public String getName() {
 return null;
 
} 
@Perm(requires="pure(this) in alive",
ensures="pure(this) in alive")
 public int getNumber() {
 return 0;
 
} 
@Perm(requires="full(this) in alive",
ensures="full(this) in alive")
 public void setNumber(int n) {
 
} 
@Perm(requires="share(this) in alive",
ensures="share(this) in alive")
 public void setName(String str) {
 
} 
@Perm(requires="full(this) in alive",
ensures="full(this) in alive")
 public void setType(String str) {
 
} 

}ENDOFCLASS

@ClassStates({@State(name = "alive")})

class EState {
@Perm(ensures="unique(this) in alive")
EState() {   }

@Perm(requires="pure(this) in alive",
ensures="pure(this) in alive")
 public String getName() {
 return null;
 
} 
@Perm(requires="pure(this) in alive",
ensures="pure(this) in alive")
 public LinkedList<EInvariant> getInvariants() {
 return null;
 
} 
@Perm(requires="pure(this) in alive",
ensures="pure(this) in alive")
 public LinkedList<EBoolInvariant> getBoolInvariants() {
 return null;
 
} 
@Perm(requires="pure(this) in alive",
ensures="pure(this) in alive")
 public int getStateIndex() {
 return 0;
 
} 
@Perm(requires="pure(this) in alive",
ensures="pure(this) in alive")
 public int isReachable() {
 return 0;
 
} 
@Perm(requires="share(this) in alive",
ensures="share(this) in alive")
 public void setReachability(int value) {
 
} 
@Perm(requires="share(this) in alive",
ensures="share(this) in alive")
 public Boolean isReachableState() {
 return null;
 
} 
@Perm(requires="share(this) in alive",
ensures="share(this) in alive")
 public void addBoolInvariant(EBoolInvariant inv) {
 
} 
@Perm(requires="share(this) in alive",
ensures="share(this) in alive")
 public void addInvariant(EInvariant inv) {
 
} 
@Perm(requires="share(this) in alive",
ensures="share(this) in alive")
 public void setIndex(int stateIndex) {
 
} 

}ENDOFCLASS

@ClassStates({@State(name = "alive")})

class EInvariant {
@Perm(ensures="unique(this) in alive")
EInvariant() {   }

@Perm(requires="pure(this) in alive",
ensures="pure(this) in alive")
 public String getAP() {
 return null;
 
} 
@Perm(requires="pure(this) in alive",
ensures="pure(this) in alive")
 public String getVariableType() {
 return null;
 
} 
@Perm(requires="pure(this) in alive",
ensures="pure(this) in alive")
 public String getVariable() {
 return null;
 
} 
@Perm(requires="pure(this) in alive",
ensures="pure(this) in alive")
 public String getStateName() {
 return null;
 
} 
@Perm(requires="unique(this) in alive",
ensures="unique(this) in alive")
 public LinkedList<EInvariant> getStateInvariants(EPackage _pkg) {
 return null;
 
} 
@Perm(requires="share(this) in alive",
ensures="share(this) in alive")
 public void setVariableType(String type) {
 
} 

 public void setStateIndex(int stateIndex) {
 
} 
@Perm(requires="full(this) in alive",
ensures="full(this) in alive")
 public void setAP(String str) {
 
} 
@Perm(requires="full(this) in alive",
ensures="full(this) in alive")
 public void setVariable(String str) {
 
} 
@Perm(requires="full(this) in alive",
ensures="full(this) in alive")
 public void setState(String str) {
 
} 

}ENDOFCLASS

@ClassStates({@State(name = "alive")})

class EBoolInvariant {
@Perm(ensures="unique(this) in alive")
EBoolInvariant() {   }

@Perm(requires="immutable(this) in alive",
ensures="immutable(this) in alive")
 public String getVariable() {
 return null;
 
} 
@Perm(requires="immutable(this) in alive",
ensures="immutable(this) in alive")
 public String getValue() {
 return null;
 
} 

}ENDOFCLASS

@ClassStates({@State(name = "alive")})

class EGrarphWriter {
@Perm(ensures="unique(this) in alive")
EGrarphWriter() {   }

@Perm(requires="unique(this) in alive",
ensures="unique(this) in alive")
  void addTrnsitions(String str) {
 
} 
@Perm(requires="share(this) in alive",
ensures="share(this) in alive")
  void parseMethodReachability(String str) {
 
} 
@Perm(requires="unique(this) in alive",
ensures="unique(this) in alive")
  void createGraph() {
 
} 
@Perm(requires="pure(this) in alive",
ensures="pure(this) in alive")
  int getNumberofUnReachableMethods() {
 return 0;
 
} 
@Perm(requires="share(this) in alive",
ensures="share(this) in alive")
  void setNumberofUnReachableMethods() {
 
} 

}ENDOFCLASS

@ClassStates({@State(name = "alive")})

class EOutputLatex {
@Perm(ensures="unique(this) in alive")
EOutputLatex() {   }

@Perm(requires="unique(this) in alive",
ensures="unique(this) in alive")
  void create_CommandLine() {
 
} 
@Perm(requires="share(this) in alive",
ensures="share(this) in alive")
  void addUsePackages() {
 
} 
@Perm(requires="unique(this) in alive",
ensures="unique(this) in alive")
  void writeToLatex() {
 
} 
@Perm(requires="unique(this) in alive",
ensures="unique(this) in alive")
  void WriteSummary() {
 
} 
@Perm(requires="share(this) in alive",
ensures="share(this) in alive")
  void addSummaryTableColumns() {
 
} 
@Perm(requires="share(this) in alive",
ensures="share(this) in alive")
  void addSummaryTableHeaders() {
 
} 
@Perm(requires="unique(this) in alive",
ensures="unique(this) in alive")
  void addSummaryTableRows() {
 
} 
@Perm(requires="unique(this) in alive",
ensures="unique(this) in alive")
  void writeRequiresClauseSatisfiabilty(EClass _class) {
 
} 
@Perm(requires="share(this) in alive",
ensures="share(this) in alive")
  void writeStateTransitionMatrix(EClass _class) {
 
} 
@Perm(requires="share(this) in alive",
ensures="share(this) in alive")
  void addSTMNumberofColumns(EClass _class) {
 
} 
@Perm(requires="share(this) in alive",
ensures="share(this) in alive")
  void addSTMColumnsHeaders(EClass _class) {
 
} 
@Perm(requires="share(this) in alive",
ensures="share(this) in alive")
  void addSTMRows(EClass _class) {
 
} 
@Perm(requires="share(this) in alive",
ensures="share(this) in alive")
  String getStateReachabilityValue(EState _state, EState __state) {
 return null;
 
} 
@Perm(requires="unique(this) in alive",
ensures="unique(this) in alive")
  void writeMethodConcurrencyMatrix(EClass _class) {
 
} 
@Perm(requires="share(this) in alive",
ensures="share(this) in alive")
  void addConcurrencyMatrixColumns(EClass _class) {
 
} 
@Perm(requires="unique(this) in alive",
ensures="unique(this) in alive")
  void addConcurrencyMatrixHeaders(EClass _class) {
 
} 
@Perm(requires="unique(this) in alive",
ensures="unique(this) in alive")
  void addConcurrencyMatrixRows(EClass _class) {
 
} 
@Perm(requires="unique(this) in alive",
ensures="unique(this) in alive")
  String getConcurrencyValue(EMethod _method, EMethod __method) {
 return null;
 
} 
@Perm(requires="share(this) in alive",
ensures="share(this) in alive")
  void writeAbbervations() {
 
} 
@Perm(requires="share(this) in alive",
ensures="share(this) in alive")
  void reset() {
 
} 
@Perm(requires="unique(this) in alive",
ensures="unique(this) in alive")
  void setText(String str) {
 
} 
@Perm(requires="unique(this) in alive",
ensures="unique(this) in alive")
  void parseRequires(String str) {
 
} 
@Perm(requires="unique(this) in alive",
ensures="unique(this) in alive")
  EMethod getMethod(String className, String methodName) {
 return null;
 
} 
@Perm(requires="unique(this) in alive",
ensures="unique(this) in alive")
  void parseTransitions(String str) {
 
} 
@Perm(requires="unique(this) in alive",
ensures="unique(this) in alive")
  void parseConcurrentMethods(String str) {
 
} 
@Perm(requires="share(this) in alive",
ensures="share(this) in alive")
  void parseSinkStates(String str) {
 
} 
@Perm(requires="unique(this) in alive",
ensures="unique(this) in alive")
  void create_Plugin() {
 
} 

}ENDOFCLASS

@ClassStates({@State(name = "alive")})

class WorkspaceUtilities {
@Perm(ensures="unique(this) in alive")
WorkspaceUtilities() {   }


  ASTNode getASTNodeFromCompilationUnit(ICompilationUnit compUnit) {
 return null;
 
} 
@Perm(requires="share(this) in alive",
ensures="share(this) in alive")
  List<ICompilationUnit> scanForCompilationUnits() {
 return null;
 
} 
@Perm(requires="share(this) in alive",
ensures="share(this) in alive")
  List<ICompilationUnit> collectCompilationUnits(IJavaElement javaElement) {
 return null;
 
} 
@Perm(requires="share(this) in alive",
ensures="share(this) in alive")
  List<ICompilationUnit> findCompilationUnits(List<String> files) {
 return null;
 
} 

  String getWorkspaceRelativeName(IJavaElement element) {
 return null;
 
} 

  Map<ICompilationUnit,ASTNode> parseCompilationUnits(List<ICompilationUnit> compilationUnits) {
 return null;
 
} 

  List<MethodDeclaration> scanForMethodDeclarations(Map<ICompilationUnit,ASTNode> compilationUnitToASTNode) {
 return null;
 
} 

  List<MethodDeclaration> scanForMethodDeclarationsFromAST(ASTNode node) {
 return null;
 
} 

}ENDOFCLASS

@ClassStates({@State(name = "alive")})

class SMCVisitor {
@Perm(ensures="unique(this) in alive")
SMCVisitor() {   }

@Perm(requires="share(this) in alive",
ensures="share(this) in alive")
 private void addUnparsedSpecifications(String annotation) {
 
} 

 public void preVisit(ASTNode node) {
 
} 

 public void postVisit(ASTNode node) {
 
} 
@Perm(requires="unique(this) in alive",
ensures="unique(this) in alive")
 public boolean visit(PackageDeclaration node) {
 return 0;
 
} 

 public void endVisit(PackageDeclaration node) {
 
} 
@Perm(requires="unique(this) in alive",
ensures="unique(this) in alive")
 private void callParser(String annotation) {
 
} 

}ENDOFCLASS

@ClassStates({@State(name = "alive")})

class PulseSettings {
@Perm(ensures="unique(this) in alive")
PulseSettings() {   }

@Perm(requires="pure(this) in alive",
ensures="pure(this) in alive")
  int getInheritance() {
 return 0;
 
} 
@Perm(requires="pure(this) in alive",
ensures="pure(this) in alive")
  int getFullModel() {
 return 0;
 
} 
@Perm(requires="pure(this) in alive",
ensures="pure(this) in alive")
  int getInvariants() {
 return 0;
 
} 
@Perm(requires="pure(this) in alive",
ensures="pure(this) in alive")
  int getDimensions() {
 return 0;
 
} 
@Perm(requires="share(this) in alive",
ensures="share(this) in alive")
  void setInvariants(int x) {
 
} 
@Perm(requires="share(this) in alive",
ensures="share(this) in alive")
  void setAliasPerObject(int x) {
 
} 
@Perm(requires="share(this) in alive",
ensures="share(this) in alive")
  void setFullModel(int x) {
 
} 
@Perm(requires="share(this) in alive",
ensures="share(this) in alive")
  void setDimensions(int x) {
 
} 
@Perm(requires="share(this) in alive",
ensures="share(this) in alive")
  void setInheritance(int x) {
 
} 
@Perm(requires="pure(this) in alive",
ensures="pure(this) in alive")
  int getAliasPerObject() {
 return 0;
 
} 

}ENDOFCLASS

@ClassStates({@State(name = "alive")})

class specificationStruct {
@Perm(ensures="unique(this) in alive")
specificationStruct() {   }


}ENDOFCLASS

@ClassStates({@State(name = "alive")})

class Clause {
@Perm(ensures="unique(this) in alive")
Clause() {   }


}ENDOFCLASS

@ClassStates({@State(name = "alive")})

class Signature {
@Perm(ensures="unique(this) in alive")
Signature() {   }


}ENDOFCLASS

@ClassStates({@State(name = "alive")})

class MethodFindVisitor {
@Perm(ensures="unique(this) in alive")
MethodFindVisitor() {   }

@Perm(requires="unique(this) in alive",
ensures="unique(this) in alive")
 public boolean visit(MethodDeclaration methodDeclaration) {
 return 0;
 
} 

}ENDOFCLASS

@ClassStates({@State(name = "alive")})

class Activator {
@Perm(ensures="unique(this) in alive")
Activator() {   }

@Perm(requires="share(this) in alive",
ensures="share(this) in alive")
 public void start(BundleContext context) {
 
} 
@Perm(requires="unique(this) in alive",
ensures="unique(this) in alive")
 public void stop(BundleContext context) {
 
} 
@Perm(requires="pure(this) in alive",
ensures="pure(this) in alive")
  Activator getDefault() {
 return null;
 
} 
@Perm(requires="unique(this) in alive",
ensures="unique(this) in alive")
  ImageDescriptor getImageDescriptor(String path) {
 return null;
 
} 

}ENDOFCLASS

@ClassStates({@State(name = "alive")})

class GAPHandler {
@Perm(ensures="unique(this) in alive")
GAPHandler() {   }


 public void addHandlerListener(IHandlerListener handlerListener) {
 
} 

 public void dispose() {
 
} 
@Perm(requires="share(this) in alive",
ensures="share(this) in alive")
 public Object execute(ExecutionEvent event) {
 return null;
 
} 
@Perm(requires="share(this) in alive",
ensures="share(this) in alive")
 private void extractSettings(ExecutionEvent event) {
 
} 

 public boolean isEnabled() {
 return 0;
 
} 

 public boolean isHandled() {
 return 0;
 
} 

 public void removeHandlerListener(IHandlerListener handlerListener) {
 
} 

}ENDOFCLASS

@ClassStates({@State(name = "alive")})

class GAPIFileAction {
@Perm(ensures="unique(this) in alive")
GAPIFileAction() {   }

@Perm(requires="share(this) in alive",
ensures="share(this) in alive")
 public void selectionChanged(IAction action, ISelection selection) {
 
} 

 public void setActivePart(IAction action, IWorkbenchPart targetPart) {
 
} 
@Perm(requires="share(this) in alive",
ensures="share(this) in alive")
 public void run(IAction action) {
 
} 

}ENDOFCLASS

@ClassStates({@State(name = "alive")})

class Anonymous {
@Perm(ensures="unique(this) in alive")
Anonymous() {   }

@Perm(requires="unique(this) in alive",
ensures="unique(this) in alive")
 protected IStatus run(IProgressMonitor monitor) {
 return null;
 
} 

}ENDOFCLASS

@ClassStates({@State(name = "alive")})

class Main {
@Perm(ensures="unique(this) in alive")
Main() {   }

@Perm(requires="unique(this) in alive",
ensures="unique(this) in alive")
  void main(String[] args) {
 
} 

  String testRead(String file) {
 return null;
 
} 
@Perm(requires="share(this) in alive",
ensures="share(this) in alive")
  void seprateJavaFile(String str) {
 
} 

  void anTest() {
 
} 

}ENDOFCLASS

@ClassStates({@State(name = "alive")})

class TypestateReturn {
@Perm(ensures="unique(this) in alive")
TypestateReturn() {   }


}ENDOFCLASS

@ClassStates({@State(name = "alive")})

class AtApPermissionReturn {
@Perm(ensures="unique(this) in alive")
AtApPermissionReturn() {   }


}ENDOFCLASS

@ClassStates({@State(name = "alive")})

class AccesspermissionReturn {
@Perm(ensures="unique(this) in alive")
AccesspermissionReturn() {   }


}ENDOFCLASS

@ClassStates({@State(name = "alive")})

class PluralLexer {
@Perm(ensures="unique(this) in alive")
PluralLexer() {   }


 public String getGrammarFileName() {
 return null;
 
} 
@Perm(requires="immutable(this) in alive",
ensures="immutable(this) in alive")
  void mATFULL() {
 
} 
@Perm(requires="immutable(this) in alive",
ensures="immutable(this) in alive")
  void mATPURE() {
 
} 
@Perm(requires="immutable(this) in alive",
ensures="immutable(this) in alive")
  void mATIMMUTABLE() {
 
} 
@Perm(requires="immutable(this) in alive",
ensures="immutable(this) in alive")
  void mATSHARE() {
 
} 
@Perm(requires="immutable(this) in alive",
ensures="immutable(this) in alive")
  void mATUNIQUE() {
 
} 
@Perm(requires="immutable(this) in alive",
ensures="immutable(this) in alive")
  void mPUBLICBEHAVIOR() {
 
} 
@Perm(requires="immutable(this) in alive",
ensures="immutable(this) in alive")
  void mFULL() {
 
} 
@Perm(requires="immutable(this) in alive",
ensures="immutable(this) in alive")
  void mPURE() {
 
} 
@Perm(requires="immutable(this) in alive",
ensures="immutable(this) in alive")
  void mIMMUTABLE() {
 
} 
@Perm(requires="immutable(this) in alive",
ensures="immutable(this) in alive")
  void mSHARE() {
 
} 
@Perm(requires="immutable(this) in alive",
ensures="immutable(this) in alive")
  void mUNIQUE() {
 
} 
@Perm(requires="immutable(this) in alive",
ensures="immutable(this) in alive")
  void mNONE() {
 
} 
@Perm(requires="immutable(this) in alive",
ensures="immutable(this) in alive")
  void mLSBRACKET() {
 
} 
@Perm(requires="immutable(this) in alive",
ensures="immutable(this) in alive")
  void mRSBRACKET() {
 
} 
@Perm(requires="immutable(this) in alive",
ensures="immutable(this) in alive")
  void mPERM() {
 
} 
@Perm(requires="immutable(this) in alive",
ensures="immutable(this) in alive")
  void mEQUAL() {
 
} 
@Perm(requires="immutable(this) in alive",
ensures="immutable(this) in alive")
  void mEQUALOPERATOR() {
 
} 
@Perm(requires="immutable(this) in alive",
ensures="immutable(this) in alive")
  void mIN() {
 
} 
@Perm(requires="immutable(this) in alive",
ensures="immutable(this) in alive")
  void mTHIS() {
 
} 
@Perm(requires="immutable(this) in alive",
ensures="immutable(this) in alive")
  void mRESULT() {
 
} 
@Perm(requires="immutable(this) in alive",
ensures="immutable(this) in alive")
  void mPARAM() {
 
} 
@Perm(requires="immutable(this) in alive",
ensures="immutable(this) in alive")
  void mREQUIRES() {
 
} 
@Perm(requires="immutable(this) in alive",
ensures="immutable(this) in alive")
  void mENSURES() {
 
} 
@Perm(requires="immutable(this) in alive",
ensures="immutable(this) in alive")
  void mQUOTE() {
 
} 
@Perm(requires="immutable(this) in alive",
ensures="immutable(this) in alive")
  void mAND() {
 
} 
@Perm(requires="immutable(this) in alive",
ensures="immutable(this) in alive")
  void mUSE() {
 
} 
@Perm(requires="immutable(this) in alive",
ensures="immutable(this) in alive")
  void mUSEFIELDS() {
 
} 
@Perm(requires="immutable(this) in alive",
ensures="immutable(this) in alive")
  void mPUNCTUATION() {
 
} 
@Perm(requires="immutable(this) in alive",
ensures="immutable(this) in alive")
  void mCASES() {
 
} 
@Perm(requires="immutable(this) in alive",
ensures="immutable(this) in alive")
  void mLCBRACKET() {
 
} 
@Perm(requires="immutable(this) in alive",
ensures="immutable(this) in alive")
  void mRCBRACKET() {
 
} 
@Perm(requires="immutable(this) in alive",
ensures="immutable(this) in alive")
  void mCLASSSTATES() {
 
} 
@Perm(requires="immutable(this) in alive",
ensures="immutable(this) in alive")
  void mREFINE() {
 
} 
@Perm(requires="immutable(this) in alive",
ensures="immutable(this) in alive")
  void mVALUE() {
 
} 
@Perm(requires="immutable(this) in alive",
ensures="immutable(this) in alive")
  void mSTATE() {
 
} 
@Perm(requires="immutable(this) in alive",
ensures="immutable(this) in alive")
  void mSTATES() {
 
} 
@Perm(requires="immutable(this) in alive",
ensures="immutable(this) in alive")
  void mDIM() {
 
} 
@Perm(requires="immutable(this) in alive",
ensures="immutable(this) in alive")
  void mNAME() {
 
} 
@Perm(requires="immutable(this) in alive",
ensures="immutable(this) in alive")
  void mINV() {
 
} 
@Perm(requires="immutable(this) in alive",
ensures="immutable(this) in alive")
  void mOPERATOR() {
 
} 
@Perm(requires="immutable(this) in alive",
ensures="immutable(this) in alive")
  void mSEMICOLON() {
 
} 
@Perm(requires="immutable(this) in alive",
ensures="immutable(this) in alive")
  void mLESS() {
 
} 
@Perm(requires="immutable(this) in alive",
ensures="immutable(this) in alive")
  void mLESSTHANEQUAL() {
 
} 
@Perm(requires="immutable(this) in alive",
ensures="immutable(this) in alive")
  void mGREATER() {
 
} 
@Perm(requires="immutable(this) in alive",
ensures="immutable(this) in alive")
  void mGREATERTHANEQUAL() {
 
} 
@Perm(requires="immutable(this) in alive",
ensures="immutable(this) in alive")
  void mANDD() {
 
} 
@Perm(requires="immutable(this) in alive",
ensures="immutable(this) in alive")
  void mOR() {
 
} 
@Perm(requires="immutable(this) in alive",
ensures="immutable(this) in alive")
  void mJMLSTART() {
 
} 
@Perm(requires="immutable(this) in alive",
ensures="immutable(this) in alive")
  void mJMLEND() {
 
} 
@Perm(requires="immutable(this) in alive",
ensures="immutable(this) in alive")
  void mPLUSMINUSOPERATOR() {
 
} 
@Perm(requires="immutable(this) in alive",
ensures="immutable(this) in alive")
  void mASSIGNABLE() {
 
} 
@Perm(requires="immutable(this) in alive",
ensures="immutable(this) in alive")
  void mNOTHING() {
 
} 
@Perm(requires="immutable(this) in alive",
ensures="immutable(this) in alive")
  void mEVERYTHING() {
 
} 
@Perm(requires="immutable(this) in alive",
ensures="immutable(this) in alive")
  void mGHOST() {
 
} 
@Perm(requires="immutable(this) in alive",
ensures="immutable(this) in alive")
  void mINT() {
 
} 
@Perm(requires="immutable(this) in alive",
ensures="immutable(this) in alive")
  void mINVARIANT() {
 
} 
@Perm(requires="immutable(this) in alive",
ensures="immutable(this) in alive")
  void mOLD() {
 
} 
@Perm(requires="immutable(this) in alive",
ensures="immutable(this) in alive")
  void mID() {
 
} 
@Perm(requires="immutable(this) in alive",
ensures="immutable(this) in alive")
  void mNUMBERS() {
 
} 
@Perm(requires="immutable(this) in alive",
ensures="immutable(this) in alive")
  void mWS() {
 
} 
@Perm(requires="unique(this) in alive",
ensures="unique(this) in alive")
 public void mTokens() {
 
} 

}ENDOFCLASS

@ClassStates({@State(name = "alive")})

class DFA7 {
@Perm(ensures="unique(this) in alive")
DFA7() {   }


 public String getDescription() {
 return null;
 
} 

}ENDOFCLASS

@ClassStates({@State(name = "alive")})

class EAPTypeState {
@Perm(ensures="unique(this) in alive")
EAPTypeState() {   }

@Perm(requires="share(this) in alive",
ensures="share(this) in alive")
 public void setAP(String str) {
 
} 
@Perm(requires="unique(this) in alive",
ensures="unique(this) in alive")
 public String getAP(String str) {
 return null;
 
} 
@Perm(requires="share(this) in alive",
ensures="share(this) in alive")
 public void setTS(String str) {
 
} 
@Perm(requires="unique(this) in alive",
ensures="unique(this) in alive")
 public String getTS() {
 return null;
 
} 

}ENDOFCLASS

