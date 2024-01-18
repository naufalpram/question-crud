use classicmodels;

-- Nomor 1

SELECT * FROM  `15_jan_customers` jc 
LEFT JOIN `15_jan_orders` jo
ON jc.id  = jo.customer_id
WHERE jo.customer_id is NULL;

-- Nomor 2

SELECT jc.name , SUM(jo.amount) FROM `15_jan_customers` jc 
JOIN `15_jan_orders` jo 
ON jc.id = jo.customer_id
GROUP BY jc.name;

-- Nomor 3
SELECT jc.name , COUNT(jo.id) AS count FROM `15_jan_customers` jc
LEFT JOIN `15_jan_orders` jo 
ON jc.id = jo.customer_id
GROUP BY jc.name
ORDER BY count DESC;

-- Nomor 4
SELECT jc.name , COUNT(DISTINCT jo.sales_id) AS count FROM `15_jan_customers` jc
LEFT JOIN `15_jan_orders` jo 
ON jc.id = jo.customer_id
GROUP BY jc.name
HAVING count >= 2;

-- Nomor 5
SELECT js.name , SUM(js.commission * jo.amount) AS sum  FROM `15_jan_sales` js
LEFT JOIN `15_jan_orders` jo
ON js.id = jo.sales_id
GROUP BY js.name
ORDER BY sum DESC
LIMIT 1;

SELECT komisi.name, sum(komisi.komisiTiapCust) as totalKomisi
FROM (
  SELECT js.name , jo.amount * js.commission as komisiTiapCust
  FROM 15_jan_orders jo INNER JOIN 15_jan_sales js on jo.sales_id = js.id
  ) as komisi
GROUP BY komisi.name
ORDER BY sum(komisi.komisiTiapCust) DESC
-- LIMIT 1;
