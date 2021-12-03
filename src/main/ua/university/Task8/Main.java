package main.ua.university.Task8;

public class Main {
    public static void main(String[] args) {
        DataReader dataReader = new DataReader();

        System.out.println("Frequency: ");
        DataFrequency dataFrequency = new DataFrequency(dataReader);
        dataFrequency.printTopThreeFrequencies();
        System.out.println();

        System.out.println("Length: ");
        DataLength dataLength = new DataLength(dataReader);
        dataLength.printTopThreeDataLength();
        System.out.println();

        System.out.println("Duplicates: ");
        DataDuplicates dataDuplicates = new DataDuplicates(dataReader);
        dataDuplicates.printFirstDuplicatesInUpCase();
        System.out.println();

    }
}
