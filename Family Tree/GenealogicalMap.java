import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
* 1) The first part deals with the individual records per person.
* 2) Your code must compile with javac from the command line and run with java from the command line.
*
* Some people put Jr., Sr., III after family name. For simplicity of input put that as the last of
* the middle names before family name. the default is Exact = false.
*
* 3) The second section of the input file will contain marriage information in the following format:
*
* 4) The penultimate section of the input file lists the children of the parents in the following format.
*
* 5) NOW, we are on to the processing of this tree. The last section will be a series of queries.
*/

class PersonFileReader extends FileUtil {
  @Override
  void callbackTask(String data, List<Person> persontree) {
    Person person = this.parserPerson(data);
    persontree.add(person);
  }
  
  private Person parserPerson(String data) {
    // ID#|FULL NAME|BIRTH|DEATH|GENERAL INFO
    String[] array = data.split("\\|");
    if (array.length > 4) {
      Person p = new Person();
      p.setIdNumber(Integer.parseInt(array[0]));
      String[] names = array[1].split(" ");
      p.setFirstName(names[0]);
      p.setLastName(names[names.length - 1]);
      // split middle name.
      p.setMiddleName(names.length > 3 ? array[1].substring(names[0].length(), array[1].length()
          - names[names.length - 1].length()) : "");
      Date brithDate = DataUtil.getDate(array[2]);
      if (brithDate != null) {
        p.setExact_Birth(true);
        p.setBirth(brithDate);
      }
      
      Date deathDate = DataUtil.getDate(array[3]);
      if (deathDate != null) {
        p.setExact_Death(true);
        p.setDeath(brithDate);
      }
      
      p.setBirth(DataUtil.getDate(array[2]));
      p.setDeath(DataUtil.getDate(array[3]));
      p.setGenInfo(array[4]);
      return p;
    } else {
      return null;
    }
  }
}

class MarriageFileReader extends FileUtil {
  @Override
  void callbackTask(String data, List<Person> persontree) {
    // file format: ID1|ID2|YEAR|CIRCA
    String[] dataSet = data.split("\\|", -1); // -1, not limit
    Person a = persontree.get(Integer.parseInt(dataSet[0])-1);
    Person b = persontree.get(Integer.parseInt(dataSet[1])-1);
    a.setSpouse(b);
    a.setMarriage(DataUtil.getDate(dataSet[2]));
    a.setExact_Marriage(true);
    b.setSpouse(a);
    b.setMarriage(DataUtil.getDate(dataSet[2]));
    b.setExact_Marriage(true);
    // TODO: CIRCA means what?
  }
}

class ChildrenFileReader extends FileUtil {
  @Override
  void callbackTask(String data, List<Person> persontree) {
    // file format: PARENT_ID|CHILDREN|#CHILDREN
    String[] dataSet = data.split("\\|");
    Person parent = persontree.get(Integer.parseInt(dataSet[0])-1);
    int numOfChildren = Integer.parseInt(dataSet[2]);
    parent.setNumOfChildren(numOfChildren);
    Person[] children = new Person[numOfChildren];
    String[] childrenids = dataSet[1].split(" ");
    for (int idx = 0; idx < numOfChildren; idx++) {
      Person child = persontree.get(Integer.parseInt(childrenids[idx])-1);
      child.setParents(parent);
      children[idx] = child;
    }
    parent.setChildren(children);
    // You will need to set the children of both spouses.
    if (parent.getExact_Marriage()) parent.getSpouse().setChildren(children);
  }
}


public class GenealogicalMap {
  // start with 1 index, so 0 index is null.
  List<Person> persontree = new ArrayList<Person>();
  String filePath = this.getClass().getResource("/").getPath();
  String PERSON_FILE =  "/Users/ruixu/Desktop/ProjectPersons.txt";
  String MARRIAGE_FILE ="/Users/ruixu/Desktop/ProjectMarriages.txt";
  String CHILDREN_FILE ="/Users/ruixu/Desktop/ProjectChildren.txt";


  public void init() {
    new PersonFileReader().read(this.PERSON_FILE, this.persontree);
    new MarriageFileReader().read(this.MARRIAGE_FILE, this.persontree);
    new ChildrenFileReader().read(this.CHILDREN_FILE, this.persontree);
  }
  
  public void depthFirstListing(int idNumber) {
    ArrayDeque<Person> stack = new ArrayDeque<Person>();
    stack.push(persontree.get(idNumber - 1));
    while(stack.isEmpty()==false){
      Person p = stack.pop();
        System.out.println(p.toString());
        if (p.numOfChildren > 0) {
          for (Person child: p.getChildren()) {
            stack.push(child);
          }
        }
    }
    System.out.print("\n");
  }

  public void breadthFirstListing(int idNumber) {
    ArrayDeque<Person> queue = new ArrayDeque<Person>();
    queue.add(persontree.get(idNumber - 1));
    while(queue.isEmpty()==false){
      Person p = queue.remove();
        System.out.println(p.toString());
        if (p.numOfChildren > 0) {
          for (Person child: p.getChildren()) {
            queue.add(child);
          }
        }
    }
    System.out.print("\n");
  }


  /**
* parents(idNumber)
* children(idNumber)
* recordInfo(idNumber)
* recordInfo(name)
* spouse(idNumber)
* @param args
*/
  public void parents(int idNumber) {
    Person p = null;
    try {
      p = this.persontree.get(idNumber - 1);
    } catch (IndexOutOfBoundsException e) {
      System.out.println("error person id.");
    }
    if (p != null) {
      if (p.getParents() != null) {
        System.out.println(p.getParents().toString());
      } else {
        System.out.println("no parents.");
      }
    }
  }
  
  public void children(int idNumber) {
    Person p = null;
    try {
      p = this.persontree.get(idNumber - 1);
    } catch (IndexOutOfBoundsException e) {
      System.out.println("error person id.");
    }
    if (p != null) {
      if (p.numOfChildren > 0 ) {
        for (Person child: p.getChildren()){
          System.out.println(child.toString());
        }
      } else {
        System.out.println("no kids.");
      }
    }
  }

  public void spouse(int idNumber) {
    Person p = null;
    try {
      p = this.persontree.get(idNumber - 1);
    } catch (IndexOutOfBoundsException e) {
      System.out.println("error person id.");
    }
    if (p != null) {
      if (p.getExact_Marriage()) {
        System.out.println(p.getSpouse().toString());
      } else {
        System.out.println("not marriaged.");
      }
    }
  }

  public void recordInfo(int idNumber) {
    Person p = null;
    try {
      p = this.persontree.get(idNumber - 1);
    } catch (IndexOutOfBoundsException e) {
      System.out.println("error person id.");
    }
    if (p != null) {
      System.out.println(p.toString());
    }
  }

  public void recordInfo(String name) {
    for(Person p: persontree) {
      String fullname = p.FirstName + p.MiddleName + p.LastName;
      System.out.println(fullname);
      if (fullname.indexOf(name) >= 0) {
        System.out.println(p.toString());
      }
    }
  }

  public static void main(String args[]) {
    GenealogicalMap map = new GenealogicalMap();
    map.init();
    for (Person p : map.persontree) {
      System.out.println(p);
    }

    // Test Case for traversal
    map.depthFirstListing(111);
    map.breadthFirstListing(111);

    // Test Case for query api
    map.parents(1);
    map.children(4);
    map.children(131);
    map.children(130);
    map.recordInfo(67);
    map.recordInfo(90);
    map.recordInfo(118);
    map.spouse(114);
    map.spouse(92);
  }
}