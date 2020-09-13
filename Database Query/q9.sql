/*
Get snum of suppliers that supply/ship all (= every one) parts 
  that are used in project "j6". 

A standard division query...

    => suppliers where: {parts# from S} contains {part# used in j6}
    => suppliers where: {part# used in j6} - {parts# from S} = EMPTY
*/

select distinct snum
from supplier S
where not exists
     (select pnum
      from part
      where pnum IN     (select pnum from spj where jnum='j6')
        and pnum NOT IN (select pnum from spj where snum=S.snum)
     )
order by snum
