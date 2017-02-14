CREATE DEFINER=`root`@`localhost` FUNCTION `initcap`(a_str TEXT) RETURNS text CHARSET utf8
BEGIN
	DECLARE result TEXT;
	SET result = "";
    SET @tmp_str = trim(a_str);
	SET @pos = LOCATE(" ", @tmp_str);
    WHILE @pos > 0 DO
		SET @word = substr(@tmp_str, 1, @pos -1);
        SET @word = concat(upper(left(@word, 1)), lower(substr(@word,2)));
        SET result = concat_ws(" ", result, @word);
         SET @tmp_str = ltrim(substr(@tmp_str, @pos+1));
         SET @pos = LOCATE(" ", @tmp_str);         
    END WHILE;    
    
	SET result = concat_ws(" ", result, concat(upper(left(@tmp_str, 1)), lower(substr(@tmp_str,2))));
	SET result = substr(result,2);
    
RETURN result;
END