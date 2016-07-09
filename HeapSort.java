/*
 * Class implemented for sorting records using
 * the HeapSort Algorithm;
 */
package sortingApplication;

/**
 *
 * @author Dhiogo
 */
public class HeapSort {
    
    private static int total;
    
    /**
     * Compares two Personal Records, seeing wich one is bigger;
     * @param one PersonalRecord one;
     * @param two PersonalRecord two;
     * @return {@code true} if PersonalRecord one is bigger than two;
     *         {@code false} otherwise;
     */
    private boolean compare(PersonalRecord one, PersonalRecord two){
        
        IdentificationDocument first = one.getIdentificationDocument();
        IdentificationDocument second = two.getIdentificationDocument();
        
        if((first.getType > second.getType)){
            return true;
        }else if(first.getType().equals(second.getType())){
            if(first.getNumber() > second.getNumber()){
                return true;
            }else if(first.getNumber().equals(second.getNumber())){
                if(first.getOriginCountryCode() > second.getOriginCountryCode()){
                    return true;
                }else{
                    return false;
                }
            }else{
                return false;
            }
        }else{
            return false;
        }
    }
    /**
     * Swap the places of two ellements in the table;
     * @param table array of ellements;
     * @param a position of the ellement one;
     * @param b position of the ellement two;
     */
    private void swap(Table table, int a, int b){
        PersonalRecord person = table.getRecord(a);
        table.setRecord(table.getRecord(b), a);
        table.setRecord(person, b);
    }
    
    /**
     * Defines wich ellement of the list will be swapped and the
     * position it will take;
     * @param table list of ellements;
     * @param i actual position of the greater ellement on the array;
     */
    private void heapify (Table table, int i){
        int left = i * 2;
        int right = left + 1; 
        int greater =  i;

        if(left <= total &&
                compare(table.getPersonalRecord(left), 
                        table.getPersonalRecord(greater))){
            greater = left;
        }
        if(right <= total &&
                compare(table.getPersonalRecord(right),
                        table.getPersonalRecord(greater))){
            greater = right;
        }
        if(greater != i){
            swap(table, i, greater);
            heapify(table, greater);
        }
    }
    
    /**
     * The base of the sorting algorithm;
     * @param table the array to be sorted;
     */
    public void heapSort(Table table){
        total = table.getNumberofRecords() - 1;
        for (int i = total / 2; i >= 0; i--){
            heapify(table, i);
        }
        for(int i = total; i > 0; i--){
            swap (table, i);
            total--;
            heapify(table, 0);
        }
    }
}
