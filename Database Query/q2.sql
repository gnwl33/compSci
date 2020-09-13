select distinct j.jnum
from   proj j, spj x, part p
where  j.jnum = x.jnum
  and  p.pnum = x.pnum
  and  p.color='Red'
  and  p.city = j.city

