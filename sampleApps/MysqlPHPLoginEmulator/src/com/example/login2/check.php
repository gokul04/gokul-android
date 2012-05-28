<!-- Keep this file in the /var/www directory.
 Apache should be running, mysql database entries to be entered (DB,user,pwd, table, fields). PHP should be installed--> 

    <?php
    $un=$_POST['username'];
    $pw=$_POST['password'];
    //connect to the db
    $user = "dbuser";
    $pswd = "dbpwd";
    $db = "mydb";
    $host = "localhost";
    $conn = mysql_connect($host, $user, $pswd);
    mysql_select_db($db);
    //run the query to search for the username and password the match  
    $query = "SELECT * FROM `mytable` WHERE user = '$un' AND pass = '$pw'";
    $result = mysql_query($query) or die("Unable to verify user because : " . mysql_error());
    //this is where the actual verification happens  
    if(mysql_num_rows($result) > 0)  
    echo 1;  // for correct login response  
    else  
    echo 0; // for incorrect login response  
    ?>
