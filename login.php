<?php
    require "init.php";
    
    
    $number = isset($_POST["number"]) ? $_POST["number"]: '';
    $password =isset($_POST["password"]) ? $_POST["password"]: '';
    
    $sql = "SELECT * FROM user_table WHERE number = '$number' and password = '$password'";
    
    $result = mysqli_query($conn,$sql);
    
    if(mysqli_num_rows($result)>0){
         echo "Success";
    }else{
        echo "Invalid Credentials";
    }
    
?>