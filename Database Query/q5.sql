/*
Find name of suppliers who do not supply any Nuts.
*/

select distinct jnum
from proj
where jnum not in
       ( select distinct jnum    /* proj's that use a 'Nut' */
         from spj, part
         where part.pnum = spj.pnum
         and   part.pname = 'Nut'
       )
  and jnum not in
       ( select distinct jnum    /* proj's that use a 'Bolt' */
         from spj, part
         where part.pnum = spj.pnum
         and   part.pname = 'Bolt'
       )

