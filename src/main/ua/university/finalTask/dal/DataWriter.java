package main.ua.university.finalTask.dal;

import main.ua.university.finalTask.bll.Country;

import java.beans.XMLEncoder;
import java.io.*;
import java.util.List;

public class DataWriter {
   /* public static void writeCountryToFile(Country country) {
        *//*try (XMLEncoder xmlEncoder = new XMLEncoder(new BufferedOutputStream(
                new FileOutputStream(FilePath.pathDir + FilePath.countriesFile, true)))) {
            xmlEncoder.writeObject(country);
            xmlEncoder.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }*//*

        JAXBContext jaxbContext = null;
        try {
            jaxbContext = JAXBContext.newInstance(Country.class);
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(country, new File(FilePath.pathDir + "product.xml"));
            //marshaller.marshal(country, System.out);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    public static void writeCountriesToFile(Country[] countries) {
        for (var country: countries)
            writeCountryToFile(country);
    }*/

    public static void writeCountryToFile(List<Country> countries) {
        try(XMLEncoder out = new XMLEncoder(new BufferedOutputStream( new ObjectOutputStream(
                new FileOutputStream(FilePath.pathDir + "country.xml"))))){
            out.writeObject(countries);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
