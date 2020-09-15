
//THIS CODE IS MY OWN WORK, IT WAS WRITTEN WITHOUT CONSULTING A TUTOR OR CODE WRITTEN BY OTHER STUDENTS - Gene Lee
import java.io.*;

class DataDescription
{
   String fieldName;
   String fieldType;
   int    fieldSize;
}

public class pj1
{
   static int MAXNFIELDS = 10;      // Max. # fields in 1 record

   static int n_fields;             // Actual number of fields

   /* ------------------------------------------------------------
      (1) Variables used to store the DESCRIPTION of the data
      ------------------------------------------------------------ */
   static DataDescription[] dataDes = new DataDescription[MAXNFIELDS];

   // This is a CONSTRUCTOR method for static variables
   static
   {
      // Need to create objects for the dataDes[]
      for ( int i = 0; i < MAXNFIELDS; i++ )
      {
	 dataDes[i] = new DataDescription();
      }
   }

   /* -------------------------------------------------------
      (2) Variables used to store the ACTUAL data
      ------------------------------------------------------- */
   static String[] c_buf=new String[MAXNFIELDS]; // Used to store String fields
   static int[]    i_buf=new int[MAXNFIELDS];    // Used to store int fields
   // TODO: add variable(s) to support double data
   static double[] f_buf=new double[MAXNFIELDS];




   public static void main(String[] args) throws IOException
   {

      /* ===========================================================
	 We must first find out the STRUCTURE of the data file
	 This information is stored in the data DESCRIPTION file
	 =========================================================== */

      DataFile descrFile = new DataFile("db-description");
			 // 1. Open the data description file

      /* -------------------------------------------------------
	 Read in the data description and store them in the
	 DataDes[] array (define in (1))
         ------------------------------------------------------- */

      n_fields = 0;                 // Count the actual number of fields in data
      while ( true )
      {
         try
         {
            dataDes[n_fields].fieldName = descrFile.ReadString(24);
            dataDes[n_fields].fieldType = descrFile.ReadString(4);
            dataDes[n_fields].fieldSize = descrFile.ReadInt();

            System.out.println("Field: " + dataDes[n_fields].fieldName
                + ", type: " + dataDes[n_fields].fieldType
                + ", size: " + dataDes[n_fields].fieldSize);

	    n_fields++;
         }
         catch ( IOException e )
         {
            System.out.println("\nFinish reading data description file....\n");
            break;   // Read error: no more data !!!
         }
      }


      DataFile dataFile =  new DataFile("db-data");   // First open data file

      System.out.println( "The data file contains these records:\n");


      // TODO:  Write a loop to print out the records
      //        You can use the PrintRecord() method given below
      //        but you must add support for double in that method
      // 


      while(true){
         try{
            for ( int i = 0; i < n_fields; i++ ){
             /* ------------------------------------------------------
               Read in the data depending on the TYPE of this field
               ------------------------------------------------------ */
            if ( dataDes[i].fieldType.equals("I") ){
             /* --------------------------------------------------------
               Field i is an integer, use i_buf[i] to store the value
               -------------------------------------------------------- */
               i_buf[i] = dataFile.ReadInt();
             }
            else if ( dataDes[i].fieldType.equals("C"))
            {
            /* --------------------------------------------------------
              Field i is an String, use c_buf[i] to store the value
              -------------------------------------------------------- */ 
               c_buf[i] = dataFile.ReadString( dataDes[i].fieldSize );
            }
            else{
               f_buf[i] = dataFile.ReadDouble();
            }
         }
         PrintRecord();
      }
         catch (IOException e){
            break;
         }

      }

      // TODO:  Define additional variables and write additional code 
      //        to find the min value in the specified fieldName 
      //        (the fieldName is given as args[0])
      // **
      // ** args[0] SHOULD have been explained in CS170 
      // ** If you DO NOT know what args[0] means, read this webpage:
      // **   http://www.mathcs.emory.edu/~cheung/Courses/170/Syllabus/09/command-args.html
      // **
      //
      // Use the >= operator to compare 2 integer or 2 double values
      // Use the compareTo() method in the String class to compare 
      // 2 Strings
      //

      int i, j;
      String type = "";
      int min_i = 0;
      String min_c = "";
      double min_f = 0;
    
         System.out.print("\nFind min value in the field " + args[0] + "\n");
         dataFile.rewind();      // Rewind data file

         for (i = 0; i < n_fields; i++){   // Look through data fields to find the desired field
            if (args[0].equals(dataDes[i].fieldName)){
               type = dataDes[i].fieldType; // Store field type for convenience
               break;
             }
         }
            
         if (i == n_fields){  // Stop if an invalid name is given
            System.out.println("--- Error: Field name not found");
            return;
         }

         for (j = 0; j < n_fields; j++){
            if (dataDes[j].fieldType.equals("I") )
               i_buf[j] = dataFile.ReadInt();
            else if (dataDes[j].fieldType.equals("C"))
               c_buf[j] = dataFile.ReadString( dataDes[j].fieldSize );
            else
               f_buf[j] = dataFile.ReadDouble();
         }

         //Set first record's field as the minimum
         if (type.equals("I"))
            min_i = i_buf[i]; // index i will always match the index of the desired field in dataDes

         else if (type.equals("C"))
            min_c = c_buf[i];

         else
            min_f = f_buf[i];
    
          while ( true )
          {
             try{
           /* =====================================================
              Read the next record (= read ALL fields !)
              ===================================================== */
              for (j = 0; j < n_fields; j++){
                  if (dataDes[j].fieldType.equals("I") )
                     i_buf[j] = dataFile.ReadInt();
                  else if (dataDes[j].fieldType.equals("C"))
                     c_buf[j] = dataFile.ReadString( dataDes[j].fieldSize );
                  else
                     f_buf[j] = dataFile.ReadDouble();
              }


               // Set new min whenever a smaller value is found
               if (type.equals("I")){
                  if (i_buf[i] < min_i)
                     min_i = i_buf[i];
               }

               else if (type.equals("C")){
                  if (c_buf[i].compareTo(min_c) == -1)
                     min_c = c_buf[i];
               }

               else
                  if (f_buf[i] < min_f)
                     min_f = f_buf[i];
               }

               catch ( IOException e ){
                  break;
               }
            }

         // TODO:  Write additional code to print out the min value found

         if (type.equals("I"))
            System.out.println("Min = " + min_i);

         else if (type.equals("C"))
            System.out.println("Min = " + min_c);

         else
            System.out.println("Min = " + min_f);
   }



   /* ===========================================================
      PrintRecord( ): print the record store in the arrays
      =========================================================== */
   public static void PrintRecord( )
   {
      // TODO: add support to print double data

      for ( int i = 0; i < n_fields; i++ )
      {
         if ( dataDes[i].fieldType.equals("I") )
         {
            /* --------------------------------------------------------
               Field i is an integer, use i_buf[i] to store the value
               -------------------------------------------------------- */
            System.out.print( i_buf[i] + " ");
         }
         else if (dataDes[i].fieldType.equals("C")) // Default is String.
         {
            /* --------------------------------------------------------
               Field i is an String, use c_buf[i] to store the value
               -------------------------------------------------------- */
            System.out.print( c_buf[i] + " ");
         }
         else{
            System.out.print( f_buf[i] + " ");
         }
      }

      System.out.println( );    // Print newline to separate records
   }

}

