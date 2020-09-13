/*
Get supplier numbers of suppliers who do not supply to         
any project in city that the supplier is based.
*/


select distinct snum
from   supplier S
where  snum not in
         (select snum             /* Suppliers in S.city */
          from spj, proj
          where spj.jnum = proj.jnum
          and   proj.city = S.city
         )
