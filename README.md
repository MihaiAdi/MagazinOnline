# MagazinOnline
Magazin Online


Aplicația pe care am creat-o are rolul de a centraliza produsele dintr-un magazine online. Baza de date continue date despre produse, stocul depozitului, date despre client precum nume, adresa, numar de telefon etc. 

Descrierea tabelelor

Principalul tabel al bazei de date este “Produse”. Acesta conține ID-ul produsului, nume, producator, pretul aferent, cantitatea disponibila, o scurta descriere si ID-ul departamentului din care face parte.

“Departamente” este tabelul care conține coloanele cu ID-ul departamentului, numele acestuia, numele managerului departamentului, precum și ID-ul furnizorului.

“Furnizori” este tabelul care este pus în legătură cu “Departamente”. Acesta se ocupă cu aprovizonarea de marfă pentru fiecare departament în parte. Conține coloanele : Furnizor ID, numele furnizorului, categoria pe care o aprovizionează.

Tabelul  “Comenzi” este utilizat pentru a stabili ce produse au fost comandate, pentru a putea fi scăzute din stoc. El conține coloanele pentru ID-ul comenzii, ID-ul clientului, Pretul total al comenzii, data plasării și data livrării.

“Clienți” este legat de tabelul  “Comenzi”, are rolul de a atribui fiecărei comenzi un autor, conține coloanele: ClientID, numele si prenumele clientului, adresa, numarul de telefon, email-ul, CNP-ul și data nașterii.   


 Constrângeri:
Am ales ca numărul de telefon si CNP-ul sa fie de 10, respectiv 13 cifre.
Câmpurile ProdusID, DepartamentID, FurnizoriID, ComandaID si ClientID sunt unice.

Funcționarea aplicației:

Aplicația începe rularea cu pagina de login pentru administrator.
Dupa logarea cu username-ul si password-ul corecte se deschide un meniu principal de unde se poate selecta ce operații să se efectueze. De aici se poate alege sa se adauge / sterge produs, adauge / sterge client, se poate da update la produs (cantitatea din stoc) sau la client (numarul de telefon). 


Interogări simple :

1.  	SELECT p.Nume,F.NumeFurnizor\n" +
        "from Produse p inner join Departamente d  on p.DepartamentID = d.DepartamentID\n" +
        "    inner join Furnizori F on d.FurnizorID = F.FurnizorID

2. 	 SELECT c.Nume,SUM(CO.PretTotal) AS TOTAL FROM Comenzi CO INNER JOIN Clienti C on C.ClientID = Co.ClientID\n" +
        "group by c.Nume order by SUM(CO.PretTotal) DESC

3.  	SELECT CO.ComandaID,SUM(PC.Cantitate) AS NrProduseComandate FROM Comenzi CO INNER JOIN ProduseComandate PC on CO.ComandaID = PC.ComandaID\n" +
        "group by CO.ComandaID

4.  	SELECT F.NumeFurnizor, F.CategorieFurnizor, d.NumeDepartament FROM Departamente D inner join Furnizori F on F.FurnizorID = D.FurnizorID

5.  	SELECT d.NumeDepartament  , sum(p.Stoc) as Stoc FROM Departamente d inner join Produse P on d.DepartamentID = P.DepartamentID\n" +
        "group by d.NumeDepartament order by sum(p.Stoc) desc

6. 	 SELECT c.Nume, count(C2.ComandaID) AS 'NrComenzi' FROM Clienti C inner join Comenzi C2 on C.ClientID = C2.ClientID\n" +
        "group by c.Nume order by count(C2.ComandaID) desc

Interogări complexe: 

1. 	SELECT C.Nume, C.Prenume
FROM Clienti C
ORDER BY (SELECT avg(CO.PretTotal)from Comenzi CO
     where co.ClientID = C.ClientID ) DESC

2. 	SELECT C.Nume + ' ' + C.Prenume AS Client
FROM Clienti C, Comenzi CO , ProduseComandate PC , Produse P
WHERE C.ClientID = CO.ClientID AND CO.ComandaID = PC.ComandaID  AND PC.ProdusID = P.ProdusID AND P.DepartamentID IN
                              (SELECT DepartamentID FROM Departamente WHERE P.DepartamentID = 10001 )

3. 	SELECT C.Nume + ' ' + C.Prenume AS Client
FROM Clienti C, Comenzi CO , ProduseComandate PC , Produse P,Departamente d
WHERE C.ClientID = CO.ClientID AND CO.ComandaID = PC.ComandaID  AND PC.ProdusID = P.ProdusID AND p.DepartamentID = d.DepartamentID and D.FurnizorID IN
                                                                                                 (SELECT FurnizorID FROM Furnizori WHERE d.FurnizorID = 1003)

4. 	SELECT C.Nume + ' ' + C.Prenume AS Client
FROM Clienti C, Comenzi CO , ProduseComandate PC
WHERE C.ClientID = CO.ClientID AND CO.ComandaID = PC.ComandaID AND PC.ProdusID IN (SELECT ProdusID FROM Produse WHERE PC.ProdusID = 100003 )


