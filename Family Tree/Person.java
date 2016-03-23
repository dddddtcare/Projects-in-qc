import java.util.Date;


public class Person {
  int idNumber; // Each entry is given a sequentially unique id number starting with idNumber = 1; default = 0;
  String FirstName; // First name only
  String MiddleName; // ie any name following the first name including titles such as III or Sr. or Jr.
  String LastName; // Family name

  Date Birth; // If full date available, or in worst case year
  Date Marriage; // If never married, then null
  Date Death; // If Living, then null.

  String GenInfo; // A place to leave notes about the individual's life.

  Boolean Exact_Birth = false; // True means EXACT date know and only False means only approximate date known.
  Boolean Exact_Marriage = false; // True means EXACT date know and only False means only approximate date known.
  Boolean Exact_Death = false; // True means EXACT date know and only False means only approximate date known.

  Person Spouse; // Even if not married; default is null;
  int numOfChildren; // For verification, keep track of the number of children
  Person[] Children; // Pointers to Person data records for each of the children

  Person Parents; // Pointers to Person data records for each of the parents

  public String toString() {
    return String.format("ID:%d, FirstName:%s, Birth:%s, Death:%s, GenInfo:%s.",
        this.idNumber, this.FirstName, this.Birth, this.Death, this.GenInfo);
  }

  public int getIdNumber() {
    return idNumber;
  }

  public void setIdNumber(int idNumber) {
    this.idNumber = idNumber;
  }

  public String getFirstName() {
    return FirstName;
  }

  public void setFirstName(String firstName) {
    FirstName = firstName;
  }

  public String getMiddleName() {
    return MiddleName;
  }

  public void setMiddleName(String middleName) {
    MiddleName = middleName;
  }

  public String getLastName() {
    return LastName;
  }

  public void setLastName(String lastName) {
    LastName = lastName;
  }

  public Date getBirth() {
    return Birth;
  }

  public void setBirth(Date birth) {
    Birth = birth;
  }

  public Date getMarriage() {
    return Marriage;
  }

  public void setMarriage(Date marriage) {
    Marriage = marriage;
  }

  public Date getDeath() {
    return Death;
  }

  public void setDeath(Date death) {
    Death = death;
  }

  public String getGenInfo() {
    return GenInfo;
  }

  public void setGenInfo(String genInfo) {
    GenInfo = genInfo;
  }

  public Boolean getExact_Birth() {
    return Exact_Birth;
  }

  public void setExact_Birth(Boolean exact_Birth) {
    Exact_Birth = exact_Birth;
  }

  public Boolean getExact_Marriage() {
    return Exact_Marriage;
  }

  public void setExact_Marriage(Boolean exact_Marriage) {
    Exact_Marriage = exact_Marriage;
  }

  public Boolean getExact_Death() {
    return Exact_Death;
  }

  public void setExact_Death(Boolean exact_Death) {
    Exact_Death = exact_Death;
  }

  public Person getSpouse() {
    return Spouse;
  }

  public void setSpouse(Person spouse) {
    Spouse = spouse;
  }

  public int getNumOfChildren() {
    return numOfChildren;
  }

  public void setNumOfChildren(int numOfChildren) {
    this.numOfChildren = numOfChildren;
  }

  public Person[] getChildren() {
    return Children;
  }

  public void setChildren(Person[] children) {
    Children = children;
  }

  public Person getParents() {
    return Parents;
  }

  public void setParents(Person parents) {
    Parents = parents;
  }

}