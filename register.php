<?php


    require 'init.php';
   $name="";
    $city="";
    $blood_group="";
    $password="";
    $number="";
    $name=isset($_POST["name"]) ? $_POST["name"]: '';
    $city=isset($_POST["city"]) ? $_POST["city"]: '';
   $blood_group=isset($_POST["blood_group"]) ? $_POST["blood_group"]: '';
    $password=isset($_POST["password"]) ? $_POST["password"]: '';
   $number=isset($_POST["number"]) ? $_POST["number"]: '';
    
    $sql="INSERT INTO user_table(name,city,blood_group,password,number) VALUES('$name','$city','$blood_group','$passsword','$number')";
    
    
    
    
    $result = mysqli_query($conn,$sql);
    
    if($result)
    {
        echo "Success";
    }
   
    mysqli_close($conn);

?>