/*
Find snum of suppliers who supply a part that is heavier than
every part supplied by supplier Green
*/


select distinct s1.snum
from   supplier s1, spj x1, part p1
where  s1.snum = x1.snum
  and  p1.pnum = x1.pnum
  and  p1.weight < ALL 
        ( select weight       /* Weights of part supplied by Green */
          from   supplier s2, spj x2, part p2
          where  s2.snum = x2.snum
            and  p2.pnum = x2.pnum
            and  s2.sname= 'Blake' 
        )
