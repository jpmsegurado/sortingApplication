/*
 * 
 *  Class: Algorithms: Sorting and Searching
 *
 */
/**
 * @author Wanderley de Souza Alencar
 */
package sortingapplication;

import java.util.Date;
import java.util.Random;

public class SortingApplication {

    
    public static void selectionSort(Table paramTable, int paramNumberOfElements) {
       for(int i=0, len = paramTable.getNumberOfRecords(); i < len - 1; i++){
           int menor = i;
           for(int j = i + 1; j < len; j++){
               if(paramTable.compareRecordKey(menor, j) == 1){
                   menor = j;
               }
           }
           if(i != menor){
               paramTable.exchangeRecords(i, menor);
           }
       }
    }
    
    public static void mergeSort(Table table, int inicio, int fim) {
	if (fim <= inicio) {
		return;
	}
	int meio = (inicio + fim) / 2;
	mergeSort(table, inicio, meio);
	mergeSort(table, meio + 1, fim);
	
        Table a = new Table(), b = new Table();
	for (int i = 0; i <= meio - inicio; i++) {
                a.addRecord(table.getRecord(inicio + i));
	}
	for (int i = 0; i <= fim - meio - 1; i++) {
                b.addRecord(table.getRecord(meio + 1 + i));
	}
	int i = 0;
	int j = 0;
	for (int k = inicio; k <= fim; k++) {
		if (i < a.getNumberOfRecords() && j < b.getNumberOfRecords()) {
			//if (A[i] < B[j]) {
                        if (compareRecords(a.getRecord(i), b.getRecord(j)) == -1) {
                                table.setRecord(a.getRecord(i), k);
			} else {
                                table.setRecord(b.getRecord(j++), k);
			}
		} else if (i < a.getNumberOfRecords()) {
                        table.setRecord(a.getRecord(i++), k);
		} else if (j < b.getNumberOfRecords()) {
                        table.setRecord(b.getRecord(j++), k);
		}
	}
    }
    
    public static int compareRecords(PersonalRecord a, PersonalRecord b){
        
        
        
        if (a.getPersonalDocument().getType() < b.getPersonalDocument().getType()) {
            //
            // If the TYPE of the first record is less than the TYPE of the second record, then ...
            //
            return(-1);  
        }
        if (a.getPersonalDocument().getType() > b.getPersonalDocument().getType()) {
            //
            // If the TYPE of the first record is greater than the TYPE of the second record, then ...
            //
            return(+1);
        }
        if (a.getPersonalDocument().getType() == b.getPersonalDocument().getType()) {
            //
            // If the TYPE of the first record is equal to the TYPE of the second record, then ... 
            //    it is necessary to compare the NUMBERS...
            //
            if (a.getPersonalDocument().getNumber() < b.getPersonalDocument().getNumber()) {
                //
                // If the NUMBER of the first record is less than the NUMBER of the second record, then ...
                //
                return(-1);
            }
            if (a.getPersonalDocument().getNumber() > b.getPersonalDocument().getNumber()) {
                //
                // If the NUMBER of the first record is greater than the NUMBER of the second record, then ...
                //
                return(+1);
            }
            if (a.getPersonalDocument().getNumber() == b.getPersonalDocument().getNumber()) {
                //
                // If the NUMBER of the first record is equal to the NUMBER of the second record, then ...    
                //    it is necessary to compare the COUNTRY CODES...
                //
                if (a.getPersonalDocument().getOriginCountryCode() < b.getPersonalDocument().getOriginCountryCode()) {
                    //
                    // If the ORIGIN COUNTRY CODE of the first record is less than the ORIGIN COUNTRY CODE of the second record, then ...
                    //
                    return(-1);
                }
                if (a.getPersonalDocument().getOriginCountryCode() > b.getPersonalDocument().getOriginCountryCode()) {
                    //
                    // If the ORIGIN COUNTRY CODE of the first record is greater than the ORIGIN COUNTRY CODE of the second record, then ...
                    //
                    return(+1);
                }
                if (a.getPersonalDocument().getOriginCountryCode() == b.getPersonalDocument().getOriginCountryCode()) {
                    //
                    // If the ORIGIN COUNTRY CODE of the first record is equal to the ORIGIN COUNTRY CODE of the second record, then ...
                    //    It is an error, because the key should be a primary key... 
                    return(0);
                }
            }
        }
        // 
        // this code is never reached, but it is necessary by constraint of the Java.
        //
        return (0);  
        
    }
    
    public static void insertionSort() {
    }
    public static void quickSort() {
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //
        // The main application should be inserted here..
        //
        int                     i, 
                                numberOfRecordsDesired,     // Number of record that... you want to generate
                                numberOfRecordsGenerated,   // effectively generated to the .csv file
                                numberOfRecordsLoaded,      // recovered from the .csv file
                                numberOfRecordsPrinted;     // printed (for checking) from the table
        String                  applicationPath,
                                auxiliaryString;
        Random                  randomNumber;               // an auxiliary variable to random generation...
        IdentificationDocument  applicationDocument;
        PersonalRecord          applicationRecord;
        Table                   applicationTable;
        Date                    birthDay;
        
        //
        // The path of the application... set up for your use
        //
        applicationPath     = "/home/joaopedro/Documentos/ProjetosNetbeans/SortingApplication";
        applicationTable    = new Table();
        applicationRecord   = new PersonalRecord();
        applicationDocument = new IdentificationDocument();
        birthDay            = new Date();
        randomNumber        = new Random();
          
        numberOfRecordsDesired = 5; 
        System.out.println("Generating <" + numberOfRecordsDesired + "> randomly records to form the table.");
        numberOfRecordsGenerated      = applicationTable.randomGenerateToCSV(applicationPath + "Table.csv", numberOfRecordsDesired);
        if (numberOfRecordsGenerated != 0) {
            System.out.println("Loading the stored record from  <" + "Table.csv" + "> to the table.");
            numberOfRecordsLoaded     = (int) applicationTable.loadFromCSV(applicationPath + "Table.csv", 0, (numberOfRecordsGenerated - 1));
            /*if (numberOfRecordsLoaded == numberOfRecordsGenerated) {
               System.out.println("Printing records from the table.");      
               numberOfRecordsPrinted      = applicationTable.print(0, (numberOfRecordsGenerated - 1));
               if (numberOfRecordsPrinted == numberOfRecordsLoaded) {
                   System.out.println("All <" + numberOfRecordsPrinted + "> records were printed."); 
                   auxiliaryString = (applicationTable.isSorted()) ? "YES" : "NO";
                   System.out.println("The records are in order by primary key?: " + auxiliaryString);
               }
               else {
                   System.out.println("Unfortunately, there are records that have not been printed.");
                   System.out.println("Please check the parameters entered for the priting process.");
               }
            }
            else {
                 System.out.println("Unfortunately there are records that have not been uploaded to the table (from .csv file).");
                 System.out.println("Please check the parameters entered for the loading process.");
            }*/
        }
        else {
            System.out.println("Unfortunately no record can be generated.");
            System.out.println("Please check the parameters entered for the generation process.");
        }
        mergeSort(applicationTable, 0, applicationTable.getNumberOfRecords() - 1);
        //applicationTable.print(0, 4);
        String isSorted = applicationTable.isSorted() ? "YES" : "NO";
        System.out.println(isSorted);
        
    }
    
}
