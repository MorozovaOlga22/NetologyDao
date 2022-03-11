SELECT o.productName
FROM Order o
WHERE o.customer.id IN
      (SELECT c.id
       FROM Customer c
       WHERE lower(c.name) = lower(:name))