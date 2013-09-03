<?php  

$file_name = './pack1.xml'; // ИМЯ ФАЙЛА ДЛЯ РАЗБОРА 
//------------------------------------------------------------ 
$tmp_db =  file_get_contents($file_name); 
$tmp_db = str_replace ( '&', ' ', $tmp_db); 
$tmp_db = simplexml_load_string($tmp_db); 

for($i = 0 ; $i != count($tmp_db->price) ; $i++ ) 
    {  
        $tmp_res .= process_price($tmp_db->price***91;$i***93;)."\r\n" ; 
    }; 
     
$tmp_res = iconv('utf-8','windows-1251', $tmp_res); 
file_put_contents ('./result.csv' , $tmp_res); 
// конец :) 

//------------------------------------------------------------     
function process_price($price) 
{ 

    for($i = 0 ; $i != count($price->fields->field) ; $i++ ) 
    {  
        $tmp_price .= $price->fields->field***91;$i***93;***91;comment***93;." ; " ; 
    }; 
    $tmp_price .= "\r\n"; 
    for($i = 0 ; $i != count($price->rows->row) ; $i++ ) 
    {  
        $tmp_price .= process_row($price->rows->row***91;$i***93;)."\r\n" ; 
    }; 

    return $tmp_price; 
}; 

function process_row($row) 
{ 
    for($i = 0 ; $i != count($row->field) ; $i++ ) 
    {  
        $tmp_item .= $row->field***91;$i***93;***91;0***93;." ; " ; 
    }; 
    return $tmp_item; 
}; 

 ?>