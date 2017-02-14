CREATE DEFINER=`root`@`localhost` PROCEDURE `split_cols`()
BEGIN
	DECLARE done INT DEFAULT FALSE;
    DECLARE cur_name VARCHAR(50);
	DECLARE cur_id INT;
    DECLARE cursor1 CURSOR FOR SELECT * from sometbl;
	DECLARE CONTINUE HANDLER FOR NOT FOUND SET done = TRUE;
    
    OPEN cursor1;
    read_loop: LOOP
		FETCH cursor1 INTO cur_id, cur_name;
		
		IF done THEN
		  LEAVE read_loop;
		END IF;
		
        SET @pos = locate("|", cur_name);
		SET @a_name = cur_name;
		
        WHILE @pos > 0 DO
		  INSERT INTO sometbl VALUES (cur_id, substring(@a_name, 1, @pos-1));    
		  SET @a_name = substring(@a_name, @pos+1);
		  SET @pos = locate("|", @a_name);      
		  IF (@pos <= 0) THEN 
			UPDATE sometbl set id = cur_id, name =  @a_name where id = cur_id and name = cur_name;           
		  END IF;
		END WHILE;
	END LOOP;

  CLOSE cursor1;
END