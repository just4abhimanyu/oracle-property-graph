https://github.com/topics/self-driving-car


# oracle property graph
Oracle property graph implementation for 23c version

Find friend of friend relation by PGQL Query

**SELECT distinct friend.name FROM MATCH All (p:Persons) -[:FRIENDS]-> {3}(friend:Persons) ON student_network_pgql
where p.name='Bob'**

{3} = number of relationship 
