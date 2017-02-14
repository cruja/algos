CREATE DEFINER=`root`@`localhost` PROCEDURE `bugcounter`(start_date DATE, end_date DATE)
BEGIN
	SET	@counter_date = start_date;	
	CREATE TEMPORARY TABLE IF NOT EXISTS bugs_counters ( day DATE, bug_count INT );  
	PREPARE stmt1 FROM  'insert into bugs_counters (SELECT (?) as day,count(*)  from bugs where open_date <= (?) and (close_date is NULL or ?<close_date));'; 
	while @counter_date <= end_date DO
		 EXECUTE stmt1 USING @counter_date,@counter_date, @counter_date;			
		  
		 SET @counter_date=DATE(DATE_ADD(@counter_date, INTERVAL 1 DAY));
	END WHILE;
    DEALLOCATE PREPARE stmt1;
    SELECT * from bugs_counters;
    DROP TABLE bugs_counters;
END