/*
   Find snum for suppliers that do not supply any Nuts
*/

select snum
from supplier
where snum not in
   (select snum              /* Supplier who supplies Nuts */
    from spj, part
    where spj.pnum = part.pnum
    and   pname = 'Nut'
   )
order by snum
