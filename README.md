Entities and Database Tables:

-Employees (Alkalmazottak)
Two types: Project Manager (Projektmenedzser) and Programmer (Programozó)
Common fields:
*Name
*Address
*Date of Birth
*Phone Number
*Email
*Projects
Project Manager additional fields:
*Subordinates (Beosztottak)
Programmer additional fields:
*Role (Frontend/Backend/FullStack) - enum
*Internship Status (yes/no)
Projects (Projektek)
Fields:
*Project Manager (Projektmenedzser) (one per project)
*Subordinates (Beosztottak)
*Client (Megrendelő)
*Start Date of the Project (Projekt indulásának dátuma)
*Description (Leírás)
*Endpoints:

*List (Lista)
*Details (Részletek)
*Create (Létrehozás)
*Update (Módosítás)
*Delete (Törlés) (Instead of deleting from the database, just set a 'deleted' flag)
