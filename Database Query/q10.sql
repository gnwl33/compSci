/*
Get jnum of projects that have shipments from all (= every one) suppliers
  that live in the city where the project is located.

E.g.: if project X is in Atlanta and the suppliers S1 and S2
      live in Atlanta, than project X must have shipments from
	supplier S1 and S2 in the spj relation

    select jnum
    from   proj J
    where  {supplier for J} contains {supplier lives in J's city}
    where  {supplier lives in J's city} - {supplier for J} = EMPTY
*/

select distinct jnum
from proj J
where not exists
     (select snum
      from   supplier
      where  snum IN     (select snum from supplier where city=J.city)
        and  snum NOT IN (select snum from spj where jnum=J.jnum)
     )
order by jnum


