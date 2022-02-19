package main.ua.advanced.practice2;

import main.ua.advanced.practice2.readerAndWriter.JSONWriter;
import main.ua.advanced.practice2.task1_list.MyList;
import main.ua.advanced.practice2.task1_list.MyListImpl;
import main.ua.advanced.practice2.task2_queue.Queue;
import main.ua.advanced.practice2.task2_queue.QueueImpl;
import main.ua.advanced.practice2.task3_stack.Stack;
import main.ua.advanced.practice2.task3_stack.StackImpl;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import static main.ua.advanced.practice2.readerAndWriter.XMLReader.readCitiesFromFile;

public class Demo {
    static City ternopil = new City("Ukraine", "Ternopil", 650000, false, 763927);
    static City munchen = new City("Germany", "Munchen", 2500000, false, 185392);
    static City newCity = new City("Russia", "Moscow", 5000000, true, 503440);
    public static void main(String[] args) throws IOException {
        final List<City> gotCities = readCitiesFromFile();

        MyList cityList = getMyListFromListCity(gotCities);
        Queue cityQueue = getQueueFromListCity(gotCities);
        Stack cityStack = getStackFromListCity(gotCities);

        myListFunctional(cityList);
        queueFunctional(cityQueue);
        stackFunctional(cityStack);
        /*System.out.println("List");
        for (var city : cityList) {
            System.out.println(((City) city).getCityInfoInFormat());
        }

        System.out.println("Queue");
        for (var city : cityQueue) {
            System.out.println(((City) city).getCityInfoInFormat());
        }

        System.out.println("Stack");
        for (var city : cityStack) {
            System.out.println(((City) city).getCityInfoInFormat());
        }*/

        JSONWriter.writeCitiesToFile(cityStack);

    }

    private static void myListFunctional(MyList container) {
        System.out.println("List");
        printContainer(container);

        //MyList's methods
        System.out.println("MyList's methods below:");

        System.out.println("AddFirst Ternopil");
        container.addFirst(ternopil);
        printContainer(container);
        System.out.println();
        System.out.println("AddLast Munchen");
        container.addLast(munchen);
        printContainer(container);
        System.out.println();

        System.out.println("GetFirst of MyList : " + container.getFirst());
        System.out.println("GetLast of MyList : " + container.getLast());
        System.out.println();

        System.out.println("Search Ternopil");
        System.out.println(container.search(ternopil) != null ? ternopil : "Couldn't find");
        System.out.println();

        System.out.println("Search newCity");
        System.out.println(container.search(newCity) != null ? newCity : "Couldn't find");
        System.out.println();

        System.out.println("Remove first element");
        container.removeFirst();
        printContainer(container);
        System.out.println();
        System.out.println("Remove last element");
        container.removeLast();
        printContainer(container);
        System.out.println();
        System.out.println("Remove Kyiv");
        container.remove(container.getFirst());
        printContainer(container);
        System.out.println();

        //Iterator's methods
        System.out.println("Iterator's methods below: ");
        System.out.println("First hasNext(): " + container.iterator().hasNext());
        System.out.println("First element: " + container.iterator().next());
        System.out.println("Second hasNext(): " + container.iterator().hasNext());
        System.out.println("Second element: " + container.iterator().next());
        System.out.println();

        //Container`s methods
        System.out.println("Container's methods below:");
        System.out.println("Size: " + container.size());
        System.out.println("Clear");
        container.clear();
        printContainer(container);

        System.out.println("==================================");
    }

    private static void printContainer(Container container) {
        System.out.println(/*"MyList now: " + "\n" + */container);
    }

    private static void queueFunctional(Queue container) {
        System.out.println("Queue");
        printContainer(container);

        //Queue's methods
        System.out.println("Queue's methods below:");

        System.out.println("Enqueue Ternopil");
        container.enqueue(ternopil);
        printContainer(container);
        System.out.println();

        System.out.println("Dequeue");
        container.dequeue();
        printContainer(container);
        System.out.println();

        System.out.println("Get head element: " + container.top());
        System.out.println();

        //Iterator's methods
        System.out.println("Iterator's methods below:");
        Iterator<Object> iterator = container.iterator();
        System.out.println("Try next x1: " + iterator.next());
        System.out.println("Try next x2: " + iterator.next());
        System.out.println("Remove the last element returned by this iterator");
        iterator.remove();
        printContainer(container);
        System.out.println();

        iterator = container.iterator();
        System.out.println("First hasNext(): " + iterator.hasNext());
        System.out.println("First element: " + iterator.next());
        System.out.println("Second hasNext(): " + iterator.hasNext());
        System.out.println("Second element: " + iterator.next());
        System.out.println();

        //Container`s methods
        System.out.println("Container's methods below:");
        System.out.println("Size: " + container.size());
        System.out.println("Clear");
        container.clear();
        printContainer(container);
        System.out.println("==================================");
    }

    private static void stackFunctional(Stack container) {
        printContainer(container);

        //Stack's methods
        System.out.println("Stack's methods below:");

        System.out.println("Push Munchen");
        container.push(munchen);
        printContainer(container);
        System.out.println();

        System.out.println("Pop element: " + container.pop());
        printContainer(container);
        System.out.println();

        System.out.println("Get top element: " + container.top());
        System.out.println();

        //Iterator's methods
        System.out.println("Iterator's methods below:");
        Iterator<Object> iterator = container.iterator();
        System.out.println("Try next x1: " + iterator.next());
        System.out.println("Try next x2: " + iterator.next());
        System.out.println("Remove the last element returned by this iterator");
        iterator.remove();
        printContainer(container);
        System.out.println();

        iterator = container.iterator();
        System.out.println("First hasNext(): " + iterator.hasNext());
        System.out.println("First element: " + iterator.next());
        System.out.println("Second hasNext(): " + iterator.hasNext());
        System.out.println("Second element: " + iterator.next());
        System.out.println();

        //Container`s methods
        System.out.println("Container's methods below:");
        System.out.println("Size: " + container.size());
        System.out.println("Clear");
        container.clear();
        printContainer(container);
    }

    private static MyList getMyListFromListCity(List<City> elements) {
        MyList myList = new MyListImpl<City>();
        for (var elem : elements) {
            myList.addLast(elem);
        }
        return myList;
    }

    private static Queue getQueueFromListCity(List<City> elements) {
        Queue queue = new QueueImpl();
        for (var elem : elements) {
            queue.enqueue(elem);
        }
        return queue;
    }

    private static Stack getStackFromListCity(List<City> elements) {
        Stack stack = new StackImpl();
        for (var elem : elements) {
            stack.push(elem);
        }
        return stack;
    }
}
