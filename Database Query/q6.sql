/*
For every supplier, list his snum and the pnum
of the heaviest part that he ships
*/

select distinct s1.snum, p1.pnum
from   supplier s1, spj x1, part p1
where  s1.snum = x1.snum
  and  p1.pnum = x1.pnum
  and  p1.weight >= ALL
           (select weight              /* Weights of parts shipped by s1 */
            from   spj x2, part p2
  	    where  p2.pnum = x2.pnum
	      and  x2.snum = s1.snum
           )
