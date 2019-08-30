<html>
   <head>
   <title> CS377 MySQL Web client</title>
   </head>
   <body>
      
   <H3>
   <HR>
   Answer to the query
   <HR>
   </H3>
   <P> 
   <UL>
      
   <?php
   # THIS CODE IS MY OWN WORK, IT WAS WRITTEN WITHOUT CONSULTING A TUTOR OR CODE WRITTEN BY OTHER STUDENTS - Gene Lee   
   $conn = mysqli_connect("holland.mathcs.emory.edu","cs377", "abc123", "spjDB");    
      
   if ( mysqli_connect_errno() != 0 )     # -----------  check connection error
   {      
      printf("Connect failed: %s\n", mysqli_connect_error());
      exit(1);
   } 

   $sname = $_POST["sname"];
   $scity = $_POST["scity"];
   $pname = $_POST["pname"];
   $pcity = $_POST["pcity"];
   $jname = $_POST["jname"];
   $jcity = $_POST["jcity"];

  if (strpos($sname, "*") === FALSE && strpos($sname, "?") === FALSE)
    $sname = " and sname = \"" . $sname . "\"";
  else{
    $sname = " and sname LIKE \"" . $sname . "\"";
      for ( $i = 0; $i < strlen($sname); $i++ )                   
      {
      if ( $sname[$i] == '*' )
         $sname[$i] = '%';
       if ( $sname[$i] == '?' )
         $sname[$i] = '_';
      }
  }

  if (strpos($scity, "*") === FALSE && strpos($scity, "?") === FALSE)
    $scity = " and supplier.city = \"" . $scity . "\"";
  else{
    $scity = " and supplier.city LIKE \"" . $scity . "\"";
  for ( $i = 0; $i < strlen($scity); $i++ )                   
      {
      if ( $scity[$i] == '*' )
         $scity[$i] = '%';
       if ( $scity[$i] == '?' )
         $scity[$i] = '_';
      }  }


  if (strpos($pname, "*") === FALSE && strpos($pname, "?") === FALSE)
    $pname = " and pname = \"" . $pname . "\"";
  else{
    $pname = " and pname LIKE \"" . $pname . "\"";
  for ( $i = 0; $i < strlen($pname); $i++ )                   
      {
      if ( $pname[$i] == '*' )
         $pname[$i] = '%';
       if ( $pname[$i] == '?' )
         $pname[$i] = '_';

      }  }


  if (strpos($pcity, "*") === FALSE && strpos($pcity, "?") === FALSE)
    $pcity = " and part.city = \"" . $pcity . "\"";
  else{
    $pcity = " and part.city LIKE \"" . $pcity . "\"";
    for ( $i = 0; $i < strlen($pcity); $i++ )                   
      {
      if ( $pcity[$i] == '*' )
         $pcity[$i] = '%';
       if ( $pcity[$i] == '?' )
         $pcity[$i] = '_';
      }}

  if (strpos($jname, "*") === FALSE && strpos($jname, "?") === FALSE)
    $jname = " and jname = \"" . $jname . "\"";
  else{    $jname = " and jname LIKE \"" . $jname . "\"";

  for ( $i = 0; $i < strlen($jname); $i++ )                   
      {
      if ( $jname[$i] == '*' )
         $jname[$i] = '%';
       if ( $jname[$i] == '?' )
         $jname[$i] = '_';
      }}
 
  if (strpos($jcity, "*") === FALSE && strpos($jcity, "?") === FALSE)
    $jcity = " and proj.city = \"" . $jcity . "\"";
  else{
    $jcity = " and proj.city LIKE \"" . $jcity . "\"";
      for ( $i = 0; $i < strlen($jcity); $i++ )                   
      {
      if ( $jcity[$i] == '*' )
         $jcity[$i] = '%';
       if ( $jcity[$i] == '?' )
         $jcity[$i] = '_';
      }
  }

  if ($sname == " and sname = \"\"")
    $sname = "";

  if ($scity == " and supplier.city = \"\"")
    $scity = "";

  if ($pname == " and pname = \"\"")
    $pname = "";

  if ($pcity == " and part.city = \"\"")
    $pcity = "";

  if ($jname == " and jname = \"\"")
    $jname = "";

  if ($jcity == " and proj.city = \"\"")
    $jcity = "";

   $query = "select sname, supplier.city, pname, part.city, jname, proj.city, qty from supplier, part, proj, spj where supplier.snum = spj.snum and part.pnum = spj.pnum and proj.jnum = spj.jnum". $sname . $scity . $pname . $pcity . $jname . $jcity;   # Get the query input from the webpage


   print("<UL><TABLE bgcolor=\"#FFEEEE\" BORDER=\"5\">\n");
   print("<TR> <TD><FONT color=\"blue\"><B><PRE>\n");
   print( $query );   # echo the query
   print("</PRE></B></FONT></TD></TR></TABLE></UL>\n");
   print("<P><HR><P>\n");
      
   if ( ($result = mysqli_query($conn, $query)) == NULL )      # Execute query
   {      
      printf("Error: %s\n", mysqli_error($conn));
      exit(1);
   }

   print("<UL>\n");
   print("<TABLE bgcolor=\"lightyellow\" BORDER=\"5\">\n");

   # ------------------------------------------------------------
   # Print names of attributes in one row of the table
   # ------------------------------------------------------------
   print("<TR bgcolor=\"lightcyan\">\n");     # Start row of HTML table

      print ("<TH>Supplier Name</TH>");
      print ("<TH>Supplier City</TH>");
      print ("<TH>Part Name</TH>");
      print ("<TH>Part City</TH>");
      print ("<TH>Project Name</TH>");
      print ("<TH>Project City</TH>");
      print ("<TH>Quantity Shipped</TH>");

   print ("</TR>\n");   # End row of HTML table


   # ------------------------------------------------------------
   # Print the tuples
   # ------------------------------------------------------------
   while ( ($row = mysqli_fetch_row( $result )) != NULL )
   {      
      # Print one tuple as a row in table

      print("<TR>\n");     # Start row
      for ( $i = 0; $i < count($row); $i++ )
      {
         print ("<TD>" . $row[$i] . "</TD>");  # Print values in one row
      }
      print ("</TR>\n");   # End row
   }      

   print("</TABLE>\n");
   print("</UL>\n");
   print("<P>\n");
      
   mysqli_free_result($result);
      
   mysqli_close($conn);

   ?>     
      
   </UL>
   <P> 
   <HR>
   <HR>
   <HR>
   <HR>