import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static List<Person> enterFamilyMembers(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the number of persons you want to insert");
        int numberOfMembers = sc.nextInt(); // number of family members
        System.out.println("Enter the person down below in the following format: 'firstName lastName dateOfBirth(YYYY-MM-DD)");
        List<Person> persons = new ArrayList<Person>();
        String person;
        String person1[];
        LocalDate dateOfBirth;
        person = sc.nextLine(); // inserting the person
        for (int i = 0; i < numberOfMembers; i++)
        {
            person = sc.nextLine(); // inserting the person
            person1 = person.split(" "); // the space separator so we can construct the Person obj;
            dateOfBirth = LocalDate.parse(person1[2]); // parsing the localDate from string
            Person person2 = new Person(person1[0], person1[1], dateOfBirth); // creating the person
            persons.add(person2); // adding it to the list
        }
        return persons; // returning the list with all persons
    }


    public static void sortByDateOfBirthAndFamilyMembers(List<Person> familyMembers){
        Comparator<Person> dateComparator = (Person p1, Person p2) -> p1.getDateOfBirth().compareTo(p2.getDateOfBirth()); // creating a comparator so we can sort by their date of birth

        Map<String, List<String>> map = familyMembers.stream().sorted(dateComparator) // creating a map by streaming the list and sorting it by our declared comparator
                .collect(Collectors.groupingBy(s -> s.getLastName(), Collectors.mapping(Person::getFirstName, Collectors.toList()))); // grouping the persons by their last name

        List<Map.Entry<String, List<String>>> sorted = new LinkedList<Map.Entry<String, List<String>>>(map.entrySet()); // declaring a new list of map entries so we can sort by the family members later

        Collections.sort(sorted, new Comparator<Map.Entry<String, List<String>>>() { // sorting the collection in descending order with a new comparator which compares the number of members in a family
            public int compare(Map.Entry<String, List<String>> o1, Map.Entry<String, List<String>> o2){
                return (Integer.valueOf(o2.getValue().size())).compareTo(Integer.valueOf(o1.getValue().size()));
            }
        });

        for (Map.Entry<String,List<String>> key: sorted)
        {
            System.out.println(key.toString().replace("=", ": ").
                    replace("[", "").
                    replace("]", "").  // printing the results
                    replace(",", ""));
        }


    }


    public static void main(String args[])
    {
        List<Person> people = enterFamilyMembers();
        sortByDateOfBirthAndFamilyMembers(people);;
    }
}
