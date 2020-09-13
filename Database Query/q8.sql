/* Find snum of supplier that only supplies to projects 
   in city that the supplier lives */

/* select distinct snum
   from   supplier S
   where  {project in S's city} contains {project supplied by S}
   where  NOT EXISTS {project supplied by S} - {project in S's city} 
   where  NOT EXISTS 
          (select * from proj where
                    jnum IN     (project supplied by S) 
                and jnum NOT IN (project in S's city)   )
*/
       
select distinct snum
from   supplier S
where  NOT EXISTS 
       (select * 
        from proj 
        where jnum IN     (select jnum from spj where snum=S.snum)
          and jnum NOT IN (select jnum from proj where city=S.city) 
       )
